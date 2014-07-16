package sfschouten.dronemod.block;

import java.util.Random;

import javax.print.attribute.standard.MediaSize.Other;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.init.MMDCreativeTabs;
import sfschouten.dronemod.reference.General;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import cpw.mods.fml.client.registry.ISimpleBlockRenderingHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.*;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockDroneBase extends BlockContainer{
	@SideOnly(Side.CLIENT)
	public static IIcon topIcon;
	/*@SideOnly(Side.CLIENT)
	public static IIcon topTakenIcon;*/
	@SideOnly(Side.CLIENT)
	public static IIcon otherIcon;

	public BlockDroneBase() {
		super(Material.iron);
		this.setHardness(4F);
		this.setBlockName("droneBase");
		this.setCreativeTab(MMDCreativeTabs.tabGeneral);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister icon) {
		topIcon = icon.registerIcon(General.modID + ":dronebase_top");
		//topTakenIcon = icon.registerIcon(DroneMod.modID + ":dronebase_top_taken");
		otherIcon = icon.registerIcon(General.modID + ":dronebase");
	}

	@Override
	public IIcon getIcon(int side, int meta) {
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
	public TileEntity createNewTileEntity(World world, int var2) {
		return new TileEntityDroneBase();
	}

	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int metadata, float what, float these, float are) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (tileEntity == null || player.isSneaking()) {
			return false;
		}
		
		player.openGui(ModularMulticopterDrones.instance, 0, world, x, y, z);
		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block par5, int par6) {
		dropItems(world, x, y, z);
		super.breakBlock(world, x, y, z, par5, par6);
	}

	private void dropItems(World world, int x, int y, int z) {
		Random rand = new Random();

		TileEntity tileEntity = world.getTileEntity(x, y, z);
		if (!(tileEntity instanceof IInventory)) {
			return;
		}
		IInventory inventory = (IInventory) tileEntity;

		for (int i = 0; i < inventory.getSizeInventory(); i++) {
			ItemStack item = inventory.getStackInSlot(i);

			if (item != null && item.stackSize > 0) {
				float rx = rand.nextFloat() * 0.8F + 0.1F;
				float ry = rand.nextFloat() * 0.8F + 0.1F;
				float rz = rand.nextFloat() * 0.8F + 0.1F;

				EntityItem entityItem = new EntityItem(world, x + rx, y + ry, z
						+ rz, new ItemStack(item.getItem(), item.stackSize,
						item.getItemDamage()));

				if (item.hasTagCompound()) {
					entityItem.getEntityItem().setTagCompound(
							(NBTTagCompound) item.getTagCompound().copy());
				}

				float factor = 0.05F;
				entityItem.motionX = rand.nextGaussian() * factor;
				entityItem.motionY = rand.nextGaussian() * factor + 0.2F;
				entityItem.motionZ = rand.nextGaussian() * factor;
				world.spawnEntityInWorld(entityItem);
				item.stackSize = 0;
			}
		}
	}
}