/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.motion;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleInitialize;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BedrockComponentInitialSpin
extends BedrockComponentBase
implements IComponentParticleInitialize {
    public MolangExpression rotation = MolangParser.ZERO;
    public MolangExpression rate = MolangParser.ZERO;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("rotation")) {
            this.rotation = parser.parseJson(element.get("rotation"));
        }
        if (element.has("rotation_rate")) {
            this.rate = parser.parseJson(element.get("rotation_rate"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        if (!MolangExpression.isZero(this.rotation)) {
            object.add("rotation", this.rotation.toJson());
        }
        if (!MolangExpression.isZero(this.rate)) {
            object.add("rotation_rate", this.rate.toJson());
        }
        return object;
    }

    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        particle.initialRotation = (float)this.rotation.get();
        particle.rotationVelocity = (float)this.rate.get() / 20.0f;
    }
}

