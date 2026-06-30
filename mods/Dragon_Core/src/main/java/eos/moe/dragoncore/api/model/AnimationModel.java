/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelRenderer
 */
package eos.moe.dragoncore.api.model;

import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import net.minecraft.client.model.ModelRenderer;

public interface AnimationModel {
    public AnimationModelRenderer getPiece(String var1);

    default public ModelRenderer findPiece(String a2) {
        return null;
    }

    default public void clear() {
    }
}

