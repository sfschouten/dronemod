package sfschouten.dronemod.entity.ai;

import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityDrone;
import net.minecraft.entity.ai.EntityMoveHelper;

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
		this.y = (y + 0.5D - 0.17D);
		this.z = z;
		this.speed = speed;
	}

	public void onUpdateMoveHelper() {
		if (this.entity.isAccelerating()) {
			//Logger.logOut("is this it?");
			if (this.x - 0.1D > this.entity.posX) {
				this.entity.motionX = this.speed;
			} else if (this.x + 0.1D < this.entity.posX) {
				this.entity.motionX = (-this.speed);
			}
			if (this.y - 0.1D > this.entity.posY) {
				this.entity.motionY = this.speed;
			} else if (this.y + 0.1D < this.entity.posY) {
				this.entity.motionY = (-this.speed);
			}
			if (this.z - 0.1D > this.entity.posZ) {
				this.entity.motionZ = this.speed;
			} else if (this.z + 0.1D < this.entity.posZ) {
				this.entity.motionZ = (-this.speed);
			}
		}
	}
}