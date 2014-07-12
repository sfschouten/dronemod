package sfschouten.dronemod.item.battery;

import sfschouten.dronemod.DroneMod;

public class ItemLargeDroneBattery extends ItemDroneBattery {

	public ItemLargeDroneBattery() {
		super();
		this.setUnlocalizedName("largeDroneBatteryItem");
		this.setTextureName(DroneMod.modID + ":large_battery");
		this.capacity = 1000000;
		this.maxExtract = 2000;
		this.maxReceive = 2000;
	}
}
