package sfschouten.dronemod.entity.ai;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.ai.DroneAI;

public class DroneRechargeAI extends DroneAI {

	@Override
	public boolean executeAI(EntityDrone e) {
		if(e.goToBase()){
			e.getBase().setSpawnOnCharged(true);
			e.getBase().storeDrone(e);
			return true;
		}else{
			return false;
		}
	}
}
