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
import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.vector.SmoothVector3f;
import goblinbob.mobends.core.math.vector.Vec3d;

public class PlayerRenderer
implements AnimationModelRenderer {
    private SmoothOrientation centerRotation;
    private SmoothVector3f globalOffset;
    private Vec3d scale;

    public PlayerRenderer(SmoothOrientation centerRotation, SmoothVector3f globalOffset, Vec3d scale) {
        this.centerRotation = centerRotation;
        this.globalOffset = globalOffset;
        this.scale = scale;
    }

    public void setRotateAngle(float x2, float y2, float z2) {
        this.centerRotation.lock(x2, -y2, -z2);
    }

    public void setOffsets(float x2, float y2, float z2) {
        this.globalOffset.lock(x2, -y2, -z2);
    }

    public bax getRotateAngle() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public bax getOffsets() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public bax getScaleFactor() {
        return new bax(1.0f, 1.0f, 1.0f);
    }

    public bax getStartRotationAngles() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    public void setScaleFactor(float scaleX, float scaleY, float scaleZ) {
        this.scale.set(scaleX, scaleY, scaleZ);
    }
}

