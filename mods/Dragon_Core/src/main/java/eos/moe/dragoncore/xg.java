/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.ww;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class xg
extends jj {
    private mh o;
    private mh y;
    private BufferedImage k;
    private String ALLATORIxDEMO;

    public xg(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        xg a4;
        a4.o = a4.createMoLangParserString("texture", "");
        a4.y = a4.createMoLangParser("render", false);
    }

    @Override
    public void render(int a2, int a3) {
        xg a4;
        if (a4.y.ALLATORIxDEMO()) {
            ww.ALLATORIxDEMO(a4.o.ALLATORIxDEMO());
            sd.ALLATORIxDEMO(0.0, 0.0, a4.p.ALLATORIxDEMO(), a4.u.ALLATORIxDEMO());
        }
    }

    @Override
    public boolean isHovered(int a2, int a3) {
        xg a4;
        if (!a4.ta.ALLATORIxDEMO()) {
            return false;
        }
        if (a4.e.ALLATORIxDEMO() != 0.0 && a4.n.ALLATORIxDEMO() != 0.0 && !sj.ALLATORIxDEMO(a2, a3, a4.getLimitXPos(), a4.getLimitYPos(), a4.e.ALLATORIxDEMO(), a4.n.ALLATORIxDEMO())) {
            return false;
        }
        int a5 = (int)(a4.getXPos() + a4.s.ALLATORIxDEMO());
        int a6 = (int)(a4.getYPos() + a4.g.ALLATORIxDEMO());
        double a7 = a4.ba.ALLATORIxDEMO();
        boolean a8 = sj.ALLATORIxDEMO(a2, a3, a5, a6, a4.p.ALLATORIxDEMO() * a7, a4.u.ALLATORIxDEMO() * a7);
        if (!a8) {
            return false;
        }
        String a9 = a4.o.ALLATORIxDEMO();
        int a10 = a4.p.ALLATORIxDEMO();
        int a11 = a4.u.ALLATORIxDEMO();
        if (a4.k == null || !a9.equals(a4.ALLATORIxDEMO)) {
            BufferedImage a12;
            Object a13;
            a4.ALLATORIxDEMO = a9;
            try {
                a13 = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("dragoncore", a9));
                a12 = TextureUtil.readBufferedImage((InputStream)a13.getInputStream());
            }
            catch (IOException a14) {
                a12 = new BufferedImage(a10, a11, 3);
                for (int a15 = 0; a15 < a10; ++a15) {
                    for (int a16 = 0; a16 < a11; ++a16) {
                        a12.setRGB(a15, a16, Color.WHITE.getRGB());
                    }
                }
            }
            if (a12.getHeight() != a11 || a12.getWidth() != a10) {
                a4.k = new BufferedImage(a10, a11, 2);
                a13 = a4.k.createGraphics();
                AffineTransform a17 = new AffineTransform();
                a17.scale((double)a10 / (double)a12.getWidth(), (double)a11 / (double)a12.getHeight());
                ((Graphics2D)a13).drawRenderedImage(a12, a17);
                ((Graphics)a13).dispose();
            } else {
                a4.k = a12;
            }
        }
        a5 = (int)((double)(a2 - a5) / a7);
        a6 = (int)((double)(a3 - a6) / a7);
        if (a5 == a10 || a6 == a11) {
            return false;
        }
        int a18 = a4.k.getRGB(a5, a6);
        int a19 = a18 >> 24 & 0xFF;
        return a19 != 0;
    }

    @Override
    public void cache(int a2, int a3) {
        xg a4;
        super.cache(a2, a3);
        a4.o.c();
        a4.y.c();
    }

    @Override
    public void setValue(String a2, Object a3) {
        xg a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "texture": {
                a4.o.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "render": {
                a4.y.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
        }
        super.setValue(a2, a3);
    }

    @Override
    public Object getValue(String a2) {
        xg a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "type_": 
            case "type": {
                return "hit";
            }
            case "texture": {
                return a3.o.c();
            }
            case "texture_": {
                return a3.o.f();
            }
            case "render": {
                return a3.y.c() ? "1" : "0";
            }
            case "render_": {
                return a3.y.f();
            }
        }
        return super.getValue(a2);
    }
}

