/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraftforge.fml.client.FMLClientHandler
 */
package journeymap.client.properties;

import journeymap.client.properties.InGameMapProperties;
import journeymap.client.ui.minimap.Orientation;
import journeymap.client.ui.minimap.Position;
import journeymap.client.ui.minimap.ReticleOrientation;
import journeymap.client.ui.minimap.Shape;
import journeymap.client.ui.option.TimeFormat;
import journeymap.client.ui.theme.ThemeLabelSource;
import journeymap.common.properties.Category;
import journeymap.common.properties.PropertiesBase;
import journeymap.common.properties.config.BooleanField;
import journeymap.common.properties.config.EnumField;
import journeymap.common.properties.config.IntegerField;
import journeymap.common.properties.config.StringField;
import net.minecraftforge.fml.client.FMLClientHandler;

public class MiniMapProperties
extends InGameMapProperties {
    public final StringField gameTimeRealFormat = new StringField(Category.Inherit, "jm.common.time_format", TimeFormat.Provider.class);
    public final StringField systemTimeRealFormat = new StringField(Category.Inherit, "jm.common.system_time_format", TimeFormat.Provider.class);
    public final BooleanField enabled = new BooleanField(Category.Inherit, "jm.minimap.enable_minimap", true, true);
    public final EnumField<Shape> shape = new EnumField<Shape>(Category.Inherit, "jm.minimap.shape", Shape.Circle);
    public final EnumField<Position> position = new EnumField<Position>(Category.Inherit, "jm.minimap.position", Position.TopRight);
    public final BooleanField showDayNight = new BooleanField(Category.Inherit, "jm.common.show_day_night", true);
    public final EnumField<ThemeLabelSource> info1Label = new EnumField<ThemeLabelSource>(Category.Inherit, "jm.minimap.info1_label.button", ThemeLabelSource.Blank);
    public final EnumField<ThemeLabelSource> info2Label = new EnumField<ThemeLabelSource>(Category.Inherit, "jm.minimap.info2_label.button", ThemeLabelSource.GameTime);
    public final EnumField<ThemeLabelSource> info3Label = new EnumField<ThemeLabelSource>(Category.Inherit, "jm.minimap.info3_label.button", ThemeLabelSource.Location);
    public final EnumField<ThemeLabelSource> info4Label = new EnumField<ThemeLabelSource>(Category.Inherit, "jm.minimap.info4_label.button", ThemeLabelSource.Biome);
    public final IntegerField sizePercent = new IntegerField(Category.Inherit, "jm.minimap.size", 1, 100, 30);
    public final IntegerField frameAlpha = new IntegerField(Category.Inherit, "jm.minimap.frame_alpha", 0, 100, 100);
    public final IntegerField terrainAlpha = new IntegerField(Category.Inherit, "jm.minimap.terrain_alpha", 0, 100, 100);
    public final EnumField<Orientation> orientation = new EnumField<Orientation>(Category.Inherit, "jm.minimap.orientation.button", Orientation.North);
    public final IntegerField compassFontScale = new IntegerField(Category.Inherit, "jm.minimap.compass_font_scale", 1, 4, 1);
    public final BooleanField showCompass = new BooleanField(Category.Inherit, "jm.minimap.show_compass", true);
    public final BooleanField showReticle = new BooleanField(Category.Inherit, "jm.minimap.show_reticle", true);
    public final EnumField<ReticleOrientation> reticleOrientation = new EnumField<ReticleOrientation>(Category.Inherit, "jm.minimap.reticle_orientation", ReticleOrientation.Compass);
    protected final transient int id;
    protected boolean active = false;

    public MiniMapProperties(int id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return String.format("minimap%s", this.id > 1 ? Integer.valueOf(this.id) : "");
    }

    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        if (this.active != active) {
            this.active = active;
            this.save();
        }
    }

    public int getId() {
        return this.id;
    }

    @Override
    public <T extends PropertiesBase> void updateFrom(T otherInstance) {
        super.updateFrom(otherInstance);
        if (otherInstance instanceof MiniMapProperties) {
            this.setActive(((MiniMapProperties)otherInstance).isActive());
        }
    }

    public int getSize() {
        return (int)Math.max(128.0, Math.floor((double)this.sizePercent.get().intValue() / 100.0 * (double)FMLClientHandler.instance().getClient().displayHeight));
    }

    @Override
    protected void postLoad(boolean isNew) {
        super.postLoad(isNew);
        if (isNew) {
            if (this.getId() == 1) {
                this.setActive(true);
                if (FMLClientHandler.instance().getClient() != null && FMLClientHandler.instance().getClient().fontRenderer.getUnicodeFlag()) {
                    this.fontScale.set(2);
                    this.compassFontScale.set(2);
                }
            } else {
                this.setActive(false);
                this.position.set(Position.TopRight);
                this.shape.set(Shape.Rectangle);
                this.frameAlpha.set(100);
                this.terrainAlpha.set(100);
                this.orientation.set(Orientation.North);
                this.reticleOrientation.set(ReticleOrientation.Compass);
                this.sizePercent.set(30);
                if (FMLClientHandler.instance().getClient() != null && FMLClientHandler.instance().getClient().fontRenderer.getUnicodeFlag()) {
                    this.fontScale.set(2);
                    this.compassFontScale.set(2);
                }
            }
        }
    }
}

