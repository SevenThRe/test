/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adj
 *  bib
 *  bqf
 *  bqu
 *  brs
 *  bzf
 *  car
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterSilverfish
extends ModelAdapter {
    public ModelAdapterSilverfish() {
        super(adj.class, "silverfish", 0.3f);
    }

    @Override
    public bqf makeModel() {
        return new bqu();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqu)) {
            return null;
        }
        bqu modelSilverfish = (bqu)model;
        String PREFIX_BODY = "body";
        if (modelPart.startsWith(PREFIX_BODY)) {
            brs[] bodyParts = (brs[])Reflector.getFieldValue(modelSilverfish, Reflector.ModelSilverfish_bodyParts);
            if (bodyParts == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_BODY.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= bodyParts.length) {
                return null;
            }
            return bodyParts[index];
        }
        String PREFIX_WINGS = "wing";
        if (modelPart.startsWith(PREFIX_WINGS)) {
            brs[] wings = (brs[])Reflector.getFieldValue(modelSilverfish, Reflector.ModelSilverfish_wingParts);
            if (wings == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_WINGS.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= wings.length) {
                return null;
            }
            return wings[index];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body1", "body2", "body3", "body4", "body5", "body6", "body7", "wing1", "wing2", "wing3"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        car render = new car(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

