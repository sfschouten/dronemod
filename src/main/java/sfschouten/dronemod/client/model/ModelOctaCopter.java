package sfschouten.dronemod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOctaCopter extends ModelBase {
	ModelRenderer Base;
	ModelRenderer Head1;
	ModelRenderer Head2;
	ModelRenderer Camera;
	ModelRenderer CameraLens;
	ModelRenderer Wing1;
	ModelRenderer Blade1;
	ModelRenderer Wing2;
	ModelRenderer Blade2;
	ModelRenderer Wing3;
	ModelRenderer Blade3;
	ModelRenderer Wing4;
	ModelRenderer Blade4;
	ModelRenderer Wing5;
	ModelRenderer Blade5;
	ModelRenderer Wing6;
	ModelRenderer Blade6;
	ModelRenderer Wing7;
	ModelRenderer Blade7;
	ModelRenderer Wing8;
	ModelRenderer Blade8;

	public ModelOctaCopter() {
		textureWidth = 64;
		textureHeight = 64;

		Base = new ModelRenderer(this, 0, 0);
		Base.addBox(-8F, 0F, -8F, 16, 1, 16);
		Base.setRotationPoint(0F, 15F, 0F);
		Base.setTextureSize(64, 64);
		Base.mirror = true;
		setRotation(Base, 0F, 0F, 0F);
		Head1 = new ModelRenderer(this, 25, 36);
		Head1.addBox(0F, 0F, 0F, 10, 1, 10);
		Head1.setRotationPoint(-5F, 14F, -5F);
		Head1.setTextureSize(64, 64);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);
		Head2 = new ModelRenderer(this, 33, 47);
		Head2.addBox(0F, 0F, 0F, 8, 1, 8);
		Head2.setRotationPoint(-4F, 13F, -4F);
		Head2.setTextureSize(64, 64);
		Head2.mirror = true;
		setRotation(Head2, 0F, 0F, 0F);
		Camera = new ModelRenderer(this, 48, 57);
		Camera.addBox(-2F, -2F, -2F, 4, 3, 4);
		Camera.setRotationPoint(0F, 12F, 0F);
		Camera.setTextureSize(64, 64);
		Camera.mirror = true;
		setRotation(Camera, 0F, 0F, 0F);
		CameraLens = new ModelRenderer(this, 43, 62);
		CameraLens.addBox(0F, 0F, 0F, 1, 1, 1);
		CameraLens.setRotationPoint(-0.5F, 11F, 2F);
		CameraLens.setTextureSize(64, 64);
		CameraLens.mirror = true;
		setRotation(CameraLens, 0F, 0F, 0F);
		Wing1 = new ModelRenderer(this, 0, 17);
		Wing1.addBox(-6F, 0F, 0F, 10, 1, 1);
		Wing1.setRotationPoint(-12F, 15F, 7F);
		Wing1.setTextureSize(64, 64);
		Wing1.mirror = true;
		setRotation(Wing1, 0F, 0F, 0F);
		Blade1 = new ModelRenderer(this, 0, 20);
		Blade1.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade1.setRotationPoint(-17.5F, 14F, 7.5F);
		Blade1.setTextureSize(64, 64);
		Blade1.mirror = true;
		setRotation(Blade1, 0F, 1.570796F, 0F);
		Wing2 = new ModelRenderer(this, 0, 23);
		Wing2.addBox(0F, 0F, 0F, 10, 1, 1);
		Wing2.setRotationPoint(-18F, 15F, -8F);
		Wing2.setTextureSize(64, 64);
		Wing2.mirror = true;
		setRotation(Wing2, 0F, 0F, 0F);
		Blade2 = new ModelRenderer(this, 0, 26);
		Blade2.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade2.setRotationPoint(-17.5F, 14F, -7.5F);
		Blade2.setTextureSize(64, 64);
		Blade2.mirror = true;
		setRotation(Blade2, 0F, 1.570796F, 0F);
		Wing3 = new ModelRenderer(this, 0, 29);
		Wing3.addBox(0F, 0F, 0F, 10, 1, 1);
		Wing3.setRotationPoint(-8F, 15F, -8F);
		Wing3.setTextureSize(64, 64);
		Wing3.mirror = true;
		setRotation(Wing3, 0F, 1.570796F, 0F);
		Blade3 = new ModelRenderer(this, 0, 32);
		Blade3.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade3.setRotationPoint(-7.5F, 14F, -17.5F);
		Blade3.setTextureSize(64, 64);
		Blade3.mirror = true;
		setRotation(Blade3, 0F, 1.570796F, 0F);
		Wing4 = new ModelRenderer(this, 0, 35);
		Wing4.addBox(0F, 0F, 0F, 10, 1, 1);
		Wing4.setRotationPoint(-8F, 15F, 18F);
		Wing4.setTextureSize(64, 64);
		Wing4.mirror = true;
		setRotation(Wing4, 0F, 1.570796F, 0F);
		Blade4 = new ModelRenderer(this, 0, 38);
		Blade4.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade4.setRotationPoint(-7.5F, 14F, 17.5F);
		Blade4.setTextureSize(64, 64);
		Blade4.mirror = true;
		setRotation(Blade4, 0F, 1.570796F, 0F);
		Wing5 = new ModelRenderer(this, 0, 41);
		Wing5.addBox(0F, 0F, 0F, 10, 1, 1);
		Wing5.setRotationPoint(7F, 15F, 18F);
		Wing5.setTextureSize(64, 64);
		Wing5.mirror = true;
		setRotation(Wing5, 0F, 1.570796F, 0F);
		Blade5 = new ModelRenderer(this, 0, 44);
		Blade5.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade5.setRotationPoint(7.5F, 14F, 17.5F);
		Blade5.setTextureSize(64, 64);
		Blade5.mirror = true;
		setRotation(Blade5, 0F, 1.570796F, 0F);
		Wing6 = new ModelRenderer(this, 0, 47);
		Wing6.addBox(0F, 0F, 0F, 10, 1, 1);
		Wing6.setRotationPoint(8F, 15F, 7F);
		Wing6.setTextureSize(64, 64);
		Wing6.mirror = true;
		setRotation(Wing6, 0F, 0F, 0F);
		Blade6 = new ModelRenderer(this, 0, 50);
		Blade6.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade6.setRotationPoint(17.5F, 14F, 7.5F);
		Blade6.setTextureSize(64, 64);
		Blade6.mirror = true;
		setRotation(Blade6, 0F, 1.570796F, 0F);
		Wing7 = new ModelRenderer(this, 0, 53);
		Wing7.addBox(0F, 0F, 0F, 10, 1, 1);
		Wing7.setRotationPoint(8F, 15F, -8F);
		Wing7.setTextureSize(64, 64);
		Wing7.mirror = true;
		setRotation(Wing7, 0F, 0F, 0F);
		Blade7 = new ModelRenderer(this, 0, 56);
		Blade7.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade7.setRotationPoint(17.5F, 14F, -7.5F);
		Blade7.setTextureSize(64, 64);
		Blade7.mirror = true;
		setRotation(Blade7, 0F, 1.570796F, 0F);
		Wing8 = new ModelRenderer(this, 0, 59);
		Wing8.addBox(0F, 0F, 0F, 10, 1, 1);
		Wing8.setRotationPoint(7F, 15F, -8F);
		Wing8.setTextureSize(64, 64);
		Wing8.mirror = true;
		setRotation(Wing8, 0F, 1.570796F, 0F);
		Blade8 = new ModelRenderer(this, 0, 62);
		Blade8.addBox(-1.5F, 0F, -0.5F, 3, 1, 1);
		Blade8.setRotationPoint(7.5F, 14F, -17.5F);
		Blade8.setTextureSize(64, 64);
		Blade8.mirror = true;
		setRotation(Blade8, 0F, 1.570796F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(entity, f, f1, f2, f3, f4, f5);
		Base.render(f5);
		Head1.render(f5);
		Head2.render(f5);
		Camera.render(f5);
		CameraLens.render(f5);
		Wing1.render(f5);
		Blade1.render(f5);
		Wing2.render(f5);
		Blade2.render(f5);
		Wing3.render(f5);
		Blade3.render(f5);
		Wing4.render(f5);
		Blade4.render(f5);
		Wing5.render(f5);
		Blade5.render(f5);
		Wing6.render(f5);
		Blade6.render(f5);
		Wing7.render(f5);
		Blade7.render(f5);
		Wing8.render(f5);
		Blade8.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(Entity e, float f, float f1, float f2, float f3, float f4, float f5) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}

}
