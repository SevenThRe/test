/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableMap
 *  javax.vecmath.Matrix4f
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraftforge.client.model.BakedItemModel
 *  net.minecraftforge.client.model.PerspectiveMapWrapper
 *  net.minecraftforge.common.model.TRSRTransformation
 *  org.apache.commons.lang3.tuple.Pair
 */
package eos.moe.dragoncore.mixins;

import com.google.common.collect.ImmutableMap;
import eos.moe.dragoncore.wo;
import javax.vecmath.Matrix4f;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraftforge.client.model.BakedItemModel;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.common.model.TRSRTransformation;
import org.apache.commons.lang3.tuple.Pair;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value={BakedItemModel.class})
public class MixinBakedItemModel {
    @Shadow(remap=false)
    @Final
    protected ImmutableMap<ItemCameraTransforms.TransformType, TRSRTransformation> transforms;

    public MixinBakedItemModel() {
        MixinBakedItemModel a2;
    }

    @Inject(method={"handlePerspective"}, at={@At(value="HEAD")}, cancellable=true, remap=false)
    private /* synthetic */ void mixin_handlePerspective(ItemCameraTransforms.TransformType a2, CallbackInfoReturnable<Pair<? extends IBakedModel, Matrix4f>> a3) {
        if (wo.ALLATORIxDEMO) {
            MixinBakedItemModel a4;
            a3.setReturnValue((Pair<? extends IBakedModel, Matrix4f>)PerspectiveMapWrapper.handlePerspective((IBakedModel)((BakedItemModel)a4), a4.transforms, (ItemCameraTransforms.TransformType)a2));
        }
    }
}

