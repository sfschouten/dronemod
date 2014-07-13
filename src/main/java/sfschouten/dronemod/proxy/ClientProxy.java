package sfschouten.dronemod.proxy;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraftforge.client.MinecraftForgeClient;
import sfschouten.dronemod.DroneMod;
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
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDroneFX;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.entity.EntityWoodStrongQuadcopter;
import sfschouten.dronemod.item.copter.ItemAluminiumStrongOctacopter;
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
		
		//Items
		//MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumMediumHexacopterItem, new DroneItemRenderer( , ));
		MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumMediumOctacopterItem, new DroneItemRenderer(octa, RenderOctacopter.aluminiumOctaTextureLocation));
		MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumMediumQuadcopterItem, new DroneItemRenderer(quad, RenderQuadcopter.quadCopterTexture));

		//MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumStrongHexacopterItem, new DroneItemRenderer( , ));
		MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumStrongOctacopterItem, new DroneItemRenderer(octa, RenderOctacopter.aluminiumOctaTextureLocation));
		MinecraftForgeClient.registerItemRenderer(DroneMod.aluminiumStrongQuadcopterItem, new DroneItemRenderer(quad, RenderQuadcopter.quadCopterTexture));

		MinecraftForgeClient.registerItemRenderer(DroneMod.caneWeakQuadcopterItem, new DroneItemRenderer(quad, RenderQuadcopter.quadCopterTexture));
		//MinecraftForgeClient.registerItemRenderer(DroneMod.caneWeakHexacopterItem, new DroneItemRenderer( , ));

		MinecraftForgeClient.registerItemRenderer(DroneMod.woodMediumQuadcopterItem, new DroneItemRenderer(quad, RenderQuadcopter.quadCopterTexture));
		//MinecraftForgeClient.registerItemRenderer(DroneMod.woodMediumHexacopterItem, new DroneItemRenderer( , ));
		MinecraftForgeClient.registerItemRenderer(DroneMod.woodMediumOctacopterItem, new DroneItemRenderer(octa, RenderOctacopter.woodOctaTextureLocation));

		MinecraftForgeClient.registerItemRenderer(DroneMod.woodStrongQuadcopterItem, new DroneItemRenderer(quad, RenderQuadcopter.quadCopterTexture));
		//MinecraftForgeClient.registerItemRenderer(DroneMod.woodStrongHexacopterItem, new DroneItemRenderer( , ));
		MinecraftForgeClient.registerItemRenderer(DroneMod.woodStrongOctacopterItem, new DroneItemRenderer(octa, RenderOctacopter.woodOctaTextureLocation));
	}
}