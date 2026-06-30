/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  cea
 *  fa
 */
package net.minecraftforge.client.model.pipeline;

public interface IVertexConsumer {
    public cea getVertexFormat();

    public void setQuadTint(int var1);

    public void setQuadOrientation(fa var1);

    public void setQuadColored();

    public void put(int var1, float ... var2);
}

