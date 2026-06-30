/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bpy
 *  bqf
 *  brs
 */
package net.optifine.entity.model;

import net.optifine.entity.model.ModelAdapter;

public abstract class ModelAdapterIllager
extends ModelAdapter {
    public ModelAdapterIllager(Class entityClass, String name, float shadowSize) {
        super(entityClass, name, shadowSize);
    }

    public ModelAdapterIllager(Class entityClass, String name, float shadowSize, String[] aliases) {
        super(entityClass, name, shadowSize, aliases);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpy)) {
            return null;
        }
        bpy modelVillager = (bpy)model;
        if (modelPart.equals("head")) {
            return modelVillager.a;
        }
        if (modelPart.equals("body")) {
            return modelVillager.c;
        }
        if (modelPart.equals("arms")) {
            return modelVillager.d;
        }
        if (modelPart.equals("left_leg")) {
            return modelVillager.f;
        }
        if (modelPart.equals("right_leg")) {
            return modelVillager.e;
        }
        if (modelPart.equals("nose")) {
            return modelVillager.g;
        }
        if (modelPart.equals("left_arm")) {
            return modelVillager.i;
        }
        if (modelPart.equals("right_arm")) {
            return modelVillager.h;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "body", "arms", "right_leg", "left_leg", "nose", "right_arm", "left_arm"};
    }
}

