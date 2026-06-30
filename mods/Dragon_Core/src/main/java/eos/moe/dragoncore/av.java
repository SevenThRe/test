/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.fm;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.nh;
import eos.moe.dragoncore.nj;
import eos.moe.dragoncore.qr;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.xf;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

@Mod.EventBusSubscriber(modid="dragoncore")
public class av {
    public av() {
        av a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        WorldClient a3 = Minecraft.func_71410_x().field_71441_e;
        if (a3 != null) {
            List a4 = a3.func_72910_y();
            for (Entity a5 : a4) {
                nh a6;
                EntityLivingBase a7;
                kp a8 = (kp)qr.b.k.getIfPresent((Object)a5.func_110124_au());
                if (a8 == null) continue;
                String a9 = a8.q.get("name");
                if (!a9.equals(qr.b.getEntityName(a5))) {
                    qr.b.k.invalidate((Object)a5.func_110124_au());
                    continue;
                }
                a8.ALLATORIxDEMO(nj.h);
                if (!(a5 instanceof EntityLivingBase)) continue;
                a8.y = a7 = (EntityLivingBase)a5;
                String a10 = a8.q.get("health");
                String a11 = String.valueOf(a7.func_110143_aJ());
                String a12 = a8.q.get("health_max");
                String a13 = String.valueOf(a7.func_110138_aP());
                if ((a10 != null && !a10.equals(a11) || a12 != null && !a12.equals(a13)) && (a6 = a8.c.get(nj.f.ALLATORIxDEMO())) != null) {
                    fm.ALLATORIxDEMO(a6, new xf(a10), new xf(a11));
                }
                a8.q.put("health", a11);
                a8.q.put("health_max", a13);
            }
        }
    }

    @SubscribeEvent(priority=EventPriority.HIGH)
    public static void ALLATORIxDEMO(RenderLivingEvent.Specials.Pre<EntityLivingBase> a2) {
        YamlConfiguration a3;
        if (a2.isCanceled()) {
            return;
        }
        EntityLivingBase a4 = a2.getEntity();
        qr a5 = qr.b;
        kp a6 = (kp)a5.k.getIfPresent((Object)a4.func_110124_au());
        if (a6 == null && (a3 = a5.getMatchYaml((Entity)a4)) != null) {
            a6 = new kp(a4, a3);
            a6.q.put("name", a5.getEntityName((Entity)a4));
            a5.k.put((Object)a4.func_110124_au(), (Object)a6);
            a6.ALLATORIxDEMO(nj.n);
        }
        if (a6 == null) {
            return;
        }
        a3 = Minecraft.func_71410_x().func_175598_ae();
        switch (a6.x) {
            case "aim": {
                if (((RenderManager)a3).field_147941_i == a4) break;
                return;
            }
            case "always": {
                break;
            }
            case "health": {
                if (a4.func_110143_aJ() != a4.func_110138_aP()) break;
                return;
            }
            case "aimorhealth": {
                if (((RenderManager)a3).field_147941_i == a4 || a4.func_110143_aJ() != a4.func_110138_aP()) break;
                return;
            }
        }
        if (a6.x.startsWith("distance_")) {
            int a7 = 10;
            try {
                a7 = Integer.parseInt(a6.x.substring(9));
            }
            catch (Exception exception) {
                // empty catch block
            }
            EntityPlayerSP a8 = Minecraft.func_71410_x().field_71439_g;
            if (a8.func_70032_d((Entity)a4) > (float)a7) {
                return;
            }
        }
        a2.setCanceled(true);
        float a9 = (float)a2.getX();
        float a10 = (float)((double)((float)a2.getY()) + a6.b.c());
        float a11 = (float)a2.getZ();
        float a12 = ((RenderManager)a3).field_78735_i;
        float a13 = ((RenderManager)a3).field_78732_j;
        boolean a14 = ((RenderManager)a3).field_78733_k.field_74320_O == 2;
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)a9, (float)a10, (float)a11);
        GlStateManager.func_187432_a((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)(-a12), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)((float)(a14 ? -1 : 1) * a13), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)-0.025f, (float)-0.025f, (float)0.025f);
        GlStateManager.func_179140_f();
        GlStateManager.func_179132_a((boolean)false);
        GlStateManager.func_179097_i();
        GlStateManager.func_179147_l();
        GlStateManager.func_187428_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        if (a6.r) {
            GlStateManager.func_179094_E();
            sd.ALLATORIxDEMO(0.5f);
            a6.ALLATORIxDEMO(a4, a2.getPartialRenderTick(), true);
            GlStateManager.func_179121_F();
        }
        GlStateManager.func_179126_j();
        GlStateManager.func_179132_a((boolean)true);
        GlStateManager.func_179094_E();
        sd.ALLATORIxDEMO(1.0f);
        a6.ALLATORIxDEMO(a4, a2.getPartialRenderTick(), false);
        GlStateManager.func_179121_F();
        GlStateManager.func_179145_e();
        GlStateManager.func_179084_k();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.func_179121_F();
    }
}

