/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.gui.inventory.GuiContainer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.inventory.Container
 *  net.minecraft.inventory.IInventory
 *  net.minecraft.inventory.Slot
 *  net.minecraft.util.math.MathHelper
 *  org.lwjgl.input.Keyboard
 */
package eos.moe.armourers;

import com.google.common.collect.ImmutableList;
import eos.moe.armourers.af;
import eos.moe.armourers.ee;
import eos.moe.armourers.je;
import eos.moe.armourers.nf;
import eos.moe.armourers.nj;
import eos.moe.armourers.qi;
import eos.moe.armourers.ql;
import eos.moe.armourers.t;
import eos.moe.armourers.th;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.input.Keyboard;

public class uf
extends GuiContainer
implements t {
    private je v;
    private GuiTextField s;
    private List<String> m = new ArrayList<String>();
    private nj j;

    public void func_73869_a(char a2, int a3) throws IOException {
        uf a4;
        if (a4.s.func_146201_a(a2, a3)) {
            return;
        }
        super.func_73869_a(a2, a3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void func_73863_a(int a2, int a3, float a4) {
        uf a5;
        GlStateManager.func_179140_f();
        uf uf2 = a5;
        uf.func_73734_a((int)0, (int)0, (int)uf2.field_146294_l, (int)uf2.field_146295_m, (int)-1946157056);
        int n2 = 6;
        uf uf3 = a5;
        int n3 = uf3.field_146295_m - 80;
        GlStateManager.func_179140_f();
        ql.r("armour-library.png");
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        uf3.func_73729_b(n2 - 1, n3 - 1, 0, 0, 162, 76);
        uf.func_73734_a((int)6, (int)24, (int)167, (int)118, (int)-16777216);
        uf.func_73734_a((int)7, (int)25, (int)166, (int)117, (int)-3750202);
        uf3.v.r(a2, a3, a4);
        uf3.j.r(a2, a3, a4);
        uf3.s.func_146194_f();
        List<String> list = af.l;
        synchronized (list) {
            if (a5.v.r() != null) {
                uf uf4 = a5;
                int n4 = uf4.field_146294_l - 162 - 25;
                n4 = MathHelper.func_76125_a((int)n4, (int)0, (int)200);
                int n5 = 39;
                int n6 = (uf4.field_146294_l - (n4 += 182) - 5) / 2;
                int n7 = (uf4.field_146295_m - n5 - 5) / 2;
                float f2 = n4 + n6;
                float f3 = n5 + n7;
                n6 = (int)(f2 + (float)n6);
                uf.func_73734_a((int)(n4 += 5), (int)12, (int)(a5.field_146294_l - 6), (int)(a5.field_146295_m - 6), (int)0x77777777);
                th.r(f2, f3, n6 - n4, (n3 += n7) - (n5 += 5), a5.v.r());
            }
        }
        GlStateManager.func_179145_e();
        super.func_73863_a(a2, a3, a4);
    }

    public void func_146976_a(float a2, int a3, int a4) {
        uf a5;
        a5.field_146289_q.func_175065_a("====\u9f99\u4e4b\u65f6\u88c5====", (float)(84 - a5.field_146289_q.func_78256_a("====\u9f99\u4e4b\u65f6\u88c5====") / 2), 27.0f, 0, false);
        a5.field_146289_q.func_175065_a("\u8bbe\u7f6e\u65f6\u88c5\u6559\u7a0b:", 10.0f, 40.0f, 0, false);
        a5.field_146289_q.func_175065_a("\u70b9\u51fb\u65f6\u88c5\uff0c\u62d6\u81f3\u7269\u54c1\u4e0a\u5373\u53ef\u8bbe\u7f6e\u65f6\u88c5", 10.0f, 50.0f, 0, false);
        a5.field_146289_q.func_175065_a("=====================================", 10.0f, 57.0f, 0, false);
        a5.field_146289_q.func_175065_a("\u5408\u5e76\u65f6\u88c5\u6559\u7a0b:", 10.0f, 65.0f, 0, false);
        a5.field_146289_q.func_175065_a("\u70b9\u51fb\u4f60\u60f3\u5408\u5e76\u7684\u65f6\u88c5\uff0c\u62d6\u81f3\u5de6\u4fa7\u9ed1\u6846\u5185", 10.0f, 75.0f, 0, false);
        a5.field_146289_q.func_175065_a("\u8f93\u5165\u4fdd\u5b58\u540d\uff0c\u70b9\u51fb\u5408\u5e76\u65f6\u88c5|\u53cc\u51fb\u79fb\u9664\u65f6\u88c5", 10.0f, 85.0f, 0, false);
        a5.field_146289_q.func_175065_a("\u6253\u5f00\u6587\u4ef6\u5939 \u65f6\u88c5\u5408\u6210", 10.0f, 95.0f, 0, false);
        a5.field_146289_q.func_175065_a("\u5373\u53ef\u770b\u5230\u4f60\u5408\u6210\u7684\u65f6\u88c5", 10.0f, 105.0f, 0, false);
    }

    public void func_146979_b(int a2, int a3) {
        uf a4;
        a4.func_191948_b(a2, a3);
    }

    private /* synthetic */ void y(List a2, ee a3) {
        uf a4;
        int n2 = a3 = 0;
        while (n2 < a2.size()) {
            ((GuiButton)a4.field_146292_n.get((int)a3)).field_146125_m = !((GuiButton)a4.field_146292_n.get((int)a3)).field_146125_m;
            n2 = ++a3;
        }
        ((GuiButton)a4.field_146292_n.get((int)17)).field_146125_m = !((GuiButton)a4.field_146292_n.get((int)17)).field_146125_m;
    }

    public void func_146281_b() {
        Keyboard.enableRepeatEvents((boolean)false);
    }

    private /* synthetic */ void r(List a2, ee a3) {
        uf a4;
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.size()) {
            Object e2 = a4.field_146292_n.get(n2);
            ((GuiButton)e2).field_146125_m = false;
            n3 = ++n2;
        }
        af.v = a3.field_146126_j;
        ((GuiButton)a4.field_146292_n.get((int)16)).field_146126_j = new StringBuilder().insert(0, "\u5f53\u524d\u7b5b\u9009: ").append(a3.field_146126_j).toString();
        ((GuiButton)a4.field_146292_n.get((int)17)).field_146125_m = true;
        a4.r();
    }

    private /* synthetic */ void r() {
        uf a2;
        if (a2.v != null) {
            a2.v.l = -1;
        }
        af.z();
    }

    @Override
    public void dragSkin(String a2, int a3, int a4) {
        uf a5;
        Slot slot = a5.getSlotUnderMouse();
        if (slot != null) {
            nf.r(slot.getSlotIndex(), a2);
            return;
        }
        if (a5.j.r(a3, a4) && !a5.m.contains(a2)) {
            a5.m.add(a2);
        }
    }

    public static /* synthetic */ List r(uf a2) {
        return a2.m;
    }

    public void func_73866_w_() {
        Slot slot;
        int n2;
        uf a3;
        ScaledResolution scaledResolution = new ScaledResolution(a3.field_146297_k);
        uf uf2 = a3;
        ScaledResolution scaledResolution2 = scaledResolution;
        a3.field_146999_f = scaledResolution2.func_78326_a();
        uf2.field_147000_g = scaledResolution2.func_78328_b();
        super.func_73866_w_();
        Keyboard.enableRepeatEvents((boolean)true);
        uf2.field_146292_n.clear();
        int n3 = 18;
        int n4 = n2 = 0;
        while (n4 < 3) {
            int n5;
            int n6 = n5 = 0;
            while (n6 < 9) {
                slot = (Slot)a3.field_147002_h.field_75151_b.get(n5 + n2 * 9);
                v4.field_75221_f = a3.field_146295_m + 1 - 76 - 5 + n2 * n3;
                n6 = ++n5;
            }
            n4 = ++n2;
        }
        int n7 = n2 = 0;
        while (n7 < 9) {
            Slot slot2 = (Slot)a3.field_147002_h.field_75151_b.get(n2 + 27);
            v6.field_75221_f = a3.field_146295_m + 1 - 5 - n3;
            n7 = ++n2;
        }
        uf uf3 = a3;
        n2 = uf3.field_146294_l - 162 - 25;
        int n8 = uf3.field_146295_m - 15 - 14 - 15 + 26;
        n2 = MathHelper.func_76125_a((int)n2, (int)0, (int)200);
        uf uf4 = a3;
        uf uf5 = a3;
        uf4.v = new je(172, 12, n2, Math.max(0, n8), 14, uf5.field_146294_l, uf5.field_146295_m, a3);
        uf uf6 = a3;
        uf3.j = new nj(a3, 7, 120, 100, Math.max(0, a3.field_146295_m - 204), 12, uf6.field_146294_l, uf6.field_146295_m);
        Object[] objectArray = new String[4];
        objectArray[0] = "\u9504";
        objectArray[1] = "\u5957\u88c5";
        objectArray[2] = "\u65b9\u5757";
        objectArray[3] = "\u7269\u54c1";
        slot = ImmutableList.of((Object)"*", (Object)"\u5934\u90e8", (Object)"\u8eab\u4f53", (Object)"\u817f\u90e8", (Object)"\u811a\u90e8", (Object)"\u7fc5\u8180", (Object)"\u5251", (Object)"\u76fe\u724c", (Object)"\u5f13", (Object)"\u9550", (Object)"\u65a7", (Object)"\u94f2", (Object[])objectArray);
        int n9 = n3 = 0;
        while (n9 < slot.size()) {
            int n10 = n3;
            ee ee2 = new ee(n10, 102, 12 * n10 + 13 + 12, 65, 12, (String)slot.get(n3), arg_0 -> a3.r((List)slot, arg_0));
            ee2.field_146125_m = false;
            a3.field_146292_n.add(ee2);
            n9 = ++n3;
        }
        a3.field_146292_n.add(new ee(100, 102, 12, 65, 12, "\u5f53\u524d\u7b5b\u9009: *", arg_0 -> a3.y((List)slot, arg_0)));
        a3.field_146292_n.add(new ee(101, 110, 145, 57, 20, "\u5408\u5e76\u65f6\u88c5", a2 -> {
            uf a3;
            if (!a3.s.func_146179_b().isEmpty() && a3.m.size() > 0) {
                uf uf2 = a3;
                nf.r(a3.s.func_146179_b(), uf2.m);
                uf2.m.clear();
            }
        }));
        uf uf7 = a3;
        uf7.s = new GuiTextField(1, a3.field_146289_q, 110, 120, 55, 20);
        uf7.s.func_146195_b(false);
        uf7.s.func_146205_d(true);
        uf7.r();
    }

    public void func_73864_a(int a2, int a3, int a4) throws IOException {
        uf a5;
        a5.s.func_146192_a(a2, a3, a4);
        super.func_73864_a(a2, a3, a4);
    }

    public uf(IInventory a2, IInventory a3) {
        super((Container)new qi(a2, a3));
        uf a4;
        af.y();
    }
}

