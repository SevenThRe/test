/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.texture.AbstractTexture
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.di;
import eos.moe.dragoncore.gs;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.qx;
import eos.moe.dragoncore.ww;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class jy {
    public jy() {
        jy a2;
    }

    @i(f={"\u5220\u9664\u8d34\u56fe\u7f13\u5b58"}, c=true)
    public static void c(String a2) {
        ResourceLocation a3 = new ResourceLocation("dragoncore", a2);
        Map a4 = (Map)ReflectionHelper.getPrivateValue(TextureManager.class, (Object)Minecraft.getMinecraft().getTextureManager(), (String[])new String[]{"mapTextureObjects", "mapTextureObjects"});
        ITextureObject a5 = (ITextureObject)a4.get(a3);
        if (a5 instanceof AbstractTexture) {
            ((AbstractTexture)a5).deleteGlTexture();
            a4.remove(a3);
        }
    }

    @i(f={"\u53d6GIF\u65f6\u95f4"})
    public static int ALLATORIxDEMO(String a2) {
        if (!jy.f(a2)) {
            return 0;
        }
        int a3 = 0;
        ww a4 = ww.ALLATORIxDEMO(a2);
        for (qx a5 : a4.getImageList()) {
            a3 += a5.c();
        }
        return a3;
    }

    @i(f={"\u8bbe\u7f6eGIF\u5e8f\u53f7"})
    public static void ALLATORIxDEMO(String a2, int a3) {
        if (!jy.f(a2)) {
            return;
        }
        if (ww.ALLATORIxDEMO.containsKey(a2)) {
            gs a4 = ww.ALLATORIxDEMO.get(a2);
            a4.o = System.currentTimeMillis();
            return;
        }
        ww a5 = ww.ALLATORIxDEMO(a2);
        if (a3 >= a5.getImageList().size()) {
            a3 = 0;
        }
        a5.setImageIndex(a3);
        qx a6 = a5.getImageList().get(a3);
        a5.setNextTime(System.currentTimeMillis() + (long)a6.c());
    }

    @i(f={"\u8bbe\u7f6eGIF\u662f\u5426\u64ad\u653e"})
    public static void ALLATORIxDEMO(String a2, boolean a3) {
        if (!jy.f(a2)) {
            return;
        }
        ww a4 = ww.ALLATORIxDEMO(a2);
        a4.setPlay(a3);
    }

    @i(f={"\u91cd\u7f6e\u89c6\u9891"})
    public static void ALLATORIxDEMO(String a2) {
        di.y.ALLATORIxDEMO(a2);
    }

    private static /* synthetic */ boolean f(String a2) {
        return a2.endsWith(".gif") && !jy.c(a2);
    }

    private static /* synthetic */ boolean c(String a2) {
        ww a3 = ww.ALLATORIxDEMO(a2);
        return a3 == ww.k;
    }
}

