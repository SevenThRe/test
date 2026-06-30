/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.collect.Ordering
 *  net.minecraft.client.resources.I18n
 *  net.minecraft.client.resources.ResourcePackRepository
 *  net.minecraft.client.resources.ResourcePackRepository$Entry
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.common.Loader
 *  net.minecraftforge.fml.common.ModContainer
 */
package journeymap.client;

import com.google.common.base.Joiner;
import com.google.common.collect.Ordering;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.ModContainer;

public class Constants {
    public static final Ordering<String> CASE_INSENSITIVE_NULL_SAFE_ORDER = Ordering.from((Comparator)String.CASE_INSENSITIVE_ORDER).nullsLast();
    public static final TimeZone GMT = TimeZone.getTimeZone("GMT");
    private static final Joiner path = Joiner.on((String)File.separator).useForNull("");
    private static final String END = null;
    public static String JOURNEYMAP_DIR = "resourcepacks" + File.separator + "journeymap";
    public static String CONFIG_DIR_LEGACY = path.join((Object)JOURNEYMAP_DIR, (Object)"config", new Object[0]);
    public static String CONFIG_DIR = path.join((Object)JOURNEYMAP_DIR, (Object)"config", new Object[]{Journeymap.JM_VERSION.toMajorMinorString(), END});
    public static String DATA_DIR = path.join((Object)JOURNEYMAP_DIR, (Object)"data", new Object[0]);
    public static String SP_DATA_DIR = path.join((Object)DATA_DIR, (Object)WorldType.sp, new Object[]{END});
    public static String MP_DATA_DIR = path.join((Object)DATA_DIR, (Object)WorldType.mp, new Object[]{END});
    public static String RESOURCE_PACKS_DEFAULT = "Default";
    private static String ICON_DIR = path.join((Object)JOURNEYMAP_DIR, (Object)"icon", new Object[0]);
    public static String ENTITY_ICON_DIR = path.join((Object)ICON_DIR, (Object)"entity", new Object[]{END});
    public static String WAYPOINT_ICON_DIR = path.join((Object)ICON_DIR, (Object)"waypoint", new Object[]{END});
    public static String THEME_ICON_DIR = path.join((Object)ICON_DIR, (Object)"theme", new Object[]{END});

    public static Locale getLocale() {
        Locale locale = Locale.getDefault();
        try {
            String lang = FMLClientHandler.instance().getClient().func_135016_M().func_135041_c().func_135034_a();
            locale = new Locale(lang);
        }
        catch (Exception e) {
            Journeymap.getLogger().warn("Couldn't determine locale from game settings, defaulting to " + locale);
        }
        return locale;
    }

    public static String getString(String key) {
        if (FMLClientHandler.instance().getClient() == null) {
            return key;
        }
        try {
            String result = I18n.func_135052_a((String)key, (Object[])new Object[0]);
            if (result.equals(key)) {
                Journeymap.getLogger().warn("Message key not found: " + key);
            }
            return result;
        }
        catch (Throwable t) {
            Journeymap.getLogger().warn(String.format("Message key '%s' threw exception: %s", key, t.getMessage()));
            return key;
        }
    }

    public static String getString(String key, Object ... names) {
        if (FMLClientHandler.instance().getClient() == null) {
            return String.format("%s (%s)", key, Joiner.on((String)",").join(names));
        }
        try {
            String result = I18n.func_135052_a((String)key, (Object[])names);
            if (result.equals(key)) {
                Journeymap.getLogger().warn("Message key not found: " + key);
            }
            return result;
        }
        catch (Throwable t) {
            Journeymap.getLogger().warn(String.format("Message key '%s' threw exception: %s", key, t.getMessage()));
            return key;
        }
    }

    public static boolean safeEqual(String first, String second) {
        int result = CASE_INSENSITIVE_NULL_SAFE_ORDER.compare((Object)first, (Object)second);
        return result == 0 && CASE_INSENSITIVE_NULL_SAFE_ORDER.compare((Object)first, (Object)second) == 0;
    }

    public static List<ResourcePackRepository.Entry> getResourcePacks() {
        ArrayList<ResourcePackRepository.Entry> entries = new ArrayList<ResourcePackRepository.Entry>();
        try {
            ResourcePackRepository resourcepackrepository = FMLClientHandler.instance().getClient().func_110438_M();
            entries.addAll(resourcepackrepository.func_110613_c());
        }
        catch (Throwable t) {
            Journeymap.getLogger().error(String.format("Can't get resource pack names: %s", LogFormatter.toString(t)));
        }
        return entries;
    }

    public static String getModNames() {
        ArrayList<String> list = new ArrayList<String>();
        for (ModContainer mod : Loader.instance().getActiveModList()) {
            if (!Loader.isModLoaded((String)mod.getModId())) continue;
            list.add(String.format("%s:%s", mod.getName(), mod.getVersion()));
        }
        Collections.sort(list);
        return Joiner.on((String)", ").join(list);
    }

    public static String birthdayMessage() {
        Calendar today = Calendar.getInstance();
        int month = today.get(2);
        int date = today.get(5);
        if (month == 6 && date == 2) {
            return Constants.getString("jm.common.birthday", "techbrew");
        }
        if (month == 8 && date == 21) {
            return Constants.getString("jm.common.birthday", "mysticdrew");
        }
        if (month == 8 && date == 27) {
            return Constants.getString("jm.common.birthday", "gdude2002");
        }
        return null;
    }

    public static enum WorldType {
        mp,
        sp;

    }
}

