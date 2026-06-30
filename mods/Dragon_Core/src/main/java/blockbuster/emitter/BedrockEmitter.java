/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  javax.vecmath.Matrix3f
 *  javax.vecmath.Tuple3d
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.GlStateManager$DestFactor
 *  net.minecraft.client.renderer.GlStateManager$SourceFactor
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.EntityLivingBase
 *  net.minecraft.util.ResourceLocation
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 *  net.minecraft.world.World
 */
package blockbuster.emitter;

import blockbuster.BedrockScheme;
import blockbuster.components.IComponentEmitterInitialize;
import blockbuster.components.IComponentEmitterUpdate;
import blockbuster.components.IComponentParticleInitialize;
import blockbuster.components.IComponentParticleMorphRender;
import blockbuster.components.IComponentParticleRender;
import blockbuster.components.IComponentParticleUpdate;
import blockbuster.components.IComponentRenderBase;
import blockbuster.components.appearance.BedrockComponentAppearanceBillboard;
import blockbuster.components.appearance.BedrockComponentCollisionAppearance;
import blockbuster.components.meta.BedrockComponentInitialization;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.IValue;
import blockbuster.math.Variable;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.utils.Interpolations;
import blockbuster.utils.texture.GifHandler;
import eos.moe.dragoncore.EntityList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.vecmath.Matrix3f;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BedrockEmitter {
    public BedrockScheme scheme;
    public List<BedrockParticle> particles = new ArrayList<BedrockParticle>();
    public List<BedrockParticle> splitParticles = new ArrayList<BedrockParticle>();
    public Map<String, IValue> variables;
    public Map<String, Double> initialValues = new HashMap<String, Double>();
    public UUID targetUUID;
    public EntityLivingBase target;
    public World world;
    public boolean lit;
    public boolean added;
    public int sanityTicks;
    public int maxLifeTicks;
    public MolangExpression[] rotationsParser;
    public boolean look;
    public int renderType;
    public boolean running = true;
    public boolean end = false;
    public Vector3d lastGlobal = new Vector3d();
    public Vector3d prevGlobal = new Vector3d();
    public Matrix3f rotation = new Matrix3f(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    public Matrix3f prevRotation = new Matrix3f(1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f);
    public Vector3f angularVelocity = new Vector3f();
    public Vector3d translation = new Vector3d();
    public int age;
    public int lifetime;
    public double spawnedParticles;
    public boolean playing = true;
    public float random1 = (float)Math.random();
    public float random2 = (float)Math.random();
    public float random3 = (float)Math.random();
    public float random4 = (float)Math.random();
    public double[] scale = new double[]{1.0, 1.0, 1.0};
    public int perspective;
    public float cYaw;
    public float cPitch;
    public double cX;
    public double cY;
    public double cZ;
    private BlockPos.MutableBlockPos blockPos = new BlockPos.MutableBlockPos();
    private Variable varAge;
    private Variable varLifetime;
    private Variable varRandom1;
    private Variable varRandom2;
    private Variable varRandom3;
    private Variable varRandom4;
    private Variable varSpeedABS;
    private Variable varSpeedX;
    private Variable varSpeedY;
    private Variable varSpeedZ;
    private Variable varBounces;
    private Variable varEmitterAge;
    private Variable varEmitterLifetime;
    private Variable varEmitterRandom1;
    private Variable varEmitterRandom2;
    private Variable varEmitterRandom3;
    private Variable varEmitterRandom4;
    public double extraX;
    public double extraY;
    public double extraZ;

    public double getDistanceSq() {
        this.setupCameraProperties(0.0f);
        double dx2 = this.cX - this.lastGlobal.x;
        double dy2 = this.cY - this.lastGlobal.y;
        double dz2 = this.cZ - this.lastGlobal.z;
        return dx2 * dx2 + dy2 * dy2 + dz2 * dz2;
    }

    public double getAge() {
        return this.getAge(0.0f);
    }

    public double getAge(float partialTicks) {
        return (double)((float)this.age + partialTicks) / 20.0;
    }

    public void updateTarget() {
        EntityLivingBase livingEntity;
        if (this.targetUUID != null && (livingEntity = EntityList.getLivingEntityByUUID(this.targetUUID)) != null) {
            this.setTarget(livingEntity);
        }
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public void setTarget(EntityLivingBase target) {
        this.target = target;
        this.world = target == null ? null : target.world;
    }

    public void setScheme(BedrockScheme scheme) {
        this.setScheme(scheme, null);
    }

    public void setScheme(BedrockScheme scheme, Map<String, String> variables) {
        this.scheme = scheme;
        if (this.scheme == null) {
            return;
        }
        if (variables != null) {
            this.parseVariables(variables);
        }
        this.lit = true;
        this.stop();
        this.start();
        this.setupVariables();
        this.setEmitterVariables(0.0f);
    }

    public void setupVariables() {
        this.varAge = (Variable)this.scheme.parser.variables.get("variable.particle_age");
        this.varLifetime = (Variable)this.scheme.parser.variables.get("variable.particle_lifetime");
        this.varRandom1 = (Variable)this.scheme.parser.variables.get("variable.particle_random_1");
        this.varRandom2 = (Variable)this.scheme.parser.variables.get("variable.particle_random_2");
        this.varRandom3 = (Variable)this.scheme.parser.variables.get("variable.particle_random_3");
        this.varRandom4 = (Variable)this.scheme.parser.variables.get("variable.particle_random_4");
        this.varSpeedABS = (Variable)this.scheme.parser.variables.get("variable.particle_speed.length");
        this.varSpeedX = (Variable)this.scheme.parser.variables.get("variable.particle_speed.x");
        this.varSpeedY = (Variable)this.scheme.parser.variables.get("variable.particle_speed.y");
        this.varSpeedZ = (Variable)this.scheme.parser.variables.get("variable.particle_speed.z");
        this.varBounces = (Variable)this.scheme.parser.variables.get("variable.particle_bounces");
        this.varEmitterAge = (Variable)this.scheme.parser.variables.get("variable.emitter_age");
        this.varEmitterLifetime = (Variable)this.scheme.parser.variables.get("variable.emitter_lifetime");
        this.varEmitterRandom1 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_1");
        this.varEmitterRandom2 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_2");
        this.varEmitterRandom3 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_3");
        this.varEmitterRandom4 = (Variable)this.scheme.parser.variables.get("variable.emitter_random_4");
    }

    public void setParticleVariables(BedrockParticle particle, float partialTicks) {
        if (this.varAge != null) {
            this.varAge.set(particle.getAge(partialTicks));
        }
        if (this.varLifetime != null) {
            this.varLifetime.set((double)particle.lifetime / 20.0);
        }
        if (this.varRandom1 != null) {
            this.varRandom1.set(particle.random1);
        }
        if (this.varRandom2 != null) {
            this.varRandom2.set(particle.random2);
        }
        if (this.varRandom3 != null) {
            this.varRandom3.set(particle.random3);
        }
        if (this.varRandom4 != null) {
            this.varRandom4.set(particle.random4);
        }
        if (this.varSpeedABS != null) {
            this.varSpeedABS.set(particle.speed.length());
        }
        if (this.varSpeedX != null) {
            this.varSpeedX.set(particle.speed.x);
        }
        if (this.varSpeedY != null) {
            this.varSpeedY.set(particle.speed.y);
        }
        if (this.varSpeedZ != null) {
            this.varSpeedZ.set(particle.speed.z);
        }
        if (this.varBounces != null) {
            this.varBounces.set(particle.bounces);
        }
        this.scheme.updateCurves();
        BedrockComponentInitialization component = this.scheme.get(BedrockComponentInitialization.class);
        if (component != null) {
            component.particleUpdate.get();
        }
    }

    public void setEmitterVariables(float partialTicks) {
        for (Map.Entry<String, Double> entry : this.initialValues.entrySet()) {
            Variable var = (Variable)this.scheme.parser.variables.get(entry.getKey());
            if (var == null) continue;
            var.set(entry.getValue());
        }
        if (this.varEmitterAge != null) {
            this.varEmitterAge.set(this.getAge(partialTicks));
        }
        if (this.varEmitterLifetime != null) {
            this.varEmitterLifetime.set((double)this.lifetime / 20.0);
        }
        if (this.varEmitterRandom1 != null) {
            this.varEmitterRandom1.set(this.random1);
        }
        if (this.varEmitterRandom2 != null) {
            this.varEmitterRandom2.set(this.random2);
        }
        if (this.varEmitterRandom3 != null) {
            this.varEmitterRandom3.set(this.random3);
        }
        if (this.varEmitterRandom4 != null) {
            this.varEmitterRandom4.set(this.random4);
        }
        this.scheme.updateCurves();
    }

    public void parseVariables(Map<String, String> variables) {
        this.variables = new HashMap<String, IValue>();
        for (Map.Entry<String, String> entry : variables.entrySet()) {
            this.parseVariable(entry.getKey(), entry.getValue());
        }
    }

    public void parseVariable(String name, String expression) {
        try {
            this.variables.put(name, this.scheme.parser.parse(expression));
        }
        catch (Exception exception) {
            // empty catch block
        }
    }

    public void replaceVariables() {
        if (this.variables == null) {
            return;
        }
        for (Map.Entry<String, IValue> entry : this.variables.entrySet()) {
            Variable var = (Variable)this.scheme.parser.variables.get(entry.getKey());
            if (var == null) continue;
            var.set(entry.getValue().get().doubleValue());
        }
    }

    public void start() {
        if (this.playing) {
            return;
        }
        this.age = 0;
        this.spawnedParticles = 0.0;
        this.playing = true;
        for (IComponentEmitterInitialize component : this.scheme.emitterInitializes) {
            component.apply(this);
        }
    }

    public void stop() {
        if (!this.playing) {
            return;
        }
        this.spawnedParticles = 0.0;
        this.playing = false;
        this.random1 = (float)Math.random();
        this.random2 = (float)Math.random();
        this.random3 = (float)Math.random();
        this.random4 = (float)Math.random();
    }

    public void stopWithOnce() {
        this.end = true;
    }

    public void update() {
        if (this.scheme == null) {
            return;
        }
        this.setEmitterVariables(0.0f);
        for (IComponentEmitterUpdate component : this.scheme.emitterUpdates) {
            component.update(this);
        }
        this.setEmitterVariables(0.0f);
        this.updateParticles();
        ++this.age;
        ++this.sanityTicks;
    }

    private void updateParticles() {
        Iterator<BedrockParticle> it2 = this.particles.iterator();
        while (it2.hasNext()) {
            BedrockParticle particle = it2.next();
            this.updateParticle(particle);
            if (!particle.dead) continue;
            it2.remove();
        }
        if (!this.splitParticles.isEmpty()) {
            this.particles.addAll(this.splitParticles);
            this.splitParticles.clear();
        }
    }

    private void updateParticle(BedrockParticle particle) {
        particle.update(this);
        this.setParticleVariables(particle, 0.0f);
        for (IComponentParticleUpdate component : this.scheme.particleUpdates) {
            component.update(this, particle);
        }
    }

    public void spawnParticle() {
        if (!this.running) {
            return;
        }
        if (this.target != null && this.target.isDead) {
            return;
        }
        this.particles.add(this.createParticle(false));
    }

    public BedrockParticle createParticle(boolean forceRelative) {
        BedrockParticle particle = new BedrockParticle();
        this.setParticleVariables(particle, 0.0f);
        particle.setupMatrix(this);
        for (IComponentParticleInitialize component : this.scheme.particleInitializes) {
            component.apply(this, particle);
        }
        if (particle.relativePosition && !particle.relativeRotation) {
            Vector3f vec = new Vector3f(particle.position);
            particle.matrix.transform((Tuple3f)vec);
            particle.position.x = vec.x;
            particle.position.y = vec.y;
            particle.position.z = vec.z;
        }
        if (!particle.relativePosition || !particle.relativeRotation) {
            particle.position.add((Tuple3d)this.lastGlobal);
            particle.initialPosition.add((Tuple3d)this.lastGlobal);
        }
        particle.prevPosition.set((Tuple3d)particle.position);
        particle.prevRotation = particle.rotation = particle.initialRotation;
        return particle;
    }

    public void render(float partialTicks) {
        if (this.scheme == null) {
            return;
        }
        this.setupCameraProperties(partialTicks);
        List<IComponentParticleRender> renders = this.scheme.particleRender;
        List<IComponentParticleMorphRender> morphRenders = this.scheme.particleMorphRender;
        boolean particleRendering = true;
        int thirdPersonView = Minecraft.getMinecraft().gameSettings.thirdPersonView;
        if (this.renderType == 1 && thirdPersonView != 0) {
            return;
        }
        if (this.renderType == 2 && thirdPersonView == 0) {
            return;
        }
        if (particleRendering) {
            this.setupOpenGL(partialTicks);
            for (IComponentParticleRender component : renders) {
                component.preRender(this, partialTicks);
            }
            if (!(this.particles.isEmpty() || this.targetUUID != null && this.target == null)) {
                this.depthSorting();
                this.renderParticles(this.scheme.texture, renders, false, partialTicks);
                BedrockComponentCollisionAppearance collisionAppearance = this.scheme.getOrCreate(BedrockComponentCollisionAppearance.class);
                if (collisionAppearance != null && collisionAppearance.texture != null) {
                    this.renderParticles(collisionAppearance.texture, renders, true, partialTicks);
                }
            }
            for (IComponentParticleRender component : renders) {
                component.postRender(this, partialTicks);
            }
            this.endOpenGL();
        }
    }

    private void renderParticles(List<? extends IComponentParticleMorphRender> renderComponents, boolean collided, float partialTicks) {
        BufferBuilder builder = Tessellator.getInstance().getBuffer();
        for (BedrockParticle particle : this.particles) {
            this.setEmitterVariables(partialTicks);
            this.setParticleVariables(particle, partialTicks);
            for (IComponentRenderBase iComponentRenderBase : renderComponents) {
                iComponentRenderBase.render(this, particle, builder, partialTicks);
            }
        }
    }

    private void renderParticles(ResourceLocation texture, List<? extends IComponentParticleRender> renderComponents, boolean collided, float partialTicks) {
        BufferBuilder builder = Tessellator.getInstance().getBuffer();
        GifHandler.bindTexture(texture, this.age, partialTicks);
        builder.begin(7, DefaultVertexFormats.POSITION_TEX_LMAP_COLOR);
        for (BedrockParticle particle : this.particles) {
            boolean collisionStuff;
            boolean bl2 = collisionStuff = particle.isCollisionTexture(this) || particle.isCollisionTinting(this);
            if (collisionStuff != collided) continue;
            this.setEmitterVariables(partialTicks);
            this.setParticleVariables(particle, partialTicks);
            for (IComponentRenderBase iComponentRenderBase : renderComponents) {
                if (collisionStuff && iComponentRenderBase.getClass() == BedrockComponentAppearanceBillboard.class) continue;
                iComponentRenderBase.render(this, particle, builder, partialTicks);
            }
        }
        Tessellator.getInstance().draw();
    }

    private void setupOpenGL(float partialTicks) {
        Entity camera = Minecraft.getMinecraft().getRenderViewEntity();
        double playerX = camera.prevPosX + (camera.posX - camera.prevPosX) * (double)partialTicks;
        double playerY = camera.prevPosY + (camera.posY - camera.prevPosY) * (double)partialTicks;
        double playerZ = camera.prevPosZ + (camera.posZ - camera.prevPosZ) * (double)partialTicks;
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.blendFunc((GlStateManager.SourceFactor)GlStateManager.SourceFactor.SRC_ALPHA, (GlStateManager.DestFactor)GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
        GlStateManager.alphaFunc((int)516, (float)0.0f);
        BufferBuilder builder = Tessellator.getInstance().getBuffer();
        GlStateManager.disableTexture2D();
        builder.setTranslation(-playerX, -playerY, -playerZ);
        GlStateManager.disableCull();
        GlStateManager.enableTexture2D();
    }

    private void endOpenGL() {
        Tessellator.getInstance().getBuffer().setTranslation(0.0, 0.0, 0.0);
        GlStateManager.disableBlend();
        GlStateManager.alphaFunc((int)516, (float)0.1f);
    }

    private void depthSorting() {
    }

    public void setupCameraProperties(float partialTicks) {
        if (this.world != null) {
            Entity camera = Minecraft.getMinecraft().getRenderViewEntity();
            this.perspective = Minecraft.getMinecraft().gameSettings.thirdPersonView;
            this.cYaw = 180.0f - Interpolations.lerp(camera.prevRotationYaw, camera.rotationYaw, partialTicks);
            this.cPitch = 180.0f - Interpolations.lerp(camera.prevRotationPitch, camera.rotationPitch, partialTicks);
            this.cX = Interpolations.lerp(camera.prevPosX, camera.posX, (double)partialTicks);
            this.cY = Interpolations.lerp(camera.prevPosY, camera.posY, (double)partialTicks) + (double)camera.getEyeHeight();
            this.cZ = Interpolations.lerp(camera.prevPosZ, camera.posZ, (double)partialTicks);
        }
    }

    public int getBrightnessForRender(float partialTicks, double x2, double y2, double z2) {
        if (this.lit || this.world == null) {
            return 0xF000F0;
        }
        this.blockPos.setPos(x2, y2, z2);
        return this.world.isBlockLoaded((BlockPos)this.blockPos) ? this.world.getCombinedLight((BlockPos)this.blockPos, 0) : 0;
    }
}

