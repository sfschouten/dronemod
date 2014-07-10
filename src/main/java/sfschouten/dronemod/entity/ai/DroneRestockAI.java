package sfschouten.dronemod.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;

public class DroneRestockAI extends EntityAIBase {
	EntityDrone drone;
	
	public DroneRestockAI(EntityDrone drone){
		this.drone = drone;
	}
	
	@Override
	public boolean shouldExecute() {
		//TODO change to a system where not just the first modules restock items get restocked.
		Item restockItem = drone.getModules().get(0).getRestockItem();
		int restockItemDamage = drone.getModules().get(0).getRestockItemDamageValue();
		int result = drone.getFirstSlotForItemAndDamage(restockItem, restockItemDamage);
		return result == -1;
	}

	@Override
	public void startExecuting() {
		TileEntityDroneBase base = drone.getBase();
		Logger.logOut("startExecutingRestock");
		if(!drone.isInBlockAt(base.xCoord, base.yCoord+1, base.zCoord)){
			Logger.logOut("drone not at base, go there");
			drone.getNavigator().tryMoveToXYZ(base.xCoord, base.yCoord, base.zCoord, 1.0D);
		}else{
			Logger.logOut("drone is at base");
			Item restockItem = drone.getModules().get(0).getRestockItem();
			int restockItemDamage = drone.getModules().get(0).getRestockItemDamageValue();
			
			IInventory adjInv = drone.getBase().getAdjacentInventory();
			if (adjInv == null){
				Logger.logOut("No adjacent inventory found!");
				return;
			}
			
			//Loop through adjacent inventory.
			for(int slot = 0; slot < adjInv.getSizeInventory(); slot++){
				ItemStack stack = adjInv.getStackInSlot(slot);
				if(stack != null){	
    				if(stack.getItem() == restockItem && stack.getItem().getDamage(stack) == restockItemDamage){
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
								ItemStack newStack = new ItemStack(restockItem);
								newStack.stackSize = residualIncrease;
								stack.stackSize -= residualIncrease;
								if(stack.stackSize == 0){
									adjInv.setInventorySlotContents(slot, null);
								}
								drone.getActualInventory().setInventorySlotContents(localSlot, newStack);
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
									return;
								}
							}
						}
    				}
				}
			}
		}
		super.startExecuting();
	}
	
	/*
	 * @Override
	public boolean executeAI(EntityDrone e) {
		if(e.goToBase()){
			e.posX = e.getBase().xCoord;
			e.posY = e.getBase().yCoord+1;
			e.posZ = e.getBase().zCoord;
			
			Item restockItem = e.getModules().get(0).getRestockItem();
			int restockItemDamage = e.getModules().get(0).getRestockItemDamageValue();
			
			IInventory adjInv = e.getBase().getAdjacentInventory();
			if (adjInv == null){
				return false;
			}
			
			//Loop through adjacent inventory.
			for(int slot = 0; slot < adjInv.getSizeInventory(); slot++){
				ItemStack stack = adjInv.getStackInSlot(slot);
				if(stack != null){	
    				if(stack.getItem() == restockItem && stack.getItem().getDamage(stack) == restockItemDamage){
    					int residualIncrease = 0;
    					
    					//If stack is smaller then what can be transferred then use that amount.
    					if(stack.stackSize < e.getRestockSpeed()){
    						residualIncrease = stack.stackSize;
    					}else{
    						residualIncrease = e.getRestockSpeed();
    					}
    					
    					//TODO make restock process that takes multiple AI calls.
    					//Loop through drone inventory
						for(int localSlot = 0; localSlot < e.getActualInventory().getSizeInventory(); localSlot++){
							ItemStack localStack = e.getActualInventory().getStackInSlot(localSlot);
							if(localStack == null){
								ItemStack newStack = new ItemStack(restockItem);
								newStack.stackSize = residualIncrease;
								stack.stackSize -= residualIncrease;
								if(stack.stackSize == 0){
									adjInv.setInventorySlotContents(slot, null);
								}
								e.getActualInventory().setInventorySlotContents(localSlot, newStack);
								return false;
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
									return false;
								}
							}
						}
    				}
				}
			}
			return true;
		}else{
			return false;
		}
	}
	 */
}
