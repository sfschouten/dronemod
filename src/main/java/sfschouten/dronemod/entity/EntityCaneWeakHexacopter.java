package sfschouten.dronemod.entity;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.item.copter.ItemCaneWeakQuadcopter;
import sfschouten.dronemod.item.copter.ItemDrone;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityCaneWeakHexacopter extends EntityDrone {
	
	private final int energyUse = 6 * 10 + 10;
	
	public EntityCaneWeakHexacopter(World par1World) {
		super(par1World);
	}
	
	public ItemDrone getItem(){
		return DroneMod.caneWeakHexacopterItem;
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
