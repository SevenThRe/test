/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.ct;
import eos.moe.dragoncore.dla;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.ms;
import eos.moe.dragoncore.ny;
import eos.moe.dragoncore.pm;
import eos.moe.dragoncore.sr;
import eos.moe.dragoncore.vja;
import eos.moe.dragoncore.wr;
import eos.moe.dragoncore.xz;
import eos.moe.dragoncore.zt;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

public class sq {
    public static final Map<String, xz> o = new HashMap<String, xz>();
    public static final HashMap<String, ny> y = new HashMap();
    public static final Map<String, Map<String, sr>> k = new HashMap<String, Map<String, sr>>();
    private static final ExecutorService ALLATORIxDEMO = Executors.newCachedThreadPool();

    public sq() {
        sq a2;
    }

    public static void f() {
        ALLATORIxDEMO.shutdownNow();
    }

    public static void c() {
        o.clear();
        y.clear();
        k.clear();
    }

    public static void ALLATORIxDEMO() {
        o.clear();
    }

    public static ny ALLATORIxDEMO(vja a2) {
        String a3 = a2.x();
        if (!y.containsKey(a2.f())) {
            String a4 = null;
            IResourceManager a5 = Minecraft.func_71410_x().func_110442_L();
            try {
                a5.func_110536_a(new ResourceLocation("dragoncore", "models/items/" + a3 + "/glow_texture.png"));
                a4 = "glow_texture.png";
            }
            catch (Exception a6) {
                try {
                    a5.func_110536_a(new ResourceLocation("dragoncore", "models/items/" + a3 + "/texture_e.png"));
                    a4 = "texture_e.png";
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
            String a7 = a4;
            ALLATORIxDEMO.submit(() -> {
                block10: {
                    try {
                        ny a5 = null;
                        if (a2.f()) {
                            a5 = new ny((hq)ct.ALLATORIxDEMO.loadModel(new ResourceLocation("dragoncore", "models/items/" + a3 + "/model.pqc")));
                        } else if (a2.x()) {
                            a5 = new ny(ms.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/items/" + a3 + "/model.json")));
                        } else if (a2.ALLATORIxDEMO()) {
                            a5 = new ny(zt.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/items/" + a3 + "/model.obj")));
                        } else {
                            Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7c\u672a\u8bbe\u5b9a\u7684\u7269\u54c1\u6a21\u578b\u7c7b\u578b:" + a2.f()));
                        }
                        if (a5 == null) break block10;
                        Map<String, kq> a6 = null;
                        try {
                            a6 = wr.ALLATORIxDEMO(new ResourceLocation("dragoncore", "models/items/" + a3 + "/animation.json"));
                        }
                        catch (Exception exception) {
                            // empty catch block
                        }
                        a5.ALLATORIxDEMO(a3, a6, a7);
                        y.put(a2.f(), a5);
                    }
                    catch (Exception a7) {
                        a7.printStackTrace();
                        Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7c\u65e0\u6cd5\u52a0\u8f7d\u7269\u54c1\u6a21\u578b:" + a2.f()));
                    }
                }
            });
            y.put(a2.f(), null);
        }
        return y.get(a2.f());
    }

    public static void ALLATORIxDEMO(UUID a2, String a3, float a4) {
        ny a5;
        vja a6;
        EntityLivingBase a7 = pm.ALLATORIxDEMO(a2);
        if (a7 != null && !a7.func_184614_ca().func_190926_b() && (a6 = dla.x.ALLATORIxDEMO(a7.func_184614_ca())) != null && (a5 = sq.ALLATORIxDEMO(a6)) != null) {
            a5.ALLATORIxDEMO(a2.toString(), a3, a4);
        }
    }

    public static sr ALLATORIxDEMO(vja a2, String a3) {
        sr a4;
        Map<String, sr> a5;
        String a6 = a2.x();
        if (!a2.z && (a5 = k.get(a6)) != null) {
            sr a7 = a5.get(a3);
            if (a7 == null) {
                return a5.get("hand");
            }
            return a7;
        }
        try {
            a5 = new HashMap<String, sr>();
            a4 = Minecraft.func_71410_x().func_110442_L().func_110536_a(new ResourceLocation("dragoncore", "models/items/" + a6 + "/transform.yml"));
            YamlConfiguration a8 = YamlConfiguration.loadConfiguration(a4.func_110527_b());
            for (String a9 : a8.getKeys(false)) {
                ConfigurationSection a10 = a8.getConfigurationSection(a9);
                if (a10 == null) continue;
                a5.put(a9, new sr(a10));
            }
            k.put(a6, a5);
        }
        catch (Exception a22) {
            a22.printStackTrace();
            k.put(a6, new HashMap());
        }
        a5 = k.get(a6);
        a4 = a5.get(a3);
        if (a4 == null) {
            return a5.get("hand");
        }
        return a4;
    }
}

