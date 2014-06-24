package sfschouten.dronemod.client.particle;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.EntityLiving;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;

public class EntityDroneFX extends EntityFlying{

	int life = 25;
	
	public EntityDroneFX(World par1World) {
		super(par1World);
		this.setSize(1F, 1F);
		this.noClip = true;
	}
	

	@Override
    public boolean canBePushed()
    {
        return false;
    }
	
	@Override
    public boolean canBeSteered()
    {
        return false;
    }
	
	@Override
	public boolean canBeCollidedWith()
    {
        return false;
    }

	@Override
	public void onEntityUpdate() {
		if(--life == 0){
			this.setDead();
		}
		super.onEntityUpdate();
	}
}
