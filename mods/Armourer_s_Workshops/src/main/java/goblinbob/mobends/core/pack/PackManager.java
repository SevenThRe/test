/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.util.ResourceLocation
 */
package goblinbob.mobends.core.pack;

import goblinbob.mobends.core.Core;
import goblinbob.mobends.core.configuration.CoreClientConfig;
import goblinbob.mobends.core.flux.ObservableMap;
import goblinbob.mobends.core.pack.IBendsPack;
import goblinbob.mobends.core.pack.InvalidPackFormatException;
import goblinbob.mobends.core.pack.LocalBendsPack;
import goblinbob.mobends.core.pack.PackCache;
import goblinbob.mobends.core.pack.PackDataProvider;
import goblinbob.mobends.core.pack.ThumbnailProvider;
import goblinbob.mobends.core.util.ErrorReporter;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class PackManager {
    public static final PackManager INSTANCE = new PackManager();
    private File localDirectory;
    private PackCache cache;
    private ThumbnailProvider thumbnailProvider;
    private ObservableMap<String, LocalBendsPack> localPacks = new ObservableMap();
    private CoreClientConfig config;
    private final List<IBendsPack> appliedPacks = new LinkedList<IBendsPack>();

    public void initialize(CoreClientConfig config) {
        this.localDirectory = new File(Minecraft.getMinecraft().gameDir, "bendspacks");
        this.localDirectory.mkdir();
        this.cache = new PackCache(new File(this.localDirectory, "public_cache"));
        this.thumbnailProvider = new ThumbnailProvider(this.cache);
        this.config = config;
        try {
            this.initLocalPacks();
        }
        catch (InvalidPackFormatException e2) {
            e2.printStackTrace();
            ErrorReporter.showErrorToPlayer(e2);
        }
    }

    public void initLocalPacks() throws InvalidPackFormatException {
        this.localPacks.clear();
        this.appliedPacks.clear();
        File[] files = this.localDirectory.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            if (!file.getAbsolutePath().endsWith(".bendsmeta")) continue;
            try {
                LocalBendsPack bendsPack = LocalBendsPack.readFromFile(file);
                this.localPacks.put(bendsPack.getKey(), bendsPack);
            }
            catch (IOException ex) {
                Core.LOG.severe(String.format("Couldn't load local bends pack: '%s'", file.getName()));
            }
        }
        for (String key : this.config.appliedPackKeys) {
            IBendsPack pack = this.localPacks.get(key);
            if (pack == null) continue;
            this.appliedPacks.add(pack);
        }
        try {
            PackDataProvider.INSTANCE.createBendsPackData(this.appliedPacks);
        }
        catch (InvalidPackFormatException ex) {
            this.resetAppliedPacks(true);
            throw ex;
        }
    }

    public void setAppliedPacks(Collection<String> packKeys, boolean saveToConfig) throws InvalidPackFormatException {
        LinkedList<IBendsPack> newAppliedPacks = new LinkedList<IBendsPack>();
        for (String key : packKeys) {
            IBendsPack pack = this.localPacks.get(key);
            if (pack == null) continue;
            newAppliedPacks.add(pack);
        }
        PackDataProvider.INSTANCE.createBendsPackData(newAppliedPacks);
        this.appliedPacks.clear();
        this.appliedPacks.addAll(newAppliedPacks);
        if (saveToConfig) {
            this.config.setAppliedPacks(packKeys);
        }
    }

    public void resetAppliedPacks(boolean saveToConfig) {
        this.appliedPacks.clear();
        if (saveToConfig) {
            this.config.setAppliedPacks(new String[0]);
        }
        try {
            PackDataProvider.INSTANCE.createBendsPackData(this.appliedPacks);
        }
        catch (InvalidPackFormatException invalidPackFormatException) {
            // empty catch block
        }
    }

    public Collection<IBendsPack> getAppliedPacks() {
        return this.appliedPacks;
    }

    public Collection<LocalBendsPack> getLocalPacks() {
        return this.localPacks.values();
    }

    public ResourceLocation getThumbnailLocation(String packName, String thumbnailUrl) {
        return this.thumbnailProvider.getThumbnailLocation(packName, thumbnailUrl);
    }

    public File getLocalDirectory() {
        return this.localDirectory;
    }

    public File getMetaFileForPack(String filename) throws IOException {
        File packFile = new File(this.localDirectory, filename + ".bendsmeta");
        packFile.createNewFile();
        return packFile;
    }

    public File getDataFileForPack(String filename) throws IOException {
        File packFile = new File(this.localDirectory, filename + ".bends");
        packFile.createNewFile();
        return packFile;
    }
}

