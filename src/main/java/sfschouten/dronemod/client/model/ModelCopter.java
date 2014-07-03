package sfschouten.dronemod.client.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import sfschouten.dronemod.Logger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public abstract class ModelCopter extends ModelBase {
	HashMap<String, ModelRenderer> parts;
	float scale;

	public ModelCopter(float scale) {
		this.parts = new HashMap<String, ModelRenderer>();
		this.scale = scale;
		this.textureWidth = 64;
		this.textureHeight = 64;
		initParts();
	}
	
	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		float scale = f5 * this.scale;
		rotateBlades(12);
		
		for (Entry<String, ModelRenderer> r : parts.entrySet()) {
			r.getValue().render(scale);
		}
	}

	protected abstract void rotateBlades(float speed);
	
	protected abstract void initParts();
}