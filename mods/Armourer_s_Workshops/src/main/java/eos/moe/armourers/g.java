/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.vertex.VertexFormat
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public interface g {
    public void y();

    public void r(float var1, float var2, float var3, float var4);

    public void r(double var1, double var3, double var5, double var7, double var9);

    public void r(int var1, VertexFormat var2);

    public void r(double var1, double var3, double var5);

    public void r(VertexFormat var1);

    public void r();

    public void r(int var1, int var2);

    public void r(double var1, double var3);

    public void r(byte var1, byte var2, byte var3, byte var4);

    public void r(float var1, float var2, float var3);
}

