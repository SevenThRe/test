/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  aai
 *  bib
 *  bqf
 *  bqz
 *  brs
 *  bzf
 *  cau
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;

public class ModelAdapterSnowman
extends ModelAdapter {
    public ModelAdapterSnowman() {
        super(aai.class, "snow_golem", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new bqz();
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof bqz)) {
            return null;
        }
        bqz modelSnowman = (bqz)model;
        if (modelPart.equals("body")) {
            return modelSnowman.a;
        }
        if (modelPart.equals("body_bottom")) {
            return modelSnowman.b;
        }
        if (modelPart.equals("head")) {
            return modelSnowman.c;
        }
        if (modelPart.equals("left_hand")) {
            return modelSnowman.e;
        }
        if (modelPart.equals("right_hand")) {
            return modelSnowman.d;
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"body", "body_bottom", "head", "right_hand", "left_hand"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        cau render = new cau(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

