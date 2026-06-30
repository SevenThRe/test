/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.aj;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

public class le
extends Gui {
    public static final ResourceLocation z = new ResourceLocation("textures/gui/widgets.png");
    private static final int s = 14;
    private static final int g = 20;
    private static final int t = 150;
    private static final int r = 82;
    private static final int x = 3;
    private final Minecraft v;
    public final aj m;
    private final int c;
    private int q;
    private int b;
    private int o;
    private String y;
    private boolean k;
    private int ALLATORIxDEMO;

    public le(aj a2, int a3, int a4) {
        le a5;
        a5.m = a2;
        a5.c = a3;
        a5.b = a4;
        a5.v = Minecraft.getMinecraft();
        a5.k = !a2.ALLATORIxDEMO.isEmpty();
        a5.y = (a2.ALLATORIxDEMO.isEmpty() ? "\u4fdd\u5b58 " : "\u52a0\u8f7d ") + a2.k;
        a5.ALLATORIxDEMO = -1;
        a5.ALLATORIxDEMO();
    }

    public void draw(int a2, int a3) {
        le a4;
        int a5 = a4.inBounds(a2, a3) ? 0xFFFFA0 : 0xFFFFFF;
        a4.ALLATORIxDEMO(a4.q, a4.b, 0, 66, a4.o, 20);
        a4.drawCenteredString(a4.v.fontRenderer, a4.y, a4.q + a4.o / 2, a4.b + 6, a5);
        if (a4.ALLATORIxDEMO != -1 && a4.ALLATORIxDEMO / 6 % 2 == 0) {
            a4.v.fontRenderer.drawStringWithShadow("_", (float)(a4.q + (a4.o + a4.v.fontRenderer.getStringWidth(a4.y)) / 2 + 1), (float)(a4.b + 6), 0xFFFFFF);
        }
        if (a4.k) {
            a5 = a4.inBoundsOfX(a2, a3) ? 0xFFFFA0 : 0xFFFFFF;
            a4.ALLATORIxDEMO(a4.c(), a4.ALLATORIxDEMO(), 0, 66, 14, 14);
            a4.drawCenteredString(a4.v.fontRenderer, "x", a4.q - 3 - 7, a4.b + 6, a5);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, int a3, int a4, int a5, int a6, int a7) {
        le a8;
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a8.v.renderEngine.bindTexture(z);
        a8.drawTexturedModalRect(a2, a3, a4, a5, a6 / 2, a7 / 2);
        a8.drawTexturedModalRect(a2 + a6 / 2, a3, a4 + 200 - a6 / 2, a5, a6 / 2, a7 / 2);
        a8.drawTexturedModalRect(a2, a3 + a7 / 2, a4, a5 + 20 - a7 / 2, a6 / 2, a7 / 2);
        a8.drawTexturedModalRect(a2 + a6 / 2, a3 + a7 / 2, a4 + 200 - a6 / 2, a5 + 20 - a7 / 2, a6 / 2, a7 / 2);
    }

    private /* synthetic */ int c() {
        le a2;
        return a2.q - 14 - 3;
    }

    private /* synthetic */ int ALLATORIxDEMO() {
        le a2;
        return a2.b + 3;
    }

    public boolean inBoundsOfX(int a2, int a3) {
        le a4;
        int a5 = a4.c();
        int a6 = a4.ALLATORIxDEMO();
        return a4.k && a2 >= a5 && a3 >= a6 && a2 < a5 + 14 && a3 < a6 + 14;
    }

    public boolean inBounds(int a2, int a3) {
        le a4;
        return a2 >= a4.q && a3 >= a4.b && a2 < a4.q + a4.o && a3 < a4.b + 20;
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        le a2;
        a2.o = a2.v.fontRenderer.getStringWidth(a2.y) + 24;
        if (a2.o % 2 == 1) {
            ++a2.o;
        }
        a2.o = MathHelper.clamp((int)a2.o, (int)82, (int)150);
        a2.q = a2.c - a2.o;
    }

    public void reset() {
        le a2;
        a2.k = false;
        a2.m.ALLATORIxDEMO = new NBTTagCompound();
        a2.y = "\u4fdd\u5b58 " + a2.m.k;
        a2.ALLATORIxDEMO();
    }

    public void saved() {
        le a2;
        a2.k = true;
        a2.y = "\u52a0\u8f7d " + a2.m.k;
        a2.ALLATORIxDEMO();
    }

    public void keyTyped(char a2, int a3) {
        le a4;
        if (a3 == 14) {
            a4.backSpace();
        }
        if (Character.isDigit(a2) || Character.isLetter(a2)) {
            a4.m.k = a4.m.k + a2;
            a4.y = (a4.m.ALLATORIxDEMO.isEmpty() ? "\u4fdd\u5b58 " : "\u52a0\u8f7d ") + a4.m.k;
            a4.ALLATORIxDEMO();
        }
    }

    public void backSpace() {
        le a2;
        if (a2.m.k.length() > 0) {
            a2.m.k = a2.m.k.substring(0, a2.m.k.length() - 1);
            a2.y = (a2.m.ALLATORIxDEMO.isEmpty() ? "\u4fdd\u5b58 " : "\u52a0\u8f7d ") + a2.m.k;
            a2.ALLATORIxDEMO();
        }
    }

    public void startEditing() {
        a.ALLATORIxDEMO = 0;
    }

    public void stopEditing() {
        a.ALLATORIxDEMO = -1;
    }

    public void update() {
        le a2;
        ++a2.ALLATORIxDEMO;
    }
}

