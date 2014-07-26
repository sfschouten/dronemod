package sfschouten.dronemod.entity.ai;

import java.text.DecimalFormat;
import java.util.List;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.nbt.NBTTagCompound;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.item.module.DroneTaskResult;
import sfschouten.dronemod.item.module.DroneTaskSubject;
import sfschouten.dronemod.item.module.ItemAdvancedTaskModule;
import sfschouten.dronemod.item.module.ItemBasicTaskModule;
import sfschouten.dronemod.item.module.ItemModule;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.registry.MarkerRegistry;
import sfschouten.dronemod.registry.MarkerRegistry.Registration;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.util.Logger;

public class DroneBasicTaskAI extends EntityAIBase {
	private int currentRadius = -1;
	private int currentBlock = 0;
	private boolean goingDeep;
	private int currentDepth = 0;
	TileEntityMarker currentWork;
	private EntityDrone drone;
	private boolean initialized;

	public DroneBasicTaskAI(EntityDrone drone) {
		this.drone = drone;
		this.setMutexBits(1);
	}

	@Override
	public boolean shouldExecute() {
		return drone.isAwake();
	}

	@Override
	public void startExecuting() {
		if(!initialized){
			init();
		}
		
		int intPosX = (int) drone.posX;
		int intPosY = (int) drone.posY;
		int intPosZ = (int) drone.posZ;

		int currentX = currentWork.xCoord + currentRadius;
		int currentZ = currentWork.zCoord + currentRadius;
		int currentY = currentWork.yCoord - currentDepth;

		// Determine coords based on currentblock and currentradius.
		if (((double) currentBlock) / 4 < (0.5D * currentRadius)) {
			currentX -= currentBlock;
		} else if (((double) currentBlock) / 4 < currentRadius) {
			currentX -= (2 * currentRadius);
			currentZ -= (currentBlock - (2 * currentRadius));
		} else if (((double) currentBlock) / 4 < (1.5D * currentRadius)) {
			currentX -= ((2 * currentRadius) - (currentBlock % (2 * currentRadius)));
			currentZ -= (2 * currentRadius);
		} else {
			currentZ -= ((2 * currentRadius) - (currentBlock % (2 * currentRadius)));
		}
		
		Logger.log("x:" + currentX + "y:" + currentY + "z:" + currentZ);
		Logger.log("DroneLoc: x: " + drone.posX + "; y: " + drone.posY + "; z: " + drone.posZ);
		
		if (!drone.isInBlockAt(currentX, currentY + 1, currentZ)) {
			// Drone has not arrived where he needs to be so move towards it.
			Logger.log("tryMoveTo");
			drone.getNavigator().tryMoveToXYZ(currentX, currentY + 1, currentZ, 1.0D);
		} else {
			DroneTaskSubject subject = new DroneTaskSubject();
			subject.setEntity(drone);
			subject.setX(currentX);
			subject.setY(currentY);
			subject.setZ(currentZ);
			Logger.info(drone.getTaskModules().size());
			for (ItemTaskModule m : drone.getTaskModules()) {
				DroneTaskResult result = m.performTask(drone, subject);
				
				switch (result) {
				case resourcelow:
					Logger.log("Resource Low");
					drone.setRestocking(true);
					break;
				case success:
					Logger.log("Success");
					increment();
					break;
				case wrongEnvironment:
					Logger.log("Wrong Environment");
					increment();
					break;
				case noModule:
					break;
				default:
					break;
				}
			}
		}

		super.startExecuting();
	}

	private void increment() {
		// Get circumference
		int cf = (int) (Math.pow((1 + (2 * currentRadius)), 2) - Math.pow((1 + (2 * (currentRadius - 1))), 2)) - 1;
		if (currentBlock < cf) {
			// Not at the end of this round so one up currentblock.
			currentBlock += 1;
		} else {
			// Round complete so set currentblock to 0.
			currentBlock = 0;
			if (currentRadius > 1) {
				currentRadius -= 1;
			} else {
				if (goingDeep) {
					currentDepth++;
				} else {
					int currentPos = drone.getWorkMarkers().lastIndexOf(currentWork);
					if (drone.getWorkMarkers().size() - 1 != currentPos) {
						currentPos++;
						currentWork = drone.getWorkMarkers().get(currentPos);
					} else if (drone.getWorkMarkers().size() > 1) {
						currentWork = drone.getWorkMarkers().get(0);
					}
				}
				currentRadius = currentWork.getRadius();
			}
		}
	}

	private void init(){
		currentWork = drone.getCurrentWork();
		currentRadius = currentWork.getRadius();
		
		goingDeep = true;
		for(ItemTaskModule m : drone.getTaskModules()){
			ItemBasicTaskModule btm = (ItemBasicTaskModule) m;
			if(!btm.hasDepth()){
				goingDeep = false;
			}
		}
		
		initialized = true;
	}
	
	@Override
	public boolean continueExecuting() {
		return !drone.getNavigator().noPath() && drone.isAwake();
	}

	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		par1nbtTagCompound.setInteger("CurrentBlock", currentBlock);
		par1nbtTagCompound.setInteger("CurrentRadius", currentRadius);
	}

	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		currentBlock = par1nbtTagCompound.getInteger("CurrentBlock");
		currentRadius = par1nbtTagCompound.getInteger("CurrentRadius");
	}

	public int getCurrentRadius() {
		return currentRadius;
	}

	public void setCurrentRadius(int currentRadius) {
		this.currentRadius = currentRadius;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}
}
