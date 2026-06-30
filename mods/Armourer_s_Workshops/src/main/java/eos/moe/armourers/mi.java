/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Type
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.logging.log4j.Level
 */
package eos.moe.armourers;

import eos.moe.armourers.bm;
import eos.moe.armourers.en;
import eos.moe.armourers.f;
import eos.moe.armourers.ln;
import eos.moe.armourers.vk;
import eos.moe.armourers.yl;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.logging.log4j.Level;

@SideOnly(value=Side.CLIENT)
public class mi {
    private AtomicIntegerArray r;
    private CompletionService<en> l;
    private Executor c;
    public static mi v = new mi();
    private Executor s;
    private AtomicInteger m;
    private AtomicInteger j;

    public mi() {
        mi a2;
        mi mi2 = a2;
        mi mi3 = a2;
        a2.m = new AtomicInteger(0);
        mi3.r = new AtomicIntegerArray(1000);
        a2.j = new AtomicInteger(0);
        mi2.c = Executors.newFixedThreadPool(vk.q);
        mi2.s = Executors.newFixedThreadPool(2);
        mi2.l = new ExecutorCompletionService<en>(a2.c);
        FMLCommonHandler.instance().bus().register((Object)a2);
    }

    public static /* synthetic */ AtomicIntegerArray r(mi a2) {
        return a2.r;
    }

    public static /* synthetic */ AtomicInteger r(mi a2) {
        return a2.j;
    }

    public void r(yl a2, String a3, f a4) {
        mi a5;
        a5.m.incrementAndGet();
        a5.l.submit(new ln(a5, a2, a3, a4));
    }

    public int y() {
        mi a2;
        return a2.m.get();
    }

    private /* synthetic */ void r() {
        mi a2;
        Future<en> future;
        Future<en> future2 = future = a2.l.poll();
        while (future2 != null) {
            try {
                en en2 = future.get();
                if (en2 != null) {
                    a2.m.decrementAndGet();
                    if (en.r(en2) == null) {
                        bm.r(Level.ERROR, new StringBuilder().insert(0, "A skin ").append(en.r(en2)).append(" failed to bake.").toString());
                    }
                    en2.r().onBakedSkin(en2);
                }
            }
            catch (Exception exception) {
                exception.printStackTrace();
            }
            future2 = a2.l.poll();
        }
    }

    @SubscribeEvent
    public void r(TickEvent.ClientTickEvent a2) {
        TickEvent.ClientTickEvent clientTickEvent;
        boolean bl;
        if (a2.side == Side.CLIENT) {
            bl = true;
            clientTickEvent = a2;
        } else {
            bl = false;
            clientTickEvent = a2;
        }
        if (bl & clientTickEvent.type == TickEvent.Type.CLIENT & a2.phase == TickEvent.Phase.END) {
            mi a3;
            a3.r();
        }
    }

    public int r() {
        mi a2;
        int n2;
        int n3 = 0;
        int n4 = 0;
        int n5 = n2 = 0;
        while (n5 < a2.r.length()) {
            int n6 = a2.r.get(n2);
            if (n6 != 0) {
                ++n3;
                n4 += n6;
            }
            n5 = ++n2;
        }
        return (int)((double)n4 / (double)n3);
    }

    public void r(Thread a2) {
        mi a3;
        a2.setPriority(1);
        a3.s.execute(a2);
    }
}

