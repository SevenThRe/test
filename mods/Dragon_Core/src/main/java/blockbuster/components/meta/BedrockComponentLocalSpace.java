/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 */
package blockbuster.components.meta;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleInitialize;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class BedrockComponentLocalSpace
extends BedrockComponentBase
implements IComponentParticleInitialize {
    public boolean position;
    public boolean rotation;
    public boolean scale;
    public boolean scaleBillboard;
    public boolean direction;
    public boolean acceleration;
    public boolean gravity;
    public float linearVelocity;
    public float angularVelocity;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("position")) {
            this.position = element.get("position").getAsBoolean();
        }
        if (element.has("rotation")) {
            this.rotation = element.get("rotation").getAsBoolean();
        }
        if (element.has("scale")) {
            this.scale = element.get("scale").getAsBoolean();
        }
        if (element.has("scale_billboard")) {
            this.scaleBillboard = element.get("scale_billboard").getAsBoolean();
        }
        if (element.has("direction")) {
            this.direction = element.get("direction").getAsBoolean();
        }
        if (element.has("acceleration")) {
            this.acceleration = element.get("acceleration").getAsBoolean();
        }
        if (element.has("gravity")) {
            this.gravity = element.get("gravity").getAsBoolean();
        }
        if (element.has("linear_velocity")) {
            this.linearVelocity = element.get("linear_velocity").getAsFloat();
        }
        if (element.has("angular_velocity")) {
            this.angularVelocity = element.get("angular_velocity").getAsFloat();
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        if (this.position) {
            object.addProperty("position", Boolean.valueOf(true));
        }
        if (this.rotation) {
            object.addProperty("rotation", Boolean.valueOf(true));
        }
        if (this.scale) {
            object.addProperty("scale", Boolean.valueOf(true));
        }
        if (this.scaleBillboard) {
            object.addProperty("scale_billboard", Boolean.valueOf(true));
        }
        if (this.direction) {
            object.addProperty("direction", Boolean.valueOf(true));
        }
        if (this.acceleration) {
            object.addProperty("acceleration", Boolean.valueOf(true));
        }
        if (this.gravity) {
            object.addProperty("gravity", Boolean.valueOf(true));
        }
        if (this.linearVelocity != 0.0f) {
            object.addProperty("linear_velocity", (Number)Float.valueOf(this.linearVelocity));
        }
        if (this.angularVelocity != 0.0f) {
            object.addProperty("angular_velocity", (Number)Float.valueOf(this.angularVelocity));
        }
        return object;
    }

    @Override
    public void apply(BedrockEmitter emitter, BedrockParticle particle) {
        particle.relativePosition = this.position;
        particle.relativeRotation = this.rotation;
        particle.relativeScale = this.scale;
        particle.relativeScaleBillboard = this.scaleBillboard;
        particle.relativeDirection = this.direction;
        particle.relativeAcceleration = this.acceleration;
        particle.gravity = this.gravity;
        particle.linearVelocity = this.linearVelocity;
        particle.angularVelocity = this.angularVelocity;
        particle.setupMatrix(emitter);
    }

    @Override
    public int getSortingIndex() {
        return 6;
    }
}

