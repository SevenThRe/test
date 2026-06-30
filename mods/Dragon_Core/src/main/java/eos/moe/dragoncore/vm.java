/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.a;
import eos.moe.dragoncore.gc;
import eos.moe.dragoncore.hc;
import eos.moe.dragoncore.il;
import eos.moe.dragoncore.ta;
import eos.moe.dragoncore.yk;
import java.io.IOException;
import java.io.InputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class vm<T extends a>
extends InputStream {
    private il b;
    private T o;
    private byte[] y;
    private byte[] k = new byte[1];
    private hc ALLATORIxDEMO;

    public vm(il a2, hc a3, char[] a4) throws IOException, yk {
        vm a5;
        a5.b = a2;
        a5.o = a5.ALLATORIxDEMO(a3, a4);
        a5.ALLATORIxDEMO = a3;
        if (a5.ALLATORIxDEMO(a3) == gc.o) {
            a5.y = new byte[4096];
        }
    }

    @Override
    public int read() throws IOException {
        vm a2;
        int a3 = a2.read(a2.k);
        if (a3 == -1) {
            return -1;
        }
        return a2.k[0] & 0xFF;
    }

    @Override
    public int read(byte[] a2) throws IOException {
        vm a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        vm a5;
        int a6 = ta.ALLATORIxDEMO(a5.b, a2, a3, a4);
        if (a6 > 0) {
            a5.ALLATORIxDEMO(a2, a6);
            a5.o.ALLATORIxDEMO(a2, a3, a6);
        }
        return a6;
    }

    @Override
    public void close() throws IOException {
        vm a2;
        a2.b.close();
    }

    public byte[] ALLATORIxDEMO() {
        vm a2;
        return a2.y;
    }

    public int ALLATORIxDEMO(byte[] a2) throws IOException {
        vm a3;
        return a3.b.ALLATORIxDEMO(a2);
    }

    private /* synthetic */ void ALLATORIxDEMO(byte[] a2, int a3) {
        vm a4;
        if (a4.y != null) {
            System.arraycopy(a2, 0, a4.y, 0, a3);
        }
    }

    private /* synthetic */ gc ALLATORIxDEMO(hc a2) throws yk {
        if (a2.ALLATORIxDEMO() != gc.y) {
            return a2.ALLATORIxDEMO();
        }
        if (a2.ALLATORIxDEMO() == null) {
            throw new yk("AesExtraDataRecord not present in localheader for aes encrypted data");
        }
        return a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    public T ALLATORIxDEMO() {
        vm a2;
        return a2.o;
    }

    public void ALLATORIxDEMO(InputStream a2) throws IOException {
    }

    public long ALLATORIxDEMO() {
        vm a2;
        return a2.b.ALLATORIxDEMO();
    }

    public hc ALLATORIxDEMO() {
        vm a2;
        return a2.ALLATORIxDEMO;
    }

    public abstract T ALLATORIxDEMO(hc var1, char[] var2) throws IOException, yk;
}

