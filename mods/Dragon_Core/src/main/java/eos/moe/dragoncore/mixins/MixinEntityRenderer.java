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
    private Minecraft field_78531_r;
    @Shadow
    private boolean field_78500_U;

    public MixinEntityRenderer() {
        MixinEntityRenderer a2;
    }

    @Inject(method={"orientCamera"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_orientCamera(float a2, CallbackInfo a3) {
        if (qha.ALLATORIxDEMO()) {
            MixinEntityRenderer a4;
            a3.cancel();
            Entity a5 = a4.field_78531_r.func_175606_aa();
            qha.ALLATORIxDEMO(a2);
            double a6 = a5.field_70169_q + (a5.field_70165_t - a5.field_70169_q) * (double)a2;
            double a7 = a5.field_70167_r + (a5.field_70163_u - a5.field_70167_r) * (double)a2 + (double)a5.func_70047_e();
            double a8 = a5.field_70166_s + (a5.field_70161_v - a5.field_70166_s) * (double)a2;
            a4.field_78500_U = a4.field_78531_r.field_71438_f.func_72721_a(a6, a7, a8, a2);
        }
    }

    @Inject(method={"hurtCameraEffect"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_hurtCameraEffect(float a2, CallbackInfo a3) {
        if (om.b) {
            a3.cancel();
        }
    }
}

