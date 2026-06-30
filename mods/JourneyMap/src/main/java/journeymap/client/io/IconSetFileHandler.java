/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.resources.IResourcePack
 *  net.minecraft.client.resources.ResourcePackRepository
 *  net.minecraft.client.resources.ResourcePackRepository$Entry
 *  net.minecraft.util.ResourceLocation
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.io;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import journeymap.client.Constants;
import journeymap.client.io.FileHandler;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.properties.config.StringField;
import net.minecraft.client.resources.IResourcePack;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class IconSetFileHandler {
    public static final ResourceLocation ASSETS_JOURNEYMAP_ICON_ENTITY = new ResourceLocation("journeymap", "icon/entity");
    public static final String MOB_ICON_SET_DEFAULT = "Default";
    private static final Set<String> modUpdatedSetNames = new HashSet<String>();
    private static final Set<ResourceLocation> entityIconLocations = new HashSet<ResourceLocation>();

    public static void initialize() {
        modUpdatedSetNames.add(MOB_ICON_SET_DEFAULT);
    }

    public static boolean registerEntityIconDirectory(ResourceLocation resourceLocation) {
        boolean valid = IconSetFileHandler.addEntityIcons(resourceLocation, MOB_ICON_SET_DEFAULT, false);
        if (valid) {
            entityIconLocations.add(resourceLocation);
        }
        return valid;
    }

    public static void ensureEntityIconSet(String setName) {
        IconSetFileHandler.ensureEntityIconSet(setName, false);
    }

    public static void ensureEntityIconSet(String setName, boolean overwrite) {
        if (!modUpdatedSetNames.contains(setName)) {
            for (ResourceLocation resourceLocation : entityIconLocations) {
                IconSetFileHandler.addEntityIcons(resourceLocation, setName, overwrite);
            }
            modUpdatedSetNames.add(setName);
        }
        try {
            ResourcePackRepository rpr = FMLClientHandler.instance().getClient().getResourcePackRepository();
            for (ResourcePackRepository.Entry entry : rpr.getRepositoryEntries()) {
                IResourcePack pack = entry.getResourcePack();
                for (String domain : pack.getResourceDomains()) {
                    ResourceLocation domainEntityIcons = new ResourceLocation(domain, "textures/entity_icons");
                    if (!pack.resourceExists(domainEntityIcons)) continue;
                    IconSetFileHandler.addEntityIcons(domainEntityIcons, setName, true);
                }
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(String.format("Can't get entity icon from resource packs: %s", LogFormatter.toString(t)));
        }
    }

    private static boolean addEntityIcons(ResourceLocation resourceLocation, String setName, boolean overwrite) {
        boolean result = false;
        try {
            result = FileHandler.copyResources(IconSetFileHandler.getEntityIconDir(), resourceLocation, setName, overwrite);
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Error adding entity icons: " + t.getMessage(), t);
        }
        Journeymap.getLogger().info(String.format("Added entity icons from %s. Success: %s", resourceLocation, result));
        return result;
    }

    public static File getEntityIconDir() {
        File dir = new File(FileHandler.getMinecraftDirectory(), Constants.ENTITY_ICON_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    public static ArrayList<String> getEntityIconSetNames() {
        return IconSetFileHandler.getIconSetNames(IconSetFileHandler.getEntityIconDir(), Collections.singletonList(MOB_ICON_SET_DEFAULT));
    }

    public static ArrayList<String> getIconSetNames(File parentDir, List<String> defaultIconSets) {
        try {
            for (String iconSetName : defaultIconSets) {
                File iconSetDir = new File(parentDir, iconSetName);
                if (iconSetDir.exists() && !iconSetDir.isDirectory()) {
                    iconSetDir.delete();
                }
                iconSetDir.mkdirs();
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Could not prepare iconset directories for " + parentDir + ": " + LogFormatter.toString(t));
        }
        ArrayList<String> names = new ArrayList<String>();
        for (File iconSetDir : parentDir.listFiles()) {
            if (!iconSetDir.isDirectory()) continue;
            names.add(iconSetDir.getName());
        }
        Collections.sort(names);
        return names;
    }

    static {
        IconSetFileHandler.registerEntityIconDirectory(ASSETS_JOURNEYMAP_ICON_ENTITY);
    }

    public static class IconSetValuesProvider
    implements StringField.ValuesProvider {
        @Override
        public List<String> getStrings() {
            if (FMLClientHandler.instance().getClient() != null) {
                return IconSetFileHandler.getEntityIconSetNames();
            }
            return Collections.singletonList(IconSetFileHandler.MOB_ICON_SET_DEFAULT);
        }

        @Override
        public String getDefaultString() {
            return IconSetFileHandler.MOB_ICON_SET_DEFAULT;
        }
    }
}

