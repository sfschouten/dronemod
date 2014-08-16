package sfschouten.dronemod.init;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.network.AddMarkerToItemMessage;
import sfschouten.dronemod.network.AddMarkerToItemMessageHandler;
import sfschouten.dronemod.network.ChangeMarkerMessage;
import sfschouten.dronemod.network.ChangeMarkerMessageHandler;
import sfschouten.dronemod.network.DroneReturnMessage;
import sfschouten.dronemod.network.DroneReturnMessageHandler;
import sfschouten.dronemod.network.LaunchDroneMessage;
import sfschouten.dronemod.network.LaunchDroneMessageHandler;
import sfschouten.dronemod.network.MarkersToPlayerMessage;
import sfschouten.dronemod.network.MarkersToPlayerMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

public class MMDPackets {
	public static SimpleNetworkWrapper networkWrapper;
	
	public static void init() {
		networkWrapper = new SimpleNetworkWrapper("droneMod");
		networkWrapper.registerMessage(ChangeMarkerMessageHandler.class, ChangeMarkerMessage.class, 0, Side.SERVER);
		networkWrapper.registerMessage(DroneReturnMessageHandler.class, DroneReturnMessage.class, 1, Side.SERVER);
		networkWrapper.registerMessage(LaunchDroneMessageHandler.class, LaunchDroneMessage.class, 2, Side.SERVER);
		networkWrapper.registerMessage(AddMarkerToItemMessageHandler.class, AddMarkerToItemMessage.class, 3, Side.SERVER);
		networkWrapper.registerMessage(MarkersToPlayerMessageHandler.class, MarkersToPlayerMessage.class, 4, Side.CLIENT);
	}
}
