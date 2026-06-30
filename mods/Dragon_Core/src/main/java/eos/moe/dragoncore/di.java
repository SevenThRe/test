/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.om;
import eos.moe.dragoncore.ze;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Future;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

public class di {
    public static di y = new di();
    private Map<String, ze> k = new ConcurrentHashMap<String, ze>();
    private Map<String, Future<?>> ALLATORIxDEMO = new ConcurrentHashMap();

    public di() {
        di a2;
    }

    public void ALLATORIxDEMO(ze a2, String a3) {
        om.q.submit(() -> {
            try {
                IResourceManager a4 = Minecraft.getMinecraft().getResourceManager();
                IResource a5 = a4.getResource(new ResourceLocation("dragoncore", a3));
                InputStream a6 = a5.getInputStream();
                a2.ALLATORIxDEMO(a6);
            }
            catch (IOException a7) {
                a7.printStackTrace();
            }
        });
    }

    public void ALLATORIxDEMO(String a2) {
        ze a3;
        di a4;
        Future<?> a5 = a4.ALLATORIxDEMO.remove(a2);
        if (a5 != null) {
            a5.cancel(true);
        }
        if ((a3 = a4.k.remove(a2)) != null) {
            a3.ALLATORIxDEMO();
        }
    }

    public void ALLATORIxDEMO() {
        di a2;
        for (Future<?> future : a2.ALLATORIxDEMO.values()) {
            future.cancel(true);
        }
        a2.ALLATORIxDEMO.clear();
        for (ze ze2 : a2.k.values()) {
            ze2.ALLATORIxDEMO();
        }
        a2.k.clear();
    }
}

