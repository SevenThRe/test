/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components;

import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public abstract class BedrockComponentBase {
    public BedrockComponentBase fromJson(JsonElement element, MolangParser parser) throws MolangException {
        return this;
    }

    public JsonElement toJson() {
        return new JsonObject();
    }

    public boolean canBeEmpty() {
        return false;
    }
}

