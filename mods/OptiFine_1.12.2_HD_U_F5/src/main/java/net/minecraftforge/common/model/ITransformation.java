/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  fa
 */
package net.minecraftforge.common.model;

import javax.vecmath.Matrix4f;

public interface ITransformation {
    public Matrix4f getMatrix();

    public fa rotate(fa var1);

    public int rotate(fa var1, int var2);
}

