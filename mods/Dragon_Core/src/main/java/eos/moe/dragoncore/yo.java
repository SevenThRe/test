/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.b;
import eos.moe.dragoncore.d;
import eos.moe.dragoncore.tv;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class yo {
    private boolean k = false;
    private final List<d> ALLATORIxDEMO = new LinkedList<d>();

    public yo() {
        yo a2;
    }

    public void c(List<b> a2) {
        yo a3;
        for (d a4 : a3.ALLATORIxDEMO) {
            a4.c(a2);
        }
        a3.k = false;
        a3.ALLATORIxDEMO(a2);
        for (d a4 : a3.ALLATORIxDEMO) {
            a4.ALLATORIxDEMO(a2);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(List<b> a2) {
        ArrayList<b> a3 = new ArrayList<b>(a2);
        for (int a4 = 0; a4 < a3.size(); ++a4) {
            yo a5;
            b a6 = (b)a3.get(a4);
            boolean a7 = false;
            boolean a8 = true;
            boolean a9 = true;
            for (d a10 : a5.ALLATORIxDEMO) {
                Object a11 = a10.ALLATORIxDEMO(a6);
                if (a11 instanceof tv) {
                    switch ((tv)((Object)a11)) {
                        case b: {
                            a7 = true;
                            break;
                        }
                        case o: {
                            a5.k = true;
                            break;
                        }
                        case y: {
                            a9 = false;
                        }
                        case k: {
                            a8 = false;
                        }
                    }
                    continue;
                }
                if (!(a11 instanceof b)) continue;
                a6 = (b)a11;
            }
            if (!a9) break;
            if (a8 && !a7) {
                a5.ALLATORIxDEMO(a6);
            }
            for (d a10 : a5.ALLATORIxDEMO) {
                a10.ALLATORIxDEMO(a6);
            }
            if (a7) {
                a2.remove(a4);
            } else {
                a2.set(a4, a6);
            }
            if (a5.k) break;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(b a2) {
        for (Field a3 : yo.ALLATORIxDEMO(a2.getClass())) {
            yo a5;
            a3.setAccessible(true);
            Object a6 = a5.ALLATORIxDEMO(a3, a2);
            if (a6 instanceof b) {
                b a4 = (b)a6;
                boolean a7 = false;
                boolean a8 = true;
                boolean a9 = true;
                for (d d2 : a5.ALLATORIxDEMO) {
                    Object a11 = d2.ALLATORIxDEMO(a4);
                    if (a11 instanceof tv) {
                        switch ((tv)((Object)a11)) {
                            case b: {
                                a7 = true;
                                break;
                            }
                            case o: {
                                a5.k = true;
                                break;
                            }
                            case y: {
                                a9 = false;
                            }
                            case k: {
                                a8 = false;
                            }
                        }
                        continue;
                    }
                    if (!(a11 instanceof b)) continue;
                    a4 = (b)a11;
                }
                if (!a9) break;
                if (a8 && !a7) {
                    a5.ALLATORIxDEMO(a4);
                }
                for (d d3 : a5.ALLATORIxDEMO) {
                    d3.ALLATORIxDEMO(a4);
                }
                if (a7) {
                    a5.ALLATORIxDEMO(a3, a2, null);
                } else {
                    a5.ALLATORIxDEMO(a3, a2, a4);
                }
                if (!a5.k) continue;
                break;
            }
            if (a6 == null || !a6.getClass().isArray()) continue;
            Object[] a4 = (Object[])a6;
            ArrayList<b> a12 = new ArrayList<b>();
            for (Object object : a4) {
                if (!(object instanceof b)) continue;
                a12.add((b)object);
            }
            a5.ALLATORIxDEMO(a12);
            a5.ALLATORIxDEMO(a3, a2, a12.toArray(new b[0]));
        }
    }

    public static List<Field> ALLATORIxDEMO(Class<?> a2) {
        ArrayList<Field> a3 = new ArrayList<Field>();
        for (Class<?> a4 = a2; a4 != null; a4 = a4.getSuperclass()) {
            a3.addAll(Arrays.asList(a4.getDeclaredFields()));
        }
        return a3;
    }

    private /* synthetic */ Object ALLATORIxDEMO(Field a2, Object a3) {
        try {
            return a2.get(a3);
        }
        catch (Throwable throwable) {
            return null;
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(Field a2, Object a3, Object a4) {
        try {
            a2.set(a3, a4);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public boolean ALLATORIxDEMO() {
        yo a2;
        return a2.k;
    }

    public List<d> ALLATORIxDEMO() {
        yo a2;
        return a2.ALLATORIxDEMO;
    }
}

