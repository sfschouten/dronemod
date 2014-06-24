package sfschouten.dronemod.entity.ai;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.ai.DroneAI;

public class DroneRestockAI extends DroneAI {

	@Override
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
}
