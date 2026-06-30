/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.annotation.Nullable
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.api.display;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.display.Overlay;
import journeymap.client.api.model.MapPolygon;
import journeymap.client.api.model.ShapeProperties;

@ParametersAreNonnullByDefault
public final class PolygonOverlay
extends Overlay {
    private MapPolygon outerArea;
    private List<MapPolygon> holes;
    private ShapeProperties shapeProperties;

    public PolygonOverlay(String modId, String displayId, int dimension, ShapeProperties shapeProperties, MapPolygon outerArea) {
        this(modId, displayId, dimension, shapeProperties, outerArea, null);
    }

    public PolygonOverlay(String modId, String displayId, int dimension, ShapeProperties shapeProperties, MapPolygon outerArea, @Nullable List<MapPolygon> holes) {
        super(modId, displayId);
        this.setDimension(dimension);
        this.setShapeProperties(shapeProperties);
        this.setOuterArea(outerArea);
        this.setHoles(holes);
    }

    public MapPolygon getOuterArea() {
        return this.outerArea;
    }

    public PolygonOverlay setOuterArea(MapPolygon outerArea) {
        this.outerArea = outerArea;
        return this;
    }

    public List<MapPolygon> getHoles() {
        return this.holes;
    }

    public PolygonOverlay setHoles(@Nullable List<MapPolygon> holes) {
        this.holes = holes == null ? null : new ArrayList<MapPolygon>(holes);
        return this;
    }

    public ShapeProperties getShapeProperties() {
        return this.shapeProperties;
    }

    public PolygonOverlay setShapeProperties(ShapeProperties shapeProperties) {
        this.shapeProperties = shapeProperties;
        return this;
    }

    public String toString() {
        return this.toStringHelper(this).add("holes", this.holes).add("outerArea", (Object)this.outerArea).add("shapeProperties", (Object)this.shapeProperties).toString();
    }
}

