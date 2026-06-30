/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers.mixins;

import eos.moe.armourers.fk;
import eos.moe.armourers.gg;
import eos.moe.armourers.ih;
import eos.moe.armourers.tg;
import eos.moe.armourers.xd;
import eos.moe.armourers.zh;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={RenderItem.class})
public class MixinRenderItem {
    private static /* synthetic */ void s() {
    }

    public MixinRenderItem() {
        MixinRenderItem a2;
    }

    private static /* synthetic */ void renderByItem2(ItemStack a2, fk a3, IBakedModel a4) {
        if (a4 instanceof gg) {
            gg gg2 = (gg)a4;
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)0.0f, (float)0.01f, (float)0.0f);
            GlStateManager.translate((double)0.5, (double)0.5, (double)0.5);
            GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
            tg.r(a3.r(), 0.0625f);
            GlStateManager.popMatrix();
            return;
        }
        Minecraft minecraft = Minecraft.getMinecraft();
        GL11.glPushMatrix();
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        Minecraft minecraft2 = minecraft;
        minecraft2.profiler.startSection("armourersItemSkin");
        GL11.glPushAttrib((int)8192);
        xd.z();
        GL11.glEnable((int)2884);
        GlStateManager.translate((float)0.5f, (float)-0.125f, (float)0.0f);
        GlStateManager.scale((float)0.4f, (float)0.4f, (float)0.4f);
        fk fk2 = a3;
        double d2 = fk2.r().getScale();
        double d3 = fk2.r().getTrans();
        double d4 = d2;
        GlStateManager.scale((double)d4, (double)d4, (double)d4);
        GlStateManager.translate((double)0.0, (double)d3, (double)0.0);
        zh.c = 4.0f;
        tg.r(a3.r(), 0.0625f);
        GL11.glPopAttrib();
        minecraft2.profiler.endSection();
        GL11.glPopMatrix();
    }

    @Inject(method={"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_renderItem(ItemStack a2, IBakedModel a3, CallbackInfo a4) {
        if (a2.isEmpty()) {
            return;
        }
        if (zh.l) {
            fk fk2 = fk.r(a2);
            if (fk2 != null && fk2.r() != null) {
                a4.cancel();
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)-0.5f, (float)-0.5f, (float)-0.5f);
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.enableRescaleNormal();
                MixinRenderItem.renderByItem(a2, Thread.currentThread().getStackTrace()[3].getClassName());
                GlStateManager.popMatrix();
            }
            if (fk2 != null && fk2.r() != null) {
                a4.cancel();
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)-0.5f, (float)-0.5f, (float)-0.5f);
                GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
                GlStateManager.enableRescaleNormal();
                MixinRenderItem.renderByItem2(a2, fk2, a3);
                GlStateManager.popMatrix();
            }
        }
    }

    @Inject(method={"getItemModelWithOverrides"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_getItemModelWithOverrides(ItemStack a2, World a3, EntityLivingBase a4, CallbackInfoReturnable<IBakedModel> a5) {
        if (a2.isEmpty() || !zh.l) {
            return;
        }
        if ((a2 = fk.r((ItemStack)a2)) != null && ((fk)a2).r() != null && ((fk)a2).r().getTransformBakedModel() != null) {
            a5.setReturnValue(((fk)a2).r().getTransformBakedModel());
        }
    }

    private static /* synthetic */ void renderByItem(ItemStack a2, String a3) {
        ItemStack itemStack;
        Minecraft minecraft = Minecraft.getMinecraft();
        GL11.glPushMatrix();
        GL11.glScalef((float)-1.0f, (float)-1.0f, (float)1.0f);
        GL11.glRotatef((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        minecraft.profiler.startSection("armourersItemSkin");
        GL11.glPushAttrib((int)8192);
        xd.z();
        GL11.glEnable((int)2884);
        GlStateManager.translate((float)0.5f, (float)-0.5f, (float)0.0f);
        GlStateManager.scale((float)0.8f, (float)0.8f, (float)0.8f);
        zh.c = 4.0f;
        if (!"net.minecraft.client.renderer.entity.RenderEntityItem".equals(a3)) {
            GlStateManager.rotate((float)30.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.rotate((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            itemStack = a2;
        } else {
            GlStateManager.translate((float)-0.1f, (float)0.0f, (float)-0.7f);
            GlStateManager.translate((float)0.0f, (float)-1.0f, (float)0.0f);
            GlStateManager.scale((float)2.0f, (float)2.0f, (float)2.0f);
            itemStack = a2;
        }
        ih.r(itemStack, true, 16.0f, 16.0f);
        GL11.glPopAttrib();
        minecraft.profiler.endSection();
        GL11.glPopMatrix();
    }
}

