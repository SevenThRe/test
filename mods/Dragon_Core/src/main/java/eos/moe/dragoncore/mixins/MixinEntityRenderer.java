/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.EntityRenderer
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.om;
import eos.moe.dragoncore.qha;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.EntityRenderer;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={EntityRenderer.class})
public class MixinEntityRenderer {
    @Shadow
    @Final
    private Minecraft mc;
    @Shadow
    private boolean cloudFog;

    public MixinEntityRenderer() {
        MixinEntityRenderer a2;
    }

    @Inject(method={"orientCamera"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_orientCamera(float a2, CallbackInfo a3) {
        if (qha.ALLATORIxDEMO()) {
            MixinEntityRenderer a4;
            a3.cancel();
            Entity a5 = a4.mc.getRenderViewEntity();
            qha.ALLATORIxDEMO(a2);
            double a6 = a5.prevPosX + (a5.posX - a5.prevPosX) * (double)a2;
            double a7 = a5.prevPosY + (a5.posY - a5.prevPosY) * (double)a2 + (double)a5.getEyeHeight();
            double a8 = a5.prevPosZ + (a5.posZ - a5.prevPosZ) * (double)a2;
            a4.cloudFog = a4.mc.renderGlobal.hasCloudFog(a6, a7, a8, a2);
        }
    }

    @Inject(method={"hurtCameraEffect"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_hurtCameraEffect(float a2, CallbackInfo a3) {
        if (om.b) {
            a3.cancel();
        }
    }
}

