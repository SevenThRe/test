/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.PositionTextureVertex
 *  net.minecraft.client.model.TexturedQuad
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.dragoncore;

import java.io.Serializable;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class jq
extends TexturedQuad
implements Serializable {
    private static final long k = -9196105177023478504L;
    public String ALLATORIxDEMO;

    public jq(String a2, PositionTextureVertex[] a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        super(a3);
        jq a10;
        a10.ALLATORIxDEMO = a2;
        if (a6 > a8) {
            a6 = a8;
        }
        if (a7 > a9) {
            a7 = a9;
        }
        float a11 = 0.0f / a8;
        float a12 = 0.0f / a9;
        a3[0] = a3[0].setTexturePosition(a6 / a8 - a11, a5 / a9 + a12);
        a3[1] = a3[1].setTexturePosition(a4 / a8 + a11, a5 / a9 + a12);
        a3[2] = a3[2].setTexturePosition(a4 / a8 + a11, a7 / a9 - a12);
        a3[3] = a3[3].setTexturePosition(a6 / a8 - a11, a7 / a9 - a12);
    }

    @SideOnly(value=Side.CLIENT)
    public void draw(BufferBuilder a2, float a3) {
        jq a4;
        Vec3d a5 = a4.vertexPositions[1].vector3D.subtractReverse(a4.vertexPositions[0].vector3D);
        Vec3d a6 = a4.vertexPositions[1].vector3D.subtractReverse(a4.vertexPositions[2].vector3D);
        Vec3d a7 = a6.crossProduct(a5).normalize();
        float a8 = (float)a7.x;
        float a9 = (float)a7.y;
        float a10 = (float)a7.z;
        a2.begin(7, DefaultVertexFormats.OLDMODEL_POSITION_TEX_NORMAL);
        for (int a11 = 0; a11 < 4; ++a11) {
            PositionTextureVertex a12 = a4.vertexPositions[a11];
            a2.pos(a12.vector3D.x * (double)a3, a12.vector3D.y * (double)a3, a12.vector3D.z * (double)a3).tex((double)a12.texturePositionX, (double)a12.texturePositionY).normal(a8, a9, a10).endVertex();
        }
        Tessellator.getInstance().draw();
    }
}

