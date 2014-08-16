package sfschouten.dronemod.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import sfschouten.dronemod.block.BlockMarker;
import sfschouten.dronemod.tileentity.TileEntityMarker;

public class MarkerRegistry extends WorldSavedData {
	private static String key = "markerregistry";
	
	public MarkerRegistry() {
		super(key);
	}
	
	public MarkerRegistry(String key) {
		super(key);
	}
	
	public static MarkerRegistry forWorld(World world) {
        // Retrieves the MyWorldData instance for the given world, creating it if necessary
		MapStorage storage = world.perWorldStorage;
		MarkerRegistry result = (MarkerRegistry)storage.loadData(MarkerRegistry.class, key);
		if (result == null) {
			result = new MarkerRegistry();
			storage.setData(key, result);
		}
		result.world = world;
		return result;
	}
	
	public class Registration{
		public int x,y,z;
		public TileEntityMarker marker;
	}
	
	private List<Registration> registry = new ArrayList<Registration>();
	private World world;
	
	public List<Registration> getRegisteredMarkers(){
		return registry;
	}
	
	public void registerMarker(TileEntityMarker marker, int x, int y, int z){
		Registration r = new Registration();
		r.marker = marker;
		r.x = x;
		r.y = y;
		r.z = z;
		registry.add(r);
		markDirty();
	}
	
	public void unRegisterMarker(int x, int y, int z){
		Registration removee = null;
		for(Registration r : registry){
			if(r.x == x && r.y == y && r.z == z){
				removee = r;
			}
		}
		registry.remove(removee);
		markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbttagcompound) {
		int i = 0;
		this.registry = new ArrayList<Registration>();
		
		while(nbttagcompound.hasKey("sub"+i)){
			Registration r = new Registration();
			NBTTagCompound subTag = nbttagcompound.getCompoundTag("sub"+i);
			TileEntityMarker marker = new TileEntityMarker();
			r.marker = marker;
			r.x = marker.xCoord;
			r.y = marker.yCoord;
			r.z = marker.zCoord;
			registry.add(r);
			i++;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		for(int i = 0; i < registry.size(); i++){
			NBTTagCompound newTag = new NBTTagCompound();
			registry.get(i).marker.writeToNBT(newTag);
			nbttagcompound.setTag("sub"+i, newTag);
		}
	}
}
