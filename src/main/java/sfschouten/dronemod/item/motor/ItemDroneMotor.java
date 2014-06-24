package sfschouten.dronemod.item.motor;

import sfschouten.dronemod.DroneMod;
import net.minecraft.item.Item;

public abstract class ItemDroneMotor extends Item{

	public ItemDroneMotor( ) {
		super();
		this.setCreativeTab(DroneMod.tabDroneMod);
        this.setMaxStackSize(16);
	}
}
