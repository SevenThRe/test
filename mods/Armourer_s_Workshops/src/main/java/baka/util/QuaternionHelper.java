/*
 * Decompiled with CFR 0.152.
 */
package baka.util;

import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.math.vector.Vec3d;
import goblinbob.mobends.core.math.vector.Vec4d;

public class QuaternionHelper {
    public static void main(String[] args) {
        Vec3d euler = QuaternionHelper.getEuler(new Quaternion(123.0f, 125.0f, 260.0f, 1.0f));
        System.out.println(euler);
    }

    public static Vec3d getEuler(Quaternion quaternion) {
        Vec3d vec3d = QuaternionHelper.toEuler(QuaternionHelper.getAxisAngle(quaternion));
        vec3d.x = (float)vec3d.x;
        vec3d.y = (float)vec3d.y;
        vec3d.z = (float)vec3d.z;
        vec3d.x = vec3d.x * 180.0 / Math.PI;
        vec3d.y = vec3d.y * 180.0 / Math.PI;
        vec3d.z = vec3d.z * 180.0 / Math.PI;
        return vec3d;
    }

    public static Vec4d getAxisAngle(Quaternion quaternion) {
        double angle = 2.0 * Math.acos(quaternion.w);
        double w2 = quaternion.w * quaternion.w;
        double xa2 = (double)quaternion.x / Math.sqrt(1.0 - w2);
        double ya2 = (double)quaternion.y / Math.sqrt(1.0 - w2);
        double za2 = (double)quaternion.z / Math.sqrt(1.0 - w2);
        return new Vec4d(xa2, ya2, za2, angle);
    }

    public static Vec3d toEuler(Vec4d axisAngle) {
        double x2 = axisAngle.x;
        double y2 = axisAngle.y;
        double z2 = axisAngle.z;
        double angle = axisAngle.w;
        double s2 = Math.sin(angle);
        double c2 = Math.cos(angle);
        double t2 = 1.0 - c2;
        double temp = x2 * y2 * t2 + z2 * s2;
        double temp1 = Math.atan2(x2 * Math.sin(angle / 2.0), Math.cos(angle / 2.0));
        if (temp > 0.999998) {
            double heading = 2.0 * temp1;
            double attitude = 1.5707963267948966;
            double bank = 0.0;
            return new Vec3d(bank, heading, attitude);
        }
        if (temp < -0.999998) {
            double heading = -2.0 * temp1;
            double attitude = -1.5707963267948966;
            double bank = 0.0;
            return new Vec3d(bank, heading, attitude);
        }
        double heading = Math.atan2(y2 * s2 - x2 * z2 * t2, 1.0 - (y2 * y2 + z2 * z2) * t2);
        double attitude = Math.asin(temp);
        double bank = Math.atan2(x2 * s2 - y2 * z2 * t2, 1.0 - (x2 * x2 + z2 * z2) * t2);
        return new Vec3d(bank, heading, attitude);
    }
}

