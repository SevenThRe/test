/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 */
package journeymap.common.feature;

import com.google.common.base.MoreObjects;

public class Location {
    private double x;
    private double y;
    private double z;
    private int dim;

    public Location() {
    }

    public Location(double x, double y, double z, int dim) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.dim = dim;
    }

    public double getX() {
        return this.x;
    }

    public double getY() {
        return this.y;
    }

    public double getZ() {
        return this.z;
    }

    public int getDim() {
        return this.dim;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("x", this.x).add("y", this.y).add("z", this.z).add("dim", this.dim).toString();
    }
}

