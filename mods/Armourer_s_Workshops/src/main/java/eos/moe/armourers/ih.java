/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.a;
import eos.moe.armourers.fk;
import eos.moe.armourers.j;
import eos.moe.armourers.kf;
import eos.moe.armourers.km;
import eos.moe.armourers.mn;
import eos.moe.armourers.o;
import eos.moe.armourers.r;
import eos.moe.armourers.vn;
import eos.moe.armourers.xf;
import eos.moe.armourers.y;
import eos.moe.armourers.yl;
import java.util.ArrayList;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class ih {
    public static boolean l;
    public static boolean c;
    public static boolean v;
    public EntityPlayer s;
    public static boolean m;
    public static boolean j;

    public static void r(yl a2, boolean a3, boolean a4) {
        r r2 = a2.r();
        km.t.r(r2).render(null, a2, null, a3, new mn(), null, true, 0.0, a4);
    }

    public ih() {
        ih a2;
    }

    public static void r(fk a2, boolean a3, boolean a4, float a5, float a6) {
        ih.r(a2.r(), a3, a4, a5, a6);
    }

    public static void r(yl a2, boolean a3, boolean a4, float a5, float a6) {
        j j2;
        int n2;
        Object object;
        int n3;
        if (a2 == null) {
            return;
        }
        float f2 = 16.0f;
        f2 = 1.0f / f2;
        ArrayList<xf> arrayList = new ArrayList<xf>();
        ArrayList<j> arrayList2 = new ArrayList<j>();
        int n4 = n3 = 0;
        while (n4 < a2.y()) {
            kf kf2 = a2.y().get(n3);
            if (a2.r() != vn.c || n3 <= 0) {
                kf kf3 = kf2;
                object = kf3.y();
                a a7 = kf3.r().r();
                xf xf2 = new xf(((xf)object).x() + a7.z(), ((xf)object).s() + a7.r(), ((xf)object).r() + a7.y(), ((xf)object).y(), ((xf)object).h(), ((xf)object).z());
                arrayList.add(xf2);
            }
            n4 = ++n3;
        }
        if (a2.r()) {
            int n5;
            ArrayList<y> arrayList3 = a2.r().r();
            int n6 = n5 = 0;
            while (n6 < arrayList3.size()) {
                object = arrayList3.get(n5);
                if (object instanceof o && object.y() != null) {
                    arrayList2.add(object.y());
                }
                n6 = ++n5;
            }
        }
        n3 = 256;
        int n7 = 256;
        int n8 = 256;
        int n9 = -256;
        int n10 = -256;
        int n11 = -256;
        int n12 = n2 = 0;
        while (n12 < arrayList.size()) {
            j2 = (j)arrayList.get(n2);
            n3 = Math.min(n3, j2.x());
            n7 = Math.min(n7, j2.s());
            n8 = Math.min(n8, j2.r());
            n9 = Math.max(n9, j2.y() + j2.x());
            n10 = Math.max(n10, j2.h() + j2.s());
            n11 = Math.max(n11, j2.z() + j2.r());
            n12 = ++n2;
        }
        int n13 = n2 = 0;
        while (n13 < arrayList2.size()) {
            j2 = (j)arrayList2.get(n2);
            n3 = Math.min(n3, j2.x());
            n7 = Math.min(n7, j2.s());
            n8 = Math.min(n8, j2.r());
            n9 = Math.max(n9, j2.y() + j2.x());
            n10 = Math.max(n10, j2.h() + j2.s());
            n11 = Math.max(n11, j2.z() + j2.r());
            n13 = ++n2;
        }
        xf xf3 = new xf(n3, n7, n8, n9 - n3, n10 - n7, n11 - n8);
        GL11.glPushMatrix();
        if (c) {
            float f3 = System.currentTimeMillis() / 10L % 360L;
            GL11.glRotatef((float)f3, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        if (v) {
            ih.r(new xf((int)(-a5) / 2, (int)(-a6) / 2, (int)(-a5) / 2, (int)a5, (int)a6, (int)a5), 0, 0, 255);
        }
        xf xf4 = xf3;
        int n14 = Math.max(xf4.y(), xf3.h());
        n14 = Math.max(n14, xf3.z());
        float f4 = Math.min(a5 /= (float)Math.max(xf3.y(), xf3.z()), a6 /= (float)xf3.h());
        GL11.glScalef((float)f4, (float)f4, (float)f4);
        GL11.glTranslated((double)(-((float)xf4.y() / 2.0f + (float)xf3.x()) * f2), (double)0.0, (double)0.0);
        GL11.glTranslated((double)0.0, (double)(-((float)xf3.h() / 2.0f + (float)xf3.s()) * f2), (double)0.0);
        GL11.glTranslated((double)0.0, (double)0.0, (double)(-((float)xf3.z() / 2.0f + (float)xf3.r()) * f2));
        ih.r(a2, a3 != 0, a4);
        if (m) {
            ih.r(xf3, 255, 255, 0);
        }
        if (l) {
            int n15 = a3 = 0;
            while (n15 < arrayList.size()) {
                j j3 = (j)arrayList.get(a3);
                ih.r(j3, 255, 0, 0);
                n15 = ++a3;
            }
        }
        if (j) {
            int n16 = a3 = 0;
            while (n16 < arrayList2.size()) {
                j j4 = (j)arrayList2.get(a3);
                ih.r(j4, 0, 255, 0);
                n16 = ++a3;
            }
        }
        GL11.glPopMatrix();
    }

    public static void r(fk a2, boolean a3) {
        yl yl2 = a2.r();
        if (yl2 == null) {
            return;
        }
        km.t.m.render(null, yl2, null, true, new mn(), null, true, 0.0, a3);
    }

    public static void r(j a2, int a3, int a4, int a5) {
        float f2 = 0.0625f;
        AxisAlignedBB axisAlignedBB = new AxisAlignedBB((double)((float)a2.x() * f2), (double)((float)a2.s() * f2), (double)((float)a2.r() * f2), (double)((float)(a2.x() + a2.y()) * f2), (double)((float)(a2.s() + a2.h()) * f2), (double)((float)(a2.r() + a2.z()) * f2));
        GL11.glEnable((int)3042);
        GL11.glDisable((int)2896);
        OpenGlHelper.glBlendFunc((int)770, (int)771, (int)1, (int)0);
        GL11.glLineWidth((float)1.0f);
        GL11.glDisable((int)3553);
        RenderGlobal.drawSelectionBoundingBox((AxisAlignedBB)axisAlignedBB, (float)((float)a3 / 255.0f), (float)((float)a4 / 255.0f), (float)((float)a5 / 255.0f), (float)1.0f);
        GL11.glEnable((int)3553);
        GL11.glEnable((int)2896);
        GL11.glDisable((int)3042);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    public static void r(ItemStack a2, boolean a3, float a4, float a5) {
        fk fk2 = fk.r(a2);
        if (fk2 != null) {
            ih.r(fk2, a3, false, a4, a5);
        }
    }
}

