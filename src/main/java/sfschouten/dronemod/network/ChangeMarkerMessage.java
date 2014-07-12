package sfschouten.dronemod.network;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

import io.netty.buffer.ByteBuf;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.util.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import cpw.mods.fml.relauncher.Side;

public class ChangeMarkerMessage implements IExecutableMessage {
	private int x, y, z;
	private int worldID;
	
	private String name;
	private int radius;
	private boolean barrier;
	
	public ChangeMarkerMessage(int x, int y, int z, int worldID, String name, int radius, boolean barrier){
	}
	
	public ChangeMarkerMessage(){
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
			Logger.logOut("Failed decoding the bytebuffer");
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
			Logger.logOut("Wrote null to buffer 0_0");
		}
		buf.writeBytes(buffer);
	}

	@Override
	public void executeClient(EntityPlayer player) {
		Logger.logOut("Can't execute this packet on the client side.");
	}

	@Override
	public void executeServer(EntityPlayer player) {
		WorldServer[] servers = MinecraftServer.getServer().worldServers;
        
        WorldServer server = null;
        for(WorldServer s : servers){
        	if(s.provider.dimensionId == worldID){
        		server = s;
        	}
        }
        
        TileEntityMarker marker = (TileEntityMarker) server.getTileEntity(x, y, z);
        marker.setRadius(radius);
        if(marker.isBarrier() != barrier){
        	marker.toggleBarrier();
        }
        marker.setName(name);
	}	
	
	/**
	 * used create a byte[] representation from this packets specific properties. 
	 */
	private JsonObject toJsonObject() {
		JsonObject json = new JsonObject();
		json.add("name", new JsonPrimitive(name));
		json.add("radius", new JsonPrimitive(radius));
		json.add("barrier", new JsonPrimitive(barrier));
		
		json.add("x", new JsonPrimitive(x));
		json.add("y", new JsonPrimitive(y));
		json.add("z", new JsonPrimitive(z));
		
		json.add("worldID", new JsonPrimitive(worldID));
		
		return json;
	}

	private void fromJsonObject(JsonObject object) {
		this.barrier = object.get("barrier").getAsBoolean();
		this.name = object.get("name").getAsString();
		this.radius = object.get("radius").getAsInt();
		
		this.x = object.get("x").getAsInt();
		this.y = object.get("y").getAsInt();
		this.z = object.get("z").getAsInt();
		
		this.worldID = object.get("worldID").getAsInt();
	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int z) {
		this.z = z;
	}

	public int getWorldID() {
		return worldID;
	}

	public void setWorldID(int worldID) {
		this.worldID = worldID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public boolean isBarrier() {
		return barrier;
	}

	public void setBarrier(boolean barrier) {
		this.barrier = barrier;
	}
}
