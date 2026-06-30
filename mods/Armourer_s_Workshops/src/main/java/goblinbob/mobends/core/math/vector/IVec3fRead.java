/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.math.vector;

public interface IVec3fRead {
    public float getX();

    public float getY();

    public float getZ();

    public float lengthSq();

    default public float length() {
        return (float)Math.sqrt(this.lengthSq());
    }
}

