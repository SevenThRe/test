/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.nbt.NBTTagCompound
 */
package eos.moe.armourers;

import eos.moe.armourers.e;
import java.awt.Color;
import java.util.Arrays;
import net.minecraft.nbt.NBTTagCompound;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xm
implements e {
    private byte[] w;
    private byte[] r;
    private byte[] l;
    private static String c = "r";
    private byte[] v;
    private static String s = "g";
    private static String m;
    private static String j;

    @Override
    public void r(int a2, int a3) {
        xm a4;
        xm xm2 = a4;
        xm2.w[a3] = (byte)(a2 >> 16 & 0xFF);
        xm2.l[a3] = (byte)(a2 >> 8 & 0xFF);
        xm2.v[a3] = (byte)(a2 & 0xFF);
    }

    @Override
    public void r(byte a2, int a3) {
        a.w[a3] = a2;
    }

    @Override
    public void h(byte a2, int a3) {
        a.v[a3] = a2;
    }

    public int r(int a2) {
        xm a3;
        return new Color(a3.w[a2] & 0xFF, a3.l[a2] & 0xFF, a3.v[a2] & 0xFF).getRGB();
    }

    @Override
    public byte[] r() {
        xm a2;
        return a2.v;
    }

    public xm() {
        xm a2;
        xm xm2 = a2;
        xm2.r();
    }

    static {
        j = "b";
        m = "t";
    }

    @Override
    public byte z(int a2) {
        xm a3;
        return a3.l[a2];
    }

    private /* synthetic */ void r() {
        int n2;
        xm a2;
        xm xm2 = a2;
        xm xm3 = a2;
        xm3.r = new byte[6];
        xm3.w = new byte[6];
        xm2.l = new byte[6];
        xm2.v = new byte[6];
        int n3 = n2 = 0;
        while (n3 < 6) {
            xm xm4 = a2;
            xm4.r[n2] = -1;
            xm4.w[n2] = -1;
            xm4.l[n2] = -1;
            xm4.v[n2++] = -1;
            n3 = n2;
        }
    }

    @Override
    public byte[] z() {
        xm a2;
        return a2.r;
    }

    @Override
    public byte y(int a2) {
        xm a3;
        return a3.v[a2];
    }

    @Override
    @Deprecated
    public void r(int a2) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < 6) {
            xm a3;
            xm xm2 = a3;
            xm2.w[n2] = (byte)(a2 >> 16 & 0xFF);
            xm2.l[n2] = (byte)(a2 >> 8 & 0xFF);
            xm2.v[n2++] = (byte)(a2 & 0xFF);
            n3 = n2;
        }
    }

    @Override
    public void z(byte a2, int a3) {
        a.r[a3] = a2;
    }

    @Override
    public void y(byte a2, int a3) {
        a.l[a3] = a2;
    }

    @Override
    public byte h(int a2) {
        xm a3;
        return a3.w[a2];
    }

    @Override
    public void r(NBTTagCompound a2) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < 6) {
            xm a3;
            NBTTagCompound nBTTagCompound = a2;
            nBTTagCompound.func_74774_a(c + n2, a3.w[n2]);
            a2.func_74774_a(new StringBuilder().insert(0, s).append(n2).toString(), a3.l[n2]);
            nBTTagCompound.func_74774_a(new StringBuilder().insert(0, j).append(n2).toString(), a3.v[n2]);
            nBTTagCompound.func_74774_a(new StringBuilder().insert(0, m).append(n2).toString(), a3.r[n2++]);
            n3 = n2;
        }
    }

    @Override
    public byte[] y() {
        xm a2;
        return a2.w;
    }

    @Override
    public byte[] h() {
        xm a2;
        return a2.l;
    }

    @Override
    public byte r(int a2) {
        xm a3;
        return a3.r[a2];
    }

    public xm(int a2) {
        int n2;
        xm a3;
        xm xm2 = a3;
        xm xm3 = a3;
        xm3.r = new byte[6];
        xm3.w = new byte[6];
        xm2.l = new byte[6];
        xm2.v = new byte[6];
        int n3 = n2 = 0;
        while (n3 < 6) {
            xm xm4 = a3;
            xm4.r[n2] = -1;
            xm4.w[n2] = (byte)(a2 >> 16 & 0xFF);
            xm4.l[n2] = (byte)(a2 >> 8 & 0xFF);
            xm4.v[n2++] = (byte)(a2 & 0xFF);
            n3 = n2;
        }
    }

    @Override
    public void y(NBTTagCompound a2) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < 6) {
            xm a3;
            xm xm2 = a3;
            int n4 = n2;
            xm2.w[n4] = a2.func_74771_c(c + n4);
            int n5 = n2;
            xm2.l[n5] = a2.func_74771_c(new StringBuilder().insert(0, s).append(n5).toString());
            NBTTagCompound nBTTagCompound = a2;
            xm2.v[n2] = nBTTagCompound.func_74771_c(new StringBuilder().insert(0, j).append(n2).toString());
            if (nBTTagCompound.func_74764_b(new StringBuilder().insert(0, m).append(n2).toString())) {
                int n6 = n2;
                a3.r[n6] = a2.func_74771_c(new StringBuilder().insert(0, m).append(n6).toString());
            } else {
                a3.r[n2] = -1;
            }
            n3 = ++n2;
        }
    }

    public String toString() {
        xm a2;
        return new StringBuilder().insert(0, "CubeColour [r=").append(Arrays.toString(a2.w)).append(", g=").append(Arrays.toString(a2.l)).append(", b=").append(Arrays.toString(a2.v)).append(", t=").append(Arrays.toString(a2.r)).append("]").toString();
    }

    public xm(e a2) {
        xm a3;
        a3.w = (byte[])a2.y().clone();
        a3.l = (byte[])a2.h().clone();
        a3.v = (byte[])a2.r().clone();
        a3.r = (byte[])a2.z().clone();
    }
}

