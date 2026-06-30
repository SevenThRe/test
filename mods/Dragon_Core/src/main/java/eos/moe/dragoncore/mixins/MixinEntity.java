/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.interfaces.AnimationTextureEntity;
import eos.moe.dragoncore.interfaces.IEntity;
import eos.moe.dragoncore.lda;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Entity.class})
public class MixinEntity
implements IEntity,
AnimationTextureEntity {
    @Shadow
    public double field_70169_q;
    @Shadow
    public double field_70167_r;
    @Shadow
    public double field_70166_s;
    @Shadow
    public float field_70131_O;
    private double prevPrevPosX;
    private double prevPrevPosY;
    private double prevPrevPosZ;
    private int tickCounter;
    private int frameCounter;

    public MixinEntity() {
        MixinEntity a2;
    }

    @Inject(method={"onEntityUpdate"}, at={@At(value="HEAD")})
    public void onUpdate(CallbackInfo a2) {
        MixinEntity a3;
        a3.prevPrevPosX = a3.field_70169_q;
        a3.prevPrevPosY = a3.field_70167_r;
        a3.prevPrevPosZ = a3.field_70166_s;
    }

    @Inject(method={"turn"}, at={@At(value="HEAD")}, cancellable=true)
    public void mixinTurn(float a2, float a3, CallbackInfo a4) {
        if (lda.ALLATORIxDEMO()) {
            a4.cancel();
        }
    }

    @Override
    public double getPrevPrevPosX() {
        MixinEntity a2;
        return a2.prevPrevPosX;
    }

    @Override
    public double getPrevPrevPosY() {
        MixinEntity a2;
        return a2.prevPrevPosY;
    }

    @Override
    public double getPrevPrevPosZ() {
        MixinEntity a2;
        return a2.prevPrevPosZ;
    }

    @Override
    public void setTickCounter(int a2) {
        a.tickCounter = a2;
    }

    @Override
    public void setFrameCounter(int a2) {
        a.frameCounter = a2;
    }

    @Override
    public int getTickCounter() {
        MixinEntity a2;
        return a2.tickCounter;
    }

    @Override
    public int getFrameCounter() {
        MixinEntity a2;
        return a2.frameCounter;
    }
}

