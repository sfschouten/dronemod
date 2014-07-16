package sfschouten.dronemod.item.frame;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import sfschouten.dronemod.reference.General;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemQuadCaneFrame extends ItemDroneFrame {

	public ItemQuadCaneFrame( ) {
		super();
		this.setUnlocalizedName("caneQuadFrameItem");

		this.setTextureName(General.modID + ":cane_quad_copter_frame_icon");
		ItemStack cane = new ItemStack(Items.reeds);
		ItemStack lily = new ItemStack(Blocks.waterlily);
		
		GameRegistry.addRecipe(new ItemStack(this), "x x", " y ", "x x", 'x', cane, 'y', lily);
	}
}
