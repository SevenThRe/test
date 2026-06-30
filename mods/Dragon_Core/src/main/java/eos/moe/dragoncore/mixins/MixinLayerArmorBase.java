/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.layers.LayerArmorBase
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.inventory.EntityEquipmentSlot
 *  net.minecraft.item.ItemArmor
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.client.ForgeHooksClient
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.pw;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.uja;
import eos.moe.dragoncore.ww;
import eos.moe.dragoncore.zca;
import java.awt.Color;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LayerArmorBase.class})
public abstract class MixinLayerArmorBase<T extends ModelBase>
implements LayerRenderer<EntityLivingBase> {
    @Shadow
    @Final
    private static Map<String, ResourceLocation> ARMOR_TEXTURE_RES_MAP;
    @Shadow
    @Final
    protected static ResourceLocation ENCHANTED_ITEM_GLINT_RES;
    @Shadow
    @Final
    private RenderLivingBase<?> renderer;
    @Shadow
    private float colorR;
    @Shadow
    private float colorG;
    @Shadow
    private float colorB;
    @Shadow
    private float alpha;
    @Shadow
    private boolean skipRenderGlint;

    public MixinLayerArmorBase() {
        MixinLayerArmorBase a2;
    }

    @Shadow
    public abstract T getModelFromSlot(EntityEquipmentSlot var1);

    @Shadow(remap=false)
    protected abstract T getArmorModelHook(EntityLivingBase var1, ItemStack var2, EntityEquipmentSlot var3, T var4);

    @Shadow
    protected abstract void setModelSlotVisible(T var1, EntityEquipmentSlot var2);

    @Shadow(remap=false)
    public abstract ResourceLocation getArmorResource(Entity var1, ItemStack var2, EntityEquipmentSlot var3, String var4);

    private /* synthetic */ qd<ResourceLocation, Boolean> _getArmorResourceSetting(Entity a2, ItemStack a3, EntityEquipmentSlot a4, String a5) {
        MixinLayerArmorBase a6;
        zca a7 = uja.ALLATORIxDEMO(a3);
        if (a7 != null) {
            String a8 = a6.isLegSlot(a4) ? a7.f() : a7.x();
            ResourceLocation a9 = ARMOR_TEXTURE_RES_MAP.get(a8 = ForgeHooksClient.getArmorTexture((Entity)a2, (ItemStack)a3, (String)a8, (EntityEquipmentSlot)a4, (String)a5));
            if (a9 == null) {
                a9 = new ResourceLocation(a8);
                ARMOR_TEXTURE_RES_MAP.put(a8, a9);
            }
            return new qd<ResourceLocation, Boolean>(a9, a7.ALLATORIxDEMO());
        }
        return new qd<ResourceLocation, Boolean>(a6.getArmorResource(a2, a3, a4, a5), false);
    }

    private /* synthetic */ boolean isLegSlot(EntityEquipmentSlot a2) {
        return a2 == EntityEquipmentSlot.LEGS;
    }

    @Redirect(method={"renderArmorLayer"}, at=@At(value="INVOKE", target="Lnet/minecraft/client/renderer/entity/layers/LayerArmorBase;renderEnchantedGlint(Lnet/minecraft/client/renderer/entity/RenderLivingBase;Lnet/minecraft/entity/EntityLivingBase;Lnet/minecraft/client/model/ModelBase;FFFFFFF)V"))
    private /* synthetic */ void mixin_renderArmorLayer_renderEnchantedGlint(RenderLivingBase<?> a2, EntityLivingBase a3, ModelBase a4, float a5, float a6, float a7, float a8, float a9, float a10, float a11, EntityLivingBase a12, float a13, float a14, float a15, float a16, float a17, float a18, float a19, EntityEquipmentSlot a20) {
        ItemStack a21 = a12.getItemStackFromSlot(a20);
        MixinLayerArmorBase.renderEnchantedGlint(a21, a2, a3, a4, a13, a14, a15, a16, a17, a18, a19);
    }

    @Inject(method={"renderArmorLayer"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void renderArmorLayer(EntityLivingBase a2, float a3, float a4, float a5, float a6, float a7, float a8, float a9, EntityEquipmentSlot a10, CallbackInfo a11) {
        ItemArmor a12;
        ItemStack a13 = a2.getItemStackFromSlot(a10);
        if (a13.getItem() instanceof ItemArmor && (a12 = (ItemArmor)a13.getItem()).getEquipmentSlot() == a10) {
            String a14;
            float a15;
            float a16;
            MixinLayerArmorBase a17;
            zca a18 = uja.ALLATORIxDEMO(a13);
            if (a18 == null) {
                return;
            }
            a11.cancel();
            T a19 = a17.getModelFromSlot(a10);
            a19 = a17.getArmorModelHook(a2, a13, a10, a19);
            a19.setModelAttributes(a17.renderer.getMainModel());
            a19.setLivingAnimations(a2, a3, a4, a5);
            a17.setModelSlotVisible(a19, a10);
            boolean a20 = a17.isLegSlot(a10);
            a17.bindTexture1(new ResourceLocation(a20 ? a18.f() : a18.x()));
            if (a12.hasOverlay(a13)) {
                int a21 = a12.getColor(a13);
                a16 = (float)(a21 >> 16 & 0xFF) / 255.0f;
                a15 = (float)(a21 >> 8 & 0xFF) / 255.0f;
                float a22 = (float)(a21 & 0xFF) / 255.0f;
                GlStateManager.color((float)(a17.colorR * a16), (float)(a17.colorG * a15), (float)(a17.colorB * a22), (float)a17.alpha);
                a19.render((Entity)a2, a3, a4, a6, a7, a8, a9);
                a17.bindTexture1(a17.getArmorResource((Entity)a2, a13, a10, "overlay"));
            }
            GlStateManager.color((float)a17.colorR, (float)a17.colorG, (float)a17.colorB, (float)a17.alpha);
            a19.render((Entity)a2, a3, a4, a6, a7, a8, a9);
            String string = a14 = a20 ? a18.ALLATORIxDEMO() : a18.c();
            if (a14 != null) {
                a17.bindTexture1(new ResourceLocation(a14));
                a16 = OpenGlHelper.lastBrightnessX;
                a15 = OpenGlHelper.lastBrightnessY;
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)a15);
                GlStateManager.color((float)a17.colorR, (float)a17.colorG, (float)a17.colorB, (float)a17.alpha);
                a19.render((Entity)a2, a3, a4, a6, a7, a8, a9);
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)a16, (float)a15);
            }
            if (!a17.skipRenderGlint && a13.hasEffect()) {
                MixinLayerArmorBase.renderEnchantedGlint(a13, a17.renderer, a2, a19, a3, a4, a5, a6, a7, a8, a9);
            }
        }
    }

    private /* synthetic */ void bindTexture1(ResourceLocation a2) {
        if (a2.getNamespace().equals("dragoncore")) {
            ww.ALLATORIxDEMO(a2.getPath());
        } else {
            MixinLayerArmorBase a3;
            a3.renderer.bindTexture(a2);
        }
    }

    private static /* synthetic */ void renderEnchantedGlint(ItemStack a2, RenderLivingBase<?> a3, EntityLivingBase a4, ModelBase a5, float a6, float a7, float a8, float a9, float a10, float a11, float a12) {
        String a13;
        String[] a14;
        float a15 = 0.38f;
        float a16 = 0.19f;
        float a17 = 0.608f;
        if (a2.getTagCompound() != null && a2.getTagCompound().hasKey("color") && (a14 = (a13 = a2.getTagCompound().getString("color")).split(",")).length == 3) {
            a15 = (float)MixinLayerArmorBase.toInt(a14[0]) / 255.0f;
            a16 = (float)MixinLayerArmorBase.toInt(a14[1]) / 255.0f;
            a17 = (float)MixinLayerArmorBase.toInt(a14[2]) / 255.0f;
        }
        float a22 = (float)a4.ticksExisted + a8;
        a3.bindTexture(ENCHANTED_ITEM_GLINT_RES);
        Minecraft.getMinecraft().entityRenderer.setupFogColor(true);
        GlStateManager.enableBlend();
        GlStateManager.depthFunc((int)514);
        GlStateManager.depthMask((boolean)false);
        GlStateManager.color((float)0.5f, (float)0.5f, (float)0.5f, (float)1.0f);
        for (int a18 = 0; a18 < 2; ++a18) {
            GlStateManager.disableLighting();
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_COLOR, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE);
            GlStateManager.color((float)a15, (float)a16, (float)a17, (float)1.0f);
            GlStateManager.matrixMode((int)5890);
            GlStateManager.loadIdentity();
            GlStateManager.scale((float)0.33333334f, (float)0.33333334f, (float)0.33333334f);
            GlStateManager.rotate((float)(30.0f - (float)a18 * 60.0f), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.translate((float)0.0f, (float)(a22 * (0.001f + (float)a18 * 0.003f) * 20.0f), (float)0.0f);
            GlStateManager.matrixMode((int)5888);
            a5.render((Entity)a4, a6, a7, a9, a10, a11, a12);
            if (a5 instanceof pw) {
                ((pw)a5).clearData();
            }
            GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        }
        GlStateManager.matrixMode((int)5890);
        GlStateManager.loadIdentity();
        GlStateManager.matrixMode((int)5888);
        GlStateManager.enableLighting();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.depthFunc((int)515);
        GlStateManager.disableBlend();
        Minecraft.getMinecraft().entityRenderer.setupFogColor(false);
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
}

