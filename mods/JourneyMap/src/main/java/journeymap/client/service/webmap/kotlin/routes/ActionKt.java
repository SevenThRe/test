/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  net.minecraft.client.Minecraft
 *  net.minecraft.world.World
 *  net.minecraft.world.storage.WorldInfo
 *  net.minecraftforge.fml.client.FMLClientHandler
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.service.webmap.kotlin.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import journeymap.client.JourneymapClient;
import journeymap.client.io.FileHandler;
import journeymap.client.io.MapSaver;
import journeymap.client.model.MapType;
import journeymap.client.task.multi.MapRegionTask;
import journeymap.client.task.multi.SaveMapTask;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import net.minecraftforge.fml.client.FMLClientHandler;
import org.apache.logging.log4j.Logger;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000*\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0000\u001a \u0010\b\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a \u0010\r\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000e"}, d2={"GSON", "Lcom/google/gson/Gson;", "logger", "Lorg/apache/logging/log4j/Logger;", "actionGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "autoMap", "minecraft", "Lnet/minecraft/client/Minecraft;", "world", "Lnet/minecraft/world/World;", "saveMap", "journeymap"})
public final class ActionKt {
    private static final Gson GSON;
    private static final Logger logger;

    @NotNull
    public static final Object actionGet(@NotNull RouteHandler handler) {
        Object object;
        String type;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        FMLClientHandler fMLClientHandler = FMLClientHandler.instance();
        Intrinsics.checkExpressionValueIsNotNull(fMLClientHandler, "FMLClientHandler.instance()");
        Minecraft minecraft = fMLClientHandler.getClient();
        Intrinsics.checkExpressionValueIsNotNull(minecraft, "FMLClientHandler.instance().client");
        Minecraft minecraft2 = minecraft;
        World world = (World)minecraft2.field_71441_e;
        if (world == null) {
            logger.warn("Action requested before world loaded");
            handler.status(400);
            return "World not loaded";
        }
        JourneymapClient journeymapClient = Journeymap.getClient();
        Intrinsics.checkExpressionValueIsNotNull(journeymapClient, "Journeymap.getClient()");
        if (!journeymapClient.isMapping().booleanValue()) {
            logger.warn("Action requested before Journeymap started");
            handler.status(400);
            return "JourneyMap is still starting";
        }
        switch (type = handler.params("type")) {
            case "automap": {
                object = ActionKt.autoMap(handler, minecraft2, world);
                break;
            }
            case "savemap": {
                object = ActionKt.saveMap(handler, minecraft2, world);
                break;
            }
            default: {
                logger.warn("Unknown action type '" + type + '\'');
                handler.status(400);
                object = "Unknown action type '" + type + '\'';
            }
        }
        return object;
    }

    @NotNull
    public static final Object saveMap(@NotNull RouteHandler handler, @NotNull Minecraft minecraft, @NotNull World world) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Intrinsics.checkParameterIsNotNull(minecraft, "minecraft");
        Intrinsics.checkParameterIsNotNull(world, "world");
        File file = FileHandler.getJMWorldDir(minecraft);
        Intrinsics.checkExpressionValueIsNotNull(file, "FileHandler.getJMWorldDir(minecraft)");
        File worldDir = file;
        if (!worldDir.exists() || !worldDir.isDirectory()) {
            logger.warn("JM world directory not found");
            handler.status(500);
            return "Unable to find JourneyMap world directory";
        }
        String string = handler.getRequest().queryParamOrDefault("dim", "0");
        Intrinsics.checkExpressionValueIsNotNull(string, "handler.request.queryParamOrDefault(\"dim\", \"0\")");
        String string2 = string;
        boolean bl = false;
        int dimension = Integer.parseInt(string2);
        String string3 = handler.getRequest().queryParamOrDefault("mapType", MapType.Name.day.name());
        Intrinsics.checkExpressionValueIsNotNull(string3, "handler.request.queryPar\u2026\", MapType.Name.day.name)");
        String mapTypeString = string3;
        Integer vSlice = handler.queryMap("depth").integerValue();
        MapType.Name mapTypeName = null;
        try {
            mapTypeName = MapType.Name.valueOf(mapTypeString);
        }
        catch (IllegalArgumentException e) {
            logger.warn("Invalid map type '" + mapTypeString + '\'');
            handler.status(400);
            return "Invalid map type '" + mapTypeString + '\'';
        }
        if (mapTypeName != MapType.Name.underground) {
            vSlice = null;
        }
        WorldInfo worldInfo = world.func_72912_H();
        Intrinsics.checkExpressionValueIsNotNull(worldInfo, "world.worldInfo");
        boolean hardcore = worldInfo.func_76093_s();
        MapType mapType = MapType.from(mapTypeName, vSlice, dimension);
        Intrinsics.checkExpressionValueIsNotNull(mapType, "MapType.from(mapTypeName, vSlice, dimension)");
        MapType mapType2 = mapType;
        if (mapType2.isUnderground() && hardcore) {
            logger.warn("Cave mapping is not allowed on hardcore servers");
            handler.status(400);
            return "Cave mapping is not allowed on hardcore servers";
        }
        MapSaver mapSaver = new MapSaver(worldDir, mapType2);
        if (!mapSaver.isValid()) {
            logger.info("No image files to save");
            handler.status(400);
            return "No image files to save";
        }
        Journeymap.getClient().toggleTask(SaveMapTask.Manager.class, true, mapSaver);
        boolean bl2 = false;
        Map data = new LinkedHashMap();
        String string4 = mapSaver.getSaveFileName();
        Intrinsics.checkExpressionValueIsNotNull(string4, "mapSaver.saveFileName");
        data.put("filename", string4);
        HttpServletResponse httpServletResponse = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
        httpServletResponse.setContentType("application/json");
        String string5 = GSON.toJson((Object)data);
        Intrinsics.checkExpressionValueIsNotNull(string5, "GSON.toJson(data)");
        return string5;
    }

    @NotNull
    public static final Object autoMap(@NotNull RouteHandler handler, @NotNull Minecraft minecraft, @NotNull World world) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Intrinsics.checkParameterIsNotNull(minecraft, "minecraft");
        Intrinsics.checkParameterIsNotNull(world, "world");
        boolean bl = false;
        Map data = new LinkedHashMap();
        boolean enabled = Journeymap.getClient().isTaskManagerEnabled(MapRegionTask.Manager.class);
        String string = handler.getRequest().queryParamOrDefault("scope", "stop");
        Intrinsics.checkExpressionValueIsNotNull(string, "handler.request.queryPar\u2026rDefault(\"scope\", \"stop\")");
        String scope = string;
        if (Intrinsics.areEqual(scope, "stop") && enabled) {
            Journeymap.getClient().toggleTask(MapRegionTask.Manager.class, false, false);
            data.put("message", "automap_complete");
        } else if (!enabled) {
            boolean doAll = Intrinsics.areEqual(scope, "all");
            Journeymap.getClient().toggleTask(MapRegionTask.Manager.class, true, doAll);
            data.put("message", "automap_started");
        } else {
            data.put("message", "automap_already_started");
        }
        HttpServletResponse httpServletResponse = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
        httpServletResponse.setContentType("application/json");
        String string2 = GSON.toJson((Object)data);
        Intrinsics.checkExpressionValueIsNotNull(string2, "GSON.toJson(data)");
        return string2;
    }

    static {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Intrinsics.checkExpressionValueIsNotNull(gson, "GsonBuilder().setPrettyPrinting().create()");
        GSON = gson;
        Logger logger = Journeymap.getLogger("webmap/routes/action");
        Intrinsics.checkExpressionValueIsNotNull(logger, "Journeymap.getLogger(\"webmap/routes/action\")");
        ActionKt.logger = logger;
    }
}

