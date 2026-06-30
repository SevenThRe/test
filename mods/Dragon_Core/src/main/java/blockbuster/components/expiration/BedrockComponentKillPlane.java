/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  javax.vecmath.Tuple3d
 *  javax.vecmath.Vector3d
 */
package blockbuster.components.expiration;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleUpdate;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.Operation;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import javax.vecmath.Tuple3d;
import javax.vecmath.Vector3d;

public class BedrockComponentKillPlane
extends BedrockComponentBase
implements IComponentParticleUpdate {
    public float a;
    public float b;
    public float c;
    public float d;

    @Override
    public BedrockComponentBase fromJson(JsonElement element, MolangParser parser) throws MolangException {
        if (!element.isJsonArray()) {
            return super.fromJson(element, parser);
        }
        JsonArray array = element.getAsJsonArray();
        if (array.size() >= 4) {
            this.a = array.get(0).getAsFloat();
            this.b = array.get(1).getAsFloat();
            this.c = array.get(2).getAsFloat();
            this.d = array.get(3).getAsFloat();
        }
        return super.fromJson(element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonArray array = new JsonArray();
        if (Operation.equals(this.a, 0.0) && Operation.equals(this.b, 0.0) && Operation.equals(this.c, 0.0) && Operation.equals(this.d, 0.0)) {
            return array;
        }
        array.add((Number)Float.valueOf(this.a));
        array.add((Number)Float.valueOf(this.b));
        array.add((Number)Float.valueOf(this.c));
        array.add((Number)Float.valueOf(this.d));
        return array;
    }

    @Override
    public void update(BedrockEmitter emitter, BedrockParticle particle) {
        if (particle.dead) {
            return;
        }
        Vector3d prevLocal = new Vector3d(particle.prevPosition);
        Vector3d local = new Vector3d(particle.position);
        if (!particle.relativePosition) {
            local.sub((Tuple3d)emitter.lastGlobal);
            prevLocal.sub((Tuple3d)emitter.lastGlobal);
        }
        double prev = (double)this.a * prevLocal.x + (double)this.b * prevLocal.y + (double)this.c * prevLocal.z + (double)this.d;
        double now = (double)this.a * local.x + (double)this.b * local.y + (double)this.c * local.z + (double)this.d;
        if (prev > 0.0 && now < 0.0 || prev < 0.0 && now > 0.0) {
            particle.dead = true;
        }
    }

    @Override
    public int getSortingIndex() {
        return 100;
    }
}

