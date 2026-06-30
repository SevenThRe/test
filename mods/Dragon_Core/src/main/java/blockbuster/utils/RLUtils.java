/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 */
package blockbuster.utils;

import net.minecraft.util.ResourceLocation;

public class RLUtils {
    public static ResourceLocation create(String path) {
        return new ResourceLocation("dragoncore", "models/particle/" + path);
    }
}

