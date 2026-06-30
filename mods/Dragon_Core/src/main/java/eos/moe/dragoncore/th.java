/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  eos.moe.armourers.SkinRenderHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.DynamicTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.armourers.SkinRenderHelper;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.di;
import eos.moe.dragoncore.ij;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.ww;
import eos.moe.dragoncore.ze;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class th
extends jj {
    public mh pa;
    public mh da;
    public mh qa;
    public mh wa;
    public mh fa;
    public mh oa;
    private mh ka;
    private mh ua;
    private mh s;
    private mh g;
    private mh q;
    private mh b;
    private mh o;
    private ze y;
    private String k;
    private boolean ALLATORIxDEMO;

    public th(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        th a4;
        a4.fa = a4.createMoLangParserString("color", "255,255,255,255");
        a4.oa = a4.createMoLangParserString("textureColor", "255,255,255,255");
        a4.ka = a4.createMoLangParserString("text", "");
        a4.ua = a4.createMoLangParserString("texture", "");
        a4.s = a4.createMoLangParserString("textureHovered", "");
        a4.g = a4.createMoLangParserString("font", "");
        a4.q = a4.createMoLangParserString("stencil", "");
        a4.b = a4.createMoLangParser("stencilWidth", 0);
        a4.o = a4.createMoLangParser("stencilHeight", 0);
        a4.pa = a4.createMoLangParser("u", 0);
        a4.da = a4.createMoLangParser("v", 0);
        Object a5 = a4.readYml("textureWidth", null);
        a4.qa = a5 == null ? a4.p : a4.createMoLangParser("textureWidth", 0);
        Object a6 = a4.readYml("textureHeight", null);
        a4.wa = a6 == null ? a4.u : a4.createMoLangParser("textureHeight", 0);
    }

    @Override
    public void render(int a2, int a3) {
        List<String> a4;
        Object a5;
        double a6;
        String a7;
        th a8;
        boolean a9 = ((ui)((Object)a8.y)).p.contains(a8);
        String a10 = a8.s.ALLATORIxDEMO();
        String a11 = a8.ua.ALLATORIxDEMO();
        String a12 = a8.q.ALLATORIxDEMO();
        double a13 = a8.qa.ALLATORIxDEMO();
        double a14 = a8.wa.ALLATORIxDEMO();
        double a15 = a8.p.ALLATORIxDEMO();
        double a16 = a8.u.ALLATORIxDEMO();
        double a17 = a8.pa.ALLATORIxDEMO();
        double a18 = a8.da.ALLATORIxDEMO();
        String string = a7 = a9 && !a10.isEmpty() ? a10 : a11;
        if (a7.startsWith("skin|")) {
            if (ca.c) {
                GlStateManager.disableCull();
                SkinRenderHelper.renderSkin((String)a7.substring(5), (float)((float)(a15 / 2.0)), (float)((float)(a16 / 2.0)), (float)((float)a15), (float)((float)a16));
                GlStateManager.enableCull();
            }
        } else {
            if (!a12.isEmpty()) {
                if (!a12.contains(".")) {
                    a8.ALLATORIxDEMO(a12);
                }
                double a19 = a8.b.ALLATORIxDEMO();
                a6 = a8.o.ALLATORIxDEMO();
                a19 = a19 == 0.0 ? a15 : a19;
                a6 = a6 == 0.0 ? a16 : a6;
                ij.c(a12, a19, a6);
            }
            a5 = sd.ALLATORIxDEMO(a7);
            if (a7.endsWith(".mp4")) {
                if (a8.y == null || !a7.equals(a8.k) || a8.ALLATORIxDEMO) {
                    a8.ALLATORIxDEMO = false;
                    a8.k = a7;
                    a8.closeMedia();
                    a8.y = new ze(a8);
                    di.y.ALLATORIxDEMO(a8.y, a7);
                }
                if (a8.y.c()) {
                    a8.y.c(0.0, 0.0, a17, a18, a15, a16, a13, a14);
                }
            } else if (a5 == null) {
                ww.ALLATORIxDEMO(a7);
                a4 = sd.ALLATORIxDEMO(a8.oa.ALLATORIxDEMO());
                sd.ALLATORIxDEMO((Color)((Object)a4), 0.0, 0.0, a17, a18, a15, a16, a13, a14);
            } else {
                sd.ALLATORIxDEMO(0.0, 0.0, a15, a16, (Color)a5);
            }
            if (!a12.isEmpty()) {
                ij.c();
                ij.ALLATORIxDEMO();
            }
        }
        a5 = a8.g.ALLATORIxDEMO();
        a4 = sj.ALLATORIxDEMO(a8.ka.ALLATORIxDEMO(), 0, (String)a5);
        if (!a4.isEmpty()) {
            a6 = a16 / 2.0 - (a4.size() == 1 ? 4.5 : (double)((a4.size() - 1) * 5) + 4.5);
            Color a20 = sd.ALLATORIxDEMO(a8.fa.ALLATORIxDEMO());
            int a21 = a20 == null ? -1 : sd.ALLATORIxDEMO(a20);
            for (int a22 = 0; a22 < a4.size(); ++a22) {
                ol.ALLATORIxDEMO((String)a4.get(a22), (String)a5, a15 / 2.0, a6 + (double)(a22 * 10), true, true, true, a21);
            }
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2) {
        th a3;
        TextureManager a4 = Minecraft.getMinecraft().getTextureManager();
        String[] a5 = a2.split("_");
        if (a5.length == 2 && a5[0].equalsIgnoreCase("circle")) {
            int a6 = a3.ALLATORIxDEMO(a5[1]);
            ResourceLocation a7 = new ResourceLocation("dragoncore", "circle_" + a6);
            if (a4.getTexture(a7) != null) {
                return;
            }
            a3.ALLATORIxDEMO(a7, a6);
        } else if (a5.length == 5 && a5[0].equalsIgnoreCase("roundrect")) {
            int a8 = a3.ALLATORIxDEMO(a5[1]);
            int a9 = a3.ALLATORIxDEMO(a5[2]);
            int a10 = a3.ALLATORIxDEMO(a5[3]);
            int a11 = a3.ALLATORIxDEMO(a5[4]);
            ResourceLocation a12 = new ResourceLocation("dragoncore", String.format("roundrect_%d_%d_%d_%d", a8, a9, a10, a11));
            if (a4.getTexture(a12) != null) {
                return;
            }
            a3.ALLATORIxDEMO(a12, a8, a9, a10, a11);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(ResourceLocation a2, int a3) {
        int a4 = 256;
        BufferedImage a5 = new BufferedImage(a4, a4, 2);
        Graphics2D a6 = (Graphics2D)a5.getGraphics();
        a6.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        a6.setColor(Color.WHITE);
        a6.fillArc(0, 0, a4, a4, 90, a3);
        a6.dispose();
        TextureManager a7 = Minecraft.getMinecraft().getTextureManager();
        a7.loadTexture(a2, (ITextureObject)new DynamicTexture(a5));
    }

    private /* synthetic */ void ALLATORIxDEMO(ResourceLocation a2, int a3, int a4, int a5, int a6) {
        BufferedImage a7 = new BufferedImage(a3, a4, 2);
        Graphics2D a8 = (Graphics2D)a7.getGraphics();
        a8.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        a8.setColor(Color.WHITE);
        a8.fillRoundRect(0, 0, a3, a4, a5, a6);
        a8.dispose();
        TextureManager a9 = Minecraft.getMinecraft().getTextureManager();
        a9.loadTexture(a2, (ITextureObject)new DynamicTexture(a7));
    }

    private /* synthetic */ int ALLATORIxDEMO(String a2) {
        try {
            return Integer.parseInt(a2);
        }
        catch (Exception a3) {
            return 0;
        }
    }

    @Override
    public void cache(int a2, int a3) {
        th a4;
        super.cache(a2, a3);
        a4.pa.c();
        a4.da.c();
        a4.qa.c();
        a4.wa.c();
        a4.fa.c();
        a4.ka.c();
        a4.ua.c();
        a4.s.c();
        a4.g.c();
        a4.q.c();
        a4.o.c();
        a4.b.c();
        a4.oa.c();
    }

    @Override
    public void setValue(String a2, Object a3) {
        th a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "texturecolor": {
                a4.oa.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "texture": {
                a4.ua.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "texturehovered": 
            case "texturehovred": {
                a4.s.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "texturewidth": {
                a4.qa.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "textureheight": {
                a4.wa.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "u": {
                a4.pa.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "v": {
                a4.da.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "text": {
                String a5 = ((String)a3).replace("\\n", "\n");
                a4.ka.ALLATORIxDEMO(a4.toMolangParser(a5));
                return;
            }
            case "color": {
                a4.fa.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "font": {
                a4.g.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "stencil": {
                a4.q.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "stencilwidth": {
                a4.b.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "stencilheight": {
                a4.o.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "resetmedia": {
                a4.ALLATORIxDEMO = true;
                return;
            }
        }
        super.setValue(a2, a3);
    }

    @Override
    public Object getValue(String a2) {
        th a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "type_": 
            case "type": {
                return "texture";
            }
            case "texturecolor": {
                return a3.oa.c();
            }
            case "texture": {
                String a4 = a3.ua.c();
                Color a5 = sd.ALLATORIxDEMO(a4);
                if (a5 != null) {
                    return String.format("%d,%d,%d,%d", a5.getRed(), a5.getGreen(), a5.getBlue(), a5.getAlpha());
                }
                return a3.ua.c();
            }
            case "texturehovered": 
            case "texturehovred": {
                String a6 = a3.s.c();
                Color a7 = sd.ALLATORIxDEMO(a6);
                if (a7 != null) {
                    return String.format("%d,%d,%d,%d", a7.getRed(), a7.getGreen(), a7.getBlue(), a7.getAlpha());
                }
                return a3.s.c();
            }
            case "texturewidth": {
                return a3.qa.c();
            }
            case "textureheight": {
                return a3.wa.c();
            }
            case "u": {
                return a3.pa.c();
            }
            case "v": {
                return a3.da.c();
            }
            case "text": {
                return a3.ka.c();
            }
            case "color": {
                return a3.fa.c();
            }
            case "font": {
                return a3.g.c();
            }
            case "stencil": {
                return a3.q.c();
            }
            case "stencilwidth": {
                return a3.b.c();
            }
            case "stencilheight": {
                return a3.o.c();
            }
            case "texture_": {
                return a3.ua.f();
            }
            case "texturehovered_": 
            case "texturehovred_": {
                return a3.s.f();
            }
            case "texturewidth_": {
                return a3.qa.f();
            }
            case "textureheight_": {
                return a3.wa.f();
            }
            case "u_": {
                return a3.pa.f();
            }
            case "v_": {
                return a3.da.f();
            }
            case "text_": {
                return a3.ka.f();
            }
            case "color_": {
                return a3.fa.f();
            }
            case "font_": {
                return a3.g.f();
            }
            case "stencil_": {
                return a3.q.f();
            }
            case "stencilwidth_": {
                return a3.b.f();
            }
            case "stencilheight_": {
                return a3.o.f();
            }
        }
        return super.getValue(a2);
    }

    @Override
    public void onClose() {
        th a2;
        super.onClose();
        a2.closeMedia();
    }

    @Override
    public void onRemove() {
        th a2;
        a2.closeMedia();
    }

    public void closeMedia() {
        th a2;
        if (a2.y != null) {
            a2.y.ALLATORIxDEMO();
            a2.y = null;
        }
    }
}

