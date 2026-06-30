/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ax;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.ct;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.dx;
import eos.moe.dragoncore.en;
import eos.moe.dragoncore.gga;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.ht;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.ms;
import eos.moe.dragoncore.mt;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.pq;
import eos.moe.dragoncore.pw;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.sw;
import eos.moe.dragoncore.tt;
import eos.moe.dragoncore.ut;
import eos.moe.dragoncore.xu;
import eos.moe.dragoncore.zt;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class raa {
    public static raa r = new raa();
    public static nd x = new nd();
    private final ConcurrentHashMap<UUID, String> v = new ConcurrentHashMap();
    public final ConcurrentHashMap<String, rda> m = new ConcurrentHashMap();
    private final ConcurrentHashMap<String, gga> c = new ConcurrentHashMap();
    private final ConcurrentHashMap<String, gga> q = new ConcurrentHashMap();
    private final ConcurrentHashMap<String, gga> b = new ConcurrentHashMap();
    public final Map<String, rda> o = new HashMap<String, rda>();
    private final gga y = new gga(ms.ALLATORIxDEMO(), null);
    private final ExecutorService k = Executors.newCachedThreadPool();
    private Map<Class<?>, String> ALLATORIxDEMO = new HashMap();

    public raa() {
        raa a2;
    }

    public ConcurrentHashMap<UUID, String> ALLATORIxDEMO() {
        raa a2;
        return a2.v;
    }

    public void c() {
        raa a2;
        dt.k.ALLATORIxDEMO.invalidateAll();
        a2.c.clear();
        a2.q.clear();
        a2.b.clear();
        a2.o.clear();
    }

    public void ALLATORIxDEMO() {
        raa a2;
        a2.k.shutdownNow();
    }

    public rda c(EntityLivingBase a2) {
        String a3;
        raa a4;
        rda a5 = a4.ALLATORIxDEMO(a2);
        if (a5 != null && a5.f() && !a4.v.containsKey(a2.getUniqueID()) && !(a3 = a4.ALLATORIxDEMO(a2)).contains("\u00a7")) {
            a5 = null;
        }
        return a5;
    }

    public rda ALLATORIxDEMO(EntityLivingBase a2) {
        raa a3;
        String a4 = a3.ALLATORIxDEMO.get(a2.getClass());
        if (a4 == null) {
            a4 = EntityList.getEntityString((Entity)a2);
            a4 = a4 == null ? "unknown" : a4.toLowerCase(Locale.ROOT);
            a3.ALLATORIxDEMO.put(a2.getClass(), a4);
        }
        String a5 = a3.c(a2);
        if (a3.v.containsKey(a2.getUniqueID())) {
            a5 = a3.v.get(a2.getUniqueID());
        }
        if (a3.o.containsKey(a5)) {
            return a3.o.get(a5);
        }
        for (Map.Entry<String, rda> a6 : a3.m.entrySet()) {
            rda a7 = a6.getValue();
            if (!a7.x() || !a5.contains(a7.c()) || !rda.ALLATORIxDEMO(a7).isEmpty() && !rda.ALLATORIxDEMO(a7).contains(a4)) continue;
            a3.o.put(a5, a6.getValue());
            return a6.getValue();
        }
        rda a8 = a3.m.get(a5);
        if (a8 != null && (rda.ALLATORIxDEMO(a8).isEmpty() || rda.ALLATORIxDEMO(a8).contains(a4))) {
            a3.o.put(a5, a8);
            return a8;
        }
        a3.o.put(a5, null);
        return null;
    }

    public String c(EntityLivingBase a2) {
        return en.ALLATORIxDEMO(a2.getName().replace(" ", ""));
    }

    public String ALLATORIxDEMO(EntityLivingBase a2) {
        return a2.getName().replace(" ", "");
    }

    public gga f(rda a2) {
        ResourceLocation a3;
        raa a4;
        String a5 = a2.c();
        if (a4.c.containsKey(a5)) {
            return a4.c.get(a5);
        }
        ResourceLocation a6 = a2.c();
        ResourceLocation a7 = a2.f();
        if (a6.getPath().endsWith(".bbmodel")) {
            try {
                a3 = Minecraft.getMinecraft().getResourceManager().getResource(a6);
                Throwable throwable = null;
                try {
                    dx a8 = ax.ALLATORIxDEMO(a3.getInputStream());
                    pq.ALLATORIxDEMO(a6.getPath() + "/model.json", a8.c());
                    pq.ALLATORIxDEMO(a6.getPath() + "/animation.json", a8.ALLATORIxDEMO());
                    for (Map.Entry<String, byte[]> a9 : a8.ALLATORIxDEMO().entrySet()) {
                        pq.ALLATORIxDEMO(a6.getPath() + "/" + a9.getKey(), a9.getValue());
                    }
                    a7 = new ResourceLocation(a6.getNamespace(), a6.getPath() + "/animation.json");
                    a6 = new ResourceLocation(a6.getNamespace(), a6.getPath() + "/model.json");
                }
                catch (Throwable throwable2) {
                    throwable = throwable2;
                    throw throwable2;
                }
                finally {
                    if (a3 != null) {
                        if (throwable != null) {
                            try {
                                a3.close();
                            }
                            catch (Throwable throwable3) {
                                throwable.addSuppressed(throwable3);
                            }
                        } else {
                            a3.close();
                        }
                    }
                }
            }
            catch (Exception a10) {
                a10.printStackTrace();
                ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u6a21\u578b: " + a5 + "  \u9519\u8bef\u539f\u56e0\u5df2\u8f93\u51fa\u81f3\u5ba2\u6237\u7aeflog\u5185");
                a4.c.put(a5, a4.y);
                return a4.y;
            }
        }
        a3 = a6;
        ResourceLocation a11 = a7;
        a4.k.submit(() -> {
            raa a6;
            void var7_12;
            pw a8 = ms.ALLATORIxDEMO(a3);
            if (a8.getBaseModel() == ms.ALLATORIxDEMO.get(0)) {
                ca.l.z("\u65e0\u6cd5\u52a0\u8f7d\u6a21\u578b: " + a5 + "  \u9519\u8bef\u539f\u56e0\u5df2\u8f93\u51fa\u81f3\u5ba2\u6237\u7aeflog\u5185");
            }
            for (mt mt2 : a8.getBaseModel().getPieceMap().values()) {
                for (xu a9 : mt2.c) {
                    a9.ALLATORIxDEMO(a2.c().getPath());
                }
            }
            Map<String, kq> a10 = null;
            if (a2.f() != null) {
                try {
                    a10 = ht.ALLATORIxDEMO(a11);
                }
                catch (Exception exception) {
                    ca.l.w("\u65e0\u6cd5\u52a0\u8f7d\u6a21\u578b\u52a8\u753b: " + a5 + "  \u9519\u8bef\u539f\u56e0\u5df2\u8f93\u51fa\u81f3\u5ba2\u6237\u7aeflog\u5185");
                }
            }
            Object var7_10 = null;
            try {
                ut ut2 = sw.ALLATORIxDEMO(a2.d());
            }
            catch (Exception a12) {
                a12.printStackTrace();
                ca.l.w("\u65e0\u6cd5\u52a0\u8f7d\u6a21\u578b\u52a8\u753b\u63a7\u5236\u5668: " + a5 + "  \u9519\u8bef\u539f\u56e0\u5df2\u8f93\u51fa\u81f3\u5ba2\u6237\u7aeflog\u5185");
            }
            gga a13 = new gga(a8, a10, (ut)var7_12);
            a6.c.put(a5, a13);
        });
        a4.c.put(a5, a4.y);
        return a4.y;
    }

    public gga c(rda a2) {
        raa a3;
        String a4 = a2.c();
        if (a3.q.containsKey(a4)) {
            return a3.q.get(a4);
        }
        a3.k.submit(() -> {
            raa a4;
            gga a5;
            Map<String, kq> a6 = null;
            if (a2.f() != null) {
                try {
                    a6 = ht.ALLATORIxDEMO(a2.f());
                }
                catch (Exception a7) {
                    ca.l.w("\u65e0\u6cd5\u52a0\u8f7d\u6a21\u578b\u52a8\u753b: " + a4 + "  \u9519\u8bef\u539f\u56e0\u5df2\u8f93\u51fa\u81f3\u5ba2\u6237\u7aeflog\u5185");
                }
            }
            try {
                tt a8 = zt.ALLATORIxDEMO(a2.c());
                a5 = new gga(a8, a6);
            }
            catch (Exception a9) {
                a5 = new gga(ms.ALLATORIxDEMO(), a6);
                ca.l.z("\u65e0\u6cd5\u52a0\u8f7dOBJ\u6a21\u578b: " + a4 + "  \u9519\u8bef\u539f\u56e0\u5df2\u8f93\u51fa\u81f3log\u5185");
                a9.printStackTrace();
            }
            a4.q.put(a4, a5);
        });
        a3.q.put(a4, a3.y);
        return a3.y;
    }

    public gga ALLATORIxDEMO(rda a2) {
        raa a3;
        String a4 = a2.c();
        if (a3.b.containsKey(a4)) {
            return a3.b.get(a4);
        }
        a3.k.submit(() -> {
            raa a4;
            gga a5;
            try {
                hq a6 = (hq)ct.ALLATORIxDEMO.loadModel(a2.c());
                a5 = new gga(a6, new HashMap<String, kq>());
            }
            catch (Exception a7) {
                a5 = new gga(ms.ALLATORIxDEMO(), new HashMap<String, kq>());
                ca.l.z("\u65e0\u6cd5\u52a0\u8f7dSmd\u6a21\u578b: " + a4 + "  \u9519\u8bef\u539f\u56e0\u5df2\u8f93\u51fa\u81f3log\u5185");
                a7.printStackTrace();
            }
            a4.b.put(a4, a5);
        });
        a3.b.put(a4, a3.y);
        return a3.y;
    }
}

