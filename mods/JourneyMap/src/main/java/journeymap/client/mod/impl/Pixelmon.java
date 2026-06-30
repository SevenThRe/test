/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package journeymap.client.mod.impl;

import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class Pixelmon {
    public static Pixelmon INSTANCE;
    public static boolean loaded;

    private Pixelmon() {
    }

    public Pixelmon(boolean loaded) {
        Pixelmon.loaded = loaded;
        INSTANCE = new Pixelmon();
    }

    public ResourceLocation getPixelmonResource(Entity entity) {
        if (Pixelmon.isInstanceOf(entity, "com.pixelmonmod.pixelmon.entities.pixelmon.EntityPixelmon")) {
            try {
                ResourceLocation pixelmonSprite = (ResourceLocation)entity.getClass().getMethod("getSprite", new Class[0]).invoke((Object)entity, new Object[0]);
                if (pixelmonSprite != null) {
                    return pixelmonSprite;
                }
                return null;
            }
            catch (Exception e) {
                Journeymap.getLogger().error(String.format("Error getting pixelmon sprite from %s:", LogFormatter.toPartialString(e)));
            }
        }
        return null;
    }

    private static boolean isInstanceOf(Object pokemonEntity, String ... classPaths) {
        for (String classPath : classPaths) {
            try {
                Class<?> matchedClass = Class.forName(classPath);
                if (!matchedClass.isInstance(pokemonEntity)) continue;
                return true;
            }
            catch (Exception e) {
                return false;
            }
        }
        return false;
    }

    static {
        loaded = false;
    }
}

