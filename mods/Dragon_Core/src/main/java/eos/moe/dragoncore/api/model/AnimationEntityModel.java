/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.entity.Entity
 */
package eos.moe.dragoncore.api.model;

import eos.moe.dragoncore.api.model.AnimationModel;
import net.minecraft.entity.Entity;

public interface AnimationEntityModel {
    public AnimationModel getBaseModel();

    public Entity getEntity();

    public void setEntity(Entity var1);
}

