/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amu
 */
package net.optifine;

import net.optifine.CustomColormap;

public class LightMap {
    private CustomColormap lightMapRgb = null;
    private float[][] sunRgbs = new float[16][3];
    private float[][] torchRgbs = new float[16][3];

    public LightMap(CustomColormap lightMapRgb) {
        this.lightMapRgb = lightMapRgb;
    }

    public CustomColormap getColormap() {
        return this.lightMapRgb;
    }

    public boolean updateLightmap(amu world, float torchFlickerX, int[] lmColors, boolean nightvision) {
        if (this.lightMapRgb == null) {
            return false;
        }
        int height = this.lightMapRgb.getHeight();
        if (nightvision && height < 64) {
            return false;
        }
        int width = this.lightMapRgb.getWidth();
        if (width < 16) {
            LightMap.warn("Invalid lightmap width: " + width);
            this.lightMapRgb = null;
            return false;
        }
        int startIndex = 0;
        if (nightvision) {
            startIndex = width * 16 * 2;
        }
        float sun = 1.1666666f * (world.b(1.0f) - 0.2f);
        if (world.ai() > 0) {
            sun = 1.0f;
        }
        sun = Config.limitTo1(sun);
        float sunX = sun * (float)(width - 1);
        float torchX = Config.limitTo1(torchFlickerX + 0.5f) * (float)(width - 1);
        float gamma = Config.limitTo1(Config.getGameSettings().aE);
        boolean hasGamma = gamma > 1.0E-4f;
        float[][] colorsRgb = this.lightMapRgb.getColorsRgb();
        this.getLightMapColumn(colorsRgb, sunX, startIndex, width, this.sunRgbs);
        this.getLightMapColumn(colorsRgb, torchX, startIndex + 16 * width, width, this.torchRgbs);
        float[] rgb = new float[3];
        for (int is = 0; is < 16; ++is) {
            for (int it = 0; it < 16; ++it) {
                for (int ic = 0; ic < 3; ++ic) {
                    float comp = Config.limitTo1(this.sunRgbs[is][ic] + this.torchRgbs[it][ic]);
                    if (hasGamma) {
                        float cg = 1.0f - comp;
                        cg = 1.0f - cg * cg * cg * cg;
                        comp = gamma * cg + (1.0f - gamma) * comp;
                    }
                    rgb[ic] = comp;
                }
                int r = (int)(rgb[0] * 255.0f);
                int g = (int)(rgb[1] * 255.0f);
                int b2 = (int)(rgb[2] * 255.0f);
                lmColors[is * 16 + it] = 0xFF000000 | r << 16 | g << 8 | b2;
            }
        }
        return true;
    }

    private void getLightMapColumn(float[][] origMap, float x, int offset, int width, float[][] colRgb) {
        int xHigh;
        int xLow = (int)Math.floor(x);
        if (xLow == (xHigh = (int)Math.ceil(x))) {
            for (int y = 0; y < 16; ++y) {
                float[] rgbLow = origMap[offset + y * width + xLow];
                float[] rgb = colRgb[y];
                for (int i = 0; i < 3; ++i) {
                    rgb[i] = rgbLow[i];
                }
            }
            return;
        }
        float dLow = 1.0f - (x - (float)xLow);
        float dHigh = 1.0f - ((float)xHigh - x);
        for (int y = 0; y < 16; ++y) {
            float[] rgbLow = origMap[offset + y * width + xLow];
            float[] rgbHigh = origMap[offset + y * width + xHigh];
            float[] rgb = colRgb[y];
            for (int i = 0; i < 3; ++i) {
                rgb[i] = rgbLow[i] * dLow + rgbHigh[i] * dHigh;
            }
        }
    }

    private static void dbg(String str) {
        Config.dbg("CustomColors: " + str);
    }

    private static void warn(String str) {
        Config.warn("CustomColors: " + str);
    }
}

