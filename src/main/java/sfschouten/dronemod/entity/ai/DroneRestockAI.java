package sfschouten.dronemod.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.util.Logger;

public class DroneRestockAI extends EntityAIBase {
	EntityDrone drone;
	
	public DroneRestockAI(EntityDrone drone){
		this.drone = drone;
		this.setMutexBits(1);
	}
	
	@Override
	public boolean shouldExecute() {
		return drone.isRestocking() && drone.isAwake();
	}

	@Override
	public void startExecuting() {
		TileEntityDroneBase base = drone.getBase();
		Logger.log("startExecutingRestock");
		Logger.log("baseXYZ: " + base.xCoord +", "+ base.yCoord + ", " + base.zCoord);
		Logger.log("droneXYZ: " + drone.posX +", "+ drone.posY + ", " + drone.posZ);
		
		if(!drone.isInBlockAt(base.xCoord, base.yCoord+1, base.zCoord)){
			Logger.log("drone not at base, go there");
			drone.getNavigator().tryMoveToXYZ(base.xCoord, base.yCoord+1, base.zCoord, 1.0D);
		}else{
			Logger.log("drone is at base");
			Item restockItem = drone.getTaskModules().get(0).getRestockItem();
			int restockItemDamage = drone.getTaskModules().get(0).getRestockItemDamageValue();
			
			IInventory adjInv = drone.getBase().getAdjacentInventory();
			if (adjInv == null){
				Logger.log("No adjacent inventory found!");
				return;
			}
			
			//Loop through the stacks in the adjacent inventory.
			for(int slot = 0; slot < adjInv.getSizeInventory(); slot++){
				ItemStack stack = adjInv.getStackInSlot(slot);
				if(stack != null){	
    				if(stack.getItem() == restockItem && stack.getItem().getDamage(stack) == restockItemDamage){
    					//This is a stack that matches the restockitem.
    					int residualIncrease = 0;
    					
    					//If stack is smaller then what can be transferred then use that amount.
    					if(stack.stackSize < drone.getRestockSpeed()){
    						residualIncrease = stack.stackSize;
    					}else{
    						residualIncrease = drone.getRestockSpeed();
    					}
    					
    					//TODO make restock process that takes multiple AI calls.
    					//Loop through drone inventory
						for(int localSlot = 0; localSlot < drone.getActualInventory().getSizeInventory(); localSlot++){
							ItemStack localStack = drone.getActualInventory().getStackInSlot(localSlot);
							
							if(localStack == null){
								//This stack in the drones inventory is null, 
								ItemStack newStack = new ItemStack(restockItem);
								newStack.stackSize = residualIncrease;
								stack.stackSize -= residualIncrease;
								if(stack.stackSize == 0){
									adjInv.setInventorySlotContents(slot, null);
								}
								drone.getActualInventory().setInventorySlotContents(localSlot, newStack);
								drone.sleep(20);
								return;
							}else if(localStack.getItem() == restockItem && localStack.getItem().getDamage(stack) == restockItemDamage){
								if(localStack.stackSize + residualIncrease > localStack.getMaxStackSize()){
									int difference = localStack.getMaxStackSize() - localStack.stackSize;
									localStack.stackSize += difference;
									stack.stackSize -= difference;
									residualIncrease -= difference;
								}else{
									localStack.stackSize += residualIncrease;
									stack.stackSize -= residualIncrease;
									if(stack.stackSize == 0){
										adjInv.setInventorySlotContents(slot, null);
									}
									drone.sleep(20);
									return;
								}
							}
						}
						drone.setRestocking(false);
    				}
				}
			}
		}
		super.startExecuting();
	}
	
	@Override
	public boolean continueExecuting() {
		return !drone.getNavigator().noPath() && drone.isRestocking() && drone.isAwake();
	}
	
	@Override
	public boolean isInterruptible(){
        return false;
    }
}
