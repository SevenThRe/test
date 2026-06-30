/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheLoader
 *  com.google.common.cache.LoadingCache
 */
package eos.moe.dragoncore;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import eos.moe.dragoncore.ii;
import eos.moe.dragoncore.jo;
import java.util.Collections;
import java.util.Stack;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class vk {
    private static LoadingCache<String, Double> o = CacheBuilder.newBuilder().expireAfterAccess(3L, TimeUnit.SECONDS).build((CacheLoader)new jo());
    private Stack<String> y = new Stack();
    private Stack<Character> k = new Stack();
    private int[] ALLATORIxDEMO = new int[]{0, 3, 2, 1, -1, 1, 0, 2};

    public vk() {
        vk a2;
    }

    public static int ALLATORIxDEMO(String a2) {
        return (int)vk.f(a2);
    }

    public static double f(String a2) {
        try {
            return (Double)o.get((Object)a2);
        }
        catch (ExecutionException a3) {
            return 0.0;
        }
    }

    public static double c(String a2) {
        if (a2.length() == 1) {
            try {
                return Integer.parseInt(a2);
            }
            catch (Exception a3) {
                return 0.0;
            }
        }
        double a4 = 0.0;
        vk a5 = new vk();
        try {
            a2 = vk.ALLATORIxDEMO(a2);
            a4 = a5.ALLATORIxDEMO(a2);
        }
        catch (Exception a6) {
            return -999.0;
        }
        return a4;
    }

    private static /* synthetic */ String ALLATORIxDEMO(String a2) {
        char[] a3 = a2.toCharArray();
        for (int a4 = 0; a4 < a3.length; ++a4) {
            if (a3[a4] != '-') continue;
            if (a4 == 0) {
                a3[a4] = 126;
                continue;
            }
            char a5 = a3[a4 - 1];
            if (a5 != '+' && a5 != '-' && a5 != '*' && a5 != '/' && a5 != '(' && a5 != 'E' && a5 != 'e') continue;
            a3[a4] = 126;
        }
        if (a3[0] == '~' || a3[1] == '(') {
            a3[0] = 45;
            return "0" + new String(a3);
        }
        return new String(a3);
    }

    public double ALLATORIxDEMO(String a2) {
        vk a3;
        Stack<String> a4 = new Stack<String>();
        a3.ALLATORIxDEMO(a2);
        Collections.reverse(a3.y);
        while (!a3.y.isEmpty()) {
            String a5 = a3.y.pop();
            if (!a3.ALLATORIxDEMO(a5.charAt(0))) {
                a5 = a5.replace("~", "-");
                a4.push(a5);
                continue;
            }
            String a6 = (String)a4.pop();
            String a7 = (String)a4.pop();
            a7 = a7.replace("~", "-");
            a6 = a6.replace("~", "-");
            String a8 = a3.ALLATORIxDEMO(a7, a6, a5.charAt(0));
            a4.push(a8);
        }
        return Double.valueOf((String)a4.pop());
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2) {
        vk a3;
        a3.k.push(Character.valueOf(','));
        char[] a4 = a2.toCharArray();
        int a5 = 0;
        int a6 = 0;
        for (int a7 = 0; a7 < a4.length; ++a7) {
            char a8 = a4[a7];
            if (a3.ALLATORIxDEMO(a8)) {
                if (a6 > 0) {
                    a3.y.push(new String(a4, a5, a6));
                }
                char a9 = a3.k.peek().charValue();
                if (a8 == ')') {
                    while (a3.k.peek().charValue() != '(') {
                        a3.y.push(String.valueOf(a3.k.pop()));
                    }
                    a3.k.pop();
                } else {
                    while (a8 != '(' && a9 != ',' && a3.ALLATORIxDEMO(a8, a9)) {
                        a3.y.push(String.valueOf(a3.k.pop()));
                        a9 = a3.k.peek().charValue();
                    }
                    a3.k.push(Character.valueOf(a8));
                }
                a6 = 0;
                a5 = a7 + 1;
                continue;
            }
            ++a6;
        }
        if (a6 > 1 || a6 == 1 && !a3.ALLATORIxDEMO(a4[a5])) {
            a3.y.push(new String(a4, a5, a6));
        }
        while (a3.k.peek().charValue() != ',') {
            a3.y.push(String.valueOf(a3.k.pop()));
        }
    }

    private /* synthetic */ boolean ALLATORIxDEMO(char a2) {
        return a2 == '+' || a2 == '-' || a2 == '*' || a2 == '/' || a2 == '(' || a2 == ')';
    }

    public boolean ALLATORIxDEMO(char a2, char a3) {
        vk a4;
        boolean a5 = false;
        if (a4.ALLATORIxDEMO[a3 - 40] >= a4.ALLATORIxDEMO[a2 - 40]) {
            a5 = true;
        }
        return a5;
    }

    private /* synthetic */ String ALLATORIxDEMO(String a2, String a3, char a4) {
        String a5 = "";
        switch (a4) {
            case '+': {
                a5 = String.valueOf(ii.x(a2, a3));
                break;
            }
            case '-': {
                a5 = String.valueOf(ii.f(a2, a3));
                break;
            }
            case '*': {
                a5 = String.valueOf(ii.c(a2, a3));
                break;
            }
            case '/': {
                a5 = String.valueOf(ii.ALLATORIxDEMO(a2, a3));
            }
        }
        return a5;
    }
}

