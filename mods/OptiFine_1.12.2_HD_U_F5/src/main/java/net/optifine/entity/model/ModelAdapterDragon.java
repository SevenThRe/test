/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  abd
 *  bib
 *  bqf
 *  brn
 *  brs
 *  bzc
 *  bzf
 */
package net.optifine.entity.model;

import net.optifine.entity.model.IEntityRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.reflect.Reflector;

public class ModelAdapterDragon
extends ModelAdapter {
    public ModelAdapterDragon() {
        super(abd.class, "dragon", 0.5f);
    }

    @Override
    public bqf makeModel() {
        return new brn(0.0f);
    }

    @Override
    public brs getModelRenderer(bqf model, String modelPart) {
        if (!(model instanceof brn)) {
            return null;
        }
        brn modelDragon = (brn)model;
        if (modelPart.equals("head")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 0);
        }
        if (modelPart.equals("spine")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 1);
        }
        if (modelPart.equals("jaw")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 2);
        }
        if (modelPart.equals("body")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 3);
        }
        if (modelPart.equals("rear_leg")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 4);
        }
        if (modelPart.equals("front_leg")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 5);
        }
        if (modelPart.equals("rear_leg_tip")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 6);
        }
        if (modelPart.equals("front_leg_tip")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 7);
        }
        if (modelPart.equals("rear_foot")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 8);
        }
        if (modelPart.equals("front_foot")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 9);
        }
        if (modelPart.equals("wing")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 10);
        }
        if (modelPart.equals("wing_tip")) {
            return (brs)Reflector.getFieldValue(modelDragon, Reflector.ModelDragon_ModelRenderers, 11);
        }
        return null;
    }

    @Override
    public String[] getModelRendererNames() {
        return new String[]{"head", "spine", "jaw", "body", "rear_leg", "front_leg", "rear_leg_tip", "front_leg_tip", "rear_foot", "front_foot", "wing", "wing_tip"};
    }

    @Override
    public IEntityRenderer makeEntityRender(bqf modelBase, float shadowSize) {
        bzf renderManager = bib.z().ac();
        bzc render = new bzc(renderManager);
        render.f = modelBase;
        render.c = shadowSize;
        return render;
    }
}

