package sfschouten.dronemod.util;

import java.util.List;

import sfschouten.dronemod.item.module.ItemAdvancedTaskModule;
import sfschouten.dronemod.item.module.ItemBasicTaskModule;
import sfschouten.dronemod.item.module.ItemModule;

public class EntityDroneConstructionHelper {

	public enum Validity{
		validBasic, validAdvanced, invalid
	}
	
	public static Validity validModuleCombination(List<ItemModule> modules){
		boolean containsBasic = false;
		boolean containsAdvanced = false;
		
		for(ItemModule m : modules){
			if(m instanceof ItemBasicTaskModule){
				containsBasic = true;
			}
			if(m instanceof ItemAdvancedTaskModule){
				containsAdvanced = true;
			}
		}
		
		if(containsAdvanced && containsBasic){
			return Validity.invalid;
		}else if(containsAdvanced){
			return Validity.validAdvanced;
		}else if(containsBasic){
			return Validity.validBasic;
		}else{
			return Validity.invalid;
		}
	}
}
