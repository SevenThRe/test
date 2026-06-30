/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.Gson
 *  com.google.gson.GsonBuilder
 *  com.google.gson.JsonElement
 *  net.minecraft.util.ResourceLocation
 */
package blockbuster;

import blockbuster.BedrockCurve;
import blockbuster.BedrockMaterial;
import blockbuster.BedrockSchemeJsonAdapter;
import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentBase;
import blockbuster.components.IComponentEmitterInitialize;
import blockbuster.components.IComponentEmitterUpdate;
import blockbuster.components.IComponentParticleInitialize;
import blockbuster.components.IComponentParticleMorphRender;
import blockbuster.components.IComponentParticleRender;
import blockbuster.components.IComponentParticleUpdate;
import blockbuster.components.motion.BedrockComponentInitialSpeed;
import blockbuster.math.Variable;
import blockbuster.math.molang.MolangParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.minecraft.util.ResourceLocation;

public class BedrockScheme {
    public static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation("dragoncore", "default_particles.png");
    public static final Gson JSON_PARSER = new GsonBuilder().registerTypeAdapter(BedrockScheme.class, (Object)new BedrockSchemeJsonAdapter()).create();
    public String identifier = "";
    public BedrockMaterial material = BedrockMaterial.OPAQUE;
    public ResourceLocation texture = DEFAULT_TEXTURE;
    public Map<String, BedrockCurve> curves = new HashMap<String, BedrockCurve>();
    public List<BedrockComponentBase> components = new ArrayList<BedrockComponentBase>();
    public List<IComponentEmitterInitialize> emitterInitializes;
    public List<IComponentEmitterUpdate> emitterUpdates;
    public List<IComponentParticleInitialize> particleInitializes;
    public List<IComponentParticleUpdate> particleUpdates;
    public List<IComponentParticleRender> particleRender;
    public List<IComponentParticleMorphRender> particleMorphRender;
    private boolean factory;
    public MolangParser parser = new MolangParser();

    public static BedrockScheme parse(String json) {
        return (BedrockScheme)JSON_PARSER.fromJson(json, BedrockScheme.class);
    }

    public static BedrockScheme parse(JsonElement json) {
        return (BedrockScheme)JSON_PARSER.fromJson(json, BedrockScheme.class);
    }

    public static JsonElement toJson(BedrockScheme scheme) {
        return JSON_PARSER.toJsonTree((Object)scheme);
    }

    public static BedrockScheme dupe(BedrockScheme scheme) {
        return BedrockScheme.parse(BedrockScheme.toJson(scheme));
    }

    public BedrockScheme() {
        this.parser.register(new Variable("variable.particle_age", 0.0));
        this.parser.register(new Variable("variable.particle_lifetime", 0.0));
        this.parser.register(new Variable("variable.particle_random_1", 0.0));
        this.parser.register(new Variable("variable.particle_random_2", 0.0));
        this.parser.register(new Variable("variable.particle_random_3", 0.0));
        this.parser.register(new Variable("variable.particle_random_4", 0.0));
        this.parser.register(new Variable("variable.particle_speed.length", 0.0));
        this.parser.register(new Variable("variable.particle_speed.x", 0.0));
        this.parser.register(new Variable("variable.particle_speed.y", 0.0));
        this.parser.register(new Variable("variable.particle_speed.z", 0.0));
        this.parser.register(new Variable("variable.particle_bounces", 0.0));
        this.parser.register(new Variable("variable.emitter_age", 0.0));
        this.parser.register(new Variable("variable.emitter_lifetime", 0.0));
        this.parser.register(new Variable("variable.emitter_random_1", 0.0));
        this.parser.register(new Variable("variable.emitter_random_2", 0.0));
        this.parser.register(new Variable("variable.emitter_random_3", 0.0));
        this.parser.register(new Variable("variable.emitter_random_4", 0.0));
        this.parser.register(new Variable("variable.heady", 0.0));
        this.parser.register(new Variable("variable.bodyyaw", 0.0));
        this.parser.register(new Variable("variable.headpitch", 0.0));
    }

    public BedrockScheme factory(boolean factory) {
        this.factory = factory;
        return this;
    }

    public boolean isFactory() {
        return this.factory;
    }

    public void setup() {
        this.getOrCreate(BedrockComponentInitialSpeed.class);
        this.emitterInitializes = this.getComponents(IComponentEmitterInitialize.class);
        this.emitterUpdates = this.getComponents(IComponentEmitterUpdate.class);
        this.particleInitializes = this.getComponents(IComponentParticleInitialize.class);
        this.particleUpdates = this.getComponents(IComponentParticleUpdate.class);
        this.particleRender = this.getComponents(IComponentParticleRender.class);
        this.particleMorphRender = this.getComponents(IComponentParticleMorphRender.class);
        for (Map.Entry<String, BedrockCurve> entry : this.curves.entrySet()) {
            entry.getValue().variable = (Variable)this.parser.variables.get(entry.getKey());
        }
    }

    public <T extends IComponentBase> List<T> getComponents(Class<T> clazz) {
        ArrayList<IComponentBase> list = new ArrayList<IComponentBase>();
        for (BedrockComponentBase component : this.components) {
            if (!clazz.isAssignableFrom(component.getClass())) continue;
            list.add((IComponentBase)((Object)component));
        }
        if (list.size() > 1) {
            Collections.sort(list, Comparator.comparingInt(IComponentBase::getSortingIndex));
        }
        return list;
    }

    public <T extends BedrockComponentBase> T get(Class<T> clazz) {
        for (BedrockComponentBase component : this.components) {
            if (!clazz.isAssignableFrom(component.getClass())) continue;
            return (T)component;
        }
        return null;
    }

    public <T extends BedrockComponentBase> T getExact(Class<T> clazz) {
        for (BedrockComponentBase component : this.components) {
            if (!clazz.equals(component.getClass())) continue;
            return (T)component;
        }
        return null;
    }

    public <T extends BedrockComponentBase> T add(Class<T> clazz) {
        BedrockComponentBase result = null;
        try {
            result = (BedrockComponentBase)clazz.getConstructor(new Class[0]).newInstance(new Object[0]);
            this.components.add(result);
            this.setup();
        }
        catch (Exception exception) {
            // empty catch block
        }
        return (T)result;
    }

    public <T extends BedrockComponentBase> T getOrCreate(Class<T> clazz) {
        return this.getOrCreate(clazz, clazz);
    }

    public <T extends BedrockComponentBase> T getOrCreateExact(Class<T> clazz) {
        return this.getOrCreateExact(clazz, clazz);
    }

    public <T extends BedrockComponentBase> T getOrCreate(Class<T> clazz, Class subclass) {
        T result = this.get(clazz);
        if (result == null) {
            result = this.add(subclass);
        }
        return result;
    }

    public <T extends BedrockComponentBase> T getOrCreateExact(Class<T> clazz, Class subclass) {
        T result = this.getExact(clazz);
        if (result == null) {
            result = this.add(subclass);
        }
        return result;
    }

    public <T extends BedrockComponentBase> T remove(Class<T> clazz) {
        Iterator<BedrockComponentBase> it2 = this.components.iterator();
        while (it2.hasNext()) {
            BedrockComponentBase component = it2.next();
            if (!clazz.isAssignableFrom(component.getClass())) continue;
            it2.remove();
            return (T)component;
        }
        return null;
    }

    public <T extends BedrockComponentBase> T replace(Class<T> clazz, Class subclass) {
        this.remove(clazz);
        return this.add(subclass);
    }

    public void updateCurves() {
        for (BedrockCurve curve : this.curves.values()) {
            if (curve.variable == null) continue;
            curve.variable.set(curve.compute());
        }
    }
}

