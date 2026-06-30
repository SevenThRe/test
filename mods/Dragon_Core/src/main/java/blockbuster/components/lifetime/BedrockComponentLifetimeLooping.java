/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.lifetime;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.lifetime.BedrockComponentLifetime;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BedrockComponentLifetimeLooping
extends BedrockComponentLifetime {
    public MolangExpression sleepTime = MolangParser.ZERO;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("sleep_time")) {
            this.sleepTime = parser.parseJson(element.get("sleep_time"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = (JsonObject)super.toJson();
        if (!MolangExpression.isZero(this.sleepTime)) {
            object.add("sleep_time", this.sleepTime.toJson());
        }
        return object;
    }

    @Override
    public void update(BedrockEmitter emitter) {
        double active = this.activeTime.get();
        double sleep = this.sleepTime.get();
        double age = emitter.getAge();
        emitter.lifetime = (int)(active * 20.0);
        if (age >= active && emitter.playing) {
            emitter.stop();
        }
        if (age >= sleep && !emitter.playing) {
            emitter.start();
        }
    }
}

