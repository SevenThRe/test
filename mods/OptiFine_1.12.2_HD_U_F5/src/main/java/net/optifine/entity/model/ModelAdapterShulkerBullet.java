/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aer
 *  bib
 *  bqf
 *  bqr
 *  brs
 *  bzf
 *  cap
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterShulkerBullet
extends ModelAdapter {
    public ModelAdapterShulkerBullet() {
        super(aer.class, "shulker_bullet", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bqr();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqr)) {
            return null;
        }
        bqr modelShulkerBullet = (bqr)model;
        if (modelPart.equals("bullet")) {
            return modelShulkerBullet.a;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"bullet"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cap render = new cap(renderManager);
        if (!Reflector.RenderShulkerBullet_model.exists()) {
            Config.warn("Field not found: RenderShulkerBullet.model");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderShulkerBullet_model, modelBase);
        render.c = shadowSize;
        return render;
    }
}

