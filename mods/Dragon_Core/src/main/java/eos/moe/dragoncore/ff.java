/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.input.Mouse
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cm;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.nm;
import eos.moe.dragoncore.ol;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.wj;
import eos.moe.dragoncore.yd;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.CompletableFuture;
import java.util.regex.Matcher;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.input.Mouse;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class ff
extends jj {
    private mh oa;
    private mh ka;
    private mh ua;
    private int u;
    public String w = null;
    private nm a = null;
    public boolean e = false;
    public boolean n = true;
    public boolean j = true;
    public boolean i = false;
    public boolean l = false;
    public boolean z = false;
    private int s;
    private int g;
    private int t;
    private int r = 0;
    private boolean x = false;
    private static final char v = '\uffff';
    public List<wj> m = new ArrayList<wj>();
    public List<wj> c = new ArrayList<wj>();
    public boolean q = true;
    private long o = 0L;
    private double y;
    private double k;
    private boolean ALLATORIxDEMO = false;

    public ff(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        ff a4;
        a4.e = a4.createMoLangParser("focused", false).c();
        a4.ua = a4.createMoLangParserString("font", "");
        a4.oa = a4.createMoLangParser("drawBackground", true);
        a4.setText(a4.createMoLangParserString("text", "").c());
        a4.ka = a4.createMoLangParserString("color", "");
        a4.q = false;
    }

    @Override
    public void render(int a2, int a3) {
        int a4;
        Object a5;
        int a6;
        int a7;
        ff a8;
        a8.y = 0.0;
        a8.k = 0.0;
        if (!a8.j) {
            return;
        }
        double a9 = 0.0;
        double a10 = 0.0;
        double a11 = a8.p.c();
        double a12 = a8.u.c();
        a2 = (int)(((double)a2 - (a8.getXPos() + a8.s.ALLATORIxDEMO())) / a8.ba.c());
        a3 = (int)(((double)a3 - (a8.getYPos() + a8.g.ALLATORIxDEMO())) / a8.ba.c());
        if (a8.oa.c()) {
            ff.ALLATORIxDEMO(a9 - 1.0, a10 - 1.0, a9 + a11 + 1.0, a10 + a12 + 1.0, -6250336);
            ff.ALLATORIxDEMO(a9, a10, a9 + a11, a10 + a12, -16777216);
        }
        a8.a.k = (int)(a12 / (double)a8.a.o);
        if (a8.i) {
            a8.i = Mouse.isButtonDown((int)0);
            a7 = a8.ALLATORIxDEMO((double)a2, a3);
            if (a7 != a8.t) {
                if (a8.l) {
                    a8.g = a6 = a8.t;
                    a8.s = a6;
                    a8.l = false;
                }
                a8.ALLATORIxDEMO(a7, true);
            }
        } else if (a8.l) {
            a8.l = false;
        }
        if (a8.z) {
            a8.z = Mouse.isButtonDown((int)0);
            a7 = a8.a.ALLATORIxDEMO - a8.a.k;
            a8.r = Math.min(Math.max((int)((double)(1.0f * (float)a7 * (float)a3) / a12), 0), a7);
        }
        a7 = 0;
        a6 = 0;
        if ((a8.g - a8.s == 1 || a8.s == a8.g) && a8.s < a8.w.length() && a8.s > 0) {
            char a13 = a8.w.charAt(a8.s);
            int a14 = 0;
            if (a13 == '{') {
                a14 = a8.c(a8.subStringText(a8.s), '{', '}');
            } else if (a13 == '[') {
                a14 = a8.c(a8.subStringText(a8.s), '[', ']');
            } else if (a13 == '(') {
                a14 = a8.c(a8.subStringText(a8.s), '(', ')');
            } else if (a13 == '}') {
                a14 = a8.ALLATORIxDEMO(a8.subStringText(0, a8.s + 1), '{', '}');
            } else if (a13 == ']') {
                a14 = a8.ALLATORIxDEMO(a8.subStringText(0, a8.s + 1), '[', ']');
            } else if (a13 == ')') {
                a14 = a8.ALLATORIxDEMO(a8.subStringText(0, a8.s + 1), '(', ')');
            }
            if (a14 != 0) {
                a7 = a8.s;
                a6 = a8.s + a14;
            }
        }
        ArrayList<yd> a15 = new ArrayList<yd>(a8.a.b);
        String a16 = null;
        if (a8.s != a8.g) {
            a5 = a8.a.x.matcher(a8.w);
            while (((Matcher)a5).find()) {
                if (((Matcher)a5).start() != a8.s || ((Matcher)a5).end() != a8.g) continue;
                a16 = a8.subStringText(a8.s, a8.g);
            }
        }
        a5 = a8.ua.c();
        for (a4 = 0; a4 < a15.size(); ++a4) {
            int a17;
            double a18;
            double a19;
            yd a20 = (yd)a15.get(a4);
            String a21 = a20.o;
            int a22 = a21.length();
            if (a7 != a6) {
                if (a7 >= a20.y && a7 < a20.k) {
                    a19 = ol.ALLATORIxDEMO(a21.substring(0, a7 - a20.y), (String)a5, false);
                    a18 = ol.ALLATORIxDEMO(a21.substring(0, a7 - a20.y + 1), (String)a5, false) + 1;
                    double a23 = (int)(a10 + 1.0 + (double)((a4 - a8.r) * a8.a.o));
                    ff.ALLATORIxDEMO(a9 + 1.0 + a19, a23, a9 + 1.0 + a18, a23 + (double)a8.a.o + 1.0, -1728001024);
                }
                if (a6 >= a20.y && a6 < a20.k) {
                    int a24 = ol.ALLATORIxDEMO(a21.substring(0, a6 - a20.y), (String)a5, false);
                    a17 = ol.ALLATORIxDEMO(a21.substring(0, a6 - a20.y + 1), (String)a5, false) + 1;
                    a18 = a10 + 1.0 + (double)((a4 - a8.r) * a8.a.o);
                    ff.ALLATORIxDEMO(a9 + 1.0 + (double)a24, a18, a9 + 1.0 + (double)a17, a18 + (double)a8.a.o + 1.0, -1728001024);
                }
            }
            if (a4 < a8.r || a4 >= a8.r + a8.a.k) continue;
            if (a16 != null) {
                Matcher a25 = a8.a.x.matcher(a21);
                while (a25.find()) {
                    if (!a21.substring(a25.start(), a25.end()).equals(a16)) continue;
                    a17 = ol.ALLATORIxDEMO(a21.substring(0, a25.start()), (String)a5, false);
                    int a26 = ol.ALLATORIxDEMO(a21.substring(0, a25.end()), (String)a5, false) + 1;
                    double a27 = a10 + 1.0 + (double)((a4 - a8.r) * a8.a.o);
                    ff.ALLATORIxDEMO(a9 + 1.0 + (double)a17, a27, a9 + 1.0 + (double)a26, a27 + (double)a8.a.o + 1.0, -1728033792);
                }
            }
            if (a8.s != a8.g && a8.g > a20.y && a8.s <= a20.k && a8.s < a20.k) {
                int a28 = ol.ALLATORIxDEMO(a21.substring(0, Math.max(a8.s - a20.y, 0)), (String)a5, false);
                a17 = ol.ALLATORIxDEMO(a21.substring(0, Math.min(a8.g - a20.y, a22)), (String)a5, false) + 1;
                a18 = a10 + 1.0 + (double)((a4 - a8.r) * a8.a.o);
                ff.ALLATORIxDEMO(a9 + 1.0 + (double)a28, a18, a9 + 1.0 + (double)a17, a18 + (double)a8.a.o + 1.0, -1728052993);
            }
            a19 = a10 + (double)((a4 - a8.r) * a8.a.o) + 1.0;
            ol.ALLATORIxDEMO(a20.ALLATORIxDEMO(), (String)a5, a9 + 1.0, a19 + 1.0, false, true, false, -2039584);
            if (!a8.e || !a8.isEnabled() || a8.u / 6 % 2 != 0 || a8.t < a20.y || a8.t >= a20.k) continue;
            a18 = a9 + (double)ol.ALLATORIxDEMO(a21.substring(0, a8.t - a20.y), (String)a5, false);
            ff.ALLATORIxDEMO(a18 + 1.0, a19, a18 + 2.0, a19 + 1.0 + (double)a8.a.o, -3092272);
            a8.y = a18 + 1.0;
            a8.k = a19;
        }
        if (a8.hasVerticalScrollbar()) {
            Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation("minecraft", "aa.png"));
            a4 = Math.max((int)((double)(1.0f * (float)a8.a.k / (float)a8.a.ALLATORIxDEMO) * a12), 2);
            double a29 = a9 + a11 - 6.0;
            double a30 = a10 + (double)(1.0f * (float)a8.r / (float)a8.a.ALLATORIxDEMO) * (a12 - 4.0) + 1.0;
            ff.ALLATORIxDEMO(a29, a30, a29 + 5.0, a30 + (double)a4, -2039584);
        }
    }

    private /* synthetic */ int c(String a2, char a3, char a4) {
        int a5 = 0;
        char[] a6 = a2.toCharArray();
        for (int a7 = 0; a7 < a6.length; ++a7) {
            char a8 = a6[a7];
            if (a8 == a3) {
                ++a5;
                continue;
            }
            if (a8 != a4 || --a5 != 0) continue;
            return a7;
        }
        return 0;
    }

    private /* synthetic */ int ALLATORIxDEMO(String a2, char a3, char a4) {
        int a5 = 0;
        char[] a6 = a2.toCharArray();
        for (int a7 = a6.length - 1; a7 >= 0; --a7) {
            char a8 = a6[a7];
            if (a8 == a4) {
                ++a5;
                continue;
            }
            if (a8 != a3 || --a5 != 0) continue;
            return a7 - a6.length + 1;
        }
        return 0;
    }

    private /* synthetic */ int ALLATORIxDEMO(double a2, double a3) {
        ff a4;
        int a5 = 0;
        int a6 = 0;
        a2 -= (double)(a5 + 1);
        a3 -= (double)(a6 + 1);
        String a7 = a4.ua.c();
        int a8 = a4.a.o;
        ArrayList<yd> a9 = new ArrayList<yd>(a4.a.b);
        for (int a10 = 0; a10 < a9.size(); ++a10) {
            int a11;
            yd a12 = (yd)a9.get(a10);
            if (a10 < a4.r || a10 >= a4.r + a8 || !(a3 >= (double)(a11 = (a10 - a4.r) * a8)) || !(a3 < (double)(a11 + a8))) continue;
            int a13 = 0;
            char[] a14 = a12.o.toCharArray();
            for (int a15 = 1; a15 <= a14.length; ++a15) {
                int a16 = ol.ALLATORIxDEMO(a12.o.substring(0, a15), a7, false);
                if (a2 < (double)a13 + (double)(a16 - a13) / 2.0) {
                    return a12.y + a15 - 1;
                }
                a13 = a16;
            }
            return a12.k - 1;
        }
        return a4.a.c.length();
    }

    @Override
    public boolean keyTyped(char a2, int a3) throws IOException {
        ff a4;
        a4.keyTyped1(a2, a3);
        return a4.e;
    }

    public void keyTyped1(char a2, int a3) {
        ff a4;
        if (!a4.e) {
            return;
        }
        if (GuiScreen.isKeyComboCtrlA((int)a3)) {
            int a5;
            a4.t = a5 = 0;
            a4.s = a5;
            a4.g = a4.w.length();
            return;
        }
        if (!a4.isEnabled()) {
            return;
        }
        String a6 = a4.w;
        if (a3 == 203) {
            int a7 = 1;
            if (GuiScreen.isCtrlKeyDown()) {
                Matcher a8 = a4.a.x.matcher(a4.subStringText(0, a4.t));
                while (a8.find()) {
                    if (a8.start() == a8.end()) continue;
                    a7 = a4.t - a8.start();
                }
            }
            a4.ALLATORIxDEMO(a4.t - a7, GuiScreen.isShiftKeyDown());
            return;
        }
        if (a3 == 205) {
            Matcher a9;
            int a10 = 1;
            if (GuiScreen.isCtrlKeyDown() && ((a9 = a4.a.x.matcher(a4.subStringText(a4.t))).find() && a9.start() > 0 || a9.find())) {
                a10 = a9.start();
            }
            a4.ALLATORIxDEMO(a4.t + a10, GuiScreen.isShiftKeyDown());
            return;
        }
        if (a3 == 200) {
            a4.ALLATORIxDEMO(a4.c(), GuiScreen.isShiftKeyDown());
            return;
        }
        if (a3 == 208) {
            a4.ALLATORIxDEMO(a4.ALLATORIxDEMO(), GuiScreen.isShiftKeyDown());
            return;
        }
        if (a3 == 211) {
            int a11;
            String a12 = a4.getSelectionAfterText();
            if (!a12.isEmpty() && a4.s == a4.g) {
                a12 = a12.substring(1);
            }
            a4.setText(a4.getSelectionBeforeText() + a12);
            a4.t = a11 = a4.s;
            a4.g = a11;
            return;
        }
        if (a3 == 14) {
            int a13;
            String a14 = a4.getSelectionBeforeText();
            if (a4.s > 0 && a4.s == a4.g) {
                a14 = a14.substring(0, Math.max(0, a14.length() - 1));
                --a4.s;
            }
            a4.setText(a14 + a4.getSelectionAfterText());
            a4.t = a13 = a4.s;
            a4.g = a13;
            return;
        }
        if (GuiScreen.isKeyComboCtrlX((int)a3)) {
            if (a4.s != a4.g) {
                int a15;
                cm.ALLATORIxDEMO(a4.subStringText(a4.s, a4.g));
                String a16 = a4.getSelectionBeforeText();
                a4.setText(a16 + a4.getSelectionAfterText());
                a4.t = a15 = a16.length();
                a4.s = a15;
                a4.g = a15;
            }
            return;
        }
        if (GuiScreen.isKeyComboCtrlC((int)a3)) {
            if (a4.s != a4.g) {
                cm.ALLATORIxDEMO(a4.subStringText(a4.s, a4.g));
            }
            return;
        }
        if (GuiScreen.isKeyComboCtrlV((int)a3)) {
            a4.ALLATORIxDEMO(cm.ALLATORIxDEMO());
            return;
        }
        if (a3 == 44 && GuiScreen.isCtrlKeyDown()) {
            int a17;
            if (a4.m.isEmpty()) {
                return;
            }
            a4.q = true;
            a4.c.add(new wj(a4.w, a4.t));
            wj a18 = a4.m.remove(a4.m.size() - 1);
            a4.setText(a18.k);
            a4.t = a17 = a18.ALLATORIxDEMO;
            a4.s = a17;
            a4.g = a17;
            a4.q = false;
        } else {
            int a19;
            if (a3 != 21 || !GuiScreen.isCtrlKeyDown()) {
                if (a3 == 15) {
                    a4.ALLATORIxDEMO("    ");
                }
                if (a3 == 28) {
                    a4.ALLATORIxDEMO('\n' + a4.ALLATORIxDEMO());
                }
                if (ChatAllowedCharacters.isAllowedCharacter((char)a2)) {
                    a4.ALLATORIxDEMO(Character.toString(a2));
                }
                return;
            }
            if (a4.c.isEmpty()) {
                return;
            }
            a4.q = true;
            a4.m.add(new wj(a4.w, a4.t));
            wj a20 = a4.c.remove(a4.c.size() - 1);
            a4.setText(a20.k);
            a4.t = a19 = a20.ALLATORIxDEMO;
            a4.s = a19;
            a4.g = a19;
            a4.q = false;
        }
    }

    private /* synthetic */ String ALLATORIxDEMO() {
        ff a2;
        for (yd a3 : a2.a.b) {
            int a4;
            if (a2.t <= a3.y || a2.t > a3.k) continue;
            for (a4 = 0; a4 < a3.o.length() && a3.o.charAt(a4) == ' '; ++a4) {
            }
            return a3.o.substring(0, a4);
        }
        return "";
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, boolean a3) {
        ff a4;
        if ((a2 = Math.min(Math.max(a2, 0), a4.w.length())) == a4.t) {
            return;
        }
        if (!a3) {
            int a5;
            a4.t = a5 = a2;
            a4.s = a5;
            a4.g = a5;
            return;
        }
        int a6 = a4.t - a2;
        if (a4.t == a4.s) {
            a4.s -= a6;
        } else if (a4.t == a4.g) {
            a4.g -= a6;
        }
        if (a4.s > a4.g) {
            int a7 = a4.g;
            a4.g = a4.s;
            a4.s = a7;
        }
        a4.t = a2;
    }

    private /* synthetic */ void ALLATORIxDEMO(String a2) {
        int a3;
        ff a4;
        a4.setText(a4.getSelectionBeforeText() + a2 + a4.getSelectionAfterText());
        a4.t = a3 = a4.s + a2.length();
        a4.s = a3;
        a4.g = a3;
    }

    private /* synthetic */ int c() {
        ff a2;
        String a3 = a2.ua.c();
        for (int a4 = 0; a4 < a2.a.b.size(); ++a4) {
            yd a5 = a2.a.b.get(a4);
            if (a2.t < a5.y || a2.t >= a5.k) continue;
            if (a4 == 0) {
                return 0;
            }
            return a2.ALLATORIxDEMO((double)(1 + ol.ALLATORIxDEMO(a5.o.substring(0, a2.t - a5.y), a3, false)), 1 + (a4 - 1 - a2.r) * a2.a.o);
        }
        return 0;
    }

    private /* synthetic */ int ALLATORIxDEMO() {
        ff a2;
        String a3 = a2.ua.c();
        for (int a4 = 0; a4 < a2.a.b.size(); ++a4) {
            yd a5 = a2.a.b.get(a4);
            if (a2.t < a5.y || a2.t >= a5.k) continue;
            return a2.ALLATORIxDEMO((double)(1 + ol.ALLATORIxDEMO(a5.o.substring(0, a2.t - a5.y), a3, false)), 1 + (a4 + 1 - a2.r) * a2.a.o);
        }
        return a2.w.length();
    }

    public String getSelectionBeforeText() {
        ff a2;
        if (a2.s <= 0) {
            return "";
        }
        if (a2.s > a2.w.length()) {
            return a2.w;
        }
        return a2.subStringText(0, a2.s);
    }

    public String getSelectionAfterText() {
        ff a2;
        return a2.subStringText(a2.g);
    }

    public String subStringText(int a2) {
        ff a3;
        if (a2 < 0) {
            a2 = 0;
        }
        if (a2 > a3.w.length()) {
            return "";
        }
        return a3.w.substring(a2);
    }

    public String subStringText(int a2, int a3) {
        ff a4;
        if (a2 < 0) {
            a2 = 0;
        }
        if (a3 > a4.w.length()) {
            a3 = a4.w.length();
        }
        if (a2 > a4.w.length()) {
            return "";
        }
        if (a3 < 0 || a3 < a2) {
            return "";
        }
        return a4.w.substring(a2, a3);
    }

    @Override
    public void runClick(int a2, int a3, int a4) {
        ff a5;
        a5.mouseClicked(a2, a3, a4);
        super.runClick(a2, a3, a4);
    }

    @Override
    public void runUnClick(int a2, int a3, int a4) {
        ff a5;
        a5.e = false;
        super.runUnClick(a2, a3, a4);
    }

    public void mouseClicked(int a2, int a3, int a4) {
        int a5;
        ff a6;
        double a7 = 0.0;
        double a8 = 0.0;
        double a9 = a6.p.c();
        double a10 = a6.u.c();
        a2 = (int)(((double)a2 - a6.getXPos() - a6.s.ALLATORIxDEMO()) / a6.ba.c());
        a3 = (int)(((double)a3 - a6.getYPos() - a6.g.ALLATORIxDEMO()) / a6.ba.c());
        a6.e = true;
        a6.t = a5 = a6.ALLATORIxDEMO((double)a2, a3);
        a6.g = a5;
        a6.s = a5;
        a6.i = a4 == 0;
        a6.l = false;
        long a11 = System.currentTimeMillis();
        if (a6.i && (double)(a6.a.ALLATORIxDEMO * a6.a.o) > a10 && (double)a2 > a7 + a9 - 8.0) {
            a6.i = false;
            a6.z = true;
        } else if (a11 - a6.o < 500L) {
            a6.l = true;
            Matcher a12 = a6.a.x.matcher(a6.w);
            while (a12.find()) {
                if (a6.t <= a12.start() || a6.t >= a12.end()) continue;
                a6.s = a12.start();
                a6.g = a12.end();
                break;
            }
        }
        a6.o = a11;
    }

    public void updateScreen() {
        ff a2;
        double a3 = a2.u.c();
        ++a2.u;
        int a4 = Mouse.getDWheel();
        if (a4 != 0) {
            a2.r = a2.r + (a4 > 0 ? -1 : 1);
            a2.r = (int)Math.max(Math.min((double)a2.r, (double)a2.a.ALLATORIxDEMO - a3 / (double)a2.a.o), 0.0);
        }
    }

    public void setText(String a2) {
        ff a3;
        double a4 = a3.p.c();
        double a5 = a3.u.c();
        a2 = a2.replace("\r", "");
        if (a3.w != null && a3.w.equals(a2)) {
            return;
        }
        if (!a3.q) {
            a3.m.add(new wj(a3.w, a3.t));
            a3.c.clear();
        }
        a3.w = a2;
        a3.a = new nm(a2);
        a3.a.ALLATORIxDEMO(a3.ua.c(), a4, a5);
        if (a3.x) {
            a3.a.ALLATORIxDEMO();
        }
        if (a3.r > a3.a.ALLATORIxDEMO - a3.a.k) {
            a3.r = Math.max(0, a3.a.ALLATORIxDEMO - a3.a.k);
        }
        if (a3.ALLATORIxDEMO) {
            return;
        }
        CompletableFuture.runAsync(() -> {
            ff a2;
            sj.ALLATORIxDEMO(() -> {
                ff a2;
                a2.runAction("textChange");
            });
        });
    }

    public String getText() {
        ff a2;
        return a2.w;
    }

    public boolean isEnabled() {
        ff a2;
        return a2.n && a2.j;
    }

    public boolean hasVerticalScrollbar() {
        ff a2;
        return a2.a.k < a2.a.ALLATORIxDEMO;
    }

    public void enableCodeHighlighting() {
        ff a2;
        a2.x = true;
        a2.a.ALLATORIxDEMO();
    }

    public boolean isActive() {
        ff a2;
        return a2.e;
    }

    public static void ALLATORIxDEMO(double a2, double a3, double a4, double a5, int a6) {
        double a7;
        if (a2 < a4) {
            a7 = a2;
            a2 = a4;
            a4 = a7;
        }
        if (a3 < a5) {
            a7 = a3;
            a3 = a5;
            a5 = a7;
        }
        float a8 = (float)(a6 >> 24 & 0xFF) / 255.0f;
        float a9 = (float)(a6 >> 16 & 0xFF) / 255.0f;
        float a10 = (float)(a6 >> 8 & 0xFF) / 255.0f;
        float a11 = (float)(a6 & 0xFF) / 255.0f;
        Tessellator a12 = Tessellator.getInstance();
        BufferBuilder a13 = a12.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)a9, (float)a10, (float)a11, (float)a8);
        a13.begin(7, DefaultVertexFormats.POSITION);
        a13.pos(a2, a5, 0.0).endVertex();
        a13.pos(a4, a5, 0.0).endVertex();
        a13.pos(a4, a3, 0.0).endVertex();
        a13.pos(a2, a3, 0.0).endVertex();
        a12.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }

    @Override
    public Object getValue(String a2) {
        ff a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "type_": 
            case "type": {
                return "textarea";
            }
            case "drawbackground": {
                return a3.oa.c() ? "1" : "0";
            }
            case "text_": 
            case "text": {
                return a3.w;
            }
            case "focused_": 
            case "focused": {
                return a3.isActive() ? "1" : "0";
            }
            case "drawbackground_": {
                return a3.oa.f();
            }
            case "color": {
                return a3.ka.c();
            }
            case "color_": {
                return a3.ka.f();
            }
            case "font": {
                return a3.ua.c();
            }
            case "font_": {
                return a3.ua.f();
            }
            case "cursorx": {
                return String.valueOf(a3.y);
            }
            case "cursory": {
                return String.valueOf(a3.k);
            }
            case "cursorposition": {
                return String.valueOf(a3.t);
            }
        }
        return super.getValue(a2);
    }

    @Override
    public void setValue(String a2, Object a3) {
        ff a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "drawbackground": {
                a4.oa.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "text": {
                sj.ALLATORIxDEMO(() -> {
                    ff a3;
                    a3.ALLATORIxDEMO = true;
                    a3.setText(a3.toMolangParser((String)a3).c());
                    a3.ALLATORIxDEMO = false;
                });
                return;
            }
            case "text_write": {
                sj.ALLATORIxDEMO(() -> {
                    ff a3;
                    a3.ALLATORIxDEMO = true;
                    a3.ALLATORIxDEMO(a3.toMolangParser((String)a3).c());
                    a3.ALLATORIxDEMO = false;
                });
                return;
            }
            case "text_write_string": {
                sj.ALLATORIxDEMO(() -> {
                    ff a3;
                    a3.ALLATORIxDEMO = true;
                    a3.ALLATORIxDEMO((String)a3);
                    a3.ALLATORIxDEMO = false;
                });
                return;
            }
            case "text_string": {
                sj.ALLATORIxDEMO(() -> {
                    ff a3;
                    a3.ALLATORIxDEMO = true;
                    a3.setText((String)a3);
                    a3.ALLATORIxDEMO = false;
                });
                return;
            }
            case "focused": {
                a4.e = a4.toMolangParser(a3).c();
                return;
            }
            case "font": {
                a4.ua.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "color": {
                a4.ka.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
        }
        super.setValue(a2, a3);
    }
}

