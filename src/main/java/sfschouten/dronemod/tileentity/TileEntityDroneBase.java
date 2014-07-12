package sfschouten.dronemod.tileentity;

import java.util.List;

import buildcraft.api.power.IPowerReceptor;
import buildcraft.api.power.PowerHandler;
import buildcraft.api.power.PowerHandler.PowerReceiver;
import buildcraft.api.power.PowerHandler.Type;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.IEnergyStorage;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IPeripheral;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.inventory.SimpleInventory;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.registry.MarkerRegistry;
import sfschouten.dronemod.registry.MarkerRegistry.Registration;
import sfschouten.dronemod.util.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityDroneBase extends TileEntity implements IPeripheral, IInventory, IPowerReceptor{
	private EntityDrone drone;
	private ItemStack[] inv;
	private int stackLimit = 1;
	boolean locked;
	boolean spawnOnCharged;
	
	private PowerHandler powerHandler;
	
	public TileEntityDroneBase(){
		inv = new ItemStack[1];
		powerHandler = new PowerHandler(this, Type.MACHINE);
		initPowerProvider();
	}
	
	private void initPowerProvider() {
		powerHandler.configure(50, 250, 25, 1000);
		powerHandler.configurePowerPerdition(0, 0);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound tagCompound)
	{
		super.writeToNBT(tagCompound);
        
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
        
        powerHandler.writeToNBT(tagCompound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound tagCompound)
	{
		super.readFromNBT(tagCompound);
        
        NBTTagList tagList = tagCompound.getTagList("Inventory", tagCompound.getId());
        for (int i = 0; i < tagList.tagCount(); i++) {
            NBTTagCompound tag = (NBTTagCompound) tagList.getCompoundTagAt(i);
            byte slot = tag.getByte("Slot");
            if (slot >= 0 && slot < inv.length) {
                    inv[slot] = ItemStack.loadItemStackFromNBT(tag);
            }
        }
        
        powerHandler.readFromNBT(tagCompound);
		initPowerProvider();
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
    	if (stack != null && stack.stackSize > getInventoryStackLimit()) {
    		stack.stackSize = getInventoryStackLimit();
    	}
    	if(stack != null && !(stack.getItem() instanceof ItemDrone)){
    		stack.stackSize = 0;
    	}
	}

	@Override
	public String getInventoryName() {
		return "sfschouten.minecraft.dronemod.dronebase";
	}

	@Override
	public int getInventoryStackLimit() {
		return stackLimit;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
        return worldObj.getTileEntity(xCoord, yCoord, zCoord) == this && player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		if(itemstack.getItem() instanceof ItemDrone){
			return true;
		}else{
			return false;
		}
	}

	public EntityDrone getDrone() {
		return drone;
	}

	public void setDrone(EntityDrone drone) {
		this.drone = drone;
	}

	public boolean storeDrone(EntityDrone e){
		if(drone == null){
			drone = e;
		}else if(drone.equals(e)){
			worldObj.removeEntity(e);
			ItemStack droneItemStack = new ItemStack(e.getItem());
			if(droneItemStack.stackTagCompound == null){
				droneItemStack.stackTagCompound = new NBTTagCompound();
			}
			e.writeToNBT(droneItemStack.stackTagCompound);
			
			//TODO verplaatsen?
			int result = e.getEnergy();
			while(result > 0){
				result -= e.getItem().receiveEnergy(droneItemStack, result, false);
			}
			setInventorySlotContents(0, droneItemStack);
			
			return true;
		}
		return false;
	}
	
	public void lock(){
		locked = true;
	}
	
	public void unLock(){
		locked = false;
	}
	
	public void spawnDrone(){
		if(!(inv[0].getItem() instanceof ItemDrone)){
			return;
		}
   		
		//See which drone is in inventory
		ItemDrone droneItem = (ItemDrone) inv[0].getItem();
		
		//Make new drone
		NBTTagCompound droneItemNBTdata = inv[0].getTagCompound();
   		EntityDrone e = droneItem.getNewEntity(worldObj, droneItemNBTdata);
   		e.setPosition(xCoord, yCoord+1, zCoord);
		e.setBase(this);
		e.setEnergy(droneItem.getEnergyStored(inv[0]));
		worldObj.spawnEntityInWorld(e);
				
		//TEMP get first marker from MarkerRegistry to test.
		MarkerRegistry m = MarkerRegistry.forWorld(worldObj);
		List<Registration> markers = m.getRegisteredMarkers();
		Registration r = markers.get(0);
		
		//Add marker to drone
   		if(r.marker == null){
   			Logger.logOut("marker is nulllllll");
   		}else{
   			Logger.logOut("NOT NULL");
   		}
   		
		e.addWorkMarker(r.marker);
		
		setDrone(e);
		lock();
		
		setInventorySlotContents(0, null);
		
		setSpawnOnCharged(false);
	}
	
	public IInventory getAdjacentInventory(){
		for(int side = 0; side < 6; side++){
			switch(side){
			case 0:
				TileEntity te0 = worldObj.getTileEntity(xCoord, yCoord+1, zCoord);
				if(te0 != null && te0 instanceof IInventory){
					return (IInventory) te0;
				}
				break;
			case 1:
				TileEntity te1 = worldObj.getTileEntity(xCoord+1, yCoord, zCoord);
				if(te1 != null && te1 instanceof IInventory){
					return (IInventory) te1;
				}
				break;
			case 2:
				TileEntity te2 = worldObj.getTileEntity(xCoord, yCoord, zCoord+1);
				if(te2 != null && te2 instanceof IInventory){
					return (IInventory) te2;
				}
				break;
			case 3:
				TileEntity te3 = worldObj.getTileEntity(xCoord-1, yCoord, zCoord);
				if(te3 != null && te3 instanceof IInventory){
					return (IInventory) te3;
				}
				break;
			case 4:
				TileEntity te4 = worldObj.getTileEntity(xCoord, yCoord, zCoord-1);
				if(te4 != null && te4 instanceof IInventory){
					return (IInventory) te4;
				}
				break;
			case 5:
				TileEntity te5 = worldObj.getTileEntity(xCoord, yCoord-1, zCoord);
				if(te5 != null && te5 instanceof IInventory){
					return (IInventory) te5;
				}
				break;
			}
		}
		return null;
	}

	@Override
	public PowerReceiver getPowerReceiver(ForgeDirection side) {
		return powerHandler.getPowerReceiver();
	}

	@Override
	public void doWork(PowerHandler workProvider) {
		ItemStack stack = getStackInSlot(0);
		if(stack != null && stack.getItem() instanceof ItemDrone){
			ItemDrone droneItem = (ItemDrone)stack.getItem();
			
			double used = workProvider.useEnergy(250, 250, true);
			int received = droneItem.receiveEnergy(stack, (int) used*10, false);
			
			System.out.println("received: " + received);
			
			if(received == 0 && spawnOnCharged){
				spawnDrone();
			}
		}
	}

	@Override
	public World getWorld() {
		return worldObj;
	}

	public boolean isSpawnOnCharged() {
		return spawnOnCharged;
	}

	public void setSpawnOnCharged(boolean spawnOnCharged) {
		this.spawnOnCharged = spawnOnCharged;
	}

	@Override
	public String getType() {
		return "dronemod_dronebase";
	}

	@Override
	public String[] getMethodNames() {
		return new String[]{"launch", "comehome"};
	}

	@Override
	public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) {
		switch(method){
		case 0:
			spawnDrone();
			return new Object[] {"You called method1!"};
			//break;
		case 1:
			//TODO getDrone().goToBase();
			break;
		}
		
		return null;
	}

	@Override
	public void attach(IComputerAccess computer) {
		Logger.logChat(worldObj, "Drone Base Attached!");
	}

	@Override
	public void detach(IComputerAccess computer) {
		
	}

	@Override
	public boolean equals(IPeripheral other) {
		TileEntity te = (TileEntity) other;
		if(te != null && te.xCoord == this.xCoord && te.yCoord == this.yCoord && te.zCoord == this.zCoord){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean hasCustomInventoryName() {
		return true;
	}

	@Override
	public void openInventory() {
	}

	@Override
	public void closeInventory() {
	}
}
