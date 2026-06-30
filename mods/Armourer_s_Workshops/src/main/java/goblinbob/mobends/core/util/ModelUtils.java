/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.model.ModelBox
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.model.PositionTextureVertex
 *  net.minecraft.client.model.TexturedQuad
 *  net.minecraft.util.math.AxisAlignedBB
 *  org.lwjgl.util.vector.Vector3f
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.client.model.BoxFactory;
import goblinbob.mobends.core.client.model.FaceRotation;
import java.util.Collection;
import java.util.LinkedList;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.PositionTextureVertex;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.util.math.AxisAlignedBB;
import org.lwjgl.util.vector.Vector3f;

public class ModelUtils {
    public static AxisAlignedBB getBounds(ModelRenderer modelRenderer) {
        return ModelUtils.getBounds(modelRenderer, new Vector3f(0.0f, 0.0f, 0.0f), new AxisAlignedBB(0.0, 0.0, 0.0, 0.0, 0.0, 0.0));
    }

    public static AxisAlignedBB getBounds(ModelRenderer modelRenderer, Vector3f position, AxisAlignedBB oldBounds) {
        double minX = oldBounds.minX;
        double minY = oldBounds.minY;
        double minZ = oldBounds.minZ;
        double maxX = oldBounds.maxX;
        double maxY = oldBounds.maxY;
        double maxZ = oldBounds.maxZ;
        float x2 = modelRenderer.rotationPointX + position.x;
        float y2 = modelRenderer.rotationPointY + position.y;
        float z2 = modelRenderer.rotationPointZ + position.z;
        if (modelRenderer.cubeList != null) {
            for (ModelBox box : modelRenderer.cubeList) {
                if ((double)(x2 + box.posX1) < minX) {
                    minX = x2 + box.posX1;
                }
                if ((double)(y2 + box.posY1) < minY) {
                    minY = y2 + box.posY1;
                }
                if ((double)(z2 + box.posZ1) < minZ) {
                    minZ = z2 + box.posZ1;
                }
                if ((double)(x2 + box.posX2) > maxX) {
                    maxX = x2 + box.posX2;
                }
                if ((double)(y2 + box.posY2) > maxY) {
                    maxY = y2 + box.posY2;
                }
                if (!((double)(z2 + box.posZ2) > maxZ)) continue;
                maxZ = z2 + box.posZ2;
            }
        }
        AxisAlignedBB newBounds = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
        if (modelRenderer.childModels != null) {
            for (ModelRenderer child : modelRenderer.childModels) {
                newBounds = ModelUtils.getBounds(child, new Vector3f(x2, y2, z2), newBounds);
            }
        }
        return newBounds;
    }

    public static ModelRenderer getRootParent(ModelRenderer partIn, Collection<ModelRenderer> partsIn) {
        for (ModelRenderer possibleParent : partsIn) {
            if (possibleParent == null || possibleParent.childModels == null || !possibleParent.childModels.contains(partIn)) continue;
            ModelRenderer nextParent = ModelUtils.getRootParent(possibleParent, partsIn);
            if (nextParent != null) {
                return nextParent;
            }
            return possibleParent;
        }
        return null;
    }

    public static Collection<ModelRenderer> getParentsList(ModelRenderer partIn, Collection<ModelRenderer> possibleParents, Collection<ModelRenderer> parentsList) {
        for (ModelRenderer possibleParent : possibleParents) {
            if (possibleParent == null || possibleParent.childModels == null || !possibleParent.childModels.contains(partIn)) continue;
            parentsList.add(possibleParent);
            ModelUtils.getParentsList(possibleParent, possibleParents, parentsList);
        }
        return parentsList;
    }

    public static Collection<ModelRenderer> getParentsList(ModelRenderer partIn, Collection<ModelRenderer> possibleParents) {
        return ModelUtils.getParentsList(partIn, possibleParents, new LinkedList<ModelRenderer>());
    }

    public static Vector3f getGlobalOrigin(ModelRenderer partIn, Collection<ModelRenderer> possibleParents) {
        Vector3f origin = new Vector3f(partIn.rotationPointX, partIn.rotationPointY, partIn.rotationPointZ);
        Collection<ModelRenderer> parentsList = ModelUtils.getParentsList(partIn, possibleParents);
        for (ModelRenderer parent : parentsList) {
            origin.x += parent.rotationPointX;
            origin.y += parent.rotationPointY;
            origin.z += parent.rotationPointZ;
        }
        return origin;
    }

    public static TexturedQuad createQuad(PositionTextureVertex[] positions, BoxFactory.TextureFace face, float textureWidth, float textureHeight) {
        int uSize = face.uSize;
        int vSize = face.vSize;
        if (face.faceRotation == FaceRotation.CLOCKWISE || face.faceRotation == FaceRotation.COUNTER_CLOCKWISE) {
            uSize = face.vSize;
            vSize = face.uSize;
        }
        TexturedQuad quad = new TexturedQuad(positions, face.uPos, face.vPos, face.uPos + uSize, face.vPos + vSize, textureWidth, textureHeight);
        ModelUtils.applyFaceRotation(quad, face.faceRotation);
        return quad;
    }

    public static void applyFaceRotation(TexturedQuad quad, FaceRotation rotation) {
        if (rotation == FaceRotation.IDENTITY) {
            return;
        }
        float[] uCoords = new float[]{quad.vertexPositions[0].texturePositionX, quad.vertexPositions[1].texturePositionX, quad.vertexPositions[2].texturePositionX, quad.vertexPositions[3].texturePositionX};
        float[] vCoords = new float[]{quad.vertexPositions[0].texturePositionY, quad.vertexPositions[1].texturePositionY, quad.vertexPositions[2].texturePositionY, quad.vertexPositions[3].texturePositionY};
        int offset = 2;
        if (rotation == FaceRotation.CLOCKWISE) {
            offset = 3;
        } else if (rotation == FaceRotation.COUNTER_CLOCKWISE) {
            offset = 1;
        }
        for (int i2 = 0; i2 < 4; ++i2) {
            quad.vertexPositions[i2].texturePositionX = uCoords[(i2 + offset) % 4];
            quad.vertexPositions[i2].texturePositionY = vCoords[(i2 + offset) % 4];
        }
    }
}

