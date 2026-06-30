/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bid
 *  bip
 *  bja
 *  blk
 *  cey
 *  rk
 */
package net.optifine.shaders.gui;

import net.optifine.Lang;
import net.optifine.gui.GuiScreenOF;
import net.optifine.gui.TooltipManager;
import net.optifine.gui.TooltipProviderShaderOptions;
import net.optifine.shaders.Shaders;
import net.optifine.shaders.config.ShaderOption;
import net.optifine.shaders.config.ShaderOptionProfile;
import net.optifine.shaders.config.ShaderOptionScreen;
import net.optifine.shaders.gui.GuiButtonShaderOption;
import net.optifine.shaders.gui.GuiSliderShaderOption;

public class GuiShaderOptions
extends GuiScreenOF {
    private blk prevScreen;
    protected String title = "Shader Options";
    private bid settings;
    private TooltipManager tooltipManager = new TooltipManager(this, new TooltipProviderShaderOptions());
    private String screenName = null;
    private String screenText = null;
    private boolean changed = false;
    public static final String OPTION_PROFILE = "<profile>";
    public static final String OPTION_EMPTY = "<empty>";
    public static final String OPTION_REST = "*";

    public GuiShaderOptions(blk guiscreen, bid gamesettings) {
        this.prevScreen = guiscreen;
        this.settings = gamesettings;
    }

    public GuiShaderOptions(blk guiscreen, bid gamesettings, String screenName) {
        this(guiscreen, gamesettings);
        this.screenName = screenName;
        if (screenName != null) {
            this.screenText = Shaders.translate("screen." + screenName, screenName);
        }
    }

    public void b() {
        this.title = cey.a((String)"of.options.shaderOptionsTitle", (Object[])new Object[0]);
        int baseId = 100;
        int baseX = 0;
        int baseY = 30;
        int stepY = 20;
        int btnWidth = 120;
        int btnHeight = 20;
        int columns = Shaders.getShaderPackColumns(this.screenName, 2);
        ShaderOption[] ops = Shaders.getShaderPackOptions(this.screenName);
        if (ops != null) {
            int colsMin = rk.f((double)((double)ops.length / 9.0));
            if (columns < colsMin) {
                columns = colsMin;
            }
            for (int i = 0; i < ops.length; ++i) {
                ShaderOption so = ops[i];
                if (so == null || !so.isVisible()) continue;
                int col = i % columns;
                int row = i / columns;
                int colWidth = Math.min(this.l / columns, 200);
                baseX = (this.l - colWidth * columns) / 2;
                int x = col * colWidth + 5 + baseX;
                int y = baseY + row * stepY;
                int w = colWidth - 10;
                int h2 = btnHeight;
                String text = GuiShaderOptions.getButtonText(so, w);
                GuiButtonShaderOption btn = Shaders.isShaderPackOptionSlider(so.getName()) ? new GuiSliderShaderOption(baseId + i, x, y, w, h2, so, text) : new GuiButtonShaderOption(baseId + i, x, y, w, h2, so, text);
                btn.l = so.isEnabled();
                this.n.add(btn);
            }
        }
        this.n.add(new bja(201, this.l / 2 - btnWidth - 20, this.m / 6 + 168 + 11, btnWidth, btnHeight, cey.a((String)"controls.reset", (Object[])new Object[0])));
        this.n.add(new bja(200, this.l / 2 + 20, this.m / 6 + 168 + 11, btnWidth, btnHeight, cey.a((String)"gui.done", (Object[])new Object[0])));
    }

    public static String getButtonText(ShaderOption so, int btnWidth) {
        String labelName = so.getNameText();
        if (so instanceof ShaderOptionScreen) {
            ShaderOptionScreen soScr = (ShaderOptionScreen)so;
            return labelName + "...";
        }
        bip fr = Config.getMinecraft().k;
        int lenSuffix = fr.a(": " + Lang.getOff()) + 5;
        while (fr.a(labelName) + lenSuffix >= btnWidth && labelName.length() > 0) {
            labelName = labelName.substring(0, labelName.length() - 1);
        }
        String col = so.isChanged() ? so.getValueColor(so.getValue()) : "";
        String labelValue = so.getValueText(so.getValue());
        return labelName + ": " + col + labelValue;
    }

    protected void a(bja guibutton) {
        if (!guibutton.l) {
            return;
        }
        if (guibutton.k < 200 && guibutton instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption btnSo = (GuiButtonShaderOption)guibutton;
            ShaderOption so = btnSo.getShaderOption();
            if (so instanceof ShaderOptionScreen) {
                String screenName = so.getName();
                GuiShaderOptions scr = new GuiShaderOptions(this, this.settings, screenName);
                this.j.a((blk)scr);
                return;
            }
            if (GuiShaderOptions.s()) {
                so.resetValue();
            } else if (btnSo.isSwitchable()) {
                so.nextValue();
            }
            this.updateAllButtons();
            this.changed = true;
        }
        if (guibutton.k == 201) {
            ShaderOption[] opts = Shaders.getChangedOptions(Shaders.getShaderPackOptions());
            for (int i = 0; i < opts.length; ++i) {
                ShaderOption opt = opts[i];
                opt.resetValue();
                this.changed = true;
            }
            this.updateAllButtons();
        }
        if (guibutton.k == 200) {
            if (this.changed) {
                Shaders.saveShaderPackOptions();
                this.changed = false;
                Shaders.uninit();
            }
            this.j.a(this.prevScreen);
        }
    }

    @Override
    protected void actionPerformedRightClick(bja btn) {
        if (btn instanceof GuiButtonShaderOption) {
            GuiButtonShaderOption btnSo = (GuiButtonShaderOption)btn;
            ShaderOption so = btnSo.getShaderOption();
            if (GuiShaderOptions.s()) {
                so.resetValue();
            } else if (btnSo.isSwitchable()) {
                so.prevValue();
            }
            this.updateAllButtons();
            this.changed = true;
        }
    }

    public void m() {
        super.m();
        if (this.changed) {
            Shaders.saveShaderPackOptions();
            this.changed = false;
            Shaders.uninit();
        }
    }

    private void updateAllButtons() {
        for (bja btn : this.n) {
            if (!(btn instanceof GuiButtonShaderOption)) continue;
            GuiButtonShaderOption gbso = (GuiButtonShaderOption)btn;
            ShaderOption opt = gbso.getShaderOption();
            if (opt instanceof ShaderOptionProfile) {
                ShaderOptionProfile optProf = (ShaderOptionProfile)opt;
                optProf.updateProfile();
            }
            gbso.j = GuiShaderOptions.getButtonText(opt, gbso.b());
            gbso.valueChanged();
        }
    }

    public void a(int x, int y, float f) {
        this.c();
        if (this.screenText != null) {
            this.a(this.q, this.screenText, this.l / 2, 15, 0xFFFFFF);
        } else {
            this.a(this.q, this.title, this.l / 2, 15, 0xFFFFFF);
        }
        super.a(x, y, f);
        this.tooltipManager.drawTooltips(x, y, this.n);
    }
}

