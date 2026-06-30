/*
 * Decompiled with CFR 0.152.
 */
package journeymap.client.ui.option;

import java.util.Arrays;
import java.util.List;
import journeymap.client.Constants;
import journeymap.client.ui.component.ListPropertyButton;
import journeymap.common.properties.config.StringField;

public class TimeFormat {
    private static String[] timeFormatValues = new String[]{"HH:mm:ss", "H:mm:ss", "HH:mm", "H:mm", "hh:mm:ss a", "h:mm:ss a", "hh:mm:ss ", "h:mm:ss", "hh:mm a", "h:mm a", "hh:mm", "h:mm"};

    public static class Button
    extends ListPropertyButton<String> {
        TimeFormat timeFormat;

        public Button(StringField valueHolder) {
            super(Arrays.asList(timeFormatValues), Constants.getString("jm.common.time_format"), valueHolder);
            if (this.timeFormat == null) {
                this.timeFormat = new TimeFormat();
            }
        }
    }

    public static class Provider
    implements StringField.ValuesProvider {
        @Override
        public List<String> getStrings() {
            return Arrays.asList(timeFormatValues);
        }

        @Override
        public String getDefaultString() {
            return timeFormatValues[0];
        }
    }
}

