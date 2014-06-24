package sfschouten.dronemod.item.module;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.tileentity.TileEntityMarker;

public class ItemHarvestModule extends ItemTaskModule {

	public ItemHarvestModule( ){
		super();
		this.setUnlocalizedName("harvestModuleItem");
		this.setTextureName("dronemod:basic_harvest_icon");
	}
	
	@Override
	public DroneTaskResult performTask(EntityDrone d, DroneTaskSubject s) {
		System.out.println("harvest");
		DroneTaskResult result;
		TileEntityMarker m = d.getCurrentWork();
		int y1ID = d.worldObj.getBlockId((int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ));
		int y1Meta = d.worldObj.getBlockMetadata((int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ));
		
		if(y1ID == Blocks.wheat.blockID && y1Meta == 7){
			d.worldObj.destroyBlock((int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ), true);
			result = DroneTaskResult.success;
		}else{
			result = DroneTaskResult.wrongEnvironment;
		}
		
		return result;
	}
	@Override
	public void applyProperties(EntityDrone e) {
		e.setCanPickUpLoot(true);
	}
}
