/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bqf
 *  brs
 *  bwx
 *  bwy
 *  bzf
 *  bzg
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  nf
 */
package net.optifine.entity.model;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.optifine.entity.model.CustomEntityModelParser;
import net.optifine.entity.model.CustomEntityRenderer;
import net.optifine.entity.model.CustomModelRegistry;
import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.anim.ModelResolver;
import net.optifine.entity.model.anim.ModelUpdater;

public class CustomEntityModels {
    private static boolean active = false;
    private static Map<Class, bzg> originalEntityRenderMap = null;
    private static Map<Class, bwy> originalTileEntityRenderMap = null;

    public static void update() {
        Map<Class, bzg> entityRenderMap = CustomEntityModels.getEntityRenderMap();
        Map<Class, bwy> tileEntityRenderMap = CustomEntityModels.getTileEntityRenderMap();
        if (entityRenderMap == null) {
            Config.warn("Entity render map not found, custom entity models are DISABLED.");
            return;
        }
        if (tileEntityRenderMap == null) {
            Config.warn("Tile entity render map not found, custom entity models are DISABLED.");
            return;
        }
        active = false;
        entityRenderMap.clear();
        tileEntityRenderMap.clear();
        entityRenderMap.putAll(originalEntityRenderMap);
        tileEntityRenderMap.putAll(originalTileEntityRenderMap);
        if (!Config.isCustomEntityModels()) {
            return;
        }
        nf[] locs = CustomEntityModels.getModelLocations();
        for (int i = 0; i < locs.length; ++i) {
            Class entityClass;
            nf loc = locs[i];
            Config.dbg("CustomEntityModel: " + loc.a());
            IEntityRenderer rc = CustomEntityModels.parseEntityRender(loc);
            if (rc == null || (entityClass = rc.getEntityClass()) == null) continue;
            if (rc instanceof bzg) {
                entityRenderMap.put(entityClass, (bzg)rc);
            } else if (rc instanceof bwy) {
                tileEntityRenderMap.put(entityClass, (bwy)rc);
            } else {
                Config.warn("Unknown renderer type: " + rc.getClass().getName());
            }
            active = true;
        }
    }

    private static Map<Class, bzg> getEntityRenderMap() {
        bzf rm = bib.z().ac();
        Map entityRenderMap = rm.getEntityRenderMap();
        if (entityRenderMap == null) {
            return null;
        }
        if (originalEntityRenderMap == null) {
            originalEntityRenderMap = new HashMap<Class, bzg>(entityRenderMap);
        }
        return entityRenderMap;
    }

    private static Map<Class, bwy> getTileEntityRenderMap() {
        Map tileEntityRenderMap = bwx.a.n;
        if (originalTileEntityRenderMap == null) {
            originalTileEntityRenderMap = new HashMap<Class, bwy>(tileEntityRenderMap);
        }
        return tileEntityRenderMap;
    }

    private static nf[] getModelLocations() {
        String prefix = "optifine/cem/";
        String suffix = ".jem";
        ArrayList<nf> resourceLocations = new ArrayList<nf>();
        String[] names = CustomModelRegistry.getModelNames();
        for (int i = 0; i < names.length; ++i) {
            String name = names[i];
            String path = prefix + name + suffix;
            nf loc = new nf(path);
            if (!Config.hasResource(loc)) continue;
            resourceLocations.add(loc);
        }
        nf[] locs = resourceLocations.toArray(new nf[resourceLocations.size()]);
        return locs;
    }

    private static IEntityRenderer parseEntityRender(nf location) {
        try {
            JsonObject jo = CustomEntityModelParser.loadJson(location);
            IEntityRenderer render = CustomEntityModels.parseEntityRender(jo, location.a());
            return render;
        }
        catch (IOException e) {
            Config.error("" + e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
        catch (JsonParseException e) {
            Config.error("" + ((Object)((Object)e)).getClass().getName() + ": " + e.getMessage());
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static IEntityRenderer parseEntityRender(JsonObject obj, String path) {
        CustomEntityRenderer cer2 = CustomEntityModelParser.parseEntityRender(obj, path);
        String name = cer2.getName();
        ModelAdapter modelAdapter = CustomModelRegistry.getModelAdapter(name);
        CustomEntityModels.checkNull(modelAdapter, "Entity not found: " + name);
        Class entityClass = modelAdapter.getEntityClass();
        CustomEntityModels.checkNull(entityClass, "Entity class not found: " + name);
        IEntityRenderer render = CustomEntityModels.makeEntityRender(modelAdapter, cer2);
        if (render == null) {
            return null;
        }
        render.setEntityClass(entityClass);
        return render;
    }

    private static IEntityRenderer makeEntityRender(ModelAdapter modelAdapter, CustomEntityRenderer cer2) {
        bqf model;
        nf textureLocation = cer2.getTextureLocation();
        CustomModelRenderer[] modelRenderers = cer2.getCustomModelRenderers();
        float shadowSize = cer2.getShadowSize();
        if (shadowSize < 0.0f) {
            shadowSize = modelAdapter.getShadowSize();
        }
        if ((model = modelAdapter.makeModel()) == null) {
            return null;
        }
        ModelResolver mr = new ModelResolver(modelAdapter, model, modelRenderers);
        if (!CustomEntityModels.modifyModel(modelAdapter, model, modelRenderers, mr)) {
            return null;
        }
        IEntityRenderer r = modelAdapter.makeEntityRender(model, shadowSize);
        if (r == null) {
            throw new JsonParseException("Entity renderer is null, model: " + modelAdapter.getName() + ", adapter: " + modelAdapter.getClass().getName());
        }
        if (textureLocation != null) {
            r.setLocationTextureCustom(textureLocation);
        }
        return r;
    }

    private static boolean modifyModel(ModelAdapter modelAdapter, bqf model, CustomModelRenderer[] modelRenderers, ModelResolver mr) {
        for (int i = 0; i < modelRenderers.length; ++i) {
            CustomModelRenderer cmr = modelRenderers[i];
            if (CustomEntityModels.modifyModel(modelAdapter, model, cmr, mr)) continue;
            return false;
        }
        return true;
    }

    private static boolean modifyModel(ModelAdapter modelAdapter, bqf model, CustomModelRenderer customModelRenderer, ModelResolver modelResolver) {
        String modelPart = customModelRenderer.getModelPart();
        brs parent = modelAdapter.getModelRenderer(model, modelPart);
        if (parent == null) {
            Config.warn("Model part not found: " + modelPart + ", model: " + model);
            return false;
        }
        if (!customModelRenderer.isAttach()) {
            if (parent.l != null) {
                parent.l.clear();
            }
            if (parent.spriteList != null) {
                parent.spriteList.clear();
            }
            if (parent.m != null) {
                brs[] mrs = modelAdapter.getModelRenderers(model);
                Set setMrs = Collections.newSetFromMap(new IdentityHashMap());
                setMrs.addAll(Arrays.asList(mrs));
                List childModels = parent.m;
                Iterator it = childModels.iterator();
                while (it.hasNext()) {
                    brs mr = (brs)it.next();
                    if (setMrs.contains(mr)) continue;
                    it.remove();
                }
            }
        }
        parent.a(customModelRenderer.getModelRenderer());
        ModelUpdater mu = customModelRenderer.getModelUpdater();
        if (mu != null) {
            modelResolver.setThisModelRenderer(customModelRenderer.getModelRenderer());
            modelResolver.setPartModelRenderer(parent);
            if (!mu.initialize(modelResolver)) {
                return false;
            }
            customModelRenderer.getModelRenderer().setModelUpdater(mu);
        }
        return true;
    }

    private static void checkNull(Object obj, String msg) {
        if (obj == null) {
            throw new JsonParseException(msg);
        }
    }

    public static boolean isActive() {
        return active;
    }
}

