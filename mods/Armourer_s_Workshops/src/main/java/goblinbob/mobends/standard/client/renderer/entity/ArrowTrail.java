/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.Minecraft
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.Tessellator
 *  net.minecraft.client.renderer.vertex.DefaultVertexFormats
 *  net.minecraft.entity.Entity
 *  net.minecraft.entity.projectile.EntityArrow
 *  net.minecraft.util.math.Vec3d
 */
package goblinbob.mobends.standard.client.renderer.entity;

import goblinbob.mobends.core.client.event.DataUpdateHandler;
import goblinbob.mobends.core.math.vector.Vec3f;
import goblinbob.mobends.core.math.vector.VectorUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.math.Vec3d;

public class ArrowTrail {
    public static final int MAX_LENGTH = 10;
    public static final float SPAWN_INTERVAL = 1.0f;
    private final Minecraft mc = Minecraft.getMinecraft();
    private EntityArrow trackedArrow;
    private TrailNode[] nodes;
    private float spawnCooldown = 0.0f;

    public ArrowTrail(EntityArrow arrow) {
        this.trackedArrow = arrow;
        this.spawnCooldown = 1.0f;
        this.nodes = new TrailNode[10];
        this.resetNodes();
    }

    public void onRenderTick() {
        this.spawnCooldown += DataUpdateHandler.ticksPerFrame;
    }

    public void render(double x2, double y2, double z2, float partialTicks) {
        if (this.spawnCooldown > 40.0f) {
            this.spawnCooldown = 0.0f;
            this.resetNodes();
        }
        while (this.spawnCooldown >= 1.0f) {
            for (int i2 = 9; i2 > 0; --i2) {
                this.nodes[i2].moveTo(this.nodes[i2 - 1]);
            }
            this.nodes[0].moveTo(this.trackedArrow);
            this.spawnCooldown -= 1.0f;
        }
        this.renderNodes(partialTicks);
    }

    public void resetNodes() {
        for (int i2 = 0; i2 < 10; ++i2) {
            this.nodes[i2] = new TrailNode(this.trackedArrow);
        }
    }

    public void renderNodes(float partialTicks) {
        Entity viewEntity = Minecraft.getMinecraft().getRenderViewEntity();
        if (viewEntity == null) {
            return;
        }
        Vec3d viewPos = new Vec3d(viewEntity.prevPosX + (viewEntity.posX - viewEntity.prevPosX) * (double)partialTicks, viewEntity.prevPosY + (viewEntity.posY - viewEntity.prevPosY) * (double)partialTicks, viewEntity.prevPosZ + (viewEntity.posZ - viewEntity.prevPosZ) * (double)partialTicks);
        float r2 = 1.0f;
        float g2 = 1.0f;
        float b2 = 1.0f;
        float a2 = 0.5f;
        GlStateManager.color((float)r2, (float)g2, (float)b2, (float)a2);
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableBlend();
        GlStateManager.blendFunc((int)770, (int)771);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        vertexbuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        for (int i2 = 1; i2 < 10; ++i2) {
            TrailNode node0 = this.nodes[i2 - 1];
            TrailNode node1 = this.nodes[i2];
            Vec3d pos0 = new Vec3d(node0.x - viewPos.x, node0.y - viewPos.y, node0.z - viewPos.z);
            Vec3d pos1 = new Vec3d(node1.x - viewPos.x, node1.y - viewPos.y, node1.z - viewPos.z);
            float scale0 = (float)(10 - i2) / 10.0f * 0.1f;
            float scale1 = (10.0f - (float)i2 - 1.0f) / 10.0f * 0.1f;
            if (i2 == 1) {
                scale1 = 0.0f;
            }
            Vec3f up0 = node0.up;
            Vec3f right0 = node0.right;
            Vec3f up1 = node1.up;
            Vec3f right1 = node1.right;
            vertexbuffer.pos(pos0.x + (double)(-right0.x * scale0), pos0.y + (double)(-right0.y * scale0), pos0.z + (double)(-right0.z * scale0)).tex(0.0, 0.15625).endVertex();
            vertexbuffer.pos(pos0.x + (double)(right0.x * scale0), pos0.y + (double)(right0.y * scale0), pos0.z + (double)(right0.z * scale0)).tex(0.0, 0.15625).endVertex();
            vertexbuffer.pos(pos1.x + (double)(right1.x * scale1), pos1.y + (double)(right1.y * scale1), pos1.z + (double)(right1.z * scale1)).tex(0.0, 0.15625).endVertex();
            vertexbuffer.pos(pos1.x + (double)(-right1.x * scale1), pos1.y + (double)(-right1.y * scale1), pos1.z + (double)(-right1.z * scale1)).tex(0.0, 0.15625).endVertex();
            vertexbuffer.pos(pos0.x + (double)(-up0.x * scale0), pos0.y + (double)(-up0.y * scale0), pos0.z + (double)(-up0.z * scale0)).tex(0.0, 0.15625).endVertex();
            vertexbuffer.pos(pos0.x + (double)(up0.x * scale0), pos0.y + (double)(up0.y * scale0), pos0.z + (double)(up0.z * scale0)).tex(0.0, 0.15625).endVertex();
            vertexbuffer.pos(pos1.x + (double)(up1.x * scale1), pos1.y + (double)(up1.y * scale1), pos1.z + (double)(up1.z * scale1)).tex(0.0, 0.15625).endVertex();
            vertexbuffer.pos(pos1.x + (double)(-up1.x * scale1), pos1.y + (double)(-up1.y * scale1), pos1.z + (double)(-up1.z * scale1)).tex(0.0, 0.15625).endVertex();
        }
        tessellator.draw();
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public void renderAxis(double x2, double y2, double z2) {
        Vec3d forward = this.trackedArrow.getForward();
        forward = new Vec3d(-forward.x, -forward.y, forward.z);
        Vec3d up = Vec3d.fromPitchYaw((float)(this.trackedArrow.rotationPitch + 90.0f), (float)this.trackedArrow.rotationYaw);
        up = new Vec3d(-up.x, -up.y, up.z);
        Vec3d right = forward.crossProduct(up);
        GlStateManager.pushMatrix();
        GlStateManager.disableTexture2D();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.depthFunc((int)519);
        GlStateManager.translate((double)x2, (double)y2, (double)z2);
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexbuffer = tessellator.getBuffer();
        GlStateManager.color((float)1.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(0.0, 0.0, 0.0).tex(0.0, 0.15625).endVertex();
        vertexbuffer.pos(right.x, right.y, right.z).tex(0.15625, 0.15625).endVertex();
        tessellator.draw();
        GlStateManager.color((float)0.0f, (float)1.0f, (float)0.0f, (float)1.0f);
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(0.0, 0.0, 0.0).tex(0.0, 0.15625).endVertex();
        vertexbuffer.pos(up.x, up.y, up.z).tex(0.15625, 0.15625).endVertex();
        tessellator.draw();
        GlStateManager.color((float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f);
        vertexbuffer.begin(1, DefaultVertexFormats.POSITION_TEX);
        vertexbuffer.pos(0.0, 0.0, 0.0).tex(0.0, 0.15625).endVertex();
        vertexbuffer.pos(forward.x, forward.y, forward.z).tex(0.15625, 0.15625).endVertex();
        tessellator.draw();
        GlStateManager.depthFunc((int)515);
        GlStateManager.enableCull();
        GlStateManager.enableTexture2D();
        GlStateManager.enableLighting();
        GlStateManager.popMatrix();
    }

    public boolean shouldBeRemoved() {
        return this.mc.world == null || this.trackedArrow.isDead;
    }

    static class TrailNode {
        public double x;
        public double y;
        public double z;
        public final Vec3f up = new Vec3f();
        public final Vec3f right = new Vec3f();

        TrailNode(EntityArrow arrow) {
            this.moveTo(arrow);
        }

        public void moveTo(TrailNode trailNode) {
            this.x = trailNode.x;
            this.y = trailNode.y;
            this.z = trailNode.z;
            this.up.set(trailNode.up);
            this.right.set(trailNode.right);
        }

        public void moveTo(EntityArrow arrow) {
            this.x = arrow.posX;
            this.y = arrow.posY;
            this.z = arrow.posZ;
            Vec3d forward = arrow.getForward();
            Vec3d up = Vec3d.fromPitchYaw((float)(arrow.rotationPitch + 90.0f), (float)arrow.rotationYaw);
            this.up.set((float)(-up.x), (float)(-up.y), (float)up.z);
            VectorUtils.cross((float)(-forward.x), (float)(-forward.y), (float)forward.z, this.up.x, this.up.y, this.up.z, this.right);
        }
    }
}

