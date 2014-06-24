package sfschouten.dronemod.block;

import java.util.List;
import java.util.Random;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.client.particle.EntityDroneFX;
import sfschouten.dronemod.entity.EntityDrone;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class BlockMarkerBarrier extends Block{
	@SideOnly(Side.CLIENT)
	IIcon icon;
	
	public BlockMarkerBarrier() {
		super(Material.glass);
		setBlockName("markerBarrier");
		setBlockUnbreakable();
	}

	public boolean isOpaqueCube(){
        return false;
    }

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		return icon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		this.icon = icon.registerIcon("dronemod:markerbarrier");
	}
	
	@Override
	public void addCollisionBoxesToList(World par1World, int par2, int par3, int par4, AxisAlignedBB par5AxisAlignedBB, List par6List, Entity par7Entity) {
		if(par7Entity instanceof EntityPlayer || par7Entity instanceof EntityDroneFX || par7Entity instanceof EntityDrone){
			return;
		}
		super.addCollisionBoxesToList(par1World, par2, par3, par4, par5AxisAlignedBB, par6List, par7Entity);
	}

	@Override
	public void onEntityCollidedWithBlock(World par1World, int par2, int par3, int par4, Entity par5Entity) {
		if(!(par5Entity instanceof EntityPlayer) && !(par5Entity instanceof EntityDroneFX)){
			
		}
		super.onEntityCollidedWithBlock(par1World, par2, par3, par4, par5Entity);
	}
}
