package sfschouten.dronemod.item.motor;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemWeakMotor extends ItemDroneMotor {

	public ItemWeakMotor( ) {
		super();
		this.setUnlocalizedName("weakMotorItem");
		this.setTextureName("dronemod:weak_motor_icon");
		LanguageRegistry.addName(this, "Weak Motor");
	}

}
