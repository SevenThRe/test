/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  net.minecraft.util.math.BlockPos
 */
package journeymap.client.api.model;

import com.google.common.base.MoreObjects;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.util.math.BlockPos;

public final class MapPolygon {
    private List<BlockPos> points;

    public MapPolygon(BlockPos ... points) {
        this(Arrays.asList(points));
    }

    public MapPolygon(List<BlockPos> points) {
        this.setPoints(points);
    }

    public List<BlockPos> getPoints() {
        return this.points;
    }

    public MapPolygon setPoints(List<BlockPos> points) {
        if (points.size() < 3) {
            throw new IllegalArgumentException("MapPolygon must have at least 3 points.");
        }
        this.points = Collections.unmodifiableList(points);
        return this;
    }

    public Iterator<BlockPos> iterator() {
        return this.points.iterator();
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("points", this.points).toString();
    }
}

