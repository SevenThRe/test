/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kl;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.yd;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class nm {
    private static char s = (char)65535;
    private static Comparator<kl> g = (a2, a3) -> {
        if (a2.b > a3.b) {
            return 1;
        }
        if (a2.b < a3.b) {
            return -1;
        }
        return 0;
    };
    public Pattern t = Pattern.compile("([\"'])(?:(?=(\\\\?))\\2.)*?\\1", 8);
    public Pattern r;
    public Pattern x = Pattern.compile("[\\p{L}-]+|\\n|$");
    public Pattern v = Pattern.compile("\\b-?(?:0[xX][\\dA-Fa-f]+|0[bB][01]+|0[oO][0-7]+|\\d*\\.?\\d+(?:[Ee][+-]?\\d+)?(?:[fFbBdDlLsS])?|NaN|Infinity|true|false)\\b");
    public Pattern m = Pattern.compile("\\/\\*[\\s\\S]*?(?:\\*\\/|$)|\\/\\/.*|#.*");
    public String c;
    public List<kl> q = new ArrayList<kl>();
    public List<yd> b = new ArrayList<yd>();
    public int o;
    public int y;
    public int k = 1;
    public int ALLATORIxDEMO;

    public nm(String a2) {
        nm a3;
        a3.c = a2;
        a3.c.replaceAll("\\r?\\n|\\r", "\n");
        double d2 = 1.0;
    }

    public int ALLATORIxDEMO(double a2) {
        nm a3;
        return (int)((double)a3.o * a2);
    }

    public void ALLATORIxDEMO(String a2, double a3, double a4) {
        nm a5;
        a5.o = 10;
        String[] a6 = a5.c.split("\n");
        int a7 = 0;
        for (String a8 : a6) {
            StringBuilder a9 = new StringBuilder();
            Matcher a10 = a5.x.matcher(a8);
            int a11 = 0;
            while (a10.find()) {
                String a12 = a8.substring(a11, a10.start());
                StringBuilder stringBuilder = new StringBuilder();
                if ((double)ol.ALLATORIxDEMO(stringBuilder.append((Object)a9).append(a12).toString(), a2, false) > a3 - 10.0) {
                    a5.b.add(new yd(a5, a9.toString(), a7, a7 + a9.length()));
                    a7 += a9.length();
                    a9 = new StringBuilder();
                }
                a9.append(a12);
                a11 = a10.start();
            }
            a5.b.add(new yd(a5, a9.toString(), a7, a7 + a9.length() + 1));
            a7 += a9.length() + 1;
        }
        a5.ALLATORIxDEMO = a5.b.size();
        a5.y = a5.ALLATORIxDEMO * a5.o;
        a5.k = (int)Math.max(a4 / (double)a5.o, 1.0);
    }

    public void ALLATORIxDEMO() {
        nm a2;
        kl a3 = null;
        int a4 = 0;
        while ((a3 = a2.ALLATORIxDEMO(a4)) != null) {
            a2.q.add(a3);
            a4 = a3.o;
        }
    }

    private /* synthetic */ kl ALLATORIxDEMO(int a2) {
        kl a3;
        nm a4;
        kl a5 = null;
        String a6 = a4.c.substring(a2);
        Matcher a7 = a4.v.matcher(a6);
        if (a7.find()) {
            a5 = new kl(a4, a7.start(), a7.end(), '6', 0);
        }
        if ((a7 = a4.r.matcher(a6)).find() && a4.ALLATORIxDEMO(a5, a3 = new kl(a4, a7.start(), a7.end(), '2', 0))) {
            a5 = a3;
        }
        if ((a7 = a4.t.matcher(a6)).find() && a4.ALLATORIxDEMO(a5, a3 = new kl(a4, a7.start(), a7.end(), '4', 7))) {
            a5 = a3;
        }
        if ((a7 = a4.m.matcher(a6)).find() && a4.ALLATORIxDEMO(a5, a3 = new kl(a4, a7.start(), a7.end(), '8', 7))) {
            a5 = a3;
        }
        if (a5 != null) {
            a3 = a5;
            a3.b += a2;
            kl a8 = a5;
            a8.o += a2;
        }
        return a5;
    }

    public boolean ALLATORIxDEMO(kl a2, kl a3) {
        return a2 == null || a2.b > a3.b;
    }

    public void ALLATORIxDEMO(int a2, int a3, char a4, int a5) {
        nm a6;
        if (!a6.ALLATORIxDEMO(a2, a3, a5)) {
            return;
        }
        a6.q.add(new kl(a6, a2, a3, a4, a5));
    }

    private /* synthetic */ boolean ALLATORIxDEMO(int a2, int a3, int a4) {
        nm a5;
        ArrayList<kl> a6 = new ArrayList<kl>();
        for (kl a7 : a5.q) {
            if (!(a2 >= a7.b && a2 <= a7.o || a3 >= a7.b && a3 <= a7.o) && (a2 >= a7.b || a3 <= a7.b)) continue;
            if (a4 < a7.y || a4 == a7.y && a7.b <= a2) {
                return false;
            }
            a6.add(a7);
        }
        a5.q.removeAll(a6);
        return true;
    }

    public String ALLATORIxDEMO() {
        nm a2;
        StringBuilder a3 = new StringBuilder(a2.c);
        for (kl a4 : a2.q) {
            a3.insert(a4.b, Character.toString('\uffff') + a4.k);
            a3.insert(a4.o, Character.toString('\uffff') + 'r');
        }
        return a3.toString();
    }
}

