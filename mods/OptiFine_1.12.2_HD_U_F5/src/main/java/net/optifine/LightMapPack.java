/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amu
 *  ayn
 */
package net.optifine;

import net.optifine.LightMap;

public class LightMapPack {
    private LightMap lightMap;
    private LightMap lightMapRain;
    private LightMap lightMapThunder;
    private int[] colorBuffer1 = new int[0];
    private int[] colorBuffer2 = new int[0];

    public LightMapPack(LightMap lightMap, LightMap lightMapRain, LightMap lightMapThunder) {
        if (lightMapRain != null || lightMapThunder != null) {
            if (lightMapRain == null) {
                lightMapRain = lightMap;
            }
            if (lightMapThunder == null) {
                lightMapThunder = lightMapRain;
            }
        }
        this.lightMap = lightMap;
        this.lightMapRain = lightMapRain;
        this.lightMapThunder = lightMapThunder;
    }

    public boolean updateLightmap(amu world, float torchFlickerX, int[] lmColors, boolean nightvision, float partialTicks) {
        boolean isThundering;
        if (this.lightMapRain == null && this.lightMapThunder == null) {
            return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
        }
        ayn dim = world.s.q();
        if (dim == ayn.c || dim == ayn.b) {
            return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
        }
        float rainStrength = world.j(partialTicks);
        float thunderStrength = world.h(partialTicks);
        float delta = 1.0E-4f;
        boolean isRaining = rainStrength > delta;
        boolean bl = isThundering = thunderStrength > delta;
        if (!isRaining && !isThundering) {
            return this.lightMap.updateLightmap(world, torchFlickerX, lmColors, nightvision);
        }
        if (rainStrength > 0.0f) {
            thunderStrength /= rainStrength;
        }
        float clearBrightness = 1.0f - rainStrength;
        float rainBrightness = rainStrength - thunderStrength;
        float thunderBrightness = thunderStrength;
        if (this.colorBuffer1.length != lmColors.length) {
            this.colorBuffer1 = new int[lmColors.length];
            this.colorBuffer2 = new int[lmColors.length];
        }
        int count = 0;
        int[][] colors = new int[][]{lmColors, this.colorBuffer1, this.colorBuffer2};
        float[] brs2 = new float[3];
        if (clearBrightness > delta && this.lightMap.updateLightmap(world, torchFlickerX, colors[count], nightvision)) {
            brs2[count] = clearBrightness;
            ++count;
        }
        if (rainBrightness > delta && this.lightMapRain != null && this.lightMapRain.updateLightmap(world, torchFlickerX, colors[count], nightvision)) {
            brs2[count] = rainBrightness;
            ++count;
        }
        if (thunderBrightness > delta && this.lightMapThunder != null && this.lightMapThunder.updateLightmap(world, torchFlickerX, colors[count], nightvision)) {
            brs2[count] = thunderBrightness;
            ++count;
        }
        if (count == 2) {
            return this.blend(colors[0], brs2[0], colors[1], brs2[1]);
        }
        if (count == 3) {
            return this.blend(colors[0], brs2[0], colors[1], brs2[1], colors[2], brs2[2]);
        }
        return true;
    }

    private boolean blend(int[] cols0, float br0, int[] cols1, float br1) {
        if (cols1.length != cols0.length) {
            return false;
        }
        for (int i = 0; i < cols0.length; ++i) {
            int col0 = cols0[i];
            int red0 = col0 >> 16 & 0xFF;
            int green0 = col0 >> 8 & 0xFF;
            int blue0 = col0 & 0xFF;
            int col1 = cols1[i];
            int red1 = col1 >> 16 & 0xFF;
            int green1 = col1 >> 8 & 0xFF;
            int blue1 = col1 & 0xFF;
            int red = (int)((float)red0 * br0 + (float)red1 * br1);
            int green = (int)((float)green0 * br0 + (float)green1 * br1);
            int blue = (int)((float)blue0 * br0 + (float)blue1 * br1);
            cols0[i] = 0xFF000000 | red << 16 | green << 8 | blue;
        }
        return true;
    }

    private boolean blend(int[] cols0, float br0, int[] cols1, float br1, int[] cols2, float br2) {
        if (cols1.length != cols0.length || cols2.length != cols0.length) {
            return false;
        }
        for (int i = 0; i < cols0.length; ++i) {
            int col0 = cols0[i];
            int red0 = col0 >> 16 & 0xFF;
            int green0 = col0 >> 8 & 0xFF;
            int blue0 = col0 & 0xFF;
            int col1 = cols1[i];
            int red1 = col1 >> 16 & 0xFF;
            int green1 = col1 >> 8 & 0xFF;
            int blue1 = col1 & 0xFF;
            int col2 = cols2[i];
            int red2 = col2 >> 16 & 0xFF;
            int green2 = col2 >> 8 & 0xFF;
            int blue2 = col2 & 0xFF;
            int red = (int)((float)red0 * br0 + (float)red1 * br1 + (float)red2 * br2);
            int green = (int)((float)green0 * br0 + (float)green1 * br1 + (float)green2 * br2);
            int blue = (int)((float)blue0 * br0 + (float)blue1 * br1 + (float)blue2 * br2);
            cols0[i] = 0xFF000000 | red << 16 | green << 8 | blue;
        }
        return true;
    }
}

