/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.common.collect.BiMap
 *  com.google.common.collect.HashBiMap
 *  com.google.gson.JsonDeserializationContext
 *  com.google.gson.JsonDeserializer
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonParseException
 *  com.google.gson.JsonPrimitive
 *  com.google.gson.JsonSerializationContext
 *  com.google.gson.JsonSerializer
 */
package blockbuster;

import blockbuster.BedrockCurve;
import blockbuster.BedrockMaterial;
import blockbuster.BedrockScheme;
import blockbuster.components.BedrockComponentBase;
import blockbuster.components.appearance.BedrockComponentAppearanceBillboard;
import blockbuster.components.appearance.BedrockComponentAppearanceLighting;
import blockbuster.components.appearance.BedrockComponentAppearanceTinting;
import blockbuster.components.appearance.BedrockComponentCollisionAppearance;
import blockbuster.components.appearance.BedrockComponentCollisionTinting;
import blockbuster.components.expiration.BedrockComponentExpireInBlocks;
import blockbuster.components.expiration.BedrockComponentExpireNotInBlocks;
import blockbuster.components.expiration.BedrockComponentKillPlane;
import blockbuster.components.expiration.BedrockComponentParticleLifetime;
import blockbuster.components.lifetime.BedrockComponentLifetimeExpression;
import blockbuster.components.lifetime.BedrockComponentLifetimeLooping;
import blockbuster.components.lifetime.BedrockComponentLifetimeOnce;
import blockbuster.components.meta.BedrockComponentInitialization;
import blockbuster.components.meta.BedrockComponentLocalSpace;
import blockbuster.components.motion.BedrockComponentInitialSpeed;
import blockbuster.components.motion.BedrockComponentInitialSpin;
import blockbuster.components.motion.BedrockComponentMotionCollision;
import blockbuster.components.motion.BedrockComponentMotionDynamic;
import blockbuster.components.motion.BedrockComponentMotionParametric;
import blockbuster.components.rate.BedrockComponentRateInstant;
import blockbuster.components.rate.BedrockComponentRateSteady;
import blockbuster.components.shape.BedrockComponentShapeBox;
import blockbuster.components.shape.BedrockComponentShapeDisc;
import blockbuster.components.shape.BedrockComponentShapeEntityAABB;
import blockbuster.components.shape.BedrockComponentShapePoint;
import blockbuster.components.shape.BedrockComponentShapeSphere;
import blockbuster.math.Operation;
import blockbuster.math.molang.MolangException;
import blockbuster.utils.RLUtils;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import java.lang.reflect.Type;
import java.util.Map;

public class BedrockSchemeJsonAdapter
implements JsonDeserializer<BedrockScheme>,
JsonSerializer<BedrockScheme> {
    public BiMap<String, Class<? extends BedrockComponentBase>> components = HashBiMap.create();

    public static boolean isEmpty(JsonElement element) {
        if (element.isJsonArray()) {
            return element.getAsJsonArray().size() == 0;
        }
        if (element.isJsonObject()) {
            return element.getAsJsonObject().size() == 0;
        }
        if (element.isJsonPrimitive()) {
            JsonPrimitive primitive = element.getAsJsonPrimitive();
            if (primitive.isString()) {
                return primitive.getAsString().isEmpty();
            }
            if (primitive.isNumber()) {
                return Operation.equals(primitive.getAsDouble(), 0.0);
            }
        }
        return element.isJsonNull();
    }

    public BedrockSchemeJsonAdapter() {
        this.components.put((Object)"minecraft:emitter_local_space", BedrockComponentLocalSpace.class);
        this.components.put((Object)"minecraft:emitter_initialization", BedrockComponentInitialization.class);
        this.components.put((Object)"minecraft:emitter_rate_instant", BedrockComponentRateInstant.class);
        this.components.put((Object)"minecraft:emitter_rate_steady", BedrockComponentRateSteady.class);
        this.components.put((Object)"minecraft:emitter_lifetime_looping", BedrockComponentLifetimeLooping.class);
        this.components.put((Object)"minecraft:emitter_lifetime_once", BedrockComponentLifetimeOnce.class);
        this.components.put((Object)"minecraft:emitter_lifetime_expression", BedrockComponentLifetimeExpression.class);
        this.components.put((Object)"minecraft:emitter_shape_disc", BedrockComponentShapeDisc.class);
        this.components.put((Object)"minecraft:emitter_shape_box", BedrockComponentShapeBox.class);
        this.components.put((Object)"minecraft:emitter_shape_entity_aabb", BedrockComponentShapeEntityAABB.class);
        this.components.put((Object)"minecraft:emitter_shape_point", BedrockComponentShapePoint.class);
        this.components.put((Object)"minecraft:emitter_shape_sphere", BedrockComponentShapeSphere.class);
        this.components.put((Object)"minecraft:particle_lifetime_expression", BedrockComponentParticleLifetime.class);
        this.components.put((Object)"minecraft:particle_expire_if_in_blocks", BedrockComponentExpireInBlocks.class);
        this.components.put((Object)"minecraft:particle_expire_if_not_in_blocks", BedrockComponentExpireNotInBlocks.class);
        this.components.put((Object)"minecraft:particle_kill_plane", BedrockComponentKillPlane.class);
        this.components.put((Object)"minecraft:particle_appearance_billboard", BedrockComponentAppearanceBillboard.class);
        this.components.put((Object)"minecraft:particle_appearance_lighting", BedrockComponentAppearanceLighting.class);
        this.components.put((Object)"minecraft:particle_appearance_tinting", BedrockComponentAppearanceTinting.class);
        this.components.put((Object)"blockbuster:particle_collision_appearance", BedrockComponentCollisionAppearance.class);
        this.components.put((Object)"blockbuster:particle_collision_tinting", BedrockComponentCollisionTinting.class);
        this.components.put((Object)"minecraft:particle_initial_speed", BedrockComponentInitialSpeed.class);
        this.components.put((Object)"minecraft:particle_initial_spin", BedrockComponentInitialSpin.class);
        this.components.put((Object)"minecraft:particle_motion_collision", BedrockComponentMotionCollision.class);
        this.components.put((Object)"minecraft:particle_motion_dynamic", BedrockComponentMotionDynamic.class);
        this.components.put((Object)"minecraft:particle_motion_parametric", BedrockComponentMotionParametric.class);
    }

    public BedrockScheme deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BedrockScheme scheme = new BedrockScheme();
        if (!json.isJsonObject()) {
            throw new JsonParseException("The root element of Bedrock particle should be an object!");
        }
        JsonObject root = json.getAsJsonObject();
        try {
            this.parseEffect(scheme, this.getObject(root, "particle_effect", "No particle_effect was found..."));
        }
        catch (MolangException e2) {
            throw new JsonParseException("Couldn't parse some MoLang expression!", (Throwable)e2);
        }
        scheme.setup();
        return scheme;
    }

    private void parseEffect(BedrockScheme scheme, JsonObject effect) throws JsonParseException, MolangException {
        JsonElement curves;
        this.parseDescription(scheme, this.getObject(effect, "description", "No particle_effect.description was found..."));
        if (effect.has("curves") && (curves = effect.get("curves")).isJsonObject()) {
            this.parseCurves(scheme, curves.getAsJsonObject());
        }
        this.parseComponents(scheme, this.getObject(effect, "components", "No particle_effect.components was found..."));
    }

    private void parseDescription(BedrockScheme scheme, JsonObject description) throws JsonParseException {
        String texture;
        JsonObject parameters;
        if (description.has("identifier")) {
            scheme.identifier = description.get("identifier").getAsString();
        }
        if ((parameters = this.getObject(description, "basic_render_parameters", "No particle_effect.basic_render_parameters was found...")).has("material")) {
            scheme.material = BedrockMaterial.fromString(parameters.get("material").getAsString());
        }
        if (parameters.has("texture") && !(texture = parameters.get("texture").getAsString()).equals("textures/particle/particles")) {
            scheme.texture = RLUtils.create(texture);
        }
    }

    private void parseCurves(BedrockScheme scheme, JsonObject curves) throws MolangException {
        for (Map.Entry entry : curves.entrySet()) {
            JsonElement element = (JsonElement)entry.getValue();
            if (!element.isJsonObject()) continue;
            BedrockCurve curve = new BedrockCurve();
            curve.fromJson(element.getAsJsonObject(), scheme.parser);
            scheme.curves.put((String)entry.getKey(), curve);
        }
    }

    private void parseComponents(BedrockScheme scheme, JsonObject components) throws MolangException {
        for (Map.Entry entry : components.entrySet()) {
            String key = (String)entry.getKey();
            if (!this.components.containsKey((Object)key)) continue;
            BedrockComponentBase component = null;
            try {
                component = (BedrockComponentBase)((Class)this.components.get((Object)key)).getConstructor(new Class[0]).newInstance(new Object[0]);
            }
            catch (Exception exception) {
                // empty catch block
            }
            if (component != null) {
                component.fromJson((JsonElement)entry.getValue(), scheme.parser);
                scheme.components.add(component);
                continue;
            }
            System.out.println("Failed to parse given component " + key + " in " + scheme.identifier + "!");
        }
    }

    private JsonObject getObject(JsonObject object, String key, String message) throws JsonParseException {
        if (!object.has(key) && !object.get(key).isJsonObject()) {
            throw new JsonParseException(message);
        }
        return object.get(key).getAsJsonObject();
    }

    public JsonElement serialize(BedrockScheme src, Type typeOfSrc, JsonSerializationContext context) {
        JsonObject object = new JsonObject();
        JsonObject effect = new JsonObject();
        object.addProperty("format_version", "1.10.0");
        object.add("particle_effect", (JsonElement)effect);
        this.addDescription(effect, src);
        this.addCurves(effect, src);
        this.addComponents(effect, src);
        return object;
    }

    private void addDescription(JsonObject effect, BedrockScheme scheme) {
        JsonObject desc = new JsonObject();
        JsonObject render = new JsonObject();
        effect.add("description", (JsonElement)desc);
        desc.addProperty("identifier", scheme.identifier);
        desc.add("basic_render_parameters", (JsonElement)render);
        render.addProperty("material", scheme.material.id);
        render.addProperty("texture", "textures/particle/particles");
        if (scheme.texture != null && !scheme.texture.equals((Object)BedrockScheme.DEFAULT_TEXTURE)) {
            render.addProperty("texture", "models/particle/" + scheme.texture);
        }
    }

    private void addCurves(JsonObject effect, BedrockScheme scheme) {
        JsonObject curves = new JsonObject();
        effect.add("curves", (JsonElement)curves);
        for (Map.Entry<String, BedrockCurve> entry : scheme.curves.entrySet()) {
            curves.add(entry.getKey(), entry.getValue().toJson());
        }
    }

    private void addComponents(JsonObject effect, BedrockScheme scheme) {
        JsonObject components = new JsonObject();
        effect.add("components", (JsonElement)components);
        for (BedrockComponentBase component : scheme.components) {
            JsonElement element = component.toJson();
            if (BedrockSchemeJsonAdapter.isEmpty(element) && !component.canBeEmpty()) continue;
            components.add((String)this.components.inverse().get(component.getClass()), element);
        }
    }
}

