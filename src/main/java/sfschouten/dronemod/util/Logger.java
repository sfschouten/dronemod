package sfschouten.dronemod.util;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import org.apache.logging.log4j.Level;

import sfschouten.dronemod.reference.General;
import cpw.mods.fml.common.FMLLog;

public class Logger {
	static Logger instance;
	EntityPlayer dev;

	private Logger() {
	}

	public static void init(World w) {
		instance = new Logger();
		instance.dev = w.getPlayerEntityByName("Stefanof93");
		if (instance.dev == null) {
			instance.dev = w.getClosestPlayer(0, 0, 0, -1);
		}
	}

	public static void chat(World w, String message) {
		if (instance == null) {
			init(w);
		}

		IChatComponent comp = new ChatComponentText(message);
		instance.dev.addChatMessage(comp);
	}

	public static void log(Object object) {
		FMLLog.log(General.modID, Level.INFO, String.valueOf(object));
	}

	public static void log(Level level, Object object) {
		FMLLog.log(General.modID, level, String.valueOf(object));
	}

	public static void all(Object object) {
		log(Level.ALL, object);
	}

	public static void debug(Object object) {
		log(Level.DEBUG, object);
	}

	public static void error(Object object) {
		log(Level.ERROR, object);
	}

	public static void fatal(Object object) {
		log(Level.FATAL, object);
	}

	public static void info(Object object) {
		log(Level.INFO, object);
	}

	public static void off(Object object) {
		log(Level.OFF, object);
	}

	public static void trace(Object object) {
		log(Level.TRACE, object);
	}

	public static void warn(Object object) {
		log(Level.WARN, object);
	}
}
