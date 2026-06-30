/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 */
package goblinbob.mobends.standard.animation.bit.biped;

import goblinbob.mobends.core.animation.bit.AnimationBit;
import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.standard.data.BipedEntityData;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;

public class RidingAnimationBit
extends AnimationBit<BipedEntityData<?>> {
    private static final String[] ACTIONS = new String[]{"riding"};
    private static final float PI = (float)Math.PI;

    @Override
    public String[] getActions(BipedEntityData<?> entityData) {
        return ACTIONS;
    }

    @Override
    public void perform(BipedEntityData<?> data) {
        Object living = data.getEntity();
        data.localOffset.slideToZero(0.3f);
        data.renderRotation.orientZero();
        data.centerRotation.setSmoothness(0.3f).orientZero();
        data.renderLeftItemRotation.orientZero();
        data.renderRightItemRotation.orientZero();
        data.head.rotation.orientX(((Float)data.headPitch.get()).floatValue()).rotateY(((Float)data.headYaw.get()).floatValue());
        data.body.rotation.orientY(0.0f).setSmoothness(0.5f);
        data.leftLeg.rotation.orientX(-90.0f).rotateZ(-10.0f).rotateY(-25.0f);
        data.rightLeg.rotation.orientX(-90.0f).rotateZ(10.0f).rotateY(25.0f);
        data.leftForeLeg.rotation.orientX(60.0f);
        data.rightForeLeg.rotation.orientX(60.0f);
        data.leftArm.rotation.orientX(0.0f).rotateZ(-10.0f);
        data.leftForeArm.rotation.orientX(-10.0f);
        data.rightArm.rotation.orientX(0.0f).rotateZ(10.0f);
        data.rightForeArm.rotation.orientX(-10.0f);
        Entity ridden = living.func_184187_bx();
        if (ridden != null && ridden instanceof EntityLivingBase) {
            EntityLivingBase riddenLiving = (EntityLivingBase)ridden;
            float relativeHeadYaw = MathHelper.func_76142_g((float)(((EntityLivingBase)living).field_70177_z - riddenLiving.field_70761_aq));
            float relativeYaw = MathHelper.func_76142_g((float)(((EntityLivingBase)living).field_70177_z - ((Float)data.headYaw.get()).floatValue() - riddenLiving.field_70761_aq));
            data.body.rotation.orientZ(MathHelper.func_76131_a((float)(-relativeHeadYaw * 0.25f), (float)-20.0f, (float)20.0f));
            data.leftLeg.rotation.rotateX(-MathHelper.func_76126_a((float)(relativeYaw / 180.0f * (float)Math.PI * 1.5f)) * 45.0f);
            data.rightLeg.rotation.rotateX(MathHelper.func_76126_a((float)(relativeYaw / 180.0f * (float)Math.PI * 1.5f)) * 45.0f);
        }
        if (!data.isStillHorizontally()) {
            data.body.rotation.orientX(25.0f);
            data.leftArm.rotation.orientX(-45.0f).rotateZ(10.0f);
            data.leftForeArm.rotation.orientX(-10.0f);
            data.rightArm.rotation.orientX(-45.0f).rotateZ(-10.0f);
            data.rightForeArm.rotation.orientX(-10.0f);
            float motionMagnitude = (float)Math.sqrt(((EntityLivingBase)living).field_70159_w * ((EntityLivingBase)living).field_70159_w + ((EntityLivingBase)living).field_70179_y * ((EntityLivingBase)living).field_70179_y) * 100.0f;
            if (motionMagnitude > 1.0f) {
                float ticks = DataUpdateHandler.getTicks() * 0.5f;
                float bodyRotation = 45.0f + MathHelper.func_76134_b((float)ticks) * 10.0f;
                data.body.rotation.orientX(bodyRotation);
                data.head.rotation.rotateX(-bodyRotation);
                data.leftArm.rotation.rotateX(-bodyRotation);
                data.rightArm.rotation.rotateX(-bodyRotation);
                data.globalOffset.slideY(MathHelper.func_76126_a((float)ticks) * 0.3f);
            } else {
                data.head.rotation.rotateX(-25.0f);
            }
        }
    }
}

