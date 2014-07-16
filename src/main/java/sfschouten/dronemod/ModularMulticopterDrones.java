package sfschouten.dronemod;

import java.util.List;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.ForgeChunkManager.LoadingCallback;
import net.minecraftforge.common.ForgeChunkManager.Ticket;
import sfschouten.dronemod.client.gui.GuiHandler;
import sfschouten.dronemod.init.MMDBlocks;
import sfschouten.dronemod.init.MMDEntities;
import sfschouten.dronemod.init.MMDItems;
import sfschouten.dronemod.init.MMDPackets;
import sfschouten.dronemod.init.MMDTileEntities;
import sfschouten.dronemod.proxy.CommonProxy;
import sfschouten.dronemod.reference.General;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = General.modID, name = General.modName, version = General.modVersion)
public class ModularMulticopterDrones implements LoadingCallback{

	@Instance(value = General.modID)
	public static ModularMulticopterDrones instance;

	@SidedProxy(clientSide = "sfschouten.dronemod.proxy.ClientProxy", serverSide = "sfschouten.dronemod.proxy.CommonProxy")
	public static CommonProxy proxy;
	

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerRenderers();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
		ForgeChunkManager.setForcedChunkLoadingCallback(this, this);

		MMDPackets.init();
		MMDItems.init();
		MMDBlocks.init();
		MMDTileEntities.init();
		MMDEntities.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	@Override
	public void ticketsLoaded(List<Ticket> tickets, World world) {
	}
}