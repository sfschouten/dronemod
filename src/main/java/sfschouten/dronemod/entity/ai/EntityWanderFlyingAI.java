package sfschouten.dronemod.entity.ai;

import sfschouten.dronemod.Logger;
import sfschouten.dronemod.entity.EntityDrone;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

public class EntityWanderFlyingAI extends EntityAIBase{
    private EntityDrone entity;
    private double xPosition;
    private double yPosition;
    private double zPosition;
    private double speed;

    public EntityWanderFlyingAI(EntityDrone drone, double par2){
        this.entity = drone;
        this.speed = par2;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute(){
    	
        if (this.entity.getRNG().nextInt(2) != 0) {
            return false;
        } else {
        	Logger.logOut("Wandering");
            //Vec3 vec3 = RandomPositionGenerator.findRandomTarget(this.entity, 10, 10);

            //if (vec3 == null) {
            //    return false;
            //} else {
                this.xPosition = 120;
                this.yPosition = 100;
                this.zPosition = 295;
                return true;
            //}
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting(){
        return !this.entity.getNavigator().noPath();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting(){
        this.entity.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}