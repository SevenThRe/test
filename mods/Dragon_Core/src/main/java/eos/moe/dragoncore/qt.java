/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class qt {
    public static Random ALLATORIxDEMO = new Random();

    public qt() {
        qt a2;
    }

    public static int ALLATORIxDEMO(int a2, int a3) {
        return qt.ALLATORIxDEMO(ALLATORIxDEMO, a2, a3);
    }

    public static float ALLATORIxDEMO(float a2, float a3) {
        return qt.ALLATORIxDEMO(ALLATORIxDEMO, a2, a3);
    }

    public static int[] ALLATORIxDEMO(int a2, int a3, int a4) {
        int a5;
        int a6 = a3 - a2 + 1;
        if (a4 < 1) {
            return new int[0];
        }
        if (a4 > a6) {
            a4 = a6;
        }
        int[] a7 = new int[a4];
        ArrayList<Integer> a8 = new ArrayList<Integer>(a6);
        for (a5 = 0; a5 < a6; ++a5) {
            a8.add(a2 + a5);
        }
        for (a5 = 0; a5 < a4; ++a5) {
            a7[a5] = qt.ALLATORIxDEMO(a8);
            a8.remove(new Integer(a7[a5]));
        }
        return a7;
    }

    public static int ALLATORIxDEMO(Random a2, int a3, int a4) {
        return a2.nextInt(Math.max(1, a4 - a3 + 1)) + a3;
    }

    public static float ALLATORIxDEMO(Random a2, float a3, float a4) {
        return a2.nextFloat() * (a4 - a3) + a3;
    }

    @Deprecated
    public static <T> Integer ALLATORIxDEMO(ArrayList<Integer> a2) {
        return qt.c(a2);
    }

    public static <T> T c(Collection<T> a2) {
        if (a2.isEmpty()) {
            return null;
        }
        int a3 = ALLATORIxDEMO.nextInt(a2.size());
        Iterator<T> a4 = a2.iterator();
        for (int a5 = 0; a5 < a3; ++a5) {
            a4.next();
        }
        return a4.next();
    }

    public static <T> T ALLATORIxDEMO(T[] a2) {
        if (a2 == null || a2.length == 0) {
            return null;
        }
        return a2[qt.ALLATORIxDEMO(0, a2.length - 1)];
    }

    public static <T> T ALLATORIxDEMO(Collection<T> a2) {
        T a3 = qt.c(a2);
        if (a3 != null) {
            a2.remove(a3);
            return a3;
        }
        return null;
    }

    public static boolean ALLATORIxDEMO(double a2) {
        return ALLATORIxDEMO.nextDouble() < a2;
    }

    public static boolean ALLATORIxDEMO(float a2) {
        return ALLATORIxDEMO.nextFloat() < a2;
    }

    public static boolean ALLATORIxDEMO(int a2) {
        return qt.ALLATORIxDEMO((float)a2 / 100.0f);
    }

    public static boolean ALLATORIxDEMO() {
        return qt.ALLATORIxDEMO(0.5f);
    }

    public static boolean ALLATORIxDEMO(Random a2, int a3) {
        return a2.nextFloat() < (float)a3 / 100.0f;
    }

    public static int ALLATORIxDEMO(int a2) {
        return a2 > 0 ? Math.max(1, ALLATORIxDEMO.nextInt(a2 + 2)) : 1;
    }

    public static void ALLATORIxDEMO(Random a2, World a3, int a4, int a5) {
        long a6 = a3 == null ? 0L : a3.getSeed();
        a6 *= a6 * 6364136223846793005L + 1442695040888963407L;
        a6 += (long)a4;
        a6 *= a6 * 6364136223846793005L + 1442695040888963407L;
        a6 += (long)a5;
        a6 *= a6 * 6364136223846793005L + 1442695040888963407L;
        a6 += (long)a4;
        a6 *= a6 * 6364136223846793005L + 1442695040888963407L;
        a2.setSeed(a6 += (long)a5);
    }

    public static Random ALLATORIxDEMO(World a2, int a3, int a4) {
        qt.ALLATORIxDEMO(ALLATORIxDEMO, a2, a3, a4);
        return ALLATORIxDEMO;
    }

    public static Color ALLATORIxDEMO() {
        return Color.getHSBColor(ALLATORIxDEMO.nextFloat() * 360.0f, 1.0f, 1.0f);
    }

    public static Vec3d ALLATORIxDEMO(double a2) {
        double a3 = ALLATORIxDEMO.nextDouble() * 2.0 * Math.PI;
        double a4 = (ALLATORIxDEMO.nextDouble() - 0.5) * Math.PI;
        double a5 = ALLATORIxDEMO.nextDouble() * a2;
        double a6 = a5 * Math.cos(a3) * Math.cos(a4);
        double a7 = a5 * Math.sin(a4);
        double a8 = a5 * Math.sin(a3) * Math.cos(a4);
        return new Vec3d(a6, a7, a8);
    }
}

