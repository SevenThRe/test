/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.item.ItemStack
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.cv;
import eos.moe.dragoncore.dla;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.ny;
import eos.moe.dragoncore.sq;
import eos.moe.dragoncore.sr;
import eos.moe.dragoncore.vja;
import eos.moe.dragoncore.wka;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.item.ItemStack;

public class px {
    public px() {
        px a2;
    }

    public static void ALLATORIxDEMO(ItemStack a2, cv a3) {
        if (!wka.k) {
            return;
        }
        EntityPlayerSP a4 = Minecraft.func_71410_x().field_71439_g;
        if (a4 == null) {
            return;
        }
        vja a5 = dla.x.ALLATORIxDEMO(a2);
        if (a5 != null) {
            boolean a6 = false;
            String a7 = "hand";
            switch (a3.getCameraTransformType()) {
                case THIRD_PERSON_LEFT_HAND: {
                    a6 = true;
                    a7 = "thirdPersonLeftHand";
                    break;
                }
                case THIRD_PERSON_RIGHT_HAND: {
                    a6 = true;
                    a7 = "thirdPersonRightHand";
                    break;
                }
                case FIRST_PERSON_LEFT_HAND: {
                    a6 = true;
                    a7 = "firstPersonLeftHand";
                    break;
                }
                case FIRST_PERSON_RIGHT_HAND: {
                    a6 = true;
                    a7 = "firstPersonRightHand";
                    break;
                }
                case HEAD: {
                    a7 = "head";
                    break;
                }
                case GROUND: {
                    a7 = "ground";
                    break;
                }
                case GUI: {
                    a7 = "gui";
                    break;
                }
                case FIXED: {
                    a7 = "fixed";
                }
            }
            ny a8 = sq.ALLATORIxDEMO(a5);
            if (a8 == null) {
                return;
            }
            ModelBase a9 = a8.ALLATORIxDEMO();
            sr a10 = sq.ALLATORIxDEMO(a5, a7);
            String a11 = "default";
            if (wka.ALLATORIxDEMO != null && wka.ALLATORIxDEMO != a4) {
                a11 = wka.ALLATORIxDEMO.func_110124_au().toString();
            } else if (a6) {
                a11 = a4.func_110124_au().toString();
            }
            a8.c(a11);
            a8.ALLATORIxDEMO(a11);
            GlStateManager.func_179094_E();
            if (a3 != cv.y) {
                GlStateManager.func_179137_b((double)0.5, (double)0.5, (double)0.5);
                GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
            }
            if (a10 != null) {
                a10.ALLATORIxDEMO();
            }
            a8.ALLATORIxDEMO();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            a9.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
            if (a8.ALLATORIxDEMO()) {
                float a12 = OpenGlHelper.lastBrightnessX;
                float a13 = OpenGlHelper.lastBrightnessY;
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)a13);
                GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                a9.func_78088_a(null, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0625f);
                OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)a12, (float)a13);
            }
            if (a9 instanceof jv) {
                ((jv)a9).clearData();
            }
            GlStateManager.func_179121_F();
        }
    }
}

