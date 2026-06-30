/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.opengl.GL15
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.yg;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import net.minecraft.client.renderer.GlStateManager;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.Frame;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ze {
    private static int z = 100;
    private int[] s = new int[2];
    private int[] g = new int[2];
    private ByteBuffer[] t = new ByteBuffer[2];
    private ArrayBlockingQueue<Frame> r = new ArrayBlockingQueue(z);
    private FFmpegFrameGrabber x;
    private int v = 0;
    private int m = 0;
    private int c = 1;
    private boolean q = false;
    private boolean b = false;
    private boolean o;
    private boolean y;
    private Thread k;
    private jj ALLATORIxDEMO;

    public ze(jj a2) {
        ze a3;
        a3.ALLATORIxDEMO = a2;
    }

    public void ALLATORIxDEMO(InputStream a2) {
        ze a3;
        a3.k = a3.ALLATORIxDEMO(a2);
    }

    public boolean c() {
        ze a2;
        return a2.o;
    }

    public int f() {
        ze a2;
        return a2.x == null ? 0 : a2.x.getImageHeight();
    }

    public int c() {
        ze a2;
        return a2.x == null ? 0 : a2.x.getImageWidth();
    }

    public int ALLATORIxDEMO() {
        ze a2;
        return a2.x == null ? 0 : a2.x.getBitsPerPixel() / 8;
    }

    public boolean ALLATORIxDEMO() {
        ze a2;
        return a2.x.getFormatContext() == null || a2.x.getFormatContext().isNull();
    }

    public void c() {
        a.q = true;
    }

    public void c(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9) {
        ze a10;
        if (!a10.y) {
            a10.c(a10.x.getImageWidth(), a10.x.getImageHeight(), 3);
            a10.ALLATORIxDEMO(a10.x.getImageWidth(), a10.x.getImageHeight(), 3);
            a10.y = true;
        }
        if (a10.b) {
            return;
        }
        if (a10.x.getFormatContext() == null || a10.x.getFormatContext().isNull()) {
            return;
        }
        Frame a11 = a10.r.poll();
        if (a11 != null) {
            a10.c();
            a10.ALLATORIxDEMO((ByteBuffer)a11.image[0]);
            a11.close();
        }
        if (a10.q) {
            a10.ALLATORIxDEMO(a2, a3, a4, a5, a6, a7, a8, a9);
        }
    }

    private /* synthetic */ void c(int a2, int a3, int a4) {
        int a5 = a2 * a3 * a4;
        a7.s[0] = GL15.glGenBuffers();
        a7.s[1] = GL15.glGenBuffers();
        for (int a6 = 0; a6 < 2; ++a6) {
            ze a7;
            GL15.glBindBuffer((int)35052, (int)a7.s[a6]);
            GL15.glBufferData((int)35052, (long)a5, (int)35040);
            GL15.glBindBuffer((int)35052, (int)0);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(int a2, int a3, int a4) {
        int a5 = a2 * a3 * a4;
        for (int a6 = 0; a6 < 2; ++a6) {
            ze a7;
            a7.t[a6] = ByteBuffer.allocateDirect(a5);
            a7.g[a6] = GL11.glGenTextures();
            GlStateManager.bindTexture((int)a7.g[a6]);
            GL11.glTexParameteri((int)3553, (int)10240, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10241, (int)9729);
            GL11.glTexParameteri((int)3553, (int)10242, (int)33071);
            GL11.glTexParameteri((int)3553, (int)10243, (int)33071);
            GL11.glEnable((int)3042);
            GL11.glBlendFunc((int)1, (int)771);
            GL11.glTexImage2D((int)3553, (int)0, (int)6407, (int)a2, (int)a3, (int)0, (int)32992, (int)5121, (ByteBuffer)null);
            GlStateManager.bindTexture((int)0);
        }
    }

    private /* synthetic */ void ALLATORIxDEMO(ByteBuffer a2) {
        ze a3;
        a3.v = 0;
        a3.m = (a3.m + 1) % 2;
        a3.c = (a3.m + 1) % 2;
        GlStateManager.bindTexture((int)a3.g[a3.v]);
        GL15.glBindBuffer((int)35052, (int)a3.s[a3.m]);
        GL11.glTexSubImage2D((int)3553, (int)0, (int)0, (int)0, (int)a3.x.getImageWidth(), (int)a3.x.getImageHeight(), (int)32992, (int)5121, (long)0L);
        GlStateManager.bindTexture((int)0);
        GL15.glBindBuffer((int)35052, (int)a3.s[a3.c]);
        a3.t[a3.c] = GL15.glMapBuffer((int)35052, (int)35001, (long)a2.limit(), (ByteBuffer)a3.t[a3.c]);
        a2.rewind();
        a3.t[a3.c].clear();
        a3.t[a3.c].put(a2);
        GL15.glUnmapBuffer((int)35052);
        GL15.glBindBuffer((int)35052, (int)0);
    }

    private /* synthetic */ void ALLATORIxDEMO(double a2, double a3, double a4, double a5, double a6, double a7, double a8, double a9) {
        ze a10;
        GlStateManager.pushMatrix();
        GlStateManager.bindTexture((int)a10.g[a10.v]);
        sd.ALLATORIxDEMO(a2, a3, a4, a5, a6, a7, a8, a9);
        GlStateManager.disableAlpha();
        GlStateManager.bindTexture((int)0);
        GlStateManager.popMatrix();
    }

    public void ALLATORIxDEMO() {
        try {
            ze a2;
            a2.b = true;
            if (a2.y) {
                for (int a3 = 0; a3 < 2; ++a3) {
                    GlStateManager.deleteTexture((int)a2.g[a3]);
                    GL15.glDeleteBuffers((int)a2.s[a3]);
                }
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public Thread ALLATORIxDEMO(InputStream a2) {
        ze a3;
        Thread a4 = new Thread(new yg(a3, a2));
        a4.start();
        return a4;
    }

    public static /* synthetic */ FFmpegFrameGrabber ALLATORIxDEMO(ze a2, FFmpegFrameGrabber a3) {
        a2.x = a3;
        return a2.x;
    }

    public static /* synthetic */ FFmpegFrameGrabber ALLATORIxDEMO(ze a2) {
        return a2.x;
    }

    public static /* synthetic */ boolean ALLATORIxDEMO(ze a2, boolean a3) {
        a2.o = a3;
        return a2.o;
    }

    public static /* synthetic */ jj ALLATORIxDEMO(ze a2) {
        return a2.ALLATORIxDEMO;
    }

    public static /* synthetic */ boolean ALLATORIxDEMO(ze a2) {
        return a2.b;
    }

    public static /* synthetic */ ArrayBlockingQueue ALLATORIxDEMO(ze a2) {
        return a2.r;
    }
}

