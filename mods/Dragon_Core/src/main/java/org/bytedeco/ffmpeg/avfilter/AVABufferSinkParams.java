/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avfilter.class})
public class AVABufferSinkParams
extends Pointer {
    public AVABufferSinkParams() {
        super((Pointer)null);
        this.allocate();
    }

    public AVABufferSinkParams(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVABufferSinkParams(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVABufferSinkParams position(long position) {
        return (AVABufferSinkParams)super.position(position);
    }

    public AVABufferSinkParams getPointer(long i2) {
        return (AVABufferSinkParams)new AVABufferSinkParams(this).offsetAddress(i2);
    }

    @Cast(value={"const AVSampleFormat*"})
    public native IntPointer sample_fmts();

    public native AVABufferSinkParams sample_fmts(IntPointer var1);

    @Cast(value={"const int64_t*"})
    public native LongPointer channel_layouts();

    public native AVABufferSinkParams channel_layouts(LongPointer var1);

    @Const
    public native IntPointer channel_counts();

    public native AVABufferSinkParams channel_counts(IntPointer var1);

    public native int all_channel_counts();

    public native AVABufferSinkParams all_channel_counts(int var1);

    public native IntPointer sample_rates();

    public native AVABufferSinkParams sample_rates(IntPointer var1);

    static {
        Loader.load();
    }
}

