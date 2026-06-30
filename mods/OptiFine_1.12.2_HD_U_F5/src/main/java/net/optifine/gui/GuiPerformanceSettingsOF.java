/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bid
 *  bid$a
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

public class GuiPerformanceSettingsOF
extends blk {
    private blk prevScreen;
    protected String title;
    private bid settings;
    private static bid.a[] enumOptions = new bid.a[]{bid.a.SMOOTH_FPS, bid.a.SMOOTH_WORLD, bid.a.FAST_RENDER, bid.a.FAST_MATH, bid.a.CHUNK_UPDATES, bid.a.CHUNK_UPDATES_DYNAMIC, bid.a.RENDER_REGIONS, bid.a.LAZY_CHUNK_LOADING, bid.a.SMART_ANIMATIONS};
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderOptions());

    public GuiPerformanceSettingsOF(blk guiscreen, bid gamesettings) {
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    public void b() {
        this.title = cey.a((String)"of.options.performanceTitle", (Object[])new Object[0]);
        this.n.clear();
        for (int i = 0; i < enumOptions.length; ++i) {
            bid.a enumoptions = enumOptions[i];
            int x = this.l / 2 - 155 + i % 2 * 160;
            int y = this.m / 6 + 21 * (i / 2) - 12;
            if (!enumoptions.a()) {
                this.n.add(new GuiOptionButtonOF(enumoptions.c(), x, y, enumoptions, this.settings.c(enumoptions)));
                continue;
            }
            this.n.add(new GuiOptionSliderOF(enumoptions.c(), x, y, enumoptions));
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
    }

    public void a(int x, int y, float f) {
        this.c();
        this.a(this.q, this.title, this.l / 2, 15, 0xFFFFFF);
        super.a(x, y, f);
        this.tooltipManager.drawTooltips(x, y, this.n);
    }
}

