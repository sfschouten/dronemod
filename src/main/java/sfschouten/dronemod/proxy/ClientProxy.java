package sfschouten.dronemod.proxy;

import sfschouten.dronemod.client.model.ModelCaneOctaCopter;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.client.renderer.block.TileEntityMillerRenderer;
import sfschouten.dronemod.client.renderer.entity.RenderOctacopter;
import sfschouten.dronemod.client.renderer.entity.RenderQuadcopter;
import sfschouten.dronemod.client.renderer.item.DroneItemRenderer;
import sfschouten.dronemod.entity.EntityAluminiumMediumOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDroneFX;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.entity.EntityWoodStrongQuadcopter;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.registry.RenderRegistry;
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
		RenderRegistry.registerDroneEntityRender(EntityAluminiumMediumQuadcopter.class, quadRender);
		RenderRegistry.registerDroneEntityRender(EntityAluminiumStrongQuadcopter.class, quadRender);
		RenderRegistry.registerDroneEntityRender(EntityWoodMediumQuadcopter.class, quadRender);
		RenderRegistry.registerDroneEntityRender(EntityWoodStrongQuadcopter.class, quadRender);
		RenderRegistry.registerDroneEntityRender(EntityCaneWeakQuadcopter.class, quadRender);
		
		//Hexacopters

		
		//Octacopters
		RenderRegistry.registerDroneEntityRender(EntityAluminiumMediumOctacopter.class, octaRender);
		RenderRegistry.registerDroneEntityRender(EntityAluminiumStrongOctacopter.class, octaRender);
		RenderRegistry.registerDroneEntityRender(EntityWoodMediumOctacopter.class, octaRender);
		RenderRegistry.registerDroneEntityRender(EntityWoodStrongOctacopter.class, octaRender);

		//Extra
		RenderRegistry.registerEntityRender(EntityDroneFX.class, new RenderQuadcopter(quadSmall, 0.0F));
		
		//Blocks
		RenderRegistry.registerTileEntityRender(TileEntityMiller.class, new TileEntityMillerRenderer());
		
		//Items
		//MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumMediumHexacopterItem, new DroneItemRenderer( , ));
		RenderRegistry.registerItemRender(MMDItems.aluminiumMediumOctacopterItem, new DroneItemRenderer(octa, ModelOctaCopter.aluminiumOctaTextureLocation));
		RenderRegistry.registerItemRender(MMDItems.aluminiumMediumQuadcopterItem, new DroneItemRenderer(quad, ModelQuadcopter.quadCopterTexture));

		//MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumStrongHexacopterItem, new DroneItemRenderer( , ));
		RenderRegistry.registerItemRender(MMDItems.aluminiumStrongOctacopterItem, new DroneItemRenderer(octa, ModelOctaCopter.aluminiumOctaTextureLocation));
		RenderRegistry.registerItemRender(MMDItems.aluminiumStrongQuadcopterItem, new DroneItemRenderer(quad, ModelQuadcopter.quadCopterTexture));

		RenderRegistry.registerItemRender(MMDItems.caneWeakQuadcopterItem, new DroneItemRenderer(quad, ModelQuadcopter.quadCopterTexture));
		//MinecraftForgeClient.registerItemRenderer(DroneMod.caneWeakHexacopterItem, new DroneItemRenderer( , ));

		RenderRegistry.registerItemRender(MMDItems.woodMediumQuadcopterItem, new DroneItemRenderer(quad, ModelQuadcopter.quadCopterTexture));
		//MinecraftForgeClient.registerItemRenderer(DroneMod.woodMediumHexacopterItem, new DroneItemRenderer( , ));
		RenderRegistry.registerItemRender(MMDItems.woodMediumOctacopterItem, new DroneItemRenderer(octa, ModelOctaCopter.woodOctaTextureLocation));

		RenderRegistry.registerItemRender(MMDItems.woodStrongQuadcopterItem, new DroneItemRenderer(quad, ModelQuadcopter.quadCopterTexture));
		//MinecraftForgeClient.registerItemRenderer(DroneMod.woodStrongHexacopterItem, new DroneItemRenderer( , ));
		RenderRegistry.registerItemRender(MMDItems.woodStrongOctacopterItem, new DroneItemRenderer(octa, ModelOctaCopter.woodOctaTextureLocation));
	
		RenderRegistry.registerAll();
	}
}