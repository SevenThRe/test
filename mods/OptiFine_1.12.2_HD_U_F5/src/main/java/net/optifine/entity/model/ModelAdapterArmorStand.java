/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abz
 *  bib
 *  bpe
 *  bqf
 *  brs
 *  byp
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapterBiped;

public class ModelAdapterArmorStand
extends ModelAdapterBiped {
    public ModelAdapterArmorStand() {
        super(abz.class, "armor_stand", 0.0f);
    }

    @Override
    public bqf makeModel() {
        return new bpe();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpe)) {
            return null;
        }
        bpe modelArmorStand = (bpe)model;
        if (modelPart.equals("right")) {
            return modelArmorStand.a;
        }
        if (modelPart.equals("left")) {
            return modelArmorStand.b;
        }
        if (modelPart.equals("waist")) {
            return modelArmorStand.c;
        }
        if (modelPart.equals("base")) {
            return modelArmorStand.d;
        }
        return super.getModelRenderer((bqf)modelArmorStand, modelPart);
    }

    @Override
    public String[] getModelRendererNames() {
        Object[] names = super.getModelRendererNames();
        names = (String[])Config.addObjectsToArray(names, new String[]{"right", "left", "waist", "base"});
        return names;
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        byp render = new byp(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

