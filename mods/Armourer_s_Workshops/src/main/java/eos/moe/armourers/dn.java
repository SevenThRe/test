/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.armourers;

import eos.moe.armourers.mn;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.vk;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class dn {
    private int g;
    private boolean z;
    private boolean t;
    private boolean w;
    public static mn r = new mn();
    private ResourceLocation l;
    public static String c;
    private n v;
    private boolean s;
    private float m;
    private oh j;

    public dn(float a2, n a3, oh a4, int a5, boolean a6, boolean a7, boolean a8, ResourceLocation a9, boolean a10) {
        dn dn2;
        oh oh2;
        dn a11;
        a11.m = a2;
        if (a3 == null) {
            oh2 = a4;
            a11.v = r;
        } else {
            a11.v = a3;
            oh2 = a4;
        }
        if (oh2 == null) {
            dn2 = a11;
            a11.j = oh.l;
        } else {
            dn2 = a11;
            a11.j = a4;
        }
        dn2.g = a5;
        dn dn3 = a11;
        dn dn4 = a11;
        a11.t = a6;
        dn4.s = a7;
        dn4.z = a8;
        dn3.l = a9;
        dn3.w = a10;
    }

    public float r() {
        dn a2;
        return a2.m;
    }

    public oh r() {
        dn a2;
        return a2.j;
    }

    public int r() {
        dn a2;
        return a2.g;
    }

    public boolean h() {
        dn a2;
        return a2.s;
    }

    public static int r(double a2) {
        return MathHelper.clamp((int)MathHelper.floor((double)(a2 / vk.na)), (int)0, (int)vk.c);
    }

    public ResourceLocation r() {
        dn a2;
        return a2.l;
    }

    public n r() {
        dn a2;
        return a2.v;
    }

    public dn(float a2, n a3, oh a4, double a5, boolean a6, boolean a7, boolean a8, ResourceLocation a9) {
        a10(a2, a3, a4, dn.r(a5), a6, a7, a8, a9);
        dn a10;
    }

    public boolean z() {
        dn a2;
        return a2.w;
    }

    public boolean y() {
        dn a2;
        return a2.t;
    }

    public dn(float a2, n a3, oh a4, int a5, boolean a6, boolean a7, boolean a8, ResourceLocation a9) {
        dn dn2;
        oh oh2;
        dn a10;
        a10.m = a2;
        if (a3 == null) {
            oh2 = a4;
            a10.v = r;
        } else {
            a10.v = a3;
            oh2 = a4;
        }
        if (oh2 == null) {
            dn2 = a10;
            a10.j = oh.l;
        } else {
            dn2 = a10;
            a10.j = a4;
        }
        dn2.g = a5;
        dn dn3 = a10;
        dn dn4 = a10;
        a10.t = a6;
        dn4.s = a7;
        dn4.z = a8;
        dn3.l = a9;
        dn3.w = false;
    }

    public boolean r() {
        dn a2;
        return a2.z;
    }
}

