package sfschouten.dronemod.client.gui;

import org.lwjgl.opengl.GL11;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.inventory.ContainerDroneBase;
import sfschouten.dronemod.inventory.ContainerMiller;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

public class GuiMiller extends GuiContainer {

	public GuiMiller(InventoryPlayer inventoryPlayer, TileEntityDroneBase tileEntity) {
		super(new ContainerMiller());

	}

	@Override
	public void initGui() {
		
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		fontRendererObj.drawString("Milling Machine", 8, 6, 4210752);
		fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		ResourceLocation guiTrap = new ResourceLocation(DroneMod.modID, "textures/gui/dronemiller.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.renderEngine.bindTexture(guiTrap);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}

}
