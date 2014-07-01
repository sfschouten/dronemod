package sfschouten.dronemod.client.renderer.entity;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityAluminiumMediumHexacopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongHexacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumHexacopter;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.entity.EntityWoodStrongHexacopter;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.entity.EntityWoodStrongQuadcopter;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

public class RenderOctacopter extends RenderDrone {

	private static final ResourceLocation woodOctaTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/woodOcta.png");
	private static final ResourceLocation aluminiumOctaTextureLocation = new ResourceLocation(DroneMod.modID + ":textures/entity/aluminiumOcta.png");
	
	private static final ResourceLocation octaModelLocation = new ResourceLocation(DroneMod.modID + ":models/entity/octa.obj");
	private static final IModelCustom octaModel = AdvancedModelLoader.loadModel(octaModelLocation);
	
	public RenderOctacopter() {
		super(octaModel);
	}

	@Override
	public void doRender(Entity var1, double x, double y, double z, float var8, float var9) {
		// Render chests, modules, batteries in appropriate locations for octacopters.
		super.doRender(var1, x, y, z, var8, var9);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		EntityDrone d = null;
		try{
			d = (EntityDrone) var1;
		}catch(ClassCastException e){
			Logger.logOut("Entity is not a drone!");
			e.printStackTrace();
			return null;
		}
		
		if(d instanceof EntityAluminiumMediumOctacopter || d instanceof EntityAluminiumStrongOctacopter) {
			return aluminiumOctaTextureLocation;
		}else if(d instanceof EntityWoodMediumOctacopter || d instanceof EntityWoodStrongOctacopter ){
			return woodOctaTextureLocation;
		}else{
			Logger.logOut("Wrong type of drone, not an Octa! No texture loaded.");
			return null;
		}
	}
}
