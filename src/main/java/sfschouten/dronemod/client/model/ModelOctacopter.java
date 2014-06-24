package sfschouten.dronemod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelOctacopter extends ModelBase {
	// fields
	ModelRenderer RM;
	ModelRenderer Body;
	ModelRenderer FR;
	ModelRenderer RL;
	ModelRenderer RR;
	ModelRenderer FL;
	ModelRenderer LM;
	ModelRenderer FM;

	public ModelOctacopter() {
		textureWidth = 64;
		textureHeight = 32;
		setTextureOffset("FR.FR6", 8, 0);
		setTextureOffset("FR.FR5", 8, 0);
		setTextureOffset("FR.FR4", 8, 0);
		setTextureOffset("FR.FR3", 8, 0);
		setTextureOffset("FR.FR2", 8, 0);
		setTextureOffset("FR.FR1", 8, 0);
		setTextureOffset("RL.RL1", 0, 0);
		setTextureOffset("RL.RL2", 0, 0);
		setTextureOffset("RL.RL3", 0, 0);
		setTextureOffset("RL.RL4", 0, 0);
		setTextureOffset("RL.RL5", 0, 0);
		setTextureOffset("RL.RL6", 0, 0);
		setTextureOffset("RR.RR1", 0, 0);
		setTextureOffset("RR.RR2", 0, 0);
		setTextureOffset("RR.RR3", 0, 0);
		setTextureOffset("RR.RR4", 0, 0);
		setTextureOffset("RR.RR5", 0, 0);
		setTextureOffset("RR.RR6", 0, 0);
		setTextureOffset("FL.FL6", 8, 0);
		setTextureOffset("FL.FL5", 8, 0);
		setTextureOffset("FL.FL4", 8, 0);
		setTextureOffset("FL.FL3", 8, 0);
		setTextureOffset("FL.FL2", 8, 0);
		setTextureOffset("FL.FL1", 8, 0);

		RM = new ModelRenderer(this, 0, 21);
		RM.addBox(0F, 0F, 0F, 8, 1, 2);
		RM.setRotationPoint(4F, 23F, -1F);
		RM.setTextureSize(64, 32);
		RM.mirror = true;
		setRotation(RM, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 0, 3);
		Body.addBox(0F, 0F, 0F, 8, 1, 8);
		Body.setRotationPoint(-4F, 23F, -4F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		FR = new ModelRenderer(this, "FR");
		FR.setRotationPoint(9F, 23F, 9F);
		setRotation(FR, 0F, 0F, 0F);
		FR.mirror = true;
		FR.addBox("FR6", -1F, 0F, -1F, 2, 1, 2);
		FR.addBox("FR5", -2F, 0F, -2F, 2, 1, 2);
		FR.addBox("FR4", -3F, 0F, -3F, 2, 1, 2);
		FR.addBox("FR3", -4F, 0F, -4F, 2, 1, 2);
		FR.addBox("FR2", -5F, 0F, -5F, 2, 1, 2);
		FR.addBox("FR1", -6F, 0F, -6F, 2, 1, 2);
		RL = new ModelRenderer(this, "RL");
		RL.setRotationPoint(-4F, 23F, -4F);
		setRotation(RL, 0F, 0F, 0F);
		RL.mirror = true;
		RL.addBox("RL1", -1F, 0F, -1F, 2, 1, 2);
		RL.addBox("RL2", -2F, 0F, -2F, 2, 1, 2);
		RL.addBox("RL3", -3F, 0F, -3F, 2, 1, 2);
		RL.addBox("RL4", -4F, 0F, -4F, 2, 1, 2);
		RL.addBox("RL5", -5F, 0F, -5F, 2, 1, 2);
		RL.addBox("RL6", -6F, 0F, -6F, 2, 1, 2);
		RR = new ModelRenderer(this, "RR");
		RR.setRotationPoint(4F, 23F, -4F);
		setRotation(RR, 0F, 0F, 0F);
		RR.mirror = true;
		RR.addBox("RR1", -1F, 0F, -1F, 2, 1, 2);
		RR.addBox("RR2", 0F, 0F, -2F, 2, 1, 2);
		RR.addBox("RR3", 1F, 0F, -3F, 2, 1, 2);
		RR.addBox("RR4", 2F, 0F, -4F, 2, 1, 2);
		RR.addBox("RR5", 3F, 0F, -5F, 2, 1, 2);
		RR.addBox("RR6", 4F, 0F, -6F, 2, 1, 2);
		FL = new ModelRenderer(this, "FL");
		FL.setRotationPoint(-9F, 23F, 9F);
		setRotation(FL, 0F, 0F, 0F);
		FL.mirror = true;
		FL.addBox("FL6", -1F, 0F, -1F, 2, 1, 2);
		FL.addBox("FL5", 0F, 0F, -2F, 2, 1, 2);
		FL.addBox("FL4", 1F, 0F, -3F, 2, 1, 2);
		FL.addBox("FL3", 2F, 0F, -4F, 2, 1, 2);
		FL.addBox("FL2", 3F, 0F, -5F, 2, 1, 2);
		FL.addBox("FL1", 4F, 0F, -6F, 2, 1, 2);
		LM = new ModelRenderer(this, 0, 21);
		LM.addBox(0F, 0F, 0F, 8, 1, 2);
		LM.setRotationPoint(-12F, 23F, -1F);
		LM.setTextureSize(64, 32);
		LM.mirror = true;
		setRotation(LM, 0F, 0F, 0F);
		FM = new ModelRenderer(this, 0, 12);
		FM.addBox(0F, 0F, 0F, 2, 1, 8);
		FM.setRotationPoint(-1F, 23F, 4F);
		FM.setTextureSize(64, 32);
		FM.mirror = true;
		setRotation(FM, 0F, 0F, 0F);
		RM = new ModelRenderer(this, 0, 12);
		RM.addBox(0F, 0F, 0F, 2, 1, 8);
		RM.setRotationPoint(-1F, 23F, -12F);
		RM.setTextureSize(64, 32);
		RM.mirror = true;
		setRotation(RM, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,
			float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		RM.render(f5);
		Body.render(f5);
		FR.render(f5);
		RL.render(f5);
		RR.render(f5);
		FL.render(f5);
		LM.render(f5);
		FM.render(f5);
		RM.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3,
			float f4, float f5, Entity entity) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
	}
}
