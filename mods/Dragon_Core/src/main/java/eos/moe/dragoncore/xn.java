/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bd;
import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.cz;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.hx;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.m;
import eos.moe.dragoncore.pe;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.pr;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xs;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class xn
implements v {
    public ConcurrentMap<String, m> y;
    public ui k;
    public kp ALLATORIxDEMO;

    public xn(ui a2) {
        xn a3;
        a3.y = new ConcurrentHashMap<String, m>();
        a3.k = a2;
    }

    public xn(kp a2) {
        xn a3;
        a3.y = new ConcurrentHashMap<String, m>();
        a3.ALLATORIxDEMO = a2;
    }

    public xn() {
        xn a2;
        a2.y = new ConcurrentHashMap<String, m>();
    }

    public boolean c() {
        xn a2;
        return a2.k != null && a2.k.w || a2.ALLATORIxDEMO != null && a2.ALLATORIxDEMO.t;
    }

    @Override
    public qv ALLATORIxDEMO() {
        xn a2;
        if (a2.k != null) {
            return a2.k.getMoLangRuntime();
        }
        if (a2.ALLATORIxDEMO != null) {
            return a2.ALLATORIxDEMO.ALLATORIxDEMO();
        }
        return null;
    }

    public v ALLATORIxDEMO(String a2) {
        xn a3;
        return a3.ALLATORIxDEMO(a2, bt.k);
    }

    public v ALLATORIxDEMO(String a2, bt a3) {
        Object a4;
        xn a5;
        Object a6;
        if (a2.startsWith("$")) {
            a6 = a2.substring(1);
            a4 = (m)a5.y.get("\u53d8\u91cf");
            if (a4 instanceof hl) {
                return a4.ALLATORIxDEMO((String)a6, a3);
            }
        }
        a6 = new LinkedList<String>(Arrays.asList(a2.split("\\.")));
        a4 = (String)((LinkedList)a6).poll();
        if (a5.k != null && a5.k.getComponents().containsKey(a4)) {
            jj a7 = a5.k.findComponent((String)a4);
            return bd.ALLATORIxDEMO(a5.k, (v)new pe(a7), String.join((CharSequence)".", (Iterable<? extends CharSequence>)a6));
        }
        if (a5.ALLATORIxDEMO != null && a5.ALLATORIxDEMO.ALLATORIxDEMO().containsKey(a4)) {
            cz a8 = a5.ALLATORIxDEMO.ALLATORIxDEMO().get(a4);
            return xs.ALLATORIxDEMO(a5.ALLATORIxDEMO, new pr(a8), String.join((CharSequence)".", (Iterable<? extends CharSequence>)a6));
        }
        if (a5.y.containsKey(a4)) {
            return ((m)a5.y.get(a4)).ALLATORIxDEMO(String.join((CharSequence)".", (Iterable<? extends CharSequence>)a6), a3);
        }
        return new pf(0.0);
    }

    public boolean ALLATORIxDEMO(String a2, v a3) {
        Object a4;
        xn a5;
        Object a6;
        if (a2.startsWith("$")) {
            a6 = a2.substring(1);
            a4 = (m)a5.y.get("\u53d8\u91cf");
            if (a4 instanceof hl) {
                a4.ALLATORIxDEMO((String)a6, a3);
            }
        }
        a6 = new LinkedList<String>(Arrays.asList(a2.split("\\.")));
        a4 = (String)((LinkedList)a6).poll();
        if (a5.k != null && a5.k.getComponents().containsKey(a4)) {
            jj a7 = a5.k.findComponent((String)a4);
            bd.ALLATORIxDEMO(a5.k, (v)new pe(a7), String.join((CharSequence)".", (Iterable<? extends CharSequence>)a6), a3);
            return true;
        }
        if (a5.ALLATORIxDEMO != null && a5.ALLATORIxDEMO.ALLATORIxDEMO().containsKey(a4)) {
            cz a8 = a5.ALLATORIxDEMO.ALLATORIxDEMO().get(a4);
            xs.ALLATORIxDEMO(a5.ALLATORIxDEMO, new pr(a8), String.join((CharSequence)".", (Iterable<? extends CharSequence>)a6), a3);
            return true;
        }
        if (a5.y.containsKey(a4)) {
            ((m)a5.y.get(a4)).ALLATORIxDEMO(String.join((CharSequence)".", (Iterable<? extends CharSequence>)a6), a3);
            return true;
        }
        return false;
    }

    @Override
    public Object ALLATORIxDEMO() {
        xn a2;
        return a2;
    }

    @Override
    public ConcurrentMap<String, m> ALLATORIxDEMO() {
        xn a2;
        return a2.y;
    }

    public void ALLATORIxDEMO(ConcurrentMap<String, m> a2) {
        a.y = a2;
    }

    @Override
    public xn ALLATORIxDEMO() {
        xn a2;
        return new hx(a2);
    }

    @Override
    public String ALLATORIxDEMO() {
        return "environment";
    }
}

