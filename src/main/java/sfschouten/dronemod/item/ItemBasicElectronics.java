package sfschouten.dronemod.item;

import net.minecraft.item.Item;
import sfschouten.dronemod.init.MMDCreativeTabs;
import sfschouten.dronemod.reference.General;

public class ItemBasicElectronics extends Item{

	public ItemBasicElectronics() {
		super();
		this.setTextureName(General.modID + ":basic_electronics");
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
		this.setMaxStackSize(64);
		this.setUnlocalizedName("basicElectronicsItem");
	}
}
