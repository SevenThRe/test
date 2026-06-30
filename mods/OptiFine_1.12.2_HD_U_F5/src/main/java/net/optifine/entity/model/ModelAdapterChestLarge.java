/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  avl
 *  bpl
 *  bpz
 *  bqf
 *  brs
 *  bwx
 *  bwy
 *  bwz
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterChestLarge
extends ModelAdapter {
    public ModelAdapterChestLarge() {
        super(avl.class, "chest_large", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bpz();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpl)) {
            return null;
        }
        bpl modelChest = (bpl)model;
        if (modelPart.equals("lid")) {
            return modelChest.a;
        }
        if (modelPart.equals("base")) {
            return modelChest.b;
        }
        if (modelPart.equals("knob")) {
            return modelChest.c;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"lid", "base", "knob"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bwx dispatcher = bwx.a;
        bwy renderer = dispatcher.a(avl.class);
        if (!(renderer instanceof bwz)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bwz();
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntityChestRenderer_largeChest.exists()) {
            Config.warn("Field not found: TileEntityChestRenderer.largeChest");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntityChestRenderer_largeChest, modelBase);
        return renderer;
    }
}

