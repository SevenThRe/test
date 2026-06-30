/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avfilter.class})
public class AVBufferSrcParameters
extends Pointer {
    public AVBufferSrcParameters() {
        super((Pointer)null);
        this.allocate();
    }

    public AVBufferSrcParameters(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVBufferSrcParameters(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVBufferSrcParameters position(long position) {
        return (AVBufferSrcParameters)super.position(position);
    }

    public AVBufferSrcParameters getPointer(long i2) {
        return (AVBufferSrcParameters)new AVBufferSrcParameters(this).offsetAddress(i2);
    }

    public native int format();

    public native AVBufferSrcParameters format(int var1);

    @ByRef
    public native AVRational time_base();

    public native AVBufferSrcParameters time_base(AVRational var1);

    public native int width();

    public native AVBufferSrcParameters width(int var1);

    public native int height();

    public native AVBufferSrcParameters height(int var1);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native AVBufferSrcParameters sample_aspect_ratio(AVRational var1);

    @ByRef
    public native AVRational frame_rate();

    public native AVBufferSrcParameters frame_rate(AVRational var1);

    public native AVBufferRef hw_frames_ctx();

    public native AVBufferSrcParameters hw_frames_ctx(AVBufferRef var1);

    public native int sample_rate();

    public native AVBufferSrcParameters sample_rate(int var1);

    @Cast(value={"uint64_t"})
    public native long channel_layout();

    public native AVBufferSrcParameters channel_layout(long var1);

    static {
        Loader.load();
    }
}

