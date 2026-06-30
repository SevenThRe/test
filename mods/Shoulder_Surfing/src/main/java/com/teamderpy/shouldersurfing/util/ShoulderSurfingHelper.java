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
        FloatBuffer screen = GLAllocation.func_74529_h((int)3);
        IntBuffer viewport = GLAllocation.func_74527_f((int)16);
        FloatBuffer modelview = GLAllocation.func_74529_h((int)16);
        FloatBuffer projection = GLAllocation.func_74529_h((int)16);
        screen.clear();
        modelview.clear();
        projection.clear();
        viewport.clear();
        GL11.glGetFloat((int)2982, (FloatBuffer)modelview);
        GL11.glGetFloat((int)2983, (FloatBuffer)projection);
        GL11.glGetInteger((int)2978, (IntBuffer)viewport);
        if (GLU.gluProject((float)((float)position.field_72450_a), (float)((float)position.field_72448_b), (float)((float)position.field_72449_c), (FloatBuffer)modelview, (FloatBuffer)projection, (IntBuffer)viewport, (FloatBuffer)screen)) {
            return new Vec2f(screen.get(0), screen.get(1));
        }
        return null;
    }

    public static double cameraDistance(World world, double distance, float yaw, float pitch) {
        Vec3d view = Minecraft.func_71410_x().func_175606_aa().func_174824_e(Minecraft.func_71410_x().func_184121_ak());
        Vec3d cameraOffset = ShoulderSurfingHelper.cameraOffset(distance, yaw, pitch);
        for (int i = 0; i < 8; ++i) {
            double newDistance;
            Vec3d camera;
            Vec3d offset = new Vec3d((double)(i & 1), (double)(i >> 1 & 1), (double)(i >> 2 & 1)).func_186678_a(2.0).func_178786_a(1.0, 1.0, 1.0).func_186678_a(0.1);
            Vec3d head = view.func_178787_e(offset);
            RayTraceResult result = world.func_147447_a(head, camera = head.func_178787_e(cameraOffset), false, true, false);
            if (result == null || !((newDistance = result.field_72307_f.func_72438_d(view)) < distance)) continue;
            distance = newDistance;
        }
        return distance;
    }

    public static RayTraceResult traceFromEyes(Entity renderView, PlayerControllerMP playerController, double playerReachOverride, float partialTicks) {
        double blockReach = Math.max((double)playerController.func_78757_d(), playerReachOverride);
        RayTraceResult rayTrace = renderView.func_174822_a(blockReach, partialTicks);
        Vec3d eyes = renderView.func_174824_e(partialTicks);
        double entityReach = blockReach;
        if (playerController.func_78749_i()) {
            entityReach = blockReach = Math.max(6.0, playerReachOverride);
        }
        if (rayTrace != null) {
            entityReach = rayTrace.field_72307_f.func_72438_d(eyes);
        }
        Vec3d look = renderView.func_70676_i(1.0f);
        Vec3d end = eyes.func_72441_c(look.field_72450_a * blockReach, look.field_72448_b * blockReach, look.field_72449_c * blockReach);
        Vec3d entityHitVec = null;
        List list = Minecraft.func_71410_x().field_71441_e.func_175674_a(renderView, renderView.func_174813_aQ().func_72321_a(look.field_72450_a * blockReach, look.field_72448_b * blockReach, look.field_72449_c * blockReach).func_72314_b(1.0, 1.0, 1.0), Predicates.and((Predicate)EntitySelectors.field_180132_d, e -> e != null && e.func_70067_L()));
        Entity pointedEntity = null;
        double minEntityReach = entityReach;
        for (Entity entity : list) {
            double distanceSq;
            if (entity instanceof EntityArmorStand || entity instanceof EntityVex) continue;
            AxisAlignedBB axisalignedbb = entity.func_174813_aQ().func_186662_g((double)entity.func_70111_Y());
            RayTraceResult raytraceresult = axisalignedbb.func_72327_a(eyes, end);
            if (axisalignedbb.func_72318_a(eyes)) {
                if (minEntityReach < 0.0) continue;
                pointedEntity = entity;
                entityHitVec = raytraceresult == null ? eyes : raytraceresult.field_72307_f;
                minEntityReach = 0.0;
                continue;
            }
            if (raytraceresult == null || (distanceSq = eyes.func_72438_d(raytraceresult.field_72307_f)) >= minEntityReach && minEntityReach != 0.0) continue;
            if (entity.func_184208_bv() == renderView.func_184208_bv() && !entity.canRiderInteract()) {
                if (minEntityReach != 0.0) continue;
                pointedEntity = entity;
                entityHitVec = raytraceresult.field_72307_f;
                continue;
            }
            pointedEntity = entity;
            entityHitVec = raytraceresult.field_72307_f;
            minEntityReach = distanceSq;
        }
        if (pointedEntity != null && (minEntityReach < entityReach || rayTrace == null)) {
            return new RayTraceResult(pointedEntity, entityHitVec);
        }
        return rayTrace;
    }

    public static Map.Entry<Vec3d, Vec3d> shoulderSurfingLook(Entity entity, float partialTicks, double distanceSq) {
        Vec3d cameraOffset = ShoulderSurfingHelper.cameraOffset(ShoulderState.getCameraDistance(), entity.field_70177_z, entity.field_70125_A);
        Vec3d offset = ShoulderSurfingHelper.rayTraceHeadOffset(cameraOffset);
        Vec3d start = entity.func_174824_e(partialTicks).func_178787_e(cameraOffset);
        Vec3d look = entity.func_70676_i(partialTicks);
        double length = offset.func_72433_c();
        length *= length;
        if (Config.CLIENT.limitPlayerReach() && length < distanceSq) {
            distanceSq -= length;
        }
        double distance = (double)MathHelper.func_76133_a((double)distanceSq) + cameraOffset.func_72438_d(offset);
        Vec3d end = start.func_178787_e(look.func_186678_a(distance));
        return new AbstractMap.SimpleEntry<Vec3d, Vec3d>(start, end);
    }

    public static Vec3d cameraOffset(double distance, float yaw, float pitch) {
        return new Vec3d(Config.CLIENT.getOffsetX(), Config.CLIENT.getOffsetY(), -Config.CLIENT.getOffsetZ()).func_178789_a((float)Math.toRadians(-pitch)).func_178785_b((float)Math.toRadians(-yaw)).func_72432_b().func_186678_a(distance);
    }

    public static Vec3d rayTraceHeadOffset(Vec3d cameraOffset) {
        Vec3d view = Minecraft.func_71410_x().func_175606_aa().func_70040_Z();
        return ShoulderSurfingHelper.lineIntersection(Vec3d.field_186680_a, view, cameraOffset, view);
    }

    public static Vec3d lineIntersection(Vec3d planePoint, Vec3d planeNormal, Vec3d linePoint, Vec3d lineNormal) {
        double distance = (planeNormal.func_72430_b(planePoint) - planeNormal.func_72430_b(linePoint)) / planeNormal.func_72430_b(lineNormal);
        return linePoint.func_178787_e(lineNormal.func_186678_a(distance));
    }

    public static boolean isHoldingSpecialItem() {
        return false;
    }

    public static void setPerspective(Perspective perspective) {
        Minecraft.func_71410_x().field_71474_y.field_74320_O = perspective.getPointOfView();
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

