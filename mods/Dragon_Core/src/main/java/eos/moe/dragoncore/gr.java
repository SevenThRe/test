/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.nbt.NBTBase
 *  net.minecraft.nbt.NBTTagByte
 *  net.minecraft.nbt.NBTTagByteArray
 *  net.minecraft.nbt.NBTTagDouble
 *  net.minecraft.nbt.NBTTagFloat
 *  net.minecraft.nbt.NBTTagInt
 *  net.minecraft.nbt.NBTTagIntArray
 *  net.minecraft.nbt.NBTTagLong
 *  net.minecraft.nbt.NBTTagShort
 *  net.minecraft.nbt.NBTTagString
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ao;
import eos.moe.dragoncore.ci;
import eos.moe.dragoncore.fi;
import eos.moe.dragoncore.ph;
import eos.moe.dragoncore.qm;
import eos.moe.dragoncore.su;
import eos.moe.dragoncore.up;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagByte;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.nbt.NBTTagDouble;
import net.minecraft.nbt.NBTTagFloat;
import net.minecraft.nbt.NBTTagInt;
import net.minecraft.nbt.NBTTagIntArray;
import net.minecraft.nbt.NBTTagLong;
import net.minecraft.nbt.NBTTagShort;
import net.minecraft.nbt.NBTTagString;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class gr
extends Gui {
    public static final ResourceLocation n = new ResourceLocation("nbtedit", "textures/gui/window.png");
    public static final int j = 178;
    public static final int i = 93;
    private Minecraft l = Minecraft.getMinecraft();
    private ph<fi> z;
    private NBTBase s;
    private boolean g;
    private boolean t;
    private qm r;
    private int x;
    private int v;
    private ao m;
    private ao c;
    private GuiButton q;
    private GuiButton b;
    private String o;
    private String y;
    private up k;
    private up ALLATORIxDEMO;

    public gr(qm a2, ph<fi> a3, boolean a4, boolean a5) {
        gr a6;
        a6.r = a2;
        a6.z = a3;
        a6.s = a3.ALLATORIxDEMO().ALLATORIxDEMO();
        a6.g = a4;
        a6.t = a5;
    }

    public void initGUI(int a2, int a3) {
        gr a4;
        a4.x = a2;
        a4.v = a3;
        a4.ALLATORIxDEMO = new up(0, a2 + 178 - 1, a3 + 34);
        a4.k = new up(1, a2 + 178 - 1, a3 + 50);
        String a5 = a4.m == null ? a4.z.ALLATORIxDEMO().ALLATORIxDEMO() : a4.m.getText();
        String a6 = a4.c == null ? gr.ALLATORIxDEMO(a4.s) : a4.c.getText();
        a4.m = new ao(a4.l.fontRenderer, a2 + 46, a3 + 18, 116, 15, false);
        a4.c = new ao(a4.l.fontRenderer, a2 + 46, a3 + 44, 116, 15, true);
        a4.m.setText(a5);
        a4.m.setEnableBackgroundDrawing(false);
        a4.m.func_82265_c(a4.g);
        a4.c.setMaxStringLength(Integer.MAX_VALUE);
        a4.c.setText(a6);
        a4.c.setEnableBackgroundDrawing(false);
        a4.c.func_82265_c(a4.t);
        a4.q = new GuiButton(1, a2 + 9, a3 + 62, 75, 20, "Save");
        if (!a4.m.isFocused() && !a4.c.isFocused()) {
            if (a4.g) {
                a4.m.setFocused(true);
            } else if (a4.t) {
                a4.c.setFocused(true);
            }
        }
        a4.ALLATORIxDEMO.setEnabled(a4.c.isFocused());
        a4.k.setEnabled(a4.c.isFocused());
        a4.b = new GuiButton(0, a2 + 93, a3 + 62, 75, 20, "Cancel");
    }

    public void click(int a2, int a3) {
        gr a4;
        if (a4.k.inBounds(a2, a3) && a4.c.isFocused()) {
            a4.c.writeText("\n");
            a4.ALLATORIxDEMO();
        } else if (a4.ALLATORIxDEMO.inBounds(a2, a3) && a4.c.isFocused()) {
            a4.c.writeText("\u00a7");
            a4.ALLATORIxDEMO();
        } else {
            a4.m.mouseClicked(a2, a3, 0);
            a4.c.mouseClicked(a2, a3, 0);
            if (a4.q.mousePressed(a4.l, a2, a3)) {
                a4.c();
            }
            if (a4.b.mousePressed(a4.l, a2, a3)) {
                a4.r.closeWindow();
            }
            a4.ALLATORIxDEMO.setEnabled(a4.c.isFocused());
            a4.k.setEnabled(a4.c.isFocused());
        }
    }

    private /* synthetic */ void c() {
        gr a2;
        if (a2.g) {
            a2.z.ALLATORIxDEMO().ALLATORIxDEMO(a2.m.getText());
        }
        gr.ALLATORIxDEMO(a2.z, a2.c.getText());
        a2.r.nodeEdited(a2.z);
        a2.r.closeWindow();
    }

    public void draw(int a2, int a3) {
        gr a4;
        a4.l.renderEngine.bindTexture(n);
        GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a4.drawTexturedModalRect(a4.x, a4.v, 0, 0, 178, 93);
        if (!a4.g) {
            gr.drawRect((int)(a4.x + 42), (int)(a4.v + 15), (int)(a4.x + 169), (int)(a4.v + 31), (int)Integer.MIN_VALUE);
        }
        if (!a4.t) {
            gr.drawRect((int)(a4.x + 42), (int)(a4.v + 41), (int)(a4.x + 169), (int)(a4.v + 57), (int)Integer.MIN_VALUE);
        }
        a4.m.drawTextBox();
        a4.c.drawTextBox();
        a4.q.drawButton(a4.l, a2, a3, 0.0f);
        a4.b.drawButton(a4.l, a2, a3, 0.0f);
        if (a4.o != null) {
            a4.drawCenteredString(a4.l.fontRenderer, a4.o, a4.x + 89, a4.v + 4, 0xFF0000);
        }
        if (a4.y != null) {
            a4.drawCenteredString(a4.l.fontRenderer, a4.y, a4.x + 89, a4.v + 32, 0xFF0000);
        }
        a4.k.draw(a2, a3);
        a4.ALLATORIxDEMO.draw(a2, a3);
    }

    public void drawCenteredString(FontRenderer a2, String a3, int a4, int a5, int a6) {
        a2.drawString(a3, a4 - a2.getStringWidth(a3) / 2, a5, a6);
    }

    public void update() {
        gr a2;
        a2.c.updateCursorCounter();
        a2.m.updateCursorCounter();
    }

    public void keyTyped(char a2, int a3) {
        gr a4;
        if (a3 == 1) {
            a4.r.closeWindow();
        } else if (a3 == 15) {
            if (a4.m.isFocused() && a4.t) {
                a4.m.setFocused(false);
                a4.c.setFocused(true);
            } else if (a4.c.isFocused() && a4.g) {
                a4.m.setFocused(true);
                a4.c.setFocused(false);
            }
            a4.ALLATORIxDEMO.setEnabled(a4.c.isFocused());
            a4.k.setEnabled(a4.c.isFocused());
        } else if (a3 == 28) {
            a4.ALLATORIxDEMO();
            if (a4.q.enabled) {
                a4.c();
            }
        } else {
            a4.m.textboxKeyTyped(a2, a3);
            a4.c.textboxKeyTyped(a2, a3);
            a4.ALLATORIxDEMO();
        }
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        gr a2;
        boolean a3 = true;
        a2.o = null;
        a2.y = null;
        if (a2.g && !a2.ALLATORIxDEMO()) {
            a3 = false;
            a2.o = "Duplicate Tag Name";
        }
        try {
            gr.ALLATORIxDEMO(a2.c.getText(), a2.s.getId());
            a3 &= true;
        }
        catch (NumberFormatException a4) {
            a2.y = a4.getMessage();
            a3 = false;
        }
        a2.q.enabled = a3;
    }

    private /* synthetic */ boolean ALLATORIxDEMO() {
        gr a2;
        for (ph a3 : ((ph)((Object)a2.z.ALLATORIxDEMO())).ALLATORIxDEMO()) {
            NBTBase a4 = ((fi)a3.ALLATORIxDEMO()).ALLATORIxDEMO();
            if (a4 == a2.s || !((fi)a3.ALLATORIxDEMO()).ALLATORIxDEMO().equals(a2.m.getText())) continue;
            return false;
        }
        return true;
    }

    private static /* synthetic */ void ALLATORIxDEMO(ph<fi> a2, String a3) {
        fi a4 = a2.ALLATORIxDEMO();
        NBTBase a5 = a4.ALLATORIxDEMO();
        if (a5 instanceof NBTTagByte) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagByte(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagShort) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagShort(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagInt) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagInt(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagLong) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagLong(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagFloat) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagFloat(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagDouble) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagDouble(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagByteArray) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagByteArray(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagIntArray) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagIntArray(ci.ALLATORIxDEMO(a3)));
        }
        if (a5 instanceof NBTTagString) {
            a4.ALLATORIxDEMO((NBTBase)new NBTTagString(a3));
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(String a2, byte a3) throws NumberFormatException {
        switch (a3) {
            case 1: {
                ci.ALLATORIxDEMO(a2);
                break;
            }
            case 2: {
                ci.ALLATORIxDEMO(a2);
                break;
            }
            case 3: {
                ci.ALLATORIxDEMO(a2);
                break;
            }
            case 4: {
                ci.ALLATORIxDEMO(a2);
                break;
            }
            case 5: {
                ci.ALLATORIxDEMO(a2);
                break;
            }
            case 6: {
                ci.ALLATORIxDEMO(a2);
                break;
            }
            case 7: {
                ci.ALLATORIxDEMO(a2);
                break;
            }
            case 11: {
                ci.ALLATORIxDEMO(a2);
            }
        }
    }

    private static /* synthetic */ String ALLATORIxDEMO(NBTBase a2) {
        switch (a2.getId()) {
            case 7: {
                String a3 = "";
                for (byte a4 : ((NBTTagByteArray)a2).getByteArray()) {
                    a3 = a3 + a4 + " ";
                }
                return a3;
            }
            case 9: {
                return "TagList";
            }
            case 10: {
                return "TagCompound";
            }
            case 11: {
                String a5 = "";
                for (int a6 : ((NBTTagIntArray)a2).getIntArray()) {
                    a5 = a5 + a6 + " ";
                }
                return a5;
            }
        }
        return su.ALLATORIxDEMO(a2);
    }
}

