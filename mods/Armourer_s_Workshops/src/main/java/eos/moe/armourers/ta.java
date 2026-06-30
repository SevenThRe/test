/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.bg;
import eos.moe.armourers.c;
import eos.moe.armourers.ca;
import eos.moe.armourers.cb;
import eos.moe.armourers.kb;
import eos.moe.armourers.la;
import eos.moe.armourers.oa;
import eos.moe.armourers.ob;
import eos.moe.armourers.pb;
import eos.moe.armourers.pc;
import eos.moe.armourers.ph;
import eos.moe.armourers.qb;
import eos.moe.armourers.rb;
import eos.moe.armourers.sa;
import eos.moe.armourers.ub;
import eos.moe.armourers.vc;
import eos.moe.armourers.vl;
import eos.moe.armourers.wa;
import eos.moe.armourers.xa;
import eos.moe.armourers.ya;
import eos.moe.armourers.yb;
import eos.moe.armourers.zb;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class ta<T>
extends oa<T> {
    private char[] c;
    private byte[] v;
    private vl s;
    private int m;
    private kb j;

    public kb r() {
        ta a2;
        return a2.j;
    }

    private /* synthetic */ void r(File a2, vc a3, zb a4, pc a5) throws IOException {
        ta a6;
        zb zb2;
        zb zb3 = zb2 = new zb(a4);
        zb3.z(a6.r(a4.r(), a2.getName()));
        zb3.w(false);
        zb3.r(cb.s);
        vc vc2 = a3;
        vc2.z(zb2);
        vc2.write(ya.y(a2).getBytes());
        a6.r(a3, a5, a2, true);
    }

    public long r(List<File> a2, zb a3) throws ph {
        long l2 = 0L;
        a2 = a2.iterator();
        block0: while (true) {
            Object object = a2;
            while (object.hasNext()) {
                ta a4;
                Object object2;
                Object object3 = (File)a2.next();
                if (!((File)object3).exists()) {
                    object = a2;
                    continue;
                }
                if (a3.s() && a3.r() == yb.m) {
                    l2 += ((File)object3).length() * 2L;
                    object2 = object3;
                } else {
                    l2 += ((File)object3).length();
                    object2 = object3;
                }
                object3 = ya.r((File)object2, a3);
                object3 = bg.y(a4.r(), (String)object3);
                if (object3 == null) continue block0;
                l2 += a4.r().r().length() - ((qb)object3).z();
                continue block0;
            }
            break;
        }
        return l2;
    }

    private /* synthetic */ List<File> r(List<File> a2, zb a3, rb a4, Charset a5) throws ph {
        ta a6;
        ArrayList<File> arrayList = new ArrayList<File>((Collection<File>)a2);
        if (!a6.j.r().exists()) {
            return arrayList;
        }
        a2 = a2.iterator();
        while (a2.hasNext()) {
            File file = (File)a2.next();
            Object object = ya.r(file, a3);
            if ((object = bg.y(a6.j, (String)object)) == null) continue;
            if (a3.z()) {
                ta ta2 = a6;
                rb rb2 = a4;
                rb2.r(ob.j);
                ta2.r((pb)object, a4, a5);
                ta2.r();
                rb2.r(ob.r);
                continue;
            }
            arrayList.remove(file);
        }
        return arrayList;
    }

    private /* synthetic */ boolean r(zb a2) {
        return ub.m.equals((Object)a2.r()) || ub.j.equals((Object)a2.r());
    }

    private /* synthetic */ zb r(zb a2, File a3, rb a4) throws IOException {
        zb zb2;
        zb zb3 = new zb((zb)a2);
        File file = a3;
        zb3.z(eos.moe.armourers.c.y(file.lastModified()));
        if (file.isDirectory()) {
            zb zb4 = zb3;
            zb2 = zb4;
            zb4.y(0L);
        } else {
            zb zb5 = zb3;
            zb2 = zb5;
            zb5.y(a3.length());
        }
        zb2.z(false);
        zb3.z(a3.lastModified());
        if (!eos.moe.armourers.c.r(((zb)a2).r())) {
            a2 = ya.r(a3, (zb)a2);
            zb3.z((String)a2);
        }
        if (a3.isDirectory()) {
            zb zb6 = zb3;
            zb3.r(cb.s);
            zb6.r(yb.v);
            zb6.w(false);
            return zb6;
        }
        if (zb3.s() && zb3.r() == yb.m) {
            rb rb2 = a4;
            rb2.r(ob.m);
            zb3.r(xa.r(a3, a4));
            rb2.r(ob.r);
        }
        if (a3.length() == 0L) {
            zb3.r(cb.s);
        }
        return zb3;
    }

    @Override
    public ob r() {
        return ob.r;
    }

    public void r(pb a2, rb a3, Charset a4) throws ph {
        ta a5;
        a3 = new ca(null, false, (rb)a3);
        ta ta2 = a5;
        ((oa)(a3 = new la(ta2.j, ta2.s, (ca)a3))).r(new sa(Collections.singletonList(a2.r()), a4));
    }

    /*
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    private /* synthetic */ void r(File a2, vc a3, zb a4, pc a5, rb a622) throws IOException {
        ta ta2;
        block13: {
            ta a7;
            block11: {
                block12: {
                    a3.z((zb)a4);
                    if (a2.isDirectory()) break block11;
                    a4 = new FileInputStream(a2);
                    Throwable throwable = null;
                    try {
                        while ((a7.m = ((InputStream)a4).read(a7.v)) != -1) {
                            ta ta3 = a7;
                            a3.write(a7.v, 0, a7.m);
                            a622.r((long)ta3.m);
                            ta3.r();
                        }
                        if (a4 == null) break block11;
                        if (throwable == null) break block12;
                    }
                    catch (Throwable a622) {
                        try {
                            throwable = a622;
                            throw throwable;
                        }
                        catch (Throwable throwable2) {
                            Throwable throwable3;
                            if (a4 != null) {
                                if (throwable != null) {
                                    try {
                                        ((InputStream)a4).close();
                                        throwable3 = throwable2;
                                        throw throwable3;
                                    }
                                    catch (Throwable a622) {
                                        throwable3 = throwable2;
                                        throwable.addSuppressed(a622);
                                        throw throwable3;
                                    }
                                }
                                ((InputStream)a4).close();
                            }
                            throwable3 = throwable2;
                            throw throwable3;
                        }
                    }
                    try {
                        ((InputStream)a4).close();
                        ta2 = a7;
                    }
                    catch (Throwable a622) {
                        ta2 = a7;
                        throwable.addSuppressed(a622);
                    }
                    break block13;
                }
                ((InputStream)a4).close();
                ta2 = a7;
                break block13;
            }
            ta2 = a7;
        }
        ta2.r(a3, a5, a2, false);
    }

    /*
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(List<File> a2222222222222, rb a3, zb a4, Charset a5222222) throws IOException {
        pc pc2;
        Throwable throwable;
        pc pc3;
        block30: {
            block31: {
                ta a6;
                List<File> list = a2222222222222;
                ya.r(list, a4.r());
                a2222222222222 = a6.r(list, a4, a3, (Charset)a5222222);
                pc3 = new pc(a6.j.r(), a6.j.r());
                throwable = null;
                a5222222 = a6.r(pc3, (Charset)a5222222);
                Throwable throwable2 = null;
                a2222222222222 = a2222222222222.iterator();
                block18: while (true) {
                    List<File> object = a2222222222222;
                    while (object.hasNext()) {
                        File file = (File)a2222222222222.next();
                        ta ta2 = a6;
                        ta2.r();
                        zb zb2 = ta2.r(a4, file, a3);
                        File file2 = file;
                        a3.r(file2.getAbsolutePath());
                        if (ya.y(file2) && a6.r(zb2)) {
                            a6.r(file, (vc)a5222222, zb2, pc3);
                            if (ub.m.equals((Object)zb2.r())) {
                                object = a2222222222222;
                                continue;
                            }
                        }
                        a6.r(file, (vc)a5222222, zb2, pc3, a3);
                        continue block18;
                    }
                    break;
                }
                if (a5222222 != null) {
                    if (throwable2 != null) {
                        try {
                            ((vc)a5222222).close();
                            pc2 = pc3;
                        }
                        catch (Throwable a2222222222222) {
                            pc2 = pc3;
                            throwable2.addSuppressed(a2222222222222);
                        }
                        break block30;
                    } else {
                        ((vc)a5222222).close();
                        pc2 = pc3;
                    }
                    break block30;
                }
                break block31;
                catch (Throwable a2222222222222) {
                    try {
                        throwable2 = a2222222222222;
                        throw throwable2;
                    }
                    catch (Throwable throwable3) {
                        Throwable throwable4;
                        if (a5222222 != null) {
                            if (throwable2 != null) {
                                try {
                                    ((vc)a5222222).close();
                                    throwable4 = throwable3;
                                    throw throwable4;
                                }
                                catch (Throwable a2222222222222) {
                                    throwable4 = throwable3;
                                    throwable2.addSuppressed(a2222222222222);
                                    throw throwable4;
                                }
                            }
                            ((vc)a5222222).close();
                        }
                        throwable4 = throwable3;
                        throw throwable4;
                    }
                }
            }
            pc2 = pc3;
        }
        if (pc2 == null) return;
        if (throwable == null) {
            pc3.close();
            return;
        }
        try {
            pc3.close();
            return;
        }
        catch (Throwable a5222222) {
            throwable.addSuppressed(a5222222);
            return;
        }
        catch (Throwable a5222222) {
            try {
                throwable = a5222222;
                throw throwable;
            }
            catch (Throwable throwable5) {
                Throwable throwable6;
                if (pc3 != null) {
                    if (throwable != null) {
                        try {
                            pc3.close();
                            throwable6 = throwable5;
                            throw throwable6;
                        }
                        catch (Throwable a2222222222222) {
                            throwable6 = throwable5;
                            throwable.addSuppressed(a2222222222222);
                            throw throwable6;
                        }
                    }
                    pc3.close();
                }
                throwable6 = throwable5;
                throw throwable6;
            }
        }
    }

    private /* synthetic */ void r(vc a2, pc a3, File a4, boolean a5) throws IOException {
        ta a6;
        a2 = ((vc)a2).r();
        a4 = ya.r((File)a4);
        if (!a5) {
            Object object = a4;
            object[3] = wa.r((byte)object[3], 5);
        }
        ((pb)a2).h((byte[])a4);
        a6.r((pb)a2, a3);
    }

    private /* synthetic */ String r(String a2, String a3) {
        if (a2.contains("/")) {
            String string = a2;
            return new StringBuilder().insert(0, string.substring(0, string.lastIndexOf("/") + 1)).append(a3).toString();
        }
        return a3;
    }

    public ta(kb a2, char[] a3, vl a4, ca a5) {
        ta a6;
        ta ta2 = a6;
        ta ta3 = a6;
        super(a5);
        a6.v = new byte[4096];
        ta3.m = -1;
        ta3.j = a2;
        ta2.c = a3;
        ta2.s = a4;
    }

    @Override
    public void r(zb a2) throws ph {
        if (a2 == null) {
            throw new ph("cannot validate zip parameters");
        }
        if (a2.r() != cb.s && a2.r() != cb.j) {
            throw new ph("unsupported compression type");
        }
        if (a2.s()) {
            ta a3;
            if (a2.r() == yb.v) {
                throw new ph("Encryption method has to be set, when encrypt files flag is set");
            }
            if (a3.c == null || a3.c.length <= 0) {
                throw new ph("input password is empty or null");
            }
        } else {
            a2.r(yb.v);
        }
    }

    public vc r(pc a2, Charset a3) throws IOException {
        ta a4;
        if (a4.j.r().exists()) {
            a2.r(bg.r(a4.j));
        }
        return new vc(a2, a4.c, a3, a4.j);
    }

    public void r(pb a2, pc a3) throws IOException {
        ta a4;
        a4.s.r(a2, a4.r(), a3);
    }
}

