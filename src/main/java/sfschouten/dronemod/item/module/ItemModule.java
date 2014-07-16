package sfschouten.dronemod.item.module;

import sfschouten.dronemod.entity.EntityDrone;
import net.minecraft.item.Item;

public abstract class ItemModule extends Item{

	public abstract EntityDrone initEntity(EntityDrone drone);
}
