/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.lda;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={EntityLivingBase.class})
public abstract class MixinEntityLivingBase
extends Entity {
    @Shadow
    public float renderYawOffset;
    @Shadow
    public float rotationYawHead;
    private boolean onLock = false;

    public MixinEntityLivingBase(World a2) {
        super(a2);
        MixinEntityLivingBase a3;
    }

    @Inject(method={"updateDistance"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void onupdateDistance(float a2, float a3, CallbackInfoReturnable<Float> a4) {
        MixinEntityLivingBase a5;
        if (lda.ALLATORIxDEMO((EntityLivingBase)a5)) {
            a4.setReturnValue(Float.valueOf(a3));
            a5.onLock = true;
        } else if (a5.onLock) {
            a5.onLock = false;
            a5.renderYawOffset = a5.rotationYaw;
            a4.setReturnValue(Float.valueOf(a3));
        }
    }

    protected float updateDistance2(float a2, float a3) {
        boolean a4;
        MixinEntityLivingBase a5;
        float a6 = MathHelper.wrapDegrees((float)(a2 - a5.renderYawOffset));
        a5.renderYawOffset += a6 * 0.3f;
        float a7 = MathHelper.wrapDegrees((float)(a5.rotationYaw - a5.renderYawOffset));
        boolean bl2 = a4 = a7 < -90.0f || a7 >= 90.0f;
        if (a7 < -75.0f) {
            a7 = -75.0f;
        }
        if (a7 >= 75.0f) {
            a7 = 75.0f;
        }
        a5.renderYawOffset = a5.rotationYaw - a7;
        if (a7 * a7 > 2500.0f) {
            a5.renderYawOffset += a7 * 0.2f;
        }
        if (a4) {
            a3 *= -1.0f;
        }
        return a3;
    }
}

