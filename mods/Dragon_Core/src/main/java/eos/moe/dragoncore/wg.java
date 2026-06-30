/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  goblinbob.mobends.core.math.SmoothOrientation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import goblinbob.mobends.core.math.SmoothOrientation;

public class wg
implements AnimationModelRenderer {
    private SmoothOrientation ALLATORIxDEMO;

    public wg(SmoothOrientation a2) {
        wg a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public void setRotateAngle(float a2, float a3, float a4) {
        wg a5;
        a5.ALLATORIxDEMO.identity();
        a5.ALLATORIxDEMO.rotateX(a2).rotateY(a3).rotateZ(a4).finish();
        a5.ALLATORIxDEMO.updateSmooth();
    }

    @Override
    public void setOffsets(float a2, float a3, float a4) {
    }

    @Override
    public bax getRotateAngle() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    @Override
    public bax getOffsets() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    @Override
    public bax getScaleFactor() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    @Override
    public bax getStartRotationAngles() {
        return new bax(0.0f, 0.0f, 0.0f);
    }

    @Override
    public void setScaleFactor(float a2, float a3, float a4) {
    }
}

