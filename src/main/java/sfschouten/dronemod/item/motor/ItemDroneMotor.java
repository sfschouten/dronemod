package sfschouten.dronemod.item.motor;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.init.MMDCreativeTabs;
import net.minecraft.item.Item;

public abstract class ItemDroneMotor extends Item{

	public ItemDroneMotor( ) {
		super();
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
        this.setMaxStackSize(16);
	}
}
