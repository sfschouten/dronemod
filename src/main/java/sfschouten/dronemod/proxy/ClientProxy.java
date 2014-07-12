package sfschouten.dronemod.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraftforge.client.MinecraftForgeClient;
import sfschouten.dronemod.client.model.ModelCaneOctaCopter;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.client.renderer.block.TileEntityMillerRenderer;
import sfschouten.dronemod.client.renderer.entity.RenderOctacopter;
import sfschouten.dronemod.client.renderer.entity.RenderQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongQuadcopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDroneFX;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.entity.EntityWoodStrongQuadcopter;
import sfschouten.dronemod.tileentity.TileEntityMiller;

public class ClientProxy extends CommonProxy {

	@Override
	public void registerRenderers() {
		//Models
		ModelQuadcopter quad = new ModelQuadcopter(1);
		ModelQuadcopter quadSmall = new ModelQuadcopter(0.3F);
		ModelOctaCopter octa = new ModelOctaCopter(1);
		ModelCaneOctaCopter caneOcta = new ModelCaneOctaCopter(1);

		//Renderers
		RenderQuadcopter quadRender = new RenderQuadcopter(quad, 0.5F);
		RenderOctacopter octaRender = new RenderOctacopter(octa, 0.5F);

		//Quadcopters
		RenderingRegistry.registerEntityRenderingHandler(EntityAluminiumMediumQuadcopter.class, quadRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityAluminiumStrongQuadcopter.class, quadRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodMediumQuadcopter.class, quadRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodStrongQuadcopter.class, quadRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityCaneWeakQuadcopter.class, quadRender);
		
		//Hexacopters

		//Octacopters
		RenderingRegistry.registerEntityRenderingHandler(EntityAluminiumMediumOctacopter.class, octaRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityAluminiumStrongOctacopter.class, octaRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodMediumOctacopter.class, octaRender);
		RenderingRegistry.registerEntityRenderingHandler(EntityWoodStrongOctacopter.class, octaRender);

		//Extra
		RenderingRegistry.registerEntityRenderingHandler(EntityDroneFX.class, new RenderQuadcopter(quadSmall, 0.0F));
		
		//Blocks
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityMiller.class, new TileEntityMillerRenderer());
	}
}