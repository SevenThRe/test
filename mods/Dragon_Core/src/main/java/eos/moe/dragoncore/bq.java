/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.settings.GameSettings
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.kca;
import eos.moe.dragoncore.mk;
import eos.moe.dragoncore.oga;
import eos.moe.dragoncore.ww;
import java.awt.Color;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class bq
extends FontRenderer {
    private static bq c;
    private FontRenderer q;
    private boolean b;
    private float o;
    private float y;
    private float k;
    private float ALLATORIxDEMO;

    public bq(GameSettings a2, TextureManager a3, FontRenderer a4) {
        super(a2, a4.locationFontTexture, a3, a4.unicodeFlag);
        bq a5;
        a5.q = a4;
        try {
            Class.forName("Config");
            a5.b = true;
        }
        catch (Exception a6) {
            a5.b = false;
        }
    }

    public bq(FontRenderer a2, ResourceLocation a3) {
        super(Minecraft.getMinecraft().gameSettings, a3, Minecraft.getMinecraft().renderEngine, a2.unicodeFlag);
        bq a4;
        a4.q = a2;
        try {
            Class.forName("Config");
            a4.b = true;
        }
        catch (Exception a5) {
            a4.b = false;
        }
    }

    public void renderStringAtPos(String a2, boolean a3) {
        for (int a4 = 0; a4 < a2.length(); ++a4) {
            boolean a5;
            bq a6;
            char a7 = a2.charAt(a4);
            kca a8 = null;
            if (a7 == '\u00a7' && a4 + 1 < a2.length() && (a8 = oga.k.ALLATORIxDEMO(a2.charAt(a4 + 1))) != null && a8.ALLATORIxDEMO()) {
                if (!a3) {
                    GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)a6.ALLATORIxDEMO);
                    ww.ALLATORIxDEMO(a8.ALLATORIxDEMO());
                    mk.ALLATORIxDEMO(a6.posX + a8.f(), a6.posY + a8.c(), a8.z(), a8.k(), a8.s(), a8.w(), a8.d(), a8.x());
                    GlStateManager.color((float)a6.o, (float)a6.y, (float)a6.k, (float)a6.ALLATORIxDEMO);
                }
                a6.posX += a8.ALLATORIxDEMO() + 1.0f;
                ++a4;
                continue;
            }
            if (a7 == '\u00a7' && a4 + 1 < a2.length()) {
                if (a4 + 7 < a2.length() && a2.charAt(a4 + 1) == '#') {
                    String a9 = a2.substring(a4 + 2, a4 + 8);
                    boolean a10 = true;
                    for (char a11 : a9.toCharArray()) {
                        int a12 = "0123456789abcdefABCDEF".indexOf(a11);
                        if (a12 != -1) continue;
                        a10 = false;
                        break;
                    }
                    if (!a10) continue;
                    a6.randomStyle = false;
                    a6.boldStyle = false;
                    a6.strikethroughStyle = false;
                    a6.underlineStyle = false;
                    a6.italicStyle = false;
                    Color a13 = Color.decode("0x" + a9);
                    float a14 = 3.3f;
                    int a15 = 42;
                    if (a3) {
                        a13 = new Color((int)Math.max((float)a15, (float)a13.getRed() / a14), (int)Math.max((float)a15, (float)a13.getBlue() / a14), (int)Math.max((float)a15, (float)a13.getGreen() / a14), (int)a6.alpha);
                    }
                    a6.setColor((float)a13.getRed() / 255.0f, (float)a13.getGreen() / 255.0f, (float)a13.getBlue() / 255.0f, a6.alpha);
                    a6.textColor = a13.getRGB();
                    a4 += 7;
                    continue;
                }
                int a16 = "0123456789abcdefklmnor".indexOf(String.valueOf(a2.charAt(a4 + 1)).toLowerCase(Locale.ROOT).charAt(0));
                if (a16 < 16) {
                    int a17;
                    a6.randomStyle = false;
                    a6.boldStyle = false;
                    a6.strikethroughStyle = false;
                    a6.underlineStyle = false;
                    a6.italicStyle = false;
                    if (a16 < 0 || a16 > 15) {
                        a16 = 15;
                    }
                    if (a3) {
                        a16 += 16;
                    }
                    a6.textColor = a17 = a6.colorCode[a16];
                    a6.setColor((float)(a17 >> 16) / 255.0f, (float)(a17 >> 8 & 0xFF) / 255.0f, (float)(a17 & 0xFF) / 255.0f, a6.alpha);
                } else if (a16 == 16) {
                    a6.randomStyle = true;
                } else if (a16 == 17) {
                    a6.boldStyle = true;
                } else if (a16 == 18) {
                    a6.strikethroughStyle = true;
                } else if (a16 == 19) {
                    a6.underlineStyle = true;
                } else if (a16 == 20) {
                    a6.italicStyle = true;
                } else if (a16 == 21) {
                    a6.randomStyle = false;
                    a6.boldStyle = false;
                    a6.strikethroughStyle = false;
                    a6.underlineStyle = false;
                    a6.italicStyle = false;
                    a6.setColor(a6.red, a6.blue, a6.green, a6.alpha);
                }
                ++a4;
                continue;
            }
            a8 = oga.k.ALLATORIxDEMO(a7);
            if (a8 != null && !a8.ALLATORIxDEMO()) {
                if (!a3) {
                    GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)a6.ALLATORIxDEMO);
                    ww.ALLATORIxDEMO(a8.ALLATORIxDEMO());
                    mk.ALLATORIxDEMO(a6.posX + a8.f(), a6.posY + a8.c(), 0.0f, 0.0f, a8.s(), a8.w(), a8.s(), a8.w());
                    GlStateManager.color((float)a6.o, (float)a6.y, (float)a6.k, (float)a6.ALLATORIxDEMO);
                }
                a6.posX += a8.ALLATORIxDEMO() + 1.0f;
                continue;
            }
            int a18 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".indexOf(a7);
            if (a6.randomStyle && a18 != -1) {
                char a19;
                int a20 = a6.getCharWidth(a7);
                while (a20 != a6.getCharWidth(a19 = "\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".charAt(a18 = a6.fontRandom.nextInt("\u00c0\u00c1\u00c2\u00c8\u00ca\u00cb\u00cd\u00d3\u00d4\u00d5\u00da\u00df\u00e3\u00f5\u011f\u0130\u0131\u0152\u0153\u015e\u015f\u0174\u0175\u017e\u0207\u0000\u0000\u0000\u0000\u0000\u0000\u0000 !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u0000\u00c7\u00fc\u00e9\u00e2\u00e4\u00e0\u00e5\u00e7\u00ea\u00eb\u00e8\u00ef\u00ee\u00ec\u00c4\u00c5\u00c9\u00e6\u00c6\u00f4\u00f6\u00f2\u00fb\u00f9\u00ff\u00d6\u00dc\u00f8\u00a3\u00d8\u00d7\u0192\u00e1\u00ed\u00f3\u00fa\u00f1\u00d1\u00aa\u00ba\u00bf\u00ae\u00ac\u00bd\u00bc\u00a1\u00ab\u00bb\u2591\u2592\u2593\u2502\u2524\u2561\u2562\u2556\u2555\u2563\u2551\u2557\u255d\u255c\u255b\u2510\u2514\u2534\u252c\u251c\u2500\u253c\u255e\u255f\u255a\u2554\u2569\u2566\u2560\u2550\u256c\u2567\u2568\u2564\u2565\u2559\u2558\u2552\u2553\u256b\u256a\u2518\u250c\u2588\u2584\u258c\u2590\u2580\u03b1\u03b2\u0393\u03c0\u03a3\u03c3\u03bc\u03c4\u03a6\u0398\u03a9\u03b4\u221e\u2205\u2208\u2229\u2261\u00b1\u2265\u2264\u2320\u2321\u00f7\u2248\u00b0\u2219\u00b7\u221a\u207f\u00b2\u25a0\u0000".length())))) {
                }
                a7 = a19;
            }
            float a21 = a18 == -1 || a6.unicodeFlag || a6.b ? 0.5f : 1.0f;
            boolean bl2 = a5 = (a7 == '\u0000' || a18 == -1 || a6.unicodeFlag) && a3;
            if (a5) {
                a6.posX -= a21;
                a6.posY -= a21;
            }
            float a22 = a6.renderChar(a7, a6.italicStyle);
            if (a5) {
                a6.posX += a21;
                a6.posY += a21;
            }
            if (a6.boldStyle) {
                a6.posX += a21;
                if (a5) {
                    a6.posX -= a21;
                    a6.posY -= a21;
                }
                a6.renderChar(a7, a6.italicStyle);
                a6.posX -= a21;
                if (a5) {
                    a6.posX += a21;
                    a6.posY += a21;
                }
                a22 += 1.0f;
            }
            a6.doDraw(a22);
        }
    }

    public void setColor(float a2, float a3, float a4, float a5) {
        bq a6;
        a6.o = a2;
        a6.y = a3;
        a6.k = a4;
        a6.ALLATORIxDEMO = a5;
        super.setColor(a2, a3, a4, a5);
    }

    public boolean hasData(char a2, boolean a3) {
        kca a4 = oga.k.ALLATORIxDEMO(a2);
        return a4 != null && (!a4.ALLATORIxDEMO() || a3);
    }

    public int getFontTextureCharWidth(char a2, boolean a3) {
        bq a4;
        kca a5 = oga.k.ALLATORIxDEMO(a2);
        if (a5 != null && (!a5.ALLATORIxDEMO() || a3)) {
            return (int)a5.ALLATORIxDEMO() + 1;
        }
        return super.getCharWidth(a2);
    }

    public int getStringWidth(String a2) {
        if (a2 == null) {
            return 0;
        }
        int a3 = 0;
        boolean a4 = false;
        oga a5 = oga.k;
        for (int a6 = 0; a6 < a2.length(); ++a6) {
            kca a7;
            bq a8;
            char a9 = a2.charAt(a6);
            int a10 = a8.getCharWidth(a9);
            if (a10 < 0 && a6 < a2.length() - 1 && (a7 = a5.ALLATORIxDEMO(a2.charAt(a6 + 1))) != null && a7.ALLATORIxDEMO()) {
                ++a6;
                a3 = (int)((float)a3 + a7.ALLATORIxDEMO());
                continue;
            }
            if (a10 < 0 && a6 < a2.length() - 1 && a2.charAt(a6 + 1) == '#') {
                a6 += 7;
                continue;
            }
            if (a10 < 0 && a6 < a2.length() - 1) {
                if ((a9 = a2.charAt(++a6)) != 'l' && a9 != 'L') {
                    if (a9 == 'r' || a9 == 'R') {
                        a4 = false;
                    }
                } else {
                    a4 = true;
                }
                a10 = 0;
            }
            kca a11 = a5.ALLATORIxDEMO(a9);
            a3 = a10 > 0 && a11 != null && !a11.ALLATORIxDEMO() ? (int)((float)a3 + a11.ALLATORIxDEMO()) : (a3 += a10);
            if (!a4 || a10 <= 0) continue;
            ++a3;
        }
        return a3;
    }

    public String trimStringToWidth(String a2, int a3, boolean a4) {
        StringBuilder a5 = new StringBuilder();
        int a6 = 0;
        int a7 = a4 ? a2.length() - 1 : 0;
        int a8 = a4 ? -1 : 1;
        boolean a9 = false;
        boolean a10 = false;
        oga a11 = oga.k;
        int a12 = 0;
        for (int a13 = a7; a13 >= 0 && a13 < a2.length() && a6 < a3; a13 += a8) {
            bq a14;
            char a15 = a2.charAt(a13);
            int a16 = a14.getCharWidth(a15);
            kca a17 = a11.ALLATORIxDEMO(a15);
            if (a12 > 0) {
                --a12;
            } else if (a9 && a17 != null && a17.ALLATORIxDEMO()) {
                a9 = false;
                a6 = (int)((float)a6 + a17.ALLATORIxDEMO());
                if (a10) {
                    ++a6;
                }
            } else if (a9 && a15 == '#') {
                a9 = false;
                a12 = 6;
            } else if (a9) {
                a9 = false;
                if (a15 != 'l' && a15 != 'L') {
                    if (a15 == 'r' || a15 == 'R') {
                        a10 = false;
                    }
                } else {
                    a10 = true;
                }
            } else if (a16 < 0) {
                a9 = true;
            } else {
                a6 += a16;
                if (a10) {
                    ++a6;
                }
            }
            if (a6 > a3) break;
            if (a4) {
                a5.insert(0, a15);
                continue;
            }
            a5.append(a15);
        }
        return a5.toString();
    }

    public void onResourceManagerReload(IResourceManager a2) {
        bq a3;
        super.onResourceManagerReload(a2);
    }

    public static void ALLATORIxDEMO() {
        Minecraft a2 = Minecraft.getMinecraft();
        c = new bq(a2.gameSettings, a2.renderEngine, a2.fontRenderer);
        if (a2.gameSettings.language != null) {
            c.setUnicodeFlag(a2.isUnicode());
            c.setBidiFlag(a2.languageManager.isCurrentLanguageBidirectional());
        }
        a2.resourceManager.registerReloadListener((IResourceManagerReloadListener)c);
    }

    public static bq ALLATORIxDEMO() {
        return c;
    }
}

