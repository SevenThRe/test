/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.HashBasedTable
 *  com.google.common.io.Files
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 *  com.google.gson.annotations.Since
 *  javax.annotation.Nullable
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.registries.GameData
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.cartography.color;

import com.google.common.collect.HashBasedTable;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.annotations.Since;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;
import javax.annotation.Nullable;
import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.cartography.color.BlockStateColor;
import journeymap.client.cartography.color.ColorManager;
import journeymap.client.cartography.color.RGB;
import journeymap.client.io.FileHandler;
import journeymap.client.log.ChatLog;
import journeymap.client.model.BlockMD;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.registries.GameData;
import org.apache.logging.log4j.Logger;

public class ColorPalette {
    public static final String HELP_PAGE = "http://journeymap.info/Color_Palette";
    public static final String SAMPLE_STANDARD_PATH = ".minecraft/journeymap/";
    public static final String SAMPLE_WORLD_PATH = ".minecraft/journeymap/data/*/worldname/";
    public static final String JSON_FILENAME = "colorpalette.json";
    public static final String HTML_FILENAME = "colorpalette.html";
    public static final String VARIABLE = "var colorpalette=";
    public static final Charset UTF8 = Charset.forName("UTF-8");
    public static final double VERSION = 5.49;
    private static final Logger logger = Journeymap.getLogger();
    private static final Gson GSON = new GsonBuilder().registerTypeAdapter(HashBasedTable.class, (Object)new Serializer()).registerTypeAdapter(HashBasedTable.class, (Object)new Deserializer()).create();
    @Since(value=3.0)
    double version;
    @Since(value=1.0)
    String name;
    @Since(value=1.0)
    String generated;
    @Since(value=1.0)
    String[] description;
    @Since(value=1.0)
    boolean permanent;
    @Since(value=1.0)
    String resourcePacks;
    @Since(value=2.0)
    String modNames;
    @Since(value=5.49)
    HashBasedTable<String, String, BlockStateColor> table;
    private transient File origin;
    private transient boolean dirty;

    ColorPalette() {
        this.table = HashBasedTable.create((int)GameData.getBlockStateIDMap().func_186804_a(), (int)16);
    }

    private ColorPalette(String resourcePacks, String modNames) {
        this.version = 5.49;
        this.name = Constants.getString("jm.colorpalette.file_title");
        this.generated = String.format("Generated using %s for %s on %s", JourneymapClient.MOD_NAME, "1.12.2", new Date());
        this.resourcePacks = resourcePacks;
        this.modNames = modNames;
        ArrayList<String> lines = new ArrayList<String>();
        lines.add(Constants.getString("jm.colorpalette.file_header_1"));
        lines.add(Constants.getString("jm.colorpalette.file_header_2", HTML_FILENAME));
        lines.add(Constants.getString("jm.colorpalette.file_header_3", JSON_FILENAME, SAMPLE_WORLD_PATH));
        lines.add(Constants.getString("jm.colorpalette.file_header_4", JSON_FILENAME, SAMPLE_STANDARD_PATH));
        lines.add(Constants.getString("jm.config.file_header_5", HELP_PAGE));
        this.description = lines.toArray(new String[4]);
        this.table = HashBasedTable.create((int)GameData.getBlockStateIDMap().func_186804_a(), (int)16);
    }

    public static ColorPalette getActiveColorPalette() {
        File standardPaletteFile;
        ColorPalette palette;
        String resourcePacks = ColorManager.getResourcePackNames();
        String modNames = Constants.getModNames();
        File worldPaletteFile = ColorPalette.getWorldPaletteFile();
        if (worldPaletteFile.canRead() && (palette = ColorPalette.loadFromFile(worldPaletteFile)) != null) {
            if (palette.version < 5.49) {
                logger.warn(String.format("Existing world color palette is obsolete. Required version: %s.  Found version: %s", 5.49, palette.version));
            } else {
                return palette;
            }
        }
        if ((standardPaletteFile = ColorPalette.getStandardPaletteFile()).canRead()) {
            ColorPalette palette2 = ColorPalette.loadFromFile(standardPaletteFile);
            if (palette2 != null && palette2.version != 5.49) {
                logger.warn(String.format("Existing color palette is unusable. Required version: %s.  Found version: %s", 5.49, palette2.version));
                standardPaletteFile.renameTo(new File(standardPaletteFile.getParentFile(), standardPaletteFile.getName() + ".v" + palette2.version));
                palette2 = null;
            }
            if (palette2 != null) {
                if (palette2.isPermanent()) {
                    logger.info("Existing color palette is set to be permanent.");
                    return palette2;
                }
                if (resourcePacks.equals(palette2.resourcePacks)) {
                    if (modNames.equals(palette2.modNames)) {
                        logger.debug("Existing color palette's resource packs and mod names match current loadout.");
                        return palette2;
                    }
                    logger.warn("Existing color palette's mods no longer match current loadout.");
                    logger.info(String.format("WAS: %s\nNOW: %s", palette2.modNames, modNames));
                } else {
                    logger.warn("Existing color palette's resource packs no longer match current loadout.");
                    logger.info(String.format("WAS: %s\nNOW: %s", palette2.resourcePacks, resourcePacks));
                }
            }
        }
        return null;
    }

    public static ColorPalette create(boolean standard, boolean permanent) {
        long start = System.currentTimeMillis();
        ColorPalette palette = null;
        try {
            String resourcePackNames = ColorManager.getResourcePackNames();
            String modPackNames = Constants.getModNames();
            palette = new ColorPalette(resourcePackNames, modPackNames);
            palette.setPermanent(permanent);
            palette.writeToFile(standard);
            long elapsed = System.currentTimeMillis() - start;
            logger.info(String.format("Color palette file generated for %d blockstates in %dms for: %s", palette.size(), elapsed, palette.getOrigin()));
            return palette;
        }
        catch (Exception e) {
            logger.error("Couldn't create ColorPalette: " + LogFormatter.toString(e));
            return null;
        }
    }

    private static File getWorldPaletteFile() {
        Minecraft mc = FMLClientHandler.instance().getClient();
        return new File(FileHandler.getJMWorldDir(mc), JSON_FILENAME);
    }

    private static File getStandardPaletteFile() {
        return new File(FileHandler.getJourneyMapDir(), JSON_FILENAME);
    }

    private static ColorPalette loadFromFile(File file) {
        String jsonString = null;
        try {
            jsonString = Files.toString((File)file, (Charset)UTF8).replaceFirst(VARIABLE, "");
            ColorPalette palette = (ColorPalette)GSON.fromJson(jsonString, ColorPalette.class);
            palette.origin = file;
            palette.getOriginHtml(true, true);
            return palette;
        }
        catch (Throwable e) {
            ChatLog.announceError(Constants.getString("jm.colorpalette.file_error", file.getPath()));
            try {
                file.renameTo(new File(file.getParentFile(), file.getName() + ".bad"));
            }
            catch (Exception e2) {
                logger.error("Couldn't rename bad palette file: " + e2);
            }
            return null;
        }
    }

    private String substituteValueInContents(String contents, String key, Object ... params) {
        String token = String.format("\\$%s\\$", key);
        return contents.replaceAll(token, Matcher.quoteReplacement(Constants.getString(key, params)));
    }

    boolean hasBlockStateColor(BlockMD blockMD) {
        return this.table.contains((Object)BlockMD.getBlockId(blockMD), (Object)BlockMD.getBlockStateId(blockMD));
    }

    @Nullable
    private BlockStateColor getBlockStateColor(BlockMD blockMD, boolean createIfMissing) {
        BlockStateColor blockStateColor = (BlockStateColor)this.table.get((Object)BlockMD.getBlockId(blockMD), (Object)BlockMD.getBlockStateId(blockMD));
        if (blockStateColor == null && createIfMissing && blockMD.hasColor()) {
            blockStateColor = new BlockStateColor(blockMD);
            this.table.put((Object)BlockMD.getBlockId(blockMD), (Object)BlockMD.getBlockStateId(blockMD), (Object)blockStateColor);
            this.dirty = true;
        }
        return blockStateColor;
    }

    public boolean applyColor(BlockMD blockMD, boolean createIfMissing) {
        boolean preExisting = this.hasBlockStateColor(blockMD);
        BlockStateColor blockStateColor = this.getBlockStateColor(blockMD, createIfMissing);
        if (blockStateColor == null) {
            return false;
        }
        if (preExisting) {
            if (blockMD.hasTransparency()) {
                blockMD.setAlpha(blockStateColor.alpha != null ? blockStateColor.alpha.floatValue() : 1.0f);
            }
            int color = RGB.hexToInt(blockStateColor.color);
            blockMD.setColor(color);
        }
        return true;
    }

    public int applyColors(Collection<BlockMD> blockMDs, boolean createIfMissing) {
        int hit = 0;
        int miss = 0;
        for (BlockMD blockMD : blockMDs) {
            if (this.applyColor(blockMD, createIfMissing)) {
                ++hit;
                continue;
            }
            ++miss;
        }
        if (miss > 0) {
            logger.debug(miss + " blockstates didn't have a color in the palette");
        }
        return hit;
    }

    public void writeToFile() {
        this.writeToFile(this.isStandard());
    }

    private boolean writeToFile(boolean standard) {
        File palleteFile = null;
        try {
            palleteFile = standard ? ColorPalette.getStandardPaletteFile() : ColorPalette.getWorldPaletteFile();
            Files.write((CharSequence)(VARIABLE + GSON.toJson((Object)this)), (File)palleteFile, (Charset)UTF8);
            this.origin = palleteFile;
            this.dirty = false;
            this.getOriginHtml(true, true);
            return true;
        }
        catch (Exception e) {
            logger.error(String.format("Can't save color palette file %s: %s", palleteFile, LogFormatter.toString(e)));
            return false;
        }
    }

    public File getOrigin() throws IOException {
        return this.origin.getCanonicalFile();
    }

    public File getOriginHtml(boolean createIfMissing, boolean overwriteExisting) {
        try {
            if (this.origin == null) {
                return null;
            }
            File htmlFile = new File(this.origin.getParentFile(), HTML_FILENAME);
            if (!htmlFile.exists() && createIfMissing || overwriteExisting) {
                htmlFile = FileHandler.copyColorPaletteHtmlFile(this.origin.getParentFile(), HTML_FILENAME);
                String htmlString = Files.toString((File)htmlFile, (Charset)UTF8);
                htmlString = this.substituteValueInContents(htmlString, "jm.colorpalette.file_title", new Object[0]);
                htmlString = this.substituteValueInContents(htmlString, "jm.colorpalette.file_missing_data", JSON_FILENAME);
                htmlString = this.substituteValueInContents(htmlString, "jm.colorpalette.resource_packs", new Object[0]);
                htmlString = this.substituteValueInContents(htmlString, "jm.colorpalette.mods", new Object[0]);
                htmlString = this.substituteValueInContents(htmlString, "jm.colorpalette.basic_colors", new Object[0]);
                htmlString = this.substituteValueInContents(htmlString, "jm.colorpalette.biome_colors", new Object[0]);
                Files.write((CharSequence)htmlString, (File)htmlFile, (Charset)UTF8);
            }
            return htmlFile;
        }
        catch (Throwable t) {
            logger.error("Can't write colorpalette.html: " + t);
            return null;
        }
    }

    public boolean isPermanent() {
        return this.permanent;
    }

    public void setPermanent(boolean permanent) {
        this.permanent = permanent;
    }

    public boolean isStandard() {
        return this.origin != null && this.origin.getParentFile().getAbsoluteFile().equals(FileHandler.getJourneyMapDir().getAbsoluteFile());
    }

    public double getVersion() {
        return this.version;
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public int size() {
        return this.table.size();
    }

    public String toString() {
        return "ColorPalette[" + this.resourcePacks + "]";
    }

    private static class Deserializer
    implements JsonDeserializer<HashBasedTable<String, String, BlockStateColor>> {
        private Deserializer() {
        }

        public HashBasedTable<String, String, BlockStateColor> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            HashBasedTable result = HashBasedTable.create((int)GameData.getBlockStateIDMap().func_186804_a(), (int)16);
            JsonObject jsonTable = json.getAsJsonObject();
            for (Map.Entry jsonMod : jsonTable.entrySet()) {
                String modId = (String)jsonMod.getKey();
                for (Map.Entry jsonBlock : ((JsonElement)jsonMod.getValue()).getAsJsonObject().entrySet()) {
                    String blockId = (String)jsonBlock.getKey();
                    for (Map.Entry jsonState : ((JsonElement)jsonBlock.getValue()).getAsJsonObject().entrySet()) {
                        String blockStateId = (String)jsonState.getKey();
                        JsonArray bscArray = ((JsonElement)jsonState.getValue()).getAsJsonArray();
                        String color = bscArray.get(0).getAsString();
                        float alpha = 1.0f;
                        if (bscArray.size() > 1) {
                            alpha = bscArray.get(1).getAsFloat();
                        }
                        BlockStateColor blockStateColor = new BlockStateColor(color, Float.valueOf(alpha));
                        result.put((Object)(modId + ":" + blockId), (Object)blockStateId, (Object)blockStateColor);
                    }
                }
            }
            return result;
        }
    }

    private static class Serializer
    implements JsonSerializer<HashBasedTable<String, String, BlockStateColor>> {
        private Serializer() {
        }

        public JsonElement serialize(HashBasedTable<String, String, BlockStateColor> src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject jsonTable = new JsonObject();
            for (String blockId : src.rowKeySet().stream().sorted().collect(Collectors.toList())) {
                String[] resource = blockId.split(":");
                String mod = resource[0];
                String block = resource[1];
                JsonObject jsonMod = null;
                if (!jsonTable.has(mod)) {
                    jsonMod = new JsonObject();
                    jsonTable.add(mod, (JsonElement)jsonMod);
                } else {
                    jsonMod = jsonTable.getAsJsonObject(mod);
                }
                JsonObject jsonBlock = null;
                if (!jsonMod.has(block)) {
                    jsonBlock = new JsonObject();
                    jsonMod.add(block, (JsonElement)jsonBlock);
                } else {
                    jsonBlock = jsonMod.getAsJsonObject(block);
                }
                for (String stateId : src.row((Object)blockId).keySet().stream().sorted().collect(Collectors.toList())) {
                    BlockStateColor blockStateColor = (BlockStateColor)src.get((Object)blockId, (Object)stateId);
                    JsonArray bscArray = new JsonArray();
                    bscArray.add((JsonElement)new JsonPrimitive(blockStateColor.color));
                    if (blockStateColor.alpha != null && blockStateColor.alpha.floatValue() != 1.0f) {
                        bscArray.add((JsonElement)new JsonPrimitive((Number)blockStateColor.alpha));
                    }
                    jsonBlock.add(stateId, (JsonElement)bscArray);
                }
            }
            return jsonTable;
        }
    }
}

