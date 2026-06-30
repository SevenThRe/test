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
        if (!(a9.func_177087_b() instanceof ModelBiped)) {
            return;
        }
        ItemStack[] a32 = on.r((UUID)a2.func_110124_au()).m;
        int n3 = n2 = 0;
        while (n3 < a32.length) {
            EntityEquipmentSlot a42 = EntityEquipmentSlot.values()[n2 + 2];
            a9.setItemStackToSlot(a2, a42, a32[n2++]);
            n3 = n2;
        }
        ModelBiped modelBiped = (ModelBiped)a9.func_177087_b();
        ModelPlayer a42 = modelBiped instanceof ModelPlayer ? (ModelPlayer)modelBiped : null;
        ModelBiped modelBiped2 = modelBiped;
        modelBiped2.field_78116_c.field_78807_k = false;
        modelBiped2.field_178720_f.field_78807_k = false;
        modelBiped2.field_178722_k.field_78807_k = false;
        modelBiped2.field_178721_j.field_78807_k = false;
        modelBiped2.field_78115_e.field_78807_k = false;
        modelBiped2.field_178724_i.field_78807_k = false;
        modelBiped2.field_178723_h.field_78807_k = false;
        if (a42 != null) {
            ModelPlayer modelPlayer = a42;
            modelPlayer.field_178733_c.field_78807_k = false;
            modelPlayer.field_178731_d.field_78807_k = false;
            modelPlayer.field_178734_a.field_78807_k = false;
            modelPlayer.field_178732_b.field_78807_k = false;
        }
    }

    @Shadow
    public abstract ModelBase func_177087_b();

    /*
     * Unable to fully structure code
     */
    @Inject(method={"doRender(Lnet/minecraft/entity/EntityLivingBase;DDDFF)V"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_doRender_HEAD(T a, double a, double a, double a, float a, float a, CallbackInfo a) {
        block20: {
            block19: {
                block18: {
                    if (!(a.func_177087_b() instanceof ModelBiped)) {
                        return;
                    }
                    a = zh.g != false || a == Minecraft.func_71410_x().field_71439_g;
                    v0 = a;
                    var3_18 = on.r(v0.func_110124_au());
                    a = zg.r(v0);
                    v1 = var3_18;
                    v1.r();
                    var5_19 = v1.m;
                    a = false;
                    v2 = var7_20 = 0;
                    while (v2 < var5_19.length) {
                        a = EntityEquipmentSlot.values()[var7_20 + 2];
                        var5_19[var7_20++] = a.func_184582_a(a);
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
                                a.setItemStackToSlot(a, a, ItemStack.field_190927_a);
                            } else if (zg.r(a, a.func_188450_d()).size() > 0) {
                                a.setItemStackToSlot(a, a, ItemStack.field_190927_a);
                            } else if (zg.r(a, vn.u).size() > 0) {
                                a.setItemStackToSlot(a, a, ItemStack.field_190927_a);
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
                        a.setItemStackToSlot(a, a, ItemStack.field_190927_a);
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
                var7_21 = (ModelBiped)a.func_177087_b();
                v7 = a = var7_21 instanceof ModelPlayer != false ? (ModelPlayer)var7_21 : null;
                if (!zh.g && a != Minecraft.func_71410_x().field_71439_g) break block19;
                v8 = var7_21.field_78116_c.field_78807_k = var7_21.field_78116_c.field_78807_k != false || var3_18.s != false || a != false;
                if (var7_21.field_178720_f.field_78807_k) ** GOTO lbl-1000
                v9 = var3_18;
                if (v9.s | v9.h || a) lbl-1000:
                // 2 sources

                {
                    v10 = true;
                } else {
                    v10 = false;
                }
                var7_21.field_178720_f.field_78807_k = v10;
                var7_21.field_178722_k.field_78807_k = var7_21.field_178722_k.field_78807_k != false || var3_18.w != false || a != false;
                var7_21.field_178721_j.field_78807_k = var7_21.field_178721_j.field_78807_k != false || var3_18.v != false || a != false;
                var7_21.field_78115_e.field_78807_k = var7_21.field_78115_e.field_78807_k != false || var3_18.b != false || a != false;
                var7_21.field_178724_i.field_78807_k = var7_21.field_178724_i.field_78807_k != false || var3_18.g != false || a != false;
                v11 = var7_21.field_178723_h.field_78807_k = var7_21.field_178723_h.field_78807_k != false || var3_18.e != false || a != false;
                if (a != null) {
                    a.field_178730_v.field_78807_k = var7_21.field_78115_e.field_78807_k != false || var3_18.b != false || a != false;
                    a.field_178733_c.field_78807_k = a.field_178733_c.field_78807_k != false || var3_18.w != false || var3_18.j != false || a != false;
                    a.field_178731_d.field_78807_k = a.field_178731_d.field_78807_k != false || var3_18.v != false || var3_18.t != false || a != false;
                    a.field_178734_a.field_78807_k = a.field_178734_a.field_78807_k != false || var3_18.g != false || var3_18.l != false || a != false;
                    a.field_178732_b.field_78807_k = a.field_178732_b.field_78807_k != false || var3_18.e != false || var3_18.r != false || a != false;
                    return;
                }
                break block20;
            }
            v12 = var7_21;
            v12.field_78116_c.field_78807_k = false;
            v12.field_178720_f.field_78807_k = false;
            v12.field_178722_k.field_78807_k = false;
            v12.field_178721_j.field_78807_k = false;
            v12.field_78115_e.field_78807_k = false;
            v12.field_178724_i.field_78807_k = false;
            v12.field_178723_h.field_78807_k = false;
            if (a != null) {
                v13 = a;
                v13.field_178730_v.field_78807_k = false;
                v13.field_178733_c.field_78807_k = false;
                v13.field_178731_d.field_78807_k = false;
                v13.field_178734_a.field_78807_k = false;
                v13.field_178732_b.field_78807_k = false;
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
                a4 = ItemStack.field_190927_a;
            }
            entityPlayer.field_71071_by.field_70460_b.set(a3.func_188454_b(), (Object)a4);
            return;
        }
        a2.func_184201_a(a3, a4);
    }
}

