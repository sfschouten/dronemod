package sfschouten.dronemod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;

public class SimpleInventory implements IInventory{
	private InventoryType type;
	private ItemStack[] inv;
	private int stackLimit = 1;
	boolean locked;

	public ItemStack[] getInv() {
		return inv;
	}

	public void setInv(ItemStack[] inv) {
		this.inv = inv;
	}

	public InventoryType getType() {
		return type;
	}

	public void setType(InventoryType type) {
		this.type = type;
	}

	public SimpleInventory(int length){
		inv = new ItemStack[length];
	}
	
	public void writeToNBT(NBTTagCompound tagCompound)
	{
        NBTTagList itemList = new NBTTagList();
        for (int i = 0; i < inv.length; i++) {
            ItemStack stack = inv[i];
            if (stack != null) {
                NBTTagCompound tag = new NBTTagCompound();
                tag.setByte("Slot", (byte) i);
                stack.writeToNBT(tag);
                itemList.appendTag(tag);
            }
        }
        tagCompound.setTag("Inventory", itemList);
	}
	
	public void readFromNBT(NBTTagCompound tagCompound){        
        NBTTagList tagList = tagCompound.getTagList("Inventory", tagCompound.getId());

        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
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
