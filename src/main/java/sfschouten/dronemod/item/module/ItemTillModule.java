package sfschouten.dronemod.item.module;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.reference.General;
import sfschouten.dronemod.tileentity.TileEntityMarker;

public class ItemTillModule extends ItemBasicTaskModule {

	public ItemTillModule(){
		super();
		this.setUnlocalizedName("tillModuleItem");
		this.setTextureName(General.modID + ":basic_hoe_icon");
	}
	
	@Override
	public DroneTaskResult performTask(EntityDrone d, DroneTaskSubject s) {
		DroneTaskResult result;
		TileEntityMarker m = d.getCurrentWork();
		Block yBlock = d.worldObj.getBlock((int)Math.floor(d.posX), (int)Math.floor(d.posY-1), (int)Math.floor(d.posZ));
		
		if (yBlock == Blocks.dirt || yBlock == Blocks.grass){
			d.worldObj.setBlock((int)Math.floor(d.posX), (int)Math.floor(d.posY-1), (int)Math.floor(d.posZ), Blocks.farmland);
			result = DroneTaskResult.success;
		}else{
			result = DroneTaskResult.wrongEnvironment;
		}
		
		return result;
	}

	@Override
	public EntityDrone initEntity(EntityDrone drone) {
		// TODO Auto-generated method stub
		return null;
	}
}
