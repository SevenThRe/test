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
import blockbuster.math.Operation;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BedrockComponentLifetimeExpression
extends BedrockComponentLifetime {
    public MolangExpression expiration = MolangParser.ZERO;

    @Override
    protected String getPropertyName() {
        return "activation_expression";
    }

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("expiration_expression")) {
            this.expiration = parser.parseJson(element.get("expiration_expression"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = (JsonObject)super.toJson();
        if (!MolangExpression.isZero(this.expiration)) {
            object.add("expiration_expression", this.expiration.toJson());
        }
        return object;
    }

    @Override
    public void update(BedrockEmitter emitter) {
        if (!Operation.equals(this.activeTime.get(), 0.0)) {
            emitter.start();
        }
        if (!Operation.equals(this.expiration.get(), 0.0)) {
            emitter.stop();
        }
    }
}

