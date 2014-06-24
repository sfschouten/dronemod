package sfschouten.dronemod.network;

import io.netty.buffer.ByteBuf;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.tileentity.TileEntityMarker;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.WorldServer;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

import cpw.mods.fml.relauncher.Side;

public class ChangeMarkerPacket implements IPacket {
	private int x, y, z;
	private int worldID;
	
	private String name;
	private int radius;
	private boolean barrier;
	
	/**
	 * Use Packet.constructPacket()
	 */
	protected ChangeMarkerPacket(){
		
	}

	@Override
	public void readBytes(ByteBuf bytes) {
		JsonParser parser = new JsonParser();
		JsonObject obj =  (JsonObject) parser.parse(bytes.toString());
		fromJsonObject(obj);
	}

	@Override
	public void writeBytes(ByteBuf bytes) {
		JsonObject obj = toJsonObject();
		bytes.writeBytes(obj.toString().getBytes());
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
