/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.co;
import eos.moe.dragoncore.ef;
import eos.moe.dragoncore.ge;
import eos.moe.dragoncore.kca;
import eos.moe.dragoncore.ln;
import eos.moe.dragoncore.lo;
import eos.moe.dragoncore.mk;
import eos.moe.dragoncore.oga;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.ww;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class in {
    private static int z = 512;
    private static List<Font> s = Arrays.asList(GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts());
    private List<Font> g;
    private LinkedHashMap<String, ln> t;
    private Map<Character, ge> r;
    private List<ef> x;
    private Font v;
    private int m;
    private Graphics2D c;
    public float q;
    private int b;
    public float o;
    private float y;
    private float k;
    private float ALLATORIxDEMO;

    public void ALLATORIxDEMO(float a2, float a3, float a4, float a5) {
        a.y = a2;
        a.k = a3;
        a.ALLATORIxDEMO = a4;
        a.o = a5;
    }

    public in(Font a2, float a3) {
        in a4;
        a4.o = 1.0f;
        a4.g = new ArrayList<Font>();
        a4.t = new lo<String, ln>(100);
        a4.r = new HashMap<Character, ge>();
        a4.x = new ArrayList<ef>();
        a4.m = 1;
        a4.c = (Graphics2D)new BufferedImage(1, 1, 2).getGraphics();
        a4.q = 1.0f;
        a4.b = 167;
        a4.v = a2;
        a4.q = a3;
        a4.c.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        a4.m = a4.c.getFontMetrics(a2).getHeight();
    }

    public in(ResourceLocation a2, int a3, float a4) {
        in a5;
        a5.o = 1.0f;
        a5.g = new ArrayList<Font>();
        a5.t = new lo<String, ln>(100);
        a5.r = new HashMap<Character, ge>();
        a5.x = new ArrayList<ef>();
        a5.m = 1;
        a5.c = (Graphics2D)new BufferedImage(1, 1, 2).getGraphics();
        a5.q = 1.0f;
        a5.b = 167;
        InputStream a6 = null;
        try {
            a6 = Minecraft.getMinecraft().getResourceManager().getResource(a2).getInputStream();
            GraphicsEnvironment a7 = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font a8 = Font.createFont(0, a6);
            a7.registerFont(a8);
            a5.v = a8.deriveFont(0, a3);
            a5.q = a4;
            a5.c.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            a5.m = a5.c.getFontMetrics(a8).getHeight();
        }
        catch (Exception a9) {
            a9.printStackTrace();
        }
    }

    public void ALLATORIxDEMO(char a2) {
        a.b = a2;
    }

    public void c(String a2, float a3, float a4) {
        in a5;
        if (a5.v == null) {
            return;
        }
        ln a6 = a5.ALLATORIxDEMO(a2);
        GlStateManager.color((float)a5.y, (float)a5.k, (float)a5.ALLATORIxDEMO, (float)a5.o);
        GlStateManager.enableBlend();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)a3, (float)a4, (float)0.0f);
        GlStateManager.scale((float)a5.q, (float)a5.q, (float)1.0f);
        float a7 = 0.0f;
        for (ge a8 : a6.k) {
            if (a8.v != co.r) {
                if (a8.v == co.o) {
                    GlStateManager.color((float)a5.y, (float)a5.k, (float)a5.ALLATORIxDEMO, (float)a5.o);
                    continue;
                }
                if (a8.v != co.x) continue;
                GlStateManager.color((float)((float)(a8.m >> 16 & 0xFF) / 255.0f), (float)((float)(a8.m >> 8 & 0xFF) / 255.0f), (float)((float)(a8.m & 0xFF) / 255.0f), (float)a5.o);
                continue;
            }
            if (a8.k != null) {
                kca a9 = a8.k;
                ww.ALLATORIxDEMO(a8.k.ALLATORIxDEMO());
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)a5.o);
                mk.ALLATORIxDEMO(a7, 0.0f, a9.z() / a5.q, a9.k() / a5.q, a9.s() / a5.q, a9.w() / a5.q, a9.d() / a5.q, a9.x() / a5.q);
                GlStateManager.color((float)a5.y, (float)a5.k, (float)a5.ALLATORIxDEMO, (float)a5.o);
            } else {
                GlStateManager.bindTexture((int)a8.y);
                a5.ALLATORIxDEMO(a7, 0.0f, (float)a8.c * a5.ALLATORIxDEMO(), (float)a8.q * a5.ALLATORIxDEMO(), (float)a8.o * a5.ALLATORIxDEMO(), (float)a8.b * a5.ALLATORIxDEMO());
            }
            a7 += (float)a8.o * a5.ALLATORIxDEMO();
        }
        GlStateManager.disableBlend();
        GlStateManager.popMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private /* synthetic */ ln ALLATORIxDEMO(String a2) {
        in a3;
        ln a4 = a3.t.get(a2);
        if (a4 != null) {
            return a4;
        }
        a4 = new ln(a3);
        oga a5 = oga.k;
        for (int a6 = 0; a6 < a2.length(); ++a6) {
            Object a7;
            int a8;
            kca a9;
            char a10 = a2.charAt(a6);
            if (a6 + 1 != a2.length() && a10 == a3.b && (a9 = a5.ALLATORIxDEMO(a2.charAt(a6 + 1))) != null && a9.ALLATORIxDEMO()) {
                ge a11 = new ge(a3);
                a11.v = co.r;
                a11.k = a9;
                a11.o = (int)(a9.s() * (float)a3.v.getSize() / 9.0f * 1.2f);
                a11.b = (int)(a9.w() * (float)a3.v.getSize() / 9.0f * 1.2f);
                a4.k.add(a11);
                a4.o += a11.o;
                a4.y = Math.max(a4.y, a11.b);
                ++a6;
                continue;
            }
            if (a6 + 7 < a2.length() && a10 == a3.b && a2.charAt(a6 + 1) == '#') {
                String a12 = a2.substring(a6 + 2, a6 + 8);
                a8 = 1;
                for (char a13 : a12.toCharArray()) {
                    int a14 = "0123456789abcdefABCDEF".indexOf(a13);
                    if (a14 != -1) continue;
                    a8 = 0;
                    break;
                }
                if (a8 != 0) {
                    a7 = Color.decode("0x" + a12);
                    int a15 = sd.ALLATORIxDEMO((Color)a7);
                    ge a16 = new ge(a3);
                    a16.v = co.x;
                    a16.m = a15;
                    a4.k.add(a16);
                    a6 += 7;
                    continue;
                }
            } else if (a10 == a3.b && a6 + 1 < a2.length()) {
                char a17 = a2.toLowerCase(Locale.ENGLISH).charAt(a6 + 1);
                a8 = "0123456789abcdefklmnor".indexOf(a17);
                if (a8 >= 0) {
                    a7 = new ge(a3);
                    if (a8 < 16) {
                        ((ge)a7).v = co.x;
                        ((ge)a7).m = Minecraft.getMinecraft().fontRenderer.getColorCode(a17);
                    } else {
                        ((ge)a7).v = a8 == 16 ? co.v : (a8 == 17 ? co.m : (a8 == 18 ? co.c : (a8 == 19 ? co.q : (a8 == 20 ? co.b : co.o))));
                    }
                    a4.k.add((ge)a7);
                    ++a6;
                    continue;
                }
            } else {
                a9 = a5.ALLATORIxDEMO(a10);
                if (a9 != null && !a9.ALLATORIxDEMO()) {
                    ge a18 = new ge(a3);
                    a18.v = co.r;
                    a18.k = a9;
                    a18.o = (int)(a9.s() * (float)a3.v.getSize() / 9.0f * 1.2f);
                    a18.b = (int)(a9.w() * (float)a3.v.getSize() / 9.0f * 1.2f);
                    a4.k.add(a18);
                    a4.o += a18.o;
                    a4.y = Math.max(a4.y, a18.b);
                    continue;
                }
            }
            ge a19 = a3.ALLATORIxDEMO(a10);
            a4.k.add(a19);
            a4.o += a19.o;
            a4.y = Math.max(a4.y, a19.b);
        }
        a3.t.put(a2, a4);
        return a4;
    }

    private /* synthetic */ ge ALLATORIxDEMO(char a2) {
        ef a3;
        in a4;
        ge a5 = a4.r.get(Character.valueOf(a2));
        if (a5 != null) {
            return a5;
        }
        ef a6 = a4.ALLATORIxDEMO();
        Font a7 = a4.ALLATORIxDEMO(a2);
        FontMetrics a8 = a4.c.getFontMetrics(a7);
        a5 = new ge(a4);
        a5.o = Math.max(a8.charWidth(a2), 1);
        a5.b = Math.max(a8.getHeight(), 1);
        if (a6.m + a5.o >= 512) {
            a6.m = 0;
            a3 = a6;
            a3.c += a4.m + 1;
            if (a6.c + a5.b >= 512) {
                a6.y = true;
                a6 = a4.ALLATORIxDEMO();
            }
        }
        a5.c = a6.m;
        a5.q = a6.c;
        a3 = a6;
        a3.m += a5.o + 3;
        a4.m = Math.max(a4.m, a5.b);
        a6.o.setFont(a7);
        a6.o.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        a6.o.drawString(a2 + "", a5.c, a5.q + a8.getAscent());
        a5.y = a6.q;
        TextureUtil.uploadTextureImage((int)a6.q, (BufferedImage)a6.b);
        a4.r.put(Character.valueOf(a2), a5);
        return a5;
    }

    private /* synthetic */ ef ALLATORIxDEMO() {
        in a2;
        ef a3 = null;
        for (ef a4 : a2.x) {
            if (a4.y) continue;
            a3 = a4;
            break;
        }
        if (a3 == null) {
            a3 = new ef(a2);
            a2.x.add(a3);
        }
        return a3;
    }

    public void ALLATORIxDEMO(String a2, float a3, float a4) {
        in a5;
        a5.c(a2, a3 - (float)a5.c(a2) / 2.0f, a4);
    }

    private /* synthetic */ Font ALLATORIxDEMO(char a2) {
        in a3;
        if (a3.v.canDisplay(a2)) {
            return a3.v;
        }
        for (Font a4 : a3.g) {
            if (!a4.canDisplay(a2)) continue;
            return a4;
        }
        Font a5 = new Font("Arial Unicode MS", 0, a3.v.getSize());
        if (a5.canDisplay(a2)) {
            return a5;
        }
        for (Font a6 : s) {
            if (!a6.canDisplay(a2)) continue;
            a6 = a6.deriveFont(0, a3.v.getSize());
            a3.g.add(a6);
            return a6;
        }
        return a3.v;
    }

    public void ALLATORIxDEMO(float a2, float a3, float a4, float a5, float a6, float a7) {
        float a8 = 0.00390625f;
        float a9 = 0.00390625f;
        boolean a10 = false;
        BufferBuilder a11 = Tessellator.getInstance().getBuffer();
        a11.begin(7, DefaultVertexFormats.POSITION_TEX);
        a11.noColor();
        a11.pos((double)a2, (double)(a3 + a7), (double)a10).tex((double)(a4 * a8), (double)((a5 + a7) * a9)).endVertex();
        a11.pos((double)(a2 + a6), (double)(a3 + a7), (double)a10).tex((double)((a4 + a6) * a8), (double)((a5 + a7) * a9)).endVertex();
        a11.pos((double)(a2 + a6), (double)a3, (double)a10).tex((double)((a4 + a6) * a8), (double)(a5 * a9)).endVertex();
        a11.pos((double)a2, (double)a3, (double)a10).tex((double)(a4 * a8), (double)(a5 * a9)).endVertex();
        Tessellator.getInstance().draw();
    }

    public int c(String a2) {
        in a3;
        if (a3.v == null) {
            return 0;
        }
        ln a4 = a3.ALLATORIxDEMO(a2);
        return (int)((float)a4.o * a3.q * a3.ALLATORIxDEMO());
    }

    public int ALLATORIxDEMO(String a2) {
        in a3;
        if (a2 == null || a2.trim().isEmpty()) {
            return (int)((float)a3.m * a3.q * a3.ALLATORIxDEMO());
        }
        ln a4 = a3.ALLATORIxDEMO(a2);
        return Math.max(1, (int)((float)a4.y * a3.q * a3.ALLATORIxDEMO()));
    }

    public String ALLATORIxDEMO(String a2, int a3, boolean a4) {
        in a5;
        if (a5.v == null) {
            return a2.substring(1);
        }
        StringBuilder a6 = new StringBuilder();
        int a7 = 0;
        int a8 = a4 ? a2.length() - 1 : 0;
        int a9 = a4 ? -1 : 1;
        boolean a10 = false;
        boolean a11 = false;
        oga a12 = oga.k;
        for (int a13 = a8; a13 >= 0 && a13 < a2.length() && a7 < a3; a13 += a9) {
            char a14 = a2.charAt(a13);
            if (a13 + 1 != a2.length() && a14 == '\u00a7' && a12.ALLATORIxDEMO(a2.charAt(a13 + 1)) != null && a12.ALLATORIxDEMO(a2.charAt(a13 + 1)).ALLATORIxDEMO()) {
                a7 = (int)((float)a7 + a12.ALLATORIxDEMO(a2.charAt(a13 + 1)).s());
                if (a11) {
                    ++a7;
                }
            } else if (a13 + 1 != a2.length() && a14 == '\u00a7' && a2.charAt(a13 + 1) == '#') {
                a13 += 7;
            } else {
                int a15 = a5.ALLATORIxDEMO((char)a14).o;
                if (a10) {
                    a10 = false;
                    if (a5.ALLATORIxDEMO(a14, true)) {
                        a7 += a5.ALLATORIxDEMO(a14, true);
                        if (a11) {
                            ++a7;
                        }
                    } else if (a14 != 'l' && a14 != 'L') {
                        if (a14 == 'r' || a14 == 'R') {
                            a11 = false;
                        }
                    } else {
                        a11 = true;
                    }
                } else if (a15 < 0) {
                    a10 = true;
                } else {
                    a7 += a5.ALLATORIxDEMO(a14, true);
                    if (a11) {
                        ++a7;
                    }
                }
            }
            if (a7 > a3) break;
            if (a4) {
                a6.insert(0, a14);
                continue;
            }
            a6.append(a14);
        }
        return a6.toString();
    }

    public boolean ALLATORIxDEMO(char a2, boolean a3) {
        kca a4 = oga.k.ALLATORIxDEMO(a2);
        return a4 != null && (!a4.ALLATORIxDEMO() || a3);
    }

    public int ALLATORIxDEMO(char a2, boolean a3) {
        in a4;
        kca a5 = oga.k.ALLATORIxDEMO(a2);
        if (a5 != null && (!a5.ALLATORIxDEMO() || a3)) {
            return (int)a5.ALLATORIxDEMO() + 1;
        }
        return a4.ALLATORIxDEMO((char)a2).o;
    }

    private /* synthetic */ float ALLATORIxDEMO() {
        return 0.5f;
    }

    public void ALLATORIxDEMO() {
        in a2;
        for (ef a3 : a2.x) {
            GlStateManager.deleteTexture((int)a3.q);
        }
        a2.t.clear();
    }

    public String ALLATORIxDEMO() {
        in a2;
        return a2.v.getFontName();
    }
}

