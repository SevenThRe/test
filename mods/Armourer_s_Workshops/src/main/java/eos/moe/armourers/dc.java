/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.pb;
import eos.moe.armourers.sb;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class dc
extends InputStream {
    public RandomAccessFile c;
    private byte[] v;
    public File s;
    private boolean m;
    private int j;

    @Override
    public void close() throws IOException {
        dc a2;
        if (a2.c != null) {
            a2.c.close();
        }
    }

    @Override
    public int read(byte[] a2) throws IOException {
        dc a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a22, int a3, int a4) throws IOException {
        dc a5;
        if (((a3 = a5.c.read(a22, a3, a4)) != a4 || a3 == -1) && a5.m) {
            dc dc2 = a5;
            dc2.r(dc2.j + 1);
            ++dc2.j;
            if (a3 < 0) {
                a3 = 0;
            }
            int n2 = a3;
            int a22 = a5.c.read(a22, n2, a4 - n2);
            if (a22 > 0) {
                a3 += a22;
            }
        }
        return a3;
    }

    public abstract File r(int var1) throws IOException;

    public void r(int a22) throws IOException {
        dc a3;
        File a22 = a3.r(a22);
        if (!a22.exists()) {
            throw new FileNotFoundException(new StringBuilder().insert(0, "zip split file does not exist: ").append(a22).toString());
        }
        a3.c.close();
        dc dc2 = a3;
        a3.c = new RandomAccessFile(a22, sb.s.r());
    }

    public dc(File a2, boolean a3, int a4) throws FileNotFoundException {
        dc a5;
        dc dc2 = a5;
        a5.j = 0;
        dc2.v = new byte[1];
        dc dc3 = a5;
        dc2.c = new RandomAccessFile(a2, sb.s.r());
        dc2.s = a2;
        a5.m = a3;
        if (a5.m) {
            a5.j = a4;
        }
    }

    public void r(pb a2) throws IOException {
        dc a3;
        if (a3.m && a3.j != a2.s()) {
            a3.r(a2.s());
            a3.j = a2.s();
        }
        a3.c.seek(a2.s());
    }

    @Override
    public int read() throws IOException {
        dc a2;
        dc dc2 = a2;
        if (dc2.read(dc2.v) == -1) {
            return -1;
        }
        return a2.v[0];
    }
}

