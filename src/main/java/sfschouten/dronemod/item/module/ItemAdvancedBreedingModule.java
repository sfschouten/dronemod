package sfschouten.dronemod.item.module;

import java.lang.reflect.Method;
import java.util.ArrayList;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.inventory.SimpleInventory;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.util.Logger;

public class ItemAdvancedBreedingModule extends ItemAdvancedTaskModule{

	public ItemAdvancedBreedingModule( ) {
		super();
		this.setUnlocalizedName("advancedBreedingModuleItem");
		//this.setTextureName("dronemod:basic_fertilize_icon");
		//this.restockItem = Item.dyePowder;//TODO make multiple possible
		//this.restockItemDamageValue = 15;
	}

	@Override
	public DroneTaskSubject findNearestTask(EntityDrone d) {
		TileEntityMarker workMarker = d.getCurrentWork();
		int workRadius = workMarker.getRadius();
		int workX = workMarker.xCoord;
		int workY = workMarker.yCoord;
		int workZ = workMarker.zCoord;
		
		workRadius++;
		AxisAlignedBB aabb = AxisAlignedBB.getBoundingBox(workX-workRadius, workY, workZ-workRadius, workX+workRadius, workY+3, workZ+workRadius);
		ArrayList<Entity> entities = new ArrayList(d.worldObj.getEntitiesWithinAABBExcludingEntity(d, aabb));
		
		ArrayList<Entity> remove = new ArrayList<Entity>();
		
		for(Entity e : entities){
			if(!(e instanceof EntityAnimal)){
				remove.add(e);
			}
		}
		entities.removeAll(remove);
		
		
		Entity closest = null;
		float closestDistance = Float.MAX_VALUE;
		for(Entity e : entities){
			float distance = d.getDistanceToEntity(e);
			if(distance < closestDistance){
				closestDistance = distance;
				closest = e;
			}
		}

		if(closest == null){
			return null;
		}
		
		DroneTaskSubject result = new DroneTaskSubject();
		result.setEntity(closest);
		result.setX(closest.posX);
		result.setY(closest.posY);
		result.setZ(closest.posZ);
		return result;
	}

	@Override
	public DroneTaskResult performTask(EntityDrone d, DroneTaskSubject s) {
		EntityAnimal animal = (EntityAnimal) s.getEntity();
		SimpleInventory inv = d.getActualInventory();
		ItemStack breedingItem = null;
		for(int i = 0; i < inv.getSizeInventory(); i++){
			ItemStack stack = inv.getStackInSlot(i);
			if(stack != null && animal.isBreedingItem(stack)){
				breedingItem = stack;
			}
		}
		
		if(breedingItem == null){
			return DroneTaskResult.resourcelow;
		}else{
			breedingItem.stackSize--;
			//TODO replace animal.func_110196_bT(); with animal.func_146082_f( --fakeplayer-- );
			Logger.logChat(animal.worldObj, "Does not work need to update code");
			return DroneTaskResult.success;
		}
	}

	@Override
	public void applyProperties(EntityDrone e) {
		// TODO Auto-generated method stub
		
	}
}
