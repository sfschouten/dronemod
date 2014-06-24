package sfschouten.dronemod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class Logger {
	static Logger instance;
	EntityPlayer dev;
	static boolean logging = true;
	
	private Logger(){
	}
	
	public static void init(World w){
		instance = new Logger(); 
		instance.dev = w.getPlayerEntityByName("Stefanof93");
		if(instance.dev == null){
			instance.dev = w.getClosestPlayer(0, 0, 0, -1);
		}
	}
	
	public static void logChat(World w, String message){
		if(logging){
			if(instance == null){
				init(w);
			}
			instance.dev.addChatMessage(message);
		}
	}
	
	public static void logOut(String message){
		if(logging){
			System.out.println(message);
		}
	}
}
