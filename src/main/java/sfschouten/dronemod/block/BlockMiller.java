package sfschouten.dronemod.block;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.tileentity.TileEntityMiller;

public class BlockMiller extends BlockContainer {

	public BlockMiller() {
		super(Material.iron);
		setCreativeTab(DroneMod.tabDroneMod);
		setBlockName("miller");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityMiller();
	}

	@Override
	public int getRenderType() {
		return -1;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	public void registerBlockIcons(IIconRegister icon) {
		this.blockIcon = icon.registerIcon(DroneMod.modID+":miller_icon");
	}
	
	@Override
	public void onBlockPlacedBy(World world, int i, int j, int k, EntityLivingBase entityLiving, ItemStack is){
		int rotation = MathHelper.floor_double((double)((entityLiving.rotationYaw * 4.0f) / 360F) + 2.5D) & 3;
		world.setBlockMetadataWithNotify(i, j, k, rotation, 2);
	}
}
