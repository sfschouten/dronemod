package sfschouten.dronemod.client;

import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.particle.EntityFlameFX;
import net.minecraft.client.particle.EntityRainFX;
import net.minecraftforge.client.MinecraftForgeClient;
import sfschouten.dronemod.CommonProxy;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.client.renderer.entity.RenderQuadcopterTemp;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDroneFX;

public class ClientProxy extends CommonProxy {  
    @Override
    public void registerRenderers() {
    	ModelOctaCopter quad = new ModelOctaCopter(1);
    	ModelOctaCopter quadSmall = new ModelOctaCopter(0.3F);
    	
    	RenderingRegistry.registerEntityRenderingHandler(EntityCaneWeakQuadcopter.class, new RenderQuadcopterTemp(quad, 0.5F));
    	RenderingRegistry.registerEntityRenderingHandler(EntityDroneFX.class, new RenderQuadcopterTemp(quadSmall, 0.5F));
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