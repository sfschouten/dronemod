package sfschouten.dronemod.entity.ai;

import net.minecraft.entity.ai.EntityAIBase;
import sfschouten.dronemod.entity.EntityDrone;

public class DroneRechargeAI extends EntityAIBase {
/*
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
*/
	@Override
	public boolean shouldExecute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void startExecuting() {
		// TODO Auto-generated method stub
		super.startExecuting();
	}
}
