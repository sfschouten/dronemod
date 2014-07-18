package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWoodStrongHexacopter extends ItemDrone {

	public ItemWoodStrongHexacopter() {
		super();
		this.setUnlocalizedName("woodStrongHexacopterItem");
		entityClass = EntityWoodStrongOctacopter.class;
		modelClass = null; //TODO this
		
		ItemStack hexaWoodFrame = new ItemStack(MMDItems.hexaWoodFrameItem);
		ItemStack strongMotor = new ItemStack(MMDItems.strongMotorItem);

		GameRegistry.addRecipe(new ItemStack(this), "y y", "yxy", "y y", 'x', hexaWoodFrame, 'y', strongMotor);
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
