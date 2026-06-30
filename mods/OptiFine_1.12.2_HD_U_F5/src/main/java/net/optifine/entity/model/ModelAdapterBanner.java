/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  avf
 *  bpf
 *  bqf
 *  brs
 *  bwu
 *  bwx
 *  bwy
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBanner
extends ModelAdapter {
    public ModelAdapterBanner() {
        super(avf.class, "banner", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bpf();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpf)) {
            return null;
        }
        bpf modelBanner = (bpf)model;
        if (modelPart.equals("slate")) {
            return modelBanner.a;
        }
        if (modelPart.equals("stand")) {
            return modelBanner.b;
        }
        if (modelPart.equals("top")) {
            return modelBanner.c;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"slate", "stand", "top"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bwx dispatcher = bwx.a;
        bwy renderer = dispatcher.a(avf.class);
        if (!(renderer instanceof bwu)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bwu();
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntityBannerRenderer_bannerModel.exists()) {
            Config.warn("Field not found: TileEntityBannerRenderer.bannerModel");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntityBannerRenderer_bannerModel, modelBase);
        return renderer;
    }
}

