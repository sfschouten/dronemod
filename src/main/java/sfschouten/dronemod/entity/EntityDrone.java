package sfschouten.dronemod.entity;

import java.lang.ProcessBuilder.Redirect;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import sfschouten.dronemod.Logger;
import sfschouten.dronemod.Pair;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.TempInventoryType;
import sfschouten.dronemod.entity.ai.DroneAI;
import sfschouten.dronemod.entity.ai.DroneAdvancedTaskAI;
import sfschouten.dronemod.entity.ai.DroneBasicTaskAI;
import sfschouten.dronemod.entity.ai.DroneRechargeAI;
import sfschouten.dronemod.entity.ai.DroneRestockAI;
import sfschouten.dronemod.inventory.TempInventory;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.item.module.DroneTaskResult;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemDye;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public abstract class EntityDrone extends EntityFlying{
	/**
	 * The tile entity corresponding to the base of this drone.
	 */
	private TileEntityDroneBase base;
	
	/**
	 * A list of TileEntityMarkers corresponding to the work markers of this drone.
	 */
	private List<TileEntityMarker> workMarkers;
	
	/**
	 * The current marker being worked.
	 */
	private TileEntityMarker currentWork;
	
	/**
	 * List of AI's that this drone has. 
	 */
	private ArrayList<DroneAI> droneAI;
	
	/**
	 * The Inventory of this drone containing the items it picks up from the world etc.
	 */
	private TempInventory actualInventory;
	
	/**
	 * TODO The amount of energy in ....
	 */
	private int energy;
	
	/**
	 * A list of modules with the tasks that this drone should do.
	 */
	private List<ItemTaskModule> modules;
	
	/**
	 * A hashmap containing a different sets of items.
	 * For every type of expansion a drone can have there is an array.
	 * The array' size is determened based upon the type of drone it is. 
	 */
	private HashMap<TempInventoryType, ItemStack[]> expansions;
	
	public EntityDrone(World par1World)
    {
        super(par1World);
        this.setSize(0.9F, 1.3F);
        workMarkers = new ArrayList<TileEntityMarker>();
        modules = new ArrayList<ItemTaskModule>();
        
        //TODO move task AI's to individual drones.
        droneAI = new ArrayList<DroneAI>();
        droneAI.add(new DroneBasicTaskAI());
        //droneAI.add(new DroneAdvancedTaskAI());
        
        currentWork = null;
        //TODO move line below to each individual drone because size of actualInventory should be based upon type of drone.
        actualInventory = new TempInventory(1);
        expansions = new HashMap<TempInventoryType, ItemStack[]>();
        
        for(TempInventoryType type : TempInventoryType.values()){
        	expansions.put(type, new ItemStack[getItem().getExpSize(type)]);
        }
    }
	
	public abstract int getEnergyUse();
	
	public abstract ItemDrone getItem();
	
	public abstract int getRestockSpeed();
    
	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.8D);
    }

    @Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
    	//Loop through each type of inventory: batteries, modules, chests, free slots.
    	for(Entry<TempInventoryType, ItemStack[]> entry : expansions.entrySet()) {
    		TempInventoryType type = entry.getKey();
    		ItemStack[] items = entry.getValue();

    		TempInventory t = new TempInventory(items.length);
    		t.setInv(items);
    		NBTTagCompound tc = new NBTTagCompound();
        	t.writeToNBT(tc);
        	//tc.setInteger("InventoryLength", t.getSizeInventory());
        	System.out.println("type.name(): "+type.name()); 
        	par1nbtTagCompound.setTag(type.name(), tc);
    	}
    	
    	par1nbtTagCompound.setInteger("Energy", energy);
    	
    	//TODO implement the NBT storage of base.
//    	NBTTagCompound baseComp = new NBTTagCompound();
//    	baseComp.setInteger("baseX", base.xCoord);
//    	baseComp.setInteger("baseY", base.yCoord);
//    	baseComp.setInteger("baseZ", base.zCoord);
//    	par1nbtTagCompound.setCompoundTag("Base", baseComp); 
    	
    	NBTTagCompound currentWorkTagComp = new NBTTagCompound();
    	if(currentWork != null){
    		currentWork.writeToNBT(currentWorkTagComp);
    	}
    	par1nbtTagCompound.setTag("CurrentWork", currentWorkTagComp);

		super.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		for(TempInventoryType type : TempInventoryType.values()){
			NBTTagCompound typeComp = par1nbtTagCompound.getCompoundTag(type.name());
			TempInventory inv = new TempInventory(typeComp.getInteger("InventoryLength"));
			inv.readFromNBT(typeComp);
        	expansions.put(type, inv.getInv());
        }
		
		NBTTagCompound baseComp = par1nbtTagCompound.getCompoundTag("Base");
		int baseX = baseComp.getInteger("baseX");
		int baseY = baseComp.getInteger("baseY");
		int baseZ = baseComp.getInteger("baseZ");
		//base = (TileEntityDroneBase) worldObj.getBlockTileEntity(baseX, baseY, baseZ);
		
		currentWork = new TileEntityMarker();
		currentWork.readFromNBT(par1nbtTagCompound.getCompoundTag("CurrentWork"));
		
		super.readFromNBT(par1nbtTagCompound);
	}

	/**
	 * Overridden to let users pick drones out of the air.
	 */
	@Override
    public boolean interact(EntityPlayer par1EntityPlayer){
    	ItemStack is = par1EntityPlayer.inventory.getCurrentItem();
    	
    	//Check if interaction is with an empty hand.
    	if(is == null || is.stackSize == 0){
    		//Remove entity from world.
    		par1EntityPlayer.worldObj.removeEntity(this);
    		
    		//Create new ItemStack with of the corresponding sort.
    		ItemStack newStack = new ItemStack(getItem());
    		
    		//Create new compound tag and write this entity to it.
    		NBTTagCompound tc = new NBTTagCompound();
    		writeToNBT(tc);
    		
    		//Set the compound tag to the new ItemStack. 
    		newStack.setTagCompound(tc);
    		
        	if(!par1EntityPlayer.inventory.addItemStackToInventory(newStack)){
        		//TODO handle failed addItemStackToInventory
        	}
        	return true;
    	}else{
    		return super.interact(par1EntityPlayer);
    	}
    }
    
    @Override
    protected boolean isAIEnabled()
    {
        return true;
    }
    
    /**
     * Overidden to make drone execute the DroneAI's in collection.
     * 
     */
    @Override
    protected void updateAITasks()
    {
    	super.updateAITasks();
		int amountAI = droneAI.size();
		ArrayList<Integer> remove = new ArrayList<Integer>();
		for(int i = 0; i < droneAI.size(); i++){
			if(droneAI.get(i).executeAI(this)){
				remove.add(i);
			}	
		}

		for(Integer i : remove){
			droneAI.remove(i.intValue());
		}
		
		boolean containsRecharge = false;
		for(DroneAI ai : droneAI){
			if(ai instanceof DroneRechargeAI){
				containsRecharge = true;
				break;
			}
		}
		
		if(!enoughEnergyForReturnHome() && !containsRecharge){
    		//droneAI.add(new DroneRechargeAI());
    	}
    }
    
    /**
     * Used to assess whether or not there is enough energy left in drone to fly home.
     * @return true or false whether there is enough energy.
     */
    private boolean enoughEnergyForReturnHome(){
    	if(base == null){
    		Logger.logOut("drone does not have a base.");
    		return false;
    	}
    	double distanceFromBase = this.getDistance(base.xCoord, base.yCoord, base.zCoord);
    	
    	if((getEnergyUse() * distanceFromBase) > energy){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**
     * Inspired by bat flight, maybe not the best but it works.
     * Adds motion in direction of the supplied coordinate.
     * Also pitches forward to simulate typical multicopter flight.
     * @param x 
     * @param y
     * @param z
     */
    public void startMovingTowards(int x, int y, int z){
    	double d0 = (double)x + 0.5D - this.posX;
		double d1 = (double)y + 0.5D - this.posY;
		double d2 = (double)z + 0.5D - this.posZ;
		this.motionX += (Math.signum(d0) * 0.1D - this.motionX) * 0.10000000149011612D;
		this.motionY += (Math.signum(d1) * 0.699999988079071D - this.motionY) * 0.10000000149011612D;
		this.motionZ += (Math.signum(d2) * 0.1D - this.motionZ) * 0.10000000149011612D;
		float f = (float)(Math.atan2(this.motionZ, this.motionX) * 180.0D / Math.PI) - 90.0F;
		float f1 = MathHelper.wrapAngleTo180_float(f - this.rotationYaw);
		this.moveForward = 0.4F;
		this.rotationYaw += f1;
		float f2 = (float) (Math.abs(this.motionX) + Math.abs(this.motionZ)) ;
		f2 *= 80;
		this.rotationPitch = f2;
    }

    
    /**
     * Uses startMovingTowards to move towards this drones base.
     * Should only be called in AI loop.
     * 
     * @return true if arrived
     */
    public boolean goToBase(){
    	if(base == null){
    		return false;
    	}
    	int intPosX = (int)Math.floor(this.posX);
		int intPosY = (int)Math.floor(this.posY);
		int intPosZ = (int)Math.floor(this.posZ);
    	
    	if(intPosX != base.xCoord || intPosY != base.yCoord+1 || intPosZ != base.zCoord){
			startMovingTowards(base.xCoord, base.yCoord+1, base.zCoord);
			return false;
		}
    	return true;
    }
    
    /**
     * Adds a DroneRestockAI on top of collection with DroneAI.
     */
    public void restock(){
    	droneAI.add(new DroneRestockAI());
    }
    
    /**
     * Removes highest DroneRestockAI from collection with DroneAI.
     */
    public void cancelRestock(){
    	for(int i = droneAI.size()-1; i > 0; i--){
    		if(droneAI.get(i) instanceof DroneRestockAI){
    			droneAI.remove(i);
    			return;
    		}
    	}
    }
    
	/**
	 * Relies on getFirstSlotForItemIDAndDamage, just uses 0 for damage value. 
	 * @param itemID the ID of an item.
	 * @return the number of the first slot that can contain an item with the supplied itemID. 
	 */
	public int getFirstSlotForItem(Item item){
		return getFirstSlotForItemAndDamage(item, 0);
	}
	
	/**
	 * @param itemID the ID of an item.
	 * @param damage the damage value of an item.
	 * @return the number of the first slot that can contain an item with the supplied itemID and damage value. 
	 */
	public int getFirstSlotForItemAndDamage(Item item, int damage){
		int slot = -1;
		for(int i = 0; i < actualInventory.getSizeInventory(); i++){
			if(actualInventory.getStackInSlot(i) != null && 
					actualInventory.getStackInSlot(i).getItem() == item &&
					actualInventory.getStackInSlot(i).getItemDamage() == damage){
				slot = i;
			}
		}
		return slot;
	}
	
	/**
	 * Used to add a module to the appropriate collections in the entity.
	 * Added to the collection that simply lists the modules in the entity.
	 * Also added to the collection with the inventories for the numerous expansions(batteries, modules, chest, etc.)
	 * 
	 * @param m Module to be added.
	 */
	public void addModule(ItemTaskModule m){
		//Add to modules list
		modules.add(m);
		
		//Apply any task specific properties to this entity.
		m.applyProperties(this);
		
		//Make new ItemStack to be added to the inventory of the entity
		ItemStack newStack = new ItemStack(m, 1, 0);
		
		//Get current list of modules in entity.
		ItemStack[] current = expansions.get(TempInventoryType.module);
		
		//Temporary list to make adding another module easier.
		List<ItemStack> temp = new ArrayList<ItemStack>(Arrays.asList(current));
		temp.add(newStack);
		
		//Remove any empty entries from the list.
		for(int i = 0; i < temp.size(); i++){
			if (temp.get(i) == null){
				temp.remove(i);
			}
		}
		
		//Convert the list back to an array and add back to the inventory.
		ItemStack[] newItemStack = temp.toArray(new ItemStack[temp.size()]);
		expansions.put(TempInventoryType.module, newItemStack);
	}
	
    public TileEntityDroneBase getBase() {
		return base;
	}

	public void setBase(TileEntityDroneBase base) {
		this.base = base;
	}
	
    public void addWorkMarker(TileEntityMarker m){
    	this.workMarkers.add(m);
    }
    
    public void addTaskModule(ItemTaskModule m){
    	this.modules.add(m);
    }
    
	public TileEntityMarker getCurrentWork() {
		return currentWork;
	}
	
	public void setCurrentWork(TileEntityMarker currentWork) {
		this.currentWork = currentWork;
	}
	
    public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public TempInventory getActualInventory() {
		return actualInventory;
	}

	public void setActualInventory(TempInventory actualInventory) {
		this.actualInventory = actualInventory;
	}

	public List<TileEntityMarker> getWorkMarkers() {
		return workMarkers;
	}

	public List<ItemTaskModule> getModules() {
		return modules;
	}
	
	public void addAI(DroneAI ai){
		droneAI.add(ai);
	}

    @Override
    protected float getSoundVolume()
    {
        return 0.0F;
    }
}