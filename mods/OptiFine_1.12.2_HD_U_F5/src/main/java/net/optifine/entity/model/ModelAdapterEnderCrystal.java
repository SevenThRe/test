/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abc
 *  bib
 *  bqf
 *  bro
 *  brs
 *  bzb
 *  bzf
 *  bzg
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterEnderCrystal
extends ModelAdapter {
    public ModelAdapterEnderCrystal() {
        this("end_crystal");
    }

    protected ModelAdapterEnderCrystal(String name) {
        super(abc.class, name, 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bro(0.0f, true);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bro)) {
            return null;
        }
        bro modelEnderCrystal = (bro)model;
        if (modelPart.equals("cube")) {
            return (brs)Reflector.getFieldValue(modelEnderCrystal, Reflector.ModelEnderCrystal_ModelRenderers, 0);
        }
        if (modelPart.equals("glass")) {
            return (brs)Reflector.getFieldValue(modelEnderCrystal, Reflector.ModelEnderCrystal_ModelRenderers, 1);
        }
        if (modelPart.equals("base")) {
            return (brs)Reflector.getFieldValue(modelEnderCrystal, Reflector.ModelEnderCrystal_ModelRenderers, 2);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"cube", "glass", "base"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzg renderObj = (bzg)renderManager.getEntityRenderMap().get(abc.class);
        if (!(renderObj instanceof bzb)) {
            Config.warn("Not an instance of RenderEnderCrystal: " + renderObj);
            return null;
        }
        bzb render = (bzb)renderObj;
        if (!Reflector.RenderEnderCrystal_modelEnderCrystal.exists()) {
            Config.warn("Field not found: RenderEnderCrystal.modelEnderCrystal");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderEnderCrystal_modelEnderCrystal, modelBase);
        render.c = shadowSize;
        return render;
    }
}

