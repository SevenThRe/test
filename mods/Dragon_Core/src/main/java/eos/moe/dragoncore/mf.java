/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.vm;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

public abstract class mf
extends InputStream {
    private vm k;
    public byte[] ALLATORIxDEMO = new byte[1];

    public mf(vm a2) {
        mf a3;
        a3.k = a2;
    }

    @Override
    public int read() throws IOException {
        mf a2;
        int a3 = a2.read(a2.ALLATORIxDEMO);
        if (a3 == -1) {
            return -1;
        }
        return a2.ALLATORIxDEMO[0];
    }

    @Override
    public int read(byte[] a2) throws IOException {
        mf a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        mf a5;
        return a5.k.read(a2, a3, a4);
    }

    @Override
    public void close() throws IOException {
        mf a2;
        a2.k.close();
    }

    public void ALLATORIxDEMO(InputStream a2) throws IOException {
        mf a3;
        a3.k.ALLATORIxDEMO(a2);
    }

    public void ALLATORIxDEMO(PushbackInputStream a2) throws IOException {
    }

    public byte[] ALLATORIxDEMO() {
        mf a2;
        return a2.k.ALLATORIxDEMO();
    }
}

