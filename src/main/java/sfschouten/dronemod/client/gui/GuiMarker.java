package sfschouten.dronemod.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.init.MMDPackets;
import sfschouten.dronemod.network.ChangeMarkerMessage;
import sfschouten.dronemod.tileentity.TileEntityMarker;

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
		
		this.nameField = new GuiTextField(fontRendererObj, posX, posY, 120, 20);
		this.nameField.setFocused(false);
		this.nameField.setMaxStringLength(50);
		
		this.radiusField = new GuiTextField(fontRendererObj, posX, posY+25, 40, 20);
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
		this.drawCenteredString(fontRendererObj, "Drone Work Marker", 8, 6, 4210752);
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
	
	private void sendToggleBarrier(){
		tileEntity.toggleBarrier();
		
		ChangeMarkerMessage p = new ChangeMarkerMessage();
		p.setX(tileEntity.xCoord);
		p.setY(tileEntity.yCoord);
		p.setZ(tileEntity.zCoord);
		p.setWorldID(tileEntity.getWorldObj().provider.dimensionId);
		p.setRadius(tileEntity.getRadius());
		p.setBarrier(!tileEntity.isBarrier());
		p.setName(tileEntity.getName());
		
		MMDPackets.networkWrapper.sendToServer(p);
	}
}
