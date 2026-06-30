/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.entity.RenderArrow
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.kf;
import eos.moe.armourers.km;
import eos.moe.armourers.mk;
import eos.moe.armourers.n;
import eos.moe.armourers.rk;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class he
extends RenderArrow {
    private km j = km.t;

    public void func_76986_a(EntityArrow a2, double a3, double a4, double a5, float a6, float a7) {
        he a8;
        super.func_76986_a(a2, a3, a4, a5, a6, a7);
    }

    public he(RenderManager a2) {
        super(a2);
        he a3;
    }

    public ResourceLocation func_110775_a(Entity a2) {
        return null;
    }

    private /* synthetic */ void r(EntityArrow a22, double a3, double a4, double a5, float a6, kf a7, n a8) {
        float f2;
        float f3 = 0.0625f;
        GL11.glPushMatrix();
        GL11.glTranslatef((float)((float)a3), (float)((float)a4), (float)((float)a5));
        EntityArrow entityArrow = a22;
        EntityArrow entityArrow2 = a22;
        GL11.glRotatef((float)(entityArrow.field_70126_B + (entityArrow2.field_70177_z - entityArrow2.field_70126_B) * a6 - 90.0f), (float)0.0f, (float)1.0f, (float)0.0f);
        EntityArrow entityArrow3 = a22;
        GL11.glRotatef((float)(entityArrow.field_70127_C + (entityArrow3.field_70125_A - entityArrow3.field_70127_C) * a6), (float)0.0f, (float)0.0f, (float)1.0f);
        GL11.glTranslatef((float)(2.5f * f3), (float)(0.5f * f3), (float)(0.5f * f3));
        float a22 = 0.05625f;
        a22 = (float)entityArrow.field_70249_b - a6;
        if (f2 > 0.0f) {
            GL11.glRotatef((float)(-MathHelper.func_76126_a((float)(a22 * 3.0f)) * a22), (float)0.0f, (float)0.0f, (float)1.0f);
        }
        GL11.glRotatef((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        rk.j.renderPart(new mk(a7, f3, a8, null, 0.0, true, false, false, null));
        GL11.glPopMatrix();
    }
}

