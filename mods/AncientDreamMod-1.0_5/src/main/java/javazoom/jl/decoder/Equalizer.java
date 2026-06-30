/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

public final class Equalizer {
    public static final float BAND_NOT_PRESENT = Float.NEGATIVE_INFINITY;
    public static final Equalizer PASS_THRU_EQ = new Equalizer();
    private static final int BANDS = 32;
    private final float[] settings = new float[32];

    public Equalizer() {
    }

    public Equalizer(float[] fArray) {
        this.setFrom(fArray);
    }

    public Equalizer(EQFunction eQFunction) {
        this.setFrom(eQFunction);
    }

    public void setFrom(float[] fArray) {
        this.reset();
        int n = fArray.length > 32 ? 32 : fArray.length;
        for (int i = 0; i < n; ++i) {
            this.settings[i] = this.limit(fArray[i]);
        }
    }

    public void setFrom(EQFunction eQFunction) {
        this.reset();
        int n = 32;
        for (int i = 0; i < n; ++i) {
            this.settings[i] = this.limit(eQFunction.getBand(i));
        }
    }

    public void setFrom(Equalizer equalizer) {
        if (equalizer != this) {
            this.setFrom(equalizer.settings);
        }
    }

    public void reset() {
        for (int i = 0; i < 32; ++i) {
            this.settings[i] = 0.0f;
        }
    }

    public int getBandCount() {
        return this.settings.length;
    }

    public float setBand(int n, float f) {
        float f2 = 0.0f;
        if (n >= 0 && n < 32) {
            f2 = this.settings[n];
            this.settings[n] = this.limit(f);
        }
        return f2;
    }

    public float getBand(int n) {
        float f = 0.0f;
        if (n >= 0 && n < 32) {
            f = this.settings[n];
        }
        return f;
    }

    private float limit(float f) {
        if (f == Float.NEGATIVE_INFINITY) {
            return f;
        }
        if (f > 1.0f) {
            return 1.0f;
        }
        if (f < -1.0f) {
            return -1.0f;
        }
        return f;
    }

    float[] getBandFactors() {
        float[] fArray = new float[32];
        int n = 32;
        for (int i = 0; i < n; ++i) {
            fArray[i] = this.getBandFactor(this.settings[i]);
        }
        return fArray;
    }

    float getBandFactor(float f) {
        if (f == Float.NEGATIVE_INFINITY) {
            return 0.0f;
        }
        float f2 = (float)Math.pow(2.0, f);
        return f2;
    }

    public static abstract class EQFunction {
        public float getBand(int n) {
            return 0.0f;
        }
    }
}

