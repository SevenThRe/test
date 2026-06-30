/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  com.google.gson.JsonPrimitive
 *  javax.annotation.Nullable
 *  javax.vecmath.Tuple3d
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  net.minecraft.entity.Entity
 *  net.minecraft.util.EnumFacing$Axis
 *  net.minecraft.util.math.AxisAlignedBB
 *  net.minecraft.util.math.BlockPos
 *  net.minecraft.util.math.BlockPos$MutableBlockPos
 */
package blockbuster.components.motion;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleUpdate;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.MathUtils;
import blockbuster.math.Operation;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import eos.moe.dragoncore.interfaces.IEntity;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nullable;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

public class BedrockComponentMotionCollision
extends BedrockComponentBase
implements IComponentParticleUpdate {
    public MolangExpression enabled = MolangParser.ONE;
    public boolean preserveEnergy = false;
    public boolean entityCollision;
    public boolean momentum;
    public float collisionDrag = 0.0f;
    public float bounciness = 1.0f;
    public float randomBounciness = 0.0f;
    public float randomDamp = 0.0f;
    public float damp = 0.0f;
    public int splitParticleCount;
    public float splitParticleSpeedThreshold;
    public float radius = 0.01f;
    public boolean expireOnImpact;
    public MolangExpression expirationDelay = MolangParser.ZERO;
    public boolean realisticCollision;
    public boolean realisticCollisionDrag;
    private Vector3d previous = new Vector3d();
    private Vector3d current = new Vector3d();
    private BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

    public static float getComponent(Vector3f vector, EnumFacing.Axis component) {
        if (component == EnumFacing.Axis.X) {
            return vector.x;
        }
        if (component == EnumFacing.Axis.Y) {
            return vector.y;
        }
        return vector.z;
    }

    public static void setComponent(Vector3f vector, EnumFacing.Axis component, float value) {
        if (component == EnumFacing.Axis.X) {
            vector.x = value;
        } else if (component == EnumFacing.Axis.Y) {
            vector.y = value;
        } else {
            vector.z = value;
        }
    }

    public static void negateComponent(Vector3f vector, EnumFacing.Axis component) {
        BedrockComponentMotionCollision.setComponent(vector, component, -BedrockComponentMotionCollision.getComponent(vector, component));
    }

    public static double getComponent(Vector3d vector, EnumFacing.Axis component) {
        if (component == EnumFacing.Axis.X) {
            return vector.x;
        }
        if (component == EnumFacing.Axis.Y) {
            return vector.y;
        }
        return vector.z;
    }

    public static void setComponent(Vector3d vector, EnumFacing.Axis component, double value) {
        if (component == EnumFacing.Axis.X) {
            vector.x = value;
        } else if (component == EnumFacing.Axis.Y) {
            vector.y = value;
        } else {
            vector.z = value;
        }
    }

    public static void negateComponent(Vector3d vector, EnumFacing.Axis component) {
        BedrockComponentMotionCollision.setComponent(vector, component, -BedrockComponentMotionCollision.getComponent(vector, component));
    }

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("enabled")) {
            this.enabled = parser.parseJson(element.get("enabled"));
        }
        if (element.has("entityCollision")) {
            this.entityCollision = element.get("entityCollision").getAsBoolean();
        }
        if (element.has("momentum")) {
            this.momentum = element.get("momentum").getAsBoolean();
        }
        if (element.has("realistic_collision_drag")) {
            this.realisticCollisionDrag = element.get("realistic_collision_drag").getAsBoolean();
        }
        if (element.has("collision_drag")) {
            this.collisionDrag = element.get("collision_drag").getAsFloat();
        }
        if (element.has("coefficient_of_restitution")) {
            this.bounciness = element.get("coefficient_of_restitution").getAsFloat();
        }
        if (element.has("bounciness_randomness")) {
            this.randomBounciness = element.get("bounciness_randomness").getAsFloat();
        }
        if (element.has("preserveEnergy") && element.get("preserveEnergy").isJsonPrimitive()) {
            JsonPrimitive energy = element.get("preserveEnergy").getAsJsonPrimitive();
            this.preserveEnergy = energy.isBoolean() ? energy.getAsBoolean() : MolangExpression.isOne(parser.parseJson((JsonElement)energy));
        }
        if (element.has("damp")) {
            this.damp = element.get("damp").getAsFloat();
        }
        if (element.has("random_damp")) {
            this.randomDamp = element.get("random_damp").getAsFloat();
        }
        if (element.has("split_particle_count")) {
            this.splitParticleCount = element.get("split_particle_count").getAsInt();
        }
        if (element.has("split_particle_speedThreshold")) {
            this.splitParticleSpeedThreshold = element.get("split_particle_speedThreshold").getAsFloat();
        }
        if (element.has("collision_radius")) {
            this.radius = element.get("collision_radius").getAsFloat();
        }
        if (element.has("expire_on_contact")) {
            this.expireOnImpact = element.get("expire_on_contact").getAsBoolean();
        }
        if (element.has("expirationDelay")) {
            this.expirationDelay = parser.parseJson(element.get("expirationDelay"));
        }
        if (element.has("realisticCollision")) {
            this.realisticCollision = element.get("realisticCollision").getAsBoolean();
        }
        return super.fromJson((JsonElement)element, parser);
    }

    @Override
    public JsonElement toJson() {
        JsonObject object = new JsonObject();
        if (!MolangExpression.isOne(this.enabled)) {
            object.add("enabled", this.enabled.toJson());
        }
        if (this.realisticCollision) {
            object.addProperty("realisticCollision", Boolean.valueOf(true));
        }
        if (this.entityCollision) {
            object.addProperty("entityCollision", Boolean.valueOf(true));
        }
        if (this.momentum) {
            object.addProperty("momentum", Boolean.valueOf(true));
        }
        if (this.realisticCollisionDrag) {
            object.addProperty("realistic_collision_drag", Boolean.valueOf(true));
        }
        if (this.collisionDrag != 0.0f) {
            object.addProperty("collision_drag", (Number)Float.valueOf(this.collisionDrag));
        }
        if (this.bounciness != 1.0f) {
            object.addProperty("coefficient_of_restitution", (Number)Float.valueOf(this.bounciness));
        }
        if (this.randomBounciness != 0.0f) {
            object.addProperty("bounciness_randomness", (Number)Float.valueOf(this.randomBounciness));
        }
        if (this.preserveEnergy) {
            object.addProperty("preserveEnergy", Boolean.valueOf(this.preserveEnergy));
        }
        if (this.damp != 0.0f) {
            object.addProperty("damp", (Number)Float.valueOf(this.damp));
        }
        if (this.randomDamp != 0.0f) {
            object.addProperty("random_damp", (Number)Float.valueOf(this.randomDamp));
        }
        if (this.splitParticleCount != 0) {
            object.addProperty("split_particle_count", (Number)this.splitParticleCount);
        }
        if (this.splitParticleSpeedThreshold != 0.0f) {
            object.addProperty("split_particle_speedThreshold", (Number)Float.valueOf(this.splitParticleSpeedThreshold));
        }
        if (this.radius != 0.01f) {
            object.addProperty("collision_radius", (Number)Float.valueOf(this.radius));
        }
        if (this.expireOnImpact) {
            object.addProperty("expire_on_contact", Boolean.valueOf(true));
        }
        if (!MolangExpression.isZero(this.expirationDelay)) {
            object.add("expirationDelay", this.expirationDelay.toJson());
        }
        return object;
    }

    @Override
    public void update(BedrockEmitter emitter, BedrockParticle particle) {
        particle.realisticCollisionDrag = this.realisticCollisionDrag;
        if (emitter.world == null) {
            return;
        }
        float r2 = this.radius;
        this.previous.set((Tuple3d)particle.getGlobalPosition(emitter, particle.prevPosition));
        this.current.set((Tuple3d)particle.getGlobalPosition(emitter));
        Vector3d prev = this.previous;
        Vector3d now = this.current;
        double x2 = now.x - prev.x;
        double y2 = now.y - prev.y;
        double z2 = now.z - prev.z;
        boolean veryBig = Math.abs(x2) > 10.0 || Math.abs(y2) > 10.0 || Math.abs(z2) > 10.0;
        this.pos.setPos(now.x, now.y, now.z);
        if (veryBig || !emitter.world.isBlockLoaded((BlockPos)this.pos)) {
            return;
        }
        AxisAlignedBB aabb = new AxisAlignedBB(prev.x - (double)r2, prev.y - (double)r2, prev.z - (double)r2, prev.x + (double)r2, prev.y + (double)r2, prev.z + (double)r2);
        double d0 = y2;
        double origX = x2;
        double origZ = z2;
        List entities = emitter.world.getEntitiesWithinAABB(Entity.class, aabb.expand(x2, y2, z2));
        HashMap<Entity, AxisAlignedBB> entityAABBs = new HashMap<Entity, AxisAlignedBB>();
        HashMap<Entity, CollisionOffset> staticEntityAABBs = new HashMap<Entity, CollisionOffset>();
        List list = emitter.world.getCollisionBoxes(null, aabb.expand(x2, y2, z2));
        if ((!list.isEmpty() || !entities.isEmpty() && this.entityCollision) && !particle.intersected) {
            particle.firstIntersection = particle.age;
            particle.intersected = true;
        }
        if (!particle.manual && !Operation.equals(this.enabled.get(), 0.0)) {
            AxisAlignedBB entityAABB;
            if (this.entityCollision) {
                for (Entity entity : entities) {
                    AxisAlignedBB aabb2 = new AxisAlignedBB(prev.x - (double)r2, prev.y - (double)r2, prev.z - (double)r2, prev.x + (double)r2, prev.y + (double)r2, prev.z + (double)r2);
                    entityAABB = entity.getEntityBoundingBox();
                    double y22 = y2;
                    double x22 = x2;
                    double z22 = z2;
                    y22 = entityAABB.calculateYOffset(aabb2, y22);
                    aabb2 = aabb2.offset(0.0, y22, 0.0);
                    x22 = entityAABB.calculateXOffset(aabb2, x22);
                    aabb2 = aabb2.offset(x22, 0.0, 0.0);
                    z22 = entityAABB.calculateZOffset(aabb2, z22);
                    aabb2 = aabb2.offset(0.0, 0.0, z22);
                    if (d0 == y22 && origX == x22 && origZ == z22) {
                        entityAABBs.put(entity, entityAABB);
                        continue;
                    }
                    list.add(entityAABB);
                    staticEntityAABBs.put(entity, new CollisionOffset(entityAABB, x22, y22, z22));
                    if (!this.momentum || d0 != y22) continue;
                    this.momentum(particle, entity);
                }
            }
            CollisionOffset offsetData = this.calculateOffsets(aabb, list, x2, y2, z2);
            aabb = offsetData.aabb;
            x2 = offsetData.x;
            y2 = offsetData.y;
            z2 = offsetData.z;
            if (d0 != y2 || origX != x2 || origZ != z2) {
                this.collision(particle, emitter, prev);
                now.set(aabb.minX + (double)r2, aabb.minY + (double)r2, aabb.minZ + (double)r2);
                if (d0 != y2) {
                    now.y = d0 < y2 ? aabb.minY : aabb.maxY;
                    now.y = now.y + (d0 < y2 ? (double)r2 : (double)(-r2));
                    this.collisionHandler(particle, emitter, EnumFacing.Axis.Y, now, prev);
                    particle.entityCollisionTime.keySet().retainAll(staticEntityAABBs.keySet());
                    for (Map.Entry entry : staticEntityAABBs.entrySet()) {
                        CollisionOffset offsetData2 = (CollisionOffset)entry.getValue();
                        AxisAlignedBB entityAABB2 = offsetData2.aabb;
                        Entity collidingEntity = (Entity)entry.getKey();
                        if (d0 != offsetData2.y && origX == offsetData2.x && origZ == offsetData2.z) {
                            this.inertia(particle, collidingEntity, now);
                        }
                        if (particle.entityCollisionTime.containsKey(collidingEntity)) {
                            particle.entityCollisionTime.get((Object)collidingEntity).y = particle.age;
                            continue;
                        }
                        particle.entityCollisionTime.put((Entity)entry.getKey(), new Vector3f(-1.0f, (float)particle.age, -1.0f));
                    }
                }
                if (origX != x2) {
                    now.x = origX < x2 ? aabb.minX : aabb.maxX;
                    now.x = now.x + (origX < x2 ? (double)r2 : (double)(-r2));
                    this.collisionHandler(particle, emitter, EnumFacing.Axis.X, now, prev);
                }
                if (origZ != z2) {
                    now.z = origZ < z2 ? aabb.minZ : aabb.maxZ;
                    now.z = now.z + (origZ < z2 ? (double)r2 : (double)(-r2));
                    this.collisionHandler(particle, emitter, EnumFacing.Axis.Z, now, prev);
                }
                particle.position.set((Tuple3d)now);
                this.drag(particle);
            } else if (entityAABBs.isEmpty() && this.realisticCollisionDrag) {
                particle.dragFactor = 0.0f;
            }
            for (Map.Entry entry : entityAABBs.entrySet()) {
                Vector3f ray;
                Vector3d frac;
                entityAABB = (AxisAlignedBB)entry.getValue();
                Entity entity = (Entity)entry.getKey();
                Vector3f speedEntity = new Vector3f((float)(entity.posX - entity.prevPosX), (float)(entity.posY - entity.prevPosY), (float)(entity.posZ - entity.prevPosZ));
                if (speedEntity.x == 0.0f && speedEntity.y == 0.0f && speedEntity.z == 0.0f || (frac = this.intersect(ray = speedEntity, particle.getGlobalPosition(emitter), entityAABB)) == null) continue;
                particle.position.add((Tuple3d)frac);
                AxisAlignedBB aabb2 = new AxisAlignedBB(particle.position.x - (double)r2, particle.position.y - (double)r2, particle.position.z - (double)r2, particle.position.x + (double)r2, particle.position.y + (double)r2, particle.position.z + (double)r2);
                this.collision(particle, emitter, prev);
                if (aabb2.minX < entityAABB.maxX && aabb2.maxX > entityAABB.maxX || aabb2.maxX > entityAABB.minX && aabb2.minX < entityAABB.minX) {
                    this.entityCollision(particle, emitter, entity, EnumFacing.Axis.X, prev);
                }
                if (aabb2.minY < entityAABB.maxY && aabb2.maxY > entityAABB.maxY || aabb2.maxY > entityAABB.minY && aabb2.minY < entityAABB.minY) {
                    this.entityCollision(particle, emitter, entity, EnumFacing.Axis.Y, prev);
                }
                if (!(aabb2.minZ < entityAABB.maxZ && aabb2.maxZ > entityAABB.maxZ) && (!(aabb2.maxZ > entityAABB.minZ) || !(aabb2.minZ < entityAABB.minZ))) continue;
                this.entityCollision(particle, emitter, entity, EnumFacing.Axis.Z, prev);
            }
            if (!entityAABBs.isEmpty()) {
                this.drag(particle);
            }
        }
    }

    public void collision(BedrockParticle particle, BedrockEmitter emitter, Vector3d prev) {
        if (this.expireOnImpact) {
            double expirationDelay = this.expirationDelay.get();
            if (expirationDelay != 0.0 && !particle.collided) {
                particle.setExpirationDelay(expirationDelay);
            } else if (expirationDelay == 0.0 && !particle.collided) {
                particle.dead = true;
                return;
            }
        }
        if (particle.relativePosition) {
            particle.relativePosition = false;
            particle.prevPosition.set((Tuple3d)prev);
        }
        particle.collided = true;
    }

    public void entityCollision(BedrockParticle particle, BedrockEmitter emitter, Entity entity, EnumFacing.Axis component, Vector3d prev) {
        Vector3f entitySpeed = new Vector3f((float)(entity.posX - entity.prevPosX), (float)(entity.posY - entity.prevPosY), (float)(entity.posZ - entity.prevPosZ));
        Vector3d entityPosition = new Vector3d(entity.posX, entity.posY, entity.posZ);
        if (this.momentum) {
            this.momentum(particle, entity);
        }
        float tmpTime = BedrockComponentMotionCollision.getComponent(particle.collisionTime, component);
        double delta = BedrockComponentMotionCollision.getComponent(particle.position, component) - BedrockComponentMotionCollision.getComponent(entityPosition, component);
        BedrockComponentMotionCollision.setComponent(particle.position, component, BedrockComponentMotionCollision.getComponent(particle.position, component) + (double)(delta > 0.0 ? this.radius : -this.radius));
        this.collisionHandler(particle, emitter, component, particle.position, prev);
        BedrockComponentMotionCollision.setComponent(particle.collisionTime, component, tmpTime);
        if (delta > 0.0 && component == EnumFacing.Axis.Y) {
            this.inertia(particle, entity, null);
        }
        if (BedrockComponentMotionCollision.getComponent(particle.speed, component) > 0.0f) {
            if (BedrockComponentMotionCollision.getComponent(entitySpeed, component) < 0.0f) {
                BedrockComponentMotionCollision.negateComponent(particle.speed, component);
            }
        } else if (BedrockComponentMotionCollision.getComponent(particle.speed, component) < 0.0f && BedrockComponentMotionCollision.getComponent(entitySpeed, component) > 0.0f) {
            BedrockComponentMotionCollision.negateComponent(particle.speed, component);
        }
        BedrockComponentMotionCollision.setComponent(particle.position, component, BedrockComponentMotionCollision.getComponent(particle.position, component) + (double)(BedrockComponentMotionCollision.getComponent(particle.speed, component) / 20.0f));
    }

    public void collisionHandler(BedrockParticle particle, BedrockEmitter emitter, EnumFacing.Axis component, Vector3d now, Vector3d prev) {
        float collisionTime = BedrockComponentMotionCollision.getComponent(particle.collisionTime, component);
        float speed = BedrockComponentMotionCollision.getComponent(particle.speed, component);
        float accelerationFactor = BedrockComponentMotionCollision.getComponent(particle.accelerationFactor, component);
        if (this.realisticCollision) {
            if (collisionTime != (float)(particle.age - 1)) {
                if (this.bounciness != 0.0f) {
                    BedrockComponentMotionCollision.setComponent(particle.speed, component, -speed * this.bounciness);
                }
            } else if (collisionTime == (float)(particle.age - 1)) {
                BedrockComponentMotionCollision.setComponent(particle.speed, component, 0.0f);
            }
        } else {
            BedrockComponentMotionCollision.setComponent(particle.accelerationFactor, component, accelerationFactor * -this.bounciness);
        }
        if (collisionTime != (float)(particle.age - 1)) {
            if (this.randomBounciness != 0.0f) {
                particle.speed = this.randomBounciness(particle.speed, component, this.randomBounciness);
            }
            if (this.splitParticleCount != 0) {
                this.splitParticle(particle, emitter, component, now, prev);
            }
            if (this.damp != 0.0f) {
                particle.speed = this.damping(particle.speed);
            }
        }
        if (collisionTime != (float)(particle.age - 1)) {
            ++particle.bounces;
        }
        BedrockComponentMotionCollision.setComponent(particle.collisionTime, component, particle.age);
    }

    public void inertia(BedrockParticle particle, Entity entity, @Nullable Vector3d now) {
        if (this.collisionDrag == 0.0f) {
            return;
        }
        Vector3d entitySpeed = new Vector3d(entity.posX - entity.prevPosX, entity.posY - entity.prevPosY, entity.posZ - entity.prevPosZ);
        double prevPrevPosX = ((IEntity)entity).getPrevPrevPosX();
        double prevPrevPosY = ((IEntity)entity).getPrevPrevPosY();
        double prevPrevPosZ = ((IEntity)entity).getPrevPrevPosZ();
        Vector3d prevEntitySpeed = new Vector3d(entity.prevPosX - prevPrevPosX, entity.prevPosY - prevPrevPosY, entity.prevPosZ - prevPrevPosZ);
        if (!particle.entityCollisionTime.containsKey(entity)) {
            prevEntitySpeed.scale(0.0);
        } else {
            particle.offset.x = entitySpeed.x;
            particle.offset.z = entitySpeed.z;
            if (now == null) {
                particle.position.x += entitySpeed.x;
                particle.position.z += entitySpeed.z;
            } else {
                now.x += entitySpeed.x;
                now.z += entitySpeed.z;
            }
        }
        particle.speed.x = (float)((double)particle.speed.x + (double)Math.round((prevEntitySpeed.x - entitySpeed.x) * 1000.0) / 250.0);
        particle.speed.y = (float)((double)particle.speed.y + (double)Math.round((prevEntitySpeed.y - entitySpeed.y) * 1000.0) / 250.0);
        particle.speed.z = (float)((double)particle.speed.z + (double)Math.round((prevEntitySpeed.z - entitySpeed.z) * 1000.0) / 250.0);
    }

    public void momentum(BedrockParticle particle, Entity entity) {
        particle.speed.x = (float)((double)particle.speed.x + 2.0 * (entity.posX - entity.prevPosX));
        particle.speed.y = (float)((double)particle.speed.y + 2.0 * (entity.posY - entity.prevPosY));
        particle.speed.z = (float)((double)particle.speed.z + 2.0 * (entity.posZ - entity.prevPosZ));
    }

    public void drag(BedrockParticle particle) {
        if (this.randomBounciness == 0.0f && !this.realisticCollision || Math.round(particle.speed.x * 10000.0f) != 0 || Math.round(particle.speed.y * 10000.0f) != 0 || Math.round(particle.speed.z * 10000.0f) != 0) {
            particle.dragFactor = this.realisticCollisionDrag ? 3.0f * this.collisionDrag : (particle.dragFactor += this.collisionDrag);
        }
    }

    public Vector3f damping(Vector3f vector) {
        float random = (float)((double)this.randomDamp * (Math.random() * 2.0 - 1.0));
        float clampedValue = MathUtils.clamp(1.0f - this.damp + random, 0.0f, 1.0f);
        vector.scale(clampedValue);
        return vector;
    }

    public void splitParticle(BedrockParticle particle, BedrockEmitter emitter, EnumFacing.Axis component, Vector3d now, Vector3d prev) {
        float speed = BedrockComponentMotionCollision.getComponent(particle.speed, component);
        if (!(Math.abs(speed) > Math.abs(this.splitParticleSpeedThreshold))) {
            return;
        }
        for (int i2 = 0; i2 < this.splitParticleCount; ++i2) {
            BedrockParticle splitParticle = emitter.createParticle(false);
            particle.softCopy(splitParticle);
            splitParticle.position.set((Tuple3d)now);
            splitParticle.prevPosition.set((Tuple3d)prev);
            splitParticle.bounces = 1;
            double splitPosition = BedrockComponentMotionCollision.getComponent(splitParticle.position, component);
            BedrockComponentMotionCollision.setComponent(splitParticle.collisionTime, component, particle.age);
            BedrockComponentMotionCollision.setComponent(splitParticle.position, component, splitPosition);
            Vector3f randomSpeed = this.randomBounciness(particle.speed, component, this.randomBounciness != 0.0f ? this.randomBounciness : 10.0f);
            randomSpeed.scale(1.0f / (float)this.splitParticleCount);
            splitParticle.speed.set((Tuple3f)randomSpeed);
            if (this.damp != 0.0f) {
                splitParticle.speed = this.damping(splitParticle.speed);
            }
            emitter.splitParticles.add(splitParticle);
        }
        particle.dead = true;
    }

    public Vector3f randomBounciness(Vector3f vector0, EnumFacing.Axis component, float randomness) {
        if (randomness != 0.0f) {
            Vector3f vector = new Vector3f(vector0);
            float randomfactor = 0.25f;
            float prevLength = vector.length();
            float random1 = (float)Math.random() * (randomness *= 0.1f);
            float random2 = (float)((double)(randomness * randomfactor) * (Math.random() * 2.0 - 1.0));
            float random3 = (float)((double)(randomness * randomfactor) * (Math.random() * 2.0 - 1.0));
            float vectorValue = BedrockComponentMotionCollision.getComponent(vector, component);
            if (component == EnumFacing.Axis.X) {
                vector.y += random2;
                vector.z += random3;
            } else if (component == EnumFacing.Axis.Y) {
                vector.x += random2;
                vector.z += random3;
            } else {
                vector.y += random2;
                vector.x += random3;
            }
            if (this.bounciness != 0.0f) {
                BedrockComponentMotionCollision.setComponent(vector, component, vectorValue + (vectorValue < 0.0f ? -random1 : random1));
                vector.scale(prevLength / vector.length());
            } else if (vector.x != 0.0f || vector.y != 0.0f || vector.z != 0.0f) {
                if (this.preserveEnergy) {
                    BedrockComponentMotionCollision.setComponent(vector, component, 0.0f);
                }
                if (vector.x != 0.0f || vector.y != 0.0f || vector.z != 0.0f) {
                    vector.scale(prevLength / vector.length());
                }
                BedrockComponentMotionCollision.setComponent(vector, component, vectorValue);
            } else {
                return vector0;
            }
            return vector;
        }
        return vector0;
    }

    public Vector3d intersect(Vector3f ray, Vector3d orig, AxisAlignedBB aabb) {
        double tzmax;
        double tzmin;
        double tymax;
        double tymin;
        double tmin = (aabb.minX - orig.x) / (double)ray.x;
        double tmax = (aabb.maxX - orig.x) / (double)ray.x;
        if (tmin > tmax) {
            double tminTmp = tmin;
            tmin = tmax;
            tmax = tminTmp;
        }
        if ((tymin = (aabb.minY - orig.y) / (double)ray.y) > (tymax = (aabb.maxY - orig.y) / (double)ray.y)) {
            double tyminTmp = tymin;
            tymin = tymax;
            tymax = tyminTmp;
        }
        if (tmin > tymax || tymin > tmax) {
            return null;
        }
        if (tymin > tmin) {
            tmin = tymin;
        }
        if (tymax < tmax) {
            tmax = tymax;
        }
        if ((tzmin = (aabb.minZ - orig.z) / (double)ray.z) > (tzmax = (aabb.maxZ - orig.z) / (double)ray.z)) {
            double tzminTmp = tzmin;
            tzmin = tzmax;
            tzmax = tzminTmp;
        }
        if (tmin > tzmax || tzmin > tmax) {
            return null;
        }
        if (tzmax < tmax) {
            tmax = tzmax;
        }
        Vector3d ray1 = new Vector3d(ray);
        ray1.scale(tmax);
        return ray1;
    }

    public CollisionOffset calculateOffsets(AxisAlignedBB aabb, List<AxisAlignedBB> list, double x2, double y2, double z2) {
        for (AxisAlignedBB axisalignedbb : list) {
            y2 = axisalignedbb.calculateYOffset(aabb, y2);
        }
        aabb = aabb.offset(0.0, y2, 0.0);
        for (AxisAlignedBB axisalignedbb1 : list) {
            x2 = axisalignedbb1.calculateXOffset(aabb, x2);
        }
        aabb = aabb.offset(x2, 0.0, 0.0);
        for (AxisAlignedBB axisalignedbb2 : list) {
            z2 = axisalignedbb2.calculateZOffset(aabb, z2);
        }
        aabb = aabb.offset(0.0, 0.0, z2);
        return new CollisionOffset(aabb, x2, y2, z2);
    }

    @Override
    public int getSortingIndex() {
        return 50;
    }

    public class CollisionOffset {
        public AxisAlignedBB aabb;
        public double x;
        public double y;
        public double z;

        public CollisionOffset(AxisAlignedBB aabb, double x2, double y2, double z2) {
            this.aabb = aabb;
            this.x = x2;
            this.y = y2;
            this.z = z2;
        }
    }
}

