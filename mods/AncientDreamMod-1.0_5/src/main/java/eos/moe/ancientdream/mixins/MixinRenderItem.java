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
    public float field_77023_b;
    @Shadow
    @Final
    private TextureManager field_175057_n;

    @Inject(method={"renderItemModelIntoGUI"}, at={@At(value="HEAD")})
    private void Mixin_renderItemModelIntoGUI(ItemStack stack, int x, int y, IBakedModel bakedmodel, CallbackInfo callbackInfo) {
        NBTTagCompound tagCompound;
        String quality;
        if (!stack.func_190926_b() && stack.func_77978_p() != null && !(quality = (tagCompound = stack.func_77978_p()).func_74779_i("qualityt")).isEmpty()) {
            float z = this.field_77023_b;
            this.field_77023_b = 0.0f;
            GlStateManager.func_179094_E();
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            if (this.bindTexture(quality)) {
                GuiScreen.func_146110_a((int)x, (int)y, (float)0.0f, (float)0.0f, (int)16, (int)16, (float)16.0f, (float)16.0f);
            }
            GlStateManager.func_179121_F();
            this.field_77023_b = z;
        }
    }

    private boolean bindTexture(String quality) {
        ResourceLocation res = new ResourceLocation("ancientdream", "quality/" + quality);
        ITextureObject texture = this.field_175057_n.func_110581_b(res);
        if (texture == null) {
            texture = new SimpleTexture(res);
            this.field_175057_n.func_110579_a(res, texture);
            texture = this.field_175057_n.func_110581_b(res);
        }
        if (texture == TextureUtil.field_111001_a) {
            return false;
        }
        GlStateManager.func_179144_i((int)texture.func_110552_b());
        return true;
    }
}

