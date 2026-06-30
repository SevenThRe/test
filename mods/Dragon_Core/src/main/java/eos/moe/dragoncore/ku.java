/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix4f
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  org.apache.commons.lang3.tuple.Pair
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.jga;
import eos.moe.dragoncore.jr;
import eos.moe.dragoncore.ww;
import javax.vecmath.Matrix4f;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import org.apache.commons.lang3.tuple.Pair;

public class ku
extends jr {
    private jga ALLATORIxDEMO;

    public jga getIconInfo() {
        ku a2;
        return a2.ALLATORIxDEMO;
    }

    public ku(IBakedModel a2, jga a3) {
        super(null, a2, null);
        ku a4;
        a4.ALLATORIxDEMO = a3;
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType a2) {
        ku a3;
        ww.ALLATORIxDEMO(a3.ALLATORIxDEMO.x());
        if (a3.ALLATORIxDEMO.ALLATORIxDEMO() > 0.0f) {
            GlStateManager.scale((float)a3.ALLATORIxDEMO.ALLATORIxDEMO(), (float)a3.ALLATORIxDEMO.ALLATORIxDEMO(), (float)a3.ALLATORIxDEMO.ALLATORIxDEMO());
        }
        if (a3.ALLATORIxDEMO.c().size() == 3) {
            GlStateManager.translate((float)a3.ALLATORIxDEMO.c().get(0).floatValue(), (float)a3.ALLATORIxDEMO.c().get(1).floatValue(), (float)a3.ALLATORIxDEMO.c().get(2).floatValue());
        }
        if (a2 == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND) {
            if (a3.ALLATORIxDEMO.c()) {
                GlStateManager.translate((double)-0.56, (double)0.5, (double)-0.2);
            } else {
                GlStateManager.translate((double)0.25, (double)0.12, (double)-0.12);
                GlStateManager.rotate((float)-28.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.scale((double)0.85, (double)0.85, (double)0.85);
            }
        }
        if (a2 == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND) {
            if (a3.ALLATORIxDEMO.c()) {
                GlStateManager.translate((double)0.56, (double)0.5, (double)-0.2);
            } else {
                GlStateManager.translate((double)-0.25, (double)0.12, (double)-0.08);
                GlStateManager.rotate((float)-28.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.scale((double)0.85, (double)0.85, (double)0.85);
            }
        }
        if (a2 == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND || a2 == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND) {
            if (a3.ALLATORIxDEMO.ALLATORIxDEMO()) {
                GlStateManager.translate((double)0.0, (double)0.25, (double)0.03);
                GlStateManager.rotate((float)-55.0f, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.rotate((float)-90.0f, (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.scale((double)0.85, (double)0.85, (double)0.85);
            } else {
                GlStateManager.scale((double)0.55, (double)0.55, (double)0.55);
                GlStateManager.translate((double)0.0, (double)0.34, (double)0.115);
            }
        }
        if (a2 == ItemCameraTransforms.TransformType.GROUND) {
            GlStateManager.translate((double)0.0, (double)0.13, (double)0.0);
            GlStateManager.scale((double)0.5, (double)0.5, (double)0.5);
        }
        Pair a4 = a3.y.handlePerspective(a2);
        return Pair.of((Object)new ku((IBakedModel)a4.getKey(), a3.ALLATORIxDEMO), (Object)a4.getRight());
    }
}

