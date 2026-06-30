/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bja
 *  bjn
 *  blk
 *  cey
 *  com.google.common.collect.Lists
 */
package net.optifine.gui;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;

public class GuiMessage
extends blk {
    private blk parentScreen;
    private String messageLine1;
    private String messageLine2;
    private final List listLines2 = Lists.newArrayList();
    protected String confirmButtonText;
    private int ticksUntilEnable;

    public GuiMessage(blk parentScreen, String line1, String line2) {
        this.parentScreen = parentScreen;
        this.messageLine1 = line1;
        this.messageLine2 = line2;
        this.confirmButtonText = cey.a((String)"gui.done", (Object[])new Object[0]);
    }

    public void b() {
        this.n.add(new bjn(0, this.l / 2 - 74, this.m / 6 + 96, this.confirmButtonText));
        this.listLines2.clear();
        this.listLines2.addAll(this.q.c(this.messageLine2, this.l - 50));
    }

    protected void a(bja button) throws IOException {
        Config.getMinecraft().a(this.parentScreen);
    }

    public void a(int mouseX, int mouseY, float partialTicks) {
        this.c();
        this.a(this.q, this.messageLine1, this.l / 2, 70, 0xFFFFFF);
        int var4 = 90;
        for (String var6 : this.listLines2) {
            this.a(this.q, var6, this.l / 2, var4, 0xFFFFFF);
            var4 += this.q.a;
        }
        super.a(mouseX, mouseY, partialTicks);
    }

    public void setButtonDelay(int ticksUntilEnable) {
        this.ticksUntilEnable = ticksUntilEnable;
        for (bja var3 : this.n) {
            var3.l = false;
        }
    }

    public void e() {
        super.e();
        if (--this.ticksUntilEnable == 0) {
            for (bja var2 : this.n) {
                var2.l = true;
            }
        }
    }
}

