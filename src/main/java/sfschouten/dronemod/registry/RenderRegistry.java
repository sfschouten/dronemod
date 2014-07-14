package sfschouten.dronemod.registry;

import java.util.HashMap;
import java.util.Map.Entry;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.client.MinecraftForgeClient;
import sfschouten.dronemod.client.renderer.entity.RenderCopter;
import sfschouten.dronemod.client.renderer.item.DroneItemRenderer;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.item.copter.ItemDrone;

/**
 * Class to make it easier to later find out what renderer is rendering a entity/tileentity/item. 
 * @author Stefan
 */
public class RenderRegistry {
	private static HashMap<Class<? extends Entity>, RenderLiving> entityRenderRegistry = new HashMap<Class<? extends Entity>, RenderLiving>();
	private static HashMap<Class<? extends EntityDrone>, RenderCopter> droneEntityRenderRegistry = new HashMap<Class<? extends EntityDrone>, RenderCopter>();
	private static HashMap<Class<? extends TileEntity>, TileEntitySpecialRenderer> tileEntityRenderRegistry = new HashMap<Class<? extends TileEntity>, TileEntitySpecialRenderer>();
	private static HashMap<ItemDrone, DroneItemRenderer> itemRenderRegistry = new HashMap<ItemDrone, DroneItemRenderer>();
	
	public static void registerItemRender(ItemDrone drone, DroneItemRenderer renderer){
		itemRenderRegistry.put(drone, renderer);
	}
	
	public static void registerTileEntityRender(Class<? extends TileEntity> tileEntityClass, TileEntitySpecialRenderer renderer){
		tileEntityRenderRegistry.put(tileEntityClass, renderer);
	}
	
	public static void registerDroneEntityRender(Class<? extends EntityDrone> drone, RenderCopter render){
		droneEntityRenderRegistry.put(drone, render);
	}
	
	public static void registerEntityRender(Class<? extends Entity> drone, RenderLiving render){
		entityRenderRegistry.put(drone, render);
	}
	
	public static RenderCopter getEntityRenderer(Class<? extends EntityDrone> entityClass){
		return droneEntityRenderRegistry.get(entityClass);
	}
	
	public static void registerAll(){
		for(Entry<Class<? extends Entity>, ? extends RenderLiving> registered : entityRenderRegistry.entrySet()){
			RenderingRegistry.registerEntityRenderingHandler(registered.getKey(), registered.getValue());
		}
		for(Entry<Class<? extends EntityDrone>, ? extends RenderCopter> registered : droneEntityRenderRegistry.entrySet()){
			RenderingRegistry.registerEntityRenderingHandler(registered.getKey(), registered.getValue());
		}
		for(Entry<Class<? extends TileEntity>, ? extends TileEntitySpecialRenderer> registered : tileEntityRenderRegistry.entrySet()){
			ClientRegistry.bindTileEntitySpecialRenderer(registered.getKey(), registered.getValue());
		}
		for(Entry<ItemDrone, DroneItemRenderer> registered : itemRenderRegistry.entrySet()){
			MinecraftForgeClient.registerItemRenderer(registered.getKey(), registered.getValue());
		}
	}
}
