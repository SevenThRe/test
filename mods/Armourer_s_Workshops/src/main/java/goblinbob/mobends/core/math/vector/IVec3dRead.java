/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

public interface IVec3dRead {
    public double getX();

    public double getY();

    public double getZ();

    public double lengthSq();

    default public double length() {
        return Math.sqrt(this.lengthSq());
    }
}

