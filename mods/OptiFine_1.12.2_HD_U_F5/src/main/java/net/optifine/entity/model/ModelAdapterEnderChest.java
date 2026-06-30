/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  avs
 *  bpl
 *  bqf
 *  brs
 *  bwx
 *  bwy
 *  bxb
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterEnderChest
extends ModelAdapter {
    public ModelAdapterEnderChest() {
        super(avs.class, "ender_chest", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bpl();
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
        bwy renderer = dispatcher.a(avs.class);
        if (!(renderer instanceof bxb)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bxb();
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntityEnderChestRenderer_modelChest.exists()) {
            Config.warn("Field not found: TileEntityEnderChestRenderer.modelChest");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntityEnderChestRenderer_modelChest, modelBase);
        return renderer;
    }
}

