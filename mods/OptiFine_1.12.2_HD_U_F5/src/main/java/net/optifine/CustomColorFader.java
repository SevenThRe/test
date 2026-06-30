/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bhe
 */
package net.optifine;

public class CustomColorFader {
    private bhe color = null;
    private long timeUpdate = System.currentTimeMillis();

    public bhe getColor(double x, double y, double z) {
        if (this.color == null) {
            this.color = new bhe(x, y, z);
            return this.color;
        }
        long timeNow = System.currentTimeMillis();
        long timeDiff = timeNow - this.timeUpdate;
        if (timeDiff == 0L) {
            return this.color;
        }
        this.timeUpdate = timeNow;
        if (Math.abs(x - this.color.b) < 0.004 && Math.abs(y - this.color.c) < 0.004 && Math.abs(z - this.color.d) < 0.004) {
            return this.color;
        }
        double k = (double)timeDiff * 0.001;
        k = Config.limit(k, 0.0, 1.0);
        double dx = x - this.color.b;
        double dy = y - this.color.c;
        double dz = z - this.color.d;
        double xn = this.color.b + dx * k;
        double yn = this.color.c + dy * k;
        double zn = this.color.d + dz * k;
        this.color = new bhe(xn, yn, zn);
        return this.color;
    }
}

