/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.RemovalListener
 *  com.google.common.cache.RemovalNotification
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.event.entity.living.LivingEvent$LivingJumpEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import eos.moe.dragoncore.bs;
import eos.moe.dragoncore.gga;
import eos.moe.dragoncore.hp;
import eos.moe.dragoncore.hr;
import eos.moe.dragoncore.jz;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.rz;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.ut;
import eos.moe.dragoncore.xr;
import eos.moe.dragoncore.xz;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class dt
implements RemovalListener<UUID, xz> {
    public static dt k = new dt();
    public Cache<UUID, xz> ALLATORIxDEMO;

    public dt() {
        dt a2;
        CacheBuilder a3 = CacheBuilder.newBuilder();
        a3.expireAfterAccess(1L, TimeUnit.MINUTES);
        a3.removalListener((RemovalListener)a2);
        a2.ALLATORIxDEMO = a3.build();
    }

    public void onRemoval(RemovalNotification<UUID, xz> a2) {
        xz a3 = (xz)a2.getValue();
        a3.ALLATORIxDEMO();
    }

    @SubscribeEvent
    public void beforeLivingRender(RenderLivingEvent.Pre<? extends EntityLivingBase> a2) {
        dt a3;
        EntityLivingBase a4 = a2.getEntity();
        xz a5 = (xz)a3.ALLATORIxDEMO.getIfPresent((Object)a4.func_110124_au());
        if (a5 != null) {
            jz.ALLATORIxDEMO(a5, a2.getPartialRenderTick());
        }
    }

    @SubscribeEvent
    public void onJoin(EntityJoinWorldEvent a2) {
        try {
            if (a2.getEntity() instanceof EntityLivingBase) {
                dt a3;
                a3.init((EntityLivingBase)a2.getEntity(), true);
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    @SubscribeEvent
    public void onJump(LivingEvent.LivingJumpEvent a2) {
        dt a3;
        xz a4 = (xz)a3.ALLATORIxDEMO.getIfPresent((Object)a2.getEntityLiving().func_110124_au());
        if (a4 != null) {
            a4.ALLATORIxDEMO(bs.x, new String[0]);
            a4.ALLATORIxDEMO("jump", "true");
        }
    }

    @SubscribeEvent
    public void onTick(TickEvent.ClientTickEvent a2) {
        if (a2.phase != TickEvent.Phase.START) {
            return;
        }
        WorldClient a3 = Minecraft.func_71410_x().field_71441_e;
        if (a3 == null) {
            return;
        }
        for (Entity a4 : a3.func_72910_y()) {
            xz a5;
            dt a6;
            if (!(a4 instanceof EntityLivingBase)) continue;
            EntityLivingBase a7 = (EntityLivingBase)a4;
            if (a6.checkNameChange(a7)) {
                a6.init((EntityLivingBase)a4, false);
            }
            if ((a5 = (xz)a6.ALLATORIxDEMO.getIfPresent((Object)a7.func_110124_au())) == null) continue;
            a5.o.ALLATORIxDEMO(a4);
            int a8 = Integer.parseInt(a5.ALLATORIxDEMO("hurtTime", "0"));
            int a9 = a7.field_70737_aN;
            if (a9 > a8) {
                a5.ALLATORIxDEMO(bs.v, new String[0]);
            }
            a5.ALLATORIxDEMO("hurtTime", String.valueOf(a9));
            if (a5.ALLATORIxDEMO("jump", "false").equals("true") && a4.field_70122_E) {
                a5.ALLATORIxDEMO("jump", "false");
            }
            a5.ALLATORIxDEMO(bs.c, new String[0]);
        }
    }

    public boolean checkNameChange(EntityLivingBase a2) {
        dt a3;
        xz a4 = (xz)a3.ALLATORIxDEMO.getIfPresent((Object)a2.func_110124_au());
        return a4 != null && !a4.t.equals(a3.getEntityName(a2));
    }

    public void init(EntityLivingBase a2, boolean a3) {
        dt a4;
        if (a3 && a4.ALLATORIxDEMO.getIfPresent((Object)a2.func_110124_au()) != null && !a4.checkNameChange(a2)) {
            return;
        }
        rda a5 = raa.r.c(a2);
        if (a5 == null || a5.d()) {
            return;
        }
        gga a6 = a5.ALLATORIxDEMO();
        if (a6 == null || a6.ALLATORIxDEMO() == null || a6.ALLATORIxDEMO() == null) {
            return;
        }
        ut a7 = a6.ALLATORIxDEMO();
        Map<String, kq> a8 = a6.ALLATORIxDEMO();
        xz a9 = new xz(a2, a7, a8);
        LinkedHashMap<String, so> a10 = new LinkedHashMap<String, so>();
        a10.put("base", new so("base", rz.y, 1.0f));
        for (hp hp2 : a7.ALLATORIxDEMO()) {
            a10.remove(hp2.f());
            a10.put(hp2.f(), new so(hp2.f(), rz.ALLATORIxDEMO(hp2.c()), hp2.ALLATORIxDEMO(), a9.ALLATORIxDEMO(hp2.ALLATORIxDEMO()), hp2.ALLATORIxDEMO(), hp2.ALLATORIxDEMO()));
            for (String a12 : hp2.ALLATORIxDEMO()) {
                a9.v.put(a12, hp2.f());
            }
        }
        a9.ALLATORIxDEMO(a10);
        for (Map.Entry entry : a7.ALLATORIxDEMO().entrySet()) {
            a9.c((String)entry.getKey(), (String)entry.getValue());
        }
        a9.c = a7.ALLATORIxDEMO();
        a9.t = a4.getEntityName(a2);
        a9.r = a2.func_110124_au();
        a9.ALLATORIxDEMO(bs.b, new String[0]);
        a4.ALLATORIxDEMO.put((Object)a2.func_110124_au(), (Object)a9);
    }

    public String getEntityName(EntityLivingBase a2) {
        raa a3 = raa.r;
        String a4 = a3.c(a2);
        if (a3.ALLATORIxDEMO().containsKey(a2.func_110124_au())) {
            a4 = a3.ALLATORIxDEMO().get(a2.func_110124_au());
        }
        return a4;
    }

    public xz getEntityManager(UUID a2) {
        dt a3;
        return (xz)a3.ALLATORIxDEMO.getIfPresent((Object)a2);
    }

    public void startAnimation(xz a2, String a3, int a4, float a5) {
        String a6;
        Map.Entry<String, kq> a7;
        kq a8;
        dt a9;
        if (a5 == 0.0f) {
            a5 = 1.0f;
        }
        if (a2.ALLATORIxDEMO(bs.g, a3, String.valueOf(a4), String.valueOf(a5))) {
            return;
        }
        Map.Entry<String, kq> a10 = a2.ALLATORIxDEMO(a3);
        kq a11 = a10.getValue();
        a3 = a10.getKey();
        if (a11 == null) {
            return;
        }
        st a12 = new st(a11);
        a12.ALLATORIxDEMO(a4);
        a12.ALLATORIxDEMO(a5);
        so a13 = a9.getAnimationLayer(a2, a3);
        if (a13.ALLATORIxDEMO() != null && (a8 = (a7 = a2.ALLATORIxDEMO(a6 = a13.ALLATORIxDEMO().c())).getValue()) != null) {
            st a14 = new st(a8);
            a14.ALLATORIxDEMO(a13.ALLATORIxDEMO());
            a12.ALLATORIxDEMO(a14);
        }
        a2.ALLATORIxDEMO(a12, a13.ALLATORIxDEMO());
    }

    public void stopAnimation(xz a2, String a3, int a4) {
        dt a5;
        Map.Entry<String, kq> a6 = a2.ALLATORIxDEMO(a3);
        if (a6.getValue() == null) {
            return;
        }
        a3 = a6.getKey();
        if (a2.ALLATORIxDEMO(bs.t, a3, String.valueOf(a4))) {
            return;
        }
        Map.Entry<String, kq> a7 = a2.ALLATORIxDEMO(a3);
        so a8 = a5.getAnimationLayer(a2, a3 = a7.getKey());
        if (a5.isPlayingAnimation(a8, a3)) {
            a2.ALLATORIxDEMO(a8.ALLATORIxDEMO(), a4);
        }
    }

    public String getPlayingAnimation(UUID a2, String a3) {
        xr a4;
        so a5;
        dt a6;
        xz a7 = (xz)a6.ALLATORIxDEMO.getIfPresent((Object)a2);
        if (a7 != null && (a5 = a7.ALLATORIxDEMO(a3)) != null && (a4 = a5.ALLATORIxDEMO()) != null) {
            if (a4 instanceof hr) {
                return ((hr)a4).c().ALLATORIxDEMO();
            }
            return a4.ALLATORIxDEMO().ALLATORIxDEMO();
        }
        return "";
    }

    public boolean isPlayingAnimation(xz a2, String a3) {
        dt a4;
        a3 = a2.ALLATORIxDEMO(a3).getKey();
        so a5 = a4.getAnimationLayer(a2, a3);
        return a4.isPlayingAnimation(a5, a3);
    }

    public boolean isPlayingAnimation(so a2, String a3) {
        xr a4 = a2.ALLATORIxDEMO();
        if (a4 != null) {
            if (a4.ALLATORIxDEMO() == null) {
                return false;
            }
            if (a4.ALLATORIxDEMO().ALLATORIxDEMO() || !a4.ALLATORIxDEMO(System.currentTimeMillis())) {
                if (a4 instanceof hr) {
                    hr a5 = (hr)a4;
                    kq a6 = a5.c();
                    return a6 != null && a6.ALLATORIxDEMO().contains(a3);
                }
                return a4.ALLATORIxDEMO().ALLATORIxDEMO().contains(a3);
            }
        }
        return false;
    }

    public so getAnimationLayer(xz a2, String a3) {
        String a4 = a2.f().get(a3);
        if (a4 == null) {
            a4 = "base";
        }
        return a2.ALLATORIxDEMO(a4);
    }
}

