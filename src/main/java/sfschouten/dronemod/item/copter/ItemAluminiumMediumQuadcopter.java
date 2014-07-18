package sfschouten.dronemod.item.copter;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.inventory.InventoryType;

public class ItemAluminiumMediumQuadcopter extends ItemDrone {
	
	public ItemAluminiumMediumQuadcopter() {
		super();
		this.setUnlocalizedName("aluminiumMediumQuadcopterItem");
		entityClass = EntityAluminiumMediumQuadcopter.class;
		modelClass = ModelQuadcopter.class;
		
		ItemStack quadAluminiumFrame = new ItemStack(MMDItems.quadAluminiumFrameItem);
		ItemStack mediumMotor = new ItemStack(MMDItems.mediumMotorItem);
		
		GameRegistry.addRecipe(new ItemStack(this), "y y", " x ", "y y", 'x', quadAluminiumFrame, 'y', mediumMotor);
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
			result = 1;
			break;
		case module:
			result = 1;
			break;
		}
		return result;
	}
}
