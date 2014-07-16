package sfschouten.dronemod.item.module;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.init.MMDCreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public abstract class ItemTaskModule extends Item {
	boolean hasDepth = false;
	
	public ItemTaskModule() {
		super();
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
        this.setMaxStackSize(1);
	}

	protected Item restockItem;
	protected int restockItemDamageValue = 0;
	
	public abstract DroneTaskResult performTask(EntityDrone d, DroneTaskSubject s);

	public Item getRestockItem() {
		return restockItem;
	}

	public int getRestockItemDamageValue() {
		return restockItemDamageValue;
	} 
	
	public abstract void applyProperties(EntityDrone e);
	
	public boolean hasDepth(){
		return hasDepth; 
	}
}
