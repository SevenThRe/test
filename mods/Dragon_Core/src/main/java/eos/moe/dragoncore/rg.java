/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  com.google.gson.stream.JsonWriter
 */
package eos.moe.dragoncore;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonWriter;
import java.io.StringWriter;
import java.io.Writer;

public class rg {
    public rg() {
        rg a2;
    }

    public static String ALLATORIxDEMO(JsonElement a2) {
        StringWriter a3 = new StringWriter();
        JsonWriter a4 = new JsonWriter((Writer)a3);
        Gson a5 = new GsonBuilder().setPrettyPrinting().create();
        a4.setIndent("    ");
        a5.toJson(a2, a4);
        return a3.toString();
    }
}

