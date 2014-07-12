package sfschouten.dronemod.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
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
			
			IChatComponent comp = new ChatComponentText(message);
			instance.dev.addChatMessage(comp);
		}
	}
	
	public static void logOut(String message){
		if(logging){
			System.out.println(message);
		}
	}
}
