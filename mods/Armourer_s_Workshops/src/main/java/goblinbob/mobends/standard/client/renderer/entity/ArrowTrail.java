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
    private final Minecraft mc = Minecraft.func_71410_x();
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
        Entity viewEntity = Minecraft.func_71410_x().func_175606_aa();
        if (viewEntity == null) {
            return;
        }
        Vec3d viewPos = new Vec3d(viewEntity.field_70169_q + (viewEntity.field_70165_t - viewEntity.field_70169_q) * (double)partialTicks, viewEntity.field_70167_r + (viewEntity.field_70163_u - viewEntity.field_70167_r) * (double)partialTicks, viewEntity.field_70166_s + (viewEntity.field_70161_v - viewEntity.field_70166_s) * (double)partialTicks);
        float r2 = 1.0f;
        float g2 = 1.0f;
        float b2 = 1.0f;
        float a2 = 0.5f;
        GlStateManager.func_179131_c((float)r2, (float)g2, (float)b2, (float)a2);
        GlStateManager.func_179094_E();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        GlStateManager.func_179147_l();
        GlStateManager.func_179112_b((int)770, (int)771);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        vertexbuffer.func_181668_a(7, DefaultVertexFormats.field_181707_g);
        for (int i2 = 1; i2 < 10; ++i2) {
            TrailNode node0 = this.nodes[i2 - 1];
            TrailNode node1 = this.nodes[i2];
            Vec3d pos0 = new Vec3d(node0.x - viewPos.field_72450_a, node0.y - viewPos.field_72448_b, node0.z - viewPos.field_72449_c);
            Vec3d pos1 = new Vec3d(node1.x - viewPos.field_72450_a, node1.y - viewPos.field_72448_b, node1.z - viewPos.field_72449_c);
            float scale0 = (float)(10 - i2) / 10.0f * 0.1f;
            float scale1 = (10.0f - (float)i2 - 1.0f) / 10.0f * 0.1f;
            if (i2 == 1) {
                scale1 = 0.0f;
            }
            Vec3f up0 = node0.up;
            Vec3f right0 = node0.right;
            Vec3f up1 = node1.up;
            Vec3f right1 = node1.right;
            vertexbuffer.func_181662_b(pos0.field_72450_a + (double)(-right0.x * scale0), pos0.field_72448_b + (double)(-right0.y * scale0), pos0.field_72449_c + (double)(-right0.z * scale0)).func_187315_a(0.0, 0.15625).func_181675_d();
            vertexbuffer.func_181662_b(pos0.field_72450_a + (double)(right0.x * scale0), pos0.field_72448_b + (double)(right0.y * scale0), pos0.field_72449_c + (double)(right0.z * scale0)).func_187315_a(0.0, 0.15625).func_181675_d();
            vertexbuffer.func_181662_b(pos1.field_72450_a + (double)(right1.x * scale1), pos1.field_72448_b + (double)(right1.y * scale1), pos1.field_72449_c + (double)(right1.z * scale1)).func_187315_a(0.0, 0.15625).func_181675_d();
            vertexbuffer.func_181662_b(pos1.field_72450_a + (double)(-right1.x * scale1), pos1.field_72448_b + (double)(-right1.y * scale1), pos1.field_72449_c + (double)(-right1.z * scale1)).func_187315_a(0.0, 0.15625).func_181675_d();
            vertexbuffer.func_181662_b(pos0.field_72450_a + (double)(-up0.x * scale0), pos0.field_72448_b + (double)(-up0.y * scale0), pos0.field_72449_c + (double)(-up0.z * scale0)).func_187315_a(0.0, 0.15625).func_181675_d();
            vertexbuffer.func_181662_b(pos0.field_72450_a + (double)(up0.x * scale0), pos0.field_72448_b + (double)(up0.y * scale0), pos0.field_72449_c + (double)(up0.z * scale0)).func_187315_a(0.0, 0.15625).func_181675_d();
            vertexbuffer.func_181662_b(pos1.field_72450_a + (double)(up1.x * scale1), pos1.field_72448_b + (double)(up1.y * scale1), pos1.field_72449_c + (double)(up1.z * scale1)).func_187315_a(0.0, 0.15625).func_181675_d();
            vertexbuffer.func_181662_b(pos1.field_72450_a + (double)(-up1.x * scale1), pos1.field_72448_b + (double)(-up1.y * scale1), pos1.field_72449_c + (double)(-up1.z * scale1)).func_187315_a(0.0, 0.15625).func_181675_d();
        }
        tessellator.func_78381_a();
        GlStateManager.func_179089_o();
        GlStateManager.func_179098_w();
        GlStateManager.func_179145_e();
        GlStateManager.func_179121_F();
    }

    public void renderAxis(double x2, double y2, double z2) {
        Vec3d forward = this.trackedArrow.func_189651_aD();
        forward = new Vec3d(-forward.field_72450_a, -forward.field_72448_b, forward.field_72449_c);
        Vec3d up = Vec3d.func_189986_a((float)(this.trackedArrow.field_70125_A + 90.0f), (float)this.trackedArrow.field_70177_z);
        up = new Vec3d(-up.field_72450_a, -up.field_72448_b, up.field_72449_c);
        Vec3d right = forward.func_72431_c(up);
        GlStateManager.func_179094_E();
        GlStateManager.func_179090_x();
        GlStateManager.func_179140_f();
        GlStateManager.func_179129_p();
        GlStateManager.func_179143_c((int)519);
        GlStateManager.func_179137_b((double)x2, (double)y2, (double)z2);
        Tessellator tessellator = Tessellator.func_178181_a();
        BufferBuilder vertexbuffer = tessellator.func_178180_c();
        GlStateManager.func_179131_c((float)1.0f, (float)0.0f, (float)0.0f, (float)1.0f);
        vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b(0.0, 0.0, 0.0).func_187315_a(0.0, 0.15625).func_181675_d();
        vertexbuffer.func_181662_b(right.field_72450_a, right.field_72448_b, right.field_72449_c).func_187315_a(0.15625, 0.15625).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179131_c((float)0.0f, (float)1.0f, (float)0.0f, (float)1.0f);
        vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b(0.0, 0.0, 0.0).func_187315_a(0.0, 0.15625).func_181675_d();
        vertexbuffer.func_181662_b(up.field_72450_a, up.field_72448_b, up.field_72449_c).func_187315_a(0.15625, 0.15625).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179131_c((float)0.0f, (float)0.0f, (float)1.0f, (float)1.0f);
        vertexbuffer.func_181668_a(1, DefaultVertexFormats.field_181707_g);
        vertexbuffer.func_181662_b(0.0, 0.0, 0.0).func_187315_a(0.0, 0.15625).func_181675_d();
        vertexbuffer.func_181662_b(forward.field_72450_a, forward.field_72448_b, forward.field_72449_c).func_187315_a(0.15625, 0.15625).func_181675_d();
        tessellator.func_78381_a();
        GlStateManager.func_179143_c((int)515);
        GlStateManager.func_179089_o();
        GlStateManager.func_179098_w();
        GlStateManager.func_179145_e();
        GlStateManager.func_179121_F();
    }

    public boolean shouldBeRemoved() {
        return this.mc.field_71441_e == null || this.trackedArrow.field_70128_L;
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
            this.x = arrow.field_70165_t;
            this.y = arrow.field_70163_u;
            this.z = arrow.field_70161_v;
            Vec3d forward = arrow.func_189651_aD();
            Vec3d up = Vec3d.func_189986_a((float)(arrow.field_70125_A + 90.0f), (float)arrow.field_70177_z);
            this.up.set((float)(-up.field_72450_a), (float)(-up.field_72448_b), (float)up.field_72449_c);
            VectorUtils.cross((float)(-forward.field_72450_a), (float)(-forward.field_72448_b), (float)forward.field_72449_c, this.up.x, this.up.y, this.up.z, this.right);
        }
    }
}

