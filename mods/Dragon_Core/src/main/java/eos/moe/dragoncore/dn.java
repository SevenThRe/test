/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.authlib.GameProfile
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.gui.GuiChat
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelPlayer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.Render
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityList
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.world.World
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.apache.commons.lang3.math.NumberUtils
 */
package eos.moe.dragoncore;

import com.mojang.authlib.GameProfile;
import eos.moe.dragoncore.EntityList;
import eos.moe.dragoncore.af;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.qk;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.xz;
import java.awt.Color;
import java.util.Locale;
import java.util.UUID;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.math.NumberUtils;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

@Mod.EventBusSubscriber(modid="dragoncore")
public class dn
extends jj {
    private mh ua;
    private Entity s;
    private mh g;
    private mh q;
    private EntityLivingBase b;
    private mh o;
    private mh y;
    private String k;
    private static boolean ALLATORIxDEMO = false;

    public dn(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        dn a4;
        a4.ua = a4.createMoLangParserString("entity", "owner");
        a4.g = a4.createMoLangParser("head", false);
        a4.q = a4.createMoLangParser("followMouse", true);
        a4.o = a4.createMoLangParser("hideName", false);
        a4.y = a4.createMoLangParserString("model", "armor_stand");
    }

    @Override
    public void render(int a2, int a3) {
        dn a4;
        Entity a5 = a4.getEntity(a4.ua.ALLATORIxDEMO());
        if (a5 != null && Minecraft.func_71410_x().func_175598_ae().field_78734_h != null && a5 instanceof EntityLivingBase) {
            xz a6;
            if (((af)((Object)a4.y)).isHud() && !(Minecraft.func_71410_x().field_71462_r instanceof GuiChat)) {
                a2 = (int)(a4.getXPos() + a4.s.ALLATORIxDEMO());
                a3 = (int)(a4.getYPos() + ((qk)((Object)a4.g)).ALLATORIxDEMO() - 50.0 * a4.ba.c());
            }
            if (Minecraft.func_71410_x().field_71441_e.func_73045_a(a5.func_145782_y()) != a5 && (a6 = dt.k.getEntityManager(a5.func_110124_au())) != null) {
                dt.k.startAnimation(a6, "idle", 0, 1.0f);
            }
            a4.drawEntityOnScreen(0, 0, 30, a4.getXPos() + a4.s.ALLATORIxDEMO() - (double)a2, a4.getYPos() + ((qk)((Object)a4.g)).ALLATORIxDEMO() - 50.0 * a4.ba.c() - (double)a3, (EntityLivingBase)a5);
        }
    }

    @Override
    public void renderDebug(int a2, int a3) {
        dn a4;
        boolean a5 = a4.isHovered(a2, a3);
        double a6 = 30.0;
        double a7 = 60.0;
        if (a5) {
            sd.ALLATORIxDEMO(0.0 - a6 / 2.0, -60.0, a6, a7, 1.0, new Color(247, 49, 49, 255));
        } else {
            sd.ALLATORIxDEMO(0.0 - a6 / 2.0, -60.0, a6, a7, 1.0, new Color(71, 145, 255, 255));
        }
    }

    public Entity getEntity(String a2) {
        dn a3;
        switch (a2) {
            case "owner": {
                return Minecraft.func_71410_x().field_71439_g;
            }
            case "aim": {
                if (Minecraft.func_71410_x().field_71476_x != null) {
                    return Minecraft.func_71410_x().field_71476_x.field_72308_g;
                }
                return null;
            }
            case "firstaim": {
                if (a3.s == null && Minecraft.func_71410_x().field_71476_x != null) {
                    a3.s = Minecraft.func_71410_x().field_71476_x.field_72308_g;
                }
                return a3.s;
            }
        }
        if (NumberUtils.isDigits((String)a2)) {
            return Minecraft.func_71410_x().field_71441_e.func_73045_a(NumberUtils.toInt((String)a2));
        }
        try {
            UUID a4 = UUID.fromString(a2);
            return EntityList.getEntityByUUID(a4);
        }
        catch (Exception a5) {
            Object a6;
            String a7 = a3.y.ALLATORIxDEMO();
            if (!a7.equals(a3.k)) {
                a3.b = null;
                a3.k = a7;
            }
            if (a3.b == null) {
                if (a7.equals("player")) {
                    a3.b = new EntityOtherPlayerMP((World)FMLClientHandler.instance().getWorldClient(), new GameProfile(UUID.randomUUID(), a2));
                } else {
                    a6 = net.minecraft.entity.EntityList.func_192839_a((String)a7);
                    if (a6 == null) {
                        a6 = EntityArmorStand.class;
                    }
                    try {
                        a3.b = (EntityLivingBase)((Class)a6).getConstructor(World.class).newInstance(FMLClientHandler.instance().getWorldClient());
                    }
                    catch (Exception a8) {
                        a3.b = new EntityArmorStand((World)FMLClientHandler.instance().getWorldClient());
                    }
                }
                a3.b.func_174805_g(true);
            }
            a6 = Minecraft.func_71410_x().func_175598_ae();
            if (((RenderManager)a6).field_78734_h != null) {
                a3.b.field_70165_t = ((RenderManager)a6).field_78734_h.field_70165_t;
                a3.b.field_70163_u = ((RenderManager)a6).field_78734_h.field_70163_u;
                a3.b.field_70161_v = ((RenderManager)a6).field_78734_h.field_70161_v;
            }
            a3.b.func_96094_a(a2);
            return a3.b;
        }
    }

    @Override
    public boolean isHovered(int a2, int a3) {
        dn a4;
        if (!a4.ta.c()) {
            return false;
        }
        if (a4.e.ALLATORIxDEMO() != 0.0 && a4.n.ALLATORIxDEMO() != 0.0 && !sj.ALLATORIxDEMO(a2, a3, a4.getLimitXPos(), a4.getLimitYPos(), a4.e.ALLATORIxDEMO(), a4.n.ALLATORIxDEMO())) {
            return false;
        }
        double a5 = a4.ba.ALLATORIxDEMO();
        return sj.ALLATORIxDEMO(a2, a3, a4.getXPos() + a4.s.ALLATORIxDEMO() - 15.0 * a5, a4.getYPos() + ((qk)((Object)a4.g)).ALLATORIxDEMO() - 60.0 * a5, 30.0 * a5, 60.0 * a5);
    }

    @Override
    public double getWidth() {
        return 30.0;
    }

    @Override
    public void cache(int a2, int a3) {
        dn a4;
        super.cache(a2, a3);
        a4.ua.c();
        a4.g.c();
        a4.q.c();
        a4.o.c();
        a4.y.c();
    }

    @Override
    public Object getValue(String a2) {
        dn a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "type": 
            case "type_": {
                return "entity";
            }
            case "head": {
                return a3.g.c();
            }
            case "followmouse": {
                return a3.q.c();
            }
            case "height": 
            case "height_": {
                return "60";
            }
            case "width": 
            case "width_": {
                return "30";
            }
            case "entity": {
                return a3.ua.c();
            }
            case "hidename": {
                return a3.o.c();
            }
            case "hidename_": {
                return a3.o.f();
            }
            case "head_": {
                return a3.g.f();
            }
            case "followmouse_": {
                return a3.q.f();
            }
            case "entity_": {
                return a3.ua.f();
            }
        }
        return super.getValue(a2);
    }

    @Override
    public void setValue(String a2, Object a3) {
        dn a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "entity": {
                a4.ua.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "head": {
                a4.g.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "followmouse": {
                a4.q.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
            case "hidename": {
                a4.o.ALLATORIxDEMO(a4.toMolangParser(a3));
                return;
            }
        }
        super.setValue(a2, a3);
    }

    public void drawEntityOnScreen(int a2, int a3, int a4, double a5, double a6, EntityLivingBase a7) {
        RenderLivingBase a8;
        ModelBase a9;
        dn a10;
        boolean a11 = a10.q.ALLATORIxDEMO();
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)sd.ALLATORIxDEMO());
        GlStateManager.func_179142_g();
        GlStateManager.func_179094_E();
        GlStateManager.func_179109_b((float)a2, (float)a3, (float)0.0f);
        GlStateManager.func_179152_a((float)(-a4), (float)a4, (float)a4);
        GlStateManager.func_179114_b((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        float a12 = a7.field_70761_aq;
        float a13 = a7.field_70177_z;
        float a14 = a7.field_70125_A;
        float a15 = a7.field_70758_at;
        float a16 = a7.field_70759_as;
        GlStateManager.func_179114_b((float)135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        RenderHelper.func_74519_b();
        GlStateManager.func_179114_b((float)-135.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        if (a11) {
            GlStateManager.func_179114_b((float)(-((float)Math.atan(a6 / 40.0)) * 20.0f), (float)1.0f, (float)0.0f, (float)0.0f);
            a7.field_70761_aq = (float)Math.atan(a5 / 40.0) * 20.0f;
            a7.field_70177_z = (float)Math.atan(a5 / 40.0) * 40.0f;
            a7.field_70125_A = -((float)Math.atan(a6 / 40.0)) * 20.0f;
        } else {
            a7.field_70761_aq = 0.0f;
            a7.field_70177_z = 0.0f;
            a7.field_70125_A = 0.0f;
        }
        a7.field_70759_as = a7.field_70177_z;
        a7.field_70758_at = a7.field_70177_z;
        RenderManager a17 = Minecraft.func_71410_x().func_175598_ae();
        a17.func_178631_a(180.0f);
        a17.func_178633_a(false);
        Render a18 = a17.func_78713_a((Entity)a7);
        ModelPlayer a19 = null;
        if (a18 instanceof RenderLivingBase && (a9 = (a8 = (RenderLivingBase)a18).func_177087_b()) instanceof ModelPlayer) {
            a19 = (ModelPlayer)a9;
        }
        if (a19 != null && a10.g.ALLATORIxDEMO()) {
            a19.func_178719_a(true);
        }
        ALLATORIxDEMO = a10.o.ALLATORIxDEMO();
        a17.func_188391_a((Entity)a7, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        ALLATORIxDEMO = false;
        a17.func_178633_a(true);
        a7.field_70761_aq = a12;
        a7.field_70177_z = a13;
        a7.field_70125_A = a14;
        a7.field_70758_at = a15;
        a7.field_70759_as = a16;
        GlStateManager.func_179121_F();
        RenderHelper.func_74518_a();
        GlStateManager.func_179101_C();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77476_b);
        GlStateManager.func_179090_x();
        GlStateManager.func_179138_g((int)OpenGlHelper.field_77478_a);
    }

    @SubscribeEvent(priority=EventPriority.HIGHEST)
    public static void ALLATORIxDEMO(RenderLivingEvent.Specials.Pre<EntityLivingBase> a2) {
        if (ALLATORIxDEMO) {
            a2.setCanceled(true);
        }
    }
}

