/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ic;
import eos.moe.dragoncore.ka;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class og
extends RandomAccessFile {
    private long q;
    private File[] b;
    private RandomAccessFile o;
    private byte[] y = new byte[1];
    private int k = 0;
    private String ALLATORIxDEMO;

    public og(String a2, String a3) throws IOException {
        a4(new File(a2), a3);
        og a4;
    }

    public og(File a2, String a3) throws IOException {
        a4(a2, a3, ka.ALLATORIxDEMO(a2));
        og a4;
    }

    public og(File a2, String a3, File[] a4) throws IOException {
        super(a2, a3);
        og a5;
        super.close();
        if (ic.y.ALLATORIxDEMO().equals(a3)) {
            throw new IllegalArgumentException("write mode is not allowed for NumberedSplitRandomAccessFile");
        }
        a5.ALLATORIxDEMO(a4);
        a5.o = new RandomAccessFile(a2, a3);
        a5.b = a4;
        a5.q = a2.length();
        a5.ALLATORIxDEMO = a3;
    }

    @Override
    public int read() throws IOException {
        og a2;
        int a3 = a2.read(a2.y);
        if (a3 == -1) {
            return -1;
        }
        return a2.y[0] & 0xFF;
    }

    @Override
    public int read(byte[] a2) throws IOException {
        og a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        og a5;
        int a6 = a5.o.read(a2, a3, a4);
        if (a6 == -1) {
            if (a5.k == a5.b.length - 1) {
                return -1;
            }
            a5.ALLATORIxDEMO(a5.k + 1);
            return a5.read(a2, a3, a4);
        }
        return a6;
    }

    @Override
    public void write(int a2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void write(byte[] a2) throws IOException {
        og a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void seek(long a2) throws IOException {
        og a3;
        int a4 = (int)(a2 / a3.q);
        if (a4 != a3.k) {
            a3.ALLATORIxDEMO(a4);
        }
        a3.o.seek(a2 - (long)a4 * a3.q);
    }

    @Override
    public long getFilePointer() throws IOException {
        og a2;
        return a2.o.getFilePointer();
    }

    @Override
    public long length() throws IOException {
        og a2;
        return a2.o.length();
    }

    public void ALLATORIxDEMO(long a2) throws IOException {
        og a3;
        a3.o.seek(a2);
    }

    public void ALLATORIxDEMO() throws IOException {
        og a2;
        a2.ALLATORIxDEMO(a2.b.length - 1);
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2) throws IOException {
        og a3;
        if (a3.k == a2) {
            return;
        }
        if (a2 > a3.b.length - 1) {
            throw new IOException("split counter greater than number of split files");
        }
        if (a3.o != null) {
            a3.o.close();
        }
        a3.o = new RandomAccessFile(a3.b[a2], a3.ALLATORIxDEMO);
        a3.k = a2;
    }

    private /* synthetic */ void ALLATORIxDEMO(File[] a2) throws IOException {
        int a3 = 1;
        for (File a4 : a2) {
            String a5 = ka.c(a4);
            try {
                if (a3 != Integer.parseInt(a5)) {
                    throw new IOException("Split file number " + a3 + " does not exist");
                }
                ++a3;
            }
            catch (NumberFormatException a6) {
                throw new IOException("Split file extension not in expected format. Found: " + a5 + " expected of format: .001, .002, etc");
            }
        }
    }
}

