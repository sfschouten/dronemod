package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.TempInventoryType;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWoodMediumOctacopter extends ItemDrone {
	
	public ItemWoodMediumOctacopter() {
		super();
		this.setUnlocalizedName("woodMediumOctacopterItem");
		entityClass = EntityWoodMediumOctacopter.class;
		
		ItemStack octaWoodFrame = new ItemStack(DroneMod.octaWoodFrameItem);
		ItemStack mediumMotor = new ItemStack(DroneMod.mediumMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "yyy", "yxy", "yyy", 'x', octaWoodFrame, 'y', mediumMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityWoodMediumOctacopter newEntity = new EntityWoodMediumOctacopter(world);
		return applySizes(newEntity, droneItemNBTdata);
	}
	
	@Override
	public int getExpSize(TempInventoryType type) {
		int result = 0;
		switch(type){
		case battery:
			result = 2;
			break;
		case chest:
			result = 2;
			break;
		case free:
			result = 0;
			break;
		case module:
			result = 1;
			break;
		}
		return result;
	}
}