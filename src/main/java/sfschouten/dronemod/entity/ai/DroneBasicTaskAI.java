package sfschouten.dronemod.entity.ai;

import java.text.DecimalFormat;

import net.minecraft.nbt.NBTTagCompound;
import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityDrone;
import sfschouten.dronemod.item.module.DroneTaskResult;
import sfschouten.dronemod.item.module.DroneTaskSubject;
import sfschouten.dronemod.item.module.ItemAdvancedTaskModule;
import sfschouten.dronemod.item.module.ItemTaskModule;
import sfschouten.dronemod.tileentity.TileEntityMarker;

public class DroneBasicTaskAI extends DroneAI {
	private int currentRadius = -1;
	private int currentBlock = 0;
	private int currentDepth = 0;
	
	@Override
	public boolean executeAI(EntityDrone e) {
		if (!e.getWorkMarkers().isEmpty()){
			if(e.getCurrentWork() == null){
	    		e.setCurrentWork(e.getWorkMarkers().get(0));
	    	}
			
			TileEntityMarker currentWork = e.getCurrentWork();
			if(currentRadius == -1){
				currentRadius = currentWork.getRadius();
			}
			
			int intPosX = (int)e.posX;
			int intPosY = (int)e.posY;
			int intPosZ = (int)e.posZ;
			
			int currentX = currentWork.xCoord + currentRadius;
			int currentZ = currentWork.zCoord + currentRadius;
			int currentY = currentWork.yCoord - currentDepth;
			
			//Determine coords based on currentblock and currentradius.
			if(((double)currentBlock) / 4 < (0.5D * currentRadius)){
				currentX -= currentBlock;
			}else if(((double)currentBlock) / 4  < currentRadius){
				currentX -= (2*currentRadius);
				currentZ -= (currentBlock - (2*currentRadius));
			}else if(((double)currentBlock) / 4  < (1.5D * currentRadius)){
				currentX -= ((2*currentRadius) - (currentBlock % (2*currentRadius)));
				currentZ -= (2*currentRadius);
			}else{
				currentZ -= ((2*currentRadius) - (currentBlock % (2*currentRadius)));
			}
			
			if(	!(currentX + 0.2 < e.posX && currentX + 0.8 > e.posX)|| 
				!(currentZ + 0.2 < e.posZ && currentZ + 0.8 > e.posZ)|| 
				!(currentY + 1.2 < e.posY && currentY + 1.8 > e.posY)){
				//Drone has not arrived where he needs to be so move towards it.
				Logger.logOut("startmovingtowards x:"+currentX + "y:"+(currentY+1) + "z:" + currentZ);
				e.startMovingTowards(currentX, currentY+1, currentZ);
			}else{
				Logger.logOut("perform task at x:"+currentX + "y:"+currentY + "z:" + currentZ);
				
				ItemTaskModule firstNotAdvancedModule = null;
				for(ItemTaskModule m : e.getModules()){
					if(!(m instanceof ItemAdvancedTaskModule)){
						firstNotAdvancedModule = m;
					}
				}
				if(firstNotAdvancedModule == null){
					return true;
				}
				
				DroneTaskResult result = firstNotAdvancedModule.performTask(e, new DroneTaskSubject());
				switch(result){
	    		case resourcelow:
	    			Logger.logOut("Resource Low");
	    			e.restock();
					break;
				case success:
					Logger.logOut("Success");
					break;
				case wrongEnvironment:
					Logger.logOut("Wrong Environment");
					break;
				}
				
				//Get circumference
				int cf = (int)(Math.pow((1+(2*currentRadius)), 2) - Math.pow((1+(2*(currentRadius-1))), 2))-1;
				if(currentBlock < cf){
					//Not at the end of this round so one up currentblock.
					currentBlock += 1;
				}else{
					//Round complete so set currentblock to 0.
					currentBlock = 0;
					if(currentRadius > 1){
						currentRadius -= 1;
					}else{
						if(e.getModules().get(0).hasDepth() /*&& result == DroneTaskResult.success*/){
							currentDepth++;
						}else{
							int currentPos = e.getWorkMarkers().lastIndexOf(currentWork);
							if(e.getWorkMarkers().size()-1 != currentPos){
								currentPos++;
								currentWork = e.getWorkMarkers().get(currentPos);
							}else if(e.getWorkMarkers().size() > 1){
								currentWork = e.getWorkMarkers().get(0);
							}
						}
						currentRadius = currentWork.getRadius();
					}
				}
			}
			return false;
		}else{
			return true;
		}
	}
	
	public void writeToNBT(NBTTagCompound par1nbtTagCompound){
    	par1nbtTagCompound.setInteger("CurrentBlock", currentBlock);
    	par1nbtTagCompound.setInteger("CurrentRadius", currentRadius);
	}
	
	public void readFromNBT(NBTTagCompound par1nbtTagCompound) {
		currentBlock = par1nbtTagCompound.getInteger("CurrentBlock");
		currentRadius = par1nbtTagCompound.getInteger("CurrentRadius");
		
	}
	
	public int getCurrentRadius() {
		return currentRadius;
	}

	public void setCurrentRadius(int currentRadius) {
		this.currentRadius = currentRadius;
	}

	public int getCurrentBlock() {
		return currentBlock;
	}

	public void setCurrentBlock(int currentBlock) {
		this.currentBlock = currentBlock;
	}
}
