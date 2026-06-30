/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

public class BufferRing<B extends ReleasableBuffer> {
    private Object[] buffers;
    private int position;

    public BufferRing(BufferFactory<B> factory, int size) {
        this.buffers = new Object[size];
        for (int i2 = 0; i2 < size; ++i2) {
            this.buffers[i2] = factory.create();
        }
        this.position = 0;
    }

    public int capacity() {
        return this.buffers.length;
    }

    public int position() {
        return this.position;
    }

    public BufferRing position(int position) {
        this.position = (position % this.buffers.length + this.buffers.length) % this.buffers.length;
        return this;
    }

    public B get() {
        return (B)((ReleasableBuffer)this.buffers[this.position]);
    }

    public B get(int offset) {
        return (B)((ReleasableBuffer)this.buffers[((this.position + offset) % this.buffers.length + this.buffers.length) % this.buffers.length]);
    }

    public void release() {
        for (int i2 = 0; i2 < this.buffers.length; ++i2) {
            ((ReleasableBuffer)this.buffers[i2]).release();
        }
        this.buffers = null;
    }

    public static interface ReleasableBuffer {
        public void release();
    }

    public static interface BufferFactory<B extends ReleasableBuffer> {
        public B create();
    }
}

