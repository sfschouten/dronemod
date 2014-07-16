package sfschouten.dronemod.item.motor;

import sfschouten.dronemod.reference.General;

public class ItemWeakMotor extends ItemDroneMotor {

	public ItemWeakMotor( ) {
		super();
		this.setUnlocalizedName("weakMotorItem");
		this.setTextureName(General.modID + ":weak_motor_icon");
	}

}
