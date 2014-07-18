package sfschouten.dronemod.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.sun.xml.internal.bind.v2.runtime.Coordinator;

import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Vec3;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import sfschouten.dronemod.entity.ai.DroneAdvancedTaskAI;
import sfschouten.dronemod.entity.ai.DroneBasicTaskAI;
import sfschouten.dronemod.entity.ai.DroneMoveHelper;
import sfschouten.dronemod.entity.ai.DroneRestockAI;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.inventory.SimpleInventory;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.item.module.ItemModule;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.pathfinding.PathNavigate3D;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.util.Axis;
import sfschouten.dronemod.util.Logger;
import cpw.mods.fml.relauncher.ReflectionHelper;

public abstract class EntityDrone extends EntityFlying {
	/** The tile entity corresponding to the base of this drone. */
	private TileEntityDroneBase base;

	/**
	 * A list of TileEntityMarkers corresponding to the work markers of this
	 * drone.
	 */
	private List<TileEntityMarker> workMarkers;

	/** The current marker being worked. */
	private TileEntityMarker currentWork;

	/**
	 * The Inventory of this drone containing the items it picks up from the
	 * world etc.
	 */
	private SimpleInventory actualInventory;

	/** TODO The amount of energy in .... */
	private int energy;

	/** A list of modules with the tasks that this drone should do. */
	private List<ItemModule> modules;

	/** A sleep timer, the amount of ticks left to let the drone sleep. */
	private int sleep;

	/**
	 * A ticket used to chunkload the chunks this drone is in if it has a
	 * chunkloadingmodule
	 */
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
		modules = new ArrayList<ItemModule>();
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

		ReflectionHelper.setPrivateValue(EntityLiving.class, this, new PathNavigate3D(this, worldObj, 75), new String[] { "navigator", "field_70699_by" });
		ReflectionHelper.setPrivateValue(EntityLiving.class, this, new DroneMoveHelper(this), new String[] { "moveHelper", "field_70765_h" });

	}

	/**
	 * Used to add a module to the appropriate collections in the entity. Added
	 * to the collection that simply lists the modules in the entity. Also added
	 * to the collection with the inventories for the numerous
	 * expansions(batteries, modules, chest, etc.)
	 * 
	 * Only call when entity is not spawned in world yet.
	 * 
	 * @param m Module to be added.
	 */
	public void addModule(ItemModule m) {
		if (!this.addedToChunk) {
			Logger.info("not added to chunk");
		}

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

	public void addWorkMarker(TileEntityMarker m) {
		this.workMarkers.add(m);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(40.0D);
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2D);
	}

	public boolean coordinateCheck(Axis a, int coord, double range){
		return coordinateCheck(a, coord, range, range);
	}

	/**
	 * Checks if this drone is within the range of the middle of theon the supplied axis
	 * 
	 * @param a
	 * @param coord
	 * @param range
	 * @return
	 */
	public boolean coordinateCheck(Axis a, int coord, double lrange, double rrange){
		switch (a) {
		case x:
			if(coord + 0.5D - lrange < posX && coord + 0.5D + rrange > posX ){
				return true;
			}
			break;
		case y:
			if(coord + 0.5D - lrange < posY && coord + 0.5D + rrange > posY ){
				return true;
			}
			break;
		case z:
			if(coord + 0.5D - lrange < posZ && coord + 0.5D + rrange > posZ ){
				return true;
			}
			break;
		}
		return false;
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

	@Override
	protected void fall(float par1) {
	}

	public void forcedChunkChanged(int newx, int newz, int oldx, int oldz) {
		int xd = newx - oldx;
		int zd = newz - oldz;

		for (int i = -1; i < 2; i++) {
			Logger.info("unload: " + oldx + "-" + xd + "+" + "(" + zd + "*" + i + ")," + oldz + "-" + zd + "+(" + xd + "*" + i + ")");
			ChunkCoordIntPair unload = new ChunkCoordIntPair(oldx - xd + (zd * i), oldz - zd + (xd * i));
			ForgeChunkManager.unforceChunk(chunkloadingTicket, unload);
		}

		for (int i = -1; i < 2; i++) {
			Logger.info("  load: " + newx + "+" + xd + "+" + "(" + zd + "*" + i + ")," + newz + "+" + zd + "+(" + xd + "*" + i + ")");
			ChunkCoordIntPair load = new ChunkCoordIntPair(newx + xd + (zd * i), newz + zd + (xd * i));
			ForgeChunkManager.forceChunk(chunkloadingTicket, load);
		}
	}

	public SimpleInventory getActualInventory() {
		return actualInventory;
	}

	public TileEntityDroneBase getBase() {
		return base;
	}

	public Ticket getChunkloadingTicket() {
		return chunkloadingTicket;
	}

	public TileEntityMarker getCurrentWork() {
		return currentWork;
	}

	public int getEnergy() {
		return energy;
	}

	public abstract int getEnergyUse();

	public HashMap<InventoryType, ItemStack[]> getExpansions() {
		return expansions;
	}
	
	/**
	 * Relies on getFirstSlotForItemIDAndDamage, just uses 0 for damage value.
	 * 
	 * @param itemID
	 *            the ID of an item.
	 * @return the number of the first slot that can contain an item with the
	 *         supplied itemID. Returns -1 if no slot with the specified item
	 *         can be found.
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
	 *         supplied itemID and damage value. Returns -1 if no slot with the
	 *         specified item can be found.
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

	public abstract ItemDrone getItem();

	public List<ItemModule> getModules() {
		return modules;
	}

	public abstract int getRestockSpeed();

	@Override
	protected float getSoundVolume() {
		return 0.0F;
	}

	public List<ItemTaskModule> getTaskModules() {
		List<ItemTaskModule> result = new ArrayList<ItemTaskModule>();
		for(ItemModule m : modules){
			if(m instanceof ItemTaskModule){
				result.add((ItemTaskModule)m);
			}
		}
		return result;
	}

	public List<TileEntityMarker> getWorkMarkers() {
		return workMarkers;
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
			//unforce chunks.
			releaseChunkloadingTicket();
			// Create new ItemStack with of the corresponding sort.
			ItemStack newStack = new ItemStack(getItem());

			// Create new compound tag and write this entity to it.
			NBTTagCompound tc = new NBTTagCompound();
			writeToNBT(tc);

			// Set the compound tag to the new ItemStack.
			newStack.setTagCompound(tc);

			if (!par1EntityPlayer.inventory.addItemStackToInventory(newStack)) {
				//TODO handle failed addItemStackToInventory
			}
			return true;
		} else {
			return super.interact(par1EntityPlayer);
		}
	}

	private void releaseChunkloadingTicket(){
		ForgeChunkManager.releaseTicket(chunkloadingTicket);
	}
	
	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	public boolean isAwake() {
		return sleep == -1;
	}
	
	public boolean isGoingHome() {
		return goingHome;
	}

	public boolean isInBlockAt(int x, int y, int z) {
		boolean bx = coordinateCheck(Axis.x, x, 0.5D);
		boolean by = coordinateCheck(Axis.y, y, 0.5D);
		boolean bz = coordinateCheck(Axis.z, z, 0.5D);
		return bx && by && bz;
	}

	public boolean isRestocking() {
		return restocking;
	}

	@Override
	public void onUpdate() {
		this.fallDistance = 0.0F;
		this.isAirBorne = true;
		if (sleep > -1) {
			this.sleep--;
		}

		PathEntity path = getNavigator().getPath();
		if (path != null) {
			if (!path.isFinished()) {
				Vec3 current = path.getPosition(this);
				Logger.info("entity: "+posX+", "+posY+", "+posZ);
				Logger.info("current: "+current.xCoord+", "+current.yCoord+", "+current.zCoord);
				if ((current.xCoord - 0.5 < posX && current.xCoord + 0.5 > posX) 
						&& (current.zCoord - 0.5 < posZ && current.zCoord + 0.5 > posZ)
						&& (current.yCoord < posY && current.yCoord + 1 > posY)) {
					path.setCurrentPathIndex(path.getCurrentPathIndex() + 1);
					Logger.info("NEXT!");
				}
			}
		}
		super.onUpdate();
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		for (InventoryType type : InventoryType.values()) {
			NBTTagCompound typeComp = par1nbtTagCompound.getCompoundTag(type.name());
			SimpleInventory inv = new SimpleInventory(typeComp.getInteger("InventoryLength"));
			inv.readFromNBT(typeComp);
			expansions.put(type, inv.getInv());
		}

		NBTTagCompound cwTag = par1nbtTagCompound.getCompoundTag("currentWork");
		int cqX = cwTag.getInteger("x");
		int cqY = cwTag.getInteger("y");
		int cqZ = cwTag.getInteger("z");

		NBTTagCompound baseComp = par1nbtTagCompound.getCompoundTag("Base");
		base = (TileEntityDroneBase) TileEntity.createAndLoadEntity(baseComp);
		
		super.readFromNBT(par1nbtTagCompound);
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

		if (currentWork != null) {
			NBTTagCompound currentWorkComp = new NBTTagCompound();
			currentWorkComp.setInteger("x", currentWork.xCoord);
			currentWorkComp.setInteger("y", currentWork.yCoord);
			currentWorkComp.setInteger("z", currentWork.zCoord);
			par1nbtTagCompound.setTag("currentWork", currentWorkComp);
		}
		
		if(base != null){
			NBTTagCompound baseComp = new NBTTagCompound();
			base.writeToNBT(baseComp);
			par1nbtTagCompound.setTag("Base", baseComp);
		}
		
		super.writeToNBT(par1nbtTagCompound);
	}
	
	public void setActualInventory(SimpleInventory actualInventory) {
		this.actualInventory = actualInventory;
	}

	public void setBase(TileEntityDroneBase base) {
		this.base = base;
	}

	public void setChunkloadingTicket(Ticket chunkloadingTicket) {
		this.chunkloadingTicket = chunkloadingTicket;
	}

	public void setCurrentWork(TileEntityMarker currentWork) {
		this.currentWork = currentWork;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
	}

	public void setGoingHome(boolean goingHome) {
		this.goingHome = goingHome;
	}

	public void setRestocking(boolean restocking) {
		this.restocking = restocking;
	}

	/**
	 * Makes the drone do nothing for the specified amount of time in ticks.
	 * 
	 * @param sleepTime
	 *            The amount of time to sleep in ticks.
	 */
	public void sleep(int sleepTime) {
		this.sleep = sleepTime;
	}

}