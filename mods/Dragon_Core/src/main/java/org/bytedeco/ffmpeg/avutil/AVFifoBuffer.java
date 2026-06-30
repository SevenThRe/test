/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avutil.class})
public class AVFifoBuffer
extends Pointer {
    public AVFifoBuffer() {
        super((Pointer)null);
        this.allocate();
    }

    public AVFifoBuffer(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVFifoBuffer(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVFifoBuffer position(long position) {
        return (AVFifoBuffer)super.position(position);
    }

    public AVFifoBuffer getPointer(long i2) {
        return (AVFifoBuffer)new AVFifoBuffer(this).offsetAddress(i2);
    }

    @Cast(value={"uint8_t*"})
    public native BytePointer buffer();

    public native AVFifoBuffer buffer(BytePointer var1);

    @Cast(value={"uint8_t*"})
    public native BytePointer rptr();

    public native AVFifoBuffer rptr(BytePointer var1);

    @Cast(value={"uint8_t*"})
    public native BytePointer wptr();

    public native AVFifoBuffer wptr(BytePointer var1);

    @Cast(value={"uint8_t*"})
    public native BytePointer end();

    public native AVFifoBuffer end(BytePointer var1);

    @Cast(value={"uint32_t"})
    public native int rndx();

    public native AVFifoBuffer rndx(int var1);

    @Cast(value={"uint32_t"})
    public native int wndx();

    public native AVFifoBuffer wndx(int var1);

    static {
        Loader.load();
    }
}

