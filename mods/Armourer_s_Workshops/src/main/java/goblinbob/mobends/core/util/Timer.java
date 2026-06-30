/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

public class Timer {
    private long time;
    private long lastTime;
    private float delta;

    public Timer() {
        this.lastTime = this.time = System.nanoTime();
    }

    public float tick() {
        this.time = System.nanoTime();
        this.delta = (float)(this.time - this.lastTime) / 1000000.0f;
        this.lastTime = this.time;
        return this.delta;
    }

    public float getDelta() {
        return this.delta;
    }
}

