package sfschouten.dronemod.item.module;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.entity.EntityDrone;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class ItemAdvancedTaskModule extends ItemTaskModule {
	
	public abstract DroneTaskSubject findNearestTask(EntityDrone d);
}
