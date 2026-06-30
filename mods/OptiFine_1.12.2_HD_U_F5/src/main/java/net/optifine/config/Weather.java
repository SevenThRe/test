/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amu
 */
package net.optifine.config;

public enum Weather {
    CLEAR,
    RAIN,
    THUNDER;


    public static Weather getWeather(amu world, float partialTicks) {
        float thunderStrength = world.h(partialTicks);
        if (thunderStrength > 0.5f) {
            return THUNDER;
        }
        float rainStrength = world.j(partialTicks);
        if (rainStrength > 0.5f) {
            return RAIN;
        }
        return CLEAR;
    }
}

