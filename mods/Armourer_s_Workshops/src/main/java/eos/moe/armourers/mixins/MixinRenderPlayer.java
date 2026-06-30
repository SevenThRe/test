/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderPlayer
 */
package eos.moe.armourers.mixins;

import eos.moe.armourers.interfaces.IRenderPlayer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={RenderPlayer.class})
public class MixinRenderPlayer
implements IRenderPlayer {
    @Shadow
    @Final
    private boolean field_177140_a;

    public MixinRenderPlayer() {
        MixinRenderPlayer a2;
    }

    @Override
    public boolean isSmallArms() {
        MixinRenderPlayer a2;
        return a2.field_177140_a;
    }
}

