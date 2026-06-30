/*
 * Decompiled with CFR 0.152.
 */
package eos.moe.dragoncore;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.ComponentColorModel;
import java.awt.image.DataBufferByte;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Hashtable;
import javax.imageio.ImageIO;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ba {
    private static final ColorModel m = new ComponentColorModel(ColorSpace.getInstance(1000), new int[]{8, 8, 8, 8}, true, false, 3, 0);
    private static final ColorModel c = new ComponentColorModel(ColorSpace.getInstance(1000), new int[]{8, 8, 8, 0}, false, false, 1, 0);
    private int q;
    private int b;
    private int o;
    private int y;
    private int k;
    private boolean ALLATORIxDEMO = true;

    public ba() {
        ba a2;
    }

    public int d() {
        ba a2;
        return a2.q;
    }

    public int x() {
        ba a2;
        return a2.b;
    }

    public int f() {
        ba a2;
        return a2.k;
    }

    public int c() {
        ba a2;
        return a2.y;
    }

    public int ALLATORIxDEMO() {
        ba a2;
        return a2.o;
    }

    public ByteBuffer ALLATORIxDEMO(InputStream a2) throws IOException {
        ba a3;
        return a3.ALLATORIxDEMO(a2, true, null);
    }

    public ByteBuffer ALLATORIxDEMO(InputStream a2, boolean a3, int[] a4) throws IOException {
        ba a5;
        return a5.ALLATORIxDEMO(a2, a3, false, a4);
    }

    public ByteBuffer ALLATORIxDEMO(InputStream a2, boolean a3, boolean a4, int[] a5) throws IOException {
        ba a6;
        if (a5 != null) {
            a4 = true;
        }
        BufferedImage a7 = ImageIO.read(a2);
        return a6.ALLATORIxDEMO(a7, a3, a4, a5);
    }

    public ByteBuffer ALLATORIxDEMO(BufferedImage a2, boolean a3, boolean a4, int[] a5) {
        ba a6;
        BufferedImage a7;
        WritableRaster a8;
        boolean a9;
        int a10;
        ByteBuffer a11 = null;
        int a12 = 2;
        for (a10 = 2; a10 < a2.getWidth(); a10 *= 2) {
        }
        while (a12 < a2.getHeight()) {
            a12 *= 2;
        }
        a6.o = a2.getWidth();
        a6.b = a2.getHeight();
        a6.k = a12;
        a6.y = a10;
        boolean bl2 = a9 = a2.getColorModel().hasAlpha() || a4;
        if (a9) {
            a6.q = 32;
            a8 = Raster.createInterleavedRaster(0, a10, a12, 4, null);
            a7 = new BufferedImage(m, a8, false, new Hashtable());
        } else {
            a6.q = 24;
            a8 = Raster.createInterleavedRaster(0, a10, a12, 3, null);
            a7 = new BufferedImage(c, a8, false, new Hashtable());
        }
        Graphics2D a13 = (Graphics2D)a7.getGraphics();
        if (a9) {
            a13.setColor(new Color(0.0f, 0.0f, 0.0f, 0.0f));
            a13.fillRect(0, 0, a10, a12);
        }
        if (a3) {
            a13.scale(1.0, -1.0);
            a13.drawImage((Image)a2, 0, -a6.b, null);
        } else {
            a13.drawImage((Image)a2, 0, 0, null);
        }
        if (a6.ALLATORIxDEMO) {
            if (a6.b < a12 - 1) {
                a6.ALLATORIxDEMO(a7, 0, 0, a6.o, 1, 0, a12 - 1);
                a6.ALLATORIxDEMO(a7, 0, a6.b - 1, a6.o, 1, 0, 1);
            }
            if (a6.o < a10 - 1) {
                a6.ALLATORIxDEMO(a7, 0, 0, 1, a6.b, a10 - 1, 0);
                a6.ALLATORIxDEMO(a7, a6.o - 1, 0, 1, a6.b, 1, 0);
            }
        }
        byte[] a14 = ((DataBufferByte)a7.getRaster().getDataBuffer()).getData();
        if (a5 != null) {
            for (int a15 = 0; a15 < a14.length; a15 += 4) {
                boolean a16 = true;
                for (int a17 = 0; a17 < 3; ++a17) {
                    int a18;
                    int n2 = a18 = a14[a15 + a17] < 0 ? 256 + a14[a15 + a17] : a14[a15 + a17];
                    if (a18 == a5[a17]) continue;
                    a16 = false;
                }
                if (!a16) continue;
                a14[a15 + 3] = 0;
            }
        }
        a11 = ByteBuffer.allocateDirect(a14.length);
        a11.order(ByteOrder.nativeOrder());
        a11.put(a14, 0, a14.length);
        a11.flip();
        a13.dispose();
        return a11;
    }

    public ByteBuffer ALLATORIxDEMO() {
        throw new RuntimeException("ImageIOImageData doesn't store it's image.");
    }

    private /* synthetic */ void ALLATORIxDEMO(BufferedImage a2, int a3, int a4, int a5, int a6, int a7, int a8) {
        Graphics2D a9 = (Graphics2D)a2.getGraphics();
        a9.drawImage((Image)a2.getSubimage(a3, a4, a5, a6), a3 + a7, a4 + a8, null);
    }

    public void ALLATORIxDEMO(boolean a2) {
        a.ALLATORIxDEMO = a2;
    }
}

