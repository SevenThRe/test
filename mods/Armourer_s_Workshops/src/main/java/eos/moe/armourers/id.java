/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix4f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.FontRenderer
 *  net.minecraft.client.gui.ScaledResolution
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Post
 *  net.minecraftforge.client.event.GuiScreenEvent$DrawScreenEvent$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.player.ItemTooltipEvent
 *  net.minecraftforge.fml.client.config.GuiUtils
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  org.apache.commons.lang3.tuple.Pair
 *  org.lwjgl.opengl.GL11
 */
package eos.moe.armourers;

import eos.moe.armourers.ModelData;
import eos.moe.armourers.fk;
import eos.moe.armourers.ih;
import eos.moe.armourers.ql;
import eos.moe.armourers.tg;
import eos.moe.armourers.vk;
import eos.moe.armourers.xd;
import java.util.List;
import javax.vecmath.Matrix4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.apache.commons.lang3.tuple.Pair;
import org.lwjgl.opengl.GL11;

@SideOnly(value=Side.CLIENT)
public class id {
    private List<String> m;
    private ItemStack j;

    private /* synthetic */ int[] r(ItemStack a22, List<String> a3, int a4, int a5, int a6, int a7, FontRenderer a8) {
        if (!a3.isEmpty()) {
            int n2;
            int a22 = 0;
            for (String string : a3) {
                n2 = a8.func_78256_a(string);
                if (n2 <= a22) continue;
                a22 = n2;
            }
            int n3 = a6 + 12;
            int n4 = a7 - 12;
            n2 = 8;
            if (a3.size() > 1) {
                n2 += 2 + (a3.size() - 1) * 10;
            }
            if (n3 + a22 > a4) {
                n3 -= 28 + a22;
            }
            if (n4 + n2 + 6 > a5) {
                n4 = a5 - n2 - 6;
            }
            if (n3 < 12) {
                n3 = 12;
            }
            if (n4 < 8) {
                n4 = 8;
            }
            int[] nArray = new int[4];
            nArray[0] = n3;
            nArray[1] = n4;
            nArray[2] = a22;
            nArray[3] = n2;
            return nArray;
        }
        return null;
    }

    @SubscribeEvent
    public void r(GuiScreenEvent.DrawScreenEvent.Post a2) {
        List<String> list;
        boolean bl;
        id a3;
        if (!vk.u) {
            return;
        }
        if (!vk.y) {
            return;
        }
        id id2 = a3;
        fk fk2 = fk.r(id2.j);
        List<String> list2 = id2.m;
        ItemStack itemStack = id2.j;
        id2.m = null;
        id2.j = null;
        Minecraft minecraft = Minecraft.func_71410_x();
        if (minecraft.field_71462_r == null) {
            return;
        }
        if (fk2 != null) {
            bl = true;
            list = list2;
        } else {
            bl = false;
            list = list2;
        }
        if (bl & list != null) {
            float f2 = vk.s;
            id id3 = a3;
            int[] nArray = id3.r(itemStack, list2, minecraft.field_71462_r.field_146294_l, minecraft.field_71462_r.field_146295_m, a2.getMouseX() + 8, a2.getMouseY(), minecraft.field_71466_p);
            int n2 = (int)((float)nArray[0] - f2 - 28.0f);
            int n3 = nArray[1] - 4;
            if (id3.r(itemStack, list2, minecraft.field_71462_r.field_146294_l, minecraft.field_71462_r.field_146295_m, a2.getMouseX() + 8, a2.getMouseY(), minecraft.field_71466_p)) {
                n2 = nArray[0] + nArray[2] + 15;
            }
            if (n3 < 0) {
                n3 = 0;
            }
            if ((float)n3 + f2 > (float)minecraft.field_71462_r.field_146295_m) {
                n3 = minecraft.field_71462_r.field_146295_m - (int)f2;
            }
            a3.r(minecraft, n2, n3, f2, fk2);
        }
    }

    public static void r() {
        new id();
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void r(ItemTooltipEvent a2) {
        if (vk.u) {
            id a3;
            id id2 = a3;
            id2.m = a2.getToolTip();
            id2.j = a2.getItemStack();
        }
    }

    private /* synthetic */ boolean r(ItemStack a22, List<String> a3, int a4, int a5, int a6, int a7, FontRenderer a8) {
        if (!a3.isEmpty()) {
            int n2;
            int a22 = 0;
            for (String string : a3) {
                n2 = a8.func_78256_a(string);
                if (n2 <= a22) continue;
                a22 = n2;
            }
            a5 = a6 + 12;
            int n3 = a7 - 12;
            n2 = 8;
            if (a3.size() > 1) {
                n2 += 2 + (a3.size() - 1) * 10;
            }
            if (a5 + a22 > a4) {
                return true;
            }
        }
        return false;
    }

    private /* synthetic */ void r(Minecraft a2, int a3, int a4, float a5, fk a6) {
        fk fk2 = a6;
        a2 = fk2.r();
        if (fk2.r() != null) {
            if (vk.a) {
                RenderHelper.func_74518_a();
                ql.r("skin-preview.png");
                xd.z();
                GuiUtils.drawContinuousTexturedBox((int)a3, (int)a4, (int)0, (int)0, (int)((int)a5), (int)((int)a5), (int)62, (int)62, (int)4, (float)400.0f);
            }
            GL11.glColor4f((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GL11.glPushMatrix();
            GL11.glPushAttrib((int)1048575);
            GL11.glEnable((int)2977);
            RenderHelper.func_74520_c();
            GL11.glTranslatef((float)-10.0f, (float)-5.0f, (float)600.0f);
            GL11.glTranslatef((float)(a5 / 2.0f + (float)a3), (float)(a5 / 2.0f + (float)a4), (float)0.0f);
            GL11.glScalef((float)10.0f, (float)10.0f, (float)10.0f);
            GL11.glTranslatef((float)1.0f, (float)0.5f, (float)1.0f);
            GL11.glScalef((float)1.0f, (float)-1.0f, (float)1.0f);
            GL11.glRotatef((float)210.0f, (float)1.0f, (float)0.0f, (float)0.0f);
            GL11.glRotatef((float)45.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            float f2 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
            GL11.glRotatef((float)f2, (float)0.0f, (float)1.0f, (float)0.0f);
            ih.r(a6, true, false, (float)((int)a5), (float)((int)a5));
            GL11.glPopAttrib();
            GL11.glPopMatrix();
            RenderHelper.func_74518_a();
            return;
        }
        if (a2 != null) {
            if (vk.a) {
                RenderHelper.func_74518_a();
                ql.r("skin-preview.png");
                xd.z();
                GuiUtils.drawContinuousTexturedBox((int)a3, (int)a4, (int)0, (int)0, (int)((int)a5), (int)((int)a5), (int)62, (int)62, (int)4, (float)400.0f);
            }
            if (((ModelData)a2).getTransformBakedModel() != null) {
                Pair<? extends IBakedModel, Matrix4f> pair = ((ModelData)a2).getTransformBakedModel().handlePerspective(ItemCameraTransforms.TransformType.GUI);
                if (pair.getRight() != null) {
                    GlStateManager.func_179094_E();
                    GlStateManager.func_179109_b((float)a3, (float)a4, (float)600.0f);
                    GlStateManager.func_179109_b((float)(a5 / 2.0f), (float)(a5 / 2.0f), (float)0.0f);
                    GlStateManager.func_179152_a((float)1.0f, (float)-1.0f, (float)1.0f);
                    GlStateManager.func_179139_a((double)((double)a5 * 0.8), (double)((double)a5 * 0.8), (double)((double)a5 * 0.8));
                    ForgeHooksClient.multiplyCurrentGlMatrix((Matrix4f)((Matrix4f)pair.getRight()));
                    GlStateManager.func_179152_a((float)-1.0f, (float)-1.0f, (float)1.0f);
                    GL11.glRotatef((float)((float)((double)System.currentTimeMillis() / 10.0 % 360.0)), (float)0.0f, (float)1.0f, (float)0.0f);
                    tg.r((ModelData)a2, 0.0625f);
                    GlStateManager.func_179121_F();
                }
                return;
            }
            GlStateManager.func_179094_E();
            GL11.glTranslatef((float)-10.0f, (float)-5.0f, (float)600.0f);
            GL11.glTranslatef((float)(a5 / 2.0f + (float)a3), (float)(a5 / 2.0f + (float)a4), (float)0.0f);
            GlStateManager.func_179109_b((float)10.0f, (float)38.0f, (float)0.0f);
            GlStateManager.func_179152_a((float)20.0f, (float)20.0f, (float)20.0f);
            float f3 = (float)((double)System.currentTimeMillis() / 10.0 % 360.0);
            GL11.glRotatef((float)f3, (float)0.0f, (float)1.0f, (float)0.0f);
            GL11.glPushAttrib((int)1048575);
            GL11.glEnable((int)2977);
            GlStateManager.func_179129_p();
            tg.r((ModelData)a2, 0.0625f);
            GL11.glPopAttrib();
            GlStateManager.func_179121_F();
        }
    }

    public id() {
        id a2;
        MinecraftForge.EVENT_BUS.register((Object)a2);
    }

    @SubscribeEvent
    public void r(GuiScreenEvent.DrawScreenEvent.Pre a2) {
        List<String> list;
        boolean bl;
        id a3;
        if (!vk.u) {
            return;
        }
        if (vk.y) {
            return;
        }
        id id2 = a3;
        a2 = fk.r(id2.j);
        List<String> list2 = id2.m;
        id2.m = null;
        id2.j = null;
        Minecraft minecraft = Minecraft.func_71410_x();
        if (minecraft.field_71462_r == null) {
            return;
        }
        if (a2 != null) {
            bl = true;
            list = list2;
        } else {
            bl = false;
            list = list2;
        }
        if (bl & list != null) {
            ScaledResolution scaledResolution;
            float f2 = vk.s;
            float f3 = vk.oa;
            float f4 = vk.v;
            ScaledResolution scaledResolution2 = scaledResolution = new ScaledResolution(minecraft);
            double d2 = scaledResolution2.func_78327_c() - (double)f2;
            double d3 = scaledResolution2.func_78324_d() - (double)f2;
            int n2 = MathHelper.func_76143_f((double)(d2 * (double)f3));
            int n3 = MathHelper.func_76143_f((double)(d3 * (double)f4));
            a3.r(minecraft, n2, n3, f2, (fk)a2);
            a2 = null;
        }
    }
}

