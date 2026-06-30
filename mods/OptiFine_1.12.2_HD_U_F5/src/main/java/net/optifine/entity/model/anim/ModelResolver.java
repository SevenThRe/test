/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  avj
 *  bqf
 *  brs
 */
package net.optifine.entity.model.anim;

import net.optifine.entity.model.CustomModelRenderer;
import net.optifine.entity.model.ModelAdapter;
import net.optifine.entity.model.anim.IModelResolver;
import net.optifine.entity.model.anim.IRenderResolver;
import net.optifine.entity.model.anim.ModelVariableFloat;
import net.optifine.entity.model.anim.ModelVariableType;
import net.optifine.entity.model.anim.RenderResolverEntity;
import net.optifine.entity.model.anim.RenderResolverTileEntity;
import net.optifine.expr.IExpression;

public class ModelResolver
implements IModelResolver {
    private ModelAdapter modelAdapter;
    private bqf model;
    private CustomModelRenderer[] customModelRenderers;
    private brs thisModelRenderer;
    private brs partModelRenderer;
    private IRenderResolver renderResolver;

    public ModelResolver(ModelAdapter modelAdapter, bqf model, CustomModelRenderer[] customModelRenderers) {
        this.modelAdapter = modelAdapter;
        this.model = model;
        this.customModelRenderers = customModelRenderers;
        Class entityClass = modelAdapter.getEntityClass();
        this.renderResolver = avj.class.isAssignableFrom(entityClass) ? new RenderResolverTileEntity() : new RenderResolverEntity();
    }

    @Override
    public IExpression getExpression(String name) {
        ModelVariableFloat mv = this.getModelVariable(name);
        if (mv != null) {
            return mv;
        }
        IExpression param = this.renderResolver.getParameter(name);
        if (param != null) {
            return param;
        }
        return null;
    }

    @Override
    public brs getModelRenderer(String name) {
        if (name == null) {
            return null;
        }
        if (name.indexOf(":") >= 0) {
            String[] parts = Config.tokenize(name, ":");
            brs mr = this.getModelRenderer(parts[0]);
            for (int i = 1; i < parts.length; ++i) {
                String part = parts[i];
                brs mrSub = mr.getChildDeep(part);
                if (mrSub == null) {
                    return null;
                }
                mr = mrSub;
            }
            return mr;
        }
        if (this.thisModelRenderer != null && name.equals("this")) {
            return this.thisModelRenderer;
        }
        if (this.partModelRenderer != null && name.equals("part")) {
            return this.partModelRenderer;
        }
        brs mrPart = this.modelAdapter.getModelRenderer(this.model, name);
        if (mrPart != null) {
            return mrPart;
        }
        for (int i = 0; i < this.customModelRenderers.length; ++i) {
            CustomModelRenderer cmr = this.customModelRenderers[i];
            brs mr = cmr.getModelRenderer();
            if (name.equals(mr.getId())) {
                return mr;
            }
            brs mrChild = mr.getChildDeep(name);
            if (mrChild == null) continue;
            return mrChild;
        }
        return null;
    }

    @Override
    public ModelVariableFloat getModelVariable(String name) {
        String[] parts = Config.tokenize(name, ".");
        if (parts.length != 2) {
            return null;
        }
        String modelName = parts[0];
        String varName = parts[1];
        brs mr = this.getModelRenderer(modelName);
        if (mr == null) {
            return null;
        }
        ModelVariableType varType = ModelVariableType.parse(varName);
        if (varType == null) {
            return null;
        }
        return new ModelVariableFloat(name, mr, varType);
    }

    public void setPartModelRenderer(brs partModelRenderer) {
        this.partModelRenderer = partModelRenderer;
    }

    public void setThisModelRenderer(brs thisModelRenderer) {
        this.thisModelRenderer = thisModelRenderer;
    }
}

