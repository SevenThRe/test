/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.bc;
import eos.moe.armourers.bg;
import eos.moe.armourers.c;
import eos.moe.armourers.cb;
import eos.moe.armourers.db;
import eos.moe.armourers.eb;
import eos.moe.armourers.fb;
import eos.moe.armourers.gb;
import eos.moe.armourers.kb;
import eos.moe.armourers.lb;
import eos.moe.armourers.nb;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.ra;
import eos.moe.armourers.rc;
import eos.moe.armourers.tc;
import eos.moe.armourers.vb;
import eos.moe.armourers.wa;
import eos.moe.armourers.wb;
import eos.moe.armourers.wc;
import eos.moe.armourers.wm;
import eos.moe.armourers.yb;
import eos.moe.armourers.yc;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class hl {
    private byte[] s;
    private kb m;
    private ra j;

    private /* synthetic */ List<eb> r(InputStream a22, int a3) throws IOException {
        if (a3 < 4) {
            if (a3 > 0) {
                a22.skip(a3);
            }
            return null;
        }
        byte[] byArray = new byte[a3];
        c.r(a22, byArray);
        try {
            hl a4;
            return a4.r(byArray, a3);
        }
        catch (Exception a22) {
            return Collections.emptyList();
        }
    }

    private /* synthetic */ void r(InputStream a2, nb a3) throws IOException {
        hl a4;
        int n2 = a3.z();
        if (n2 <= 0) {
            return;
        }
        a3.r(a4.r(a2, n2));
    }

    private /* synthetic */ void y(pb a2, ra a3) throws ph {
        hl a4;
        if (a2.r() == null || a2.r().size() <= 0) {
            return;
        }
        a3 = a4.r(a2.r(), (ra)a3);
        if (a3 != null) {
            pb pb2 = a2;
            pb2.r((wc)a3);
            pb2.r(yb.j);
        }
    }

    private /* synthetic */ void y(nb a2, ra a3) throws ph {
        hl a4;
        if (a2 == null) {
            throw new ph("file header is null in reading Zip64 Extended Info");
        }
        if (a2.r() == null || a2.r().size() <= 0) {
            return;
        }
        a3 = a4.r(a2.r(), (ra)a3, a2.r(), a2.z(), 0L, 0);
        if (a3 == null) {
            return;
        }
        a2.r((wb)a3);
        if (((wb)a3).r() != -1L) {
            a2.h(((wb)a3).r());
        }
        if (((wb)a3).y() != -1L) {
            a2.r(((wb)a3).y());
        }
    }

    private /* synthetic */ void r(pb a2, ra a3) throws ph {
        hl a4;
        if (a2.r() == null || a2.r().size() <= 0) {
            return;
        }
        a3 = a4.r(a2.r(), (ra)a3, a2.r(), a2.z(), a2.s(), a2.s());
        if (a3 == null) {
            return;
        }
        a2.r((wb)a3);
        if (((wb)a3).r() != -1L) {
            a2.h(((wb)a3).r());
        }
        if (((wb)a3).y() != -1L) {
            a2.r(((wb)a3).y());
        }
        if (((wb)a3).z() != -1L) {
            a2.x(((wb)a3).z());
        }
        if (((wb)a3).r() != -1) {
            a2.s(((wb)a3).r());
        }
    }

    public nb r(InputStream a2, Charset a3) throws IOException {
        hl hl2;
        hl a4;
        nb nb2 = new nb();
        byte[] byArray = new byte[4];
        if ((long)a4.j.r(a2) != wm.c.r()) {
            return null;
        }
        nb2.r(wm.c);
        nb2.z(a4.j.y(a2));
        byte[] byArray2 = new byte[2];
        if (c.r(a2, byArray2) != 2) {
            throw new ph("Could not read enough bytes for generalPurposeFlags");
        }
        nb nb3 = nb2;
        nb nb4 = nb2;
        nb4.y(wa.r(byArray2[0], 0));
        nb4.r(wa.r(byArray2[0], 3));
        nb3.z(wa.r(byArray2[1], 3));
        nb3.r((byte[])byArray2.clone());
        nb nb5 = nb2;
        nb5.r(cb.r(a4.j.y(a2)));
        nb5.z((long)a4.j.r(a2));
        nb nb6 = nb2;
        c.r(a2, byArray);
        nb6.y(a4.j.r(byArray, 0));
        nb6.y((byte[])byArray.clone());
        nb nb7 = nb2;
        hl hl3 = a4;
        nb2.r(a4.j.r(a2, 4));
        nb2.h(hl3.j.r(a2, 4));
        int n2 = hl3.j.y(a2);
        nb7.r(n2);
        nb7.y(a4.j.y(a2));
        if (n2 > 0) {
            byte[] byArray3 = new byte[n2];
            c.r(a2, byArray3);
            a3 = bg.r(byArray3, nb2.y(), (Charset)a3);
            if (a3 == null) {
                throw new ph("file name is null, cannot assign file name to local file header");
            }
            if (((String)a3).contains(new StringBuilder().insert(0, ":").append(System.getProperty("file.separator")).toString())) {
                Object object = a3;
                a3 = ((String)object).substring(((String)object).indexOf(":" + System.getProperty("file.separator")) + 2);
            }
            Object object = a3;
            nb2.r((String)object);
            nb2.h(((String)object).endsWith("/") || ((String)a3).endsWith("\\"));
            hl2 = a4;
        } else {
            nb2.r((String)null);
            hl2 = a4;
        }
        hl2.r(a2, nb2);
        nb nb8 = nb2;
        hl hl4 = a4;
        hl4.y(nb2, hl4.j);
        a4.r(nb8, hl4.j);
        if (nb8.r()) {
            if (nb2.r() == yb.j) {
                return nb2;
            }
            nb nb9 = nb2;
            if (BigInteger.valueOf(nb2.y()[0]).testBit(6)) {
                nb9.r(yb.c);
                return nb2;
            }
            nb9.r(yb.m);
        }
        return nb2;
    }

    private /* synthetic */ void r(RandomAccessFile a2, pb a3) throws IOException {
        hl a4;
        int n2 = a3.z();
        if (n2 <= 0) {
            return;
        }
        a3.r(a4.r(a2, n2));
    }

    private /* synthetic */ List<eb> r(RandomAccessFile a22, int a3) throws IOException {
        if (a3 < 4) {
            if (a3 > 0) {
                a22.skipBytes(a3);
            }
            return null;
        }
        byte[] byArray = new byte[a3];
        a22.read(byArray);
        try {
            hl a4;
            return a4.r(byArray, a3);
        }
        catch (Exception a22) {
            return Collections.emptyList();
        }
    }

    private /* synthetic */ long r(RandomAccessFile a2) throws IOException {
        byte[] byArray = new byte[4096];
        long l2 = a2.getFilePointer();
        do {
            hl a3;
            int n2;
            long l3;
            if ((l3 = l2 - (long)(n2 = l2 > 4096L ? 4096 : (int)l2) + 4L) == 4L) {
                l3 = 0L;
            }
            a3.y(a2, l3);
            a2.read(byArray, 0, n2);
            l2 = l3;
            int n3 = 0;
            int n4 = n3;
            while (n4 < n2 - 3) {
                if ((long)a3.j.y(byArray, n3) == wm.m.r()) {
                    return l2 + (long)n3;
                }
                n4 = ++n3;
            }
        } while (l2 > 0L);
        throw new ph("Zip headers not found. Probably not a zip file");
    }

    private /* synthetic */ String r(RandomAccessFile a2, int a32, Charset a4) {
        if (a32 <= 0) {
            return null;
        }
        try {
            byte[] a32 = new byte[a32];
            a2.readFully(a32);
            return new String(a32, a4);
        }
        catch (IOException a32) {
            return null;
        }
    }

    /*
     * Unable to fully structure code
     */
    public kb r(RandomAccessFile a, Charset a) throws IOException {
        if (a.length() < 22L) {
            throw new ph("Zip file size less than minimum expected zip file size. Probably not a zip file or a corrupted zip file");
        }
        a.m = new kb();
        try {
            v0 = a;
            a.m.r(v0.r(a, v0.j, a));
        }
        catch (ph var3_3) {
            throw var3_3;
        }
        catch (IOException var3_4) {
            throw new ph("Zip headers not found. Probably not a zip file or a corrupted zip file", var3_4);
        }
        if (a.m.r().r() == 0) {
            return a.m;
        }
        v1 = a;
        v2 = a;
        v1.m.r(v2.r(a, a.j, v2.m.r().y()));
        if (!v1.m.r()) ** GOTO lbl26
        v3 = a;
        a.m.r(v3.r(a, a.j));
        if (v3.m.r() != null && a.m.r().y() > 0) {
            v4 = a;
            v5 = v4;
            v4.m.y(true);
        } else {
            a.m.y(false);
lbl26:
            // 2 sources

            v5 = a;
        }
        v6 = a;
        v5.m.r(v6.r(a, v6.j, a));
        return a.m;
    }

    private /* synthetic */ void y(RandomAccessFile a2, long a3) throws IOException {
        if (a2 instanceof bc) {
            ((bc)a2).r(a3);
            return;
        }
        a2.seek(a3);
    }

    /*
     * Unable to fully structure code
     */
    private /* synthetic */ yc r(RandomAccessFile a, ra a, Charset a) throws IOException {
        var4_4 = new yc();
        var5_5 = new ArrayList<pb>();
        v0 = a;
        var6_6 = bg.r(v0.m);
        var8_8 = v0.r(v0.m);
        a.seek(var6_6);
        var6_7 = new byte[2];
        var7_9 = new byte[4];
        v1 = var10_10 = 0;
        while ((long)v1 < var8_8) {
            var11_12 = new pb();
            if ((long)a.y(a) != wm.z.r()) {
                throw new ph(new StringBuilder().insert(0, "Expected central directory entry not found (#").append(var10_10 + 1).append(")").toString());
            }
            v2 = var11_12;
            v3 = var11_12;
            v4 = a;
            var11_12.r(wm.z);
            var11_12.h(v4.r(a));
            v3.z(v4.r(a));
            var12_13 = new byte[2];
            a.readFully(var12_13);
            v2.y(wa.r(var12_13[0], 0));
            v3.r(wa.r(var12_13[0], 3));
            v2.z(wa.r(var12_13[1], 3));
            v2.r((byte[])var12_13.clone());
            v5 = var11_12;
            v6 = a;
            v7 = var11_12;
            v8 = a;
            v9 = a;
            v10 = var11_12;
            v11 = var11_12;
            v11.r(cb.r(a.r(a)));
            v11.z((long)a.y(a));
            a.readFully(var7_9);
            v10.y(a.r(var7_9, 0));
            v10.y(var7_9);
            var11_12.r(a.r(v9, 4));
            var11_12.h(v8.r(v9, 4));
            var12_14 = v8.r(a);
            v7.r(var12_14);
            v7.y(a.r(a));
            var13_16 = v6.r(a);
            var11_12.x(var13_16);
            v5.s(v6.r(a));
            a.readFully(var6_7);
            v5.z((byte[])var6_7.clone());
            a.readFully(var7_9);
            var11_12.h((byte[])var7_9.clone());
            a.readFully(var7_9);
            var11_12.x(a.r(var7_9, 0));
            if (var12_14 > 0) {
                var14_17 = new byte[var12_14];
                a.readFully(var14_17);
                var12_15 = bg.r(var14_17, var11_12.y(), a);
                if (var12_15.contains(":\\")) {
                    v12 = var12_15;
                    var12_15 = v12.substring(v12.indexOf(":\\") + 2);
                }
                v13 = var12_15;
                var11_12.r(v13);
                var11_12.h(v13.endsWith("/") != false || var12_15.endsWith("\\") != false);
                v14 = a;
            } else {
                var11_12.r((String)null);
                v14 = a;
            }
            v14.r(a, (pb)var11_12);
            v15 = a;
            v15.r((pb)var11_12, a);
            v15.y((pb)var11_12, a);
            if (var13_16 > 0) {
                var14_17 = new byte[var13_16];
                a.readFully(var14_17);
                v16 = var11_12;
                v16.y(bg.r(var14_17, v16.y(), a));
            }
            if (!var11_12.r()) ** GOTO lbl92
            v17 = var11_12;
            if (var11_12.r() != null) {
                v17.r(yb.j);
                v18 = var5_5;
            } else {
                v17.r(yb.m);
lbl92:
                // 2 sources

                v18 = var5_5;
            }
            v18.add((pb)var11_12);
            v1 = ++var10_10;
        }
        var4_4.r(var5_5);
        var10_11 = new rc();
        if ((long)a.y(a) == wm.e.r()) {
            v19 = var10_11;
            v19.r(wm.e);
            v19.r(a.r(a));
            if (v19.r() > 0) {
                var11_12 = new byte[var10_11.r()];
                a.readFully((byte[])var11_12);
                var10_11.r(new String((byte[])var11_12));
            }
        }
        return var4_4;
    }

    private /* synthetic */ void r(RandomAccessFile a2, long a3) throws IOException {
        hl a4;
        a4.y(a2, a3 - 4L - 8L - 4L - 4L);
    }

    private /* synthetic */ void r(nb a2, ra a3) throws ph {
        hl a4;
        if (a2.r() == null || a2.r().size() <= 0) {
            return;
        }
        a3 = a4.r(a2.r(), (ra)a3);
        if (a3 != null) {
            nb nb2 = a2;
            nb2.r((wc)a3);
            nb2.r(yb.j);
        }
    }

    /*
     * Enabled aggressive block sorting
     */
    private /* synthetic */ wc r(List<eb> a2, ra a3) throws ph {
        wc wc2;
        Object object;
        if (a2 == null) {
            return null;
        }
        a2 = a2.iterator();
        block0: do {
            Iterator iterator = a2;
            while (true) {
                if (!iterator.hasNext()) {
                    return null;
                }
                object = (eb)a2.next();
                if (object != null) continue block0;
                iterator = a2;
            }
        } while (((eb)object).r() != wm.b.r());
        if (((eb)object).r() == null) {
            throw new ph("corrupt AES extra data records");
        }
        wc wc3 = wc2 = new wc();
        wc2.r(wm.b);
        wc3.r(((eb)object).r());
        object = ((eb)object).r();
        wc3.r(lb.r(a3.r((byte[])object, 0)));
        byte[] byArray = new byte[2];
        System.arraycopy(object, 2, byArray, 0, 2);
        wc2.r(new String(byArray));
        wc2.r(vb.r(object[4] & 0xFF));
        wc2.r(cb.r(a3.r((byte[])object, 5)));
        return wc2;
    }

    public tc r(InputStream a2, boolean a3) throws IOException {
        boolean bl;
        hl a4;
        tc tc2 = new tc();
        byte[] byArray = new byte[4];
        c.r(a2, byArray);
        long l2 = a4.j.r(byArray, 0);
        if (l2 == wm.w.r()) {
            bl = a3;
            tc2.r(wm.w);
            c.r(a2, byArray);
            tc2.z(a4.j.r(byArray, 0));
        } else {
            tc2.z(l2);
            bl = a3;
        }
        if (bl) {
            tc tc3 = tc2;
            tc3.r(a4.j.r(a2));
            tc3.y(a4.j.r(a2));
            return tc3;
        }
        tc tc4 = tc2;
        tc4.r(a4.j.r(a2));
        tc4.y(a4.j.r(a2));
        return tc2;
    }

    private /* synthetic */ db r(RandomAccessFile a2, ra a3) throws IOException {
        hl a4;
        if (a4.m.r() == null) {
            throw new ph("invalid zip64 end of central directory locator");
        }
        long l2 = a4.m.r().r();
        if (l2 < 0L) {
            throw new ph("invalid offset for start of end of central directory record");
        }
        a2.seek(l2);
        db db2 = new db();
        if ((long)((ra)a3).y(a2) != wm.v.r()) {
            throw new ph("invalid signature for zip64 end of central directory record");
        }
        db db3 = db2;
        ra ra2 = a3;
        RandomAccessFile randomAccessFile = a2;
        db db4 = db2;
        ra ra3 = a3;
        RandomAccessFile randomAccessFile2 = a2;
        db db5 = db2;
        db5.r(wm.v);
        db5.r(((ra)a3).r(a2));
        db2.z(((ra)a3).r(randomAccessFile2));
        db2.y(ra3.r(randomAccessFile2));
        db4.h(ra3.y(a2));
        db4.r(((ra)a3).y(a2));
        db2.x(((ra)a3).r(randomAccessFile));
        db2.y(ra2.r(randomAccessFile));
        db3.z(ra2.r(a2));
        db3.h(((ra)a3).r(a2));
        l2 = db2.h() - 44L;
        if (l2 > 0L) {
            Object object = a3 = (Object)new byte[(int)l2];
            a2.readFully((byte[])object);
            db2.r((byte[])object);
        }
        return db2;
    }

    private /* synthetic */ List<eb> r(byte[] a2, int a3) {
        int n2 = 0;
        ArrayList<eb> arrayList = new ArrayList<eb>();
        int n3 = n2;
        while (n3 < a3) {
            hl a4;
            eb eb2;
            eb eb3 = eb2 = new eb();
            int n4 = n2;
            eb3.r((long)a4.j.r(a2, n4));
            int n5 = a4.j.r(a2, n2 += 2);
            n2 += 2;
            int n6 = n5;
            eb3.r(n5);
            if (n6 > 0) {
                byte[] byArray = new byte[n6];
                System.arraycopy(a2, n2, byArray, 0, n6);
                eb2.r(byArray);
            }
            n3 = n2 = n2 + n6;
            arrayList.add(eb2);
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    public hl() {
        hl a2;
        hl hl2 = a2;
        a2.j = new ra();
        a2.s = new byte[4];
    }

    /*
     * Enabled aggressive block sorting
     */
    private /* synthetic */ wb r(List<eb> a2, ra a3, long a4, long a5, long a6, int a7) {
        eb eb2;
        a2 = a2.iterator();
        block0: do {
            Iterator iterator = a2;
            while (true) {
                if (!iterator.hasNext()) {
                    return null;
                }
                eb2 = (eb)a2.next();
                if (eb2 != null) continue block0;
                iterator = a2;
            }
        } while (wm.t.r() != eb2.r());
        wb wb2 = new wb();
        eb eb3 = eb2;
        byte[] byArray = eb3.r();
        if (eb3.r() <= 0) {
            return null;
        }
        int n2 = 0;
        if (0 < eb2.r() && a4 == 0xFFFFFFFFL) {
            int n3 = n2;
            n2 += 8;
            wb2.z(a3.r(byArray, n3));
        }
        if (n2 < eb2.r() && a5 == 0xFFFFFFFFL) {
            int n4 = n2;
            n2 += 8;
            wb2.y(a3.r(byArray, n4));
        }
        if (n2 < eb2.r() && a6 == 0xFFFFFFFFL) {
            int n5 = n2;
            n2 += 8;
            wb2.r(a3.r(byArray, n5));
        }
        if (n2 < eb2.r() && a7 == 65535) {
            wb2.y(a3.y(byArray, n2));
        }
        return wb2;
    }

    private /* synthetic */ fb r(RandomAccessFile a2, ra a3, long a4) throws IOException {
        hl a5;
        fb fb2 = new fb();
        RandomAccessFile randomAccessFile = a2;
        a5.r(randomAccessFile, a4);
        if ((long)a3.y(randomAccessFile) != wm.r.r()) {
            a5.m.z(false);
            return null;
        }
        fb fb3 = fb2;
        a5.m.z(true);
        fb3.r(wm.r);
        fb3.r(a3.y(a2));
        fb fb4 = fb2;
        fb4.r(a3.r(a2));
        fb4.y(a3.y(a2));
        return fb4;
    }

    private /* synthetic */ long r(kb a2) {
        if (a2.r()) {
            return a2.r().z();
        }
        return a2.r().r();
    }

    private /* synthetic */ gb r(RandomAccessFile a2, ra a32, Charset a4) throws IOException {
        hl a5;
        long l2 = a2.length() - 22L;
        a5.y(a2, l2);
        if ((long)a32.y(a2) != wm.m.r()) {
            RandomAccessFile randomAccessFile = a2;
            l2 = a5.r(randomAccessFile);
            randomAccessFile.seek(l2 + 4L);
        }
        gb gb2 = new gb();
        hl hl2 = a5;
        ra ra2 = a32;
        hl hl3 = a5;
        gb gb3 = gb2;
        ra ra3 = a32;
        RandomAccessFile randomAccessFile = a2;
        gb gb4 = gb2;
        gb2.r(wm.m);
        gb4.z(a32.r(a2));
        gb4.y(a32.r(a2));
        gb2.r(a32.r(randomAccessFile));
        gb2.x(ra3.r(randomAccessFile));
        gb3.h(ra3.y(a2));
        gb3.y(l2);
        a2.readFully(hl3.s);
        gb2.r(ra2.r(hl3.s, 0));
        int a32 = ra2.r(a2);
        gb2.r(hl2.r(a2, a32, a4));
        hl2.m.y(gb2.z() > 0);
        return gb2;
    }
}

