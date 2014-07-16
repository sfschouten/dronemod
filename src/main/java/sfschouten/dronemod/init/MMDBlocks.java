package sfschouten.dronemod.init;

import sfschouten.dronemod.block.BlockDroneBase;
import sfschouten.dronemod.block.BlockMarker;
import sfschouten.dronemod.block.BlockMarkerBarrier;
import sfschouten.dronemod.block.BlockMiller;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMDBlocks {

	public final static BlockDroneBase droneBase = new BlockDroneBase();
	public final static BlockMarker marker = new BlockMarker();
	public final static BlockMarkerBarrier markerBarrier = new BlockMarkerBarrier();
	public final static BlockMiller miller = new BlockMiller();
	
	public static void init() {
		GameRegistry.registerBlock(droneBase, "droneBase");
		GameRegistry.registerBlock(marker, "marker");
		GameRegistry.registerBlock(markerBarrier, "markerBarrier");
		GameRegistry.registerBlock(miller, "miller");
	}
}
