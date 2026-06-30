/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ez;
import eos.moe.dragoncore.nx;
import eos.moe.dragoncore.vx;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class kv {
    private final String b;
    private int o = 0;
    private int y = 0;
    private int k = 0;
    private int ALLATORIxDEMO = 0;

    public kv(String a2) {
        kv a3;
        a3.b = a2;
    }

    public boolean ALLATORIxDEMO() {
        kv a2;
        return a2.o < a2.b.length();
    }

    public vx ALLATORIxDEMO() {
        kv a2;
        while (a2.o < a2.b.length()) {
            Object a3;
            if (a2.b.length() > a2.o + 1 && (a3 = ez.ALLATORIxDEMO(a2.b.substring(a2.o, a2.o + 2))) != null) {
                a2.o += 2;
                return new vx((ez)((Object)a3), a2.ALLATORIxDEMO());
            }
            a3 = a2.ALLATORIxDEMO(a2.o);
            ez a4 = ez.ALLATORIxDEMO((String)a3);
            if (a4 != null) {
                ++a2.o;
                return new vx(a4, a2.ALLATORIxDEMO());
            }
            if (((String)a3).equals("`")) {
                int a5;
                int a6 = a2.o;
                for (a5 = a2.o + 1; a5 < a2.b.length() && !a2.ALLATORIxDEMO(a5).equals("`"); ++a5) {
                }
                a2.o = ++a5;
                return new vx(ez.c, a2.b.substring(a6 + 1, a5 - 1), a2.ALLATORIxDEMO());
            }
            if (((String)a3).equals("\"")) {
                int a7;
                int a8 = a2.o;
                for (a7 = a2.o + 1; a7 < a2.b.length() && !a2.ALLATORIxDEMO(a7).equals("\""); ++a7) {
                }
                a2.o = ++a7;
                return new vx(ez.q, a2.b.substring(a8 + 1, a7 - 1), a2.ALLATORIxDEMO());
            }
            if (((String)a3).equals("'")) {
                int a9;
                int a10 = a2.o;
                for (a9 = a2.o + 1; a9 < a2.b.length() && !a2.ALLATORIxDEMO(a9).equals("'"); ++a9) {
                }
                a2.o = ++a9;
                return new vx(ez.q, a2.b.substring(a10 + 1, a9 - 1), a2.ALLATORIxDEMO());
            }
            if (((String)a3).equals("@")) {
                int a11;
                int a12 = a2.o;
                for (a11 = a2.o + 1; a11 < a2.b.length() && !a2.ALLATORIxDEMO(a11).equals("@"); ++a11) {
                }
                a2.o = ++a11;
                return new vx(ez.q, a2.b.substring(a12 + 1, a11 - 1), a2.ALLATORIxDEMO());
            }
            if (((String)a3).equals("$")) {
                int a13;
                for (a13 = a2.o + 1; a13 < a2.b.length() && (Character.isLetterOrDigit(a2.ALLATORIxDEMO(a13).charAt(0)) || a2.ALLATORIxDEMO(a13).equals("_") || a2.ALLATORIxDEMO(a13).equals(".")); ++a13) {
                }
                String a14 = a2.b.substring(a2.o, a13).toLowerCase();
                ez a15 = ez.o;
                a2.o = a13;
                return new vx(a15, a14, a2.ALLATORIxDEMO());
            }
            if (Character.isLetter(((String)a3).charAt(0))) {
                int a16;
                for (a16 = a2.o + 1; a16 < a2.b.length() && (Character.isLetterOrDigit(a2.ALLATORIxDEMO(a16).charAt(0)) || a2.ALLATORIxDEMO(a16).equals("_") || a2.ALLATORIxDEMO(a16).equals(".")); ++a16) {
                }
                String a17 = a2.b.substring(a2.o, a16).toLowerCase();
                ez a18 = ez.ALLATORIxDEMO(a17);
                if (a18 == null) {
                    a18 = ez.o;
                    if (a17.equalsIgnoreCase("w")) {
                        a17 = "func.\u53d6\u5c4f\u5e55\u5bbd\u5ea6";
                    } else if (a17.equalsIgnoreCase("h")) {
                        a17 = "func.\u53d6\u5c4f\u5e55\u9ad8\u5ea6";
                    } else if (!a17.contains(".")) {
                        a17 = "func." + a17;
                    }
                }
                a2.o = a16;
                return new vx(a18, a17, a2.ALLATORIxDEMO());
            }
            if (Character.isDigit(((String)a3).charAt(0))) {
                int a19 = a2.o;
                int a20 = a2.o + 1;
                boolean a21 = false;
                int a22 = -1;
                boolean a23 = false;
                while (a20 < a2.b.length()) {
                    String a24 = a2.ALLATORIxDEMO(a20);
                    if (Character.isDigit(a24.charAt(0))) {
                        ++a20;
                        continue;
                    }
                    if (a24.equals(".") && !a21) {
                        a21 = true;
                        ++a20;
                        continue;
                    }
                    if (a24.equalsIgnoreCase("e") && a22 == -1) {
                        a22 = ++a20;
                        continue;
                    }
                    if (!a24.equals("-") || a22 != a20 || a23) break;
                    ++a20;
                    a23 = true;
                }
                a2.o = a20;
                return new vx(ez.b, a2.b.substring(a19, a20), a2.ALLATORIxDEMO());
            }
            if (((String)a3).equals("\n") || ((String)a3).equals("\r")) {
                ++a2.y;
            }
            ++a2.o;
        }
        return new vx(ez.y, a2.ALLATORIxDEMO());
    }

    public void c() {
        kv a2;
        a2.k = a2.o;
        a2.ALLATORIxDEMO = a2.y;
    }

    public nx ALLATORIxDEMO() {
        kv a2;
        return new nx(a2.ALLATORIxDEMO, a2.y, a2.k, a2.o);
    }

    public String ALLATORIxDEMO(String a2, int a3) {
        return a2.substring(a3, a3 + 1);
    }

    public String ALLATORIxDEMO(int a2) {
        kv a3;
        return a3.b.substring(a2, a2 + 1);
    }

    public void ALLATORIxDEMO() {
        a.o = 0;
        a.y = 0;
        a.k = 0;
        a.ALLATORIxDEMO = 0;
    }

    public String ALLATORIxDEMO() {
        kv a2;
        return a2.b;
    }
}

