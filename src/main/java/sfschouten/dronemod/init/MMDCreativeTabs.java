package sfschouten.dronemod.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MMDCreativeTabs {
	
	public static CreativeTabs tabGeneral = new CreativeTabs("tabMMDGeneral") {
		@Override
		public Item getTabIconItem() {
			return MMDItems.aluminiumStrongOctacopterItem;
		}
	};
	
}
