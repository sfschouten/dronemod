package sfschouten.dronemod.item.module;

import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDCreativeTabs;
import sfschouten.dronemod.init.MMDTileEntities;
import net.minecraft.item.Item;

public abstract class ItemModule extends Item{

	public ItemModule(){
		setCreativeTab(MMDCreativeTabs.tabGeneral);
	}
	
	public abstract EntityDrone initEntity(EntityDrone drone);
}
