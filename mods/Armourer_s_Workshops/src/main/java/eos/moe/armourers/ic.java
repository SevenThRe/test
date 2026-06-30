/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.kc;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public abstract class ic
extends InputStream {
    public byte[] m;
    private kc j;

    public void r(PushbackInputStream a2) throws IOException {
    }

    @Override
    public int read() throws IOException {
        ic a2;
        ic ic2 = a2;
        if (ic2.read(ic2.m) == -1) {
            return -1;
        }
        return a2.m[0];
    }

    @Override
    public int read(byte[] a2) throws IOException {
        ic a3;
        return a3.read(a2, 0, a2.length);
    }

    public ic(kc a2) {
        ic a3;
        ic ic2 = a3;
        ic2.m = new byte[1];
        ic2.j = a2;
    }

    public void r(InputStream a2) throws IOException {
        ic a3;
        a3.j.r(a2);
    }

    public byte[] r() {
        ic a2;
        return a2.j.r();
    }

    @Override
    public void close() throws IOException {
        ic a2;
        a2.j.close();
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        ic a5;
        return a5.j.read(a2, a3, a4);
    }
}

