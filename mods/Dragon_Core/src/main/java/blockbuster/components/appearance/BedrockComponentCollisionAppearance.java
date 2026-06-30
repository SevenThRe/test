/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.util.ResourceLocation
 */
package blockbuster.components.appearance;

import blockbuster.BedrockMaterial;
import blockbuster.BedrockScheme;
import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleRender;
import blockbuster.components.appearance.BedrockComponentAppearanceBillboard;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.utils.Interpolations;
import blockbuster.utils.RLUtils;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.util.Map;
import java.util.Set;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.util.ResourceLocation;

public class BedrockComponentCollisionAppearance
extends BedrockComponentAppearanceBillboard
implements IComponentParticleRender {
    public BedrockMaterial material = BedrockMaterial.OPAQUE;
    public ResourceLocation texture = BedrockScheme.DEFAULT_TEXTURE;
    public MolangExpression enabled = MolangParser.ZERO;
    public boolean lit;

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        String texture;
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("enabled")) {
            this.enabled = parser.parseJson(element.get("enabled"));
        }
        if (element.has("lit")) {
            this.lit = element.get("lit").getAsBoolean();
        }
        if (element.has("material")) {
            this.material = BedrockMaterial.fromString(element.get("material").getAsString());
        }
        if (element.has("texture") && !(texture = element.get("texture").getAsString()).equals("textures/particle/particles")) {
            this.texture = RLUtils.create(texture);
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        object.add("enabled", this.enabled.toJson());
        object.addProperty("lit", Boolean.valueOf(this.lit));
        object.addProperty("material", this.material.id);
        if (this.texture != null && !this.texture.equals((Object)BedrockScheme.DEFAULT_TEXTURE)) {
            object.addProperty("texture", this.texture.toString());
        }
        JsonObject superJson = (JsonObject)super.toJson();
        Set entries = superJson.entrySet();
        for (Map.Entry entry : entries) {
            object.add((String)entry.getKey(), (JsonElement)entry.getValue());
        }
        return object;
    }

    @Override
    public void preRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public void render(BedrockEmitter emitter, BedrockParticle particle, BufferBuilder builder, float partialTicks) {
        boolean tmpLit = false;
        if (!particle.isCollisionTexture(emitter)) {
            if (particle.isCollisionTinting(emitter)) {
                tmpLit = emitter.lit;
                emitter.lit = this.lit;
                emitter.scheme.get(BedrockComponentAppearanceBillboard.class).render(emitter, particle, builder, partialTicks);
                emitter.lit = tmpLit;
            }
            return;
        }
        if (!particle.isCollisionTinting(emitter)) {
            tmpLit = this.lit;
            this.lit = emitter.lit;
        }
        this.calculateUVs(particle, partialTicks);
        double px2 = Interpolations.lerp(particle.prevPosition.x, particle.position.x, (double)partialTicks);
        double py2 = Interpolations.lerp(particle.prevPosition.y, particle.position.y, (double)partialTicks);
        double pz2 = Interpolations.lerp(particle.prevPosition.z, particle.position.z, (double)partialTicks);
        float angle = Interpolations.lerp(particle.prevRotation, particle.rotation, partialTicks);
        Vector3d pos = this.calculatePosition(emitter, particle, px2, py2, pz2);
        px2 = pos.x;
        py2 = pos.y;
        pz2 = pos.z;
        int light = this.lit ? 0xF000F0 : emitter.getBrightnessForRender(partialTicks, px2, py2, pz2);
        int lightX = light >> 16 & 0xFFFF;
        int lightY = light & 0xFFFF;
        this.calculateFacing(emitter, particle, px2, py2, pz2);
        this.rotation.rotZ(angle / 180.0f * (float)Math.PI);
        this.transform.mul(this.rotation);
        this.transform.setTranslation(new Vector3f((float)px2, (float)py2, (float)pz2));
        for (Vector4f vertex : this.vertices) {
            this.transform.transform((Tuple4f)vertex);
        }
        float u1 = this.u1 / (float)this.textureWidth;
        float u2 = this.u2 / (float)this.textureWidth;
        float v1 = this.v1 / (float)this.textureHeight;
        float v2 = this.v2 / (float)this.textureHeight;
        builder.func_181662_b((double)this.vertices[0].x, (double)this.vertices[0].y, (double)this.vertices[0].z).func_187315_a((double)u1, (double)v1).func_187314_a(lightX, lightY).func_181666_a(particle.r, particle.g, particle.b, particle.a).func_181675_d();
        builder.func_181662_b((double)this.vertices[1].x, (double)this.vertices[1].y, (double)this.vertices[1].z).func_187315_a((double)u2, (double)v1).func_187314_a(lightX, lightY).func_181666_a(particle.r, particle.g, particle.b, particle.a).func_181675_d();
        builder.func_181662_b((double)this.vertices[2].x, (double)this.vertices[2].y, (double)this.vertices[2].z).func_187315_a((double)u2, (double)v2).func_187314_a(lightX, lightY).func_181666_a(particle.r, particle.g, particle.b, particle.a).func_181675_d();
        builder.func_181662_b((double)this.vertices[3].x, (double)this.vertices[3].y, (double)this.vertices[3].z).func_187315_a((double)u1, (double)v2).func_187314_a(lightX, lightY).func_181666_a(particle.r, particle.g, particle.b, particle.a).func_181675_d();
        if (!particle.isCollisionTinting(emitter)) {
            this.lit = tmpLit;
        }
    }

    @Override
    public void renderOnScreen(BedrockParticle particle, int x2, int y2, float scale, float partialTicks) {
    }

    @Override
    public void calculateUVs(BedrockParticle particle, float partialTicks) {
        this.w = (float)this.sizeW.get() * 2.25f;
        this.h = (float)this.sizeH.get() * 2.25f;
        float u2 = (float)this.uvX.get();
        float v2 = (float)this.uvY.get();
        float w2 = (float)this.uvW.get();
        float h2 = (float)this.uvH.get();
        if (this.flipbook) {
            int index = (int)(particle.getAge(partialTicks) * (double)this.fps);
            int max = (int)this.maxFrame.get();
            if (this.stretchFPS) {
                float lifetime;
                float f2 = lifetime = particle.lifetime <= 0 ? 0.0f : ((float)particle.age + partialTicks) / (float)(particle.lifetime - particle.firstIntersection);
                if (particle.getExpireAge() != -1) {
                    lifetime = particle.lifetime <= 0 ? 0.0f : ((float)particle.age + partialTicks) / (float)particle.getExpirationDelay();
                }
                index = (int)(lifetime * (float)max);
            }
            if (this.loop && max != 0) {
                index %= max;
            }
            if (index > max) {
                index = max;
            }
            u2 += this.stepX * (float)index;
            v2 += this.stepY * (float)index;
        }
        this.u1 = u2;
        this.v1 = v2;
        this.u2 = u2 + w2;
        this.v2 = v2 + h2;
    }

    @Override
    public void postRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public int getSortingIndex() {
        return 200;
    }
}

