/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bpx
 *  bqf
 *  brs
 */
package net.optifine.entity.model;

import net.optifine.entity.model.ModelAdapter;

public abstract class ModelAdapterBiped
extends ModelAdapter {
    public ModelAdapterBiped(Class entityClass, String name, float shadowSize) {
        super(entityClass, name, shadowSize);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpx)) {
            return null;
        }
        bpx modelBiped = (bpx)model;
        if (modelPart.equals("head")) {
            return modelBiped.e;
        }
        if (modelPart.equals("headwear")) {
            return modelBiped.f;
        }
        if (modelPart.equals("body")) {
            return modelBiped.g;
        }
        if (modelPart.equals("left_arm")) {
            return modelBiped.i;
        }
        if (modelPart.equals("right_arm")) {
            return modelBiped.h;
        }
        if (modelPart.equals("left_leg")) {
            return modelBiped.k;
        }
        if (modelPart.equals("right_leg")) {
            return modelBiped.j;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "headwear", "body", "left_arm", "right_arm", "left_leg", "right_leg"};
    }
}

