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
        double minX = oldBounds.field_72340_a;
        double minY = oldBounds.field_72338_b;
        double minZ = oldBounds.field_72339_c;
        double maxX = oldBounds.field_72336_d;
        double maxY = oldBounds.field_72337_e;
        double maxZ = oldBounds.field_72334_f;
        float x2 = modelRenderer.field_78800_c + position.x;
        float y2 = modelRenderer.field_78797_d + position.y;
        float z2 = modelRenderer.field_78798_e + position.z;
        if (modelRenderer.field_78804_l != null) {
            for (ModelBox box : modelRenderer.field_78804_l) {
                if ((double)(x2 + box.field_78252_a) < minX) {
                    minX = x2 + box.field_78252_a;
                }
                if ((double)(y2 + box.field_78250_b) < minY) {
                    minY = y2 + box.field_78250_b;
                }
                if ((double)(z2 + box.field_78251_c) < minZ) {
                    minZ = z2 + box.field_78251_c;
                }
                if ((double)(x2 + box.field_78248_d) > maxX) {
                    maxX = x2 + box.field_78248_d;
                }
                if ((double)(y2 + box.field_78249_e) > maxY) {
                    maxY = y2 + box.field_78249_e;
                }
                if (!((double)(z2 + box.field_78246_f) > maxZ)) continue;
                maxZ = z2 + box.field_78246_f;
            }
        }
        AxisAlignedBB newBounds = new AxisAlignedBB(minX, minY, minZ, maxX, maxY, maxZ);
        if (modelRenderer.field_78805_m != null) {
            for (ModelRenderer child : modelRenderer.field_78805_m) {
                newBounds = ModelUtils.getBounds(child, new Vector3f(x2, y2, z2), newBounds);
            }
        }
        return newBounds;
    }

    public static ModelRenderer getRootParent(ModelRenderer partIn, Collection<ModelRenderer> partsIn) {
        for (ModelRenderer possibleParent : partsIn) {
            if (possibleParent == null || possibleParent.field_78805_m == null || !possibleParent.field_78805_m.contains(partIn)) continue;
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
            if (possibleParent == null || possibleParent.field_78805_m == null || !possibleParent.field_78805_m.contains(partIn)) continue;
            parentsList.add(possibleParent);
            ModelUtils.getParentsList(possibleParent, possibleParents, parentsList);
        }
        return parentsList;
    }

    public static Collection<ModelRenderer> getParentsList(ModelRenderer partIn, Collection<ModelRenderer> possibleParents) {
        return ModelUtils.getParentsList(partIn, possibleParents, new LinkedList<ModelRenderer>());
    }

    public static Vector3f getGlobalOrigin(ModelRenderer partIn, Collection<ModelRenderer> possibleParents) {
        Vector3f origin = new Vector3f(partIn.field_78800_c, partIn.field_78797_d, partIn.field_78798_e);
        Collection<ModelRenderer> parentsList = ModelUtils.getParentsList(partIn, possibleParents);
        for (ModelRenderer parent : parentsList) {
            origin.x += parent.field_78800_c;
            origin.y += parent.field_78797_d;
            origin.z += parent.field_78798_e;
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
        float[] uCoords = new float[]{quad.field_78239_a[0].field_78241_b, quad.field_78239_a[1].field_78241_b, quad.field_78239_a[2].field_78241_b, quad.field_78239_a[3].field_78241_b};
        float[] vCoords = new float[]{quad.field_78239_a[0].field_78242_c, quad.field_78239_a[1].field_78242_c, quad.field_78239_a[2].field_78242_c, quad.field_78239_a[3].field_78242_c};
        int offset = 2;
        if (rotation == FaceRotation.CLOCKWISE) {
            offset = 3;
        } else if (rotation == FaceRotation.COUNTER_CLOCKWISE) {
            offset = 1;
        }
        for (int i2 = 0; i2 < 4; ++i2) {
            quad.field_78239_a[i2].field_78241_b = uCoords[(i2 + offset) % 4];
            quad.field_78239_a[i2].field_78242_c = vCoords[(i2 + offset) % 4];
        }
    }
}

