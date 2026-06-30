/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.PositionTextureVertex
 *  net.minecraft.client.model.TexturedQuad
 *  net.minecraft.client.renderer.BufferBuilder
 */
package goblinbob.mobends.core.client.model;

import goblinbob.mobends.core.client.model.BoxFactory;
import goblinbob.mobends.core.math.physics.AABBox;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.util.ModelUtils;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.BufferBuilder;

public class MutatedBox
extends ModelBox {
    protected final byte faceVisibilityFlag;
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    public static final int TOP = 2;
    public static final int BOTTOM = 3;
    public static final int FRONT = 4;
    public static final int BACK = 5;

    public MutatedBox(ModelRenderer renderer, IVec3fRead min, IVec3fRead max, BoxFactory.TextureFace[] faces, byte faceVisibilityFlag) {
        super(renderer, 0, 0, 0.0f, 0.0f, 0.0f, 0, 0, 0, 0.0f, false);
        this.faceVisibilityFlag = faceVisibilityFlag;
        float x0 = min.getX();
        float y0 = min.getY();
        float z0 = min.getZ();
        float x1 = max.getX();
        float y1 = max.getY();
        float z1 = max.getZ();
        if (renderer.mirror) {
            float temp = x1;
            x1 = x0;
            x0 = temp;
        }
        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(x0, y0, z0, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(x1, y0, z0, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(x1, y1, z0, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(x0, y1, z0, 8.0f, 0.0f);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(x0, y0, z1, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(x1, y0, z1, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(x1, y1, z1, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(x0, y1, z1, 8.0f, 0.0f);
        this.vertexPositions[0] = positiontexturevertex7;
        this.vertexPositions[1] = positiontexturevertex;
        this.vertexPositions[2] = positiontexturevertex1;
        this.vertexPositions[3] = positiontexturevertex2;
        this.vertexPositions[4] = positiontexturevertex3;
        this.vertexPositions[5] = positiontexturevertex4;
        this.vertexPositions[6] = positiontexturevertex5;
        this.vertexPositions[7] = positiontexturevertex6;
        this.quadList[0] = ModelUtils.createQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, faces[0], renderer.textureWidth, renderer.textureHeight);
        this.quadList[1] = ModelUtils.createQuad(new PositionTextureVertex[]{positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, faces[1], renderer.textureWidth, renderer.textureHeight);
        this.quadList[2] = ModelUtils.createQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, faces[2], renderer.textureWidth, renderer.textureHeight);
        this.quadList[3] = ModelUtils.createQuad(new PositionTextureVertex[]{positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, faces[3], renderer.textureWidth, renderer.textureHeight);
        this.quadList[4] = ModelUtils.createQuad(new PositionTextureVertex[]{positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, faces[4], renderer.textureWidth, renderer.textureHeight);
        this.quadList[5] = ModelUtils.createQuad(new PositionTextureVertex[]{positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, faces[5], renderer.textureWidth, renderer.textureHeight);
        if (renderer.mirror) {
            for (int j1 = 0; j1 < this.quadList.length; ++j1) {
                this.quadList[j1].flipFace();
            }
        }
    }

    public MutatedBox(ModelRenderer modelRenderer, int texU, int texV, float x2, float y2, float z2, int width, int height, int length, float inflation, boolean mirrored, byte faceVisibilityFlag) {
        super(modelRenderer, texU, texV, x2, y2, z2, width, height, length, inflation);
        this.faceVisibilityFlag = faceVisibilityFlag;
        float f4 = x2 + (float)width;
        float f5 = y2 + (float)height;
        float f6 = z2 + (float)length;
        x2 -= inflation;
        y2 -= inflation;
        z2 -= inflation;
        f4 += inflation;
        f5 += inflation;
        f6 += inflation;
        if (mirrored) {
            float f7 = f4;
            f4 = x2;
            x2 = f7;
        }
        PositionTextureVertex positiontexturevertex7 = new PositionTextureVertex(x2, y2, z2, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex = new PositionTextureVertex(f4, y2, z2, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex1 = new PositionTextureVertex(f4, f5, z2, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex2 = new PositionTextureVertex(x2, f5, z2, 8.0f, 0.0f);
        PositionTextureVertex positiontexturevertex3 = new PositionTextureVertex(x2, y2, f6, 0.0f, 0.0f);
        PositionTextureVertex positiontexturevertex4 = new PositionTextureVertex(f4, y2, f6, 0.0f, 8.0f);
        PositionTextureVertex positiontexturevertex5 = new PositionTextureVertex(f4, f5, f6, 8.0f, 8.0f);
        PositionTextureVertex positiontexturevertex6 = new PositionTextureVertex(x2, f5, f6, 8.0f, 0.0f);
        this.vertexPositions[0] = positiontexturevertex7;
        this.vertexPositions[1] = positiontexturevertex;
        this.vertexPositions[2] = positiontexturevertex1;
        this.vertexPositions[3] = positiontexturevertex2;
        this.vertexPositions[4] = positiontexturevertex3;
        this.vertexPositions[5] = positiontexturevertex4;
        this.vertexPositions[6] = positiontexturevertex5;
        this.vertexPositions[7] = positiontexturevertex6;
        this.quadList[0] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex, positiontexturevertex1, positiontexturevertex5}, texU + length + width, texV + length, texU + length + width + length, texV + length + height, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[1] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex7, positiontexturevertex3, positiontexturevertex6, positiontexturevertex2}, texU, texV + length, texU + length, texV + length + height, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[2] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex4, positiontexturevertex3, positiontexturevertex7, positiontexturevertex}, texU + length, texV, texU + length + width, texV + length, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[3] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex1, positiontexturevertex2, positiontexturevertex6, positiontexturevertex5}, texU + length + width, texV + length, texU + length + width + width, texV, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[4] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex, positiontexturevertex7, positiontexturevertex2, positiontexturevertex1}, texU + length, texV + length, texU + length + width, texV + length + height, modelRenderer.textureWidth, modelRenderer.textureHeight);
        this.quadList[5] = new TexturedQuad(new PositionTextureVertex[]{positiontexturevertex3, positiontexturevertex4, positiontexturevertex5, positiontexturevertex6}, texU + length + width + length, texV + length, texU + length + width + length + width, texV + length + height, modelRenderer.textureWidth, modelRenderer.textureHeight);
        if (mirrored) {
            for (int j1 = 0; j1 < this.quadList.length; ++j1) {
                this.quadList[j1].flipFace();
            }
        }
    }

    public MutatedBox(ModelRenderer modelRenderer, int texU, int texV, float x2, float y2, float z2, int width, int height, int length, float inflation, boolean mirrored) {
        this(modelRenderer, texU, texV, x2, y2, z2, width, height, length, inflation, mirrored, 63);
    }

    public MutatedBox(ModelRenderer modelRenderer, int texU, int texV, float x2, float y2, float z2, int width, int height, int length, float inflation) {
        this(modelRenderer, texU, texV, x2, y2, z2, width, height, length, inflation, modelRenderer.mirror, 63);
    }

    public void render(BufferBuilder bufferBuilder, float scale) {
        byte tempFlag = this.faceVisibilityFlag;
        for (TexturedQuad quad : this.quadList) {
            if ((tempFlag & 1) == 1) {
                quad.draw(bufferBuilder, scale);
            }
            tempFlag = (byte)(tempFlag >> 1);
        }
    }

    public boolean isFaceVisible(int faceIndex) {
        return (this.faceVisibilityFlag >> faceIndex & 1) == 1;
    }

    public AABBox createAABB() {
        return new AABBox(this.posX1, this.posY1, this.posZ1, this.posX2, this.posY2, this.posZ2);
    }
}

