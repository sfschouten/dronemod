package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumHexacopter;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemWoodMediumHexacopter extends ItemDrone {
	
	public ItemWoodMediumHexacopter() {
		super();
		this.setUnlocalizedName("woodMediumHexacopterItem");
		entityClass = EntityWoodMediumHexacopter.class;
		modelClass = null; //TODO this
		
		ItemStack hexaWoodFrame = new ItemStack(MMDItems.hexaWoodFrameItem);
		ItemStack mediumMotor = new ItemStack(MMDItems.mediumMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", "yxy", "y y", 'x', hexaWoodFrame, 'y', mediumMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityWoodMediumHexacopter newEntity = new EntityWoodMediumHexacopter(world);
		return applySizes(newEntity, droneItemNBTdata);
	}
	
	@Override
	public int getExpSize(InventoryType type) {
		int result = 0;
		switch(type){
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
