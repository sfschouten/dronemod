package sfschouten.dronemod.network;

import net.minecraft.client.Minecraft;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLIndexedMessageToMessageCodec;
import cpw.mods.fml.common.network.NetworkRegistry;

public class ChannelHandler extends FMLIndexedMessageToMessageCodec<IPacket>{

	@Override
	public void encodeInto(ChannelHandlerContext ctx, IPacket msg, ByteBuf target) throws Exception {
		msg.writeBytes(target);
	}

	@Override
	public void decodeInto(ChannelHandlerContext ctx, ByteBuf source, IPacket msg) {
		msg.readBytes(source);
		switch (FMLCommonHandler.instance().getEffectiveSide()) {
        case CLIENT:
            msg.executeClient(Minecraft.getMinecraft().thePlayer);
            break;
        case SERVER:
            INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
            msg.executeServer(((NetHandlerPlayServer) netHandler).playerEntity);
            break;
		}
	}
}
