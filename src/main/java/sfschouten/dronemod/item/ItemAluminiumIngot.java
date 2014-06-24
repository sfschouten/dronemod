package sfschouten.dronemod.item;

import cpw.mods.fml.common.registry.LanguageRegistry;
import sfschouten.dronemod.DroneMod;
import net.minecraft.item.Item;
import net.minecraftforge.oredict.OreDictionary;

public class ItemAluminiumIngot extends Item{

	public ItemAluminiumIngot() {
		super();
		OreDictionary.registerOre("ingotAluminum", this);
		this.setTextureName("dronemod:ingot_aluminium");
		this.setCreativeTab(DroneMod.tabDroneMod);
        this.setMaxStackSize(64);
        this.setUnlocalizedName("aluminiumIngot");
	}
}
