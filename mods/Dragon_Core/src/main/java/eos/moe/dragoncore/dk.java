/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ah;
import eos.moe.dragoncore.be;
import eos.moe.dragoncore.bj;
import eos.moe.dragoncore.cd;
import eos.moe.dragoncore.df;
import eos.moe.dragoncore.dm;
import eos.moe.dragoncore.eg;
import eos.moe.dragoncore.he;
import eos.moe.dragoncore.hj;
import eos.moe.dragoncore.jd;
import eos.moe.dragoncore.je;
import eos.moe.dragoncore.jn;
import eos.moe.dragoncore.ko;
import eos.moe.dragoncore.mo;
import eos.moe.dragoncore.qf;
import eos.moe.dragoncore.rf;
import eos.moe.dragoncore.rh;
import eos.moe.dragoncore.sh;
import eos.moe.dragoncore.u;
import eos.moe.dragoncore.uf;
import eos.moe.dragoncore.wf;
import eos.moe.dragoncore.wh;
import eos.moe.dragoncore.xj;
import eos.moe.dragoncore.yj;
import eos.moe.dragoncore.yn;
import eos.moe.dragoncore.zf;
import eos.moe.dragoncore.zh;
import eos.moe.dragoncore.zi;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class dk {
    public Map<String, zi> k = new HashMap<String, zi>();
    public Map<String, Class<? extends je>> ALLATORIxDEMO = new HashMap<String, Class<? extends je>>();

    public dk() {
        dk a2;
        a2.ALLATORIxDEMO(new zi("PI", Math.PI));
        a2.ALLATORIxDEMO(new zi("E", Math.E));
        a2.ALLATORIxDEMO.put("floor", cd.class);
        a2.ALLATORIxDEMO.put("round", be.class);
        a2.ALLATORIxDEMO.put("ceil", dm.class);
        a2.ALLATORIxDEMO.put("trunc", bj.class);
        a2.ALLATORIxDEMO.put("clamp", ko.class);
        a2.ALLATORIxDEMO.put("max", wh.class);
        a2.ALLATORIxDEMO.put("min", qf.class);
        a2.ALLATORIxDEMO.put("abs", yn.class);
        a2.ALLATORIxDEMO.put("cos", rh.class);
        a2.ALLATORIxDEMO.put("sin", rf.class);
        a2.ALLATORIxDEMO.put("exp", xj.class);
        a2.ALLATORIxDEMO.put("ln", zf.class);
        a2.ALLATORIxDEMO.put("sqrt", wf.class);
        a2.ALLATORIxDEMO.put("mod", jn.class);
        a2.ALLATORIxDEMO.put("pow", uf.class);
        a2.ALLATORIxDEMO.put("lerp", hj.class);
        a2.ALLATORIxDEMO.put("lerprotate", yj.class);
        a2.ALLATORIxDEMO.put("random", df.class);
    }

    public void ALLATORIxDEMO(zi a2) {
        dk a3;
        a3.k.put(a2.ALLATORIxDEMO(), a2);
    }

    public u ALLATORIxDEMO(String a2) throws Exception {
        dk a3;
        return a3.c(a3.ALLATORIxDEMO(a3.ALLATORIxDEMO(a2)));
    }

    public String[] ALLATORIxDEMO(String a2) throws Exception {
        if (!a2.matches("^[\\w\\d\\s_+-/*%^&|<>=!?:.,()]+$")) {
            throw new Exception("Given expression '" + a2 + "' contains illegal characters!");
        }
        a2 = a2.replaceAll("\\s+", "");
        String[] a3 = a2.split("(?!^)");
        int a4 = 0;
        int a5 = 0;
        for (String a6 : a3) {
            if (a6.equals("(")) {
                ++a4;
                continue;
            }
            if (!a6.equals(")")) continue;
            ++a5;
        }
        if (a4 != a5) {
            throw new Exception("Given expression '" + a2 + "' has more uneven amount of parenthesis, there are " + a4 + " open and " + a5 + " closed!");
        }
        return a3;
    }

    public List<Object> ALLATORIxDEMO(String[] a2) {
        ArrayList<Object> a3 = new ArrayList<Object>();
        String a4 = "";
        int a5 = a2.length;
        block0: for (int a6 = 0; a6 < a5; ++a6) {
            int a7;
            int a8;
            dk a9;
            boolean a10;
            String a11 = a2[a6];
            boolean bl2 = a10 = a6 > 0 && a9.c(a2[a6 - 1] + a11);
            if (a9.c(a11) || a10 || a11.equals(",")) {
                if (a11.equals("-")) {
                    boolean a12;
                    a8 = a3.size();
                    a7 = a8 == 0 && a4.isEmpty() ? 1 : 0;
                    boolean bl3 = a12 = a8 > 0 && (a9.ALLATORIxDEMO(a3.get(a8 - 1)) || a3.get(a8 - 1).equals(",")) && a4.isEmpty();
                    if (a7 != 0 || a12) {
                        a4 = a4 + a11;
                        continue;
                    }
                }
                if (a10) {
                    a11 = a2[a6 - 1] + a11;
                    a4 = a4.substring(0, a4.length() - 1);
                }
                if (!a4.isEmpty()) {
                    a3.add(a4);
                    a4 = "";
                }
                a3.add(a11);
                continue;
            }
            if (a11.equals("(")) {
                if (!a4.isEmpty()) {
                    a3.add(a4);
                    a4 = "";
                }
                a8 = 1;
                for (a7 = a6 + 1; a7 < a5; ++a7) {
                    String a13 = a2[a7];
                    if (a13.equals("(")) {
                        ++a8;
                    } else if (a13.equals(")")) {
                        --a8;
                    }
                    if (a8 == 0) {
                        a3.add(a9.ALLATORIxDEMO(a4.split("(?!^)")));
                        a6 = a7;
                        a4 = "";
                        continue block0;
                    }
                    a4 = a4 + a13;
                }
                continue;
            }
            a4 = a4 + a11;
        }
        if (!a4.isEmpty()) {
            a3.add(a4);
        }
        return a3;
    }

    public u c(List<Object> a2) throws Exception {
        zh a3;
        int a4;
        dk a5;
        u a6 = a5.ALLATORIxDEMO(a2);
        if (a6 != null) {
            return a6;
        }
        int a7 = a2.size();
        if (a7 == 1) {
            return a5.ALLATORIxDEMO(a2.get(0));
        }
        if (a7 == 2) {
            Object a8 = a2.get(0);
            Object a9 = a2.get(1);
            if ((a5.c(a8) || a8.equals("-")) && a9 instanceof List) {
                return a5.ALLATORIxDEMO((String)a8, (List)a9);
            }
        }
        int a10 = a4 = a5.c(a2);
        while (a4 != -1) {
            int a11 = a5.c(a2, a4 - 1);
            if (a11 != -1) {
                a3 = a5.ALLATORIxDEMO(a2.get(a11));
                zh a12 = a5.ALLATORIxDEMO(a2.get(a4));
                if (a12.k > a3.k) {
                    u a13 = a5.c(a2.subList(0, a11));
                    u a14 = a5.c(a2.subList(a11 + 1, a7));
                    return new ah(a3, a13, a14);
                }
                if (a3.k > a12.k) {
                    zh a15 = a5.ALLATORIxDEMO(a2.get(a10));
                    if (a15.k < a3.k) {
                        u a16 = a5.c(a2.subList(0, a10));
                        u a17 = a5.c(a2.subList(a10 + 1, a7));
                        return new ah(a15, a16, a17);
                    }
                    u a18 = a5.c(a2.subList(0, a4));
                    u a19 = a5.c(a2.subList(a4 + 1, a7));
                    return new ah(a12, a18, a19);
                }
            }
            a4 = a11;
        }
        a3 = a5.ALLATORIxDEMO(a2.get(a10));
        return new ah(a3, a5.c(a2.subList(0, a10)), a5.c(a2.subList(a10 + 1, a7)));
    }

    public int c(List<Object> a2) {
        dk a3;
        return a3.c(a2, a2.size() - 1);
    }

    public int c(List<Object> a2, int a3) {
        for (int a4 = a3; a4 >= 0; --a4) {
            dk a5;
            Object a6 = a2.get(a4);
            if (!a5.ALLATORIxDEMO(a6)) continue;
            return a4;
        }
        return -1;
    }

    public int ALLATORIxDEMO(List<Object> a2) {
        dk a3;
        return a3.ALLATORIxDEMO(a2, 0);
    }

    public int ALLATORIxDEMO(List<Object> a2, int a3) {
        int a4 = a2.size();
        for (int a5 = a3; a5 < a4; ++a5) {
            dk a6;
            Object a7 = a2.get(a5);
            if (!a6.ALLATORIxDEMO(a7)) continue;
            return a5;
        }
        return -1;
    }

    public u ALLATORIxDEMO(List<Object> a2) throws Exception {
        int a3 = -1;
        int a4 = 0;
        int a5 = -1;
        int a6 = 0;
        int a7 = a2.size();
        for (int a8 = 0; a8 < a7; ++a8) {
            Object a9 = a2.get(a8);
            if (!(a9 instanceof String)) continue;
            if (a9.equals("?")) {
                if (a3 == -1) {
                    a3 = a8;
                }
                ++a4;
                continue;
            }
            if (!a9.equals(":")) continue;
            if (a6 + 1 == a4 && a5 == -1) {
                a5 = a8;
            }
            ++a6;
        }
        if (a4 == a6 && a3 > 0 && a3 + 1 < a5 && a5 < a7 - 1) {
            dk a10;
            return new jd(a10.c(a2.subList(0, a3)), a10.c(a2.subList(a3 + 1, a5)), a10.c(a2.subList(a5 + 1, a7)));
        }
        return null;
    }

    public u ALLATORIxDEMO(String a2, List<Object> a3) throws Exception {
        dk a5;
        if (a2.equals("!")) {
            return new he(a5.c(a3));
        }
        if (a2.startsWith("!") && a2.length() > 1) {
            return new he(a5.ALLATORIxDEMO(a2.substring(1), a3));
        }
        if (a2.equals("-")) {
            return new sh(a5.c(a3));
        }
        if (a2.startsWith("-") && a2.length() > 1) {
            return new sh(a5.ALLATORIxDEMO(a2.substring(1), a3));
        }
        if (!a5.ALLATORIxDEMO.containsKey(a2)) {
            throw new Exception("Function '" + a2 + "' couldn't be found!");
        }
        ArrayList<u> a6 = new ArrayList<u>();
        ArrayList<Object> a7 = new ArrayList<Object>();
        for (Object object : a3) {
            if (object.equals(",")) {
                a6.add(a5.c(a7));
                a7.clear();
                continue;
            }
            a7.add(object);
        }
        if (!a7.isEmpty()) {
            a6.add(a5.c(a7));
        }
        Class<? extends je> a8 = a5.ALLATORIxDEMO.get(a2);
        Constructor<? extends je> constructor = a8.getConstructor(u[].class, String.class);
        je a9 = constructor.newInstance(a6.toArray(new u[a6.size()]), a2);
        return a9;
    }

    public u ALLATORIxDEMO(Object a2) throws Exception {
        dk a3;
        if (a2 instanceof String) {
            String a4 = (String)a2;
            if (a4.startsWith("!")) {
                return new he(a3.ALLATORIxDEMO((Object)a4.substring(1)));
            }
            if (a3.ALLATORIxDEMO(a4)) {
                return new eg(Double.parseDouble(a4));
            }
            if (a3.c((Object)a4)) {
                if (a4.startsWith("-")) {
                    u a5 = a3.ALLATORIxDEMO(a4 = a4.substring(1));
                    if (a5 != null) {
                        return new sh(a5);
                    }
                } else {
                    u a6 = a3.ALLATORIxDEMO(a4);
                    if (a6 != null) {
                        return a6;
                    }
                }
            }
        } else if (a2 instanceof List) {
            return new mo(a3.c((List)a2));
        }
        throw new Exception("Given object couldn't be converted to value! " + a2);
    }

    public zi ALLATORIxDEMO(String a2) {
        dk a3;
        return a3.k.get(a2);
    }

    public zh ALLATORIxDEMO(Object a2) throws Exception {
        for (zh a3 : zh.values()) {
            if (!a3.y.equals(a2)) continue;
            return a3;
        }
        throw new Exception("There is no such operator '" + a2 + "'!");
    }

    public boolean c(Object a2) {
        dk a3;
        return a2 instanceof String && !a3.ALLATORIxDEMO((String)a2) && !a3.c((String)a2);
    }

    public boolean ALLATORIxDEMO(Object a2) {
        dk a3;
        return a2 instanceof String && a3.c((String)a2);
    }

    public boolean c(String a2) {
        return zh.o.contains(a2) || a2.equals("?") || a2.equals(":");
    }

    public boolean ALLATORIxDEMO(String a2) {
        return a2.matches("^-?\\d+(\\.\\d+)?$");
    }
}

