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
        a3[0] = a3[0].func_78240_a(a6 / a8 - a11, a5 / a9 + a12);
        a3[1] = a3[1].func_78240_a(a4 / a8 + a11, a5 / a9 + a12);
        a3[2] = a3[2].func_78240_a(a4 / a8 + a11, a7 / a9 - a12);
        a3[3] = a3[3].func_78240_a(a6 / a8 - a11, a7 / a9 - a12);
    }

    @SideOnly(value=Side.CLIENT)
    public void func_178765_a(BufferBuilder a2, float a3) {
        jq a4;
        Vec3d a5 = a4.field_78239_a[1].field_78243_a.func_72444_a(a4.field_78239_a[0].field_78243_a);
        Vec3d a6 = a4.field_78239_a[1].field_78243_a.func_72444_a(a4.field_78239_a[2].field_78243_a);
        Vec3d a7 = a6.func_72431_c(a5).func_72432_b();
        float a8 = (float)a7.field_72450_a;
        float a9 = (float)a7.field_72448_b;
        float a10 = (float)a7.field_72449_c;
        a2.func_181668_a(7, DefaultVertexFormats.field_181703_c);
        for (int a11 = 0; a11 < 4; ++a11) {
            PositionTextureVertex a12 = a4.field_78239_a[a11];
            a2.func_181662_b(a12.field_78243_a.field_72450_a * (double)a3, a12.field_78243_a.field_72448_b * (double)a3, a12.field_78243_a.field_72449_c * (double)a3).func_187315_a((double)a12.field_78241_b, (double)a12.field_78242_c).func_181663_c(a8, a9, a10).func_181675_d();
        }
        Tessellator.func_178181_a().func_78381_a();
    }
}

