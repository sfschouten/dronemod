package sfschouten.dronemod.item.battery;

import sfschouten.dronemod.DroneMod;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemMediumDroneBattery extends ItemDroneBattery {

	public ItemMediumDroneBattery() {
		super();
		this.setUnlocalizedName("mediumDroneBatteryItem");
		this.setTextureName(DroneMod.modID + ":medium_battery");
		this.capacity = 100000;
		this.maxExtract = 200;
		this.maxReceive = 200;
	}
}
