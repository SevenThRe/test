/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.Lists
 *  com.google.gson.Gson
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiTextField
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.SoundCategory
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.apache.commons.io.FileUtils
 *  org.apache.commons.io.filefilter.IOFileFilter
 *  org.apache.commons.io.filefilter.TrueFileFilter
 *  org.lwjgl.opengl.Display
 */
package eos.moe.dragoncore;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.EntityList;
import eos.moe.dragoncore.ModelLocator;
import eos.moe.dragoncore.acf;
import eos.moe.dragoncore.ag;
import eos.moe.dragoncore.aka;
import eos.moe.dragoncore.baa;
import eos.moe.dragoncore.bax;
import eos.moe.dragoncore.bf;
import eos.moe.dragoncore.ca;
import eos.moe.dragoncore.cy;
import eos.moe.dragoncore.de;
import eos.moe.dragoncore.dga;
import eos.moe.dragoncore.dia;
import eos.moe.dragoncore.dla;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.eba;
import eos.moe.dragoncore.efa;
import eos.moe.dragoncore.ega;
import eos.moe.dragoncore.event.EventLoader;
import eos.moe.dragoncore.faa;
import eos.moe.dragoncore.fia;
import eos.moe.dragoncore.fla;
import eos.moe.dragoncore.gv;
import eos.moe.dragoncore.h;
import eos.moe.dragoncore.hja;
import eos.moe.dragoncore.hv;
import eos.moe.dragoncore.ie;
import eos.moe.dragoncore.jga;
import eos.moe.dragoncore.jia;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.jla;
import eos.moe.dragoncore.kba;
import eos.moe.dragoncore.kca;
import eos.moe.dragoncore.kp;
import eos.moe.dragoncore.me;
import eos.moe.dragoncore.nfa;
import eos.moe.dragoncore.nha;
import eos.moe.dragoncore.no;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.nz;
import eos.moe.dragoncore.oa;
import eos.moe.dragoncore.oga;
import eos.moe.dragoncore.oja;
import eos.moe.dragoncore.oka;
import eos.moe.dragoncore.om;
import eos.moe.dragoncore.pi;
import eos.moe.dragoncore.pl;
import eos.moe.dragoncore.pm;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.qr;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.sfa;
import eos.moe.dragoncore.sja;
import eos.moe.dragoncore.sq;
import eos.moe.dragoncore.to;
import eos.moe.dragoncore.tz;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.uja;
import eos.moe.dragoncore.va;
import eos.moe.dragoncore.vja;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.wq;
import eos.moe.dragoncore.xz;
import eos.moe.dragoncore.zca;
import java.io.File;
import java.io.Serializable;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.lwjgl.opengl.Display;
import org.yaml.snakeyamla.configuration.ConfigurationSection;
import org.yaml.snakeyamla.configuration.file.YamlConfiguration;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class yi {
    private static Gson ALLATORIxDEMO = new Gson();

    public yi() {
        yi a2;
    }

    @h(c=0)
    public static void ALLATORIxDEMO(UUID a2, String a3, int a4) {
        xz a5 = (xz)dt.k.ALLATORIxDEMO.getIfPresent((Object)a2);
        if (a5 != null) {
            dt.k.stopAnimation(a5, a3, a4);
        }
    }

    @h(c=1)
    public static void y(va a2) {
        UUID a3 = a2.readUniqueId();
        String a4 = a2.readString();
        int a5 = a2.readInt();
        float a6 = 1.0f;
        try {
            a6 = a2.readFloat();
        }
        catch (Exception exception) {
            // empty catch block
        }
        xz a7 = (xz)dt.k.ALLATORIxDEMO.getIfPresent((Object)a3);
        if (a7 != null) {
            dt.k.startAnimation(a7, a4, a5, a6);
        }
    }

    @h(c=2)
    public static void ALLATORIxDEMO(String a2, String a3) throws Exception {
        yi.ALLATORIxDEMO(a2, a3);
    }

    @h(c=3)
    public static void s(String a2) throws Exception {
        wka.y = a2;
    }

    @h(c=4)
    public static void w(String a2) throws Exception {
        if (Minecraft.getMinecraft().currentScreen instanceof GuiChat) {
            GuiChat a3 = (GuiChat)Minecraft.getMinecraft().currentScreen;
            GuiTextField a4 = (GuiTextField)ReflectionHelper.getPrivateValue(GuiChat.class, (Object)a3, (String[])new String[]{"inputField", "inputField"});
            if (a2.startsWith("[add]")) {
                a4.setText(a4.getText() + a2.substring(5));
            } else if (a2.startsWith("[write]")) {
                a4.writeText(a2.substring(7));
            } else {
                a4.setText(a2);
            }
        }
    }

    @h(c=5)
    public static void z(UUID a2, String a3) throws Exception {
        raa.r.ALLATORIxDEMO().put(a2, a3);
    }

    @h(c=6)
    public static void x(UUID a2) throws Exception {
        raa.r.ALLATORIxDEMO().remove(a2);
    }

    @h(c=7)
    public static void h(va a2) throws Exception {
        int a3 = a2.readInt();
        for (int a4 = 0; a4 < a3; ++a4) {
            faa.ALLATORIxDEMO(a2.readString(32768), a2.readString(32768));
        }
    }

    @h(c=11)
    public static void k() throws Exception {
        raa.r.c();
        raa.r.m.clear();
        raa.r.o.clear();
        dga.y.k.clear();
        oga.k.ALLATORIxDEMO().clear();
        aka.k.ALLATORIxDEMO().clear();
        ega.k.ALLATORIxDEMO().clear();
        uja.ALLATORIxDEMO.clear();
        dla.x.ALLATORIxDEMO().clear();
        dla.x.c().clear();
        sja.y.k.clear();
        hja.c.c().clear();
        hja.c.ALLATORIxDEMO().clear();
        hja.c.ALLATORIxDEMO().clear();
        hja.c.ALLATORIxDEMO();
        qr.b.clear();
        de.c();
        wi.b.x();
        nz.c();
        sq.ALLATORIxDEMO();
        nw.ALLATORIxDEMO("DragonCore", new String[]{"cache_clear"});
    }

    @h(c=12)
    public static void f(String a2, va a3) {
        oja a4 = new oja(a3);
        aka.k.ALLATORIxDEMO().put(a2, a4);
    }

    @h(c=13)
    public static void s(va a2) {
        String a4 = a2.readString();
        try {
            if (a2.readBoolean()) {
                aka.k.ALLATORIxDEMO().entrySet().removeIf(a3 -> ((String)a3.getKey()).startsWith(a4));
                return;
            }
        }
        catch (Exception exception) {
            // empty catch block
        }
        aka.k.ALLATORIxDEMO().remove(a4);
    }

    @h(c=14)
    public static void w(va a2) {
        HashSet<String> a3 = new HashSet<String>();
        int a4 = a2.readInt();
        for (int a5 = 0; a5 < a4; ++a5) {
            a3.add(a2.readString(32768));
        }
        jla.y.k = a3;
    }

    @h(c=15)
    public static void c(String a2, va a3) {
        oja a4 = new oja(a3, true);
        aka.k.ALLATORIxDEMO().put(a2, a4);
    }

    @h(c=16)
    public static void ALLATORIxDEMO(String a2, ItemStack a3) {
        wi.b.ALLATORIxDEMO(a2, a3);
    }

    @h(c=17)
    public static void z(String a2) {
        wq.k = a2.getBytes();
        wka.k = true;
        oa.c();
    }

    @h(c=18)
    public static void k(String a2) {
        wq.k = a2.getBytes();
        wka.k = true;
        oa.c();
    }

    @h(c=400)
    public static void d(String a2) {
        wq.ALLATORIxDEMO.add(a2);
    }

    @h(c=19)
    public static void z(va a2) {
        String a3 = a2.readString();
        String a4 = a2.readString();
        float a5 = a2.readFloat();
        float a6 = a2.readFloat();
        boolean a7 = a2.readBoolean();
        float a8 = a2.readFloat();
        float a9 = a2.readFloat();
        float a10 = a2.readFloat();
        SoundCategory a11 = SoundCategory.MASTER;
        try {
            a11 = SoundCategory.getByName((String)a2.readString());
        }
        catch (Exception exception) {
            // empty catch block
        }
        a3 = a3.isEmpty() ? MathHelper.getRandomUUID((Random)ThreadLocalRandom.current()).toString() : a3;
        om.ALLATORIxDEMO(null, a3, a4, a11, a5, a6, a8, a9, a10, a7);
    }

    @h(c=20)
    public static void x(String a2) {
        om.f(a2);
    }

    @h(c=21)
    public static void ALLATORIxDEMO(long a2, long a3, String a4, String a5, String a6, va a7) {
        HashMap<String, String> a8 = new HashMap<String, String>();
        int a9 = a7.readInt();
        for (int a10 = 0; a10 < a9; ++a10) {
            a8.put(a7.readString(), a7.readString());
        }
        jia.ALLATORIxDEMO.put(new fia(a4, a5, a6, a8), new qd<Long, Long>(a2, a3));
    }

    @h(c=22)
    public static void c(String a2, boolean a3) {
        wi.b.ALLATORIxDEMO(a2, a3);
    }

    @h(c=23)
    public static void ALLATORIxDEMO(String a2, boolean a3) {
        faa.ALLATORIxDEMO(a2, a3);
    }

    @h(c=24)
    public static void ALLATORIxDEMO(int a2) {
        Minecraft.getMinecraft().gameSettings.thirdPersonView = a2;
    }

    @h(c=25)
    public static void f(String a2) {
        Display.setTitle((String)a2);
    }

    @h(c=26)
    public static void k(va a2) {
        UUID a3 = null;
        boolean a4 = a2.readBoolean();
        if (a4) {
            a3 = a2.readUniqueId();
        }
        int a5 = a2.readInt();
        int a6 = a2.readInt();
        float a7 = a2.readFloat();
        float a8 = a2.readFloat();
        float a9 = a2.readFloat();
        float a10 = a2.readFloat();
        float a11 = a2.readFloat();
        float a12 = a2.readFloat();
        float a13 = a2.readFloat();
        EntityLivingBase a14 = pm.ALLATORIxDEMO(a3);
        if (a14 != null) {
            Vec3d a15 = nfa.c((Entity)a14);
            if (a15 == null) {
                a15 = nfa.ALLATORIxDEMO((Entity)a14);
            }
            if (a15 == null) {
                a15 = a14.getPositionVector().add(0.0, 1.5, 0.0);
            }
            a7 = (float)a15.x;
            a8 = (float)a15.y;
            a9 = (float)a15.z;
        }
        nfa.ALLATORIxDEMO.add(new fla(a5, a6, (World)Minecraft.getMinecraft().world, a7, a8, a9, 1.0f, 1.0f, 1.0f));
    }

    @h(c=27)
    public static void d(va a2) {
        UUID a3 = a2.readUniqueId();
        String a4 = a2.readString();
        float a5 = 1.0f;
        try {
            a5 = a2.readFloat();
        }
        catch (Exception exception) {
            // empty catch block
        }
        dga.y.ALLATORIxDEMO(a3, a4, a5);
    }

    @h(c=28)
    public static void k(UUID a2, String a3) {
        dga.y.ALLATORIxDEMO(a2, a3);
    }

    @h(c=29)
    public static void d(UUID a2, String a3) {
        Entity a4 = EntityList.getEntityByUUID(a2);
        if (a4 instanceof EntityPlayer) {
            dga.y.ALLATORIxDEMO((EntityPlayer)a4, YamlConfiguration.loadConfiguration(new StringReader(a3)));
        }
    }

    @h(c=36)
    public static void x(UUID a2, String a3) {
        dga.y.c(a2, a3);
    }

    @h(c=30)
    public static void ALLATORIxDEMO(String a2, va a3) {
        a2 = a2.replace("\\", "/");
        byte[] a4 = new byte[a3.readableBytes()];
        int a5 = a3.readerIndex();
        a3.getBytes(a5, a4);
        MinecraftForge.EVENT_BUS.post((Event)new EventLoader.ServerFileEvent(a2, a4));
    }

    @h(c=31)
    public static void f(UUID a2, String a3) {
        kp a4 = (kp)qr.b.k.getIfPresent((Object)a2);
        if (a4 != null) {
            a4.ALLATORIxDEMO((Object)a3).c();
        }
    }

    @h(c=32)
    public static void ALLATORIxDEMO(int a2, int a3, int a4, String a5) {
        no.ALLATORIxDEMO(a2, a3, a4, a5);
        cy.q.ALLATORIxDEMO(a2, a3, a4, a5);
    }

    @h(c=33)
    public static void c(UUID a2, String a3) {
        xz a4 = (xz)dt.k.ALLATORIxDEMO.getIfPresent((Object)a2);
        if (a4 != null) {
            a4.c(a3);
        }
    }

    @h(c=34)
    public static void ALLATORIxDEMO(UUID a2, String a3) {
        qr.b.y.put(a2, a3);
    }

    @h(c=35)
    public static void f(UUID a2) {
        qr.b.y.remove(a2);
    }

    @h(c=40)
    public static void d() {
        Collection a2 = FileUtils.listFiles((File)ca.s, (IOFileFilter)TrueFileFilter.TRUE, (IOFileFilter)TrueFileFilter.TRUE);
        for (File a3 : a2) {
            try {
                String a4;
                if (a3.isDirectory() || a3.getName().equals("config.yml") || a3.getName().endsWith(".zip") || a3.getName().endsWith(".data")) continue;
                byte[] a5 = FileUtils.readFileToByteArray((File)a3);
                boolean a6 = false;
                if (a3.getName().endsWith(".json") && (a4 = new String(a5, StandardCharsets.UTF_8)).contains("\"description\"") && a4.contains("\"bones\"")) {
                    String a7 = yi.ALLATORIxDEMO(a4);
                    byte[] a8 = a7.getBytes(StandardCharsets.UTF_8);
                    a8 = tz.ALLATORIxDEMO(a8);
                    FileUtils.writeByteArrayToFile((File)new File(a3.getParentFile(), a3.getName() + ".data"), (byte[])a8);
                    a3.delete();
                    a6 = true;
                }
                if (a6) continue;
                a5 = tz.ALLATORIxDEMO(a5);
                FileUtils.writeByteArrayToFile((File)new File(a3.getParentFile(), a3.getName() + ".data"), (byte[])a5);
                a3.delete();
            }
            catch (Exception a9) {
                a9.printStackTrace();
            }
        }
        ca.l.z("\u6587\u4ef6\u5df2\u52a0\u5bc6\u5b8c\u6210,\u5982\u4fee\u6539config.yml\u5bc6\u7801\u9700\u91cd\u65b0\u52a0\u5bc6");
    }

    public static String ALLATORIxDEMO(String a2) throws to {
        JsonObject a3 = (JsonObject)ALLATORIxDEMO.fromJson(a2, JsonObject.class);
        String a4 = null;
        for (Map.Entry a5 : a3.entrySet()) {
            if (((String)a5.getKey()).equals("format_version")) {
                a4 = ((JsonElement)a5.getValue()).getAsString();
                continue;
            }
            if (a4 == null || "1.8.0".equals(a4) || "1.10.0".equals(a4)) {
                JsonArray a6 = new JsonArray();
                a6.add((JsonElement)a5.getValue());
                yi.ALLATORIxDEMO(a6);
                continue;
            }
            yi.ALLATORIxDEMO(((JsonElement)a5.getValue()).getAsJsonArray());
        }
        return ALLATORIxDEMO.toJson((JsonElement)a3);
    }

    @h(c=95)
    public static void x() {
        pl.b.k();
    }

    @h(c=96)
    public static void c(String a2) {
        pl.b.f(a2);
    }

    @h(c=97)
    public static void ALLATORIxDEMO(String a2, String a3, va a4) {
        int a5 = a4.readInt();
        byte[] a6 = new byte[a5];
        a4.readBytes(a6);
        pl.b.c(a2, a3, a6);
    }

    @h(c=98)
    public static void f() {
        pl.b.x();
    }

    @h(c=99)
    public static void c() {
        de.f();
        nw.ALLATORIxDEMO("DragonCore", new String[]{"cache_loaded"});
    }

    @h(c=100)
    public static void ALLATORIxDEMO(String a2, String a3, boolean a4) {
        a2 = a2.toLowerCase(Locale.ROOT);
        if (a3.equals("opengui")) {
            if (wi.b.ALLATORIxDEMO(a2)) {
                de.c(a2, wi.b.ALLATORIxDEMO(a2));
            }
        } else if (a3.equals("openhud")) {
            if (wi.b.ALLATORIxDEMO(a2)) {
                de.ALLATORIxDEMO(a2, wi.b.ALLATORIxDEMO(a2));
            }
        } else if (a3.equals("openoverride") || a3.equals("opensubgui")) {
            wi.b.c(a2);
        } else {
            GuiScreen a5 = Minecraft.getMinecraft().currentScreen;
            ui a6 = null;
            if (a2.equals("default")) {
                a6 = ui.qa;
            } else if (a2.equals("itemtip") || a2.equals("tooltip")) {
                a6 = hja.c.k;
            } else if (a2.equals("override") || a2.equals("subgui")) {
                a6 = wi.b.ALLATORIxDEMO();
            } else if (a5 instanceof ui && (a2.isEmpty() || ((ui)a5).wa.toLowerCase().equals(a2))) {
                a6 = (ui)a5;
            } else if (de.c.containsKey(a2.toLowerCase(Locale.ROOT))) {
                a6 = de.c.get(a2.toLowerCase(Locale.ROOT));
            }
            if (a6 == null) {
                return;
            }
            if (a4) {
                ui a7 = a6;
                ie.ALLATORIxDEMO.submit(() -> a7.parseExpression((Object)a3).ALLATORIxDEMO());
            } else {
                a6.parseExpression((Object)a3).ALLATORIxDEMO();
            }
        }
    }

    @h(c=101)
    public static void x(va a2) {
        String a3 = a2.readString();
        String a4 = "1";
        try {
            a4 = a2.readString();
        }
        catch (Exception exception) {
            // empty catch block
        }
        GuiScreen a5 = Minecraft.getMinecraft().currentScreen;
        if (a5 instanceof ui) {
            ui a6 = (ui)a5;
            YamlConfiguration a7 = new YamlConfiguration();
            a7.loadFromStringIgnoreException(a3);
            if (a6.parseExpression((Object)a4).c()) {
                a6.reloadFromYaml(a7);
            }
        }
    }

    @h(c=103)
    public static void f(va a2) {
        GuiScreen a3 = Minecraft.getMinecraft().currentScreen;
        if (a3 instanceof ui) {
            ui a4 = (ui)a3;
            int a5 = a2.readInt();
            for (int a6 = 0; a6 < a5; ++a6) {
                String a7 = a2.readString();
                String a8 = a2.readString();
                String a9 = a2.readString();
                jj a10 = a4.findComponent(a7);
                if (a10 == null) continue;
                a10.setValue(a8, a9);
            }
        }
    }

    @h(c=102)
    public static void c(va a2) {
        UUID a3 = a2.readUniqueId();
        String a4 = a2.readString();
        float a5 = 1.0f;
        try {
            a5 = a2.readFloat();
        }
        catch (Exception exception) {
            // empty catch block
        }
        sq.ALLATORIxDEMO(a3, a4, a5);
    }

    @h(c=150)
    public static void ALLATORIxDEMO(UUID a2, UUID a3, float a4, float a5, float a6, float a7, float a8) {
        kba.ALLATORIxDEMO.put(a2, new oka(a3, a4, a5, a6, a7 != 0.0f, a8 != 0.0f));
    }

    @h(c=151)
    public static void c(UUID a2) {
        kba.ALLATORIxDEMO.remove(a2);
    }

    @h(c=201)
    public static void ALLATORIxDEMO(va a2) {
        String a3 = a2.readString();
        String a4 = a2.readString();
        String a5 = a2.readString();
        String a6 = a2.readString();
        int a7 = a2.readInt();
        String a8 = "";
        try {
            a8 = a2.readString();
        }
        catch (Exception exception) {
            // empty catch block
        }
        pi.ALLATORIxDEMO.ALLATORIxDEMO(a3, a4, a5, a6, a8, a7);
    }

    @h(c=202)
    public static void ALLATORIxDEMO(String a2) {
        pi.ALLATORIxDEMO.ALLATORIxDEMO(a2);
    }

    @h(c=203)
    public static void ALLATORIxDEMO(UUID a2) {
        pi.ALLATORIxDEMO.ALLATORIxDEMO(a2);
    }

    @h(c=204)
    public static void ALLATORIxDEMO() {
        pi.ALLATORIxDEMO.ALLATORIxDEMO();
    }

    public static YamlConfiguration ALLATORIxDEMO(String a2, String a3) throws Exception {
        Object a4;
        String a6 = a2 = a2.replace("\\", "/");
        a2 = a2.toLowerCase(Locale.ROOT);
        YamlConfiguration a7 = new YamlConfiguration();
        a7.setFileName(a6);
        Set<String> a8 = a7.getKeys(true);
        for (String string : a8) {
            if (!a7.isConfigurationSection(string)) continue;
            a7.getConfigurationSection(string).setFileName(a6);
        }
        try {
            a7.loadFromString(a3);
        }
        catch (Exception a42) {
            a42.printStackTrace();
        }
        if (a2.startsWith("EntityModel".toLowerCase())) {
            for (ConfigurationSection configurationSection : a7.getSections()) {
                rda a9 = new rda(configurationSection);
                raa.r.m.put(a9.c(), a9);
                raa.r.o.clear();
            }
        } else if (a2.startsWith("ItemTip".toLowerCase())) {
            if (a7.getConfigurationSection("setting") == null) {
                hja.c.ALLATORIxDEMO().add(new baa(a7));
                hja.c.ALLATORIxDEMO().sort(Comparator.comparing(baa::ALLATORIxDEMO).reversed());
                hja.c.ALLATORIxDEMO().put(a2.substring(8).replace(".yml", ""), a7);
            } else {
                hja.c.c().add(new eba(a7));
                hja.c.c().sort(Comparator.comparing(eba::ALLATORIxDEMO).reversed());
            }
        } else if (a2.startsWith("ItemModel".toLowerCase())) {
            a4 = dla.x.c();
            ArrayList<vja> arrayList = new ArrayList<vja>();
            for (ConfigurationSection a10 : a7.getSections()) {
                vja a11 = new vja(a2, a10);
                arrayList.add(a11);
            }
            ((CopyOnWriteArrayList)a4).addAll(arrayList);
            ((CopyOnWriteArrayList)a4).sort(Comparator.comparing(vja::c).reversed());
            dla.x.ALLATORIxDEMO();
        } else if (a2.startsWith("Blood".toLowerCase())) {
            for (ConfigurationSection configurationSection : a7.getSections()) {
                ConcurrentHashMap<String, nha> a12 = ega.k.ALLATORIxDEMO();
                String a13 = configurationSection.getName();
                nha a14 = new nha(configurationSection);
                a12.put(a13, a14);
            }
        } else if (a2.startsWith("FontConfig".toLowerCase())) {
            a4 = new HashMap();
            for (ConfigurationSection a15 : a7.getSections()) {
                for (char a16 : a15.getName().toCharArray()) {
                    kca a17 = new kca(a16, a15);
                    ((HashMap)a4).put(Character.valueOf(a16), a17);
                }
            }
            oga.k.ALLATORIxDEMO().putAll((Map<Character, kca>)a4);
        } else if (a2.startsWith("ItemIcon".toLowerCase())) {
            a4 = dla.x.ALLATORIxDEMO();
            ArrayList<jga> arrayList = new ArrayList<jga>();
            for (ConfigurationSection a18 : a7.getSections()) {
                try {
                    jga a19 = new jga(a2, a18);
                    arrayList.add(a19);
                }
                catch (dia a20) {
                    a20.printStackTrace();
                }
            }
            ((CopyOnWriteArrayList)a4).addAll(arrayList);
            ((CopyOnWriteArrayList)a4).sort(Comparator.comparing(jga::c).reversed());
            dla.x.ALLATORIxDEMO();
        } else if (a2.startsWith("WorldTexture".toLowerCase())) {
            for (ConfigurationSection configurationSection : a7.getSections()) {
                ConcurrentHashMap<String, oja> a21 = aka.k.ALLATORIxDEMO();
                String a22 = configurationSection.getName();
                oja a23 = new oja(configurationSection);
                a21.put(a22, a23);
            }
        } else if (a2.startsWith("Gui/".toLowerCase())) {
            wi.b.ALLATORIxDEMO(a2.substring(4).replace(".yml", ""), a7);
        } else if (a2.startsWith("ArmorLayer".toLowerCase(Locale.ROOT))) {
            for (ConfigurationSection configurationSection : a7.getSections()) {
                uja.ALLATORIxDEMO.put(configurationSection.getName(), new zca(configurationSection));
            }
        } else if (a2.startsWith("ItemInfo".toLowerCase(Locale.ROOT))) {
            for (ConfigurationSection configurationSection : a7.getSections()) {
                efa.k.ALLATORIxDEMO(configurationSection);
            }
        } else if (a2.startsWith("BlockModel".toLowerCase())) {
            for (ConfigurationSection configurationSection : a7.getSections()) {
                cy.q.ALLATORIxDEMO(configurationSection);
            }
        } else if (a2.startsWith("HeadTag/".toLowerCase())) {
            qr.b.o.put(a2.substring(8).replace(".yml", ""), new hv(a7));
        } else if (a2.startsWith("itemeffect")) {
            a4 = sja.y.k;
            ArrayList<sfa> arrayList = new ArrayList<sfa>();
            for (ConfigurationSection a24 : a7.getSections()) {
                try {
                    sfa a25 = new sfa(a24);
                    arrayList.add(a25);
                }
                catch (dia a26) {
                    a26.printStackTrace();
                }
            }
            a4.addAll(arrayList);
            sja.y.ALLATORIxDEMO();
        }
        return a7;
    }

    private static /* synthetic */ bf ALLATORIxDEMO(JsonElement a2) throws to {
        bax a3;
        Object a42;
        JsonObject a5;
        bax a6 = me.ALLATORIxDEMO("pivot", a2, new bax(0.0f, 0.0f, 0.0f));
        bax a7 = me.ALLATORIxDEMO("rotation", a2, new bax(0.0f, 0.0f, 0.0f));
        boolean a8 = me.ALLATORIxDEMO("mirror", a2, false);
        boolean a9 = me.ALLATORIxDEMO("neverrender", a2, false);
        float a10 = me.ALLATORIxDEMO("inflate", a2, 0.0f);
        String a11 = me.ALLATORIxDEMO("name", a2);
        HashMap<String, ModelLocator> a12 = new HashMap<String, ModelLocator>();
        String a13 = me.ALLATORIxDEMO("parent", a2, null);
        if (a2.getAsJsonObject().has("locators")) {
            a5 = a2.getAsJsonObject().get("locators").getAsJsonObject();
            for (Object a42 : a5.entrySet()) {
                if (((JsonElement)a42.getValue()).isJsonArray()) {
                    a12.put((String)a42.getKey(), new ModelLocator(a11, me.ALLATORIxDEMO((JsonElement)a42.getValue())));
                    continue;
                }
                Object a14 = me.ALLATORIxDEMO("offset", (JsonElement)a42.getValue(), new bax(0.0f, 0.0f, 0.0f));
                a3 = me.ALLATORIxDEMO("rotation", (JsonElement)a42.getValue(), new bax(0.0f, 0.0f, 0.0f));
                a12.put((String)a42.getKey(), new ModelLocator(a11, (bax)a14, a3));
            }
        }
        a5 = new ArrayList();
        ArrayList<gv> a15 = new ArrayList<gv>();
        if (a2.getAsJsonObject().has("cubes")) {
            for (Object a14 : a2.getAsJsonObject().get("cubes").getAsJsonArray()) {
                Serializable a16;
                Serializable a17;
                Serializable a18;
                a3 = me.ALLATORIxDEMO("origin", a14, new bax(0.0f, 0.0f, 0.0f));
                bax a19 = me.ALLATORIxDEMO("size", a14, new bax(0.0f, 0.0f, 0.0f));
                JsonElement a20 = me.ALLATORIxDEMO("uv", a14.getAsJsonObject());
                if (!(a20 instanceof JsonArray)) {
                    a18 = new ag(a20, "north");
                    a17 = new ag(a20, "east");
                    a16 = new ag(a20, "south");
                    ag a21 = new ag(a20, "west");
                    ag a22 = new ag(a20, "up");
                    ag a23 = new ag(a20, "down");
                    ArrayList a24 = Lists.newArrayList((Object[])new ag[]{a18, a17, a16, a21, a22, a23});
                    if (a14.getAsJsonObject().has("rotation") || a14.getAsJsonObject().has("inflate") || a14.getAsJsonObject().has("mirror")) {
                        bax a25 = me.ALLATORIxDEMO("rotation", a14, new bax(0.0f, 0.0f, 0.0f));
                        bax a26 = me.ALLATORIxDEMO("pivot", a14, new bax(0.0f, 0.0f, 0.0f));
                        boolean a27 = me.ALLATORIxDEMO("mirror", a14, false);
                        float a28 = me.ALLATORIxDEMO("inflate", a14, 0.0f);
                        a5.add(new bf(Lists.newArrayList((Object[])new gv[]{new gv(a3, a19, a24)}), a26, a25, a27, false, a28, "cube_wrapper_" + a5.size(), a11));
                        continue;
                    }
                    a15.add(new gv(a3, a19, a24));
                    continue;
                }
                a18 = me.ALLATORIxDEMO("uv", a14, new acf());
                if (a14.getAsJsonObject().has("rotation") || a14.getAsJsonObject().has("inflate") || a14.getAsJsonObject().has("mirror")) {
                    a17 = me.ALLATORIxDEMO("rotation", a14, new bax(0.0f, 0.0f, 0.0f));
                    a16 = me.ALLATORIxDEMO("pivot", a14, new bax(0.0f, 0.0f, 0.0f));
                    boolean a29 = me.ALLATORIxDEMO("mirror", a14, false);
                    float a30 = me.ALLATORIxDEMO("inflate", a14, 0.0f);
                    a5.add(new bf(Lists.newArrayList((Object[])new gv[]{new gv(a3, a19, (acf)a18)}), (bax)a16, (bax)a17, a29, false, a30, "cube_wrapper_" + a5.size(), a11));
                    continue;
                }
                a15.add(new gv(a3, a19, (acf)a18));
            }
        }
        a42 = new bf(a15, a6, a7, a8, a9, a10, a11, a13);
        ((bf)a42).r = a5;
        ((bf)a42).locatorMap = a12;
        return a42;
    }

    private static /* synthetic */ void ALLATORIxDEMO(JsonArray a2) throws to {
        JsonObject a3 = a2.get(0).getAsJsonObject();
        JsonArray a4 = a3.get("bones").getAsJsonArray();
        for (int a5 = 0; a5 < a4.size(); ++a5) {
            JsonElement a6 = a4.get(a5);
            if (a6 instanceof JsonPrimitive) continue;
            bf a7 = yi.ALLATORIxDEMO(a6);
            String a8 = ALLATORIxDEMO.toJson((Object)a7);
            a4.set(a5, (JsonElement)new JsonPrimitive(a8));
        }
    }
}

