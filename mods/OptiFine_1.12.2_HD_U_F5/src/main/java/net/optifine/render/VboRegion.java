/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  amm
 *  bus
 *  bvf
 *  cdy
 *  cii
 */
package net.optifine.render;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import net.optifine.render.VboRange;
import net.optifine.util.LinkedList;

public class VboRegion {
    private amm layer = null;
    private int glBufferId = cii.e();
    private int capacity = 4096;
    private int positionTop = 0;
    private int sizeUsed;
    private LinkedList<VboRange> rangeList = new LinkedList();
    private VboRange compactRangeLast = null;
    private IntBuffer bufferIndexVertex = Config.createDirectIntBuffer(this.capacity);
    private IntBuffer bufferCountVertex = Config.createDirectIntBuffer(this.capacity);
    private int drawMode = 7;
    private final int vertexBytes = cdy.a.g();

    public VboRegion(amm layer) {
        this.layer = layer;
        this.bindBuffer();
        long capacityBytes = this.toBytes(this.capacity);
        cii.glBufferData((int)cii.R, (long)capacityBytes, (int)cii.S);
        this.unbindBuffer();
    }

    public void bufferData(ByteBuffer data, VboRange range) {
        int posOld = range.getPosition();
        int sizeOld = range.getSize();
        int sizeNew = this.toVertex(data.limit());
        if (sizeNew <= 0) {
            if (posOld >= 0) {
                range.setPosition(-1);
                range.setSize(0);
                this.rangeList.remove(range.getNode());
                this.sizeUsed -= sizeOld;
            }
            return;
        }
        if (sizeNew > sizeOld) {
            range.setPosition(this.positionTop);
            range.setSize(sizeNew);
            this.positionTop += sizeNew;
            if (posOld >= 0) {
                this.rangeList.remove(range.getNode());
            }
            this.rangeList.addLast(range.getNode());
        }
        range.setSize(sizeNew);
        this.sizeUsed += sizeNew - sizeOld;
        this.checkVboSize(range.getPositionNext());
        long positionBytes = this.toBytes(range.getPosition());
        this.bindBuffer();
        cii.glBufferSubData((int)cii.R, (long)positionBytes, (ByteBuffer)data);
        this.unbindBuffer();
        if (this.positionTop > this.sizeUsed * 11 / 10) {
            this.compactRanges(1);
        }
    }

    private void compactRanges(int countMax) {
        if (this.rangeList.isEmpty()) {
            return;
        }
        VboRange range = this.compactRangeLast;
        if (range == null || !this.rangeList.contains(range.getNode())) {
            range = this.rangeList.getFirst().getItem();
        }
        int posCompact = range.getPosition();
        VboRange rangePrev = range.getPrev();
        posCompact = rangePrev == null ? 0 : rangePrev.getPositionNext();
        for (int count = 0; range != null && count < countMax; ++count) {
            if (range.getPosition() == posCompact) {
                posCompact += range.getSize();
                range = range.getNext();
                continue;
            }
            int sizeFree = range.getPosition() - posCompact;
            if (range.getSize() <= sizeFree) {
                this.copyVboData(range.getPosition(), posCompact, range.getSize());
                range.setPosition(posCompact);
                posCompact += range.getSize();
                range = range.getNext();
                continue;
            }
            this.checkVboSize(this.positionTop + range.getSize());
            this.copyVboData(range.getPosition(), this.positionTop, range.getSize());
            range.setPosition(this.positionTop);
            this.positionTop += range.getSize();
            VboRange rangeNext = range.getNext();
            this.rangeList.remove(range.getNode());
            this.rangeList.addLast(range.getNode());
            range = rangeNext;
        }
        if (range == null) {
            this.positionTop = this.rangeList.getLast().getItem().getPositionNext();
        }
        this.compactRangeLast = range;
    }

    private void checkRanges() {
        int count = 0;
        int size = 0;
        for (VboRange range = this.rangeList.getFirst().getItem(); range != null; range = range.getNext()) {
            ++count;
            size += range.getSize();
            if (range.getPosition() < 0 || range.getSize() <= 0 || range.getPositionNext() > this.positionTop) {
                throw new RuntimeException("Invalid range: " + range);
            }
            VboRange rangePrev = range.getPrev();
            if (rangePrev != null && range.getPosition() < rangePrev.getPositionNext()) {
                throw new RuntimeException("Invalid range: " + range);
            }
            VboRange rangeNext = range.getNext();
            if (rangeNext == null || range.getPositionNext() <= rangeNext.getPosition()) continue;
            throw new RuntimeException("Invalid range: " + range);
        }
        if (count != this.rangeList.getSize()) {
            throw new RuntimeException("Invalid count: " + count + " <> " + this.rangeList.getSize());
        }
        if (size != this.sizeUsed) {
            throw new RuntimeException("Invalid size: " + size + " <> " + this.sizeUsed);
        }
    }

    private void checkVboSize(int sizeMin) {
        if (this.capacity < sizeMin) {
            this.expandVbo(sizeMin);
        }
    }

    private void copyVboData(int posFrom, int posTo, int size) {
        long posFromBytes = this.toBytes(posFrom);
        long posToBytes = this.toBytes(posTo);
        long sizeBytes = this.toBytes(size);
        cii.g((int)cii.GL_COPY_READ_BUFFER, (int)this.glBufferId);
        cii.g((int)cii.GL_COPY_WRITE_BUFFER, (int)this.glBufferId);
        cii.glCopyBufferSubData((int)cii.GL_COPY_READ_BUFFER, (int)cii.GL_COPY_WRITE_BUFFER, (long)posFromBytes, (long)posToBytes, (long)sizeBytes);
        Config.checkGlError("Copy VBO range");
        cii.g((int)cii.GL_COPY_READ_BUFFER, (int)0);
        cii.g((int)cii.GL_COPY_WRITE_BUFFER, (int)0);
    }

    private void expandVbo(int sizeMin) {
        int capacityNew = this.capacity * 6 / 4;
        while (capacityNew < sizeMin) {
            capacityNew = capacityNew * 6 / 4;
        }
        long capacityBytes = this.toBytes(this.capacity);
        long capacityNewBytes = this.toBytes(capacityNew);
        int glBufferIdNew = cii.e();
        cii.g((int)cii.R, (int)glBufferIdNew);
        cii.glBufferData((int)cii.R, (long)capacityNewBytes, (int)cii.S);
        Config.checkGlError("Expand VBO");
        cii.g((int)cii.R, (int)0);
        cii.g((int)cii.GL_COPY_READ_BUFFER, (int)this.glBufferId);
        cii.g((int)cii.GL_COPY_WRITE_BUFFER, (int)glBufferIdNew);
        cii.glCopyBufferSubData((int)cii.GL_COPY_READ_BUFFER, (int)cii.GL_COPY_WRITE_BUFFER, (long)0L, (long)0L, (long)capacityBytes);
        Config.checkGlError("Copy VBO: " + capacityNewBytes);
        cii.g((int)cii.GL_COPY_READ_BUFFER, (int)0);
        cii.g((int)cii.GL_COPY_WRITE_BUFFER, (int)0);
        cii.g((int)this.glBufferId);
        this.bufferIndexVertex = Config.createDirectIntBuffer(capacityNew);
        this.bufferCountVertex = Config.createDirectIntBuffer(capacityNew);
        this.glBufferId = glBufferIdNew;
        this.capacity = capacityNew;
    }

    public void bindBuffer() {
        cii.g((int)cii.R, (int)this.glBufferId);
    }

    public void drawArrays(int drawMode, VboRange range) {
        if (this.drawMode != drawMode) {
            if (this.bufferIndexVertex.position() > 0) {
                throw new IllegalArgumentException("Mixed region draw modes: " + this.drawMode + " != " + drawMode);
            }
            this.drawMode = drawMode;
        }
        this.bufferIndexVertex.put(range.getPosition());
        this.bufferCountVertex.put(range.getSize());
    }

    public void finishDraw(bvf vboRenderList) {
        this.bindBuffer();
        vboRenderList.a();
        this.bufferIndexVertex.flip();
        this.bufferCountVertex.flip();
        bus.glMultiDrawArrays((int)this.drawMode, (IntBuffer)this.bufferIndexVertex, (IntBuffer)this.bufferCountVertex);
        this.bufferIndexVertex.limit(this.bufferIndexVertex.capacity());
        this.bufferCountVertex.limit(this.bufferCountVertex.capacity());
        if (this.positionTop > this.sizeUsed * 11 / 10) {
            this.compactRanges(1);
        }
    }

    public void unbindBuffer() {
        cii.g((int)cii.R, (int)0);
    }

    public void deleteGlBuffers() {
        if (this.glBufferId >= 0) {
            cii.g((int)this.glBufferId);
            this.glBufferId = -1;
        }
    }

    private long toBytes(int vertex) {
        return (long)vertex * (long)this.vertexBytes;
    }

    private int toVertex(long bytes) {
        return (int)(bytes / (long)this.vertexBytes);
    }

    public int getPositionTop() {
        return this.positionTop;
    }
}

