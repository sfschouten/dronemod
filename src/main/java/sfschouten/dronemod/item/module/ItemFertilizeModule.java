package sfschouten.dronemod.item.module;

import net.minecraft.init.Items;
import net.minecraft.item.ItemDye;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.reference.General;
import sfschouten.dronemod.tileentity.TileEntityMarker;

public class ItemFertilizeModule extends ItemTaskModule {

	public ItemFertilizeModule(){
		super();
		this.setUnlocalizedName("fertilizeModuleItem");
		this.setTextureName(General.modID + ":basic_fertilize_icon");
		this.restockItem = Items.dye;
		this.restockItemDamageValue = 15;
	}
	
	@Override
	public DroneTaskResult performTask(EntityDrone d, DroneTaskSubject s) {
		DroneTaskResult result;
		TileEntityMarker m = d.getCurrentWork();
		int slot = d.getFirstSlotForItemAndDamage(this.restockItem, this.restockItemDamageValue);
		if(slot > -1){	
			ItemDye.func_150919_a(d.getActualInventory().getStackInSlot(slot), d.worldObj, (int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ));
			result = DroneTaskResult.success;
		}else{
			result = DroneTaskResult.resourcelow;
		}
		
		return result;
	}

	@Override
	public void applyProperties(EntityDrone e) {
		// TODO Auto-generated method stub
		
	}
}
