/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aaf
 *  bib
 *  bqf
 *  bqn
 *  brs
 *  bzf
 *  can
 */
package net.optifine.entity.model;

import java.util.HashMap;
import java.util.Map;
import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterRabbit
extends ModelAdapter {
    private static Map<String, Integer> mapPartFields = null;

    public ModelAdapterRabbit() {
        super(aaf.class, "rabbit", 0.3f);
    }

    @Override
    public bqf makeModel() {
        return new bqn();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqn)) {
            return null;
        }
        bqn modelRabbit = (bqn)model;
        Map<String, Integer> mapParts = ModelAdapterRabbit.getMapPartFields();
        if (mapParts.containsKey(modelPart)) {
            int index = mapParts.get(modelPart);
            return (brs)Reflector.getFieldValue(modelRabbit, Reflector.ModelRabbit_renderers, index);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"left_foot", "right_foot", "left_thigh", "right_thigh", "body", "left_arm", "right_arm", "head", "right_ear", "left_ear", "tail", "nose"};
    }

    private static Map<String, Integer> getMapPartFields() {
        if (mapPartFields != null) {
            return mapPartFields;
        }
        mapPartFields = new HashMap<String, Integer>();
        mapPartFields.put("left_foot", 0);
        mapPartFields.put("right_foot", 1);
        mapPartFields.put("left_thigh", 2);
        mapPartFields.put("right_thigh", 3);
        mapPartFields.put("body", 4);
        mapPartFields.put("left_arm", 5);
        mapPartFields.put("right_arm", 6);
        mapPartFields.put("head", 7);
        mapPartFields.put("right_ear", 8);
        mapPartFields.put("left_ear", 9);
        mapPartFields.put("tail", 10);
        mapPartFields.put("nose", 11);
        return mapPartFields;
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        can render = new can(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

