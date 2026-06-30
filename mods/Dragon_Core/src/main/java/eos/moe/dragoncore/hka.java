/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.entity.EntityOtherPlayerMP
 *  net.minecraft.client.entity.EntityPlayerSP
 *  net.minecraft.client.model.ModelBase
 *  net.minecraft.client.model.ModelRenderer
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$Profile
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.entity.RenderLivingBase
 *  net.minecraft.client.renderer.texture.ITickableTextureObject
 *  net.minecraft.client.renderer.texture.TextureManager
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.client.resources.IResource
 *  net.minecraft.client.resources.IResourceManager
 *  net.minecraft.client.shader.ShaderManager
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.entity.item.EntityItem
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.EntityViewRenderEvent$CameraSetup
 *  net.minecraftforge.client.event.RenderLivingEvent$Post
 *  net.minecraftforge.client.event.RenderLivingEvent$Pre
 *  net.minecraftforge.client.event.RenderLivingEvent$Specials$Pre
 *  net.minecraftforge.common.MinecraftForge
 *  net.minecraftforge.event.entity.EntityJoinWorldEvent
 *  net.minecraftforge.fml.common.eventhandler.Event
 *  net.minecraftforge.fml.common.eventhandler.EventPriority
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  org.apache.commons.lang3.math.NumberUtils
 */
package eos.moe.dragoncore;

import blockbuster.utils.Interpolations;
import eos.moe.dragoncore.api.model.AnimationEntityModel;
import eos.moe.dragoncore.api.model.AnimationModelRenderer;
import eos.moe.dragoncore.dga;
import eos.moe.dragoncore.dt;
import eos.moe.dragoncore.ega;
import eos.moe.dragoncore.event.EventLoader;
import eos.moe.dragoncore.gga;
import eos.moe.dragoncore.hq;
import eos.moe.dragoncore.nd;
import eos.moe.dragoncore.nha;
import eos.moe.dragoncore.nw;
import eos.moe.dragoncore.om;
import eos.moe.dragoncore.pw;
import eos.moe.dragoncore.qe;
import eos.moe.dragoncore.qw;
import eos.moe.dragoncore.raa;
import eos.moe.dragoncore.rda;
import eos.moe.dragoncore.rt;
import eos.moe.dragoncore.sj;
import eos.moe.dragoncore.tca;
import eos.moe.dragoncore.vw;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.ww;
import eos.moe.dragoncore.xz;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityOtherPlayerMP;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.texture.ITickableTextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.shader.ShaderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.EntityViewRenderEvent;
import net.minecraftforge.client.event.RenderLivingEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import org.apache.commons.lang3.math.NumberUtils;

public class hka
extends RenderLivingBase
implements Runnable {
    private static final Minecraft m = Minecraft.getMinecraft();
    public static boolean c = false;
    private static final ScheduledExecutorService q = Executors.newScheduledThreadPool(1);
    public static ShaderManager b;
    private boolean o = false;
    private final Map<UUID, Long> y = new HashMap<UUID, Long>();
    private boolean k;
    private Field ALLATORIxDEMO;

    public hka() {
        super(Minecraft.getMinecraft().getRenderManager(), null, 1.0f);
        hka a2;
        a2.addLayer(new rt(a2));
        q.scheduleAtFixedRate(() -> {
            hka a2;
            sj.ALLATORIxDEMO(a2);
        }, 30L, 30L, TimeUnit.MINUTES);
    }

    @Override
    public void run() {
        hka a3;
        a3.y.entrySet().removeIf(a2 -> (Long)a2.getValue() < System.currentTimeMillis());
    }

    private static /* synthetic */ void ALLATORIxDEMO(int a2, int a3, float a4, float a5, float a6, float a7, float a8, float a9) {
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

    public static void ALLATORIxDEMO(int a2, int a3, float a4, float a5, float a6, float a7, float a8, float a9, float a10, float a11) {
        a10 = 1.0f / a10;
        a11 = 1.0f / a11;
        Tessellator a12 = Tessellator.getInstance();
        BufferBuilder a13 = a12.getBuffer();
        a13.begin(7, DefaultVertexFormats.POSITION_TEX);
        a13.pos((double)((float)a2 - a8 / 2.0f), (double)((float)a3 + a7 - a9 / 2.0f), 0.0).tex((double)(a4 * a10), (double)((a5 + a7) * a11)).endVertex();
        a13.pos((double)((float)a2 + a6 - a8 / 2.0f), (double)((float)a3 + a7 - a9 / 2.0f), 0.0).tex((double)((a4 + a6) * a10), (double)((a5 + a7) * a11)).endVertex();
        a13.pos((double)((float)a2 + a6 - a8 / 2.0f), (double)((float)a3 - a9 / 2.0f), 0.0).tex((double)((a4 + a6) * a10), (double)(a5 * a11)).endVertex();
        a13.pos((double)((float)a2 - a8 / 2.0f), (double)((float)a3 - a9 / 2.0f), 0.0).tex((double)(a4 * a10), (double)(a5 * a11)).endVertex();
        a12.draw();
    }

    @SubscribeEvent
    public void onjoin(EntityJoinWorldEvent a2) {
        if (a2.getEntity() != null && !(a2.getEntity() instanceof EntityItem)) {
            nw.c(a2.getEntity().getUniqueID());
        }
    }

    @SubscribeEvent
    public void onServerFileEvent(EventLoader.ServerFileEvent a2) {
        if (a2.getFileName().startsWith("PlayerAnimation/")) {
            dga.y.ALLATORIxDEMO(a2.getFileName(), a2.getBytes());
        }
    }

    @SubscribeEvent
    public void onEntityViewRenderEventCameraSetup(EntityViewRenderEvent.CameraSetup a2) {
        hka a3;
        if (!wka.k) {
            return;
        }
        EntityPlayerSP a4 = Minecraft.getMinecraft().player;
        rda a5 = raa.r.c((EntityLivingBase)a4);
        if (a5 != null && a5.ALLATORIxDEMO() != 0.0) {
            a4.eyeHeight = (float)a5.ALLATORIxDEMO();
            a3.o = true;
        } else if (a3.o) {
            a4.eyeHeight = a4.getDefaultEyeHeight();
        }
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onRenderLivingEvent1(RenderLivingEvent.Pre a2) {
        hka a3;
        if (!wka.k) {
            return;
        }
        if (!(a2.getEntity() instanceof EntityOtherPlayerMP)) {
            return;
        }
        EntityOtherPlayerMP a4 = (EntityOtherPlayerMP)a2.getEntity();
        if (a4.getName().contains("\u00a7")) {
            return;
        }
        a3.drawEntityHealthBar((EntityLivingBase)a4, "player", a2.getX(), a2.getY(), a2.getZ());
    }

    @SubscribeEvent
    public void onRenderLivingEventSpecialsPre(RenderLivingEvent.Specials.Pre<?> a2) {
        if (!wka.k) {
            return;
        }
        if (a2.getEntity() == null) {
            return;
        }
        rda a3 = raa.r.c(a2.getEntity());
        if (a3 == null) {
            return;
        }
        if (a3.d() && a3.z()) {
            a2.setCanceled(true);
        }
    }

    @SubscribeEvent
    public void onEntityJoinWorldEvent(EntityJoinWorldEvent a2) {
        hka a3;
        a3.y.put(a2.getEntity().getUniqueID(), System.currentTimeMillis() + 55L);
    }

    @SubscribeEvent(priority=EventPriority.LOWEST)
    public void onRenderLivingEvent(RenderLivingEvent.Pre a2) {
        rda a3;
        hka a4;
        if (!wka.k) {
            return;
        }
        if (!(a2.getEntity() instanceof EntityLivingBase)) {
            return;
        }
        if (a2.isCanceled()) {
            return;
        }
        Long a5 = a4.y.get(a2.getEntity().getUniqueID());
        if (a5 != null) {
            if (a5 > System.currentTimeMillis() && (!a2.getEntity().hasCustomName() || a2.getEntity().getName().contains("Adyeshach"))) {
                a2.setCanceled(true);
            } else {
                a4.y.remove(a2.getEntity().getUniqueID());
            }
        }
        if ((a3 = raa.r.c(a2.getEntity())) == null) {
            return;
        }
        double a6 = a2.getX();
        double a7 = a2.getY();
        if (a2.getEntity() instanceof EntityPlayer && a2.getEntity().isSneaking()) {
            a7 += 0.125;
        }
        double a8 = a2.getZ();
        if (!a3.d() && !c) {
            boolean a9;
            float a10;
            Object a11;
            gga a12 = a3.ALLATORIxDEMO();
            if (a12 == null) {
                return;
            }
            ModelBase a13 = a12.ALLATORIxDEMO();
            if (a13 == null) {
                return;
            }
            EntityLivingBase a14 = a2.getEntity();
            a14.height = a3.c();
            a14.width = a3.ALLATORIxDEMO();
            if (!a3.ALLATORIxDEMO()) {
                return;
            }
            a2.setCanceled(true);
            String a15 = a14.getCustomNameTag();
            if (om.o && a15 != null && a15.contains("-") && a15.endsWith("\u7279\u6548") && !((String)(a11 = a15.substring(0, a15.indexOf("-")))).equals(hka.m.player.getName())) {
                return;
            }
            a11 = null;
            AnimationEntityModel a16 = null;
            if (!a3.c() && !a3.k()) {
                dt.k.init(a14, true);
                a11 = dt.k.getEntityManager(a14.getUniqueID());
                if (a11 != null && a13 instanceof AnimationEntityModel) {
                    a16 = (AnimationEntityModel)a13;
                    raa.x.ALLATORIxDEMO();
                    hka.ALLATORIxDEMO((Entity)a14, (xz)a11);
                }
            } else if (a3.c() && a13 instanceof hq) {
                hq a17 = (hq)a13;
                float a18 = 0.0f;
                a10 = 0.0f;
                if (a14.isEntityAlive()) {
                    boolean a19;
                    a18 = Interpolations.lerp(a14.prevLimbSwingAmount, a14.limbSwingAmount, a2.getPartialRenderTick());
                    a10 = a14.limbSwing - a14.limbSwingAmount * (1.0f - a2.getPartialRenderTick());
                    if (a14.isChild()) {
                        a10 *= 3.0f;
                    }
                    if (a18 > 1.0f) {
                        a18 = 1.0f;
                    }
                    boolean bl2 = a19 = !(a18 > -0.15f) || !(a18 < 0.15f);
                    if (a19) {
                        a17.setAnimation("walk");
                    } else {
                        a17.setAnimation("idle");
                    }
                }
            }
            float a20 = a2.getPartialRenderTick();
            GlStateManager.pushMatrix();
            GlStateManager.disableCull();
            a4.mainModel = a13;
            a4.mainModel.swingProgress = a4.getSwingProgress(a14, a20);
            a4.mainModel.isRiding = a9 = a14.isRiding() && a14.getRidingEntity() != null && a14.getRidingEntity().shouldRiderSit();
            a4.mainModel.isChild = a14.isChild();
            try {
                a10 = a4.interpolateRotation(a14.prevRenderYawOffset, a14.renderYawOffset, a20);
                float a21 = a4.interpolateRotation(a14.prevRotationYawHead, a14.rotationYawHead, a20);
                float a22 = a21 - a10;
                if (a9 && a14.getRidingEntity() instanceof EntityLivingBase) {
                    EntityLivingBase a23 = (EntityLivingBase)a14.getRidingEntity();
                    a10 = a4.interpolateRotation(a23.prevRenderYawOffset, a23.renderYawOffset, a20);
                    a22 = a21 - a10;
                    float a24 = MathHelper.wrapDegrees((float)a22);
                    if (a24 < -85.0f) {
                        a24 = -85.0f;
                    }
                    if (a24 >= 85.0f) {
                        a24 = 85.0f;
                    }
                    a10 = a21 - a24;
                    if (a24 * a24 > 2500.0f) {
                        a10 += a24 * 0.2f;
                    }
                    a22 = a21 - a10;
                }
                float a25 = a14.prevRotationPitch + (a14.rotationPitch - a14.prevRotationPitch) * a20;
                nd a26 = raa.x;
                a26.ALLATORIxDEMO("query.yaw", a22);
                if (a14.getTicksElytraFlying() > 4) {
                    a26.ALLATORIxDEMO("query.pitch", -45.0);
                } else {
                    a26.ALLATORIxDEMO("query.pitch", a25);
                }
                if (a11 != null && a16 != null) {
                    ((qw)a11).ALLATORIxDEMO(a16);
                    ((qw)a11).ALLATORIxDEMO(a16);
                    if (((xz)a11).b != null) {
                        for (Map.Entry<String, Boolean> a27 : ((xz)a11).b.entrySet()) {
                            AnimationModelRenderer a28 = a16.getBaseModel().getPiece(a27.getKey());
                            if (a28 == null || !(a28 instanceof ModelRenderer)) continue;
                            ((ModelRenderer)a28).showModel = a27.getValue();
                        }
                    }
                }
                a4.renderLivingAt(a14, a6, a7, a8);
                float a29 = a4.handleRotationFloat(a14, a20);
                if (!a3.k() && a3.f() != 0.0f) {
                    GlStateManager.scale((float)a3.f(), (float)a3.f(), (float)a3.f());
                }
                a4.applyRotations(a14, a29, a10, a20);
                float a30 = a4.prepareScale(a14, a20);
                float a31 = 0.0f;
                float a32 = 0.0f;
                if (!a14.isRiding()) {
                    a31 = a14.prevLimbSwingAmount + (a14.limbSwingAmount - a14.prevLimbSwingAmount) * a20;
                    a32 = a14.limbSwing - a14.limbSwingAmount * (1.0f - a20);
                    if (a14.isChild()) {
                        a32 *= 3.0f;
                    }
                    if (a31 > 1.0f) {
                        a31 = 1.0f;
                    }
                    a22 = a21 - a10;
                }
                GlStateManager.enableAlpha();
                a4.mainModel.setLivingAnimations(a14, a32, a31, a20);
                if (a4.renderOutlines) {
                    boolean a33 = a4.setScoreTeamColor(a14);
                    GlStateManager.enableColorMaterial();
                    GlStateManager.enableOutlineMode((int)a4.getTeamColor((Entity)a14));
                    if (!a4.renderMarker) {
                        a4.renderModel(a14, a32, a31, a29, a22, a25, a30);
                    }
                    a4.renderLayers(a14, a32, a31, a20, a29, a22, a25, a30);
                    if (!(a14 instanceof EntityPlayer) || !((EntityPlayer)a14).isSpectator()) {
                        a4.renderLayers(a14, a32, a31, a20, a29, a22, a25, a30);
                    }
                    GlStateManager.disableOutlineMode();
                    GlStateManager.disableColorMaterial();
                    if (a33) {
                        a4.unsetScoreTeamColor();
                    }
                } else {
                    boolean a34 = a4.setDoRenderBrightness(a14, a20, a3.s());
                    GlStateManager.pushMatrix();
                    a3.ALLATORIxDEMO();
                    a4.renderModel(a14, a32, a31, a29, a22, a25, a30);
                    GlStateManager.popMatrix();
                    if (a34) {
                        a4.unsetBrightness();
                    }
                    GlStateManager.depthMask((boolean)true);
                    if (!(a14 instanceof EntityPlayer) || !((EntityPlayer)a14).isSpectator()) {
                        a4.renderLayers(a14, a32, a31, a20, a29, a22, a25, a30);
                    }
                }
                GlStateManager.disableRescaleNormal();
            }
            catch (Exception a35) {
                a35.printStackTrace();
            }
            if (a4.mainModel instanceof pw) {
                ((pw)a4.mainModel).clearData();
            }
            GlStateManager.setActiveTexture((int)OpenGlHelper.lightmapTexUnit);
            GlStateManager.enableTexture2D();
            GlStateManager.setActiveTexture((int)OpenGlHelper.defaultTexUnit);
            GlStateManager.enableCull();
            GlStateManager.popMatrix();
            a4.drawEntityHealthBar(a14, a3.ALLATORIxDEMO(), a6, a7, a8);
            if (!a4.renderOutlines && !a3.z()) {
                a2.getRenderer().renderName(a14, a6, a7, a8);
            }
            MinecraftForge.EVENT_BUS.post((Event)new RenderLivingEvent.Post(a14, a2.getRenderer(), a20, a6, a7, a8));
        } else {
            a4.drawEntityHealthBar(a2.getEntity(), a3.ALLATORIxDEMO(), a6, a7, a8);
        }
    }

    public void renderModel(EntityLivingBase a2, float a3, float a4, float a5, float a6, float a7, float a8) {
        boolean a9;
        boolean a10;
        hka a11;
        if (a2.isDead) {
            return;
        }
        if (a11.ALLATORIxDEMO != null) {
            try {
                if (((Boolean)a11.ALLATORIxDEMO.get(null)).booleanValue()) {
                    return;
                }
            }
            catch (IllegalAccessException illegalAccessException) {}
        } else if (!a11.k) {
            try {
                a11.ALLATORIxDEMO = Class.forName("goblinbob.mobends.core.client.model.ModelPart").getDeclaredField("cancelRenderSkin");
            }
            catch (Throwable a12) {
                a11.k = true;
            }
        }
        boolean bl2 = a10 = !(a9 = a11.isVisible(a2)) && !a2.isInvisibleToPlayer((EntityPlayer)Minecraft.getMinecraft().player);
        if (a9 || a10) {
            String a13;
            rda a14 = raa.r.c(a2);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            xz a15 = dt.k.getEntityManager(a2.getUniqueID());
            String string = a13 = a15 != null ? a15.c() : null;
            if (a13 == null) {
                a11.bindTexture(a14.x());
            } else if (!a13.contains(",")) {
                a11.bindTexture(new ResourceLocation("dragoncore", "models/entities/" + a13));
            } else {
                String[] a16 = a13.split(",");
                a11.bindTexture(new ResourceLocation("dragoncore", "models/entities/" + a16[0]));
                if (a16.length == 5) {
                    GlStateManager.color((float)((float)NumberUtils.toInt((String)a16[1]) / 255.0f), (float)((float)NumberUtils.toInt((String)a16[2]) / 255.0f), (float)((float)NumberUtils.toInt((String)a16[3]) / 255.0f), (float)((float)NumberUtils.toInt((String)a16[4]) / 255.0f));
                }
            }
            if (a10) {
                GlStateManager.enableBlendProfile((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
            a11.mainModel.render((Entity)a2, a3, a4, a5, a6, a7, a8);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            a13 = a15 != null ? a15.ALLATORIxDEMO() : null;
            boolean a17 = false;
            if (a13 == null) {
                if (a14.ALLATORIxDEMO() != null) {
                    a17 = true;
                    a11.bindTexture(a14.ALLATORIxDEMO());
                }
            } else if (!a13.contains(",")) {
                a17 = true;
                a11.bindTexture(new ResourceLocation("dragoncore", "models/entities/" + a13));
            } else {
                a17 = true;
                String[] a18 = a13.split(",");
                a11.bindTexture(new ResourceLocation("dragoncore", "models/entities/" + a18[0]));
                if (a18.length == 5) {
                    GlStateManager.color((float)((float)NumberUtils.toInt((String)a18[1]) / 255.0f), (float)((float)NumberUtils.toInt((String)a18[2]) / 255.0f), (float)((float)NumberUtils.toInt((String)a18[3]) / 255.0f), (float)((float)NumberUtils.toInt((String)a18[4]) / 255.0f));
                }
            }
            if (a17) {
                float a19 = OpenGlHelper.lastBrightnessX;
                float a20 = OpenGlHelper.lastBrightnessY;
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)a20);
                a11.mainModel.render((Entity)a2, a3, a4, a5, a6, a7, a8);
                OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)a19, (float)a20);
            }
            if (a10) {
                GlStateManager.disableBlendProfile((GlStateManager.Profile)GlStateManager.Profile.TRANSPARENT_MODEL);
            }
        }
    }

    public void bindTexture(ResourceLocation a2) {
        if (!hka.ALLATORIxDEMO(a2)) {
            hka a3;
            super.bindTexture(a2);
        }
    }

    public static boolean ALLATORIxDEMO(ResourceLocation a2) {
        if (a2.getNamespace().equals("dragoncore")) {
            IResourceManager a4 = Minecraft.getMinecraft().getResourceManager();
            TextureManager a5 = Minecraft.getMinecraft().getTextureManager();
            if (a5.getTexture(a2) == null) {
                try (IResource a6 = a4.getResource(a2);){
                    if (a6.hasMetadata()) {
                        vw a7 = new vw("");
                        a7.loadSprites(a4, a3 -> a3.registerSprite(a2));
                        a5.loadTickableTexture(a2, (ITickableTextureObject)a7);
                    }
                }
                catch (IOException iOException) {
                    // empty catch block
                }
            }
            a5.bindTexture(a2);
            return true;
        }
        return false;
    }

    public void drawEntityHealthBar(EntityLivingBase a2, String a3, double a4, double a5, double a6) {
        hka a7;
        if (a3 == null || !ega.k.ALLATORIxDEMO().containsKey(a3)) {
            return;
        }
        nha a8 = ega.k.ALLATORIxDEMO().get(a3);
        float a9 = a7.renderManager.playerViewY;
        float a10 = a7.renderManager.playerViewX;
        boolean a11 = a7.renderManager.options.thirdPersonView == 2;
        GlStateManager.pushMatrix();
        GlStateManager.translate((double)a4, (double)(a5 + a8.ALLATORIxDEMO(a2)), (double)a6);
        GlStateManager.glNormal3f((float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)(-a9), (float)0.0f, (float)1.0f, (float)0.0f);
        GlStateManager.rotate((float)((float)(a11 ? -1 : 1) * a10), (float)1.0f, (float)0.0f, (float)0.0f);
        GlStateManager.scale((float)-0.025f, (float)-0.025f, (float)0.025f);
        GlStateManager.disableLighting();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, (GlStateManager.SourceFactor)GlStateManager.SourceFactor.ONE, (GlStateManager.DestFactor)GlStateManager.DestFactor.ZERO);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ww.ALLATORIxDEMO(a8.c());
        float a12 = a8.x(a2.getHealth(), a2.getMaxHealth());
        float a13 = a8.f(a2.getHealth(), a2.getMaxHealth());
        hka.ALLATORIxDEMO(0, 0, 0.0f, 0.0f, a12, a13, a12, a13);
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        ww.ALLATORIxDEMO(a8.ALLATORIxDEMO());
        GlStateManager.translate((float)0.0f, (float)0.0f, (float)-0.1f);
        GlStateManager.scale((double)1.01, (double)1.01, (double)1.01);
        float a14 = a8.c(a2.getHealth(), a2.getMaxHealth());
        float a15 = a8.ALLATORIxDEMO(a2.getHealth(), a2.getMaxHealth());
        hka.ALLATORIxDEMO(0, 0, 0.0f, 0.0f, a14, a15, a12, a13, a12, a13);
        GlStateManager.translate((float)(-a12 / 2.0f), (float)(-a13 / 2.0f), (float)-0.1f);
        for (tca a16 : a8.ALLATORIxDEMO()) {
            GlStateManager.pushMatrix();
            GlStateManager.scale((float)a16.ALLATORIxDEMO(), (float)a16.ALLATORIxDEMO(), (float)a16.ALLATORIxDEMO());
            String a17 = a16.ALLATORIxDEMO(a2.getHealth(), a2.getMaxHealth(), a2.getDisplayName().getFormattedText());
            float a18 = a16.c(a2.getHealth(), a2.getMaxHealth());
            float a19 = a16.ALLATORIxDEMO(a2.getHealth(), a2.getMaxHealth());
            if (a16.ALLATORIxDEMO()) {
                a18 -= (float)hka.m.fontRenderer.getStringWidth(a17) * a16.ALLATORIxDEMO() / 2.0f;
            }
            hka.m.fontRenderer.drawString(a17, a18, a19, -16777216, false);
            GlStateManager.popMatrix();
        }
        GlStateManager.enableLighting();
        GlStateManager.disableBlend();
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.popMatrix();
    }

    public static void ALLATORIxDEMO(Entity a2, xz a3) {
        nd a4 = raa.x;
        Minecraft a5 = Minecraft.getMinecraft();
        float a6 = a5.getRenderPartialTicks();
        if (a3.q != null) {
            for (Map.Entry<String, Double> entry : a3.q.entrySet()) {
                a4.ALLATORIxDEMO("query." + entry.getKey(), entry.getValue());
            }
        }
        a4.ALLATORIxDEMO("query.actor_count", (double)a5.world.loadedEntityList.size());
        a4.ALLATORIxDEMO("query.time_of_day", qe.ALLATORIxDEMO(a5.world.getTotalWorldTime()));
        a4.ALLATORIxDEMO("query.moon_phase", (double)a5.world.getMoonPhase());
        if (a2 != null) {
            Entity a8 = a5.getRenderViewEntity();
            if (a8 == null) {
                return;
            }
            Vec3d vec3d = new Vec3d(a8.prevPosX + (a8.posX - a8.prevPosX) * (double)a6, a8.prevPosY + (a8.posY - a8.prevPosY) * (double)a6, a8.prevPosZ + (a8.posZ - a8.prevPosZ) * (double)a6);
            Vec3d a9 = new Vec3d(a2.prevPosX + (a2.posX - a2.prevPosX) * (double)a6, a2.prevPosY + (a2.posY - a2.prevPosY) * (double)a6, a2.prevPosZ + (a2.posZ - a2.prevPosZ) * (double)a6);
            double a10 = vec3d.add(ActiveRenderInfo.getCameraPosition()).distanceTo(a9);
            a4.ALLATORIxDEMO("query.distance_from_camera", a10);
            a4.ALLATORIxDEMO("query.is_on_ground", qe.ALLATORIxDEMO(a2.onGround));
            a4.ALLATORIxDEMO("query.is_in_water", qe.ALLATORIxDEMO(a2.isInWater()));
            a4.ALLATORIxDEMO("query.is_in_water_or_rain", qe.ALLATORIxDEMO(a2.isWet()));
            if (a2 instanceof EntityLivingBase) {
                EntityLivingBase a11 = (EntityLivingBase)a2;
                a4.ALLATORIxDEMO("query.health", a11.getHealth());
                a4.ALLATORIxDEMO("query.max_health", a11.getMaxHealth());
                a4.ALLATORIxDEMO("query.is_on_fire", qe.ALLATORIxDEMO(a11.isBurning()));
                double a12 = a11.motionX;
                double a13 = a11.motionZ;
                float a14 = MathHelper.sqrt((double)(a12 * a12 + a13 * a13));
                a4.ALLATORIxDEMO("query.ground_speed", a14);
                float a15 = hka.ALLATORIxDEMO(a11, Minecraft.getMinecraft().getRenderPartialTicks()) - hka.ALLATORIxDEMO(a11, (float)((double)Minecraft.getMinecraft().getRenderPartialTicks() - 0.1));
                a4.ALLATORIxDEMO("query.yaw_speed", a15);
            }
        }
    }

    private static /* synthetic */ float ALLATORIxDEMO(EntityLivingBase a2, float a3) {
        return a2.prevRotationYaw + (a2.rotationYaw - a2.prevRotationYaw) * a3;
    }

    public ResourceLocation getEntityTexture(Entity a2) {
        rda a3 = raa.r.c((EntityLivingBase)a2);
        if (a3 == null) {
            return new ResourceLocation("dragoncore", "empty.png");
        }
        return a3.x();
    }

    public float prepareScale(EntityLivingBase a2, float a3) {
        hka a4;
        rda a5 = raa.r.c(a2);
        if (a5 != null && a5.k()) {
            GlStateManager.enableRescaleNormal();
            GlStateManager.scale((float)a5.f(), (float)a5.f(), (float)a5.f());
            GlStateManager.rotate((float)180.0f, (float)0.0f, (float)((float)a2.posY), (float)0.0f);
            a4.preRenderCallback(a2, a3);
            return 0.0625f;
        }
        return super.prepareScale(a2, a3);
    }

    public boolean setDoRenderBrightness(EntityLivingBase a2, float a3, boolean a4) {
        hka a5;
        int a6 = a2.deathTime;
        int a7 = a2.hurtTime;
        if (!a4) {
            a2.deathTime = 0;
            a2.hurtTime = 0;
        }
        boolean a8 = super.setBrightness(a2, a3, true);
        if (!a4) {
            a2.deathTime = a6;
            a2.hurtTime = a7;
        }
        return a8;
    }
}

