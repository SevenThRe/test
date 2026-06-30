/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.sk;
import eos.moe.dragoncore.su;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import org.lwjgl.opengl.GL11;

public class bl
extends Gui {
    public static final int m = 9;
    public static final int c = 9;
    private Minecraft q = Minecraft.func_71410_x();
    private byte b;
    private int o;
    private int y;
    private boolean k;
    private long ALLATORIxDEMO;

    public bl(byte a2, int a3, int a4) {
        bl a5;
        a5.b = a2;
        a5.o = a3;
        a5.y = a4;
    }

    public void draw(int a2, int a3) {
        bl a4;
        a4.q.field_71446_o.func_110577_a(sk.v);
        if (a4.inBounds(a2, a3)) {
            Gui.func_73734_a((int)a4.o, (int)a4.y, (int)(a4.o + 9), (int)(a4.y + 9), (int)-2130706433);
            if (a4.ALLATORIxDEMO == -1L) {
                a4.ALLATORIxDEMO = System.currentTimeMillis();
            }
        } else {
            a4.ALLATORIxDEMO = -1L;
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        if (a4.k) {
            a4.func_73729_b(a4.o, a4.y, (a4.b - 1) * 9, 18, 9, 9);
        }
        if (a4.ALLATORIxDEMO != -1L && System.currentTimeMillis() - a4.ALLATORIxDEMO > 300L) {
            a4.ALLATORIxDEMO(a2, a3);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, int a3) {
        bl a4;
        String a5 = su.ALLATORIxDEMO(a4.b);
        int a6 = a4.q.field_71466_p.func_78256_a(a5);
        bl.func_73734_a((int)(a2 + 8), (int)(a3 + 10), (int)(a2 + 9 + a6), (int)(a3 + 20), (int)-16777216);
        a4.q.field_71466_p.func_78276_b(a5, a2 + 9, a3 + 11, 0xFFFFFF);
    }

    public void setEnabled(boolean a2) {
        a.k = a2;
    }

    public boolean isEnabled() {
        bl a2;
        return a2.k;
    }

    public boolean inBounds(int a2, int a3) {
        bl a4;
        return a4.k && a2 >= a4.o && a3 >= a4.y && a2 < a4.o + 9 && a3 < a4.y + 9;
    }

    public byte getId() {
        bl a2;
        return a2.b;
    }
}

