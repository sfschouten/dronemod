package sfschouten.dronemod.init;

import sfschouten.dronemod.ModularMulticopterDrones;
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
import cpw.mods.fml.common.registry.EntityRegistry;

public class MMDEntities {

	public static void init() {
		int id = 0;
		EntityRegistry.registerModEntity(EntityAluminiumMediumHexacopter.class, "EntityAluminiumMediumHexacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumMediumOctacopter.class, "EntityAluminiumMediumOctacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumMediumQuadcopter.class, "EntityAluminiumMediumQuadcopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;

		EntityRegistry.registerModEntity(EntityAluminiumStrongHexacopter.class, "EntityAluminiumStrongHexacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumStrongOctacopter.class, "EntityAluminiumStrongOctacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityAluminiumStrongQuadcopter.class, "EntityAluminiumStrongQuadcopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;

		EntityRegistry.registerModEntity(EntityCaneWeakQuadcopter.class, "EntityCaneWeakQuadcopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityCaneWeakHexacopter.class, "EntityCaneWeakHexacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;

		EntityRegistry.registerModEntity(EntityWoodMediumQuadcopter.class, "EntityWoodMediumQuadcopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodMediumHexacopter.class, "EntityWoodMediumHexacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodMediumOctacopter.class, "EntityWoodMediumOctacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;

		EntityRegistry.registerModEntity(EntityWoodStrongQuadcopter.class, "EntityWoodStrongQuadcopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodStrongHexacopter.class, "EntityWoodStrongHexacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;
		EntityRegistry.registerModEntity(EntityWoodStrongOctacopter.class, "EntityWoodStrongOctacopter", id, ModularMulticopterDrones.instance, 64, 10, true);
		id++;

		EntityRegistry.registerModEntity(EntityDroneFX.class, "EntityDroneFX", id, ModularMulticopterDrones.instance, 64, 10, true);
	}
}
