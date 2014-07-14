package sfschouten.dronemod.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import sfschouten.dronemod.inventory.InventoryType;
import sfschouten.dronemod.util.Logger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public abstract class ModelCopter extends ModelBase {
	HashMap<String, ModelRenderer> parts;
	float scale;
	
	protected float[][] chestPositions;
	protected float[][] batteryPositions;
	protected float[][] modulePositions;
	/** The middle of the copter, all other positions are relative to this one.*/
	protected float[] defaultPosition;
	
	public ModelCopter(float scale) {
		this.parts = new HashMap<String, ModelRenderer>();
		this.scale = scale;
		this.textureWidth = 64;
		this.textureHeight = 64;
		initParts();
		initPositions();
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		render(entity, f, f1, f2, f3, f4, f5, true);
	}
	
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5, boolean rotate){
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		
		if(rotate){
			rotateBlades(12);
		}
		for (Entry<String, ModelRenderer> r : parts.entrySet()) {
			r.getValue().render(f5);
		}
	}
	
	/**
	 * Make the blades of this copter rotate with the given speed when called.
	 * So add the speed to the y rotation of the blades.(if the blades are positioned horizontally.)
	 * @param speed The speed at which the blades are supposed to be spinning.
	 */
	protected abstract void rotateBlades(float speed);
	
	/**
	 * Initialize all the position variables here.
	 * Make sure there are enough positions for all 
	 */
	protected abstract void initPositions();
	
	/**
	 * Put all the parts of this model that need to be rendered  in the parts hashmap.
	 */
	protected abstract void initParts();
	
	
	public float[][] getPositions(InventoryType t){
		switch(t){
		case battery:
			return getBatteryPositions();
		case chest:
			return getChestPositions();
		case free:
			return null;
		case module:
			return getModulePositions();
		default:
			return null;
		}
	}
	
	public float[][] getChestPositions() {
		return chestPositions;
	}

	public float[][] getBatteryPositions() {
		return batteryPositions;
	}

	public float[][] getModulePositions() {
		return modulePositions;
	}
	
	public float[] getDefaultPosition(){
		return defaultPosition;
	}
}
