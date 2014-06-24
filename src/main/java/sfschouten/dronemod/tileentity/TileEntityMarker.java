package sfschouten.dronemod.tileentity;

import java.util.Random;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.client.particle.EntityDroneFX;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

public class TileEntityMarker extends TileEntity{
	private String name;
	private int radius;
	private boolean barrier;
	private int barPos;
	
	public TileEntityMarker(){
		radius = 4;
		name = "";
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
	
	public void toggleBarrier(){
		toggleBarriers();
		if(barrier){
			barrier = false;
		}else{
			barrier = true;
		}
	}
	
	public boolean isBarrier() {
		return barrier;
	}

	public void setBarrier(boolean barrier) {
		this.barrier = barrier;
	}

	@Override
	public void updateEntity() {
		if(barrier){
			switch(barPos){
			case 0:
				spawnBarrierDrones();
				break;
			case 3:
				spawnBarrierDrones();
				break;
			case 6:
				spawnBarrierDrones();
				break;
			}
		}
		
		if(++barPos == 9){
			barPos = 0;
		}
		super.updateEntity();
	}

	@Override
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		barrier = par1nbtTagCompound.getBoolean("barrier");
		super.readFromNBT(par1nbtTagCompound);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound par1nbtTagCompound) {
		par1nbtTagCompound.setBoolean("barrier", barrier);
		super.writeToNBT(par1nbtTagCompound);
	}
	
	private void spawnBarrierDrone(double x, double y, double z, double motionX, double motionZ){
		Random rand = new Random();
		double height = (double)y + 2 - ((double)rand.nextInt(19))/10;
		
		EntityDroneFX newDrone = new EntityDroneFX(worldObj);
		newDrone.setPosition(x, height, z);
		
		newDrone.motionX = motionX;
		newDrone.motionZ = motionZ;
		worldObj.spawnEntityInWorld(newDrone);
	}
	
	private void spawnBarrierDrones(){
		spawnBarrierDrone(this.xCoord+radius+1.5, this.yCoord, this.zCoord+radius+1.5, 0, -1);
		spawnBarrierDrone(this.xCoord+radius+1.5, this.yCoord, this.zCoord-radius-0.5, -1, 0);
		spawnBarrierDrone(this.xCoord-radius-0.5, this.yCoord, this.zCoord+radius+1.5, 1, 0);
		spawnBarrierDrone(this.xCoord-radius-0.5, this.yCoord, this.zCoord-radius-0.5, 0, 1);
	}
	
	private void toggleBarriers(){
		System.out.println("toggle barriers with radius: "+ this.radius);
		int radius = this.radius + 1;
		int radius2 = radius*2;
		int circumference = 8*radius;
		
		for(int t = 0; t < circumference; t++){
			int currentX = xCoord + radius;
			int currentZ = zCoord + radius;
			int currentY = yCoord + 1;
			
			//Determine coords based on currentblock and currentradius.
			if(((double)t) / 4 < (0.5D * radius)){
				currentX -= t;
			}else if(((double)t) / 4  < radius){
				currentX -= radius2;
				currentZ -= t - radius2;
			}else if(((double)t) / 4  < (1.5D * radius)){
				currentX -= radius2 - (t % radius2);
				currentZ -= radius2;
			}else{
				currentZ -= radius2 - (t % radius2);
			}
			
			if(worldObj.getBlock(currentX, currentY, currentZ) == DroneMod.markerBarrier){
				worldObj.setBlock(currentX, currentY, currentZ, Blocks.air);
			}else if(worldObj.getBlock(currentX, currentY, currentZ) == Blocks.air){
				worldObj.setBlock(currentX, currentY, currentZ, DroneMod.markerBarrier);
			}
			
			if(worldObj.getBlock(currentX, currentY+1, currentZ) == DroneMod.markerBarrier){
				worldObj.setBlock(currentX, currentY+1, currentZ, Blocks.air);
			}else if(worldObj.getBlock(currentX, currentY+1, currentZ) == Blocks.air){
				worldObj.setBlock(currentX, currentY+1, currentZ, DroneMod.markerBarrier);
			}
		}
	}
}
