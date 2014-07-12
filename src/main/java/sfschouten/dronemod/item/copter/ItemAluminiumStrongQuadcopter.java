package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.entity.EntityAluminiumStrongOctacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemAluminiumStrongQuadcopter extends ItemDrone {

	public ItemAluminiumStrongQuadcopter() {
		super();
		this.setUnlocalizedName("aluminiumStrongQuadcopterItem");
		entityClass = EntityAluminiumStrongOctacopter.class;

		ItemStack quadAluminiumFrame = new ItemStack(DroneMod.quadAluminiumFrameItem);
		ItemStack strongMotor = new ItemStack(DroneMod.strongMotorItem);

		GameRegistry.addRecipe(new ItemStack(this), "y y", " x ", "y y", 'x', quadAluminiumFrame, 'y', strongMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityAluminiumStrongOctacopter newEntity = new EntityAluminiumStrongOctacopter(world);
		return applySizes(newEntity, droneItemNBTdata);
	}

	@Override
	public int getExpSize(InventoryType type) {
		int result = 0;
		switch (type) {
		case battery:
			result = 1;
			break;
		case chest:
			result = 0;
			break;
		case free:
			result = 2;
			break;
		case module:
			result = 1;
			break;
		}
		return result;
	}
}
