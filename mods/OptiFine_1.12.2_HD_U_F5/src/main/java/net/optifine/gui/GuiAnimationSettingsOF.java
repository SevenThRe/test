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

import net.optifine.Lang;
import net.optifine.gui.GuiOptionButtonOF;
import net.optifine.gui.GuiOptionSliderOF;

public class GuiAnimationSettingsOF
extends blk {
    private blk prevScreen;
    protected String title;
    private bid settings;
    private static bid.a[] enumOptions = new bid.a[]{bid.a.ANIMATED_WATER, bid.a.ANIMATED_LAVA, bid.a.ANIMATED_FIRE, bid.a.ANIMATED_PORTAL, bid.a.ANIMATED_REDSTONE, bid.a.ANIMATED_EXPLOSION, bid.a.ANIMATED_FLAME, bid.a.ANIMATED_SMOKE, bid.a.VOID_PARTICLES, bid.a.WATER_PARTICLES, bid.a.RAIN_SPLASH, bid.a.PORTAL_PARTICLES, bid.a.POTION_PARTICLES, bid.a.DRIPPING_WATER_LAVA, bid.a.ANIMATED_TERRAIN, bid.a.ANIMATED_TEXTURES, bid.a.FIREWORK_PARTICLES, bid.a.o};

    public GuiAnimationSettingsOF(blk guiscreen, bid gamesettings) {
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    public void b() {
        this.title = cey.a((String)"of.options.animationsTitle", (Object[])new Object[0]);
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
        this.n.add(new bja(210, this.l / 2 - 155, this.m / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOn")));
        this.n.add(new bja(211, this.l / 2 - 155 + 80, this.m / 6 + 168 + 11, 70, 20, Lang.get("of.options.animation.allOff")));
        this.n.add(new bjn(200, this.l / 2 + 5, this.m / 6 + 168 + 11, cey.a((String)"gui.done", (Object[])new Object[0])));
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
        if (guibutton.k == 210) {
            this.j.t.setAllAnimations(true);
        }
        if (guibutton.k == 211) {
            this.j.t.setAllAnimations(false);
        }
        bit sr = new bit(this.j);
        this.a(this.j, sr.a(), sr.b());
    }

    public void a(int x, int y, float f) {
        this.c();
        this.a(this.q, this.title, this.l / 2, 15, 0xFFFFFF);
        super.a(x, y, f);
    }
}

