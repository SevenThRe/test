/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.bt;
import eos.moe.dragoncore.hl;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.iv;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.pt;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class fk {
    public fk() {
        fk a2;
    }

    @i(f={"\u672c\u5730\u53d8\u91cf", "client"})
    public static v x() {
        return qv.y;
    }

    @i(f={"\u7528\u6237\u53d8\u91cf", "player"})
    public static v f() {
        return qv.o;
    }

    @i(f={"\u7cfb\u7edf\u53d8\u91cf", "system"})
    public static v c() {
        return qv.k;
    }

    @i(f={"\u5168\u5c40\u53d8\u91cf", "global"})
    public static v ALLATORIxDEMO() {
        return qv.b;
    }

    @i(f={"\u53d8\u91cf\u53d6\u503c", "Var_Get"})
    public static v ALLATORIxDEMO(v a2, String a3) {
        if (a2 instanceof hl) {
            hl a4 = (hl)a2;
            return a4.ALLATORIxDEMO(a3, (bt)null);
        }
        return wk.k;
    }

    @i(f={"\u53d8\u91cf\u7f6e\u503c", "Var_Set"})
    public static void ALLATORIxDEMO(v a2, String a3, v a4) {
        if (a2 instanceof hl) {
            hl a5 = (hl)a2;
            a5.ALLATORIxDEMO(a3, a4);
        }
    }

    @i(f={"\u91cd\u8f7d\u53d8\u91cf", "Var_Reload"})
    public static void ALLATORIxDEMO(v a2) {
        if (a2 instanceof iv) {
            ((iv)a2).f();
        }
    }

    @i(f={"\u65b0\u5efa\u6570\u7ec4", "Array_Create"})
    public static v ALLATORIxDEMO(v ... a2) {
        return new qg(Arrays.asList(a2));
    }

    @i(f={"\u4fee\u6539\u6210\u5458", "Array_Replace"})
    public static v ALLATORIxDEMO(v a2, int a3, v a4) {
        qg a5;
        if (a2 instanceof qg && a3 <= (a5 = (qg)a2).ALLATORIxDEMO().size() && a3 >= 0) {
            a5.ALLATORIxDEMO(String.valueOf(a3), a4);
        }
        return a2;
    }

    @i(f={"\u63d2\u5165\u6210\u5458", "Array_Add"})
    public static v ALLATORIxDEMO(v a2, int a3, v ... a4) {
        if (a2 instanceof qg) {
            qg a5 = (qg)a2;
            ArrayList<v> a6 = new ArrayList<v>();
            if (a3 < 0) {
                a6.addAll(pt.c(a4));
                a6.addAll(a5.ALLATORIxDEMO());
                a5.ALLATORIxDEMO(new qg(a6).ALLATORIxDEMO());
                return a2;
            }
            if (a3 >= a5.ALLATORIxDEMO().size()) {
                return fk.c(a2, a4);
            }
            List<v> a7 = pt.c(a4);
            for (Map.Entry<String, v> a8 : a5.ALLATORIxDEMO().entrySet()) {
                if (Integer.parseInt(a8.getKey()) == a3) {
                    a6.addAll(a7);
                }
                a6.add(a8.getValue());
            }
            a5.ALLATORIxDEMO(new qg(a6).ALLATORIxDEMO());
        }
        return a2;
    }

    @i(f={"\u6dfb\u52a0\u6210\u5458", "Array_Add"})
    public static v c(v a2, v ... a3) {
        if (a2 instanceof qg) {
            qg a4 = (qg)a2;
            ArrayList<v> a5 = new ArrayList<v>();
            a5.addAll(a4.ALLATORIxDEMO());
            a5.addAll(pt.c(a3));
            a4.ALLATORIxDEMO(new qg(a5).ALLATORIxDEMO());
        }
        return a2;
    }

    @i(f={"\u79fb\u9664\u6210\u5458", "Array_Remove"})
    public static v ALLATORIxDEMO(v a3, v ... a4) {
        if (a3 instanceof qg) {
            Set a5 = Arrays.stream(a4).map(a2 -> (int)a2.ALLATORIxDEMO()).collect(Collectors.toSet());
            qg a6 = (qg)a3;
            ArrayList<v> a7 = new ArrayList<v>();
            for (Map.Entry<String, v> a8 : a6.ALLATORIxDEMO().entrySet()) {
                int a9 = Integer.parseInt(a8.getKey());
                if (a5.contains(a9)) continue;
                a7.add(a8.getValue());
            }
            a6.ALLATORIxDEMO(new qg(a7).ALLATORIxDEMO());
        }
        return a3;
    }

    @i(f={"\u53d6\u6210\u5458", "get", "Array_Get"})
    public static v ALLATORIxDEMO(v a2, int a3) {
        if (a2 instanceof qg) {
            qg a4 = (qg)a2;
            return a4.ALLATORIxDEMO(String.valueOf(a3), bt.k);
        }
        return pf.y;
    }

    @i(f={"\u622a\u53d6\u6570\u7ec4", "sublist", "Array_Sub"})
    public static v ALLATORIxDEMO(v a2, int a3, int a4) {
        if (a2 instanceof qg) {
            ArrayList<v> a5 = new ArrayList<v>();
            qg a6 = (qg)a2;
            Map<String, v> a7 = a6.ALLATORIxDEMO();
            for (Map.Entry<String, v> a8 : a7.entrySet()) {
                try {
                    int a9 = Integer.parseInt(a8.getKey());
                    if (a9 < a3 || a9 >= a4) continue;
                    a5.add(a8.getValue());
                }
                catch (NumberFormatException a9) {}
            }
            return new qg(a5);
        }
        return a2;
    }

    @i(f={"\u53d6\u6210\u5458\u6570", "get_size", "Array_Size"})
    public static v ALLATORIxDEMO(v a2) {
        if (a2 instanceof qg) {
            qg a3 = (qg)a2;
            return pf.ALLATORIxDEMO(a3.ALLATORIxDEMO().size());
        }
        return pf.y;
    }

    @i(f={"\u622a\u53d6\u5185\u5bb9", "subContent"})
    public static v ALLATORIxDEMO(v a2, String a3, int a4, int a5) {
        if (!(a2 instanceof qg)) {
            return pf.y;
        }
        qg a6 = (qg)a2;
        List a7 = a6.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.toList());
        int a8 = 0;
        ArrayList<String> a9 = new ArrayList<String>();
        for (String a10 : a7) {
            if (a10.equals(a3)) {
                ++a8;
            }
            if (a8 >= a4 && a8 < a5) {
                a9.add(a10);
            }
            if (a8 != a5) continue;
            break;
        }
        return new qg(a9, false);
    }
}

