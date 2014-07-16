package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongOctacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemAluminiumStrongOctacopter extends ItemDrone {

	public ItemAluminiumStrongOctacopter() {
		super();
		this.setUnlocalizedName("aluminiumStrongOctacopterItem");
		entityClass = EntityAluminiumStrongOctacopter.class;
		modelClass = ModelOctaCopter.class;
		
		ItemStack octaAluminiumFrame = new ItemStack(MMDItems.octaAluminiumFrameItem);
		ItemStack strongMotor = new ItemStack(MMDItems.strongMotorItem);

		GameRegistry.addRecipe(new ItemStack(this), "yyy", "yxy", "yyy", 'x', octaAluminiumFrame, 'y', strongMotor);
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
			result = 4;
			break;
		case module:
			result = 1;
			break;
		}
		return result;
	}
}
