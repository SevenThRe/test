/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avcodec.AVPacketSideData;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class AVPacket
extends Pointer {
    public AVPacket() {
        super((Pointer)null);
        this.allocate();
    }

    public AVPacket(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVPacket(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVPacket position(long position) {
        return (AVPacket)super.position(position);
    }

    public AVPacket getPointer(long i2) {
        return (AVPacket)new AVPacket(this).offsetAddress(i2);
    }

    public native AVBufferRef buf();

    public native AVPacket buf(AVBufferRef var1);

    @Cast(value={"int64_t"})
    public native long pts();

    public native AVPacket pts(long var1);

    @Cast(value={"int64_t"})
    public native long dts();

    public native AVPacket dts(long var1);

    @Cast(value={"uint8_t*"})
    public native BytePointer data();

    public native AVPacket data(BytePointer var1);

    public native int size();

    public native AVPacket size(int var1);

    public native int stream_index();

    public native AVPacket stream_index(int var1);

    public native int flags();

    public native AVPacket flags(int var1);

    public native AVPacketSideData side_data();

    public native AVPacket side_data(AVPacketSideData var1);

    public native int side_data_elems();

    public native AVPacket side_data_elems(int var1);

    @Cast(value={"int64_t"})
    public native long duration();

    public native AVPacket duration(long var1);

    @Cast(value={"int64_t"})
    public native long pos();

    public native AVPacket pos(long var1);

    @Cast(value={"int64_t"})
    @Deprecated
    public native long convergence_duration();

    public native AVPacket convergence_duration(long var1);

    static {
        Loader.load();
    }
}

