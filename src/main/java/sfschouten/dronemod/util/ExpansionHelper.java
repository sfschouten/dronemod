package sfschouten.dronemod.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import net.minecraft.item.ItemStack;
import sfschouten.dronemod.inventory.InventoryType;

public class ExpansionHelper {

	/**
	 * Returns a hashmap in wich all free expansions are sorted through the other expansiontypes.
	 * @param preSort	the hashmap that needs sorting.
	 * @return	a hashmap that has the sorted expansions.
	 */
	public static HashMap<InventoryType, ItemStack[]> divideExpansions(HashMap<InventoryType, ItemStack[]> preSort){
		ItemStack[] free = preSort.get(InventoryType.free);
		
		ItemStack[] battery = preSort.get(InventoryType.battery);
		ItemStack[] chest = preSort.get(InventoryType.chest);
		ItemStack[] module = preSort.get(InventoryType.module);
		
		for(ItemStack s : free){
			if(s != null){
				InventoryType t = InventoryType.getInventoryType(s.getItem().getClass());
				
				switch(t){
				case battery:
					addToStack(battery, s);
					break;
				case chest:
					addToStack(chest, s);
					break;
				case module:
					addToStack(module, s);
					break;
				default:
					break;
				}
			}
		}
		
		HashMap<InventoryType, ItemStack[]> postSort = new HashMap<InventoryType, ItemStack[]>();
		postSort.put(InventoryType.battery, battery);
		postSort.put(InventoryType.chest, chest);
		postSort.put(InventoryType.module, module);
		
		return postSort;
	}
	
	private static ItemStack[] addToStack(ItemStack[] toAddTo, ItemStack toBeAdded){
		List<ItemStack> list = Arrays.asList(toAddTo);
		list.add(toBeAdded);
		return (ItemStack[]) list.toArray();
	}
	
}
