package sfschouten.dronemod.item.frame;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.init.MMDCreativeTabs;
import net.minecraft.item.Item;

public abstract class ItemDroneFrame extends Item {

	public ItemDroneFrame( ) {
		super();
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
        this.setMaxStackSize(16);
	}
}
