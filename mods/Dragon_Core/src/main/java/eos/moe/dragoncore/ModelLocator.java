/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bax;
import java.io.Serializable;

public class ModelLocator
implements Serializable {
    private static final long serialVersionUID = -5751315363719049778L;
    private String bone;
    private float offsetX;
    private float offsetY;
    private float offsetZ;
    private float rotationX;
    private float rotationY;
    private float rotationZ;

    public ModelLocator(String a2, bax a3) {
        ModelLocator a4;
        a4.bone = a2;
        a4.offsetX = a3.x * 0.0625f;
        a4.offsetY = a3.y * 0.0625f;
        a4.offsetZ = a3.z * 0.0625f;
    }

    public ModelLocator(String a2, bax a3, bax a4) {
        ModelLocator a5;
        a5.bone = a2;
        a5.offsetX = a3.x * 0.0625f;
        a5.offsetY = a3.y * 0.0625f;
        a5.offsetZ = a3.z * 0.0625f;
        a5.rotationX = a4.x;
        a5.rotationY = a4.y;
        a5.rotationZ = a4.z;
    }

    public String getBone() {
        ModelLocator a2;
        return a2.bone;
    }

    public float getOffsetX() {
        ModelLocator a2;
        return a2.offsetX;
    }

    public float getOffsetY() {
        ModelLocator a2;
        return a2.offsetY;
    }

    public float getOffsetZ() {
        ModelLocator a2;
        return a2.offsetZ;
    }

    public float getRotationX() {
        ModelLocator a2;
        return a2.rotationX;
    }

    public float getRotationY() {
        ModelLocator a2;
        return a2.rotationY;
    }

    public float getRotationZ() {
        ModelLocator a2;
        return a2.rotationZ;
    }
}

