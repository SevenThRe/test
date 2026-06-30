/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.do;
import eos.moe.dragoncore.ea;
import eos.moe.dragoncore.ex;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.yz;
import java.util.ArrayList;
import java.util.List;

public class hy {
    private final String o;
    private final List<yz> y;
    private final List<yz> k;
    private final List<yz> ALLATORIxDEMO;

    public hy(String a2, List<yz> a3, List<yz> a4, List<yz> a5) {
        hy a6;
        a6.o = a2;
        a6.y = a3;
        a6.k = a4;
        a6.ALLATORIxDEMO = a5;
    }

    public static qd<yz, yz> ALLATORIxDEMO(List<yz> a2, long a3) {
        if (a2 == null) {
            return null;
        }
        for (int a4 = 0; a4 < a2.size(); ++a4) {
            yz a5 = a2.get(a4);
            if (a4 == 0 && (long)a5.ALLATORIxDEMO() > a3) {
                return qd.ALLATORIxDEMO(null, a5);
            }
            if (a4 == a2.size() - 1) {
                return qd.ALLATORIxDEMO(a5, null);
            }
            if ((long)a5.ALLATORIxDEMO() > a3 || (long)a2.get(a4 + 1).ALLATORIxDEMO() <= a3) continue;
            return qd.ALLATORIxDEMO(a5, a2.get(a4 + 1));
        }
        return null;
    }

    public static List<yz> ALLATORIxDEMO(List<yz> a2, long a3) {
        if (a2 == null) {
            return null;
        }
        if (a2.size() == 1) {
            return new ArrayList<yz>(a2);
        }
        for (int a4 = 0; a4 < a2.size(); ++a4) {
            yz a5 = a2.get(a4);
            if (a4 == 0 && (long)a5.ALLATORIxDEMO() > a3) {
                return Lists.newArrayList((Object[])new yz[]{a5});
            }
            if (a4 == a2.size() - 1) {
                return Lists.newArrayList((Object[])new yz[]{a5, null});
            }
            if ((long)a5.ALLATORIxDEMO() > a3 || (long)a2.get(a4 + 1).ALLATORIxDEMO() <= a3) continue;
            return Lists.newArrayList((Object[])new yz[]{a2.get(a4 - 1), a5, a2.get(a4 + 1), a2.get(a4 + 2)});
        }
        return null;
    }

    public static bax ALLATORIxDEMO(kq a2, qd<yz, yz> a3, bax a4, int a5) {
        int a6;
        bax a7;
        int a8;
        bax a9;
        yz a10 = a3.c();
        yz a11 = a3.ALLATORIxDEMO();
        if (a10 == null) {
            a9 = a4;
            a8 = 0;
        } else {
            a9 = a10.c();
            a8 = a10.ALLATORIxDEMO();
        }
        if (a11 == null) {
            a7 = a9;
            a6 = a2.ALLATORIxDEMO();
        } else {
            a7 = a11.ALLATORIxDEMO();
            a6 = a11.ALLATORIxDEMO();
        }
        return hy.ALLATORIxDEMO(a9, a7, a8, a6, a5);
    }

    public static String ALLATORIxDEMO(bax a2) {
        return a2.getX() + "," + a2.getY() + "," + a2.getZ();
    }

    public static bax ALLATORIxDEMO(kq a3, List<yz> a4, bax a5, int a6) {
        if (a4.size() == 1) {
            bax a7 = a4.get(0).c();
            return a7;
        }
        if (a4.size() == 2) {
            bax a8 = hy.ALLATORIxDEMO(a3, qd.ALLATORIxDEMO(a4.get(0), a4.get(1)), a5, a6);
            return a8;
        }
        boolean a9 = a4.stream().anyMatch(a2 -> "catmullrom".equals(a2.ALLATORIxDEMO()));
        if (!a9) {
            return hy.ALLATORIxDEMO(a3, qd.ALLATORIxDEMO(a4.get(1), a4.get(2)), a5, a6);
        }
        yz a10 = a4.get(1);
        yz a11 = a4.get(2);
        int a12 = a10.ALLATORIxDEMO();
        int a13 = a11.ALLATORIxDEMO();
        float a14 = a13 - a12 == 0 ? 1.0f : (float)(a6 - a12) / (float)(a13 - a12);
        float a15 = a14 * a14;
        float a16 = a15 * a14;
        bax a17 = new bax(0.0f, 0.0f, 0.0f);
        yz a18 = a4.get(0);
        yz a19 = a4.get(1);
        yz a20 = a4.get(2);
        yz a21 = a4.get(3);
        a17.x = (float)(0.5 * (double)(2.0f * a19.c().getX() + (a20.c().getX() - a18.c().getX()) * a14 + (2.0f * a18.c().getX() - 5.0f * a19.c().getX() + 4.0f * a20.c().getX() - a21.c().getX()) * a15 + (3.0f * a19.c().getX() - a18.c().getX() - 3.0f * a20.c().getX() + a21.c().getX()) * a16));
        a17.y = (float)(0.5 * (double)(2.0f * a19.c().getY() + (a20.c().getY() - a18.c().getY()) * a14 + (2.0f * a18.c().getY() - 5.0f * a19.c().getY() + 4.0f * a20.c().getY() - a21.c().getY()) * a15 + (3.0f * a19.c().getY() - a18.c().getY() - 3.0f * a20.c().getY() + a21.c().getY()) * a16));
        a17.z = (float)(0.5 * (double)(2.0f * a19.c().getZ() + (a20.c().getZ() - a18.c().getZ()) * a14 + (2.0f * a18.c().getZ() - 5.0f * a19.c().getZ() + 4.0f * a20.c().getZ() - a21.c().getZ()) * a15 + (3.0f * a19.c().getZ() - a18.c().getZ() - 3.0f * a20.c().getZ() + a21.c().getZ()) * a16));
        return a17;
    }

    public static bax ALLATORIxDEMO(bax a2, bax a3, int a4, int a5, int a6) {
        float a7 = a5 - a4 == 0 ? 1.0f : (float)(a6 - a4) / (float)(a5 - a4);
        float a8 = do.ALLATORIxDEMO(a7, a2.getX(), a3.getX());
        float a9 = do.ALLATORIxDEMO(a7, a2.getY(), a3.getY());
        float a10 = do.ALLATORIxDEMO(a7, a2.getZ(), a3.getZ());
        return new bax(a8, a9, a10);
    }

    public void ALLATORIxDEMO(kq a2, ea a3, AnimationModelRenderer a4, int a5) {
        bax a6;
        hy a7;
        List<yz> a8 = hy.ALLATORIxDEMO(a7.y, a5);
        if (a8 != null) {
            a6 = hy.ALLATORIxDEMO(a2, a8, new bax(0.0f, 0.0f, 0.0f), a5);
            ex.f(a4, a3, a6);
        }
        if ((a8 = hy.ALLATORIxDEMO(a7.k, a5)) != null) {
            a6 = hy.ALLATORIxDEMO(a2, a8, a4.getOffsets(), a5);
            ex.c(a4, a3, a6);
        }
        if ((a8 = hy.ALLATORIxDEMO(a7.ALLATORIxDEMO, a5)) != null) {
            a6 = a4.getScaleFactor();
            bax a9 = hy.ALLATORIxDEMO(a2, a8, a6, a5);
            ex.ALLATORIxDEMO(a4, a3, a9);
        }
    }

    public List<yz> f() {
        hy a2;
        return a2.k;
    }

    public List<yz> c() {
        hy a2;
        return a2.y;
    }

    public List<yz> ALLATORIxDEMO() {
        hy a2;
        return a2.ALLATORIxDEMO;
    }

    public String ALLATORIxDEMO() {
        hy a2;
        return a2.o;
    }
}

