package sfschouten.dronemod.item.module;

import sfschouten.dronemod.entity.EntityDrone;

public abstract class ItemBasicTaskModule extends ItemTaskModule {
	boolean hasDepth = false;
	
	public boolean hasDepth(){
		return hasDepth; 
	}
}
