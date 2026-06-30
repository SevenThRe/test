/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.stream.Collectors;

public class qg
extends hl {
    public qg() {
        qg a2;
    }

    public qg(Collection<?> a3, boolean a4) {
        a5(a3.stream().map(a2 -> xf.ALLATORIxDEMO(String.valueOf(a2))).collect(Collectors.toList()));
        qg a5;
    }

    public qg(Collection<v> a2) {
        qg a3;
        LinkedHashMap<String, v> a4 = new LinkedHashMap<String, v>();
        int a5 = 0;
        for (v a6 : a2) {
            a4.put(String.valueOf(a5), a6);
            ++a5;
        }
        a3.ALLATORIxDEMO(a4);
    }

    @Override
    public void ALLATORIxDEMO(String a2, v a3) {
        qg a4;
        CharSequence[] a5 = a2.split("\\.");
        a5[a5.length - 1] = String.valueOf(Integer.parseInt(a5[a5.length - 1]));
        super.ALLATORIxDEMO(String.join((CharSequence)".", a5), a3);
    }

    @Override
    public v ALLATORIxDEMO(String a2, bt a3) {
        qg a4;
        return super.ALLATORIxDEMO(a2, a3);
    }

    @Override
    public String c() {
        qg a2;
        return a2.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.joining("\n"));
    }

    @Override
    public String ALLATORIxDEMO() {
        return "array";
    }

    public String toString() {
        qg a2;
        return a2.c();
    }
}

