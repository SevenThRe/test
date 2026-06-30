/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.bg;
import eos.moe.armourers.ca;
import eos.moe.armourers.gb;
import eos.moe.armourers.kb;
import eos.moe.armourers.ob;
import eos.moe.armourers.pa;
import eos.moe.armourers.pb;
import eos.moe.armourers.pc;
import eos.moe.armourers.ph;
import eos.moe.armourers.rb;
import eos.moe.armourers.sa;
import eos.moe.armourers.sb;
import eos.moe.armourers.vl;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class la
extends pa<sa> {
    private vl m;
    private kb j;

    @Override
    public long r(sa a2) {
        la a3;
        return a3.j.r().length();
    }

    @Override
    private /* synthetic */ long r(long a2) {
        if (a2 == Long.MIN_VALUE) {
            throw new ArithmeticException("long overflow");
        }
        return -a2;
    }

    private /* synthetic */ List<String> y(List<String> a2) throws ph {
        ArrayList<String> arrayList = new ArrayList<String>();
        a2 = a2.iterator();
        while (a2.hasNext()) {
            la a3;
            String string = (String)a2.next();
            if (bg.y(a3.j, string) == null) continue;
            arrayList.add(string);
        }
        return arrayList;
    }

    public la(kb a2, vl a3, ca a4) {
        la a5;
        la la2 = a5;
        super(a4);
        la2.j = a2;
        la2.m = a3;
    }

    @Override
    public ob r() {
        return ob.j;
    }

    private /* synthetic */ void r(List<pb> a2, pb a3, long a4) throws ph {
        la a5;
        la la2 = a5;
        la2.r((List<pb>)a2, la2.j, a3, a5.r(a4));
        a2 = la2.j.r();
        ((gb)a2).r(((gb)a2).r() - a4);
        ((gb)a2).x(((gb)a2).r() - 1);
        if (((gb)a2).x() > 0) {
            Object object = a2;
            ((gb)object).r(((gb)object).x() - 1);
        }
        if (a5.j.r()) {
            la la3 = a5;
            la la4 = a5;
            la3.j.r().h(la4.j.r().x() - a4);
            la3.j.r().x(a5.j.r().z() - 1L);
            la4.j.r().r(a5.j.r().r() - a4);
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(sa a, rb a) throws IOException {
        block36: {
            block33: {
                block34: {
                    if (a.j.z()) {
                        throw new ph("This is a split archive. Zip file format does not allow updating split/spanned files");
                    }
                    var3_6 = a.y(sa.r(a));
                    if (var3_6.isEmpty()) {
                        return;
                    }
                    v0 = a;
                    var4_7 = v0.r(v0.j.r().getPath());
                    var5_8 = false;
                    try {
                        var6_9 = new pc(var4_7);
                        var7_10 = null;
                        try {
                            var8_11 = new RandomAccessFile(a.j.r(), sb.s.r());
                            var9_14 = null;
                            try {
                                var10_15 = 0L;
                                v1 = a;
                                var12_18 = v1.r(v1.j.r().r());
                                v2 = var13_19 = var12_18.iterator();
lbl20:
                                // 2 sources

                                while (v2.hasNext()) {
                                    var14_20 = var13_19.next();
                                    v3 = a;
                                    var15_21 = v3.r(var12_18, var14_20, a.j) - var6_9.r();
                                    if (!v3.r(var14_20, var3_6)) ** GOTO lbl-1000
                                    v4 = a;
                                    v4.r(var12_18, var14_20, var15_21);
                                    if (!v4.j.r().r().remove(var14_20)) {
                                        throw new ph("Could not remove entry from list of central directory headers");
                                    }
                                    var10_15 += var15_21;
                                    v5 = a;
                                    ** GOTO lbl75
                                }
                                ** GOTO lbl78
                            }
                            catch (Throwable var10_17) {
                                try {
                                    var9_14 = var10_17;
                                    throw var9_14;
                                }
                                catch (Throwable var17_22) {
                                    if (var8_11 != null) {
                                        if (var9_14 != null) {
                                            try {
                                                var8_11.close();
                                                v6 = var17_22;
                                                throw v6;
                                            }
                                            catch (Throwable a) {
                                                v6 = var17_22;
                                                var9_14.addSuppressed(a);
                                                throw v6;
                                            }
                                        }
                                        var8_11.close();
                                    }
                                    v6 = var17_22;
                                    throw v6;
                                }
                            }
                        }
                        catch (Throwable var8_13) {
                            try {
                                var7_10 = var8_13;
                                throw var7_10;
                            }
                            catch (Throwable var18_23) {
                                if (var6_9 != null) {
                                    if (var7_10 != null) {
                                        try {
                                            var6_9.close();
                                            v7 = var18_23;
                                            throw v7;
                                        }
                                        catch (Throwable a) {
                                            v7 = var18_23;
                                            var7_10.addSuppressed(a);
                                            throw v7;
                                        }
                                    }
                                    var6_9.close();
                                }
                                v7 = var18_23;
                                throw v7;
                            }
                        }
                    }
                    catch (Throwable a) {
                        v8 = a;
                        v8.r(var5_8, v8.j.r(), var4_7);
                        throw a;
                    }
lbl-1000:
                    // 1 sources

                    {
                        var10_15 += super.r(var8_11, var6_9, var10_15, var15_21, a);
                        v5 = a;
lbl75:
                        // 2 sources

                        v5.r();
                        v2 = var13_19;
                        ** GOTO lbl20
lbl78:
                        // 1 sources

                        v9 = a;
                        v9.m.r(v9.j, var6_9, (Charset)a.j);
                        var5_8 = true;
                        if (var8_11 == null) break block33;
                        if (var9_14 == null) break block34;
                    }
                    try {
                        var8_11.close();
                        v10 = var6_9;
                    }
                    catch (Throwable var10_16) {
                        v10 = var6_9;
                        var9_14.addSuppressed(var10_16);
                    }
                    break block36;
                }
                var8_11.close();
                v10 = var6_9;
                break block36;
            }
            v10 = var6_9;
        }
        if (v10 != null) {
            if (var7_10 != null) {
                try {
                    var6_9.close();
                    v11 = a;
                }
                catch (Throwable var8_12) {
                    v11 = a;
                    var7_10.addSuppressed(var8_12);
                }
            } else {
                var6_9.close();
                v11 = a;
            }
        } else {
            v11 = a;
        }
        v11.r(var5_8, a.j.r(), var4_7);
    }

    private /* synthetic */ boolean r(pb a2, List<String> a3) {
        a3 = a3.iterator();
        while (a3.hasNext()) {
            String string = (String)a3.next();
            if (!a2.r().startsWith(string)) continue;
            return true;
        }
        return false;
    }
}

