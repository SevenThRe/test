/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.ca;
import eos.moe.armourers.db;
import eos.moe.armourers.fb;
import eos.moe.armourers.gb;
import eos.moe.armourers.ka;
import eos.moe.armourers.kb;
import eos.moe.armourers.oa;
import eos.moe.armourers.ob;
import eos.moe.armourers.pb;
import eos.moe.armourers.ph;
import eos.moe.armourers.ra;
import eos.moe.armourers.rb;
import eos.moe.armourers.sb;
import eos.moe.armourers.vl;
import eos.moe.armourers.wm;
import eos.moe.armourers.ya;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.List;

public class ja
extends oa<ka> {
    private kb m;
    private ra j;

    public ja(kb a2, ca a3) {
        super(a3);
        ja a4;
        ja ja2 = a4;
        a4.j = new ra();
        a4.m = a2;
    }

    @Override
    private /* synthetic */ void r(kb a22) {
        gb gb2;
        kb kb2 = a22;
        int a22 = kb2.r().r().size();
        gb gb3 = gb2 = kb2.r();
        gb gb4 = gb2;
        gb4.z(0);
        gb4.y(0);
        gb3.x(a22);
        gb3.r(a22);
    }

    private /* synthetic */ void r(List<pb> a2, long a3, int a4, int a5) {
        a2 = a2.iterator();
        while (a2.hasNext()) {
            pb pb2 = (pb)a2.next();
            if (pb2.s() != a4) continue;
            pb pb3 = pb2;
            pb3.x(pb3.s() + a3 - (long)a5);
            pb3.s(0);
        }
    }

    private /* synthetic */ void z(kb a2, long a3) {
        ja a4;
        kb kb2 = a2;
        kb2.y(false);
        a4.r(kb2);
        if (kb2.r()) {
            ja ja2 = a4;
            long l2 = a3;
            ja2.r(a2, l2);
            ja2.y(a2, l2);
        }
    }

    private /* synthetic */ void y(kb a2, long a3) {
        db db2;
        if (a2.r() == null) {
            return;
        }
        db db3 = db2 = a2.r();
        db2.h(0);
        db3.r(0);
        db2.x(a2.r().r());
        db2.h(db3.x() + a3);
    }

    private /* synthetic */ void r(kb a2, long a3) {
        if (((kb)a2).r() == null) {
            return;
        }
        Object object = a2 = ((kb)a2).r();
        ((fb)a2).r(0);
        ((fb)object).r(((fb)object).r() + a3);
        ((fb)object).y(1);
    }

    @Override
    public ob r() {
        return ob.t;
    }

    private /* synthetic */ void r(kb a2, long a3, OutputStream a4, Charset a5) throws IOException, CloneNotSupportedException {
        ja a6;
        kb kb2 = a2 = (kb)a2.clone();
        kb2.r().r(a3);
        a6.z(kb2, a3);
        new vl().y(a2, a4, a5);
    }

    private /* synthetic */ File r(kb a2, int a3) {
        if (a3 == ((kb)a2).r().z()) {
            return ((kb)a2).r();
        }
        String string = ".z0";
        if (a3 >= 9) {
            string = ".z";
        }
        String string2 = ((kb)a2).r().getPath();
        a2 = new StringBuilder().insert(0, ((kb)a2).r().getPath().substring(0, string2.lastIndexOf("."))).append(string).append(a3 + 1).toString();
        return new File((String)a2);
    }

    private /* synthetic */ RandomAccessFile r(kb a2, int a3) throws FileNotFoundException {
        ja a4;
        a2 = a4.r((kb)a2, a3);
        return new RandomAccessFile((File)a2, sb.s.r());
    }

    @Override
    public long r(ka a2) {
        ja a3;
        if (!a3.m.z()) {
            return 0L;
        }
        long l2 = 0L;
        int n2 = a2 = 0;
        while (n2 <= a3.m.r().z()) {
            ja ja2 = a3;
            File file = ja2.r(ja2.m, a2);
            l2 += file.length();
            n2 = ++a2;
        }
        return l2;
    }

    /*
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(ka a, rb a) throws IOException {
        block34: {
            block35: {
                block36: {
                    block33: {
                        block31: {
                            block32: {
                                if (!a.m.z()) {
                                    v0 = var3_4 = new ph("archive not a split zip file");
                                    a.y(v0);
                                    throw v0;
                                }
                                try {
                                    var3_5 = new FileOutputStream(ka.r(a));
                                    var4_7 = null;
                                    var5_8 = 0L;
                                    var7_11 = a.m.r().z();
                                    if (var7_11 <= 0) {
                                        throw new ph("zip archive not a split zip file");
                                    }
                                    var8_12 = 0;
                                    v1 = var9_13 = 0;
lbl15:
                                    // 2 sources

                                    while (v1 <= var7_11) {
                                        v2 = a;
                                        var10_14 = v2.r(v2.m, var9_13);
                                        var11_15 = null;
                                        try {
                                            var12_16 = 0;
                                            var13_20 = var10_14.length();
                                            if (var9_13 != 0) break block31;
                                            if ((long)a.j.y(var10_14) != wm.l.r()) break block32;
                                            var8_12 = 4;
                                            var12_16 = 4;
                                            v3 = var9_13;
                                            break block33;
                                        }
                                        catch (Throwable var12_18) {
                                            try {
                                                var11_15 = var12_18;
                                                throw var11_15;
                                            }
                                            catch (Throwable var15_21) {
                                                if (var10_14 != null) {
                                                    if (var11_15 != null) {
                                                        try {
                                                            var10_14.close();
                                                            v4 = var15_21;
                                                            throw v4;
                                                        }
                                                        catch (Throwable var12_19) {
                                                            v4 = var15_21;
                                                            var11_15.addSuppressed(var12_19);
                                                            throw v4;
                                                        }
                                                    }
                                                    var10_14.close();
                                                }
                                                v4 = var15_21;
                                                throw v4;
                                            }
                                        }
                                    }
                                    break block34;
                                }
                                catch (CloneNotSupportedException var3_6) {
                                    throw new ph(var3_6);
                                }
                                catch (Throwable var5_10) {
                                    try {
                                        var4_7 = var5_10;
                                        throw var4_7;
                                    }
                                    catch (Throwable var16_22) {
                                        if (var3_5 != null) {
                                            if (var4_7 != null) {
                                                try {
                                                    var3_5.close();
                                                    v5 = var16_22;
                                                    throw v5;
                                                }
                                                catch (Throwable a) {
                                                    v5 = var16_22;
                                                    var4_7.addSuppressed(a);
                                                    throw v5;
                                                }
                                            }
                                            var3_5.close();
                                        }
                                        v5 = var16_22;
                                        throw v5;
                                    }
                                }
                            }
                            var10_14.seek(0L);
                        }
                        v3 = var9_13;
                    }
                    if (v3 == var7_11) {
                        var13_20 = a.m.r().r();
                    }
                    ya.r(var10_14, var3_5, var12_16, var13_20, a);
                    var5_8 += var13_20 - (long)var12_16;
                    v6 = a;
                    v7 = v6.m.r().r();
                    if (var9_13 != 0) ** GOTO lbl-1000
                    v8 = 0L;
                    v9 = var9_13;
                    ** GOTO lbl84
lbl-1000:
                    // 1 sources

                    {
                        v8 = var5_8;
                        v9 = var9_13;
lbl84:
                        // 2 sources

                        v6.r(v7, v8, v9, var8_12);
                        a.r();
                        if (var10_14 == null) break block35;
                        if (var11_15 == null) break block36;
                    }
                    try {
                        var10_14.close();
                    }
                    catch (Throwable var12_17) {
                        var11_15.addSuppressed(var12_17);
                    }
                    break block35;
                }
                var10_14.close();
            }
            v1 = ++var9_13;
            ** GOTO lbl15
        }
        v10 = a;
        v10.r(v10.m, var5_8, var3_5, (Charset)a.j);
        a.r();
        if (var3_5 == null) return;
        if (var4_7 != null) {
            try {
                var3_5.close();
                return;
            }
            catch (Throwable var5_9) {
                var4_7.addSuppressed(var5_9);
                return;
            }
        }
        var3_5.close();
    }
}

