package sfschouten.dronemod.item.motor;

import sfschouten.dronemod.DroneMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemStrongMotor extends ItemDroneMotor {

	public ItemStrongMotor( ) {
		super();
		this.setUnlocalizedName("strongMotorItem");
		this.setTextureName(DroneMod.modID + ":strong_motor_icon");
	}

}
