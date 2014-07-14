package sfschouten.dronemod.client.model;

import sfschouten.dronemod.DroneMod;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelBattery extends ModelExpansion {
	ModelRenderer Body;
	ModelRenderer Thing1;
	ModelRenderer Thing2;

	public ModelBattery() {
		textureWidth = 32;
		textureHeight = 32;

		texture = new ResourceLocation(DroneMod.modID, "textures/entity/battery.png");
		
		Body = new ModelRenderer(this, 0, 0);
		Body.addBox(0F, 0F, 0F, 6, 4, 4);
		Body.setRotationPoint(-4F, 17F, 0F);
		Body.setTextureSize(32, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);

		Thing1 = new ModelRenderer(this, 0, 9);
		Thing1.addBox(0F, 0F, 0F, 1, 1, 1);
		Thing1.setRotationPoint(-3F, 16F, 1.5F);
		Thing1.setTextureSize(32, 32);
		Thing1.mirror = true;
		setRotation(Thing1, 0F, 0F, 0F);

		Thing2 = new ModelRenderer(this, 0, 9);
		Thing2.addBox(0F, 0F, 0F, 1, 1, 1);
		Thing2.setRotationPoint(0F, 16F, 1.5F);
		Thing2.setTextureSize(32, 32);
		Thing2.mirror = true;
		setRotation(Thing2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		Body.render(f5);
		Thing1.render(f5);
		Thing2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
}
