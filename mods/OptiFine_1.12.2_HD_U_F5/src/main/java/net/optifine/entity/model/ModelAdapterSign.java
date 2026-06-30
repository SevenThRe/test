/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  awc
 *  bqf
 *  bqt
 *  brs
 *  bwx
 *  bwy
 *  bxf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSign
extends ModelAdapter {
    public ModelAdapterSign() {
        super(awc.class, "sign", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bqt();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqt)) {
            return null;
        }
        bqt modelSign = (bqt)model;
        if (modelPart.equals("board")) {
            return modelSign.a;
        }
        if (modelPart.equals("stick")) {
            return modelSign.b;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"board", "stick"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bwx dispatcher = bwx.a;
        bwy renderer = dispatcher.a(awc.class);
        if (!(renderer instanceof bxf)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bxf();
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntitySignRenderer_model.exists()) {
            Config.warn("Field not found: TileEntitySignRenderer.model");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntitySignRenderer_model, modelBase);
        return renderer;
    }
}

