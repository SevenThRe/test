/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix4f
 *  net.minecraft.block.state.IBlockState
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.block.model.ItemOverrideList
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.util.EnumFacing
 *  org.apache.commons.lang3.tuple.Pair
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.vja;
import eos.moe.dragoncore.vw;
import java.util.List;
import javax.vecmath.Matrix4f;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import org.apache.commons.lang3.tuple.Pair;

public class jr
implements IBakedModel {
    public final IBakedModel y;
    private vja k;
    private vw ALLATORIxDEMO;

    public vw getTextureMap() {
        jr a2;
        return a2.ALLATORIxDEMO;
    }

    public jr(vw a2, IBakedModel a3, vja a4) {
        jr a5;
        a5.ALLATORIxDEMO = a2;
        a5.y = a3;
        a5.k = a4;
    }

    public List<BakedQuad> func_188616_a(IBlockState a2, EnumFacing a3, long a4) {
        jr a5;
        return a5.y.func_188616_a(a2, a3, a4);
    }

    public boolean func_177555_b() {
        jr a2;
        return a2.y.func_177555_b();
    }

    public boolean func_177556_c() {
        jr a2;
        return a2.y.func_177556_c();
    }

    public boolean func_188618_c() {
        jr a2;
        return a2.y.func_188618_c();
    }

    public TextureAtlasSprite func_177554_e() {
        jr a2;
        return a2.y.func_177554_e();
    }

    public ItemOverrideList func_188617_f() {
        jr a2;
        return a2.y.func_188617_f();
    }

    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType a2) {
        jr a3;
        GlStateManager.func_179144_i((int)a3.ALLATORIxDEMO.func_110552_b());
        if (a3.k.ALLATORIxDEMO() > 0.0f) {
            GlStateManager.func_179152_a((float)a3.k.ALLATORIxDEMO(), (float)a3.k.ALLATORIxDEMO(), (float)a3.k.ALLATORIxDEMO());
        }
        if (a3.k.ALLATORIxDEMO().size() == 3) {
            GlStateManager.func_179109_b((float)a3.k.ALLATORIxDEMO().get(0).floatValue(), (float)a3.k.ALLATORIxDEMO().get(1).floatValue(), (float)a3.k.ALLATORIxDEMO().get(2).floatValue());
        }
        Pair a4 = a3.y.handlePerspective(a2);
        return Pair.of((Object)new jr(a3.ALLATORIxDEMO, (IBakedModel)a4.getKey(), a3.k), (Object)a4.getRight());
    }

    public ItemCameraTransforms func_177552_f() {
        jr a2;
        return a2.y.func_177552_f();
    }

    public boolean isAmbientOcclusion(IBlockState a2) {
        jr a3;
        return a3.y.isAmbientOcclusion(a2);
    }

    public IBakedModel getmProxy() {
        jr a2;
        return a2.y;
    }
}

