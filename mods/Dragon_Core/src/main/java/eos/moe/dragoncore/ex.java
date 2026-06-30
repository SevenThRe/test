/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.ea;
import eos.moe.dragoncore.rz;

public class ex {
    public ex() {
        ex a2;
    }

    public static float ALLATORIxDEMO(int a2) {
        return (float)a2 / 1000.0f * 20.0f;
    }

    public static void f(AnimationModelRenderer a2, ea a3, bax a4) {
        rz a5 = a3.ALLATORIxDEMO();
        ex.ALLATORIxDEMO(a4, a3.ALLATORIxDEMO());
        if (a5 == rz.y) {
            a2.setRotateAngle(a2.getStartRotationAngles().getX() + a4.getX(), a2.getStartRotationAngles().getY() + a4.getY(), a2.getStartRotationAngles().getZ() + a4.getZ());
        } else if (a5 == rz.k) {
            a2.setRotateAngle(a2.getRotateAngle().getX() + a4.getX(), a2.getRotateAngle().getY() + a4.getY(), a2.getRotateAngle().getZ() + a4.getZ());
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public static void c(AnimationModelRenderer a2, ea a3, bax a4) {
        rz a5 = a3.ALLATORIxDEMO();
        ex.ALLATORIxDEMO(a4, a3.ALLATORIxDEMO());
        if (a5 == rz.y) {
            a2.setOffsets(a4.getX(), a4.getY(), a4.getZ());
        } else if (a5 == rz.k) {
            a2.setOffsets(a2.getOffsets().x + a4.getX(), a2.getOffsets().y + a4.getY(), a2.getOffsets().z + a4.getZ());
        } else {
            throw new UnsupportedOperationException();
        }
    }

    public static void ALLATORIxDEMO(AnimationModelRenderer a2, ea a3, bax a4) {
        rz a5 = a3.ALLATORIxDEMO();
        float a6 = a3.ALLATORIxDEMO();
        a4.set(ex.ALLATORIxDEMO(a4.getX(), a6), ex.ALLATORIxDEMO(a4.getY(), a6), ex.ALLATORIxDEMO(a4.getZ(), a6));
        if (a5 == rz.y) {
            a2.setScaleFactor(a4.getX(), a4.getY(), a4.getZ());
        } else if (a5 == rz.k) {
            bax a7 = a2.getScaleFactor();
            a2.setScaleFactor(a4.getX() * a7.getX(), a4.getY() * a7.getY(), a4.getZ() * a7.getZ());
        } else {
            throw new UnsupportedOperationException();
        }
    }

    private static /* synthetic */ float ALLATORIxDEMO(float a2, float a3) {
        return a2 > 1.0f ? 1.0f + (a2 - 1.0f) * a3 : 1.0f - (1.0f - a2) * a3;
    }

    public static void ALLATORIxDEMO(bax a2, float a3) {
        a2.x *= a3;
        a2.y *= a3;
        a2.z *= a3;
    }
}

