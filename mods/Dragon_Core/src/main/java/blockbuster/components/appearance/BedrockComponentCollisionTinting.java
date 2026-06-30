/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.client.renderer.BufferBuilder
 */
package blockbuster.components.appearance;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleRender;
import blockbuster.components.appearance.BedrockComponentAppearanceTinting;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Set;
import net.minecraft.client.renderer.BufferBuilder;

public class BedrockComponentCollisionTinting
extends BedrockComponentAppearanceTinting
implements IComponentParticleRender {
    public MolangExpression enabled = MolangParser.ZERO;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("enabled")) {
            this.enabled = parser.parseJson(element.get("enabled"));
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.add("enabled", this.enabled.toJson());
        JsonObject superJson = (JsonObject)super.toJson();
        Set entries = superJson.entrySet();
        for (Map.Entry entry : entries) {
            object.add((String)entry.getKey(), (JsonElement)entry.getValue());
        }
        return object;
    }

    @Override
    public void render(BedrockEmitter emitter, BedrockParticle particle, BufferBuilder builder, float partialTicks) {
        if (particle.isCollisionTinting(emitter)) {
            this.renderOnScreen(particle, 0, 0, 0.0f, 0.0f);
        }
    }

    @Override
    public int getSortingIndex() {
        return -5;
    }
}

