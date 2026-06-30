/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer
 *  net.minecraft.tileentity.TileEntitySkull
 */
package eos.moe.armourers.mixins;

import eos.moe.armourers.kd;
import eos.moe.armourers.kf;
import eos.moe.armourers.mk;
import eos.moe.armourers.mn;
import eos.moe.armourers.rk;
import eos.moe.armourers.yl;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.tileentity.TileEntitySkull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={TileEntitySkullRenderer.class})
public class MixinTileEntitySkullRenderer {
    private static float SCALE = 0.0625f;

    public MixinTileEntitySkullRenderer() {
        MixinTileEntitySkullRenderer a2;
    }

    @Inject(method={"render(Lnet/minecraft/tileentity/TileEntitySkull;DDDFIF)V"}, at={@At(value="HEAD")}, cancellable=true)
    public void mixin_render(TileEntitySkull a2, double a3, double a4, double a5, float a62, int a72, float a8, CallbackInfo a9) {
        Object object = a62 = a2.getPlayerProfile() != null && a2.getPlayerProfile().getName() != null && a2.getPlayerProfile().getName().startsWith("skin-") ? a2.getPlayerProfile().getName().substring(5) : null;
        if (a62 != null) {
            Object a62 = ((String)a62).split("\\|~\\|")[0];
            if ((a62 = kd.j.getSkin((String)a62)) != null) {
                a9.cancel();
                GlStateManager.pushMatrix();
                GlStateManager.pushAttrib();
                GlStateManager.translate((double)(a3 + 0.5), (double)(a4 + 0.5), (double)(a5 + 0.5));
                GlStateManager.enableBlend();
                GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                GlStateManager.rotate((float)((float)(22.5 * (double)a2.getSkullRotation())), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.scale((float)-1.0f, (float)-1.0f, (float)1.0f);
                int n2 = a2 = 0;
                while (n2 < ((yl)a62).y().size()) {
                    kf a72 = ((yl)a62).y().get(a2);
                    rk.j.renderPart(new mk(a72, SCALE, new mn(), null, 0.0, true, true, true, null));
                    n2 = ++a2;
                }
                GlStateManager.disableBlend();
                GlStateManager.popAttrib();
                GlStateManager.popMatrix();
            }
        }
    }
}

