/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.service.webmap.kotlin.routes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import info.journeymap.shaded.kotlin.kotlin.Metadata;
import info.journeymap.shaded.kotlin.kotlin.Pair;
import info.journeymap.shaded.kotlin.kotlin.TuplesKt;
import info.journeymap.shaded.kotlin.kotlin.TypeCastException;
import info.journeymap.shaded.kotlin.kotlin.collections.MapsKt;
import info.journeymap.shaded.kotlin.kotlin.jvm.internal.Intrinsics;
import info.journeymap.shaded.kotlin.spark.kotlin.RouteHandler;
import info.journeymap.shaded.org.javax.servlet.http.HttpServletResponse;
import info.journeymap.shaded.org.jetbrains.annotations.NotNull;
import java.util.ArrayList;
import java.util.List;
import journeymap.client.api.display.Context;
import journeymap.client.api.display.Overlay;
import journeymap.client.api.display.PolygonOverlay;
import journeymap.client.api.impl.ClientAPI;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;
import journeymap.client.api.util.UIState;
import journeymap.client.cartography.color.RGB;
import journeymap.client.render.draw.DrawPolygonStep;
import journeymap.client.render.draw.OverlayDrawStep;
import net.minecraft.util.math.BlockPos;

@Metadata(mv={1, 1, 16}, bv={1, 0, 3}, k=2, d1={"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2={"GSON", "Lcom/google/gson/Gson;", "polygonsGet", "", "handler", "Linfo/journeymap/shaded/kotlin/spark/kotlin/RouteHandler;", "journeymap"})
public final class PolygonsKt {
    private static final Gson GSON;

    @NotNull
    public static final Object polygonsGet(@NotNull RouteHandler handler) {
        UIState uiState;
        Intrinsics.checkParameterIsNotNull(handler, "handler");
        boolean bl = false;
        List data = new ArrayList();
        boolean bl2 = false;
        List steps = new ArrayList();
        UIState uIState = uiState = ClientAPI.INSTANCE.getUIState(Context.UI.Fullscreen);
        if (uIState == null) {
            Intrinsics.throwNpe();
        }
        ClientAPI.INSTANCE.getDrawSteps(steps, uIState);
        for (OverlayDrawStep step : steps) {
            if (!(step instanceof DrawPolygonStep)) continue;
            Overlay overlay = ((DrawPolygonStep)step).overlay;
            if (overlay == null) {
                throw new TypeCastException("null cannot be cast to non-null type journeymap.client.api.display.PolygonOverlay");
            }
            PolygonOverlay polygon = (PolygonOverlay)overlay;
            boolean bl3 = false;
            List points = new ArrayList();
            MapPolygon mapPolygon = polygon.getOuterArea();
            Intrinsics.checkExpressionValueIsNotNull(mapPolygon, "polygon.outerArea");
            List<BlockPos> list = mapPolygon.getPoints();
            Intrinsics.checkExpressionValueIsNotNull(list, "polygon.outerArea.points");
            Iterable $this$forEach$iv = list;
            boolean $i$f$forEach = false;
            for (Object element$iv : $this$forEach$iv) {
                BlockPos point = (BlockPos)element$iv;
                boolean bl4 = false;
                Pair[] pairArray = new Pair[3];
                BlockPos blockPos = point;
                Intrinsics.checkExpressionValueIsNotNull(blockPos, "point");
                pairArray[0] = TuplesKt.to("x", blockPos.func_177958_n());
                pairArray[1] = TuplesKt.to("y", point.func_177956_o());
                pairArray[2] = TuplesKt.to("z", point.func_177952_p());
                points.add(MapsKt.mapOf(pairArray));
            }
            $i$f$forEach = false;
            List holes = new ArrayList();
            if (polygon.getHoles() != null) {
                for (MapPolygon hole : polygon.getHoles()) {
                    boolean point = false;
                    List holePoints = new ArrayList();
                    MapPolygon mapPolygon2 = hole;
                    Intrinsics.checkExpressionValueIsNotNull(mapPolygon2, "hole");
                    for (BlockPos holePoint : mapPolygon2.getPoints()) {
                        Pair[] pairArray = new Pair[3];
                        BlockPos blockPos = holePoint;
                        Intrinsics.checkExpressionValueIsNotNull(blockPos, "holePoint");
                        pairArray[0] = TuplesKt.to("x", blockPos.func_177958_n());
                        pairArray[1] = TuplesKt.to("y", holePoint.func_177956_o());
                        pairArray[2] = TuplesKt.to("z", holePoint.func_177952_p());
                        holePoints.add(MapsKt.mapOf(pairArray));
                    }
                    holes.add(holePoints);
                }
            }
            Pair[] pairArray = new Pair[7];
            ShapeProperties shapeProperties = polygon.getShapeProperties();
            Intrinsics.checkExpressionValueIsNotNull(shapeProperties, "polygon.shapeProperties");
            pairArray[0] = TuplesKt.to("fillColor", RGB.toHexString(shapeProperties.getFillColor()));
            ShapeProperties shapeProperties2 = polygon.getShapeProperties();
            Intrinsics.checkExpressionValueIsNotNull(shapeProperties2, "polygon.shapeProperties");
            pairArray[1] = TuplesKt.to("fillOpacity", Float.valueOf(shapeProperties2.getFillOpacity()));
            ShapeProperties shapeProperties3 = polygon.getShapeProperties();
            Intrinsics.checkExpressionValueIsNotNull(shapeProperties3, "polygon.shapeProperties");
            pairArray[2] = TuplesKt.to("strokeColor", RGB.toHexString(shapeProperties3.getStrokeColor()));
            ShapeProperties shapeProperties4 = polygon.getShapeProperties();
            Intrinsics.checkExpressionValueIsNotNull(shapeProperties4, "polygon.shapeProperties");
            pairArray[3] = TuplesKt.to("strokeOpacity", Float.valueOf(shapeProperties4.getStrokeOpacity()));
            ShapeProperties shapeProperties5 = polygon.getShapeProperties();
            Intrinsics.checkExpressionValueIsNotNull(shapeProperties5, "polygon.shapeProperties");
            pairArray[4] = TuplesKt.to("strokeWidth", Float.valueOf(shapeProperties5.getStrokeWidth()));
            pairArray[5] = TuplesKt.to("holes", holes);
            pairArray[6] = TuplesKt.to("points", points);
            data.add(MapsKt.mapOf(pairArray));
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
    }
}

