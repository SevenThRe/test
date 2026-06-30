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

public class HandItemRotationRenderer
implements AnimationModelRenderer {
    private SmoothOrientation orientation;

    public HandItemRotationRenderer(SmoothOrientation orientation) {
        this.orientation = orientation;
    }

    public void setRotateAngle(float x2, float y2, float z2) {
        this.orientation.lock(x2, y2, z2);
    }

    public void setOffsets(float x2, float y2, float z2) {
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
    }
}

