/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.c;
import eos.moe.armourers.db;
import eos.moe.armourers.eb;
import eos.moe.armourers.fb;
import eos.moe.armourers.gc;
import eos.moe.armourers.k;
import eos.moe.armourers.kb;
import eos.moe.armourers.mb;
import eos.moe.armourers.nb;
import eos.moe.armourers.pb;
import eos.moe.armourers.pc;
import eos.moe.armourers.ph;
import eos.moe.armourers.qb;
import eos.moe.armourers.ra;
import eos.moe.armourers.wc;
import eos.moe.armourers.wm;
import eos.moe.armourers.ya;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;

public class vl {
    private static final short l = 28;
    private byte[] c;
    private ra v;
    private static final short s = 16;
    private static final short m = 11;
    private byte[] j;

    private /* synthetic */ boolean r(pb a2) {
        return a2.z() >= 0xFFFFFFFFL || a2.r() >= 0xFFFFFFFFL || a2.s() >= 0xFFFFFFFFL || a2.s() >= 65535;
    }

    private /* synthetic */ boolean r(OutputStream a2) {
        if (a2 instanceof pc) {
            return ((pc)a2).r();
        }
        if (a2 instanceof gc) {
            return ((gc)a2).r();
        }
        return false;
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(kb a2222222, nb a3, OutputStream a4, Charset a5222222) throws IOException {
        Throwable throwable;
        ByteArrayOutputStream byteArrayOutputStream;
        block20: {
            boolean bl;
            vl a6;
            byteArrayOutputStream = new ByteArrayOutputStream();
            throwable = null;
            ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
            vl vl2 = a6;
            a6.v.r((OutputStream)byteArrayOutputStream, (int)a3.r().r());
            vl2.v.y(byteArrayOutputStream, a3.y());
            byteArrayOutputStream2.write(a3.y());
            vl2.v.y(byteArrayOutputStream, a3.r().r());
            vl vl3 = a6;
            a6.v.r(vl3.j, 0, a3.y());
            byteArrayOutputStream2.write(vl3.j, 0, 4);
            a6.v.r(a6.j, 0, a3.h());
            byteArrayOutputStream.write(a6.j, 0, 4);
            boolean bl2 = bl = a3.z() >= 0xFFFFFFFFL || a3.r() >= 0xFFFFFFFFL;
            if (bl) {
                vl vl4 = a6;
                vl4.v.r(vl4.j, 0, 0xFFFFFFFFL);
                ByteArrayOutputStream byteArrayOutputStream3 = byteArrayOutputStream;
                byteArrayOutputStream3.write(a6.j, 0, 4);
                byteArrayOutputStream3.write(vl4.j, 0, 4);
                ((kb)a2222222).z(true);
                a3.x(true);
            } else {
                vl vl5 = a6;
                a6.v.r(vl5.j, 0, a3.z());
                byteArrayOutputStream.write(vl5.j, 0, 4);
                a6.v.r(a6.j, 0, a3.r());
                byteArrayOutputStream.write(a6.j, 0, 4);
                a3.x(false);
            }
            a2222222 = new byte[0];
            if (eos.moe.armourers.c.r(a3.r())) {
                a2222222 = a3.r().getBytes(a5222222);
            }
            a6.v.y(byteArrayOutputStream, ((Object)a2222222).length);
            int a5222222 = 0;
            if (bl) {
                a5222222 += 20;
            }
            if (a3.r() != null) {
                a5222222 += 11;
            }
            a6.v.y(byteArrayOutputStream, a5222222);
            if (((Object)a2222222).length > 0) {
                byteArrayOutputStream.write((byte[])a2222222);
            }
            if (bl) {
                vl vl6 = a6;
                vl6.v.y(byteArrayOutputStream, (int)wm.t.r());
                vl6.v.y(byteArrayOutputStream, 16);
                vl6.v.r((OutputStream)byteArrayOutputStream, a3.r());
                vl6.v.r((OutputStream)byteArrayOutputStream, a3.z());
            }
            if (a3.r() != null) {
                a2222222 = a3.r();
                ByteArrayOutputStream byteArrayOutputStream4 = byteArrayOutputStream;
                vl vl7 = a6;
                vl vl8 = a6;
                vl8.v.y(byteArrayOutputStream, (int)((mb)a2222222).r().r());
                vl8.v.y(byteArrayOutputStream, ((wc)a2222222).r());
                vl7.v.y(byteArrayOutputStream, ((wc)a2222222).r().r());
                byteArrayOutputStream4.write(((wc)a2222222).r().getBytes());
                byte[] byArray = new byte[1];
                byArray[0] = (byte)((wc)a2222222).r().z();
                byteArrayOutputStream4.write(byArray);
                vl7.v.y(byteArrayOutputStream, ((wc)a2222222).r().r());
            }
            a4.write(byteArrayOutputStream.toByteArray());
            if (byteArrayOutputStream == null) return;
            if (throwable == null) break block20;
            try {
                byteArrayOutputStream.close();
                return;
            }
            catch (Throwable throwable2) {
                throwable.addSuppressed(throwable2);
                return;
            }
        }
        byteArrayOutputStream.close();
        return;
        catch (Throwable throwable3) {
            try {
                throwable = throwable3;
                throw throwable;
            }
            catch (Throwable throwable4) {
                Throwable throwable5;
                if (byteArrayOutputStream != null) {
                    if (throwable != null) {
                        try {
                            byteArrayOutputStream.close();
                            throwable5 = throwable4;
                            throw throwable5;
                        }
                        catch (Throwable a2222222) {
                            throwable5 = throwable4;
                            throwable.addSuppressed(a2222222);
                            throw throwable5;
                        }
                    }
                    byteArrayOutputStream.close();
                }
                throwable5 = throwable4;
                throw throwable5;
            }
        }
    }

    private /* synthetic */ void r(kb a2, ByteArrayOutputStream a3, ra a4, Charset a5) throws ph {
        Iterator<pb> iterator;
        if (a2.r() == null || a2.r().r() == null || a2.r().r().size() <= 0) {
            return;
        }
        Iterator<pb> iterator2 = iterator = a2.r().r().iterator();
        while (iterator2.hasNext()) {
            vl a6;
            pb pb2 = iterator.next();
            iterator2 = iterator;
            a6.r(a2, pb2, a3, a4, a5);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(nb a2222222222222, OutputStream a3) throws IOException {
        Throwable throwable;
        ByteArrayOutputStream byteArrayOutputStream;
        block14: {
            OutputStream outputStream;
            vl a4;
            if (a2222222222222 == null) throw new ph("input parameters is null, cannot write extended local header");
            if (a3 == null) {
                throw new ph("input parameters is null, cannot write extended local header");
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            throwable = null;
            vl vl2 = a4;
            vl2.v.r((OutputStream)byteArrayOutputStream, (int)wm.w.r());
            vl2.v.r(a4.j, 0, a2222222222222.h());
            byteArrayOutputStream.write(a4.j, 0, 4);
            if (a2222222222222.x()) {
                outputStream = a3;
                vl vl3 = a4;
                vl3.v.r((OutputStream)byteArrayOutputStream, a2222222222222.z());
                vl3.v.r((OutputStream)byteArrayOutputStream, a2222222222222.r());
            } else {
                vl vl4 = a4;
                a4.v.r(vl4.j, 0, a2222222222222.z());
                byteArrayOutputStream.write(vl4.j, 0, 4);
                a4.v.r(a4.j, 0, a2222222222222.r());
                outputStream = a3;
                byteArrayOutputStream.write(a4.j, 0, 4);
            }
            outputStream.write(byteArrayOutputStream.toByteArray());
            if (byteArrayOutputStream == null) return;
            if (throwable == null) break block14;
            try {
                byteArrayOutputStream.close();
                return;
            }
            catch (Throwable a2222222222222) {
                throwable.addSuppressed(a2222222222222);
                return;
            }
        }
        byteArrayOutputStream.close();
        return;
        catch (Throwable a2222222222222) {
            try {
                throwable = a2222222222222;
                throw throwable;
            }
            catch (Throwable throwable2) {
                Throwable throwable3;
                if (byteArrayOutputStream != null) {
                    if (throwable != null) {
                        try {
                            byteArrayOutputStream.close();
                            throwable3 = throwable2;
                            throw throwable3;
                        }
                        catch (Throwable a2222222222222) {
                            throwable3 = throwable2;
                            throwable.addSuppressed(a2222222222222);
                            throw throwable3;
                        }
                    }
                    byteArrayOutputStream.close();
                }
                throwable3 = throwable2;
                throw throwable3;
            }
        }
    }

    private /* synthetic */ int r(pb a2, boolean a32) throws IOException {
        int n2 = 0;
        if (a32) {
            n2 += 32;
        }
        if (((qb)a2).r() != null) {
            n2 += 11;
        }
        if (((qb)a2).r() != null) {
            a2 = ((qb)a2).r().iterator();
            block0: while (true) {
                Object object = a2;
                while (object.hasNext()) {
                    eb a32 = (eb)a2.next();
                    if (a32.r() == wm.b.r()) continue block0;
                    if (a32.r() == wm.t.r()) {
                        object = a2;
                        continue;
                    }
                    n2 += 4 + a32.r();
                    object = a2;
                }
                break;
            }
        }
        return n2;
    }

    public vl() {
        vl a2;
        vl vl2 = a2;
        vl vl3 = a2;
        vl3.v = new ra();
        vl2.j = new byte[8];
        vl2.c = new byte[4];
    }

    private /* synthetic */ void r(kb a2, int a3, long a4, ByteArrayOutputStream a5, ra a6) throws IOException {
        long l2;
        ra ra2;
        byte[] byArray = new byte[2];
        byArray[0] = 0;
        byArray[1] = 0;
        byte[] byArray2 = byArray;
        a6.r((OutputStream)a5, (int)wm.v.r());
        a6.r((OutputStream)a5, 44L);
        if (a2.r() != null && a2.r().r() != null && a2.r().r().size() > 0) {
            a6.y(a5, a2.r().r().get(0).x());
            a6.y(a5, a2.r().r().get(0).y());
            ra2 = a6;
        } else {
            ByteArrayOutputStream byteArrayOutputStream = a5;
            byteArrayOutputStream.write(byArray2);
            byteArrayOutputStream.write(byArray2);
            ra2 = a6;
        }
        ra2.r((OutputStream)a5, a2.r().z());
        kb kb2 = a2;
        a6.r((OutputStream)a5, kb2.r().y());
        long l3 = l2 = (long)kb2.r().r().size();
        if (kb2.z()) {
            vl a7;
            l3 = a7.r(a2.r().r(), a2.r().z());
        }
        ByteArrayOutputStream byteArrayOutputStream = a5;
        ra ra3 = a6;
        a6.r((OutputStream)a5, l3);
        ra3.r((OutputStream)a5, l2);
        ra3.r((OutputStream)byteArrayOutputStream, (long)a3);
        a6.r((OutputStream)byteArrayOutputStream, a4);
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void y(kb a22, OutputStream a3, Charset a4) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream;
        block14: {
            if (a22 == null) throw new ph("input parameters is null, cannot finalize zip file without validations");
            if (a3 == null) {
                throw new ph("input parameters is null, cannot finalize zip file without validations");
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            Throwable throwable = null;
            try {
                vl a5;
                long l2 = a22.r().r();
                ByteArrayOutputStream byteArrayOutputStream2 = byteArrayOutputStream;
                a5.r(a22, byteArrayOutputStream2, a5.v, a4);
                int n2 = byteArrayOutputStream2.size();
                if (a22.r() || l2 >= 0xFFFFFFFFL || a22.r().r().size() >= 65535) {
                    if (a22.r() == null) {
                        a22.r(new db());
                    }
                    if (a22.r() == null) {
                        a22.r(new fb());
                    }
                    a22.r().r(l2 + (long)n2);
                    vl vl2 = a5;
                    vl2.r(a22, n2, l2, byteArrayOutputStream, a5.v);
                    vl2.r(a22, byteArrayOutputStream, a5.v);
                }
                kb kb2 = a22;
                a5.r(kb2, n2, l2, byteArrayOutputStream, a5.v, a4);
                a5.r(kb2, a3, byteArrayOutputStream.toByteArray(), a4);
                if (byteArrayOutputStream == null) return;
                if (throwable == null) break block14;
            }
            catch (Throwable throwable2) {
                try {
                    throwable = throwable2;
                    throw throwable;
                }
                catch (Throwable throwable3) {
                    Throwable throwable4;
                    if (byteArrayOutputStream != null) {
                        if (throwable != null) {
                            try {
                                byteArrayOutputStream.close();
                                throwable4 = throwable3;
                                throw throwable4;
                            }
                            catch (Throwable a22) {
                                throwable4 = throwable3;
                                throwable.addSuppressed(a22);
                                throw throwable4;
                            }
                        }
                        byteArrayOutputStream.close();
                    }
                    throwable4 = throwable3;
                    throw throwable4;
                }
            }
            try {
                byteArrayOutputStream.close();
                return;
            }
            catch (Throwable throwable5) {
                throwable.addSuppressed(throwable5);
                return;
            }
        }
        byteArrayOutputStream.close();
    }

    private /* synthetic */ void r(kb a2, int a3, long a4, ByteArrayOutputStream a5, ra a6, Charset a7) throws IOException {
        kb kb2;
        long l2;
        byte[] byArray = new byte[8];
        kb kb3 = a2;
        ra ra2 = a6;
        ra2.r((OutputStream)a5, (int)wm.m.r());
        ra2.y(a5, ((kb)a2).r().z());
        a6.y(a5, kb3.r().y());
        long l3 = l2 = (long)kb3.r().r().size();
        if (kb3.z()) {
            vl a8;
            l3 = a8.r(((kb)a2).r().r(), ((kb)a2).r().z());
        }
        if (l3 > 65535L) {
            l3 = 65535L;
        }
        a6.y(a5, (int)l3);
        if (l2 > 65535L) {
            l2 = 65535L;
        }
        ByteArrayOutputStream byteArrayOutputStream = a5;
        a6.y(byteArrayOutputStream, (int)l2);
        a6.r((OutputStream)byteArrayOutputStream, a3);
        if (a4 > 0xFFFFFFFFL) {
            kb2 = a2;
            a6.r(byArray, 0, 0xFFFFFFFFL);
            a5.write(byArray, 0, 4);
        } else {
            a6.r(byArray, 0, a4);
            kb2 = a2;
            a5.write(byArray, 0, 4);
        }
        a2 = kb2.r().r();
        if (eos.moe.armourers.c.r((String)a2)) {
            a2 = ((String)a2).getBytes(a7);
            a6.y(a5, ((Object)a2).length);
            a5.write((byte[])a2);
            return;
        }
        a6.y(a5, 0);
    }

    private /* synthetic */ void r(pc a2, pb a3) throws IOException {
        vl a4;
        if (a3.r() >= 0xFFFFFFFFL) {
            vl vl2 = a4;
            vl2.v.r(vl2.j, 0, 0xFFFFFFFFL);
            pc pc2 = a2;
            pc2.write(a4.j, 0, 4);
            a2.write(a4.j, 0, 4);
            int n2 = 4 + a3.r() + 2 + 2;
            if (pc2.r(n2) != n2) {
                throw new ph(new StringBuilder().insert(0, "Unable to skip ").append(n2).append(" bytes to update LFH").toString());
            }
            vl vl3 = a4;
            vl3.v.r((OutputStream)a2, a3.r());
            vl3.v.r((OutputStream)a2, a3.z());
            return;
        }
        vl vl4 = a4;
        a4.v.r(vl4.j, 0, a3.z());
        a2.write(vl4.j, 0, 4);
        a4.v.r(a4.j, 0, a3.r());
        a2.write(a4.j, 0, 4);
    }

    private /* synthetic */ void r(kb a2, OutputStream a3) throws IOException {
        int n2 = 0;
        if (a3 instanceof k) {
            a2.r().r(((k)((Object)a3)).r());
            n2 = ((k)((Object)a3)).r();
        }
        if (a2.r()) {
            if (a2.r() == null) {
                a2.r(new db());
            }
            if (a2.r() == null) {
                a2.r(new fb());
            }
            kb kb2 = a2;
            kb kb3 = a2;
            kb2.r().h(kb3.r().r());
            kb3.r().r(n2);
            kb2.r().y(n2 + 1);
        }
        kb kb4 = a2;
        kb4.r().z(n2);
        kb4.r().y(n2);
    }

    public void r(pb a2, kb a3, pc a4) throws IOException {
        vl a5;
        pc pc2;
        pc pc3;
        if (a2 == null || a3 == null) {
            throw new ph("invalid input parameters, cannot update local file header");
        }
        boolean bl = false;
        if (a2.s() != a4.r()) {
            kb kb2 = a3;
            String string = kb2.r().getParent();
            String string2 = ya.y(kb2.r().getName());
            a3 = new StringBuilder().insert(0, string).append(System.getProperty("file.separator")).toString();
            a3 = a2.s() < 9 ? new StringBuilder().insert(0, (String)a3).append(string2).append(".z0").append(a2.s() + 1).toString() : new StringBuilder().insert(0, (String)a3).append(string2).append(".z").append(a2.s() + 1).toString();
            pc3 = new pc(new File((String)a3));
            bl = true;
            pc2 = pc3;
        } else {
            pc2 = pc3 = a4;
        }
        long l2 = pc2.r();
        pc pc4 = pc3;
        vl vl2 = a5;
        pc3.r(a2.s() + 14L);
        a5.v.r(a5.j, 0, a2.h());
        pc4.write(vl2.j, 0, 4);
        vl2.r(pc4, a2);
        if (bl) {
            pc3.close();
            return;
        }
        a4.r(l2);
    }

    private /* synthetic */ int r(OutputStream a2) {
        if (a2 instanceof pc) {
            return ((pc)a2).r();
        }
        return ((gc)a2).r();
    }

    private /* synthetic */ long r(List<pb> a2, int a3) throws ph {
        if (a2 == null) {
            throw new ph("file headers are null, cannot calculate number of entries on this disk");
        }
        int n2 = 0;
        a2 = a2.iterator();
        while (a2.hasNext()) {
            if (((pb)a2.next()).s() != a3) continue;
            ++n2;
        }
        return n2;
    }

    private /* synthetic */ long r(kb a2) {
        if (a2.r() && a2.r() != null && a2.r().x() != -1L) {
            return a2.r().x();
        }
        return a2.r().r();
    }

    private /* synthetic */ void r(kb a2, OutputStream a3, byte[] a4, Charset a5) throws IOException {
        if (a4 == null) {
            throw new ph("invalid buff to write as zip headers");
        }
        if (a3 instanceof gc && ((gc)a3).r(a4.length)) {
            vl a6;
            a6.r(a2, a3, a5);
            return;
        }
        a3.write(a4);
    }

    private /* synthetic */ void r(kb a2, ByteArrayOutputStream a3, ra a4) throws IOException {
        ra ra2 = a4;
        ByteArrayOutputStream byteArrayOutputStream = a3;
        a4.r((OutputStream)a3, (int)wm.r.r());
        a4.r((OutputStream)byteArrayOutputStream, a2.r().r());
        ra2.r((OutputStream)byteArrayOutputStream, a2.r().r());
        ra2.r((OutputStream)a3, a2.r().y());
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private /* synthetic */ void r(kb a2, pb a3, ByteArrayOutputStream a4, ra a5, Charset a6) throws ph {
        if (a3 == null) {
            throw new ph("input parameters is null, cannot write local file header");
        }
        try {
            ByteArrayOutputStream byteArrayOutputStream;
            vl vl2;
            vl a7;
            byte[] byArray = new byte[2];
            byArray[0] = 0;
            byArray[1] = 0;
            byte[] byArray2 = byArray;
            boolean bl = a7.r(a3);
            ByteArrayOutputStream byteArrayOutputStream2 = a4;
            vl vl3 = a7;
            ra ra2 = a5;
            ByteArrayOutputStream byteArrayOutputStream3 = a4;
            pb pb2 = a3;
            a5.r((OutputStream)a4, (int)pb2.r().r());
            a5.y(byteArrayOutputStream3, pb2.x());
            ra2.y(byteArrayOutputStream3, a3.y());
            a4.write(a3.y());
            ra2.y(a4, a3.r().r());
            a5.r(vl3.j, 0, a3.y());
            byteArrayOutputStream2.write(vl3.j, 0, 4);
            a5.r(a7.j, 0, a3.h());
            byteArrayOutputStream2.write(a7.j, 0, 4);
            if (bl) {
                vl vl4 = a7;
                a5.r(vl4.j, 0, 0xFFFFFFFFL);
                ByteArrayOutputStream byteArrayOutputStream4 = a4;
                byteArrayOutputStream4.write(a7.j, 0, 4);
                byteArrayOutputStream4.write(vl4.j, 0, 4);
                ((kb)a2).z(true);
            } else {
                vl vl5 = a7;
                a5.r(a7.j, 0, a3.z());
                a4.write(vl5.j, 0, 4);
                a5.r(vl5.j, 0, a3.r());
                a4.write(a7.j, 0, 4);
            }
            byte[] byArray3 = new byte[]{};
            if (eos.moe.armourers.c.r(a3.r())) {
                byArray3 = a3.r().getBytes(a6);
            }
            a5.y(a4, byArray3.length);
            byte[] byArray4 = new byte[4];
            if (bl) {
                vl vl6 = a7;
                vl2 = vl6;
                a5.r(vl6.j, 0, 0xFFFFFFFFL);
                System.arraycopy(vl6.j, 0, byArray4, 0, 4);
            } else {
                a5.r(a7.j, 0, a3.s());
                vl vl7 = a7;
                vl2 = vl7;
                System.arraycopy(vl7.j, 0, byArray4, 0, 4);
            }
            int n2 = vl2.r(a3, bl);
            a5.y(a4, n2);
            String string = a3.y();
            byte[] byArray5 = new byte[]{};
            if (eos.moe.armourers.c.r(string)) {
                byArray5 = string.getBytes(a6);
            }
            a5.y(a4, byArray5.length);
            ra ra3 = a5;
            if (bl) {
                ra3.y(a7.c, 0, 65535);
                ByteArrayOutputStream byteArrayOutputStream5 = a4;
                byteArrayOutputStream = byteArrayOutputStream5;
                byteArrayOutputStream5.write(a7.c, 0, 2);
            } else {
                ra3.y(a4, a3.s());
                byteArrayOutputStream = a4;
            }
            byteArrayOutputStream.write(byArray2);
            ByteArrayOutputStream byteArrayOutputStream6 = a4;
            byteArrayOutputStream6.write(a3.z());
            byteArrayOutputStream6.write(byArray4);
            if (byArray3.length > 0) {
                a4.write(byArray3);
            }
            if (bl) {
                ((kb)a2).z(true);
                ra ra4 = a5;
                ByteArrayOutputStream byteArrayOutputStream7 = a4;
                pb pb3 = a3;
                ra ra5 = a5;
                ra5.y(a4, (int)wm.t.r());
                ra5.y(a4, 28);
                a5.r((OutputStream)a4, pb3.r());
                a5.r((OutputStream)byteArrayOutputStream7, pb3.z());
                ra4.r((OutputStream)byteArrayOutputStream7, a3.s());
                ra4.r((OutputStream)a4, a3.s());
            }
            if (a3.r() != null) {
                a2 = a3.r();
                ByteArrayOutputStream byteArrayOutputStream8 = a4;
                ra ra6 = a5;
                ByteArrayOutputStream byteArrayOutputStream9 = a4;
                Object object = a2;
                a5.y(a4, (int)((mb)object).r().r());
                a5.y(byteArrayOutputStream9, ((wc)object).r());
                ra6.y(byteArrayOutputStream9, ((wc)a2).r().r());
                a4.write(((wc)a2).r().getBytes());
                byte[] byArray6 = new byte[1];
                byArray6[0] = (byte)((wc)a2).r().z();
                byteArrayOutputStream8.write(byArray6);
                ra6.y(byteArrayOutputStream8, ((wc)a2).r().r());
            }
            a7.r(a3, (OutputStream)a4);
            if (byArray5.length <= 0) return;
            a4.write(byArray5);
            return;
        }
        catch (Exception exception) {
            throw new ph(exception);
        }
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(kb a2222222, OutputStream a3, Charset a4) throws IOException {
        Throwable throwable;
        ByteArrayOutputStream byteArrayOutputStream;
        block17: {
            vl a5;
            if (a2222222 == null) throw new ph("input parameters is null, cannot finalize zip file");
            if (a3 == null) {
                throw new ph("input parameters is null, cannot finalize zip file");
            }
            byteArrayOutputStream = new ByteArrayOutputStream();
            throwable = null;
            vl vl2 = a5;
            kb kb2 = a2222222;
            a5.r(a2222222, a3);
            long l2 = vl2.r(kb2);
            vl2.r(kb2, byteArrayOutputStream, a5.v, a4);
            int n2 = byteArrayOutputStream.size();
            if (a2222222.r() || l2 >= 0xFFFFFFFFL || a2222222.r().r().size() >= 65535) {
                vl vl3;
                if (a2222222.r() == null) {
                    a2222222.r(new db());
                }
                if (a2222222.r() == null) {
                    a2222222.r(new fb());
                }
                a2222222.r().r(l2 + (long)n2);
                if (a5.r(a3)) {
                    vl vl4 = a5;
                    vl3 = vl4;
                    int n3 = vl4.r(a3);
                    a2222222.r().r(n3);
                    a2222222.r().y(n3 + 1);
                } else {
                    kb kb3 = a2222222;
                    kb3.r().r(0);
                    kb3.r().y(1);
                    vl3 = a5;
                }
                vl3.r(a2222222, n2, l2, byteArrayOutputStream, a5.v);
                vl vl5 = a5;
                vl5.r(a2222222, byteArrayOutputStream, vl5.v);
            }
            kb kb4 = a2222222;
            a5.r(kb4, n2, l2, byteArrayOutputStream, a5.v, a4);
            a5.r(kb4, a3, byteArrayOutputStream.toByteArray(), a4);
            if (byteArrayOutputStream == null) return;
            if (throwable == null) break block17;
            try {
                byteArrayOutputStream.close();
                return;
            }
            catch (Throwable throwable2) {
                throwable.addSuppressed(throwable2);
                return;
            }
        }
        byteArrayOutputStream.close();
        return;
        catch (Throwable throwable3) {
            try {
                throwable = throwable3;
                throw throwable;
            }
            catch (Throwable throwable4) {
                Throwable throwable5;
                if (byteArrayOutputStream != null) {
                    if (throwable != null) {
                        try {
                            byteArrayOutputStream.close();
                            throwable5 = throwable4;
                            throw throwable5;
                        }
                        catch (Throwable a2222222) {
                            throwable5 = throwable4;
                            throwable.addSuppressed(a2222222);
                            throw throwable5;
                        }
                    }
                    byteArrayOutputStream.close();
                }
                throwable5 = throwable4;
                throw throwable5;
            }
        }
    }

    private /* synthetic */ void r(pb a2, OutputStream a3) throws IOException {
        if (((qb)a2).r() == null || ((qb)a2).r().size() == 0) {
            return;
        }
        a2 = ((qb)a2).r().iterator();
        block0: while (true) {
            Object object = a2;
            while (object.hasNext()) {
                vl a4;
                eb eb2 = (eb)a2.next();
                if (eb2.r() == wm.b.r()) continue block0;
                if (eb2.r() == wm.t.r()) {
                    object = a2;
                    continue;
                }
                vl vl2 = a4;
                vl2.v.y(a3, (int)eb2.r());
                vl2.v.y(a3, eb2.r());
                if (eb2.r() <= 0 || eb2.r() == null) continue block0;
                a3.write(eb2.r());
                continue block0;
            }
            break;
        }
    }
}

