package sfschouten.dronemod.pathfinding;

import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.MathHelper;

/**
 * Class to wrap a PathPoint in so the private values are easily modified through reflection.
 * 
 * @author Stefan
 *
 */
public class PathPointWrapper {
	/** The PathPoint being wrapped. */
	private PathPoint pathPoint;
	private static final Class PATH_POINT = PathPoint.class;

	public PathPointWrapper(PathPoint pathPoint){
		this.pathPoint = pathPoint;
	}

	public PathPoint getWrapped(){
		return pathPoint;
	}
	
	/**
	 * Returns the linear distance to another path point
	 */
	public float distanceTo(PathPoint par1PathPoint) {
		return this.pathPoint.distanceTo(par1PathPoint);
	}

	/**
	 * Returns the squared distance to another path point
	 */
	public float distanceToSquared(PathPoint par1PathPoint) {
		return this.pathPoint.distanceToSquared(par1PathPoint);
	}

	public boolean equals(Object par1Obj) {
		return this.pathPoint.equals(par1Obj);
	}

	public int hashCode() {
		return this.pathPoint.hashCode();
	}

	public void setFirst(boolean isFirst){
		pathPoint.isFirst = isFirst;
	}
	
	public boolean getFirst(){
		return pathPoint.isFirst;
	}
	
	/**
	 * Returns true if this point has already been assigned to a path
	 */
	public boolean isAssigned() {
		return this.pathPoint.isAssigned();
	}

	public String toString() {
		return this.pathPoint.toString();
	}
	
	public float getTotalPathDistance(){
		return ReflectionHelper.getPrivateValue(PATH_POINT, pathPoint, "totalPathDistance");
	}
	
	public float getDistanceToNext(){
		return ReflectionHelper.getPrivateValue(PATH_POINT, pathPoint, "distanceToNext");
	}
	
	public float getDistanceToTarget(){
		return ReflectionHelper.getPrivateValue(PATH_POINT, pathPoint, "distanceToTarget");
	}
	
	public PathPoint getPrevious(){
		return ReflectionHelper.getPrivateValue(PATH_POINT, pathPoint, "previous");
	}
	
	public void setTotalPathDistance(float totalPathDistance){
		ReflectionHelper.setPrivateValue(PATH_POINT, pathPoint, totalPathDistance, "totalPathDistance");
	}
	
	public void setDistanceToNext(float distanceToNext){
		ReflectionHelper.setPrivateValue(PATH_POINT, pathPoint, distanceToNext, "distanceToNext");
	}
	
	public void setDistanceToTarget(float distanceToTarget){
		ReflectionHelper.setPrivateValue(PATH_POINT, pathPoint, distanceToTarget, "distanceToTarget");
	}
	
	public void setPrevious(PathPoint previous){
		ReflectionHelper.setPrivateValue(PATH_POINT, pathPoint, previous, "previous");
	}
}