package sfschouten.dronemod.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import sfschouten.dronemod.inventory.ContainerDroneBase;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import cpw.mods.fml.common.network.PacketDispatcher;

public class GuiDroneBase extends GuiContainer {
	TileEntityDroneBase tileEntity;
	
	public GuiDroneBase(InventoryPlayer inventoryPlayer, TileEntityDroneBase tileEntity) {
		super(new ContainerDroneBase(inventoryPlayer, tileEntity));
		this.tileEntity = tileEntity;
	}

	@Override
	public void initGui() {
		super.initGui();
		int posX = (this.width - 161) / 2;
		int posY = (this.height - 173) / 2;
		buttonList.add(new GuiButton(1, posX+168, posY+5, 75, 20, "Launch"));
		buttonList.add(new GuiButton(2, posX+168, posY+25, 75, 20, "Come Home"));
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		super.actionPerformed(par1GuiButton);
		switch(par1GuiButton.id){
		case 1:
			launch();
			break;
		case 2:
			returnHome();
			break;
		default:
			break;
		}
	}

	private void launch(){
		if(tileEntity.getStackInSlot(0) == null || tileEntity.getStackInSlot(0).stackSize == 0){
			return;
		}
		World world = tileEntity.getWorldObj();
		ItemDrone i = (ItemDrone) tileEntity.getStackInSlot(0).getItem();

		tileEntity.setInventorySlotContents(0, null);
		
		sendPacket("MakeWorkQuadcopterAt" + 
				tileEntity.xCoord + ";" + 
				tileEntity.yCoord + ";" + 
				tileEntity.zCoord + "In" + 
				world.provider.dimensionId);
	}
	
	private void returnHome(){
		World world = tileEntity.getWorldObj();
		
		sendPacket("ReturnHomeAt"+
				tileEntity.xCoord+";"+
				tileEntity.yCoord+";"+
				tileEntity.zCoord+"In"+ 
				world.provider.dimensionId);
	}
	
	private void sendPacket(String message){
		ByteArrayOutputStream bos = new ByteArrayOutputStream(8);
		DataOutputStream outputStream = new DataOutputStream(bos);
		try {
	        outputStream.writeUTF(message);
		} catch (Exception ex) {
	        ex.printStackTrace();
		}

		Packet250CustomPayload packet = new Packet250CustomPayload();
		packet.channel = "DroneMod";
		packet.data = bos.toByteArray();
		packet.length = bos.size();
		
		PacketDispatcher.sendPacketToServer(packet);
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		// draw text and stuff here
		// the parameters for drawString are: string, x, y, color
		fontRenderer.drawString("Drone Base", 8, 6, 4210752);
		// draws "Inventory" or your regional equivalent
		fontRenderer.drawString(
				StatCollector.translateToLocal("container.inventory"), 8,
				ySize - 96 + 2, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2,
			int par3) {
		// draw your Gui here, only thing you need to change is the path
		ResourceLocation guiTrap = null;
		if(tileEntity.getDrone() == null){
			guiTrap = new ResourceLocation("dronemod:textures/gui/dronebase_nodrone.png");
		}else{
			guiTrap = new ResourceLocation("dronemod:textures/gui/dronebase.png"); 
		}
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.renderEngine.bindTexture(guiTrap);
		int x = (width - xSize) / 2;
		int y = (height - ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, xSize, ySize);
	}
}
