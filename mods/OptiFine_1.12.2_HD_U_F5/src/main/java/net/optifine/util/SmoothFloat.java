/*
 * Decompiled with CFR 0.152.
 */
package net.optifine.util;

import net.optifine.util.NumUtils;

public class SmoothFloat {
    private float valueLast;
    private float timeFadeUpSec;
    private float timeFadeDownSec;
    private long timeLastMs;

    public SmoothFloat(float valueLast, float timeFadeSec) {
        this(valueLast, timeFadeSec, timeFadeSec);
    }

    public SmoothFloat(float valueLast, float timeFadeUpSec, float timeFadeDownSec) {
        this.valueLast = valueLast;
        this.timeFadeUpSec = timeFadeUpSec;
        this.timeFadeDownSec = timeFadeDownSec;
        this.timeLastMs = System.currentTimeMillis();
    }

    public float getValueLast() {
        return this.valueLast;
    }

    public float getTimeFadeUpSec() {
        return this.timeFadeUpSec;
    }

    public float getTimeFadeDownSec() {
        return this.timeFadeDownSec;
    }

    public long getTimeLastMs() {
        return this.timeLastMs;
    }

    public float getSmoothValue(float value, float timeFadeUpSec, float timeFadeDownSec) {
        this.timeFadeUpSec = timeFadeUpSec;
        this.timeFadeDownSec = timeFadeDownSec;
        return this.getSmoothValue(value);
    }

    public float getSmoothValue(float value) {
        float valSmooth;
        long timeNowMs = System.currentTimeMillis();
        float valPrev = this.valueLast;
        long timePrevMs = this.timeLastMs;
        float timeDeltaSec = (float)(timeNowMs - timePrevMs) / 1000.0f;
        float timeFadeSec = value >= valPrev ? this.timeFadeUpSec : this.timeFadeDownSec;
        this.valueLast = valSmooth = SmoothFloat.getSmoothValue(valPrev, value, timeDeltaSec, timeFadeSec);
        this.timeLastMs = timeNowMs;
        return valSmooth;
    }

    public static float getSmoothValue(float valPrev, float value, float timeDeltaSec, float timeFadeSec) {
        float valSmooth;
        if (timeDeltaSec <= 0.0f) {
            return valPrev;
        }
        float valDelta = value - valPrev;
        if (timeFadeSec > 0.0f && timeDeltaSec < timeFadeSec && Math.abs(valDelta) > 1.0E-6f) {
            float countUpdates = timeFadeSec / timeDeltaSec;
            float k1 = 4.61f;
            float k2 = 0.13f;
            float k3 = 10.0f;
            float kCorr = k1 - 1.0f / (k2 + countUpdates / k3);
            float kTime = timeDeltaSec / timeFadeSec * kCorr;
            kTime = NumUtils.limit(kTime, 0.0f, 1.0f);
            valSmooth = valPrev + valDelta * kTime;
        } else {
            valSmooth = value;
        }
        return valSmooth;
    }
}

