/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cg;
import eos.moe.dragoncore.rk;
import eos.moe.dragoncore.sc;
import java.io.IOException;
import java.util.zip.Deflater;

public class xe
extends cg {
    private byte[] k = new byte[4096];
    public Deflater ALLATORIxDEMO;

    public xe(rk a2, sc a3) {
        super(a2);
        xe a4;
        a4.ALLATORIxDEMO = new Deflater(a3.ALLATORIxDEMO(), true);
    }

    @Override
    public void write(byte[] a2) throws IOException {
        xe a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(int a2) throws IOException {
        xe a3;
        byte[] a4 = new byte[]{(byte)a2};
        a3.write(a4, 0, 1);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        xe a5;
        a5.ALLATORIxDEMO.setInput(a2, a3, a4);
        while (!a5.ALLATORIxDEMO.needsInput()) {
            a5.c();
        }
    }

    private /* synthetic */ void c() throws IOException {
        xe a2;
        int a3 = a2.ALLATORIxDEMO.deflate(a2.k, 0, a2.k.length);
        if (a3 > 0) {
            super.write(a2.k, 0, a3);
        }
    }

    @Override
    public void ALLATORIxDEMO() throws IOException {
        xe a2;
        if (!a2.ALLATORIxDEMO.finished()) {
            a2.ALLATORIxDEMO.finish();
            while (!a2.ALLATORIxDEMO.finished()) {
                a2.c();
            }
        }
        a2.ALLATORIxDEMO.end();
        super.ALLATORIxDEMO();
    }
}

