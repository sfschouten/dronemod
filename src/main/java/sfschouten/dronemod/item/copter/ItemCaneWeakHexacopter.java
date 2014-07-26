package sfschouten.dronemod.item.copter;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemCaneWeakHexacopter extends ItemDrone {
	
	public ItemCaneWeakHexacopter() {
		super();
		this.setUnlocalizedName("caneWeakHexacopterItem");
		entityClass = EntityCaneWeakHexacopter.class;
		modelClass = null; //TODO this
		
		ItemStack hexaCaneFrame = new ItemStack(MMDItems.hexaCaneFrameItem);
		ItemStack weakMotor = new ItemStack(MMDItems.weakMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", "yxy", "y y", 'x', hexaCaneFrame, 'y', weakMotor);
	}

	@Override
	public int getExpSize(InventoryType type) {
		int result = 0;
		switch(type){
		case battery:
			result = 1;
			break;
		case chest:
			result = 1;
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
