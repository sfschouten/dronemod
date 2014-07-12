package sfschouten.dronemod.pathfinding;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.util.Logger;

/**
 * Use instead of PathNavigate when you want to find a path using PathFinder3D 
 * 
 * @author Stefan
 *
 */
public class PathNavigate3D extends PathNavigate {
	private int searchRange = 75;
	
	/**
	 * Necessary because theEntity in PathNavigate is not accessible.
	 */
	private Entity pathfindingEntity;

	/**
	 * Constructor for 3D version of PathNavigate.
	 * 
	 * @param pathfindingEntity The entity from which the path needs to be found.
	 * @param par2World The world in which the path needs to be found.
	 */
	public PathNavigate3D(EntityDrone pathfindingEntity, World par2World, int searchRange) {
		super(pathfindingEntity, par2World);
		this.pathfindingEntity = pathfindingEntity;	
		this.searchRange = searchRange;
	}

	@Override
	public net.minecraft.pathfinding.PathEntity getPathToXYZ(double par1, double par3, double par5) {
		return getEntityPathToXYZ(this.pathfindingEntity, MathHelper.floor_double(par1), (int)par3, MathHelper.floor_double(par5), searchRange, false, false, true, false);
	}

	@Override
	public net.minecraft.pathfinding.PathEntity getPathToEntityLiving(Entity par1Entity) {
		return getPathEntityToEntity(this.pathfindingEntity, par1Entity, searchRange, false, false, true, false);
	}

	/**
	 * Use to find a path from one entity to another.
	 * 
	 * @param par1Entity The entity from which the path needs to be found.
	 * @param par2Entity The entity to which the path needs to be found.
	 * @param range The maximum distance of the path to be found.
	 * @param doors
	 * @param moveBlock
	 * @param inWater
	 * @param canDrown
	 * @return
	 */
	private PathEntity getPathEntityToEntity(Entity par1Entity, Entity par2Entity, int range, boolean doors, boolean moveBlock, boolean inWater, boolean canDrown) {
		int floorX = (int) Math.floor(par2Entity.posX);
		int floorY = (int) Math.floor(par2Entity.posY);
		int floorZ = (int) Math.floor(par2Entity.posZ);
		return getEntityPathToXYZ(par1Entity, floorX, floorY, floorZ, range, doors, moveBlock, inWater, canDrown);
	}

	/**
	 * Method to find a path between the entity and the XYZ coordinates, using the Pathfinder3D class.
	 * 
	 * @param par1Entity The entity from which the path needs to be found.
	 * @param bx The x coordinate to find a path to.
	 * @param by The y coordinate to find a path to.
	 * @param bz The z coordinate to find a path to.
	 * @param range The maximum distance of the path to be found.
	 * @param doors 
	 * @param moveBlock
	 * @param inWater
	 * @param canDrown
	 * @return
	 */
	private PathEntity getEntityPathToXYZ(Entity par1Entity, int bx, int by, int bz, int range, boolean doors, boolean moveBlock, boolean inWater, boolean canDrown) {
		//Current position of entity as integers.
		int ax = MathHelper.floor_double(par1Entity.posX);
		int ay = MathHelper.floor_double(par1Entity.posY);
		int az = MathHelper.floor_double(par1Entity.posZ);
		
		//Get the maximum and minimum XYZ that a path can be found to.
		int x1 = ax - range;
		int y1 = ay - range;
		int z1 = az - range;
		int x2 = ax + range;
		int y2 = ay + range;
		int z2 = az + range;
		
		ChunkCache chunkcache = new ChunkCache(par1Entity.worldObj, x1, y1, z1, x2, y2, z2, 0);
		PathFinder3D pathFinder3D = new PathFinder3D(chunkcache, doors, moveBlock, inWater, canDrown);
		PathEntity pathentity = pathFinder3D.createEntityPathTo(par1Entity, bx, by, bz, range);
		
		if (pathentity != null) {
			PathPoint finalPoint = pathentity.getFinalPathPoint();
			//Check if the right finalPoint is reached.
			if ((finalPoint == null) || (finalPoint.xCoord != bx) || (finalPoint.yCoord != by) || (finalPoint.zCoord != bz)) {
				pathentity = null;
			}
		}
		return pathentity;
	}
}
