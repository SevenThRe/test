/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 */
package eos.moe.dragoncore;

import com.google.common.collect.ImmutableList;
import eos.moe.dragoncore.fm;
import eos.moe.dragoncore.iz;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.p;
import eos.moe.dragoncore.pe;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.ys;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class tn
extends mh {
    private final pe y;
    public p k;
    private v ALLATORIxDEMO;

    public tn(nh a2, p a3) {
        super(a2);
        tn a4;
        a4.y = new pe(a3);
        a4.k = a3;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public v ALLATORIxDEMO() {
        tn a2;
        tn tn2 = a2;
        synchronized (tn2) {
            v a3 = null;
            if (((nh)((Object)a2.ALLATORIxDEMO)).ALLATORIxDEMO() == null) {
                throw new NullPointerException("MoLangRuntime is null");
            }
            if (((nh)((Object)a2.ALLATORIxDEMO)).k != null) {
                a3 = ((nh)((Object)a2.ALLATORIxDEMO)).k == wk.ALLATORIxDEMO ? (((nh)((Object)a2.ALLATORIxDEMO)).y ? fm.ALLATORIxDEMO((nh)((Object)a2.ALLATORIxDEMO), a2.y) : fm.ALLATORIxDEMO((nh)((Object)a2.ALLATORIxDEMO))) : ((nh)((Object)a2.ALLATORIxDEMO)).k;
            }
            if (a3 == null) {
                try {
                    ((nh)((Object)a2.ALLATORIxDEMO)).x();
                }
                catch (Exception a4) {
                    ((nh)((Object)a2.ALLATORIxDEMO)).ALLATORIxDEMO(Collections.EMPTY_LIST);
                    a4.printStackTrace();
                    a3 = pf.ALLATORIxDEMO(0);
                }
                if (a3 == null) {
                    ((nh)((Object)a2.ALLATORIxDEMO)).k = ((nh)((Object)a2.ALLATORIxDEMO)).ALLATORIxDEMO().size() == 0 ? pf.ALLATORIxDEMO(0) : (((nh)((Object)a2.ALLATORIxDEMO)).ALLATORIxDEMO().size() == 1 ? ((a = ((nh)((Object)a2.ALLATORIxDEMO)).ALLATORIxDEMO().get(0)) instanceof iz || a instanceof ys ? a.ALLATORIxDEMO(null, null) : wk.ALLATORIxDEMO) : wk.ALLATORIxDEMO);
                    a3 = ((nh)((Object)a2.ALLATORIxDEMO)).k == wk.ALLATORIxDEMO ? fm.ALLATORIxDEMO((nh)((Object)a2.ALLATORIxDEMO), a2.y) : ((nh)((Object)a2.ALLATORIxDEMO)).k;
                }
            }
            return a3;
        }
    }

    @Override
    public double c() {
        tn a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    @Override
    public boolean c() {
        tn a2;
        return a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    @Override
    public int c() {
        tn a2;
        return (int)a2.ALLATORIxDEMO().ALLATORIxDEMO();
    }

    @Override
    public String c() {
        tn a2;
        return a2.ALLATORIxDEMO().c();
    }

    @Override
    public List<String> w() {
        tn a2;
        v a3 = a2.ALLATORIxDEMO();
        if (a3 instanceof qg) {
            qg a4 = (qg)a3;
            return a4.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.toList());
        }
        return ImmutableList.of((Object)a3.c());
    }

    @Override
    public List<v> z() {
        tn a2;
        v a3 = a2.ALLATORIxDEMO();
        if (a3 instanceof qg) {
            qg a4 = (qg)a3;
            return new ArrayList<v>(a4.ALLATORIxDEMO());
        }
        return ImmutableList.of((Object)a3);
    }

    @Override
    public void c() {
        tn a2;
        a2.ALLATORIxDEMO = a2.ALLATORIxDEMO();
    }

    public v c() {
        tn a2;
        if (a2.ALLATORIxDEMO == null) {
            return a2.ALLATORIxDEMO();
        }
        return a2.ALLATORIxDEMO;
    }

    @Override
    public double ALLATORIxDEMO() {
        tn a2;
        return a2.c().ALLATORIxDEMO();
    }

    @Override
    public boolean ALLATORIxDEMO() {
        tn a2;
        return a2.c().ALLATORIxDEMO();
    }

    @Override
    public int ALLATORIxDEMO() {
        tn a2;
        return (int)a2.c().ALLATORIxDEMO();
    }

    @Override
    public String ALLATORIxDEMO() {
        tn a2;
        return a2.c().c();
    }

    @Override
    public List<String> k() {
        tn a2;
        v a3 = a2.c();
        if (a3 instanceof qg) {
            qg a4 = (qg)a3;
            return a4.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.toList());
        }
        return ImmutableList.of((Object)a3.c());
    }

    @Override
    public List<v> d() {
        tn a2;
        v a3 = a2.c();
        if (a3 instanceof qg) {
            qg a4 = (qg)a3;
            return new ArrayList<v>(a4.ALLATORIxDEMO());
        }
        return ImmutableList.of((Object)a3);
    }
}

