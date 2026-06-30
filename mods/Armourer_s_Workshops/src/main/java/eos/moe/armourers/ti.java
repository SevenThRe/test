/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.TextureUtil
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 */
package eos.moe.armourers;

import eos.moe.armourers.gl;
import eos.moe.armourers.lo;
import eos.moe.armourers.nl;
import eos.moe.armourers.te;
import eos.moe.armourers.yi;
import eos.moe.armourers.yl;
import eos.moe.armourers.ym;
import java.awt.image.BufferedImage;
import java.io.IOException;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(value=Side.CLIENT)
public class ti
extends AbstractTexture {
    private BufferedImage j;

    public void createTextureForColours(yl a2, yi a3) {
        int n2;
        int n3 = n2 = 0;
        while (n3 < nl.v) {
            int n4;
            int n5 = n4 = 0;
            while (n5 < nl.m) {
                ti a4;
                int n6 = a2.r()[n2 + n4 * nl.v];
                ym ym2 = te.y(n6);
                if (a3 != null) {
                    if (ym2 == te.c) {
                        a4.j.setRGB(n2, n4, lo.r(n6, 0, 255));
                    } else if (ym2.r() != null) {
                        ti ti2 = a4;
                        int n7 = ti2.r(a3.r().r(ym2.r()), n6, ym2.r(), a2, a3);
                        ti2.j.setRGB(n2, n4, lo.r(n7, 0, 255));
                    } else {
                        ym ym3;
                        boolean bl;
                        if (ym2.z() >= 1) {
                            bl = true;
                            ym3 = ym2;
                        } else {
                            bl = false;
                            ym3 = ym2;
                        }
                        if (bl & ym3.z() <= 8) {
                            int n8;
                            Object object = a3.r();
                            if (object.r(n8 = ym2.z() - 1)) {
                                byte[] byArray = object.r(n8);
                                object = byArray;
                                if (te.r(byArray[3]) != te.f) {
                                    ti ti3 = a4;
                                    int n9 = ti3.r((byte[])object, n6, ym2.r(), a2, a3);
                                    ti3.j.setRGB(n2, n4, n9);
                                }
                            } else {
                                a4.j.setRGB(n2, n4, lo.r(n6, 0, 255));
                            }
                        }
                    }
                } else {
                    ym ym4;
                    boolean bl;
                    if (ym2 == te.c) {
                        a4.j.setRGB(n2, n4, lo.r(n6, 0, 255));
                    }
                    if (ym2.z() >= 1) {
                        bl = true;
                        ym4 = ym2;
                    } else {
                        bl = false;
                        ym4 = ym2;
                    }
                    if (bl & ym4.z() <= 8) {
                        a4.j.setRGB(n2, n4, lo.r(n6, 0, 255));
                    }
                }
                n5 = ++n4;
            }
            n3 = ++n2;
        }
    }

    public void func_110551_a(IResourceManager a2) throws IOException {
    }

    public void bindTexture() {
        ti a2;
        if (a2.field_110553_a == -1) {
            a2.func_110552_b();
            ti ti2 = a2;
            TextureUtil.func_110987_a((int)ti2.field_110553_a, (BufferedImage)ti2.j);
        }
        GlStateManager.func_179144_i((int)a2.field_110553_a);
    }

    private /* synthetic */ int r(byte[] a2, int a3, int a4, yl a5, yi a62) {
        Object object;
        byte a62 = (byte)(a3 >>> 16 & 0xFF);
        byte by = (byte)(a3 >>> 8 & 0xFF);
        a3 = (byte)(a3 & 0xFF);
        if (a2.length <= 3 || ((ym)(object = te.r(a2[3]))).r() != null) {
            // empty if block
        }
        int[] nArray = new int[3];
        nArray[0] = 127;
        nArray[1] = 127;
        nArray[2] = 127;
        object = nArray;
        if (a5 != null) {
            object = a5.r(a4);
        }
        a2 = gl.r(a62, by, (byte)a3, a2, (int[])object);
        return -16777216 + ((a2[0] & 0xFF) << 16) + ((a2[1] & 0xFF) << 8) + (a2[2] & 0xFF);
    }

    public ti() {
        ti a2;
        ti ti2 = a2;
        ti2.j = new BufferedImage(nl.v, nl.m, 2);
    }
}

