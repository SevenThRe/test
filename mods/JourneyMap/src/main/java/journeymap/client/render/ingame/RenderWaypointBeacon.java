/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.client.renderer.entity.RenderManager
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.MathHelper
 *  net.minecraft.util.math.Vec3d
 *  net.minecraft.util.text.TextFormatting
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.render.ingame;

import java.util.Collection;
import journeymap.client.Constants;
import journeymap.client.cartography.color.RGB;
import journeymap.client.model.Waypoint;
import journeymap.client.properties.WaypointProperties;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.waypoint.WaypointStore;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class RenderWaypointBeacon {
    static final ResourceLocation beam = new ResourceLocation("textures/entity/beacon_beam.png");
    static Minecraft mc = FMLClientHandler.instance().getClient();
    static RenderManager renderManager = mc.getRenderManager();
    static String distanceLabel = Constants.getString("jm.waypoint.distance_meters", "%1.0f");
    static WaypointProperties waypointProperties;

    public static void resetStatTimers() {
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public static void renderAll() {
        try {
            waypointProperties = Journeymap.getClient().getWaypointProperties();
            Collection<Waypoint> waypoints = WaypointStore.INSTANCE.getAll();
            int playerDim = RenderWaypointBeacon.mc.player.dimension;
            for (Waypoint wp : waypoints) {
                if (!wp.isEnable() || !wp.getDimensions().contains(playerDim)) continue;
                try {
                    RenderWaypointBeacon.doRender(wp);
                }
                catch (Throwable t) {
                    Journeymap.getLogger().error("EntityWaypoint failed to render for " + wp + ": " + LogFormatter.toString(t));
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error rendering waypoints: " + LogFormatter.toString(t));
        }
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static void doRender(Waypoint waypoint) {
        if (RenderWaypointBeacon.renderManager.renderViewEntity == null) {
            return;
        }
        RenderHelper.enableStandardItemLighting();
        try {
            double maxRenderDistance;
            double viewDistance;
            Vec3d playerVec = RenderWaypointBeacon.renderManager.renderViewEntity.getPositionVector();
            Vec3d waypointVec = waypoint.getPosition().add(0.0, 0.118, 0.0);
            double actualDistance = playerVec.distanceTo(waypointVec);
            int maxDistance = RenderWaypointBeacon.waypointProperties.maxDistance.get();
            if (maxDistance > 0 && actualDistance > (double)maxDistance) {
                return;
            }
            float fadeAlpha = 1.0f;
            int minDistance = RenderWaypointBeacon.waypointProperties.minDistance.get();
            if (waypoint.isDeathPoint() && RenderWaypointBeacon.waypointProperties.autoRemoveDeathpoints.get().booleanValue() && actualDistance < (double)RenderWaypointBeacon.waypointProperties.autoRemoveDeathpointDistance.get().intValue() && actualDistance > 1.0) {
                WaypointStore.INSTANCE.remove(waypoint);
            }
            if (minDistance > 0) {
                if ((int)actualDistance <= minDistance) {
                    return;
                }
                if ((int)actualDistance <= minDistance + 4) {
                    fadeAlpha = (float)(actualDistance - (double)minDistance) / 3.0f;
                }
            }
            if ((viewDistance = actualDistance) > (maxRenderDistance = (double)(RenderWaypointBeacon.mc.gameSettings.renderDistanceChunks * 16))) {
                Vec3d delta = waypointVec.subtract(playerVec).normalize();
                waypointVec = playerVec.add(delta.x * maxRenderDistance, delta.y * maxRenderDistance, delta.z * maxRenderDistance);
                viewDistance = maxRenderDistance;
            }
            double shiftX = waypointVec.x - RenderWaypointBeacon.renderManager.viewerPosX;
            double shiftY = waypointVec.y - RenderWaypointBeacon.renderManager.viewerPosY;
            double shiftZ = waypointVec.z - RenderWaypointBeacon.renderManager.viewerPosZ;
            boolean showStaticBeam = RenderWaypointBeacon.waypointProperties.showStaticBeam.get();
            boolean showRotatingBeam = RenderWaypointBeacon.waypointProperties.showRotatingBeam.get();
            if (showStaticBeam || showRotatingBeam) {
                RenderWaypointBeacon.renderBeam(shiftX, -RenderWaypointBeacon.renderManager.viewerPosY, shiftZ, waypoint.getColor(), fadeAlpha, showStaticBeam, showRotatingBeam);
            }
            String label = waypoint.getName();
            boolean labelHidden = false;
            if (viewDistance > 0.5 && RenderWaypointBeacon.waypointProperties.autoHideLabel.get().booleanValue()) {
                double playerYaw;
                int angle = 5;
                double yaw = Math.atan2(RenderWaypointBeacon.renderManager.viewerPosZ - waypointVec.z, RenderWaypointBeacon.renderManager.viewerPosX - waypointVec.x);
                double degrees = Math.toDegrees(yaw) + 90.0;
                if (degrees < 0.0) {
                    degrees = 360.0 + degrees;
                }
                if ((playerYaw = (double)(RenderWaypointBeacon.renderManager.renderViewEntity.getRotationYawHead() % 360.0f)) < 0.0) {
                    playerYaw += 360.0;
                }
                playerYaw = Math.toRadians(playerYaw);
                double playerDegrees = Math.toDegrees(playerYaw);
                labelHidden = Math.abs((degrees += (double)angle) + (double)angle - ((playerDegrees += (double)angle) + (double)angle)) > (double)angle;
            }
            double scale = 0.00390625 * ((viewDistance + 4.0) / 3.0);
            TextureImpl texture = waypoint.getTexture();
            double halfTexHeight = texture.getHeight() / 2;
            boolean showName = RenderWaypointBeacon.waypointProperties.showName.get() != false && label != null && label.length() > 0;
            boolean showDistance = RenderWaypointBeacon.waypointProperties.showDistance.get();
            if (!labelHidden && (showName || showDistance)) {
                StringBuilder sb = new StringBuilder();
                if (RenderWaypointBeacon.waypointProperties.boldLabel.get().booleanValue()) {
                    sb.append(TextFormatting.BOLD);
                }
                if (showName) {
                    sb.append(label);
                }
                if (showName && showDistance) {
                    sb.append(" ");
                }
                if (showDistance) {
                    sb.append(String.format(distanceLabel, actualDistance));
                }
                if (sb.length() > 0) {
                    label = sb.toString();
                    GlStateManager.pushMatrix();
                    GlStateManager.disableLighting();
                    GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * scale));
                    GlStateManager.translate((double)shiftX, (double)shiftY, (double)shiftZ);
                    GlStateManager.rotate((float)(-RenderWaypointBeacon.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                    GlStateManager.rotate((float)RenderWaypointBeacon.renderManager.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                    GlStateManager.scale((double)(-scale), (double)(-scale), (double)scale);
                    GlStateManager.depthMask((boolean)true);
                    GlStateManager.depthMask((boolean)true);
                    GlStateManager.enableDepth();
                    int fontScale = RenderWaypointBeacon.waypointProperties.fontScale.get();
                    double labelY = 0.0 - halfTexHeight - 8.0;
                    DrawUtil.drawLabel(label, 1.0, labelY, DrawUtil.HAlign.Center, DrawUtil.VAlign.Above, 0, 0.6f * fadeAlpha, waypoint.getSafeColor(), fadeAlpha, fontScale, false);
                    GlStateManager.disableDepth();
                    GlStateManager.depthMask((boolean)false);
                    DrawUtil.drawLabel(label, 1.0, labelY, DrawUtil.HAlign.Center, DrawUtil.VAlign.Above, 0, 0.4f * fadeAlpha, waypoint.getSafeColor(), fadeAlpha, fontScale, false);
                    GlStateManager.popMatrix();
                }
            }
            if (viewDistance > 0.1 && RenderWaypointBeacon.waypointProperties.showTexture.get().booleanValue()) {
                GlStateManager.pushMatrix();
                GlStateManager.disableLighting();
                GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * scale));
                GlStateManager.disableDepth();
                GlStateManager.depthMask((boolean)false);
                int n = RenderWaypointBeacon.waypointProperties.textureSmall.get() != false ? 1 : 2;
                GlStateManager.translate((double)shiftX, (double)shiftY, (double)shiftZ);
                GlStateManager.rotate((float)(-RenderWaypointBeacon.renderManager.playerViewY), (float)0.0f, (float)1.0f, (float)0.0f);
                GlStateManager.rotate((float)RenderWaypointBeacon.renderManager.playerViewX, (float)1.0f, (float)0.0f, (float)0.0f);
                GlStateManager.scale((double)(-(scale *= (double)n)), (double)(-scale), (double)scale);
                GL11.glNormal3d((double)0.0, (double)0.0, (double)(-1.0 * scale));
                DrawUtil.drawColoredImage(texture, waypoint.getColor(), fadeAlpha, (double)(0 - texture.getWidth() / 2) + 0.5, 0.0 - halfTexHeight + 0.2, 0.0);
                GlStateManager.popMatrix();
            }
        }
        finally {
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableDepth();
            GlStateManager.enableLighting();
            GlStateManager.depthMask((boolean)true);
            GlStateManager.enableCull();
            GlStateManager.disableBlend();
            GlStateManager.disableFog();
            RenderHelper.disableStandardItemLighting();
        }
    }

    static void renderBeam(double x, double y, double z, Integer color, float alpha, boolean staticBeam, boolean rotatingBeam) {
        float f1 = alpha;
        RenderWaypointBeacon.mc.renderEngine.bindTexture(beam);
        GL11.glTexParameterf((int)3553, (int)10242, (float)10497.0f);
        GlStateManager.disableLighting();
        GlStateManager.disableBlend();
        GlStateManager.enableDepth();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)1, (int)1, (int)0);
        float time = RenderWaypointBeacon.mc.world.getTotalWorldTime();
        if (mc.isGamePaused()) {
            time = Minecraft.getSystemTime() / 50L;
        }
        float texOffset = -(-time * 0.2f - (float)MathHelper.floor((float)(-time * 0.1f))) * 0.6f;
        if (rotatingBeam) {
            boolean b0 = true;
            double d3 = (double)time * 0.025 * (1.0 - (double)(b0 & true) * 2.5);
            int[] rgba = RGB.ints((int)color, alpha * 0.45f);
            DrawUtil.startDrawingQuads(true);
            GlStateManager.enableBlend();
            double d4 = (double)b0 * 0.2;
            double d5 = Math.cos(d3 + 2.356194490192345) * d4;
            double d6 = Math.sin(d3 + 2.356194490192345) * d4;
            double d7 = Math.cos(d3 + 0.7853981633974483) * d4;
            double d8 = Math.sin(d3 + 0.7853981633974483) * d4;
            double d9 = Math.cos(d3 + 3.9269908169872414) * d4;
            double d10 = Math.sin(d3 + 3.9269908169872414) * d4;
            double d11 = Math.cos(d3 + 5.497787143782138) * d4;
            double d12 = Math.sin(d3 + 5.497787143782138) * d4;
            double d13 = 256.0f * f1;
            double d14 = 0.0;
            double d15 = 1.0;
            double d16 = -1.0f + texOffset;
            double d17 = (double)(256.0f * f1) * (0.5 / d4) + d16;
            DrawUtil.addVertexWithUV(x + d5, y + d13, z + d6, d15, d17, rgba);
            DrawUtil.addVertexWithUV(x + d5, y, z + d6, d15, d16, rgba);
            DrawUtil.addVertexWithUV(x + d7, y, z + d8, d14, d16, rgba);
            DrawUtil.addVertexWithUV(x + d7, y + d13, z + d8, d14, d17, rgba);
            DrawUtil.addVertexWithUV(x + d11, y + d13, z + d12, d15, d17, rgba);
            DrawUtil.addVertexWithUV(x + d11, y, z + d12, d15, d16, rgba);
            DrawUtil.addVertexWithUV(x + d9, y, z + d10, d14, d16, rgba);
            DrawUtil.addVertexWithUV(x + d9, y + d13, z + d10, d14, d17, rgba);
            DrawUtil.addVertexWithUV(x + d7, y + d13, z + d8, d15, d17, rgba);
            DrawUtil.addVertexWithUV(x + d7, y, z + d8, d15, d16, rgba);
            DrawUtil.addVertexWithUV(x + d11, y, z + d12, d14, d16, rgba);
            DrawUtil.addVertexWithUV(x + d11, y + d13, z + d12, d14, d17, rgba);
            DrawUtil.addVertexWithUV(x + d9, y + d13, z + d10, d15, d17, rgba);
            DrawUtil.addVertexWithUV(x + d9, y, z + d10, d15, d16, rgba);
            DrawUtil.addVertexWithUV(x + d5, y, z + d6, d14, d16, rgba);
            DrawUtil.addVertexWithUV(x + d5, y + d13, z + d6, d14, d17, rgba);
            DrawUtil.draw();
        }
        if (staticBeam) {
            GlStateManager.disableCull();
            double d26 = 256.0f * f1;
            double d29 = -1.0f + texOffset;
            double d30 = (double)(256.0f * f1) + d29;
            x -= 0.5;
            z -= 0.5;
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
            GlStateManager.depthMask((boolean)false);
            int[] rgba = RGB.ints((int)color, alpha * 0.4f);
            DrawUtil.startDrawingQuads(true);
            DrawUtil.addVertexWithUV(x + 0.2, y + d26, z + 0.2, 1.0, d30, rgba);
            DrawUtil.addVertexWithUV(x + 0.2, y, z + 0.2, 1.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y, z + 0.2, 0.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y + d26, z + 0.2, 0.0, d30, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y + d26, z + 0.8, 1.0, d30, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y, z + 0.8, 1.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.2, y, z + 0.8, 0.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.2, y + d26, z + 0.8, 0.0, d30, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y + d26, z + 0.2, 1.0, d30, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y, z + 0.2, 1.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y, z + 0.8, 0.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.8, y + d26, z + 0.8, 0.0, d30, rgba);
            DrawUtil.addVertexWithUV(x + 0.2, y + d26, z + 0.8, 1.0, d30, rgba);
            DrawUtil.addVertexWithUV(x + 0.2, y, z + 0.8, 1.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.2, y, z + 0.2, 0.0, d29, rgba);
            DrawUtil.addVertexWithUV(x + 0.2, y + d26, z + 0.2, 0.0, d30, rgba);
            DrawUtil.draw();
            GlStateManager.disableBlend();
        }
        GlStateManager.enableLighting();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.enableDepth();
    }
}

