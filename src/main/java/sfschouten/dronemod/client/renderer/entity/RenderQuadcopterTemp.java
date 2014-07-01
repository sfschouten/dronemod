package sfschouten.dronemod.client.renderer.entity;

import ch.epfl.lamp.compiler.msil.ICustomAttributeProvider;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.client.model.ModelQuadcopter;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

@SideOnly(Side.CLIENT)
public class RenderQuadcopterTemp extends RenderLiving{
    private static final ResourceLocation quadCopterTexture = new ResourceLocation(DroneMod.modID + ":textures/entity/quadcopter.png"); 

    public RenderQuadcopterTemp(ModelQuadcopter par1ModelBase , float par2){
        super(par1ModelBase, par2);
    }

	@Override
    protected ResourceLocation getEntityTexture(Entity par1Entity){
        return quadCopterTexture;
    }
}
