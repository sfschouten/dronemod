package sfschouten.dronemod;

import sfschouten.dronemod.block.BlockDroneBase;
import sfschouten.dronemod.block.BlockMarker;
import sfschouten.dronemod.block.BlockMarkerBarrier;
import sfschouten.dronemod.client.gui.GuiHandler;
import sfschouten.dronemod.client.particle.EntityDroneFX;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.item.ItemAluminiumIngot;
import sfschouten.dronemod.item.ItemBasicElectronics;
import sfschouten.dronemod.item.battery.ItemLargeDroneBattery;
import sfschouten.dronemod.item.battery.ItemMediumDroneBattery;
import sfschouten.dronemod.item.battery.ItemSmallDroneBattery;
import sfschouten.dronemod.item.copter.ItemCaneWeakHexacopter;
import sfschouten.dronemod.item.copter.ItemCaneWeakQuadcopter;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.item.copter.ItemWoodMediumQuadcopter;
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
import sfschouten.dronemod.network.ExecutableMessageHandler;
import sfschouten.dronemod.network.IExecutableMessage;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
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
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;

@Mod(modid=DroneMod.modID, name="DroneMod", version="dev")
public class DroneMod {
	
	public static final String modID = "SchoutenDroneMod";
	
	//CreativeTabs
	public static CreativeTabs tabDroneMod = new CreativeTabs("tabDroneMod") {
		@Override
		public Item getTabIconItem() {
			return caneWeakQuadcopterItem;
		}
	};
	
	//DroneParts
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
	
	//Other Items
	public final static ItemAluminiumIngot aluminiumIngotItem = new ItemAluminiumIngot();
	public final static ItemBasicElectronics basicElectronicsItem = new ItemBasicElectronics();
	
	//Drones
	public final static ItemCaneWeakQuadcopter caneWeakQuadcopterItem = new ItemCaneWeakQuadcopter();
	public final static ItemCaneWeakHexacopter caneWeakHexacopterItem = new ItemCaneWeakHexacopter();
	public final static ItemWoodMediumQuadcopter woodMediumQuadcopterItem = new ItemWoodMediumQuadcopter();
	
	//Blocks
	public final static BlockDroneBase droneBase = new BlockDroneBase();
	public final static BlockMarker marker = new BlockMarker();
	public final static BlockMarkerBarrier markerBarrier = new BlockMarkerBarrier();
	
    // The instance of your mod that Forge uses.
    @Instance(value = "SchoutenDroneMod")
    public static DroneMod instance;
    
    // Says where the client and server 'proxy' code is loaded.
    @SidedProxy(clientSide="sfschouten.dronemod.client.ClientProxy", serverSide="sfschouten.dronemod.CommonProxy")
    public static CommonProxy proxy;
    public static SimpleNetworkWrapper networkWrapper;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
    	itemInit();
    	blockInit();
    	tileEntityInit();
    }
    
    @EventHandler
    public void load(FMLInitializationEvent event) {
    	NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());

        int id = 0;
        EntityRegistry.registerModEntity(EntityCaneWeakQuadcopter.class, "EntityCaneWeakQuadcopter", id, this, 64, 10, true);
        id++;              
        EntityRegistry.registerModEntity(EntityDroneFX.class, "EntityDroneFX", id, this, 64, 10, true);
        
    	proxy.registerRenderers();
    	
    	this.packetInit();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {
    }
    
    private void packetInit(){
    	networkWrapper = new SimpleNetworkWrapper("droneMod");
    	
    	networkWrapper.registerMessage(ExecutableMessageHandler.class, IExecutableMessage.class, 0, Side.SERVER);
    }
    
    private void itemInit(){
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
    	
    	GameRegistry.registerItem(caneWeakQuadcopterItem, "caneWeakQuadcopterItem");
    	GameRegistry.registerItem(caneWeakHexacopterItem, "caneWeakHexacopterItem");
    	GameRegistry.registerItem(woodMediumQuadcopterItem, "woodMediumQuadcopterItem");
    }
    
    private void blockInit(){
    	GameRegistry.registerBlock(droneBase, "droneBase");
    	GameRegistry.registerBlock(marker, "marker");
    	GameRegistry.registerBlock(markerBarrier, "markerBarrier");
    }
    
    private void tileEntityInit(){
    	GameRegistry.registerTileEntity(TileEntityDroneBase.class, "DroneBaseTileEntity");
        GameRegistry.registerTileEntity(TileEntityMarker.class, "MarkerTileEntity");
    }
}