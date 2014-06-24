package sfschouten.dronemod.item.battery;

import sfschouten.dronemod.DroneMod;
import cofh.api.energy.IEnergyContainerItem;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class ItemSmallDroneBattery extends ItemDroneBattery {

	public ItemSmallDroneBattery() {
		super();
		this.setUnlocalizedName("smallDroneBatteryItem");
		this.setTextureName(DroneMod.modID + ":small_battery");
		this.capacity = 1000;
		this.maxExtract = 50;
		this.maxReceive = 50;
	}
}
