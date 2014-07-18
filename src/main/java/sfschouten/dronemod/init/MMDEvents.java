package sfschouten.dronemod.init;

import net.minecraftforge.common.MinecraftForge;
import sfschouten.dronemod.event.EntityEventHandler;

public class MMDEvents {
	
	public static void init(){
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
	}
}
