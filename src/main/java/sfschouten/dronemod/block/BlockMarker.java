package sfschouten.dronemod.block;

import java.util.List;

import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.init.MMDCreativeTabs;
import sfschouten.dronemod.reference.General;
import sfschouten.dronemod.registry.MarkerRegistry;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.IIcon;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;

public class BlockMarker extends Block implements ITileEntityProvider{
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	@SideOnly(Side.CLIENT)
	public static IIcon otherIcon;
	
	public BlockMarker() {
		super(Material.sponge);
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
		this.setBlockName("marker");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int metadata) {
		IIcon returnIcon = null;
		switch (side) {
		case 1:
			returnIcon = topIcon;
			break;
		default:
			returnIcon = otherIcon;
			break;
		}
		return returnIcon;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		topIcon = icon.registerIcon(General.modID + ":marker_top");
		otherIcon = icon.registerIcon(General.modID + ":dronebase");
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int par6, float par7,	float par8, float par9) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		
		player.openGui(ModularMulticopterDrones.instance, 2, world, x, y, z);
		return super.onBlockActivated(world, x, y, z, player, par6, par7, par8, par9);
	}

	@Override
	public void onBlockAdded(World par1World, int x, int y, int z) {
		TileEntityMarker m = (TileEntityMarker) par1World.getTileEntity(x, y, z);
		MarkerRegistry registry = MarkerRegistry.forWorld(par1World);
		registry.registerMarker(m, x, y, z);
		super.onBlockAdded(par1World, x, y, z);
	}

	@Override
	public void onBlockDestroyedByPlayer(World par1World, int x, int y,
			int z, int par5) {
		MarkerRegistry registry = MarkerRegistry.forWorld(par1World);
		registry.unRegisterMarker(x, y, z);
		super.onBlockDestroyedByPlayer(par1World, x, y, z, par5);
	}

	@Override
	public void onBlockDestroyedByExplosion(World par1World, int x,
			int y, int z, Explosion par5Explosion) {
		MarkerRegistry registry = MarkerRegistry.forWorld(par1World);
		registry.unRegisterMarker(x, y, z);
		super.onBlockDestroyedByExplosion(par1World, x, y, z, par5Explosion);
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityMarker();
	}
}
