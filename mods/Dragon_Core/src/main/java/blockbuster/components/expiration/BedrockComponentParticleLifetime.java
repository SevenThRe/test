/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 */
package blockbuster.components.expiration;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleInitialize;
import blockbuster.components.IComponentParticleUpdate;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

public class BedrockComponentParticleLifetime
extends BedrockComponentBase
implements IComponentParticleInitialize,
IComponentParticleUpdate {
    public MolangExpression expression = MolangParser.ZERO;
    public boolean max;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        JsonElement expression = null;
        if (element.has("expiration_expression")) {
            expression = element.get("expiration_expression");
            this.max = false;
        } else if (element.has("max_lifetime")) {
            expression = element.get("max_lifetime");
            this.max = true;
        } else {
            throw new JsonParseException("No expiration_expression or max_lifetime was found in minecraft:particle_lifetime_expression component");
        }
        this.expression = parser.parseJson(expression);
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.add(this.max ? "max_lifetime" : "expiration_expression", this.expression.toJson());
        return object;
    }

    @Override
    public void update(BedrockEmitter emitter, BedrockParticle particle) {
        if (!this.max && this.expression.get() != 0.0) {
            particle.dead = true;
        }
    }

    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        particle.lifetime = this.max ? (int)(this.expression.get() * 20.0) : -1;
    }
}

