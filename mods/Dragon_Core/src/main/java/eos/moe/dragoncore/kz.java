/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 *  org.lwjgl.BufferUtils
 */
package eos.moe.dragoncore;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.BufferUtils;

public class kz {
    private static String ALLATORIxDEMO = "There was an error loading the %s \"%s\".";

    public kz() {
        kz a2;
    }

    public static BufferedImage ALLATORIxDEMO(String a2) throws IOException {
        InputStream a3 = null;
        try {
            a2 = a2.replaceAll("//", "/");
            a3 = kz.class.getResourceAsStream(a2);
            return ImageIO.read(a3);
        }
        catch (IOException a4) {
            throw new IOException(String.format(ALLATORIxDEMO, "file", a2));
        }
    }

    public static BufferedImage ALLATORIxDEMO(ResourceLocation a2) throws IOException {
        try {
            InputStream a3 = Minecraft.func_71410_x().func_110442_L().func_110536_a(a2).func_110527_b();
            return ImageIO.read(a3);
        }
        catch (IOException a4) {
            throw new IOException(String.format(ALLATORIxDEMO, "ResourceLocation", a2));
        }
    }

    public static BufferedImage ALLATORIxDEMO(BufferedImage a2, double a3) {
        double a4 = (double)a2.getWidth() * 0.5;
        double a5 = (double)a2.getHeight() * 0.5;
        double a6 = Math.toRadians(a3);
        AffineTransform a7 = new AffineTransform();
        a7.rotate(a6, a4, a5);
        AffineTransformOp a8 = new AffineTransformOp(a7, 1);
        return a8.filter(a2, null);
    }

    public static ByteBuffer ALLATORIxDEMO(BufferedImage a2) {
        int a3 = a2.getWidth();
        int a4 = a2.getHeight();
        int[] a5 = new int[a2.getWidth() * a2.getHeight()];
        a2.getRGB(0, 0, a3, a4, a5, 0, a3);
        ByteBuffer a6 = BufferUtils.createByteBuffer((int)(a3 * a4 * 4));
        for (int a7 = 0; a7 < a4; ++a7) {
            for (int a8 = 0; a8 < a3; ++a8) {
                int a9 = a5[a7 * a3 + a8];
                a6.put((byte)(a9 >> 16 & 0xFF));
                a6.put((byte)(a9 >> 8 & 0xFF));
                a6.put((byte)(a9 & 0xFF));
                a6.put((byte)(a9 >> 24 & 0xFF));
            }
        }
        a6.flip();
        a6.rewind();
        return a6;
    }
}

