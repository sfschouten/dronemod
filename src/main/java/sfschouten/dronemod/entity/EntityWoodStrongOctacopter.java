package sfschouten.dronemod.entity;

import net.minecraft.world.World;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.item.copter.ItemDrone;

public class EntityWoodStrongOctacopter extends EntityDrone {
	
	private final int energyUse = 8 * 20 + 30;
	
	public EntityWoodStrongOctacopter(World par1World) {
		super(par1World);
	}
	
	public ItemDrone getItem(){
		return MMDItems.woodStrongOctacopterItem;
	}

	@Override
	public int getRestockSpeed() {
		return 2;
	}
	
	/**
	 * @return energy use per meter in RF
	 */
	@Override
	public int getEnergyUse() {
		return energyUse;
	}
}
