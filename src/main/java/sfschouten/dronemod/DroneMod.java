package sfschouten.dronemod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import sfschouten.dronemod.block.BlockDroneBase;
import sfschouten.dronemod.block.BlockMarker;
import sfschouten.dronemod.block.BlockMarkerBarrier;
import sfschouten.dronemod.block.BlockMiller;
import sfschouten.dronemod.client.gui.GuiHandler;
import sfschouten.dronemod.entity.EntityAluminiumMediumHexacopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumQuadcopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongHexacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongQuadcopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.entity.EntityDroneFX;
import sfschouten.dronemod.entity.EntityWoodMediumHexacopter;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import sfschouten.dronemod.entity.EntityWoodMediumQuadcopter;
import sfschouten.dronemod.entity.EntityWoodStrongHexacopter;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.entity.EntityWoodStrongQuadcopter;
import sfschouten.dronemod.item.ItemAluminiumIngot;
import sfschouten.dronemod.item.ItemBasicElectronics;
import sfschouten.dronemod.item.battery.ItemLargeDroneBattery;
import sfschouten.dronemod.item.battery.ItemMediumDroneBattery;
import sfschouten.dronemod.item.battery.ItemSmallDroneBattery;
import sfschouten.dronemod.item.copter.ItemAluminiumMediumHexacopter;
import sfschouten.dronemod.item.copter.ItemAluminiumMediumOctacopter;
import sfschouten.dronemod.item.copter.ItemAluminiumMediumQuadcopter;
import sfschouten.dronemod.item.copter.ItemAluminiumStrongHexacopter;
import sfschouten.dronemod.item.copter.ItemAluminiumStrongOctacopter;
import sfschouten.dronemod.item.copter.ItemAluminiumStrongQuadcopter;
import sfschouten.dronemod.item.copter.ItemCaneWeakHexacopter;
import sfschouten.dronemod.item.copter.ItemCaneWeakQuadcopter;
import sfschouten.dronemod.item.copter.ItemWoodMediumHexacopter;
import sfschouten.dronemod.item.copter.ItemWoodMediumOctacopter;
import sfschouten.dronemod.item.copter.ItemWoodMediumQuadcopter;
import sfschouten.dronemod.item.copter.ItemWoodStrongHexacopter;
import sfschouten.dronemod.item.copter.ItemWoodStrongOctacopter;
import sfschouten.dronemod.item.copter.ItemWoodStrongQuadcopter;
import sfschouten.dronemod.item.frame.ItemHexaAluminiumFrame;
import sfschouten.dronemod.item.frame.ItemHexaCaneFrame;
import sfschouten.dronemod.item.frame.ItemHexaWoodFrame;
import sfschouten.dronemod.item.frame.ItemOctaAluminiumFrame;
import sfschouten.dronemod.item.frame.ItemOctaWoodFrame;
import sfschouten.dronemod.item.frame.ItemQuadAluminiumFrame;
import sfschouten.dronemod.item.frame.ItemQuadCaneFrame;
import sfschouten.dronemod.item.frame.ItemQuadWoodFrame;
import sfschouten.dronemod.item.module.ItemAdvancedBreedingModule;
import sfschouten.dronemod.item.module.ItemFertilizeModule;
import sfschouten.dronemod.item.module.ItemHarvestModule;
import sfschouten.dronemod.item.module.ItemMineModule;
import sfschouten.dronemod.item.module.ItemPlantModule;
import sfschouten.dronemod.item.module.ItemTillModule;
import sfschouten.dronemod.item.motor.ItemMediumMotor;
import sfschouten.dronemod.item.motor.ItemStrongMotor;
import sfschouten.dronemod.item.motor.ItemWeakMotor;
import sfschouten.dronemod.network.ChangeMarkerMessage;
import sfschouten.dronemod.network.ChangeMarkerMessageHandler;
import sfschouten.dronemod.network.DroneReturnMessage;
import sfschouten.dronemod.network.DroneReturnMessageHandler;
import sfschouten.dronemod.network.LaunchDroneMessage;
import sfschouten.dronemod.network.LaunchDroneMessageHandler;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.tileentity.TileEntityMiller;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler; // used in 1.6.2
//import cpw.mods.fml.common.Mod.PreInit;    // used in 1.5.2
//import cpw.mods.fml.common.Mod.Init;       // used in 1.5.2
//import cpw.mods.fml.common.Mod.PostInit;   // used in 1.5.2
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid = DroneMod.modID, name = "DroneMod", version = "dev")
public class DroneMod {

	public static final String modID = "schoutendronemod";

	// CreativeTabs
	public static CreativeTabs tabDroneMod = new CreativeTabs("tabDroneMod") {
		@Override
		public Item getTabIconItem() {
			return caneWeakQuadcopterItem;
		}
	};

	// DroneParts
	public final static ItemHexaAluminiumFrame hexaAluminiumFrameItem = new ItemHexaAluminiumFrame();
	public final static ItemHexaCaneFrame hexaCaneFrameItem = new ItemHexaCaneFrame();
	public final static ItemHexaWoodFrame hexaWoodFrameItem = new ItemHexaWoodFrame();
	public final static ItemOctaAluminiumFrame octaAluminiumFrameItem = new ItemOctaAluminiumFrame();
	public final static ItemOctaWoodFrame octaWoodFrameItem = new ItemOctaWoodFrame();
	public final static ItemQuadAluminiumFrame quadAluminiumFrameItem = new ItemQuadAluminiumFrame();
	public final static ItemQuadCaneFrame quadCaneFrameItem = new ItemQuadCaneFrame();
	public final static ItemQuadWoodFrame quadWoodFrameItem = new ItemQuadWoodFrame();

	public final static ItemWeakMotor weakMotorItem = new ItemWeakMotor();
	public final static ItemMediumMotor mediumMotorItem = new ItemMediumMotor();
	public final static ItemStrongMotor strongMotorItem = new ItemStrongMotor();

	public final static ItemFertilizeModule fertilizeModuleItem = new ItemFertilizeModule();
	public final static ItemHarvestModule harvestModuleItem = new ItemHarvestModule();
	public final static ItemPlantModule plantModuleItem = new ItemPlantModule();
	public final static ItemTillModule tillModuleItem = new ItemTillModule();
	public final static ItemMineModule mineModuleItem = new ItemMineModule();

	public final static ItemAdvancedBreedingModule advancedBreedingModuleItem = new ItemAdvancedBreedingModule();

	public final static ItemSmallDroneBattery smallDroneBatteryItem = new ItemSmallDroneBattery();
	public final static ItemMediumDroneBattery mediumDroneBatteryItem = new ItemMediumDroneBattery();
	public final static ItemLargeDroneBattery largeDroneBatteryItem = new ItemLargeDroneBattery();

	// Other Items
	public final static ItemAluminiumIngot aluminiumIngotItem = new ItemAluminiumIngot();
	public final static ItemBasicElectronics basicElectronicsItem = new ItemBasicElectronics();

	// Drones
	public final static ItemAluminiumMediumHexacopter aluminiumMediumHexacopterItem = new ItemAluminiumMediumHexacopter();
	public final static ItemAluminiumMediumOctacopter aluminiumMediumOctacopterItem = new ItemAluminiumMediumOctacopter();
	public final static ItemAluminiumMediumQuadcopter aluminiumMediumQuadcopterItem = new ItemAluminiumMediumQuadcopter();

	public final static ItemAluminiumStrongHexacopter aluminiumStrongHexacopterItem = new ItemAluminiumStrongHexacopter();
	public final static ItemAluminiumStrongOctacopter aluminiumStrongOctacopterItem = new ItemAluminiumStrongOctacopter();
	public final static ItemAluminiumStrongQuadcopter aluminiumStrongQuadcopterItem = new ItemAluminiumStrongQuadcopter();

	public final static ItemCaneWeakQuadcopter caneWeakQuadcopterItem = new ItemCaneWeakQuadcopter();
	public final static ItemCaneWeakHexacopter caneWeakHexacopterItem = new ItemCaneWeakHexacopter();

	public final static ItemWoodMediumQuadcopter woodMediumQuadcopterItem = new ItemWoodMediumQuadcopter();
	public final static ItemWoodMediumHexacopter woodMediumHexacopterItem = new ItemWoodMediumHexacopter();
	public final static ItemWoodMediumOctacopter woodMediumOctacopterItem = new ItemWoodMediumOctacopter();

	public final static ItemWoodStrongQuadcopter woodStrongQuadcopterItem = new ItemWoodStrongQuadcopter();
	public final static ItemWoodStrongHexacopter woodStrongHexacopterItem = new ItemWoodStrongHexacopter();
	public final static ItemWoodStrongOctacopter woodStrongOctacopterItem = new ItemWoodStrongOctacopter();

	// Blocks
	public final static BlockDroneBase droneBase = new BlockDroneBase();
	public final static BlockMarker marker = new BlockMarker();
	public final static BlockMarkerBarrier markerBarrier = new BlockMarkerBarrier();
	public final static BlockMiller miller = new BlockMiller();

	// The instance of your mod that Forge uses.
	@Instance(value = DroneMod.modID)
	public static DroneMod instance;

	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide = "sfschouten.dronemod.client.ClientProxy", serverSide = "sfschouten.dronemod.CommonProxy")
	public static CommonProxy proxy;
	public static SimpleNetworkWrapper networkWrapper;

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		proxy.registerRenderers();
	}

	@EventHandler
	public void load(FMLInitializationEvent event) {
		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

		packetInit();
		itemInit();
		blockInit();
		tileEntityInit();
		entityInit();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
	}

	private void packetInit() {
		networkWrapper = new SimpleNetworkWrapper("droneMod");
		networkWrapper.registerMessage(ChangeMarkerMessageHandler.class, ChangeMarkerMessage.class, 0, Side.SERVER);
		networkWrapper.registerMessage(DroneReturnMessageHandler.class, DroneReturnMessage.class, 1, Side.SERVER);
		networkWrapper.registerMessage(LaunchDroneMessageHandler.class, LaunchDroneMessage.class, 2, Side.SERVER);

	}

	private void itemInit() {
		GameRegistry.registerItem(hexaAluminiumFrameItem, "hexaAluminiumFrameItem");
		GameRegistry.registerItem(hexaCaneFrameItem, "hexaCaneFrameItem");
		GameRegistry.registerItem(hexaWoodFrameItem, "hexaWoodFrameItem");
		GameRegistry.registerItem(octaAluminiumFrameItem, "octaAluminiumFrameItem");
		GameRegistry.registerItem(octaWoodFrameItem, "octaWoodFrameItem");
		GameRegistry.registerItem(quadAluminiumFrameItem, "quadAluminiumFrameItem");
		GameRegistry.registerItem(quadCaneFrameItem, "quadCaneFrameItem");
		GameRegistry.registerItem(quadWoodFrameItem, "quadWoodFrameItem");

		GameRegistry.registerItem(weakMotorItem, "weakMotorItem");
		GameRegistry.registerItem(mediumMotorItem, "mediumMotorItem");
		GameRegistry.registerItem(strongMotorItem, "strongMotorItem");

		GameRegistry.registerItem(fertilizeModuleItem, "fertilizeModuleItem");
		GameRegistry.registerItem(harvestModuleItem, "harvestModuleItem");
		GameRegistry.registerItem(plantModuleItem, "plantModuleItem");
		GameRegistry.registerItem(tillModuleItem, "tillModuleItem");
		GameRegistry.registerItem(mineModuleItem, "mineModuleItem");

		GameRegistry.registerItem(advancedBreedingModuleItem, "advancedBreedingModuleItem");

		GameRegistry.registerItem(smallDroneBatteryItem, "smallDroneBatteryItem");
		GameRegistry.registerItem(mediumDroneBatteryItem, "mediumDroneBatteryItem");
		GameRegistry.registerItem(largeDroneBatteryItem, "largeDroneBatteryItem");

		GameRegistry.registerItem(aluminiumIngotItem, "aluminiumIngotItem");
		GameRegistry.registerItem(basicElectronicsItem, "basicElectronicsItem");

		GameRegistry.registerItem(aluminiumMediumHexacopterItem, "aluminiumMediumHexacopterItem");
		GameRegistry.registerItem(aluminiumMediumOctacopterItem, "aluminiumMediumOctacopterItem");
		GameRegistry.registerItem(aluminiumMediumQuadcopterItem, "aluminiumMediumQuadcopterItem");
		
		GameRegistry.registerItem(aluminiumStrongHexacopterItem, "aluminiumStrongHexacopterItem");
		GameRegistry.registerItem(aluminiumStrongOctacopterItem, "aluminiumStrongOctacopterItem");
		GameRegistry.registerItem(aluminiumStrongQuadcopterItem, "aluminiumStrongQuadcopterItem");
		
		GameRegistry.registerItem(caneWeakQuadcopterItem, "caneWeakQuadcopterItem");
		GameRegistry.registerItem(caneWeakHexacopterItem, "caneWeakHexacopterItem");
		
		GameRegistry.registerItem(woodMediumQuadcopterItem, "woodMediumQuadcopterItem");
		GameRegistry.registerItem(woodMediumHexacopterItem, "woodMediumHexacopterItem");
		GameRegistry.registerItem(woodMediumOctacopterItem, "woodMediumOctacopterItem");
		
		GameRegistry.registerItem(woodStrongQuadcopterItem, "woodStrongQuadcopterItem");
		GameRegistry.registerItem(woodStrongHexacopterItem, "woodStrongHexacopterItem");
		GameRegistry.registerItem(woodStrongOctacopterItem, "woodStrongOctacopterItem");
	}

	private void blockInit() {
		GameRegistry.registerBlock(droneBase, "droneBase");
		GameRegistry.registerBlock(marker, "marker");
		GameRegistry.registerBlock(markerBarrier, "markerBarrier");
		GameRegistry.registerBlock(miller, "miller");
	}

	private void tileEntityInit() {
		GameRegistry.registerTileEntity(TileEntityDroneBase.class, "DroneBaseTileEntity");
		GameRegistry.registerTileEntity(TileEntityMarker.class, "MarkerTileEntity");
		GameRegistry.registerTileEntity(TileEntityMiller.class, "MillingTileEntity");
	}

	private void entityInit() {
		int id = 0;
		EntityRegistry.registerModEntity(EntityAluminiumMediumHexacopter.class, "EntityAluminiumMediumHexacopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumMediumOctacopter.class, "EntityAluminiumMediumOctacopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumMediumQuadcopter.class, "EntityAluminiumMediumQuadcopter", id, this, 64, 10, true);
		id++;
		
		EntityRegistry.registerModEntity(EntityAluminiumStrongHexacopter.class, "EntityAluminiumStrongHexacopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumStrongOctacopter.class, "EntityAluminiumStrongOctacopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumStrongQuadcopter.class, "EntityAluminiumStrongQuadcopter", id, this, 64, 10, true);
		id++;
		
		EntityRegistry.registerModEntity(EntityCaneWeakQuadcopter.class, "EntityCaneWeakQuadcopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityCaneWeakHexacopter.class, "EntityCaneWeakHexacopter", id, this, 64, 10, true);
		id++;
		
		EntityRegistry.registerModEntity(EntityWoodMediumQuadcopter.class, "EntityWoodMediumQuadcopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodMediumHexacopter.class, "EntityWoodMediumHexacopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodMediumOctacopter.class, "EntityWoodMediumOctacopter", id, this, 64, 10, true);
		id++;
		
		EntityRegistry.registerModEntity(EntityWoodStrongQuadcopter.class, "EntityWoodStrongQuadcopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodStrongHexacopter.class, "EntityWoodStrongHexacopter", id, this, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodStrongOctacopter.class, "EntityWoodStrongOctacopter", id, this, 64, 10, true);
		id++;

		EntityRegistry.registerModEntity(EntityDroneFX.class, "EntityDroneFX", id, this, 64, 10, true);
	}
}