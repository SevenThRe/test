/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aab
 *  bib
 *  bqf
 *  bqg
 *  brs
 *  bzf
 *  cah
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterOcelot
extends ModelAdapter {
    private static Map<String, Integer> mapPartFields = null;

    public ModelAdapterOcelot() {
        super(aab.class, "ocelot", 0.4f);
    }

    @Override
    public bqf makeModel() {
        return new bqg();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqg)) {
            return null;
        }
        bqg modelOcelot = (bqg)model;
        Map<String, Integer> mapParts = ModelAdapterOcelot.getMapPartFields();
        if (mapParts.containsKey(modelPart)) {
            int index = mapParts.get(modelPart);
            return (brs)Reflector.getFieldValue(modelOcelot, Reflector.ModelOcelot_ModelRenderers, index);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"back_left_leg", "back_right_leg", "front_left_leg", "front_right_leg", "tail", "tail2", "head", "body"};
    }

    private static Map<String, Integer> getMapPartFields() {
        if (mapPartFields != null) {
            return mapPartFields;
        }
        mapPartFields = new HashMap<String, Integer>();
        mapPartFields.put("back_left_leg", 0);
        mapPartFields.put("back_right_leg", 1);
        mapPartFields.put("front_left_leg", 2);
        mapPartFields.put("front_right_leg", 3);
        mapPartFields.put("tail", 4);
        mapPartFields.put("tail2", 5);
        mapPartFields.put("head", 6);
        mapPartFields.put("body", 7);
        return mapPartFields;
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cah render = new cah(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

