/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.rz;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.xz;
import eos.moe.dragoncore.zw;
import java.util.LinkedHashMap;
import java.util.Map;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fq {
    private final LinkedHashMap<String, so> o = new LinkedHashMap();
    private zw y;
    private zw k;
    private Map<String, kq> ALLATORIxDEMO;

    public fq() {
        fq a2;
    }

    public void ALLATORIxDEMO(Map<String, kq> a2) {
        a.ALLATORIxDEMO = a2;
    }

    public static fq c() {
        return new fq();
    }

    public fq c(st a2, String a3) {
        fq a4;
        if (a4.o.containsKey(a3)) {
            a4.k = new zw(a3, a2);
            return a4;
        }
        throw new IllegalStateException();
    }

    public fq ALLATORIxDEMO(st a2, String a3) {
        fq a4;
        if (a4.o.containsKey(a3)) {
            a4.y = new zw(a3, a2);
            return a4;
        }
        throw new IllegalStateException();
    }

    public fq ALLATORIxDEMO(String a2, rz a3, float a4) {
        fq a5;
        so a6 = a5.o.put(a2, new so(a2, a3, a4));
        if (a6 != null) {
            throw new IllegalArgumentException("\u52a8\u753b\u5c42 " + a2 + " \u5df2\u7ecf\u5b58\u5728\u7ba1\u7406\u5668\u5167");
        }
        return a5;
    }

    public fq ALLATORIxDEMO() {
        fq a2;
        a2.ALLATORIxDEMO("base", rz.y, 1.0f);
        return a2;
    }

    private /* synthetic */ fq ALLATORIxDEMO(so a2) {
        try {
            fq a3;
            so a4 = a3.o.put(a2.ALLATORIxDEMO(), a2.ALLATORIxDEMO());
            if (a4 != null) {
                throw new IllegalArgumentException("\u52a8\u753b\u5c42 " + a2.ALLATORIxDEMO() + " \u5df2\u7ecf\u5b58\u5728\u52a8\u753b\u7ba1\u7406\u5668\u5167");
            }
            return a3;
        }
        catch (CloneNotSupportedException a5) {
            throw new RuntimeException(a5);
        }
    }

    public xz ALLATORIxDEMO() {
        fq a2;
        xz a3 = new xz(a2.k, a2.y, a2.ALLATORIxDEMO);
        if (a2.o.isEmpty()) {
            a2.ALLATORIxDEMO();
        }
        a3.ALLATORIxDEMO(a2.o);
        return a3;
    }
}

