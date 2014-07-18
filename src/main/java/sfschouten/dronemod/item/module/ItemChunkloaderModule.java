package sfschouten.dronemod.item.module;

import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import net.minecraftforge.common.ForgeChunkManager.Type;
import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.util.Logger;

public class ItemChunkloaderModule extends ItemPassiveModule {

	public ItemChunkloaderModule(){
		super();
		this.setUnlocalizedName("chunkLoaderModuleItem");
	}
	
	@Override
	public EntityDrone initEntity(EntityDrone drone) {
		Ticket droneTicket = ForgeChunkManager.requestTicket(ModularMulticopterDrones.instance, drone.worldObj, Type.ENTITY);
		drone.setChunkloadingTicket(droneTicket);
		droneTicket.bindEntity(drone);
		
		for(int x = -1; x < 2; x++){
			for(int z = -1; z < 2; z++){
				Chunk c2 = drone.worldObj.getChunkFromBlockCoords((int)drone.posX, (int)drone.posZ);
				ChunkCoordIntPair ccip = new ChunkCoordIntPair(c2.xPosition+x, c2.zPosition+z);
				Logger.info("InitialLoading: "+ccip.chunkXPos+","+ccip.chunkZPos);
				ForgeChunkManager.forceChunk(droneTicket, ccip);
			}
		}
		
		return drone;
	}
}
