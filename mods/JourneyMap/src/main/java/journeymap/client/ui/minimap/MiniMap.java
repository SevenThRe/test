/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.OpenGlHelper
 *  net.minecraft.client.renderer.RenderHelper
 *  net.minecraft.entity.player.EntityPlayer
 *  net.minecraft.util.math.MathHelper
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.ui.minimap;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import journeymap.client.api.display.Context;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.util.UIState;
import journeymap.client.data.DataCache;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.forge.event.MiniMapOverlayHandler;
import journeymap.client.log.JMLogger;
import journeymap.client.log.StatTimer;
import journeymap.client.model.EntityDTO;
import journeymap.client.model.MapState;
import journeymap.client.model.MapType;
import journeymap.client.properties.CoreProperties;
import journeymap.client.properties.MiniMapProperties;
import journeymap.client.render.draw.DrawStep;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.draw.DrawWayPointStep;
import journeymap.client.render.draw.RadarDrawStepFactory;
import journeymap.client.render.draw.WaypointDrawStepFactory;
import journeymap.client.render.map.GridRenderer;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.ui.minimap.DisplayVars;
import journeymap.client.ui.minimap.EntityDisplay;
import journeymap.client.ui.minimap.Position;
import journeymap.client.ui.minimap.ReticleOrientation;
import journeymap.client.ui.minimap.Shape;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.lwjgl.opengl.GL11;

public class MiniMap {
    private static final MapState state = new MapState();
    private static final float lightmapS = 240.0f;
    private static final float lightmapT = 240.0f;
    private static final GridRenderer gridRenderer = new GridRenderer(Context.UI.Minimap, 3);
    private final Minecraft mc = FMLClientHandler.instance().getClient();
    private final WaypointDrawStepFactory waypointRenderer = new WaypointDrawStepFactory();
    private final RadarDrawStepFactory radarRenderer = new RadarDrawStepFactory();
    private TextureImpl playerArrowFg;
    private TextureImpl playerArrowBg;
    private int playerArrowColor;
    private MiniMapProperties miniMapProperties;
    private StatTimer drawTimer;
    private StatTimer refreshStateTimer;
    private DisplayVars dv;
    private Point2D.Double centerPoint;
    private Rectangle2D.Double centerRect;
    private long initTime = System.currentTimeMillis();
    private long lastAutoDayNightTime = -1L;
    private Boolean lastPlayerUnderground;

    public MiniMap(MiniMapProperties miniMapProperties) {
        this.setMiniMapProperties(miniMapProperties);
    }

    public static synchronized MapState state() {
        return state;
    }

    public static synchronized UIState uiState() {
        return gridRenderer.getUIState();
    }

    public static void updateUIState(boolean isActive) {
        if (FMLClientHandler.instance().getClient().world != null) {
            gridRenderer.updateUIState(isActive);
        }
    }

    private void initGridRenderer() {
        gridRenderer.clear();
        state.requireRefresh();
        if (this.mc.player == null || this.mc.player.isDead) {
            return;
        }
        state.refresh(this.mc, (EntityPlayer)this.mc.player, this.miniMapProperties);
        MapType mapType = state.getMapType();
        int gridSize = this.miniMapProperties.getSize() <= 768 ? 3 : 5;
        gridRenderer.setGridSize(gridSize);
        gridRenderer.setContext(state.getWorldDir(), mapType);
        gridRenderer.center(state.getWorldDir(), mapType, this.mc.player.posX, this.mc.player.posZ, this.miniMapProperties.zoomLevel.get());
        boolean highQuality = Journeymap.getClient().getCoreProperties().tileHighDisplayQuality.get();
        gridRenderer.updateTiles(state.getMapType(), state.getZoom(), highQuality, this.mc.displayWidth, this.mc.displayHeight, true, 0.0, 0.0);
    }

    public void resetInitTime() {
        this.initTime = System.currentTimeMillis();
    }

    public void setMiniMapProperties(MiniMapProperties miniMapProperties) {
        this.miniMapProperties = miniMapProperties;
        MiniMap.state().requireRefresh();
        this.reset();
    }

    public MiniMapProperties getCurrentMinimapProperties() {
        return this.miniMapProperties;
    }

    public void drawMap() {
        this.drawMap(false);
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    public void drawMap(boolean preview) {
        StatTimer timer = this.drawTimer;
        RenderHelper.disableStandardItemLighting();
        try {
            if (this.mc.player == null || this.mc.player.isDead) {
                return;
            }
            gridRenderer.clearGlErrors(false);
            boolean doStateRefresh = state.shouldRefresh(this.mc, this.miniMapProperties);
            if (doStateRefresh) {
                timer = this.refreshStateTimer.start();
                this.autoDayNight();
                gridRenderer.setContext(state.getWorldDir(), state.getMapType());
                if (!preview) {
                    state.refresh(this.mc, (EntityPlayer)this.mc.player, this.miniMapProperties);
                }
                ClientAPI.INSTANCE.flagOverlaysForRerender();
            } else {
                timer.start();
            }
            boolean moved = gridRenderer.center(state.getWorldDir(), state.getMapType(), this.mc.player.posX, this.mc.player.posZ, this.miniMapProperties.zoomLevel.get());
            if (moved || doStateRefresh) {
                gridRenderer.updateTiles(state.getMapType(), state.getZoom(), state.isHighQuality(), this.mc.displayWidth, this.mc.displayHeight, doStateRefresh || preview, 0.0, 0.0);
            }
            if (doStateRefresh) {
                boolean checkWaypointDistance = Journeymap.getClient().getWaypointProperties().maxDistance.get() > 0;
                state.generateDrawSteps(this.mc, gridRenderer, this.waypointRenderer, this.radarRenderer, this.miniMapProperties, checkWaypointDistance);
                state.updateLastRefresh();
            }
            this.updateDisplayVars(false);
            long now = System.currentTimeMillis();
            DrawUtil.sizeDisplay(this.mc.displayWidth, this.mc.displayHeight);
            OpenGlHelper.setLightmapTextureCoords((int)OpenGlHelper.lightmapTexUnit, (float)240.0f, (float)240.0f);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc((int)770, (int)0);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.enableDepth();
            this.beginStencil();
            double rotation = 0.0;
            switch (this.dv.orientation) {
                case North: {
                    rotation = 0.0;
                    break;
                }
                case OldNorth: {
                    rotation = 90.0;
                    break;
                }
                case PlayerHeading: {
                    if (this.dv.shape != Shape.Circle) break;
                    rotation = 180.0f - this.mc.player.rotationYawHead;
                }
            }
            this.startMapRotation(rotation);
            try {
                Point2D.Double windowCenter;
                float alpha;
                long lastMapChangeTime;
                GlStateManager.translate((float)this.dv.translateX, (float)this.dv.translateY, (float)0.0f);
                gridRenderer.draw(this.dv.terrainAlpha, 0.0, 0.0, this.miniMapProperties.showGrid.get());
                gridRenderer.draw(state.getDrawSteps(), 0.0, 0.0, this.dv.fontScale, rotation);
                this.centerPoint = gridRenderer.getPixel(this.mc.player.posX, this.mc.player.posZ);
                this.centerRect = new Rectangle2D.Double(this.centerPoint.x - (double)(this.dv.minimapWidth / 2), this.centerPoint.y - (double)(this.dv.minimapHeight / 2), this.dv.minimapWidth, this.dv.minimapHeight);
                this.drawOnMapWaypoints(rotation);
                if (this.miniMapProperties.showSelf.get().booleanValue() && this.playerArrowFg != null && this.centerPoint != null) {
                    DrawUtil.drawColoredEntity(this.centerPoint.getX(), this.centerPoint.getY(), this.playerArrowBg, 0xFFFFFF, 1.0f, 1.0f, this.mc.player.rotationYawHead);
                    DrawUtil.drawColoredEntity(this.centerPoint.getX(), this.centerPoint.getY(), this.playerArrowFg, this.playerArrowColor, 1.0f, 1.0f, this.mc.player.rotationYawHead);
                }
                GlStateManager.translate((float)(-this.dv.translateX), (float)(-this.dv.translateY), (float)0.0f);
                ReticleOrientation reticleOrientation = null;
                if (this.dv.showReticle) {
                    reticleOrientation = this.dv.minimapFrame.getReticleOrientation();
                    if (reticleOrientation == ReticleOrientation.Compass) {
                        this.dv.minimapFrame.drawReticle();
                    } else {
                        this.startMapRotation(this.mc.player.rotationYawHead);
                        this.dv.minimapFrame.drawReticle();
                        this.stopMapRotation(this.mc.player.rotationYawHead);
                    }
                }
                if (now - (lastMapChangeTime = state.getLastMapTypeChange()) <= 1000L) {
                    this.stopMapRotation(rotation);
                    GlStateManager.translate((float)this.dv.translateX, (float)this.dv.translateY, (float)0.0f);
                    alpha = (float)Math.min(255L, Math.max(0L, 1100L - (now - lastMapChangeTime))) / 255.0f;
                    windowCenter = gridRenderer.getWindowPosition(this.centerPoint);
                    this.dv.getMapTypeStatus(state.getMapType()).draw(windowCenter, alpha, 0.0);
                    GlStateManager.translate((float)(-this.dv.translateX), (float)(-this.dv.translateY), (float)0.0f);
                    this.startMapRotation(rotation);
                }
                if (now - this.initTime <= 1000L) {
                    this.stopMapRotation(rotation);
                    GlStateManager.translate((float)this.dv.translateX, (float)this.dv.translateY, (float)0.0f);
                    alpha = (float)Math.min(255L, Math.max(0L, 1100L - (now - this.initTime))) / 255.0f;
                    windowCenter = gridRenderer.getWindowPosition(this.centerPoint);
                    this.dv.getMapPresetStatus(state.getMapType(), this.miniMapProperties.getId()).draw(windowCenter, alpha, 0.0);
                    GlStateManager.translate((float)(-this.dv.translateX), (float)(-this.dv.translateY), (float)0.0f);
                    this.startMapRotation(rotation);
                }
                this.endStencil();
                if (!this.dv.frameRotates && rotation != 0.0) {
                    this.stopMapRotation(rotation);
                }
                this.dv.minimapFrame.drawFrame();
                if (!this.dv.frameRotates && rotation != 0.0) {
                    this.startMapRotation(rotation);
                }
                if (this.dv.showCompass) {
                    this.dv.minimapCompassPoints.drawPoints(rotation);
                }
                GlStateManager.translate((float)this.dv.translateX, (float)this.dv.translateY, (float)0.0f);
                this.drawOffMapWaypoints(rotation);
                if (this.dv.showCompass) {
                    GlStateManager.translate((float)(-this.dv.translateX), (float)(-this.dv.translateY), (float)0.0f);
                    this.dv.minimapCompassPoints.drawLabels(rotation);
                }
            }
            finally {
                GlStateManager.popMatrix();
            }
            this.dv.drawInfoLabels(now);
            DrawUtil.sizeDisplay(this.dv.scaledResolution.getScaledWidth_double(), this.dv.scaledResolution.getScaledHeight_double());
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error during MiniMap.drawMap(): " + t.getMessage(), t);
        }
        finally {
            this.cleanup();
            timer.stop();
            gridRenderer.clearGlErrors(true);
        }
    }

    private void drawOnMapWaypoints(double rotation) {
        boolean showLabel = this.miniMapProperties.showWaypointLabels.get();
        for (DrawStep.Pass pass : DrawStep.Pass.values()) {
            for (DrawWayPointStep drawWayPointStep : state.getDrawWaypointSteps()) {
                boolean onScreen = false;
                if (pass == DrawStep.Pass.Object) {
                    Point2D.Double waypointPos = drawWayPointStep.getPosition(0.0, 0.0, gridRenderer, true);
                    onScreen = this.isOnScreen(waypointPos, this.centerPoint, this.centerRect);
                    drawWayPointStep.setOnScreen(onScreen);
                } else {
                    onScreen = drawWayPointStep.isOnScreen();
                }
                if (!onScreen) continue;
                drawWayPointStep.setShowLabel(showLabel);
                drawWayPointStep.draw(pass, 0.0, 0.0, gridRenderer, this.dv.fontScale, rotation);
            }
        }
    }

    private void drawOffMapWaypoints(double rotation) {
        for (DrawWayPointStep drawWayPointStep : state.getDrawWaypointSteps()) {
            if (drawWayPointStep.isOnScreen()) continue;
            Point2D.Double point = this.getPointOnFrame(drawWayPointStep.getPosition(0.0, 0.0, gridRenderer, false), this.centerPoint, this.dv.minimapSpec.waypointOffset);
            drawWayPointStep.drawOffscreen(DrawStep.Pass.Object, point, rotation);
        }
    }

    private void startMapRotation(double rotation) {
        GlStateManager.pushMatrix();
        if (rotation % 360.0 != 0.0) {
            double width = this.dv.displayWidth / 2 + this.dv.translateX;
            double height = this.dv.displayHeight / 2 + this.dv.translateY;
            GlStateManager.translate((double)width, (double)height, (double)0.0);
            GlStateManager.rotate((float)((float)rotation), (float)0.0f, (float)0.0f, (float)1.0f);
            GlStateManager.translate((double)(-width), (double)(-height), (double)0.0);
        }
        gridRenderer.updateRotation(rotation);
    }

    private void stopMapRotation(double rotation) {
        GlStateManager.popMatrix();
        gridRenderer.updateRotation(rotation);
    }

    private boolean isOnScreen(Point2D.Double objectPixel, Point2D centerPixel, Rectangle2D.Double centerRect) {
        if (this.dv.shape == Shape.Circle) {
            return centerPixel.distance(objectPixel) < (double)(this.dv.minimapWidth / 2);
        }
        return centerRect.contains(gridRenderer.getWindowPosition(objectPixel));
    }

    private Point2D.Double getPointOnFrame(Point2D.Double objectPixel, Point2D centerPixel, double offset) {
        if (this.dv.shape == Shape.Circle) {
            double bearing = Math.atan2(objectPixel.getY() - centerPixel.getY(), objectPixel.getX() - centerPixel.getX());
            Point2D.Double framePos = new Point2D.Double((double)(this.dv.minimapWidth / 2) * Math.cos(bearing) + centerPixel.getX(), (double)(this.dv.minimapHeight / 2) * Math.sin(bearing) + centerPixel.getY());
            return framePos;
        }
        Rectangle2D.Double rect = new Rectangle2D.Double(this.dv.textureX - this.dv.translateX, this.dv.textureY - this.dv.translateY, this.dv.minimapWidth, this.dv.minimapHeight);
        if (objectPixel.x > rect.getMaxX()) {
            objectPixel.x = rect.getMaxX();
        } else if (objectPixel.x < rect.getMinX()) {
            objectPixel.x = rect.getMinX();
        }
        if (objectPixel.y > rect.getMaxY()) {
            objectPixel.y = rect.getMaxY();
        } else if (objectPixel.y < rect.getMinY()) {
            objectPixel.y = rect.getMinY();
        }
        return objectPixel;
    }

    private void beginStencil() {
        try {
            this.cleanup();
            DrawUtil.zLevel = 1000.0;
            GlStateManager.colorMask((boolean)false, (boolean)false, (boolean)false, (boolean)false);
            this.dv.minimapFrame.drawMask();
            GlStateManager.colorMask((boolean)true, (boolean)true, (boolean)true, (boolean)true);
            DrawUtil.zLevel = 0.0;
            GlStateManager.depthMask((boolean)false);
            GlStateManager.depthFunc((int)516);
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error during MiniMap.beginStencil()", t);
        }
    }

    private void endStencil() {
        try {
            GlStateManager.disableDepth();
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error during MiniMap.endStencil()", t);
        }
    }

    private void cleanup() {
        try {
            DrawUtil.zLevel = 0.0;
            GlStateManager.depthMask((boolean)true);
            GL11.glClear((int)256);
            GlStateManager.enableDepth();
            GlStateManager.depthFunc((int)515);
            GlStateManager.enableAlpha();
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
            GlStateManager.clearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        }
        catch (Throwable t) {
            JMLogger.logOnce("Error during MiniMap.cleanup()", t);
        }
    }

    private void autoDayNight() {
        if (this.mc.world != null) {
            boolean wasInCaves = false;
            if (this.miniMapProperties.showCaves.get().booleanValue() && FeatureManager.isAllowed(Feature.MapCaves)) {
                EntityDTO player = DataCache.getPlayer();
                boolean neverChecked = this.lastPlayerUnderground == null;
                boolean playerUnderground = player.underground;
                if (neverChecked || playerUnderground != this.lastPlayerUnderground) {
                    this.lastPlayerUnderground = playerUnderground;
                    if (playerUnderground) {
                        state.setMapType(MapType.underground(player));
                    } else {
                        state.setMapType(MapType.from((MapType.Name)((Object)this.miniMapProperties.preferredMapType.get()), player));
                        wasInCaves = true;
                    }
                }
                MapType currentMapType = state.getMapType();
                if (playerUnderground && currentMapType.isUnderground() && currentMapType.vSlice != player.chunkCoordY) {
                    state.setMapType(MapType.underground(player));
                }
            }
            if (this.miniMapProperties.showDayNight.get().booleanValue() && (wasInCaves || state.getMapType().isDayOrNight())) {
                boolean neverChecked;
                long NIGHT = 13800L;
                long worldTime = this.mc.world.getWorldTime() % 24000L;
                boolean bl = neverChecked = this.lastAutoDayNightTime == -1L;
                if (worldTime >= 13800L && (neverChecked || this.lastAutoDayNightTime < 13800L)) {
                    this.lastAutoDayNightTime = worldTime;
                    state.setMapType(MapType.night(this.mc.world.provider.getDimension()));
                } else if (worldTime < 13800L && (neverChecked || this.lastAutoDayNightTime >= 13800L)) {
                    this.lastAutoDayNightTime = worldTime;
                    state.setMapType(MapType.day(this.mc.world.provider.getDimension()));
                }
            }
        }
    }

    public void reset() {
        this.initTime = System.currentTimeMillis();
        this.lastAutoDayNightTime = -1L;
        this.initGridRenderer();
        this.updateDisplayVars((Shape)this.miniMapProperties.shape.get(), (Position)this.miniMapProperties.position.get(), true);
        MiniMapOverlayHandler.checkEventConfig();
        GridRenderer.clearDebugMessages();
        CoreProperties coreProperties = Journeymap.getClient().getCoreProperties();
        this.playerArrowColor = coreProperties.getColor(coreProperties.colorSelf);
        if (((EntityDisplay)this.miniMapProperties.playerDisplay.get()).isLarge()) {
            this.playerArrowBg = TextureCache.getTexture(TextureCache.PlayerArrowBG_Large);
            this.playerArrowFg = TextureCache.getTexture(TextureCache.PlayerArrow_Large);
        } else {
            this.playerArrowBg = TextureCache.getTexture(TextureCache.PlayerArrowBG);
            this.playerArrowFg = TextureCache.getTexture(TextureCache.PlayerArrow);
        }
    }

    public void updateDisplayVars(boolean force) {
        if (this.dv != null) {
            this.updateDisplayVars(this.dv.shape, this.dv.position, force);
        }
    }

    public void updateDisplayVars(Shape shape, Position position, boolean force) {
        if (this.dv != null && !force && this.mc.displayHeight == this.dv.displayHeight && this.mc.displayWidth == this.dv.displayWidth && this.dv.shape == shape && this.dv.position == position && this.dv.fontScale == (double)this.miniMapProperties.fontScale.get().intValue()) {
            return;
        }
        this.initGridRenderer();
        if (force) {
            shape = (Shape)this.miniMapProperties.shape.get();
            position = (Position)this.miniMapProperties.position.get();
        }
        this.miniMapProperties.shape.set(shape);
        this.miniMapProperties.position.set(position);
        this.miniMapProperties.save();
        DisplayVars oldDv = this.dv;
        this.dv = new DisplayVars(this.mc, this.miniMapProperties);
        if (oldDv == null || oldDv.shape != this.dv.shape) {
            String timerName = String.format("MiniMap%s.%s", this.miniMapProperties.getId(), shape.name());
            this.drawTimer = StatTimer.get(timerName, 100);
            this.drawTimer.reset();
            this.refreshStateTimer = StatTimer.get(timerName + "+refreshState", 5);
            this.refreshStateTimer.reset();
        }
        double xpad = 0.0;
        double ypad = 0.0;
        Rectangle2D.Double viewPort = new Rectangle2D.Double((double)this.dv.textureX + xpad, (double)this.dv.textureY + ypad, (double)this.dv.minimapWidth - 2.0 * xpad, (double)this.dv.minimapHeight - 2.0 * ypad);
        gridRenderer.setViewPort(viewPort);
        MiniMap.updateUIState(true);
    }

    public String getLocation() {
        int playerX = MathHelper.floor((double)this.mc.player.posX);
        int playerZ = MathHelper.floor((double)this.mc.player.posZ);
        int playerY = MathHelper.floor((double)this.mc.player.getEntityBoundingBox().minY);
        return this.dv.locationFormatKeys.format(this.dv.locationFormatVerbose, playerX, playerZ, playerY, this.mc.player.chunkCoordY);
    }

    public String getBiome() {
        return state.getPlayerBiome();
    }
}

