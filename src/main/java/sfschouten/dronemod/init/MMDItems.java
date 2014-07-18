package sfschouten.dronemod.init;

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
import sfschouten.dronemod.item.module.ItemChunkloaderModule;
import sfschouten.dronemod.item.module.ItemFertilizeModule;
import sfschouten.dronemod.item.module.ItemHarvestModule;
import sfschouten.dronemod.item.module.ItemMineModule;
import sfschouten.dronemod.item.module.ItemPlantModule;
import sfschouten.dronemod.item.module.ItemTillModule;
import sfschouten.dronemod.item.motor.ItemMediumMotor;
import sfschouten.dronemod.item.motor.ItemStrongMotor;
import sfschouten.dronemod.item.motor.ItemWeakMotor;
import cpw.mods.fml.common.registry.GameRegistry;

public class MMDItems {
	//Frames
	public final static ItemHexaAluminiumFrame hexaAluminiumFrameItem = new ItemHexaAluminiumFrame();
	public final static ItemHexaCaneFrame hexaCaneFrameItem = new ItemHexaCaneFrame();
	public final static ItemHexaWoodFrame hexaWoodFrameItem = new ItemHexaWoodFrame();
	public final static ItemOctaAluminiumFrame octaAluminiumFrameItem = new ItemOctaAluminiumFrame();
	public final static ItemOctaWoodFrame octaWoodFrameItem = new ItemOctaWoodFrame();
	public final static ItemQuadAluminiumFrame quadAluminiumFrameItem = new ItemQuadAluminiumFrame();
	public final static ItemQuadCaneFrame quadCaneFrameItem = new ItemQuadCaneFrame();
	public final static ItemQuadWoodFrame quadWoodFrameItem = new ItemQuadWoodFrame();

	//Motors
	public final static ItemWeakMotor weakMotorItem = new ItemWeakMotor();
	public final static ItemMediumMotor mediumMotorItem = new ItemMediumMotor();
	public final static ItemStrongMotor strongMotorItem = new ItemStrongMotor();

	//Modules
	public final static ItemFertilizeModule fertilizeModuleItem = new ItemFertilizeModule();
	public final static ItemHarvestModule harvestModuleItem = new ItemHarvestModule();
	public final static ItemPlantModule plantModuleItem = new ItemPlantModule();
	public final static ItemTillModule tillModuleItem = new ItemTillModule();
	public final static ItemMineModule mineModuleItem = new ItemMineModule();
	public final static ItemChunkloaderModule chunkloaderModuleItem = new ItemChunkloaderModule();
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

	public static void init() {
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
		GameRegistry.registerItem(chunkloaderModuleItem, "chunkloaderModuleItem");
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
}
