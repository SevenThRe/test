/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.entity.Entity
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.math.BlockPos
 *  org.lwjgl.input.Keyboard
 *  org.lwjgl.input.Mouse
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.gr;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.qm;
import eos.moe.dragoncore.ro;
import eos.moe.dragoncore.yh;
import java.io.IOException;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

public class nr
extends GuiScreen {
    public final int q;
    public final int b;
    public final int o;
    private ro y;
    public String k;
    private qm ALLATORIxDEMO;

    public nr(int a2, NBTTagCompound a3) {
        nr a4;
        a4.y = ro.y;
        a4.q = a2;
        a4.b = 0;
        a4.o = 0;
        a4.k = "NBTEdit -- EntityId #" + a4.q;
        a4.ALLATORIxDEMO = new qm(new yh(a3));
    }

    public nr(BlockPos a2, NBTTagCompound a3) {
        nr a4;
        a4.y = ro.k;
        a4.q = a2.func_177958_n();
        a4.b = a2.func_177956_o();
        a4.o = a2.func_177952_p();
        a4.k = "NBTEdit -- TileEntity at " + a2.func_177958_n() + "," + a2.func_177956_o() + "," + a2.func_177952_p();
        a4.ALLATORIxDEMO = new qm(new yh(a3));
    }

    public nr(NBTTagCompound a2) {
        nr a3;
        a3.y = ro.o;
        a3.q = 0;
        a3.b = 0;
        a3.o = 0;
        a3.k = "NBTEdit -- \u624b\u4e2d\u4e4b\u7269";
        a3.ALLATORIxDEMO = new qm(new yh(a2));
    }

    public void func_73866_w_() {
        nr a2;
        Keyboard.enableRepeatEvents((boolean)true);
        a2.field_146292_n.clear();
        a2.ALLATORIxDEMO.initGUI(a2.field_146294_l, a2.field_146295_m, a2.field_146295_m - 35);
        a2.field_146292_n.add(new GuiButton(1, a2.field_146294_l / 4 - 100, a2.field_146295_m - 27, "\u4fdd\u5b58\u6570\u636e"));
        a2.field_146292_n.add(new GuiButton(0, a2.field_146294_l * 3 / 4 - 100, a2.field_146295_m - 27, "\u9000\u51fa"));
    }

    public void func_146281_b() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    public void func_73869_a(char a2, int a3) {
        nr a4;
        gr a5 = a4.ALLATORIxDEMO.getWindow();
        if (a5 != null) {
            a5.keyTyped(a2, a3);
        } else if (a3 == 1) {
            if (a4.ALLATORIxDEMO.isEditingSlot()) {
                a4.ALLATORIxDEMO.stopEditingSlot();
            } else {
                a4.ALLATORIxDEMO();
            }
        } else if (a3 == 211) {
            a4.ALLATORIxDEMO.deleteSelected();
        } else if (a3 == 28) {
            a4.ALLATORIxDEMO.editSelected();
        } else if (a3 == 200) {
            a4.ALLATORIxDEMO.arrowKeyPressed(true);
        } else if (a3 == 208) {
            a4.ALLATORIxDEMO.arrowKeyPressed(false);
        } else {
            a4.ALLATORIxDEMO.keyTyped(a2, a3);
        }
    }

    public void func_73864_a(int a2, int a3, int a4) throws IOException {
        nr a5;
        if (a5.ALLATORIxDEMO.getWindow() == null) {
            super.func_73864_a(a2, a3, a4);
        }
        if (a4 == 0) {
            a5.ALLATORIxDEMO.mouseClicked(a2, a3);
        }
        if (a4 == 1) {
            a5.ALLATORIxDEMO.rightClick(a2, a3);
        }
    }

    public void func_146274_d() throws IOException {
        nr a2;
        super.func_146274_d();
        int a3 = Mouse.getEventDWheel();
        if (a3 != 0) {
            a2.ALLATORIxDEMO.shift(a3 >= 1 ? 6 : -6);
        }
    }

    public void func_146284_a(GuiButton a2) {
        if (a2.field_146124_l) {
            switch (a2.field_146127_k) {
                case 1: {
                    nr a3;
                    a3.c();
                    break;
                }
                default: {
                    nr a3;
                    a3.ALLATORIxDEMO();
                }
            }
        }
    }

    public void func_73876_c() {
        nr a2;
        if (!a2.field_146297_k.field_71439_g.func_70089_S()) {
            a2.ALLATORIxDEMO();
        } else {
            a2.ALLATORIxDEMO.updateScreen();
        }
    }

    private /* synthetic */ void c() {
        nr a2;
        switch (a2.y) {
            case o: {
                nw.ALLATORIxDEMO(a2.ALLATORIxDEMO.getNBTTree().ALLATORIxDEMO());
                break;
            }
            case y: {
                nw.ALLATORIxDEMO(a2.q, a2.ALLATORIxDEMO.getNBTTree().ALLATORIxDEMO());
                break;
            }
            case k: {
                nw.ALLATORIxDEMO(new BlockPos(a2.q, a2.b, a2.o), a2.ALLATORIxDEMO.getNBTTree().ALLATORIxDEMO());
            }
        }
        a2.field_146297_k.func_147108_a(null);
        a2.field_146297_k.func_71381_h();
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        nr a2;
        a2.field_146297_k.func_147108_a(null);
    }

    public void func_73863_a(int a2, int a3, float a4) {
        nr a5;
        a5.func_146276_q_();
        a5.ALLATORIxDEMO.draw(a2, a3);
        a5.func_73732_a(a5.field_146297_k.field_71466_p, a5.k, a5.field_146294_l / 2, 5, 0xFFFFFF);
        if (a5.ALLATORIxDEMO.getWindow() == null) {
            super.func_73863_a(a2, a3, a4);
        } else {
            super.func_73863_a(-1, -1, a4);
        }
    }

    public boolean func_73868_f() {
        return true;
    }

    public Entity getEntity() {
        nr a2;
        return a2.y == ro.y ? a2.field_146297_k.field_71441_e.func_73045_a(a2.q) : null;
    }

    public boolean isTileEntity() {
        nr a2;
        return a2.y == ro.k;
    }

    public int getBlockX() {
        nr a2;
        return a2.y == ro.y ? 0 : a2.q;
    }
}

