/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.common.base.Objects
 *  com.google.common.base.Strings
 *  com.google.gson.annotations.Since
 *  javax.annotation.ParametersAreNonnullByDefault
 */
package journeymap.client.api.display;

import com.google.common.base.Joiner;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import com.google.gson.annotations.Since;
import java.util.UUID;
import javax.annotation.ParametersAreNonnullByDefault;
import journeymap.client.api.display.DisplayType;

@ParametersAreNonnullByDefault
public abstract class Displayable
implements Comparable<Displayable> {
    @Since(value=1.1)
    protected final String modId;
    @Since(value=1.1)
    protected final String id;
    @Since(value=1.1)
    protected final DisplayType displayType;

    private Displayable() {
        this.modId = null;
        this.id = null;
        this.displayType = null;
    }

    protected Displayable(String modId) {
        this(modId, UUID.randomUUID().toString());
    }

    protected Displayable(String modId, String displayId) {
        if (Strings.isNullOrEmpty((String)modId)) {
            throw new IllegalArgumentException("modId may not be blank");
        }
        if (Strings.isNullOrEmpty((String)displayId)) {
            throw new IllegalArgumentException("displayId may not be blank");
        }
        this.modId = modId;
        this.id = displayId;
        this.displayType = DisplayType.of(this.getClass());
    }

    public static int clampRGB(int rgb) {
        return 0xFF000000 | rgb;
    }

    public static float clampOpacity(float opacity) {
        return Math.max(0.0f, Math.min(opacity, 1.0f));
    }

    public abstract int getDisplayOrder();

    public final String getModId() {
        return this.modId;
    }

    public final String getId() {
        return this.id;
    }

    public final DisplayType getDisplayType() {
        return this.displayType;
    }

    public final String getGuid() {
        return Joiner.on((String)":").join((Object)this.modId, (Object)this.displayType, new Object[]{this.id});
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Displayable)) {
            return false;
        }
        Displayable that = (Displayable)o;
        return Objects.equal((Object)this.modId, (Object)that.modId) && Objects.equal((Object)((Object)this.displayType), (Object)((Object)that.displayType)) && Objects.equal((Object)this.id, (Object)that.id);
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.modId, this.displayType, this.id});
    }

    @Override
    public int compareTo(Displayable o) {
        return Integer.compare(this.getDisplayOrder(), o.getDisplayOrder());
    }
}

