/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ic;
import eos.moe.armourers.kc;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class mj
extends ic {
    private byte[] c;
    private Inflater v;
    private byte[] s;
    private int j;

    public mj(kc a2) {
        mj a3;
        mj mj2 = a3;
        super(a2);
        mj2.s = new byte[1];
        mj mj3 = a3;
        mj2.v = new Inflater(true);
        mj2.c = new byte[4096];
    }

    @Override
    public int read() throws IOException {
        mj a2;
        mj mj2 = a2;
        if (mj2.read(mj2.s) == -1) {
            return -1;
        }
        return a2.s[0];
    }

    @Override
    public void close() throws IOException {
        mj a2;
        if (a2.v != null) {
            a2.v.end();
        }
        super.close();
    }

    @Override
    public void r(PushbackInputStream a2) throws IOException {
        mj a3;
        int n2 = a3.v.getRemaining();
        if (n2 > 0) {
            byte[] byArray = a3.r();
            a2.unread(byArray, a3.j - n2, n2);
        }
    }

    @Override
    public int read(byte[] a2) throws IOException {
        mj a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public void r(InputStream a2) throws IOException {
        mj a3;
        if (a3.v != null) {
            a3.v.end();
            a3.v = null;
        }
        super.r(a2);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        try {
            int n2;
            block4: while (true) {
                mj a5;
                mj mj2 = a5;
                while ((n2 = mj2.v.inflate(a2, a3, a4)) == 0) {
                    if (a5.v.finished() || a5.v.needsDictionary()) {
                        return -1;
                    }
                    if (!a5.v.needsInput()) continue block4;
                    mj mj3 = a5;
                    mj2 = mj3;
                    mj3.r();
                }
                break;
            }
            return n2;
        }
        catch (DataFormatException dataFormatException) {
            throw new IOException(dataFormatException);
        }
    }

    private /* synthetic */ void r() throws IOException {
        mj a2;
        mj mj2 = a2;
        a2.j = super.read(a2.c, 0, mj2.c.length);
        if (a2.j == -1) {
            throw new EOFException("Unexpected end of input stream");
        }
        mj mj3 = a2;
        mj3.v.setInput(mj3.c, 0, a2.j);
    }
}

