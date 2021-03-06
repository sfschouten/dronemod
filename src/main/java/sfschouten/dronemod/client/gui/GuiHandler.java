package sfschouten.dronemod.client.gui;

import java.util.List;

import sfschouten.dronemod.TempInventoryType;
import sfschouten.dronemod.inventory.ContainerDroneBase;
import sfschouten.dronemod.inventory.ContainerDroneItem;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {
	// returns an instance of the Container you made earlier
	@Override
	public Object getServerGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(id){
		case 0:
			if (tileEntity instanceof TileEntityDroneBase) {
				return new ContainerDroneBase(player.inventory, (TileEntityDroneBase) tileEntity);
			}else{
				return null;
			}
		case 1:
			ItemStack stack = player.inventory.getStackInSlot(player.inventory.currentItem);
			return new ContainerDroneItem(player.inventory, stack);
		case 2:
			return null;
		default:
			return null;
		}
	}

	// returns an instance of the Gui you made earlier
	@Override
	public Object getClientGuiElement(int id, EntityPlayer player, World world, int x, int y, int z) {
		TileEntity tileEntity = world.getTileEntity(x, y, z);
		switch(id){
		case 0:
			if (tileEntity instanceof TileEntityDroneBase) {
				return new GuiDroneBase(player.inventory, (TileEntityDroneBase) tileEntity); 
			}else{
				return null;
			}
		case 1:
			ItemStack stack = player.inventory.getStackInSlot(player.inventory.currentItem);
			return new GuiDroneItem(player.inventory, stack);
		case 2:
			return new GuiMarker((TileEntityMarker) tileEntity);
		default:
			return null;
		}
	}
}