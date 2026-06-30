/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.armourers;

import eos.moe.armourers.bg;
import eos.moe.armourers.ca;
import eos.moe.armourers.da;
import eos.moe.armourers.dc;
import eos.moe.armourers.fa;
import eos.moe.armourers.kb;
import eos.moe.armourers.oc;
import eos.moe.armourers.pb;
import eos.moe.armourers.rb;
import eos.moe.armourers.za;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Iterator;

public class ha
extends da<za> {
    private char[] m;
    private dc j;

    private /* synthetic */ pb r(kb a2) {
        if (a2.r() == null || a2.r().r() == null || a2.r().r().size() == 0) {
            return null;
        }
        return a2.r().r().get(0);
    }

    public ha(kb a2, char[] a3, ca a4) {
        super(a2, a4);
        ha a5;
        a5.m = a3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Loose catch block
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public void r(za a2222222, rb a3) throws IOException {
        ha a4;
        try {
            ha ha2;
            block19: {
                block20: {
                    oc oc2 = a4.r((Charset)((Object)a2222222.j));
                    Throwable throwable = null;
                    Iterator<pb> iterator = a4.r().r().r().iterator();
                    block12: while (true) {
                        Iterator<pb> iterator2 = iterator;
                        while (iterator2.hasNext()) {
                            pb pb2 = iterator.next();
                            if (pb2.r().startsWith("__MACOSX")) {
                                iterator2 = iterator;
                                a3.r(pb2.r());
                                continue;
                            }
                            ha ha3 = a4;
                            ha3.j.r(pb2);
                            ha3.r(oc2, pb2, za.r(a2222222), null, a3);
                            a4.r();
                            continue block12;
                        }
                        break;
                    }
                    if (oc2 == null) break block20;
                    if (throwable != null) {
                        try {
                            oc2.close();
                            ha2 = a4;
                        }
                        catch (Throwable throwable2) {
                            ha2 = a4;
                            throwable.addSuppressed(throwable2);
                        }
                        break block19;
                    } else {
                        oc2.close();
                        ha2 = a4;
                    }
                    break block19;
                    catch (Throwable throwable3) {
                        try {
                            throwable = throwable3;
                            throw throwable;
                        }
                        catch (Throwable throwable4) {
                            Throwable throwable5;
                            if (oc2 != null) {
                                if (throwable != null) {
                                    try {
                                        oc2.close();
                                        throwable5 = throwable4;
                                        throw throwable5;
                                    }
                                    catch (Throwable a2222222) {
                                        throwable5 = throwable4;
                                        throwable.addSuppressed(a2222222);
                                        throw throwable5;
                                    }
                                }
                                oc2.close();
                            }
                            throwable5 = throwable4;
                            throw throwable5;
                        }
                    }
                }
                ha2 = a4;
            }
            if (ha2.j == null) return;
        }
        catch (Throwable throwable) {
            if (a4.j == null) throw throwable;
            a4.j.close();
            throw throwable;
        }
        a4.j.close();
    }

    @Override
    public long r(za a2) {
        ha a3;
        return bg.r(a3.r().r().r());
    }

    private /* synthetic */ oc r(Charset a2) throws IOException {
        ha a3;
        ha ha2 = a3;
        ha2.j = fa.r(ha2.r());
        pb pb2 = ha2.r(ha2.r());
        if (pb2 != null) {
            a3.j.r(pb2);
        }
        ha ha3 = a3;
        return new oc(ha3.j, ha3.m, a2);
    }
}

