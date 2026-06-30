/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Render.class})
public class MixinRender {
    public MixinRender() {
        MixinRender a2;
    }

    @Inject(method={"renderShadow"}, at={@At(value="HEAD")}, cancellable=true)
    public void mixin_shouldRender(Entity a2, double a3, double a4, double a5, float a6, float a7, CallbackInfo a8) {
        rda a9;
        if (a2 instanceof EntityLivingBase && (a9 = raa.r.c((EntityLivingBase)a2)) != null && !a9.w()) {
            a8.cancel();
        }
    }
}

