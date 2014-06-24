package sfschouten.dronemod.item.module;

import cpw.mods.fml.common.registry.LanguageRegistry;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import net.minecraft.block.Block;
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
		int yID = d.worldObj.getBlockId((int)Math.floor(d.posX), (int)Math.floor(d.posY-1), (int)Math.floor(d.posZ));
		int y1ID = d.worldObj.getBlockId((int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ));
		
		if(yID == Block.tilledField.blockID && y1ID == 0){
			int slot = d.getFirstSlotForItemID(Item.seeds.itemID);
			if(slot > -1){
				d.getActualInventory().decrStackSize(slot, 1);
				d.worldObj.setBlock((int)Math.floor(d.posX), (int)Math.floor(d.posY), (int)Math.floor(d.posZ), Block.crops.blockID);
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
		// TODO Auto-generated method stub
		
	}
	
}
