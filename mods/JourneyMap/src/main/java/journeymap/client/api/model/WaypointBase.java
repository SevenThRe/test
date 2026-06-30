/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Objects
 *  com.google.common.base.Strings
 *  com.google.gson.annotations.Since
 *  javax.annotation.Nullable
 *  org.apache.commons.lang3.ArrayUtils
 */
package journeymap.client.api.model;

import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.gson.annotations.Since;
import java.util.Arrays;
import javax.annotation.Nullable;
import journeymap.client.api.display.Displayable;
import journeymap.client.api.display.IWaypointDisplay;
import journeymap.client.api.model.MapImage;
import org.apache.commons.lang3.ArrayUtils;

public abstract class WaypointBase<T extends WaypointBase>
extends Displayable
implements IWaypointDisplay {
    @Since(value=1.4)
    protected String name;
    @Since(value=1.4)
    protected Integer color;
    @Since(value=1.4)
    protected Integer bgColor;
    @Since(value=1.4)
    protected MapImage icon;
    @Since(value=1.4)
    protected int[] displayDims;
    @Since(value=1.4)
    protected transient boolean dirty;

    protected WaypointBase(String modId, String name) {
        super(modId);
        this.setName(name);
    }

    protected WaypointBase(String modId, String id, String name) {
        super(modId, id);
        this.setName(name);
    }

    protected abstract IWaypointDisplay getDelegate();

    protected abstract boolean hasDelegate();

    public final String getName() {
        return this.name;
    }

    public final T setName(String name) {
        if (Strings.isNullOrEmpty((String)name)) {
            throw new IllegalArgumentException("name may not be blank");
        }
        this.name = name;
        return this.setDirty();
    }

    @Override
    public final Integer getColor() {
        if (this.color == null && this.hasDelegate()) {
            return this.getDelegate().getColor();
        }
        return this.color;
    }

    public final T setColor(int color) {
        this.color = WaypointBase.clampRGB(color);
        return this.setDirty();
    }

    public final T clearColor() {
        this.color = null;
        return this.setDirty();
    }

    @Override
    public final Integer getBackgroundColor() {
        if (this.bgColor == null && this.hasDelegate()) {
            return this.getDelegate().getBackgroundColor();
        }
        return this.bgColor;
    }

    public final T setBackgroundColor(int bgColor) {
        this.bgColor = WaypointBase.clampRGB(bgColor);
        return this.setDirty();
    }

    public final T clearBackgroundColor() {
        this.bgColor = null;
        return this.setDirty();
    }

    @Override
    public int[] getDisplayDimensions() {
        if (this.displayDims == null && this.hasDelegate()) {
            return this.getDelegate().getDisplayDimensions();
        }
        return this.displayDims;
    }

    public final T setDisplayDimensions(int ... dimensions) {
        this.displayDims = dimensions;
        return this.setDirty();
    }

    public final T clearDisplayDimensions() {
        this.displayDims = null;
        return this.setDirty();
    }

    public void setDisplayed(int dimension, boolean displayed) {
        if (displayed && !this.isDisplayed(dimension)) {
            this.setDisplayDimensions(ArrayUtils.add((int[])this.getDisplayDimensions(), (int)dimension));
        } else if (!displayed && this.isDisplayed(dimension)) {
            this.setDisplayDimensions(ArrayUtils.removeElement((int[])this.getDisplayDimensions(), (int)dimension));
        }
    }

    public final boolean isDisplayed(int dimension) {
        return Arrays.binarySearch(this.getDisplayDimensions(), dimension) > -1;
    }

    @Override
    public MapImage getIcon() {
        if (this.icon == null && this.hasDelegate()) {
            return this.getDelegate().getIcon();
        }
        return this.icon;
    }

    public final T setIcon(@Nullable MapImage icon) {
        this.icon = icon;
        return this.setDirty();
    }

    public final T clearIcon() {
        this.icon = null;
        return this.setDirty();
    }

    public boolean isDirty() {
        return this.dirty;
    }

    public T setDirty(boolean dirty) {
        this.dirty = dirty;
        return (T)this;
    }

    public T setDirty() {
        return this.setDirty(true);
    }

    public boolean hasIcon() {
        return this.icon != null;
    }

    public boolean hasColor() {
        return this.color != null;
    }

    public boolean hasBackgroundColor() {
        return this.bgColor != null;
    }

    public boolean hasDisplayDimensions() {
        return this.displayDims != null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WaypointBase)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        WaypointBase that = (WaypointBase)o;
        return Objects.equal((Object)this.getName(), (Object)that.getName()) && Objects.equal((Object)this.getIcon(), (Object)that.getIcon()) && Objects.equal((Object)this.getColor(), (Object)that.getColor()) && Objects.equal((Object)this.getBackgroundColor(), (Object)that.getBackgroundColor()) && Arrays.equals(this.getDisplayDimensions(), that.getDisplayDimensions());
    }
}

