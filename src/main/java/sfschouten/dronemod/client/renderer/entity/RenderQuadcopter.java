package sfschouten.dronemod.client.renderer.entity;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.entity.EntityWoodStrongQuadcopter;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderQuadcopter extends RenderDrone {

	private static final ResourceLocation woodQuadTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/woodQuad.png");
	private static final ResourceLocation caneQuadTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/caneQuad.png");
	private static final ResourceLocation aluminiumQuadTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/aluminiumQuad.png");
	
	private static final ResourceLocation quadModelLocation = new ResourceLocation(DroneMod.modID + ":models/entity/quad.obj");
	private static final IModelCustom quadModel = AdvancedModelLoader.loadModel(quadModelLocation);
	
	public RenderQuadcopter() {
		super(quadModel);
	}

	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9) {
		// Render chests, modules, batteries in appropriate locations for quadcopters.
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
		
		if(d instanceof EntityAluminiumMediumQuadcopter || d instanceof EntityAluminiumStrongQuadcopter) {
			return aluminiumQuadTextureLocation;
		}else if(d instanceof EntityWoodMediumQuadcopter || d instanceof EntityWoodStrongQuadcopter ){
			return woodQuadTextureLocation;
		}else if(d instanceof EntityCaneWeakQuadcopter){
			return caneQuadTextureLocation;
		}else{
			Logger.logOut("Wrong type of drone, not a quad! No texture loaded.");
			return null;
		}
	}
}
