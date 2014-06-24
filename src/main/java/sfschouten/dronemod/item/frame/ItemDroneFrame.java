package sfschouten.dronemod.item.frame;

import sfschouten.dronemod.DroneMod;
import net.minecraft.item.Item;

public abstract class ItemDroneFrame extends Item {

	public ItemDroneFrame( ) {
		super();
		this.setCreativeTab(DroneMod.tabDroneMod);
        this.setMaxStackSize(16);
	}
}
