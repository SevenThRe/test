/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBiped
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemStack
 */
package eos.moe.armourers.mixins;

import eos.moe.armourers.fk;
import eos.moe.armourers.on;
import eos.moe.armourers.vn;
import eos.moe.armourers.zg;
import eos.moe.armourers.zh;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderLivingBase.class})
public abstract class MixinEntityRenderer<T extends EntityLivingBase>
extends Render<T> {
    @Inject(method={"doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_doRender_RETURN(T a2, double a32, double a42, double a5, float a6, float a7, CallbackInfo a8) {
        int n2;
        MixinEntityRenderer a9;
        if (!(a9.getMainModel() instanceof ModelBiped)) {
            return;
        }
        ItemStack[] a32 = on.r((UUID)a2.getUniqueID()).m;
        int n3 = n2 = 0;
        while (n3 < a32.length) {
            EntityEquipmentSlot a42 = EntityEquipmentSlot.values()[n2 + 2];
            a9.setItemStackToSlot(a2, a42, a32[n2++]);
            n3 = n2;
        }
        ModelBiped modelBiped = (ModelBiped)a9.getMainModel();
        ModelPlayer a42 = modelBiped instanceof ModelPlayer ? (ModelPlayer)modelBiped : null;
        ModelBiped modelBiped2 = modelBiped;
        modelBiped2.bipedHead.isHidden = false;
        modelBiped2.bipedHeadwear.isHidden = false;
        modelBiped2.bipedLeftLeg.isHidden = false;
        modelBiped2.bipedRightLeg.isHidden = false;
        modelBiped2.bipedBody.isHidden = false;
        modelBiped2.bipedLeftArm.isHidden = false;
        modelBiped2.bipedRightArm.isHidden = false;
        if (a42 != null) {
            ModelPlayer modelPlayer = a42;
            modelPlayer.bipedLeftLegwear.isHidden = false;
            modelPlayer.bipedRightLegwear.isHidden = false;
            modelPlayer.bipedLeftArmwear.isHidden = false;
            modelPlayer.bipedRightArmwear.isHidden = false;
        }
    }

    @Shadow
    public abstract ModelBase getMainModel();

    /*
     * Unable to fully structure code
     */
    @Inject(method={"doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_doRender_HEAD(T a, double a, double a, double a, float a, float a, CallbackInfo a) {
        block20: {
            block19: {
                block18: {
                    if (!(a.getMainModel() instanceof ModelBiped)) {
                        return;
                    }
                    a = zh.g != false || a == Minecraft.getMinecraft().player;
                    v0 = a;
                    var3_18 = on.r(v0.getUniqueID());
                    a = zg.r(v0);
                    v1 = var3_18;
                    v1.r();
                    var5_19 = v1.m;
                    a = false;
                    v2 = var7_20 = 0;
                    while (v2 < var5_19.length) {
                        a = EntityEquipmentSlot.values()[var7_20 + 2];
                        var5_19[var7_20++] = a.getItemStackFromSlot(a);
                        v2 = var7_20;
                    }
                    v3 = var7_20 = 0;
                    while (v3 < var5_19.length) {
                        a = EntityEquipmentSlot.values()[var7_20 + 2];
                        if (a) {
                            a = fk.r(var5_19[var7_20]);
                            if (a != null) {
                                if ("outfit".equals(a.r())) {
                                    v4 = a = true;
                                    break block18;
                                }
                                a.setItemStackToSlot(a, a, ItemStack.EMPTY);
                            } else if (zg.r(a, a.getName()).size() > 0) {
                                a.setItemStackToSlot(a, a, ItemStack.EMPTY);
                            } else if (zg.r(a, vn.u).size() > 0) {
                                a.setItemStackToSlot(a, a, ItemStack.EMPTY);
                            }
                        }
                        v3 = ++var7_20;
                    }
                    v4 = a;
                }
                if (v4) {
                    v5 = var7_20 = 0;
                    while (v5 < var5_19.length) {
                        a = EntityEquipmentSlot.values()[var7_20 + 2];
                        a.setItemStackToSlot(a, a, ItemStack.EMPTY);
                        v5 = ++var7_20;
                    }
                }
                for (fk a : zg.r(a, "any")) {
                    a = a.r();
                    v6 = var3_18;
                    if (a != null) {
                        v6.r(a);
                        continue;
                    }
                    v6.r(a.r());
                }
                var3_18.r(a);
                var7_21 = (ModelBiped)a.getMainModel();
                v7 = a = var7_21 instanceof ModelPlayer != false ? (ModelPlayer)var7_21 : null;
                if (!zh.g && a != Minecraft.getMinecraft().player) break block19;
                v8 = var7_21.bipedHead.isHidden = var7_21.bipedHead.isHidden != false || var3_18.s != false || a != false;
                if (var7_21.bipedHeadwear.isHidden) ** GOTO lbl-1000
                v9 = var3_18;
                if (v9.s | v9.h || a) lbl-1000:
                // 2 sources

                {
                    v10 = true;
                } else {
                    v10 = false;
                }
                var7_21.bipedHeadwear.isHidden = v10;
                var7_21.bipedLeftLeg.isHidden = var7_21.bipedLeftLeg.isHidden != false || var3_18.w != false || a != false;
                var7_21.bipedRightLeg.isHidden = var7_21.bipedRightLeg.isHidden != false || var3_18.v != false || a != false;
                var7_21.bipedBody.isHidden = var7_21.bipedBody.isHidden != false || var3_18.b != false || a != false;
                var7_21.bipedLeftArm.isHidden = var7_21.bipedLeftArm.isHidden != false || var3_18.g != false || a != false;
                v11 = var7_21.bipedRightArm.isHidden = var7_21.bipedRightArm.isHidden != false || var3_18.e != false || a != false;
                if (a != null) {
                    a.bipedBodyWear.isHidden = var7_21.bipedBody.isHidden != false || var3_18.b != false || a != false;
                    a.bipedLeftLegwear.isHidden = a.bipedLeftLegwear.isHidden != false || var3_18.w != false || var3_18.j != false || a != false;
                    a.bipedRightLegwear.isHidden = a.bipedRightLegwear.isHidden != false || var3_18.v != false || var3_18.t != false || a != false;
                    a.bipedLeftArmwear.isHidden = a.bipedLeftArmwear.isHidden != false || var3_18.g != false || var3_18.l != false || a != false;
                    a.bipedRightArmwear.isHidden = a.bipedRightArmwear.isHidden != false || var3_18.e != false || var3_18.r != false || a != false;
                    return;
                }
                break block20;
            }
            v12 = var7_21;
            v12.bipedHead.isHidden = false;
            v12.bipedHeadwear.isHidden = false;
            v12.bipedLeftLeg.isHidden = false;
            v12.bipedRightLeg.isHidden = false;
            v12.bipedBody.isHidden = false;
            v12.bipedLeftArm.isHidden = false;
            v12.bipedRightArm.isHidden = false;
            if (a != null) {
                v13 = a;
                v13.bipedBodyWear.isHidden = false;
                v13.bipedLeftLegwear.isHidden = false;
                v13.bipedRightLegwear.isHidden = false;
                v13.bipedLeftArmwear.isHidden = false;
                v13.bipedRightArmwear.isHidden = false;
            }
        }
    }

    protected MixinEntityRenderer(RenderManager a2) {
        super(a2);
        MixinEntityRenderer a3;
    }

    public void setItemStackToSlot(T a2, EntityEquipmentSlot a3, ItemStack a4) {
        if (a2 instanceof EntityPlayer) {
            EntityPlayer entityPlayer = (EntityPlayer)a2;
            if (a4 == null) {
                a4 = ItemStack.EMPTY;
            }
            entityPlayer.inventory.armorInventory.set(a3.getIndex(), (Object)a4);
            return;
        }
        a2.setItemStackToSlot(a3, a4);
    }
}

