package sfschouten.dronemod.item.copter;

import java.util.HashMap;

import cofh.api.energy.IEnergyContainerItem;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.TempInventoryType;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.inventory.TempInventory;
import sfschouten.dronemod.item.module.ItemTaskModule;
import net.minecraft.block.Block;
import net.minecraft.block.BlockChest;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public abstract class ItemDrone extends Item implements IEnergyContainerItem{
	protected HashMap<TempInventoryType, Integer> amounts;
	protected Class entityClass;
	
    public ItemDrone() {
        super();
        this.setCreativeTab(DroneMod.tabDroneMod);
        this.setMaxStackSize(1);
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
    	int x = (int)par3EntityPlayer.posX;
    	int y = (int)par3EntityPlayer.posY;
    	int z = (int)par3EntityPlayer.posZ;
    	
		par3EntityPlayer.openGui(DroneMod.instance, 1, par2World, x, y, z);

        return par1ItemStack;
    }
    
    public abstract EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata);
    
    public Class getEntityClass(){
    	return entityClass;
    }
    
    public abstract int getExpSize(TempInventoryType type);
    
    protected EntityDrone applySizes(EntityDrone e, NBTTagCompound droneItemNBTdata){
		for(TempInventoryType it : TempInventoryType.values()){
			TempInventory inv = new TempInventory(getExpSize(it));
			NBTTagCompound NBTTagComp = droneItemNBTdata.getCompoundTag(it.name());
			if(NBTTagComp == null){
				NBTTagComp = new NBTTagCompound();
			}
			
			inv.readFromNBT(NBTTagComp);
			for(int currentStackInt = 0; currentStackInt < inv.getSizeInventory(); currentStackInt++){
				ItemStack currentStack = inv.getStackInSlot(currentStackInt);
				if(currentStack != null){
					if(currentStack.getItem() instanceof ItemTaskModule){
						System.out.println("Adding Module to entitytobespawned");
						e.addModule((ItemTaskModule) currentStack.getItem());
						break;
					}else if(false/*TODO implement battery stuffs*/){
					}else if(currentStack.getItem() == Item.getItemFromBlock(Blocks.chest)){
						e.setActualInventory(new TempInventory(e.getActualInventory().getSizeInventory()+1));
					}
				}
			}
		}
		return e;
    }

    /* IEnergyContainerItem */
	@Override
	public int receiveEnergy(ItemStack container, int maxReceive, boolean simulate) {

		if (container.stackTagCompound == null) {
			container.stackTagCompound = new NBTTagCompound();
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int capacity = container.stackTagCompound.getInteger("EnergyCapacity");
		int maxReceiveItem = container.stackTagCompound.getInteger("EnergyMaxReceive");
		int energyReceived = Math.min(capacity - energy, Math.min(maxReceiveItem, maxReceive));

		if (!simulate) {
			energy += energyReceived;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyReceived;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract, boolean simulate) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		int energy = container.stackTagCompound.getInteger("Energy");
		int capacity = container.stackTagCompound.getInteger("EnergyCapacity");
		int maxExtractItem = getMaxEnergyStored(container);
		int energyExtracted = Math.min(energy, Math.min(maxExtractItem, maxExtract));

		if (!simulate) {
			energy -= energyExtracted;
			container.stackTagCompound.setInteger("Energy", energy);
		}
		return energyExtracted;
	}

	@Override
	public int getEnergyStored(ItemStack container) {

		if (container.stackTagCompound == null || !container.stackTagCompound.hasKey("Energy")) {
			return 0;
		}
		return container.stackTagCompound.getInteger("Energy");
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return container.stackTagCompound.getInteger("EnergyCapacity");
	}
}