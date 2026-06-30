/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.MoreObjects
 *  com.google.common.base.Objects
 *  com.google.gson.annotations.Since
 *  javax.annotation.Nullable
 *  net.minecraft.util.ResourceLocation
 */
package journeymap.client.api.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.gson.annotations.Since;
import java.awt.image.BufferedImage;
import javax.annotation.Nullable;
import journeymap.client.api.display.Displayable;
import net.minecraft.util.ResourceLocation;

public final class MapImage {
    @Since(value=1.1)
    private transient BufferedImage image;
    @Since(value=1.1)
    private ResourceLocation imageLocation;
    @Since(value=1.1)
    private Integer color = 0xFFFFFF;
    @Since(value=1.1)
    private Float opacity = Float.valueOf(1.0f);
    @Since(value=1.1)
    private Integer textureX = 0;
    @Since(value=1.1)
    private Integer textureY = 0;
    @Since(value=1.1)
    private Integer textureWidth;
    @Since(value=1.1)
    private Integer textureHeight;
    @Since(value=1.1)
    private Integer rotation;
    @Since(value=1.1)
    private Double displayWidth;
    @Since(value=1.1)
    private Double displayHeight;
    @Since(value=1.1)
    private Double anchorX;
    @Since(value=1.1)
    private Double anchorY;

    public MapImage(BufferedImage image) {
        this(image, 0, 0, image.getWidth(), image.getHeight(), 0xFFFFFF, 1.0f);
    }

    public MapImage(BufferedImage image, int textureX, int textureY, int textureWidth, int textureHeight, int color, float opacity) {
        this.image = image;
        this.textureX = textureX;
        this.textureY = textureY;
        this.textureWidth = Math.max(1, textureWidth);
        this.textureHeight = Math.max(1, textureHeight);
        this.setDisplayWidth(this.textureWidth.intValue());
        this.setDisplayHeight(this.textureHeight.intValue());
        this.setColor(color);
        this.setOpacity(opacity);
    }

    public MapImage(ResourceLocation imageLocation, int textureWidth, int textureHeight) {
        this(imageLocation, 0, 0, textureWidth, textureHeight, 0xFFFFFF, 1.0f);
    }

    public MapImage(ResourceLocation imageLocation, int textureX, int textureY, int textureWidth, int textureHeight, int color, float opacity) {
        this.imageLocation = imageLocation;
        this.textureX = textureX;
        this.textureY = textureY;
        this.textureWidth = Math.max(1, textureWidth);
        this.textureHeight = Math.max(1, textureHeight);
        this.setDisplayWidth(this.textureWidth.intValue());
        this.setDisplayHeight(this.textureHeight.intValue());
        this.setColor(color);
        this.setOpacity(opacity);
    }

    public int getColor() {
        return this.color;
    }

    public MapImage setColor(int color) {
        this.color = Displayable.clampRGB(color);
        return this;
    }

    public float getOpacity() {
        return this.opacity.floatValue();
    }

    public MapImage setOpacity(float opacity) {
        this.opacity = Float.valueOf(Displayable.clampOpacity(opacity));
        return this;
    }

    public int getTextureX() {
        return this.textureX;
    }

    public int getTextureY() {
        return this.textureY;
    }

    public double getAnchorX() {
        return this.anchorX;
    }

    public MapImage setAnchorX(double anchorX) {
        this.anchorX = anchorX;
        return this;
    }

    public double getAnchorY() {
        return this.anchorY;
    }

    public MapImage setAnchorY(double anchorY) {
        this.anchorY = anchorY;
        return this;
    }

    public MapImage centerAnchors() {
        this.setAnchorX(this.displayWidth / 2.0);
        this.setAnchorY(this.displayHeight / 2.0);
        return this;
    }

    public int getTextureWidth() {
        return this.textureWidth;
    }

    public int getTextureHeight() {
        return this.textureHeight;
    }

    @Nullable
    public ResourceLocation getImageLocation() {
        return this.imageLocation;
    }

    @Nullable
    public BufferedImage getImage() {
        return this.image;
    }

    public int getRotation() {
        return this.rotation;
    }

    public MapImage setRotation(int rotation) {
        this.rotation = rotation % 360;
        return this;
    }

    public double getDisplayWidth() {
        return this.displayWidth;
    }

    public MapImage setDisplayWidth(double displayWidth) {
        this.displayWidth = displayWidth;
        return this;
    }

    public double getDisplayHeight() {
        return this.displayHeight;
    }

    public MapImage setDisplayHeight(double displayHeight) {
        this.displayHeight = displayHeight;
        return this;
    }

    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        MapImage mapImage = (MapImage)o;
        return Objects.equal((Object)this.color, (Object)mapImage.color) && Objects.equal((Object)this.opacity, (Object)mapImage.opacity) && Objects.equal((Object)this.anchorX, (Object)mapImage.anchorX) && Objects.equal((Object)this.anchorY, (Object)mapImage.anchorY) && Objects.equal((Object)this.textureX, (Object)mapImage.textureX) && Objects.equal((Object)this.textureY, (Object)mapImage.textureY) && Objects.equal((Object)this.textureWidth, (Object)mapImage.textureWidth) && Objects.equal((Object)this.textureHeight, (Object)mapImage.textureHeight) && Objects.equal((Object)this.imageLocation, (Object)mapImage.imageLocation);
    }

    public int hashCode() {
        return Objects.hashCode((Object[])new Object[]{this.imageLocation, this.color, this.opacity, this.anchorX, this.anchorY, this.textureX, this.textureY, this.textureWidth, this.textureHeight});
    }

    public String toString() {
        return MoreObjects.toStringHelper((Object)this).add("imageLocation", (Object)this.imageLocation).add("anchorX", (Object)this.anchorX).add("anchorY", (Object)this.anchorY).add("color", (Object)this.color).add("textureHeight", (Object)this.textureHeight).add("opacity", (Object)this.opacity).add("textureX", (Object)this.textureX).add("textureY", (Object)this.textureY).add("textureWidth", (Object)this.textureWidth).toString();
    }
}

