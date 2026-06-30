/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bus
 */
package net.optifine.render;

public class Blender {
    public static final int BLEND_ALPHA = 0;
    public static final int BLEND_ADD = 1;
    public static final int BLEND_SUBSTRACT = 2;
    public static final int BLEND_MULTIPLY = 3;
    public static final int BLEND_DODGE = 4;
    public static final int BLEND_BURN = 5;
    public static final int BLEND_SCREEN = 6;
    public static final int BLEND_OVERLAY = 7;
    public static final int BLEND_REPLACE = 8;
    public static final int BLEND_DEFAULT = 1;

    public static int parseBlend(String str) {
        if (str == null) {
            return 1;
        }
        if ((str = str.toLowerCase().trim()).equals("alpha")) {
            return 0;
        }
        if (str.equals("add")) {
            return 1;
        }
        if (str.equals("subtract")) {
            return 2;
        }
        if (str.equals("multiply")) {
            return 3;
        }
        if (str.equals("dodge")) {
            return 4;
        }
        if (str.equals("burn")) {
            return 5;
        }
        if (str.equals("screen")) {
            return 6;
        }
        if (str.equals("overlay")) {
            return 7;
        }
        if (str.equals("replace")) {
            return 8;
        }
        Config.warn("Unknown blend: " + str);
        return 1;
    }

    public static void setupBlend(int blend, float brightness) {
        switch (blend) {
            case 0: {
                bus.d();
                bus.m();
                bus.b((int)770, (int)771);
                bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)brightness);
                break;
            }
            case 1: {
                bus.d();
                bus.m();
                bus.b((int)770, (int)1);
                bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)brightness);
                break;
            }
            case 2: {
                bus.d();
                bus.m();
                bus.b((int)775, (int)0);
                bus.c((float)brightness, (float)brightness, (float)brightness, (float)1.0f);
                break;
            }
            case 3: {
                bus.d();
                bus.m();
                bus.b((int)774, (int)771);
                bus.c((float)brightness, (float)brightness, (float)brightness, (float)brightness);
                break;
            }
            case 4: {
                bus.d();
                bus.m();
                bus.b((int)1, (int)1);
                bus.c((float)brightness, (float)brightness, (float)brightness, (float)1.0f);
                break;
            }
            case 5: {
                bus.d();
                bus.m();
                bus.b((int)0, (int)769);
                bus.c((float)brightness, (float)brightness, (float)brightness, (float)1.0f);
                break;
            }
            case 6: {
                bus.d();
                bus.m();
                bus.b((int)1, (int)769);
                bus.c((float)brightness, (float)brightness, (float)brightness, (float)1.0f);
                break;
            }
            case 7: {
                bus.d();
                bus.m();
                bus.b((int)774, (int)768);
                bus.c((float)brightness, (float)brightness, (float)brightness, (float)1.0f);
                break;
            }
            case 8: {
                bus.e();
                bus.l();
                bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)brightness);
            }
        }
        bus.y();
    }

    public static void clearBlend(float rainBrightness) {
        bus.d();
        bus.m();
        bus.b((int)770, (int)1);
        bus.c((float)1.0f, (float)1.0f, (float)1.0f, (float)rainBrightness);
    }
}

