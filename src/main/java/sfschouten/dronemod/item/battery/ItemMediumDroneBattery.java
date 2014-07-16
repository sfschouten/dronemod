package sfschouten.dronemod.item.battery;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.reference.General;

public class ItemMediumDroneBattery extends ItemDroneBattery {

	public ItemMediumDroneBattery() {
		super();
		this.setUnlocalizedName("mediumDroneBatteryItem");
		this.setTextureName(General.modID + ":medium_battery");
		this.capacity = 100000;
		this.maxExtract = 200;
		this.maxReceive = 200;
	}
}
