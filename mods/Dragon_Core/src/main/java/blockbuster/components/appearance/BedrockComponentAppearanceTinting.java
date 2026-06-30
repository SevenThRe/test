/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  net.minecraft.client.renderer.BufferBuilder
 */
package blockbuster.components.appearance;

import blockbuster.BedrockSchemeJsonAdapter;
import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleRender;
import blockbuster.components.appearance.Tint;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.client.renderer.BufferBuilder;

public class BedrockComponentAppearanceTinting
extends BedrockComponentBase
implements IComponentParticleRender {
    public Tint color = new Tint.Solid();

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("color")) {
            JsonElement color = element.get("color");
            if (color.isJsonArray() || color.isJsonPrimitive()) {
                this.color = Tint.parseColor(color, parser);
            } else if (color.isJsonObject()) {
                this.color = Tint.parseGradient(color.getAsJsonObject(), parser);
            }
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        JsonElement element = this.color.toJson();
        if (!BedrockSchemeJsonAdapter.isEmpty(element)) {
            object.add("color", element);
        }
        return object;
    }

    @Override
    public void preRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public void render(BedrockEmitter emitter, BedrockParticle particle, BufferBuilder builder, float partialTicks) {
        this.renderOnScreen(particle, 0, 0, 0.0f, 0.0f);
    }

    @Override
    public void renderOnScreen(BedrockParticle particle, int x2, int y2, float scale, float partialTicks) {
        if (this.color != null) {
            this.color.compute(particle);
        } else {
            particle.a = 1.0f;
            particle.b = 1.0f;
            particle.g = 1.0f;
            particle.r = 1.0f;
        }
    }

    @Override
    public void postRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public int getSortingIndex() {
        return -10;
    }
}

