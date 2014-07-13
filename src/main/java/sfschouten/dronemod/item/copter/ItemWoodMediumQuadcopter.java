package sfschouten.dronemod.item.copter;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.inventory.InventoryType;

public class ItemWoodMediumQuadcopter extends ItemDrone {
	
	public ItemWoodMediumQuadcopter() {
		super();
		this.setUnlocalizedName("woodMediumQuadcopterItem");
		entityClass = EntityWoodMediumQuadcopter.class;
		modelClass = ModelQuadcopter.class;
		
		ItemStack quadWoodFrame = new ItemStack(DroneMod.quadWoodFrameItem);
		ItemStack mediumMotor = new ItemStack(DroneMod.mediumMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", " x ", "y y", 'x', quadWoodFrame, 'y', mediumMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityWoodMediumQuadcopter newEntity = new EntityWoodMediumQuadcopter(world);
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
