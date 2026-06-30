/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.renderer.GlStateManager
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.sk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;

public class up
extends Gui {
    public static final int c = 14;
    public static final int q = 14;
    private Minecraft b = Minecraft.func_71410_x();
    private byte o;
    private int y;
    private int k;
    private boolean ALLATORIxDEMO;

    public up(byte a2, int a3, int a4) {
        up a5;
        a5.o = a2;
        a5.y = a3;
        a5.k = a4;
    }

    public void draw(int a2, int a3) {
        up a4;
        a4.b.field_71446_o.func_110577_a(sk.v);
        if (a4.inBounds(a2, a3)) {
            Gui.func_73734_a((int)a4.y, (int)a4.k, (int)(a4.y + 14), (int)(a4.k + 14), (int)-2130706433);
        }
        if (a4.ALLATORIxDEMO) {
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        } else {
            GlStateManager.func_179131_c((float)0.5f, (float)0.5f, (float)0.5f, (float)1.0f);
        }
        a4.func_73729_b(a4.y, a4.k, a4.o * 14, 27, 14, 14);
    }

    public void setEnabled(boolean a2) {
        a.ALLATORIxDEMO = a2;
    }

    public boolean inBounds(int a2, int a3) {
        up a4;
        return a4.ALLATORIxDEMO && a2 >= a4.y && a3 >= a4.k && a2 < a4.y + 14 && a3 < a4.k + 14;
    }

    public byte getId() {
        up a2;
        return a2.o;
    }
}

