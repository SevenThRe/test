/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  avr
 *  bpk
 *  bqf
 *  brs
 *  bwx
 *  bwy
 *  bxa
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBook
extends ModelAdapter {
    public ModelAdapterBook() {
        super(avr.class, "book", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bpk();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpk)) {
            return null;
        }
        bpk modelBook = (bpk)model;
        if (modelPart.equals("cover_right")) {
            return modelBook.a;
        }
        if (modelPart.equals("cover_left")) {
            return modelBook.b;
        }
        if (modelPart.equals("pages_right")) {
            return modelBook.c;
        }
        if (modelPart.equals("pages_left")) {
            return modelBook.d;
        }
        if (modelPart.equals("flipping_page_right")) {
            return modelBook.e;
        }
        if (modelPart.equals("flipping_page_left")) {
            return modelBook.f;
        }
        if (modelPart.equals("book_spine")) {
            return modelBook.g;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"cover_right", "cover_left", "pages_right", "pages_left", "flipping_page_right", "flipping_page_left", "book_spine"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bwx dispatcher = bwx.a;
        bwy renderer = dispatcher.a(avr.class);
        if (!(renderer instanceof bxa)) {
            return null;
        }
        if (renderer.getEntityClass() == null) {
            renderer = new bxa();
            renderer.a(dispatcher);
        }
        if (!Reflector.TileEntityEnchantmentTableRenderer_modelBook.exists()) {
            Config.warn("Field not found: TileEntityEnchantmentTableRenderer.modelBook");
            return null;
        }
        Reflector.setFieldValue(renderer, Reflector.TileEntityEnchantmentTableRenderer_modelBook, modelBase);
        return renderer;
    }
}

