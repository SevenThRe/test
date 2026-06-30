/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.br;
import eos.moe.dragoncore.hw;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.xf;
import eos.moe.dragoncore.xn;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class xo
implements b {
    public static Pattern o = Pattern.compile("\\$\\{(.*?)}");
    public String y;
    public Map<String, nh> k;

    public xo(String a2) {
        xo a3;
        a3.y = a2;
        a3.k = new HashMap<String, nh>();
        Matcher a4 = o.matcher(a2);
        while (a4.find()) {
            a3.k.put(Pattern.quote(a4.group()), hw.ALLATORIxDEMO(a4.group(1)));
        }
    }

    @Override
    public v ALLATORIxDEMO(br a2, xn a3) {
        xo a4;
        String a5 = a4.y;
        qv a6 = a3.ALLATORIxDEMO();
        if (a6 != null && !a4.k.isEmpty()) {
            for (Map.Entry<String, nh> a7 : a4.k.entrySet()) {
                nh a8 = a7.getValue();
                v a9 = a6.ALLATORIxDEMO(a8.x(), a3);
                a5 = a5.replaceAll(a7.getKey(), Matcher.quoteReplacement(a9.c()));
            }
        }
        return new xf(a5);
    }
}

