package sfschouten.dronemod.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import sfschouten.dronemod.DroneMod;
import net.minecraft.item.Item;

public class ItemBasicElectronics extends Item{

	public ItemBasicElectronics() {
		super();
		this.setTextureName("dronemod:basic_electronics");
		this.setCreativeTab(DroneMod.tabDroneMod);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("basicElectronicsItem");
	}
}
