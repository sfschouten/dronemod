package sfschouten.dronemod.entity;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.item.copter.ItemCaneWeakQuadcopter;
import sfschouten.dronemod.item.copter.ItemDrone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityAluminiumMediumQuadcopter extends EntityDrone {
	
	private final int energyUse = 4 * 15 + 20;
	
	public EntityAluminiumMediumQuadcopter(World par1World) {
		super(par1World);
	}
	
	public ItemDrone getItem(){
		return DroneMod.caneWeakQuadcopterItem;
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
