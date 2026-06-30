/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hu;
import eos.moe.dragoncore.tx;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class oy {
    public hu[] o;
    public hu[] y;
    public hu k;
    public tx[] ALLATORIxDEMO;

    public oy() {
        oy a2;
    }

    @SideOnly(value=Side.CLIENT)
    public void ALLATORIxDEMO(int a2, BufferBuilder a3, float a4) {
        boolean a5;
        oy a6;
        if (a6.k == null) {
            a6.k = a6.ALLATORIxDEMO();
        }
        boolean bl2 = a5 = a6.ALLATORIxDEMO != null && a6.ALLATORIxDEMO.length > 0;
        if (a2 < 0) {
            a2 = 4;
        }
        if (a5) {
            a3.begin(a2, DefaultVertexFormats.POSITION_TEX_NORMAL);
        } else {
            a3.begin(a2, DefaultVertexFormats.POSITION_NORMAL);
        }
        for (int a7 = 0; a7 < a6.o.length; ++a7) {
            if (a5) {
                a3.pos((double)a6.o[a7].y * (double)a4, (double)a6.o[a7].k * (double)a4, (double)a6.o[a7].ALLATORIxDEMO * (double)a4).tex((double)a6.ALLATORIxDEMO[a7].y, (double)a6.ALLATORIxDEMO[a7].k).normal(a6.k.y, a6.k.k, a6.k.ALLATORIxDEMO).endVertex();
                continue;
            }
            a3.pos((double)a6.o[a7].y * (double)a4, (double)a6.o[a7].k * (double)a4, (double)a6.o[a7].ALLATORIxDEMO * (double)a4).normal(a6.k.y, a6.k.k, a6.k.ALLATORIxDEMO).endVertex();
        }
        Tessellator.getInstance().draw();
    }

    public hu ALLATORIxDEMO() {
        oy a2;
        Vec3d a3 = new Vec3d((double)(a2.o[1].y - a2.o[0].y), (double)(a2.o[1].k - a2.o[0].k), (double)(a2.o[1].ALLATORIxDEMO - a2.o[0].ALLATORIxDEMO));
        Vec3d a4 = new Vec3d((double)(a2.o[2].y - a2.o[0].y), (double)(a2.o[2].k - a2.o[0].k), (double)(a2.o[2].ALLATORIxDEMO - a2.o[0].ALLATORIxDEMO));
        Vec3d a5 = a3.crossProduct(a4).normalize();
        return new hu((float)a5.x, (float)a5.y, (float)a5.z);
    }
}

