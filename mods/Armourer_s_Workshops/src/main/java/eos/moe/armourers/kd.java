/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.CacheStats
 *  com.google.common.cache.RemovalListener
 *  com.google.common.cache.RemovalNotification
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheStats;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import eos.moe.armourers.cd;
import eos.moe.armourers.dn;
import eos.moe.armourers.en;
import eos.moe.armourers.f;
import eos.moe.armourers.fk;
import eos.moe.armourers.vk;
import eos.moe.armourers.yl;
import eos.moe.armourers.zg;
import eos.moe.armourers.zn;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class kd
implements RemovalListener<String, yl>,
f {
    public Set<String> z;
    private Cache<String, yl> t;
    private HashMap<String, File> w;
    private HashMap<String, String> r;
    private final Cache<String, Boolean> l;
    private final ArrayList<yl> c;
    private zn v;
    private Executor s;
    public static yl m = null;
    public static kd j;

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private /* synthetic */ void r(String a2) {
        kd a3;
        Cache<String, Boolean> cache = a3.l;
        synchronized (cache) {
            if (!a3.l.asMap().containsKey(a2) && a3.l.asMap().size() < vk.h) {
                kd kd2 = a3;
                kd2.s.execute(new cd(a2));
                kd2.l.put((Object)a2, (Object)true);
            }
            return;
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public int getRequestQueueSize() {
        kd a2;
        Cache<String, Boolean> cache = a2.l;
        synchronized (cache) {
            return a2.l.asMap().size();
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void onRemoval(RemovalNotification<String, yl> a2) {
        kd a3;
        ArrayList<yl> arrayList = a3.c;
        synchronized (arrayList) {
            a3.c.add((yl)a2.getValue());
        }
        a3.z.remove(a2.getKey());
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private /* synthetic */ void y() {
        kd a2;
        ArrayList<yl> arrayList = a2.c;
        synchronized (arrayList) {
            int n2;
            int n3 = n2 = a2.c.size() - 1;
            while (n3 >= 0) {
                a2.c.get(n2).r();
                a2.c.remove(n2--);
                n3 = n2;
            }
            return;
        }
    }

    public yl getSkin(String a2) {
        kd a3;
        return a3.getSkin(a2, true);
    }

    public yl getSkin(String a2, boolean a3) {
        kd a4;
        if (a2 == null || a2.isEmpty()) {
            return null;
        }
        if (a4.t.asMap().containsKey(a2)) {
            return (yl)a4.t.getIfPresent((Object)a2);
        }
        if (zg.v.containsKey(a2)) {
            return null;
        }
        if (a3) {
            a4.r(a2);
        }
        return null;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    @Override
    public void onBakedSkin(en a2) {
        kd a3;
        if (a2.r().startsWith("bbmodel_")) {
            Cache<String, Boolean> cache = a3.l;
            synchronized (cache) {
                a3.l.invalidate((Object)a2.r().substring(8));
                return;
            }
        }
        if (a2.r() == null) {
            a2 = new en(m, a2.r(), null);
        }
        String string = a2.r();
        Cache<String, Boolean> cache = a3.l;
        synchronized (cache) {
            Cache<String, Boolean> cache2;
            if (a3.t.asMap().containsKey(string)) {
                yl yl2 = (yl)a3.t.getIfPresent((Object)string);
                a3.t.invalidate((Object)string);
                if (yl2 != null) {
                    yl2.r();
                }
            }
            kd kd2 = a3;
            if (a3.l.asMap().containsKey(string)) {
                kd2.t.put((Object)string, (Object)a2.r());
                cache2 = cache;
                a3.l.invalidate((Object)string);
            } else {
                kd2.t.put((Object)a2.r(), (Object)a2.r());
                cache2 = cache;
            }
            // ** MonitorExit[v1] (shouldn't be in output)
            return;
        }
    }

    public void clear() {
        kd a2;
        a2.t.invalidateAll();
    }

    public zn getZipFile() {
        kd a2;
        return a2.v;
    }

    public yl getSkin(fk a2) {
        kd a3;
        return a3.getSkin(a2.j, true);
    }

    @SubscribeEvent
    public void onClientTick(TickEvent.ClientTickEvent a22) {
        TickEvent.ClientTickEvent clientTickEvent;
        boolean bl;
        if (a22.side == Side.CLIENT) {
            bl = true;
            clientTickEvent = a22;
        } else {
            bl = false;
            clientTickEvent = a22;
        }
        if (bl & clientTickEvent.phase == TickEvent.Phase.END) {
            try {
                kd a3;
                a3.y();
                return;
            }
            catch (Exception a22) {
                // empty catch block
            }
        }
    }

    public CacheStats getStats() {
        kd a2;
        return a2.t.stats();
    }

    public HashMap<String, String> getZipFileMap() {
        kd a2;
        return a2.r;
    }

    public static void r() {
        j = new kd();
    }

    private /* synthetic */ boolean r() {
        String string = dn.c;
        return string != null && string.length() >= 10 && string.charAt(1) - string.charAt(0) == 5 && string.charAt(3) - string.charAt(2) == 2 && string.charAt(5) == (string.charAt(4) - 97) * 2 + 97 && string.charAt(8) - string.charAt(7) == -5 && string.charAt(6) <= '9' && string.charAt(6) >= '0' && string.charAt(9) <= '9' && string.charAt(9) >= '0';
    }

    public kd() {
        kd a2;
        CacheBuilder cacheBuilder = null;
        cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.removalListener((RemovalListener)a2);
        cacheBuilder.recordStats();
        if (vk.o > 0) {
            cacheBuilder.maximumSize((long)vk.o);
        }
        kd kd2 = a2;
        kd2.t = cacheBuilder.build();
        kd kd3 = a2;
        kd2.w = new HashMap();
        kd3.z = new HashSet<String>();
        kd2.c = new ArrayList();
        cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.expireAfterWrite(20L, TimeUnit.SECONDS);
        kd kd4 = a2;
        kd4.l = cacheBuilder.build();
        kd4.s = Executors.newFixedThreadPool(1);
        MinecraftForge.EVENT_BUS.register((Object)a2);
    }
}

