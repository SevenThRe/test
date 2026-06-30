/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.Gui
 *  net.minecraft.client.gui.GuiButton
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  org.lwjgl.input.Mouse
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import java.io.IOException;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraftforge.fml.client.config.GuiUtils;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public abstract class ge {
    private float i;
    private float k;
    private boolean p;
    public final int n;
    private boolean q;
    public final int f;
    public final int u;
    private float d;
    private int h;
    public int a;
    public int e;
    private int b;
    public final int g;
    private long z;
    public final int t;
    public boolean w;
    public final int r;
    private int l;
    public final int c;
    public int v;
    public final int s;
    public final int m;
    private final Minecraft j;

    public abstract void r(int var1, boolean var2);

    public ge(Minecraft a2, int a3, int a4, int a5, int a6, int a7, int a8, int a9, int a10) {
        ge a11;
        ge ge2 = a11;
        ge ge3 = a11;
        ge ge4 = a11;
        ge ge5 = a11;
        ge ge6 = a11;
        ge ge7 = a11;
        ge ge8 = a11;
        ge8.d = -2.0f;
        ge8.v = -1;
        ge7.z = 0L;
        ge7.p = true;
        ge6.w = true;
        ge6.j = a2;
        ge5.m = a3;
        ge5.t = a4;
        ge4.u = a5;
        ge4.g = a6;
        ge3.c = a8;
        ge3.f = a7;
        a11.s = a3 + a11.f;
        ge2.n = a9;
        ge2.r = a10;
    }

    @Deprecated
    public void x(int a2, int a3) {
    }

    public boolean r(int a2, int a3) {
        ge a4;
        return a2 >= a4.f && a2 <= a4.s && a3 >= a4.u && a3 <= a4.g;
    }

    @Deprecated
    public int r(int a2, int a3) {
        ge a4;
        ge ge2 = a4;
        int n2 = ge2.f + 1;
        int n3 = ge2.f + a4.m - 7;
        a3 = a3 - a4.u - a4.l + (int)a4.k - 4;
        int n4 = a3 / a4.c;
        if (a2 >= n2 && a2 <= n3 && n4 >= 0 && a3 >= 0 && n4 < a4.y()) {
            return n4;
        }
        return -1;
    }

    public ge(int a2, int a3, int a4, int a5, int a6, int a7, int a8) {
        ge a9;
        int n2 = a3;
        int n3 = a5;
        a9(Minecraft.func_71410_x(), a4, n3, n2, n2 + n3, a2, a6, a7, a8);
    }

    /*
     * Unable to fully structure code
     */
    public void r(int a, int a, float a) {
        block24: {
            block25: {
                block22: {
                    block23: {
                        a.e = a;
                        a.a = a;
                        a.r();
                        if (a < a.f) ** GOTO lbl-1000
                        v0 = a;
                        if (a <= v0.f + v0.m && a >= a.u && a <= a.g) {
                            v1 = true;
                        } else lbl-1000:
                        // 2 sources

                        {
                            v1 = false;
                        }
                        a = v1;
                        v2 = a;
                        var4_7 = v2.y();
                        var5_8 = 10;
                        v3 = a;
                        var6_9 = v2.f + v3.m;
                        var5_8 = var6_9 - var5_8;
                        var7_10 = v3.f;
                        var8_11 = var5_8 - 1;
                        var9_12 = v2.g - a.u;
                        var10_13 = 4;
                        if (!Mouse.isButtonDown((int)0)) break block22;
                        if (a.d != -1.0f) break block23;
                        if (a) {
                            var11_14 = a - a.u - a.l + (int)a.k - var10_13;
                            var12_16 = var11_14 / a.c;
                            if (a >= var7_10 && a <= var8_11 && var12_16 >= 0 && var11_14 >= 0 && var12_16 < var4_7) {
                                v4 = var12_16;
                                v5 = a;
                                v5.r(v4, v4 == v5.v && System.currentTimeMillis() - a.z < 250L);
                                v6 = a;
                                v7 = a;
                                v7.v = var12_16;
                                v7.z = System.currentTimeMillis();
                            } else {
                                if (a >= var7_10 && a <= var8_11 && var11_14 < 0) {
                                    v8 = a;
                                    v8.z(a - var7_10, a - v8.u + (int)a.k - var10_13);
                                }
                                v6 = a;
                            }
                            if (v6 >= var5_8 && a <= var6_9) {
                                a.i = -1.0f;
                                var13_20 = a.r() - var9_12 - var10_13;
                                if (var13_20 < 1) {
                                    var13_20 = 1;
                                }
                                v9 = var9_12;
                                var14_23 = (int)((float)(v9 * v9) / (float)a.r());
                                if (var14_23 < 32) {
                                    var14_23 = 32;
                                }
                                if (var14_23 > var9_12 - var10_13 * 2) {
                                    var14_23 = var9_12 - var10_13 * 2;
                                }
                                v10 = a;
                                v11 = v10;
                                v10.i /= (float)(var9_12 - var14_23) / (float)var13_20;
                            } else {
                                v11 = a;
                                a.i = 1.0f;
                            }
                            v11.d = a;
                            v12 = a;
                        } else {
                            v12 = a;
                            a.d = -2.0f;
                        }
                        break block24;
                    }
                    if (!(a.d >= 0.0f)) break block25;
                    v13 = a;
                    v12 = v13;
                    v13.k -= ((float)a - a.d) * a.i;
                    v13.d = a;
                    break block24;
                }
                a.d = -1.0f;
            }
            v12 = a;
        }
        v12.y();
        var11_15 = Tessellator.func_178181_a();
        var12_17 = var11_15.func_178180_c();
        var13_21 = new ScaledResolution(a.j);
        v14 = a;
        var14_24 = (double)v14.j.field_71443_c / var13_21.func_78327_c();
        var16_25 = (double)v14.j.field_71440_d / var13_21.func_78324_d();
        GL11.glEnable((int)3089);
        GL11.glScissor((int)((int)((double)v14.f * var14_24)), (int)((int)((double)a.j.field_71440_d - (double)a.g * var16_25)), (int)((int)((double)a.m * var14_24)), (int)((int)((double)var9_12 * var16_25)));
        if (v14.j.field_71441_e != null) {
            v15 = a;
        } else {
            GlStateManager.func_179140_f();
            GlStateManager.func_179106_n();
            a.j.field_71446_o.func_110577_a(Gui.field_110325_k);
            GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            a = 32.0f;
            v16 = a;
            v15 = v16;
            v17 = var12_17;
            v18 = var12_17;
            v18.func_181668_a(7, DefaultVertexFormats.field_181709_i);
            v19 = a;
            v20 = a;
            v18.func_181662_b((double)a.f, (double)a.g, 0.0).func_187315_a((double)((float)v19.f / 32.0f), (double)((float)(v20.g + (int)v20.k) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            v21 = a;
            v17.func_181662_b((double)v19.s, (double)a.g, 0.0).func_187315_a((double)((float)a.s / 32.0f), (double)((float)(v21.g + (int)v21.k) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            v22 = a;
            v17.func_181662_b((double)a.s, (double)a.u, 0.0).func_187315_a((double)((float)a.s / 32.0f), (double)((float)(v22.u + (int)v22.k) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            v23 = a;
            var12_17.func_181662_b((double)v16.f, (double)a.u, 0.0).func_187315_a((double)((float)a.f / 32.0f), (double)((float)(v23.u + (int)v23.k) / 32.0f)).func_181669_b(32, 32, 32, 255).func_181675_d();
            var11_15.func_78381_a();
        }
        a = v15.u + var10_13 - (int)a.k;
        if (a.q) {
            a.y(var8_11, a, var11_15);
        }
        v24 = var6_9 = 0;
        while (v24 < var4_7) {
            block26: {
                var7_10 = a + var6_9 * a.c + a.l;
                var12_18 = a.c - var10_13;
                if (var7_10 > a.g || var7_10 + var12_18 < a.u) break block26;
                if (a < a.f + 1) ** GOTO lbl-1000
                v25 = a;
                if (a <= v25.f + v25.m - 12 && a >= var7_10 - 1 && a <= var7_10 + a.c - 3) {
                    v26 = true;
                } else lbl-1000:
                // 2 sources

                {
                    v26 = false;
                }
                var13_22 = v26;
                a.r(var6_9, var8_11, var7_10, var12_18, var13_22, var11_15);
            }
            v24 = ++var6_9;
        }
        GlStateManager.func_179097_i();
        var6_9 = a.r() + var10_13 - var9_12;
        if (var6_9 > 0) {
            v27 = var9_12;
            var7_10 = v27 * v27 / a.r();
            if (var7_10 < 32) {
                var7_10 = 32;
            }
            if (var7_10 > var9_12 - var10_13 * 2) {
                var7_10 = var9_12 - var10_13 * 2;
            }
            if ((var12_19 = (int)a.k * (var9_12 - var7_10) / var6_9 + a.u) < a.u) {
                var12_19 = a.u;
            }
            a.r(var5_8, var12_19, var7_10);
        }
        a.r(a, a);
        GlStateManager.func_179098_w();
        GlStateManager.func_179103_j((int)7424);
        GlStateManager.func_179141_d();
        GlStateManager.func_179084_k();
        GL11.glDisable((int)3089);
    }

    private /* synthetic */ void y() {
        ge a2;
        ge ge2 = a2;
        int n2 = a2.r() - (ge2.g - ge2.u - 4);
        if (n2 < 0) {
            n2 /= 2;
        }
        if (a2.k > (float)n2) {
            a2.k = n2;
        }
        if (a2.k < 0.0f) {
            a2.k = 0.0f;
        }
    }

    public void r(int a2, int a3, int a4, int a5, int a6, int a7) {
        GuiUtils.drawGradientRect((int)0, (int)a2, (int)a3, (int)a4, (int)a5, (int)a6, (int)a7);
    }

    public void r() {
        ge a2;
        ge ge2 = a2;
        Gui.func_73734_a((int)(a2.f - 1), (int)(a2.u - 1), (int)(ge2.s + 1), (int)(a2.g + 1), (int)-13158601);
        ge ge3 = a2;
        Gui.func_73734_a((int)ge2.f, (int)ge3.u, (int)ge3.s, (int)a2.g, (int)-16777216);
    }

    @Deprecated
    public void h(int a2, int a3) {
    }

    public abstract void r(int var1, int var2, int var3, int var4, boolean var5, Tessellator var6);

    public void z(int a2, int a3) {
        ge a4;
        a4.h(a2, a3);
    }

    public void r(int a2, int a3, int a4) {
        ge a5;
        int n2 = a2;
        Gui.func_73734_a((int)n2, (int)a5.u, (int)(n2 + 10), (int)a5.g, (int)-1);
        Gui.func_73734_a((int)(a2 + 1), (int)(a5.u + 1), (int)(a2 + 9), (int)(a5.g - 1), (int)-13158601);
        Gui.func_73734_a((int)(a2 + 2), (int)(a5.u + 2), (int)(a2 + 9), (int)(a5.g - 2), (int)-7631989);
        int n3 = a3;
        Gui.func_73734_a((int)(a2 + 1), (int)n3, (int)(a2 + 10), (int)(n3 + a4), (int)-1);
        Gui.func_73734_a((int)(a2 + 2), (int)(a3 + 1), (int)(a2 + 10), (int)(a3 + a4), (int)-13158601);
        Gui.func_73734_a((int)(a2 + 2), (int)(a3 + 1), (int)(a2 + 9), (int)(a3 + a4 - 1), (int)-7631989);
        int n4 = a3;
        Gui.func_73734_a((int)(a2 + 10), (int)n4, (int)(a2 + 10), (int)(n4 + 1), (int)-7631989);
        Gui.func_73734_a((int)(a2 + 1), (int)(a3 + a4 - 1), (int)(a2 + 1), (int)(a3 + a4), (int)-7631989);
    }

    public void r(GuiButton a2) {
        if (a2.field_146124_l) {
            ge a3;
            if (a2.field_146127_k == a3.h) {
                ge ge2 = a3;
                ge2.k -= (float)(a3.c * 2 / 3);
                ge2.d = -2.0f;
                ge2.y();
                return;
            }
            if (a2.field_146127_k == a3.b) {
                ge ge3 = a3;
                ge3.k += (float)(a3.c * 2 / 3);
                ge3.d = -2.0f;
                ge3.y();
            }
        }
    }

    public abstract int y();

    @Deprecated
    public void r(boolean a2) {
        a.p = a2;
    }

    public void y(int a2, int a3, Tessellator a4) {
        ge a5;
        a5.r(a2, a3, a4);
    }

    public int r() {
        ge a2;
        return a2.y() * a2.c + a2.l;
    }

    public void y(boolean a2, int a3) {
        ge a4;
        ge ge2 = a4;
        ge2.q = a2;
        ge2.l = a3;
        if (!a2) {
            a4.l = 0;
        }
    }

    @Deprecated
    public void r(boolean a2, int a3) {
        ge a4;
        a4.y(a2, a3);
    }

    public abstract boolean r(int var1);

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public void y(int a2, int a3) throws IOException {
        ge a4;
        if (a2 < a4.f) return;
        ge ge2 = a4;
        if (a2 > ge2.f + ge2.m) return;
        if (a3 < a4.u) return;
        if (a3 > a4.g) return;
        boolean bl = true;
        boolean bl2 = bl;
        if (!bl2) {
            return;
        }
        a2 = Mouse.getEventDWheel();
        if (a2 == 0) return;
        a4.k += (float)(-1 * a2) / 120.0f * (float)a4.c / 2.0f;
    }

    public void r(List<GuiButton> a2, int a3, int a4) {
        ge a5;
        ge ge2 = a5;
        ge2.h = a3;
        ge2.b = a4;
    }

    public void r(int a2, int a3) {
        ge a4;
        a4.x(a2, a3);
    }

    @Deprecated
    public void r(int a2, int a3, Tessellator a4) {
    }
}

