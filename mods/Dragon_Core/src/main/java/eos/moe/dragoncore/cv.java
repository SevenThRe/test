/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix4f
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.block.model.ItemOverrideList
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.util.EnumFacing
 *  org.apache.commons.lang3.tuple.ImmutablePair
 *  org.apache.commons.lang3.tuple.Pair
 *  org.jetbrains.annotations.Nullable
 */
package eos.moe.dragoncore;

import java.util.List;
import javax.vecmath.Matrix4f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public class cv
implements IBakedModel {
    public static cv y = new cv();
    private ItemCameraTransforms.TransformType k;
    private IBakedModel ALLATORIxDEMO;

    public cv() {
        cv a2;
    }

    public cv(IBakedModel a2) {
        cv a3;
        a3.ALLATORIxDEMO = a2;
    }

    public List<BakedQuad> func_188616_a(@Nullable IBlockState iBlockState, @Nullable EnumFacing enumFacing, long a2) {
        return null;
    }

    public boolean func_177555_b() {
        return false;
    }

    public boolean func_177556_c() {
        return true;
    }

    public boolean func_188618_c() {
        return true;
    }

    public TextureAtlasSprite func_177554_e() {
        return null;
    }

    public ItemOverrideList func_188617_f() {
        return null;
    }

    public ItemCameraTransforms.TransformType getCameraTransformType() {
        cv a2;
        return a2.k;
    }

    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType a2) {
        cv a3;
        a3.k = a2;
        if (a3.ALLATORIxDEMO != null) {
            Pair a4 = a3.ALLATORIxDEMO.handlePerspective(a2);
            return new ImmutablePair((Object)a3, a4.getRight());
        }
        return super.handlePerspective(a2);
    }
}

