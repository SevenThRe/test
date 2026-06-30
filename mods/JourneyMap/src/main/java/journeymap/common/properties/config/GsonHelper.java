/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.base.Joiner
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package journeymap.common.properties.config;

import com.google.common.base.Joiner;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.awt.Color;
import java.lang.reflect.Type;
import java.util.Map;
import java.util.Set;
import journeymap.client.cartography.color.RGB;
import journeymap.client.model.GridSpec;
import journeymap.common.Journeymap;
import journeymap.common.properties.Category;
import journeymap.common.properties.CategorySet;
import journeymap.common.properties.config.BooleanField;
import journeymap.common.properties.config.ConfigField;
import journeymap.common.properties.config.CustomField;
import journeymap.common.properties.config.EnumField;
import journeymap.common.properties.config.IntegerField;
import journeymap.common.properties.config.StringField;
import journeymap.common.version.Version;

public abstract class GsonHelper<T extends ConfigField> {
    protected final boolean verbose;

    public GsonHelper(Boolean verbose) {
        this.verbose = verbose;
    }

    public JsonElement serializeAttributes(ConfigField<?> src, Type typeOfSrc, JsonSerializationContext context) {
        if (!this.verbose) {
            return context.serialize((Object)src.getStringAttr("value"));
        }
        JsonObject jsonObject = new JsonObject();
        for (String attrName : src.getAttributeNames()) {
            jsonObject.addProperty(attrName, src.getStringAttr(attrName));
        }
        return jsonObject;
    }

    protected T deserializeAttributes(T result, JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        if (!this.verbose || !json.isJsonObject()) {
            ((ConfigField)result).put("value", json.getAsString());
        } else {
            Set set = json.getAsJsonObject().entrySet();
            for (Map.Entry entry : set) {
                try {
                    ((ConfigField)result).put((String)entry.getKey(), ((JsonElement)entry.getValue()).getAsString());
                }
                catch (Throwable t) {
                    Journeymap.getLogger().warn("Error deserializing %s in %s: %s", (Object)entry, (Object)json, (Object)t);
                }
            }
        }
        return result;
    }

    public static class EnumFieldSerializer
    extends GsonHelper<EnumField>
    implements JsonSerializer<EnumField>,
    JsonDeserializer<EnumField> {
        public EnumFieldSerializer(boolean verbose) {
            super(verbose);
        }

        public JsonElement serialize(EnumField src, Type typeOfSrc, JsonSerializationContext context) {
            return this.serializeAttributes(src, typeOfSrc, context);
        }

        public EnumField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializeAttributes(new EnumField(), json, typeOfT, context);
        }
    }

    public static class TextFieldSerializer
    extends GsonHelper<CustomField>
    implements JsonSerializer<CustomField>,
    JsonDeserializer<CustomField> {
        public TextFieldSerializer(boolean verbose) {
            super(verbose);
        }

        public JsonElement serialize(CustomField src, Type typeOfSrc, JsonSerializationContext context) {
            return this.serializeAttributes(src, typeOfSrc, context);
        }

        public CustomField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializeAttributes(new CustomField(), json, typeOfT, context);
        }
    }

    public static class StringFieldSerializer
    extends GsonHelper<StringField>
    implements JsonSerializer<StringField>,
    JsonDeserializer<StringField> {
        public StringFieldSerializer(boolean verbose) {
            super(verbose);
        }

        public JsonElement serialize(StringField src, Type typeOfSrc, JsonSerializationContext context) {
            return this.serializeAttributes(src, typeOfSrc, context);
        }

        public StringField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializeAttributes(new StringField(), json, typeOfT, context);
        }
    }

    public static class IntegerFieldSerializer
    extends GsonHelper<IntegerField>
    implements JsonSerializer<IntegerField>,
    JsonDeserializer<IntegerField> {
        public IntegerFieldSerializer(boolean verbose) {
            super(verbose);
        }

        public JsonElement serialize(IntegerField src, Type typeOfSrc, JsonSerializationContext context) {
            return this.serializeAttributes(src, typeOfSrc, context);
        }

        public IntegerField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializeAttributes(new IntegerField(), json, typeOfT, context);
        }
    }

    public static class BooleanFieldSerializer
    extends GsonHelper<BooleanField>
    implements JsonSerializer<BooleanField>,
    JsonDeserializer<BooleanField> {
        public BooleanFieldSerializer(boolean verbose) {
            super(verbose);
        }

        public JsonElement serialize(BooleanField src, Type typeOfSrc, JsonSerializationContext context) {
            return this.serializeAttributes(src, typeOfSrc, context);
        }

        public BooleanField deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return this.deserializeAttributes(new BooleanField(), json, typeOfT, context);
        }
    }

    public static class GridSpecSerializer
    implements JsonSerializer<GridSpec>,
    JsonDeserializer<GridSpec> {
        public GridSpecSerializer(boolean verbose) {
        }

        public JsonElement serialize(GridSpec src, Type typeOfSrc, JsonSerializationContext context) {
            String string = Joiner.on((String)",").join((Object)src.style, (Object)RGB.toHexString(src.getColor()), new Object[]{Float.valueOf(src.alpha), src.getColorX(), src.getColorY()});
            return context.serialize((Object)string);
        }

        public GridSpec deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                JsonObject jo = json.getAsJsonObject();
                GridSpec gridSpec = new GridSpec(GridSpec.Style.valueOf(GridSpec.Style.class, jo.get("style").getAsString()), jo.get("red").getAsFloat(), jo.get("green").getAsFloat(), jo.get("blue").getAsFloat(), jo.get("alpha").getAsFloat());
                gridSpec.setColorCoords(jo.get("colorX").getAsInt(), jo.get("colorY").getAsInt());
                return gridSpec;
            }
            String[] parts = json.getAsString().split(",");
            GridSpec gridSpec = new GridSpec(GridSpec.Style.valueOf(GridSpec.Style.class, parts[0]), new Color(RGB.hexToInt(parts[1])), Float.parseFloat(parts[2]));
            gridSpec.setColorCoords(Integer.parseInt(parts[3]), Integer.parseInt(parts[4]));
            return gridSpec;
        }
    }

    public static class VersionSerializer
    implements JsonSerializer<Version>,
    JsonDeserializer<Version> {
        public VersionSerializer(boolean verbose) {
        }

        public JsonElement serialize(Version src, Type typeOfSrc, JsonSerializationContext context) {
            return context.serialize((Object)src.toString());
        }

        public Version deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if (json.isJsonObject()) {
                JsonObject jo = json.getAsJsonObject();
                return Version.from(jo.get("major").getAsString(), jo.get("minor").getAsString(), jo.get("micro").getAsString(), jo.get("patch").getAsString(), Journeymap.JM_VERSION);
            }
            return Version.from(json.getAsString(), Journeymap.JM_VERSION);
        }
    }

    public static class CategorySetSerializer
    implements JsonSerializer<CategorySet>,
    JsonDeserializer<CategorySet> {
        protected final boolean verbose;

        public CategorySetSerializer(boolean verbose) {
            this.verbose = verbose;
        }

        public JsonElement serialize(CategorySet src, Type typeOfSrc, JsonSerializationContext context) {
            if (!this.verbose) {
                return null;
            }
            Category[] array = new Category[src.size()];
            return context.serialize((Object)src.toArray(array));
        }

        public CategorySet deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            CategorySet categorySet = new CategorySet();
            if (this.verbose) {
                JsonArray jsonArray = json.getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    categorySet.add((Category)context.deserialize(jsonElement, Category.class));
                }
            }
            return categorySet;
        }
    }
}

