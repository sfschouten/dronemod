package sfschouten.dronemod.item.copter;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.TempInventoryType;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.inventory.TempInventory;
import sfschouten.dronemod.item.module.ItemTaskModule;

public class ItemCaneWeakQuadcopter extends ItemDrone {
	
	public ItemCaneWeakQuadcopter() {
		super();
		this.setUnlocalizedName("caneWeakQuadcopterItem");
		entityClass = EntityCaneWeakQuadcopter.class;
		
		ItemStack quadCaneFrame = new ItemStack(DroneMod.quadCaneFrameItem);
		ItemStack weakMotor = new ItemStack(DroneMod.weakMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", " x ", "y y", 'x', quadCaneFrame, 'y', weakMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityCaneWeakQuadcopter newEntity = new EntityCaneWeakQuadcopter(world);

		return (EntityCaneWeakQuadcopter) applySizes(newEntity, droneItemNBTdata);
	}

	@Override
	public int getExpSize(TempInventoryType type) {
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
