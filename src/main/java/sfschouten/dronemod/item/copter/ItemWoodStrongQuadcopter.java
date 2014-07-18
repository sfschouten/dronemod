package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWoodStrongQuadcopter extends ItemDrone {

	public ItemWoodStrongQuadcopter() {
		super();
		this.setUnlocalizedName("woodStrongQuadcopterItem");
		entityClass = EntityWoodStrongOctacopter.class;
		modelClass = ModelQuadcopter.class;
		
		ItemStack quadWoodFrame = new ItemStack(MMDItems.quadWoodFrameItem);
		ItemStack mediumMotor = new ItemStack(MMDItems.strongMotorItem);

		GameRegistry.addRecipe(new ItemStack(this), "y y", " x ", "y y", 'x', quadWoodFrame, 'y', mediumMotor);
	}

	@Override
	public int getExpSize(InventoryType type) {
		int result = 0;
		switch (type) {
		case battery:
			result = 1;
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
