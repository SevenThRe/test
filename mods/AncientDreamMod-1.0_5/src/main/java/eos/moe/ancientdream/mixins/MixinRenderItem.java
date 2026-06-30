/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.SimpleTexture
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.item.ItemStack
 *  net.minecraft.nbt.NBTTagCompound
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.ancientdream.mixins;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={RenderItem.class})
public abstract class MixinRenderItem {
    @Shadow
    public float zLevel;
    @Shadow
    @Final
    private TextureManager textureManager;

    @Inject(method={"renderItemModelIntoGUI"}, at={@At(value="HEAD")})
    private void Mixin_renderItemModelIntoGUI(ItemStack stack, int x, int y, IBakedModel bakedmodel, CallbackInfo callbackInfo) {
        NBTTagCompound tagCompound;
        String quality;
        if (!stack.isEmpty() && stack.getTagCompound() != null && !(quality = (tagCompound = stack.getTagCompound()).getString("qualityt")).isEmpty()) {
            float z = this.zLevel;
            this.zLevel = 0.0f;
            GlStateManager.pushMatrix();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (this.bindTexture(quality)) {
                GuiScreen.drawModalRectWithCustomSizedTexture((int)x, (int)y, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
            }
            GlStateManager.popMatrix();
            this.zLevel = z;
        }
    }

    private boolean bindTexture(String quality) {
        ResourceLocation res = new ResourceLocation("ancientdream", "quality/" + quality);
        ITextureObject texture = this.textureManager.getTexture(res);
        if (texture == null) {
            texture = new SimpleTexture(res);
            this.textureManager.loadTexture(res, texture);
            texture = this.textureManager.getTexture(res);
        }
        if (texture == TextureUtil.MISSING_TEXTURE) {
            return false;
        }
        GlStateManager.bindTexture((int)texture.getGlTextureId());
        return true;
    }
}

