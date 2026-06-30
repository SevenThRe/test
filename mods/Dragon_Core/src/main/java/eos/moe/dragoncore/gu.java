/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class gu {
    private final Minecraft u;
    public final int w;
    public final int a;
    public final int e;
    public final int n;
    public final int j;
    public final int i;
    public final int l;
    public final int z;
    public final int s;
    private int g;
    private int t;
    public int r;
    public int x;
    private float v = -2.0f;
    private float m;
    private float c;
    public int q = -1;
    private long b = 0L;
    private boolean o = true;
    private boolean y;
    private int k;
    public boolean ALLATORIxDEMO = true;

    public gu(int a2, int a3, int a4, int a5, int a6, int a7, int a8) {
        a9(Minecraft.func_71410_x(), a4, a5, a3, a3 + a5, a2, a6, a7, a8);
        gu a9;
    }

    public gu(Minecraft a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10) {
        gu a11;
        a11.u = a2;
        a11.w = a3;
        a11.a = a4;
        a11.j = a5;
        a11.i = a6;
        a11.s = a8;
        a11.z = a7;
        a11.l = a3 + a11.z;
        a11.e = a9;
        a11.n = a10;
    }

    @Deprecated
    public void ALLATORIxDEMO(boolean a2) {
        a.o = a2;
    }

    @Deprecated
    public void c(boolean a2, int a3) {
        gu a4;
        a4.ALLATORIxDEMO(a2, a3);
    }

    public void ALLATORIxDEMO(boolean a2, int a3) {
        a.y = a2;
        a.k = a3;
        if (!a2) {
            a.k = 0;
        }
    }

    public boolean ALLATORIxDEMO(int a2, int a3) {
        gu a4;
        return a2 >= a4.z && a2 <= a4.l && a3 >= a4.j && a3 <= a4.i;
    }

    public abstract int c();

    public abstract void ALLATORIxDEMO(int var1, boolean var2);

    public abstract boolean ALLATORIxDEMO(int var1);

    public int ALLATORIxDEMO() {
        gu a2;
        return a2.c() * a2.s + a2.k;
    }

    public void c() {
        gu a2;
        Gui.func_73734_a((int)(a2.z - 1), (int)(a2.j - 1), (int)(a2.l + 1), (int)(a2.i + 1), (int)-13158601);
        Gui.func_73734_a((int)a2.z, (int)a2.j, (int)a2.l, (int)a2.i, (int)-16777216);
    }

    public abstract void ALLATORIxDEMO(int var1, int var2, int var3, int var4, boolean var5, Tessellator var6);

    @Deprecated
    public void c(int a2, int a3, Tessellator a4) {
    }

    public void ALLATORIxDEMO(int a2, int a3, Tessellator a4) {
        gu a5;
        a5.c(a2, a3, a4);
    }

    @Deprecated
    public void d(int a2, int a3) {
    }

    public void x(int a2, int a3) {
        gu a4;
        a4.d(a2, a3);
    }

    @Deprecated
    public void f(int a2, int a3) {
    }

    public void c(int a2, int a3) {
        gu a4;
        a4.f(a2, a3);
    }

    @Deprecated
    public int ALLATORIxDEMO(int a2, int a3) {
        gu a4;
        int a5 = a4.z + 1;
        int a6 = a4.z + a4.w - 7;
        int a7 = a3 - a4.j - a4.k + (int)a4.c - 4;
        int a8 = a7 / a4.s;
        return a2 >= a5 && a2 <= a6 && a8 >= 0 && a7 >= 0 && a8 < a4.c() ? a8 : -1;
    }

    public void ALLATORIxDEMO(List<GuiButton> a2, int a3, int a4) {
        a.g = a3;
        a.t = a4;
    }

    private /* synthetic */ void ALLATORIxDEMO() {
        gu a2;
        int a3 = a2.ALLATORIxDEMO() - (a2.i - a2.j - 4);
        if (a3 < 0) {
            a3 /= 2;
        }
        if (a2.c > (float)a3) {
            a2.c = a3;
        }
        if (a2.c < 0.0f) {
            a2.c = 0.0f;
        }
    }

    public void ALLATORIxDEMO(GuiButton a2) {
        if (a2.field_146124_l) {
            gu a3;
            if (a2.field_146127_k == a3.g) {
                a3.c -= (float)(a3.s * 2 / 3);
                a3.v = -2.0f;
                a3.ALLATORIxDEMO();
            } else if (a2.field_146127_k == a3.t) {
                a3.c += (float)(a3.s * 2 / 3);
                a3.v = -2.0f;
                a3.ALLATORIxDEMO();
            }
        }
    }

    public void ALLATORIxDEMO(int a2, int a3) throws IOException {
        gu a4;
        boolean a5;
        boolean bl2 = a5 = a2 >= a4.z && a2 <= a4.z + a4.w && a3 >= a4.j && a3 <= a4.i;
        if (!a5) {
            return;
        }
        int a6 = Mouse.getEventDWheel();
        if (a6 != 0) {
            a4.c += (float)(-1 * a6) / 120.0f * (float)a4.s / 2.0f;
        }
    }

    public void ALLATORIxDEMO(int a2, int a3, float a4) {
        int a5;
        int a6;
        int a7;
        gu a8;
        a8.r = a2;
        a8.x = a3;
        a8.c();
        boolean a9 = a2 >= a8.z && a2 <= a8.z + a8.w && a3 >= a8.j && a3 <= a8.i;
        int a10 = a8.c();
        int a11 = 10;
        int a12 = a8.z + a8.w;
        int a13 = a12 - a11;
        int a14 = a8.z;
        int a15 = a13 - 1;
        int a16 = a8.i - a8.j;
        int a17 = 4;
        if (Mouse.isButtonDown((int)0)) {
            if (a8.v == -1.0f) {
                if (a9) {
                    int a18 = a3 - a8.j - a8.k + (int)a8.c - a17;
                    int a19 = a18 / a8.s;
                    if (a2 >= a14 && a2 <= a15 && a19 >= 0 && a18 >= 0 && a19 < a10) {
                        a8.ALLATORIxDEMO(a19, a19 == a8.q && System.currentTimeMillis() - a8.b < 250L);
                        a8.q = a19;
                        a8.b = System.currentTimeMillis();
                    } else if (a2 >= a14 && a2 <= a15 && a18 < 0) {
                        a8.x(a2 - a14, a3 - a8.j + (int)a8.c - a17);
                    }
                    if (a2 >= a13 && a2 <= a12) {
                        int a20;
                        a8.m = -1.0f;
                        int a21 = a8.ALLATORIxDEMO() - a16 - a17;
                        if (a21 < 1) {
                            a21 = 1;
                        }
                        if ((a20 = (int)((float)(a16 * a16) / (float)a8.ALLATORIxDEMO())) < 32) {
                            a20 = 32;
                        }
                        if (a20 > a16 - a17 * 2) {
                            a20 = a16 - a17 * 2;
                        }
                        a8.m /= (float)(a16 - a20) / (float)a21;
                    } else {
                        a8.m = 1.0f;
                    }
                    a8.v = a3;
                } else {
                    a8.v = -2.0f;
                }
            } else if (a8.v >= 0.0f) {
                a8.c -= ((float)a3 - a8.v) * a8.m;
                a8.v = a3;
            }
        } else {
            a8.v = -1.0f;
        }
        a8.ALLATORIxDEMO();
        Tessellator a22 = Tessellator.func_178181_a();
        BufferBuilder a23 = a22.func_178180_c();
        ScaledResolution a24 = new ScaledResolution(a8.u);
        double a25 = (double)a8.u.field_71443_c / a24.func_78327_c();
        double a26 = (double)a8.u.field_71440_d / a24.func_78324_d();
        GL11.glEnable((int)3089);
        GL11.glScissor((int)((int)((double)a8.z * a25)), (int)((int)((double)a8.u.field_71440_d - (double)a8.i * a26)), (int)((int)((double)a8.w * a25)), (int)((int)((double)a16 * a26)));
        if (a8.u.field_71441_e == null) {
            GlStateManager.func_179140_f();
            GlStateManager.func_179106_n();
            a8.u.field_71446_o.func_110577_a(Gui.field_110325_k);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            float a27 = 32.0f;
            a23.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            a23.func_181662_b((double)a8.z, (double)a8.i, 0.0).func_187315_a((double)((float)a8.z / 32.0f), (double)((float)(a8.i + (int)a8.c) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            a23.func_181662_b((double)a8.l, (double)a8.i, 0.0).func_187315_a((double)((float)a8.l / 32.0f), (double)((float)(a8.i + (int)a8.c) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            a23.func_181662_b((double)a8.l, (double)a8.j, 0.0).func_187315_a((double)((float)a8.l / 32.0f), (double)((float)(a8.j + (int)a8.c) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            a23.func_181662_b((double)a8.z, (double)a8.j, 0.0).func_187315_a((double)((float)a8.z / 32.0f), (double)((float)(a8.j + (int)a8.c) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            a22.func_78381_a();
        }
        int a28 = a8.j + a17 - (int)a8.c;
        if (a8.y) {
            a8.ALLATORIxDEMO(a15, a28, a22);
        }
        for (a7 = 0; a7 < a10; ++a7) {
            a6 = a28 + a7 * a8.s + a8.k;
            a5 = a8.s - a17;
            if (a6 > a8.i || a6 + a5 < a8.j) continue;
            boolean a29 = a2 >= a8.z + 1 && a2 <= a8.z + a8.w - 12 && a3 >= a6 - 1 && a3 <= a6 + a8.s - 3;
            a8.ALLATORIxDEMO(a7, a15, a6, a5, a29, a22);
        }
        GlStateManager.func_179097_i();
        a7 = a8.ALLATORIxDEMO() + a17 - a16;
        if (a7 > 0) {
            a6 = a16 * a16 / a8.ALLATORIxDEMO();
            if (a6 < 32) {
                a6 = 32;
            }
            if (a6 > a16 - a17 * 2) {
                a6 = a16 - a17 * 2;
            }
            if ((a5 = (int)a8.c * (a16 - a6) / a7 + a8.j) < a8.j) {
                a5 = a8.j;
            }
            a8.ALLATORIxDEMO(a13, a5, a6);
        }
        a8.c(a2, a3);
        GlStateManager.func_179098_w();
        GlStateManager.func_179103_j((int)7424);
        GlStateManager.func_179141_d();
        GlStateManager.func_179084_k();
        GL11.glDisable((int)3089);
    }

    public void ALLATORIxDEMO(int a2, int a3, int a4) {
        gu a5;
        Gui.func_73734_a((int)a2, (int)a5.j, (int)(a2 + 10), (int)a5.i, (int)-1);
        Gui.func_73734_a((int)(a2 + 1), (int)(a5.j + 1), (int)(a2 + 9), (int)(a5.i - 1), (int)-13158601);
        Gui.func_73734_a((int)(a2 + 2), (int)(a5.j + 2), (int)(a2 + 9), (int)(a5.i - 2), (int)-7631989);
        Gui.func_73734_a((int)(a2 + 1), (int)a3, (int)(a2 + 10), (int)(a3 + a4), (int)-1);
        Gui.func_73734_a((int)(a2 + 2), (int)(a3 + 1), (int)(a2 + 10), (int)(a3 + a4), (int)-13158601);
        Gui.func_73734_a((int)(a2 + 2), (int)(a3 + 1), (int)(a2 + 9), (int)(a3 + a4 - 1), (int)-7631989);
        Gui.func_73734_a((int)(a2 + 10), (int)a3, (int)(a2 + 10), (int)(a3 + 1), (int)-7631989);
        Gui.func_73734_a((int)(a2 + 1), (int)(a3 + a4 - 1), (int)(a2 + 1), (int)(a3 + a4), (int)-7631989);
    }

    public void ALLATORIxDEMO(int a2, int a3, int a4, int a5, int a6, int a7) {
        GuiUtils.drawGradientRect((int)0, (int)a2, (int)a3, (int)a4, (int)a5, (int)a6, (int)a7);
    }
}

