/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

public abstract class Obuffer {
    public static final int OBUFFERSIZE = 2304;
    public static final int MAXCHANNELS = 2;

    public abstract void append(int var1, short var2);

    public void appendSamples(int n, float[] fArray) {
        int n2 = 0;
        while (n2 < 32) {
            short s = this.clip(fArray[n2++]);
            this.append(n, s);
        }
    }

    private final short clip(float f) {
        return (short)(f > 32767.0f ? Short.MAX_VALUE : (short)(f < -32768.0f ? Short.MIN_VALUE : (short)f));
    }

    public abstract void write_buffer(int var1);

    public abstract void close();

    public abstract void clear_buffer();

    public abstract void set_stop_flag();
}

