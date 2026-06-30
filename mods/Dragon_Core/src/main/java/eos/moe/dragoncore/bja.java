/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.qaa;
import eos.moe.dragoncore.rba;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid="dynamiclights_onglowname_dragoncore", name="Dynamic Lights on glow name", version="1.0.0", dependencies="required-after:dynamiclights_dragoncore", acceptedMinecraftVersions="[1.12.2]")
public class bja {
    private Minecraft b;
    private long o;
    private long y;
    private Map<UUID, rba> k;
    private boolean ALLATORIxDEMO;

    public bja() {
        bja a2;
    }

    @Mod.EventHandler
    public void ALLATORIxDEMO(FMLPreInitializationEvent a2) {
        bja a3;
        a3.y = 500L;
        MinecraftForge.EVENT_BUS.register((Object)a3);
    }

    @Mod.EventHandler
    public void ALLATORIxDEMO(FMLInitializationEvent a2) {
        a.b = FMLClientHandler.instance().getClient();
        a.o = System.currentTimeMillis();
        a.k = new HashMap<UUID, rba>();
        a.ALLATORIxDEMO = false;
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        bja a3;
        if (!ca.q) {
            return;
        }
        if (a3.b.world != null && System.currentTimeMillis() > a3.o) {
            a3.o = System.currentTimeMillis() + a3.y;
            if (!a3.ALLATORIxDEMO) {
                qaa a4 = new qaa(a3, a3.b.world.loadedEntityList);
                a4.setPriority(1);
                a4.start();
                a3.ALLATORIxDEMO = true;
            }
        }
    }

    private static /* synthetic */ int c(Entity a2) {
        String a3 = a2.getName();
        if (a3.equals("glow-block")) {
            return 15;
        }
        return 0;
    }

    public static /* synthetic */ Map ALLATORIxDEMO(bja a2) {
        return a2.k;
    }

    public static /* synthetic */ int ALLATORIxDEMO(Entity a2) {
        return bja.c(a2);
    }

    public static /* synthetic */ boolean ALLATORIxDEMO(bja a2, boolean a3) {
        a2.ALLATORIxDEMO = a3;
        return a2.ALLATORIxDEMO;
    }
}

