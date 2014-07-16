package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWoodStrongOctacopter extends ItemDrone {

	public ItemWoodStrongOctacopter() {
		super();
		this.setUnlocalizedName("woodStrongOctacopterItem");
		entityClass = EntityWoodStrongOctacopter.class;
		modelClass = ModelOctaCopter.class;
		
		ItemStack octaWoodFrame = new ItemStack(MMDItems.octaWoodFrameItem);
		ItemStack strongMotor = new ItemStack(MMDItems.strongMotorItem);

		GameRegistry.addRecipe(new ItemStack(this), "yyy", "yxy", "yyy", 'x', octaWoodFrame, 'y', strongMotor);
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
			result = 3;
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
