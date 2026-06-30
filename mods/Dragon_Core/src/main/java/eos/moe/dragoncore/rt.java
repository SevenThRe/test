/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.EnumHandSide
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.mt;
import eos.moe.dragoncore.pw;
import eos.moe.dragoncore.xz;
import java.util.ArrayList;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHandSide;

public class rt
implements LayerRenderer<EntityLivingBase> {
    public final RenderLivingBase<?> ALLATORIxDEMO;

    public rt(RenderLivingBase<?> a2) {
        rt a3;
        a3.ALLATORIxDEMO = a2;
    }

    public void doRenderLayer(EntityLivingBase a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        ItemStack a10;
        boolean a11 = a2.getPrimaryHand() == EnumHandSide.RIGHT;
        ItemStack a12 = a11 ? a2.getHeldItemOffhand() : a2.getHeldItemMainhand();
        ItemStack itemStack = a10 = a11 ? a2.getHeldItemMainhand() : a2.getHeldItemOffhand();
        if (!a12.isEmpty() || !a10.isEmpty()) {
            rt a13;
            GlStateManager.pushMatrix();
            a13.ALLATORIxDEMO(a2, a10, ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND, EnumHandSide.RIGHT);
            a13.ALLATORIxDEMO(a2, a12, ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND, EnumHandSide.LEFT);
            GlStateManager.popMatrix();
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(EntityLivingBase a2, ItemStack a3, ItemCameraTransforms.TransformType a4, EnumHandSide a5) {
        if (!a3.isEmpty()) {
            xz a6;
            rt a7;
            ModelBase a8 = a7.ALLATORIxDEMO.getMainModel();
            if (!(a8 instanceof pw)) {
                return;
            }
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)0.0f, (float)1.5f, (float)0.0f);
            if (a5 == EnumHandSide.LEFT) {
                if (!a7.translateToHand("core_left_hand")) {
                    GlStateManager.popMatrix();
                    return;
                }
            } else if (a5 == EnumHandSide.RIGHT && !a7.translateToHand("core_right_hand")) {
                GlStateManager.popMatrix();
                return;
            }
            if ((a6 = (xz)dt.k.ALLATORIxDEMO.getIfPresent((Object)a2.getUniqueID())) != null) {
                a6.c.ALLATORIxDEMO();
            }
            GlStateManager.rotate((float)-90.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            boolean a9 = a5 == EnumHandSide.LEFT;
            Minecraft.getMinecraft().getItemRenderer().renderItemSide(a2, a3, a4, a9);
            GlStateManager.popMatrix();
        }
    }

    public boolean translateToHand(String a2) {
        rt a3;
        ModelBase a4 = a3.ALLATORIxDEMO.getMainModel();
        if (a4 instanceof pw) {
            pw a5 = (pw)a4;
            ArrayList<mt> a6 = new ArrayList<mt>();
            mt a7 = a5.getBaseModel().getPiece(a2);
            while (a7 != null) {
                a6.add(a7);
                a7 = a7.m;
            }
            Collections.reverse(a6);
            for (mt a8 : a6) {
                a8.applyTransAndRotations(0.0625f);
            }
            return a6.size() > 0;
        }
        return false;
    }

    public boolean shouldCombineTextures() {
        return false;
    }
}

