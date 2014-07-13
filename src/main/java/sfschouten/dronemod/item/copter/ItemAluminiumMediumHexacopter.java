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
import sfschouten.dronemod.entity.EntityAluminiumMediumHexacopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityWoodMediumHexacopter;
import sfschouten.dronemod.inventory.InventoryType;

public class ItemAluminiumMediumHexacopter extends ItemDrone {
	
	public ItemAluminiumMediumHexacopter() {
		super();
		this.setUnlocalizedName("aluminiumMediumHexacopterItem");
		entityClass = EntityAluminiumMediumHexacopter.class;
		modelClass = null; //TODO this
		
		ItemStack hexaAluminiumFrame = new ItemStack(DroneMod.hexaAluminiumFrameItem);
		ItemStack mediumMotor = new ItemStack(DroneMod.mediumMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", "yxy", "y y", 'x', hexaAluminiumFrame, 'y', mediumMotor);
	}

	@Override
	public EntityDrone getNewEntity(World world, NBTTagCompound droneItemNBTdata) {
		EntityAluminiumMediumHexacopter newEntity = new EntityAluminiumMediumHexacopter(world);
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
			result = 2;
			break;
		case module:
			result = 1;
			break;
		}
		return result;
	}
}
