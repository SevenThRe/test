/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.particle.ParticleManager
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.ri;
import net.minecraft.client.particle.ParticleManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={ParticleManager.class})
public class MixinParticleManager {
    public MixinParticleManager() {
        MixinParticleManager a2;
    }

    @Inject(method={"renderParticles"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_renderParticles(Entity a2, float a3, CallbackInfo a4) {
        ri.ALLATORIxDEMO(a3);
    }
}

