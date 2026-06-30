/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  io.netty.util.internal.ConcurrentSet
 */
package eos.moe.armourers;

import eos.moe.armourers.nf;
import io.netty.util.internal.ConcurrentSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class jn {
    private static final ConcurrentSet<String> v;
    private static final ConcurrentHashMap<String, String> s;
    private static final Pattern m;
    private static final ScheduledExecutorService j;

    static {
        s = new ConcurrentHashMap();
        v = new ConcurrentSet();
        m = Pattern.compile("[%]([^%]+)[%]");
        j = Executors.newScheduledThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public static void r(String a2, String a3) {
        s.put(a2, a3);
    }

    public static void r() {
        j.scheduleAtFixedRate(() -> {
            if (v.size() > 0) {
                Iterator iterator;
                HashMap<String, String> hashMap = new HashMap<String, String>();
                Iterator iterator2 = iterator = v.iterator();
                while (iterator2.hasNext()) {
                    String string = (String)iterator.next();
                    String string2 = s.getOrDefault(string, "");
                    iterator2 = iterator;
                    hashMap.put(string, string2);
                }
                nf.r(hashMap);
                v.clear();
            }
        }, 500L, 500L, TimeUnit.MILLISECONDS);
    }

    public static String r(String a2) {
        if (a2 == null || !a2.contains("%")) {
            return a2;
        }
        Matcher matcher = m.matcher(a2);
        block0: while (true) {
            Matcher matcher2 = matcher;
            while (matcher2.find()) {
                String string = matcher.group();
                int n2 = string.indexOf("_");
                if (n2 <= 0) continue block0;
                if (n2 >= string.length()) {
                    matcher2 = matcher;
                    continue;
                }
                String string2 = s.getOrDefault(string, "");
                a2 = a2.replaceAll(Pattern.quote(string), Matcher.quoteReplacement(string2));
                v.add((Object)string);
                continue block0;
            }
            break;
        }
        return a2.replace("&", "\u00a7");
    }

    public jn() {
        jn a2;
    }
}

