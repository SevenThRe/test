/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.PngSizeInfo
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureUtil
 */
package eos.moe.dragoncore;

import java.io.IOException;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;

public class jt
extends TextureAtlasSprite {
    public boolean k;
    public jt ALLATORIxDEMO;

    public jt(String a2) {
        super(a2);
        jt a3;
    }

    public void loadSprite(PngSizeInfo a2, boolean a3) throws IOException {
        jt a4;
        a4.resetSprite();
        a4.width = a2.pngWidth;
        a4.height = a2.pngHeight;
        if (a3) {
            a4.height = a4.width;
        } else if (a2.pngHeight != a2.pngWidth) {
            // empty if block
        }
    }

    public int getTickCounter() {
        jt a2;
        return a2.tickCounter;
    }

    public int getFrameCounter() {
        jt a2;
        return a2.frameCounter;
    }

    public void updateAnimation(int a2, int a3) {
        jt a4;
        a4.tickCounter = a2;
        a4.frameCounter = a3;
        if (a4.tickCounter >= a4.animationMetadata.getFrameTimeSingle(a4.frameCounter)) {
            int a5 = a4.animationMetadata.getFrameIndex(a4.frameCounter);
            int a6 = a4.animationMetadata.getFrameCount() == 0 ? a4.framesTextureData.size() : a4.animationMetadata.getFrameCount();
            a4.frameCounter = (a4.frameCounter + 1) % a6;
            int a7 = a4.animationMetadata.getFrameIndex(a4.frameCounter);
            if (a5 != a7 && a7 >= 0 && a7 < a4.framesTextureData.size()) {
                TextureUtil.uploadTextureMipmap((int[][])((int[][])a4.framesTextureData.get(a7)), (int)a4.width, (int)a4.height, (int)a4.originX, (int)a4.originY, (boolean)false, (boolean)false);
            }
        } else if (a4.animationMetadata.isInterpolate()) {
            a4.updateAnimationInterpolated();
        }
    }

    public void updateAnimation() {
        jt a2;
        ++a2.tickCounter;
        if (a2.tickCounter >= a2.animationMetadata.getFrameTimeSingle(a2.frameCounter)) {
            int a3 = a2.animationMetadata.getFrameIndex(a2.frameCounter);
            int a4 = a2.animationMetadata.getFrameCount() == 0 ? a2.framesTextureData.size() : a2.animationMetadata.getFrameCount();
            a2.frameCounter = (a2.frameCounter + 1) % a4;
            a2.tickCounter = 0;
            int a5 = a2.animationMetadata.getFrameIndex(a2.frameCounter);
            if (a3 != a5 && a5 >= 0 && a5 < a2.framesTextureData.size()) {
                TextureUtil.uploadTextureMipmap((int[][])((int[][])a2.framesTextureData.get(a5)), (int)a2.width, (int)a2.height, (int)a2.originX, (int)a2.originY, (boolean)false, (boolean)false);
            }
        } else if (a2.animationMetadata.isInterpolate()) {
            a2.updateAnimationInterpolated();
        }
    }
}

