package sfschouten.dronemod.client.renderer.entity;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sfschouten.dronemod.client.model.ModelBattery;
import sfschouten.dronemod.client.model.ModelCopter;
import sfschouten.dronemod.client.model.ModelOctaCopter;
import sfschouten.dronemod.entity.EntityAluminiumMediumOctacopter;
import sfschouten.dronemod.entity.EntityAluminiumStrongOctacopter;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityWoodMediumOctacopter;
import sfschouten.dronemod.entity.EntityWoodStrongOctacopter;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.util.Logger;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderOctacopter extends RenderCopter {

	public RenderOctacopter(ModelOctaCopter par1ModelBase, float par2) {
		super(par1ModelBase, par2);
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity var1) {
		EntityDrone d = null;
		try {
			d = (EntityDrone) var1;
		} catch (ClassCastException e) {
			Logger.log("Entity is not a drone!");
			e.printStackTrace();
			return null;
		}

		if (d instanceof EntityAluminiumMediumOctacopter || d instanceof EntityAluminiumStrongOctacopter) {
			return ModelOctaCopter.aluminiumOctaTextureLocation;
		} else if (d instanceof EntityWoodMediumOctacopter || d instanceof EntityWoodStrongOctacopter) {
			return ModelOctaCopter.woodOctaTextureLocation;
		} else {
			Logger.log("Wrong type of drone, not an Octa! No texture loaded.");
			return null;
		}
	}
}
