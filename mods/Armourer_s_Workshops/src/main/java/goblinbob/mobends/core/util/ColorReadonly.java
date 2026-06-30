/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.util.IColorRead;

public class ColorReadonly
implements IColorRead {
    private final float r;
    private final float g;
    private final float b;
    private final float a;

    public ColorReadonly(float r2, float g2, float b2) {
        this.r = r2;
        this.g = g2;
        this.b = b2;
        this.a = 1.0f;
    }

    public ColorReadonly(float r2, float g2, float b2, float a2) {
        this.r = r2;
        this.g = g2;
        this.b = b2;
        this.a = a2;
    }

    public ColorReadonly(int hexValue) {
        int valueB = hexValue & 0xFF;
        int valueG = (hexValue >>= 8) & 0xFF;
        int valueR = (hexValue >>= 8) & 0xFF;
        int valueA = (hexValue >>= 8) & 0xFF;
        hexValue >>= 8;
        this.a = (float)valueA / 255.0f;
        this.r = (float)valueR / 255.0f;
        this.g = (float)valueG / 255.0f;
        this.b = (float)valueB / 255.0f;
    }

    @Override
    public float getR() {
        return this.r;
    }

    @Override
    public float getG() {
        return this.g;
    }

    @Override
    public float getB() {
        return this.b;
    }

    @Override
    public float getA() {
        return this.a;
    }
}

