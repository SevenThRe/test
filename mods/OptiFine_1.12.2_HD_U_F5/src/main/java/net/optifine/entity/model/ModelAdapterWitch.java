/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  adr
 *  bib
 *  bqf
 *  bri
 *  brs
 *  bzf
 *  cbi
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterWitch
extends ModelAdapter {
    public ModelAdapterWitch() {
        super(adr.class, "witch", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bri(0.0f);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bri)) {
            return null;
        }
        bri modelWitch = (bri)model;
        if (modelPart.equals("mole")) {
            return (brs)Reflector.getFieldValue(modelWitch, Reflector.ModelWitch_mole);
        }
        if (modelPart.equals("hat")) {
            return (brs)Reflector.getFieldValue(modelWitch, Reflector.ModelWitch_hat);
        }
        if (modelPart.equals("head")) {
            return modelWitch.a;
        }
        if (modelPart.equals("body")) {
            return modelWitch.b;
        }
        if (modelPart.equals("arms")) {
            return modelWitch.c;
        }
        if (modelPart.equals("left_leg")) {
            return modelWitch.e;
        }
        if (modelPart.equals("right_leg")) {
            return modelWitch.d;
        }
        if (modelPart.equals("nose")) {
            return modelWitch.f;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"mole", "head", "body", "arms", "right_leg", "left_leg", "nose"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cbi render = new cbi(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

