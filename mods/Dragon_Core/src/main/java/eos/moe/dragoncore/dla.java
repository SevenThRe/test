/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemOverrideList
 *  net.minecraft.client.renderer.texture.ITickableTextureObject
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.model.IModel
 *  net.minecraftforge.client.model.ItemLayerModel
 *  net.minecraftforge.client.model.ModelLoaderRegistry
 *  net.minecraftforge.client.model.ModelLoaderRegistry$LoaderException
 */
package eos.moe.dragoncore;

import com.google.common.collect.ImmutableList;
import eos.moe.dragoncore.al;
import eos.moe.dragoncore.cv;
import eos.moe.dragoncore.dj;
import eos.moe.dragoncore.interfaces.IItemStack;
import eos.moe.dragoncore.jga;
import eos.moe.dragoncore.jr;
import eos.moe.dragoncore.ku;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.vja;
import eos.moe.dragoncore.vw;
import eos.moe.dragoncore.ww;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;

public class dla {
    public static dla x = new dla();
    public static Set<String> v = new HashSet<String>();
    private static boolean m;
    private IResourceManager c;
    private HashMap<String, IBakedModel> q = new HashMap();
    private HashMap<String, IBakedModel> b = new HashMap();
    private HashMap<String, rda> o = new HashMap();
    private CopyOnWriteArrayList<vja> y = new CopyOnWriteArrayList();
    private CopyOnWriteArrayList<jga> k = new CopyOnWriteArrayList();
    public long ALLATORIxDEMO = Long.MAX_VALUE;

    public dla() {
        dla a2;
        a2.c = Minecraft.getMinecraft().getResourceManager();
    }

    public vja ALLATORIxDEMO(ItemStack a2) {
        dla a3;
        if (a2.isEmpty()) {
            return null;
        }
        if (a2.getItem().getRegistryName() != null && a2.getItem().getRegistryName().getPath().contains("shulker_box")) {
            return null;
        }
        String a4 = dj.ALLATORIxDEMO(a2, false, false);
        int a5 = Item.getIdFromItem((Item)a2.getItem());
        for (vja a6 : a3.y) {
            if (!vja.c(a6) || a6.ALLATORIxDEMO() != 0 && a5 != a6.ALLATORIxDEMO() || !al.ALLATORIxDEMO(vja.ALLATORIxDEMO(a6), a4)) continue;
            return a6;
        }
        return null;
    }

    public qd<IBakedModel, String> ALLATORIxDEMO(ItemStack a2) {
        Object a3;
        dla a4;
        if (a2.isEmpty()) {
            return null;
        }
        if (a2.getItem().getRegistryName() != null && a2.getItem().getRegistryName().getPath().contains("shulker_box")) {
            return null;
        }
        IItemStack a5 = (IItemStack)a2;
        long a6 = (Long)a5.getMeta("applyModelTime", 0L);
        if (a4.ALLATORIxDEMO > a6) {
            String a7 = dj.ALLATORIxDEMO(a2, false, false);
            int a72 = Item.getIdFromItem((Item)a2.getItem());
            for (jga jga2 : a4.k) {
                if (jga2.ALLATORIxDEMO() != 0 && a72 != jga2.ALLATORIxDEMO() || !al.ALLATORIxDEMO(jga.ALLATORIxDEMO(jga2), a7)) continue;
                a5.setMeta("modelKey", jga2);
                break;
            }
            for (vja vja2 : a4.y) {
                if (vja2.ALLATORIxDEMO() != 0 && a72 != vja2.ALLATORIxDEMO() || !al.ALLATORIxDEMO(vja.ALLATORIxDEMO(vja2), a7)) continue;
                a5.setMeta("modelKey", vja2);
                break;
            }
            a5.setMeta("applyModelTime", System.currentTimeMillis());
        }
        if ((a3 = a5.getMeta("modelKey")) instanceof jga) {
            return qd.ALLATORIxDEMO(a4.ALLATORIxDEMO((jga)a3), ((jga)a3).ALLATORIxDEMO());
        }
        if (a3 instanceof vja) {
            return qd.ALLATORIxDEMO(a4.ALLATORIxDEMO((vja)a3), ((vja)a3).ALLATORIxDEMO());
        }
        return null;
    }

    public IBakedModel c(String a2) {
        dla a3;
        for (jga jga2 : a3.k) {
            if (!jga2.f().equals(a2)) continue;
            return a3.ALLATORIxDEMO(jga2);
        }
        for (vja vja2 : a3.y) {
            if (!vja2.f().equals(a2)) continue;
            return a3.ALLATORIxDEMO(vja2);
        }
        return null;
    }

    private /* synthetic */ IBakedModel ALLATORIxDEMO(vja a3) {
        dla a5;
        if (a5.q.containsKey(a3.x())) {
            return a5.q.get(a3.x());
        }
        if (vja.c(a3) && vja.ALLATORIxDEMO(a3)) {
            if (vja.ALLATORIxDEMO(a3) == null) {
                try {
                    IModel a6 = ModelLoaderRegistry.getModel((ResourceLocation)new ResourceLocation("dragoncore", "items/" + a3.x() + "/transform"));
                    IBakedModel a7 = a6.bake(a6.getDefaultState(), DefaultVertexFormats.ITEM, a2 -> null);
                    vja.ALLATORIxDEMO(a3, new cv(a7));
                }
                catch (ModelLoaderRegistry.LoaderException a8) {
                    vja.ALLATORIxDEMO(a3, cv.y);
                }
                catch (Exception a9) {
                    vja.ALLATORIxDEMO(a3, cv.y);
                    a9.printStackTrace();
                }
            }
            return vja.ALLATORIxDEMO(a3);
        }
        if (vja.c(a3)) {
            return cv.y;
        }
        LinkedHashMap<String, IModel> a10 = new LinkedHashMap<String, IModel>();
        IModel a11 = ModelLoaderRegistry.getModelOrLogError((ResourceLocation)new ResourceLocation("dragoncore", "items/" + a3.x() + "/model"), (String)("\u65e0\u6cd5\u52a0\u8f7d\u7269\u54c1\u6a21\u578b: " + a3.x()));
        a10.put(a3.x(), a11);
        for (ResourceLocation a12 : a11.getDependencies()) {
            IModel iModel = ModelLoaderRegistry.getModelOrLogError((ResourceLocation)a12, (String)("\u65e0\u6cd5\u52a0\u8f7d\u7269\u54c1\u6a21\u578b: " + a3.x()));
            a10.put(a12.getPath(), iModel);
        }
        vw a14 = new vw("");
        a14.loadSprites(a5.c, a4 -> {
            for (Map.Entry a5 : a10.entrySet()) {
                for (ResourceLocation a6 : ((IModel)a5.getValue()).getTextures()) {
                    a4.registerSprite(new ResourceLocation("dragoncore", "models/items/" + a3.x() + "/" + a6.getPath()));
                }
            }
        });
        Minecraft.getMinecraft().getTextureManager().loadTickableTexture(new ResourceLocation("dragoncore", a3.x()), (ITickableTextureObject)a14);
        for (Map.Entry entry : a10.entrySet()) {
            IModel a15 = (IModel)entry.getValue();
            IBakedModel a16 = a15.bake(a15.getDefaultState(), DefaultVertexFormats.ITEM, a4 -> a14.getAtlasSprite("dragoncore:models/items/" + a3.x() + "/" + a4.getPath()));
            a16 = new jr(a14, a16, a3);
            a5.q.put((String)entry.getKey(), a16);
        }
        return a5.q.get(a3.x());
    }

    public IBakedModel ALLATORIxDEMO(String a2) {
        dla a3;
        return a3.q.get(a2);
    }

    private /* synthetic */ IBakedModel ALLATORIxDEMO(jga a2) {
        dla a4;
        ww a5 = ww.ALLATORIxDEMO(a2.x());
        if (a5 == null || !a5.isLoaded()) {
            return null;
        }
        int a6 = a5.getGlTextureId();
        if (a4.b.containsKey(a2.x() + "-" + a6)) {
            return a4.b.get(a2.x() + "-" + a6);
        }
        ItemLayerModel a7 = new ItemLayerModel(ImmutableList.of((Object)new ResourceLocation("dragoncore", a2.x())), ItemOverrideList.NONE);
        IBakedModel a8 = a7.bake(a7.getDefaultState(), DefaultVertexFormats.ITEM, a3 -> a5.toATextureCIItem());
        a8 = new ku(a8, a2);
        a4.b.put(a2.x() + "-" + a6, a8);
        return a8;
    }

    public void c() {
        dla a2;
        a2.q.clear();
        a2.b.clear();
        a2.o.clear();
        a2.ALLATORIxDEMO();
    }

    public void ALLATORIxDEMO() {
        a.ALLATORIxDEMO = System.currentTimeMillis();
    }

    public CopyOnWriteArrayList<vja> c() {
        dla a2;
        return a2.y;
    }

    public CopyOnWriteArrayList<jga> ALLATORIxDEMO() {
        dla a2;
        return a2.k;
    }
}

