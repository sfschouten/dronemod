package sfschouten.dronemod.client.renderer.entity;

import java.util.HashMap;
import java.util.Map.Entry;

import org.lwjgl.opengl.GL11;

import sfschouten.dronemod.client.model.ModelBattery;
import sfschouten.dronemod.client.model.ModelCopter;
import sfschouten.dronemod.client.model.ModelExpansion;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityDroneFX;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.util.ExpansionHelper;
import sfschouten.dronemod.util.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;

public abstract class RenderCopter extends RenderLiving{

	/** Always applied to the default z rotation so things render the right way up. */
	private static final float Z_ROTATE = 180.0F;
	
	public RenderCopter(ModelCopter model, float par2) {
		super(model, par2);
	}

	@Override
	public void doRender(EntityLiving par1EntityLiving, double x, double y, double z, float par8, float par9) {
		super.doRender(par1EntityLiving, x, y, z, par8, par9);
		if(par1EntityLiving instanceof EntityDroneFX){
			return;
		}
		EntityDrone drone = (EntityDrone) par1EntityLiving;
		HashMap<InventoryType, ItemStack[]> expansions = ExpansionHelper.divideExpansions(drone.getExpansions());
		
		//TODO remove this temp
		renderExpansionType(InventoryType.battery, expansions.get(InventoryType.battery), x, y, z);
		
		//TODO enable this again
		/*
		for(Entry<InventoryType, ItemStack[]> e : expansions.entrySet()){
			renderExpansionType(e.getKey(), e.getValue(), x, y, z);
		}
		*/
	}
	
	private void renderExpansionType(InventoryType type, ItemStack[] expansions, double x, double y, double z){
		Class<? extends ModelExpansion> modelClass = type.getModel();
		ModelExpansion expansionModel = null;
		ModelCopter droneModel = (ModelCopter)mainModel;
		float[][] positions = droneModel.getPositions(type);
		
		try {
			expansionModel = modelClass.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		float[] defaultPosition = droneModel.getDefaultPosition();
		GL11.glPushMatrix();
		GL11.glTranslatef((float)x + defaultPosition[0], (float)y + defaultPosition[1], (float)z + defaultPosition[2]);
		GL11.glRotatef(defaultPosition[3], 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(defaultPosition[4], 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(defaultPosition[5]+Z_ROTATE, 0.0F, 0.0F, 1.0F);
		GL11.glScalef(defaultPosition[6], defaultPosition[7], defaultPosition[8]);
		
		for(int i = 0; i < expansions.length; i++){
			//if(expansions[i] != null){
				float[] position = positions[i];
				GL11.glPushMatrix();
				GL11.glTranslatef(position[0], position[1], position[2]);
				GL11.glRotatef(position[3], 1.0F, 0.0F, 0.0F);
				GL11.glRotatef(position[4], 0.0F, 1.0F, 0.0F);
				GL11.glRotatef(position[5], 0.0F, 0.0F, 1.0F);
				GL11.glScalef(position[6], position[7], position[8]);
				Minecraft.getMinecraft().renderEngine.bindTexture(expansionModel.texture);
				expansionModel.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
				GL11.glPopMatrix();
			//}
		}
		GL11.glPopMatrix();
	}
}
