/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  net.minecraft.client.renderer.BufferBuilder
 *  net.minecraft.client.renderer.GlStateManager
 *  net.minecraft.client.renderer.vertex.VertexBuffer
 *  net.minecraft.client.renderer.vertex.VertexFormat
 *  net.minecraft.client.renderer.vertex.VertexFormatElement
 *  net.minecraft.client.renderer.vertex.VertexFormatElement$EnumUsage
 */
package goblinbob.mobends.core.client;

import goblinbob.mobends.core.util.IColorRead;
import java.nio.ByteBuffer;
import java.util.List;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;

public class Mesh {
    private VertexFormat vertexFormat;
    private BufferBuilder bufferBuilder;
    private VertexBuffer buffer;

    public Mesh(VertexFormat vertexFormat, int maxVertices) {
        this.vertexFormat = vertexFormat;
        this.bufferBuilder = new BufferBuilder(vertexFormat.func_177338_f() * maxVertices);
    }

    public void beginDrawing(int drawMode) {
        this.bufferBuilder.func_181668_a(drawMode, this.vertexFormat);
    }

    public void finishDrawing() {
        this.bufferBuilder.func_178977_d();
        this.buffer = new VertexBuffer(this.vertexFormat);
        this.buffer.func_181722_a(this.bufferBuilder.func_178966_f());
    }

    public Mesh pos(double x2, double y2, double z2) {
        this.bufferBuilder.func_181662_b(x2, y2, z2);
        return this;
    }

    public Mesh normal(float x2, float y2, float z2) {
        this.bufferBuilder.func_181663_c(x2, y2, z2);
        return this;
    }

    public Mesh tex(double u2, double v2) {
        this.bufferBuilder.func_187315_a(u2, v2);
        return this;
    }

    public Mesh color(IColorRead color) {
        this.bufferBuilder.func_181666_a(color.getR(), color.getG(), color.getB(), color.getA());
        return this;
    }

    public void endVertex() {
        this.bufferBuilder.func_181675_d();
    }

    public void display() {
        if (this.bufferBuilder.func_178989_h() > 0) {
            int i2 = this.vertexFormat.func_177338_f();
            ByteBuffer bytebuffer = this.bufferBuilder.func_178966_f();
            List list = this.vertexFormat.func_177343_g();
            for (int j2 = 0; j2 < list.size(); ++j2) {
                VertexFormatElement vertexformatelement = (VertexFormatElement)list.get(j2);
                VertexFormatElement.EnumUsage usage = vertexformatelement.func_177375_c();
                bytebuffer.position(this.vertexFormat.func_181720_d(j2));
                usage.preDraw(this.vertexFormat, j2, i2, bytebuffer);
            }
            GlStateManager.func_187439_f((int)this.bufferBuilder.func_178979_i(), (int)0, (int)this.bufferBuilder.func_178989_h());
            int j1 = list.size();
            for (int i1 = 0; i1 < j1; ++i1) {
                VertexFormatElement vertexformatelement1 = (VertexFormatElement)list.get(i1);
                vertexformatelement1.func_177375_c().postDraw(this.vertexFormat, i1, i2, bytebuffer);
            }
        }
    }

    public BufferBuilder getBufferBuilder() {
        return this.bufferBuilder;
    }
}

