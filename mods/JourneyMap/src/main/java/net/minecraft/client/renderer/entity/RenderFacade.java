/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 */
package net.minecraft.client.renderer.entity;

import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderFacade
extends Render {
    public RenderFacade(RenderManager unused) {
        super(unused);
    }

    public static ResourceLocation getEntityTexture(Render render, Entity entity) {
        return render.getEntityTexture(entity);
    }

    protected ResourceLocation getEntityTexture(Entity entity) {
        return null;
    }

    public void doRender(Entity entity, double var2, double var4, double var6, float var8, float var9) {
    }
}

