package sfschouten.dronemod.network;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import io.netty.buffer.ByteBuf;
import sfschouten.dronemod.client.gui.GuiDroneItem;
import sfschouten.dronemod.registry.MarkerRegistry;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.util.Logger;
import sfschouten.dronemod.util.NBTTagCompWrapper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.util.Constants.NBT;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class MarkersToPlayerMessage implements IExecutableMessage {
	private MarkerRegistry registry;
	
	public MarkersToPlayerMessage(MarkerRegistry registry){
		this.registry = registry;
	}
	
	public MarkersToPlayerMessage(){
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		JsonParser parser = new JsonParser();
		Charset charset = Charset.forName("UTF-8");
		
		CharsetDecoder dec = charset.newDecoder();
		CharBuffer charBuf = null;
		try {
			charBuf = dec.decode(buf.nioBuffer());
		} catch (CharacterCodingException e) {
			e.printStackTrace();
			Logger.log("Failed decoding the bytebuffer");
		}
		
		String json = charBuf.toString();
		JsonObject obj =  (JsonObject) parser.parse(json);
		fromJsonObject(obj);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		JsonObject obj = toJsonObject();
		Charset charset = Charset.forName("UTF-8");
		CharsetEncoder enc = charset.newEncoder();
		ByteBuffer buffer = null;
		try {
			buffer = enc.encode(CharBuffer.wrap(obj.toString().toCharArray()));
		} catch (CharacterCodingException e) {
			e.printStackTrace();
			Logger.log("Wrote null to buffer 0_0");
		}
		buf.writeBytes(buffer);
	}

	@Override
	public void executeClient(EntityPlayer player) {
		GuiDroneItem gui = GuiDroneItem.currentInstance;
		gui.setRegistry(this.registry);
		Logger.info("ReInitialising GUI");
		//gui.initGui();
	}

	@Override
	public void executeServer(EntityPlayer player) {
		Logger.error("Can't execute this packet on the server side.");
	}	
	
	/**
	 * used create a byte[] representation from this packets specific properties. 
	 */
	private JsonObject toJsonObject() {
		JsonObject json = new JsonObject();
		NBTTagCompound tag = new NBTTagCompound();
		registry.writeToNBT(tag);
		NBTTagCompWrapper wrap = new NBTTagCompWrapper(tag);
		json.add("regnbt", new JsonPrimitive(wrap.toProperString()));
		return json;
	}

	private void fromJsonObject(JsonObject object) {
		String regnbt = object.get("regnbt").getAsString();
		NBTTagCompWrapper wrap = new NBTTagCompWrapper(new NBTTagCompound());
		NBTTagCompound regNbt = wrap.fromProperString(regnbt);
		this.registry = MarkerRegistry.forWorld(Minecraft.getMinecraft().theWorld);
		Logger.info(regNbt);
		this.registry.readFromNBT(regNbt);
	}
}
