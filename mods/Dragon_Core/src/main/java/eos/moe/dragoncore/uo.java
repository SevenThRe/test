/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.hu;
import eos.moe.dragoncore.ju;
import eos.moe.dragoncore.oy;
import eos.moe.dragoncore.po;
import eos.moe.dragoncore.qu;
import eos.moe.dragoncore.sx;
import eos.moe.dragoncore.tx;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.util.ResourceLocation;

public class uo {
    private static Pattern w = Pattern.compile("(v( (\\-){0,1}\\d+(\\.\\d+)?){3,99} *\\n)|(v( (\\-){0,1}\\d+(\\.\\d+)?){3,99} *$)");
    private static Pattern a = Pattern.compile("(vn( (\\-){0,1}\\d+(\\.\\d+)?){3,99} *\\n)|(vn( (\\-){0,1}\\d+(\\.\\d+)?){3,99} *$)");
    private static Pattern e = Pattern.compile("(vt( (\\-){0,1}\\d+\\.\\d+){2,99} *\\n)|(vt( (\\-){0,1}\\d+(\\.\\d+)?){2,99} *$)");
    private static Pattern n = Pattern.compile("(f( \\d+/\\d+/\\d+){3,99} *\\n)|(f( \\d+/\\d+/\\d+){3,99} *$)");
    private static Pattern j = Pattern.compile("(f( \\d+/\\d+){3,99} *\\n)|(f( \\d+/\\d+){3,99} *$)");
    private static Pattern i = Pattern.compile("(f( \\d+//\\d+){3,99} *\\n)|(f( \\d+//\\d+){3,99} *$)");
    private static Pattern l = Pattern.compile("(f( \\d+){3,99} *\\n)|(f( \\d+){3,99} *$)");
    private static Pattern z = Pattern.compile("([go]( [\\w\\d\\.]+) *\\n)|([go]( [\\w\\d\\.]+) *$)");
    private static Matcher s;
    private static Matcher g;
    private static Matcher t;
    private static Matcher r;
    private static Matcher x;
    private static Matcher v;
    private static Matcher m;
    private static Matcher c;
    public ArrayList<hu> q = new ArrayList();
    private ArrayList<hu> b = new ArrayList();
    private ArrayList<tx> o = new ArrayList();
    private sx y;
    private ResourceLocation k;
    private ArrayList<ju> ALLATORIxDEMO = new ArrayList();

    public uo(ResourceLocation a2) throws qu {
        uo a3;
        a3.k = a2;
    }

    private static synchronized /* synthetic */ boolean s(String a2) {
        if (s != null) {
            s.reset();
        }
        s = w.matcher(a2);
        return s.matches();
    }

    private static synchronized /* synthetic */ boolean w(String a2) {
        if (g != null) {
            g.reset();
        }
        g = a.matcher(a2);
        return g.matches();
    }

    private static synchronized /* synthetic */ boolean z(String a2) {
        if (t != null) {
            t.reset();
        }
        t = e.matcher(a2);
        return t.matches();
    }

    private static synchronized /* synthetic */ boolean k(String a2) {
        if (r != null) {
            r.reset();
        }
        r = n.matcher(a2);
        return r.matches();
    }

    private static synchronized /* synthetic */ boolean d(String a2) {
        if (x != null) {
            x.reset();
        }
        x = j.matcher(a2);
        return x.matches();
    }

    private static synchronized /* synthetic */ boolean x(String a2) {
        if (v != null) {
            v.reset();
        }
        v = i.matcher(a2);
        return v.matches();
    }

    private static synchronized /* synthetic */ boolean f(String a2) {
        if (m != null) {
            m.reset();
        }
        m = l.matcher(a2);
        return m.matches();
    }

    private static synchronized /* synthetic */ boolean c(String a2) {
        return uo.k(a2) || uo.d(a2) || uo.x(a2) || uo.f(a2);
    }

    private static synchronized /* synthetic */ boolean ALLATORIxDEMO(String a2) {
        if (c != null) {
            c.reset();
        }
        c = z.matcher(a2);
        return c.matches();
    }

    public po ALLATORIxDEMO() throws qu {
        uo a2;
        int a3 = 0;
        po a4 = new po();
        try (IResource a5 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a2.k);
             BufferedReader a6 = new BufferedReader(new InputStreamReader(a5.func_110527_b()));){
            String a7;
            while ((a7 = a6.readLine()) != null) {
                Object a8;
                ++a3;
                if ((a7 = a7.replaceAll("\\s+", " ").trim()).startsWith("#") || a7.length() == 0) continue;
                if (a7.startsWith("v ")) {
                    a8 = a2.c(a7, a3);
                    if (a8 == null) continue;
                    a2.q.add((hu)a8);
                    continue;
                }
                if (a7.startsWith("vn ")) {
                    a8 = a2.ALLATORIxDEMO(a7, a3);
                    if (a8 == null) continue;
                    a2.b.add((hu)a8);
                    continue;
                }
                if (a7.startsWith("vt ")) {
                    a8 = a2.ALLATORIxDEMO(a7, a3);
                    if (a8 == null) continue;
                    a2.o.add((tx)a8);
                    continue;
                }
                if (a7.startsWith("f ")) {
                    if (a2.y == null) {
                        a2.y = new sx("Default");
                    }
                    if ((a8 = a2.ALLATORIxDEMO(a7, a3)) == null) continue;
                    a2.y.k.add((oy)a8);
                    continue;
                }
                if (!(a7.startsWith("g ") | a7.startsWith("o "))) continue;
                a8 = a2.ALLATORIxDEMO(a7, a3);
                if (a8 != null && a2.y != null) {
                    a2.ALLATORIxDEMO.add(new ju(a4, a2.y));
                }
                a2.y = a8;
            }
            a2.ALLATORIxDEMO.add(new ju(a4, a2.y));
        }
        catch (IOException a9) {
            throw new qu("IO Exception", a9);
        }
        a4.ALLATORIxDEMO(a2.ALLATORIxDEMO);
        return a4;
    }

    private /* synthetic */ hu c(String a2, int a3) throws qu {
        block5: {
            uo a4;
            if (uo.s(a2)) {
                a2 = a2.substring(a2.indexOf(" ") + 1);
                String[] a5 = a2.split(" ");
                try {
                    if (a5.length == 2) {
                        return new hu(Float.parseFloat(a5[0]), Float.parseFloat(a5[1]));
                    }
                    if (a5.length >= 3) {
                        return new hu(Float.parseFloat(a5[0]), Float.parseFloat(a5[1]), Float.parseFloat(a5[2]));
                    }
                    break block5;
                }
                catch (NumberFormatException a6) {
                    throw new qu(String.format("\u6587\u672c\u8f6c\u6570\u5b57\u51fa\u73b0\u5f02\u5e38 line %d", a3), a6);
                }
            }
            throw new qu("\u683c\u5f0f\u9519\u8bef ('" + a2 + "', line " + a3 + ") \u6587\u4ef6: '" + a4.k + "' - Incorrect format");
        }
        return null;
    }

    private /* synthetic */ hu ALLATORIxDEMO(String a2, int a3) throws qu {
        block4: {
            uo a4;
            if (uo.w(a2)) {
                a2 = a2.substring(a2.indexOf(" ") + 1);
                String[] a5 = a2.split(" ");
                try {
                    if (a5.length == 3) {
                        return new hu(Float.parseFloat(a5[0]), Float.parseFloat(a5[1]), Float.parseFloat(a5[2]));
                    }
                    break block4;
                }
                catch (NumberFormatException a6) {
                    throw new qu(String.format("\u6587\u672c\u8f6c\u6570\u5b57\u51fa\u73b0\u5f02\u5e38 line: %d", a3), a6);
                }
            }
            throw new qu("\u683c\u5f0f\u9519\u8bef ('" + a2 + "', line " + a3 + ") \u6587\u4ef6: '" + a4.k);
        }
        return null;
    }

    private /* synthetic */ tx ALLATORIxDEMO(String a2, int a3) throws qu {
        block5: {
            uo a4;
            if (uo.z(a2)) {
                a2 = a2.substring(a2.indexOf(" ") + 1);
                String[] a5 = a2.split(" ");
                try {
                    if (a5.length == 2) {
                        return new tx(Float.parseFloat(a5[0]), 1.0f - Float.parseFloat(a5[1]));
                    }
                    if (a5.length == 3) {
                        return new tx(Float.parseFloat(a5[0]), 1.0f - Float.parseFloat(a5[1]), Float.parseFloat(a5[2]));
                    }
                    break block5;
                }
                catch (NumberFormatException a6) {
                    throw new qu(String.format("\u6587\u672c\u8f6c\u6570\u5b57\u51fa\u73b0\u5f02\u5e38 line: %d", a3), a6);
                }
            }
            throw new qu("\u683c\u5f0f\u9519\u8bef ('" + a2 + "', line " + a3 + ") \u6587\u4ef6: '" + a4.k);
        }
        return null;
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    private /* synthetic */ oy ALLATORIxDEMO(String a2, int a3) throws qu {
        uo a4;
        if (!uo.c(a2)) throw new qu("\u683c\u5f0f\u9519\u8bef ('" + a2 + "', line " + a3 + ") \u6587\u4ef6: '" + a4.k);
        oy a5 = new oy();
        String a6 = a2.substring(a2.indexOf(" ") + 1);
        String[] a7 = a6.split(" ");
        if (a7.length == 3) {
            a4.y.ALLATORIxDEMO = 4;
        } else if (a7.length >= 4) {
            a4.y.ALLATORIxDEMO = 7;
        }
        if (uo.k(a2)) {
            a5.o = new hu[a7.length];
            a5.ALLATORIxDEMO = new tx[a7.length];
            a5.y = new hu[a7.length];
            for (int a8 = 0; a8 < a7.length; ++a8) {
                String[] a9 = a7[a8].split("/");
                a5.o[a8] = a4.q.get(Integer.parseInt(a9[0]) - 1);
                a5.ALLATORIxDEMO[a8] = a4.o.get(Integer.parseInt(a9[1]) - 1);
                a5.y[a8] = a4.b.get(Integer.parseInt(a9[2]) - 1);
            }
            a5.k = a5.ALLATORIxDEMO();
            return a5;
        } else if (uo.d(a2)) {
            a5.o = new hu[a7.length];
            a5.ALLATORIxDEMO = new tx[a7.length];
            for (int a10 = 0; a10 < a7.length; ++a10) {
                String[] a11 = a7[a10].split("/");
                a5.o[a10] = a4.q.get(Integer.parseInt(a11[0]) - 1);
                a5.ALLATORIxDEMO[a10] = a4.o.get(Integer.parseInt(a11[1]) - 1);
            }
            a5.k = a5.ALLATORIxDEMO();
            return a5;
        } else if (uo.x(a2)) {
            a5.o = new hu[a7.length];
            a5.y = new hu[a7.length];
            for (int a12 = 0; a12 < a7.length; ++a12) {
                String[] a13 = a7[a12].split("//");
                a5.o[a12] = a4.q.get(Integer.parseInt(a13[0]) - 1);
                a5.y[a12] = a4.b.get(Integer.parseInt(a13[1]) - 1);
            }
            a5.k = a5.ALLATORIxDEMO();
            return a5;
        } else {
            if (!uo.f(a2)) throw new qu("\u683c\u5f0f\u9519\u8bef ('" + a2 + "', line " + a3 + ") \u6587\u4ef6: '" + a4.k);
            a5.o = new hu[a7.length];
            for (int a14 = 0; a14 < a7.length; ++a14) {
                a5.o[a14] = a4.q.get(Integer.parseInt(a7[a14]) - 1);
            }
            a5.k = a5.ALLATORIxDEMO();
        }
        return a5;
    }

    private /* synthetic */ sx ALLATORIxDEMO(String a2, int a3) throws qu {
        sx a4 = null;
        if (uo.ALLATORIxDEMO(a2)) {
            String a5 = a2.substring(a2.indexOf(" ") + 1);
            if (a5.length() > 0) {
                a4 = new sx(a5);
            }
        } else {
            uo a6;
            throw new qu("\u683c\u5f0f\u9519\u8bef ('" + a2 + "', line " + a3 + ") \u6587\u4ef6: '" + a6.k);
        }
        return a4;
    }
}

