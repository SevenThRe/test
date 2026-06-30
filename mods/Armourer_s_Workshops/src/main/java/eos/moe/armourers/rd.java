/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.cache.Cache
 *  com.google.common.cache.CacheBuilder
 *  com.google.common.cache.RemovalListener
 *  com.google.common.cache.RemovalNotification
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.common.FMLCommonHandler
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import eos.moe.armourers.di;
import eos.moe.armourers.n;
import eos.moe.armourers.oh;
import eos.moe.armourers.ti;
import eos.moe.armourers.vk;
import eos.moe.armourers.wn;
import eos.moe.armourers.yi;
import eos.moe.armourers.yl;
import java.util.AbstractCollection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class rd
implements RemovalListener<yi, ti>,
Runnable {
    private ArrayList<di> c;
    public static rd v = new rd();
    private volatile Thread s;
    private Cache<yi, ti> m;
    private HashSet<di> j;

    private /* synthetic */ void r(ti a2) {
        if (a2 != null) {
            a2.deleteGlTexture();
        }
    }

    public ti getTextureForSkin(yl a2, n a3, oh a4) {
        rd a5;
        if (a4 == null) {
            a4 = oh.l;
        }
        a3 = new yi(a2.r(), (n)a3, a4);
        return a5.getTextureForSkin(a2, (yi)a3);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     */
    public ti getTextureForSkin(yl a2, yi a3) {
        rd a4;
        Object object = (ti)((Object)a4.m.getIfPresent(a3));
        if (object != null) {
            return object;
        }
        a3 = new di(a4, a2, (yi)a3);
        object = a4.j;
        synchronized (object) {
            Object object2;
            if (!a4.j.contains(a3)) {
                rd rd2 = a4;
                rd2.j.add((di)a3);
                ArrayList<di> arrayList = rd2.c;
                synchronized (arrayList) {
                    a4.c.add((di)a3);
                }
                object2 = object;
            } else {
                object2 = object;
            }
            // ** MonitorExit[v1 /* !! */ ] (shouldn't be in output)
            return a2.w;
        }
    }

    public void finalize() throws Throwable {
        rd a2;
        a2.s = null;
        super.finalize();
    }

    @Override
    public void run() {
        rd a2;
        Thread thread = Thread.currentThread();
        rd rd2 = a2;
        while (rd2.s == thread) {
            try {
                Thread.sleep(100L);
                a2.r();
                rd2 = a2;
            }
            catch (InterruptedException interruptedException) {
                rd2 = a2;
                interruptedException.printStackTrace();
            }
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    private /* synthetic */ void r() {
        rd a2;
        ti ti2 = null;
        di di2 = null;
        AbstractCollection abstractCollection = a2.c;
        synchronized (abstractCollection) {
            if (a2.c.size() > 0) {
                rd rd2 = a2;
                di2 = rd2.c.get(rd2.c.size() - 1);
                rd rd3 = a2;
                rd3.c.remove(rd3.c.size() - 1);
                ti2 = new ti();
                di di3 = di2;
                ti2.createTextureForColours(di3.m, di3.j);
            }
        }
        if (ti2 != null && di2 != null) {
            rd rd4 = a2;
            rd4.m.put((Object)di2.j, ti2);
            abstractCollection = rd4.j;
            synchronized (abstractCollection) {
                a2.j.remove(di2);
                return;
            }
        }
    }

    public int size() {
        rd a2;
        rd rd2 = a2;
        rd2.m.cleanUp();
        return (int)rd2.m.size();
    }

    public rd() {
        rd a2;
        CacheBuilder cacheBuilder = CacheBuilder.newBuilder();
        cacheBuilder.removalListener((RemovalListener)a2);
        if (vk.x > 0) {
            cacheBuilder.expireAfterAccess((long)vk.x, TimeUnit.SECONDS);
        }
        if (vk.e > 0) {
            cacheBuilder.maximumSize((long)vk.e);
        }
        rd rd2 = a2;
        rd2.m = cacheBuilder.build();
        rd rd3 = a2;
        rd2.j = new HashSet();
        rd3.c = new ArrayList();
        rd2.s = new Thread((Runnable)a2, "Texture Gen Thread");
        rd2.s.setPriority(1);
        rd2.s.start();
        FMLCommonHandler.instance().bus().register((Object)a2);
    }

    public void onRemoval(RemovalNotification<yi, ti> a2) {
        rd a3;
        Minecraft.getMinecraft().addScheduledTask((Runnable)new wn(a3, a2));
    }

    public void clear() {
        rd a2;
        a2.m.invalidateAll();
    }

    public static /* synthetic */ void r(rd a2, ti a3) {
        a2.r(a3);
    }
}

