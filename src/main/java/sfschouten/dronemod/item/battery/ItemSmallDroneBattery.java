package sfschouten.dronemod.item.battery;

import sfschouten.dronemod.reference.General;

public class ItemSmallDroneBattery extends ItemDroneBattery {

	public ItemSmallDroneBattery() {
		super();
		this.setUnlocalizedName("smallDroneBatteryItem");
		this.setTextureName(General.modID + ":small_battery");
		this.capacity = 1000;
		this.maxExtract = 50;
		this.maxReceive = 50;
	}
}
