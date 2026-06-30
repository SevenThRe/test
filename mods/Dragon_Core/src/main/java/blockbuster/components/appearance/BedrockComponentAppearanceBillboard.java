/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.google.gson.JsonArray
 *  com.google.gson.JsonElement
 *  com.google.gson.JsonObject
 *  javax.vecmath.Matrix3d
 *  javax.vecmath.Matrix4d
 *  javax.vecmath.Matrix4f
 *  javax.vecmath.Tuple3d
 *  javax.vecmath.Tuple3f
 *  javax.vecmath.Tuple4f
 *  javax.vecmath.Vector3d
 *  javax.vecmath.Vector3f
 *  javax.vecmath.Vector4f
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.util.math.MathHelper
 */
package blockbuster.components.appearance;

import blockbuster.components.BedrockComponentBase;
import blockbuster.components.IComponentParticleRender;
import blockbuster.components.appearance.CameraFacing;
import blockbuster.emitter.BedrockEmitter;
import blockbuster.emitter.BedrockParticle;
import blockbuster.math.molang.MolangException;
import blockbuster.math.molang.MolangParser;
import blockbuster.math.molang.expressions.MolangExpression;
import blockbuster.utils.Interpolations;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javax.vecmath.Matrix3d;
import javax.vecmath.Matrix4d;
import javax.vecmath.Matrix4f;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;
import javax.vecmath.Tuple4f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.math.MathHelper;

public class BedrockComponentAppearanceBillboard
extends BedrockComponentBase
implements IComponentParticleRender {
    public MolangExpression sizeW = MolangParser.ZERO;
    public MolangExpression sizeH = MolangParser.ZERO;
    public CameraFacing facing = CameraFacing.LOOKAT_XYZ;
    public int textureWidth = 128;
    public int textureHeight = 128;
    public MolangExpression uvX = MolangParser.ZERO;
    public MolangExpression uvY = MolangParser.ZERO;
    public MolangExpression uvW = MolangParser.ZERO;
    public MolangExpression uvH = MolangParser.ZERO;
    public boolean flipbook = false;
    public float stepX;
    public float stepY;
    public float fps;
    public MolangExpression maxFrame = MolangParser.ZERO;
    public boolean stretchFPS = false;
    public boolean loop = false;
    protected float w;
    protected float h;
    protected float u1;
    protected float v1;
    protected float u2;
    protected float v2;
    protected Matrix4f transform = new Matrix4f();
    protected Matrix4f rotation = new Matrix4f();
    protected Vector4f[] vertices = new Vector4f[]{new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f), new Vector4f(0.0f, 0.0f, 0.0f, 1.0f)};
    protected Vector3f vector = new Vector3f();

    @Override
    public BedrockComponentBase fromJson(JsonElement elem, MolangParser parser) throws MolangException {
        JsonArray size;
        if (!elem.isJsonObject()) {
            return super.fromJson(elem, parser);
        }
        JsonObject element = elem.getAsJsonObject();
        if (element.has("size") && element.get("size").isJsonArray() && (size = element.getAsJsonArray("size")).size() >= 2) {
            this.sizeW = parser.parseJson(size.get(0));
            this.sizeH = parser.parseJson(size.get(1));
        }
        if (element.has("facing_camera_mode")) {
            this.facing = CameraFacing.fromString(element.get("facing_camera_mode").getAsString());
        }
        if (element.has("uv") && element.get("uv").isJsonObject()) {
            this.parseUv(element.get("uv").getAsJsonObject(), parser);
        }
        return super.fromJson((JsonElement)element, parser);
    }

    protected void parseUv(JsonObject object, MolangParser parser) throws MolangException {
        JsonArray uv2;
        if (object.has("texture_width")) {
            this.textureWidth = object.get("texture_width").getAsInt();
        }
        if (object.has("texture_height")) {
            this.textureHeight = object.get("texture_height").getAsInt();
        }
        if (object.has("uv") && object.get("uv").isJsonArray() && (uv2 = object.getAsJsonArray("uv")).size() >= 2) {
            this.uvX = parser.parseJson(uv2.get(0));
            this.uvY = parser.parseJson(uv2.get(1));
        }
        if (object.has("uv_size") && object.get("uv_size").isJsonArray() && (uv2 = object.getAsJsonArray("uv_size")).size() >= 2) {
            this.uvW = parser.parseJson(uv2.get(0));
            this.uvH = parser.parseJson(uv2.get(1));
        }
        if (object.has("flipbook") && object.get("flipbook").isJsonObject()) {
            this.flipbook = true;
            this.parseFlipbook(object.get("flipbook").getAsJsonObject(), parser);
        }
    }

    protected void parseFlipbook(JsonObject flipbook, MolangParser parser) throws MolangException {
        JsonArray uv2;
        if (flipbook.has("base_UV") && flipbook.get("base_UV").isJsonArray() && (uv2 = flipbook.getAsJsonArray("base_UV")).size() >= 2) {
            this.uvX = parser.parseJson(uv2.get(0));
            this.uvY = parser.parseJson(uv2.get(1));
        }
        if (flipbook.has("size_UV") && flipbook.get("size_UV").isJsonArray() && (uv2 = flipbook.getAsJsonArray("size_UV")).size() >= 2) {
            this.uvW = parser.parseJson(uv2.get(0));
            this.uvH = parser.parseJson(uv2.get(1));
        }
        if (flipbook.has("step_UV") && flipbook.get("step_UV").isJsonArray() && (uv2 = flipbook.getAsJsonArray("step_UV")).size() >= 2) {
            this.stepX = uv2.get(0).getAsFloat();
            this.stepY = uv2.get(1).getAsFloat();
        }
        if (flipbook.has("frames_per_second")) {
            this.fps = flipbook.get("frames_per_second").getAsFloat();
        }
        if (flipbook.has("max_frame")) {
            this.maxFrame = parser.parseJson(flipbook.get("max_frame"));
        }
        if (flipbook.has("stretch_to_lifetime")) {
            this.stretchFPS = flipbook.get("stretch_to_lifetime").getAsBoolean();
        }
        if (flipbook.has("loop")) {
            this.loop = flipbook.get("loop").getAsBoolean();
        }
    }

    @Override
    public JsonElement toJson() {
        JsonArray uvs;
        JsonObject object = new JsonObject();
        JsonArray size = new JsonArray();
        JsonObject uv2 = new JsonObject();
        size.add(this.sizeW.toJson());
        size.add(this.sizeH.toJson());
        uv2.addProperty("texture_width", (Number)this.textureWidth);
        uv2.addProperty("texture_height", (Number)this.textureHeight);
        if (!this.flipbook && !MolangExpression.isZero(this.uvX) || !MolangExpression.isZero(this.uvY)) {
            uvs = new JsonArray();
            uvs.add(this.uvX.toJson());
            uvs.add(this.uvY.toJson());
            uv2.add("uv", (JsonElement)uvs);
        }
        if (!this.flipbook && !MolangExpression.isZero(this.uvW) || !MolangExpression.isZero(this.uvH)) {
            uvs = new JsonArray();
            uvs.add(this.uvW.toJson());
            uvs.add(this.uvH.toJson());
            uv2.add("uv_size", (JsonElement)uvs);
        }
        if (this.flipbook) {
            JsonObject flipbook = new JsonObject();
            if (!MolangExpression.isZero(this.uvX) || !MolangExpression.isZero(this.uvY)) {
                JsonArray base = new JsonArray();
                base.add(this.uvX.toJson());
                base.add(this.uvY.toJson());
                flipbook.add("base_UV", (JsonElement)base);
            }
            if (!MolangExpression.isZero(this.uvW) || !MolangExpression.isZero(this.uvH)) {
                JsonArray uvSize = new JsonArray();
                uvSize.add(this.uvW.toJson());
                uvSize.add(this.uvH.toJson());
                flipbook.add("size_UV", (JsonElement)uvSize);
            }
            if (this.stepX != 0.0f || this.stepY != 0.0f) {
                JsonArray step = new JsonArray();
                step.add((Number)Float.valueOf(this.stepX));
                step.add((Number)Float.valueOf(this.stepY));
                flipbook.add("step_UV", (JsonElement)step);
            }
            if (this.fps != 0.0f) {
                flipbook.addProperty("frames_per_second", (Number)Float.valueOf(this.fps));
            }
            if (!MolangExpression.isZero(this.maxFrame)) {
                flipbook.add("max_frame", this.maxFrame.toJson());
            }
            if (this.stretchFPS) {
                flipbook.addProperty("stretch_to_lifetime", Boolean.valueOf(true));
            }
            if (this.loop) {
                flipbook.addProperty("loop", Boolean.valueOf(true));
            }
            uv2.add("flipbook", (JsonElement)flipbook);
        }
        object.add("size", (JsonElement)size);
        object.addProperty("facing_camera_mode", this.facing.id);
        object.add("uv", (JsonElement)uv2);
        return object;
    }

    @Override
    public void preRender(BedrockEmitter emitter, float partialTicks) {
    }

    @Override
    public void render(BedrockEmitter emitter, BedrockParticle particle, BufferBuilder builder, float partialTicks) {
        this.calculateUVs(particle, partialTicks);
        double px2 = Interpolations.lerp(particle.prevPosition.x, particle.position.x, (double)partialTicks);
        double py2 = Interpolations.lerp(particle.prevPosition.y, particle.position.y, (double)partialTicks);
        double pz2 = Interpolations.lerp(particle.prevPosition.z, particle.position.z, (double)partialTicks);
        float angle = Interpolations.lerp(particle.prevRotation, particle.rotation, partialTicks);
        Vector3d pos = this.calculatePosition(emitter, particle, px2, py2, pz2);
        px2 = pos.x;
        py2 = pos.y;
        pz2 = pos.z;
        int light = emitter.getBrightnessForRender(partialTicks, px2, py2, pz2);
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
        builder.pos((double)this.vertices[0].x, (double)this.vertices[0].y, (double)this.vertices[0].z).tex((double)u1, (double)v1).lightmap(lightX, lightY).color(particle.r, particle.g, particle.b, particle.a).endVertex();
        builder.pos((double)this.vertices[1].x, (double)this.vertices[1].y, (double)this.vertices[1].z).tex((double)u2, (double)v1).lightmap(lightX, lightY).color(particle.r, particle.g, particle.b, particle.a).endVertex();
        builder.pos((double)this.vertices[2].x, (double)this.vertices[2].y, (double)this.vertices[2].z).tex((double)u2, (double)v2).lightmap(lightX, lightY).color(particle.r, particle.g, particle.b, particle.a).endVertex();
        builder.pos((double)this.vertices[3].x, (double)this.vertices[3].y, (double)this.vertices[3].z).tex((double)u1, (double)v2).lightmap(lightX, lightY).color(particle.r, particle.g, particle.b, particle.a).endVertex();
    }

    protected void calculateFacing(BedrockEmitter emitter, BedrockParticle particle, double px2, double py2, double pz2) {
        boolean lookAt;
        float entityYaw = emitter.cYaw;
        float entityPitch = emitter.cPitch;
        double entityX = emitter.cX;
        double entityY = emitter.cY;
        double entityZ = emitter.cZ;
        boolean bl2 = lookAt = this.facing == CameraFacing.LOOKAT_XYZ || this.facing == CameraFacing.LOOKAT_Y;
        if (emitter.perspective == 2) {
            this.w = -this.w;
        } else if (emitter.perspective == 100 && !lookAt) {
            entityYaw = 180.0f - entityYaw;
            this.w = -this.w;
            this.h = -this.h;
        }
        if (lookAt) {
            double dX = entityX - px2;
            double dY = entityY - py2;
            double dZ = entityZ - pz2;
            double horizontalDistance = MathHelper.sqrt((double)(dX * dX + dZ * dZ));
            entityYaw = 180.0f - (float)(MathHelper.atan2((double)dZ, (double)dX) * 57.29577951308232) - 90.0f;
            entityPitch = (float)(-(MathHelper.atan2((double)dY, (double)horizontalDistance) * 57.29577951308232)) + 180.0f;
        }
        this.calculateVertices(emitter, particle);
        if (this.facing == CameraFacing.ROTATE_XYZ || this.facing == CameraFacing.LOOKAT_XYZ) {
            this.rotation.rotY(entityYaw / 180.0f * (float)Math.PI);
            this.transform.mul(this.rotation);
            this.rotation.rotX(entityPitch / 180.0f * (float)Math.PI);
            this.transform.mul(this.rotation);
        } else if (this.facing == CameraFacing.ROTATE_Y || this.facing == CameraFacing.LOOKAT_Y) {
            this.rotation.rotY(entityYaw / 180.0f * (float)Math.PI);
            this.transform.mul(this.rotation);
        } else if (this.facing == CameraFacing.EMITTER_YZ) {
            this.rotation.rotZ((float)Math.toRadians(180.0));
            this.transform.mul(this.rotation);
            this.rotation.rotY((float)Math.toRadians(90.0));
            this.transform.mul(this.rotation);
        } else if (this.facing == CameraFacing.EMITTER_XZ) {
            this.rotation.rotX((float)Math.toRadians(90.0));
            this.transform.mul(this.rotation);
        } else if (this.facing == CameraFacing.EMITTER_XY) {
            this.rotation.rotX((float)Math.toRadians(180.0));
            this.transform.mul(this.rotation);
        }
    }

    protected void calculateVertices(BedrockEmitter emitter, BedrockParticle particle) {
        this.vertices[0].set(-this.w / 2.0f, -this.h / 2.0f, 0.0f, 1.0f);
        this.vertices[1].set(this.w / 2.0f, -this.h / 2.0f, 0.0f, 1.0f);
        this.vertices[2].set(this.w / 2.0f, this.h / 2.0f, 0.0f, 1.0f);
        this.vertices[3].set(-this.w / 2.0f, this.h / 2.0f, 0.0f, 1.0f);
        this.transform.setIdentity();
        if (particle.relativeScaleBillboard) {
            Matrix4d scale = new Matrix4d(emitter.scale[0], 0.0, 0.0, 0.0, 0.0, emitter.scale[1], 0.0, 0.0, 0.0, 0.0, emitter.scale[2], 0.0, 0.0, 0.0, 0.0, 1.0);
            for (Vector4f vertex : this.vertices) {
                scale.transform((Tuple4f)vertex);
            }
        }
    }

    protected Vector3d calculatePosition(BedrockEmitter emitter, BedrockParticle particle, double px2, double py2, double pz2) {
        if (particle.relativePosition && particle.relativeRotation) {
            this.vector.set((float)px2, (float)py2, (float)pz2);
            if (particle.relativeScale) {
                Vector3d pos = new Vector3d(px2, py2, pz2);
                Matrix3d scale = new Matrix3d(emitter.scale[0], 0.0, 0.0, 0.0, emitter.scale[1], 0.0, 0.0, 0.0, emitter.scale[2]);
                scale.transform((Tuple3d)pos);
                this.vector.x = (float)pos.x;
                this.vector.y = (float)pos.y;
                this.vector.z = (float)pos.z;
            }
            emitter.rotation.transform((Tuple3f)this.vector);
            px2 = this.vector.x;
            py2 = this.vector.y;
            pz2 = this.vector.z;
            px2 += emitter.lastGlobal.x;
            py2 += emitter.lastGlobal.y;
            pz2 += emitter.lastGlobal.z;
        } else if (particle.relativeScale) {
            Vector3d pos = new Vector3d(px2, py2, pz2);
            Matrix3d scale = new Matrix3d(emitter.scale[0], 0.0, 0.0, 0.0, emitter.scale[1], 0.0, 0.0, 0.0, emitter.scale[2]);
            pos.sub((Tuple3d)emitter.lastGlobal);
            scale.transform((Tuple3d)pos);
            pos.add((Tuple3d)emitter.lastGlobal);
            px2 = pos.x;
            py2 = pos.y;
            pz2 = pos.z;
        }
        return new Vector3d(px2, py2, pz2);
    }

    @Override
    public void renderOnScreen(BedrockParticle particle, int x2, int y2, float scale, float partialTicks) {
        this.calculateUVs(particle, partialTicks);
        this.h = 0.5f;
        this.w = 0.5f;
        float angle = Interpolations.lerp(particle.prevRotation, particle.rotation, partialTicks);
        this.vertices[0].set(-this.w / 2.0f, -this.h / 2.0f, 0.0f, 1.0f);
        this.vertices[1].set(this.w / 2.0f, -this.h / 2.0f, 0.0f, 1.0f);
        this.vertices[2].set(this.w / 2.0f, this.h / 2.0f, 0.0f, 1.0f);
        this.vertices[3].set(-this.w / 2.0f, this.h / 2.0f, 0.0f, 1.0f);
        this.transform.setIdentity();
        this.transform.setScale(scale * 2.75f);
        this.transform.setTranslation(new Vector3f((float)x2, (float)y2 - scale / 2.0f, 0.0f));
        this.rotation.rotZ(angle / 180.0f * (float)Math.PI);
        this.transform.mul(this.rotation);
        for (Vector4f vertex : this.vertices) {
            this.transform.transform((Tuple4f)vertex);
        }
        float u1 = this.u1 / (float)this.textureWidth;
        float u2 = this.u2 / (float)this.textureWidth;
        float v1 = this.v1 / (float)this.textureHeight;
        float v2 = this.v2 / (float)this.textureHeight;
        BufferBuilder builder = Tessellator.getInstance().getBuffer();
        builder.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
        builder.pos((double)this.vertices[0].x, (double)this.vertices[0].y, (double)this.vertices[0].z).tex((double)u1, (double)v1).color(particle.r, particle.g, particle.b, particle.a).endVertex();
        builder.pos((double)this.vertices[1].x, (double)this.vertices[1].y, (double)this.vertices[1].z).tex((double)u2, (double)v1).color(particle.r, particle.g, particle.b, particle.a).endVertex();
        builder.pos((double)this.vertices[2].x, (double)this.vertices[2].y, (double)this.vertices[2].z).tex((double)u2, (double)v2).color(particle.r, particle.g, particle.b, particle.a).endVertex();
        builder.pos((double)this.vertices[3].x, (double)this.vertices[3].y, (double)this.vertices[3].z).tex((double)u1, (double)v2).color(particle.r, particle.g, particle.b, particle.a).endVertex();
        Tessellator.getInstance().draw();
    }

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
                float f2 = lifetime = particle.lifetime <= 0 ? 0.0f : ((float)particle.age + partialTicks) / (float)particle.lifetime;
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
}

