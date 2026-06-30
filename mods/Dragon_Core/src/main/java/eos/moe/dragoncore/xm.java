/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.mf;
import eos.moe.dragoncore.vm;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class xm
extends mf {
    private Inflater o;
    private byte[] y;
    private byte[] k = new byte[1];
    private int ALLATORIxDEMO;

    public xm(vm a2) {
        super(a2);
        xm a3;
        a3.o = new Inflater(true);
        a3.y = new byte[4096];
    }

    @Override
    public int read() throws IOException {
        xm a2;
        int a3 = a2.read(a2.k);
        if (a3 == -1) {
            return -1;
        }
        return a2.k[0];
    }

    @Override
    public int read(byte[] a2) throws IOException {
        xm a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        try {
            xm a5;
            int a6;
            while ((a6 = a5.o.inflate(a2, a3, a4)) == 0) {
                if (a5.o.finished() || a5.o.needsDictionary()) {
                    return -1;
                }
                if (!a5.o.needsInput()) continue;
                a5.ALLATORIxDEMO();
            }
            return a6;
        }
        catch (DataFormatException a7) {
            throw new IOException(a7);
        }
    }

    @Override
    public void ALLATORIxDEMO(InputStream a2) throws IOException {
        xm a3;
        if (a3.o != null) {
            a3.o.end();
            a3.o = null;
        }
        super.ALLATORIxDEMO(a2);
    }

    @Override
    public void ALLATORIxDEMO(PushbackInputStream a2) throws IOException {
        xm a3;
        int a4 = a3.o.getRemaining();
        if (a4 > 0) {
            byte[] a5 = a3.ALLATORIxDEMO();
            a2.unread(a5, a3.ALLATORIxDEMO - a4, a4);
        }
    }

    @Override
    public void close() throws IOException {
        xm a2;
        if (a2.o != null) {
            a2.o.end();
        }
        super.close();
    }

    private /* synthetic */ void ALLATORIxDEMO() throws IOException {
        xm a2;
        a2.ALLATORIxDEMO = super.read(a2.y, 0, a2.y.length);
        if (a2.ALLATORIxDEMO == -1) {
            throw new EOFException("Unexpected end of input stream");
        }
        a2.o.setInput(a2.y, 0, a2.ALLATORIxDEMO);
    }
}

