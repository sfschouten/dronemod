package sfschouten.dronemod.item.battery;

import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemLargeDroneBattery extends ItemDroneBattery {

	public ItemLargeDroneBattery() {
		super();
		this.setUnlocalizedName("largeDroneBatteryItem");
		this.setTextureName("dronemod:large_battery");
		this.capacity = 1000000;
		this.maxExtract = 2000;
		this.maxReceive = 2000;
	}
}
