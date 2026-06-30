/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.block.model.BakedQuad
 *  net.minecraft.client.renderer.block.model.BakedQuadRetextured
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 */
package eos.moe.dragoncore.mixins;

import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.interfaces.IBakedQuad;
import eos.moe.dragoncore.jt;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BakedQuadRetextured;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(value={BakedQuad.class})
public class MixinBakedQuad
implements IBakedQuad {
    @Shadow
    @Final
    protected TextureAtlasSprite sprite;
    private boolean quadEmissiveChecked;
    private BakedQuad quadEmissive;

    public MixinBakedQuad() {
        MixinBakedQuad a2;
    }

    @Override
    public BakedQuad getQuadEmissive() {
        MixinBakedQuad a2;
        if (a2.quadEmissiveChecked) {
            return a2.quadEmissive;
        }
        if (a2.quadEmissive == null && a2.sprite != null) {
            if (a2.sprite instanceof jt && ((jt)a2.sprite).ALLATORIxDEMO != null) {
                a2.quadEmissive = new BakedQuadRetextured((BakedQuad)a2, (TextureAtlasSprite)((jt)a2.sprite).ALLATORIxDEMO);
            } else if (ca.v != null) {
                try {
                    TextureAtlasSprite a3 = (TextureAtlasSprite)ca.v.get(a2.sprite);
                    a2.quadEmissive = new BakedQuadRetextured((BakedQuad)a2, a3);
                }
                catch (Exception exception) {
                    // empty catch block
                }
            }
        }
        a2.quadEmissiveChecked = true;
        return a2.quadEmissive;
    }
}

