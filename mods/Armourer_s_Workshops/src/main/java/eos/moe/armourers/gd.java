/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class gd
implements LayerRenderer<EntityLivingBase> {
    private RenderLivingBase j;

    public void doRenderLayer(EntityLivingBase a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        gd a10;
        a2 = a10.j.getMainModel();
        if (a2 instanceof ModelBiped) {
            ModelBiped modelBiped = (ModelBiped)a2;
            modelBiped.bipedRightArm.isHidden = false;
            modelBiped.bipedLeftArm.isHidden = false;
        }
    }

    public boolean shouldCombineTextures() {
        return false;
    }

    public gd(RenderLivingBase a2) {
        gd a3;
        a3.j = a2;
    }
}

