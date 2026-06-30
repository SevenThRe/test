/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.GuiButton
 *  org.jetbrains.annotations.NotNull
 *  org.jetbrains.annotations.Nullable
 */
package invtweaks;

import invtweaks.InvTweaksObfuscation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class InvTweaksGuiTooltipButton
extends GuiButton {
    public static final int LINE_HEIGHT = 11;
    private int hoverTime = 0;
    private long prevSystemTime = 0L;
    @Nullable
    private String tooltip = null;
    @Nullable
    private String[] tooltipLines = null;
    private int tooltipWidth = -1;
    private boolean drawBackground = true;

    public InvTweaksGuiTooltipButton(int id_, int x, int y, @NotNull String displayString_, String tooltip_) {
        this(id_, x, y, 150, 20, displayString_, tooltip_);
    }

    public InvTweaksGuiTooltipButton(int id_, int x, int y, int w, int h, @NotNull String displayString_, @Nullable String tooltip_) {
        super(id_, x, y, w, h, displayString_);
        if (tooltip_ != null) {
            this.setTooltip(tooltip_);
        }
    }

    public InvTweaksGuiTooltipButton(int id_, int x, int y, int w, int h, @NotNull String displayString_, @Nullable String tooltip_, boolean drawBackground_) {
        super(id_, x, y, w, h, displayString_);
        if (tooltip_ != null) {
            this.setTooltip(tooltip_);
        }
        this.drawBackground = drawBackground_;
    }

    public void func_191745_a(@NotNull Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (this.drawBackground) {
            super.func_191745_a(mc, mouseX, mouseY, partialTicks);
        } else {
            this.func_73731_b(mc.field_71466_p, this.field_146126_j, this.field_146128_h, this.field_146129_i + (this.field_146121_g - 8) / 2, 0x999999);
        }
        InvTweaksObfuscation obf = new InvTweaksObfuscation(mc);
        if (this.tooltipLines != null) {
            if (this.isMouseOverButton(mouseX, mouseY)) {
                long systemTime = System.currentTimeMillis();
                if (this.prevSystemTime != 0L) {
                    this.hoverTime = (int)((long)this.hoverTime + (systemTime - this.prevSystemTime));
                }
                this.prevSystemTime = systemTime;
            } else {
                this.hoverTime = 0;
                this.prevSystemTime = 0L;
            }
            if (this.hoverTime > 800 && this.tooltipLines != null) {
                FontRenderer fontRenderer = obf.getFontRenderer();
                int x = mouseX + 12;
                int y = mouseY - 11 * this.tooltipLines.length;
                if (this.tooltipWidth == -1) {
                    for (String line : this.tooltipLines) {
                        this.tooltipWidth = Math.max(fontRenderer.func_78256_a(line), this.tooltipWidth);
                    }
                }
                if (x + this.tooltipWidth > obf.getCurrentScreen().field_146294_l) {
                    x = obf.getCurrentScreen().field_146294_l - this.tooltipWidth;
                }
                this.func_73733_a(x - 3, y - 3, x + this.tooltipWidth + 3, y + 11 * this.tooltipLines.length, -1073741824, -1073741824);
                int lineCount = 0;
                for (String line : this.tooltipLines) {
                    int j1 = y + lineCount++ * 11;
                    int k = -1;
                    fontRenderer.func_175063_a(line, (float)x, (float)j1, k);
                }
            }
        }
    }

    protected boolean isMouseOverButton(int i, int j) {
        return i >= this.field_146128_h && j >= this.field_146129_i && i < this.field_146128_h + this.field_146120_f && j < this.field_146129_i + this.field_146121_g;
    }

    protected int getTextColor(int i, int j) {
        int textColor = -2039584;
        if (!this.field_146124_l) {
            textColor = -6250336;
        } else if (this.isMouseOverButton(i, j)) {
            textColor = -96;
        }
        return textColor;
    }

    @Nullable
    public String getTooltip() {
        return this.tooltip;
    }

    public void setTooltip(@NotNull String tooltip_) {
        this.tooltip = tooltip_ = tooltip_.replace("\\n", "\n");
        this.tooltipLines = this.tooltip.split("\n");
    }
}

