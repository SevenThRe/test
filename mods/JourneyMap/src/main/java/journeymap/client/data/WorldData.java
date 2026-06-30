/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Strings
 *  com.google.common.cache.CacheLoader
 *  com.mojang.realmsclient.RealmsMainScreen
 *  com.mojang.realmsclient.dto.RealmsServer
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.gui.GuiScreen
 *  net.minecraft.client.gui.GuiScreenRealmsProxy
 *  net.minecraft.client.multiplayer.ServerData
 *  net.minecraft.client.multiplayer.WorldClient
 *  net.minecraft.client.network.NetHandlerPlayClient
 *  net.minecraft.client.resources.ResourcePackRepository
 *  net.minecraft.network.NetworkManager
 *  net.minecraft.realms.RealmsScreen
 *  net.minecraft.server.integrated.IntegratedServer
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.world.EnumSkyBlock
 *  net.minecraft.world.WorldProvider
 *  net.minecraft.world.chunk.Chunk
 *  net.minecraft.world.storage.WorldInfo
 *  net.minecraftforge.common.DimensionManager
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  net.minecraftforge.fml.relauncher.ReflectionHelper
 *  org.apache.logging.log4j.Level
 */
package journeymap.client.data;

import com.google.common.base.Strings;
import com.google.common.cache.CacheLoader;
import com.mojang.realmsclient.RealmsMainScreen;
import com.mojang.realmsclient.dto.RealmsServer;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import journeymap.client.Constants;
import journeymap.client.JourneymapClient;
import journeymap.client.data.DataCache;
import journeymap.client.feature.Feature;
import journeymap.client.feature.FeatureManager;
import journeymap.client.log.JMLogger;
import journeymap.client.model.MapType;
import journeymap.client.model.RegionCoord;
import journeymap.common.Journeymap;
import journeymap.common.log.LogFormatter;
import journeymap.common.version.VersionCheck;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiScreenRealmsProxy;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.resources.ResourcePackRepository;
import net.minecraft.network.NetworkManager;
import net.minecraft.realms.RealmsScreen;
import net.minecraft.server.integrated.IntegratedServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.ReflectionHelper;
import org.apache.logging.log4j.Level;

public class WorldData
extends CacheLoader<Class, WorldData> {
    private static String DAYTIME = Constants.getString("jm.theme.labelsource.gametime.day");
    private static String SUNRISE = Constants.getString("jm.theme.labelsource.gametime.sunrise");
    private static String SUNSET = Constants.getString("jm.theme.labelsource.gametime.sunset");
    private static String NIGHT = Constants.getString("jm.theme.labelsource.gametime.night");
    String name;
    int dimension;
    long time;
    boolean hardcore;
    boolean singlePlayer;
    Map<Feature, Boolean> features;
    String jm_version;
    String latest_journeymap_version;
    String mc_version;
    String mod_name = JourneymapClient.MOD_NAME;
    String iconSetName;
    String[] iconSetNames;
    int browser_poll;

    public static boolean isHardcoreAndMultiplayer() {
        WorldData world = DataCache.INSTANCE.getWorld(false);
        return world.hardcore && !world.singlePlayer;
    }

    private static String getServerName() {
        try {
            Minecraft mc;
            String serverName;
            block9: {
                serverName = null;
                mc = FMLClientHandler.instance().getClient();
                if (!mc.func_71356_B()) {
                    try {
                        RealmsScreen realmsScreen;
                        NetHandlerPlayClient netHandler = mc.func_147114_u();
                        GuiScreen netHandlerGui = (GuiScreen)ReflectionHelper.getPrivateValue(NetHandlerPlayClient.class, (Object)netHandler, (String[])new String[]{"field_147307_j", "guiScreenServer"});
                        if (!(netHandlerGui instanceof GuiScreenRealmsProxy) || !((realmsScreen = ((GuiScreenRealmsProxy)netHandlerGui).func_154321_a()) instanceof RealmsMainScreen)) break block9;
                        RealmsMainScreen mainScreen = (RealmsMainScreen)realmsScreen;
                        long selectedServerId = (Long)ReflectionHelper.getPrivateValue(RealmsMainScreen.class, (Object)mainScreen, (String[])new String[]{"selectedServerId"});
                        List mcoServers = (List)ReflectionHelper.getPrivateValue(RealmsMainScreen.class, (Object)mainScreen, (String[])new String[]{"mcoServers"});
                        for (RealmsServer mcoServer : mcoServers) {
                            if (mcoServer.id != selectedServerId) continue;
                            serverName = mcoServer.name;
                            break;
                        }
                    }
                    catch (Throwable t) {
                        Journeymap.getLogger().error("Unable to get Realms server name: " + LogFormatter.toString(t));
                    }
                }
            }
            if (serverName != null) {
                return serverName;
            }
            mc = FMLClientHandler.instance().getClient();
            ServerData serverData = mc.func_147104_D();
            if (serverData != null && (serverName = serverData.field_78847_a) != null) {
                if (Strings.isNullOrEmpty((String)(serverName = serverName.replaceAll("\\W+", "~").trim()).replaceAll("~", ""))) {
                    serverName = serverData.field_78845_b;
                }
                return serverName;
            }
            return null;
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Couldn't get service name: " + LogFormatter.toString(t));
            return WorldData.getLegacyServerName();
        }
    }

    public static String getLegacyServerName() {
        try {
            SocketAddress socketAddress;
            NetworkManager netManager = FMLClientHandler.instance().getClientToServerNetworkManager();
            if (netManager != null && (socketAddress = netManager.func_74430_c()) != null && socketAddress instanceof InetSocketAddress) {
                InetSocketAddress inetAddr = (InetSocketAddress)socketAddress;
                return inetAddr.getHostName();
            }
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Couldn't get server name: " + LogFormatter.toString(t));
        }
        return "server";
    }

    /*
     * Enabled force condition propagation
     * Lifted jumps to return sites
     */
    public static String getWorldName(Minecraft mc, boolean useLegacyName) {
        String worldName = null;
        if (mc.func_71356_B()) {
            if (!useLegacyName) return mc.func_71401_C().func_71270_I();
            worldName = mc.func_71401_C().func_71221_J();
        } else {
            worldName = mc.field_71441_e.func_72912_H().func_76065_j();
            String serverName = WorldData.getServerName();
            if (serverName == null) {
                return "offline";
            }
            worldName = !"MpServer".equals(worldName) ? serverName + "_" + worldName : serverName;
        }
        worldName = useLegacyName ? WorldData.getLegacyUrlEncodedWorldName(worldName) : worldName.trim();
        if (!Strings.isNullOrEmpty((String)worldName.trim())) return worldName;
        return "unnamed";
    }

    private static String getLegacyUrlEncodedWorldName(String worldName) {
        try {
            return URLEncoder.encode(worldName, "UTF-8").replace("+", " ");
        }
        catch (UnsupportedEncodingException e) {
            return worldName;
        }
    }

    public static List<DimensionProvider> getDimensionProviders(List<Integer> requiredDimensionList) {
        try {
            HashSet<Integer> requiredDims = new HashSet<Integer>(requiredDimensionList);
            HashMap<Integer, DimensionProvider> dimProviders = new HashMap<Integer, DimensionProvider>();
            Level logLevel = Level.DEBUG;
            Journeymap.getLogger().log(logLevel, String.format("Required dimensions from waypoints: %s", requiredDimensionList));
            Integer[] dims = DimensionManager.getIDs();
            Journeymap.getLogger().log(logLevel, String.format("DimensionManager has dims: %s", Arrays.asList(dims)));
            requiredDims.addAll(Arrays.asList(dims));
            dims = DimensionManager.getStaticDimensionIDs();
            Journeymap.getLogger().log(logLevel, String.format("DimensionManager has static dims: %s", Arrays.asList(dims)));
            requiredDims.addAll(Arrays.asList(dims));
            Minecraft mc = FMLClientHandler.instance().getClient();
            WorldProvider playerProvider = mc.field_71439_g.field_70170_p.field_73011_w;
            int dimId = mc.field_71439_g.field_71093_bK;
            WrappedProvider playerDimProvider = new WrappedProvider(playerProvider);
            dimProviders.put(dimId, playerDimProvider);
            requiredDims.remove(dimId);
            Journeymap.getLogger().log(logLevel, String.format("Using player's provider for dim %s: %s", dimId, WorldData.getSafeDimensionName(playerDimProvider)));
            for (int dim : requiredDims) {
                WrappedProvider dimProvider;
                if (dimProviders.containsKey(dim)) continue;
                if (DimensionManager.getWorld((int)dim) != null) {
                    try {
                        WorldProvider worldProvider = DimensionManager.getProvider((int)dim);
                        worldProvider.func_186058_p().func_186065_b();
                        dimProvider = new WrappedProvider(worldProvider);
                        dimProviders.put(dim, dimProvider);
                        Journeymap.getLogger().log(logLevel, String.format("DimensionManager.getProvider(%s): %s", dim, WorldData.getSafeDimensionName(dimProvider)));
                    }
                    catch (Throwable t) {
                        JMLogger.logOnce(String.format("Couldn't DimensionManager.getProvider(%s) because of error: %s", dim, t), t);
                    }
                    continue;
                }
                try {
                    WorldProvider provider = DimensionManager.createProviderFor((int)dim);
                    provider.func_186058_p().func_186065_b();
                    provider.setDimension(dim);
                    dimProvider = new WrappedProvider(provider);
                    dimProviders.put(dim, dimProvider);
                    Journeymap.getLogger().log(logLevel, String.format("DimensionManager.createProviderFor(%s): %s", dim, WorldData.getSafeDimensionName(dimProvider)));
                }
                catch (Throwable t) {
                    JMLogger.logOnce(String.format("Couldn't DimensionManager.createProviderFor(%s) because of error: %s", dim, t), t);
                }
            }
            requiredDims.removeAll(dimProviders.keySet());
            for (int dim : requiredDims) {
                if (dimProviders.containsKey(dim)) continue;
                dimProviders.put(dim, new DummyProvider(dim));
                Journeymap.getLogger().warn(String.format("Used DummyProvider for required dim: %s", dim));
            }
            ArrayList<DimensionProvider> providerList = new ArrayList<DimensionProvider>(dimProviders.values());
            Collections.sort(providerList, new Comparator<DimensionProvider>(){

                @Override
                public int compare(DimensionProvider o1, DimensionProvider o2) {
                    return Integer.valueOf(o1.getDimension()).compareTo(o2.getDimension());
                }
            });
            return providerList;
        }
        catch (Throwable t) {
            Journeymap.getLogger().error("Unexpected error in WorldData.getDimensionProviders(): ", t);
            return Collections.emptyList();
        }
    }

    public static String getSafeDimensionName(DimensionProvider dimensionProvider) {
        if (dimensionProvider == null || dimensionProvider.getName() == null) {
            return null;
        }
        try {
            return dimensionProvider.getName();
        }
        catch (Exception e) {
            Minecraft mc = FMLClientHandler.instance().getClient();
            return Constants.getString("jm.common.dimension", mc.field_71441_e.field_73011_w.getDimension());
        }
    }

    public static String getDimension() {
        int dimId = Minecraft.func_71410_x().field_71439_g.field_71093_bK;
        String dimName = WorldData.getSafeDimensionName(new WrappedProvider(FMLClientHandler.instance().getClient().field_71439_g.field_70170_p.field_73011_w));
        return dimName + " (" + dimId + ")";
    }

    public WorldData load(Class aClass) throws Exception {
        Minecraft mc = FMLClientHandler.instance().getClient();
        WorldInfo worldInfo = mc.field_71441_e.func_72912_H();
        IntegratedServer server = mc.func_71401_C();
        boolean multiplayer = server == null || server.func_71344_c();
        this.name = WorldData.getWorldName(mc, false);
        this.dimension = mc.field_71441_e.field_73011_w.getDimension();
        this.hardcore = worldInfo.func_76093_s();
        this.singlePlayer = !multiplayer;
        this.time = mc.field_71441_e.func_72820_D() % 24000L;
        this.features = FeatureManager.getAllowedFeatures();
        this.mod_name = JourneymapClient.MOD_NAME;
        this.jm_version = Journeymap.JM_VERSION.toString();
        this.latest_journeymap_version = VersionCheck.getVersionAvailable();
        this.mc_version = (String)ResourcePackRepository.func_190115_a().get("X-Minecraft-Version");
        this.browser_poll = Math.max(1000, Journeymap.getClient().getCoreProperties().browserPoll.get());
        return this;
    }

    public static String getLightLevel() {
        BlockPos blockpos = Minecraft.func_71410_x().field_71439_g.func_180425_c();
        WorldClient world = Minecraft.func_71410_x().field_71441_e;
        Chunk chunk = world.func_175726_f(blockpos);
        int light = chunk.func_177443_a(blockpos, 0);
        int lightSky = chunk.func_177413_a(EnumSkyBlock.SKY, blockpos);
        int lightBlock = chunk.func_177413_a(EnumSkyBlock.BLOCK, blockpos);
        String lightLevels = String.format("Light: %s (%s sky, %s block)", light, lightSky, lightBlock);
        return lightLevels;
    }

    public static String getRegion() {
        BlockPos blockpos = Minecraft.func_71410_x().field_71439_g.func_180425_c();
        Chunk chunk = Minecraft.func_71410_x().field_71441_e.func_175726_f(blockpos);
        RegionCoord regionCoord = RegionCoord.fromChunkPos(null, MapType.none(), chunk.field_76635_g, chunk.field_76647_h);
        return "Region: x:" + regionCoord.regionX + " z:" + regionCoord.regionZ;
    }

    public static String getRealGameTime() {
        String format = Journeymap.getClient().getActiveMiniMapProperties().gameTimeRealFormat.get();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(format);
        Minecraft minecraft = Minecraft.func_71410_x();
        long time = minecraft.field_71441_e.func_72820_D();
        long hour = (time / 1000L + 6L) % 24L;
        long minute = time % 1000L * 60L / 1000L;
        double ticks = (double)time - Math.floor((double)time / 16.666666666666668) * 16.666666666666668;
        long seconds = (long)Math.floor(ticks / 0.2777777777777778);
        String timeString = String.format(Locale.ENGLISH, "%02d:%02d:%02d", hour, minute, seconds);
        return LocalTime.parse(timeString).format(dtf);
    }

    public static String getSystemTime() {
        SimpleDateFormat timeFormat = new SimpleDateFormat(Journeymap.getClient().getActiveMiniMapProperties().systemTimeRealFormat.get());
        return timeFormat.format(new Date());
    }

    public static String getGameTime() {
        long worldTime = FMLClientHandler.instance().getClient().field_71441_e.func_72820_D() % 24000L;
        String label = worldTime < 12000L ? DAYTIME : (worldTime < 13800L ? SUNSET : (worldTime < 22200L ? NIGHT : SUNRISE));
        long allSecs = worldTime / 20L;
        return String.format("%02d:%02d %s", (long)Math.floor(allSecs / 60L), (long)Math.ceil(allSecs % 60L), label);
    }

    public static boolean isDay(long worldTime) {
        return worldTime % 24000L < 13800L;
    }

    public static boolean isNight(long worldTime) {
        return worldTime % 24000L >= 13800L;
    }

    public long getTTL() {
        return 1000L;
    }

    static class DummyProvider
    implements DimensionProvider {
        final int dim;

        DummyProvider(int dim) {
            this.dim = dim;
        }

        @Override
        public int getDimension() {
            return this.dim;
        }

        @Override
        public String getName() {
            return "Dimension " + this.dim;
        }
    }

    public static class WrappedProvider
    implements DimensionProvider {
        WorldProvider worldProvider;

        public WrappedProvider(WorldProvider worldProvider) {
            this.worldProvider = worldProvider;
        }

        @Override
        public int getDimension() {
            return this.worldProvider.getDimension();
        }

        @Override
        public String getName() {
            return this.worldProvider.func_186058_p().func_186065_b();
        }
    }

    public static interface DimensionProvider {
        public int getDimension();

        public String getName();
    }
}

