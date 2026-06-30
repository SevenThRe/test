/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.model.pipeline.LightUtil
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.interfaces.IBakedQuad;
import eos.moe.dragoncore.interfaces.RenderStatus;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.model.pipeline.LightUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value={LightUtil.class})
public abstract class MixinLightUtil {
    public MixinLightUtil() {
        MixinLightUtil a2;
    }

    @Shadow(remap=false)
    public static void renderQuadColorSlow(BufferBuilder a2, BakedQuad a3, int a4) {
    }

    @Inject(method={"renderQuadColor"}, at={@At(value="HEAD")}, cancellable=true, remap=false)
    private static /* synthetic */ void mixin_renderQuadColor(BufferBuilder a2, BakedQuad a3, int a4, CallbackInfo a5) {
        IBakedQuad a6 = (IBakedQuad)a3;
        if (RenderStatus.renderModelEmissive) {
            BakedQuad a7 = a6.getQuadEmissive();
            if (a7 != null) {
                MixinLightUtil.renderQuadColor1(a2, a7, a4);
            }
            a5.cancel();
        } else if (a6.getQuadEmissive() != null) {
            RenderStatus.renderModelHasEmissive = true;
        }
    }

    private static /* synthetic */ void renderQuadColor1(BufferBuilder a2, BakedQuad a3, int a4) {
        if (a3.getFormat().equals((Object)a2.getVertexFormat())) {
            a2.addVertexData(a3.getVertexData());
            ForgeHooksClient.putQuadColor((BufferBuilder)a2, (BakedQuad)a3, (int)a4);
        } else {
            MixinLightUtil.renderQuadColorSlow(a2, a3, a4);
        }
    }
}

