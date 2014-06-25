package sfschouten.dronemod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraftforge.client.MinecraftForgeClient;
import sfschouten.dronemod.CommonProxy;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.client.renderer.entity.RenderQuadcopter;
import sfschouten.dronemod.client.renderer.entity.RenderQuadcopterFX;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDroneFX;

public class ClientProxy extends CommonProxy {  
    @Override
    public void registerRenderers() {
    	ModelQuadcopter quad = new ModelQuadcopter(1);
    	ModelQuadcopter quadSmall = new ModelQuadcopter(0.3F);
    	//ModelHexacopter hexa = new ModelHexacopter();
    	//ModelOctacopter octa = new ModelOctacopter();
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityCaneWeakQuadcopter.class, new RenderQuadcopter(quad, 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(EntityDroneFX.class, new RenderQuadcopter(quadSmall, 0.5F));
    	/*
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityCaneWeakQuadcopter.class, new RenderCaneWeakQuadcopter(quad, 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityCaneWeakHexacopter.class, new RenderCaneWeakHexacopter(hexa, 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 * RenderingRegistry.registerEntityRenderingHandler(EntityQuadcopter.class, new RenderQuadcopter(new ModelQuadcopter(), 0.5F));
    	 */
    }
}