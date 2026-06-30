/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ac;
import eos.moe.armourers.c;
import eos.moe.armourers.cb;
import eos.moe.armourers.eb;
import eos.moe.armourers.ga;
import eos.moe.armourers.hl;
import eos.moe.armourers.ic;
import eos.moe.armourers.kc;
import eos.moe.armourers.lb;
import eos.moe.armourers.lg;
import eos.moe.armourers.mj;
import eos.moe.armourers.nb;
import eos.moe.armourers.nd;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.qc;
import eos.moe.armourers.tc;
import eos.moe.armourers.uh;
import eos.moe.armourers.wm;
import eos.moe.armourers.yb;
import eos.moe.armourers.zc;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class oc
extends InputStream {
    private ic t;
    private hl w;
    private PushbackInputStream r;
    private byte[] l;
    private nb c;
    private boolean v;
    private char[] s;
    private CRC32 m;
    private Charset j;

    private /* synthetic */ ic r(nb a2) throws IOException {
        oc a3;
        zc zc2 = new zc(a3.r, a3.r(a2));
        oc oc2 = a3;
        return oc2.r(oc2.r(zc2, a2), a2);
    }

    public oc(InputStream a2, Charset a3) {
        a4(a2, null, a3);
        oc a4;
    }

    private /* synthetic */ void x() throws IOException {
        oc a2;
        if (a2.c.r() == yb.j && a2.c.r().r().equals((Object)lb.s)) {
            return;
        }
        if (a2.c.h() != a2.m.getValue()) {
            uh uh2 = uh.r;
            oc oc2 = a2;
            if (oc2.r(oc2.c)) {
                uh2 = uh.m;
            }
            throw new ph(new StringBuilder().insert(0, "Reached end of entry, but crc verification failed for ").append(a2.c.r()).toString(), uh2);
        }
    }

    public oc(InputStream a2) {
        a3(a2, null, ga.d);
        oc a3;
    }

    private /* synthetic */ kc r(zc a2, nb a3) throws IOException {
        oc a4;
        if (!a3.r()) {
            return new lg(a2, a3, a4.s);
        }
        if (a3.r() == yb.j) {
            return new nd(a2, a3, a4.s);
        }
        return new ac(a2, a3, a4.s);
    }

    public oc(InputStream a2, char[] a3) {
        a4(a2, a3, ga.d);
        oc a4;
    }

    public int r() throws IOException {
        oc a2;
        return a2.r.available();
    }

    private /* synthetic */ boolean r(String a2) {
        return a2.endsWith("/") || a2.endsWith("\\");
    }

    private /* synthetic */ void r(nb a2) throws IOException {
        oc a3;
        if (!a3.r(a2.r()) && a2.r() == cb.s && a2.r() < 0L) {
            throw new IOException(new StringBuilder().insert(0, "Invalid local file header for: ").append(a2.r()).append(". Uncompressed size has to be set for entry of compression type store which is not a directory").toString());
        }
    }

    private /* synthetic */ void h() throws IOException {
        oc a2;
        oc oc2 = a2;
        oc oc3 = a2;
        oc2.t.r(oc3.r);
        oc2.t.r((InputStream)a2.r);
        oc3.r();
        oc2.x();
        oc2.y();
    }

    private /* synthetic */ void z() throws IOException {
        oc a2;
        if (a2.c.z() || a2.c.z() == 0L) {
            return;
        }
        if (a2.l == null) {
            a2.l = new byte[512];
        }
        oc oc2 = a2;
        while (oc2.read(a2.l) != -1) {
            oc2 = a2;
        }
    }

    @Override
    public int read() throws IOException {
        oc a2;
        byte[] byArray = new byte[1];
        if (a2.read(byArray) == -1) {
            return -1;
        }
        return byArray[0] & 0xFF;
    }

    @Override
    public int read(byte[] a2) throws IOException {
        oc a3;
        return a3.read(a2, 0, a2.length);
    }

    private /* synthetic */ int r(nb a2) {
        if (!a2.r()) {
            return 0;
        }
        if (a2.r().equals((Object)yb.j)) {
            return 12 + a2.r().r().r();
        }
        if (a2.r().equals((Object)yb.m)) {
            return 12;
        }
        return 0;
    }

    private /* synthetic */ boolean r(nb a2) {
        return a2.r() && yb.m.equals((Object)a2.r());
    }

    public nb r() throws IOException {
        oc a2;
        return a2.r((pb)null);
    }

    @Override
    public int read(byte[] a2, int a3, int a4) throws IOException {
        oc a5;
        if (a4 < 0) {
            throw new IllegalArgumentException("Negative read length");
        }
        if (a4 == 0) {
            return 0;
        }
        if (a5.c == null) {
            return -1;
        }
        try {
            int n2 = a5.t.read(a2, a3, a4);
            if (n2 == -1) {
                a5.h();
                return n2;
            }
            a5.m.update(a2, a3, n2);
            return n2;
        }
        catch (IOException iOException) {
            if (iOException.getCause() != null && iOException.getCause() instanceof DataFormatException) {
                oc oc2 = a5;
                if (oc2.r(oc2.c)) {
                    throw new ph(iOException.getMessage(), iOException.getCause(), uh.m);
                }
            }
            throw iOException;
        }
    }

    private /* synthetic */ ic r(kc a2, nb a3) {
        if (eos.moe.armourers.c.r(a3) == cb.j) {
            return new mj(a2);
        }
        return new qc(a2);
    }

    @Override
    public void close() throws IOException {
        oc a2;
        if (a2.t != null) {
            a2.t.close();
        }
    }

    private /* synthetic */ long r(nb a2) {
        oc a3;
        if (eos.moe.armourers.c.r(a2).equals((Object)cb.s)) {
            return a2.r();
        }
        if (a2.h() && !a3.v) {
            return -1L;
        }
        return a2.z() - (long)a3.r(a2);
    }

    public nb r(pb a2) throws IOException {
        oc oc2;
        oc a3;
        if (a3.c != null) {
            a3.z();
        }
        oc oc3 = a3;
        a3.c = oc3.w.r((InputStream)oc3.r, a3.j);
        if (oc3.c == null) {
            return null;
        }
        oc oc4 = a3;
        oc4.r(oc4.c);
        oc4.m.reset();
        if (a2 != null) {
            oc oc5 = a3;
            oc2 = oc5;
            oc oc6 = a3;
            oc6.c.y(a2.h());
            oc6.c.r(a2.z());
            oc5.c.h(a2.r());
            oc5.v = true;
        } else {
            oc2 = a3;
            a3.v = false;
        }
        oc oc7 = a3;
        oc2.t = oc7.r(oc7.c);
        return a3.c;
    }

    private /* synthetic */ void y() {
        oc a2;
        a2.c = null;
        a2.m.reset();
    }

    private /* synthetic */ boolean r(List<eb> a2) {
        if (a2 == null) {
            return false;
        }
        a2 = a2.iterator();
        while (a2.hasNext()) {
            if (((eb)a2.next()).r() != wm.t.r()) continue;
            return true;
        }
        return false;
    }

    public oc(InputStream a2, char[] a3, Charset a4) {
        oc a5;
        oc oc2 = a5;
        oc oc3 = a5;
        oc2.w = new hl();
        oc2.m = new CRC32();
        oc2.v = false;
        if (a4 == null) {
            a4 = ga.d;
        }
        oc oc4 = a5;
        oc4.r = new PushbackInputStream(a2, 4096);
        oc4.s = a3;
        a5.j = a4;
    }

    private /* synthetic */ void r() throws IOException {
        oc a2;
        if (!a2.c.h() || a2.v) {
            return;
        }
        oc oc2 = a2;
        oc oc3 = a2;
        oc oc4 = a2;
        tc tc2 = oc2.w.r((InputStream)oc3.r, oc4.r(oc4.c.r()));
        oc2.c.r(tc2.r());
        oc3.c.h(tc2.y());
        oc2.c.y(tc2.z());
    }
}

