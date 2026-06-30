/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.gui.FontRenderer
 *  org.lwjgl.util.vector.Vector3f
 */
package goblinbob.mobends.core.util;

import goblinbob.mobends.core.math.Quaternion;
import goblinbob.mobends.core.math.QuaternionUtils;
import goblinbob.mobends.core.math.vector.IVec3f;
import goblinbob.mobends.core.math.vector.Vec3f;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import net.minecraft.client.gui.FontRenderer;
import org.lwjgl.util.vector.Vector3f;

public class GUtil {
    public static final float PI = (float)Math.PI;
    public static final float TWO_PI = (float)Math.PI * 2;
    public static final float RAD_TO_DEG = 57.295776f;

    public static float clamp(float value, float min, float max) {
        return Math.min(Math.max(value, min), max);
    }

    public static int clamp(int value, int min, int max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double angleFromCoordinates(double x2, double z2) {
        return Math.atan2(x2, z2) / Math.PI * 180.0;
    }

    public static double getRadianDifference(double a2, double b2) {
        double d2 = Math.abs((a2 = GUtil.wrapRadians(a2)) - (b2 = GUtil.wrapRadians(b2)));
        d2 = d2 > Math.PI ? Math.PI * 2 - d2 : d2;
        return d2;
    }

    public static double wrapRadians(double a2) {
        if ((a2 %= Math.PI) >= Math.PI) {
            a2 -= Math.PI * 2;
        } else if (a2 < -Math.PI) {
            a2 += Math.PI * 2;
        }
        return a2;
    }

    public static IVec3f translate(IVec3f vector, float x2, float y2, float z2) {
        vector.add(x2, y2, z2);
        return vector;
    }

    public static IVec3f scale(IVec3f vector, float x2, float y2, float z2) {
        vector.scale(x2, y2, z2);
        return vector;
    }

    public static void rotate(IVec3f[] points, Quaternion rotation) {
        for (IVec3f point : points) {
            QuaternionUtils.multiply(point, rotation, point);
        }
    }

    public static IVec3f[] rotateX(IVec3f[] nums, float move) {
        for (int i2 = 0; i2 < nums.length; ++i2) {
            nums[i2] = GUtil.rotateX(nums[i2], move);
        }
        return nums;
    }

    public static IVec3f[] rotateY(IVec3f[] nums, float move) {
        for (int i2 = 0; i2 < nums.length; ++i2) {
            nums[i2] = GUtil.rotateY(nums[i2], move);
        }
        return nums;
    }

    public static IVec3f[] rotateZ(IVec3f[] nums, float move) {
        for (int i2 = 0; i2 < nums.length; ++i2) {
            nums[i2] = GUtil.rotateZ(nums[i2], move);
        }
        return nums;
    }

    public static IVec3f rotateX(IVec3f num, float rotation) {
        Vector3f y2 = new Vector3f();
        Vector3f z2 = new Vector3f();
        y2.y = (float)Math.cos((double)((180.0f + rotation) / 180.0f) * Math.PI);
        y2.z = (float)Math.sin((double)((180.0f + rotation) / 180.0f) * Math.PI);
        y2.normalise();
        y2.y *= -num.getY();
        y2.z *= num.getY();
        z2.y = (float)Math.sin((double)((180.0f + rotation) / 180.0f) * Math.PI);
        z2.z = (float)Math.cos((double)((180.0f + rotation) / 180.0f) * Math.PI);
        z2.normalise();
        z2.y *= -num.getZ();
        z2.z *= -num.getZ();
        num = new Vec3f(num.getX(), y2.y + z2.y, y2.z + z2.z);
        return num;
    }

    public static IVec3f rotateY(IVec3f num, float rotation) {
        Vector3f x2 = new Vector3f();
        Vector3f z2 = new Vector3f();
        x2.x = (float)Math.cos((double)(-rotation / 180.0f) * Math.PI);
        x2.z = (float)Math.sin((double)(-rotation / 180.0f) * Math.PI);
        x2.normalise();
        x2.x *= -num.getX();
        x2.z *= num.getX();
        z2.x = (float)Math.sin((double)(-rotation / 180.0f) * Math.PI);
        z2.z = (float)Math.cos((double)(-rotation / 180.0f) * Math.PI);
        z2.normalise();
        z2.x *= num.getZ();
        z2.z *= num.getZ();
        num = new Vec3f(x2.x + z2.x, num.getY(), x2.z + z2.z);
        return num;
    }

    public static IVec3f rotateZ(IVec3f num, float rotation) {
        Vector3f x2 = new Vector3f();
        Vector3f y2 = new Vector3f();
        x2.x = (float)Math.sin((double)((rotation - 90.0f) / 180.0f) * Math.PI);
        x2.y = (float)Math.cos((double)((rotation - 90.0f) / 180.0f) * Math.PI);
        x2.normalise();
        x2.x *= -num.getX();
        x2.y *= num.getX();
        y2.x = (float)Math.cos((double)((rotation - 90.0f) / 180.0f) * Math.PI);
        y2.y = (float)Math.sin((double)((rotation - 90.0f) / 180.0f) * Math.PI);
        y2.normalise();
        y2.x *= -num.getY();
        y2.y *= -num.getY();
        num = new Vec3f(y2.x + x2.x, y2.y + x2.y, num.getZ());
        return num;
    }

    public static float lerp(float a2, float b2, float slide) {
        return a2 + (b2 - a2) * slide;
    }

    public static float interpolateRotation(float a2, float b2, float partialTicks) {
        float f2;
        for (f2 = b2 - a2; f2 < -180.0f; f2 += 360.0f) {
        }
        while (f2 >= 180.0f) {
            f2 -= 360.0f;
        }
        return a2 + partialTicks * f2;
    }

    public static float interpolateRadians(float a2, float b2, float partialTicks) {
        float f2;
        for (f2 = b2 - a2; f2 < (float)(-Math.PI); f2 += (float)Math.PI * 2) {
        }
        while (f2 >= (float)Math.PI) {
            f2 -= (float)Math.PI * 2;
        }
        return a2 + partialTicks * f2;
    }

    public static IVec3f[] translate(IVec3f[] vectors, float x2, float y2, float z2) {
        for (IVec3f vector : vectors) {
            GUtil.translate(vector, x2, y2, z2);
        }
        return vectors;
    }

    public static IVec3f[] scale(IVec3f[] vectors, float x2, float y2, float z2) {
        for (IVec3f vector : vectors) {
            GUtil.scale(vector, x2, y2, z2);
        }
        return vectors;
    }

    public static String[] wrapText(FontRenderer fontRenderer, String text, int maxWidth) {
        if (maxWidth <= 0) {
            return new String[0];
        }
        if (!text.contains(" ")) {
            return new String[]{text};
        }
        ArrayList<String> lines = new ArrayList<String>();
        String leftover = text + "";
        String line = "";
        boolean endOfString = false;
        do {
            String leftoverToNextSpace;
            if (leftover.contains(" ")) {
                leftoverToNextSpace = leftover.substring(0, leftover.indexOf(" "));
            } else {
                leftoverToNextSpace = leftover;
                endOfString = true;
            }
            int currentWidth = fontRenderer.func_78256_a(line + leftoverToNextSpace);
            if (currentWidth > maxWidth) {
                lines.add(line.trim());
                line = leftoverToNextSpace + " ";
            } else {
                line = line + leftoverToNextSpace + " ";
            }
            if (!endOfString) {
                leftover = leftover.substring(leftover.indexOf(" ") + 1);
                continue;
            }
            lines.add(line.trim());
        } while (!endOfString);
        return lines.toArray(new String[0]);
    }

    public static String[] readLines(BufferedReader reader) {
        try {
            ArrayList<String> lines = new ArrayList<String>();
            String line = reader.readLine();
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            reader.close();
            return lines.toArray(new String[0]);
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static String[] readLines(File file) {
        try {
            return GUtil.readLines(new BufferedReader(new FileReader(file)));
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static String readFile(BufferedReader reader) {
        try {
            String line;
            String content = "";
            while ((line = reader.readLine()) != null) {
                content = content + line + "\n";
            }
            reader.close();
            return content;
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
        }
        catch (IOException e3) {
            e3.printStackTrace();
        }
        return null;
    }

    public static String readFile(File file) {
        try {
            return GUtil.readFile(new BufferedReader(new FileReader(file)));
        }
        catch (FileNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void writeLines(File file, String[] lines) {
        try {
            BufferedWriter os = new BufferedWriter(new FileWriter(file));
            for (String line : lines) {
                os.write(line);
                os.newLine();
            }
            os.close();
        }
        catch (IOException e2) {
            e2.printStackTrace();
        }
    }
}

