/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.EnumFacing
 *  net.minecraft.util.math.Vec3i
 */
package eos.moe.armourers;

import eos.moe.armourers.gl;
import eos.moe.armourers.jg;
import eos.moe.armourers.kf;
import eos.moe.armourers.qf;
import eos.moe.armourers.te;
import eos.moe.armourers.ve;
import eos.moe.armourers.vk;
import eos.moe.armourers.x;
import eos.moe.armourers.xf;
import eos.moe.armourers.ym;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3i;

public class mo {
    private static /* synthetic */ void r(int a2, int a3, int a42, int a5, xf a6, int[][][] a7, qf a8, boolean a9) {
        a3 = mo.r(a2, a3, a42, a6, a7);
        if (!a9) {
            a8.r(a3 - 1).set(a5, true);
            return;
        }
        qf qf2 = a8;
        x a42 = qf2.r(a3 - 1);
        qf2.r(a3 - 1).set(a5, a42.y() != a9);
    }

    private static /* synthetic */ boolean r(int a2, int a3, int a4, byte a5, int[][][] a6, qf a7, xf a8, int a9) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < a5) {
            int n4;
            int n5 = n4 = 0;
            while (n5 < a5) {
                int n6;
                int n7 = n6 = 0;
                while (n7 < a5) {
                    int n8 = mo.r(n2 + a2, n4 + a3, n6 + a4, a8, a6) - 1;
                    if (n8 != -1 && a7.r(n8).get(a9)) {
                        return true;
                    }
                    n7 = ++n6;
                }
                n5 = ++n4;
            }
            n3 = ++n2;
        }
        return false;
    }

    public static boolean r(double a2, double a3, double a4) {
        return !(Minecraft.getMinecraft().player.getDistance(a2, a3, a4) > (double)vk.n);
    }

    public static void r(kf a2, int[][] a3, int[] a4, int[][][] a5, int a6) {
        ArrayList[] arrayListArray;
        block24: {
            int n2;
            a6 = jg.r() ? 1 : 0;
            int n3 = vk.c;
            arrayListArray = new ArrayList[jg.r() * (n3 + 1)];
            int n4 = n2 = 0;
            while (n4 < arrayListArray.length) {
                arrayListArray[n2++] = new ArrayList();
                n4 = n2;
            }
            float f2 = 0.0625f;
            kf kf2 = a2;
            qf qf2 = kf2.r();
            xf xf2 = kf2.y();
            int n5 = qf2.r();
            int n6 = 1;
            if (kf2.r().size() > 1) {
                // empty if block
            }
            if (n5 != 0 && (!Arrays.equals(qf2.r(0), qf2.r(n5 - 1)) || !Arrays.equals(qf2.r(0), qf2.r(n5 - 1)) || !Arrays.equals(qf2.r(0), qf2.r(n5 - 1)))) break block24;
            int n7 = n5 = 0;
            while (n7 < xf2.y()) {
                int n8 = n6 = 0;
                while (n8 < xf2.h()) {
                    int n9;
                    int n10 = n9 = 0;
                    while (n10 < xf2.z()) {
                        int n11;
                        int n12;
                        Object object;
                        byte[] byArray;
                        byte by;
                        int n13 = mo.r(n5, n6, n9, xf2, a5) - 1;
                        if (n13 != -1) {
                            int n14;
                            qf qf3 = qf2;
                            byte[] byArray2 = qf3.h(n13);
                            byte[] byArray3 = qf3.x(n13);
                            x x2 = a2.r().r(n13);
                            by = -1;
                            if (x2.y()) {
                                by = 127;
                            }
                            qf qf4 = qf2;
                            byArray = qf4.r(n13);
                            object = qf4.z(n13);
                            byte[] byArray4 = qf4.y(n13);
                            int n15 = n12 = 0;
                            while (n15 < 6) {
                                ym ym2 = te.r(byArray3[n12]);
                                if (ym2.r()) {
                                    int n16 = ym2.r();
                                    a4[n16] = a4[n16] + 1;
                                    int[] nArray = a3[0];
                                    int n17 = n16;
                                    nArray[n17] = nArray[n17] + (byArray[n12] & 0xFF);
                                    int[] nArray2 = a3[1];
                                    int n18 = n16;
                                    nArray2[n18] = nArray2[n18] + (object[n12] & 0xFF);
                                    int[] nArray3 = a3[2];
                                    int n19 = n16;
                                    nArray3[n19] = nArray3[n19] + (byArray4[n12] & 0xFF);
                                }
                                n15 = ++n12;
                            }
                            n12 = 0;
                            if (a6 != 0) {
                                if (x2.r() && !x2.y()) {
                                    n12 = 1;
                                }
                                if (x2.y() && !x2.r()) {
                                    n12 = 2;
                                }
                                if (x2.r() && x2.y()) {
                                    n12 = 3;
                                }
                            } else if (x2.r()) {
                                n12 = 1;
                            }
                            int n20 = n14 = 0;
                            while (n20 < 6) {
                                if (qf2.r(n13).get(n14)) {
                                    gl gl2 = new gl(byArray2[0], byArray2[1], byArray2[2], byArray[n14], object[n14], byArray4[n14], by, byArray3[n14], (byte)n14, 1);
                                    arrayListArray[n12].add(gl2);
                                }
                                n20 = ++n14;
                            }
                        }
                        int n21 = n11 = 1;
                        while (n21 < n3 + 1) {
                            block25: {
                                int n22;
                                int n23;
                                boolean bl;
                                byte by2 = (byte)Math.pow(2.0, n11);
                                if (n5 % by2 == 0) {
                                    bl = true;
                                    n23 = n6;
                                } else {
                                    bl = false;
                                    n23 = n6;
                                }
                                if (!(bl & n23 % by2 == 0 & n9 % by2 == 0)) break block25;
                                int n24 = n22 = 0;
                                while (n24 < 6) {
                                    block26: {
                                        int n25;
                                        int n26;
                                        block29: {
                                            block28: {
                                                block27: {
                                                    boolean bl2 = mo.r(n5, n6, n9, by2, a5, qf2, xf2, n22);
                                                    by = (byte)(bl2 ? 1 : 0);
                                                    if (!bl2) break block26;
                                                    byArray = mo.r(n5, n6, n9, by2, a5, qf2, xf2, n22);
                                                    object = ve.m.r(byArray[5]);
                                                    n26 = 0;
                                                    if (a6 == 0) break block27;
                                                    if (object.r() && !object.y()) {
                                                        n26 = 1;
                                                    }
                                                    if (object.y() && !object.r()) {
                                                        n26 = 2;
                                                    }
                                                    if (!object.r() || !object.y()) break block28;
                                                    n26 = 3;
                                                    n25 = n11;
                                                    break block29;
                                                }
                                                if (object.r()) {
                                                    n26 = 1;
                                                }
                                            }
                                            n25 = n11;
                                        }
                                        n12 = n25 * jg.r() + n26;
                                        gl gl3 = new gl((byte)(n5 + xf2.x()), (byte)(n6 + xf2.s()), (byte)(n9 + xf2.r()), byArray[0], byArray[1], byArray[2], byArray[3], byArray[4], (byte)n22, by2);
                                        arrayListArray[n12].add(gl3);
                                    }
                                    n24 = ++n22;
                                }
                            }
                            n21 = ++n11;
                        }
                        n10 = ++n9;
                    }
                    n8 = ++n6;
                }
                n7 = ++n5;
            }
        }
        a2.r().setVertexLists(arrayListArray);
    }

    private static /* synthetic */ int r(int a2, int a3, int a4, xf a5, int[][][] a6) {
        int n2;
        boolean bl;
        if (a2 >= 0) {
            bl = true;
            n2 = a2;
        } else {
            bl = false;
            n2 = a2;
        }
        if (bl & n2 < a5.y()) {
            int n3;
            boolean bl2;
            if (a3 >= 0) {
                bl2 = true;
                n3 = a3;
            } else {
                bl2 = false;
                n3 = a3;
            }
            if (bl2 & n3 < a5.h()) {
                int n4;
                boolean bl3;
                if (a4 >= 0) {
                    bl3 = true;
                    n4 = a4;
                } else {
                    bl3 = false;
                    n4 = a4;
                }
                if (bl3 & n4 < a5.z()) {
                    return a6[a2][a3][a4];
                }
            }
        }
        return 0;
    }

    public static int[][][] r(kf a2, int a32) {
        int n2;
        Object object;
        kf kf2 = a2;
        qf a32 = kf2.r();
        a32.r();
        kf2.r().m = new int[ve.m.r()];
        xf xf2 = kf2.y();
        int[][][] nArray = new int[xf2.y()][xf2.h()][xf2.z()];
        int n3 = 0;
        int n4 = n3 = 0;
        while (n4 < a32.r()) {
            qf qf2 = a32;
            byte by = qf2.r(n3);
            object = qf2.h(n3);
            byte by2 = by;
            a2.r().m[by2] = a2.r().m[by2] + 1;
            int n5 = object[0] - xf2.x();
            int n6 = object[1] - xf2.s();
            n2 = object[2] - xf2.r();
            nArray[n5][n6][n2] = ++n3;
            n4 = n3;
        }
        ArrayDeque<byte[]> arrayDeque = new ArrayDeque<byte[]>();
        HashSet<byte[]> hashSet = new HashSet<byte[]>();
        object = new Vec3i(-1, -1, -1);
        arrayDeque.add((byte[])object);
        hashSet.add((byte[])object);
        while (arrayDeque.size() > 0) {
            Vec3i vec3i = (Vec3i)arrayDeque.poll();
            ArrayList<Vec3i> arrayList = mo.r(a32, vec3i, xf2, nArray);
            int n7 = n2 = 0;
            while (n7 < arrayList.size()) {
                object = arrayList.get(n2);
                if (!hashSet.contains(object)) {
                    hashSet.add((byte[])object);
                    if (mo.r((Vec3i)object, xf2)) {
                        arrayDeque.add((byte[])object);
                    }
                }
                n7 = ++n2;
            }
        }
        return nArray;
    }

    private static /* synthetic */ ArrayList<Vec3i> r(qf a2, Vec3i a3, xf a4, int[][][] a5) {
        int n2;
        ArrayList<Vec3i> arrayList = new ArrayList<Vec3i>();
        EnumFacing[] enumFacingArray = new EnumFacing[6];
        enumFacingArray[0] = EnumFacing.DOWN;
        enumFacingArray[1] = EnumFacing.UP;
        enumFacingArray[2] = EnumFacing.SOUTH;
        enumFacingArray[3] = EnumFacing.NORTH;
        enumFacingArray[4] = EnumFacing.WEST;
        enumFacingArray[5] = EnumFacing.EAST;
        EnumFacing[] enumFacingArray2 = enumFacingArray;
        int n3 = mo.r(a3.getX(), a3.getY(), a3.getZ(), a4, a5);
        boolean bl = false;
        if (n3 > 0) {
            x x2 = a2.r(n3 - 1);
            bl = x2.y();
        }
        int n4 = n2 = 0;
        while (n4 < enumFacingArray2.length) {
            int n5;
            int n6;
            EnumFacing enumFacing = enumFacingArray2[n2];
            Vec3i vec3i = a3;
            int n7 = vec3i.getX() + enumFacing.getXOffset();
            int n8 = mo.r(n7, n6 = vec3i.getY() + enumFacing.getYOffset(), n3 = vec3i.getZ() + enumFacing.getZOffset(), a4, a5);
            if (n8 < 1) {
                n5 = n8;
                arrayList.add(new Vec3i(n7, n6, n3));
            } else {
                if (a2.r(n8 - 1).y()) {
                    arrayList.add(new Vec3i(n7, n6, n3));
                }
                n5 = n8;
            }
            if (n5 > 0) {
                mo.r(n7, n6, n3, n2, a4, a5, a2, bl);
            }
            n4 = ++n2;
        }
        return arrayList;
    }

    private static /* synthetic */ byte[] r(int a2, int a3, int a4, byte a5, int[][][] a6, qf a7, xf a8, int a9) {
        int n2;
        int n3;
        int n4;
        int n5;
        int n6 = 0;
        int n7 = 0;
        int n8 = 0;
        int n9 = 0;
        int n10 = 0;
        int[] nArray = new int[256];
        int[] nArray2 = new int[256];
        int n11 = n5 = 0;
        while (n11 < a5) {
            int n12 = n4 = 0;
            while (n12 < a5) {
                int n13 = n3 = 0;
                while (n13 < a5) {
                    n2 = mo.r(n5 + a2, n4 + a3, n3 + a4, a8, a6) - 1;
                    if (n2 != -1 && a7.r(n2).get(a9)) {
                        int[] nArray3;
                        ++n6;
                        n7 += a7.r(n2)[a9] & 0xFF;
                        n8 += a7.z(n2)[a9] & 0xFF;
                        n9 += a7.y(n2)[a9] & 0xFF;
                        if (a7.r(n2).y()) {
                            nArray3 = nArray;
                            n10 += 127;
                        } else {
                            n10 += 255;
                            nArray3 = nArray;
                        }
                        int n14 = a7.x(n2)[a9] & 0xFF;
                        nArray3[n14] = nArray3[n14] + 1;
                        int n15 = a7.r(n2) & 0xFF;
                        nArray2[n15] = nArray2[n15] + 1;
                    }
                    n13 = ++n3;
                }
                n12 = ++n4;
            }
            n11 = ++n5;
        }
        byte[] byArray = new byte[6];
        if (n6 != 0) {
            byArray[0] = (byte)(n7 / n6);
            byArray[1] = (byte)(n8 / n6);
            byArray[2] = (byte)(n9 / n6);
            byArray[3] = (byte)(n10 / n6);
            n4 = 0;
            n3 = 0;
            int n16 = n2 = 0;
            while (n16 < nArray.length) {
                if (nArray[n2] > n3) {
                    n3 = nArray[n2];
                    n4 = n2;
                }
                n16 = ++n2;
            }
            byArray[4] = (byte)n4;
            n2 = 0;
            a3 = 0;
            int n17 = a4 = 0;
            while (n17 < nArray2.length) {
                if (nArray2[a4] > a3) {
                    a3 = nArray2[a4];
                    n2 = a4;
                }
                n17 = ++a4;
            }
            byArray[5] = (byte)n2;
        }
        return byArray;
    }

    public mo() {
        mo a2;
    }

    public static boolean r(Entity a2) {
        return mo.r(a2.posX, a2.posY, a2.posZ);
    }

    private static /* synthetic */ boolean r(Vec3i a2, xf a3) {
        Vec3i vec3i;
        boolean bl;
        if (a2.getX() > -2) {
            bl = true;
            vec3i = a2;
        } else {
            bl = false;
            vec3i = a2;
        }
        if (bl & vec3i.getX() < a3.y() + 1) {
            Vec3i vec3i2;
            boolean bl2;
            if (a2.getY() > -2) {
                bl2 = true;
                vec3i2 = a2;
            } else {
                bl2 = false;
                vec3i2 = a2;
            }
            if (bl2 & vec3i2.getY() < a3.h() + 1) {
                Vec3i vec3i3;
                boolean bl3;
                if (a2.getZ() > -2) {
                    bl3 = true;
                    vec3i3 = a2;
                } else {
                    bl3 = false;
                    vec3i3 = a2;
                }
                return bl3 & vec3i3.getZ() < a3.z() + 1;
            }
        }
        return false;
    }
}

