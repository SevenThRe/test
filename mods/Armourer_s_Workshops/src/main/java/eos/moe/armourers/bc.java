/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.sb;
import eos.moe.armourers.ya;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class bc
extends RandomAccessFile {
    private String l;
    private long c;
    private RandomAccessFile v;
    private File[] s;
    private int m;
    private byte[] j;

    @Override
    public long length() throws IOException {
        bc a2;
        return a2.v.length();
    }

    @Override
    public void seek(long a2) throws IOException {
        bc a3;
        int n2 = (int)(a2 / a3.c);
        if (n2 != a3.m) {
            a3.r(n2);
        }
        a3.v.seek(a2 - (long)n2 * a3.c);
    }

    @Override
    public long getFilePointer() throws IOException {
        bc a2;
        return a2.v.getFilePointer();
    }

    @Override
    public int read(byte[] a2) throws IOException {
        bc a3;
        return a3.read(a2, 0, a2.length);
    }

    @Override
    public int read() throws IOException {
        bc a2;
        bc bc2 = a2;
        if (bc2.read(bc2.j) == -1) {
            return -1;
        }
        return a2.j[0] & 0xFF;
    }

    @Override
    public void write(byte[] a2) throws IOException {
        bc a3;
        a3.write(a2, 0, a2.length);
    }

    public bc(File a2, String a3, File[] a4) throws IOException {
        super(a2, a3);
        bc a5;
        a5.j = new byte[1];
        a5.m = 0;
        super.close();
        if (sb.j.r().equals(a3)) {
            throw new IllegalArgumentException("write mode is not allowed for NumberedSplitRandomAccessFile");
        }
        bc bc2 = a5;
        a5.r(a4);
        bc bc3 = a5;
        a5.v = new RandomAccessFile(a2, a3);
        bc2.s = a4;
        bc2.c = a2.length();
        a5.l = a3;
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        throw new UnsupportedOperationException();
    }

    private /* synthetic */ void r(int a2) throws IOException {
        bc a3;
        if (a3.m == a2) {
            return;
        }
        if (a2 > a3.s.length - 1) {
            throw new IOException("split counter greater than number of split files");
        }
        if (a3.v != null) {
            a3.v.close();
        }
        a3.v = new RandomAccessFile(a3.s[a2], a3.l);
        a3.m = a2;
    }

    public bc(File a2, String a3) throws IOException {
        bc a4;
        File file = a2;
        a4(file, a3, ya.r(file));
    }

    public void r(long a2) throws IOException {
        bc a3;
        a3.v.seek(a2);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        bc a5;
        int n2 = a5.v.read(a2, a3, a4);
        if (n2 == -1) {
            bc bc2 = a5;
            if (bc2.m == bc2.s.length - 1) {
                return -1;
            }
            bc bc3 = a5;
            bc3.r(bc3.m + 1);
            return a5.read(a2, a3, a4);
        }
        return n2;
    }

    public void r() throws IOException {
        bc a2;
        bc bc2 = a2;
        bc2.r(bc2.s.length - 1);
    }

    private /* synthetic */ void r(File[] a2) throws IOException {
        int n2;
        int n3 = 1;
        int n4 = a2.length;
        int n5 = n2 = 0;
        while (n5 < n4) {
            String string = ya.r(a2[n2]);
            try {
                if (n3 != Integer.parseInt(string)) {
                    throw new IOException(new StringBuilder().insert(0, "Split file number ").append(n3).append(" does not exist").toString());
                }
                ++n3;
            }
            catch (NumberFormatException numberFormatException) {
                throw new IOException(new StringBuilder().insert(0, "Split file extension not in expected format. Found: ").append(string).append(" expected of format: .001, .002, etc").toString());
            }
            n5 = ++n2;
        }
    }

    public bc(String a2, String a3) throws IOException {
        a4(new File(a2), a3);
        bc a4;
    }

    @Override
    public void write(int a2) throws IOException {
        throw new UnsupportedOperationException();
    }
}

