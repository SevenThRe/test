/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.k;
import eos.moe.armourers.ph;
import eos.moe.armourers.ra;
import eos.moe.armourers.sb;
import eos.moe.armourers.wm;
import eos.moe.armourers.ya;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class pc
extends OutputStream
implements k {
    private long l;
    private int c;
    private long v;
    private File s;
    private ra m;
    private RandomAccessFile j;

    @Override
    public long r() throws IOException {
        pc a2;
        return a2.j.getFilePointer();
    }

    @Override
    public void write(int a2) throws IOException {
        pc a3;
        byte[] byArray = new byte[1];
        byArray[0] = (byte)a2;
        a3.write(byArray);
    }

    public void r(long a2) throws IOException {
        pc a3;
        a3.j.seek(a2);
    }

    public long y() {
        pc a2;
        return a2.l;
    }

    public boolean r() {
        pc a2;
        return a2.l != -1L;
    }

    public int r(int a2) throws IOException {
        pc a3;
        return a3.j.skipBytes(a2);
    }

    @Override
    public void close() throws IOException {
        pc a2;
        a2.j.close();
    }

    @Override
    public void write(byte[] a2) throws IOException {
        pc a3;
        a3.write(a2, 0, a2.length);
    }

    private /* synthetic */ boolean y(int a2) {
        pc a3;
        if (a3.l >= 65536L) {
            return a3.v + (long)a2 <= a3.l;
        }
        return true;
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        pc a5;
        if (a4 <= 0) {
            return;
        }
        if (a5.l == -1L) {
            pc pc2 = a5;
            pc2.j.write(a2, a3, a4);
            pc2.v += (long)a4;
            return;
        }
        pc pc3 = a5;
        if (a5.v >= a5.l) {
            pc3.r();
            a5.j.write(a2, a3, a4);
            a5.v = a4;
            return;
        }
        if (pc3.v + (long)a4 > a5.l) {
            pc pc4 = a5;
            if (a5.r(a2)) {
                pc4.r();
                a5.j.write(a2, a3, a4);
                a5.v = a4;
                return;
            }
            pc4.j.write(a2, a3, (int)(a5.l - a5.v));
            pc pc5 = a5;
            pc5.r();
            pc5.j.write(a2, a3 + (int)(a5.l - a5.v), (int)((long)a4 - (a5.l - a5.v)));
            pc5.v = (long)a4 - (a5.l - a5.v);
            return;
        }
        pc pc6 = a5;
        pc6.j.write(a2, a3, a4);
        pc6.v += (long)a4;
    }

    public pc(File a2, long a3) throws FileNotFoundException, ph {
        pc a4;
        pc pc2 = a4;
        pc2.m = new ra();
        if (a3 >= 0L && a3 < 65536L) {
            throw new ph("split length less than minimum allowed split length of 65536 Bytes");
        }
        pc pc3 = a4;
        pc pc4 = a4;
        pc4.j = new RandomAccessFile(a2, sb.j.r());
        pc4.l = a3;
        pc3.s = a2;
        pc3.c = 0;
        a4.v = 0L;
    }

    private /* synthetic */ boolean r(byte[] a22) {
        int n2;
        pc a3;
        int a22 = a3.m.r(a22);
        wm[] wmArray = wm.values();
        int n3 = wmArray.length;
        int n4 = n2 = 0;
        while (n4 < n3) {
            wm wm2 = wmArray[n2];
            if (wm2 != wm.l && wm2.r() == (long)a22) {
                return true;
            }
            n4 = ++n2;
        }
        return false;
    }

    @Override
    public int r() {
        pc a2;
        return a2.c;
    }

    public boolean r(int a22) throws ph {
        pc a3;
        if (a22 < 0) {
            throw new ph("negative buffersize for checkBufferSizeAndStartNextSplitFile");
        }
        if (!a3.y(a22)) {
            try {
                a3.r();
                a3.v = 0L;
                return true;
            }
            catch (IOException a22) {
                throw new ph(a22);
            }
        }
        return false;
    }

    public pc(File a2) throws FileNotFoundException, ph {
        a3(a2, -1L);
        pc a3;
    }

    private /* synthetic */ void r() throws IOException {
        pc a2;
        pc pc2 = a2;
        Object object = ya.y(pc2.s.getName());
        String string = pc2.s.getAbsolutePath();
        String string2 = pc2.s.getParent() == null ? "" : new StringBuilder().insert(0, a2.s.getParent()).append(System.getProperty("file.separator")).toString();
        String string3 = new StringBuilder().insert(0, ".z0").append(a2.c + 1).toString();
        if (a2.c >= 9) {
            string3 = new StringBuilder().insert(0, ".z").append(a2.c + 1).toString();
        }
        object = new File(new StringBuilder().insert(0, string2).append((String)object).append(string3).toString());
        a2.j.close();
        if (((File)object).exists()) {
            throw new IOException(new StringBuilder().insert(0, "split file: ").append(((File)object).getName()).append(" already exists in the current directory, cannot rename this file").toString());
        }
        if (!a2.s.renameTo((File)object)) {
            throw new IOException("cannot rename newly created split file");
        }
        a2.s = new File(string);
        pc pc3 = a2;
        a2.j = new RandomAccessFile(a2.s, sb.j.r());
        ++a2.c;
    }
}

