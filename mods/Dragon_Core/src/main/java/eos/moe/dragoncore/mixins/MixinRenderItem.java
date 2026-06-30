/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.CooldownTracker
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.world.World
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.cv;
import eos.moe.dragoncore.dla;
import eos.moe.dragoncore.interfaces.RenderStatus;
import eos.moe.dragoncore.jia;
import eos.moe.dragoncore.jr;
import eos.moe.dragoncore.ku;
import eos.moe.dragoncore.px;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.sja;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.wo;
import java.awt.Color;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntityItemStackRenderer;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.CooldownTracker;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={RenderItem.class})
public abstract class MixinRenderItem {
    @Shadow
    @Final
    private TextureManager field_175057_n;
    @Shadow
    @Final
    private static ResourceLocation field_110798_h;

    public MixinRenderItem() {
        MixinRenderItem a2;
    }

    @Shadow
    public abstract void func_191965_a(IBakedModel var1, int var2);

    @Shadow
    protected abstract void func_191961_a(IBakedModel var1, ItemStack var2);

    @Inject(method={"renderItemModelIntoGUI"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_renderItemModelIntoGUI_HEAD(ItemStack a2, int a3, int a4, IBakedModel a5, CallbackInfo a6) {
        sja.y.ALLATORIxDEMO(a2, a3, a4, 0);
    }

    @Inject(method={"renderItemModelIntoGUI"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_renderItemModelIntoGUI_RETURN(ItemStack a2, int a3, int a4, IBakedModel a5, CallbackInfo a6) {
        sja.y.ALLATORIxDEMO(a2, a3, a4, 1);
    }

    @Inject(method={"getItemModelWithOverrides"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_getItemModelWithOverrides(ItemStack a2, World a3, EntityLivingBase a4, CallbackInfoReturnable<IBakedModel> a5) {
        if (!wka.k) {
            return;
        }
        wka.ALLATORIxDEMO = a4;
        qd<IBakedModel, String> a6 = dla.x.ALLATORIxDEMO(a2);
        if (a6 != null) {
            IBakedModel a7 = null;
            a7 = a6.ALLATORIxDEMO() != null && wo.ALLATORIxDEMO ? dla.x.c(a6.ALLATORIxDEMO()) : a6.c();
            if (a7 == null) {
                return;
            }
            if (a7.func_188617_f() != null) {
                a7 = a7.func_188617_f().handleItemState(a7, a2, a3, a4);
            }
            a5.setReturnValue(a7);
        }
    }

    @Inject(method={"getItemModelWithOverrides"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_getItemModelWithOverrides1(ItemStack a2, World a3, EntityLivingBase a4, CallbackInfoReturnable<IBakedModel> a5) {
        wka.ALLATORIxDEMO = null;
    }

    @Redirect(method={"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/RenderItem;renderEffect(Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"))
    private /* synthetic */ void mixin_Redirect_renderItem_renderEffect(RenderItem a2, IBakedModel a3, ItemStack a4, IBakedModel a5) {
        MixinRenderItem a6;
        String a7;
        String[] a8;
        int a9 = -8372020;
        if (a4.func_77978_p() != null && a4.func_77978_p().func_74764_b("color") && (a8 = (a7 = a4.func_77978_p().func_74779_i("color")).split(",")).length == 3) {
            a9 = MixinRenderItem.toRGBA(new Color(MixinRenderItem.toInt(a8[0]), MixinRenderItem.toInt(a8[1]), MixinRenderItem.toInt(a8[2])));
        }
        a6.renderEffect(a3, a9);
    }

    @Redirect(method={"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/tileentity/TileEntityItemStackRenderer;renderByItem(Lnet/minecraft/item/ItemStack;)V"))
    private /* synthetic */ void mixin_renderItem_renderByItem(TileEntityItemStackRenderer a2, ItemStack a3, ItemStack a4, IBakedModel a5) {
        if (a5 instanceof cv) {
            px.ALLATORIxDEMO(a3, (cv)a5);
        } else {
            a4.func_77973_b().getTileEntityItemStackRenderer().func_179022_a(a4);
        }
    }

    @Inject(method={"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/renderer/RenderItem;renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/item/ItemStack;)V", shift=At.Shift.BEFORE)})
    private /* synthetic */ void mixin_renderItem_before(ItemStack a2, IBakedModel a3, CallbackInfo a4) {
        RenderStatus.renderModelHasEmissive = false;
    }

    @Inject(method={"renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/renderer/block/model/IBakedModel;)V"}, at={@At(value="INVOKE", target="Lnet/minecraft/client/renderer/RenderItem;renderModel(Lnet/minecraft/client/renderer/block/model/IBakedModel;Lnet/minecraft/item/ItemStack;)V", shift=At.Shift.AFTER)})
    private /* synthetic */ void mixin_renderItem_after(ItemStack a2, IBakedModel a3, CallbackInfo a4) {
        if (RenderStatus.renderModelHasEmissive) {
            MixinRenderItem a5;
            float a6 = OpenGlHelper.lastBrightnessX;
            float a7 = OpenGlHelper.lastBrightnessY;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)240.0f, (float)a7);
            RenderStatus.renderModelEmissive = true;
            a5.func_191961_a(a3, a2);
            RenderStatus.renderModelEmissive = false;
            OpenGlHelper.func_77475_a((int)OpenGlHelper.field_77476_b, (float)a6, (float)a7);
        }
    }

    @Redirect(method={"renderItemOverlayIntoGUI"}, at=@At(value="INVOKE", target="Lnet/minecraft/util/CooldownTracker;getCooldown(Lnet/minecraft/item/Item;F)F"))
    private /* synthetic */ float mixin_Redirect_renderItem_renderEffect(CooldownTracker cooldownTracker, Item itemIn, float partialTicks, FontRenderer fr2, ItemStack stack, int xPosition, int yPosition, @Nullable String a2) {
        qd<Long, Long> a3 = jia.ALLATORIxDEMO(stack);
        if (a3 != null) {
            long a4 = a3.c();
            long a5 = a3.ALLATORIxDEMO();
            long a6 = System.currentTimeMillis();
            float a7 = a5 - a4;
            float a8 = a5 - a6;
            return MathHelper.func_76131_a((float)(a8 / a7), (float)0.0f, (float)1.0f);
        }
        return cooldownTracker.func_185143_a(itemIn, partialTicks);
    }

    @Inject(method={"renderItemAndEffectIntoGUI(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;II)V"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_renderItemAndEffectIntoGUI_head(EntityLivingBase a2, ItemStack a3, int a4, int a5, CallbackInfo a6) {
        wo.ALLATORIxDEMO = true;
    }

    @Inject(method={"renderItemAndEffectIntoGUI(Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/item/ItemStack;II)V"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_renderItemAndEffectIntoGUI_return(EntityLivingBase a2, ItemStack a3, int a4, int a5, CallbackInfo a6) {
        wo.ALLATORIxDEMO = false;
    }

    @Inject(method={"renderItemIntoGUI"}, at={@At(value="HEAD")})
    private /* synthetic */ void mixin_renderItemIntoGUI_head(ItemStack a2, int a3, int a4, CallbackInfo a5) {
        wo.ALLATORIxDEMO = true;
    }

    @Inject(method={"renderItemIntoGUI"}, at={@At(value="RETURN")})
    private /* synthetic */ void mixin_renderItemIntoGUI_return(ItemStack a2, int a3, int a4, CallbackInfo a5) {
        wo.ALLATORIxDEMO = false;
    }

    private static /* synthetic */ int toRGBA(Color a2) {
        return a2.getBlue() | a2.getGreen() << 8 | a2.getRed() << 16 | a2.getAlpha() << 24;
    }

    private static /* synthetic */ int toInt(String a2) {
        try {
            return Integer.parseInt(a2);
        }
        catch (Exception a3) {
            return 0;
        }
    }

    private /* synthetic */ void renderEffect(IBakedModel a2, int a3) {
        MixinRenderItem a4;
        GlStateManager.func_179132_a((boolean)false);
        GlStateManager.func_179143_c((int)514);
        GlStateManager.func_179140_f();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_COLOR, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
        a4.field_175057_n.func_110577_a(field_110798_h);
        GlStateManager.func_179128_n((int)5890);
        GlStateManager.func_179094_E();
        float a5 = 8.0f;
        if (a2 instanceof ku) {
            a5 = 0.125f;
        } else if (a2 instanceof jr) {
            a5 = 2.0f;
        }
        GlStateManager.func_179152_a((float)a5, (float)a5, (float)a5);
        float a6 = (float)(Minecraft.func_71386_F() % 3000L) / 3000.0f / a5;
        GlStateManager.func_179109_b((float)a6, (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)-50.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        a4.func_191965_a(a2, a3);
        GlStateManager.func_179121_F();
        GlStateManager.func_179094_E();
        GlStateManager.func_179152_a((float)a5, (float)a5, (float)a5);
        float a7 = (float)(Minecraft.func_71386_F() % 4873L) / 4873.0f / a5;
        GlStateManager.func_179109_b((float)(-a7), (float)0.0f, (float)0.0f);
        GlStateManager.func_179114_b((float)10.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        a4.func_191965_a(a2, a3);
        GlStateManager.func_179121_F();
        GlStateManager.func_179128_n((int)5888);
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179145_e();
        GlStateManager.func_179143_c((int)515);
        GlStateManager.func_179132_a((boolean)true);
        a4.field_175057_n.func_110577_a(TextureMap.field_110575_b);
    }
}

