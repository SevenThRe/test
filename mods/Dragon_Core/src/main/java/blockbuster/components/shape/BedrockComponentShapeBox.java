/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.shape;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.shape.BedrockComponentShapeBase;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BedrockComponentShapeBox
extends BedrockComponentShapeBase {
    public MolangExpression[] halfDimensions = new MolangExpression[]{MolangParser.ZERO, MolangParser.ZERO, MolangParser.ZERO};

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        JsonArray array;
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("half_dimensions") && (array = element.getAsJsonArray("half_dimensions")).size() >= 3) {
            this.halfDimensions[0] = parser.parseJson(array.get(0));
            this.halfDimensions[1] = parser.parseJson(array.get(1));
            this.halfDimensions[2] = parser.parseJson(array.get(2));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = (JsonObject)super.toJson();
        JsonArray array = new JsonArray();
        for (MolangExpression expression : this.halfDimensions) {
            array.add(expression.toJson());
        }
        object.add("half_dimensions", (JsonElement)array);
        return object;
    }

    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        float centerX = (float)this.offset[0].get();
        float centerY = (float)this.offset[1].get();
        float centerZ = (float)this.offset[2].get();
        float w2 = (float)this.halfDimensions[0].get();
        float h2 = (float)this.halfDimensions[1].get();
        float d2 = (float)this.halfDimensions[2].get();
        particle.position.x = centerX + ((float)Math.random() * 2.0f - 1.0f) * w2;
        particle.position.y = centerY + ((float)Math.random() * 2.0f - 1.0f) * h2;
        particle.position.z = centerZ + ((float)Math.random() * 2.0f - 1.0f) * d2;
        if (this.surface) {
            int roll = (int)(Math.random() * 6.0 * 100.0) % 6;
            if (roll == 0) {
                particle.position.x = centerX + w2;
            } else if (roll == 1) {
                particle.position.x = centerX - w2;
            } else if (roll == 2) {
                particle.position.y = centerY + h2;
            } else if (roll == 3) {
                particle.position.y = centerY - h2;
            } else if (roll == 4) {
                particle.position.z = centerZ + d2;
            } else if (roll == 5) {
                particle.position.z = centerZ - d2;
            }
        }
        this.direction.applyDirection(particle, centerX, centerY, centerZ);
    }
}

