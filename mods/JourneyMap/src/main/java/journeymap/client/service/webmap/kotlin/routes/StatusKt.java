/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  net.minecraft.client.Minecraft
 */
package journeymap.client.service.webmap.kotlin.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.TuplesKt;
import info.journeymap.shaded.kotlin.kotlin.collections.MapsKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.LinkedHashMap;
import java.util.Map;
import journeymap.client.JourneymapClient;
import journeymap.client.model.MapState;
import journeymap.client.service.webmap.kotlin.enums.WebmapStatus;
import journeymap.client.ui.minimap.MiniMap;
import journeymap.common.Journeymap;
import net.minecraft.client.Minecraft;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"GSON", "Lcom/google/gson/Gson;", "statusGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "journeymap"})
public final class StatusKt {
    private static final Gson GSON;

    @NotNull
    public static final Object statusGet(@NotNull RouteHandler handler) {
        WebmapStatus status;
        WebmapStatus webmapStatus;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        boolean bl = false;
        Map data = new LinkedHashMap();
        if (Minecraft.func_71410_x().field_71441_e == null) {
            webmapStatus = WebmapStatus.NO_WORLD;
        } else {
            JourneymapClient journeymapClient = Journeymap.getClient();
            Intrinsics.checkExpressionValueIsNotNull(journeymapClient, "Journeymap.getClient()");
            webmapStatus = status = journeymapClient.isMapping() == false ? WebmapStatus.STARTING : WebmapStatus.READY;
        }
        if (status == WebmapStatus.READY) {
            Map allowedMapTypes;
            MapState mapState;
            MapState mapState2 = mapState = MiniMap.state();
            Intrinsics.checkExpressionValueIsNotNull(mapState2, "mapState");
            String string = mapState2.getMapType().name();
            Intrinsics.checkExpressionValueIsNotNull(string, "mapState.mapType.name()");
            data.put("mapType", string);
            Map $this$filterValues$iv = allowedMapTypes = MapsKt.mapOf(TuplesKt.to("cave", mapState.isCaveMappingAllowed() && mapState.isCaveMappingEnabled()), TuplesKt.to("surface", mapState.isSurfaceMappingAllowed()), TuplesKt.to("topo", mapState.isTopoMappingAllowed()));
            boolean $i$f$filterValues = false;
            LinkedHashMap result$iv = new LinkedHashMap();
            Map map = $this$filterValues$iv;
            boolean bl2 = false;
            for (Map.Entry entry$iv : map.entrySet()) {
                boolean it = (Boolean)entry$iv.getValue();
                boolean bl3 = false;
                if (!it) continue;
                result$iv.put(entry$iv.getKey(), entry$iv.getValue());
            }
            if (((Map)result$iv).isEmpty()) {
                status = WebmapStatus.DISABLED;
            }
            data.put("allowedMapTypes", allowedMapTypes);
        }
        data.put("status", status.getStatus());
        String string = GSON.toJson((Object)data);
        Intrinsics.checkExpressionValueIsNotNull(string, "GSON.toJson(data)");
        return string;
    }

    static {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Intrinsics.checkExpressionValueIsNotNull(gson, "GsonBuilder().setPrettyPrinting().create()");
        GSON = gson;
    }
}

