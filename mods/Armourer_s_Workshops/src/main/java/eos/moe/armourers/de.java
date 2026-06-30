/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.entity.EntityPlayerSP
 */
package eos.moe.armourers;

import net.minecraft.client.entity.EntityPlayerSP;

public class de {
    public double t;
    public float w;
    public float r;
    public double l;
    public float c;
    public float v;
    public float s;
    public double m;
    public float j;

    public de(EntityPlayerSP a2, float a3) {
        de a4;
        de de2 = a4;
        EntityPlayerSP entityPlayerSP = a2;
        de de3 = a4;
        EntityPlayerSP entityPlayerSP2 = a2;
        de de4 = a4;
        a4.j = a3;
        de4.l = a2.posX;
        de4.m = a2.posY;
        a4.t = entityPlayerSP2.posZ;
        de3.w = entityPlayerSP2.renderYawOffset;
        de3.r = a2.rotationYaw;
        a4.s = entityPlayerSP.rotationPitch;
        de2.v = entityPlayerSP.prevRotationYawHead;
        de2.c = a2.rotationYawHead;
    }
}

