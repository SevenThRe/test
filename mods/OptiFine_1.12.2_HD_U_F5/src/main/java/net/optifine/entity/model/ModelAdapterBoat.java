/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  afd
 *  bib
 *  bpj
 *  bqf
 *  brs
 *  byt
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBoat
extends ModelAdapter {
    public ModelAdapterBoat() {
        super(afd.class, "boat", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpj();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpj)) {
            return null;
        }
        bpj modelBoat = (bpj)model;
        if (modelPart.equals("bottom")) {
            return modelBoat.a[0];
        }
        if (modelPart.equals("back")) {
            return modelBoat.a[1];
        }
        if (modelPart.equals("front")) {
            return modelBoat.a[2];
        }
        if (modelPart.equals("right")) {
            return modelBoat.a[3];
        }
        if (modelPart.equals("left")) {
            return modelBoat.a[4];
        }
        if (modelPart.equals("paddle_left")) {
            return modelBoat.b[0];
        }
        if (modelPart.equals("paddle_right")) {
            return modelBoat.b[1];
        }
        if (modelPart.equals("bottom_no_water")) {
            return modelBoat.c;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"bottom", "back", "front", "right", "left", "paddle_left", "paddle_right", "bottom_no_water"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byt render = new byt(renderManager);
        if (!Reflector.RenderBoat_modelBoat.exists()) {
            Config.warn("Field not found: RenderBoat.modelBoat");
            return null;
        }
        Reflector.setFieldValue(render, Reflector.RenderBoat_modelBoat, modelBase);
        render.c = shadowSize;
        return render;
    }
}

