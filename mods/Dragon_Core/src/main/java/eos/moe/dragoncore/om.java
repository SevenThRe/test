/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.ImmutableList
 *  com.google.common.collect.Sets
 *  io.netty.util.internal.ThreadLocalRandom
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.audio.ISound
 *  net.minecraft.client.audio.ISound$AttenuationType
 *  net.minecraft.client.audio.ISoundEventAccessor
 *  net.minecraft.client.audio.Sound$Type
 *  net.minecraft.client.audio.SoundEventAccessor
 *  net.minecraft.client.audio.SoundHandler
 *  net.minecraft.client.audio.SoundManager
 *  net.minecraft.client.renderer.texture.ITextureObject
 *  net.minecraft.client.renderer.texture.PngSizeInfo
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.SoundEvent
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  paulscode.sound.SoundSystem
 */
package eos.moe.dragoncore;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;
import eos.moe.dragoncore.ala;
import eos.moe.dragoncore.am;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.i;
import eos.moe.dragoncore.ie;
import eos.moe.dragoncore.lda;
import eos.moe.dragoncore.lr;
import eos.moe.dragoncore.pq;
import eos.moe.dragoncore.qg;
import eos.moe.dragoncore.qha;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ud;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.v;
import eos.moe.dragoncore.vn;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.ww;
import imcheck.e;
import imcheck.f;
import io.netty.util.internal.ThreadLocalRandom;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.ISoundEventAccessor;
import net.minecraft.client.audio.Sound;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.PngSizeInfo;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import paulscode.sound.SoundSystem;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class om {
    public static final ExecutorService q = ie.ALLATORIxDEMO;
    public static boolean b;
    public static boolean o;
    public static int y;
    private static final Executor k;
    private static final Set<String> ALLATORIxDEMO;

    public om() {
        om a2;
    }

    @i(f={"disableSneakSpeed"})
    public static void c(int a2) {
        y = a2;
    }

    @i(f={"disableSkillMob"})
    public static void f(boolean a2) {
        o = a2;
    }

    @i(f={"DisableHurtCameraEffect"})
    public static void c(boolean a2) {
        b = a2;
    }

    @i(f={"Platform"})
    public static int ALLATORIxDEMO() {
        return e.ALLATORIxDEMO;
    }

    @i(f={"IMCheckState"})
    public static void ALLATORIxDEMO(boolean a2) {
        f.ALLATORIxDEMO = a2;
    }

    @i(f={"Class_forName"})
    public static boolean c(String a2) {
        try {
            Class.forName(a2);
            return true;
        }
        catch (Exception a3) {
            return false;
        }
    }

    @i(f={"\u53d6\u503c\u7c7b\u578b", "Type_Get"})
    public static String ALLATORIxDEMO(v a2) {
        return a2.ALLATORIxDEMO();
    }

    @i(f={"\u53d6\u56fe\u7247\u5bbd\u9ad8"})
    public static v ALLATORIxDEMO(String a2) {
        ResourceLocation a3;
        TextureManager a4 = Minecraft.getMinecraft().getTextureManager();
        ITextureObject a5 = a4.getTexture(a3 = new ResourceLocation("dragoncore", a2));
        if (a5 != null && a5 instanceof ww) {
            ww a6 = (ww)a5;
            return new qg((Collection<?>)ImmutableList.of((Object)a6.q, (Object)a6.b), true);
        }
        IResourceManager a7 = Minecraft.getMinecraft().getResourceManager();
        try {
            IResource a8 = a7.getResource(a3);
            PngSizeInfo a9 = PngSizeInfo.makeFromResource((IResource)a8);
            return new qg((Collection<?>)ImmutableList.of((Object)a9.pngWidth, (Object)a9.pngHeight), true);
        }
        catch (Exception a10) {
            return new qg((Collection<?>)ImmutableList.of((Object)0, (Object)0), true);
        }
    }

    @i(f={"\u65b9\u6cd5\u5927\u5168"})
    public static qg ALLATORIxDEMO(ui a2, String a5, boolean a6) {
        lr a7 = (lr)a2.getMoLangRuntime().ALLATORIxDEMO().ALLATORIxDEMO().get("\u65b9\u6cd5");
        List a8 = a7.ALLATORIxDEMO().keySet().stream().filter(a4 -> a6 ? a4.startsWith(a5) : a4.contains(a5)).collect(Collectors.toList());
        a8.sort(Comparator.comparingInt(a3 -> a3.startsWith(a5) ? 0 : 1));
        return new qg(a8, true);
    }

    @i(f={"\u8bbe\u7f6e\u89d2\u89c6\u573a", "setFOV"})
    public static void ALLATORIxDEMO(double a2, int a3) {
        qha.e = (float)a2;
        qha.n = System.currentTimeMillis() + (long)a3;
    }

    @i(f={"\u5c4f\u5e55\u6296\u52a8", "screenShake"})
    public static void ALLATORIxDEMO(double a2, int a3, int a4, int a5) {
        qha.j = (float)a2;
        qha.i = a3;
        qha.l = a4;
        qha.z = a5;
        qha.s = System.currentTimeMillis();
    }

    @i(f={"\u5c4f\u5e55\u9707\u52a8", "PosScreenEarthquake"}, c=true)
    public static void ALLATORIxDEMO(double a2, double a3, double a4, float a5, float a6, int a7, int a8) {
        qha.ALLATORIxDEMO(new Vec3d(a2, a3, a4), a5, a6, a7, a8);
    }

    @i(f={"\u9501\u5b9a\u529f\u80fd", "lock"})
    public static void ALLATORIxDEMO(String a2, int a3) {
        for (String a4 : a2.toLowerCase(Locale.ROOT).split(",")) {
            lda.k.put(a4, System.currentTimeMillis() + (long)a3);
        }
    }

    @i(f={"\u62c9\u8fdc\u89c6\u89d2", "zoomout"})
    public static void ALLATORIxDEMO(v ... a2) {
        int a3 = 0;
        float a4 = -999.0f;
        if (a2.length == 5) {
            a4 = (float)a2[0].ALLATORIxDEMO();
            ++a3;
        }
        if (a2.length >= 4) {
            double a5 = a2[a3++].ALLATORIxDEMO();
            int a6 = (int)a2[a3++].ALLATORIxDEMO();
            int a7 = (int)a2[a3++].ALLATORIxDEMO();
            int a8 = (int)a2[a3++].ALLATORIxDEMO();
            if (qha.g == null) {
                qha.g = ala.ALLATORIxDEMO();
            }
            Entity a9 = Minecraft.getMinecraft().getRenderViewEntity();
            float a10 = Minecraft.getMinecraft().getRenderPartialTicks();
            qha.x = a9.prevRotationPitch + (a9.rotationPitch - a9.prevRotationPitch) * a10;
            qha.v = a4;
            qha.m = (float)a5;
            qha.q = a7;
            qha.c = a6;
            qha.b = a8;
            qha.t = System.currentTimeMillis();
            qha.r = System.currentTimeMillis() + (long)a6 + (long)a7 + (long)a8;
        }
    }

    @i(f={"\u503c\u7c7b\u578b\u662f\u5426\u76f8\u540c", "Type_Equal"})
    public static boolean ALLATORIxDEMO(v a2, String a3) {
        Object a4 = a2.ALLATORIxDEMO();
        if (((String)a4).equals("string") && a3.equals("double")) {
            String a5 = a2.c();
            if (a5.equals("true") || a5.equals("false")) {
                a4 = "double";
            } else {
                try {
                    Double.parseDouble(a5);
                    a4 = "double";
                }
                catch (NumberFormatException numberFormatException) {
                    // empty catch block
                }
            }
        }
        return ((String)a4).equals(a3);
    }

    @i(f={"\u64ad\u653e\u58f0\u97f3", "\u58f0\u97f3", "\u64ad\u653e\u97f3\u4e50", "Sound_Play"})
    public static String ALLATORIxDEMO(ui a2, String a3, float a4, float a5, float a6, float a7, float a8, boolean a9) {
        return om.ALLATORIxDEMO(a2, MathHelper.getRandomUUID((Random)ThreadLocalRandom.current()).toString(), a3, SoundCategory.MASTER, a4, a5, a6, a7, a8, a9);
    }

    @i(f={"\u64ad\u653e\u58f0\u97f31", "\u58f0\u97f31", "\u64ad\u653e\u97f3\u4e501", "Sound_Play1"})
    public static String ALLATORIxDEMO(ui a2, String a3, String a4, float a5, float a6, float a7, float a8, float a9, boolean a10) {
        return om.ALLATORIxDEMO(a2, a3, a4, SoundCategory.MASTER, a5, a6, a7, a8, a9, a10);
    }

    @i(f={"\u64ad\u653e\u58f0\u97f32", "\u58f0\u97f32", "\u64ad\u653e\u97f3\u4e502", "Sound_Play2"})
    public static String ALLATORIxDEMO(ui a2, String a3, String a4, String a5, float a6, float a7, float a8, float a9, float a10, boolean a11) {
        SoundCategory a12 = SoundCategory.getByName((String)a5);
        return om.ALLATORIxDEMO(a2, a3, a4, a12, a6, a7, a8, a9, a10, a11);
    }

    public static String ALLATORIxDEMO(ui a2, String a3, String a4, SoundCategory a5, float a6, float a7, float a8, float a9, float a10, boolean a11) {
        if (a5 == null) {
            a5 = SoundCategory.MASTER;
        }
        a4 = a4.isEmpty() ? "ui.button.click" : a4;
        a6 = a6 == 0.0f ? 1.0f : a6;
        float f2 = a7 = a7 == 0.0f ? 1.0f : a7;
        if (a4.endsWith(".ogg")) {
            String a12 = a4;
            SoundCategory a13 = a5;
            float a14 = a6;
            float a15 = a7;
            sj.ALLATORIxDEMO(() -> om.c(a3, a12, a13, a14, a15, a8, a9, a10, a11));
            if (a2 != null) {
                a2.n.add(a3);
            }
            return a3;
        }
        SoundEvent a16 = (SoundEvent)SoundEvent.REGISTRY.getObject((Object)new ResourceLocation(a4));
        if (a16 != null && Minecraft.getMinecraft().player != null) {
            float a17 = a6;
            float a18 = a7;
            sj.ALLATORIxDEMO(() -> Minecraft.getMinecraft().player.playSound(a16, a17, a18));
        }
        return a3;
    }

    @i(f={"\u505c\u6b62\u97f3\u4e50", "Sound_Stop"})
    public static void f(String a2) {
        om.c(a2);
    }

    @i(f={"\u505c\u6b62\u5168\u90e8\u97f3\u4e50", "Sound_StopAll"})
    public static void ALLATORIxDEMO(ui a2) {
        if (a2 == null) {
            return;
        }
        for (String a3 : a2.n) {
            om.c(a3);
        }
    }

    @i(f={"debug"})
    public static void ALLATORIxDEMO(ui a2, boolean a3) {
        a2.ua = a3;
    }

    public static void c(String a2) {
        try {
            SoundHandler a3 = Minecraft.getMinecraft().getSoundHandler();
            SoundManager a4 = (SoundManager)ReflectionHelper.getPrivateValue(SoundHandler.class, (Object)a3, (String[])new String[]{"sndManager", "sndManager"});
            SoundSystem a5 = (SoundSystem)ReflectionHelper.getPrivateValue(SoundManager.class, (Object)a4, (String[])new String[]{"sndSystem", "sndSystem"});
            a5.stop(a2);
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public static void c(String a2, String a3, SoundCategory a4, float a5, float a6, float a7, float a8, float a9, boolean a10) {
        if (!wka.k) {
            return;
        }
        SoundHandler a13 = Minecraft.getMinecraft().getSoundHandler();
        ResourceLocation a14 = new ResourceLocation("dragoncore", a3);
        try {
            Minecraft.getMinecraft().getResourceManager().getResource(a14);
        }
        catch (Exception a15) {
            if (a3.startsWith("http:") || a3.startsWith("https:")) {
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return ud.ALLATORIxDEMO(new URI(a3));
                    }
                    catch (Throwable a3) {
                        throw new CompletionException(a3);
                    }
                }).whenCompleteAsync((a11, a12) -> {
                    if (a12 != null) {
                        a12.printStackTrace();
                    } else {
                        pq.ALLATORIxDEMO.put(a3, (byte[])a11);
                        om.c(a2, a3, a4, a5, a6, a7, a8, a9, a10);
                    }
                }, k);
                return;
            }
            a14 = new ResourceLocation("dragoncore", "sounds/" + a3);
            try {
                Minecraft.getMinecraft().getResourceManager().getResource(a14);
            }
            catch (Exception a16) {
                ca.l.w("\u7f3a\u5c11\u97f3\u4e50\u6587\u4ef6,\u65e0\u6cd5\u64ad\u653e->" + a3);
                return;
            }
        }
        ISound.AttenuationType a17 = a7 == 0.0f && a8 == 0.0f && a9 == 0.0f ? ISound.AttenuationType.NONE : ISound.AttenuationType.LINEAR;
        am a18 = new am(null, a14, a4, a5, a6, a10, 0, a17, a7, a8, a9);
        SoundEventAccessor a19 = new SoundEventAccessor(a14, null);
        a19.addSound((ISoundEventAccessor)new vn(a14, 1.0f, 1.0f, 1, Sound.Type.FILE, a4 == SoundCategory.MUSIC));
        a13.soundRegistry.add(a19);
        a18.setKey(a2);
        Minecraft.getMinecraft().getSoundHandler().playSound((ISound)a18);
    }

    @i(f={"\u5ef6\u65f6", "Delay"})
    public static void ALLATORIxDEMO(int a2) {
        if (Minecraft.getMinecraft().isCallingFromMinecraftThread()) {
            return;
        }
        if (a2 <= 0) {
            a2 = 1;
        }
        try {
            Thread.sleep(a2);
        }
        catch (InterruptedException interruptedException) {
            // empty catch block
        }
    }

    @i(f={"randomUUID"})
    public static String ALLATORIxDEMO() {
        return UUID.randomUUID().toString();
    }

    @i(f={"isUUID"})
    public static boolean ALLATORIxDEMO(String a2) {
        try {
            UUID.fromString(a2);
            return true;
        }
        catch (Exception a3) {
            return false;
        }
    }

    @i(f={"\u6253\u5f00\u7f51\u9875", "Url"})
    public static void ALLATORIxDEMO(String a2) {
        try {
            URI a3 = new URI(a2);
            String a4 = a3.getScheme();
            if (a4 == null) {
                throw new URISyntaxException(a2, "Missing protocol");
            }
            if (!ALLATORIxDEMO.contains(a4.toLowerCase(Locale.ROOT))) {
                throw new URISyntaxException(a2, "Unsupported protocol: " + a4.toLowerCase(Locale.ROOT));
            }
            om.ALLATORIxDEMO(a3);
        }
        catch (URISyntaxException a5) {
            ca.l.x("Can't open url for {}", a2);
        }
    }

    private static /* synthetic */ void ALLATORIxDEMO(URI a2) {
        try {
            Class<?> a3 = Class.forName("java.awt.Desktop");
            Object a4 = a3.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            a3.getMethod("browse", URI.class).invoke(a4, a2);
        }
        catch (Throwable a5) {
            Throwable a6 = a5.getCause();
            ca.l.x("Couldn't open link: {}", a6 == null ? "<UNKNOWN>" : a6.getMessage());
        }
    }

    static {
        k = a2 -> Minecraft.getMinecraft().addScheduledTask(a2);
        ALLATORIxDEMO = Sets.newHashSet((Object[])new String[]{"http", "https"});
    }
}

