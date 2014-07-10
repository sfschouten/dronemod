package sfschouten.dronemod.client.renderer.entity;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityAluminiumMediumHexacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongHexacopter;
import sfschouten.dronemod.entity.EntityCaneWeakHexacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumHexacopter;
import sfschouten.dronemod.entity.EntityWoodStrongHexacopter;

public class RenderHexacopter extends RenderLiving {
	private static final ResourceLocation woodHexaTextureLocation = new ResourceLocation(DroneMod.modID, "textures/entity/woodHexa.png");
	private static final ResourceLocation caneHexaTextureLocation = new ResourceLocation(DroneMod.modID, "textures/entity/caneHexa.png");
	private static final ResourceLocation aluminiumHexaTextureLocation = new ResourceLocation(DroneMod.modID, "textures/entity/aluminiumHexa.png");

	//TODO change ModelBase to HexaModel
	public RenderHexacopter(ModelBase par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		EntityDrone d = null;
		try {
			d = (EntityDrone) var1;
		} catch (ClassCastException e) {
			e.printStackTrace();
		}

		if (d instanceof EntityAluminiumMediumHexacopter || d instanceof EntityAluminiumStrongHexacopter) {
			return aluminiumHexaTextureLocation;
		} else if (d instanceof EntityWoodMediumHexacopter || d instanceof EntityWoodStrongHexacopter) {
			return woodHexaTextureLocation;
		} else if (d instanceof EntityCaneWeakHexacopter) {
			return caneHexaTextureLocation;
		} else {
			Logger.logOut("Wrong type of drone, not a Hexa! No texture loaded.");
			return null;
		}
	}
}
