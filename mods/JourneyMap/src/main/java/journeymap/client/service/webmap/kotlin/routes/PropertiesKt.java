/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.service.webmap.kotlin.routes;

import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.collections.MapsKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import info.journeymap.shaded.org.jetbrains.annotations.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import journeymap.client.JourneymapClient;
import journeymap.client.properties.WebMapProperties;
import journeymap.common.Journeymap;
import journeymap.common.properties.config.BooleanField;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000(\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\u001a\u0010\u0010\u0010\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000\"(\u0010\u0000\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0001X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0004\u0010\u0005\"\u0004\b\u0006\u0010\u0007\"\u0011\u0010\b\u001a\u00020\t\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0011"}, d2={"propertiesMap", "", "", "Ljourneymap/common/properties/config/BooleanField;", "getPropertiesMap", "()Ljava/util/Map;", "setPropertiesMap", "(Ljava/util/Map;)V", "webMapProperties", "Ljourneymap/client/properties/WebMapProperties;", "getWebMapProperties", "()Ljourneymap/client/properties/WebMapProperties;", "propertiesGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "propertiesPost", "journeymap"})
public final class PropertiesKt {
    @NotNull
    private static final WebMapProperties webMapProperties;
    @Nullable
    private static Map<String, ? extends BooleanField> propertiesMap;

    @NotNull
    public static final WebMapProperties getWebMapProperties() {
        return webMapProperties;
    }

    @Nullable
    public static final Map<String, BooleanField> getPropertiesMap() {
        return propertiesMap;
    }

    public static final void setPropertiesMap(@Nullable Map<String, ? extends BooleanField> map) {
        propertiesMap = map;
    }

    @NotNull
    public static final Object propertiesGet(@NotNull RouteHandler handler) {
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        HttpServletResponse httpServletResponse = handler.getResponse().raw();
        Intrinsics.checkExpressionValueIsNotNull(httpServletResponse, "handler.response.raw()");
        httpServletResponse.setContentType("application/json");
        JourneymapClient journeymapClient = Journeymap.getClient();
        Intrinsics.checkExpressionValueIsNotNull(journeymapClient, "Journeymap.getClient()");
        String string = journeymapClient.getWebMapProperties().toJsonString(true);
        Intrinsics.checkExpressionValueIsNotNull(string, "Journeymap.getClient().w\u2026erties.toJsonString(true)");
        return string;
    }

    @NotNull
    public static final Object propertiesPost(@NotNull RouteHandler handler) {
        block9: {
            block8: {
                Intrinsics.checkParameterIsNotNull(handler, "handler");
                if (propertiesMap == null) break block8;
                Map<String, ? extends BooleanField> map = propertiesMap;
                if (map == null) {
                    Intrinsics.throwNpe();
                }
                if (!map.isEmpty()) break block9;
            }
            JourneymapClient journeymapClient = Journeymap.getClient();
            Intrinsics.checkExpressionValueIsNotNull(journeymapClient, "Journeymap.getClient()");
            WebMapProperties webMapProperties = journeymapClient.getWebMapProperties();
            Intrinsics.checkExpressionValueIsNotNull(webMapProperties, "Journeymap.getClient().webMapProperties");
            WebMapProperties properties = webMapProperties;
            boolean bl = false;
            Map propMap = new LinkedHashMap();
            BooleanField booleanField = properties.showCaves;
            Intrinsics.checkExpressionValueIsNotNull(booleanField, "properties.showCaves");
            propMap.put("showCaves", booleanField);
            BooleanField booleanField2 = properties.showEntityNames;
            Intrinsics.checkExpressionValueIsNotNull(booleanField2, "properties.showEntityNames");
            propMap.put("showEntityNames", booleanField2);
            BooleanField booleanField3 = properties.showGrid;
            Intrinsics.checkExpressionValueIsNotNull(booleanField3, "properties.showGrid");
            propMap.put("showGrid", booleanField3);
            BooleanField booleanField4 = properties.showSelf;
            Intrinsics.checkExpressionValueIsNotNull(booleanField4, "properties.showSelf");
            propMap.put("showSelf", booleanField4);
            BooleanField booleanField5 = properties.showWaypoints;
            Intrinsics.checkExpressionValueIsNotNull(booleanField5, "properties.showWaypoints");
            propMap.put("showWaypoints", booleanField5);
            propertiesMap = MapsKt.toMap(propMap);
        }
        for (String key : handler.queryMap().toMap().keySet()) {
            String string;
            if (propertiesMap == null) {
                Intrinsics.throwNpe();
            }
            boolean bl = false;
            Map<String, ? extends BooleanField> map = string;
            boolean bl2 = false;
            if (!map.containsKey(key)) continue;
            Map<String, ? extends BooleanField> map2 = propertiesMap;
            if (map2 == null) {
                Intrinsics.throwNpe();
            }
            BooleanField booleanField = map2.get(key);
            if (booleanField == null) {
                string = "Properties value for " + key + " is null";
                bl = false;
                throw (Throwable)new IllegalStateException(string.toString());
            }
            String string2 = key;
            Intrinsics.checkExpressionValueIsNotNull(string2, "key");
            booleanField.set(handler.queryMap(string2).booleanValue());
        }
        return webMapProperties.save();
    }

    static {
        JourneymapClient journeymapClient = Journeymap.getClient();
        Intrinsics.checkExpressionValueIsNotNull(journeymapClient, "Journeymap.getClient()");
        WebMapProperties webMapProperties = journeymapClient.getWebMapProperties();
        Intrinsics.checkExpressionValueIsNotNull(webMapProperties, "Journeymap.getClient().webMapProperties");
        PropertiesKt.webMapProperties = webMapProperties;
    }
}

