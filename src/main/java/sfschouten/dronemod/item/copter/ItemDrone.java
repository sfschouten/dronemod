package sfschouten.dronemod.item.copter;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import cofh.api.energy.IEnergyContainerItem;
import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.client.model.ModelCopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDCreativeTabs;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.inventory.SimpleInventory;
import sfschouten.dronemod.item.module.ItemModule;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.util.Logger;
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
	protected HashMap<InventoryType, Integer> amounts;
	protected Class<? extends EntityDrone> entityClass;
	protected Class<? extends ModelCopter> modelClass;
	
    public ItemDrone() {
        super();
        this.setCreativeTab(MMDCreativeTabs.tabGeneral);
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
    	
		par3EntityPlayer.openGui(ModularMulticopterDrones.instance, 1, par2World, x, y, z);

        return par1ItemStack;
    }
    
    /**
     * Used to instantiate the appropriate entity for this item.
     * 
     * 
     * @param world
     * @param droneItemNBTdata
     * @param x
     * @param y
     * @param z
     * @return
     */
    public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata, double x, double y, double z){
    	try {
    		Constructor c = entityClass.getConstructor(new Class[]{World.class});
			EntityDrone drone = (EntityDrone) c.newInstance(new Object[]{world});
			//It's important that the position is set before the sizes are applied. 
			//This has to do with chunkloading.
			drone.setPosition(x, y, z);
			applySizes(drone, droneItemNBTdata);
			return drone;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    public Class getModelClass(){
    	return modelClass;
    }
    
    public Class getEntityClass(){
    	return entityClass;
    }
    
    public abstract int getExpSize(InventoryType type);
    
    protected EntityDrone applySizes(EntityDrone e, NBTTagCompound droneItemNBTdata){
		for(InventoryType it : InventoryType.values()){
			SimpleInventory inv = new SimpleInventory(getExpSize(it));
			NBTTagCompound NBTTagComp = droneItemNBTdata.getCompoundTag(it.name());
			if(NBTTagComp == null){
				NBTTagComp = new NBTTagCompound();
			}
			
			inv.readFromNBT(NBTTagComp);
			for(int currentStackInt = 0; currentStackInt < inv.getSizeInventory(); currentStackInt++){
				ItemStack currentStack = inv.getStackInSlot(currentStackInt);
				if(currentStack != null){
					if(currentStack.getItem() instanceof ItemModule){
						System.out.println("Adding Module to entitytobespawned");
						e.addModule((ItemModule) currentStack.getItem());
						break;
					}else if(false/*TODO implement battery stuffs*/){
					}else if(currentStack.getItem() == Item.getItemFromBlock(Blocks.chest)){
						e.setActualInventory(new SimpleInventory(e.getActualInventory().getSizeInventory()+1));
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