package sfschouten.dronemod.client.renderer.entity;

import sfschouten.dronemod.client.model.ModelQuadcopter;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.renderer.entity.RenderEntity;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

@SideOnly(Side.CLIENT)
public class RenderQuadcopterFX extends RendererLivingEntity{
    private static final ResourceLocation Your_Texture = new ResourceLocation("dronemod:textures/entity/quadcopter.png"); 

    public RenderQuadcopterFX(ModelQuadcopter par1ModelBase , float par2)
    {
        super(par1ModelBase, par2);
    }

    @Override
    protected ResourceLocation getEntityTexture(Entity par1Entity)
    {
        return Your_Texture;
    }
}
