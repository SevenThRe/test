/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  ada
 *  bib
 *  bpu
 *  bqf
 *  brs
 *  bzf
 *  bzp
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterGuardian
extends ModelAdapter {
    public ModelAdapterGuardian() {
        super(ada.class, "guardian", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bpu();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bpu)) {
            return null;
        }
        bpu modelGuardian = (bpu)model;
        if (modelPart.equals("body")) {
            return (brs)Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_body);
        }
        if (modelPart.equals("eye")) {
            return (brs)Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_eye);
        }
        String PREFIX_SPINE = "spine";
        if (modelPart.startsWith(PREFIX_SPINE)) {
            brs[] spines = (brs[])Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_spines);
            if (spines == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_SPINE.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= spines.length) {
                return null;
            }
            return spines[index];
        }
        String PREFIX_TAIL = "tail";
        if (modelPart.startsWith(PREFIX_TAIL)) {
            brs[] tails = (brs[])Reflector.getFieldValue(modelGuardian, Reflector.ModelGuardian_tail);
            if (tails == null) {
                return null;
            }
            String numStr = modelPart.substring(PREFIX_TAIL.length());
            int index = Config.parseInt(numStr, -1);
            if (--index < 0 || index >= tails.length) {
                return null;
            }
            return tails[index];
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "eye", "spine1", "spine2", "spine3", "spine4", "spine5", "spine6", "spine7", "spine8", "spine9", "spine10", "spine11", "spine12", "tail1", "tail2", "tail3"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzp render = new bzp(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

