/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonObject
 */
package journeymap.client.ui.serveroption;

import com.google.gson.JsonObject;
import journeymap.client.Constants;

public class ServerOption {
    private String field;
    private String opField;
    private JsonObject properties;
    public Option option;

    public ServerOption(String field, JsonObject properties) {
        this.field = field;
        this.properties = properties;
        this.opField = "op_" + field;
        this.option = this.findOption();
    }

    public Option getOption() {
        return this.option;
    }

    public boolean getFieldValue() {
        return Option.ALL.equals((Object)this.option);
    }

    public boolean getOpFieldValue() {
        return Option.OPS.equals((Object)this.option) || Option.ALL.equals((Object)this.option);
    }

    public void setOption(Option option) {
        this.option = option;
    }

    private Option findOption() {
        if (this.properties.get(this.field) != null && this.properties.get(this.opField) != null) {
            boolean all = this.properties.get(this.field).getAsBoolean();
            boolean op = this.properties.get(this.opField).getAsBoolean();
            if (all) {
                return Option.ALL;
            }
            if (op) {
                return Option.OPS;
            }
        }
        return Option.NONE;
    }

    public static enum Option {
        ALL("jm.server.edit.option.all"),
        OPS("jm.server.edit.option.op"),
        NONE("jm.server.edit.option.none");

        private String key;

        private Option(String key) {
            this.key = key;
        }

        public String displayName() {
            return Constants.getString(this.key);
        }

        public String toString() {
            return this.displayName();
        }
    }
}

