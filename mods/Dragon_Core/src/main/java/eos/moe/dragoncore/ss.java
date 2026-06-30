/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ju;
import eos.moe.dragoncore.vy;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ss
extends ModelBase {
    public vy ALLATORIxDEMO;

    public ss(vy a2) {
        ss a3;
        a3.ALLATORIxDEMO = a2;
    }

    public static void ALLATORIxDEMO(ju a2, ju a3) {
        a3.s = a2.s;
        a3.g = a2.g;
        a3.t = a2.t;
        a3.i = a2.i;
        a3.l = a2.l;
        a3.z = a2.z;
    }

    public static void ALLATORIxDEMO(ju a2, ModelRenderer a3) {
        a3.rotateAngleX = a2.s;
        a3.rotateAngleY = a2.g;
        a3.rotateAngleZ = a2.t;
        a3.rotationPointX = a2.i;
        a3.rotationPointY = a2.l;
        a3.rotationPointZ = a2.z;
    }

    public static void ALLATORIxDEMO(ModelRenderer a2, ju a3) {
        a3.s = a2.rotateAngleX;
        a3.g = a2.rotateAngleY;
        a3.t = a2.rotateAngleZ;
        a3.i = a2.rotationPointX;
        a3.l = a2.rotationPointY;
        a3.z = a2.rotationPointZ;
    }

    public void render(Entity a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        ss a9;
        a9.ALLATORIxDEMO.ALLATORIxDEMO(a8);
        super.render(a2, a3, a4, a5, a6, a7, a8);
    }
}

