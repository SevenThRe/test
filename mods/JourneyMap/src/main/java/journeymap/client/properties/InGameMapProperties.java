/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.properties;

import journeymap.client.properties.MapProperties;
import journeymap.client.ui.minimap.EntityDisplay;
import journeymap.client.ui.option.LocationFormat;
import journeymap.common.properties.Category;
import journeymap.common.properties.config.BooleanField;
import journeymap.common.properties.config.EnumField;
import journeymap.common.properties.config.IntegerField;
import journeymap.common.properties.config.StringField;

public abstract class InGameMapProperties
extends MapProperties {
    public final EnumField<EntityDisplay> playerDisplay = new EnumField<EntityDisplay>(Category.Inherit, "jm.minimap.player_display", EntityDisplay.SmallDots);
    public final BooleanField showPlayerHeading = new BooleanField(Category.Inherit, "jm.minimap.player_heading", true);
    public final EnumField<EntityDisplay> mobDisplay = new EnumField<EntityDisplay>(Category.Inherit, "jm.minimap.mob_display", EntityDisplay.SmallDots);
    public final BooleanField showMobHeading = new BooleanField(Category.Inherit, "jm.minimap.mob_heading", true);
    public final BooleanField showMobs = new BooleanField(Category.Inherit, "jm.common.show_mobs", true);
    public final BooleanField showAnimals = new BooleanField(Category.Inherit, "jm.common.show_animals", true);
    public final BooleanField showVillagers = new BooleanField(Category.Inherit, "jm.common.show_villagers", true);
    public final BooleanField showPets = new BooleanField(Category.Inherit, "jm.common.show_pets", true);
    public final BooleanField showPlayers = new BooleanField(Category.Inherit, "jm.common.show_players", true);
    public final IntegerField fontScale = new IntegerField(Category.Inherit, "jm.common.font_scale", 1, 4, 1);
    public final BooleanField showWaypointLabels = new BooleanField(Category.Inherit, "jm.minimap.show_waypointlabels", true);
    public final BooleanField locationFormatVerbose = new BooleanField(Category.Inherit, "jm.common.location_format_verbose", true);
    public final StringField locationFormat = new StringField(Category.Inherit, "jm.common.location_format", LocationFormat.IdProvider.class);

    protected InGameMapProperties() {
    }
}

