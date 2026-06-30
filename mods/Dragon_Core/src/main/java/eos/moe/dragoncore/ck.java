/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.faa;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.pt;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import java.util.HashMap;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ck {
    public ck() {
        ck a2;
    }

    @i(f={"\u66f4\u65b0\u53d8\u91cf\u503c", "\u66f4\u65b0\u53d8\u91cf", "PlaceholderAPI_Update"})
    public static void ALLATORIxDEMO(v ... a2) {
        HashMap<String, String> a3 = new HashMap<String, String>();
        for (String a4 : pt.ALLATORIxDEMO(a2)) {
            a4 = "%" + a4 + "%";
            a3.put(a4, faa.c(a4));
        }
        nw.ALLATORIxDEMO(a3);
    }

    @i(f={"\u7f6e\u53d8\u91cf\u503c", "\u8bbe\u7f6e\u53d8\u91cf", "\u8bbe\u7f6e\u53d8\u91cf\u503c", "PlaceholderAPI_Set"})
    public static void ALLATORIxDEMO(String a2, String a3) {
        faa.ALLATORIxDEMO(a2, a3);
    }

    @i(f={"\u53d6\u53d8\u91cf\u503c", "\u53d6\u53d8\u91cf", "PlaceholderAPI_Get"})
    public static v ALLATORIxDEMO(String a2) {
        String a3 = faa.c(a2);
        return new xf(a3);
    }

    @i(f={"\u66ff\u6362\u53d8\u91cf", "\u66ff\u6362\u53d8\u91cf\u503c", "PlaceholderAPI_Replace"})
    public static String ALLATORIxDEMO(String a2) {
        return faa.ALLATORIxDEMO(a2);
    }

    @i(f={"\u5220\u9664\u53d8\u91cf", "PlaceholderAPI_Delete"})
    public static void ALLATORIxDEMO(String a2, boolean a3) {
        faa.ALLATORIxDEMO(a2, a3);
    }
}

