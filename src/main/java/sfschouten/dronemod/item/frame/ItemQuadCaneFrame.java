package sfschouten.dronemod.item.frame;

import sfschouten.dronemod.DroneMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemQuadCaneFrame extends ItemDroneFrame {

	public ItemQuadCaneFrame( ) {
		super();
		this.setUnlocalizedName("caneQuadFrameItem");

		this.setTextureName(DroneMod.modID + ":cane_quad_copter_frame_icon");
		ItemStack cane = new ItemStack(Items.reeds);
		ItemStack lily = new ItemStack(Blocks.waterlily);
		
		GameRegistry.addRecipe(new ItemStack(this), "x x", " y ", "x x", 'x', cane, 'y', lily);
	}
}
