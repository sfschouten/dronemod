package sfschouten.dronemod.pathfinding;

import sfschouten.dronemod.util.Logger;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.IntHashMap;
import net.minecraft.util.MathHelper;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public class PathFinder3D {

	/** Used to find obstacles */
	private IBlockAccess worldMap;

	/** The path being generated */
	private Path path = new Path();

	/** The points in the path */
	private IntHashMap pointMap = new IntHashMap();

	/** Selection of path points to add to the path */
	private PathPoint[] pathOptions = new PathPoint[32];

	/** should the PathFinder go through wodden door blocks */
	private boolean isWoddenDoorAllowed;

	/**
	 * should the PathFinder disregard BlockMovement type materials in its path
	 */
	private boolean isMovementBlockAllowed;
	private boolean isPathingInWater;

	/** tells the PathFinder to not stop pathing underwater */
	private boolean canEntityDrown;

	public PathFinder3D(IBlockAccess par1IBlockAccess, boolean doors, boolean moveBlock, boolean inWater, boolean canDrown) {
		this.worldMap = par1IBlockAccess;
		this.isWoddenDoorAllowed = doors;
		this.isMovementBlockAllowed = moveBlock;
		this.isPathingInWater = inWater;
		this.canEntityDrown = canDrown;
	}

	/**
	 * Creates a path from one entity to another within a minimum distance
	 */
	public PathEntity createEntityPathTo(Entity par1Entity, Entity par2Entity, float range) {
		return this.createEntityPathTo(par1Entity, par2Entity.posX, par2Entity.boundingBox.minY, par2Entity.posZ, range);
	}

	/**
	 * Creates a path from an entity to a specified location within a minimum
	 * distance
	 */
	public PathEntity createEntityPathTo(Entity par1Entity, int x, int y, int z, float range) {
		return this.createEntityPathTo(par1Entity, (double) ((float) x + 0.5F), (double) ((float) y + 0.5F), (double) ((float) z + 0.5F), range);
	}

	/**
	 * Internal implementation of creating a path from an entity to a point
	 */
	private PathEntity createEntityPathTo(Entity par1Entity, double dx, double dy, double dz, float range) {
		path.clearPath();
		pointMap.clearMap();
		int y;

		if (canEntityDrown && par1Entity.isInWater()) {
			y = (int) par1Entity.boundingBox.minY;
			int ex = MathHelper.floor_double(par1Entity.posX);
			int ez = MathHelper.floor_double(par1Entity.posZ);

			// increment i until a block that is not water is reached.
			for (Block block = worldMap.getBlock(ex, y, ez); block == Blocks.flowing_water || block == Blocks.water; block = worldMap.getBlock(ex, y, ez)) {
				++y;
			}

			isPathingInWater = false;
		} else {
			y = MathHelper.floor_double(par1Entity.boundingBox.minY + 0.5D);
		}

		// Start point
		int bbx = MathHelper.floor_double(par1Entity.boundingBox.minX);
		int bbz = MathHelper.floor_double(par1Entity.boundingBox.minZ);
		PathPoint startPathpoint = openPoint(bbx, y, bbz);

		// End point
		int wx = MathHelper.floor_double(dx - (double) (par1Entity.width / 2.0F));
		int wz = MathHelper.floor_double(dz - (double) (par1Entity.width / 2.0F));
		PathPoint endPathpoint = openPoint(wx, MathHelper.floor_double(dy), wz);

		// Entity Width
		int intwidth = MathHelper.floor_float(par1Entity.width + 1.0F);
		int intheight = MathHelper.floor_float(par1Entity.height + 1.0F);
		PathPoint entityWidthPathpoint = new PathPoint(intwidth, intheight, intwidth);

		PathEntity pathentity = addToPath(par1Entity, startPathpoint, endPathpoint, entityWidthPathpoint, range);

		// Some logging, printing the created pathentity
		Logger.logOut("FinalPoint: " + pathentity.getFinalPathPoint().toString());
		Logger.logOut("PathLength: " + pathentity.getCurrentPathLength());
		for (int t = 0; t < pathentity.getCurrentPathLength(); t++) {
			PathPoint p = pathentity.getPathPointFromIndex(t);
			Logger.logOut(t + ": " + p.xCoord + ", " + p.yCoord + ", " + p.zCoord);
		}

		return pathentity;
	}

	/**
	 * Adds a path from start to end and returns the whole path (args: unused,
	 * start, end, unused, maxDistance)
	 */
	private PathEntity addToPath(Entity par1Entity, PathPoint startPathPoint, PathPoint endPathPoint, PathPoint entityWidthPathpoint, float range) {
		PathPointWrapper startPathPointWrap = new PathPointWrapper(startPathPoint);
		
		startPathPointWrap.setTotalPathDistance(0.0F);
		startPathPointWrap.setDistanceToNext(startPathPointWrap.distanceToSquared(endPathPoint));
		startPathPointWrap.setDistanceToTarget(startPathPointWrap.getDistanceToNext());
		path.clearPath();
		path.addPoint(startPathPointWrap.getWrapped());

		PathPoint pathpoint3 = startPathPointWrap.getWrapped();
		while (!path.isPathEmpty()) {
			PathPoint dequeuee = path.dequeue();
			PathPointWrapper dequeueeWrap = new PathPointWrapper(dequeuee);
			
			if (dequeueeWrap.equals(endPathPoint)) {
				return createEntityPath(startPathPoint, endPathPoint);
			}

			if (dequeueeWrap.distanceToSquared(endPathPoint) < pathpoint3.distanceToSquared(endPathPoint)) {
				pathpoint3 = dequeueeWrap.getWrapped();
			}

			dequeueeWrap.setFirst(true);
			int i = findPathOptions(par1Entity, dequeueeWrap.getWrapped(), entityWidthPathpoint, endPathPoint, range);

			// Loop through the options found.
			for (int j = 0; j < i; ++j) {
				PathPoint optionPathpoint = pathOptions[j];
				PathPointWrapper optionPathpointWrap = new PathPointWrapper(optionPathpoint);
				float f1 = dequeueeWrap.getTotalPathDistance() + dequeueeWrap.distanceToSquared(optionPathpoint);

				if (!optionPathpointWrap.isAssigned() || f1 < optionPathpointWrap.getTotalPathDistance()) {
					optionPathpointWrap.setPrevious(dequeueeWrap.getWrapped());
					optionPathpointWrap.setTotalPathDistance(f1);
					optionPathpointWrap.setDistanceToNext(optionPathpointWrap.distanceToSquared(endPathPoint));

					if (optionPathpoint.isAssigned()) {
						path.changeDistance(optionPathpointWrap.getWrapped(), optionPathpointWrap.getTotalPathDistance() + optionPathpointWrap.getDistanceToNext());
					} else {
						optionPathpointWrap.setDistanceToTarget(optionPathpointWrap.getTotalPathDistance() + optionPathpointWrap.getDistanceToNext());
						path.addPoint(optionPathpoint);
					}
				}
			}
		}

		if (pathpoint3 == startPathPointWrap.getWrapped()) {
			return null;
		} else {
			return createEntityPath(startPathPointWrap.getWrapped(), pathpoint3);
		}
	}

	/**
	 * populates pathOptions with available points and returns the number of
	 * options found (args: unused1, currentPoint, unused2, targetPoint,
	 * maxDistance)
	 * 
	 * @return the amount of options added to pathOptions array
	 */
	private int findPathOptions(Entity par1Entity, PathPoint fromPathPoint, PathPoint entityWidthPathPoint, PathPoint endPathPoint, float range) {
		int i = 0;
		byte b0 = 0;

		int collType = doesEntityCollideAt(par1Entity, fromPathPoint.xCoord, fromPathPoint.yCoord + 1, fromPathPoint.zCoord, entityWidthPathPoint);
		if (collType == 1) {
			b0 = 1;
		}

		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			int offX = fromPathPoint.xCoord + dir.offsetX;
			int offY = fromPathPoint.yCoord + dir.offsetY;
			int offZ = fromPathPoint.zCoord + dir.offsetZ;
			PathPoint safePoint = getSafePoint(par1Entity, offX, offY, offZ, entityWidthPathPoint, b0);

			if (safePoint != null && !safePoint.isFirst && safePoint.distanceTo(endPathPoint) < range) {
				pathOptions[(i++)] = safePoint;
			}
		}

		return i;
	}

	/**
	 * Returns a point that the entity can safely move to
	 */
	private PathPoint getSafePoint(Entity par1Entity, int par2, int par3, int par4, PathPoint par5PathPoint, int par6) {
		PathPoint pathpoint1 = null;
		if (worldMap.isAirBlock(par2, par3, par4)) {
			pathpoint1 = openPoint(par2, par3, par4);
		}

		return pathpoint1;
	}

	/**
	 * Returns a mapped point or creates and adds one
	 */
	private final PathPoint openPoint(int par1, int par2, int par3) {
		int l = PathPoint.makeHash(par1, par2, par3);
		PathPoint pathpoint = (PathPoint) this.pointMap.lookup(l);

		if (pathpoint == null) {
			pathpoint = new PathPoint(par1, par2, par3);
			this.pointMap.addKey(l, pathpoint);
		}

		return pathpoint;
	}

	/**
	 * Checks if an entity collides with blocks at a position. Returns 1 if
	 * clear, 0 for colliding with any solid block, -1 for water(if avoiding
	 * water) but otherwise clear, -2 for lava, -3 for fence, -4 for closed
	 * trapdoor, 2 if otherwise clear except for open trapdoor or water(if not
	 * avoiding)
	 */
	public int doesEntityCollideAt(Entity par1Entity, int par2, int par3, int par4, PathPoint entityWidthPathPoint) {
		return PathFinder.func_82565_a(par1Entity, par2, par3, par4, entityWidthPathPoint, this.isPathingInWater, this.isMovementBlockAllowed,
				this.isWoddenDoorAllowed);
	}

	/**
	 * Returns a new PathEntity for a given start and end point
	 */
	private PathEntity createEntityPath(PathPoint par1PathPoint, PathPoint par2PathPoint) {
		int i = 1;
		PathPointWrapper pathpoint2;

		for (pathpoint2 = new PathPointWrapper(par2PathPoint); pathpoint2.getPrevious() != null; pathpoint2 = new PathPointWrapper(pathpoint2.getPrevious())) {
			++i;
		}

		PathPoint[] apathpoint = new PathPoint[i];
		pathpoint2 = new PathPointWrapper(par2PathPoint);
		--i;

		for (apathpoint[i] = par2PathPoint; pathpoint2.getPrevious() != null; apathpoint[i] = pathpoint2.getWrapped()) {
			pathpoint2 = new PathPointWrapper(pathpoint2.getPrevious());
			--i;
		}

		return new PathEntity(apathpoint);
	}
}
