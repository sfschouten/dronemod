package sfschouten.dronemod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelMilling extends ModelBase {
	ModelRenderer Head1;
	ModelRenderer Body;
	ModelRenderer Foot;
	ModelRenderer Drill1;
	ModelRenderer DrillHead;
	ModelRenderer DrillTip;
	ModelRenderer TableStick;
	ModelRenderer Table;

	public ModelMilling() {
		textureWidth = 64;
		textureHeight = 64;

		Head1 = new ModelRenderer(this, 0, 0);
		Head1.addBox(0F, 0F, 0F, 5, 1, 9);
		Head1.setRotationPoint(-2F, 8.5F, -5F);
		Head1.setTextureSize(64, 64);
		Head1.mirror = true;
		setRotation(Head1, 0F, 0F, 0F);
		Body = new ModelRenderer(this, 34, 0);
		Body.addBox(0F, 0F, 0F, 9, 14, 6);
		Body.setRotationPoint(-4F, 9F, -8F);
		Body.setTextureSize(64, 64);
		Body.mirror = true;
		setRotation(Body, 0F, 0F, 0F);
		Foot = new ModelRenderer(this, 0, 39);
		Foot.addBox(0F, 0F, 0F, 9, 1, 15);
		Foot.setRotationPoint(-4F, 23F, -8F);
		Foot.setTextureSize(64, 64);
		Foot.mirror = true;
		setRotation(Foot, 0F, 0F, 0F);
		Drill1 = new ModelRenderer(this, 29, 0);
		Drill1.addBox(-0.5F, 0F, -0.5F, 1, 4, 1);
		Drill1.setRotationPoint(0.5F, 9F, 3.5F);
		Drill1.setTextureSize(64, 64);
		Drill1.mirror = true;
		setRotation(Drill1, 0F, 0F, 0F);
		DrillHead = new ModelRenderer(this, 0, 11);
		DrillHead.addBox(-1.5F, 0F, -1.5F, 3, 1, 3);
		DrillHead.setRotationPoint(0.5F, 13F, 3.5F);
		DrillHead.setTextureSize(64, 64);
		DrillHead.mirror = true;
		setRotation(DrillHead, 0F, 0F, 0F);
		DrillTip = new ModelRenderer(this, 29, 6);
		DrillTip.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
		DrillTip.setRotationPoint(0.5F, 14F, 3.5F);
		DrillTip.setTextureSize(64, 64);
		DrillTip.mirror = true;
		setRotation(DrillTip, 0F, 0F, 0F);
		TableStick = new ModelRenderer(this, 37, 60);
		TableStick.addBox(0F, 0F, 0F, 1, 3, 1);
		TableStick.setRotationPoint(0F, 20F, 3F);
		TableStick.setTextureSize(64, 64);
		TableStick.mirror = true;
		setRotation(TableStick, 0F, 0F, 0F);
		Table = new ModelRenderer(this, 0, 56);
		Table.addBox(0F, 0F, 0F, 11, 1, 7);
		Table.setRotationPoint(-5F, 19F, 0F);
		Table.setTextureSize(64, 64);
		Table.mirror = true;
		setRotation(Table, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		Head1.render(f5);
		Body.render(f5);
		Foot.render(f5);
		Drill1.render(f5);
		DrillHead.render(f5);
		DrillTip.render(f5);
		TableStick.render(f5);
		Table.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}

	public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity e) {
		super.setRotationAngles(f, f1, f2, f3, f4, f5, e);
	}
}
