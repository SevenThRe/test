/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bid
 *  bid$a
 *  bit
 *  bja
 *  bjn
 *  blk
 *  cey
 */
package net.optifine.gui;

import net.optifine.gui.GuiOptionButtonOF;
import net.optifine.gui.GuiOptionSliderOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderOptions;

public class GuiQualitySettingsOF
extends blk {
    private blk prevScreen;
    protected String title;
    private bid settings;
    private static bid.a[] enumOptions = new bid.a[]{bid.a.D, bid.a.MIPMAP_TYPE, bid.a.AF_LEVEL, bid.a.AA_LEVEL, bid.a.CLEAR_WATER, bid.a.RANDOM_ENTITIES, bid.a.BETTER_GRASS, bid.a.BETTER_SNOW, bid.a.CUSTOM_FONTS, bid.a.CUSTOM_COLORS, bid.a.CONNECTED_TEXTURES, bid.a.NATURAL_TEXTURES, bid.a.CUSTOM_SKY, bid.a.CUSTOM_ITEMS, bid.a.CUSTOM_ENTITY_MODELS, bid.a.CUSTOM_GUIS, bid.a.EMISSIVE_TEXTURES};
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

    public GuiQualitySettingsOF(blk guiscreen, bid gamesettings) {
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    public void b() {
        this.title = cey.a((String)"of.options.qualityTitle", (Object[])new Object[0]);
        this.n.clear();
        for (int i = 0; i < enumOptions.length; ++i) {
            bid.a opt = enumOptions[i];
            int x = this.l / 2 - 155 + i % 2 * 160;
            int y = this.m / 6 + 21 * (i / 2) - 12;
            if (!opt.a()) {
                this.n.add(new GuiOptionButtonOF(opt.c(), x, y, opt, this.settings.c(opt)));
                continue;
            }
            this.n.add(new GuiOptionSliderOF(opt.c(), x, y, opt));
        }
        this.n.add(new bja(200, this.l / 2 - 100, this.m / 6 + 168 + 11, cey.a((String)"gui.done", (Object[])new Object[0])));
    }

    protected void a(bja guibutton) {
        if (!guibutton.l) {
            return;
        }
        if (guibutton.k < 200 && guibutton instanceof bjn) {
            this.settings.a(((bjn)guibutton).c(), 1);
            guibutton.j = this.settings.c(bid.a.a((int)guibutton.k));
        }
        if (guibutton.k == 200) {
            this.j.t.b();
            this.j.a(this.prevScreen);
        }
        if (guibutton.k != bid.a.AA_LEVEL.ordinal()) {
            bit sr = new bit(this.j);
            this.a(this.j, sr.a(), sr.b());
        }
    }

    public void a(int x, int y, float f) {
        this.c();
        this.a(this.q, this.title, this.l / 2, 15, 0xFFFFFF);
        super.a(x, y, f);
        this.tooltipManager.drawTooltips(x, y, this.n);
    }
}

