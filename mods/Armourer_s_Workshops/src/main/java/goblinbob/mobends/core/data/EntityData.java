/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.BlockStairs
 *  net.minecraft.block.BlockStaticLiquid
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 */
package goblinbob.mobends.core.data;

import goblinbob.mobends.core.animation.controller.IAnimationController;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.client.model.IBendsModel;
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.vector.SmoothVector3f;
import goblinbob.mobends.core.math.vector.Vec3d;
import goblinbob.mobends.core.pack.state.PackAnimationState;
import goblinbob.mobends.core.util.GUtil;
import java.util.HashMap;
import java.util.List;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;

public abstract class EntityData<E extends Entity>
implements IBendsModel {
    protected int entityID;
    protected final E entity;
    protected double positionX;
    protected double positionY;
    protected double positionZ;
    protected double prevMotionX;
    protected double prevMotionY;
    protected double prevMotionZ;
    protected double motionX;
    protected double motionY;
    protected double motionZ;
    protected final HashMap<String, Object> nameToPartMap = new HashMap();
    public SmoothVector3f globalOffset;
    public SmoothVector3f localOffset;
    public SmoothOrientation renderRotation;
    public SmoothOrientation centerRotation;
    public Vec3d scale;
    public boolean onGround = true;
    public Boolean onGroundOverride = null;
    public Boolean stillnessOverride = null;
    public final PackAnimationState packAnimationState;
    private static final float STRAFING_THRESHOLD = 30.0f;

    public EntityData(E entity) {
        this.entity = entity;
        if (this.entity != null) {
            this.entityID = entity.func_145782_y();
            this.positionX = ((Entity)this.entity).field_70165_t;
            this.positionY = ((Entity)this.entity).field_70163_u;
            this.positionZ = ((Entity)this.entity).field_70161_v;
        }
        this.prevMotionX = 0.0;
        this.motionX = 0.0;
        this.prevMotionY = 1.0;
        this.motionY = 1.0;
        this.prevMotionZ = 0.0;
        this.motionZ = 0.0;
        this.packAnimationState = new PackAnimationState();
        this.initModelPose();
    }

    public void overrideOnGroundState(boolean state) {
        this.onGroundOverride = state;
    }

    public void unsetOnGroundStateOverride() {
        this.onGroundOverride = null;
    }

    public void overrideStillness(boolean stillness) {
        this.stillnessOverride = stillness;
    }

    public void unsetStillnessOverride() {
        this.stillnessOverride = null;
    }

    public void initModelPose() {
        this.globalOffset = new SmoothVector3f();
        this.localOffset = new SmoothVector3f();
        this.renderRotation = new SmoothOrientation();
        this.centerRotation = new SmoothOrientation();
        this.scale = new Vec3d(1.0, 1.0, 1.0);
        this.nameToPartMap.put("renderRotation", this.renderRotation);
        this.nameToPartMap.put("centerRotation", this.centerRotation);
    }

    public void updateParts(float ticksPerFrame) {
        this.globalOffset.update(ticksPerFrame);
        this.localOffset.update(ticksPerFrame);
        this.renderRotation.update(ticksPerFrame);
        this.centerRotation.update(ticksPerFrame);
    }

    public boolean calcOnGround() {
        if (this.onGroundOverride != null) {
            return this.onGroundOverride;
        }
        BlockPos position = new BlockPos(Math.floor(((Entity)this.entity).field_70165_t), Math.floor(((Entity)this.entity).field_70163_u), Math.floor(((Entity)this.entity).field_70161_v));
        IBlockState block = ((Entity)this.entity).field_70170_p.func_180495_p(position);
        IBlockState blockBelow = ((Entity)this.entity).field_70170_p.func_180495_p(position.func_177982_a(0, -1, 0));
        if (this.motionY <= 0.0 && (block.func_177230_c() instanceof BlockStairs || blockBelow.func_177230_c() instanceof BlockStairs)) {
            return true;
        }
        List list = ((Entity)this.entity).field_70170_p.func_184144_a(this.entity, this.entity.func_174813_aQ().func_72317_d(0.0, -0.125, 0.0));
        return list.size() > 0;
    }

    public boolean calcCollidedHorizontally() {
        List list = ((Entity)this.entity).field_70170_p.func_184144_a(this.entity, this.entity.func_174813_aQ().func_72317_d(this.motionX, 0.0, this.motionZ));
        return list.size() > 0;
    }

    public double getPositionX() {
        return this.positionX;
    }

    public double getPositionY() {
        return this.positionY;
    }

    public double getPositionZ() {
        return this.positionZ;
    }

    public double getMotionX() {
        return this.motionX;
    }

    public double getMotionY() {
        return this.motionY;
    }

    public double getMotionZ() {
        return this.motionZ;
    }

    public double getPrevMotionX() {
        return this.prevMotionX;
    }

    public double getPrevMotionY() {
        return this.prevMotionY;
    }

    public double getPrevMotionZ() {
        return this.prevMotionZ;
    }

    public double getInterpolatedMotionX() {
        return this.prevMotionX + (this.motionX - this.prevMotionX) * (double)DataUpdateHandler.partialTicks;
    }

    public double getInterpolatedMotionY() {
        return this.prevMotionY + (this.motionY - this.prevMotionY) * (double)DataUpdateHandler.partialTicks;
    }

    public double getInterpolatedMotionZ() {
        return this.prevMotionZ + (this.motionZ - this.prevMotionZ) * (double)DataUpdateHandler.partialTicks;
    }

    public boolean isOnGround() {
        return this.onGround;
    }

    public boolean isStillHorizontally() {
        double deadZone = 0.0025;
        double horizontalSqMagnitude = this.motionX * this.motionX + this.motionZ * this.motionZ;
        return this.stillnessOverride != null ? this.stillnessOverride : horizontalSqMagnitude < 0.0025;
    }

    public abstract IAnimationController<?> getController();

    public void update(float partialTicks) {
        if (this.entity == null) {
            return;
        }
        this.updateParts(DataUpdateHandler.ticksPerFrame);
    }

    public E getEntity() {
        return this.entity;
    }

    public float getLookAngle() {
        net.minecraft.util.math.Vec3d lookVec = this.entity.func_70040_Z();
        return (float)GUtil.angleFromCoordinates(lookVec.field_72450_a, lookVec.field_72449_c);
    }

    private float getWorldMovementAngle() {
        return (float)GUtil.angleFromCoordinates(this.motionX, this.motionZ);
    }

    public float getMovementAngle() {
        if (this.isStillHorizontally()) {
            return 0.0f;
        }
        return this.getWorldMovementAngle() - this.getLookAngle();
    }

    public double getForwardMomentum() {
        if (this.isStillHorizontally()) {
            return 0.0;
        }
        net.minecraft.util.math.Vec3d lookVec = this.entity.func_70040_Z();
        net.minecraft.util.math.Vec3d lookVecHorizontal = new net.minecraft.util.math.Vec3d(lookVec.field_72450_a, 0.0, lookVec.field_72449_c).func_72432_b();
        return lookVecHorizontal.field_72450_a * this.motionX + lookVecHorizontal.field_72449_c * this.motionZ;
    }

    public double getSidewaysMomentum() {
        if (this.isStillHorizontally()) {
            return 0.0;
        }
        net.minecraft.util.math.Vec3d rightVec = this.entity.func_70040_Z().func_178785_b(-1.5707964f);
        net.minecraft.util.math.Vec3d rightVecHorizontal = new net.minecraft.util.math.Vec3d(rightVec.field_72450_a, 0.0, rightVec.field_72449_c).func_72432_b();
        return rightVecHorizontal.field_72450_a * this.motionX + rightVecHorizontal.field_72449_c * this.motionZ;
    }

    public boolean isStrafing() {
        float angle = this.getMovementAngle();
        return angle >= 30.0f && angle <= 150.0f || angle >= -150.0f && angle <= -30.0f;
    }

    public boolean isUnderwater() {
        if (!this.entity.func_70090_H()) {
            return false;
        }
        int blockX = MathHelper.func_76128_c((double)((Entity)this.entity).field_70165_t);
        int blockY = MathHelper.func_76128_c((double)(((Entity)this.entity).field_70163_u + 2.0));
        int blockZ = MathHelper.func_76128_c((double)((Entity)this.entity).field_70161_v);
        IBlockState state = Minecraft.func_71410_x().field_71441_e.func_180495_p(new BlockPos(blockX, blockY, blockZ));
        return state.func_177230_c() instanceof BlockStaticLiquid;
    }

    public double getPrevMotionMagnitude() {
        return Math.sqrt(this.prevMotionX * this.prevMotionX + this.prevMotionY * this.prevMotionY + this.prevMotionZ * this.prevMotionZ);
    }

    public double getMotionMagnitude() {
        return Math.sqrt(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
    }

    public double getInterpolatedMotionMagnitude() {
        return EntityData.interpolateMagitude(this.getMotionMagnitude(), this.getPrevMotionMagnitude());
    }

    public double getXZMotionMagnitude() {
        return Math.sqrt(this.motionX * this.motionX + this.motionZ * this.motionZ);
    }

    public double getPrevXZMotionMagnitude() {
        return Math.sqrt(this.prevMotionX * this.prevMotionX + this.prevMotionZ * this.prevMotionZ);
    }

    public double getInterpolatedXZMotionMagnitude() {
        return EntityData.interpolateMagitude(this.getXZMotionMagnitude(), this.getPrevXZMotionMagnitude());
    }

    private static double interpolateMagitude(double magnitude, double prevMagnitude) {
        return prevMagnitude + (magnitude - prevMagnitude) * (double)DataUpdateHandler.partialTicks;
    }

    public void updateClient() {
        this.prevMotionX = this.motionX;
        this.prevMotionY = this.motionY;
        this.prevMotionZ = this.motionZ;
        this.motionX = ((Entity)this.entity).field_70165_t - this.positionX;
        this.motionY = ((Entity)this.entity).field_70163_u - this.positionY;
        this.motionZ = ((Entity)this.entity).field_70161_v - this.positionZ;
        this.positionX = ((Entity)this.entity).field_70165_t;
        this.positionY = ((Entity)this.entity).field_70163_u;
        this.positionZ = ((Entity)this.entity).field_70161_v;
    }

    @Override
    public Object getPartForName(String name) {
        return this.nameToPartMap.get(name);
    }

    public abstract void onTicksRestart();
}

