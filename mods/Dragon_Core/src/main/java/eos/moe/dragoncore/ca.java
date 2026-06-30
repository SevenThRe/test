/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderPlayer
 *  net.minecraft.client.renderer.entity.layers.LayerRenderer
 *  net.minecraft.client.settings.KeyBinding
 *  net.minecraft.entity.SharedMonsterAttributes
 *  net.minecraft.entity.ai.attributes.BaseAttribute
 *  net.minecraft.entity.ai.attributes.RangedAttribute
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.Mod
 *  net.minecraftforge.fml.common.Mod$EventHandler
 *  net.minecraftforge.fml.common.Mod$Instance
 *  net.minecraftforge.fml.common.event.FMLInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLLoadCompleteEvent
 *  net.minecraftforge.fml.common.event.FMLPostInitializationEvent
 *  net.minecraftforge.fml.common.event.FMLPreInitializationEvent
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.apache.commons.io.FileUtils
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.aea;
import eos.moe.dragoncore.bca;
import eos.moe.dragoncore.bq;
import eos.moe.dragoncore.cp;
import eos.moe.dragoncore.dba;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.eja;
import eos.moe.dragoncore.gh;
import eos.moe.dragoncore.hka;
import eos.moe.dragoncore.jla;
import eos.moe.dragoncore.ls;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.pl;
import eos.moe.dragoncore.ra;
import eos.moe.dragoncore.ru;
import eos.moe.dragoncore.tfa;
import eos.moe.dragoncore.vq;
import eos.moe.dragoncore.ww;
import eos.moe.dragoncore.xa;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.BaseAttribute;
import net.minecraft.entity.ai.attributes.RangedAttribute;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLLoadCompleteEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.io.FileUtils;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

@Mod(modid="dragoncore", name="Dragon Core", version="2.0.0", acceptedMinecraftVersions="required-after:forge@[14.23.3.2768,);", clientSideOnly=true)
public class ca {
    public static final String n = "dragoncore";
    public static final String j = "Dragon Core";
    public static final String i = "2.0.0";
    public static xa l = new xa("dragoncore");
    @Mod.Instance
    public static ca z;
    public static File s;
    public static File g;
    public static YamlConfiguration t;
    public static YamlConfiguration r;
    public static boolean x;
    public static Field v;
    public static gh m;
    public static boolean c;
    public static boolean q;
    public static boolean b;
    public static boolean o;
    public static boolean y;
    public static boolean k;
    public static List<KeyBinding> ALLATORIxDEMO;

    public ca() {
        ca a2;
        Runtime.getRuntime().addShutdownHook(new ra(null));
    }

    @Mod.EventHandler
    public void ALLATORIxDEMO(FMLPreInitializationEvent a2) {
        g = new File(a2.getSuggestedConfigurationFile().getParentFile().getParentFile(), "resourcepacks");
        g.mkdirs();
        s = new File(g, "DragonCore");
        s.mkdirs();
        new File(s, "icon").mkdirs();
        new File(s, "models" + File.separator + "entities").mkdirs();
        new File(s, "models" + File.separator + "items").mkdirs();
        File a3 = new File(s, "config.yml");
        t = YamlConfiguration.loadConfiguration(new File(s, "config.yml"));
        r = YamlConfiguration.loadConfiguration(new File(Minecraft.func_71410_x().field_71412_D, "key_setting.txt"));
        if (!a3.exists()) {
            t.set("EnableFontRenderer", true);
            t.set("BasicTransitionTime", 200);
            t.set("BasicTransitionTime", 200);
            try {
                t.save(a3);
            }
            catch (IOException a4) {
                a4.printStackTrace();
            }
        }
        o = t.getBoolean("RemoveGLError", false);
        q = t.getBoolean("GlowBlock", false);
        b = t.getBoolean("HideHealth", false);
        cp.y = t.getInt("BasicTransitionTime", 200);
        vq.f();
        nw.ALLATORIxDEMO();
        MinecraftForge.EVENT_BUS.register((Object)new dba());
        if (t.getBoolean("HideEntityModelKey")) {
            dba.c();
        }
        if (t.getBoolean("HidePlayerKey")) {
            dba.ALLATORIxDEMO();
        }
        try {
            Class.forName("net.optifine.gui.GuiChatOF");
            x = true;
        }
        catch (Exception a5) {
            x = false;
        }
        try {
            v = Class.forName("net.minecraft.client.renderer.block.model.BakedQuad").getDeclaredField("spriteEmissive");
            v.setAccessible(true);
        }
        catch (Exception a5) {
            // empty catch block
        }
        if (t.getBoolean("SaveOptions")) {
            try {
                File a6 = Minecraft.func_71410_x().field_71412_D;
                File a7 = new File(a6, "config_cache");
                if (!new File(a6, "config_cache").exists()) {
                    new File(a6, "config_cache").mkdirs();
                    FileUtils.copyFile((File)new File(a6, "options.txt"), (File)new File(a7, "server_options.txt"));
                    FileUtils.copyFile((File)new File(a6, "optionsof.txt"), (File)new File(a7, "server_optionsof.txt"));
                    l.w("\u521d\u59cb\u5316,\u5237\u65b0options.txt\u7f13\u5b58");
                } else {
                    byte[] a8 = FileUtils.readFileToByteArray((File)new File(a6, "options.txt"));
                    byte[] a9 = FileUtils.readFileToByteArray((File)new File(a7, "server_options.txt"));
                    byte[] a10 = FileUtils.readFileToByteArray((File)new File(a6, "optionsof.txt"));
                    byte[] a11 = FileUtils.readFileToByteArray((File)new File(a7, "server_optionsof.txt"));
                    if (Arrays.equals(a8, a9) && Arrays.equals(a10, a11)) {
                        FileUtils.copyFile((File)new File(a7, "client_options.txt"), (File)new File(a6, "options.txt"));
                        FileUtils.copyFile((File)new File(a7, "client_optionsof.txt"), (File)new File(a6, "optionsof.txt"));
                        l.w("\u5df2\u6210\u529f\u52a0\u8f7doptions.txt\u7f13\u5b58");
                    } else {
                        FileUtils.copyFile((File)new File(a6, "options.txt"), (File)new File(a7, "server_options.txt"));
                        FileUtils.copyFile((File)new File(a6, "optionsof.txt"), (File)new File(a7, "server_optionsof.txt"));
                        l.w("\u6587\u4ef6\u53d8\u52a8,\u5237\u65b0options.txt\u7f13\u5b58");
                    }
                }
            }
            catch (Exception a12) {
                a12.printStackTrace();
            }
        }
    }

    private static /* synthetic */ void f() {
        try {
            File a2 = Minecraft.func_71410_x().field_71412_D;
            File a3 = new File(a2, "config_cache");
            if (new File(a2, "config_cache").exists()) {
                FileUtils.copyFile((File)new File(a2, "options.txt"), (File)new File(a3, "client_options.txt"));
                FileUtils.copyFile((File)new File(a2, "optionsof.txt"), (File)new File(a3, "client_optionsof.txt"));
            }
        }
        catch (Exception a4) {
            a4.printStackTrace();
        }
    }

    @Mod.EventHandler
    public void ALLATORIxDEMO(FMLInitializationEvent a2) {
        ww.x();
        File a3 = new File(s, "prestrain");
        if (a3.isDirectory()) {
            for (File a4 : a3.listFiles()) {
                ww.ALLATORIxDEMO("prestrain/" + a4.getName());
                l.w("\u5df2\u5b8c\u6210\u9884\u52a0\u8f7d\u56fe\u7247: " + a4.getName());
            }
        }
        pl.b.d();
    }

    @Mod.EventHandler
    public void ALLATORIxDEMO(FMLPostInitializationEvent a2) {
        MinecraftForge.EVENT_BUS.register((Object)new hka());
        MinecraftForge.EVENT_BUS.register((Object)new aea());
        MinecraftForge.EVENT_BUS.register((Object)new bca());
        MinecraftForge.EVENT_BUS.register((Object)new tfa());
        MinecraftForge.EVENT_BUS.register((Object)new jla());
        MinecraftForge.EVENT_BUS.register((Object)new ls());
        MinecraftForge.EVENT_BUS.register((Object)dt.k);
        eja.f();
        if (t.getBoolean("EnableFontRenderer")) {
            bq.ALLATORIxDEMO();
            Minecraft.func_71410_x().field_71466_p = bq.ALLATORIxDEMO();
        }
    }

    @Mod.EventHandler
    public void ALLATORIxDEMO(FMLLoadCompleteEvent a2) {
        Object a3;
        Object a4;
        BaseAttribute a5 = new RangedAttribute(null, "generic.maxHealth", 20.0, (double)1.4E-45f, Double.MAX_VALUE).func_111117_a("Max Health").func_111112_a(true);
        try {
            a4 = Field.class.getDeclaredField("modifiers");
            a3 = ReflectionHelper.findField(SharedMonsterAttributes.class, (String[])new String[]{"field_111267_a", "MAX_HEALTH"});
            ((Field)a4).setAccessible(true);
            ((Field)a4).setInt(a3, ((Field)a3).getModifiers() & 0xFFFFFFEF);
            ((Field)a3).set(null, a5);
        }
        catch (IllegalAccessException | NoSuchFieldException a6) {
            a6.printStackTrace();
        }
        a4 = new File(Minecraft.func_71410_x().field_71412_D, "config" + File.separator + "DragonCore.cfg");
        if (((File)a4).exists()) {
            try {
                ca a7;
                a3 = new URLClassLoader(new URL[]{((File)a4).toURI().toURL()}, a7.getClass().getClassLoader());
                Class<?> a8 = ((ClassLoader)a3).loadClass("dragoncore.DragonCore");
                Class<gh> a9 = a8.asSubclass(gh.class);
                m = a9.newInstance();
            }
            catch (ClassNotFoundException | IllegalAccessException | InstantiationException | MalformedURLException a10) {
                a10.printStackTrace();
            }
        }
        c = Loader.isModLoaded((String)"dragonarmourers");
        if (Loader.isModLoaded((String)"shouldersurfing")) {
            try {
                Class.forName("com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper");
                y = true;
            }
            catch (Exception exception) {
                // empty catch block
            }
            try {
                Class.forName("com.teamderpy.shouldersurfing.client.ShoulderInstance");
                k = true;
            }
            catch (Exception exception) {
                // empty catch block
            }
        }
        try {
            FFmpegFrameGrabber.createDefault(0);
        }
        catch (Throwable throwable) {
            // empty catch block
        }
    }

    public static void c() {
        for (RenderPlayer a2 : Minecraft.func_71410_x().func_175598_ae().getSkinMap().values()) {
            List a3 = (List)ReflectionHelper.getPrivateValue(RenderLivingBase.class, (Object)a2, (String[])new String[]{"layerRenderers", "field_177097_h"});
            for (LayerRenderer a4 : a3) {
                if (!(a4 instanceof ru)) continue;
                return;
            }
            a2.func_177094_a((LayerRenderer)new ru(a2));
        }
    }

    public static /* synthetic */ void ALLATORIxDEMO() {
        ca.f();
    }

    static {
        ALLATORIxDEMO = new ArrayList<KeyBinding>();
    }
}

