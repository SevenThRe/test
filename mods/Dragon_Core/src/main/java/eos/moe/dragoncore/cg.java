/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.rk;
import java.io.IOException;
import java.io.OutputStream;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class cg
extends OutputStream {
    private rk ALLATORIxDEMO;

    public cg(rk a2) {
        cg a3;
        a3.ALLATORIxDEMO = a2;
    }

    @Override
    public void write(int a2) throws IOException {
        cg a3;
        a3.write(new byte[]{(byte)a2});
    }

    @Override
    public void write(byte[] a2) throws IOException {
        cg a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        cg a5;
        a5.ALLATORIxDEMO.write(a2, a3, a4);
    }

    public void ALLATORIxDEMO() throws IOException {
        cg a2;
        a2.ALLATORIxDEMO.ALLATORIxDEMO();
    }

    @Override
    public void close() throws IOException {
        cg a2;
        a2.ALLATORIxDEMO.close();
    }

    public long ALLATORIxDEMO() {
        cg a2;
        return a2.ALLATORIxDEMO.ALLATORIxDEMO();
    }
}

