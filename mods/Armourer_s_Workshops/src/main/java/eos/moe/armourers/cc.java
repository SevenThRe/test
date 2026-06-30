/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ab;
import eos.moe.armourers.jc;
import eos.moe.armourers.mc;
import java.io.IOException;
import java.util.zip.Deflater;

public class cc
extends jc {
    private byte[] m = new byte[4096];
    public Deflater j;

    private /* synthetic */ void y() throws IOException {
        cc a2;
        cc cc2 = a2;
        int n2 = cc2.j.deflate(cc2.m, 0, a2.m.length);
        if (n2 > 0) {
            cc cc3 = a2;
            super.write(cc3.m, 0, n2);
        }
    }

    @Override
    public void write(int a2) throws IOException {
        cc a3;
        byte[] byArray = new byte[]{(byte)a2};
        a3.write(byArray, 0, 1);
    }

    @Override
    public void r() throws IOException {
        cc a2;
        if (!a2.j.finished()) {
            cc cc2 = a2;
            cc cc3 = cc2;
            cc2.j.finish();
            while (!cc3.j.finished()) {
                cc cc4 = a2;
                cc3 = cc4;
                cc4.y();
            }
        }
        cc cc5 = a2;
        cc5.j.end();
        super.r();
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        cc a5;
        cc cc2 = a5;
        cc cc3 = cc2;
        cc2.j.setInput(a2, a3, a4);
        while (!cc3.j.needsInput()) {
            cc cc4 = a5;
            cc3 = cc4;
            cc4.y();
        }
    }

    public cc(mc a2, ab a3) {
        super(a2);
        cc a4;
        cc cc2 = a4;
        a4.j = new Deflater(a3.r(), true);
    }

    @Override
    public void write(byte[] a2) throws IOException {
        cc a3;
        a3.write(a2, 0, a2.length);
    }
}

