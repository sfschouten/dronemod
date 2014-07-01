package sfschouten.dronemod.client.renderer.entity;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityAluminiumMediumHexacopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongHexacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumHexacopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.entity.EntityWoodStrongHexacopter;
import sfschouten.dronemod.entity.EntityWoodStrongQuadcopter;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderHexacopter extends RenderDrone {

	private static final ResourceLocation woodHexaTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/woodHexa.png");
	private static final ResourceLocation caneHexaTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/caneHexa.png");
	private static final ResourceLocation aluminiumHexaTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/aluminiumHexa.png");
	
	private static final ResourceLocation hexaModelLocation = new ResourceLocation(DroneMod.modID + ":models/entity/hexa.obj");
	private static final IModelCustom hexaModel = AdvancedModelLoader.loadModel(hexaModelLocation);
	
	public RenderHexacopter() {
		super(hexaModel);
	}

	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9) {
		// Render chests, modules, batteries in appropriate locations for hexacopters.
		super.doRender(var1, x, y, z, var8, var9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		EntityDrone d = null;
		try{
			d = (EntityDrone) var1;
		}catch(ClassCastException e){
			e.printStackTrace();
		}
		
		if(d instanceof EntityAluminiumMediumHexacopter || d instanceof EntityAluminiumStrongHexacopter) {
			return aluminiumHexaTextureLocation;
		}else if(d instanceof EntityWoodMediumHexacopter || d instanceof EntityWoodStrongHexacopter ){
			return woodHexaTextureLocation;
		}else if(d instanceof EntityCaneWeakHexacopter){
			return caneHexaTextureLocation;
		}else{
			Logger.logOut("Wrong type of drone, not a Hexa! No texture loaded.");
			return null;
		}
	}
}
