/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.texture.PngSizeInfo
 *  net.minecraft.client.renderer.texture.TextureAtlasSprite
 *  net.minecraft.client.renderer.texture.TextureUtil
 */
package eos.moe.dragoncore;

import java.io.IOException;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureUtil;

public class jt
extends TextureAtlasSprite {
    public boolean k;
    public jt ALLATORIxDEMO;

    public jt(String a2) {
        super(a2);
        jt a3;
    }

    public void func_188538_a(PngSizeInfo a2, boolean a3) throws IOException {
        jt a4;
        a4.func_130102_n();
        a4.field_130223_c = a2.field_188533_a;
        a4.field_130224_d = a2.field_188534_b;
        if (a3) {
            a4.field_130224_d = a4.field_130223_c;
        } else if (a2.field_188534_b != a2.field_188533_a) {
            // empty if block
        }
    }

    public int getTickCounter() {
        jt a2;
        return a2.field_110983_h;
    }

    public int getFrameCounter() {
        jt a2;
        return a2.field_110973_g;
    }

    public void updateAnimation(int a2, int a3) {
        jt a4;
        a4.field_110983_h = a2;
        a4.field_110973_g = a3;
        if (a4.field_110983_h >= a4.field_110982_k.func_110472_a(a4.field_110973_g)) {
            int a5 = a4.field_110982_k.func_110468_c(a4.field_110973_g);
            int a6 = a4.field_110982_k.func_110473_c() == 0 ? a4.field_110976_a.size() : a4.field_110982_k.func_110473_c();
            a4.field_110973_g = (a4.field_110973_g + 1) % a6;
            int a7 = a4.field_110982_k.func_110468_c(a4.field_110973_g);
            if (a5 != a7 && a7 >= 0 && a7 < a4.field_110976_a.size()) {
                TextureUtil.func_147955_a((int[][])((int[][])a4.field_110976_a.get(a7)), (int)a4.field_130223_c, (int)a4.field_130224_d, (int)a4.field_110975_c, (int)a4.field_110974_d, (boolean)false, (boolean)false);
            }
        } else if (a4.field_110982_k.func_177219_e()) {
            a4.func_180599_n();
        }
    }

    public void func_94219_l() {
        jt a2;
        ++a2.field_110983_h;
        if (a2.field_110983_h >= a2.field_110982_k.func_110472_a(a2.field_110973_g)) {
            int a3 = a2.field_110982_k.func_110468_c(a2.field_110973_g);
            int a4 = a2.field_110982_k.func_110473_c() == 0 ? a2.field_110976_a.size() : a2.field_110982_k.func_110473_c();
            a2.field_110973_g = (a2.field_110973_g + 1) % a4;
            a2.field_110983_h = 0;
            int a5 = a2.field_110982_k.func_110468_c(a2.field_110973_g);
            if (a3 != a5 && a5 >= 0 && a5 < a2.field_110976_a.size()) {
                TextureUtil.func_147955_a((int[][])((int[][])a2.field_110976_a.get(a5)), (int)a2.field_130223_c, (int)a2.field_130224_d, (int)a2.field_110975_c, (int)a2.field_110974_d, (boolean)false, (boolean)false);
            }
        } else if (a2.field_110982_k.func_177219_e()) {
            a2.func_180599_n();
        }
    }
}

