package sfschouten.dronemod.client.model;

import java.util.ArrayList;
import java.util.List;

import sfschouten.dronemod.DroneMod;
import sfschouten.dronemod.util.Logger;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class ModelOctaCopter extends ModelCopter {
	public static final ResourceLocation woodOctaTextureLocation = new ResourceLocation(DroneMod.modID, "textures/entity/woodocta.png");
	public static final ResourceLocation aluminiumOctaTextureLocation = new ResourceLocation(DroneMod.modID, "textures/entity/aluminiumocta.png");
	
	@Override
	protected void initPositions(){
		defaultPosition = new float[]{-0.125F, 1.9F, -0.125F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F};
		
		chestPositions = new float[][]{
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F}
		};
		
		batteryPositions = new float[][]{
			new float[]{0.0F, 0.0F, 0.375F, 0.0F, 90.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{0.0F, 0.0F, -0.375F, 0.0F, 90.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{-0.5F, 0.0F, 0.25F, 0.0F, 90.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{-0.5F, 0.0F, -0.25F, 0.0F, 90.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{-0.5F, 0.0F, 0.0F, 0.0F, 90.0F, 0.0F, 1.0F, 1.0F, 1.0F}
		};
		
		modulePositions = new float[][]{
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F},
			new float[]{1.0F, 1.0F, 1.0F, 0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F}
		};
	};
	
	public ModelOctaCopter(float scale) {
		super(scale);
	}
	
	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	protected void rotateBlades(float amount){
		parts.get("blade1").rotateAngleY += amount;
		parts.get("blade2").rotateAngleY += amount;
		parts.get("blade3").rotateAngleY += amount;
		parts.get("blade4").rotateAngleY += amount;
		parts.get("blade5").rotateAngleY += amount;
		parts.get("blade6").rotateAngleY += amount;
		parts.get("blade7").rotateAngleY += amount;
		parts.get("blade8").rotateAngleY += amount;
	}
	
	protected void initParts(){
		ModelRenderer base;
		base = new ModelRenderer(this, 0, 0);
		base.addBox(-8F, 0F, -8F, 16, 1, 16);
		base.setRotationPoint(0F, 15F, 0F);
		base.setTextureSize(64, 64);
		base.mirror = true;
		setRotation(base, 0F, 0F, 0F);
		parts.put("base", base);
		
		ModelRenderer head1;
		head1 = new ModelRenderer(this, 25, 36);
		head1.addBox(0F, 0F, 0F, 10, 1, 10);
		head1.setRotationPoint(-5F, 14F, -5F);
		head1.setTextureSize(64, 64);
		head1.mirror = true;
		setRotation(head1, 0F, 0F, 0F);
		parts.put("head1", head1);
		
		ModelRenderer head2;
		head2 = new ModelRenderer(this, 33, 47);
		head2.addBox(0F, 0F, 0F, 8, 1, 8);
		head2.setRotationPoint(-4F, 13F, -4F);
		head2.setTextureSize(64, 64);
		head2.mirror = true;
		setRotation(head2, 0F, 0F, 0F);
		parts.put("head2", head2);
		
		ModelRenderer camera;
		camera = new ModelRenderer(this, 48, 57);
		camera.addBox(-2F, -2F, -2F, 4, 3, 4);
		camera.setRotationPoint(0F, 12F, 0F);
		camera.setTextureSize(64, 64);
		camera.mirror = true;
		setRotation(camera, 0F, 0F, 0F);
		parts.put("camera", camera);
		
		ModelRenderer cameraLens;
		cameraLens = new ModelRenderer(this, 43, 62);
		cameraLens.addBox(0F, 0F, 0F, 1, 1, 1);
		cameraLens.setRotationPoint(-0.5F, 11F, 2F);
		cameraLens.setTextureSize(64, 64);
		cameraLens.mirror = true;
		setRotation(cameraLens, 0F, 0F, 0F);
		parts.put("cameraLens", cameraLens);
		
		ModelRenderer wing1;
		wing1 = new ModelRenderer(this, 0, 17);
		wing1.addBox(-6F, 0F, 0F, 10, 1, 1);
		wing1.setRotationPoint(-12F, 15F, 7F);
		wing1.setTextureSize(64, 64);
		wing1.mirror = true;
		setRotation(wing1, 0F, 0F, 0F);
		parts.put("wing1", wing1);
		
		ModelRenderer blade1;
		blade1 = new ModelRenderer(this, 0, 20);
		blade1.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade1.setRotationPoint(-17.5F, 14F, 7.5F);
		blade1.setTextureSize(64, 64);
		blade1.mirror = true;
		setRotation(blade1, 0F, 1.570796F, 0F);
		parts.put("blade1", blade1);
		
		ModelRenderer wing2;
		wing2 = new ModelRenderer(this, 0, 23);
		wing2.addBox(0F, 0F, 0F, 10, 1, 1);
		wing2.setRotationPoint(-18F, 15F, -8F);
		wing2.setTextureSize(64, 64);
		wing2.mirror = true;
		setRotation(wing2, 0F, 0F, 0F);
		parts.put("wing2", wing2);
		
		ModelRenderer blade2;
		blade2 = new ModelRenderer(this, 0, 26);
		blade2.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade2.setRotationPoint(-17.5F, 14F, -7.5F);
		blade2.setTextureSize(64, 64);
		blade2.mirror = true;
		setRotation(blade2, 0F, 1.570796F, 0F);
		parts.put("blade2", blade2);
		
		ModelRenderer wing3;
		wing3 = new ModelRenderer(this, 0, 29);
		wing3.addBox(0F, 0F, 0F, 10, 1, 1);
		wing3.setRotationPoint(-8F, 15F, -8F);
		wing3.setTextureSize(64, 64);
		wing3.mirror = true;
		setRotation(wing3, 0F, 1.570796F, 0F);
		parts.put("wing3", wing3);
		
		ModelRenderer blade3;
		blade3 = new ModelRenderer(this, 0, 32);
		blade3.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade3.setRotationPoint(-7.5F, 14F, -17.5F);
		blade3.setTextureSize(64, 64);
		blade3.mirror = true;
		setRotation(blade3, 0F, 1.570796F, 0F);
		parts.put("blade3", blade3);
		
		ModelRenderer wing4;
		wing4 = new ModelRenderer(this, 0, 35);
		wing4.addBox(0F, 0F, 0F, 10, 1, 1);
		wing4.setRotationPoint(-8F, 15F, 18F);
		wing4.setTextureSize(64, 64);
		wing4.mirror = true;
		setRotation(wing4, 0F, 1.570796F, 0F);
		parts.put("wing4", wing4);
		
		ModelRenderer blade4;
		blade4 = new ModelRenderer(this, 0, 38);
		blade4.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade4.setRotationPoint(-7.5F, 14F, 17.5F);
		blade4.setTextureSize(64, 64);
		blade4.mirror = true;
		setRotation(blade4, 0F, 1.570796F, 0F);
		parts.put("blade4", blade4);
		
		ModelRenderer wing5;
		wing5 = new ModelRenderer(this, 0, 41);
		wing5.addBox(0F, 0F, 0F, 10, 1, 1);
		wing5.setRotationPoint(7F, 15F, 18F);
		wing5.setTextureSize(64, 64);
		wing5.mirror = true;
		setRotation(wing5, 0F, 1.570796F, 0F);
		parts.put("wing5", wing5);
		
		ModelRenderer blade5;
		blade5 = new ModelRenderer(this, 0, 44);
		blade5.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade5.setRotationPoint(7.5F, 14F, 17.5F);
		blade5.setTextureSize(64, 64);
		blade5.mirror = true;
		setRotation(blade5, 0F, 1.570796F, 0F);
		parts.put("blade5", blade5);
		
		ModelRenderer wing6;
		wing6 = new ModelRenderer(this, 0, 47);
		wing6.addBox(0F, 0F, 0F, 10, 1, 1);
		wing6.setRotationPoint(8F, 15F, 7F);
		wing6.setTextureSize(64, 64);
		wing6.mirror = true;
		setRotation(wing6, 0F, 0F, 0F);
		parts.put("wing6", wing6);
		
		ModelRenderer blade6;
		blade6 = new ModelRenderer(this, 0, 50);
		blade6.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade6.setRotationPoint(17.5F, 14F, 7.5F);
		blade6.setTextureSize(64, 64);
		blade6.mirror = true;
		setRotation(blade6, 0F, 1.570796F, 0F);
		parts.put("blade6", blade6);
		
		ModelRenderer wing7;
		wing7 = new ModelRenderer(this, 0, 53);
		wing7.addBox(0F, 0F, 0F, 10, 1, 1);
		wing7.setRotationPoint(8F, 15F, -8F);
		wing7.setTextureSize(64, 64);
		wing7.mirror = true;
		setRotation(wing7, 0F, 0F, 0F);
		parts.put("wing7", wing7);
		
		ModelRenderer blade7;
		blade7 = new ModelRenderer(this, 0, 56);
		blade7.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade7.setRotationPoint(17.5F, 14F, -7.5F);
		blade7.setTextureSize(64, 64);
		blade7.mirror = true;
		setRotation(blade7, 0F, 1.570796F, 0F);
		parts.put("blade7", blade7);
		
		ModelRenderer wing8;
		wing8 = new ModelRenderer(this, 0, 59);
		wing8.addBox(0F, 0F, 0F, 10, 1, 1);
		wing8.setRotationPoint(7F, 15F, -8F);
		wing8.setTextureSize(64, 64);
		wing8.mirror = true;
		setRotation(wing8, 0F, 1.570796F, 0F);
		parts.put("wing8", wing8);
		
		ModelRenderer blade8;
		blade8 = new ModelRenderer(this, 0, 62);
		blade8.addBox(-2.5F, 0F, -0.5F, 5, 1, 1);
		blade8.setRotationPoint(7.5F, 14F, -17.5F);
		blade8.setTextureSize(64, 64);
		blade8.mirror = true;
		setRotation(blade8, 0F, 1.570796F, 0F);
		parts.put("blade8", blade8);
	}
}
