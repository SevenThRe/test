/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ec;
import eos.moe.dragoncore.ic;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class wm
extends InputStream {
    public RandomAccessFile b;
    public File o;
    private boolean y;
    private int k = 0;
    private byte[] ALLATORIxDEMO = new byte[1];

    public wm(File a2, boolean a3, int a4) throws FileNotFoundException {
        wm a5;
        a5.b = new RandomAccessFile(a2, ic.o.ALLATORIxDEMO());
        a5.o = a2;
        a5.y = a3;
        if (a3) {
            a5.k = a4;
        }
    }

    @Override
    public int read() throws IOException {
        wm a2;
        int a3 = a2.read(a2.ALLATORIxDEMO);
        if (a3 == -1) {
            return -1;
        }
        return a2.ALLATORIxDEMO[0];
    }

    @Override
    public int read(byte[] a2) throws IOException {
        wm a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        wm a5;
        int a6 = a5.b.read(a2, a3, a4);
        if ((a6 != a4 || a6 == -1) && a5.y) {
            int a7;
            a5.ALLATORIxDEMO(a5.k + 1);
            ++a5.k;
            if (a6 < 0) {
                a6 = 0;
            }
            if ((a7 = a5.b.read(a2, a6, a4 - a6)) > 0) {
                a6 += a7;
            }
        }
        return a6;
    }

    public void ALLATORIxDEMO(ec a2) throws IOException {
        wm a3;
        if (a3.y && a3.k != a2.x()) {
            a3.ALLATORIxDEMO(a2.x());
            a3.k = a2.x();
        }
        a3.b.seek(a2.k());
    }

    public void ALLATORIxDEMO(int a2) throws IOException {
        wm a3;
        File a4 = a3.ALLATORIxDEMO(a2);
        if (!a4.exists()) {
            throw new FileNotFoundException("zip split file does not exist: " + a4);
        }
        a3.b.close();
        a3.b = new RandomAccessFile(a4, ic.o.ALLATORIxDEMO());
    }

    public abstract File ALLATORIxDEMO(int var1) throws IOException;

    @Override
    public void close() throws IOException {
        wm a2;
        if (a2.b != null) {
            a2.b.close();
        }
    }
}

