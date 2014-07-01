package sfschouten.dronemod.client.renderer.entity;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModelCustom;

public abstract class RenderDrone extends Render{

	private IModelCustom model; 
	
	public RenderDrone(IModelCustom model){
		this.model = model;
	}
	
	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9) {
		GL11.glPushMatrix();
        GL11.glTranslated(x, y, z);
        ResourceLocation texture = getEntityTexture(var1);
        Minecraft.getMinecraft().renderEngine.bindTexture(texture);
        model.renderAll();
        GL11.glPopMatrix();
	}

	@Override
	protected abstract ResourceLocation getEntityTexture(Entity var1);

}
