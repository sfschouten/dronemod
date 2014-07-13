package sfschouten.dronemod.client.renderer.block;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.client.model.ModelMilling;

public class TileEntityMillerRenderer extends TileEntitySpecialRenderer {
	private final ModelMilling model;

	public TileEntityMillerRenderer() {
		this.model = new ModelMilling();
	}

	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glRotatef(meta * (90), 0.0F, 1.0F, 0.0F);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) {
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 2.25F, (float) z + 0.5F);
		
		ResourceLocation textures = new ResourceLocation(DroneMod.modID, "textures/blocks/millingmachine.png");
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(1.5F, 1.5F, 1.5F);;
		adjustRotatePivotViaMeta(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
		this.model.render((Entity) null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();
	}


	private void adjustLightFixture(World world, int i, int j, int k, Block block) {
		Tessellator tess = Tessellator.instance;

		float brightness = block.getLightValue(world, i, j, k);
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier);
	}
}
