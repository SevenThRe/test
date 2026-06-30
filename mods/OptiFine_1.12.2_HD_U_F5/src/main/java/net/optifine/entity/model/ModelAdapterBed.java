/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  avi
 *  bph
 *  bqf
 *  brs
 *  bww
 *  bwx
 *  bwy
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBed
extends ModelAdapter {
    public ModelAdapterBed() {
        super(avi.class, "bed", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bph();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bph)) {
            return null;
        }
        bph modelBed = (bph)model;
        if (modelPart.equals("head")) {
            return modelBed.a;
        }
        if (modelPart.equals("foot")) {
            return modelBed.b;
        }
        if (modelPart.equals("leg1")) {
            return modelBed.c[0];
        }
        if (modelPart.equals("leg2")) {
            return modelBed.c[1];
        }
        if (modelPart.equals("leg3")) {
            return modelBed.c[2];
        }
        if (modelPart.equals("leg4")) {
            return modelBed.c[3];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "foot", "leg1", "leg2", "leg3", "leg4"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bwx dispatcher = bwx.a;
        bwy renderer = dispatcher.a(avi.class);
        if (!(renderer instanceof bww)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bww();
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntityBedRenderer_model.exists()) {
            Config.warn("Field not found: TileEntityBedRenderer.model");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntityBedRenderer_model, modelBase);
        return renderer;
    }
}

