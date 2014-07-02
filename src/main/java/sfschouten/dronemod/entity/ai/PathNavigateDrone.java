package sfschouten.dronemod.entity.ai;

import net.minecraft.entity.Entity;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathFinder3D;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCache;
import net.minecraft.world.World;
import sfschouten.dronemod.entity.EntityDrone;

public class PathNavigateDrone extends PathNavigate {
	private EntityDrone pathfindingEntity;

	public PathNavigateDrone(EntityDrone pathfindingEntity, World par2World) {
		super(pathfindingEntity, par2World);
		this.pathfindingEntity = pathfindingEntity;
	}

	public PathEntity getPathToXYZ(double par1, double par3, double par5) {
		return getEntityPathToXYZ(this.pathfindingEntity, MathHelper.floor_double(par1), (int) par3, MathHelper.floor_double(par5), getPathSearchRange(),
				false, false, true, false);
	}

	public PathEntity getPathToEntityLiving(Entity par1Entity) {
		return getPathEntityToEntity(this.pathfindingEntity, par1Entity, getPathSearchRange(), false, false, true, false);
	}

	private PathEntity getPathEntityToEntity(EntityDrone par1Entity, Entity par2Entity, float range, boolean par4, boolean par5, boolean par6, boolean par7) {
		int floorX = (int) Math.floor(par2Entity.posX);
		int floorY = (int) Math.floor(par2Entity.posY);
		int floorZ = (int) Math.floor(par2Entity.posZ);
		return getEntityPathToXYZ(par1Entity, floorX, floorY, floorZ, range, par4, par5, par6, par7);
	}

	private PathEntity getEntityPathToXYZ(EntityDrone par1Entity, int bx, int by, int bz, float range, boolean par6, boolean par7, boolean par8, boolean par9) {
		int ax = MathHelper.floor_double(par1Entity.posX);
		int ay = MathHelper.floor_double(par1Entity.posY);
		int az = MathHelper.floor_double(par1Entity.posZ);
		int newRange = (int) (range + 8.0F);
		int x1 = ax - newRange;
		int y1 = ay - newRange;
		int z1 = az - newRange;
		int x2 = ax + newRange;
		int y2 = ay + newRange;
		int z2 = az + newRange;
		ChunkCache chunkcache = new ChunkCache(par1Entity.worldObj, x1, y1, z1, x2, y2, z2, 0);
		PathEntity pathentity = new PathFinder3D(chunkcache, par6, par7, false, par9).createEntityPathTo(par1Entity, bx, by, bz, range);
		if (pathentity != null) {
			PathPoint finalPoint = pathentity.getFinalPathPoint();
			if ((finalPoint == null) || (finalPoint.xCoord != bx) || (finalPoint.yCoord != by) || (finalPoint.zCoord != bz)) {
				pathentity = null;
			}
		}
		return pathentity;
	}

	public float getPathSearchRange() {
		// TODO change to something logical
		return (float) 75.0D;
	}
}
