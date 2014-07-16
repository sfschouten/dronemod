package sfschouten.dronemod.item;

import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;
import sfschouten.dronemod.init.MMDCreativeTabs;
import sfschouten.dronemod.reference.General;

public class ItemAluminiumIngot extends Item{

	public ItemAluminiumIngot() {
		super();
		OreDictionary.registerOre("ingotAluminum", this);
		this.setTextureName(General.modID + ":ingot_aluminium");
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
        this.setMaxStackSize(64);
        this.setUnlocalizedName("aluminiumIngotItem");
	}
}
