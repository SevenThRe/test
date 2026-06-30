/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 */
package eos.moe.dragoncore;

import blockbuster.emitter.BedrockEmitter;
import blockbuster.utils.Interpolations;
import eos.moe.dragoncore.ModelLocator;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.gg;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.kw;
import eos.moe.dragoncore.ll;
import eos.moe.dragoncore.mt;
import eos.moe.dragoncore.nk;
import eos.moe.dragoncore.pw;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.ri;
import eos.moe.dragoncore.xz;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;

public class fba {
    public fba() {
        fba a2;
    }

    public static void ALLATORIxDEMO(float a2) {
        for (Entity a3 : Minecraft.func_71410_x().field_71441_e.func_72910_y()) {
            EntityLivingBase a4;
            rda a5;
            xz a6 = dt.k.getEntityManager(a3.func_110124_au());
            if (a6 == null || !(a3 instanceof EntityLivingBase) || a6.c().isEmpty() || (a5 = raa.r.c(a4 = (EntityLivingBase)a3)) == null || a5.ALLATORIxDEMO() == null || a5.ALLATORIxDEMO().ALLATORIxDEMO() == null) continue;
            pw a7 = (pw)a5.ALLATORIxDEMO().ALLATORIxDEMO();
            jv a8 = a7.getBaseModel();
            a6.ALLATORIxDEMO(a7);
            for (Map.Entry<kw, BedrockEmitter> a9 : a6.c().entrySet()) {
                mt a10;
                BedrockEmitter a11 = a9.getValue();
                String a12 = a9.getKey().c();
                if (a8.getLocatorMap() == null) continue;
                ModelLocator a13 = a8.getLocatorMap().get(a12);
                if (a12 == null || a13 == null || (a10 = a8.getPiece(a13.getBone())) == null) continue;
                nk a14 = new nk();
                a14.c();
                float a15 = ri.ALLATORIxDEMO(a4, a2);
                a14.ALLATORIxDEMO(gg.q.ALLATORIxDEMO(a15));
                a10.position(a14, 0.0625f);
                a10.backPosition(a14, 0.0625f);
                a14.ALLATORIxDEMO((double)a13.getOffsetX(), (double)(-a13.getOffsetY()), (double)a13.getOffsetZ());
                ll a16 = new ll(0.0f, 0.0f, 0.0f, 1.0f);
                a16.ALLATORIxDEMO(a14);
                a11.lastGlobal.x = Interpolations.lerp(a4.field_70169_q, a4.field_70165_t, (double)a2) + (double)a16.x();
                a11.lastGlobal.y = Interpolations.lerp(a4.field_70167_r, a4.field_70163_u, (double)a2) - (double)a16.f();
                a11.lastGlobal.z = Interpolations.lerp(a4.field_70166_s, a4.field_70161_v, (double)a2) - (double)a16.c();
                GlStateManager.func_179094_E();
                a11.render(a2);
                GlStateManager.func_179121_F();
            }
            a7.clearData();
        }
    }
}

