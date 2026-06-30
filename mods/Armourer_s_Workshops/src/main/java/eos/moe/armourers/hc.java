/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.an;
import eos.moe.armourers.ec;
import eos.moe.armourers.mc;
import eos.moe.armourers.ph;
import eos.moe.armourers.zb;
import java.io.IOException;
import java.io.OutputStream;

public class hc
extends mc<an> {
    private byte[] m;
    private int j;

    @Override
    public void r() throws IOException {
        hc a2;
        if (a2.j != 0) {
            hc hc2 = a2;
            super.write(hc2.m, 0, a2.j);
            hc2.j = 0;
        }
        hc hc3 = a2;
        hc3.r(((an)hc3.r()).r());
        super.r();
    }

    private /* synthetic */ void r(an a2) throws IOException {
        hc a3;
        hc hc2 = a3;
        hc2.r(a2.z());
        hc2.r(a2.y());
    }

    @Override
    public void write(int a2) throws IOException {
        hc a3;
        byte[] byArray = new byte[1];
        byArray[0] = (byte)a2;
        a3.write(byArray);
    }

    @Override
    public void write(byte[] a2) throws IOException {
        hc a3;
        a3.write(a2, 0, a2.length);
    }

    public hc(ec a2, zb a3, char[] a4) throws IOException, ph {
        hc a5;
        hc hc2 = a5;
        super(a2, a3, a4);
        hc2.m = new byte[16];
        hc2.j = 0;
    }

    @Override
    public an r(OutputStream a2, zb a3, char[] a4) throws IOException, ph {
        hc a5;
        Object object = a2 = new an(a4, a3.r());
        a5.r((an)object);
        return object;
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        hc a5;
        if (a4 < 16 - a5.j) {
            hc hc2 = a5;
            System.arraycopy(a2, a3, hc2.m, hc2.j, a4);
            a5.j += a4;
            return;
        }
        hc hc3 = a5;
        System.arraycopy(a2, a3, hc3.m, hc3.j, 16 - a5.j);
        hc hc4 = a5;
        super.write(hc3.m, 0, hc4.m.length);
        a3 = 16 - a5.j;
        a5.j = 0;
        if ((a4 -= a3) != 0 && a4 % 16 != 0) {
            int n2 = a4;
            System.arraycopy(a2, a4 + a3 - a4 % 16, a5.m, 0, a4 % 16);
            a5.j = n2 % 16;
            a4 = n2 - a5.j;
        }
        super.write(a2, a3, a4);
    }
}

