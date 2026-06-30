/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.armourers;

import eos.moe.armourers.bh;
import eos.moe.armourers.fe;
import eos.moe.armourers.g;
import eos.moe.armourers.jg;
import eos.moe.armourers.mk;
import eos.moe.armourers.o;
import eos.moe.armourers.oh;
import eos.moe.armourers.qd;
import eos.moe.armourers.se;
import eos.moe.armourers.te;
import eos.moe.armourers.ul;
import eos.moe.armourers.ym;
import java.awt.Point;
import java.awt.image.BufferedImage;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.MathHelper;

public class gl {
    private final byte z;
    public byte t;
    public byte w;
    public byte r;
    public byte l;
    public byte c;
    public byte v;
    private final byte s;
    public byte m;
    public byte j;

    private static /* synthetic */ byte[] r(byte a2, byte a32, byte a4, byte a5, byte a6, byte a7, byte a82, mk a92, BufferedImage a10, o a11) {
        Point point;
        boolean bl;
        int n2;
        EnumFacing a82 = EnumFacing.VALUES[a82];
        Point point2 = a11.z();
        int a92 = point2.x;
        int n3 = point2.y;
        int n4 = 0;
        int n5 = 0;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        switch (a82) {
            case NORTH: {
                o o2 = a11;
                while (false) {
                }
                n4 = o2.r().z();
                n5 = o2.r().z();
                n6 = o2.r().y();
                n7 = o2.r().h();
                n8 = o2.r().x() + n6 + a2;
                n9 = o2.r().s() + n7 + a32;
                n2 = a92;
                break;
            }
            case EAST: {
                n4 = 0;
                o o3 = a11;
                n5 = o3.r().z();
                n6 = o3.r().z();
                n7 = o3.r().h();
                n8 = o3.r().r() + n6 + a4;
                n8 = n6 - n8 - 1;
                n9 = o3.r().s() + n7 + a32;
                n2 = a92;
                break;
            }
            case SOUTH: {
                o o4 = a11;
                o o5 = a11;
                n4 = o4.r().z() * 2 + o5.r().y();
                n5 = o4.r().z();
                n6 = o5.r().y();
                n7 = o4.r().h();
                n8 = n6 + a11.r().x() + a2;
                n8 = n6 - n8 - 1;
                n9 = o4.r().s() + n7 + a32;
                n2 = a92;
                break;
            }
            case WEST: {
                o o6 = a11;
                o o7 = a11;
                n4 = o6.r().z() + o7.r().y();
                n5 = o6.r().z();
                n6 = o7.r().z();
                n7 = o6.r().h();
                n8 = o6.r().r() + n6 + a4;
                n9 = o6.r().s() + n7 + a32;
                n2 = a92;
                break;
            }
            case UP: {
                o o8 = a11;
                n4 = o8.r().z();
                n5 = 0;
                n6 = o8.r().y();
                n7 = o8.r().z();
                n8 = o8.r().x() + n6 + a2;
                n9 = o8.r().r() + n7 + a4;
                n2 = a92;
                break;
            }
            case DOWN: {
                o o9 = a11;
                o o10 = a11;
                n4 = o9.r().z() + o10.r().y();
                n5 = 0;
                n6 = o9.r().y();
                n7 = o10.r().z();
                n8 = o9.r().x() + n6 + a2;
                n9 = o9.r().r() + n7 + a4;
                n9 = n7 - n9 - 1;
            }
            default: {
                n2 = a92;
            }
        }
        a32 = (byte)MathHelper.clamp((int)(n2 + n4 + n8), (int)(a92 + n4), (int)(a92 + n4 + n6));
        a4 = (byte)MathHelper.clamp((int)(n3 + n5 + n9), (int)(n3 + n5), (int)(n3 + n5 + n7));
        Point a32 = new Point(a32, a4);
        if (a32.x >= 0) {
            bl = true;
            point = a32;
        } else {
            bl = false;
            point = a32;
        }
        if (bl & point.y >= 0 & a32.x < a10.getWidth() & a32.y < a10.getHeight()) {
            Point point3 = a32;
            return fe.r(a10.getRGB(point3.x, point3.y));
        }
        byte[] byArray = new byte[3];
        byArray[0] = a5;
        byArray[1] = a6;
        byArray[2] = a7;
        return byArray;
    }

    public gl(byte a2, byte a3, byte a4, byte a5, byte a6, byte a7, byte a8, byte a9, byte a10, byte a11) {
        gl a12;
        gl gl2 = a12;
        gl gl3 = a12;
        gl gl4 = a12;
        gl gl5 = a12;
        gl gl6 = a12;
        gl6.m = a2;
        gl6.w = a3;
        gl5.v = a4;
        gl5.t = a5;
        gl4.r = a6;
        gl4.j = a7;
        gl3.s = a8;
        gl3.l = a9;
        gl2.c = a10;
        gl2.z = a11;
    }

    public static byte[] r(byte a2, byte a3, byte a4, byte[] a5, int[] a62) {
        if (a5.length == 4 && (a5[3] & 0xFF) == 0) {
            byte[] byArray = new byte[3];
            byArray[0] = a2;
            byArray[1] = a3;
            byArray[2] = a4;
            return byArray;
        }
        a3 = (byte)(((a2 & 0xFF) + (a3 & 0xFF) + (a4 & 0xFF)) / 3);
        a4 = (byte)((a62[0] + a62[1] + a62[2]) / 3);
        int a62 = a3 + (a5[0] & 0xFF) - a4;
        int n2 = a3 + (a5[1] & 0xFF) - a4;
        a3 = (byte)(a3 + (a5[2] & 0xFF) - a4);
        a62 = MathHelper.clamp((int)a62, (int)0, (int)255);
        n2 = MathHelper.clamp((int)n2, (int)0, (int)255);
        a3 = (byte)MathHelper.clamp((int)a3, (int)0, (int)255);
        byte[] byArray = new byte[3];
        byArray[0] = (byte)a62;
        byArray[1] = (byte)n2;
        byArray[2] = a3;
        return byArray;
    }

    public void r(g a22, mk a3, ul a4) {
        Object object;
        Object object2;
        gl a5;
        gl gl2 = a5;
        byte a22 = gl2.t;
        byte by = gl2.r;
        byte by2 = gl2.j;
        Object object3 = te.r(gl2.l);
        mk mk2 = a3;
        Object object4 = mk2.r();
        oh oh2 = mk2.r();
        if (object3 == te.f) {
            return;
        }
        ym ym2 = object3;
        int n2 = ym2.r();
        if (ym2.z() >= 1 && ((ym)object3).z() <= 8 && object4 != null && object4.r(((ym)object3).z() - 1)) {
            byte[] byArray = object4.r(((ym)object3).z() - 1);
            object2 = byArray;
            if (byArray.length == 4) {
                object = te.r(object2[3]);
                if (object == te.f) {
                    return;
                }
                if (object == te.c) {
                    byte[] byArray2;
                    oh oh3;
                    boolean bl;
                    object4 = a4.getAverageDyeColour(n2);
                    byte[] byArray3 = null;
                    if (((ym)object).r() != null) {
                        bl = true;
                        oh3 = oh2;
                    } else {
                        bl = false;
                        oh3 = oh2;
                    }
                    byArray3 = bl & oh3 != null ? ((byArray2 = oh2.r(((ym)object).r())).length == 4 && (byArray2[3] & 0xFF) != 0 ? gl.r(a22, by, by2, oh2.r(((ym)object).r()), (int[])object4) : gl.r(a22, by, by2, (byte[])object2, (int[])object4)) : gl.r(a22, by, by2, (byte[])object2, (int[])object4);
                    a22 = byArray3[0];
                    by = byArray3[1];
                    by2 = byArray3[2];
                }
                object3 = object;
            }
        }
        if (object3 == te.s) {
            object2 = a4.getAverageDyeColour(n2);
            byte[] byArray = new byte[3];
            byArray[0] = 127;
            byArray[1] = 127;
            byArray[2] = 127;
            byte[] byArray4 = gl.r(a22, by, by2, byArray, object2);
            object = byArray4;
            a22 = byArray4[0];
            by = byArray4[1];
            by2 = byArray4[2];
        } else {
            mk mk3;
            boolean bl;
            if (object3 == te.e) {
                bl = true;
                mk3 = a3;
            } else {
                bl = false;
                mk3 = a3;
            }
            if (bl & mk3.r() != null & jg.r() != qd.c) {
                if (a3.r().r() instanceof o) {
                    BufferedImage bufferedImage = bh.r(a3.r());
                    object2 = bufferedImage;
                    if (bufferedImage != null) {
                        gl gl3 = a5;
                        mk mk4 = a3;
                        byte[] byArray = gl.r(gl3.m, gl3.w, a5.v, a22, by, by2, a5.c, mk4, (BufferedImage)object2, (o)mk4.r().r());
                        object = byArray;
                        a22 = byArray[0];
                        by = byArray[1];
                        by2 = byArray[2];
                    }
                }
            } else if (oh2 != null && ((ym)object3).r() != null) {
                object2 = a4.getAverageDyeColour(n2);
                byte[] byArray = gl.r(a22, by, by2, oh2.r(((ym)object3).r()), object2);
                object = byArray;
                a22 = byArray[0];
                by = byArray[1];
                by2 = byArray[2];
            }
        }
        double d2 = 0.00390625;
        if (a3.r() != null && ("\u5de6\u80f3\u818a".equals(a3.r()) || "\u53f3\u80f3\u818a".equals(a3.r()) ? a5.w > 3 : ("\u5de6\u624b\u81c2".equals(a3.r()) || "\u53f3\u624b\u81c2".equals(a3.r()) ? a5.w <= 3 : ("\u5de6\u5927\u817f".equals(a3.r()) || "\u53f3\u5927\u817f".equals(a3.r()) ? a5.w > 5 : ("\u5de6\u5c0f\u817f".equals(a3.r()) || "\u53f3\u5c0f\u817f".equals(a3.r())) && a5.w <= 5)))) {
            return;
        }
        gl gl4 = a5;
        se.r(a5.m, a5.w, a5.v, a22, by, by2, gl4.s, gl4.c, a5.z, (double)((ym)object3).r() * d2, (double)((ym)object3).y() * d2, (double)((ym)object3).r() * d2 + d2, (double)((ym)object3).y() * d2 + d2);
    }
}

