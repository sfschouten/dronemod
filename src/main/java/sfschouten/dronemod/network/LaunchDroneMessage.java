package sfschouten.dronemod.network;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;

import io.netty.buffer.ByteBuf;
import sfschouten.dronemod.tileentity.TileEntityDroneBase;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import sfschouten.dronemod.util.Logger;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.relauncher.Side;

public class LaunchDroneMessage implements IExecutableMessage {
	/**
	 * Coordinates of the tile entity that is the drone base of the drone that needs to return to this base.
	 */
	private int x, y, z;
	/**
	 * The ID of the world in which the dronebase is that needs to have its drone return.
	 */
	private int worldID;
	
	public LaunchDroneMessage(){
		
	}
	
	public LaunchDroneMessage(int x, int y, int z, int worldID){
		this.worldID = worldID;
		this.x = x;
		this.y = y;
		this.z = z;
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
		Logger.log("Can't execute this packet on the client side.");
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
        
        TileEntityDroneBase base = (TileEntityDroneBase) server.getTileEntity(x, y, z);
        if(base == null){
        	//TODO throw exception
        }else{
        	base.spawnDrone();
        }
	}	
	
	/**
	 * used create a byte[] representation from this packets specific properties. 
	 */
	private JsonObject toJsonObject() {
		JsonObject json = new JsonObject();
		
		json.add("x", new JsonPrimitive(x));
		json.add("y", new JsonPrimitive(y));
		json.add("z", new JsonPrimitive(z));
		
		json.add("worldID", new JsonPrimitive(worldID));
		
		return json;
	}

	private void fromJsonObject(JsonObject object) {
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
}
