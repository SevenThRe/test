/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.teamderpy.shouldersurfing.config.Config
 *  com.teamderpy.shouldersurfing.util.ShoulderState
 *  com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.particle.Particle
 *  net.minecraft.client.renderer.ActiveRenderInfo
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraftforge.client.event.RenderWorldLastEvent
 *  net.minecraftforge.fml.common.Mod$EventBusSubscriber
 *  net.minecraftforge.fml.common.eventhandler.SubscribeEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$ClientTickEvent
 *  net.minecraftforge.fml.common.gameevent.TickEvent$Phase
 */
package eos.moe.dragoncore;

import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import com.teamderpy.shouldersurfing.util.ShoulderSurfingHelper;
import eos.moe.dragoncore.bca;
import eos.moe.dragoncore.wka;
import eos.moe.dragoncore.ww;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

@Mod.EventBusSubscriber(modid="dragoncore")
public class nfa {
    public static List<Particle> ALLATORIxDEMO = new ArrayList<Particle>();

    public nfa() {
        nfa a2;
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(RenderWorldLastEvent a2) {
        if (Minecraft.getMinecraft().getRenderViewEntity() == null) {
            return;
        }
        if (!wka.k) {
            return;
        }
        if (ALLATORIxDEMO.isEmpty()) {
            return;
        }
        Entity a3 = Minecraft.getMinecraft().getRenderViewEntity();
        float a4 = a2.getPartialTicks();
        float a5 = ActiveRenderInfo.getRotationX();
        float a6 = ActiveRenderInfo.getRotationZ();
        float a7 = ActiveRenderInfo.getRotationYZ();
        float a8 = ActiveRenderInfo.getRotationXY();
        float a9 = ActiveRenderInfo.getRotationXZ();
        Particle.interpPosX = a3.lastTickPosX + (a3.posX - a3.lastTickPosX) * (double)a4;
        Particle.interpPosY = a3.lastTickPosY + (a3.posY - a3.lastTickPosY) * (double)a4;
        Particle.interpPosZ = a3.lastTickPosZ + (a3.posZ - a3.lastTickPosZ) * (double)a4;
        Particle.cameraViewDir = a3.getLook(a4);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.depthMask((boolean)false);
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc((int)516, (float)0.003921569f);
        OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
        ww.ALLATORIxDEMO("damagepic11.png");
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        Tessellator a10 = Tessellator.getInstance();
        BufferBuilder a11 = a10.getBuffer();
        a11.begin(7, DefaultVertexFormats.PARTICLE_POSITION_TEX_COLOR_LMAP);
        for (Particle a12 : ALLATORIxDEMO) {
            a12.renderParticle(a11, a3, a4, a5, a9, a6, a7, a8);
        }
        a10.draw();
        GlStateManager.depthMask((boolean)true);
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
    }

    @SubscribeEvent
    public static void ALLATORIxDEMO(TickEvent.ClientTickEvent a2) {
        if (a2.phase == TickEvent.Phase.START) {
            if (ALLATORIxDEMO.isEmpty()) {
                return;
            }
            Iterator<Particle> a3 = ALLATORIxDEMO.iterator();
            while (a3.hasNext()) {
                Particle a4 = a3.next();
                a4.onUpdate();
                if (a4.isAlive()) continue;
                a3.remove();
            }
        }
    }

    public static Vec3d ALLATORIxDEMO(float a2, float a3) {
        float a4 = MathHelper.cos((float)(-a3 * ((float)Math.PI / 180) - (float)Math.PI));
        float a5 = MathHelper.sin((float)(-a3 * ((float)Math.PI / 180) - (float)Math.PI));
        float a6 = -MathHelper.cos((float)(-a2 * ((float)Math.PI / 180)));
        float a7 = MathHelper.sin((float)(-a2 * ((float)Math.PI / 180)));
        return new Vec3d((double)(a5 * a6), (double)a7, (double)(a4 * a6));
    }

    public static Vec3d c(Entity a2) {
        Entity a3 = Minecraft.getMinecraft().getRenderManager().renderViewEntity;
        double a4 = a3.getDistance(a2) + 5.0f;
        Vec3d a5 = a3.getPositionEyes(1.0f);
        Vec3d a6 = nfa.ALLATORIxDEMO(a3.rotationPitch, a3.rotationYaw);
        Map.Entry<Vec3d, Vec3d> a7 = nfa.ALLATORIxDEMO(a6, a4);
        AxisAlignedBB a8 = a2.getEntityBoundingBox().grow((double)a2.getCollisionBorderSize());
        RayTraceResult a9 = a8.calculateIntercept(a7.getKey(), a7.getValue());
        if (a8.contains(a5)) {
            return a9 == null ? a5 : a9.hitVec;
        }
        if (a9 != null) {
            return a9.hitVec;
        }
        return null;
    }

    public static Vec3d ALLATORIxDEMO(Entity a2) {
        Entity a3 = Minecraft.getMinecraft().getRenderManager().renderViewEntity;
        Vec3d a4 = new Vec3d(a2.posX - a3.posX, 0.0, a2.posZ - a3.posZ);
        float a5 = bca.ALLATORIxDEMO((Vec3d)a4.normalize()).y;
        double a6 = a3.getDistance(a2) + 5.0f;
        Vec3d a7 = a3.getPositionEyes(1.0f);
        Vec3d a8 = nfa.ALLATORIxDEMO(a3.rotationPitch, a5);
        Map.Entry<Vec3d, Vec3d> a9 = nfa.ALLATORIxDEMO(a8, a6);
        AxisAlignedBB a10 = a2.getEntityBoundingBox().grow((double)a2.getCollisionBorderSize());
        RayTraceResult a11 = a10.calculateIntercept(a9.getKey(), a9.getValue());
        if (a10.contains(a7)) {
            return a11 == null ? a7 : a11.hitVec;
        }
        if (a11 != null) {
            return a11.hitVec;
        }
        return null;
    }

    public static Map.Entry<Vec3d, Vec3d> ALLATORIxDEMO(Vec3d a2, double a3) {
        if (ShoulderState.doShoulderSurfing() && !Config.CLIENT.getCrosshairType().isHoldingSpecialItem()) {
            return ShoulderSurfingHelper.shoulderSurfingLook((Entity)Minecraft.getMinecraft().getRenderViewEntity(), (float)Minecraft.getMinecraft().getRenderPartialTicks(), (double)(a3 * a3));
        }
        Entity a4 = Minecraft.getMinecraft().getRenderViewEntity();
        Vec3d a5 = a4.getPositionEyes(Minecraft.getMinecraft().getRenderPartialTicks());
        Vec3d a6 = a5.add(a2.x * a3, a2.y * a3, a2.z * a3);
        return new AbstractMap.SimpleEntry<Vec3d, Vec3d>(a5, a6);
    }
}

