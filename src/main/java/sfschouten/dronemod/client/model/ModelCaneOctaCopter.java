package sfschouten.dronemod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelCaneOctaCopter extends ModelCopter {

	public ModelCaneOctaCopter(float scale) {
		super(scale);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

	@Override
	protected void rotateBlades(float speed) {
		parts.get("blade1").rotateAngleY += speed;
		parts.get("blade2").rotateAngleY += speed;
		parts.get("blade3").rotateAngleY += speed;
		parts.get("blade4").rotateAngleY += speed;
		parts.get("blade5").rotateAngleY += speed;
		parts.get("blade6").rotateAngleY += speed;
		parts.get("blade7").rotateAngleY += speed;
		parts.get("blade8").rotateAngleY += speed;
	}

	@Override
	protected void initParts() {
		ModelRenderer body1;
		body1 = new ModelRenderer(this, 0, 0);
		body1.addBox(0F, 0F, 0F, 1, 1, 16);
		body1.setRotationPoint(7F, 15F, -8F);
		body1.setTextureSize(64, 64);
		body1.mirror = true;
		setRotation(body1, 0F, 0F, 0F);
		parts.put("body1", body1);
		
		ModelRenderer body2;
		body2 = new ModelRenderer(this, 0, 0);
		body2.addBox(0F, 0F, 0F, 1, 1, 16);
		body2.setRotationPoint(-8F, 15F, -8F);
		body2.setTextureSize(64, 64);
		body2.mirror = true;
		setRotation(body2, 0F, 0F, 0F);
		parts.put("body2", body2);
		
		ModelRenderer body3;
		body3 = new ModelRenderer(this, 0, 0);
		body3.addBox(0F, 0F, 0F, 1, 1, 16);
		body3.setRotationPoint(-8F, 15F, -7F);
		body3.setTextureSize(64, 64);
		body3.mirror = true;
		setRotation(body3, 0F, 1.570796F, 0F);
		parts.put("body3", body3);
		
		ModelRenderer body4;
		body4 = new ModelRenderer(this, 0, 0);
		body4.addBox(0F, 0F, 0F, 1, 1, 16);
		body4.setRotationPoint(-8F, 15F, 8F);
		body4.setTextureSize(64, 64);
		body4.mirror = true;
		setRotation(body4, 0F, 1.570796F, 0F);
		parts.put("body4", body4);
		
		ModelRenderer body5;
		body5 = new ModelRenderer(this, 0, 0);
		body5.addBox(0F, 0F, 0F, 1, 1, 16);
		body5.setRotationPoint(-8F, 15F, 0.5F);
		body5.setTextureSize(64, 64);
		body5.mirror = true;
		setRotation(body5, 0F, 1.570796F, 0F);
		parts.put("body5", body5);
		
		ModelRenderer body6;
		body6 = new ModelRenderer(this, 0, 0);
		body6.addBox(0F, 0F, 0F, 1, 1, 16);
		body6.setRotationPoint(-0.5F, 15F, -8F);
		body6.setTextureSize(64, 64);
		body6.mirror = true;
		setRotation(body6, 0F, 0F, 0F);
		parts.put("body6", body1);
		
		ModelRenderer cameraStick;
		cameraStick = new ModelRenderer(this, 38, 61);
		cameraStick.addBox(0F, 0F, 0F, 1, 2, 1);
		cameraStick.setRotationPoint(-0.5F, 13F, -0.5F);
		cameraStick.setTextureSize(64, 64);
		cameraStick.mirror = true;
		setRotation(cameraStick, 0F, 0F, 0F);
		parts.put("cameraStick", cameraStick);
		
		ModelRenderer camera;
		camera = new ModelRenderer(this, 48, 57);
		camera.addBox(-2F, -2F, -2F, 4, 3, 4);
		camera.setRotationPoint(0F, 12F, 0F);
		camera.setTextureSize(64, 32);
		camera.mirror = true;
		setRotation(camera, 0F, 0F, 0F);
		parts.put("camera", camera);
		
		ModelRenderer cameraLens;
		cameraLens = new ModelRenderer(this, 43, 62);
		cameraLens.addBox(0F, 0F, 0F, 1, 1, 1);
		cameraLens.setRotationPoint(-0.5F, 11F, 2F);
		cameraLens.setTextureSize(64, 32);
		cameraLens.mirror = true;
		setRotation(cameraLens, 0F, 0F, 0F);
		parts.put("cameraLens", cameraLens);
		
		ModelRenderer wing1;
		wing1 = new ModelRenderer(this, 0, 17);
		wing1.addBox(-6F, 0F, 0F, 10, 1, 1);
		wing1.setRotationPoint(-12F, 15F, 7F);
		wing1.setTextureSize(64, 32);
		wing1.mirror = true;
		setRotation(wing1, 0F, 0F, 0F);
		parts.put("wing1", wing1);
		
		ModelRenderer blade1;
		blade1 = new ModelRenderer(this, 0, 20);
		blade1.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade1.setRotationPoint(-17.5F, 14F, 7.5F);
		blade1.setTextureSize(64, 32);
		blade1.mirror = true;
		setRotation(blade1, 0F, 1.570796F, 0F);
		parts.put("blade1", blade1);
		
		ModelRenderer wing2;
		wing2 = new ModelRenderer(this, 0, 23);
		wing2.addBox(0F, 0F, 0F, 10, 1, 1);
		wing2.setRotationPoint(-18F, 15F, -8F);
		wing2.setTextureSize(64, 32);
		wing2.mirror = true;
		setRotation(wing2, 0F, 0F, 0F);
		parts.put("wing2", wing2);
		
		ModelRenderer blade2;
		blade2 = new ModelRenderer(this, 0, 26);
		blade2.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade2.setRotationPoint(-17.5F, 14F, -7.5F);
		blade2.setTextureSize(64, 32);
		blade2.mirror = true;
		setRotation(blade2, 0F, 1.570796F, 0F);
		parts.put("blade2", blade2);
		
		ModelRenderer wing3;
		wing3 = new ModelRenderer(this, 0, 29);
		wing3.addBox(0F, 0F, 0F, 10, 1, 1);
		wing3.setRotationPoint(-8F, 15F, -8F);
		wing3.setTextureSize(64, 32);
		wing3.mirror = true;
		setRotation(wing3, 0F, 1.570796F, 0F);
		parts.put("wing3", wing3);
		
		ModelRenderer blade3;
		blade3 = new ModelRenderer(this, 0, 32);
		blade3.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade3.setRotationPoint(-7.5F, 14F, -17.5F);
		blade3.setTextureSize(64, 32);
		blade3.mirror = true;
		setRotation(blade3, 0F, 1.570796F, 0F);
		parts.put("blade3", blade3);
		
		ModelRenderer wing4;
		wing4 = new ModelRenderer(this, 0, 35);
		wing4.addBox(0F, 0F, 0F, 10, 1, 1);
		wing4.setRotationPoint(-8F, 15F, 18F);
		wing4.setTextureSize(64, 32);
		wing4.mirror = true;
		setRotation(wing4, 0F, 1.570796F, 0F);
		parts.put("wing4", wing4);
		
		ModelRenderer blade4;
		blade4 = new ModelRenderer(this, 0, 38);
		blade4.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade4.setRotationPoint(-7.5F, 14F, 17.5F);
		blade4.setTextureSize(64, 32);
		blade4.mirror = true;
		setRotation(blade4, 0F, 1.570796F, 0F);
		parts.put("blade4", blade4);
		
		ModelRenderer wing5;
		wing5 = new ModelRenderer(this, 0, 41);
		wing5.addBox(0F, 0F, 0F, 10, 1, 1);
		wing5.setRotationPoint(7F, 15F, 18F);
		wing5.setTextureSize(64, 32);
		wing5.mirror = true;
		setRotation(wing5, 0F, 1.570796F, 0F);
		parts.put("wing5", wing5);
		
		ModelRenderer blade5;
		blade5 = new ModelRenderer(this, 0, 44);
		blade5.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade5.setRotationPoint(7.5F, 14F, 17.5F);
		blade5.setTextureSize(64, 32);
		blade5.mirror = true;
		setRotation(blade5, 0F, 1.570796F, 0F);
		parts.put("blade5", blade5);
		
		ModelRenderer wing6;
		wing6 = new ModelRenderer(this, 0, 47);
		wing6.addBox(0F, 0F, 0F, 10, 1, 1);
		wing6.setRotationPoint(8F, 15F, 7F);
		wing6.setTextureSize(64, 32);
		wing6.mirror = true;
		setRotation(wing6, 0F, 0F, 0F);
		parts.put("wing6", wing6);
		
		ModelRenderer blade6;
		blade6 = new ModelRenderer(this, 0, 50);
		blade6.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade6.setRotationPoint(17.5F, 14F, 7.5F);
		blade6.setTextureSize(64, 32);
		blade6.mirror = true;
		setRotation(blade6, 0F, 1.570796F, 0F);
		parts.put("blade6", blade6);
		
		ModelRenderer wing7;
		wing7 = new ModelRenderer(this, 0, 53);
		wing7.addBox(0F, 0F, 0F, 10, 1, 1);
		wing7.setRotationPoint(8F, 15F, -8F);
		wing7.setTextureSize(64, 32);
		wing7.mirror = true;
		setRotation(wing7, 0F, 0F, 0F);
		parts.put("wing7", wing7);
		
		ModelRenderer blade7;
		blade7 = new ModelRenderer(this, 0, 56);
		blade7.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade7.setRotationPoint(17.5F, 14F, -7.5F);
		blade7.setTextureSize(64, 32);
		blade7.mirror = true;
		setRotation(blade7, 0F, 1.570796F, 0F);
		parts.put("blade7", blade7);
		
		ModelRenderer wing8;
		wing8 = new ModelRenderer(this, 0, 59);
		wing8.addBox(0F, 0F, 0F, 10, 1, 1);
		wing8.setRotationPoint(7F, 15F, -8F);
		wing8.setTextureSize(64, 32);
		wing8.mirror = true;
		setRotation(wing8, 0F, 1.570796F, 0F);
		parts.put("wing8", wing8);
		
		ModelRenderer blade8;
		blade8 = new ModelRenderer(this, 0, 62);
		blade8.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		blade8.setRotationPoint(7.5F, 14F, -17.5F);
		blade8.setTextureSize(64, 32);
		blade8.mirror = true;
		setRotation(blade8, 0F, 1.570796F, 0F);
		parts.put("blade8", blade8);
	}
}
