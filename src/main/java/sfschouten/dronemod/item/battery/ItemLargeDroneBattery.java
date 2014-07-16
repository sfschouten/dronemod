package sfschouten.dronemod.item.battery;

import sfschouten.dronemod.reference.General;

public class ItemLargeDroneBattery extends ItemDroneBattery {

	public ItemLargeDroneBattery() {
		super();
		this.setUnlocalizedName("largeDroneBatteryItem");
		this.setTextureName(General.modID + ":large_battery");
		this.capacity = 1000000;
		this.maxExtract = 2000;
		this.maxReceive = 2000;
	}
}
