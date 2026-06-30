/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.vecmath.Vector3f
 */
package blockbuster.components.shape;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.shape.BedrockComponentShapeBase;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.vecmath.Vector3f;

public class BedrockComponentShapeSphere
extends BedrockComponentShapeBase {
    public MolangExpression radius = MolangParser.ZERO;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("radius")) {
            this.radius = parser.parseJson(element.get("radius"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = (JsonObject)super.toJson();
        if (!MolangExpression.isZero(this.radius)) {
            object.add("radius", this.radius.toJson());
        }
        return object;
    }

    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        float centerX = (float)this.offset[0].get();
        float centerY = (float)this.offset[1].get();
        float centerZ = (float)this.offset[2].get();
        float radius = (float)this.radius.get();
        Vector3f direction = new Vector3f((float)Math.random() * 2.0f - 1.0f, (float)Math.random() * 2.0f - 1.0f, (float)Math.random() * 2.0f - 1.0f);
        direction.normalize();
        if (!this.surface) {
            radius = (float)((double)radius * Math.random());
        }
        direction.scale(radius);
        particle.position.x = centerX + direction.x;
        particle.position.y = centerY + direction.y;
        particle.position.z = centerZ + direction.z;
        this.direction.applyDirection(particle, centerX, centerY, centerZ);
    }
}

