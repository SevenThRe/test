/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.v;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class md
extends nh {
    private List<nh> ALLATORIxDEMO;

    public md(List<nh> a2) {
        md a3;
        a3.c(a2);
    }

    public void c(List<nh> a2) {
        a.ALLATORIxDEMO = new CopyOnWriteArrayList<nh>(a2);
    }

    public List<nh> f() {
        md a2;
        return a2.ALLATORIxDEMO;
    }

    public int f() {
        md a2;
        return a2.ALLATORIxDEMO.size();
    }

    @Override
    public void c() {
        md a2;
        for (nh a3 : a2.ALLATORIxDEMO) {
            a3.c();
        }
    }

    @Override
    public qg ALLATORIxDEMO() {
        md a2;
        return new qg(a2.ALLATORIxDEMO.stream().map(nh::f).collect(Collectors.toList()), false);
    }

    @Override
    public List<v> z() {
        md a2;
        return a2.ALLATORIxDEMO.stream().map(nh::z).flatMap(Collection::stream).collect(Collectors.toList());
    }

    @Override
    public List<v> d() {
        md a2;
        return a2.ALLATORIxDEMO.stream().map(nh::d).flatMap(Collection::stream).collect(Collectors.toList());
    }
}

