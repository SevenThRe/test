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
        this.bufferBuilder = new BufferBuilder(vertexFormat.getSize() * maxVertices);
    }

    public void beginDrawing(int drawMode) {
        this.bufferBuilder.begin(drawMode, this.vertexFormat);
    }

    public void finishDrawing() {
        this.bufferBuilder.finishDrawing();
        this.buffer = new VertexBuffer(this.vertexFormat);
        this.buffer.bufferData(this.bufferBuilder.getByteBuffer());
    }

    public Mesh pos(double x2, double y2, double z2) {
        this.bufferBuilder.pos(x2, y2, z2);
        return this;
    }

    public Mesh normal(float x2, float y2, float z2) {
        this.bufferBuilder.normal(x2, y2, z2);
        return this;
    }

    public Mesh tex(double u2, double v2) {
        this.bufferBuilder.tex(u2, v2);
        return this;
    }

    public Mesh color(IColorRead color) {
        this.bufferBuilder.color(color.getR(), color.getG(), color.getB(), color.getA());
        return this;
    }

    public void endVertex() {
        this.bufferBuilder.endVertex();
    }

    public void display() {
        if (this.bufferBuilder.getVertexCount() > 0) {
            int i2 = this.vertexFormat.getSize();
            ByteBuffer bytebuffer = this.bufferBuilder.getByteBuffer();
            List list = this.vertexFormat.getElements();
            for (int j2 = 0; j2 < list.size(); ++j2) {
                VertexFormatElement vertexformatelement = (VertexFormatElement)list.get(j2);
                VertexFormatElement.EnumUsage usage = vertexformatelement.getUsage();
                bytebuffer.position(this.vertexFormat.getOffset(j2));
                usage.preDraw(this.vertexFormat, j2, i2, bytebuffer);
            }
            GlStateManager.glDrawArrays((int)this.bufferBuilder.getDrawMode(), (int)0, (int)this.bufferBuilder.getVertexCount());
            int j1 = list.size();
            for (int i1 = 0; i1 < j1; ++i1) {
                VertexFormatElement vertexformatelement1 = (VertexFormatElement)list.get(i1);
                vertexformatelement1.getUsage().postDraw(this.vertexFormat, i1, i2, bytebuffer);
            }
        }
    }

    public BufferBuilder getBufferBuilder() {
        return this.bufferBuilder;
    }
}

