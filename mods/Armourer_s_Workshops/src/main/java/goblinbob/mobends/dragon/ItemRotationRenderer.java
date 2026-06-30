/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.dragoncore.api.model.AnimationModelRenderer
 *  eos.moe.dragoncore.bax
 */
package goblinbob.mobends.dragon;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import goblinbob.mobends.core.math.vector.Vec3d;

public class ItemRotationRenderer
implements AnimationModelRenderer {
    private Vec3d scale;
    private Vec3d offset;
    private Vec3d rotation;

    public ItemRotationRenderer(Vec3d offset, Vec3d rotation, Vec3d scale) {
        this.scale = scale;
        this.offset = offset;
        this.rotation = rotation;
    }

    public void setRotateAngle(float x2, float y2, float z2) {
        this.rotation.set(x2, y2, z2);
    }

    public void setOffsets(float x2, float y2, float z2) {
        this.offset.set(x2, y2, z2);
    }

    public bax getRotateAngle() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public bax getOffsets() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public bax getScaleFactor() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public bax getStartRotationAngles() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public void setScaleFactor(float scaleX, float scaleY, float scaleZ) {
        this.scale.set(scaleX, scaleY, scaleZ);
    }
}

