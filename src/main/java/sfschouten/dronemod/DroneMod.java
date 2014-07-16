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
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;

@Mod(modid = DroneMod.modID, name = "Modular Multicopter Drones", version = "dev")
public class DroneMod implements LoadingCallback{
	public static final String modID = "schoutendronemod";
	public static CreativeTabs tabDroneMod = new CreativeTabs("tabDroneMod") {
		@Override
		public Item getTabIconItem() {
			return MMDItems.aluminiumStrongOctacopterItem;
		}
	};

	@Instance(value = DroneMod.modID)
	public static DroneMod instance;

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