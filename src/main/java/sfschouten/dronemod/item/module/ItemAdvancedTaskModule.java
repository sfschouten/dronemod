package sfschouten.dronemod.item.module;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.entity.EntityDrone;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class ItemAdvancedTaskModule extends ItemTaskModule {
	
	public ItemAdvancedTaskModule() {
		super();
	}
	
	public abstract DroneTaskSubject findNearestTask(EntityDrone d);
}
