/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.TypeAdapter
 *  com.google.gson.stream.JsonReader
 *  com.google.gson.stream.JsonWriter
 */
package goblinbob.mobends.core.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import goblinbob.mobends.core.util.Color;
import java.io.IOException;

public class ColorAdapter
extends TypeAdapter<Color> {
    public Color read(JsonReader in2) throws IOException {
        int colorHex = in2.nextInt();
        return Color.fromHexRGB(colorHex);
    }

    public void write(JsonWriter out, Color value) throws IOException {
        out.value((long)Color.asHex(value));
    }
}

