package sfschouten.dronemod.registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import sfschouten.dronemod.block.BlockMarker;
import sfschouten.dronemod.tileentity.TileEntityMarker;

public class MarkerRegistry extends WorldSavedData {
	private static String key = "markerregistry";
	
	public MarkerRegistry(String par1Str) {
		super(par1Str);
	}
	
	public static MarkerRegistry forWorld(World world) {
        // Retrieves the MyWorldData instance for the given world, creating it if necessary
		MapStorage storage = world.perWorldStorage;
		MarkerRegistry result = (MarkerRegistry)storage.loadData(MarkerRegistry.class, key);
		if (result == null) {
			result = new MarkerRegistry(key);
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
		while(nbttagcompound.hasKey("x"+i)){
			Registration r = new Registration();
			r.x = nbttagcompound.getInteger("x"+i);
			r.y = nbttagcompound.getInteger("y"+i);
			r.z = nbttagcompound.getInteger("z"+i);
			r.marker = (TileEntityMarker) world.getTileEntity(r.x, r.y, r.z);
			registry.add(r);
			i++;
		}
	}

	@Override
	public void writeToNBT(NBTTagCompound nbttagcompound) {
		for(int i = 0; i < registry.size(); i++){
			nbttagcompound.setInteger("x"+i, registry.get(i).x);
			nbttagcompound.setInteger("y"+i, registry.get(i).y);
			nbttagcompound.setInteger("z"+i, registry.get(i).z);
		}
	}
}
