/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.DefaultPlayerSkin
 *  net.minecraft.util.ResourceLocation
 */
package eos.moe.armourers;

import eos.moe.armourers.bh;
import eos.moe.armourers.gl;
import eos.moe.armourers.in;
import eos.moe.armourers.ji;
import eos.moe.armourers.lo;
import eos.moe.armourers.mn;
import eos.moe.armourers.n;
import eos.moe.armourers.o;
import eos.moe.armourers.oh;
import eos.moe.armourers.te;
import eos.moe.armourers.wf;
import eos.moe.armourers.y;
import eos.moe.armourers.yl;
import eos.moe.armourers.ym;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.util.ResourceLocation;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class ae {
    private int[] d;
    private boolean h;
    private boolean a;
    private int e;
    private BufferedImage b;
    private int[] g;
    private yl[] z;
    private ResourceLocation t;
    private BufferedImage w;
    private int r;
    private ResourceLocation l;
    private n[] c;
    private oh v;
    private int s;
    private static int m = 64;
    private static int j = 32;

    private /* synthetic */ void x() {
        ae a2;
        ae ae2 = a2;
        ae2.h();
        ae2.r();
        ae2.y();
    }

    private /* synthetic */ void r(BufferedImage a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10) {
        if (a4 >= 20) {
            if (a3 < 12) {
                a2.setRGB(15 + (12 - a3), a4 + 32, a5);
                return;
            }
            a2.setRGB(27 + (4 - (a3 - 12)), a4 + 32, a5);
            return;
        }
        a2.setRGB(15 + (16 - a3), a4 + 32, a5);
    }

    public ae(int a2, int a3) {
        ae a4;
        ae ae2 = a4;
        ae ae3 = a4;
        ae ae4 = a4;
        a4.r = a2;
        ae4.e = a3;
        ae4.s = -1;
        ae3.g = new int[5 * wf.j];
        ae3.d = new int[5 * wf.j];
        a4.l = null;
        a4.t = null;
        Arrays.fill(a4.g, -1);
        Arrays.fill(a4.d, -1);
        a4.v = oh.l;
        ae2.a = true;
        ae2.h = false;
    }

    public ResourceLocation z() {
        ae a2;
        ae ae2 = a2;
        ae2.z();
        if (ae2.t != null) {
            return a2.t;
        }
        return a2.l;
    }

    private /* synthetic */ int r(byte[] a2, int a3, int a4, yl a5) {
        Object object;
        byte by = (byte)(a3 >>> 16 & 0xFF);
        byte by2 = (byte)(a3 >>> 8 & 0xFF);
        a3 = (byte)(a3 & 0xFF);
        if (a2.length > 3 && ((ym)(object = te.r(a2[3]))).r() != null) {
            ae a6;
            a2 = a6.v.r(((ym)object).r());
        }
        int[] nArray = new int[3];
        nArray[0] = 127;
        nArray[1] = 127;
        nArray[2] = 127;
        object = nArray;
        if (a5 != null) {
            object = a5.r(a4);
        }
        a2 = gl.r(by, by2, (byte)a3, a2, (int[])object);
        return -16777216 + ((a2[0] & 0xFF) << 16) + ((a2[1] & 0xFF) << 8) + (a2[2] & 0xFF);
    }

    private /* synthetic */ void h() {
        ae a2;
        a2.w = bh.r(a2.b);
    }

    public void r(oh a2) {
        ae a3;
        if (!a3.v.equals(a2)) {
            ae ae2 = a3;
            ae ae3 = a3;
            ae2.v = new oh(a2);
            ae2.a = true;
        }
    }

    public void z() {
        ae a2;
        if (a2.a) {
            a2.x();
            a2.a = false;
        }
    }

    public ae() {
        a2(64, 64);
        ae a2;
    }

    private /* synthetic */ void r(yl a2, n a3, BufferedImage a4) {
        int n2 = a4 = 0;
        while (n2 < m) {
            int n3;
            int n4 = n3 = 0;
            while (n4 < j) {
                int n5;
                ae a5;
                int n6 = a2.r()[a4 + n3 * a5.r];
                ym ym2 = te.y(n6);
                if (ym2 == te.c) {
                    ae ae2 = a5;
                    ae2.r(ae2.w, a4, n3, lo.r(n6, 0, 255));
                } else if (ym2.z() >= 1 && ym2.z() <= 8) {
                    n5 = ym2.z() - 1;
                    if (a3 != null && a3.r(n5)) {
                        byte[] byArray = a3.r(n5);
                        ae ae3 = a5;
                        int n7 = ae3.r(byArray, n6, ym2.r(), a2);
                        ae3.r(ae3.w, a4, n3, n7);
                    } else {
                        ae ae4 = a5;
                        ae4.r(ae4.w, a4, n3, lo.r(n6, 0, 255));
                    }
                } else if (ym2.r() != null) {
                    ae ae5 = a5;
                    n5 = ae5.r(ae5.v.r(ym2.r()), n6, ym2.r(), a2);
                    ae5.r(ae5.w, a4, n3, n5);
                }
                n4 = ++n3;
            }
            n2 = ++a4;
        }
    }

    public ResourceLocation y() {
        ae a2;
        return a2.t;
    }

    public static byte[] r(byte ... a2) {
        return a2;
    }

    public void r(n[] a2) {
        ae a3;
        int n2;
        a3.c = a2;
        int n3 = n2 = 0;
        while (n3 < a3.z.length) {
            if (a2[n2] != null) {
                if (a2[n2].hashCode() != a3.d[n2]) {
                    int n4 = n2;
                    a3.d[n4] = a2[n4].hashCode();
                    a3.a = true;
                }
            } else if (a3.d[n2] != -1) {
                a3.d[n2] = -1;
                a3.a = true;
            }
            n3 = ++n2;
        }
    }

    /*
     * Unable to fully structure code
     */
    private /* synthetic */ void r(BufferedImage a, int a, int a, int a) {
        v0 = a;
        a.setRGB(v0, a, a);
        if (v0 < 16) {
            v1 = true;
            v2 = a;
        } else {
            v1 = false;
            v2 = a;
        }
        if (!(v1 & v2 >= 16 & a < 32)) ** GOTO lbl23
        if (a >= 20) {
            if (a < 12) {
                v3 = a;
                a.setRGB(15 + (12 - a), a + 32, a);
            } else {
                a.setRGB(27 + (4 - (a - 12)), a + 32, a);
                v3 = a;
            }
        } else if (a < 8) {
            v3 = a;
            a.setRGB(15 + (8 - (a - 4)), a + 32, a);
        } else {
            a.setRGB(23 + (8 - (a - 4)), a + 32, a);
lbl23:
            // 2 sources

            v3 = a;
        }
        if (v3 >= 40) {
            v4 = true;
            v5 = a;
        } else {
            v4 = false;
            v5 = a;
        }
        if (v4 & v5 < 56 & a >= 16 & a < 32) {
            if (a >= 20) {
                if (a < 52) {
                    a.setRGB(12 - (a - 40) + 31, a + 32, a);
                    return;
                }
                a.setRGB(4 - (a - 52) + 43, a + 32, a);
                return;
            }
            if (a < 48) {
                a.setRGB(8 - (a - 40) + 4 + 31, a + 32, a);
                return;
            }
            a.setRGB(8 - (a - 48) + 4 + 31, a + 32, a);
        }
    }

    public void r(yl[] a2) {
        ae a3;
        int n2;
        a3.z = a2;
        if (a2.length == 0) {
            int n3 = n2 = 0;
            while (n3 < a3.g.length) {
                if (a3.g[n2] != -1) {
                    a3.g[n2] = -1;
                    a3.a = true;
                }
                n3 = ++n2;
            }
        }
        int n4 = n2 = 0;
        while (n4 < a3.g.length) {
            if (n2 < a2.length && a2[n2] != null) {
                if (a2[n2].r() != a3.g[n2]) {
                    int n5 = n2;
                    a3.g[n5] = a2[n5].r();
                    a3.a = true;
                }
            } else if (a3.g[n2] != -1) {
                a3.g[n2] = -1;
                a3.a = true;
            }
            n4 = ++n2;
        }
    }

    public void r(o a2, BufferedImage a3, in a4) {
        int n2;
        o o2 = a2;
        Point point = o2.z();
        Point point2 = o2.y();
        int n3 = o2.z().z() * 2 + a2.z().y() * 2;
        int n4 = o2.z().r() + a2.z().y();
        int n5 = n2 = 0;
        while (n5 < n3) {
            int n6;
            int n7 = n6 = 0;
            while (n7 < n4) {
                if (a2.r(a4)) {
                    a3.setRGB(point.x + n2, point.y + n6, 0xFFFFFF);
                }
                if (a2.y(a4)) {
                    a3.setRGB(point2.x + n2, point2.y + n6, 0xFFFFFF);
                }
                n7 = ++n6;
            }
            n5 = ++n2;
        }
    }

    private /* synthetic */ void y() {
        ae a2;
        TextureManager textureManager = Minecraft.getMinecraft().renderEngine;
        if (a2.t != null) {
            textureManager.deleteTexture(a2.t);
        }
        ae ae2 = a2;
        ji ji2 = new ji(ae2, ae2.w);
        a2.t = new ResourceLocation("armourers_workshops".toLowerCase(), String.valueOf(a2.w.hashCode()));
        textureManager.loadTexture(a2.t, (ITextureObject)ji2);
    }

    public ResourceLocation r() {
        ae a2;
        return a2.l;
    }

    private /* synthetic */ void r() {
        yl yl2;
        ae a2;
        int n2;
        int n3 = n2 = 0;
        while (n3 < a2.z.length) {
            yl2 = a2.z[n2];
            if (yl2 != null && yl2.r()) {
                ae ae2 = a2;
                ae2.r(yl2, new mn(), ae2.w);
            }
            n3 = ++n2;
        }
        int n4 = n2 = 0;
        while (n4 < a2.z.length) {
            yl2 = a2.z[n2];
            if (yl2 != null) {
                int n5;
                int n6 = n5 = 0;
                while (n6 < yl2.r().r().size()) {
                    y y2 = yl2.r().r().get(n5);
                    if (y2 instanceof o) {
                        ae ae3 = a2;
                        ae3.r((o)y2, ae3.w, yl2.r());
                    }
                    n6 = ++n5;
                }
            }
            n4 = ++n2;
        }
    }

    public boolean r() {
        ae a2;
        return a2.a;
    }

    public void finalize() throws Throwable {
        ae a2;
        TextureManager textureManager = Minecraft.getMinecraft().renderEngine;
        if (a2.t != null) {
            textureManager.deleteTexture(a2.t);
        }
        super.finalize();
    }

    public void r(ResourceLocation a2) {
        ae a3;
        if (a3.s != a2.hashCode()) {
            BufferedImage bufferedImage = bh.r(a2);
            a3.b = null;
            if (bufferedImage != null) {
                ae ae2 = a3;
                ae ae3 = a3;
                a3.h = false;
                ae3.s = a2.hashCode();
                ae3.l = a2;
                ae2.b = bufferedImage;
                ae2.a = true;
            }
        }
        if (a3.b == null) {
            ae ae4;
            boolean bl;
            a3.s = DefaultPlayerSkin.getDefaultSkinLegacy().hashCode();
            a3.b = bh.r(DefaultPlayerSkin.getDefaultSkinLegacy());
            if (a3.b != null) {
                bl = true;
                ae4 = a3;
            } else {
                bl = false;
                ae4 = a3;
            }
            if (bl & !ae4.h) {
                ae ae5 = a3;
                ae5.h = true;
                ae5.a = true;
            }
        }
    }
}

