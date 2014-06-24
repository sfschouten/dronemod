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

public class DroneAdvancedTaskAI extends DroneAI {
	private int currentRadius = -1;
	
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
			
			ItemAdvancedTaskModule closestTaskModule = null;
			DroneTaskSubject closestSubject = null;
			float closestDistance = Float.MAX_VALUE;
			
			for(ItemTaskModule m : e.getModules()){
				if(m instanceof ItemAdvancedTaskModule){
					DroneTaskSubject s = ((ItemAdvancedTaskModule)m).findNearestTask(e);
					if(s != null){
						float distance = e.getDistanceToEntity(s.getEntity());
						if(distance < closestDistance){
							closestTaskModule = (ItemAdvancedTaskModule) m;
							closestDistance = distance;
							closestSubject = s;
						}
					}
				}
			}
			
			if(closestSubject == null){
				return false;
			}
			
			double currentX = closestSubject.getX();
			double currentY = closestSubject.getY(); 
			double currentZ = closestSubject.getZ();
			
			//Make sure the currentXYZ are already a 'double' coordinate so not 1,1,1 but 1.5,1,1.5
			if(	!(currentX - 0.5 < e.posX && currentX + 0.5 > e.posX)|| 
				!(currentZ - 0.5 < e.posZ && currentZ + 0.5 > e.posZ)|| 
				!(currentY + 0.5 < e.posY && currentY + 1.5 > e.posY)){
				//Drone has not arrived where he needs to be so move towards it.
				Logger.logOut("startmovingtowards x:"+currentX + "y:"+currentY+1 + "z:" + currentZ);
				e.startMovingTowards((int)currentX, (int)currentY+1, (int)currentZ);
			}else{
				Logger.logOut("perform task at x:"+currentX + "y:"+currentY + "z:" + currentZ);
				DroneTaskResult result = closestTaskModule.performTask(e, closestSubject);
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
			}
			return false;
		}else{
			return true;
		}
	}
}
