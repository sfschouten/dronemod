// Date: 09/03/2014 5:49:47 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package sfschouten.dronemod.client.model;

import net.minecraft.client.model.ModelRenderer;
import net.minecraft.util.ResourceLocation;
import sfschouten.dronemod.reference.General;

public class ModelQuadcopter extends ModelCopter {
	public static final ResourceLocation quadCopterTexture = new ResourceLocation(General.modID, "textures/entity/quadcopter.png");
	
	float scale;

	public ModelQuadcopter(float scale) {
		super(scale);
	}
	
	@Override
	protected void initParts(){
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
		
		ModelRenderer FL = new ModelRenderer(this, "FL");
		FL.setRotationPoint(0F, 0F, 0F);
		setRotation(FL, 0F, 0F, 0F);
		FL.mirror = true;
		FL.addBox("FL1", 3F, 15F, -5F, 2, 1, 2);
		FL.addBox("FL2", 4F, 15F, -6F, 2, 1, 2);
		FL.addBox("FL3", 5F, 15F, -7F, 2, 1, 2);
		FL.addBox("FL4", 6F, 15F, -8F, 2, 1, 2);
		FL.addBox("FL5", 7F, 15F, -9F, 2, 1, 2);
		FL.addBox("FL6", 8F, 15F, -10F, 2, 1, 2);
		parts.put("FL", FL);
		
		ModelRenderer FR = new ModelRenderer(this, "FR");
		FR.setRotationPoint(0F, 0F, 0F);
		setRotation(FR, 0F, 0F, 0F);
		FR.mirror = true;
		FR.addBox("FR1", 3F, 15F, 3F, 2, 1, 2);
		FR.addBox("FR2", 4F, 15F, 4F, 2, 1, 2);
		FR.addBox("FR3", 5F, 15F, 5F, 2, 1, 2);
		FR.addBox("FR4", 6F, 15F, 6F, 2, 1, 2);
		FR.addBox("FR5", 7F, 15F, 7F, 2, 1, 2);
		FR.addBox("FR6", 8F, 15F, 8F, 2, 1, 2);
		parts.put("FR", FR);
		
		ModelRenderer Body = new ModelRenderer(this, 0, 3);
		Body.addBox(-4F, 15F, -4F, 8, 1, 8);
		Body.setRotationPoint(0F, 0F, 0F);
		Body.setTextureSize(64, 32);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		parts.put("Body", Body);
		
		ModelRenderer RL = new ModelRenderer(this, "RL");
		RL.setRotationPoint(0F, 0F, 0F);
		setRotation(RL, 0F, 0F, 0F);
		RL.mirror = true;
		RL.addBox("RL1", -5F, 15F, -5F, 2, 1, 2);
		RL.addBox("RL2", -6F, 15F, -6F, 2, 1, 2);
		RL.addBox("RL3", -7F, 15F, -7F, 2, 1, 2);
		RL.addBox("RL4", -8F, 15F, -8F, 2, 1, 2);
		RL.addBox("RL5", -9F, 15F, -9F, 2, 1, 2);
		RL.addBox("RL6", -10F, 15F, -10F, 2, 1, 2);
		parts.put("RL", RL);
		
		ModelRenderer RR = new ModelRenderer(this, "RR");
		RR.setRotationPoint(0F, 0F, 0F);
		setRotation(RR, 0F, 0F, 0F);
		RR.mirror = true;
		RR.addBox("RR1", -5F, 15F, 3F, 2, 1, 2);
		RR.addBox("RR2", -6F, 15F, 4F, 2, 1, 2);
		RR.addBox("RR3", -7F, 15F, 5F, 2, 1, 2);
		RR.addBox("RR4", -8F, 15F, 6F, 2, 1, 2);
		RR.addBox("RR5", -9F, 15F, 7F, 2, 1, 2);
		RR.addBox("RR6", -10F, 15F, 8F, 2, 1, 2);
		parts.put("RR", RR);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	@Override
	protected void rotateBlades(float speed) {
		//TODO make new model with blades and rotate them.
	}

	@Override
	protected void initPositions() {
		// TODO Add some positions.
	}
}
