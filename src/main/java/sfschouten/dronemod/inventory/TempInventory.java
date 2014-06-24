package sfschouten.dronemod.inventory;

import java.util.List;

import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import sfschouten.dronemod.MarkerRegistry;
import sfschouten.dronemod.MarkerRegistry.Registration;
import sfschouten.dronemod.TempInventoryType;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.item.module.ItemTaskModule;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;

public class TempInventory implements IInventory{
	private TempInventoryType type;
	private ItemStack[] inv;
	private int stackLimit = 1;
	boolean locked;

	public ItemStack[] getInv() {
		return inv;
	}

	public void setInv(ItemStack[] inv) {
		this.inv = inv;
	}

	public TempInventoryType getType() {
		return type;
	}

	public void setType(TempInventoryType type) {
		this.type = type;
	}

	public TempInventory(int length){
		inv = new ItemStack[length];
	}
	
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		System.out.println("Start Writing");
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
        	System.out.println("#"+i);
            ItemStack stack = inv[i];
            if (stack != null) {
            	System.out.println("stack is not null");
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
	}
	
	public void readFromNBT(NBTTagCompound tagCompound)
	{        
        NBTTagList tagList = tagCompound.getTagList("Inventory");
        System.out.println(tagCompound.getName()+": "+tagCompound.hasKey("Inventory")+", "+tagList.tagCount());
        //System.out.println("tagcount: " + tagList.tagCount());
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.tagAt(i);
            byte slot = tag.getByte("Slot");
            //System.out.println("id: " + ItemStack.loadItemStackFromNBT(tag).itemID);
            if (slot >= 0 && slot < inv.length) {
                inv[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
	}

	@Override
	public int getSizeInventory() {
		return inv.length;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return inv[i];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amt) {
		ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
                if (stack.stackSize <= amt) {
                        setInventorySlotContents(slot, null);
                } else {
                        stack = stack.splitStack(amt);
                        if (stack.stackSize == 0) {
                                setInventorySlotContents(slot, null);
                        }
                }
        }
        return stack;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int slot) {
		ItemStack stack = getStackInSlot(slot);
        if (stack != null) {
            setInventorySlotContents(slot, null);
        }
        return stack;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		inv[slot] = stack;
    	if (stack != null && stack.stackSize > getInventoryStackLimit() ) {
    		stack.stackSize = getInventoryStackLimit();
    	}
	}

	@Override
	public int getInventoryStackLimit() {
		return stackLimit;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
        return true;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(itemstack != null){
			return type.isValidFor(itemstack.getItem());
		}else{
			return true;
		}
	}

	@Override
	public String getInventoryName() {
		return "sfschouten.minecraft.dronemod.dronebase";
	}

	@Override
	public boolean hasCustomInventoryName() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void markDirty() {
		// TODO Auto-generated method stub
	}

	@Override
	public void openInventory() {
		// TODO Auto-generated method stub
	}

	@Override
	public void closeInventory() {
		// TODO Auto-generated method stub
	}
}
