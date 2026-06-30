/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Objects
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.util.math.ChunkPos
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.opengl.GL11
 */
package journeymap.client.render.map;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Set;
import java.util.concurrent.Future;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.log.StatTimer;
import journeymap.client.model.GridSpec;
import journeymap.client.model.ImageHolder;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.client.model.RegionImageSet;
import journeymap.client.render.draw.DrawUtil;
import journeymap.client.render.map.Tile;
import journeymap.client.render.map.TilePos;
import journeymap.client.render.texture.RegionTextureImpl;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.render.texture.TextureImpl;
import journeymap.client.task.main.ExpireTextureTask;
import journeymap.common.Journeymap;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.ChunkPos;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;

public class TileDrawStep
implements TextureImpl.Listener<RegionTextureImpl> {
    private static final Integer bgColor = 0x222222;
    private static final Logger logger = Journeymap.getLogger();
    private static final RegionImageCache regionImageCache = RegionImageCache.INSTANCE;
    private boolean debug = false;
    private final RegionCoord regionCoord;
    private final MapType mapType;
    private final Integer zoom;
    private final boolean highQuality;
    private final StatTimer drawTimer;
    private final StatTimer updateRegionTimer = StatTimer.get("TileDrawStep.updateRegionTexture", 5, 50);
    private final StatTimer updateScaledTimer = StatTimer.get("TileDrawStep.updateScaledTexture", 5, 50);
    private final int theHashCode;
    private final String theCacheKey;
    private final RegionImageSet.Key regionImageSetKey;
    private int sx1;
    private int sy1;
    private int sx2;
    private int sy2;
    private volatile TextureImpl scaledTexture;
    private volatile Future<RegionTextureImpl> regionFuture;
    private volatile Future<TextureImpl> scaledFuture;
    private volatile boolean needsScaledUpdate;
    private int lastTextureFilter;
    private int lastTextureWrap;

    public TileDrawStep(RegionCoord regionCoord, MapType mapType, Integer zoom, boolean highQuality, int sx1, int sy1, int sx2, int sy2) {
        this.mapType = mapType;
        this.regionCoord = regionCoord;
        this.regionImageSetKey = RegionImageSet.Key.from(regionCoord);
        this.zoom = zoom;
        this.sx1 = sx1;
        this.sx2 = sx2;
        this.sy1 = sy1;
        this.sy2 = sy2;
        this.highQuality = highQuality && zoom != 0;
        this.drawTimer = this.highQuality ? StatTimer.get("TileDrawStep.draw(high)") : StatTimer.get("TileDrawStep.draw(low)");
        this.theCacheKey = TileDrawStep.toCacheKey(regionCoord, mapType, zoom, highQuality, sx1, sy1, sx2, sy2);
        this.theHashCode = this.theCacheKey.hashCode();
        this.updateRegionTexture();
        if (highQuality) {
            this.updateScaledTexture();
        }
    }

    public static String toCacheKey(RegionCoord regionCoord, MapType mapType, Integer zoom, boolean highQuality, int sx1, int sy1, int sx2, int sy2) {
        return regionCoord.cacheKey() + mapType.toCacheKey() + zoom + highQuality + sx1 + "," + sy1 + "," + sx2 + "," + sy2;
    }

    ImageHolder getRegionTextureHolder() {
        return regionImageCache.getRegionImageSet(this.regionImageSetKey).getHolder(this.mapType);
    }

    boolean draw(TilePos pos, double offsetX, double offsetZ, float alpha, int textureFilter, int textureWrap, GridSpec gridSpec) {
        boolean regionUpdatePending = this.updateRegionTexture();
        if (this.highQuality && !regionUpdatePending) {
            this.updateScaledTexture();
        }
        Integer textureId = -1;
        boolean useScaled = false;
        if (this.highQuality && this.scaledTexture != null) {
            textureId = this.scaledTexture.getGlTextureId();
            useScaled = true;
        } else {
            textureId = !regionUpdatePending ? Integer.valueOf(this.getRegionTextureHolder().getTexture().getGlTextureId()) : Integer.valueOf(-1);
        }
        if (textureFilter != this.lastTextureFilter) {
            this.lastTextureFilter = textureFilter;
        }
        if (textureWrap != this.lastTextureWrap) {
            this.lastTextureWrap = textureWrap;
        }
        this.drawTimer.start();
        double startX = offsetX + pos.startX;
        double startY = offsetZ + pos.startZ;
        double endX = offsetX + pos.endX;
        double endY = offsetZ + pos.endZ;
        double z = 0.0;
        double size = 512.0;
        double startU = useScaled ? 0.0 : (double)this.sx1 / 512.0;
        double startV = useScaled ? 0.0 : (double)this.sy1 / 512.0;
        double endU = useScaled ? 1.0 : (double)this.sx2 / 512.0;
        double endV = useScaled ? 1.0 : (double)this.sy2 / 512.0;
        DrawUtil.drawRectangle(startX, startY, endX - startX, endY - startY, bgColor, 0.8f);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate((int)770, (int)771, (int)1, (int)0);
        GlStateManager.enableTexture2D();
        if (textureId != -1) {
            GlStateManager.bindTexture((int)textureId);
            GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)alpha);
            GL11.glTexParameteri((int)3553, (int)10241, (int)textureFilter);
            GL11.glTexParameteri((int)3553, (int)10240, (int)textureFilter);
            GL11.glTexParameteri((int)3553, (int)10242, (int)textureWrap);
            GL11.glTexParameteri((int)3553, (int)10243, (int)textureWrap);
            DrawUtil.drawBoundTexture(startU, startV, startX, startY, 0.0, endU, endV, endX, endY);
        }
        if (gridSpec != null) {
            gridSpec.beginTexture(9728, 33071, alpha);
            DrawUtil.drawBoundTexture((double)this.sx1 / 512.0, (double)this.sy1 / 512.0, startX, startY, 0.0, (double)this.sx2 / 512.0, (double)this.sy2 / 512.0, endX, endY);
            gridSpec.finishTexture();
        }
        if (this.debug) {
            int debugX = (int)startX;
            int debugY = (int)startY;
            DrawUtil.drawRectangle(debugX, debugY, 3.0, endV * 512.0, 65280, 0.8f);
            DrawUtil.drawRectangle(debugX, debugY, endU * 512.0, 3.0, 0xFF0000, 0.8f);
            DrawUtil.drawLabel(this.toString(), debugX + 5, debugY + 10, DrawUtil.HAlign.Right, DrawUtil.VAlign.Below, 0xFFFFFF, 255.0f, 255, 255.0f, 1.0, false);
            DrawUtil.drawLabel(String.format("Tile Render Type: %s, Scaled: %s", Tile.debugGlSettings, useScaled), debugX + 5, debugY + 20, DrawUtil.HAlign.Right, DrawUtil.VAlign.Below, 0xFFFFFF, 255.0f, 255, 255.0f, 1.0, false);
            long imageTimestamp = useScaled ? this.scaledTexture.getLastImageUpdate() : this.getRegionTextureHolder().getImageTimestamp();
            long age = (System.currentTimeMillis() - imageTimestamp) / 1000L;
            DrawUtil.drawLabel(this.mapType + " tile age: " + age + " seconds old", debugX + 5, debugY + 30, DrawUtil.HAlign.Right, DrawUtil.VAlign.Below, 0xFFFFFF, 255.0f, 255, 255.0f, 1.0, false);
        }
        GlStateManager.color((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        GlStateManager.clearColor((float)1.0f, (float)1.0f, (float)1.0f, (float)1.0f);
        this.drawTimer.stop();
        int glErr = GL11.glGetError();
        if (glErr != 0) {
            Journeymap.getLogger().warn("GL Error in TileDrawStep: " + glErr);
            this.clearTexture();
        }
        return textureId != 1;
    }

    public void clearTexture() {
        ExpireTextureTask.queue(this.scaledTexture);
        this.scaledTexture = null;
        if (this.scaledFuture != null && !this.scaledFuture.isDone()) {
            this.scaledFuture.cancel(true);
        }
        this.scaledFuture = null;
        if (this.regionFuture != null && !this.regionFuture.isDone()) {
            this.regionFuture.cancel(true);
        }
        this.regionFuture = null;
    }

    public MapType getMapType() {
        return this.mapType;
    }

    public Integer getZoom() {
        return this.zoom;
    }

    public String cacheKey() {
        return this.theCacheKey;
    }

    public int hashCode() {
        return this.theHashCode;
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("rc", (Object)this.regionCoord).add("type", (Object)this.mapType).add("high", this.highQuality).add("zoom", (Object)this.zoom).add("sx1", this.sx1).add("sy1", this.sy1).toString();
    }

    boolean hasTexture(MapType mapType) {
        if (!Objects.equal((Object)this.mapType, (Object)mapType)) {
            return false;
        }
        if (this.highQuality) {
            return this.scaledTexture != null && this.scaledTexture.isBound();
        }
        return this.getRegionTextureHolder().getTexture().isBound();
    }

    private boolean updateRegionTexture() {
        ImageHolder imageHolder;
        this.updateRegionTimer.start();
        if (this.regionFuture != null) {
            if (!this.regionFuture.isDone()) {
                this.updateRegionTimer.stop();
                return true;
            }
            this.regionFuture = null;
        }
        if ((imageHolder = this.getRegionTextureHolder()).hasTexture()) {
            RegionTextureImpl tex = imageHolder.getTexture();
            tex.addListener(this);
            if (tex.isBindNeeded()) {
                tex.bindTexture();
            }
            this.updateRegionTimer.stop();
            return false;
        }
        this.regionFuture = TextureCache.scheduleTextureTask(() -> {
            RegionTextureImpl tex = this.getRegionTextureHolder().getTexture();
            tex.addListener(this);
            return tex;
        });
        this.updateRegionTimer.stop();
        return true;
    }

    private boolean updateScaledTexture() {
        this.updateScaledTimer.start();
        if (this.scaledFuture != null) {
            if (!this.scaledFuture.isDone()) {
                this.updateScaledTimer.stop();
                return true;
            }
            try {
                this.scaledTexture = this.scaledFuture.get();
                this.scaledTexture.bindTexture();
            }
            catch (Throwable e) {
                logger.error((Object)e);
            }
            this.scaledFuture = null;
            this.updateScaledTimer.stop();
            return false;
        }
        if (this.scaledTexture == null) {
            this.needsScaledUpdate = false;
            this.scaledFuture = TextureCache.scheduleTextureTask(() -> {
                TextureImpl temp = new TextureImpl(null, this.getScaledRegionArea(), false, false);
                temp.setDescription("Scaled " + this);
                return temp;
            });
        } else if (this.needsScaledUpdate) {
            this.needsScaledUpdate = false;
            TextureImpl temp = this.scaledTexture;
            this.scaledFuture = TextureCache.scheduleTextureTask(() -> {
                temp.setImage(this.getScaledRegionArea(), false);
                return temp;
            });
        }
        this.updateScaledTimer.stop();
        return true;
    }

    public BufferedImage getScaledRegionArea() {
        int scale = (int)Math.pow(2.0, this.zoom.intValue());
        int scaledSize = 512 / scale;
        try {
            BufferedImage subImage = this.getRegionTextureHolder().getTexture().getImage().getSubimage(this.sx1, this.sy1, scaledSize, scaledSize);
            BufferedImage scaledImage = new BufferedImage(512, 512, 2);
            Graphics2D g = RegionImageHandler.initRenderingHints(scaledImage.createGraphics());
            g.drawImage(subImage, 0, 0, 512, 512, null);
            g.dispose();
            return scaledImage;
        }
        catch (Throwable e) {
            logger.error((Object)e);
            return null;
        }
    }

    @Override
    public void textureImageUpdated(RegionTextureImpl textureImpl) {
        if (this.highQuality && this.zoom > 0) {
            Set<ChunkPos> dirtyAreas = textureImpl.getDirtyAreas();
            if (dirtyAreas.isEmpty()) {
                this.needsScaledUpdate = true;
            } else {
                for (ChunkPos area : dirtyAreas) {
                    if (area.x < this.sx1 || area.z < this.sy1 || area.x + 16 > this.sx2 || area.z + 16 > this.sy2) continue;
                    this.needsScaledUpdate = true;
                    return;
                }
            }
        }
    }
}

