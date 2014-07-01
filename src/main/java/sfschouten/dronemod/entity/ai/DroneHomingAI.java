package sfschouten.dronemod.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import sfschouten.dronemod.entity.EntityDrone;
public class DroneHomingAI extends EntityAIBase {
	private EntityDrone drone;
	
	public DroneHomingAI(EntityDrone drone){
		this.drone = drone;
	}

	@Override
	public boolean shouldExecute() {
		return this.drone.isGoingHome();
	}

	@Override
	public void startExecuting() {
		
		super.startExecuting();
	}
}
