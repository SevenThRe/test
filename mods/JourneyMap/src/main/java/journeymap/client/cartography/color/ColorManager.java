/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  javax.annotation.Nullable
 *  javax.annotation.ParametersAreNonnullByDefault
 *  net.minecraft.client.resources.ResourcePackRepository$Entry
 *  org.apache.logging.log4j.Logger
 *  org.lwjgl.LWJGLException
 *  org.lwjgl.opengl.Display
 */
package journeymap.client.cartography.color;

import com.google.common.base.Joiner;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.Constants;
import journeymap.client.cartography.color.ColorPalette;
import journeymap.client.cartography.color.ColoredSprite;
import journeymap.client.cartography.color.RGB;
import journeymap.client.model.BlockMD;
import journeymap.client.task.multi.MapPlayerTask;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.resources.ResourcePackRepository;
import org.apache.logging.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;

@ParametersAreNonnullByDefault
public enum ColorManager {
    INSTANCE;

    private Logger logger = Journeymap.getLogger();
    private volatile ColorPalette currentPalette;
    private String lastResourcePackNames;
    private String lastModNames;
    private double lastPaletteVersion;
    private HashMap<String, float[]> iconColorCache = new HashMap();

    public void reset() {
        this.lastResourcePackNames = null;
        this.lastModNames = null;
        this.lastPaletteVersion = 0.0;
        this.currentPalette = null;
        this.iconColorCache.clear();
    }

    public static String getResourcePackNames() {
        String packs;
        List<ResourcePackRepository.Entry> entries = Constants.getResourcePacks();
        if (entries.isEmpty()) {
            packs = Constants.RESOURCE_PACKS_DEFAULT;
        } else {
            ArrayList<String> entryStrings = new ArrayList<String>(entries.size());
            for (ResourcePackRepository.Entry entry : entries) {
                entryStrings.add(entry.toString());
            }
            Collections.sort(entryStrings);
            packs = Joiner.on((String)", ").join(entryStrings);
        }
        return packs;
    }

    public void ensureCurrent(boolean forceReset) {
        double currentPaletteVersion;
        try {
            if (!Display.isCurrent()) {
                this.logger.error("ColorManager.ensureCurrent() must be called on main thread!");
            }
        }
        catch (LWJGLException e) {
            e.printStackTrace();
            return;
        }
        String currentResourcePackNames = ColorManager.getResourcePackNames();
        String currentModNames = Constants.getModNames();
        double d = currentPaletteVersion = this.currentPalette == null ? 0.0 : this.currentPalette.getVersion();
        if (this.currentPalette != null && !forceReset) {
            if (!currentResourcePackNames.equals(this.lastResourcePackNames) && !this.iconColorCache.isEmpty()) {
                this.logger.debug("Resource Pack(s) changed: " + currentResourcePackNames);
                forceReset = true;
            }
            if (!currentModNames.equals(this.lastModNames)) {
                this.logger.debug("Mod Pack(s) changed: " + currentModNames);
                forceReset = true;
            }
            if (currentPaletteVersion != this.lastPaletteVersion) {
                this.logger.debug("Color Palette version changed: " + currentPaletteVersion);
                forceReset = true;
            }
        }
        if (forceReset || this.iconColorCache.isEmpty()) {
            this.logger.debug("Building color palette...");
            this.initBlockColors(forceReset);
        }
        this.lastModNames = currentModNames;
        this.lastResourcePackNames = currentResourcePackNames;
        this.lastPaletteVersion = this.currentPalette == null ? 0.0 : this.currentPalette.getVersion();
    }

    public ColorPalette getCurrentPalette() {
        return this.currentPalette;
    }

    private void initBlockColors(boolean forceReset) {
        try {
            long start = System.currentTimeMillis();
            ColorPalette palette = ColorPalette.getActiveColorPalette();
            Set<BlockMD> blockMDs = Journeymap.getClient().isMapping() != false ? BlockMD.getAllValid() : BlockMD.getAllMinecraft();
            if (forceReset || palette == null) {
                this.logger.debug("Color palette update required.");
                this.iconColorCache.clear();
                blockMDs.forEach(BlockMD::clearColor);
            }
            boolean standard = true;
            boolean permanent = false;
            if (palette != null) {
                standard = palette.isStandard();
                permanent = palette.isPermanent();
                if (permanent && forceReset) {
                    this.logger.debug("Applying permanent palette colors before updating");
                }
                if (permanent || !forceReset) {
                    try {
                        int count = palette.applyColors(blockMDs, true);
                        this.logger.debug(String.format("Loaded %d block colors from %s", count, palette.getOrigin()));
                    }
                    catch (Exception e) {
                        this.logger.warn(String.format("Could not load block colors from %s: %s", palette.getOrigin(), e));
                    }
                }
            }
            if (forceReset && !permanent || palette == null) {
                palette = ColorPalette.create(standard, permanent);
            }
            this.currentPalette = palette;
            for (BlockMD blockMD : blockMDs) {
                if (!blockMD.hasColor()) {
                    blockMD.getTextureColor();
                    this.currentPalette.applyColor(blockMD, true);
                }
                if (blockMD.hasColor()) continue;
                this.logger.warn("Could not derive color for " + blockMD.getBlockState());
            }
            if (this.currentPalette.isDirty()) {
                long elapsed = System.currentTimeMillis() - start;
                this.currentPalette.writeToFile();
                this.logger.info(String.format("Updated color palette for %s blockstates in %sms: %s", this.currentPalette.size(), elapsed, this.currentPalette.getOrigin()));
            } else {
                long elapsed = System.currentTimeMillis() - start;
                this.logger.info(String.format("Loaded color palette for %s blockstates in %sms", this.currentPalette.size(), elapsed));
            }
            MapPlayerTask.forceNearbyRemap();
        }
        catch (Throwable t) {
            this.logger.error("ColorManager.initBlockColors() encountered an unexpected error: " + LogFormatter.toPartialString(t));
        }
    }

    @Nullable
    public float[] getAverageColor(Collection<ColoredSprite> sprites) {
        float[] rgba;
        if (sprites == null || sprites.isEmpty()) {
            return null;
        }
        List names = sprites.stream().map(ColoredSprite::getIconName).collect(Collectors.toList());
        Collections.sort(names);
        String name = Joiner.on((String)",").join(names);
        if (this.iconColorCache.containsKey(name)) {
            rgba = this.iconColorCache.get(name);
        } else {
            rgba = this.calculateAverageColor(sprites);
            if (rgba != null) {
                this.iconColorCache.put(name, rgba);
                if (this.logger.isDebugEnabled()) {
                    this.logger.debug(String.format("Cached color %s for %s", RGB.toHexString(RGB.toInteger(rgba)), name));
                }
            }
        }
        return rgba;
    }

    private float[] calculateAverageColor(Collection<ColoredSprite> sprites) {
        ArrayList<BufferedImage> images = new ArrayList<BufferedImage>(sprites.size());
        for (ColoredSprite coloredSprite : sprites) {
            BufferedImage img = coloredSprite.getColoredImage();
            if (img == null) continue;
            images.add(img);
        }
        if (images.isEmpty()) {
            return null;
        }
        int count = 0;
        int b = 0;
        int g = 0;
        int r = 0;
        int a = 0;
        for (BufferedImage image : images) {
            try {
                int[] argbInts;
                for (int argb : argbInts = image.getRGB(0, 0, image.getWidth(), image.getHeight(), null, 0, image.getWidth())) {
                    int alpha = argb >> 24 & 0xFF;
                    if (alpha <= 0) continue;
                    ++count;
                    a += alpha;
                    r += argb >> 16 & 0xFF;
                    g += argb >> 8 & 0xFF;
                    b += argb & 0xFF;
                }
            }
            catch (Exception e) {
            }
        }
        if (count > 0) {
            int rgb = RGB.toInteger(r / count, g / count, b / count);
            return RGB.floats(rgb, a / count);
        }
        return null;
    }
}

