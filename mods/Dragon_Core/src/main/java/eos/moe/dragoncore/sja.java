/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.al;
import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.interfaces.IItemStack;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sfa;
import eos.moe.dragoncore.ww;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.lwjgl.opengl.GL11;

public class sja {
    public static sja y = new sja();
    public List<sfa> k = new CopyOnWriteArrayList<sfa>();
    public long ALLATORIxDEMO = Long.MAX_VALUE;

    public sja() {
        sja a2;
    }

    public void ALLATORIxDEMO() {
        a.ALLATORIxDEMO = System.currentTimeMillis();
    }

    public sfa ALLATORIxDEMO(ItemStack a2, int a3) {
        sja a4;
        if (a4.k.size() == 0) {
            return null;
        }
        if (a2 == null || a2.func_190926_b()) {
            return null;
        }
        IItemStack a5 = (IItemStack)a2;
        long a6 = (Long)a5.getMeta("EffectApplyModelTime" + a3, 0L);
        if (a4.ALLATORIxDEMO > a6) {
            String a7 = dj.ALLATORIxDEMO(a2, false, false);
            int a8 = Item.func_150891_b((Item)a2.func_77973_b());
            sfa a9 = null;
            for (sfa a10 : a4.k) {
                if (sfa.c(a10) != a3 || sfa.ALLATORIxDEMO(a10) != 0 && sfa.ALLATORIxDEMO(a10) != a8 || !al.ALLATORIxDEMO(sfa.ALLATORIxDEMO(a10), a7)) continue;
                a9 = a10;
                break;
            }
            a5.setMeta("effectKey" + a3, a9);
            a5.setMeta("EffectApplyModelTime" + a3, System.currentTimeMillis());
        }
        return (sfa)a5.getMeta("effectKey" + a3);
    }

    public void ALLATORIxDEMO(ItemStack a2, double a3, double a4, int a5) {
        sja a6;
        sfa a7 = a6.ALLATORIxDEMO(a2, a5);
        if (a7 == null) {
            return;
        }
        a3 -= (sfa.c(a7) - 16.0) / 2.0;
        a4 -= (sfa.ALLATORIxDEMO(a7) - 16.0) / 2.0;
        ww.ALLATORIxDEMO(sfa.ALLATORIxDEMO(a7));
        sd.ALLATORIxDEMO(1.0f);
        boolean a8 = GL11.glIsEnabled((int)2896);
        boolean a9 = GL11.glIsEnabled((int)2929);
        boolean a10 = GL11.glIsEnabled((int)3042);
        GlStateManager.func_179140_f();
        GlStateManager.func_179097_i();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179147_l();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179118_c();
        sd.ALLATORIxDEMO(a3, a4, 0.0, 0.0, sfa.c(a7), sfa.ALLATORIxDEMO(a7), sfa.c(a7), sfa.ALLATORIxDEMO(a7));
        if (a8) {
            GlStateManager.func_179145_e();
        }
        if (a9) {
            GlStateManager.func_179126_j();
        }
        if (!a10) {
            GlStateManager.func_179084_k();
        }
    }
}

