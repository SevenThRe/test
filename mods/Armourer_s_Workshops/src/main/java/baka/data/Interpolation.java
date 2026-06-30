/*
 * Decompiled with CFR 0.152.
 */
package baka.data;

public class Interpolation {
    public static float cal(float start, float stop, float progress) {
        return (stop - start) * progress + start;
    }

    public static float progress(long start, float v2, long current) {
        float progress = Interpolation.progress(start, (long)(v2 * 1000.0f) + start, current);
        return Math.max(0.0f, Math.min(progress, 1.0f));
    }

    private static float progress(long start, long stop, long current) {
        return (float)(current - start) * 1.0f / (float)(stop - start);
    }
}

