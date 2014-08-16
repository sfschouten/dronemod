package sfschouten.dronemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class MarkersToPlayerMessageHandler implements IMessageHandler<MarkersToPlayerMessage, IMessage> {
	@Override
	public IMessage onMessage(MarkersToPlayerMessage message, MessageContext ctx) {
        message.executeClient(Minecraft.getMinecraft().thePlayer);
		return null;
	}
}
