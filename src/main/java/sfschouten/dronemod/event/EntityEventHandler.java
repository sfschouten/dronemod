package sfschouten.dronemod.event;

import net.minecraftforge.event.entity.EntityEvent;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.util.Logger;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler {

	@SubscribeEvent
	public void onEntityCrossChunkBorder(EntityEvent.EnteringChunk e){
		//If entity is a drone and has a chunkloadingticket, then make it forceload the chunks.
		if(e.entity instanceof EntityDrone){
			EntityDrone drone = (EntityDrone) e.entity;
			if(drone.getChunkloadingTicket() != null){
				Logger.info("chunkborderevent");
				drone.forcedChunkChanged(e.newChunkX, e.newChunkZ, e.oldChunkX, e.oldChunkZ);
			}
		}
	}
}
