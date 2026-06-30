/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.io.Files
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.io;

import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import journeymap.client.Constants;
import journeymap.client.io.FileHandler;
import journeymap.client.log.JMLogger;
import journeymap.client.render.texture.TextureCache;
import journeymap.client.ui.UIManager;
import journeymap.client.ui.theme.Theme;
import journeymap.client.ui.theme.ThemePresets;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.config.StringField;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class ThemeLoader {
    public static final String THEME_FILE_SUFFIX = ".theme2.json";
    public static final String DEFAULT_THEME_FILE = "default.theme.config";
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().setVersion(2.0).create();
    private static transient Theme currentTheme = null;

    public static void initialize(boolean preLoadCurrentTheme) {
        Journeymap.getLogger().trace("Initializing themes ...");
        Set themeDirNames = ThemePresets.getPresetDirs().stream().collect(Collectors.toSet());
        for (String dirName : themeDirNames) {
            FileHandler.copyResources(ThemeLoader.getThemeIconDir(), new ResourceLocation("journeymap", "theme/" + dirName), dirName, true);
        }
        ThemePresets.getPresets().forEach(ThemeLoader::save);
        ThemeLoader.ensureDefaultThemeFile();
        if (preLoadCurrentTheme) {
            ThemeLoader.preloadCurrentTheme();
        }
    }

    public static File getThemeIconDir() {
        File dir = new File(FileHandler.getMinecraftDirectory(), Constants.THEME_ICON_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static File[] getThemeDirectories() {
        File parentDir = ThemeLoader.getThemeIconDir();
        File[] themeDirs = parentDir.listFiles(new FileFilter(){

            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory();
            }
        });
        return themeDirs;
    }

    public static List<Theme> getThemes() {
        File[] themeDirs = ThemeLoader.getThemeDirectories();
        if (themeDirs == null || themeDirs.length == 0) {
            ThemeLoader.initialize(false);
            themeDirs = ThemeLoader.getThemeDirectories();
            if (themeDirs == null || themeDirs.length == 0) {
                Journeymap.getLogger().error("Couldn't find theme directories.");
                return Collections.emptyList();
            }
        }
        ArrayList<Theme> themes = new ArrayList<Theme>();
        for (File themeDir : themeDirs) {
            File[] themeFiles = themeDir.listFiles(new FilenameFilter(){

                @Override
                public boolean accept(File dir, String name) {
                    return name.endsWith(ThemeLoader.THEME_FILE_SUFFIX);
                }
            });
            if (themeFiles == null || themeFiles.length <= 0) continue;
            for (File themeFile : themeFiles) {
                Theme theme2 = ThemeLoader.loadThemeFromFile(themeFile, false);
                if (theme2 == null) continue;
                themes.add(theme2);
            }
        }
        if (themes.isEmpty()) {
            themes.addAll(ThemePresets.getPresets());
        }
        Collections.sort(themes, Comparator.comparing(theme -> theme.name));
        return themes;
    }

    public static List<String> getThemeNames() {
        List<Theme> themes = null;
        try {
            themes = ThemeLoader.getThemes();
        }
        catch (Exception e) {
            themes = ThemePresets.getPresets();
        }
        return themes.stream().map(theme -> theme.name).collect(Collectors.toList());
    }

    public static Theme getCurrentTheme() {
        return ThemeLoader.getCurrentTheme(false);
    }

    public static synchronized void setCurrentTheme(Theme theme) {
        if (currentTheme == theme) {
            return;
        }
        Journeymap.getClient().getCoreProperties().themeName.set(theme.name);
        ThemeLoader.getCurrentTheme(true);
        UIManager.INSTANCE.getMiniMap().reset();
    }

    public static synchronized Theme getCurrentTheme(boolean forceReload) {
        if (forceReload) {
            TextureCache.purgeThemeImages(TextureCache.themeImages);
        }
        String themeName = Journeymap.getClient().getCoreProperties().themeName.get();
        if (forceReload || currentTheme == null || !themeName.equals(ThemeLoader.currentTheme.name)) {
            currentTheme = ThemeLoader.getThemeByName(themeName);
            Journeymap.getClient().getCoreProperties().themeName.set(ThemeLoader.currentTheme.name);
        }
        return currentTheme;
    }

    public static Theme getThemeByName(String themeName) {
        for (Theme theme : ThemeLoader.getThemes()) {
            if (!theme.name.equals(themeName)) continue;
            return theme;
        }
        Journeymap.getLogger().warn(String.format("Theme '%s' not found, reverting to default", themeName));
        return ThemePresets.getDefault();
    }

    public static Theme loadThemeFromFile(File themeFile, boolean createIfMissing) {
        try {
            if (themeFile != null && themeFile.exists()) {
                Charset UTF8 = Charset.forName("UTF-8");
                String json = Files.toString((File)themeFile, (Charset)UTF8);
                Theme theme = (Theme)GSON.fromJson(json, Theme.class);
                if ((double)theme.schema < 2.0) {
                    Journeymap.getLogger().error("Theme file schema is obsolete, cannot be used: " + themeFile);
                    return null;
                }
                return theme;
            }
            if (createIfMissing) {
                Journeymap.getLogger().info("Generating Theme json file: " + themeFile);
                Theme theme = new Theme();
                theme.name = themeFile.getName();
                ThemeLoader.save(theme);
                return theme;
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Could not load Theme json file: " + LogFormatter.toString(t));
        }
        return null;
    }

    private static File getThemeFile(String themeDirName, String themeFileName) {
        File themeDir = new File(ThemeLoader.getThemeIconDir(), themeDirName);
        String fileName = String.format("%s%s", themeFileName.replaceAll("[\\\\/:\"*?<>|]", "_"), THEME_FILE_SUFFIX);
        return new File(themeDir, fileName);
    }

    public static void save(Theme theme) {
        try {
            File themeFile = ThemeLoader.getThemeFile(theme.directory, theme.name);
            Files.createParentDirs((File)themeFile);
            Charset UTF8 = Charset.forName("UTF-8");
            Files.write((CharSequence)GSON.toJson((Object)theme), (File)themeFile, (Charset)UTF8);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Could not save Theme json file: " + t);
        }
    }

    private static void ensureDefaultThemeFile() {
        File defaultThemeFile = new File(ThemeLoader.getThemeIconDir(), DEFAULT_THEME_FILE);
        if (!defaultThemeFile.exists()) {
            try {
                Theme.DefaultPointer defaultPointer = new Theme.DefaultPointer(ThemePresets.getDefault());
                Charset UTF8 = Charset.forName("UTF-8");
                Files.write((CharSequence)GSON.toJson((Object)defaultPointer), (File)defaultThemeFile, (Charset)UTF8);
            }
            catch (Throwable t) {
                Journeymap.getLogger().error("Could not save DefaultTheme json file: " + t);
            }
        }
    }

    public static Theme getDefaultTheme() {
        if (FMLClientHandler.instance().getClient() == null) {
            return ThemePresets.getDefault();
        }
        Theme theme = null;
        File themeFile = null;
        Theme.DefaultPointer pointer = null;
        try {
            pointer = ThemeLoader.loadDefaultPointer();
            pointer.filename = pointer.filename.replace(THEME_FILE_SUFFIX, "");
            themeFile = ThemeLoader.getThemeFile(pointer.directory, pointer.filename);
            theme = ThemeLoader.loadThemeFromFile(themeFile, false);
        }
        catch (Exception e) {
            JMLogger.logOnce("Default theme not found in files", e);
        }
        if (theme == null) {
            theme = ThemePresets.getDefault();
        }
        return theme;
    }

    public static synchronized void loadNextTheme() {
        List<String> themeNames = ThemeLoader.getThemeNames();
        int index = themeNames.indexOf(ThemeLoader.getCurrentTheme().name);
        index = index < 0 || index >= themeNames.size() - 1 ? 0 : ++index;
        ThemeLoader.setCurrentTheme(ThemeLoader.getThemes().get(index));
    }

    private static Theme.DefaultPointer loadDefaultPointer() {
        try {
            ThemeLoader.ensureDefaultThemeFile();
            File defaultThemeFile = new File(ThemeLoader.getThemeIconDir(), DEFAULT_THEME_FILE);
            if (defaultThemeFile.exists()) {
                Charset UTF8 = Charset.forName("UTF-8");
                String json = Files.toString((File)defaultThemeFile, (Charset)UTF8);
                return (Theme.DefaultPointer)GSON.fromJson(json, Theme.DefaultPointer.class);
            }
            return new Theme.DefaultPointer(ThemePresets.getDefault());
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Could not load Theme.DefaultTheme json file: " + LogFormatter.toString(t));
            return null;
        }
    }

    public static void preloadCurrentTheme() {
        int count = 0;
        try {
            Theme theme = ThemeLoader.getCurrentTheme();
            File themeDir = new File(ThemeLoader.getThemeIconDir(), theme.directory).getCanonicalFile();
            Path themePath = themeDir.toPath();
            for (File file : Files.fileTreeTraverser().breadthFirstTraversal((Object)themeDir)) {
                if (!file.isFile() || !file.getName().toLowerCase().endsWith(".png")) continue;
                String relativePath = themePath.relativize(file.toPath()).toString().replaceAll("\\\\", "/");
                TextureCache.getThemeTexture(theme, relativePath);
                ++count;
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error preloading theme textures: " + LogFormatter.toString(t));
        }
        Journeymap.getLogger().info("Preloaded theme textures: " + count);
    }

    public static class ThemeValuesProvider
    implements StringField.ValuesProvider {
        @Override
        public List<String> getStrings() {
            return ThemeLoader.getThemeNames();
        }

        @Override
        public String getDefaultString() {
            return ThemeLoader.getDefaultTheme().name;
        }
    }
}

