/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Predicate
 *  com.google.common.base.Predicates
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.PlayerControllerMP
 *  net.minecraft.client.renderer.GLAllocation
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.item.EntityArmorStand
 *  net.minecraft.entity.monster.EntityVex
 *  net.minecraft.util.EntitySelectors
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.RayTraceResult
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.world.World
 *  net.minecraftforge.fml.relauncher.Side
 *  net.minecraftforge.fml.relauncher.SideOnly
 *  net.optifine.shaders.Shaders
 *  org.lwjgl.opengl.GL11
 *  org.lwjgl.util.glu.GLU
 *  shadersmod.client.Shaders
 */
package com.teamderpy.shouldersurfing.util;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.teamderpy.shouldersurfing.config.Config;
import com.teamderpy.shouldersurfing.config.Perspective;
import com.teamderpy.shouldersurfing.math.Vec2f;
import com.teamderpy.shouldersurfing.util.ShoulderState;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.entity.monster.EntityVex;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.optifine.shaders.Shaders;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;

@SideOnly(value=Side.CLIENT)
public class ShoulderSurfingHelper {
    private static final ResourceLocation PULL_PROPERTY = new ResourceLocation("pull");
    private static final ResourceLocation THROWING_PROPERTY = new ResourceLocation("throwing");
    private static final ResourceLocation CHARGED_PROPERTY = new ResourceLocation("charged");

    @Nullable
    public static Vec2f project2D(Vec3d position) {
        FloatBuffer screen = GLAllocation.createDirectFloatBuffer((int)3);
        IntBuffer viewport = GLAllocation.createDirectIntBuffer((int)16);
        FloatBuffer modelview = GLAllocation.createDirectFloatBuffer((int)16);
        FloatBuffer projection = GLAllocation.createDirectFloatBuffer((int)16);
        screen.clear();
        modelview.clear();
        projection.clear();
        viewport.clear();
        GL11.glGetFloat((int)2982, (FloatBuffer)modelview);
        GL11.glGetFloat((int)2983, (FloatBuffer)projection);
        GL11.glGetInteger((int)2978, (IntBuffer)viewport);
        if (GLU.gluProject((float)((float)position.x), (float)((float)position.y), (float)((float)position.z), (FloatBuffer)modelview, (FloatBuffer)projection, (IntBuffer)viewport, (FloatBuffer)screen)) {
            return new Vec2f(screen.get(0), screen.get(1));
        }
        return null;
    }

    public static double cameraDistance(World world, double distance, float yaw, float pitch) {
        Vec3d view = Minecraft.getMinecraft().getRenderViewEntity().getPositionEyes(Minecraft.getMinecraft().getRenderPartialTicks());
        Vec3d cameraOffset = ShoulderSurfingHelper.cameraOffset(distance, yaw, pitch);
        for (int i = 0; i < 8; ++i) {
            double newDistance;
            Vec3d camera;
            Vec3d offset = new Vec3d((double)(i & 1), (double)(i >> 1 & 1), (double)(i >> 2 & 1)).scale(2.0).subtract(1.0, 1.0, 1.0).scale(0.1);
            Vec3d head = view.add(offset);
            RayTraceResult result = world.rayTraceBlocks(head, camera = head.add(cameraOffset), false, true, false);
            if (result == null || !((newDistance = result.hitVec.distanceTo(view)) < distance)) continue;
            distance = newDistance;
        }
        return distance;
    }

    public static RayTraceResult traceFromEyes(Entity renderView, PlayerControllerMP playerController, double playerReachOverride, float partialTicks) {
        double blockReach = Math.max((double)playerController.getBlockReachDistance(), playerReachOverride);
        RayTraceResult rayTrace = renderView.rayTrace(blockReach, partialTicks);
        Vec3d eyes = renderView.getPositionEyes(partialTicks);
        double entityReach = blockReach;
        if (playerController.extendedReach()) {
            entityReach = blockReach = Math.max(6.0, playerReachOverride);
        }
        if (rayTrace != null) {
            entityReach = rayTrace.hitVec.distanceTo(eyes);
        }
        Vec3d look = renderView.getLook(1.0f);
        Vec3d end = eyes.add(look.x * blockReach, look.y * blockReach, look.z * blockReach);
        Vec3d entityHitVec = null;
        List list = Minecraft.getMinecraft().world.getEntitiesInAABBexcluding(renderView, renderView.getEntityBoundingBox().expand(look.x * blockReach, look.y * blockReach, look.z * blockReach).grow(1.0, 1.0, 1.0), Predicates.and((Predicate)EntitySelectors.NOT_SPECTATING, e -> e != null && e.canBeCollidedWith()));
        Entity pointedEntity = null;
        double minEntityReach = entityReach;
        for (Entity entity : list) {
            double distanceSq;
            if (entity instanceof EntityArmorStand || entity instanceof EntityVex) continue;
            AxisAlignedBB axisalignedbb = entity.getEntityBoundingBox().grow((double)entity.getCollisionBorderSize());
            RayTraceResult raytraceresult = axisalignedbb.calculateIntercept(eyes, end);
            if (axisalignedbb.contains(eyes)) {
                if (minEntityReach < 0.0) continue;
                pointedEntity = entity;
                entityHitVec = raytraceresult == null ? eyes : raytraceresult.hitVec;
                minEntityReach = 0.0;
                continue;
            }
            if (raytraceresult == null || (distanceSq = eyes.distanceTo(raytraceresult.hitVec)) >= minEntityReach && minEntityReach != 0.0) continue;
            if (entity.getLowestRidingEntity() == renderView.getLowestRidingEntity() && !entity.canRiderInteract()) {
                if (minEntityReach != 0.0) continue;
                pointedEntity = entity;
                entityHitVec = raytraceresult.hitVec;
                continue;
            }
            pointedEntity = entity;
            entityHitVec = raytraceresult.hitVec;
            minEntityReach = distanceSq;
        }
        if (pointedEntity != null && (minEntityReach < entityReach || rayTrace == null)) {
            return new RayTraceResult(pointedEntity, entityHitVec);
        }
        return rayTrace;
    }

    public static Map.Entry<Vec3d, Vec3d> shoulderSurfingLook(Entity entity, float partialTicks, double distanceSq) {
        Vec3d cameraOffset = ShoulderSurfingHelper.cameraOffset(ShoulderState.getCameraDistance(), entity.rotationYaw, entity.rotationPitch);
        Vec3d offset = ShoulderSurfingHelper.rayTraceHeadOffset(cameraOffset);
        Vec3d start = entity.getPositionEyes(partialTicks).add(cameraOffset);
        Vec3d look = entity.getLook(partialTicks);
        double length = offset.length();
        length *= length;
        if (Config.CLIENT.limitPlayerReach() && length < distanceSq) {
            distanceSq -= length;
        }
        double distance = (double)MathHelper.sqrt((double)distanceSq) + cameraOffset.distanceTo(offset);
        Vec3d end = start.add(look.scale(distance));
        return new AbstractMap.SimpleEntry<Vec3d, Vec3d>(start, end);
    }

    public static Vec3d cameraOffset(double distance, float yaw, float pitch) {
        return new Vec3d(Config.CLIENT.getOffsetX(), Config.CLIENT.getOffsetY(), -Config.CLIENT.getOffsetZ()).rotatePitch((float)Math.toRadians(-pitch)).rotateYaw((float)Math.toRadians(-yaw)).normalize().scale(distance);
    }

    public static Vec3d rayTraceHeadOffset(Vec3d cameraOffset) {
        Vec3d view = Minecraft.getMinecraft().getRenderViewEntity().getLookVec();
        return ShoulderSurfingHelper.lineIntersection(Vec3d.ZERO, view, cameraOffset, view);
    }

    public static Vec3d lineIntersection(Vec3d planePoint, Vec3d planeNormal, Vec3d linePoint, Vec3d lineNormal) {
        double distance = (planeNormal.dotProduct(planePoint) - planeNormal.dotProduct(linePoint)) / planeNormal.dotProduct(lineNormal);
        return linePoint.add(lineNormal.scale(distance));
    }

    public static boolean isHoldingSpecialItem() {
        return false;
    }

    public static void setPerspective(Perspective perspective) {
        Minecraft.getMinecraft().gameSettings.thirdPersonView = perspective.getPointOfView();
        ShoulderState.setEnabled(Perspective.SHOULDER_SURFING.equals((Object)perspective));
    }

    public static float getShadersResmul() {
        switch (ShoulderState.getShaderType()) {
            case OLD: {
                return shadersmod.client.Shaders.shaderPackLoaded ? shadersmod.client.Shaders.configRenderResMul : 1.0f;
            }
            case NEW: {
                return Shaders.shaderPackLoaded ? Shaders.configRenderResMul : 1.0f;
            }
        }
        return 1.0f;
    }
}

