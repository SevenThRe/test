/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.PositionTextureVertex
 *  net.minecraft.client.model.TexturedQuad
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package goblinbob.mobends.standard.client.renderer.entity;

import goblinbob.mobends.standard.data.PlayerData;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BendsCapeRenderer {
    public static final int MODEL_WIDTH = 10;
    public static final int MODEL_LENGTH = 16;
    public static final int MODEL_DEPTH = 1;
    public static final int SLAB_AMOUNT = 16;
    public static final ResourceLocation CAPE_TEXTURE = new ResourceLocation("mobends", "textures/cape.png");
    public Slab[] slabs = new Slab[16];

    public BendsCapeRenderer() {
        for (int i2 = 0; i2 < 16; ++i2) {
            this.slabs[i2] = new Slab(i2 * 16 / 16);
            if (i2 <= 0) continue;
            this.slabs[i2 - 1].setChildSlab(this.slabs[i2]);
        }
        this.slabs[0].rotationPointY = 0;
    }

    public void applyAnimation(PlayerData playerData) {
        double phase = playerData.getCapeWavePhase();
        for (int i2 = 0; i2 < 16; ++i2) {
            float waveSpeed = 0.2f;
            float waveFrequency = 7.2f;
            float waveOffset = (float)i2 / 16.0f;
            float magnitude = 5.0f * (0.7f + (float)(i2 / 16));
            this.slabs[i2].setRotateAngle((float)(Math.cos(phase * (double)waveSpeed + (double)(waveOffset * waveFrequency)) * (double)magnitude));
        }
        this.slabs[0].rotate(-10.0f);
    }

    public void render(float scale) {
        this.slabs[0].render(scale);
    }

    static class Slab {
        float rotateAngle;
        public float textureWidth = 64.0f;
        public float textureHeight = 32.0f;
        private int textureOffsetX;
        private int textureOffsetY;
        private boolean compiled;
        private int displayList;
        private Slab childSlab;
        public boolean showModel = true;
        public boolean isHidden;
        public int offsetX = -5;
        public int offsetY = 0;
        public int offsetZ = 0;
        public int rotationPointX = 0;
        public int rotationPointY = 1;
        public int rotationPointZ = 0;
        public int hingeOffset = 0;
        private final PositionTextureVertex[] vertexPositions;
        private final TexturedQuad[] quadList;
        public final float posX1;
        public final float posY1;
        public final float posZ1;
        public final float posX2;
        public final float posY2;
        public final float posZ2;

        public Slab(int texV) {
            int slabLength = 1;
            this.posX1 = this.offsetX;
            this.posY1 = this.offsetY;
            this.posZ1 = this.offsetZ;
            this.posX2 = this.offsetX + 10;
            this.posY2 = this.offsetY + slabLength;
            this.posZ2 = this.offsetZ + 1;
            int texU = 0;
            this.vertexPositions = new PositionTextureVertex[8];
            this.quadList = new TexturedQuad[6];
            PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(this.posX1, this.posY1, this.posZ1, 0.0f, 0.0f);
            PositionTextureVertex positiontexturevertex = new PositionTextureVertex(this.posX2, this.posY1, this.posZ1, 0.0f, 8.0f);
            PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(this.posX2, this.posY2, this.posZ1, 8.0f, 8.0f);
            PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(this.posX1, this.posY2, this.posZ1, 8.0f, 0.0f);
            PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(this.posX1, this.posY1, this.posZ2, 0.0f, 0.0f);
            PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(this.posX2, this.posY1, this.posZ2, 0.0f, 8.0f);
            PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(this.posX2, this.posY2, this.posZ2, 8.0f, 8.0f);
            PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(this.posX1, this.posY2, this.posZ2, 8.0f, 0.0f);
            this.vertexPositions[0] = positiontexturevertex7;
            this.vertexPositions[1] = positiontexturevertex;
            this.vertexPositions[2] = positiontexturevertex1;
            this.vertexPositions[3] = positiontexturevertex2;
            this.vertexPositions[4] = positiontexturevertex3;
            this.vertexPositions[5] = positiontexturevertex4;
            this.vertexPositions[6] = positiontexturevertex5;
            this.vertexPositions[7] = positiontexturevertex6;
            this.quadList[0] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, texU + 1 + 10, texV + 1, texU + 1 + 10 + 1, texV + 1 + slabLength, this.textureWidth, this.textureHeight);
            this.quadList[1] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, texU, texV + 1, texU + 1, texV + 1 + slabLength, this.textureWidth, this.textureHeight);
            this.quadList[2] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, texU + 1, texV, texU + 1 + 10, texV + 1, this.textureWidth, this.textureHeight);
            this.quadList[3] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, texU + 1 + 10, texV + 1, texU + 1 + 10 + 10, texV, this.textureWidth, this.textureHeight);
            this.quadList[4] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, texU + 1, texV + 1, texU + 1 + 10, texV + 1 + slabLength, this.textureWidth, this.textureHeight);
            this.quadList[5] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, texU + 1 + 10 + 1, texV + 1, texU + 1 + 10 + 1 + 10, texV + 1 + slabLength, this.textureWidth, this.textureHeight);
        }

        public void rotate(float f2) {
            this.setRotateAngle(this.rotateAngle + f2);
        }

        public Slab setChildSlab(Slab slab) {
            this.childSlab = slab;
            return this;
        }

        public void setRotateAngle(float rotateAngle) {
            this.rotateAngle = rotateAngle;
            this.hingeOffset = this.rotateAngle < 0.0f ? 1 : 0;
        }

        @SideOnly(value=Side.CLIENT)
        public void render(float scale) {
            if (!this.isHidden && this.showModel) {
                if (!this.compiled) {
                    this.compileDisplayList(scale);
                }
                GlStateManager.pushMatrix();
                GlStateManager.translate((float)((float)this.rotationPointX * scale), (float)((float)this.rotationPointY * scale), (float)((float)(this.rotationPointZ + this.hingeOffset) * scale));
                GlStateManager.rotate((float)this.rotateAngle, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.translate((float)0.0f, (float)0.0f, (float)((float)(-this.hingeOffset) * scale));
                GlStateManager.callList((int)this.displayList);
                if (this.childSlab != null) {
                    this.childSlab.render(scale);
                }
                GlStateManager.popMatrix();
            }
        }

        private void compileDisplayList(float scale) {
            this.displayList = GLAllocation.generateDisplayLists((int)1);
            GlStateManager.glNewList((int)this.displayList, (int)4864);
            BufferBuilder bufferbuilder = Tessellator.getInstance().getBuffer();
            for (TexturedQuad texturedquad : this.quadList) {
                texturedquad.draw(bufferbuilder, scale);
            }
            GlStateManager.glEndList();
            this.compiled = true;
        }
    }
}

