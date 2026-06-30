/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 */
package goblinbob.mobends.core.math.physics;

import goblinbob.mobends.core.math.physics.AABBox;
import goblinbob.mobends.core.math.physics.OBBox;
import goblinbob.mobends.core.math.physics.Plane;
import goblinbob.mobends.core.math.physics.Ray;
import goblinbob.mobends.core.math.physics.RayHitInfo;
import goblinbob.mobends.core.math.vector.IVec3fRead;
import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.math.vector.VectorUtils;
import javax.annotation.Nullable;

public class Physics {
    @Nullable
    public static RayHitInfo intersect(Ray ray, Plane plane) {
        float ratio;
        IVec3fRead rayOrigin = ray.getPosition();
        IVec3fRead rayDirection = ray.getDirection();
        IVec3fRead planeOrigin = plane.getPosition();
        IVec3fRead planeNormal = plane.getNormal();
        Vec3f rayToPlane = VectorUtils.subtract(planeOrigin, rayOrigin);
        float rayToPlaneDot = VectorUtils.dot(rayToPlane, planeNormal);
        float rayToEndDot = VectorUtils.dot(rayDirection, planeNormal);
        if (rayToEndDot != 0.0f && (ratio = rayToPlaneDot / rayToEndDot) > 0.0f) {
            return new RayHitInfo(rayOrigin.getX() + rayDirection.getX() * ratio, rayOrigin.getY() + rayDirection.getY() * ratio, rayOrigin.getZ() + rayDirection.getZ() * ratio);
        }
        return null;
    }

    public static RayHitInfo intersect(Ray ray, AABBox box) {
        float rayX = ray.position.getX();
        float rayY = ray.position.getY();
        float rayZ = ray.position.getZ();
        float dirX = ray.direction.getX();
        float dirY = ray.direction.getY();
        float dirZ = ray.direction.getZ();
        float dirInvX = 1.0f / dirX;
        float dirInvY = 1.0f / dirY;
        float dirInvZ = 1.0f / dirZ;
        float tMin = ((dirInvX < 0.0f ? box.max.getX() : box.min.getX()) - rayX) * dirInvX;
        float tMax = ((dirInvX < 0.0f ? box.min.getX() : box.max.getX()) - rayX) * dirInvX;
        float min = ((dirInvY < 0.0f ? box.max.getY() : box.min.getY()) - rayY) * dirInvY;
        float max = ((dirInvY < 0.0f ? box.min.getY() : box.max.getY()) - rayY) * dirInvY;
        if (max < tMin || min > tMax) {
            return null;
        }
        if (min > tMin) {
            tMin = min;
        }
        if (max < tMax) {
            tMax = max;
        }
        min = ((dirInvZ < 0.0f ? box.max.getZ() : box.min.getZ()) - rayZ) * dirInvZ;
        max = ((dirInvZ < 0.0f ? box.min.getZ() : box.max.getZ()) - rayZ) * dirInvZ;
        if (max < tMin || min > tMax) {
            return null;
        }
        if (min > tMin) {
            tMin = min;
        }
        if (max < tMax) {
            tMax = max;
        }
        return new RayHitInfo(rayX + dirX * tMin, rayY + dirY * tMin, rayZ + dirZ * tMin);
    }

    public static RayHitInfo intersect(Ray ray, OBBox box) {
        float t2;
        float max;
        float min;
        double[] fields = box.transform.getFields();
        float rayX = ray.position.getX();
        float rayY = ray.position.getY();
        float rayZ = ray.position.getZ();
        float boxX = (float)fields[12];
        float boxY = (float)fields[13];
        float boxZ = (float)fields[14];
        Vec3f right = new Vec3f((float)fields[0], (float)fields[1], (float)fields[2]);
        Vec3f up = new Vec3f((float)fields[4], (float)fields[5], (float)fields[6]);
        Vec3f forward = new Vec3f((float)fields[8], (float)fields[9], (float)fields[10]);
        float scaleX = right.length();
        float scaleY = up.length();
        float scaleZ = forward.length();
        VectorUtils.normalize(right);
        VectorUtils.normalize(up);
        VectorUtils.normalize(forward);
        Vec3f rayToBox = new Vec3f(boxX - rayX, boxY - rayY, boxZ - rayZ);
        float tMin = 0.0f;
        float tMax = Float.POSITIVE_INFINITY;
        float nomLen = VectorUtils.dot(right, rayToBox);
        float denomLen = VectorUtils.dot(ray.direction, right);
        if ((double)Math.abs(denomLen) > 1.0E-5) {
            min = (nomLen + box.min.getX() * scaleX) / denomLen;
            if (min > (max = (nomLen + box.max.getX() * scaleX) / denomLen)) {
                t2 = min;
                min = max;
                max = t2;
            }
            if (max < tMax) {
                tMax = max;
            }
            if (min > tMin) {
                tMin = min;
            }
            if (tMax < tMin) {
                return null;
            }
        } else if (-nomLen + box.min.getX() > 0.0f || -nomLen + box.max.getX() < 0.0f) {
            return null;
        }
        nomLen = VectorUtils.dot(up, rayToBox);
        denomLen = VectorUtils.dot(ray.direction, up);
        if ((double)Math.abs(denomLen) > 1.0E-5) {
            min = (nomLen + box.min.getY() * scaleY) / denomLen;
            if (min > (max = (nomLen + box.max.getY() * scaleY) / denomLen)) {
                t2 = min;
                min = max;
                max = t2;
            }
            if (max < tMax) {
                tMax = max;
            }
            if (min > tMin) {
                tMin = min;
            }
            if (tMax < tMin) {
                return null;
            }
        } else if (-nomLen + box.min.getY() > 0.0f || -nomLen + box.max.getY() < 0.0f) {
            return null;
        }
        nomLen = VectorUtils.dot(forward, rayToBox);
        denomLen = VectorUtils.dot(ray.direction, forward);
        if ((double)Math.abs(denomLen) > 1.0E-5) {
            min = (nomLen + box.min.getZ() * scaleZ) / denomLen;
            if (min > (max = (nomLen + box.max.getZ() * scaleZ) / denomLen)) {
                t2 = min;
                min = max;
                max = t2;
            }
            if (max < tMax) {
                tMax = max;
            }
            if (min > tMin) {
                tMin = min;
            }
            if (tMax < tMin) {
                return null;
            }
        } else if (-nomLen + box.min.getZ() > 0.0f || -nomLen + box.max.getZ() < 0.0f) {
            return null;
        }
        return new RayHitInfo(rayX + ray.direction.getX() * tMin, rayY + ray.direction.getY() * tMin, rayZ + ray.direction.getZ() * tMin);
    }
}

