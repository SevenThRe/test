/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.bg;
import eos.moe.armourers.c;
import eos.moe.armourers.ca;
import eos.moe.armourers.kb;
import eos.moe.armourers.ma;
import eos.moe.armourers.ob;
import eos.moe.armourers.pa;
import eos.moe.armourers.pb;
import eos.moe.armourers.pc;
import eos.moe.armourers.ph;
import eos.moe.armourers.ra;
import eos.moe.armourers.rb;
import eos.moe.armourers.sb;
import eos.moe.armourers.vl;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class va
extends pa<ma> {
    private vl v;
    private Charset s;
    private kb m;
    private ra j;

    private /* synthetic */ void r(List<pb> a2, pb a3, String a4, byte[] a5, int a6) throws ph {
        va a7;
        pb pb2 = bg.y(a7.m, a3.r());
        if (pb2 == null) {
            throw new ph(new StringBuilder().insert(0, "could not find any header with name: ").append(a3.r()).toString());
        }
        pb2.r(a4);
        pb2.r(a5.length);
        va va2 = a7;
        va2.r(a2, va2.m, pb2, a6);
        va va3 = a7;
        va2.m.r().r(va3.m.r().r() + (long)a6);
        if (va3.m.r()) {
            va va4 = a7;
            a7.m.r().h(va4.m.r().x() + (long)a6);
            va4.m.r().r(a7.m.r().r() + (long)a6);
        }
    }

    private /* synthetic */ Map.Entry<String, String> r(pb a2, Map<String, String> a3) {
        for (Map.Entry<String, String> entry : a3.entrySet()) {
            if (!a2.r().startsWith((String)entry.getKey())) continue;
            return entry;
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * WARNING - Removed back jump from a try to a catch block - possible behaviour change.
     * Unable to fully structure code
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(ma a, rb a) throws IOException {
        block34: {
            block31: {
                block32: {
                    if ((a = a.r(ma.r(a))).size() == 0) {
                        return;
                    }
                    v0 = a;
                    var3_6 = v0.r(v0.m.r().getPath());
                    var4_7 = false;
                    try {
                        var5_8 = new RandomAccessFile(a.m.r(), sb.j.r());
                        var6_9 = null;
                        try {
                            var7_10 = new pc(var3_6);
                            var8_13 = null;
                            try {
                                var9_14 = 0L;
                                v1 = a;
                                var11_17 = v1.r(v1.m.r().r());
                                v2 = var12_18 = var11_17.iterator();
lbl17:
                                // 2 sources

                                while (v2.hasNext()) {
                                    var13_19 = var12_18.next();
                                    var14_20 = a.r(var13_19, a);
                                    v3 = var13_19;
                                    a.r(v3.r());
                                    v4 = a;
                                    var15_21 = v4.r(var11_17, v3, v4.m) - var7_10.r();
                                    if (var14_20 != null) ** GOTO lbl-1000
                                    var9_14 += a.r(var5_8, var7_10, var9_14, var15_21, a);
                                    v5 = a;
                                    ** GOTO lbl76
                                }
                                ** GOTO lbl79
                            }
                            catch (Throwable var9_16) {
                                try {
                                    var8_13 = var9_16;
                                    throw var8_13;
                                }
                                catch (Throwable var19_24) {
                                    if (var7_10 != null) {
                                        if (var8_13 != null) {
                                            try {
                                                var7_10.close();
                                                v6 = var19_24;
                                                throw v6;
                                            }
                                            catch (Throwable a) {
                                                v6 = var19_24;
                                                var8_13.addSuppressed(a);
                                                throw v6;
                                            }
                                        }
                                        var7_10.close();
                                    }
                                    v6 = var19_24;
                                    throw v6;
                                }
                            }
                        }
                        catch (Throwable var7_12) {
                            try {
                                var6_9 = var7_12;
                                throw var6_9;
                            }
                            catch (Throwable var20_25) {
                                if (var5_8 != null) {
                                    if (var6_9 != null) {
                                        try {
                                            var5_8.close();
                                            v7 = var20_25;
                                            throw v7;
                                        }
                                        catch (Throwable a) {
                                            v7 = var20_25;
                                            var6_9.addSuppressed(a);
                                            throw v7;
                                        }
                                    }
                                    var5_8.close();
                                }
                                v7 = var20_25;
                                throw v7;
                            }
                        }
                    }
                    catch (Throwable a) {
                        v8 = a;
                        v8.r(var4_7, v8.m.r(), var3_6);
                        throw a;
                    }
lbl-1000:
                    // 1 sources

                    {
                        var14_20 = a.r(var14_20.getValue(), var14_20.getKey(), var13_19.r());
                        var17_22 = var14_20.getBytes(a.s);
                        var18_23 = var17_22.length - var13_19.r();
                        v9 = a;
                        v5 = v9;
                        var9_14 = v9.r(var17_22, var13_19, var9_14, var15_21, var5_8, var7_10, a);
                        v9.r(var11_17, var13_19, (String)var14_20, var17_22, var18_23);
lbl76:
                        // 2 sources

                        v5.r();
                        v2 = var12_18;
                        ** GOTO lbl17
lbl79:
                        // 1 sources

                        v10 = a;
                        v10.v.r(v10.m, var7_10, a.s);
                        var4_7 = true;
                        if (var7_10 == null) break block31;
                        if (var8_13 == null) break block32;
                    }
                    try {
                        var7_10.close();
                        v11 = var5_8;
                    }
                    catch (Throwable var9_15) {
                        v11 = var5_8;
                        var8_13.addSuppressed(var9_15);
                    }
                    break block34;
                }
                var7_10.close();
                v11 = var5_8;
                break block34;
            }
            v11 = var5_8;
        }
        if (v11 != null) {
            if (var6_9 != null) {
                try {
                    var5_8.close();
                    v12 = a;
                }
                catch (Throwable var7_11) {
                    v12 = a;
                    var6_9.addSuppressed(var7_11);
                }
            } else {
                var5_8.close();
                v12 = a;
            }
        } else {
            v12 = a;
        }
        v12.r(var4_7, a.m.r(), var3_6);
    }

    private /* synthetic */ String r(String a2, String a3, String a4) throws ph {
        if (a4.equals(a3)) {
            return a2;
        }
        if (a4.startsWith(a3)) {
            a3 = a4.substring(a3.length());
            return new StringBuilder().insert(0, a2).append(a3).toString();
        }
        throw new ph("old file name was neither an exact match nor a partial match");
    }

    @Override
    public ob r() {
        return ob.v;
    }

    private /* synthetic */ Map<String, String> r(Map<String, String> a2) throws ph {
        HashMap<String, String> hashMap = new HashMap<String, String>();
        a2 = a2.entrySet().iterator();
        block0: while (true) {
            Object object = a2;
            while (object.hasNext()) {
                va a3;
                Map.Entry entry = (Map.Entry)a2.next();
                if (!c.r((String)entry.getKey())) {
                    object = a2;
                    continue;
                }
                pb pb2 = bg.y(a3.m, (String)entry.getKey());
                if (pb2 == null) continue block0;
                if (pb2.z() && !((String)entry.getValue()).endsWith("/")) {
                    hashMap.put((String)entry.getKey(), (String)entry.getValue() + "/");
                    continue block0;
                }
                hashMap.put((String)entry.getKey(), (String)entry.getValue());
                continue block0;
            }
            break;
        }
        return hashMap;
    }

    @Override
    public long r(ma a2) {
        va a3;
        return a3.m.r().length();
    }

    public va(kb a2, vl a3, ra a4, Charset a5, ca a6) {
        va a7;
        va va2 = a7;
        va va3 = a7;
        super(a6);
        va3.m = a2;
        va3.v = a3;
        va2.j = a4;
        va2.s = a5;
    }

    private /* synthetic */ long r(byte[] a2, pb a3, long a4, long a5, RandomAccessFile a6, OutputStream a7, rb a8) throws IOException {
        va a9;
        long l2 = a4;
        l2 += a9.r(a6, a7, l2, 26L, a8);
        a9.j.y(a7, a2.length);
        l2 += 2L;
        l2 += a9.r(a6, a7, l2, 2L, a8);
        a7.write(a2);
        a4 = a5 - ((l2 += (long)a3.r()) - a4);
        l2 += a9.r(a6, a7, l2, a4, a8);
        return l2;
    }
}

