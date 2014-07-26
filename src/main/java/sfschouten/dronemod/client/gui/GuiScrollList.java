package sfschouten.dronemod.client.gui;

import java.util.ArrayList;
import java.util.List;

import sfschouten.dronemod.util.Logger;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

public class GuiScrollList extends Gui {
	private final FontRenderer fontRenderer;
	private final int xPosition;
	private final int yPosition;
	/** The width of this text field. */
	private final int width;
	private final int height;
	
	private boolean visible = true;
	
	private List<String> items;
	private int selected = -1;

	public GuiScrollList(FontRenderer par1FontRenderer, int par2, int par3, int par4, int par5) {
		this.fontRenderer = par1FontRenderer;
		this.xPosition = par2;
		this.yPosition = par3;
		this.width = par4;
		this.height = par5;
		this.items = new ArrayList<String>();
	}

	public void drawScrollList(){
		if(this.isVisible()){
			drawRect((this.xPosition - 1), (this.yPosition - 1), (this.xPosition + this.width + 1), (this.yPosition + this.height + 1), -6250336);
            drawRect(this.xPosition, this.yPosition, (this.xPosition + this.width), (this.yPosition + this.height), -16777216);
            
            for(int i = 0; i < items.size(); i++){
            	int iy = (i * 12) + yPosition;
            	if(i == selected){
            		drawRect(this.xPosition, iy, (this.xPosition + this.width), (iy + 12), -6250336);
            		drawRect(this.xPosition + 1, iy + 1, (this.xPosition + this.width - 1), (iy + 11), -16777216);
            	}
            	this.fontRenderer.drawString(items.get(i), xPosition + 2, iy + 2, -6250336);
            }
        }
	}
		
	public boolean isVisible() {
		return visible;
	}
	
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	
	public void addItem(String item){
		items.add(item);
	}

	public void mouseClicked(int par1, int par2, int par3) {
		if((par1 > this.xPosition && par1 < (this.xPosition + width)) && (par2 > this.yPosition && par2 < (this.yPosition + height))){
			int rx = par1 - this.xPosition;
			int ry = par2 - this.yPosition;
			
			selected = ry / 12;
		}
	}
	
	public int getSelected(){
		return selected;
	}
	
	public void setSelected(int selected){
		this.selected = selected;
	}
}