/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ei;
import eos.moe.dragoncore.ic;
import eos.moe.dragoncore.ka;
import eos.moe.dragoncore.ua;
import eos.moe.dragoncore.x;
import eos.moe.dragoncore.yk;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class re
extends OutputStream
implements x {
    private RandomAccessFile q;
    private long b;
    private File o;
    private int y;
    private long k;
    private ua ALLATORIxDEMO = new ua();

    public re(File a2) throws FileNotFoundException, yk {
        a3(a2, -1L);
        re a3;
    }

    public re(File a2, long a3) throws FileNotFoundException, yk {
        re a4;
        if (a3 >= 0L && a3 < 65536L) {
            throw new yk("split length less than minimum allowed split length of 65536 Bytes");
        }
        a4.q = new RandomAccessFile(a2, ic.y.ALLATORIxDEMO());
        a4.b = a3;
        a4.o = a2;
        a4.y = 0;
        a4.k = 0L;
    }

    @Override
    public void write(int a2) throws IOException {
        re a3;
        a3.write(new byte[]{(byte)a2});
    }

    @Override
    public void write(byte[] a2) throws IOException {
        re a3;
        a3.write(a2, 0, a2.length);
    }

    @Override
    public void write(byte[] a2, int a3, int a4) throws IOException {
        re a5;
        if (a4 <= 0) {
            return;
        }
        if (a5.b == -1L) {
            a5.q.write(a2, a3, a4);
            a5.k += (long)a4;
            return;
        }
        if (a5.k >= a5.b) {
            a5.ALLATORIxDEMO();
            a5.q.write(a2, a3, a4);
            a5.k = a4;
        } else if (a5.k + (long)a4 > a5.b) {
            if (a5.ALLATORIxDEMO(a2)) {
                a5.ALLATORIxDEMO();
                a5.q.write(a2, a3, a4);
                a5.k = a4;
            } else {
                a5.q.write(a2, a3, (int)(a5.b - a5.k));
                a5.ALLATORIxDEMO();
                a5.q.write(a2, a3 + (int)(a5.b - a5.k), (int)((long)a4 - (a5.b - a5.k)));
                a5.k = (long)a4 - (a5.b - a5.k);
            }
        } else {
            a5.q.write(a2, a3, a4);
            a5.k += (long)a4;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO() throws IOException {
        re a2;
        String a3 = ka.ALLATORIxDEMO(a2.o.getName());
        String a4 = a2.o.getAbsolutePath();
        String a5 = a2.o.getParent() == null ? "" : a2.o.getParent() + System.getProperty("file.separator");
        String a6 = ".z0" + (a2.y + 1);
        if (a2.y >= 9) {
            a6 = ".z" + (a2.y + 1);
        }
        File a7 = new File(a5 + a3 + a6);
        a2.q.close();
        if (a7.exists()) {
            throw new IOException("split file: " + a7.getName() + " already exists in the current directory, cannot rename this file");
        }
        if (!a2.o.renameTo(a7)) {
            throw new IOException("cannot rename newly created split file");
        }
        a2.o = new File(a4);
        a2.q = new RandomAccessFile(a2.o, ic.y.ALLATORIxDEMO());
        ++a2.y;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(byte[] a2) {
        re a3;
        int a4 = a3.ALLATORIxDEMO.ALLATORIxDEMO(a2);
        for (ei a5 : ei.values()) {
            if (a5 == ei.c || a5.ALLATORIxDEMO() != (long)a4) continue;
            return true;
        }
        return false;
    }

    public boolean c(int a2) throws yk {
        re a3;
        if (a2 < 0) {
            throw new yk("negative buffersize for checkBufferSizeAndStartNextSplitFile");
        }
        if (!a3.ALLATORIxDEMO(a2)) {
            try {
                a3.ALLATORIxDEMO();
                a3.k = 0L;
                return true;
            }
            catch (IOException a4) {
                throw new yk(a4);
            }
        }
        return false;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(int a2) {
        re a3;
        if (a3.b >= 65536L) {
            return a3.k + (long)a2 <= a3.b;
        }
        return true;
    }

    public void ALLATORIxDEMO(long a2) throws IOException {
        re a3;
        a3.q.seek(a2);
    }

    public int ALLATORIxDEMO(int a2) throws IOException {
        re a3;
        return a3.q.skipBytes(a2);
    }

    @Override
    public void close() throws IOException {
        re a2;
        a2.q.close();
    }

    @Override
    public long ALLATORIxDEMO() throws IOException {
        re a2;
        return a2.q.getFilePointer();
    }

    public boolean ALLATORIxDEMO() {
        re a2;
        return a2.b != -1L;
    }

    public long c() {
        re a2;
        return a2.b;
    }

    @Override
    public int ALLATORIxDEMO() {
        re a2;
        return a2.y;
    }
}

