/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bqf
 *  brs
 */
package net.optifine.entity.model;

import java.util.ArrayList;
import net.optifine.entity.model.IEntityRenderer;

public abstract class ModelAdapter {
    private Class entityClass;
    private String name;
    private float shadowSize;
    private String[] aliases;

    public ModelAdapter(Class entityClass, String name, float shadowSize) {
        this.entityClass = entityClass;
        this.name = name;
        this.shadowSize = shadowSize;
    }

    public ModelAdapter(Class entityClass, String name, float shadowSize, String[] aliases) {
        this.entityClass = entityClass;
        this.name = name;
        this.shadowSize = shadowSize;
        this.aliases = aliases;
    }

    public Class getEntityClass() {
        return this.entityClass;
    }

    public String getName() {
        return this.name;
    }

    public String[] getAliases() {
        return this.aliases;
    }

    public float getShadowSize() {
        return this.shadowSize;
    }

    public abstract bqf makeModel();

    public abstract brs getModelRenderer(bqf var1, String var2);

    public abstract String[] getModelRendererNames();

    public abstract IEntityRenderer makeEntityRender(bqf var1, float var2);

    public brs[] getModelRenderers(bqf model) {
        String[] names = this.getModelRendererNames();
        ArrayList<brs> list = new ArrayList<brs>();
        for (int i = 0; i < names.length; ++i) {
            String name = names[i];
            brs mr = this.getModelRenderer(model, name);
            if (mr == null) continue;
            list.add(mr);
        }
        brs[] mrs = list.toArray(new brs[list.size()]);
        return mrs;
    }
}

