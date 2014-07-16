package sfschouten.dronemod.item.copter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemCaneWeakQuadcopter extends ItemDrone {
	
	public ItemCaneWeakQuadcopter() {
		super();
		this.setUnlocalizedName("caneWeakQuadcopterItem");
		entityClass = EntityCaneWeakQuadcopter.class;
		modelClass = ModelQuadcopter.class;
		
		ItemStack quadCaneFrame = new ItemStack(MMDItems.quadCaneFrameItem);
		ItemStack weakMotor = new ItemStack(MMDItems.weakMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", " x ", "y y", 'x', quadCaneFrame, 'y', weakMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityCaneWeakQuadcopter newEntity = new EntityCaneWeakQuadcopter(world);

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
			result = 0;
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
