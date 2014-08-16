package sfschouten.dronemod.client.gui;

import java.util.List;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import org.lwjgl.opengl.GL11;

import sfschouten.dronemod.ModularMulticopterDrones;
import sfschouten.dronemod.init.MMDPackets;
import sfschouten.dronemod.inventory.ContainerDroneItem;
import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.inventory.SimpleInventory;
import sfschouten.dronemod.item.copter.ItemDrone;
import sfschouten.dronemod.network.AddMarkerToItemMessage;
import sfschouten.dronemod.network.LaunchDroneMessage;
import sfschouten.dronemod.reference.General;
import sfschouten.dronemod.registry.MarkerRegistry;
import sfschouten.dronemod.registry.MarkerRegistry.Registration;
import sfschouten.dronemod.util.Logger;

public class GuiDroneItem extends GuiContainer {
	public static GuiDroneItem currentInstance;
	MarkerRegistry registery;
	ItemStack stack;
	GuiScrollList scrollList;
	World world;
	
	public GuiDroneItem(EntityPlayer player, ItemStack stack, World w) {
		super(new ContainerDroneItem(player, stack));
		this.stack = stack;
		this.world = w;
		currentInstance = this;
		
		WorldServer[] servers = MinecraftServer.getServer().worldServers;
        WorldServer server = null;
        for(WorldServer s : servers){
        	if(s.provider.dimensionId == w.provider.dimensionId){
        		server = s;
        	}
        }
	}

	@Override
	public void initGui() {
		super.initGui();
		int posX = (this.width - 161) / 2;
		int posY = (this.height - 173) / 2;
		
		this.scrollList = new GuiScrollList(fontRendererObj, posX+80, posY+10, 80, 130);

		NBTTagCompound stackTag = stack.getTagCompound();
		NBTTagCompound markerTag;
		if(stackTag != null){
			markerTag = stackTag.getCompoundTag("Marker");
		}else{
			markerTag = new NBTTagCompound();
		}
		int x = markerTag.getInteger("x");
		int y = markerTag.getInteger("y");
		int z = markerTag.getInteger("z");

		Logger.info("initGUI");
		
		if(this.registery != null){
			Logger.info("registry is not null");
			List<Registration> markers = this.registery.getRegisteredMarkers();
			
			for(int i = 0; i < markers.size(); i++){
				Logger.info("Marker " + i);
				Registration r = markers.get(i);
				String s;
				if(r.marker.getName() == null || r.marker.getName().isEmpty()){
					s = r.x + ", " + r.y + ", " + r.z;
				}else{
					s = r.marker.getName();
				}
				this.scrollList.addItem(s);
				
				if(r.x == x && r.y == y && r.z == z){
					this.scrollList.setSelected(i);
				}
			}
		}
		Logger.info("\n");
	}
	
	@Override
	public void onGuiClosed() {
		if(this.registery != null){
			List<Registration> markers = this.registery.getRegisteredMarkers();
			int selected = scrollList.getSelected();
			if(selected != -1 && selected < markers.size()){
				Registration r = markers.get(selected);
				AddMarkerToItemMessage message = new AddMarkerToItemMessage();
				message.setWorldID(world.provider.dimensionId);
				message.setX(r.x);
				message.setY(r.y);
				message.setZ(r.z);
				MMDPackets.networkWrapper.sendToServer(message);
			}
		}
		super.onGuiClosed();
	}

	@Override
	protected void mouseClicked(int par1, int par2, int par3) {
		scrollList.mouseClicked(par1, par2, par3);
		super.mouseClicked(par1, par2, par3);
	}

	@Override
	protected void drawGuiContainerForegroundLayer(int param1, int param2) {
		fontRendererObj.drawString("Drone", 8, 6, 4210752);
		
		for(InventoryType type : InventoryType.values()){
			fontRendererObj.drawString(type.name(), 8, 16+(type.ordinal()*25), 4210752);
		}
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3) {
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
		
		scrollList.drawScrollList();
	}
	
	public void setRegistry(MarkerRegistry registry){
		this.registery = registry;
	}
}
