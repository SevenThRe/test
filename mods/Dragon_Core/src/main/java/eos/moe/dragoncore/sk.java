/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fi;
import eos.moe.dragoncore.ph;
import eos.moe.dragoncore.qm;
import eos.moe.dragoncore.su;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class sk
extends Gui {
    public static final ResourceLocation v = new ResourceLocation("nbtedit", "textures/gui/widgets.png");
    private Minecraft m = Minecraft.func_71410_x();
    private ph<fi> c;
    private qm q;
    public int b;
    public int o;
    public int y;
    public int k;
    private String ALLATORIxDEMO;

    public sk(qm a2, ph<fi> a3, int a4, int a5) {
        sk a6;
        a6.q = a2;
        a6.c = a3;
        a6.y = a4;
        a6.k = a5;
        a6.o = a6.m.field_71466_p.field_78288_b;
        a6.updateDisplay();
    }

    private /* synthetic */ boolean c(int a2, int a3) {
        sk a4;
        return a2 >= a4.y && a3 >= a4.k && a2 < a4.b + a4.y && a3 < a4.o + a4.k;
    }

    private /* synthetic */ boolean ALLATORIxDEMO(int a2, int a3) {
        sk a4;
        return a2 >= a4.y - 9 && a3 >= a4.k && a2 < a4.y && a3 < a4.k + a4.o;
    }

    public boolean shouldDrawChildren() {
        sk a2;
        return a2.c.f();
    }

    public boolean clicked(int a2, int a3) {
        sk a4;
        return a4.c(a2, a3);
    }

    public boolean hideShowClicked(int a2, int a3) {
        sk a4;
        if (a4.c.c() && a4.ALLATORIxDEMO(a2, a3)) {
            a4.c.ALLATORIxDEMO(!a4.c.f());
            return true;
        }
        return false;
    }

    public ph<fi> getNode() {
        sk a2;
        return a2.c;
    }

    public void shift(int a2) {
        a.k += a2;
    }

    public void updateDisplay() {
        sk a2;
        a2.ALLATORIxDEMO = su.ALLATORIxDEMO(a2.c.ALLATORIxDEMO());
        a2.b = a2.m.field_71466_p.func_78256_a(a2.ALLATORIxDEMO) + 12;
    }

    public void draw(int a2, int a3) {
        sk a4;
        boolean a5 = a4.q.getFocused() == a4.c;
        boolean a6 = a4.c(a2, a3);
        boolean a7 = a4.ALLATORIxDEMO(a2, a3);
        int a8 = a5 ? 255 : (a6 ? 0xFFFFA0 : (a4.c.ALLATORIxDEMO() ? 0xE0E0E0 : -6250336));
        a4.m.field_71446_o.func_110577_a(v);
        if (a5) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            Gui.func_73734_a((int)(a4.y + 11), (int)a4.k, (int)(a4.y + a4.b), (int)(a4.k + a4.o), (int)Integer.MIN_VALUE);
        }
        if (a4.c.c()) {
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            a4.func_73729_b(a4.y - 9, a4.k, a4.c.f() ? 9 : 0, a7 ? a4.o : 0, 9, a4.o);
        }
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a4.func_73729_b(a4.y + 1, a4.k, (a4.c.ALLATORIxDEMO().ALLATORIxDEMO().func_74732_a() - 1) * 9, 18, 9, 9);
        a4.func_73731_b(a4.m.field_71466_p, a4.ALLATORIxDEMO, a4.y + 11, a4.k + (a4.o - 8) / 2, a8);
    }

    public boolean shouldDraw(int a2, int a3) {
        sk a4;
        return a4.k + a4.o >= a2 && a4.k <= a3;
    }
}

