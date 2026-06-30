/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
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
 */
package eos.moe.armourers;

import java.util.List;
import javax.annotation.Nullable;
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

public class gg
implements IBakedModel {
    private IBakedModel m;
    private ItemCameraTransforms.TransformType j;

    public gg() {
        gg a2;
    }

    public boolean func_177555_b() {
        return false;
    }

    public ItemOverrideList func_188617_f() {
        return null;
    }

    public boolean func_177556_c() {
        return true;
    }

    public boolean func_188618_c() {
        return true;
    }

    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType a2) {
        gg a3;
        a3.j = a2;
        if (a3.m != null) {
            Pair pair = a3.m.handlePerspective(a2);
            return new ImmutablePair((Object)a3, pair.getRight());
        }
        return super.handlePerspective(a2);
    }

    public List<BakedQuad> func_188616_a(@Nullable IBlockState iBlockState, @Nullable EnumFacing enumFacing, long a2) {
        return null;
    }

    public ItemCameraTransforms.TransformType getCameraTransformType() {
        gg a2;
        return a2.j;
    }

    public gg(IBakedModel a2) {
        gg a3;
        a3.m = a2;
    }

    public TextureAtlasSprite func_177554_e() {
        return null;
    }
}

