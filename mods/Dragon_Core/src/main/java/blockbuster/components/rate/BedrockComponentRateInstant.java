/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.rate;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentEmitterUpdate;
import blockbuster.components.rate.BedrockComponentRate;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.math.Constant;
import blockbuster.math.Operation;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.math.molang.expressions.MolangValue;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BedrockComponentRateInstant
extends BedrockComponentRate
implements IComponentEmitterUpdate {
    public static final MolangExpression DEFAULT_PARTICLES = new MolangValue(null, new Constant(10.0));

    public BedrockComponentRateInstant() {
        this.particles = DEFAULT_PARTICLES;
    }

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("num_particles")) {
            this.particles = parser.parseJson(element.get("num_particles"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        if (!MolangExpression.isConstant(this.particles, 10.0)) {
            object.add("num_particles", this.particles.toJson());
        }
        return object;
    }

    @Override
    public void update(BedrockEmitter emitter) {
        double age = emitter.getAge();
        if (emitter.playing && Operation.equals(age, 0.0)) {
            emitter.setEmitterVariables(0.0f);
            int c2 = (int)this.particles.get();
            for (int i2 = 0; i2 < c2; ++i2) {
                emitter.spawnParticle();
            }
        }
    }
}

