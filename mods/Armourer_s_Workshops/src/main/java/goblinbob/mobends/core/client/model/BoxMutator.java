/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.TexturedQuad
 */
package goblinbob.mobends.core.client.model;

import goblinbob.mobends.core.client.model.BoxFactory;
import goblinbob.mobends.core.client.model.BoxSide;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TexturedQuad;

public class BoxMutator {
    protected ModelBase targetModel;
    protected ModelRenderer targetRenderer;
    protected BoxFactory factory;
    protected int textureOffsetX;
    protected int textureOffsetY;

    public BoxMutator(ModelBase targetModel, ModelRenderer targetRenderer, BoxFactory factory, int textureOffsetX, int textureOffsetY) {
        this.targetModel = targetModel;
        this.targetRenderer = targetRenderer;
        this.factory = factory;
        this.textureOffsetX = textureOffsetX;
        this.textureOffsetY = textureOffsetY;
    }

    public static BoxMutator createFrom(ModelBase modelBase, ModelRenderer modelRenderer, ModelBox original) {
        TexturedQuad[] quadList = original.field_78254_i;
        if (quadList == null) {
            return null;
        }
        float textureWidth = modelRenderer.field_78801_a;
        float textureHeight = modelRenderer.field_78799_b;
        int texU = (int)(quadList[1].field_78239_a[1].field_78241_b * textureWidth);
        int texV = (int)(quadList[2].field_78239_a[1].field_78242_c * textureHeight);
        if (modelRenderer.field_78809_i) {
            texV = (int)(quadList[3].field_78239_a[1].field_78242_c * textureHeight);
        }
        float inflation1 = Math.abs((float)((double)original.field_78252_a - quadList[1].field_78239_a[0].field_78243_a.field_72450_a));
        float inflation2 = Math.abs((float)((double)original.field_78248_d - quadList[1].field_78239_a[0].field_78243_a.field_72450_a));
        float inflation = Math.min(inflation1, inflation2);
        BoxFactory target = new BoxFactory(modelRenderer, original);
        target.inflate(inflation, inflation, inflation);
        return new BoxMutator(modelBase, modelRenderer, target, texU, texV);
    }

    public BoxFactory getFactory() {
        return this.factory;
    }

    public int getTextureOffsetX() {
        return this.textureOffsetX;
    }

    public int getTextureOffsetY() {
        return this.textureOffsetY;
    }

    public void offsetBy(float offsetX, float offsetY, float offsetZ) {
        this.factory.offset(offsetX, offsetY, offsetZ);
    }

    public BoxFactory sliceFromBottom(float sliceY) {
        float height = this.factory.max.y - this.factory.min.y;
        if (sliceY > this.factory.min.y && sliceY < this.factory.max.y) {
            BoxSide[] faces;
            float newHeight = sliceY - this.factory.min.y;
            BoxFactory.TextureFace[] newBoxFaces = new BoxFactory.TextureFace[6];
            for (BoxSide faceEnum : faces = new BoxSide[]{BoxSide.BACK, BoxSide.FRONT, BoxSide.LEFT, BoxSide.RIGHT}) {
                float textureScale = newHeight / height;
                BoxFactory.TextureFace face = this.factory.faces[faceEnum.faceIndex];
                int vSizeSlice = (int)((float)face.vSize * textureScale);
                newBoxFaces[faceEnum.faceIndex] = new BoxFactory.TextureFace(face.uPos, face.vPos + vSizeSlice, face.uSize, face.vSize - vSizeSlice);
                face.vSize = vSizeSlice;
            }
            newBoxFaces[BoxSide.TOP.faceIndex] = new BoxFactory.TextureFace(this.factory.faces[BoxSide.TOP.faceIndex]);
            newBoxFaces[BoxSide.BOTTOM.faceIndex] = new BoxFactory.TextureFace(this.factory.faces[BoxSide.BOTTOM.faceIndex]);
            BoxFactory sliced = new BoxFactory(this.factory.min.x, sliceY, this.factory.min.z, this.factory.max.x, this.factory.max.y, this.factory.max.z, newBoxFaces);
            sliced.hideFace(BoxSide.TOP);
            this.factory.max.setY(sliceY);
            this.factory.hideFace(BoxSide.BOTTOM);
            return sliced;
        }
        return null;
    }
}

