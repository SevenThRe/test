/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  org.apache.logging.log4j.Logger
 */
package journeymap.client.service.webmap.kotlin.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.CollectionsKt;
import info.journeymap.shaded.kotlin.kotlin.collections.MapsKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import journeymap.client.data.DataCache;
import journeymap.client.data.ImagesData;
import journeymap.client.model.Waypoint;
import journeymap.common.Journeymap;
import org.apache.logging.log4j.Logger;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2={"GSON", "Lcom/google/gson/Gson;", "dataTypesRequiringSince", "", "", "getDataTypesRequiringSince", "()Ljava/util/List;", "logger", "Lorg/apache/logging/log4j/Logger;", "dataGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "journeymap"})
public final class DataKt {
    private static final Gson GSON;
    private static final Logger logger;
    @NotNull
    private static final List<String> dataTypesRequiringSince;

    @NotNull
    public static final List<String> getDataTypesRequiringSince() {
        return dataTypesRequiringSince;
    }

    @NotNull
    public static final Object dataGet(@NotNull RouteHandler handler) {
        ImagesData data;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        Long since = handler.queryMap("images.since").longValue();
        String type = handler.params("type");
        if (dataTypesRequiringSince.contains(type) && since == null) {
            logger.warn("Data type '" + type + "' requested without 'images.since' parameter");
            handler.status(400);
            return "Data type '" + type + "' requires 'images.since' parameter.";
        }
        switch (type) {
            case "all": {
                Long l = since;
                if (l == null) {
                    Intrinsics.throwNpe();
                }
                Object object = DataCache.INSTANCE.getAll(l);
                break;
            }
            case "animals": {
                Object object = DataCache.INSTANCE.getAnimals(false);
                break;
            }
            case "mobs": {
                Object object = DataCache.INSTANCE.getMobs(false);
                break;
            }
            case "images": {
                Long l = since;
                if (l == null) {
                    Intrinsics.throwNpe();
                }
                Object object = new ImagesData(l);
                break;
            }
            case "messages": {
                Object object = DataCache.INSTANCE.getMessages(false);
                break;
            }
            case "player": {
                Object object = DataCache.INSTANCE.getPlayer(false);
                break;
            }
            case "players": {
                Object object = DataCache.INSTANCE.getPlayers(false);
                break;
            }
            case "world": {
                Object object = DataCache.INSTANCE.getWorld(false);
                break;
            }
            case "villagers": {
                Object object = DataCache.INSTANCE.getVillagers(false);
                break;
            }
            case "waypoints": {
                Collection<Waypoint> collection = DataCache.INSTANCE.getWaypoints(false);
                Intrinsics.checkExpressionValueIsNotNull(collection, "DataCache.INSTANCE.getWaypoints(false)");
                Collection<Waypoint> waypoints = collection;
                boolean bl = false;
                Map wpMap = new LinkedHashMap();
                for (Waypoint waypoint : waypoints) {
                    String string = waypoint.getId();
                    Intrinsics.checkExpressionValueIsNotNull(string, "waypoint.id");
                    wpMap.put(string, waypoint);
                }
                Object object = MapsKt.toMap(wpMap);
                break;
            }
            default: {
                Object object = data = null;
            }
        }
        if (data == null) {
            logger.warn("Unknown data type '" + type + '\'');
            handler.status(400);
            return "Unknown data type '" + type + '\'';
        }
        HttpServletResponse httpServletResponse = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
        httpServletResponse.setContentType("application/json");
        String string = GSON.toJson((Object)data);
        Intrinsics.checkExpressionValueIsNotNull(string, "GSON.toJson(data)");
        return string;
    }

    static {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Intrinsics.checkExpressionValueIsNotNull(gson, "GsonBuilder().setPrettyPrinting().create()");
        GSON = gson;
        Logger logger = Journeymap.getLogger("webmap/routes/data");
        Intrinsics.checkExpressionValueIsNotNull(logger, "Journeymap.getLogger(\"webmap/routes/data\")");
        DataKt.logger = logger;
        dataTypesRequiringSince = CollectionsKt.listOf("all", "images");
    }
}

