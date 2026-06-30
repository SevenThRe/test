/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.ui.theme;

import java.util.function.Supplier;
import journeymap.client.Constants;
import journeymap.client.data.WorldData;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.option.KeyedEnum;
import net.minecraft.client.Minecraft;

public enum ThemeLabelSource implements KeyedEnum
{
    FPS("jm.theme.labelsource.fps", 100L, 1L, ThemeLabelSource::getFps),
    GameTime("jm.theme.labelsource.gametime", 0L, 1000L, ThemeLabelSource::getGameTime),
    GameTimeReal("jm.theme.labelsource.gametime.real", 0L, 1000L, ThemeLabelSource::getGameTimeReal),
    RealTime("jm.theme.labelsource.realtime", 0L, 1000L, ThemeLabelSource::getRealTime),
    Location("jm.theme.labelsource.location", 1000L, 1L, ThemeLabelSource::getLocation),
    Biome("jm.theme.labelsource.biome", 1000L, 1L, ThemeLabelSource::getBiome),
    Dimension("jm.theme.labelsource.dimension", 1000L, 1L, ThemeLabelSource::getDimension),
    Region("jm.theme.labelsource.region", 1000L, 1L, ThemeLabelSource::getRegion),
    LightLevel("jm.theme.labelsource.lightlevel", 100L, 100L, ThemeLabelSource::getLightLevel),
    Blank("jm.theme.labelsource.blank", 0L, 1L, () -> "");

    private final String key;
    private final Supplier<String> supplier;
    private final long cacheMillis;
    private final long granularityMillis;
    private long lastCallTime;
    private String lastValue = "";

    private ThemeLabelSource(String key, long cacheMillis, long granularityMillis, Supplier<String> supplier) {
        this.key = key;
        this.cacheMillis = cacheMillis;
        this.granularityMillis = granularityMillis;
        this.supplier = supplier;
    }

    public static void resetCaches() {
        for (ThemeLabelSource source : ThemeLabelSource.values()) {
            source.lastCallTime = 0L;
            source.lastValue = "";
        }
    }

    public String getLabelText(long currentTimeMillis) {
        try {
            long now = this.granularityMillis * (currentTimeMillis / this.granularityMillis);
            if (now - this.lastCallTime <= this.cacheMillis) {
                return this.lastValue;
            }
            this.lastCallTime = now;
            this.lastValue = this.supplier.get();
            return this.lastValue;
        }
        catch (Exception e) {
            return "?";
        }
    }

    public boolean isShown() {
        return this != Blank;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    public String toString() {
        return Constants.getString(this.key);
    }

    private static String getFps() {
        return Minecraft.getDebugFPS() + " fps";
    }

    private static String getGameTime() {
        return WorldData.getGameTime();
    }

    private static String getGameTimeReal() {
        return WorldData.getRealGameTime();
    }

    private static String getRealTime() {
        return WorldData.getSystemTime();
    }

    private static String getLocation() {
        return UIManager.INSTANCE.getMiniMap().getLocation();
    }

    private static String getBiome() {
        return UIManager.INSTANCE.getMiniMap().getBiome();
    }

    private static String getDimension() {
        return WorldData.getDimension();
    }

    private static String getLightLevel() {
        return WorldData.getLightLevel();
    }

    private static String getRegion() {
        return WorldData.getRegion();
    }
}

