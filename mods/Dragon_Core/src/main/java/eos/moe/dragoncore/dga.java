/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.event.world.WorldEvent$Unload
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.EntityList;
import eos.moe.dragoncore.bs;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.hp;
import eos.moe.dragoncore.ht;
import eos.moe.dragoncore.jz;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.rz;
import eos.moe.dragoncore.so;
import eos.moe.dragoncore.ut;
import eos.moe.dragoncore.xz;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

@Mod.EventBusSubscriber(modid="dragoncore")
public class dga {
    public static dga y = new dga();
    public final Map<String, kq> k = new ConcurrentHashMap<String, kq>();
    public final Map<UUID, xz> ALLATORIxDEMO = new ConcurrentHashMap<UUID, xz>();

    public dga() {
        dga a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(RenderLivingEvent.Pre<? extends EntityLivingBase> a2) {
        xz a3;
        EntityLivingBase a4 = a2.getEntity();
        if (a4 instanceof EntityPlayer && (a3 = y.ALLATORIxDEMO(a4.func_110124_au())) != null) {
            jz.ALLATORIxDEMO(a3, a2.getPartialRenderTick());
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START && Minecraft.func_71410_x().field_71441_e != null) {
            for (Entity a3 : Minecraft.func_71410_x().field_71441_e.func_72910_y()) {
                EntityPlayer a4;
                xz a5;
                if (!(a3 instanceof EntityPlayer) || (a5 = y.ALLATORIxDEMO((a4 = (EntityPlayer)a3).func_110124_au())) == null) continue;
                a5.ALLATORIxDEMO(bs.c, new String[0]);
            }
        }
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(WorldEvent.Unload a2) {
        dga.y.ALLATORIxDEMO.clear();
    }

    public xz ALLATORIxDEMO(UUID a2) {
        dga a3;
        if (a3.k.isEmpty()) {
            return null;
        }
        Entity a4 = EntityList.getEntityByUUID(a2);
        if (!(a4 instanceof EntityPlayer)) {
            return null;
        }
        EntityPlayer a5 = (EntityPlayer)a4;
        xz a6 = a3.ALLATORIxDEMO.get(a2);
        if (a6 == null) {
            YamlConfiguration a7 = new YamlConfiguration();
            a7.createSection("Layer").createSection("base");
            a3.ALLATORIxDEMO(a5, a7);
        }
        return a6;
    }

    public void ALLATORIxDEMO(String a2, byte[] a3) {
        dga a4;
        Map<String, kq> a5 = ht.ALLATORIxDEMO(a2, new ByteArrayInputStream(a3));
        a4.k.putAll(a5);
        HashMap<EntityPlayer, ut> a6 = new HashMap<EntityPlayer, ut>();
        for (Map.Entry<UUID, xz> entry : a4.ALLATORIxDEMO.entrySet()) {
            Entity a8 = EntityList.getEntityByUUID(entry.getKey());
            if (!(a8 instanceof EntityPlayer)) continue;
            a6.put((EntityPlayer)a8, entry.getValue().y);
        }
        a4.ALLATORIxDEMO.clear();
        for (Map.Entry<UUID, xz> entry : a6.entrySet()) {
            a4.ALLATORIxDEMO((EntityPlayer)entry.getKey(), (ut)((Object)entry.getValue()));
        }
    }

    public void ALLATORIxDEMO(EntityPlayer a2, YamlConfiguration a3) {
        dga a4;
        ut a5 = ut.ALLATORIxDEMO(a3);
        a4.ALLATORIxDEMO(a2, a5);
    }

    private /* synthetic */ void ALLATORIxDEMO(EntityPlayer a2, ut a3) {
        dga a4;
        xz a5 = new xz((EntityLivingBase)a2, a3, a4.k);
        LinkedHashMap<String, so> a6 = new LinkedHashMap<String, so>();
        so a7 = new so("base", rz.y, 1.0f);
        a7.ALLATORIxDEMO(true);
        a6.put("base", a7);
        for (hp hp2 : a3.ALLATORIxDEMO()) {
            a6.remove(hp2.f());
            a6.put(hp2.f(), new so(hp2.f(), rz.ALLATORIxDEMO(hp2.c()), hp2.ALLATORIxDEMO(), a5.ALLATORIxDEMO(hp2.ALLATORIxDEMO()), hp2.ALLATORIxDEMO(), hp2.ALLATORIxDEMO()));
            for (String a9 : hp2.ALLATORIxDEMO()) {
                a5.v.put(a9, hp2.f());
            }
        }
        a5.ALLATORIxDEMO(a6);
        for (Map.Entry entry : a3.ALLATORIxDEMO().entrySet()) {
            a5.c((String)entry.getKey(), (String)entry.getValue());
        }
        a5.t = "";
        a5.r = a2.func_110124_au();
        a5.ALLATORIxDEMO(bs.b, new String[0]);
        a4.ALLATORIxDEMO.put(a2.func_110124_au(), a5);
    }

    public void c(UUID a2, String a3) {
        dga a4;
        xz a5 = a4.ALLATORIxDEMO(a2);
        if (a5 == null) {
            return;
        }
        a5.c(a3);
    }

    public void ALLATORIxDEMO(UUID a2, String a3, float a4) {
        dga a5;
        kq a6 = a5.k.get(a3);
        if (a6 == null) {
            return;
        }
        xz a7 = a5.ALLATORIxDEMO(a2);
        if (a7 == null) {
            return;
        }
        dt.k.startAnimation(a7, a3, 0, a4);
    }

    public void ALLATORIxDEMO(UUID a2, String a3) {
        dga a4;
        xz a5 = a4.ALLATORIxDEMO(a2);
        if (a5 == null) {
            return;
        }
        if (a3.equals("all") || a3.isEmpty()) {
            for (String a6 : a5.ALLATORIxDEMO()) {
                a5.ALLATORIxDEMO(a6, 0);
            }
        } else {
            dt.k.stopAnimation(a5, a3, 0);
        }
    }
}

