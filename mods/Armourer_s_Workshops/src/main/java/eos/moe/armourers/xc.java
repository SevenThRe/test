/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.ec;
import eos.moe.armourers.mc;
import eos.moe.armourers.ph;
import eos.moe.armourers.sl;
import eos.moe.armourers.zb;
import java.io.IOException;
import java.io.OutputStream;

public class xc
extends mc<sl> {
    public xc(ec a2, zb a3, char[] a4) throws IOException, ph {
        super(a2, a3, a4);
        xc a5;
    }

    private /* synthetic */ long r(zb a2) {
        if (a2.y()) {
            return (c.y(a2.z()) & 0xFFFFL) << 16;
        }
        return a2.y();
    }

    @Override
    public void write(int a2) throws IOException {
        xc a3;
        byte[] byArray = new byte[1];
        byArray[0] = (byte)a2;
        a3.write(byArray);
    }

    @Override
    public sl r(OutputStream a2, zb a3, char[] a4) throws IOException, ph {
        xc a5;
        xc xc2 = a5;
        long l2 = xc2.r(a3);
        a2 = new sl(a4, l2);
        xc2.r(((sl)a2).r());
        return a2;
    }

    @Override
    public void write(byte[] a2) throws IOException {
        xc a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        xc a5;
        super.write(a2, a3, a4);
    }
}

