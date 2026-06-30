/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.ok;
import eos.moe.armourers.ve;
import eos.moe.armourers.wg;
import eos.moe.armourers.x;
import eos.moe.armourers.y;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.BitSet;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class qf {
    private byte[] t;
    private byte[][] w;
    private byte[] r;
    private byte[] l;
    private byte[][] c;
    private byte[][] v;
    private byte[] s;
    private byte[][] m;
    @SideOnly(value=Side.CLIENT)
    private BitSet[] j;

    @SideOnly(value=Side.CLIENT)
    public void r(int a2, BitSet a3) {
        a.j[a2] = a3;
    }

    public String toString() {
        qf a2;
        return new StringBuilder().insert(0, "SkinCubeData [cubeId=").append(Arrays.toString(a2.r)).append(", cubeLocX=").append(Arrays.toString(a2.l)).append(", cubeLocY=").append(Arrays.toString(a2.s)).append(", cubeLocZ=").append(Arrays.toString(a2.t)).append(", cubeColourR=").append(Arrays.deepToString((Object[])a2.m)).append(", cubeColourG=").append(Arrays.deepToString((Object[])a2.w)).append(", cubeColourB=").append(Arrays.deepToString((Object[])a2.v)).append(", cubePaintType=").append(Arrays.deepToString((Object[])a2.c)).append("]").toString();
    }

    public byte[] x(int a2) {
        qf a3;
        byte[] byArray = new byte[6];
        byArray[0] = a3.c[a2][0];
        byArray[1] = a3.c[a2][1];
        byArray[2] = a3.c[a2][2];
        byArray[3] = a3.c[a2][3];
        byArray[4] = a3.c[a2][4];
        byArray[5] = a3.c[a2][5];
        return byArray;
    }

    public void r(int a2, byte a3, byte a4, byte a5) {
        qf a6;
        qf qf2 = a6;
        qf2.l[a2] = a3;
        qf2.s[a2] = a4;
        qf2.t[a2] = a5;
    }

    public byte[] h(int a2) {
        qf a3;
        byte[] byArray = new byte[3];
        byArray[0] = a3.l[a2];
        byArray[1] = a3.s[a2];
        byArray[2] = a3.t[a2];
        return byArray;
    }

    public void r(DataInputStream a2, int a3, y a4) throws IOException, ok {
        int n2;
        qf a5;
        a5.r(a2.readInt());
        int n3 = n2 = 0;
        while (n3 < a5.r()) {
            int n4;
            if (a3 < 10) {
                wg.r(a5, n2, a2, a3, a4);
                int n5 = n4 = 0;
                while (n5 < 6) {
                    a5.c[n2][n4++] = -1;
                    n5 = n4;
                }
            } else {
                qf qf2 = a5;
                qf2.r[n2] = a2.readByte();
                qf2.l[n2] = a2.readByte();
                qf2.s[n2] = a2.readByte();
                qf2.t[n2] = a2.readByte();
                int n6 = n4 = 0;
                while (n6 < 6) {
                    qf qf3 = a5;
                    qf3.m[n2][n4] = a2.readByte();
                    qf3.w[n2][n4] = a2.readByte();
                    qf3.v[n2][n4] = a2.readByte();
                    qf3.c[n2][n4++] = a2.readByte();
                    n6 = n4;
                }
            }
            if (a3 < 11) {
                int n7 = n4 = 0;
                while (n7 < 6) {
                    a5.c[n2][n4++] = -1;
                    n7 = n4;
                }
            }
            n3 = ++n2;
        }
    }

    @SideOnly(value=Side.CLIENT)
    public BitSet r(int a2) {
        qf a3;
        return a3.j[a2];
    }

    public void r(int a2, int a3, byte a4, byte a5, byte a6) {
        qf a7;
        qf qf2 = a7;
        qf2.m[a2][a3] = a4;
        qf2.w[a2][a3] = a5;
        qf2.v[a2][a3] = a6;
    }

    public void r(int a2) {
        qf a3;
        qf qf2 = a3;
        int n2 = a2;
        qf qf3 = a3;
        qf3.r = new byte[a2];
        qf3.l = new byte[a2];
        a3.s = new byte[n2];
        qf2.t = new byte[n2];
        qf2.m = new byte[a2][6];
        a3.w = new byte[a2][6];
        a3.v = new byte[a2][6];
        a3.c = new byte[a2][6];
    }

    public byte[] z(int a2) {
        qf a3;
        byte[] byArray = new byte[6];
        byArray[0] = a3.w[a2][0];
        byArray[1] = a3.w[a2][1];
        byArray[2] = a3.w[a2][2];
        byArray[3] = a3.w[a2][3];
        byArray[4] = a3.w[a2][4];
        byArray[5] = a3.w[a2][5];
        return byArray;
    }

    public byte r(int a2) {
        qf a3;
        return a3.r[a2];
    }

    public x r(int a2) {
        qf a3;
        return ve.m.r(a3.r[a2]);
    }

    public byte r(int a2, int a3) {
        qf a4;
        return a4.c[a2][a3];
    }

    public void r(int a2, int a3, byte a4) {
        a.c[a2][a3] = a4;
    }

    public void r(int a2, byte a3) {
        a.r[a2] = a3;
    }

    public qf() {
        qf a2;
    }

    public byte[] y(int a2) {
        qf a3;
        byte[] byArray = new byte[6];
        byArray[0] = a3.v[a2][0];
        byArray[1] = a3.v[a2][1];
        byArray[2] = a3.v[a2][2];
        byArray[3] = a3.v[a2][3];
        byArray[4] = a3.v[a2][4];
        byArray[5] = a3.v[a2][5];
        return byArray;
    }

    public byte[] r(int a2) {
        qf a3;
        byte[] byArray = new byte[6];
        byArray[0] = a3.m[a2][0];
        byArray[1] = a3.m[a2][1];
        byArray[2] = a3.m[a2][2];
        byArray[3] = a3.m[a2][3];
        byArray[4] = a3.m[a2][4];
        byArray[5] = a3.m[a2][5];
        return byArray;
    }

    public int r() {
        qf a2;
        return a2.r.length;
    }

    public void r() {
        int n2;
        qf a2;
        a2.j = new BitSet[a2.r()];
        int n3 = n2 = 0;
        while (n3 < a2.r()) {
            a2.j[n2++] = new BitSet(6);
            n3 = n2;
        }
    }
}

