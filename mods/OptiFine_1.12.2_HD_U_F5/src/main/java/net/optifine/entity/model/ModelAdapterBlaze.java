/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  acq
 *  bib
 *  bpi
 *  bqf
 *  brs
 *  bys
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterBlaze
extends ModelAdapter {
    public ModelAdapterBlaze() {
        super(acq.class, "blaze", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpi();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpi)) {
            return null;
        }
        bpi modelBlaze = (bpi)model;
        if (modelPart.equals("head")) {
            return (brs)Reflector.getFieldValue(modelBlaze, Reflector.ModelBlaze_blazeHead);
        }
        String PREFIX_STICK = "stick";
        if (modelPart.startsWith(PREFIX_STICK)) {
            brs[] sticks = (brs[])Reflector.getFieldValue(modelBlaze, Reflector.ModelBlaze_blazeSticks);
            if (sticks == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_STICK.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= sticks.length) {
                return null;
            }
            return sticks[index];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "stick1", "stick2", "stick3", "stick4", "stick5", "stick6", "stick7", "stick8", "stick9", "stick10", "stick11", "stick12"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bys render = new bys(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

