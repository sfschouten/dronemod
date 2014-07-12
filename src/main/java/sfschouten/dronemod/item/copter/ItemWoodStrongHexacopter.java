package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWoodStrongHexacopter extends ItemDrone {

	public ItemWoodStrongHexacopter() {
		super();
		this.setUnlocalizedName("woodStrongHexacopterItem");
		entityClass = EntityWoodStrongOctacopter.class;

		ItemStack octaWoodFrame = new ItemStack(DroneMod.octaWoodFrameItem);
		ItemStack strongMotor = new ItemStack(DroneMod.strongMotorItem);

		GameRegistry.addRecipe(new ItemStack(this), "y y", "yxy", "y y", 'x', octaWoodFrame, 'y', strongMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityWoodStrongOctacopter newEntity = new EntityWoodStrongOctacopter(world);
		return applySizes(newEntity, droneItemNBTdata);
	}

	@Override
	public int getExpSize(InventoryType type) {
		int result = 0;
		switch (type) {
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
