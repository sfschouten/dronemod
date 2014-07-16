package sfschouten.dronemod.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import sfschouten.dronemod.entity.ai.DroneAdvancedTaskAI;
import sfschouten.dronemod.entity.ai.DroneBasicTaskAI;
import sfschouten.dronemod.entity.ai.DroneHomingAI;
import sfschouten.dronemod.entity.ai.DroneMoveHelper;
import sfschouten.dronemod.entity.ai.DroneRechargeAI;
import sfschouten.dronemod.entity.ai.DroneRestockAI;
import sfschouten.dronemod.entity.ai.EntityWanderFlyingAI;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.inventory.SimpleInventory;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.pathfinding.PathNavigate3D;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.util.Logger;

public abstract class EntityDrone extends EntityFlying {
	/** The tile entity corresponding to the base of this drone. */
	private TileEntityDroneBase base;

	/** A list of TileEntityMarkers corresponding to the work markers of this drone. */
	private List<TileEntityMarker> workMarkers;

	/** The current marker being worked. */
	private TileEntityMarker currentWork;
	
	/** The Inventory of this drone containing the items it picks up from the world etc. */
	private SimpleInventory actualInventory;

	/** TODO The amount of energy in .... */
	private int energy;

	/** A list of modules with the tasks that this drone should do. */
	private List<ItemTaskModule> modules;

	/** A sleep timer, the amount of ticks left to let the drone sleep.	 */
	private int sleep;
	
	private Ticket chunkloadingTicket;
	
	/**
	 * A HashMap containing a different sets of items. For every type of
	 * expansion a drone can have there is an array. The array' size is
	 * Determined based upon the type of drone it is.
	 */
	private HashMap<InventoryType, ItemStack[]> expansions;

	private boolean goingHome;
	private boolean restocking;

	public EntityDrone(World par1World) {
		super(par1World);
		this.setSize(0.9F, 0.3F);
		workMarkers = new ArrayList<TileEntityMarker>();
		modules = new ArrayList<ItemTaskModule>();
		sleep = -1;
		
		// TODO move task AI's to individual drones.
		tasks.addTask(0, new DroneRestockAI(this));
		tasks.addTask(1, new DroneBasicTaskAI(this));
		tasks.addTask(2, new DroneAdvancedTaskAI());

		// TODO move line below to each individual drone because size of
		// actualInventory should be based upon type of drone.
		actualInventory = new SimpleInventory(1);
		expansions = new HashMap<InventoryType, ItemStack[]>();

		for (InventoryType type : InventoryType.values()) {
			expansions.put(type, new ItemStack[getItem().getExpSize(type)]);
		}

		ReflectionHelper.setPrivateValue(EntityLiving.class, this, new PathNavigate3D(this, worldObj, 75), new String[]{"navigator", "field_70699_by"});
		ReflectionHelper.setPrivateValue(EntityLiving.class, this, new DroneMoveHelper(this), new String[]{"moveHelper", "field_70765_h"});
		
		
	}
	
	public abstract int getEnergyUse();

	public abstract ItemDrone getItem();

	public abstract int getRestockSpeed();

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
	}

	@Override
	public void onUpdate() {
		this.fallDistance = 0.0F;
		this.isAirBorne = true;
		if(sleep > -1){
			this.sleep--;
		}

		PathEntity path = getNavigator().getPath();
		if(path != null){
			if(!path.isFinished()){
				Vec3 current = path.getPosition(this);
				if((current.xCoord - 0.5 < posX && current.xCoord + 0.5 > posX) 
				 &&(current.zCoord - 0.5 < posZ && current.zCoord + 0.5 > posZ) 
				 &&(current.yCoord - 0.5 < posY && current.yCoord + 0.5 > posY)){
					path.setCurrentPathIndex(path.getCurrentPathIndex() + 1);
				}
			}
		}
		super.onUpdate();
	}

	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		// Loop through each type of inventory: batteries, modules, chests, free
		// slots.
		for (Entry<InventoryType, ItemStack[]> entry : expansions.entrySet()) {
			InventoryType type = entry.getKey();
			ItemStack[] items = entry.getValue();

			SimpleInventory t = new SimpleInventory(items.length);
			t.setInv(items);
			NBTTagCompound tc = new NBTTagCompound();
			t.writeToNBT(tc);
			tc.setInteger("InventoryLength", t.getSizeInventory());
			par1nbtTagCompound.setTag(type.name(), tc);
		}

		par1nbtTagCompound.setInteger("Energy", energy);

		if(currentWork != null){
			NBTTagCompound currentWorkComp = new NBTTagCompound();
			currentWorkComp.setInteger("x", currentWork.xCoord);
			currentWorkComp.setInteger("y", currentWork.yCoord);
			currentWorkComp.setInteger("z", currentWork.zCoord);
			par1nbtTagCompound.setTag("currentWork", currentWorkComp);
		}
		
		
		
		// TODO implement the NBT storage of base.
		// NBTTagCompound baseComp = new NBTTagCompound();
		// baseComp.setInteger("baseX", base.xCoord);
		// baseComp.setInteger("baseY", base.yCoord);
		// baseComp.setInteger("baseZ", base.zCoord);
		// par1nbtTagCompound.setCompoundTag("Base", baseComp);
		super.writeToNBT(par1nbtTagCompound);
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		for (InventoryType type : InventoryType.values()) {
			NBTTagCompound typeComp = par1nbtTagCompound.getCompoundTag(type.name());
			SimpleInventory inv = new SimpleInventory(typeComp.getInteger("InventoryLength"));
			inv.readFromNBT(typeComp);
			expansions.put(type, inv.getInv());
		}

		NBTTagCompound baseComp = par1nbtTagCompound.getCompoundTag("Base");
		int baseX = baseComp.getInteger("baseX");
		int baseY = baseComp.getInteger("baseY");
		int baseZ = baseComp.getInteger("baseZ");
		// base = (TileEntityDroneBase) worldObj.getBlockTileEntity(baseX,
		// baseY, baseZ);

		NBTTagCompound cwTag = par1nbtTagCompound.getCompoundTag("currentWork");
		int cqX = cwTag.getInteger("x");
		int cqY = cwTag.getInteger("y");
		int cqZ = cwTag.getInteger("z");
		if(cqX == 0 && cqY == 0 && cqZ == 0){
			
		}else{
			//currentWork = (TileEntityMarker) worldObj.getTileEntity(cqX, cqY, cqZ);
		}
		super.readFromNBT(par1nbtTagCompound);
	}

	/**
	 * Overridden to let users pick drones out of the air.
	 */
	@Override
	public boolean interact(EntityPlayer par1EntityPlayer) {
		ItemStack is = par1EntityPlayer.inventory.getCurrentItem();
		
		Logger.log(this.boundingBox.toString());
		
		// Check if interaction is with an empty hand.
		if (is == null || is.stackSize == 0) {
			// Remove entity from world.
			par1EntityPlayer.worldObj.removeEntity(this);

			// Create new ItemStack with of the corresponding sort.
			ItemStack newStack = new ItemStack(getItem());

			// Create new compound tag and write this entity to it.
			NBTTagCompound tc = new NBTTagCompound();
			writeToNBT(tc);

			// Set the compound tag to the new ItemStack.
			newStack.setTagCompound(tc);

			if (!par1EntityPlayer.inventory.addItemStackToInventory(newStack)) {
				// TODO handle failed addItemStackToInventory
			}
			return true;
		} else {
			return super.interact(par1EntityPlayer);
		}
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	/**
	 * Used to assess whether or not there is enough energy left in drone to fly
	 * home.
	 * 
	 * @return true or false whether there is enough energy.
	 */
	private boolean enoughEnergyForReturnHome() {
		if (base == null) {
			Logger.log("drone does not have a base.");
			return false;
		}
		double distanceFromBase = this.getDistance(base.xCoord, base.yCoord, base.zCoord);

		if ((getEnergyUse() * distanceFromBase) > energy) {
			return false;
		} else {
			return true;
		}
	}
	

	/**
	 * Relies on getFirstSlotForItemIDAndDamage, just uses 0 for damage value.
	 * 
	 * @param itemID
	 *            the ID of an item.
	 * @return the number of the first slot that can contain an item with the
	 *         supplied itemID.
	 *         Returns -1 if no slot with the specified item can be found.
	 */
	public int getFirstSlotForItem(Item item) {
		return getFirstSlotForItemAndDamage(item, 0);
	}

	/**
	 * @param itemID
	 *            the ID of an item.
	 * @param damage
	 *            the damage value of an item.
	 * @return the number of the first slot that can contain an item with the
	 *         supplied itemID and damage value. 
	 *         Returns -1 if no slot with the specified item can be found.
	 */
	public int getFirstSlotForItemAndDamage(Item item, int damage) {
		int slot = -1;
		for (int i = 0; i < actualInventory.getSizeInventory(); i++) {
			if (actualInventory.getStackInSlot(i) != null && actualInventory.getStackInSlot(i).getItem() == item
					&& actualInventory.getStackInSlot(i).getItemDamage() == damage) {
				slot = i;
			}
		}
		return slot;
	}

	/**
	 * Used to add a module to the appropriate collections in the entity. Added
	 * to the collection that simply lists the modules in the entity. Also added
	 * to the collection with the inventories for the numerous
	 * expansions(batteries, modules, chest, etc.)
	 * 
	 * @param m Module to be added.
	 */
	public void addModule(ItemTaskModule m) {
		// Add to modules list
		modules.add(m);

		// Apply any task specific properties to this entity.
		m.initEntity(this);

		// Make new ItemStack to be added to the inventory of the entity
		ItemStack newStack = new ItemStack(m, 1, 0);

		// Get current list of modules in entity.
		ItemStack[] current = expansions.get(InventoryType.module);

		// Temporary list to make adding another module easier.
		List<ItemStack> temp = new ArrayList<ItemStack>(Arrays.asList(current));
		temp.add(newStack);

		// Remove any empty entries from the list.
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i) == null) {
				temp.remove(i);
			}
		}

		// Convert the list back to an array and add back to the inventory.
		ItemStack[] newItemStack = temp.toArray(new ItemStack[temp.size()]);
		expansions.put(InventoryType.module, newItemStack);
	}

	public boolean isInBlockAt(int x, int y, int z){
		return (posX >= x && posX < (x+1))&&(posY >= y && posY < (y+1))&&(posZ >= z && posZ < (z+1));
	}
	
	public TileEntityDroneBase getBase() {
		return base;
	}

	public void setBase(TileEntityDroneBase base) {
		this.base = base;
	}

	public void addWorkMarker(TileEntityMarker m) {
		this.workMarkers.add(m);
	}

	public void addTaskModule(ItemTaskModule m) {
		this.modules.add(m);
	}

	public int getEnergy() {
		return energy;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public SimpleInventory getActualInventory() {
		return actualInventory;
	}

	public void setActualInventory(SimpleInventory actualInventory) {
		this.actualInventory = actualInventory;
	}

	public List<TileEntityMarker> getWorkMarkers() {
		return workMarkers;
	}

	public List<ItemTaskModule> getModules() {
		return modules;
	}

	@Override
	protected float getSoundVolume() {
		return 0.0F;
	}

	public boolean isGoingHome() {
		return goingHome;
	}

	public void setGoingHome(boolean goingHome) {
		this.goingHome = goingHome;
	}

	@Override
	protected void fall(float par1) {}
	
	public TileEntityMarker getCurrentWork() {
		return currentWork;
	}
	
	public void setCurrentWork(TileEntityMarker currentWork) {
		this.currentWork = currentWork;
	}
	
	public boolean isRestocking() {
		return restocking;
	}
	
	public void setRestocking(boolean restocking) {
		this.restocking = restocking;
	}	
	
	/**
	 * Makes the drone do nothing for the specified amount of time in ticks.
	 * 
	 * @param sleepTime The amount of time to sleep in ticks.
	 */
	public void sleep(int sleepTime){
		this.sleep = sleepTime;
	}
	public boolean isAwake() {
		return sleep == -1;
	}
	public HashMap<InventoryType, ItemStack[]> getExpansions() {
		return expansions;
	}
	
	
}