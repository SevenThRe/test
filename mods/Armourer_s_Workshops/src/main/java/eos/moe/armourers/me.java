/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.gui.inventory.GuiInventory
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.ClickType
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundEvent
 *  org.lwjgl.input.Mouse
 */
package eos.moe.armourers;

import eos.moe.armourers.cn;
import eos.moe.armourers.dj;
import eos.moe.armourers.gh;
import eos.moe.armourers.gm;
import eos.moe.armourers.jn;
import eos.moe.armourers.ql;
import eos.moe.armourers.rg;
import eos.moe.armourers.ri;
import eos.moe.armourers.sd;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.ClickType;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import org.lwjgl.input.Mouse;

public class me
extends GuiContainer {
    private IInventory s;
    private IInventory m;
    private rg j;

    public void func_73863_a(int a2, int a4, float a52) {
        me a6;
        me me2 = a6;
        super.func_73863_a(a2, a4, a52);
        me2.func_191948_b(a2, a4);
        if (!me2.field_146297_k.field_71439_g.field_71071_by.func_70445_o().func_190926_b()) {
            return;
        }
        Slot a52 = a6.getSlotUnderMouse();
        if (a52 != null && !a52.func_75216_d()) {
            gh gh2 = a6.j.y().values().stream().filter(a3 -> a3.r() == a2.field_75222_d).findFirst().orElse(null);
            if (gh2 != null) {
                List<String> list = gh2.r();
                a6.func_146283_a(list, a2, a4);
                return;
            }
        } else {
            for (sd sd2 : a6.j.z().values()) {
                if (!sd2.r(a2, a4) || sd2.r().size() <= 0) continue;
                a52 = a6.getSlotBySlotIdStr(sd2.r().get(0));
                if (a52 != null) {
                    if (!a6.field_146297_k.field_71439_g.field_71071_by.func_70445_o().func_190926_b() || !a52.func_75216_d()) continue;
                    a6.func_146285_a(a52.func_75211_c(), a2, a4);
                    continue;
                }
                a6.func_146283_a(sd2.r(), a2, a4);
            }
        }
    }

    public void func_184098_a(Slot a2, int a3, int a4, ClickType a5) {
        me a6;
        if (a2 != null) {
            a3 = a2.field_75222_d;
        }
        if (a6.j.y()) {
            if (a5 == ClickType.SWAP) {
                return;
            }
            if (a5 == ClickType.QUICK_MOVE) {
                return;
            }
        }
        a6.field_146297_k.field_71442_b.func_187098_a(a6.field_147002_h.field_75152_c, a3, a4, a5, (EntityPlayer)a6.field_146297_k.field_71439_g);
    }

    public void func_73864_a(int a2, int a3, int a4) throws IOException {
        me a5;
        me me2 = a5;
        super.func_73864_a(a2, a3, a4);
        me me3 = a5;
        int n2 = (me2.field_146294_l - me3.field_146999_f) / 2;
        int n3 = (me3.field_146295_m - a5.field_147000_g) / 2;
        for (sd sd2 : me2.j.z().values()) {
            if (!sd2.r(a2 - n2, a3 - n3)) continue;
            sd sd3 = sd2;
            a5.playSound(sd3.h());
            if (sd3.z().isEmpty()) continue;
            Slot slot = a5.getSlotBySlotIdStr(sd2.z());
            if (slot != null) {
                a5.func_184098_a(slot, 0, a4, ClickType.PICKUP);
                continue;
            }
            a5.field_146297_k.field_71439_g.func_71165_d(sd2.z());
        }
    }

    public boolean func_193983_c(int a2, int a3, int a4, int a5) {
        me a6;
        me me2 = a6;
        me me3 = a6;
        int n2 = (me2.field_146294_l - me3.field_146999_f) / 2;
        int n3 = (me2.field_146295_m - a6.field_147000_g) / 2;
        Iterator<sd> iterator = me3.j.z().values().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().r(a2 - n2, a3 - n3)) continue;
            return false;
        }
        return a2 < a4 || a3 < a5 || a2 >= a4 + a6.field_146999_f || a3 >= a5 + a6.field_147000_g;
    }

    public void func_146281_b() {
        me a2;
        me me2 = a2;
        super.func_146281_b();
        if (me2.j.r()) {
            gm.r();
        }
    }

    public void playSound(String a2) {
        if (!a2.isEmpty() && (a2 = (SoundEvent)SoundEvent.field_187505_a.func_82594_a((Object)new ResourceLocation(a2))) != null) {
            me a3;
            a3.field_146297_k.field_71439_g.func_184185_a((SoundEvent)a2, 1.0f, 1.0f);
        }
    }

    public void func_146976_a(float a222, int a3, int a6) {
        float f2;
        String string;
        me a7;
        me me2 = a7;
        ql.r(me2.j.y());
        me me3 = a7;
        int a222 = (me2.field_146294_l - me3.field_146999_f) / 2;
        int n2 = (me3.field_146295_m - a7.field_147000_g) / 2;
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        me me4 = a7;
        me.func_146110_a((int)a222, (int)n2, (float)0.0f, (float)0.0f, (int)me4.field_146999_f, (int)me4.field_147000_g, (float)a7.field_146999_f, (float)a7.field_147000_g);
        for (sd cn2 : me2.j.z().values()) {
            if (cn2.r(a3 - a222, a6 - n2)) {
                if (Mouse.isButtonDown((int)0)) {
                    ql.r(cn2.s());
                } else {
                    ql.r(cn2.y());
                }
            } else {
                ql.r(cn2.x());
            }
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            me.func_146110_a((int)(a222 + cn2.r().x), (int)(n2 + cn2.r().y), (float)0.0f, (float)0.0f, (int)cn2.y(), (int)cn2.r(), (float)cn2.y(), (float)cn2.r());
            if (cn2.w() == null) continue;
            string = new StringBuilder().insert(0, "\u00a7f").append(jn.r(cn2.w())).toString();
            f2 = (float)(a222 + cn2.r().x) + (float)cn2.y() / 2.0f;
            float f3 = (float)(n2 + cn2.r().y) + (float)cn2.r() / 2.0f - 5.0f;
            String string2 = string;
            a7.field_146289_q.func_175065_a(string2, f2 - (float)a7.field_146289_q.func_78256_a(string2) / 2.0f, f3, 0, false);
        }
        a7.j.y().forEach((a4, a5) -> {
            me a6;
            a4 = a6.field_147002_h.func_75139_a(a5.r());
            if (a4.func_75216_d() && a5.y() != null && !a5.y().isEmpty()) {
                ql.r(a5.y());
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                me.func_146110_a((int)(a222 + ((Slot)a4).field_75223_e), (int)(n2 + ((Slot)a4).field_75221_f), (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
                return;
            }
            if (a5.z() != null && !a5.z().isEmpty()) {
                ql.r(a5.z());
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                me.func_146110_a((int)(a222 + ((Slot)a4).field_75223_e), (int)(n2 + ((Slot)a4).field_75221_f), (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
            }
        });
        Iterator<cn> iterator = a7.j.r().values().iterator();
        Iterator<cn> iterator2 = iterator;
        while (iterator2.hasNext()) {
            ri ri2;
            ri ri3 = (ri)iterator.next();
            string = new StringBuilder().insert(0, "\u00a7f").append(jn.r(ri3.y())).toString();
            GlStateManager.func_179094_E();
            if (ri3.r()) {
                f2 = (float)a7.field_146297_k.field_71466_p.func_78256_a(string) / 2.0f * ri3.r();
                ri ri4 = ri3;
                ri2 = ri4;
                GlStateManager.func_179137_b((double)(ri3.r().getX() + (double)a222 - (double)f2), (double)(ri4.r().getY() + (double)n2), (double)0.0);
            } else {
                ri ri5 = ri3;
                ri2 = ri5;
                GlStateManager.func_179137_b((double)(ri5.r().getX() + (double)a222), (double)(ri3.r().getY() + (double)n2), (double)0.0);
            }
            GlStateManager.func_179152_a((float)ri2.r(), (float)ri3.r(), (float)ri3.r());
            me me5 = a7;
            if (ri3.r()) {
                me5.field_146297_k.field_71466_p.func_175063_a(string, 0.0f, 0.0f, -1);
            } else {
                me5.field_146297_k.field_71466_p.func_175063_a(string, 0.0f, 0.0f, -1);
            }
            GlStateManager.func_179121_F();
            iterator2 = iterator;
        }
        if (a7.j.r().r() > 0) {
            float f4 = 1.6666666f * (float)a7.j.r().r();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GuiInventory.func_147046_a((int)(a7.j.r().r().x + a222), (int)(a7.j.r().r().y + n2), (int)a7.j.r().r(), (float)(a7.j.r().r().x + a222 - a3), (float)((float)(a7.j.r().r().y + n2) - f4 - (float)a6), (EntityLivingBase)a7.field_146297_k.field_71439_g);
        }
    }

    public me(rg a2, IInventory a3, IInventory a4) {
        me a5;
        me me2 = a5;
        me me3 = a5;
        super((Container)new dj(a2, a3, a4));
        a5.j = a2;
        me2.m = a3;
        me2.s = a4;
        if (a2.r()) {
            gm.y();
        }
    }

    public void func_73866_w_() {
        me a5;
        me me2 = a5;
        me2.field_146999_f = me2.j.z();
        me2.field_147000_g = me2.j.y();
        super.func_73866_w_();
        me2.j.y().forEach((a2, a3) -> {
            me a4;
            if (a3.r() < a4.s.func_70302_i_()) {
                String string = a2 = a4.field_147002_h.func_75139_a(a3.r());
                ((Slot)string).field_75223_e = a3.r().x;
                ((Slot)string).field_75221_f = a3.r().y;
            }
        });
        me me3 = a5;
        int n2 = me3.s.func_70302_i_();
        me3.j.r().forEach((a3, a4) -> {
            me a5;
            Slot slot = a2 = a5.field_147002_h.func_75139_a(n2 + a3);
            slot.field_75223_e = a4.x;
            slot.field_75221_f = a4.y;
        });
    }

    public Slot getSlotBySlotIdStr(String a22) {
        if (a22.startsWith("slot-")) {
            try {
                me a3;
                return a3.field_147002_h.func_75139_a(Integer.parseInt(a22.substring(5)));
            }
            catch (Exception a22) {
                return null;
            }
        }
        return null;
    }
}

