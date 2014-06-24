package sfschouten.dronemod.item.motor;

import sfschouten.dronemod.DroneMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemWeakMotor extends ItemDroneMotor {

	public ItemWeakMotor( ) {
		super();
		this.setUnlocalizedName("weakMotorItem");
		this.setTextureName(DroneMod.modID + ":weak_motor_icon");
	}

}
