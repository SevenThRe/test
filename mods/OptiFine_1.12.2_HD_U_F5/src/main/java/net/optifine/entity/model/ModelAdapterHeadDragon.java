/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  awd
 *  bqf
 *  brm
 *  brs
 *  bwx
 *  bwy
 *  bxg
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterHeadDragon
extends ModelAdapter {
    public ModelAdapterHeadDragon() {
        super(awd.class, "head_dragon", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new brm(0.0f);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof brm)) {
            return null;
        }
        brm modelDragonHead = (brm)model;
        if (modelPart.equals("head")) {
            return (brs)Reflector.getFieldValue(modelDragonHead, Reflector.ModelDragonHead_head);
        }
        if (modelPart.equals("jaw")) {
            return (brs)Reflector.getFieldValue(modelDragonHead, Reflector.ModelDragonHead_jaw);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "jaw"};
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
        if (!Reflector.TileEntitySkullRenderer_dragonHead.exists()) {
            Config.warn("Field not found: TileEntitySkullRenderer.dragonHead");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntitySkullRenderer_dragonHead, modelBase);
        return renderer;
    }
}

