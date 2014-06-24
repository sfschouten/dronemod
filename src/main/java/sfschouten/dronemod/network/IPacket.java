package sfschouten.dronemod.network;

import net.minecraft.entity.player.EntityPlayer;
import io.netty.buffer.ByteBuf;
import sfschouten.dronemod.Logger;

public interface IPacket {
    public void readBytes(ByteBuf bytes);
    public void writeBytes(ByteBuf bytes);
    public void executeClient(EntityPlayer player);
    public void executeServer(EntityPlayer player);
}
