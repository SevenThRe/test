/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.client.Mesh;
import goblinbob.mobends.core.util.IColorRead;

public class MeshBuilder {
    public static void texturedSimpleCube(Mesh builder, double minX, double minY, double minZ, double maxX, double maxY, double maxZ, IColorRead color, int[] tex, int textureWidth, int textureHeight, int faceSize) {
        double us = 1.0f / (float)textureWidth;
        double vs = 1.0f / (float)textureHeight;
        double[] positions = new double[]{minX, maxY, minZ, minX, minY, minZ, minX, minY, maxZ, minX, maxY, maxZ, maxX, maxY, maxZ, maxX, minY, maxZ, maxX, minY, minZ, maxX, maxY, minZ, maxX, maxY, minZ, maxX, minY, minZ, minX, minY, minZ, minX, maxY, minZ, minX, maxY, maxZ, minX, minY, maxZ, maxX, minY, maxZ, maxX, maxY, maxZ, minX, minY, maxZ, minX, minY, minZ, maxX, minY, minZ, maxX, minY, maxZ, maxX, maxY, maxZ, maxX, maxY, minZ, minX, maxY, minZ, minX, maxY, maxZ};
        int[] normals = new int[]{-1, 0, 0, 1, 0, 0, 0, 0, -1, 0, 0, 1, 0, -1, 0, 0, 1, 0};
        for (int face = 0; face < 6; ++face) {
            int posIndex = face * 12;
            double minU = (double)tex[face * 2] * us;
            double maxU = (double)(tex[face * 2] + faceSize) * us;
            double minV = (double)tex[face * 2 + 1] * vs;
            double maxV = (double)(tex[face * 2 + 1] + faceSize) * vs;
            builder.pos(positions[posIndex + 0], positions[posIndex + 1], positions[posIndex + 2]).tex(minU, minV).color(color).normal(normals[face * 3], normals[face * 3 + 1], normals[face * 3 + 2]).endVertex();
            builder.pos(positions[posIndex + 3], positions[posIndex + 4], positions[posIndex + 5]).tex(minU, maxV).color(color).normal(normals[face * 3], normals[face * 3 + 1], normals[face * 3 + 2]).endVertex();
            builder.pos(positions[posIndex + 6], positions[posIndex + 7], positions[posIndex + 8]).tex(maxU, maxV).color(color).normal(normals[face * 3], normals[face * 3 + 1], normals[face * 3 + 2]).endVertex();
            builder.pos(positions[posIndex + 9], positions[posIndex + 10], positions[posIndex + 11]).tex(maxU, minV).color(color).normal(normals[face * 3], normals[face * 3 + 1], normals[face * 3 + 2]).endVertex();
        }
    }

    public static void texturedXZPlane(Mesh mesh, double minX, double y2, double minZ, double width, double length, boolean facingUp, IColorRead color, int[] tex, int textureWidth, int textureHeight) {
        double us = 1.0f / (float)textureWidth;
        double vs = 1.0f / (float)textureHeight;
        double maxX = minX + width;
        double maxZ = minZ + length;
        int[] normals = new int[]{0, facingUp ? -1 : 1, 0};
        double minU = (double)tex[0] * us;
        double maxU = (double)tex[2] * us;
        double minV = (double)tex[1] * vs;
        double maxV = (double)tex[3] * vs;
        mesh.pos(minX, y2, maxZ).tex(minU, minV).color(color).normal(normals[0], normals[1], normals[2]).endVertex();
        mesh.pos(minX, y2, minZ).tex(minU, maxV).color(color).normal(normals[0], normals[1], normals[2]).endVertex();
        mesh.pos(maxX, y2, minZ).tex(maxU, maxV).color(color).normal(normals[0], normals[1], normals[2]).endVertex();
        mesh.pos(minX, y2, maxZ).tex(minU, minV).color(color).normal(normals[0], normals[1], normals[2]).endVertex();
        mesh.pos(maxX, y2, minZ).tex(maxU, maxV).color(color).normal(normals[0], normals[1], normals[2]).endVertex();
        mesh.pos(maxX, y2, maxZ).tex(maxU, minV).color(color).normal(normals[0], normals[1], normals[2]).endVertex();
    }
}

