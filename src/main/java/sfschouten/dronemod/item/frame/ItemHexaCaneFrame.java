package sfschouten.dronemod.item.frame;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemHexaCaneFrame extends ItemDroneFrame {

	public ItemHexaCaneFrame( ) {
		super();
		this.setUnlocalizedName("caneHexaFrameItem");
		
		ItemStack cane = new ItemStack(Items.reeds);
		ItemStack lily = new ItemStack(Blocks.waterlily);
		
		GameRegistry.addRecipe(new ItemStack(this), "x x", "xyx", "x x", 'x', cane, 'y', lily);
	}
}
