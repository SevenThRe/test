/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore.api.model;

import eos.moe.dragoncore.api.model.IModelPiece;
import java.util.List;

public interface IModel {
    public void render(float var1);

    public void clearData();

    public List<IModelPiece> getModelPieces();
}

