package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumOctacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemAluminiumMediumOctacopter extends ItemDrone {

	public ItemAluminiumMediumOctacopter() {
		super();
		this.setUnlocalizedName("aluminiumMediumOctacopterItem");
		entityClass = EntityAluminiumMediumOctacopter.class;
		modelClass = ModelOctaCopter.class;
		
		ItemStack octaWoodFrame = new ItemStack(MMDItems.octaAluminiumFrameItem);
		ItemStack mediumMotor = new ItemStack(MMDItems.mediumMotorItem);

		GameRegistry.addRecipe(new ItemStack(this), "yyy", "yxy", "yyy", 'x', octaWoodFrame, 'y', mediumMotor);
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
			result = 3;
			break;
		case module:
			result = 1;
			break;
		}
		return result;
	}

	@Override
	public Class getModelClass() {
		return ModelOctaCopter.class;
	}
}
