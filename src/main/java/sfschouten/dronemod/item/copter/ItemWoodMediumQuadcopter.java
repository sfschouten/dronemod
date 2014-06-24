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
import sfschouten.dronemod.TempInventoryType;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;

public class ItemWoodMediumQuadcopter extends ItemDrone {
	
	public ItemWoodMediumQuadcopter() {
		super();
		this.setUnlocalizedName("woodMediumQuadcopterItem");
		entityClass = EntityCaneWeakHexacopter.class;
		
		ItemStack quadCaneFrame = new ItemStack(DroneMod.quadCaneFrameItem);
		ItemStack weakMotor = new ItemStack(DroneMod.weakMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", " x ", "y y", 'x', quadCaneFrame, 'y', weakMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		//TODO create new entity and make it here.
		EntityWoodMediumQuadcopter newEntity = new EntityWoodMediumQuadcopter(world);

		applySizes(newEntity, droneItemNBTdata);
		
		return newEntity;
	}
	
	@Override
	public int getExpSize(TempInventoryType type) {
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
