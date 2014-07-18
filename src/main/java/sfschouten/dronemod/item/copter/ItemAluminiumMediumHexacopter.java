package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.entity.EntityAluminiumMediumHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemAluminiumMediumHexacopter extends ItemDrone {
	
	public ItemAluminiumMediumHexacopter() {
		super();
		this.setUnlocalizedName("aluminiumMediumHexacopterItem");
		entityClass = EntityAluminiumMediumHexacopter.class;
		modelClass = null; //TODO this
		
		ItemStack hexaAluminiumFrame = new ItemStack(MMDItems.hexaAluminiumFrameItem);
		ItemStack mediumMotor = new ItemStack(MMDItems.mediumMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", "yxy", "y y", 'x', hexaAluminiumFrame, 'y', mediumMotor);
	}
	
	@Override
	public int getExpSize(InventoryType type) {
		int result = 0;
		switch(type){
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
