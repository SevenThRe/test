/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.kn;
import eos.moe.dragoncore.md;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.ne;
import eos.moe.dragoncore.ni;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.qk;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class ke
extends jj {
    public md fa;
    public mh oa;
    public mh ka;
    public mh ua;
    public mh s;
    public mh g;
    public mh q;
    public mh b;
    public mh o;
    private double y = -1.0;
    private double k = -1.0;
    private List<String> ALLATORIxDEMO;

    public ke(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        ke a4;
        a4.s = a4.createMoLangParser("center", false);
        a4.oa = a4.createMoLangParserString("font", "");
        a4.g = a4.createMoLangParser("shadow", true);
        a4.ka = a4.createMoLangParser("length", 0);
        a4.ua = a4.createMoLangParserString("color", "255,255,255,255");
        a4.q = a4.createMoLangParser("replaceColor", true);
        a4.b = a4.createMoLangParser("lineSpace", 10);
        a4.o = a4.createMoLangParser("limitRender", false);
        a4.fa = a4.createMoLangParserList("texts");
    }

    @Override
    public void render(int a2, int a3) {
        ke a4;
        List<String> a5 = a4.ALLATORIxDEMO == null ? ni.c(a4) : a4.ALLATORIxDEMO;
        String a6 = a4.oa.ALLATORIxDEMO();
        double a7 = a4.b.ALLATORIxDEMO();
        boolean a8 = a4.s.ALLATORIxDEMO();
        boolean a9 = a4.g.ALLATORIxDEMO();
        double a10 = a4.getYPos() + a4.c.ALLATORIxDEMO() + ((qk)((Object)a4.g)).ALLATORIxDEMO();
        double a11 = a4.ba.ALLATORIxDEMO();
        double a12 = a4.getLimitYPos();
        double a13 = a12 + a4.n.ALLATORIxDEMO();
        Color a14 = sd.ALLATORIxDEMO(a4.ua.ALLATORIxDEMO());
        int a15 = a14 == null ? -1 : sd.ALLATORIxDEMO(a14);
        for (int a16 = 0; a16 < a5.size(); ++a16) {
            String a17 = a5.get(a16);
            double a18 = a10 + (double)a16 * a7 * a11;
            double a19 = a18 + a7 * a11;
            if (a18 < -100.0 || a18 > ne.ALLATORIxDEMO() || a12 != a13 && (a18 > a13 || a19 < a12)) continue;
            ol.ALLATORIxDEMO(a17, a6, 0.0, (double)a16 * a7, a8, a9, false, a15);
        }
    }

    @Override
    public void renderDebug(int a2, int a3) {
        ke a4;
        boolean a5 = a4.isHovered(a2, a3);
        boolean a6 = a4.s.ALLATORIxDEMO();
        qd<Double, Double> a7 = ni.ALLATORIxDEMO(a4, 2);
        double a8 = a6 ? -(a7.c() / 2.0 + 1.0) : 0.0;
        Color a9 = a5 ? new Color(247, 49, 49, 255) : new Color(71, 145, 255, 255);
        sd.ALLATORIxDEMO(a8, 0.0, a7.c(), a7.ALLATORIxDEMO(), 1.0, a9);
    }

    @Override
    public boolean isHovered(int a2, int a3) {
        ke a4;
        if (!a4.ta.ALLATORIxDEMO()) {
            return false;
        }
        boolean a5 = a4.s.ALLATORIxDEMO();
        if (a4.e.ALLATORIxDEMO() != 0.0 && a4.n.ALLATORIxDEMO() != 0.0 && !sj.ALLATORIxDEMO(a2, a3, a4.getLimitXPos(), a4.getLimitYPos(), a4.e.ALLATORIxDEMO(), a4.n.ALLATORIxDEMO())) {
            return false;
        }
        double a6 = a4.getWidth();
        double a7 = a4.getHeight();
        return sj.ALLATORIxDEMO(a2, a3, a4.getXPos() + ((qk)((Object)a4.s)).ALLATORIxDEMO() + (a5 ? -(a6 / 2.0 + 1.0) : 0.0), a4.getYPos() + ((qk)((Object)a4.g)).ALLATORIxDEMO(), a6, a7);
    }

    @Override
    public void runClick(int a2, int a3, int a4) {
        ke a5;
        super.runClick(a2, a3, a4);
        ITextComponent a6 = a5.getTextComponent(a2, a3);
        if (a6 != null) {
            a5.y.handleComponentClick(a6);
        }
    }

    public ITextComponent getTextComponent(int a2, int a3) {
        ke a4;
        double a5 = a4.b.ALLATORIxDEMO() * a4.ba.ALLATORIxDEMO();
        double a6 = a4.getYPos() + ((qk)((Object)a4.g)).ALLATORIxDEMO();
        List<v> a7 = ni.ALLATORIxDEMO(a4);
        int a8 = -1;
        for (int a9 = 0; a9 < a7.size(); ++a9) {
            if (!((double)a3 >= a6 + (double)a9 * a5) || !((double)a3 < a6 + (double)(a9 + 1) * a5)) continue;
            a8 = a9;
        }
        if (a8 == -1) {
            return null;
        }
        v a10 = a7.get(a8);
        if (!(a10 instanceof kn)) {
            return null;
        }
        boolean a11 = a4.q.ALLATORIxDEMO();
        boolean a12 = a4.s.ALLATORIxDEMO();
        String a13 = a4.oa.ALLATORIxDEMO();
        String a14 = a10.c();
        double a15 = (double)a2 - (a4.getXPos() + ((qk)((Object)a4.s)).ALLATORIxDEMO() + (a12 ? -((double)ol.ALLATORIxDEMO(a14, a13, a11) / 2.0 + 1.0) : 0.0));
        a15 /= a4.ba.ALLATORIxDEMO();
        int a16 = 0;
        kn a17 = (kn)a10;
        for (ITextComponent a18 : a17.ALLATORIxDEMO()) {
            TextComponentString a19;
            String a20;
            double a21;
            if (!(a18 instanceof TextComponentString) || !((double)(a16 = (int)((double)a16 + (a21 = (double)ol.ALLATORIxDEMO(a20 = (a19 = (TextComponentString)a18).getText(), a13, a11)))) > a15)) continue;
            return a19;
        }
        return null;
    }

    @Override
    public double getWidth() {
        ke a2;
        return a2.y == -1.0 ? ni.ALLATORIxDEMO(a2, 0).c() : a2.y;
    }

    @Override
    public double getHeight() {
        ke a2;
        return a2.k == -1.0 ? ni.ALLATORIxDEMO(a2, 1).c() : a2.k;
    }

    @Override
    public void cache(int a2, int a3) {
        ke a4;
        super.cache(a2, a3);
        a4.ALLATORIxDEMO = ni.c(a4);
        qd<Double, Double> a5 = ni.ALLATORIxDEMO(a4, 2);
        a4.y = a5.c();
        a4.k = a5.ALLATORIxDEMO();
        a4.fa.c();
        a4.oa.c();
        a4.ka.c();
        a4.ua.c();
        a4.s.c();
        a4.g.c();
        a4.q.c();
        a4.b.c();
    }

    @Override
    public void setValue(String a3, Object a4) {
        ke a5;
        switch (a3.toLowerCase(Locale.ROOT)) {
            case "texts": {
                List<String> a6 = new ArrayList();
                if (a4 instanceof String) {
                    String[] a7 = ((String)a4).split("\\\\n");
                    a6.addAll(Arrays.asList(a7));
                } else {
                    a6 = (List)a4;
                }
                a5.fa.c(a6.stream().map(a2 -> {
                    ke a3;
                    return a3.toMolangParser("texts", (String)a2);
                }).collect(Collectors.toList()));
                return;
            }
            case "center": {
                a5.s.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "shadow": {
                a5.g.ALLATORIxDEMO(a5.toMolangParser(a4));
                return;
            }
            case "length": {
                a5.ka.ALLATORIxDEMO(a5.toMolangParser(a4));
            }
            case "color": {
                a5.ua.ALLATORIxDEMO(a5.toMolangParser((String)a4));
                break;
            }
            case "font": {
                a5.oa.ALLATORIxDEMO(a5.toMolangParser((String)a4));
                break;
            }
            case "replacecolor": {
                a5.q.ALLATORIxDEMO(a5.toMolangParser(a4));
                break;
            }
            case "linespace": {
                a5.b.ALLATORIxDEMO(a5.toMolangParser(a4));
            }
        }
        super.setValue(a3, a4);
    }

    @Override
    public Object getValue(String a2) {
        ke a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "type_": 
            case "type": {
                return "label";
            }
            case "width_": 
            case "width": {
                return String.valueOf(ni.ALLATORIxDEMO(a3, 0).c());
            }
            case "height_": 
            case "height": {
                return String.valueOf(ni.ALLATORIxDEMO(a3, 1).c());
            }
            case "text": {
                return new qg(ni.c(a3), false).c();
            }
            case "texts": {
                return new qg(ni.c(a3), false);
            }
            case "center": {
                return a3.s.c();
            }
            case "shadow": {
                return a3.g.c();
            }
            case "color": {
                return a3.ua.c();
            }
            case "length": {
                return a3.ka.c();
            }
            case "font": {
                return a3.oa.c();
            }
            case "linespace": {
                return a3.b.c();
            }
            case "linespace_": {
                return a3.b.f();
            }
            case "replacecolor": {
                return a3.q.ALLATORIxDEMO() ? "1" : "0";
            }
            case "replacecolor_": {
                return a3.q.f();
            }
            case "texts_": {
                return a3.fa.ALLATORIxDEMO();
            }
            case "center_": {
                return a3.s.f();
            }
            case "shadow_": {
                return a3.g.f();
            }
            case "color_": {
                return a3.ua.f();
            }
            case "length_": {
                return a3.ka.f();
            }
            case "font_": {
                return a3.oa.f();
            }
        }
        return super.getValue(a2);
    }

    public static boolean ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9) {
        double a10 = a4 / 2.0;
        double a11 = a5 / 2.0;
        double a12 = a8 / 2.0;
        double a13 = a9 / 2.0;
        double a14 = a2 + a10;
        double a15 = a3 + a11;
        double a16 = a6 + a12;
        double a17 = a7 + a13;
        return Math.abs(a16 - a14) <= a10 + a12 && Math.abs(a17 - a15) <= a11 + a13;
    }
}

