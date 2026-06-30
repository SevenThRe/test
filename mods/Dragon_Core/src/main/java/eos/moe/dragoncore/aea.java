/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.RenderItem
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.block.model.IBakedModel
 *  net.minecraft.client.renderer.block.model.ItemCameraTransforms$TransformType
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.client.renderer.texture.TextureMap
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.item.Item
 *  net.minecraft.item.ItemStack
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.client.ForgeHooksClient
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 */
package eos.moe.dragoncore;

import eos.moe.dragoncore.aka;
import eos.moe.dragoncore.cea;
import eos.moe.dragoncore.faa;
import eos.moe.dragoncore.oja;
import eos.moe.dragoncore.pa;
import eos.moe.dragoncore.pm;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.ww;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

/*
 * Duplicate member names - consider using --renamedupmembers true
 */
public class aea {
    public static aea b;
    private final Minecraft o = Minecraft.getMinecraft();
    private final RenderManager y = a2.o.getRenderManager();
    private final RenderItem k = Minecraft.getMinecraft().getRenderItem();
    private final Random ALLATORIxDEMO = new Random();

    public aea() {
        aea a2;
        b = a2;
    }

    @SubscribeEvent
    public void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        aea a3;
        if (a3.y.renderViewEntity == null) {
            return;
        }
        GlStateManager.pushMatrix();
        for (oja a4 : aka.k.ALLATORIxDEMO().values()) {
            if (!"*".equalsIgnoreCase(a4.ALLATORIxDEMO()) && !wka.y.equals(a4.ALLATORIxDEMO())) continue;
            a3.ALLATORIxDEMO(a4, a2.getPartialTicks());
        }
        GlStateManager.popMatrix();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
    }

    private /* synthetic */ void ALLATORIxDEMO(oja a2, float a3) {
        Vec3d a4;
        aea a5;
        Entity a6 = pm.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        if (a2.ALLATORIxDEMO() != null && a6 == null) {
            return;
        }
        Vec3d a7 = a5.y.renderViewEntity.getPositionVector();
        double a8 = a7.distanceTo(a4 = a2.c());
        if (a8 > a2.ALLATORIxDEMO() && a6 == null) {
            return;
        }
        if (!a2.f() && Minecraft.getMinecraft().gameSettings.thirdPersonView == 0) {
            return;
        }
        GlStateManager.pushMatrix();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        if (a2.c()) {
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        }
        if (a2.ALLATORIxDEMO() != null) {
            Vec3d a9 = a2.ALLATORIxDEMO();
            double a10 = a6.lastTickPosX + (a6.posX - a6.lastTickPosX) * (double)a3;
            double a11 = a6.lastTickPosY + (a6.posY - a6.lastTickPosY) * (double)a3;
            double a12 = a6.lastTickPosZ + (a6.posZ - a6.lastTickPosZ) * (double)a3;
            float a13 = 0.0f;
            if (a6 instanceof EntityLivingBase) {
                EntityLivingBase a14 = (EntityLivingBase)a6;
                a13 = a5.ALLATORIxDEMO(a14.prevRenderYawOffset, a14.renderYawOffset, a3);
            }
            double a15 = Math.toRadians(a13 + 90.0f);
            double a16 = Math.toRadians(90.0);
            double a17 = Math.sin(a16) * Math.cos(a15);
            double a18 = Math.sin(a16) * Math.sin(a15);
            a15 = a9.x < 0.0 ? Math.toRadians(a13) : Math.toRadians(a13 + 180.0f);
            double a19 = Math.abs(a9.x) * Math.sin(a16) * Math.cos(a15) + a9.z * a17;
            double a20 = a9.y;
            double a21 = Math.abs(a9.x) * Math.sin(a16) * Math.sin(a15) + a9.z * a18;
            a9 = new Vec3d(a10 - Particle.interpPosX, a11 - Particle.interpPosY, a12 - Particle.interpPosZ);
            GlStateManager.translate((double)a9.x, (double)a9.y, (double)a9.z);
            GlStateManager.translate((double)a19, (double)a20, (double)a21);
            GlStateManager.translate((double)a2.c().x, (double)a2.c().y, (double)a2.c().z);
        } else {
            double a22 = a4.x - a5.y.viewerPosX;
            double a23 = a4.y - a5.y.viewerPosY;
            double a24 = a4.z - a5.y.viewerPosZ;
            GlStateManager.translate((double)a22, (double)a23, (double)a24);
        }
        if (a2.x()) {
            GlStateManager.rotate((float)(-a5.y.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)a5.y.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
        } else if (a2.d()) {
            float a25 = 0.0f;
            if (a6 instanceof EntityLivingBase) {
                EntityLivingBase a26 = (EntityLivingBase)a6;
                a25 = a5.ALLATORIxDEMO(a26.prevRenderYawOffset, a26.renderYawOffset, a3);
            }
            GlStateManager.rotate((float)(-a25), (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
        }
        GlStateManager.rotate((float)a2.f(), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)a2.x(), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.rotate((float)a2.c(), (float)0.0f, (float)0.0f, (float)1.0f);
        GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        long a27 = System.currentTimeMillis();
        cea.r = 1.0f;
        for (pa a28 : a2.ALLATORIxDEMO()) {
            a28.ALLATORIxDEMO(a27, a6, a3);
        }
        GlStateManager.enableBlend();
        if (a2.ALLATORIxDEMO()) {
            GlStateManager.disableDepth();
            GlStateManager.depthMask((boolean)false);
        }
        if (a2.ALLATORIxDEMO() != null) {
            GlStateManager.translate((float)0.0f, (float)-0.09375f, (float)0.0f);
            GlStateManager.scale((float)a2.d(), (float)a2.d(), (float)a2.d());
            GlStateManager.translate((float)0.0f, (float)0.09375f, (float)0.0f);
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)1.0f, (float)0.0f);
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)cea.r);
            a5.ALLATORIxDEMO(a2.ALLATORIxDEMO());
        } else if (a2.c().startsWith("[text]")) {
            Object a23 = a2.c().substring(6).replace("&", "\u00a7");
            a23 = faa.ALLATORIxDEMO((String)a23);
            float a29 = a2.d() / 9.0f;
            float a30 = a5.o.fontRenderer.getStringWidth((String)a23);
            GlStateManager.translate((float)(-(a30 *= a29) / 2.0f), (float)(-a2.d() / 2.0f), (float)0.0f);
            GlStateManager.scale((float)a29, (float)a29, (float)a29);
            int a31 = 0xFFFFFF | (int)(cea.r * 255.0f) << 24;
            a5.o.fontRenderer.drawString((String)a23, 0.0f, 0.0f, a31, false);
        } else {
            ww.ALLATORIxDEMO(a2.c());
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)(a2.ALLATORIxDEMO() * cea.r));
            aea.ALLATORIxDEMO(0, 0, 0.0f, 0.0f, a2.k(), a2.d(), a2.k(), a2.d());
        }
        if (a2.ALLATORIxDEMO()) {
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
        }
        GlStateManager.disableBlend();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public void ALLATORIxDEMO(ItemStack a2) {
        aea a3;
        int a4 = a2.isEmpty() ? 187 : Item.getIdFromItem((Item)a2.getItem()) + a2.getMetadata();
        a3.ALLATORIxDEMO.setSeed(a4);
        boolean a5 = false;
        if (a3.ALLATORIxDEMO()) {
            a3.y.renderEngine.getTexture(a3.ALLATORIxDEMO()).setBlurMipmap(false, false);
            a5 = true;
        }
        GlStateManager.enableRescaleNormal();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
        GlStateManager.enableBlend();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.pushMatrix();
        IBakedModel a6 = a3.k.getItemModelWithOverrides(a2, (World)FMLClientHandler.instance().getWorldClient(), null);
        GlStateManager.pushMatrix();
        IBakedModel a7 = ForgeHooksClient.handleCameraTransforms((IBakedModel)a6, (ItemCameraTransforms.TransformType)ItemCameraTransforms.TransformType.GROUND, (boolean)false);
        a3.k.renderItem(a2, a7);
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        GlStateManager.disableRescaleNormal();
        GlStateManager.disableBlend();
        a3.ALLATORIxDEMO();
        if (a5) {
            a3.y.renderEngine.getTexture(a3.ALLATORIxDEMO()).restoreLastBlurMipmap();
        }
    }

    public ResourceLocation ALLATORIxDEMO() {
        return TextureMap.LOCATION_BLOCKS_TEXTURE;
    }

    public boolean ALLATORIxDEMO() {
        aea a2;
        a2.y.renderEngine.bindTexture(TextureMap.LOCATION_BLOCKS_TEXTURE);
        return true;
    }

    public float ALLATORIxDEMO(float a2, float a3, float a4) {
        float a5;
        for (a5 = a3 - a2; a5 < -180.0f; a5 += 360.0f) {
        }
        while (a5 >= 180.0f) {
            a5 -= 360.0f;
        }
        return a2 + a4 * a5;
    }

    public static void ALLATORIxDEMO(int a2, int a3, float a4, float a5, float a6, float a7, float a8, float a9) {
        a8 = 1.0f / a8;
        a9 = 1.0f / a9;
        Tessellator a10 = Tessellator.getInstance();
        BufferBuilder a11 = a10.getBuffer();
        a11.begin(7, DefaultVertexFormats.POSITION_TEX);
        a11.pos((double)((float)a2 - a6 / 2.0f), (double)((float)a3 + a7 - a7 / 2.0f), 0.0).tex((double)(a4 * a8), (double)((a5 + a7) * a9)).endVertex();
        a11.pos((double)((float)a2 + a6 - a6 / 2.0f), (double)((float)a3 + a7 - a7 / 2.0f), 0.0).tex((double)((a4 + a6) * a8), (double)((a5 + a7) * a9)).endVertex();
        a11.pos((double)((float)a2 + a6 - a6 / 2.0f), (double)((float)a3 - a7 / 2.0f), 0.0).tex((double)((a4 + a6) * a8), (double)(a5 * a9)).endVertex();
        a11.pos((double)((float)a2 - a6 / 2.0f), (double)((float)a3 - a7 / 2.0f), 0.0).tex((double)(a4 * a8), (double)(a5 * a9)).endVertex();
        a10.draw();
    }

    private static /* synthetic */ void ALLATORIxDEMO(int a2, int a3, float a4, float a5, float a6, float a7) {
        a6 = 1.0f / a6;
        a7 = 1.0f / a7;
        Tessellator a8 = Tessellator.getInstance();
        BufferBuilder a9 = a8.getBuffer();
        a9.begin(7, DefaultVertexFormats.POSITION_TEX);
        a9.pos((double)a2, (double)((float)a3 + a7), 0.0).tex((double)(a4 * a6), (double)((a5 + a7) * a7)).endVertex();
        a9.pos((double)((float)a2 + a6), (double)((float)a3 + a7), 0.0).tex((double)((a4 + a6) * a6), (double)((a5 + a7) * a7)).endVertex();
        a9.pos((double)((float)a2 + a6), (double)a3, 0.0).tex((double)((a4 + a6) * a6), (double)(a5 * a7)).endVertex();
        a9.pos((double)a2, (double)a3, 0.0).tex((double)(a4 * a6), (double)(a5 * a7)).endVertex();
        a8.draw();
    }
}

