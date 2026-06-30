/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ModelManager
 *  net.minecraft.client.renderer.block.model.ModelResourceLocation
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.dla;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ModelManager;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={ModelManager.class})
public class MixinModelManager {
    public MixinModelManager() {
        MixinModelManager a2;
    }

    @Inject(method={"getModel"}, at={@At(value="HEAD")}, cancellable=true)
    private /* synthetic */ void mixin_getModel(ModelResourceLocation a2, CallbackInfoReturnable<IBakedModel> a3) {
        IBakedModel a4;
        if (a2 != null && (a4 = dla.x.ALLATORIxDEMO(a2.func_110623_a())) != null) {
            a3.setReturnValue(a4);
        }
    }
}

