/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.util.internal.ConcurrentSet
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.eca;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.xf;
import io.netty.util.internal.ConcurrentSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class faa {
    private static boolean q;
    public static final ConcurrentHashMap<String, String> b;
    private static final ConcurrentSet<String> o;
    private static final Pattern y;
    private static final ScheduledExecutorService k;
    private static ConcurrentHashMap<String, eca> ALLATORIxDEMO;

    public faa() {
        faa a2;
    }

    public static void f() {
        k.shutdownNow();
    }

    public static void ALLATORIxDEMO(String a2, String a3) {
        if (a2.startsWith("%")) {
            a2 = a2.substring(1, a2.length() - 1);
        }
        String a4 = b.getOrDefault(a2, "");
        b.put(a2, a3);
        wi.b.ALLATORIxDEMO("updatePlaceholder", xf.ALLATORIxDEMO(a2), xf.ALLATORIxDEMO(a3), xf.ALLATORIxDEMO(a4));
        faa.ALLATORIxDEMO(a2);
    }

    public static String c(String a2) {
        if (a2.startsWith("%")) {
            a2 = a2.substring(1, a2.length() - 1);
        }
        return b.getOrDefault(a2, "");
    }

    public static void ALLATORIxDEMO(String a2, boolean a3) {
        if (a2.startsWith("%")) {
            a2 = a2.substring(1, a2.length() - 1);
        }
        HashMap<String, String> a4 = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : b.entrySet()) {
            if (a3 && entry.getKey().startsWith(a2)) {
                a4.put(entry.getKey(), entry.getValue());
                continue;
            }
            if (!entry.getKey().equals(a2)) continue;
            a4.put(entry.getKey(), entry.getValue());
            break;
        }
        for (Map.Entry<String, String> entry : a4.entrySet()) {
            b.remove(entry.getKey());
            wi.b.ALLATORIxDEMO("deletePlaceholder", xf.ALLATORIxDEMO(entry.getKey()), xf.ALLATORIxDEMO(entry.getValue()));
        }
    }

    public static void c() {
        k.scheduleAtFixedRate(() -> {
            if (o.size() > 0) {
                HashMap<String, String> a2 = new HashMap<String, String>();
                for (String a3 : o) {
                    String a4 = faa.c(a3);
                    a2.put("%" + a3 + "%", a4);
                }
                nw.ALLATORIxDEMO(a2);
                o.clear();
            }
        }, 500L, 500L, TimeUnit.MILLISECONDS);
    }

    private static /* synthetic */ void ALLATORIxDEMO(String a2) {
        ALLATORIxDEMO.entrySet().removeIf(a3 -> eca.ALLATORIxDEMO((eca)a3.getValue()).contains(a2));
    }

    public static String ALLATORIxDEMO(String a2) {
        Object a3;
        if (!q) {
            q = true;
            faa.c();
        }
        if (a2 == null || !a2.contains("%")) {
            return a2;
        }
        if (!ALLATORIxDEMO.containsKey(a2)) {
            a3 = y.matcher(a2);
            HashSet<String> a4 = new HashSet<String>();
            while (((Matcher)a3).find()) {
                String a5 = ((Matcher)a3).group();
                int a6 = a5.indexOf("_");
                if (a6 <= 0 || a6 >= a5.length()) continue;
                String a7 = faa.c(a5);
                a2 = a2.replaceAll(Pattern.quote(a5), Matcher.quoteReplacement(a7));
                a4.add(a5.substring(1, a5.length() - 1));
            }
            ALLATORIxDEMO.put(a2, new eca(a2, a4));
        }
        a3 = ALLATORIxDEMO.get(a2);
        o.addAll((Collection)eca.ALLATORIxDEMO((eca)a3));
        return eca.ALLATORIxDEMO((eca)a3);
    }

    static {
        b = new ConcurrentHashMap();
        o = new ConcurrentSet();
        y = Pattern.compile("[%]([^%]+)[%]");
        k = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
        ALLATORIxDEMO = new ConcurrentHashMap();
    }
}

