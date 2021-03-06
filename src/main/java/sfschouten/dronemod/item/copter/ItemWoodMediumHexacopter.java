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
import sfschouten.dronemod.entity.EntityWoodMediumHexacopter;

public class ItemWoodMediumHexacopter extends ItemDrone {
	
	public ItemWoodMediumHexacopter() {
		super();
		this.setUnlocalizedName("woodMediumHexacopterItem");
		entityClass = EntityCaneWeakHexacopter.class;
		
		ItemStack hexaCaneFrame = new ItemStack(DroneMod.hexaCaneFrameItem);
		ItemStack mediumMotor = new ItemStack(DroneMod.mediumMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", "yxy", "y y", 'x', hexaCaneFrame, 'y', mediumMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		//TODO create new entity and make it here.
		EntityWoodMediumHexacopter newEntity = new EntityWoodMediumHexacopter(world);

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
