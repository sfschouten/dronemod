package sfschouten.dronemod.item.motor;

import sfschouten.dronemod.DroneMod;
import cpw.mods.fml.common.registry.LanguageRegistry;

public class ItemMediumMotor extends ItemDroneMotor {

	public ItemMediumMotor( ) {
		super();
		this.setUnlocalizedName("mediumMotorItem");
		this.setTextureName(DroneMod.modID + ":medium_motor_icon");
	}

}
