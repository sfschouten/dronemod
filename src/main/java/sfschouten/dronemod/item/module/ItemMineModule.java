package sfschouten.dronemod.item.module;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.tileentity.TileEntityMarker;

public class ItemMineModule extends ItemTaskModule {

	public ItemMineModule(){
		super();
		this.hasDepth = true;
		this.setUnlocalizedName("mineModuleItem");
		//this.setTextureName("dronemod:basic_hoe_icon");
	}
	
	@Override
	public DroneTaskResult performTask(EntityDrone d, DroneTaskSubject s) {
		if(d.worldObj.func_147480_a((int)Math.floor(d.posX), (int)Math.floor(d.posY-1), (int)Math.floor(d.posZ), true)){
			return DroneTaskResult.success;
		}
		else{
			return DroneTaskResult.wrongEnvironment;
		}
	}	

	@Override
	public void applyProperties(EntityDrone e) {
		// TODO Auto-generated method stub
	}
}
