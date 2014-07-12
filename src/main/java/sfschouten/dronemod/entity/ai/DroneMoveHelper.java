package sfschouten.dronemod.entity.ai;

import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.util.Logger;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.util.MathHelper;

public class DroneMoveHelper extends EntityMoveHelper {
	private final EntityDrone entity;
	private double x;
	private double y;
	private double z;
	private double speed;

	public DroneMoveHelper(EntityDrone par1EntityLiving) {
		super(par1EntityLiving);
		this.entity = par1EntityLiving;
		this.x = this.entity.posX;
		this.y = this.entity.posY;
		this.z = this.entity.posZ;
	}

	public void setMoveTo(double x, double y, double z, double speed) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.speed = speed;
	}

	public void onUpdateMoveHelper() {
		double motionX = 0;
		double motionY = 0;
		double motionZ = 0;
		
		double speed = this.speed * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue();
		//Logger.logOut("move" + x + "|" + y + "|" + z);
		if (this.x - 0.1D > this.entity.posX) {
			motionX = speed;
		} else if (this.x + 0.1D < this.entity.posX) {
			motionX = (speed * -1.0D);
		}
		
		if (this.y - 0.1D > this.entity.posY) {
			motionY = speed;
		} else if (this.y + 0.1D < this.entity.posY) {
			motionY = (speed * -1.0D);
		}
		
		if (this.z - 0.1D > this.entity.posZ) {
			motionZ = speed;
		} else if (this.z + 0.1D < this.entity.posZ) {
			motionZ = (speed * -1.0D);
		}
		
		this.entity.setVelocity(motionX, motionY, motionZ);
	}
}