/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  awb
 *  bqf
 *  bqs
 *  brs
 *  bwx
 *  bwy
 *  bxe
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterShulkerBox
extends ModelAdapter {
    public ModelAdapterShulkerBox() {
        super(awb.class, "shulker_box", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bqs();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqs)) {
            return null;
        }
        bqs modelShulker = (bqs)model;
        if (modelPart.equals("head")) {
            return modelShulker.c;
        }
        if (modelPart.equals("base")) {
            return modelShulker.a;
        }
        if (modelPart.equals("lid")) {
            return modelShulker.b;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"base", "lid", "head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bwx dispatcher = bwx.a;
        bwy renderer = dispatcher.a(awb.class);
        if (!(renderer instanceof bxe)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bxe((bqs)modelBase);
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntityShulkerBoxRenderer_model.exists()) {
            Config.warn("Field not found: TileEntityShulkerBoxRenderer.model");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntityShulkerBoxRenderer_model, modelBase);
        return renderer;
    }
}

