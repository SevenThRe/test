/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.audio.Sound
 *  net.minecraft.client.audio.Sound$Type
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import net.minecraft.client.audio.Sound;
import net.minecraft.util.ResourceLocation;

public class vn
extends Sound {
    public ResourceLocation ALLATORIxDEMO;

    public vn(ResourceLocation a2, float a3, float a4, int a5, Sound.Type a6, boolean a7) {
        super("", a3, a4, a5, a6, a7);
        vn a8;
        a8.ALLATORIxDEMO = a2;
    }

    public ResourceLocation func_188719_a() {
        vn a2;
        return a2.ALLATORIxDEMO;
    }

    public ResourceLocation func_188721_b() {
        vn a2;
        return a2.ALLATORIxDEMO;
    }

    public String toString() {
        vn a2;
        return "Sound[" + a2.ALLATORIxDEMO + "]";
    }
}

