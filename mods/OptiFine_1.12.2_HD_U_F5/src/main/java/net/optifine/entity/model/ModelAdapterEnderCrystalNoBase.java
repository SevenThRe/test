/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abc
 *  bib
 *  bqf
 *  bro
 *  bzb
 *  bzf
 *  bzg
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterEnderCrystal;
import net.optifine.reflect.Reflector;

public class ModelAdapterEnderCrystalNoBase
extends ModelAdapterEnderCrystal {
    public ModelAdapterEnderCrystalNoBase() {
        super("end_crystal_no_base");
    }

    @Override
    public bqf makeModel() {
        return new bro(0.0f, false);
    }

    @Override
    public String[] getModelRendererNames() {
        Object[] names = super.getModelRendererNames();
        names = (String[])Config.removeObjectFromArray(names, "base");
        return names;
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
        if (!Reflector.RenderEnderCrystal_modelEnderCrystalNoBase.exists()) {
            Config.warn("Field not found: RenderEnderCrystal.modelEnderCrystalNoBase");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderEnderCrystal_modelEnderCrystalNoBase, modelBase);
        render.c = shadowSize;
        return render;
    }
}

