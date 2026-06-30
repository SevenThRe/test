/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.PositionTextureVertex
 *  net.minecraft.client.model.TexturedQuad
 */
package goblinbob.mobends.core.client.model;

import goblinbob.mobends.core.client.model.BoxSide;
import goblinbob.mobends.core.client.model.FaceRotation;
import goblinbob.mobends.core.client.model.ModelPart;
import goblinbob.mobends.core.client.model.MutatedBox;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3f;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;

public class BoxFactory {
    public ModelPart target;
    public final Vec3f min;
    public final Vec3f max;
    public final TextureFace[] faces;
    public int uvWidth;
    public int uvHeight;
    public int uvLength;
    public boolean mirrored;
    public byte faceVisibilityFlag;
    int textureU;
    int textureV;
    boolean textureUVSet = false;

    public BoxFactory(ModelRenderer renderer, ModelBox source) {
        this.min = new Vec3f(source.field_78252_a, source.field_78250_b, source.field_78251_c);
        this.max = new Vec3f(source.field_78248_d, source.field_78249_e, source.field_78246_f);
        this.faces = new TextureFace[6];
        this.mirrored = renderer.field_78809_i;
        this.faceVisibilityFlag = (byte)63;
        this.textureU = 0;
        this.textureV = 0;
        TexturedQuad[] quadList = source.field_78254_i;
        if (quadList == null) {
            return;
        }
        float textureWidth = renderer.field_78801_a;
        float textureHeight = renderer.field_78799_b;
        this.textureUVSet = true;
        for (int i2 = 0; i2 < 6; ++i2) {
            PositionTextureVertex endVertex;
            PositionTextureVertex startVertex;
            if (this.mirrored) {
                startVertex = quadList[i2].field_78239_a[2];
                endVertex = quadList[i2].field_78239_a[0];
                this.faces[i2] = new TextureFace((int)(startVertex.field_78241_b * textureWidth), (int)(startVertex.field_78242_c * textureHeight), (int)((endVertex.field_78241_b - startVertex.field_78241_b) * textureWidth), (int)((endVertex.field_78242_c - startVertex.field_78242_c) * textureHeight));
                continue;
            }
            startVertex = quadList[i2].field_78239_a[1];
            endVertex = quadList[i2].field_78239_a[3];
            this.faces[i2] = new TextureFace((int)(startVertex.field_78241_b * textureWidth), (int)(startVertex.field_78242_c * textureHeight), (int)((endVertex.field_78241_b - startVertex.field_78241_b) * textureWidth), (int)((endVertex.field_78242_c - startVertex.field_78242_c) * textureHeight));
        }
    }

    public BoxFactory(float x2, float y2, float z2, int dx, int dy, int dz, float delta) {
        this.min = new Vec3f(x2 - delta, y2 - delta, z2 - delta);
        this.max = new Vec3f(x2 + (float)dx + delta, y2 + (float)dy + delta, z2 + (float)dz + delta);
        this.faces = new TextureFace[6];
        this.uvWidth = dx;
        this.uvHeight = dy;
        this.uvLength = dz;
        this.mirrored = false;
        this.faceVisibilityFlag = (byte)63;
        this.textureU = 0;
        this.textureV = 0;
    }

    public BoxFactory(float x0, float y0, float z0, float x1, float y1, float z1, TextureFace[] faces) {
        this.min = new Vec3f(x0, y0, z0);
        this.max = new Vec3f(x1, y1, z1);
        this.faces = new TextureFace[6];
        for (int i2 = 0; i2 < faces.length; ++i2) {
            this.faces[i2] = new TextureFace(faces[i2]);
        }
        this.mirrored = false;
        this.faceVisibilityFlag = (byte)63;
        this.textureU = 0;
        this.textureV = 0;
        this.textureUVSet = true;
    }

    public BoxFactory(IVec3fRead min, IVec3fRead max, TextureFace[] faces) {
        this(min.getX(), min.getY(), min.getZ(), max.getX(), max.getY(), max.getZ(), faces);
    }

    BoxFactory setTarget(ModelPart target) {
        this.target = target;
        if (!this.textureUVSet) {
            this.textureU = this.target.getTextureOffsetX();
            this.textureV = this.target.getTextureOffsetY();
            this.generateTextureFaces();
        }
        return this;
    }

    public BoxFactory setMinMax(float x0, float y0, float z0, float x1, float y1, float z1) {
        this.min.set(x0, y0, z0);
        this.max.set(x1, y1, z1);
        return this;
    }

    public BoxFactory setPosSize(float x2, float y2, float z2, float dx, float dy, float dz) {
        this.min.set(x2, y2, z2);
        this.max.set(x2 + dx, y2 + dy, z2 + dz);
        return this;
    }

    public BoxFactory inflate(float dx, float dy, float dz) {
        this.min.add(-dx, -dy, -dz);
        this.max.add(dx, dy, dz);
        return this;
    }

    public BoxFactory setWidth(float width) {
        this.max.x = this.min.x + width;
        return this;
    }

    public BoxFactory setHeight(float height) {
        this.max.y = this.min.y + height;
        return this;
    }

    public BoxFactory setLength(float length) {
        this.max.z = this.min.z + length;
        return this;
    }

    public BoxFactory resize(float dx, float dy, float dz) {
        this.max.set(this.min.x + dx, this.min.y + dy, this.min.z + dz);
        return this;
    }

    public BoxFactory withUVs(int u2, int v2) {
        this.textureU = u2;
        this.textureV = v2;
        this.textureUVSet = true;
        this.generateTextureFaces();
        return this;
    }

    public BoxFactory hideFace(BoxSide face) {
        int mask = 1;
        mask = (byte)(mask << face.faceIndex);
        this.faceVisibilityFlag = (byte)(this.faceVisibilityFlag & ~mask);
        return this;
    }

    public BoxFactory showFace(BoxSide face) {
        int mask = 1;
        mask = (byte)(mask << face.faceIndex);
        this.faceVisibilityFlag = (byte)(this.faceVisibilityFlag | mask);
        return this;
    }

    public BoxFactory mirror() {
        this.mirrored = true;
        return this;
    }

    public BoxFactory offsetTextureQuad(BoxSide face, float x2, float y2) {
        if (!this.textureUVSet) {
            this.textureUVSet = true;
            this.generateTextureFaces();
        }
        this.faces[face.faceIndex].uPos = (int)((float)this.faces[face.faceIndex].uPos + x2);
        this.faces[face.faceIndex].vPos = (int)((float)this.faces[face.faceIndex].vPos + y2);
        return this;
    }

    public BoxFactory rotateTextureQuad(BoxSide face, FaceRotation rotation) {
        if (!this.textureUVSet) {
            this.textureUVSet = true;
            this.generateTextureFaces();
        }
        this.faces[face.faceIndex].faceRotation = rotation;
        return this;
    }

    public BoxFactory offset(float x2, float y2, float z2) {
        this.min.add(x2, y2, z2);
        this.max.add(x2, y2, z2);
        return this;
    }

    public MutatedBox create() {
        MutatedBox box = new MutatedBox(this.target, this.min, this.max, this.faces, this.faceVisibilityFlag);
        if (this.target != null) {
            this.target.addBox(box);
        }
        return box;
    }

    public MutatedBox create(ModelRenderer renderer) {
        return new MutatedBox(renderer, this.min, this.max, this.faces, this.faceVisibilityFlag);
    }

    private void generateTextureFaces() {
        int u2 = this.textureU;
        int v2 = this.textureV;
        this.faces[0] = new TextureFace(u2 + this.uvLength + this.uvWidth, v2 + this.uvLength, this.uvLength, this.uvHeight);
        this.faces[1] = new TextureFace(u2, v2 + this.uvLength, this.uvLength, this.uvHeight);
        this.faces[2] = new TextureFace(u2 + this.uvLength, v2, this.uvWidth, this.uvLength);
        this.faces[3] = new TextureFace(u2 + this.uvLength + this.uvWidth, v2 + this.uvLength, this.uvWidth, -this.uvLength);
        this.faces[4] = new TextureFace(u2 + this.uvLength, v2 + this.uvLength, this.uvWidth, this.uvHeight);
        this.faces[5] = new TextureFace(u2 + this.uvLength + this.uvWidth + this.uvLength, v2 + this.uvLength, this.uvWidth, this.uvHeight);
    }

    public static class TextureFace {
        public int uPos;
        public int vPos;
        public int uSize;
        public int vSize;
        public FaceRotation faceRotation = FaceRotation.IDENTITY;

        public TextureFace(int uPos, int vPos, int uSize, int vSize) {
            this.uPos = uPos;
            this.vPos = vPos;
            this.uSize = uSize;
            this.vSize = vSize;
        }

        public TextureFace(TextureFace face) {
            this.uPos = face.uPos;
            this.vPos = face.vPos;
            this.uSize = face.uSize;
            this.vSize = face.vSize;
        }
    }
}

