/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Objects
 */
package journeymap.client.api.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import journeymap.client.api.display.Displayable;

public class ShapeProperties {
    private int strokeColor = 0;
    private int fillColor = 0;
    private float strokeOpacity = 1.0f;
    private float fillOpacity = 0.5f;
    private float strokeWidth = 2.0f;

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public ShapeProperties setStrokeColor(int strokeColor) {
        this.strokeColor = Displayable.clampRGB(strokeColor);
        return this;
    }

    public int getFillColor() {
        return this.fillColor;
    }

    public ShapeProperties setFillColor(int fillColor) {
        this.fillColor = Displayable.clampRGB(fillColor);
        return this;
    }

    public float getStrokeOpacity() {
        return this.strokeOpacity;
    }

    public ShapeProperties setStrokeOpacity(float strokeOpacity) {
        this.strokeOpacity = Displayable.clampOpacity(strokeOpacity);
        return this;
    }

    public float getFillOpacity() {
        return this.fillOpacity;
    }

    public ShapeProperties setFillOpacity(float fillOpacity) {
        this.fillOpacity = Displayable.clampOpacity(fillOpacity);
        return this;
    }

    public float getStrokeWidth() {
        return this.strokeWidth;
    }

    public ShapeProperties setStrokeWidth(float strokeWidth) {
        this.strokeWidth = strokeWidth;
        return this;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ShapeProperties)) {
            return false;
        }
        ShapeProperties that = (ShapeProperties)o;
        return Objects.equal((Object)this.strokeColor, (Object)that.strokeColor) && Objects.equal((Object)this.fillColor, (Object)that.fillColor) && Objects.equal((Object)Float.valueOf(this.strokeOpacity), (Object)Float.valueOf(that.strokeOpacity)) && Objects.equal((Object)Float.valueOf(this.fillOpacity), (Object)Float.valueOf(that.fillOpacity)) && Objects.equal((Object)Float.valueOf(this.strokeWidth), (Object)Float.valueOf(that.strokeWidth));
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.strokeColor, this.fillColor, Float.valueOf(this.strokeOpacity), Float.valueOf(this.fillOpacity), Float.valueOf(this.strokeWidth)});
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("fillColor", this.fillColor).add("fillOpacity", this.fillOpacity).add("strokeColor", this.strokeColor).add("strokeOpacity", this.strokeOpacity).add("strokeWidth", this.strokeWidth).toString();
    }
}

