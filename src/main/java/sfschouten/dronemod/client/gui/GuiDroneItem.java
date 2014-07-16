package sfschouten.dronemod.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.inventory.ContainerDroneItem;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.inventory.SimpleInventory;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.reference.General;

public class GuiDroneItem extends GuiContainer {
	ItemStack stack;
	
	public GuiDroneItem(InventoryPlayer inventoryPlayer, ItemStack stack) {
		super(new ContainerDroneItem(inventoryPlayer, stack));
		this.stack = stack;
	}

	@Override
	public void initGui() {
		super.initGui();
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		super.actionPerformed(par1GuiButton);
		
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRendererObj.drawString("Drone", 8, 6, 4210752);
		
		for(InventoryType type : InventoryType.values()){
			fontRendererObj.drawString(type.name(), 8, 16+(type.ordinal()*25), 4210752);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
		// draw your Gui here, only thing you need to change is the path
		ResourceLocation guiTrap = new ResourceLocation(General.modID + ":textures/gui/droneitemgui.png");
		
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(guiTrap);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
		
		ResourceLocation guiSlot = new ResourceLocation(General.modID + ":textures/gui/slot.png");
		this.mc.renderEngine.bindTexture(guiSlot);
		
		NBTTagCompound stackNBT = stack.getTagCompound();
		for(InventoryType type : InventoryType.values()){
			if(stackNBT == null){
				stackNBT = new NBTTagCompound();
			}
			NBTTagCompound comp = stackNBT.getCompoundTag(type.name());
			ItemDrone drone = (ItemDrone)stack.getItem();
			int invLength = comp.getInteger("length");
			SimpleInventory temp = new SimpleInventory(drone.getExpSize(type));
			temp.readFromNBT(comp);

			for(int t = 0; t < temp.getSizeInventory(); t++){
				this.drawTexturedModalRect(x + 7 + (t*18), y + 24 + (type.ordinal()*25), 0, 0, 50, 50);
			}
		}
	}
}
