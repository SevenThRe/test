/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.jh;
import eos.moe.armourers.l;
import eos.moe.armourers.ph;
import eos.moe.armourers.uh;

public class yn
implements l {
    private char[] v;
    private byte[] s;
    private byte[] m;
    private jh j;

    public yn(char[] a2, byte[] a3, byte[] a4) throws ph {
        yn a5;
        yn yn2 = a5;
        yn2.s = new byte[4];
        yn2.v = a2;
        a5.m = a3;
        yn yn3 = a5;
        a5.j = new jh();
        a5.r(a4);
    }

    private /* synthetic */ void r(byte[] a2) throws ph {
        int n2;
        yn a3;
        yn yn2 = a3;
        yn2.s[3] = (byte)(a3.m[3] & 0xFF);
        yn yn3 = a3;
        yn2.s[2] = (byte)(yn3.m[3] >> 8 & 0xFF);
        yn3.s[1] = (byte)(a3.m[3] >> 16 & 0xFF);
        yn2.s[0] = (byte)(a3.m[3] >> 24 & 0xFF);
        if (yn2.s[2] > 0 || a3.s[1] > 0 || a3.s[0] > 0) {
            throw new IllegalStateException("Invalid CRC in File Header");
        }
        if (a3.v == null || a3.v.length <= 0) {
            throw new ph("Wrong password!", uh.m);
        }
        yn yn4 = a3;
        yn4.j.r(yn4.v);
        byte by = a2[0];
        int n3 = n2 = 0;
        while (n3 < 12) {
            a3.j.r((byte)(by ^ a3.j.r()));
            if (n2 + 1 != 12) {
                by = a2[n2 + 1];
            }
            n3 = ++n2;
        }
    }

    @Override
    public int r(byte[] a2, int a3, int a4) throws ph {
        int n2;
        if (a3 < 0 || a4 < 0) {
            throw new ph("one of the input parameters were null in standard decrypt data");
        }
        int n3 = n2 = a3;
        while (n3 < a3 + a4) {
            yn a5;
            int n4 = a2[n2] & 0xFF;
            n4 = (n4 ^ a5.j.r()) & 0xFF;
            a5.j.r((byte)n4);
            a2[n2++] = (byte)n4;
            n3 = n2;
        }
        return a4;
    }
}

