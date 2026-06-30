/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dn;
import eos.moe.dragoncore.md;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.qk;
import eos.moe.dragoncore.rd;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.tn;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.wk;
import eos.moe.dragoncore.xf;
import java.awt.Color;
import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.opengl.GL11;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public abstract class jj
extends rd {
    public ConcurrentHashMap<String, String> ka = new ConcurrentHashMap();
    public ConcurrentHashMap<String, v> ua = new ConcurrentHashMap();
    public mh xa;
    public mh ta;
    public mh aa;
    public mh ba;
    public mh ma;
    public mh h;
    public mh f;
    public mh d;
    public mh p;
    public mh u;
    public mh w;
    public mh a;
    public mh e;
    public mh n;
    public mh j;
    public mh i;
    public mh l;
    public mh z;
    public qk s;
    public qk g;
    public mh t;
    public mh r;
    public mh x;
    public mh v;
    public mh m;
    public mh c;
    public md q;
    public boolean b;
    public boolean o;
    public boolean y;
    public Map<String, nh> k;
    public Map<Integer, Point> ALLATORIxDEMO = new HashMap<Integer, Point>();

    public jj(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        jj a4;
        a4.ma = a4.createMoLangParserString("position", "");
        a4.h = a4.createMoLangParser("x", 0);
        a4.f = a4.createMoLangParser("y", 0);
        a4.d = a4.createMoLangParser("z", 0);
        a4.p = a4.createMoLangParser("width", 0);
        a4.u = a4.createMoLangParser("height", 0);
        a4.w = a4.createMoLangParser("limitX", 0);
        a4.a = a4.createMoLangParser("limitY", 0);
        a4.e = a4.createMoLangParser("limitWidth", 0);
        a4.n = a4.createMoLangParser("limitHeight", 0);
        a4.xa = a4.createMoLangParser("enable", true);
        a4.ta = a4.createMoLangParser("visible", true);
        a4.aa = a4.createMoLangParser("alpha", 1);
        a4.ba = a4.createMoLangParser("scale", 1);
        a4.j = a4.createMoLangParser("maxDistanceY", 0);
        a4.i = a4.createMoLangParser("maxDistanceX", 0);
        a4.l = a4.createMoLangParser("minDistanceY", 0);
        a4.z = a4.createMoLangParser("minDistanceX", 0);
        a4.s = new qk("distanceX", a4, a4.createMoLangParser("distanceX", 0).ALLATORIxDEMO());
        a4.g = new qk("distanceY", a4, a4.createMoLangParser("distanceY", 0).ALLATORIxDEMO());
        a4.t = a4.createMoLangParser("fscale", 1);
        a4.r = a4.createMoLangParser("frotatex", 0);
        a4.x = a4.createMoLangParser("frotatey", 0);
        a4.v = a4.createMoLangParser("frotatez", 0);
        a4.m = a4.createMoLangParser("fx", 0);
        a4.c = a4.createMoLangParser("fy", 0);
        a4.q = a4.createMoLangParserList("tip");
        a4.k = new ConcurrentHashMap<String, nh>();
        Object a5 = a3.contains("actions") ? a3 : a4.ALLATORIxDEMO;
        ConfigurationSection a6 = null;
        if (a5.isConfigurationSection("actions")) {
            a6 = a5.getConfigurationSection("actions");
        } else if (a5.isString("actions")) {
            a6 = a2.getYaml().getConfigurationSection(a5.getString("actions"));
        }
        if (a6 != null) {
            for (String a7 : a6.getKeys(false)) {
                a4.k.put(a7, new tn(a4.toMolangParser(a6.get(a7)), a4));
            }
        }
        for (String a7 : a4.ALLATORIxDEMO.getKeys(false)) {
            a4.ka.put(a7, String.valueOf(a4.ALLATORIxDEMO.get(a7)));
        }
        for (String a7 : a3.getKeys(false)) {
            a4.ka.put(a7, String.valueOf(a3.get(a7)));
        }
    }

    public void draw(int a2, int a3, boolean a4) {
        jj a5;
        a5.runAction("preRender");
        boolean a6 = a5.xa.ALLATORIxDEMO();
        if (a5.b && !a5.y.p.contains(a5)) {
            a5.b = false;
            if (a6) {
                a5.runLeave(false);
            }
        }
        if (a5.o && a5.y.d != a5) {
            a5.o = false;
            if (a6) {
                a5.runLeave(true);
            }
        }
        if (a5.ta.ALLATORIxDEMO()) {
            if (!a5.b && a5.y.p.contains(a5)) {
                a5.b = true;
                if (a6) {
                    a5.runHover(false);
                }
            }
            if (!a5.o && a5.y.d == a5) {
                a5.o = true;
                if (a6) {
                    a5.runHover(true);
                }
            }
            GlStateManager.disableLighting();
            if (a5 instanceof dn) {
                GlStateManager.enableDepth();
            } else {
                GlStateManager.disableDepth();
            }
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
            GlStateManager.enableBlend();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.disableAlpha();
            a5.preRender(a2, a3);
            a5.render(a2, a3);
            if (a4) {
                a5.renderDebug(a2, a3);
            }
            a5.postRender(a2, a3);
        }
        a5.runAction("postRender");
    }

    public void renderDebug(int a2, int a3) {
        jj a4;
        boolean a5 = a4.isHovered(a2, a3);
        double a6 = a4.p.ALLATORIxDEMO();
        double a7 = a4.u.ALLATORIxDEMO();
        Color a8 = a5 ? new Color(247, 49, 49, 255) : new Color(71, 145, 255, 255);
        sd.ALLATORIxDEMO(0.0, 0.0, a6, a7, 1.0, a8);
    }

    public void preRender(int a2, int a3) {
        double a4;
        double a5;
        jj a6;
        sd.ALLATORIxDEMO((float)a6.aa.ALLATORIxDEMO());
        double a7 = a6.g.ALLATORIxDEMO();
        double a8 = a6.s.ALLATORIxDEMO();
        double a9 = a6.z.ALLATORIxDEMO();
        double a10 = a6.i.ALLATORIxDEMO();
        double a11 = a6.j.ALLATORIxDEMO();
        double a12 = a6.l.ALLATORIxDEMO();
        if (a6.y.g <= 0 && a6.ALLATORIxDEMO.containsKey(0)) {
            a5 = sj.ALLATORIxDEMO(a2 - a6.ALLATORIxDEMO.get((Object)Integer.valueOf((int)0)).x, a9, a10);
            a4 = sj.ALLATORIxDEMO(a3 - a6.ALLATORIxDEMO.get((Object)Integer.valueOf((int)0)).y, a12, a11);
        } else {
            a5 = sj.ALLATORIxDEMO(a8, a9, a10);
            a4 = sj.ALLATORIxDEMO(a7, a12, a11);
        }
        if (a5 != a8 || a4 != a7) {
            a6.s.ALLATORIxDEMO(a5);
            a6.g.ALLATORIxDEMO(a4);
            a8 = a5;
            a7 = a4;
            a6.runAction("drag");
        }
        GlStateManager.pushMatrix();
        double a13 = a6.e.ALLATORIxDEMO();
        double a14 = a6.n.ALLATORIxDEMO();
        boolean bl2 = a6.y = a13 != 0.0 && a14 != 0.0;
        if (a6.y) {
            GL11.glEnable((int)3089);
            sd.ALLATORIxDEMO((int)a6.getLimitXPos(), (int)a6.getLimitYPos(), (int)a13, (int)a14);
        }
        GlStateManager.translate((double)(Math.floor((a6.getXPos() + a6.m.ALLATORIxDEMO() + a8) * 10.0) / 10.0), (double)(Math.floor((a6.getYPos() + a6.c.ALLATORIxDEMO() + a7) * 10.0) / 10.0), (double)0.0);
        double a15 = a6.ba.ALLATORIxDEMO();
        if (a15 != 1.0) {
            GlStateManager.scale((double)a15, (double)a15, (double)1.0);
        }
        a15 = a6.t.ALLATORIxDEMO();
        float a16 = (float)a6.r.ALLATORIxDEMO();
        float a17 = (float)a6.x.ALLATORIxDEMO();
        float a18 = (float)a6.v.ALLATORIxDEMO();
        if (a15 != 1.0 || a16 != 0.0f || a17 != 0.0f || a18 != 0.0f) {
            double a19 = a6.p.ALLATORIxDEMO();
            double a20 = a6.u.ALLATORIxDEMO();
            GlStateManager.translate((double)(a19 / 2.0), (double)(a20 / 2.0), (double)0.0);
            GlStateManager.scale((double)a15, (double)a15, (double)1.0);
            if (a16 != 0.0f || a17 != 0.0f || a18 != 0.0f) {
                GlStateManager.rotate((float)a16, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.rotate((float)a17, (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.rotate((float)a18, (float)0.0f, (float)0.0f, (float)1.0f);
            }
            GlStateManager.translate((double)(-a19 / 2.0), (double)(-a20 / 2.0), (double)0.0);
        }
    }

    public void postRender(int a2, int a3) {
        jj a4;
        if (a4.y) {
            GL11.glDisable((int)3089);
        }
        GlStateManager.popMatrix();
        sd.ALLATORIxDEMO(1.0f);
    }

    public abstract void render(int var1, int var2);

    public void runUnClick(int a2, int a3, int a4) {
    }

    public void runClick(int a2, int a3, int a4) {
        jj a5;
        switch (a4) {
            case 0: {
                a5.runAction("click_left");
                break;
            }
            case 1: {
                a5.runAction("click_right");
                break;
            }
            case 2: {
                a5.runAction("click_middle");
            }
        }
        a5.runAction("click");
    }

    public void runRelease(int a2, int a3, int a4) {
        jj a5;
        switch (a4) {
            case 0: {
                a5.runAction("release_left");
                break;
            }
            case 1: {
                a5.runAction("release_right");
                break;
            }
            case 2: {
                a5.runAction("release_middle");
            }
        }
        a5.runAction("release");
    }

    public void runHover(boolean a2) {
        jj a3;
        if (a2) {
            a3.runAction("enterTop");
        } else {
            a3.runAction("enter");
        }
    }

    public void runLeave(boolean a2) {
        jj a3;
        if (a2) {
            a3.runAction("leaveTop");
        } else {
            a3.runAction("leave");
        }
    }

    public v runAction(String a2) {
        nh a3;
        jj a4;
        if (a4.k.size() > 0 && (a3 = a4.k.get(a2)) != null) {
            return a3.ALLATORIxDEMO();
        }
        return wk.k;
    }

    public boolean keyTyped(char a2, int a3) throws IOException {
        return false;
    }

    public boolean isHovered(int a2, int a3) {
        jj a4;
        if (!a4.ta.ALLATORIxDEMO()) {
            return false;
        }
        if (a4.e.ALLATORIxDEMO() != 0.0 && a4.n.ALLATORIxDEMO() != 0.0 && !sj.ALLATORIxDEMO(a2, a3, a4.getLimitXPos(), a4.getLimitYPos(), a4.e.ALLATORIxDEMO(), a4.n.ALLATORIxDEMO())) {
            return false;
        }
        return sj.ALLATORIxDEMO(a2, a3, a4.getXPos() + a4.s.ALLATORIxDEMO(), a4.getYPos() + a4.g.ALLATORIxDEMO(), a4.p.ALLATORIxDEMO() * a4.ba.ALLATORIxDEMO(), a4.u.ALLATORIxDEMO() * a4.ba.ALLATORIxDEMO());
    }

    public double getXPos() {
        jj a2;
        String a3 = a2.ma.ALLATORIxDEMO();
        if (!a3.isEmpty()) {
            jj a4;
            if (a3.equals("3") || a3.equals("6") || a3.equals("9")) {
                return a2.h.ALLATORIxDEMO() + (double)a2.y.width;
            }
            if (a3.equals("2") || a3.equals("5") || a3.equals("8")) {
                return (double)((float)a2.y.width / 2.0f) + a2.h.ALLATORIxDEMO();
            }
            if (a3.startsWith("1_") || a3.startsWith("4_") || a3.startsWith("7_")) {
                jj a5 = a2.y.findComponent(a3.substring(2));
                if (a5 != null) {
                    return a2.h.ALLATORIxDEMO() + a5.getXPos();
                }
            } else if (a3.startsWith("3_") || a3.startsWith("6_") || a3.startsWith("9_")) {
                jj a6 = a2.y.findComponent(a3.substring(2));
                if (a6 != null) {
                    return a2.h.ALLATORIxDEMO() + a6.getXPos() + a6.getWidth();
                }
            } else if ((a3.startsWith("2_") || a3.startsWith("5_") || a3.startsWith("8_")) && (a4 = a2.y.findComponent(a3.substring(2))) != null) {
                return a2.h.ALLATORIxDEMO() + a4.getXPos() + a4.getWidth() / 2.0;
            }
        }
        return a2.h.ALLATORIxDEMO();
    }

    public double getYPos() {
        jj a2;
        String a3 = a2.ma.ALLATORIxDEMO();
        if (!a3.isEmpty()) {
            jj a4;
            if (a3.equals("7") || a3.equals("8") || a3.equals("9")) {
                return a2.f.ALLATORIxDEMO() + (double)a2.y.height;
            }
            if (a3.equals("4") || a3.equals("5") || a3.equals("6")) {
                return (double)((float)a2.y.height / 2.0f) + a2.f.ALLATORIxDEMO();
            }
            if (a3.startsWith("1_") || a3.startsWith("2_") || a3.startsWith("3_")) {
                jj a5 = a2.y.findComponent(a3.substring(2));
                if (a5 != null) {
                    return a2.f.ALLATORIxDEMO() + a5.getYPos();
                }
            } else if (a3.startsWith("7_") || a3.startsWith("8_") || a3.startsWith("9_")) {
                jj a6 = a2.y.findComponent(a3.substring(2));
                if (a6 != null) {
                    return a2.f.ALLATORIxDEMO() + a6.getYPos() + a6.getHeight();
                }
            } else if ((a3.startsWith("4_") || a3.startsWith("5_") || a3.startsWith("6_")) && (a4 = a2.y.findComponent(a3.substring(2))) != null) {
                return a2.f.ALLATORIxDEMO() + a4.getYPos() + a4.getHeight() / 2.0;
            }
        }
        return a2.f.ALLATORIxDEMO();
    }

    public double getLimitXPos() {
        jj a2;
        String a3 = a2.ma.ALLATORIxDEMO();
        if (!a3.isEmpty()) {
            jj a4;
            if (a3.equals("3") || a3.equals("6") || a3.equals("9")) {
                return a2.w.ALLATORIxDEMO() + (double)a2.y.width;
            }
            if (a3.equals("2") || a3.equals("5") || a3.equals("8")) {
                return (double)((float)a2.y.width / 2.0f) + a2.w.ALLATORIxDEMO();
            }
            if (a3.startsWith("1_") || a3.startsWith("4_") || a3.startsWith("7_")) {
                jj a5 = a2.y.findComponent(a3.substring(2));
                if (a5 != null) {
                    return a2.w.ALLATORIxDEMO() + a5.getXPos();
                }
            } else if (a3.startsWith("3_") || a3.startsWith("6_") || a3.startsWith("9_")) {
                jj a6 = a2.y.findComponent(a3.substring(2));
                if (a6 != null) {
                    return a2.w.ALLATORIxDEMO() + a6.getXPos() + a6.getWidth();
                }
            } else if ((a3.startsWith("2_") || a3.startsWith("5_") || a3.startsWith("8_")) && (a4 = a2.y.findComponent(a3.substring(2))) != null) {
                return a2.w.ALLATORIxDEMO() + a4.getXPos() + a4.getWidth() / 2.0;
            }
        }
        return a2.w.ALLATORIxDEMO();
    }

    public double getLimitYPos() {
        jj a2;
        String a3 = a2.ma.ALLATORIxDEMO();
        if (!a3.isEmpty()) {
            jj a4;
            if (a3.equals("7") || a3.equals("8") || a3.equals("9")) {
                return a2.a.ALLATORIxDEMO() + (double)a2.y.height;
            }
            if (a3.equals("4") || a3.equals("5") || a3.equals("6")) {
                return (double)((float)a2.y.height / 2.0f) + a2.a.ALLATORIxDEMO();
            }
            if (a3.startsWith("1_") || a3.startsWith("2_") || a3.startsWith("3_")) {
                jj a5 = a2.y.findComponent(a3.substring(2));
                if (a5 != null) {
                    return a2.a.ALLATORIxDEMO() + a5.getYPos();
                }
            } else if (a3.startsWith("7_") || a3.startsWith("8_") || a3.startsWith("9_")) {
                jj a6 = a2.y.findComponent(a3.substring(2));
                if (a6 != null) {
                    return a2.a.ALLATORIxDEMO() + a6.getYPos() + a6.getHeight();
                }
            } else if ((a3.startsWith("4_") || a3.startsWith("5_") || a3.startsWith("6_")) && (a4 = a2.y.findComponent(a3.substring(2))) != null) {
                return a2.a.ALLATORIxDEMO() + a4.getYPos() + a4.getHeight() / 2.0;
            }
        }
        return a2.a.ALLATORIxDEMO();
    }

    public double getWidth() {
        jj a2;
        return a2.p.ALLATORIxDEMO() * a2.ba.ALLATORIxDEMO();
    }

    public double getHeight() {
        jj a2;
        return a2.u.ALLATORIxDEMO() * a2.ba.ALLATORIxDEMO();
    }

    public double getDistanceX() {
        jj a2;
        return a2.s.ALLATORIxDEMO();
    }

    public double getDistanceY() {
        jj a2;
        return a2.g.ALLATORIxDEMO();
    }

    public void cache(int a2, int a3) {
        double a4;
        double a5;
        jj a6;
        a6.i.c();
        a6.j.c();
        a6.z.c();
        a6.l.c();
        double a7 = a6.g.ALLATORIxDEMO();
        double a8 = a6.s.ALLATORIxDEMO();
        double a9 = a6.z.ALLATORIxDEMO();
        double a10 = a6.i.ALLATORIxDEMO();
        double a11 = a6.l.ALLATORIxDEMO();
        double a12 = a6.j.ALLATORIxDEMO();
        if (a6.ALLATORIxDEMO.containsKey(0)) {
            a5 = sj.ALLATORIxDEMO(a2 - a6.ALLATORIxDEMO.get((Object)Integer.valueOf((int)0)).x, a9, a10);
            a4 = sj.ALLATORIxDEMO(a3 - a6.ALLATORIxDEMO.get((Object)Integer.valueOf((int)0)).y, a11, a12);
        } else {
            a5 = sj.ALLATORIxDEMO(a8, a9, a10);
            a4 = sj.ALLATORIxDEMO(a7, a11, a12);
        }
        if (a5 != a8 || a4 != a7) {
            a6.s.ALLATORIxDEMO(a5);
            a6.g.ALLATORIxDEMO(a4);
            a6.runAction("drag");
        }
        a6.xa.c();
        a6.aa.c();
        a6.ba.c();
        a6.ma.c();
        a6.h.c();
        a6.f.c();
        a6.d.c();
        a6.p.c();
        a6.p.c();
        a6.u.c();
        a6.w.c();
        a6.a.c();
        a6.e.c();
        a6.n.c();
        a6.t.c();
        a6.r.c();
        a6.x.c();
        a6.v.c();
        a6.m.c();
        a6.c.c();
        a6.q.c();
    }

    @Override
    public void setValue(String a3, Object a4) {
        jj a5;
        if (a3.startsWith("actions.")) {
            a5.k.put(a3.substring(8), new tn(a5.toMolangParser(a4), a5));
            return;
        }
        switch (a3.toLowerCase(Locale.ROOT)) {
            case "x": {
                a5.h.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "y": {
                a5.f.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "z": {
                a5.d.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "width": {
                a5.p.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "height": {
                a5.u.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "limitx": {
                a5.w.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "limity": {
                a5.a.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "limitwidth": {
                a5.e.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "limitheight": {
                a5.n.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "maxdistancex": {
                a5.i.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "maxdistancey": {
                a5.j.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "mindistancex": {
                a5.z.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "mindistancey": {
                a5.l.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "distancex": {
                double a6 = a5.z.c();
                double a7 = a5.i.c();
                a5.s.ALLATORIxDEMO(sj.ALLATORIxDEMO(a5.toMolangParser(a4).c(), a6, a7));
                return;
            }
            case "distancey": {
                double a8 = a5.j.c();
                double a9 = a5.l.c();
                a5.g.ALLATORIxDEMO(sj.ALLATORIxDEMO(a5.toMolangParser(a4).c(), a9, a8));
                return;
            }
            case "dx": {
                double a10 = a5.toMolangParser(a4).c();
                a5.s.ALLATORIxDEMO(a5.i.c() * a10);
                return;
            }
            case "dy": {
                double a11 = a5.toMolangParser(a4).c();
                a5.g.ALLATORIxDEMO(a5.j.c() * a11);
                return;
            }
            case "visible": {
                a5.ta.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "enable": {
                a5.xa.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "alpha": {
                a5.aa.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "scale": {
                a5.ba.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "tip": {
                List<String> a12 = new ArrayList();
                if (a4 instanceof String) {
                    String[] a13 = ((String)a4).split("\\\\n");
                    a12.addAll(Arrays.asList(a13));
                } else {
                    a12 = (List)a4;
                }
                a5.q.c(a12.stream().map(a2 -> {
                    jj a3;
                    return a3.toMolangParser("tip", (String)a2);
                }).collect(Collectors.toList()));
                return;
            }
            case "fscale": {
                a5.t.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "frotatex": {
                a5.r.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "frotatey": {
                a5.x.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "frotatez": {
                a5.v.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "fx": {
                a5.m.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "fy": {
                a5.c.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
        }
        if (a4 instanceof String) {
            a5.ka.put(a3.toLowerCase(Locale.ROOT), (String)a4);
        }
    }

    @Override
    public Object getValue(String a2) {
        jj a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "name_": 
            case "name": {
                return a3.k.getName();
            }
            case "hover_": 
            case "hover": {
                return a3.y.getHoveredComponents().contains(a3) ? "1" : "0";
            }
            case "x": {
                return a3.h.c();
            }
            case "y": {
                return a3.f.c();
            }
            case "z": {
                return a3.d.c();
            }
            case "width": {
                return a3.p.c();
            }
            case "height": {
                return a3.u.c();
            }
            case "limitx": {
                return a3.w.c();
            }
            case "limity": {
                return a3.a.c();
            }
            case "limitwidth": {
                return a3.e.c();
            }
            case "limitheight": {
                return a3.n.c();
            }
            case "maxdistancex": {
                return a3.i.c();
            }
            case "maxdistancey": {
                return a3.j.c();
            }
            case "mindistancex": {
                return a3.z.c();
            }
            case "mindistancey": {
                return a3.l.c();
            }
            case "distancex_": 
            case "distancex": {
                double a4 = a3.z.ALLATORIxDEMO();
                double a5 = a3.i.ALLATORIxDEMO();
                return String.valueOf(sj.ALLATORIxDEMO(a3.s.ALLATORIxDEMO(), a4, a5));
            }
            case "distancey_": 
            case "distancey": {
                double a6 = a3.l.ALLATORIxDEMO();
                double a7 = a3.j.ALLATORIxDEMO();
                return String.valueOf(sj.ALLATORIxDEMO(a3.g.ALLATORIxDEMO(), a6, a7));
            }
            case "dx_": 
            case "dx": {
                return String.valueOf(a3.s.ALLATORIxDEMO() / a3.i.c());
            }
            case "dy_": 
            case "dy": {
                return String.valueOf(a3.g.ALLATORIxDEMO() / a3.j.c());
            }
            case "visible": {
                return a3.ta.c() ? "1" : "0";
            }
            case "enable": {
                return a3.xa.c() ? "1" : "0";
            }
            case "alpha": {
                return Double.toString(sj.ALLATORIxDEMO(a3.aa.c(), 0.0, 1.0));
            }
            case "scale": {
                return a3.ba.c();
            }
            case "tip": {
                return new qg(a3.getTipStringTexts(), false);
            }
            case "fscale": {
                return a3.t.c();
            }
            case "frotatex": {
                return a3.r.c();
            }
            case "frotatey": {
                return a3.x.c();
            }
            case "frotatez": {
                return a3.v.c();
            }
            case "fx": {
                return a3.m.c();
            }
            case "fy": {
                return a3.c.c();
            }
            case "x_": {
                return a3.h.f();
            }
            case "y_": {
                return a3.f.f();
            }
            case "z_": {
                return a3.d.f();
            }
            case "width_": {
                return a3.p.f();
            }
            case "height_": {
                return a3.u.f();
            }
            case "limitx_": {
                return a3.w.f();
            }
            case "limity_": {
                return a3.a.f();
            }
            case "limitwidth_": {
                return a3.e.f();
            }
            case "limitheight_": {
                return a3.n.f();
            }
            case "maxdistancex_": {
                return a3.i.f();
            }
            case "maxdistancey_": {
                return a3.j.f();
            }
            case "mindistancex_": {
                return a3.z.f();
            }
            case "mindistancey_": {
                return a3.l.f();
            }
            case "visible_": {
                return a3.ta.f();
            }
            case "enable_": {
                return a3.xa.f();
            }
            case "alpha_": {
                return a3.aa.f();
            }
            case "scale_": {
                return a3.ba.f();
            }
            case "tip_": {
                return a3.q.ALLATORIxDEMO();
            }
            case "fscale_": {
                return a3.t.f();
            }
            case "frotatex_": {
                return a3.r.f();
            }
            case "frotatey_": {
                return a3.x.f();
            }
            case "frotatez_": {
                return a3.v.f();
            }
            case "fx_": {
                return a3.m.f();
            }
            case "fy_": {
                return a3.c.f();
            }
        }
        return a3.ka.getOrDefault(a2.toLowerCase(Locale.ROOT), "");
    }

    @Override
    public v getAnimationValue(String a2) {
        jj a3;
        return a3.ua.get(a2);
    }

    @Override
    public void setAnimationValue(String a2, v a3) {
        jj a4;
        if (a3 == null) {
            a4.ua.remove(a2);
        } else {
            a4.ua.put(a2, a3);
        }
    }

    public List<String> getTipStringTexts() {
        jj a3;
        List<String> a4 = a3.ALLATORIxDEMO().stream().map(v::c).collect(Collectors.toList());
        a4.replaceAll(a2 -> a2.replace("&", "\u00a7"));
        return a4;
    }

    private /* synthetic */ List<v> ALLATORIxDEMO() {
        jj a2;
        List<v> a3 = a2.q.d();
        ArrayList<v> a4 = new ArrayList<v>();
        for (v a5 : a3) {
            a4.addAll(sj.ALLATORIxDEMO(a5.c(), -1, null).stream().map(xf::new).collect(Collectors.toList()));
        }
        return a4;
    }

    public void onClose() {
    }

    public void onRemove() {
    }
}

