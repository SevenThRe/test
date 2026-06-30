/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.block.Block
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.renderer.RenderGlobal
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.IBlockAccess
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.eka;
import eos.moe.dragoncore.ha;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod(modid="dynamiclights_dragoncore", name="Dynamic Lights DragonCore", version="1.4.9", clientSideOnly=true, dependencies="required-after:forge@[14.23.3.2698,)", acceptedMinecraftVersions="[1.12.2]")
public class jja {
    private Minecraft q;
    @Mod.Instance(value="dynamiclights_dragoncore")
    private static jja b;
    private IBlockAccess o;
    private ConcurrentLinkedQueue<eka> y;
    private ConcurrentHashMap<World, ConcurrentLinkedQueue<eka>> k;
    private static boolean ALLATORIxDEMO;

    public jja() {
        jja a2;
    }

    @Mod.EventHandler
    public void ALLATORIxDEMO(FMLPreInitializationEvent a2) {
        jja a3;
        a3.q = FMLClientHandler.instance().getClient();
        a3.k = new ConcurrentHashMap();
        MinecraftForge.EVENT_BUS.register((Object)a3);
        ALLATORIxDEMO = false;
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        ConcurrentLinkedQueue<eka> a3;
        jja a4;
        if (!ca.q) {
            return;
        }
        if (a2.phase == TickEvent.Phase.END && a4.q.world != null && (a3 = a4.k.get(a4.q.world)) != null) {
            Iterator<eka> a5 = a3.iterator();
            while (a5.hasNext()) {
                eka a6 = a5.next();
                if (!a6.ALLATORIxDEMO()) continue;
                a5.remove();
                a4.q.world.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(a6.f(), a6.c(), a6.ALLATORIxDEMO()));
            }
        }
    }

    public static int ALLATORIxDEMO(Block a2, IBlockState a3, IBlockAccess a4, BlockPos a5) {
        int a6 = a2.getLightValue(a3, a4, a5);
        if (!ca.q) {
            return a6;
        }
        if (b == null || !(a4 instanceof WorldClient)) {
            return a6;
        }
        if (!a4.equals(jja.b.o) || jja.b.y == null) {
            jja.b.o = a4;
            jja.b.y = jja.b.k.get(a4);
            jja.ALLATORIxDEMO();
        }
        int a7 = 0;
        if (jja.b.y != null && !jja.b.y.isEmpty()) {
            for (eka a8 : jja.b.y) {
                if (a8.f() != a5.getX() || a8.c() != a5.getY() || a8.ALLATORIxDEMO() != a5.getZ()) continue;
                a7 = Math.max(a7, a8.ALLATORIxDEMO().ALLATORIxDEMO());
            }
        }
        return Math.max(a6, a7);
    }

    private static /* synthetic */ void ALLATORIxDEMO() {
        if (ALLATORIxDEMO) {
            return;
        }
        for (Field a2 : RenderGlobal.class.getDeclaredFields()) {
            ParameterizedType a3;
            if (!Set.class.isAssignableFrom(a2.getType()) || !BlockPos.class.equals((Object)(a3 = (ParameterizedType)a2.getGenericType()).getActualTypeArguments()[0])) continue;
            try {
                a2.setAccessible(true);
                Set a4 = (Set)a2.get(jja.b.q.renderGlobal);
                if (a4 instanceof ConcurrentSkipListSet) {
                    return;
                }
                ConcurrentSkipListSet a5 = new ConcurrentSkipListSet(a4);
                a2.set(jja.b.q.renderGlobal, a5);
                System.out.println("Dynamic Lights successfully hacked Set RenderGlobal.setLightUpdates and replaced it with a ConcurrentSkipListSet!");
                return;
            }
            catch (Exception a6) {
                a6.printStackTrace();
            }
        }
        System.out.println("Dynamic Lights completely failed to hack Set RenderGlobal.setLightUpdates and will not try again!");
        ALLATORIxDEMO = true;
    }

    public static void c(ha a2) {
        if (a2.ALLATORIxDEMO() != null) {
            if (a2.ALLATORIxDEMO().isEntityAlive()) {
                eka a3 = new eka(a2);
                ConcurrentLinkedQueue<eka> a4 = jja.b.k.get(a2.ALLATORIxDEMO().world);
                if (a4 != null) {
                    if (!a4.contains(a3)) {
                        a4.add(a3);
                    } else {
                        System.out.println("Cannot add Dynamic Light: Attachment Entity is already registered!");
                    }
                } else {
                    a4 = new ConcurrentLinkedQueue();
                    a4.add(a3);
                    jja.b.k.put(a2.ALLATORIxDEMO().world, a4);
                }
            } else {
                System.err.println("Cannot add Dynamic Light: Attachment Entity is dead or in a banned dimension!");
            }
        } else {
            System.err.println("Cannot add Dynamic Light: Attachment Entity is null!");
        }
    }

    public static void ALLATORIxDEMO(ha a2) {
        World a3;
        if (a2 != null && a2.ALLATORIxDEMO() != null && (a3 = a2.ALLATORIxDEMO().world) != null) {
            eka a4 = null;
            ConcurrentLinkedQueue<eka> a5 = jja.b.k.get(a3);
            if (a5 != null) {
                Iterator<eka> a6 = a5.iterator();
                while (a6.hasNext()) {
                    a4 = a6.next();
                    if (!a4.ALLATORIxDEMO().equals(a2)) continue;
                    a6.remove();
                    break;
                }
                if (a4 != null) {
                    a3.checkLightFor(EnumSkyBlock.BLOCK, new BlockPos(a4.f(), a4.c(), a4.ALLATORIxDEMO()));
                }
            }
        }
    }
}

