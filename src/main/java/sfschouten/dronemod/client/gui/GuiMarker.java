package sfschouten.dronemod.client.gui;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import org.lwjgl.opengl.GL11;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import sfschouten.dronemod.client.particle.EntityDroneFX;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.entity.EntityCaneWeakQuadcopter;
import sfschouten.dronemod.inventory.ContainerDroneBase;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.network.ChangeMarkerPacket;
import sfschouten.dronemod.network.Packet;
import sfschouten.dronemod.network.Packet.PacketException;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import cpw.mods.fml.common.network.PacketDispatcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class GuiMarker extends GuiScreen {
	TileEntityMarker tileEntity;
	GuiTextField nameField;
	GuiTextField radiusField;
	GuiButton barrierButton;
	
	public GuiMarker(TileEntityMarker m) {
		super();
		tileEntity = m;
	}

	@Override
	public void initGui() {
		super.initGui();
		int posX = (this.width - 161) / 2;
		int posY = (this.height - 173) / 2;
		
		this.nameField = new GuiTextField(fontRenderer, posX, posY, 120, 20);
		this.nameField.setFocused(false);
		this.nameField.setMaxStringLength(50);
		
		this.radiusField = new GuiTextField(fontRenderer, posX, posY+25, 40, 20);
		this.radiusField.setFocused(false);
		this.radiusField.setMaxStringLength(3);
		
		buttonList.add(new GuiButton(1, posX, posY+50, 75, 20, "Barrier"));
		
		if(tileEntity.getName() != null){
			nameField.setText(tileEntity.getName());
		}
		radiusField.setText(String.valueOf(tileEntity.getRadius()));
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawCenteredString(fontRenderer, "Drone Work Marker", 8, 6, 4210752);
		this.nameField.drawTextBox();
		this.radiusField.drawTextBox();
		drawDefaultBackground();
		super.drawScreen(par1, par2, par3);
	}

	@Override
	protected void keyTyped(char par1, int par2) {
		if(nameField.isFocused()){
			nameField.textboxKeyTyped(par1, par2);
		}else if(radiusField.isFocused()){
			radiusField.textboxKeyTyped(par1, par2);
		}
		super.keyTyped(par1, par2);
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		this.nameField.mouseClicked(par1, par2, par3);
		this.radiusField.mouseClicked(par1, par2, par3);
		super.mouseClicked(par1, par2, par3);
	}

	@Override
	public void onGuiClosed() {
		
		tileEntity.setName(nameField.getText());
		String radiusString = radiusField.getText();
		int radius = 1;
		try{
			radius = Integer.parseInt(radiusString);
		}catch(NumberFormatException e){
			System.out.println("exception???");
		}
		tileEntity.setRadius(radius);
		super.onGuiClosed();
	}

	@Override
	protected void actionPerformed(GuiButton par1GuiButton) {
		switch(par1GuiButton.id){
		case 1:
			sendToggleBarrier();
			break;
		}
		super.actionPerformed(par1GuiButton);
	}
	
	private void sendNewRadius(int newRadius){
		World world = tileEntity.getWorldObj();
		
		sendPacket("ChangeRadiusAt" + 
				tileEntity.xCoord + ";" + 
				tileEntity.yCoord + ";" + 
				tileEntity.zCoord + "In" + 
				world.provider.dimensionId + "To" +
				newRadius);
	}
	
	private void sendToggleBarrier(){
		tileEntity.toggleBarrier();
		try {
			ChangeMarkerPacket p = (ChangeMarkerPacket) Packet.constructPacket(0);
			p.setX(tileEntity.xCoord);
			p.setY(tileEntity.yCoord);
			p.setZ(tileEntity.zCoord);
			p.setWorldID(tileEntity.getWorldObj().provider.dimensionId);
			p.setRadius(tileEntity.getRadius());
			p.setBarrier(!tileEntity.isBarrier());
			p.setName(tileEntity.getName());
			p.sendToServer();
		} catch (PacketException e) {
			e.printStackTrace();
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
		
		/*
		World world = tileEntity.worldObj;
		
		sendPacket("ToggleBarrierAt" + 
				tileEntity.xCoord + ";" + 
				tileEntity.yCoord + ";" + 
				tileEntity.zCoord + "In" + 
				world.provider.dimensionId);*/
	}

	/**
	 * Use to send text message from client to server.
	 * 
	 * @param message the message to sent.
	 */
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
}
