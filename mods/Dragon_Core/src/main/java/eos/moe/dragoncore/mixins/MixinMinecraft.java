/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.ScaledResolution
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.wi;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={Minecraft.class})
public class MixinMinecraft {
    public MixinMinecraft() {
        MixinMinecraft a2;
    }

    @Inject(method={"resize"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_resize(int a2, int a3, CallbackInfo a4) {
        Minecraft a5 = Minecraft.func_71410_x();
        de.o = new ScaledResolution(a5);
        for (ui a6 : wi.b.ALLATORIxDEMO()) {
            a6.onScreenResize();
        }
    }

    @Inject(method={"checkGLError"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_checkGLError(String a2, CallbackInfo a3) {
        if (ca.o) {
            a3.cancel();
        }
    }
}

