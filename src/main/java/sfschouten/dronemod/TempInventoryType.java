package sfschouten.dronemod;

import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import sfschouten.dronemod.item.module.ItemTaskModule;
import cofh.api.energy.IEnergyStorage;

public enum TempInventoryType {
	battery, module, chest, free;
	
	private Class item;
	
	static {
		battery.item = IEnergyStorage.class;
		module.item = ItemTaskModule.class;
		chest.item = Blocks.chest.getClass();
	}
	
	public boolean isValidFor(Item i){
		if(this == free && (i.getClass() == battery.item || i.getClass() == module.item || i.getClass() == chest.item)){
			return true;
		}
		return i.getClass().equals(item);
	}
}
