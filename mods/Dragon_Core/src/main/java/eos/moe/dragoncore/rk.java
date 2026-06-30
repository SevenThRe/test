/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cj;
import eos.moe.dragoncore.lc;
import eos.moe.dragoncore.s;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class rk<T extends s>
extends OutputStream {
    private cj k;
    private T ALLATORIxDEMO;

    public rk(cj a2, lc a3, char[] a4) throws IOException, yk {
        rk a5;
        a5.k = a2;
        a5.ALLATORIxDEMO = a5.ALLATORIxDEMO(a2, a3, a4);
    }

    @Override
    public void write(int a2) throws IOException {
        rk a3;
        a3.k.write(a2);
    }

    @Override
    public void write(byte[] a2) throws IOException {
        rk a3;
        a3.k.write(a2);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        rk a5;
        a5.ALLATORIxDEMO.ALLATORIxDEMO(a2, a3, a4);
        a5.k.write(a2, a3, a4);
    }

    public void ALLATORIxDEMO(byte[] a2) throws IOException {
        rk a3;
        a3.k.write(a2);
    }

    public void ALLATORIxDEMO() throws IOException {
        rk a2;
        a2.k.ALLATORIxDEMO();
    }

    @Override
    public void close() throws IOException {
        rk a2;
        a2.k.close();
    }

    public long ALLATORIxDEMO() {
        rk a2;
        return a2.k.ALLATORIxDEMO();
    }

    public T ALLATORIxDEMO() {
        rk a2;
        return a2.ALLATORIxDEMO;
    }

    public abstract T ALLATORIxDEMO(OutputStream var1, lc var2, char[] var3) throws IOException, yk;
}

