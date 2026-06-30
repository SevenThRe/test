/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  awd
 *  bqf
 *  bqv
 *  brs
 *  bwx
 *  bwy
 *  bxg
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterHeadSkeleton
extends ModelAdapter {
    public ModelAdapterHeadSkeleton() {
        super(awd.class, "head_skeleton", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bqv(0, 0, 64, 32);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqv)) {
            return null;
        }
        bqv modelSkeletonHead = (bqv)model;
        if (modelPart.equals("head")) {
            return modelSkeletonHead.a;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bwx dispatcher = bwx.a;
        bwy renderer = dispatcher.a(awd.class);
        if (!(renderer instanceof bxg)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bxg();
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntitySkullRenderer_humanoidHead.exists()) {
            Config.warn("Field not found: TileEntitySkullRenderer.humanoidHead");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntitySkullRenderer_humanoidHead, modelBase);
        return renderer;
    }
}

