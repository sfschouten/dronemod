package sfschouten.dronemod.client.renderer.item;

import org.lwjgl.opengl.GL11;

import sfschouten.dronemod.client.model.ModelCopter;
import sfschouten.dronemod.client.renderer.entity.RenderOctacopter;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.util.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import net.minecraftforge.client.IItemRenderer.ItemRenderType;

public class DroneItemRenderer implements IItemRenderer{

	private ModelCopter model;
	private ResourceLocation textureLocation;
	
	public DroneItemRenderer(ModelCopter model, ResourceLocation textureLocation) {
		this.model = model;
		this.textureLocation = textureLocation;
	}

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item, ItemRendererHelper helper) {
		return false;	
	}

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) {
		Logger.debug("renderItem");
		switch(type){
		case ENTITY:
			renderItemEntity(item, data);
			break;
		case EQUIPPED:
			renderItemEquipped(item, data);
			break;
		case EQUIPPED_FIRST_PERSON:
			renderItemEquippedFirstPerson(item, data);
			break;
		case FIRST_PERSON_MAP:
			break;
		case INVENTORY:
			renderItemInventory(item, data);
			break;
		default:
			break;
		}
	}
	
	private void renderItemEntity(ItemStack item, Object... data){
		RenderBlocks render = (RenderBlocks) data[0];
		EntityItem entity = (EntityItem) data[1];
		
		RenderManager.instance.renderEngine.bindTexture(textureLocation);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 1.0F, 0.0F);
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);
		model.render(entity, 0F, 0F, 0F, 0F, 0F, 0.0625F, false);
		GL11.glPopMatrix();
	}
	
	private void renderItemEquipped(ItemStack item, Object... data){
		RenderBlocks render = (RenderBlocks) data[0];
		EntityClientPlayerMP entity = (EntityClientPlayerMP) data[1];

		RenderManager.instance.renderEngine.bindTexture(textureLocation);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 1.3F, 0.0F);
		GL11.glRotatef(200F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(-15F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-15F, 1.0F, 0.0F, 0.0F);
		model.render(entity, 0F, 0F, 0F, 0F, 0F, 0.0625F, false);
		GL11.glPopMatrix();
		
	}
	
	private void renderItemEquippedFirstPerson(ItemStack item, Object... data){
		RenderBlocks render = (RenderBlocks) data[0];
		EntityClientPlayerMP entity = (EntityClientPlayerMP) data[1];
		
		RenderManager.instance.renderEngine.bindTexture(textureLocation);
		GL11.glPushMatrix();
		GL11.glTranslatef(0.0F, 1.3F, 0.0F);
		GL11.glRotatef(200F, 0.0F, 0.0F, 1.0F);
		GL11.glRotatef(-15F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-15F, 1.0F, 0.0F, 0.0F);
		model.render(entity, 0F, 0F, 0F, 0F, 0F, 0.0625F, false);
		GL11.glPopMatrix();
	}
	
	private void renderItemInventory(ItemStack item, Object... data){
		RenderBlocks render = (RenderBlocks) data[0];
		
		Minecraft.getMinecraft().renderEngine.bindTexture(textureLocation);
		GL11.glPushMatrix();
		GL11.glTranslatef(9.0F, 0.0F, 0.0F);
		GL11.glRotatef(15F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-20F, 1.0F, 0.0F, 0.0F);
		GL11.glScalef(10F, 10F, 10F);
		model.render(null, 0F, 0F, 0F, 0F, 0F, 0.0625F, false);
		GL11.glPopMatrix();
	}
}
