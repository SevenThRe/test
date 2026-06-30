/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.resources.IResourceManagerReloadListener
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.SimpleReloadableResourceManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.ModelLoaderRegistry
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.dragoncore;

import blockbuster.utils.texture.GifHandler;
import blockbuster.utils.texture.GifTexture;
import eos.moe.dragoncore.bca;
import eos.moe.dragoncore.cy;
import eos.moe.dragoncore.di;
import eos.moe.dragoncore.dla;
import eos.moe.dragoncore.fd;
import eos.moe.dragoncore.kx;
import eos.moe.dragoncore.li;
import eos.moe.dragoncore.no;
import eos.moe.dragoncore.oa;
import eos.moe.dragoncore.pq;
import eos.moe.dragoncore.qv;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.sja;
import eos.moe.dragoncore.sq;
import eos.moe.dragoncore.vw;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.wq;
import eos.moe.dragoncore.ww;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.SimpleReloadableResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class vq
implements IResourceManagerReloadListener {
    public vq() {
        vq a2;
        IResourceManager a3 = Minecraft.getMinecraft().getResourceManager();
        if (a3 instanceof SimpleReloadableResourceManager) {
            ((SimpleReloadableResourceManager)a3).registerReloadListener((IResourceManagerReloadListener)a2);
        }
    }

    public static void f() {
        new vq();
    }

    public static void c() {
        dla.x.c();
        sja.y.ALLATORIxDEMO();
        Map a3 = (Map)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])new String[]{"cache"});
        Deque a4 = (Deque)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])new String[]{"loadingModels"});
        Set a5 = (Set)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])new String[]{"textures"});
        Map a6 = (Map)ReflectionHelper.getPrivateValue(ModelLoaderRegistry.class, null, (String[])new String[]{"aliases"});
        a3.entrySet().removeIf(a2 -> ((ResourceLocation)a2.getKey()).getNamespace().equals("dragoncore"));
        a4.removeIf(a2 -> a2.getNamespace().equals("dragoncore"));
        a5.removeIf(a2 -> a2.getNamespace().equals("dragoncore"));
        a6.entrySet().removeIf(a2 -> ((ResourceLocation)a2.getKey()).getNamespace().equals("dragoncore"));
    }

    public static void ALLATORIxDEMO() {
        System.out.println("\u5f00\u59cb\u91cd\u8f7d\u8d44\u6e90\u6587\u4ef6");
        wka.k = false;
        Map a3 = (Map)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.getMinecraft().getTextureManager(), (String[])new String[]{"mapTextureObjects", "mapTextureObjects"});
        a3.entrySet().removeIf(a2 -> {
            boolean a3 = "dragoncore".equals(((ResourceLocation)a2.getKey()).getNamespace());
            if (a3 && a2.getValue() instanceof AbstractTexture) {
                AbstractTexture a4 = (AbstractTexture)a2.getValue();
                a4.deleteGlTexture();
            }
            if (a3 && a2.getValue() instanceof GifTexture) {
                GifHandler.removeGif((ResourceLocation)a2.getKey());
                ((GifTexture)((Object)((Object)a2.getValue()))).deleteGlTexture();
            }
            return a3;
        });
        List a4 = (List)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.getMinecraft().getTextureManager(), (String[])new String[]{"listTickables", "listTickables"});
        a4.removeIf(a2 -> a2 instanceof vw || a2 instanceof ww);
        wq.b.reload();
        oa.c();
        vq.c();
        raa.r.c();
        fd.ALLATORIxDEMO();
        dla.v.clear();
        no.k.clear();
        no.ALLATORIxDEMO.clear();
        sq.c();
        cy.q.ALLATORIxDEMO();
        qv.y.f();
        qv.k.f();
        qv.o.f();
        di.y.ALLATORIxDEMO();
        li.k.ALLATORIxDEMO();
        pq.ALLATORIxDEMO.clear();
        ww.x();
        bca.o = false;
        wka.k = true;
        System.out.println("\u8d44\u6e90\u6587\u4ef6\u91cd\u8f7d\u5b8c\u6210");
    }

    public void onResourceManagerReload(IResourceManager a2) {
        if (a2 instanceof SimpleReloadableResourceManager) {
            ((SimpleReloadableResourceManager)a2).reloadResourcePack((IResourcePack)new pq());
            ((SimpleReloadableResourceManager)a2).reloadResourcePack((IResourcePack)new wq());
            ((SimpleReloadableResourceManager)a2).reloadResourcePack((IResourcePack)kx.k);
            if (wka.k) {
                wka.k = false;
                vq.c();
                wka.k = true;
            }
        }
    }
}

