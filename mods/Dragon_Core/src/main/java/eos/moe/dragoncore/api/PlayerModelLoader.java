/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.text.ITextComponent
 *  net.minecraft.util.text.TextComponentString
 */
package eos.moe.dragoncore.api;

import eos.moe.dragoncore.api.model.IModel;
import eos.moe.dragoncore.eq;
import eos.moe.dragoncore.fq;
import eos.moe.dragoncore.ht;
import eos.moe.dragoncore.jv;
import eos.moe.dragoncore.kq;
import eos.moe.dragoncore.ms;
import eos.moe.dragoncore.qw;
import eos.moe.dragoncore.rz;
import eos.moe.dragoncore.st;
import eos.moe.dragoncore.xz;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class PlayerModelLoader {
    public static final Map<String, xz> animations = new HashMap<String, xz>();
    public static final HashMap<String, ModelBase> models = new HashMap();
    private static final ExecutorService EXECUTOR = Executors.newCachedThreadPool();

    public PlayerModelLoader() {
        PlayerModelLoader a2;
    }

    public static void clear() {
        animations.clear();
        models.clear();
    }

    public static void shutdown() {
        EXECUTOR.shutdownNow();
    }

    public static IModel getModel(String a2, byte[] a3) {
        if (!models.containsKey(a2)) {
            EXECUTOR.submit(() -> {
                try {
                    jv a4 = ms.ALLATORIxDEMO(a2, a3);
                    models.put(a2, a4);
                }
                catch (Exception a5) {
                    a5.printStackTrace();
                    Minecraft.func_71410_x().field_71439_g.func_145747_a((ITextComponent)new TextComponentString("\u00a7c\u65e0\u6cd5\u52a0\u8f7d\u73a9\u5bb6\u65f6\u88c5\u6a21\u578b:" + a2));
                }
            });
            models.put(a2, ms.ALLATORIxDEMO());
        }
        return (IModel)models.get(a2);
    }

    public static void clearData(String a2) {
        if (models.containsKey(a2)) {
            ((jv)models.get(a2)).clearData();
        }
    }

    public static void bindTexture(String a2, byte[] a3) {
        ResourceLocation a4;
        TextureManager a5 = Minecraft.func_71410_x().func_110434_K();
        Object a6 = a5.func_110581_b(a4 = new ResourceLocation("dragoncore", "models/player/" + a2 + "/texture.png"));
        if (a6 == null) {
            a6 = new eq(a3);
            a5.func_110579_a(a4, a6);
        }
        a5.func_110577_a(a4);
    }

    public static void bindGlowTexture(String a2, byte[] a3) {
        ResourceLocation a4;
        TextureManager a5 = Minecraft.func_71410_x().func_110434_K();
        Object a6 = a5.func_110581_b(a4 = new ResourceLocation("dragoncore", "models/player/" + a2 + "/glow_texture.png"));
        if (a6 == null) {
            a6 = new eq(a3);
            a5.func_110579_a(a4, a6);
        }
        a5.func_110577_a(a4);
    }

    public static boolean hasGlowTexture(byte[] a2) {
        return a2 != null && a2.length > 0;
    }

    public static void applyAnimation(UUID a2, String a3, byte[] a4, String a5, boolean a6) {
        ModelBase a7 = models.get(a3);
        if (a7 instanceof jv && a4 != null && a4.length > 0) {
            Object a82;
            Object a9;
            if (!animations.containsKey(a2.toString() + "_" + a3)) {
                a9 = fq.c();
                try {
                    a82 = ht.ALLATORIxDEMO("models/player/" + a3 + "/animation.json", a4);
                    ((fq)a9).ALLATORIxDEMO((Map<String, kq>)a82);
                    a82.forEach((arg_0, arg_1) -> PlayerModelLoader.lambda$applyAnimation$1((fq)a9, arg_0, arg_1));
                }
                catch (Exception a82) {
                    // empty catch block
                }
                animations.put(a2 + "_" + a3, ((fq)a9).ALLATORIxDEMO());
            }
            a9 = animations.get(a2 + "_" + a3);
            if ("idle".equals(a5)) {
                if (((qw)a9).ALLATORIxDEMO() != null && !((qw)a9).isOnPlayAnimation()) {
                    a82 = ((qw)a9).ALLATORIxDEMO().ALLATORIxDEMO();
                    ((qw)a9).ALLATORIxDEMO((st)a82, ((qw)a9).ALLATORIxDEMO().ALLATORIxDEMO());
                }
            } else {
                a82 = ((xz)a9).x().get(a5);
                if (a82 != null) {
                    ((qw)a9).ALLATORIxDEMO(null, 0);
                    ((qw)a9).ALLATORIxDEMO(new st((kq)a82), a5);
                }
            }
            if (a6) {
                ((qw)a9).ALLATORIxDEMO((jv)a7);
            }
        }
    }

    private static /* synthetic */ void lambda$applyAnimation$1(fq a2, String a3, kq a4) {
        if (a3.contains("idle")) {
            a2.ALLATORIxDEMO(a3, rz.k, 1.0f);
            a2.ALLATORIxDEMO(new st(a4).ALLATORIxDEMO(1.0f), a3);
        } else {
            a2.ALLATORIxDEMO(a3, rz.y, 1.0f);
        }
    }
}

