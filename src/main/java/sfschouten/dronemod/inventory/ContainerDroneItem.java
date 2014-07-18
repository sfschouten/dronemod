package sfschouten.dronemod.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cofh.api.energy.IEnergyContainerItem;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagEnd;

public class ContainerDroneItem extends Container {
	//Stack with drone.
	ItemStack stack;
	HashMap<InventoryType, SimpleInventory> inventories = new HashMap<InventoryType, SimpleInventory>();
	
	public ContainerDroneItem(InventoryPlayer inventoryPlayer, ItemStack stack) {
		this.stack = stack;
		
		NBTTagCompound stackNBT = stack.getTagCompound();
		if(stackNBT == null){
			stackNBT = new NBTTagCompound();
			System.out.println("stacknbt is null");
		}else{
			System.out.println("stacknbt is not null");
		}
		
		for(InventoryType type : InventoryType.values()){
			ItemDrone drone = (ItemDrone)stack.getItem();
			SimpleInventory temp = new SimpleInventory(drone.getExpSize(type));
			
			//Get inventorytype specific compoundtag
			NBTTagCompound comp = stackNBT.getCompoundTag(type.name());
			temp.readFromNBT(comp);
			temp.setType(type);
			
			for(int t = 0; t < temp.getSizeInventory(); t++){
				addSlotToContainer(new Slot(temp, t, 8+(t*18), 25+(type.ordinal()*25)));
			}
			
			inventories.put(type, temp);
		}
		
		//commonly used vanilla code that adds the player's inventory
		bindPlayerInventory(inventoryPlayer);
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

	protected void bindPlayerInventory(InventoryPlayer inventoryPlayer) {
		for (int i = 0; i < 9; i++) {
			addSlotToContainer(new Slot(inventoryPlayer, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		//TODO Implement Shift Click in/out of drone
		return null;
	}

	@Override
	public void onContainerClosed(EntityPlayer par1EntityPlayer) {
		System.out.println( "container closed" );
		NBTTagCompound stackNBT = par1EntityPlayer.inventory.getCurrentItem().getTagCompound();
		if(stackNBT == null){
			stackNBT = new NBTTagCompound();
		}
		
		for(InventoryType type : InventoryType.values()){
			NBTTagCompound comp = new NBTTagCompound();
			inventories.get(type).writeToNBT(comp);
			stackNBT.setTag(type.name(), comp);
		}
		
		SimpleInventory inv = inventories.get(InventoryType.battery);
		int totalEnergyCapacity = 0;
		int totalEnergy = 0;
		
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack != null && stack.getItem() instanceof IEnergyContainerItem){
				IEnergyContainerItem energyCon = (IEnergyContainerItem) stack.getItem();
				totalEnergyCapacity += energyCon.getMaxEnergyStored(stack);
				totalEnergy += energyCon.getEnergyStored(stack);
			}
		}
		
		stackNBT.setInteger("Energy", totalEnergy);
		stackNBT.setInteger("EnergyCapacity", totalEnergyCapacity);
		stackNBT.setInteger("EnergyMaxReceive", 50);
		stackNBT.setInteger("EnergyMaxExtract", 50);
		System.out.println("totalEnergy: "+totalEnergy);
		System.out.println("totalEnergyCapacity: "+totalEnergyCapacity);
		
		par1EntityPlayer.inventory.getCurrentItem().setTagCompound(stackNBT);
		super.onContainerClosed(par1EntityPlayer);
	}
}
