/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.animation.keyframe;

public class Keyframe {
    public float[] position;
    public float[] rotation;
    public float[] scale;

    public void mirrorRotationYZ() {
        this.rotation[1] = this.rotation[1] * -1.0f;
        this.rotation[2] = this.rotation[2] * -1.0f;
    }

    public void swapRotationYZ() {
        float y2 = this.rotation[1];
        this.rotation[1] = this.rotation[2];
        this.rotation[2] = y2;
    }
}

