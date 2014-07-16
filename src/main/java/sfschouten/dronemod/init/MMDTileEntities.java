package sfschouten.dronemod.init;

import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.tileentity.TileEntityMiller;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMDTileEntities {
	
	public static void init() {
		GameRegistry.registerTileEntity(TileEntityDroneBase.class, "DroneBaseTileEntity");
		GameRegistry.registerTileEntity(TileEntityMarker.class, "MarkerTileEntity");
		GameRegistry.registerTileEntity(TileEntityMiller.class, "MillingTileEntity");
	}
}
