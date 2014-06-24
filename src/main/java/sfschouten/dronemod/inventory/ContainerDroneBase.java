package sfschouten.dronemod.inventory;

import java.util.ArrayList;

import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerDroneBase extends Container {
	protected TileEntityDroneBase tileEntity;

	public ContainerDroneBase(InventoryPlayer inventoryPlayer,
			TileEntityDroneBase te) {
		tileEntity = te;
		inventorySlots = new ArrayList();
		addSlotToContainer(new Slot(tileEntity, 0, 80, 35));
		
		//commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return tileEntity.isUseableByPlayer(player);
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		//TODO Implement Shift Click in/out of droneBase
		return null;
		/*
		ItemStack stack = null;
		Slot slotObject = (Slot) inventorySlots.get(slot);

		// null checks and checks if the item can be stacked (maxStackSize > 1)
		if (slotObject != null && slotObject.getHasStack()) {
			ItemStack stackInSlot = slotObject.getStack();
			stack = stackInSlot.copy();

			// merges the item into player inventory since its in the tileEntity
			if (slot < 1) {
				
				if (!this.mergeItemStack(stackInSlot, 0, 10, true)) {
					System.out.println("mergeItemStack geeft false terug");
					System.out.println("return null");
					return null;
				}
				return null;
			}
			// places it into the tileEntity is possible since its in the player
			// inventory
			
			else if (!this.mergeItemStack(stackInSlot, 0, 1, false)) {
				return null;
			}
			
			if (stackInSlot.stackSize == 0) {
				slotObject.putStack(null);
			} else {
				slotObject.onSlotChanged();
			}

			if (stackInSlot.stackSize == stack.stackSize) {
				return null;
			}
			
			slotObject.onPickupFromSlot(player, stackInSlot);
		}
		return stack;
		*/
	}
}
