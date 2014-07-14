package sfschouten.dronemod.inventory;

import net.minecraft.client.model.ModelBase;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import sfschouten.dronemod.client.model.ModelBattery;
import sfschouten.dronemod.client.model.ModelExpansion;
import sfschouten.dronemod.item.module.ItemTaskModule;
import cofh.api.energy.IEnergyStorage;

public enum InventoryType {
	battery, module, chest, free;
	
	private Class item;
	private Class model;
	
	static {
		battery.item = IEnergyStorage.class;
		module.item = ItemTaskModule.class;
		chest.item = Blocks.chest.getClass();
		
		battery.model = ModelBattery.class;
		module.model = ModelBattery.class;
		chest.model = ModelBattery.class;
	}
	
	public boolean isValidFor(Item i){
		if(this == free && (i.getClass() == battery.item || i.getClass() == module.item || i.getClass() == chest.item)){
			return true;
		}
		return i.getClass().equals(item);
	}
	
	public boolean isValidFor(ModelBase i){
		if(this == free && (i.getClass() == battery.model || i.getClass() == module.model || i.getClass() == chest.model)){
			return true;
		}
		return i.getClass().equals(model);
	}
	
	public Class<? extends ModelExpansion> getModel(){
		return model;
	}
	
	public static InventoryType getInventoryType(Class<? extends Item> i){
		for(InventoryType t : values()){
			if(t.item == i){
				return t;
			}
		}
		
		return null;
	}
}
