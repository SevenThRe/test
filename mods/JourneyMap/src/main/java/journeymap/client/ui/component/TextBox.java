/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiTextField
 */
package journeymap.client.ui.component;

import java.awt.Color;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiTextField;

public class TextBox
extends GuiTextField {
    protected final String numericRegex;
    protected final boolean numeric;
    protected final boolean allowNegative;
    protected int minLength;
    protected Integer clampMin;
    protected Integer clampMax;

    public TextBox(Object text, FontRenderer fontRenderer, int width, int height) {
        this(text, fontRenderer, width, height, false, false);
    }

    public TextBox(Object text, FontRenderer fontRenderer, int width, int height, boolean isNumeric, boolean negative) {
        super(0, fontRenderer, 0, 0, width, height);
        this.func_146180_a(text.toString());
        this.numeric = isNumeric;
        this.allowNegative = negative;
        String regex = null;
        if (this.numeric) {
            regex = this.allowNegative ? "[^-?\\d]" : "[^\\d]";
        }
        this.numericRegex = regex;
    }

    public void setClamp(Integer min, Integer max) {
        this.clampMin = min;
        this.clampMax = max;
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void func_146191_b(String par1Str) {
        super.func_146191_b(par1Str);
        if (this.numeric) {
            String fixed = this.func_146179_b().replaceAll(this.numericRegex, "");
            if (this.allowNegative) {
                String start = fixed.startsWith("-") ? "-" : "";
                fixed = start + fixed.replaceAll("-", "");
            }
            super.func_146180_a(fixed);
        }
    }

    public void setText(Object object) {
        super.func_146180_a(object.toString());
    }

    public boolean isNumeric() {
        return this.numeric;
    }

    public boolean hasMinLength() {
        String text = this.func_146179_b();
        int textLen = text == null ? 0 : text.length();
        return this.minLength <= textLen;
    }

    public boolean func_146201_a(char par1, int par2) {
        boolean res = super.func_146201_a(par1, par2);
        if (this.numeric && this.func_146206_l()) {
            this.clamp();
        }
        return res;
    }

    public void func_146194_f() {
        super.func_146194_f();
        if (this.func_146176_q() && !this.hasMinLength()) {
            int red = Color.red.getRGB();
            int x1 = this.getX() - 1;
            int y1 = this.getY() - 1;
            int x2 = x1 + this.func_146200_o() + 1;
            int y2 = y1 + this.getHeight() + 1;
            TextBox.func_73734_a((int)x1, (int)y1, (int)x2, (int)(y1 + 1), (int)red);
            TextBox.func_73734_a((int)x1, (int)y2, (int)x2, (int)(y2 + 1), (int)red);
            TextBox.func_73734_a((int)x1, (int)y1, (int)(x1 + 1), (int)y2, (int)red);
            TextBox.func_73734_a((int)x2, (int)y1, (int)(x2 + 1), (int)y2, (int)red);
        }
    }

    public Integer clamp() {
        if (!this.numeric) {
            return null;
        }
        String text = this.func_146179_b();
        if (this.clampMin != null) {
            if (text == null || text.length() == 0 || text.equals("-")) {
                return null;
            }
            try {
                this.setText(Math.max(this.clampMin, Integer.parseInt(text)));
            }
            catch (Exception e) {
                this.setText(this.clampMin);
            }
            if (this.clampMax != null) {
                try {
                    this.setText(Math.min(this.clampMax, Integer.parseInt(text)));
                }
                catch (Exception e) {
                    this.setText(this.clampMax);
                }
            }
        }
        try {
            return Integer.parseInt(text);
        }
        catch (Exception e) {
            return null;
        }
    }

    public int getX() {
        return this.field_146209_f;
    }

    public void setX(int x) {
        this.field_146209_f = x;
    }

    public int getY() {
        return this.field_146210_g;
    }

    public void setY(int y) {
        this.field_146210_g = y;
    }

    public int func_146200_o() {
        return this.field_146218_h;
    }

    public void setWidth(int w) {
        this.field_146218_h = w;
    }

    public int getHeight() {
        return this.field_146219_i;
    }

    public void setHeight(int h) {
        this.field_146219_i = h;
    }

    public int getCenterX() {
        return this.getX() + this.func_146200_o() / 2;
    }

    public int getMiddleY() {
        return this.getY() + this.getHeight() / 2;
    }

    public int getBottomY() {
        return this.getY() + this.getHeight();
    }

    public int getRightX() {
        return this.getX() + this.func_146200_o();
    }
}

