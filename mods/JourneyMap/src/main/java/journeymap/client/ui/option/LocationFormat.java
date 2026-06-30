/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.ui.option;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.ui.component.ListPropertyButton;
import journeymap.common.Journeymap;
import journeymap.common.properties.config.StringField;

public class LocationFormat {
    private static String[] locationFormatIds = new String[]{"xzyv", "xyvz", "xzy", "xyz", "xz"};
    private HashMap<String, LocationFormatKeys> idToFormat = new HashMap();

    public LocationFormat() {
        for (String id : locationFormatIds) {
            this.idToFormat.put(id, new LocationFormatKeys(id));
        }
    }

    public LocationFormatKeys getFormatKeys(String id) {
        LocationFormatKeys locationLocationFormatKeys = this.idToFormat.get(id);
        if (locationLocationFormatKeys == null) {
            Journeymap.getLogger().warn("Invalid location format id: " + id);
            locationLocationFormatKeys = this.idToFormat.get(locationFormatIds[0]);
        }
        return locationLocationFormatKeys;
    }

    public String getLabel(String id) {
        return Constants.getString(this.getFormatKeys((String)id).label_key);
    }

    public static class Button
    extends ListPropertyButton<String> {
        LocationFormat locationFormat;

        public Button(StringField valueHolder) {
            super(Arrays.asList(locationFormatIds), Constants.getString("jm.common.location_format"), valueHolder);
            if (this.locationFormat == null) {
                this.locationFormat = new LocationFormat();
            }
        }

        @Override
        public String getFormattedLabel(String id) {
            if (this.locationFormat == null) {
                this.locationFormat = new LocationFormat();
            }
            return String.format("%1$s : %2$s %3$s %2$s", this.baseLabel, "\u21d5", this.locationFormat.getLabel(id));
        }

        public String getLabel(String id) {
            return this.locationFormat.getLabel(id);
        }
    }

    public static class LocationFormatKeys {
        final String id;
        final String label_key;
        final String verbose_key;
        final String plain_key;

        LocationFormatKeys(String id) {
            this.id = id;
            this.label_key = String.format("jm.common.location_%s_label", id);
            this.verbose_key = String.format("jm.common.location_%s_verbose", id);
            this.plain_key = String.format("jm.common.location_%s_plain", id);
        }

        public String format(boolean verbose, int x, int z, int y, int vslice) {
            if (verbose) {
                return Constants.getString(this.verbose_key, x, z, y, vslice);
            }
            return Constants.getString(this.plain_key, x, z, y, vslice);
        }
    }

    public static class IdProvider
    implements StringField.ValuesProvider {
        @Override
        public List<String> getStrings() {
            return Arrays.asList(locationFormatIds);
        }

        @Override
        public String getDefaultString() {
            return locationFormatIds[0];
        }
    }
}

