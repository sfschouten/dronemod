package sfschouten.dronemod.entity;

import net.minecraft.world.World;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.item.copter.ItemDrone;

public class EntityAluminiumStrongQuadcopter extends EntityDrone {
	
	private final int energyUse = 4 * 20 + 20;
	
	public EntityAluminiumStrongQuadcopter(World par1World) {
		super(par1World);
	}
	
	public ItemDrone getItem(){
		return MMDItems.aluminiumStrongQuadcopterItem;
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