/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.g;
import eos.moe.armourers.gk;

public class se {
    private static g s = gk.j;
    private static float m;
    private static float j;

    public static void s(double a2, double a3, double a4, byte a5, byte a6, byte a7, byte a8, float a9, double a10, double a11, double a12, double a13) {
        s.r(a2 * (double)j + (double)a9, a3 * (double)j + (double)a9, a4 * (double)j + (double)a9, a12, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 1.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j + (double)a9, a4 * (double)j, a12, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 1.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j + (double)a9, a4 * (double)j, a10, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 1.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j + (double)a9, a4 * (double)j + (double)a9, a10, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 1.0f, 0.0f);
        s.r();
    }

    public static void x(double a2, double a3, double a4, byte a5, byte a6, byte a7, byte a8, float a9, double a10, double a11, double a12, double a13) {
        s.r(a2 * (double)j, a3 * (double)j, a4 * (double)j, a10, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, -1.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j + (double)a9, a4 * (double)j, a10, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, -1.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j + (double)a9, a4 * (double)j, a12, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, -1.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j, a4 * (double)j, a12, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, -1.0f);
        s.r();
    }

    public static void r(double a2, double a3, double a4, byte a5, byte a6, byte a7, byte a8, byte a9, byte a10, double a11, double a12, double a13, double a14) {
        if (a9 == 0) {
            se.s(a2, a3, a4, a5, a6, a7, a8, j * (float)a10, a11, a12, a13, a14);
        }
        if (a9 == 1) {
            se.y(a2, a3, a4, a5, a6, a7, a8, j * (float)a10, a11, a12, a13, a14);
        }
        if (a9 == 2) {
            se.x(a2, a3, a4, a5, a6, a7, a8, j * (float)a10, a11, a12, a13, a14);
        }
        if (a9 == 3) {
            se.z(a2, a3, a4, a5, a6, a7, a8, j * (float)a10, a11, a12, a13, a14);
        }
        if (a9 == 4) {
            se.r(a2, a3, a4, a5, a6, a7, a8, j * (float)a10, a11, a12, a13, a14);
        }
        if (a9 == 5) {
            se.h(a2, a3, a4, a5, a6, a7, a8, j * (float)a10, a11, a12, a13, a14);
        }
    }

    public se() {
        se a2;
    }

    static {
        j = 0.0625f;
        m = 0.75f;
    }

    public static void h(double a2, double a3, double a4, byte a5, byte a6, byte a7, byte a8, float a9, double a10, double a11, double a12, double a13) {
        s.r(a2 * (double)j, a3 * (double)j, a4 * (double)j + (double)a9, a10, a11);
        s.r(a5, a6, a7, a8);
        s.r(-1.0f, 0.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j + (double)a9, a4 * (double)j + (double)a9, a10, a13);
        s.r(a5, a6, a7, a8);
        s.r(-1.0f, 0.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j + (double)a9, a4 * (double)j, a12, a13);
        s.r(a5, a6, a7, a8);
        s.r(-1.0f, 0.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j, a4 * (double)j, a12, a11);
        s.r(a5, a6, a7, a8);
        s.r(-1.0f, 0.0f, 0.0f);
        s.r();
    }

    public static void z(double a2, double a3, double a4, byte a5, byte a6, byte a7, byte a8, float a9, double a10, double a11, double a12, double a13) {
        s.r(a2 * (double)j + (double)a9, a3 * (double)j, a4 * (double)j + (double)a9, a10, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, 1.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j + (double)a9, a4 * (double)j + (double)a9, a10, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, 1.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j + (double)a9, a4 * (double)j + (double)a9, a12, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, 1.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j, a4 * (double)j + (double)a9, a12, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, 0.0f, 1.0f);
        s.r();
    }

    public static void y(double a2, double a3, double a4, byte a5, byte a6, byte a7, byte a8, float a9, double a10, double a11, double a12, double a13) {
        s.r(a2 * (double)j, a3 * (double)j, a4 * (double)j + (double)a9, a10, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, -1.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j, a3 * (double)j, a4 * (double)j, a10, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, -1.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j, a4 * (double)j, a12, a13);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, -1.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j, a4 * (double)j + (double)a9, a12, a11);
        s.r(a5, a6, a7, a8);
        s.r(0.0f, -1.0f, 0.0f);
        s.r();
    }

    public static void r(double a2, double a3, double a4, byte a5, byte a6, byte a7, byte a8, float a9, double a10, double a11, double a12, double a13) {
        s.r(a2 * (double)j + (double)a9, a3 * (double)j, a4 * (double)j, a10, a11);
        s.r(a5, a6, a7, a8);
        s.r(1.0f, 0.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j + (double)a9, a4 * (double)j, a10, a13);
        s.r(a5, a6, a7, a8);
        s.r(1.0f, 0.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j + (double)a9, a4 * (double)j + (double)a9, a12, a13);
        s.r(a5, a6, a7, a8);
        s.r(1.0f, 0.0f, 0.0f);
        s.r();
        s.r(a2 * (double)j + (double)a9, a3 * (double)j, a4 * (double)j + (double)a9, a12, a11);
        s.r(a5, a6, a7, a8);
        s.r(1.0f, 0.0f, 0.0f);
        s.r();
    }
}

