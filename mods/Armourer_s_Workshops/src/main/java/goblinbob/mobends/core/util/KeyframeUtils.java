/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.math.SmoothOrientation;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.SmoothVector3f;

public class KeyframeUtils {
    public static void tweenVectorAdditive(SmoothVector3f target, float[] positionA, float[] positionB, float tween, float amount) {
        float x2 = positionA[0] + (positionB[0] - positionA[0]) * tween;
        float y2 = positionA[1] + (positionB[1] - positionA[1]) * tween;
        float z2 = positionA[2] + (positionB[2] - positionA[2]) * tween;
        target.add(x2 * amount, y2 * amount, z2 * amount);
        target.finish();
    }

    public static void tweenVectorAdditive(IVec3f target, float[] positionA, float[] positionB, float tween, float amount) {
        float x2 = positionA[0] + (positionB[0] - positionA[0]) * tween;
        float y2 = positionA[1] + (positionB[1] - positionA[1]) * tween;
        float z2 = positionA[2] + (positionB[2] - positionA[2]) * tween;
        target.add(x2 * amount, y2 * amount, z2 * amount);
    }

    public static void tweenVector(SmoothVector3f target, float[] positionA, float[] positionB, float tween) {
        float x2 = positionA[0] + (positionB[0] - positionA[0]) * tween;
        float y2 = positionA[1] + (positionB[1] - positionA[1]) * tween;
        float z2 = positionA[2] + (positionB[2] - positionA[2]) * tween;
        target.set(x2, y2, z2);
    }

    public static void tweenVector(IVec3f target, float[] positionA, float[] positionB, float tween) {
        float x2 = positionA[0] + (positionB[0] - positionA[0]) * tween;
        float y2 = positionA[1] + (positionB[1] - positionA[1]) * tween;
        float z2 = positionA[2] + (positionB[2] - positionA[2]) * tween;
        target.set(x2, y2, z2);
    }

    public static void tweenOrientationAdditive(SmoothOrientation target, float[] rotationA, float[] rotationB, float tween, float amount) {
        float x0 = rotationA[0];
        float y0 = rotationA[1];
        float z0 = rotationA[2];
        float w0 = rotationA[3];
        float x1 = rotationB[0];
        float y1 = rotationB[1];
        float z1 = rotationB[2];
        float w1 = rotationB[3];
        target.add((x0 + (x1 - x0) * tween) * amount, (y0 + (y1 - y0) * tween) * amount, (z0 + (z1 - z0) * tween) * amount, (w0 + (w1 - w0) * tween) * amount);
    }

    public static void tweenOrientation(SmoothOrientation target, float[] rotationA, float[] rotationB, float tween) {
        float x0 = rotationA[0];
        float y0 = rotationA[1];
        float z0 = rotationA[2];
        float w0 = rotationA[3];
        float x1 = rotationB[0];
        float y1 = rotationB[1];
        float z1 = rotationB[2];
        float w1 = rotationB[3];
        target.set(x0 + (x1 - x0) * tween, y0 + (y1 - y0) * tween, z0 + (z1 - z0) * tween, w0 + (w1 - w0) * tween);
    }
}

