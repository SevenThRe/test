/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.util.ColorReadonly;
import goblinbob.mobends.core.util.GUtil;
import goblinbob.mobends.core.util.IColor;
import goblinbob.mobends.core.util.IColorRead;

public class Color
implements IColor {
    public static final ColorReadonly WHITE = new ColorReadonly(1.0f, 1.0f, 1.0f, 1.0f);
    public static final ColorReadonly RED = new ColorReadonly(1.0f, 0.0f, 0.0f, 1.0f);
    public static final ColorReadonly GREEN = new ColorReadonly(0.0f, 1.0f, 0.0f, 1.0f);
    public static final ColorReadonly BLUE = new ColorReadonly(0.0f, 0.0f, 1.0f, 1.0f);
    public static final ColorReadonly BLACK = new ColorReadonly(0.0f, 0.0f, 0.0f, 1.0f);
    public static final ColorReadonly ZERO = new ColorReadonly(0.0f, 0.0f, 0.0f, 0.0f);
    public float r;
    public float g;
    public float b;
    public float a;

    public Color(float r2, float g2, float b2) {
        this.r = r2;
        this.g = g2;
        this.b = b2;
        this.a = 1.0f;
    }

    public Color(float r2, float g2, float b2, float a2) {
        this.r = r2;
        this.g = g2;
        this.b = b2;
        this.a = a2;
    }

    public Color(int hexValue) {
        this.hex(hexValue);
    }

    public Color(IColorRead other) {
        this.r = other.getR();
        this.g = other.getG();
        this.b = other.getB();
        this.a = other.getA();
    }

    public void hex(int hexValue) {
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

    @Override
    public void set(float r2, float g2, float b2, float a2) {
        this.r = r2;
        this.g = g2;
        this.b = b2;
        this.a = a2;
    }

    @Override
    public void setR(float r2) {
        this.r = r2;
    }

    @Override
    public void setG(float g2) {
        this.g = g2;
    }

    @Override
    public void setB(float b2) {
        this.b = b2;
    }

    @Override
    public void setA(float a2) {
        this.a = a2;
    }

    @Override
    public void add(float r2, float g2, float b2, float a2) {
        this.r += r2;
        this.g += g2;
        this.b += b2;
        this.a += a2;
    }

    public static int asHex(IColorRead color) {
        int valueA = (int)GUtil.clamp(color.getA() * 255.0f, 0.0f, 255.0f);
        int valueR = (int)GUtil.clamp(color.getR() * 255.0f, 0.0f, 255.0f);
        int valueG = (int)GUtil.clamp(color.getG() * 255.0f, 0.0f, 255.0f);
        int valueB = (int)GUtil.clamp(color.getB() * 255.0f, 0.0f, 255.0f);
        int value = valueA;
        value <<= 8;
        value |= valueR & 0xFF;
        value <<= 8;
        value |= valueG & 0xFF;
        value <<= 8;
        return value |= valueB & 0xFF;
    }

    public static Color fromHexRGB(int hexValue) {
        int valueB = hexValue & 0xFF;
        int valueG = (hexValue >>= 8) & 0xFF;
        int valueR = (hexValue >>= 8) & 0xFF;
        hexValue >>= 8;
        return new Color((float)valueR / 255.0f, (float)valueG / 255.0f, (float)valueB / 255.0f, 1.0f);
    }

    public static Color fromHex(int hexValue) {
        int valueB = hexValue & 0xFF;
        int valueG = (hexValue >>= 8) & 0xFF;
        int valueR = (hexValue >>= 8) & 0xFF;
        int valueA = (hexValue >>= 8) & 0xFF;
        hexValue >>= 8;
        return new Color((float)valueR / 255.0f, (float)valueG / 255.0f, (float)valueB / 255.0f, (float)valueA / 255.0f);
    }
}

