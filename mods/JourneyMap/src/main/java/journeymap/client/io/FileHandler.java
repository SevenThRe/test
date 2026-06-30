/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.io.ByteSink
 *  com.google.common.io.ByteSource
 *  com.google.common.io.FileWriteMode
 *  com.google.common.io.Files
 *  com.google.gson.GsonBuilder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.Util
 *  net.minecraft.util.Util$EnumOS
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.ModContainer
 *  org.apache.logging.log4j.Level
 *  org.lwjgl.Sys
 */
package journeymap.client.io;

import com.google.common.base.Joiner;
import com.google.common.io.ByteSink;
import com.google.common.io.ByteSource;
import com.google.common.io.FileWriteMode;
import com.google.common.io.Files;
import com.google.gson.GsonBuilder;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import javax.imageio.ImageIO;
import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.data.WorldData;
import journeymap.client.log.JMLogger;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;
import org.apache.logging.log4j.Level;
import org.lwjgl.Sys;

public class FileHandler {
    public static final String DEV_MINECRAFT_DIR = "run/";
    public static final String ASSETS_JOURNEYMAP = "/assets/journeymap";
    public static final String ASSETS_JOURNEYMAP_UI = "/assets/journeymap/ui";
    public static final String ASSETS_WEBMAP = "/assets/journeymap/web";
    public static final Pattern PATTERN_WITH_UNICODE = Pattern.compile("[^\\w\\s\\p{L}]+", 256);
    public static final File MinecraftDirectory = FileHandler.getMinecraftDirectory();
    public static final File JourneyMapDirectory = new File(MinecraftDirectory, Constants.JOURNEYMAP_DIR);
    public static final File StandardConfigDirectory = new File(MinecraftDirectory, Constants.CONFIG_DIR);
    private static WorldClient theLastWorld;

    public static File getMinecraftDirectory() {
        Minecraft minecraft = FMLClientHandler.instance().getClient();
        if (minecraft != null) {
            return minecraft.field_71412_D;
        }
        return new File(DEV_MINECRAFT_DIR);
    }

    public static File getMCWorldDir(Minecraft minecraft) {
        if (minecraft.func_71387_A()) {
            String lastMCFolderName = minecraft.func_71401_C().func_71270_I();
            File lastMCWorldDir = new File(FileHandler.getMinecraftDirectory(), "saves" + File.separator + lastMCFolderName);
            return lastMCWorldDir;
        }
        return null;
    }

    public static File getWorldSaveDir(Minecraft minecraft) {
        if (minecraft.func_71356_B()) {
            try {
                File savesDir = new File(FileHandler.getMinecraftDirectory(), "saves");
                File worldSaveDir = new File(savesDir, minecraft.func_71401_C().func_71270_I());
                if (minecraft.field_71441_e.field_73011_w.getSaveFolder() != null) {
                    File dir = new File(worldSaveDir, minecraft.field_71441_e.field_73011_w.getSaveFolder());
                    dir.mkdirs();
                    return dir;
                }
                return worldSaveDir;
            }
            catch (Throwable t) {
                Journeymap.getLogger().error("Error getting world save dir: %s", t);
            }
        }
        return null;
    }

    public static File getMCWorldDir(Minecraft minecraft, int dimension) {
        File worldDir = FileHandler.getMCWorldDir(minecraft);
        if (worldDir == null) {
            return null;
        }
        if (dimension == 0) {
            return worldDir;
        }
        final String dimString = Integer.toString(dimension);
        File dimDir = null;
        if (dimension == -1 || dimension == 1) {
            dimDir = new File(worldDir, "DIM" + dimString);
        }
        if (dimDir == null || !dimDir.exists()) {
            File[] dims = worldDir.listFiles(new FilenameFilter(){

                @Override
                public boolean accept(File dir, String name) {
                    return name.startsWith("DIM") && name.endsWith(dimString) && !name.endsWith("-" + dimString);
                }
            });
            if (dims.length == 0) {
                return new File(worldDir, "DIM" + dimString);
            }
            if (dims.length == 1) {
                dimDir = dims[0];
            } else {
                List<File> list = Arrays.asList(dims);
                Collections.sort(list, new Comparator<File>(){

                    @Override
                    public int compare(File o1, File o2) {
                        return new Integer(o1.getName().length()).compareTo(o2.getName().length());
                    }
                });
                return list.get(0);
            }
        }
        return dimDir;
    }

    public static File getJourneyMapDir() {
        return JourneyMapDirectory;
    }

    public static File getJMWorldDir(Minecraft minecraft) {
        if (minecraft.field_71441_e == null) {
            return null;
        }
        return FileHandler.getJMWorldDir(minecraft, Journeymap.getClient().getCurrentWorldId());
    }

    public static synchronized File getJMWorldDir(Minecraft minecraft, String worldId) {
        if (minecraft.field_71441_e == null) {
            theLastWorld = null;
            return null;
        }
        File worldDirectory = null;
        try {
            worldDirectory = FileHandler.getJMWorldDirForWorldId(minecraft, worldId);
            if (worldDirectory == null) {
                worldDirectory = FileHandler.getJMWorldDirForWorldId(minecraft, null);
            }
            if (worldDirectory != null && !worldDirectory.exists()) {
                worldDirectory.mkdirs();
            }
        }
        catch (Exception e) {
            Journeymap.getLogger().log(Level.ERROR, LogFormatter.toString(e));
            throw new RuntimeException(e);
        }
        theLastWorld = minecraft.field_71441_e;
        return worldDirectory;
    }

    public static File getJMWorldDirForWorldId(Minecraft minecraft, String worldId) {
        if (minecraft == null || minecraft.field_71441_e == null) {
            return null;
        }
        File testWorldDirectory = null;
        try {
            String suffix;
            String worldName = WorldData.getWorldName(minecraft, false).replaceAll(PATTERN_WITH_UNICODE.pattern(), "~");
            if (worldId != null) {
                worldId = worldId.replaceAll("\\W+", "~");
            }
            String string = suffix = worldId != null ? "_" + worldId : "";
            testWorldDirectory = !minecraft.func_71356_B() ? new File(MinecraftDirectory, Constants.MP_DATA_DIR + worldName + suffix) : new File(MinecraftDirectory, Constants.SP_DATA_DIR + worldName + suffix);
        }
        catch (Exception e) {
            Journeymap.getLogger().log(Level.ERROR, LogFormatter.toString(e));
        }
        return testWorldDirectory;
    }

    public static File getWaypointDir() {
        return FileHandler.getWaypointDir(FileHandler.getJMWorldDir(FMLClientHandler.instance().getClient()));
    }

    public static File getWaypointDir(File jmWorldDir) {
        File waypointDir = new File(jmWorldDir, "waypoints");
        if (!waypointDir.isDirectory()) {
            waypointDir.delete();
        }
        if (!waypointDir.exists()) {
            waypointDir.mkdirs();
        }
        return waypointDir;
    }

    public static Properties getLangFile(String fileName) {
        try {
            InputStream is = JourneymapClient.class.getResourceAsStream("/assets/journeymap/lang/" + fileName);
            if (is == null) {
                File file = new File("../src/main/resources/assets/journeymap/lang/" + fileName);
                if (file.exists()) {
                    is = new FileInputStream(file);
                } else {
                    Journeymap.getLogger().warn("Language file not found: " + fileName);
                    return null;
                }
            }
            Properties properties = new Properties();
            properties.load(is);
            is.close();
            return properties;
        }
        catch (IOException e) {
            String error = "Could not get language file " + fileName + ": " + e.getMessage();
            Journeymap.getLogger().error(error);
            return null;
        }
    }

    public static <M> M getMessageModel(Class<M> model, String filePrefix) {
        try {
            String lang = Minecraft.func_71410_x().func_135016_M().func_135041_c().func_135034_a();
            InputStream is = FileHandler.getMessageModelInputStream(filePrefix, lang);
            if (is == null && !lang.equals("en_US")) {
                is = FileHandler.getMessageModelInputStream(filePrefix, "en_US");
            }
            if (is == null) {
                Journeymap.getLogger().warn("Message file not found: " + filePrefix);
                return null;
            }
            return (M)new GsonBuilder().create().fromJson((Reader)new InputStreamReader(is), model);
        }
        catch (Throwable e) {
            String error = "Could not get Message model " + filePrefix + ": " + e.getMessage();
            Journeymap.getLogger().error(error);
            return null;
        }
    }

    public static InputStream getMessageModelInputStream(String filePrefix, String lang) {
        String file = String.format("/assets/journeymap/lang/message/%s-%s.json", filePrefix, lang);
        return JourneymapClient.class.getResourceAsStream(file);
    }

    public static File getWorldConfigDir(boolean fallbackToStandardConfigDir) {
        File worldConfigDir;
        File worldDir = FileHandler.getJMWorldDirForWorldId(FMLClientHandler.instance().getClient(), null);
        if (worldDir != null && worldDir.exists() && (worldConfigDir = new File(worldDir, "config")).exists()) {
            return worldConfigDir;
        }
        return fallbackToStandardConfigDir ? StandardConfigDirectory : null;
    }

    public static BufferedImage getImage(File imageFile) {
        try {
            if (!imageFile.canRead()) {
                return null;
            }
            return ImageIO.read(imageFile);
        }
        catch (IOException e) {
            String error = "Could not get imageFile " + imageFile + ": " + e.getMessage();
            Journeymap.getLogger().error(error);
            return null;
        }
    }

    public static boolean isInJar() {
        return FileHandler.isInJar(JourneymapClient.class.getProtectionDomain().getCodeSource().getLocation());
    }

    public static boolean isInJar(URL location) {
        File file;
        if ("jar".equals(location.getProtocol())) {
            return true;
        }
        return "file".equals(location.getProtocol()) && (file = new File(location.getFile())).exists() && (file.getName().endsWith(".jar") || file.getName().endsWith(".jar"));
    }

    public static File copyColorPaletteHtmlFile(File toDir, String fileName) {
        try {
            final File outFile = new File(toDir, fileName);
            String htmlPath = "/assets/journeymap/ui/" + fileName;
            InputStream inputStream = JourneymapClient.class.getResource(htmlPath).openStream();
            ByteSink out = new ByteSink(){

                public OutputStream openStream() throws IOException {
                    return new FileOutputStream(outFile);
                }
            };
            out.writeFrom(inputStream);
            return outFile;
        }
        catch (Throwable t) {
            Journeymap.getLogger().warn("Couldn't copy color palette html: " + t);
            return null;
        }
    }

    public static void open(File file) {
        String path = file.getAbsolutePath();
        if (Util.func_110647_a() == Util.EnumOS.OSX) {
            try {
                Runtime.getRuntime().exec(new String[]{"/usr/bin/open", path});
                return;
            }
            catch (IOException e) {
                Journeymap.getLogger().error("Could not open path with /usr/bin/open: " + path + " : " + LogFormatter.toString(e));
            }
        } else if (Util.func_110647_a() == Util.EnumOS.WINDOWS) {
            String cmd = String.format("cmd.exe /C start \"Open file\" \"%s\"", path);
            try {
                Runtime.getRuntime().exec(cmd);
                return;
            }
            catch (IOException e) {
                Journeymap.getLogger().error("Could not open path with cmd.exe: " + path + " : " + LogFormatter.toString(e));
            }
        }
        try {
            Class<?> desktopClass = Class.forName("java.awt.Desktop");
            Object method = desktopClass.getMethod("getDesktop", new Class[0]).invoke(null, new Object[0]);
            desktopClass.getMethod("browse", URI.class).invoke(method, file.toURI());
        }
        catch (Throwable e) {
            Journeymap.getLogger().error("Could not open path with Desktop: " + path + " : " + LogFormatter.toString(e));
            Sys.openURL((String)("file://" + path));
        }
    }

    public static boolean copyResources(File targetDirectory, ResourceLocation location, String setName, boolean overwrite) {
        Object fromPath = null;
        Object toDir = null;
        try {
            String domain = location.func_110624_b();
            URL fileLocation = null;
            if (domain.equals("minecraft")) {
                fileLocation = Minecraft.class.getProtectionDomain().getCodeSource().getLocation();
            } else {
                ModContainer mod = (ModContainer)Loader.instance().getIndexedModList().get(domain);
                if (mod == null) {
                    for (Map.Entry modEntry : Loader.instance().getIndexedModList().entrySet()) {
                        if (!((ModContainer)modEntry.getValue()).getModId().toLowerCase().equals(domain)) continue;
                        mod = (ModContainer)modEntry.getValue();
                        break;
                    }
                }
                if (mod != null) {
                    fileLocation = mod.getSource().toURI().toURL();
                }
            }
            if (fileLocation != null) {
                String assetsPath = location.func_110623_a().startsWith("assets/") ? location.func_110623_a() : String.format("assets/%s/%s", domain, location.func_110623_a());
                return FileHandler.copyResources(targetDirectory, fileLocation, assetsPath, setName, overwrite);
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(String.format("Couldn't get resource set from %s to %s: %s", fromPath, toDir, t));
        }
        return false;
    }

    public static boolean copyResources(File targetDirectory, String assetsPath, String setName, boolean overwrite) {
        ModContainer modContainer = (ModContainer)Loader.instance().getIndexedModList().get("journeymap");
        if (modContainer != null) {
            try {
                URL resourceDir = modContainer.getSource().toURI().toURL();
                return FileHandler.copyResources(targetDirectory, resourceDir, assetsPath, setName, overwrite);
            }
            catch (MalformedURLException e) {
                Journeymap.getLogger().error(String.format("Couldn't find resource directory %s ", targetDirectory));
            }
        }
        return false;
    }

    public static boolean copyResources(File targetDirectory, URL resourceDir, String assetsPath, String setName, boolean overwrite) {
        String fromPath = null;
        File toDir = null;
        try {
            toDir = new File(targetDirectory, setName);
            boolean inJar = FileHandler.isInJar(resourceDir);
            if (inJar) {
                fromPath = "jar".equals(resourceDir.getProtocol()) ? URLDecoder.decode(resourceDir.getPath(), "utf-8").split("file:")[1].split("!/")[0] : new File(resourceDir.getPath()).getPath();
                return FileHandler.copyFromZip(fromPath, assetsPath, toDir, overwrite);
            }
            File fromDir = new File(resourceDir.getFile(), assetsPath);
            if (fromDir.exists()) {
                fromPath = fromDir.getPath();
                return FileHandler.copyFromDirectory(fromDir, toDir, overwrite);
            }
            Journeymap.getLogger().error(String.format("Couldn't locate icons for %s: %s", setName, fromDir));
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(String.format("Couldn't unzip resource set from %s to %s: %s", fromPath, toDir, t));
        }
        return false;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     */
    static boolean copyFromZip(String zipFilePath, String zipEntryName, File destDir, boolean overWrite) throws Throwable {
        if (zipEntryName.startsWith("/")) {
            zipEntryName = zipEntryName.substring(1);
        }
        ZipFile zipFile = new ZipFile(zipFilePath);
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(zipFilePath));
        ZipEntry entry = zipIn.getNextEntry();
        boolean success = false;
        try {
            while (entry != null) {
                if (entry.getName().startsWith(zipEntryName)) {
                    File toFile = new File(destDir, entry.getName().split(zipEntryName)[1]);
                    if (!(!overWrite && toFile.exists() || entry.isDirectory())) {
                        Files.createParentDirs((File)toFile);
                        new ZipEntryByteSource(zipFile, entry).copyTo(Files.asByteSink((File)toFile, (FileWriteMode[])new FileWriteMode[0]));
                        success = true;
                    }
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
        finally {
            zipIn.close();
        }
        return success;
    }

    static boolean copyFromDirectory(File fromDir, File toDir, boolean overWrite) throws IOException {
        if (!toDir.exists() && !toDir.mkdirs()) {
            throw new IOException("Couldn't create directory: " + toDir);
        }
        File[] files = fromDir.listFiles();
        if (files == null) {
            throw new IOException(fromDir + " nas no files");
        }
        boolean success = true;
        for (File from : files) {
            File to = new File(toDir, from.getName());
            if (from.isDirectory()) {
                if (FileHandler.copyFromDirectory(from, to, overWrite)) continue;
                success = false;
                continue;
            }
            if (!overWrite && to.exists()) continue;
            Files.copy((File)from, (File)to);
            if (to.exists()) continue;
            success = false;
        }
        return success;
    }

    public static boolean delete(File file) {
        if (!file.exists()) {
            return true;
        }
        if (file.isFile()) {
            return file.delete();
        }
        Object[] cmd = null;
        String path = file.getAbsolutePath();
        Util.EnumOS os = Util.func_110647_a();
        switch (os) {
            case WINDOWS: {
                cmd = new String[]{String.format("cmd.exe /C RD /S /Q \"%s\"", path)};
                break;
            }
            case OSX: {
                cmd = new String[]{"rm", "-rf", path};
                break;
            }
            default: {
                cmd = new String[]{"rm", "-rf", path};
            }
        }
        try {
            ProcessBuilder pb = new ProcessBuilder((String[])cmd);
            pb.redirectOutput(ProcessBuilder.Redirect.INHERIT);
            pb.redirectError(ProcessBuilder.Redirect.INHERIT);
            Process p = pb.start();
            p.waitFor();
        }
        catch (Throwable e) {
            Journeymap.getLogger().error(String.format("Could not delete using: %s : %s", Joiner.on((String)" ").join(cmd), LogFormatter.toString(e)));
        }
        return file.exists();
    }

    public static BufferedImage getIconFromFile(File parentdir, String setName, String iconPath) {
        BufferedImage img = null;
        if (iconPath == null) {
            iconPath = "null";
        }
        File iconFile = null;
        try {
            String filePath = Joiner.on((char)File.separatorChar).join((Object)setName, (Object)iconPath.replace('/', File.separatorChar), new Object[0]);
            iconFile = new File(parentdir, filePath);
            if (iconFile.exists()) {
                img = FileHandler.getImage(iconFile);
            }
        }
        catch (Exception e) {
            JMLogger.logOnce("Couldn't load iconset file: " + iconFile, e);
        }
        return img;
    }

    public static BufferedImage getIconFromResource(String assetsPath, String setName, String iconPath) {
        try {
            InputStream is = FileHandler.getIconStream(assetsPath, setName, iconPath);
            if (is == null) {
                return null;
            }
            BufferedImage img = ImageIO.read(is);
            is.close();
            return img;
        }
        catch (IOException e) {
            String error = String.format("Could not get icon from resource: %s, %s, %s : %s", assetsPath, setName, iconPath, e.getMessage());
            Journeymap.getLogger().error(error);
            return null;
        }
    }

    public static InputStream getIconStream(String assetsPath, String setName, String iconPath) {
        try {
            String pngPath = Joiner.on((char)'/').join((Object)assetsPath, (Object)setName, new Object[]{iconPath});
            InputStream is = JourneymapClient.class.getResourceAsStream(pngPath);
            if (is == null) {
                Journeymap.getLogger().warn(String.format("Icon Set asset not found: " + pngPath, new Object[0]));
                return null;
            }
            return is;
        }
        catch (Throwable e) {
            String error = String.format("Could not get icon stream: %s, %s, %s : %s", assetsPath, setName, iconPath, e.getMessage());
            Journeymap.getLogger().error(error);
            return null;
        }
    }

    private static class ZipEntryByteSource
    extends ByteSource {
        final ZipFile file;
        final ZipEntry entry;

        ZipEntryByteSource(ZipFile file, ZipEntry entry) {
            this.file = file;
            this.entry = entry;
        }

        public InputStream openStream() throws IOException {
            return this.file.getInputStream(this.entry);
        }

        public String toString() {
            return String.format("ZipEntryByteSource( %s / %s )", this.file, this.entry);
        }
    }
}

