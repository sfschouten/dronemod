package sfschouten.dronemod.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.ai.DroneAI;

public class DroneHomingAI extends EntityAIBase {

	@Override
	public boolean executeAI(EntityDrone e) {
		if(e.goToBase()){
			e.getBase().storeDrone(e);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		return false;
	}
}
