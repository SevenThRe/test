/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ChatAllowedCharacters
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.vs;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import org.lwjgl.opengl.GL11;

public class ao
extends Gui {
    private final FontRenderer j;
    private final int i;
    private final int l;
    private final int z;
    private final int s;
    private String g = "";
    private int t = 32;
    private int r;
    private boolean x = false;
    private boolean v = true;
    private int m = 0;
    private int c = 0;
    private int q = 0;
    private int b = 0xE0E0E0;
    private int o = 0x707070;
    private boolean y = true;
    private boolean k = true;
    private boolean ALLATORIxDEMO;

    public ao(FontRenderer a2, int a3, int a4, int a5, int a6, boolean a7) {
        ao a8;
        a8.j = a2;
        a8.i = a3;
        a8.l = a4;
        a8.z = a5;
        a8.s = a6;
        a8.ALLATORIxDEMO = a7;
    }

    public void updateCursorCounter() {
        ao a2;
        ++a2.r;
    }

    public void setText(String a2) {
        ao a3;
        a3.g = a2.length() > a3.t ? a2.substring(0, a3.t) : a2;
        a3.setCursorPositionEnd();
    }

    public String getText() {
        ao a2;
        return a2.g;
    }

    public String getSelectedtext() {
        ao a2;
        int a3 = a2.c < a2.q ? a2.c : a2.q;
        int a4 = a2.c < a2.q ? a2.q : a2.c;
        return a2.g.substring(a3, a4);
    }

    public void writeText(String a2) {
        int a3;
        ao a4;
        String a5 = "";
        String a6 = vs.ALLATORIxDEMO(a2, a4.ALLATORIxDEMO);
        int a7 = a4.c < a4.q ? a4.c : a4.q;
        int a8 = a4.c < a4.q ? a4.q : a4.c;
        int a9 = a4.t - a4.g.length() - (a7 - a4.q);
        if (a4.g.length() > 0) {
            a5 = a5 + a4.g.substring(0, a7);
        }
        if (a9 < a6.length()) {
            a5 = a5 + a6.substring(0, a9);
            a3 = a9;
        } else {
            a5 = a5 + a6;
            a3 = a6.length();
        }
        if (a4.g.length() > 0 && a8 < a4.g.length()) {
            a5 = a5 + a4.g.substring(a8);
        }
        a4.g = a5;
        a4.moveCursorBy(a7 - a4.q + a3);
    }

    public void deleteWords(int a2) {
        ao a3;
        if (a3.g.length() != 0) {
            if (a3.q != a3.c) {
                a3.writeText("");
            } else {
                a3.deleteFromCursor(a3.getNthWordFromCursor(a2) - a3.c);
            }
        }
    }

    public void deleteFromCursor(int a2) {
        ao a3;
        if (a3.g.length() != 0) {
            if (a3.q != a3.c) {
                a3.writeText("");
            } else {
                boolean a4 = a2 < 0;
                int a5 = a4 ? a3.c + a2 : a3.c;
                int a6 = a4 ? a3.c : a3.c + a2;
                String a7 = "";
                if (a5 >= 0) {
                    a7 = a3.g.substring(0, a5);
                }
                if (a6 < a3.g.length()) {
                    a7 = a7 + a3.g.substring(a6);
                }
                a3.g = a7;
                if (a4) {
                    a3.moveCursorBy(a2);
                }
            }
        }
    }

    public int getNthWordFromCursor(int a2) {
        ao a3;
        return a3.getNthWordFromPos(a2, a3.getCursorPosition());
    }

    public int getNthWordFromPos(int a2, int a3) {
        ao a4;
        return a4.func_73798_a(a2, a4.getCursorPosition(), true);
    }

    public int func_73798_a(int a2, int a3, boolean a4) {
        int a5 = a3;
        boolean a6 = a2 < 0;
        int a7 = Math.abs(a2);
        for (int a8 = 0; a8 < a7; ++a8) {
            ao a9;
            if (a6) {
                while (a4 && a5 > 0 && a9.g.charAt(a5 - 1) == ' ') {
                    --a5;
                }
                while (a5 > 0 && a9.g.charAt(a5 - 1) != ' ') {
                    --a5;
                }
                continue;
            }
            int a10 = a9.g.length();
            if ((a5 = a9.g.indexOf(32, a5)) == -1) {
                a5 = a10;
                continue;
            }
            while (a4 && a5 < a10 && a9.g.charAt(a5) == ' ') {
                ++a5;
            }
        }
        return a5;
    }

    public void moveCursorBy(int a2) {
        ao a3;
        a3.setCursorPosition(a3.q + a2);
    }

    public void setCursorPosition(int a2) {
        ao a3;
        a3.c = a2;
        int a4 = a3.g.length();
        if (a3.c < 0) {
            a3.c = 0;
        }
        if (a3.c > a4) {
            a3.c = a4;
        }
        a3.setSelectionPos(a3.c);
    }

    public void setCursorPositionZero() {
        ao a2;
        a2.setCursorPosition(0);
    }

    public void setCursorPositionEnd() {
        ao a2;
        a2.setCursorPosition(a2.g.length());
    }

    public boolean textboxKeyTyped(char a2, int a3) {
        ao a4;
        if (a4.v && a4.x) {
            switch (a2) {
                case '\u0001': {
                    a4.setCursorPositionEnd();
                    a4.setSelectionPos(0);
                    return true;
                }
                case '\u0003': {
                    GuiScreen.func_146275_d((String)a4.getSelectedtext());
                    return true;
                }
                case '\u0016': {
                    a4.writeText(GuiScreen.func_146277_j());
                    return true;
                }
                case '\u0018': {
                    GuiScreen.func_146275_d((String)a4.getSelectedtext());
                    a4.writeText("");
                    return true;
                }
            }
            switch (a3) {
                case 14: {
                    if (GuiScreen.func_146271_m()) {
                        a4.deleteWords(-1);
                    } else {
                        a4.deleteFromCursor(-1);
                    }
                    return true;
                }
                case 199: {
                    if (GuiScreen.func_146272_n()) {
                        a4.setSelectionPos(0);
                    } else {
                        a4.setCursorPositionZero();
                    }
                    return true;
                }
                case 203: {
                    if (GuiScreen.func_146272_n()) {
                        if (GuiScreen.func_146271_m()) {
                            a4.setSelectionPos(a4.getNthWordFromPos(-1, a4.getSelectionEnd()));
                        } else {
                            a4.setSelectionPos(a4.getSelectionEnd() - 1);
                        }
                    } else if (GuiScreen.func_146271_m()) {
                        a4.setCursorPosition(a4.getNthWordFromCursor(-1));
                    } else {
                        a4.moveCursorBy(-1);
                    }
                    return true;
                }
                case 205: {
                    if (GuiScreen.func_146272_n()) {
                        if (GuiScreen.func_146271_m()) {
                            a4.setSelectionPos(a4.getNthWordFromPos(1, a4.getSelectionEnd()));
                        } else {
                            a4.setSelectionPos(a4.getSelectionEnd() + 1);
                        }
                    } else if (GuiScreen.func_146271_m()) {
                        a4.setCursorPosition(a4.getNthWordFromCursor(1));
                    } else {
                        a4.moveCursorBy(1);
                    }
                    return true;
                }
                case 207: {
                    if (GuiScreen.func_146272_n()) {
                        a4.setSelectionPos(a4.g.length());
                    } else {
                        a4.setCursorPositionEnd();
                    }
                    return true;
                }
                case 211: {
                    if (GuiScreen.func_146271_m()) {
                        a4.deleteWords(1);
                    } else {
                        a4.deleteFromCursor(1);
                    }
                    return true;
                }
            }
            if (ChatAllowedCharacters.func_71566_a((char)a2)) {
                a4.writeText(Character.toString(a2));
                return true;
            }
            return false;
        }
        return false;
    }

    public void mouseClicked(int a2, int a3, int a4) {
        ao a5;
        String a6 = a5.g.replace('\u00a7', '?');
        boolean a7 = a2 >= a5.i && a2 < a5.i + a5.z && a3 >= a5.l && a3 < a5.l + a5.s;
        a5.setFocused(a5.v && a7);
        if (a5.x && a4 == 0) {
            int a8 = a2 - a5.i;
            if (a5.k) {
                a8 -= 4;
            }
            String a9 = a5.j.func_78269_a(a6.substring(a5.m), a5.getWidth());
            a5.setCursorPosition(a5.j.func_78269_a(a9, a8).length() + a5.m);
        }
    }

    public void drawTextBox() {
        ao a2;
        String a3 = a2.g.replace('\u00a7', '?');
        if (a2.getVisible()) {
            if (a2.getEnableBackgroundDrawing()) {
                ao.func_73734_a((int)(a2.i - 1), (int)(a2.l - 1), (int)(a2.i + a2.z + 1), (int)(a2.l + a2.s + 1), (int)-6250336);
                ao.func_73734_a((int)a2.i, (int)a2.l, (int)(a2.i + a2.z), (int)(a2.l + a2.s), (int)-16777216);
            }
            int a4 = a2.v ? a2.b : a2.o;
            int a5 = a2.c - a2.m;
            int a6 = a2.q - a2.m;
            String a7 = a2.j.func_78269_a(a3.substring(a2.m), a2.getWidth());
            boolean a8 = a5 >= 0 && a5 <= a7.length();
            boolean a9 = a2.x && a2.r / 6 % 2 == 0 && a8;
            int a10 = a2.k ? a2.i + 4 : a2.i;
            int a11 = a2.k ? a2.l + (a2.s - 8) / 2 : a2.l;
            int a12 = a10;
            if (a6 > a7.length()) {
                a6 = a7.length();
            }
            if (a7.length() > 0) {
                String a13 = a8 ? a7.substring(0, a5) : a7;
                a12 = a2.j.func_175063_a(a13, (float)a10, (float)a11, a4);
            }
            boolean a14 = a2.c < a2.g.length() || a2.g.length() >= a2.getMaxStringLength();
            int a15 = a12;
            if (!a8) {
                a15 = a5 > 0 ? a10 + a2.z : a10;
            } else if (a14) {
                a15 = a12 - 1;
                --a12;
            }
            if (a7.length() > 0 && a8 && a5 < a7.length()) {
                a2.j.func_175063_a(a7.substring(a5), (float)a12, (float)a11, a4);
            }
            if (a9) {
                if (a14) {
                    Gui.func_73734_a((int)a15, (int)(a11 - 1), (int)(a15 + 1), (int)(a11 + 1 + a2.j.field_78288_b), (int)-3092272);
                } else {
                    a2.j.func_175063_a("_", (float)a15, (float)a11, a4);
                }
            }
            if (a6 != a5) {
                int a16 = a10 + a2.j.func_78256_a(a7.substring(0, a6));
                a2.ALLATORIxDEMO(a15, a11 - 1, a16 - 1, a11 + 1 + a2.j.field_78288_b);
            }
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, int a3, int a4, int a5) {
        int a6;
        if (a2 < a4) {
            a6 = a2;
            a2 = a4;
            a4 = a6;
        }
        if (a3 < a5) {
            a6 = a3;
            a3 = a5;
            a5 = a6;
        }
        Tessellator a7 = Tessellator.func_178181_a();
        BufferBuilder a8 = a7.func_178180_c();
        GL11.glColor4f((float)0.0f, (float)0.0f, (float)255.0f, (float)255.0f);
        GlStateManager.func_179090_x();
        GlStateManager.func_179115_u();
        GlStateManager.func_179116_f((int)5387);
        a8.func_181668_a(7, DefaultVertexFormats.field_181706_f);
        a8.func_181662_b((double)a2, (double)a5, 0.0);
        a8.func_181662_b((double)a4, (double)a5, 0.0);
        a8.func_181662_b((double)a4, (double)a3, 0.0);
        a8.func_181662_b((double)a2, (double)a3, 0.0);
        a7.func_78381_a();
        GlStateManager.func_179134_v();
        GlStateManager.func_179098_w();
    }

    public void setMaxStringLength(int a2) {
        ao a3;
        a3.t = a2;
        if (a3.g.length() > a2) {
            a3.g = a3.g.substring(0, a2);
        }
    }

    public int getMaxStringLength() {
        ao a2;
        return a2.t;
    }

    public int getCursorPosition() {
        ao a2;
        return a2.c;
    }

    public boolean getEnableBackgroundDrawing() {
        ao a2;
        return a2.k;
    }

    public void setEnableBackgroundDrawing(boolean a2) {
        a.k = a2;
    }

    public void setTextColor(int a2) {
        a.b = a2;
    }

    public void func_82266_h(int a2) {
        a.o = a2;
    }

    public void setFocused(boolean a2) {
        ao a3;
        if (a2 && !a3.x) {
            a3.r = 0;
        }
        a3.x = a2;
    }

    public boolean isFocused() {
        ao a2;
        return a2.x;
    }

    public void func_82265_c(boolean a2) {
        a.v = a2;
    }

    public int getSelectionEnd() {
        ao a2;
        return a2.q;
    }

    public int getWidth() {
        ao a2;
        return a2.getEnableBackgroundDrawing() ? a2.z - 8 : a2.z;
    }

    public void setSelectionPos(int a2) {
        ao a3;
        String a4 = a3.g.replace('\u00a7', '?');
        int a5 = a4.length();
        if (a2 > a5) {
            a2 = a5;
        }
        if (a2 < 0) {
            a2 = 0;
        }
        a3.q = a2;
        if (a3.j != null) {
            if (a3.m > a5) {
                a3.m = a5;
            }
            int a6 = a3.getWidth();
            String a7 = a3.j.func_78269_a(a4.substring(a3.m), a6);
            int a8 = a7.length() + a3.m;
            if (a2 == a3.m) {
                a3.m -= a3.j.func_78262_a(a4, a6, true).length();
            }
            if (a2 > a8) {
                a3.m += a2 - a8;
            } else if (a2 <= a3.m) {
                a3.m -= a3.m - a2;
            }
            if (a3.m < 0) {
                a3.m = 0;
            }
            if (a3.m > a5) {
                a3.m = a5;
            }
        }
    }

    public boolean getVisible() {
        ao a2;
        return a2.y;
    }

    public void setVisible(boolean a2) {
        a.y = a2;
    }
}

