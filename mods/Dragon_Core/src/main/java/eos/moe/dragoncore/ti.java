/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.re;
import eos.moe.dragoncore.x;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ti
extends OutputStream
implements x {
    private OutputStream k;
    private long ALLATORIxDEMO = 0L;

    public ti(OutputStream a2) {
        ti a3;
        a3.k = a2;
    }

    @Override
    public void write(int a2) throws IOException {
        ti a3;
        a3.write(new byte[]{(byte)a2});
    }

    @Override
    public void write(byte[] a2) throws IOException {
        ti a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        ti a5;
        a5.k.write(a2, a3, a4);
        a5.ALLATORIxDEMO += (long)a4;
    }

    @Override
    public int ALLATORIxDEMO() {
        ti a2;
        if (a2.ALLATORIxDEMO()) {
            return ((re)a2.k).ALLATORIxDEMO();
        }
        return 0;
    }

    public long x() throws IOException {
        ti a2;
        if (a2.k instanceof re) {
            return ((re)a2.k).ALLATORIxDEMO();
        }
        return a2.ALLATORIxDEMO;
    }

    public long f() {
        ti a2;
        if (a2.ALLATORIxDEMO()) {
            return ((re)a2.k).c();
        }
        return 0L;
    }

    public boolean ALLATORIxDEMO() {
        ti a2;
        return a2.k instanceof re && ((re)a2.k).ALLATORIxDEMO();
    }

    public long c() throws IOException {
        ti a2;
        if (a2.k instanceof re) {
            return ((re)a2.k).ALLATORIxDEMO();
        }
        return a2.ALLATORIxDEMO;
    }

    public boolean ALLATORIxDEMO(int a2) throws yk {
        ti a3;
        if (!a3.ALLATORIxDEMO()) {
            return false;
        }
        return ((re)a3.k).c(a2);
    }

    @Override
    public long ALLATORIxDEMO() throws IOException {
        ti a2;
        if (a2.k instanceof re) {
            return ((re)a2.k).ALLATORIxDEMO();
        }
        return a2.ALLATORIxDEMO;
    }

    @Override
    public void close() throws IOException {
        ti a2;
        a2.k.close();
    }
}

