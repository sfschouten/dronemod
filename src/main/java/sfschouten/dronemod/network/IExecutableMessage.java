package sfschouten.dronemod.network;

import net.minecraft.entity.player.EntityPlayer;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public interface IExecutableMessage extends IMessage {
	public void executeClient(EntityPlayer player);
	public void executeServer(EntityPlayer player);
}
