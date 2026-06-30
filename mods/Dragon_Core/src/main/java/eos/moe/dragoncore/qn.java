/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.item.ItemStack
 *  net.minecraft.world.World
 *  net.minecraftforge.client.ForgeHooksClient
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.af;
import eos.moe.dragoncore.gi;
import eos.moe.dragoncore.jj;
import eos.moe.dragoncore.mh;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.pf;
import eos.moe.dragoncore.qd;
import eos.moe.dragoncore.sd;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.sja;
import eos.moe.dragoncore.ui;
import eos.moe.dragoncore.wi;
import eos.moe.dragoncore.wo;
import eos.moe.dragoncore.xf;
import java.awt.Point;
import java.util.Locale;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import org.lwjgl.opengl.GL11;
import org.yaml.snakeyamla.configuration.ConfigurationSection;

public class qn
extends jj {
    public mh y;
    public mh k;
    public mh ALLATORIxDEMO;

    public qn(ui a2, ConfigurationSection a3) {
        super(a2, a3);
        qn a4;
        a4.y = a4.createMoLangParserString("identifier", "unknown");
        a4.p = a4.createMoLangParser("width", 16);
        a4.u = a4.createMoLangParser("height", 16);
        a4.ALLATORIxDEMO = a4.createMoLangParserString("amount", "");
        a4.k = a4.createMoLangParser("drawBackground", true);
    }

    @Override
    public void preRender(int a2, int a3) {
        double a4;
        double a5;
        qn a6;
        sd.ALLATORIxDEMO((float)a6.aa.ALLATORIxDEMO());
        double a7 = a6.g.ALLATORIxDEMO();
        double a8 = a6.s.ALLATORIxDEMO();
        double a9 = a6.z.ALLATORIxDEMO();
        double a10 = a6.i.ALLATORIxDEMO();
        double a11 = a6.j.ALLATORIxDEMO();
        double a12 = a6.l.ALLATORIxDEMO();
        if (((ui)((Object)a6.y)).g <= 0 && a6.ALLATORIxDEMO.containsKey(0)) {
            a5 = sj.ALLATORIxDEMO(a2 - ((Point)a6.ALLATORIxDEMO.get((Object)Integer.valueOf((int)0))).x, a9, a10);
            a4 = sj.ALLATORIxDEMO(a3 - ((Point)a6.ALLATORIxDEMO.get((Object)Integer.valueOf((int)0))).y, a12, a11);
        } else {
            a5 = sj.ALLATORIxDEMO(a8, a9, a10);
            a4 = sj.ALLATORIxDEMO(a7, a12, a11);
        }
        if (a5 != a8 || a4 != a7) {
            a6.s.ALLATORIxDEMO(a5);
            a6.g.ALLATORIxDEMO(a4);
            a8 = a5;
            a7 = a4;
            a6.runAction("drag");
        }
        GlStateManager.func_179094_E();
        double a13 = a6.e.ALLATORIxDEMO();
        double a14 = a6.n.ALLATORIxDEMO();
        a6.y = (mh)(a13 != 0.0 && a14 != 0.0 ? 1 : 0);
        if (a6.y != false) {
            GL11.glEnable((int)3089);
            sd.ALLATORIxDEMO((int)a6.getLimitXPos(), (int)a6.getLimitYPos(), (int)a13, (int)a14);
        }
        GlStateManager.func_179137_b((double)(Math.floor((a6.getXPos() + a6.m.ALLATORIxDEMO() + a8) * 10.0) / 10.0), (double)(Math.floor((a6.getYPos() + a6.c.ALLATORIxDEMO() + a7) * 10.0) / 10.0), (double)0.0);
        double a15 = a6.ba.ALLATORIxDEMO();
        if (a15 != 1.0) {
            GlStateManager.func_179139_a((double)a15, (double)a15, (double)1.0);
        }
        if ((a15 = a6.t.ALLATORIxDEMO()) != 1.0) {
            double a16 = a6.p.ALLATORIxDEMO();
            double a17 = a6.u.ALLATORIxDEMO();
            GlStateManager.func_179137_b((double)(a16 / 2.0), (double)(a17 / 2.0), (double)0.0);
            GlStateManager.func_179139_a((double)a15, (double)a15, (double)1.0);
            GlStateManager.func_179137_b((double)(-a16 / 2.0), (double)(-a17 / 2.0), (double)0.0);
        }
    }

    @Override
    public void render(int a2, int a3) {
        qn a4;
        String a5 = a4.y.ALLATORIxDEMO();
        qd<ItemStack, String> a6 = wi.b.ALLATORIxDEMO(a5, ((af)((Object)a4.y)).isHud());
        ItemStack a7 = a6.c();
        if (a7 == null) {
            return;
        }
        boolean a8 = a4.k.ALLATORIxDEMO();
        if (a6.ALLATORIxDEMO() != null && a6.ALLATORIxDEMO().endsWith("\u00a7c") && a8) {
            sd.ALLATORIxDEMO(0.0, 0.0, a4.p.ALLATORIxDEMO(), a4.u.ALLATORIxDEMO(), -2130706433);
        }
        if (!a7.func_190926_b() && sd.ALLATORIxDEMO() >= 1.0f) {
            RenderItem a9 = Minecraft.func_71410_x().func_175599_af();
            FontRenderer a10 = a7.func_77973_b().getFontRenderer(a7);
            FontRenderer a11 = Minecraft.func_71410_x().field_71466_p;
            GlStateManager.func_179094_E();
            GlStateManager.func_179139_a((double)(a4.p.ALLATORIxDEMO() / 16.0), (double)(a4.u.ALLATORIxDEMO() / 16.0), (double)1.0);
            RenderHelper.func_74520_c();
            GlStateManager.func_179126_j();
            wo.ALLATORIxDEMO = true;
            IBakedModel a12 = a9.func_184393_a(a7, (World)Minecraft.func_71410_x().field_71441_e, null);
            a4.renderItemModelIntoGUI(a7, 0, 0, a12);
            wo.ALLATORIxDEMO = false;
            String a13 = a4.ALLATORIxDEMO.ALLATORIxDEMO();
            if (!a13.isEmpty()) {
                a9.func_180453_a(a10 == null ? a11 : a10, a7, 0, 0, a13);
            } else {
                a9.func_180453_a(a10 == null ? a11 : a10, a7, 0, 0, a6.ALLATORIxDEMO());
            }
            GlStateManager.func_179121_F();
            RenderHelper.func_74518_a();
        }
        if (((ui)((Object)a4.y)).d == a4 && a8) {
            GlStateManager.func_179140_f();
            GlStateManager.func_179097_i();
            sd.ALLATORIxDEMO(0.0, 0.0, a4.p.ALLATORIxDEMO(), a4.u.ALLATORIxDEMO(), -2130706433);
            GlStateManager.func_179126_j();
        }
    }

    @Override
    public void runClick(int a2, int a3, int a4) {
        qn a5;
        super.runClick(a2, a3, a4);
        if (!gi.c((ui)((Object)a5.y), "slotClick", xf.ALLATORIxDEMO(a5.k.getName()), pf.ALLATORIxDEMO(a4)).ALLATORIxDEMO()) {
            nw.ALLATORIxDEMO("DragonCore_ClickSlot", a5.y.ALLATORIxDEMO(), String.valueOf(a4));
        }
    }

    @Override
    public void cache(int a2, int a3) {
        qn a4;
        super.cache(a2, a3);
        a4.y.c();
        a4.k.c();
        a4.ALLATORIxDEMO.c();
    }

    @Override
    public void setValue(String a2, Object a3) {
        qn a4;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "identifier": {
                a4.y.ALLATORIxDEMO(a4.toMolangParser((String)a3));
                return;
            }
            case "drawbackground": {
                a4.k.ALLATORIxDEMO(a4.toMolangParser(a3));
                break;
            }
            case "amount": {
                a4.ALLATORIxDEMO.ALLATORIxDEMO(a4.toMolangParser((String)a3));
            }
        }
        super.setValue(a2, a3);
    }

    @Override
    public Object getValue(String a2) {
        qn a3;
        switch (a2.toLowerCase(Locale.ROOT)) {
            case "type_": 
            case "type": {
                return "slot";
            }
            case "identifier": {
                return a3.y.c();
            }
            case "drawbackground": {
                return a3.k.c();
            }
            case "identifier_": {
                return a3.y.f();
            }
            case "drawbackground_": {
                return a3.k.f();
            }
            case "amount": {
                return a3.ALLATORIxDEMO.c();
            }
            case "amount_": {
                return a3.ALLATORIxDEMO.f();
            }
        }
        return super.getValue(a2);
    }

    public void renderItemModelIntoGUI(ItemStack a2, int a3, int a4, IBakedModel a5) {
        qn a6;
        sja.y.ALLATORIxDEMO(a2, 0.0, 0.0, 0);
        TextureManager a7 = Minecraft.func_71410_x().func_110434_K();
        RenderItem a8 = Minecraft.func_71410_x().func_175599_af();
        GlStateManager.func_179094_E();
        a7.func_110577_a(TextureMap.field_110575_b);
        a7.func_110581_b(TextureMap.field_110575_b).func_174936_b(false, false);
        GlStateManager.func_179091_B();
        GlStateManager.func_179141_d();
        GlStateManager.func_179092_a((int)516, (float)0.1f);
        GlStateManager.func_179147_l();
        GlStateManager.func_187401_a((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.func_179131_c((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        a6.ALLATORIxDEMO(a5.func_177556_c());
        float a9 = (float)a6.r.ALLATORIxDEMO();
        float a10 = (float)a6.x.ALLATORIxDEMO();
        float a11 = (float)a6.v.ALLATORIxDEMO();
        if (a9 != 0.0f || a10 != 0.0f || a11 != 0.0f) {
            GlStateManager.func_179114_b((float)a9, (float)1.0f, (float)0.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)a10, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.func_179114_b((float)a11, (float)0.0f, (float)0.0f, (float)1.0f);
        }
        a5 = ForgeHooksClient.handleCameraTransforms((IBakedModel)a5, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GUI, (boolean)false);
        a8.func_180454_a(a2, a5);
        GlStateManager.func_179118_c();
        GlStateManager.func_179101_C();
        GlStateManager.func_179140_f();
        GlStateManager.func_179121_F();
        a7.func_110577_a(TextureMap.field_110575_b);
        a7.func_110581_b(TextureMap.field_110575_b).func_174935_a();
        sja.y.ALLATORIxDEMO(a2, 0.0, 0.0, 1);
    }

    private /* synthetic */ void ALLATORIxDEMO(boolean a2) {
        RenderItem a3 = Minecraft.func_71410_x().func_175599_af();
        GlStateManager.func_179109_b((float)0.0f, (float)0.0f, (float)(100.0f + a3.field_77023_b));
        GlStateManager.func_179109_b((float)8.0f, (float)8.0f, (float)0.0f);
        GlStateManager.func_179152_a((float)1.0f, (float)-1.0f, (float)1.0f);
        GlStateManager.func_179152_a((float)16.0f, (float)16.0f, (float)16.0f);
        if (a2) {
            GlStateManager.func_179145_e();
        } else {
            GlStateManager.func_179140_f();
        }
    }
}

