/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBiped$ArmPose
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.EnumAction
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemFood
 *  net.minecraft.item.ItemStack
 *  net.minecraft.item.ItemSword
 *  net.minecraft.util.EnumHand
 *  net.minecraft.util.EnumHandSide
 */
package eos.moe.dragoncore;

import blockbuster.utils.Interpolations;
import eos.moe.dragoncore.oz;
import eos.moe.dragoncore.xz;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumHandSide;

public class jz {
    public jz() {
        jz a2;
    }

    public static void ALLATORIxDEMO(xz a2, float a3) {
        EntityLivingBase a4 = a2.ALLATORIxDEMO();
        if (a4 instanceof EntityPlayer) {
            jz.ALLATORIxDEMO((EntityPlayer)a4, a2, a3);
        } else {
            jz.ALLATORIxDEMO(a4, a2, a3);
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(EntityLivingBase a2, xz a3, float a4) {
        if (a2.func_110143_aJ() <= 0.0f) {
            a3.ALLATORIxDEMO("death", new String[0]);
            return;
        }
        float a5 = 0.0f;
        if (a2.func_70089_S()) {
            boolean a6;
            a5 = Interpolations.lerp(a2.field_184618_aE, a2.field_70721_aZ, a4);
            if (a5 > 1.0f) {
                a5 = 1.0f;
            }
            boolean bl2 = a6 = a5 <= -0.15f || a5 >= 0.03f;
            if (a6) {
                a3.ALLATORIxDEMO("walk", new String[0]);
            } else {
                a3.ALLATORIxDEMO("stand", new String[0]);
            }
            if (a2.field_82175_bq) {
                a3.ALLATORIxDEMO("attack", new String[0]);
            }
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(EntityPlayer a2, xz a3, float a4) {
        EnumHandSide a5;
        oz a6 = (oz)a3.o;
        a3.ALLATORIxDEMO("cape", new String[0]);
        if (a2.func_70089_S() && a2.func_70608_bn()) {
            a3.ALLATORIxDEMO("sleeping", new String[0]);
            a3.ALLATORIxDEMO("sneak-end", new String[0]);
        } else if (a2.func_184218_aH()) {
            if (a2.func_184187_bx() instanceof EntityLivingBase) {
                a3.ALLATORIxDEMO("riding", new String[0]);
            } else {
                a3.ALLATORIxDEMO("sitting", new String[0]);
            }
            a3.ALLATORIxDEMO("sneak-end", new String[0]);
        } else if (a2.func_184599_cB() > 4) {
            a3.ALLATORIxDEMO("elytra", new String[0]);
            a3.ALLATORIxDEMO("sneak-end", new String[0]);
            a3.ALLATORIxDEMO("torch-end", new String[0]);
        } else if (a6.s()) {
            a3.ALLATORIxDEMO("ladderClimb", new String[0]);
            a3.ALLATORIxDEMO("sneak-end", new String[0]);
            a3.ALLATORIxDEMO("torch-end", new String[0]);
        } else if (a2.func_70090_H()) {
            a3.ALLATORIxDEMO("swimming", new String[0]);
            a3.ALLATORIxDEMO("sneak-end", new String[0]);
            a3.ALLATORIxDEMO("torch-end", new String[0]);
        } else if (!a6.x() || a6.z() < 2.0f) {
            if (a6.h()) {
                a3.ALLATORIxDEMO("flying", new String[0]);
            } else if (a6.d() > 10.0f) {
                a3.ALLATORIxDEMO("falling", new String[0]);
            } else if (a2.func_70051_ag()) {
                a3.ALLATORIxDEMO("sprint-jump", new String[0]);
            } else {
                a3.ALLATORIxDEMO("jump", new String[0]);
            }
            a3.ALLATORIxDEMO("sneak-end", new String[0]);
            a3.ALLATORIxDEMO("torch-end", new String[0]);
        } else {
            if (a6.f()) {
                a3.ALLATORIxDEMO("stand", new String[0]);
                a3.ALLATORIxDEMO("torchHolding", new String[0]);
            } else if (a2.func_70051_ag()) {
                a3.ALLATORIxDEMO("sprint", new String[0]);
                a3.ALLATORIxDEMO("torch-end", new String[0]);
            } else {
                a3.ALLATORIxDEMO("walk", new String[0]);
                a3.ALLATORIxDEMO("torchHolding", new String[0]);
            }
            if (a2.func_70093_af()) {
                a3.ALLATORIxDEMO("sneak", new String[0]);
            } else {
                a3.ALLATORIxDEMO("sneak-end", new String[0]);
            }
        }
        if (a2.func_70089_S() && a2.func_70608_bn()) {
            a3.ALLATORIxDEMO("action-end", new String[0]);
            return;
        }
        EnumHandSide a7 = a2.func_184591_cq();
        EnumHandSide a8 = a7 == EnumHandSide.RIGHT ? EnumHandSide.LEFT : EnumHandSide.RIGHT;
        ItemStack a9 = a2.func_184614_ca();
        ItemStack a10 = a2.func_184592_cb();
        Item a11 = a2.func_184607_cu().func_77973_b();
        ModelBiped.ArmPose a12 = jz.ALLATORIxDEMO(a2, a9);
        ModelBiped.ArmPose a13 = jz.ALLATORIxDEMO(a2, a10);
        EnumHandSide enumHandSide = a5 = a2.func_184600_cs() == EnumHand.MAIN_HAND ? a7 : a8;
        if (jz.ALLATORIxDEMO(a12, a13)) {
            if (a12 == ModelBiped.ArmPose.BLOCK) {
                a3.ALLATORIxDEMO("shield-primary", new String[0]);
            } else {
                a3.ALLATORIxDEMO("shield-off", new String[0]);
            }
        } else if (jz.c(a11)) {
            if (a5 == a7) {
                a3.ALLATORIxDEMO("eating-primary", new String[0]);
            } else {
                a3.ALLATORIxDEMO("eating-off", new String[0]);
            }
        } else if (jz.c(a12, a13)) {
            if (a12 == ModelBiped.ArmPose.BOW_AND_ARROW) {
                a3.ALLATORIxDEMO("bow-primary", new String[0]);
            } else {
                a3.ALLATORIxDEMO("bow-off", new String[0]);
            }
        } else if (jz.ALLATORIxDEMO(a9.func_77973_b()) || a9.func_190926_b()) {
            if (a9.func_190926_b()) {
                if (a6.k() < 60.0f) {
                    a3.ALLATORIxDEMO("punch", new String[0]);
                } else {
                    a3.ALLATORIxDEMO("base-end", new String[0]);
                }
            } else if (a6.k() < 60.0f) {
                a3.ALLATORIxDEMO("sword", new String[0]);
            } else {
                a3.ALLATORIxDEMO("base-end", new String[0]);
            }
        } else if (a2.field_82175_bq) {
            if (a5 == a7) {
                a3.ALLATORIxDEMO("harvest-primary", new String[0]);
            } else {
                a3.ALLATORIxDEMO("harvest-off", new String[0]);
            }
        } else {
            a3.ALLATORIxDEMO("action-end", new String[0]);
        }
    }

    private static /* synthetic */ ModelBiped.ArmPose ALLATORIxDEMO(EntityPlayer a2, ItemStack a3) {
        if (!a3.func_190926_b()) {
            if (a2.func_184605_cv() > 0) {
                EnumAction a4 = a3.func_77975_n();
                if (a4 == EnumAction.BLOCK) {
                    return ModelBiped.ArmPose.BLOCK;
                }
                if (a4 == EnumAction.BOW) {
                    return ModelBiped.ArmPose.BOW_AND_ARROW;
                }
            }
            return ModelBiped.ArmPose.ITEM;
        }
        return ModelBiped.ArmPose.EMPTY;
    }

    public static boolean c(Item a2) {
        return a2 instanceof ItemFood;
    }

    public static boolean c(ModelBiped.ArmPose a2, ModelBiped.ArmPose a3) {
        return a2 == ModelBiped.ArmPose.BOW_AND_ARROW || a3 == ModelBiped.ArmPose.BOW_AND_ARROW;
    }

    public static boolean ALLATORIxDEMO(ModelBiped.ArmPose a2, ModelBiped.ArmPose a3) {
        return a2 == ModelBiped.ArmPose.BLOCK || a3 == ModelBiped.ArmPose.BLOCK;
    }

    public static boolean ALLATORIxDEMO(Item a2) {
        return a2 instanceof ItemSword;
    }
}

