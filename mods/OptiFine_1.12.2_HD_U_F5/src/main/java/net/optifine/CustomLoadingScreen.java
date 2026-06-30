/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  buk
 *  bus
 *  bve
 *  cdy
 *  nf
 */
package net.optifine;

import java.util.Properties;
import net.optifine.CustomLoadingScreens;

public class CustomLoadingScreen {
    private nf locationTexture;
    private int scaleMode = 0;
    private int scale = 2;
    private boolean center;
    private static final int SCALE_DEFAULT = 2;
    private static final int SCALE_MODE_FIXED = 0;
    private static final int SCALE_MODE_FULL = 1;
    private static final int SCALE_MODE_STRETCH = 2;

    public CustomLoadingScreen(nf locationTexture, int scaleMode, int scale, boolean center) {
        this.locationTexture = locationTexture;
        this.scaleMode = scaleMode;
        this.scale = scale;
        this.center = center;
    }

    public static CustomLoadingScreen parseScreen(String path, int dimId, Properties props) {
        nf loc = new nf(path);
        int scaleMode = CustomLoadingScreen.parseScaleMode(CustomLoadingScreen.getProperty("scaleMode", dimId, props));
        int scaleDef = scaleMode == 0 ? 2 : 1;
        int scale = CustomLoadingScreen.parseScale(CustomLoadingScreen.getProperty("scale", dimId, props), scaleDef);
        boolean center = Config.parseBoolean(CustomLoadingScreen.getProperty("center", dimId, props), false);
        CustomLoadingScreen scr = new CustomLoadingScreen(loc, scaleMode, scale, center);
        return scr;
    }

    private static String getProperty(String key, int dim, Properties props) {
        if (props == null) {
            return null;
        }
        String val = props.getProperty("dim" + dim + "." + key);
        if (val != null) {
            return val;
        }
        val = props.getProperty(key);
        return val;
    }

    private static int parseScaleMode(String str) {
        if (str == null) {
            return 0;
        }
        if ((str = str.toLowerCase().trim()).equals("fixed")) {
            return 0;
        }
        if (str.equals("full")) {
            return 1;
        }
        if (str.equals("stretch")) {
            return 2;
        }
        CustomLoadingScreens.warn("Invalid scale mode: " + str);
        return 0;
    }

    private static int parseScale(String str, int def) {
        if (str == null) {
            return def;
        }
        int val = Config.parseInt(str = str.trim(), -1);
        if (val < 1) {
            CustomLoadingScreens.warn("Invalid scale: " + str);
            return def;
        }
        return val;
    }

    public void drawBackground(int width, int height) {
        bus.g();
        bus.p();
        bve tessellator = bve.a();
        buk bufferbuilder = tessellator.c();
        Config.getTextureManager().a(this.locationTexture);
        bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        double div = 16 * this.scale;
        double uMax = (double)width / div;
        double vMax = (double)height / div;
        double du = 0.0;
        double dv = 0.0;
        if (this.center) {
            du = (div - (double)width) / (div * 2.0);
            dv = (div - (double)height) / (div * 2.0);
        }
        switch (this.scaleMode) {
            case 1: {
                div = Math.max(width, height);
                uMax = (double)(this.scale * width) / div;
                vMax = (double)(this.scale * height) / div;
                if (!this.center) break;
                du = (double)this.scale * (div - (double)width) / (div * 2.0);
                dv = (double)this.scale * (div - (double)height) / (div * 2.0);
                break;
            }
            case 2: {
                uMax = this.scale;
                vMax = this.scale;
                du = 0.0;
                dv = 0.0;
            }
        }
        bufferbuilder.a(7, cdy.i);
        bufferbuilder.b(0.0, (double)height, 0.0).a(du, dv + vMax).b(255, 255, 255, 255).d();
        bufferbuilder.b((double)width, (double)height, 0.0).a(du + uMax, dv + vMax).b(255, 255, 255, 255).d();
        bufferbuilder.b((double)width, 0.0, 0.0).a(du + uMax, dv).b(255, 255, 255, 255).d();
        bufferbuilder.b(0.0, 0.0, 0.0).a(du, dv).b(255, 255, 255, 255).d();
        tessellator.b();
    }
}

