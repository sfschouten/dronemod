package sfschouten.dronemod.item.module;

import cpw.mods.fml.common.registry.LanguageRegistry;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class ItemPlantModule extends ItemTaskModule {

	public ItemPlantModule( ){
		super();
		this.restockItem = Items.wheat_seeds;
		this.setUnlocalizedName("plantModuleItem");
		this.setTextureName("dronemod:basic_planting_icon");
	}
	
	@Override
	public DroneTaskResult performTask(EntityDrone d, DroneTaskSubject s) {
		DroneTaskResult result;
		TileEntityMarker m = d.getCurrentWork();
		Block yBlock = d.worldObj.getBlock((int)Math.floor(d.posX), (int)Math.floor(d.posY-1), (int)Math.floor(d.posZ));
		Block y1Block = d.worldObj.getBlock((int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ));
		
		if(yBlock == Blocks.farmland && y1Block == Blocks.air){
			int slot = d.getFirstSlotForItem(Items.wheat_seeds);
			if(slot > -1){
				d.getActualInventory().decrStackSize(slot, 1);
				d.worldObj.setBlock((int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ), Blocks.wheat);
				result = DroneTaskResult.success;
			}else{
				result = DroneTaskResult.resourcelow;
			}
		}else{
			result = DroneTaskResult.wrongEnvironment;
		}
		
		return result;
	}

	@Override
	public void applyProperties(EntityDrone e) {

	}
}
