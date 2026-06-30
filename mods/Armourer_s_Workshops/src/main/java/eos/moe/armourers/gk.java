/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.VertexFormat
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.g;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class gk
implements g {
    private Tessellator m = Tessellator.getInstance();
    public static g j;

    @Override
    public void r(int a2, VertexFormat a3) {
        gk a4;
        a4.m.getBuffer().begin(a2, a3);
    }

    @Override
    public void r(float a2, float a3, float a4) {
        gk a5;
        a5.m.getBuffer().normal(a2, a3, a4);
    }

    private /* synthetic */ gk() {
        gk a2;
    }

    public static void z() {
        j = new gk();
    }

    @Override
    public void r(float a2, float a3, float a4, float a5) {
        gk a6;
        a6.m.getBuffer().color(a2, a3, a4, a5);
    }

    @Override
    public void r(double a2, double a3, double a4, double a5, double a6) {
        gk a7;
        a7.m.getBuffer().pos(a2, a3, a4);
        a7.m.getBuffer().tex(a5, a6);
    }

    @Override
    public void r(double a2, double a3) {
        gk a4;
        a4.m.getBuffer().tex(a2, a3);
    }

    @Override
    public void r(double a2, double a3, double a4) {
        gk a5;
        a5.m.getBuffer().pos(a2, a3, a4);
    }

    @Override
    public void y() {
        gk a2;
        a2.m.draw();
    }

    @Override
    public void r() {
        gk a2;
        a2.m.getBuffer().endVertex();
    }

    @Override
    public void r(VertexFormat a2) {
        gk a3;
        a3.m.getBuffer().begin(7, a2);
    }

    @Override
    public void r(int a2, int a3) {
        gk a4;
        a4.m.getBuffer().lightmap(a2, a3);
    }

    @Override
    public void r(byte a2, byte a3, byte a4, byte a5) {
        gk a6;
        a6.m.getBuffer().color((float)(a2 & 0xFF) / 255.0f, (float)(a3 & 0xFF) / 255.0f, (float)(a4 & 0xFF) / 255.0f, (float)(a5 & 0xFF) / 255.0f);
    }
}

