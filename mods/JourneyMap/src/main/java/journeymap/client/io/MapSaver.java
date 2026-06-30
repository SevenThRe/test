/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  net.minecraft.client.Minecraft
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Level
 */
package journeymap.client.io;

import com.google.common.base.Joiner;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import journeymap.client.Constants;
import journeymap.client.data.WorldData;
import journeymap.client.io.FileHandler;
import journeymap.client.io.PngjHelper;
import journeymap.client.io.RegionImageHandler;
import journeymap.client.log.ChatLog;
import journeymap.client.log.StatTimer;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.client.model.RegionImageCache;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Level;

public class MapSaver {
    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
    final File worldDir;
    final MapType mapType;
    File saveFile;
    int outputColumns;
    int outputRows;
    ArrayList<File> files;

    public MapSaver(File worldDir, MapType mapType) {
        this.worldDir = worldDir;
        this.mapType = mapType;
        this.prepareFiles();
    }

    public File saveMap() {
        StatTimer timer = StatTimer.get("MapSaver.saveMap");
        try {
            if (!this.isValid()) {
                Journeymap.getLogger().warn("No images found in " + this.getImageDir());
                return null;
            }
            RegionImageCache.INSTANCE.flushToDisk(false);
            timer.start();
            File[] fileArray = this.files.toArray(new File[this.files.size()]);
            PngjHelper.mergeFiles(fileArray, this.saveFile, this.outputColumns, 512);
            timer.stop();
            Journeymap.getLogger().info("Map filesize:" + this.saveFile.length());
            String message = Constants.getString("jm.common.map_saved", this.saveFile);
            ChatLog.announceFile(message, this.saveFile);
        }
        catch (OutOfMemoryError e) {
            String error = "Out Of Memory: Increase Java Heap Size for Minecraft to save large maps.";
            Journeymap.getLogger().error(error);
            ChatLog.announceError(error);
            timer.cancel();
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(LogFormatter.toString(t));
            timer.cancel();
            return null;
        }
        return this.saveFile;
    }

    public String getSaveFileName() {
        return this.saveFile.getName();
    }

    public boolean isValid() {
        return this.files != null && this.files.size() > 0;
    }

    private File getImageDir() {
        RegionCoord fakeRc = new RegionCoord(this.worldDir, 0, 0, this.mapType.dimension);
        return RegionImageHandler.getImageDir(fakeRc, this.mapType);
    }

    private void prepareFiles() {
        try {
            Minecraft mc = FMLClientHandler.instance().getClient();
            String date = dateFormat.format(new Date());
            String worldName = WorldData.getWorldName(mc, false);
            String dimName = WorldData.getSafeDimensionName(new WorldData.WrappedProvider(mc.field_71441_e.field_73011_w));
            String fileName = Joiner.on((String)"_").skipNulls().join((Object)date, (Object)worldName, new Object[]{dimName, this.mapType.name, this.mapType.vSlice}) + ".png";
            File screenshotsDir = new File(FileHandler.getMinecraftDirectory(), "screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdir();
            }
            this.saveFile = new File(screenshotsDir, fileName);
            RegionImageCache.INSTANCE.flushToDisk(false);
            File imageDir = this.getImageDir();
            File[] pngFiles = imageDir.listFiles();
            Pattern tilePattern = Pattern.compile("([^\\.]+)\\,([^\\.]+)\\.png");
            Integer minX = null;
            Integer minZ = null;
            Integer maxX = null;
            Integer maxZ = null;
            for (File file : pngFiles) {
                Matcher matcher = tilePattern.matcher(file.getName());
                if (!matcher.matches()) continue;
                Integer x = Integer.parseInt(matcher.group(1));
                Integer z = Integer.parseInt(matcher.group(2));
                if (minX == null || x < minX) {
                    minX = x;
                }
                if (minZ == null || z < minZ) {
                    minZ = z;
                }
                if (maxX == null || x > maxX) {
                    maxX = x;
                }
                if (maxZ != null && z <= maxZ) continue;
                maxZ = z;
            }
            if (minX == null || maxX == null || minZ == null || maxZ == null) {
                Journeymap.getLogger().warn("No region files to save in " + imageDir);
                return;
            }
            long blankSize = RegionImageHandler.getBlank512x512ImageFile().length();
            this.outputColumns = maxX - minX + 1;
            this.outputRows = maxZ - minZ + 1;
            this.files = new ArrayList(this.outputColumns * this.outputRows);
            for (int rz = minZ.intValue(); rz <= maxZ; ++rz) {
                for (int rx = minX.intValue(); rx <= maxX; ++rx) {
                    RegionCoord rc = new RegionCoord(this.worldDir, rx, rz, this.mapType.dimension);
                    File rfile = RegionImageHandler.getRegionImageFile(rc, this.mapType, true);
                    if (rfile.canRead()) {
                        this.files.add(rfile);
                        continue;
                    }
                    this.files.add(RegionImageHandler.getBlank512x512ImageFile());
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().log(Level.ERROR, LogFormatter.toString(t));
        }
    }
}

