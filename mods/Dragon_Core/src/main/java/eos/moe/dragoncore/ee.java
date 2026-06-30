/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$LogicOp
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.ChatAllowedCharacters
 *  net.minecraft.util.math.MathHelper
 */
package eos.moe.dragoncore;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import java.awt.Color;
import java.io.IOException;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.util.math.MathHelper;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class ee
extends jj {
    private final FontRenderer oa;
    private String ka = "";
    private mh ua;
    private int s;
    private mh g;
    private boolean m;
    private int c;
    private int q;
    private int b;
    private int o = 0xE0E0E0;
    private int y = 0x707070;
    private mh k;
    private Predicate<String> ALLATORIxDEMO = Predicates.alwaysTrue();

    public ee(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        ee a4;
        a4.oa = Minecraft.func_71410_x().field_71466_p;
        a4.ua = a4.createMoLangParser("length", 32);
        a4.ka = a4.createMoLangParserString("text", "").c();
        a4.m = a4.createMoLangParser("focused", false).c();
        a4.g = a4.createMoLangParser("drawBackground", true);
        a4.k = a4.createMoLangParserString("color", "224,224,224");
        if (!a4.ka.isEmpty() && a4.m) {
            Minecraft.func_71410_x().func_152344_a(() -> {
                ee a2;
                a2.setCursorPosition(999);
            });
        }
    }

    public void updateCursorCounter() {
        ee a2;
        ++a2.s;
    }

    public void setText(String a2) {
        ee a3;
        if (a3.ALLATORIxDEMO.apply((Object)a2)) {
            a3.ka = a2.length() > a3.getMaxStringLength() ? a2.substring(0, a3.getMaxStringLength()) : a2;
            a3.setCursorPositionEnd();
        }
    }

    public String getText() {
        ee a2;
        return a2.ka;
    }

    public String getSelectedText() {
        ee a2;
        int a3 = Math.min(a2.q, a2.b);
        int a4 = Math.max(a2.q, a2.b);
        return a2.ka.substring(a3, a4);
    }

    public void setValidator(Predicate<String> a2) {
        a.ALLATORIxDEMO = a2;
    }

    public void writeText(String a2) {
        int a3;
        ee a4;
        String a5 = "";
        String a6 = ChatAllowedCharacters.func_71565_a((String)a2);
        int a7 = Math.min(a4.q, a4.b);
        int a8 = Math.max(a4.q, a4.b);
        int a9 = a4.getMaxStringLength() - a4.ka.length() - (a7 - a8);
        if (!a4.ka.isEmpty()) {
            a5 = a5 + a4.ka.substring(0, a7);
        }
        if (a9 < a6.length()) {
            a5 = a5 + a6.substring(0, a9);
            a3 = a9;
        } else {
            a5 = a5 + a6;
            a3 = a6.length();
        }
        if (!a4.ka.isEmpty() && a8 < a4.ka.length()) {
            a5 = a5 + a4.ka.substring(a8);
        }
        if (a4.ALLATORIxDEMO.apply((Object)a5)) {
            a4.ka = a5;
            a4.moveCursorBy(a7 - a4.b + a3);
        }
        a4.runAction("textChange");
    }

    public void deleteWords(int a2) {
        ee a3;
        if (!a3.ka.isEmpty()) {
            if (a3.b != a3.q) {
                a3.writeText("");
            } else {
                a3.deleteFromCursor(a3.getNthWordFromCursor(a2) - a3.q);
            }
        }
    }

    public void deleteFromCursor(int a2) {
        ee a3;
        if (!a3.ka.isEmpty()) {
            if (a3.b != a3.q) {
                a3.writeText("");
            } else {
                boolean a4 = a2 < 0;
                int a5 = a4 ? a3.q + a2 : a3.q;
                int a6 = a4 ? a3.q : a3.q + a2;
                String a7 = "";
                if (a5 >= 0) {
                    a7 = a3.ka.substring(0, a5);
                }
                if (a6 < a3.ka.length()) {
                    a7 = a7 + a3.ka.substring(a6);
                }
                if (a3.ALLATORIxDEMO.apply((Object)a7)) {
                    a3.ka = a7;
                    if (a4) {
                        a3.moveCursorBy(a2);
                    }
                }
                a3.runAction("textChange");
            }
        }
    }

    public int getNthWordFromCursor(int a2) {
        ee a3;
        return a3.getNthWordFromPos(a2, a3.getCursorPosition());
    }

    public int getNthWordFromPos(int a2, int a3) {
        ee a4;
        return a4.getNthWordFromPosWS(a2, a3, true);
    }

    public int getNthWordFromPosWS(int a2, int a3, boolean a4) {
        int a5 = a3;
        boolean a6 = a2 < 0;
        int a7 = Math.abs(a2);
        for (int a8 = 0; a8 < a7; ++a8) {
            ee a9;
            if (!a6) {
                int a10 = a9.ka.length();
                if ((a5 = a9.ka.indexOf(32, a5)) == -1) {
                    a5 = a10;
                    continue;
                }
                while (a4 && a5 < a10 && a9.ka.charAt(a5) == ' ') {
                    ++a5;
                }
                continue;
            }
            while (a4 && a5 > 0 && a9.ka.charAt(a5 - 1) == ' ') {
                --a5;
            }
            while (a5 > 0 && a9.ka.charAt(a5 - 1) != ' ') {
                --a5;
            }
        }
        return a5;
    }

    public void moveCursorBy(int a2) {
        ee a3;
        a3.setCursorPosition(a3.b + a2);
    }

    public void setCursorPosition(int a2) {
        ee a3;
        a3.q = a2;
        int a4 = a3.ka.length();
        a3.q = MathHelper.func_76125_a((int)a3.q, (int)0, (int)a4);
        a3.setSelectionPos(a3.q);
    }

    public void setCursorPositionZero() {
        ee a2;
        a2.setCursorPosition(0);
    }

    public void setCursorPositionEnd() {
        ee a2;
        a2.setCursorPosition(a2.ka.length());
    }

    @Override
    public boolean keyTyped(char a2, int a3) throws IOException {
        ee a4;
        a4.textboxKeyTyped(a2, a3);
        return a4.m;
    }

    public boolean textboxKeyTyped(char a2, int a3) {
        ee a4;
        if (!a4.m) {
            return false;
        }
        if (GuiScreen.func_175278_g((int)a3)) {
            a4.setCursorPositionEnd();
            a4.setSelectionPos(0);
            return true;
        }
        if (GuiScreen.func_175280_f((int)a3)) {
            GuiScreen.func_146275_d((String)a4.getSelectedText());
            return true;
        }
        if (GuiScreen.func_175279_e((int)a3)) {
            if (a4.xa.c()) {
                a4.writeText(GuiScreen.func_146277_j());
            }
            return true;
        }
        if (GuiScreen.func_175277_d((int)a3)) {
            GuiScreen.func_146275_d((String)a4.getSelectedText());
            if (a4.xa.c()) {
                a4.writeText("");
            }
            return true;
        }
        switch (a3) {
            case 14: {
                if (GuiScreen.func_146271_m()) {
                    if (a4.xa.c()) {
                        a4.deleteWords(-1);
                    }
                } else if (a4.xa.c()) {
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
                    a4.setSelectionPos(a4.ka.length());
                } else {
                    a4.setCursorPositionEnd();
                }
                return true;
            }
            case 211: {
                if (GuiScreen.func_146271_m()) {
                    if (a4.xa.c()) {
                        a4.deleteWords(1);
                    }
                } else if (a4.xa.c()) {
                    a4.deleteFromCursor(1);
                }
                return true;
            }
        }
        if (ChatAllowedCharacters.func_71566_a((char)a2)) {
            if (a4.xa.c()) {
                a4.writeText(Character.toString(a2));
            }
            return true;
        }
        return false;
    }

    @Override
    public void runClick(int a2, int a3, int a4) {
        ee a5;
        a5.mouseClicked(a2, a3, a4, true);
        super.runClick(a2, a3, a4);
    }

    @Override
    public void runUnClick(int a2, int a3, int a4) {
        ee a5;
        a5.mouseClicked(a2, a3, a4, false);
        super.runUnClick(a2, a3, a4);
    }

    public boolean mouseClicked(int a2, int a3, int a4, boolean a5) {
        ee a6;
        a6.setFocused(a5);
        if (a6.m && a5 && a4 == 0) {
            int a7 = (int)((double)a2 - (a6.getXPos() + a6.s.ALLATORIxDEMO()));
            a7 = (int)((double)a7 / a6.ba.ALLATORIxDEMO());
            if (a6.getEnableBackgroundDrawing()) {
                a7 -= 4;
            }
            String a8 = a6.oa.func_78269_a(a6.ka.substring(a6.c), a6.p.ALLATORIxDEMO());
            a6.setCursorPosition(a6.oa.func_78269_a(a8, a7).length() + a6.c);
            return true;
        }
        return false;
    }

    @Override
    public void render(int a2, int a3) {
        ee a4;
        a4.drawTextBox();
    }

    public void drawTextBox() {
        ee a2;
        int a3 = 0;
        int a4 = 0;
        int a5 = a2.p.ALLATORIxDEMO();
        int a6 = a2.u.ALLATORIxDEMO();
        boolean a7 = a2.getEnableBackgroundDrawing();
        if (a7) {
            sd.ALLATORIxDEMO((double)(a3 - 1), (double)(a4 - 1), (double)(a5 + 1), (double)(a6 + 1), -6250336);
            sd.ALLATORIxDEMO((double)a3, (double)a4, (double)a5, (double)a6, -16777216);
        }
        String a8 = a2.k.ALLATORIxDEMO();
        Color a9 = sd.ALLATORIxDEMO(a8);
        int a10 = a2.xa.ALLATORIxDEMO() ? sd.ALLATORIxDEMO(a9) : a2.y;
        int a11 = a2.q - a2.c;
        int a12 = a2.b - a2.c;
        String a13 = a2.oa.func_78269_a(a2.ka.substring(a2.c), a5);
        boolean a14 = a11 >= 0 && a11 <= a13.length();
        boolean a15 = a2.m && a2.s / 6 % 2 == 0 && a14;
        int a16 = a7 ? a3 + 4 : a3;
        int a17 = a7 ? a4 + (a6 - 8) / 2 : a4;
        int a18 = a16;
        if (a12 > a13.length()) {
            a12 = a13.length();
        }
        if (!a13.isEmpty()) {
            String a19 = a14 ? a13.substring(0, a11) : a13;
            a18 = a2.oa.func_78276_b(a19, a16, a17, a10);
        }
        boolean a20 = a2.q < a2.ka.length() || a2.ka.length() >= a2.getMaxStringLength();
        int a21 = a18;
        if (!a14) {
            a21 = a11 > 0 ? a16 + a5 : a16;
        } else if (a20) {
            a21 = a18 - 1;
        }
        if (!a13.isEmpty() && a14 && a11 < a13.length()) {
            a18 = a2.oa.func_78276_b(a13.substring(a11), a18, a17, a10);
        }
        if (a15) {
            if (a20) {
                sd.ALLATORIxDEMO((double)a21, (double)(a17 - 1), 1.0, (double)(1 + a2.oa.field_78288_b), -3092272);
            } else {
                a2.oa.func_175063_a("_", (float)a21, (float)a17, a10);
            }
        }
        if (a12 != a11) {
            int a22 = a16 + a2.oa.func_78256_a(a13.substring(0, a12));
            a2.ALLATORIxDEMO(a21, a17 - 1, a22 - 1, a17 + 1 + a2.oa.field_78288_b);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, int a3, int a4, int a5) {
        ee a6;
        int a7;
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
        if (a4 > (a7 = a6.p.ALLATORIxDEMO())) {
            a4 = a7;
        }
        if (a2 > a7) {
            a2 = a7;
        }
        Tessellator a8 = Tessellator.func_178181_a();
        BufferBuilder a9 = a8.func_178180_c();
        GlStateManager.func_179131_c((float)0.0f, (float)0.0f, (float)255.0f, (float)255.0f);
        GlStateManager.func_179090_x();
        GlStateManager.func_179115_u();
        GlStateManager.func_187422_a((GlStateManager.LogicOp)GlStateManager.LogicOp.OR_REVERSE);
        a9.func_181668_a(7, DefaultVertexFormats.field_181705_e);
        a9.func_181662_b((double)a2, (double)a5, 0.0).func_181675_d();
        a9.func_181662_b((double)a4, (double)a5, 0.0).func_181675_d();
        a9.func_181662_b((double)a4, (double)a3, 0.0).func_181675_d();
        a9.func_181662_b((double)a2, (double)a3, 0.0).func_181675_d();
        a8.func_78381_a();
        GlStateManager.func_179134_v();
        GlStateManager.func_179098_w();
    }

    public int getMaxStringLength() {
        ee a2;
        return a2.ua.c();
    }

    public int getCursorPosition() {
        ee a2;
        return a2.q;
    }

    public boolean getEnableBackgroundDrawing() {
        ee a2;
        return a2.g.c();
    }

    public void setTextColor(int a2) {
        a.o = a2;
    }

    public void setDisabledTextColour(int a2) {
        a.y = a2;
    }

    public void setFocused(boolean a2) {
        ee a3;
        if (a2 && !a3.m) {
            a3.s = 0;
        }
        a3.m = a2;
        if (Minecraft.func_71410_x().field_71462_r != null) {
            Minecraft.func_71410_x().field_71462_r.func_193975_a(a2);
        }
    }

    public boolean isFocused() {
        ee a2;
        return a2.m;
    }

    public int getSelectionEnd() {
        ee a2;
        return a2.b;
    }

    public void setSelectionPos(int a2) {
        ee a3;
        int a4 = a3.ka.length();
        if (a2 > a4) {
            a2 = a4;
        }
        if (a2 < 0) {
            a2 = 0;
        }
        a3.b = a2;
        if (a3.oa != null) {
            if (a3.c > a4) {
                a3.c = a4;
            }
            int a5 = a3.p.ALLATORIxDEMO();
            String a6 = a3.oa.func_78269_a(a3.ka.substring(a3.c), a5);
            int a7 = a6.length() + a3.c;
            if (a2 == a3.c) {
                a3.c -= a3.oa.func_78262_a(a3.ka, a5, true).length();
            }
            if (a2 > a7) {
                a3.c += a2 - a7;
            } else if (a2 <= a3.c) {
                a3.c -= a3.c - a2;
            }
            a3.c = MathHelper.func_76125_a((int)a3.c, (int)0, (int)a4);
        }
    }

    @Override
    public Object getValue(String a2) {
        ee a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "type_": 
            case "type": {
                return "textbox";
            }
            case "length": {
                return a3.ua.c();
            }
            case "drawbackground": {
                return a3.g.c() ? "1" : "0";
            }
            case "text_": 
            case "text": {
                return a3.ka;
            }
            case "focused_": 
            case "focused": {
                return a3.m ? "1" : "0";
            }
            case "color": {
                return a3.k.c();
            }
            case "color_": {
                return a3.k.f();
            }
            case "length_": {
                return a3.ua.f();
            }
            case "drawbackground_": {
                return a3.g.f();
            }
            case "cursorposition": {
                return String.valueOf(a3.q);
            }
        }
        return super.getValue(a2);
    }

    @Override
    public void setValue(String a2, Object a3) {
        ee a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "length": {
                a4.ua.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "drawbackground": {
                a4.g.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "text_write": {
                sj.ALLATORIxDEMO(() -> {
                    ee a3;
                    a3.writeText(a3.toMolangParser((String)a3).c());
                });
                return;
            }
            case "text": {
                sj.ALLATORIxDEMO(() -> {
                    ee a3;
                    a3.setText(a3.toMolangParser((String)a3).c());
                });
                return;
            }
            case "text_write_string": {
                sj.ALLATORIxDEMO(() -> {
                    ee a3;
                    a3.writeText((String)a3);
                });
                return;
            }
            case "text_string": {
                sj.ALLATORIxDEMO(() -> {
                    ee a3;
                    a3.setText((String)a3);
                });
                return;
            }
            case "focused": {
                a4.setFocused(a4.toMolangParser(a3).c());
                return;
            }
            case "color": {
                a4.k.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
        }
        super.setValue(a2, a3);
    }
}

