/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.mc;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class jc
extends OutputStream {
    private mc j;

    public jc(mc a2) {
        jc a3;
        a3.j = a2;
    }

    @Override
    public void close() throws IOException {
        jc a2;
        a2.j.close();
    }

    public long r() {
        jc a2;
        return a2.j.r();
    }

    @Override
    public void write(int a2) throws IOException {
        jc a3;
        byte[] byArray = new byte[1];
        byArray[0] = (byte)a2;
        a3.write(byArray);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        jc a5;
        a5.j.write(a2, a3, a4);
    }

    public void r() throws IOException {
        jc a2;
        a2.j.r();
    }

    @Override
    public void write(byte[] a2) throws IOException {
        jc a3;
        a3.write(a2, 0, a2.length);
    }
}

