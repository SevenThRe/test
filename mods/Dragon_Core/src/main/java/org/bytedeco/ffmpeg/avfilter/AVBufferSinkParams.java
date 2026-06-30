/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avfilter.class})
public class AVBufferSinkParams
extends Pointer {
    public AVBufferSinkParams() {
        super((Pointer)null);
        this.allocate();
    }

    public AVBufferSinkParams(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVBufferSinkParams(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVBufferSinkParams position(long position) {
        return (AVBufferSinkParams)super.position(position);
    }

    public AVBufferSinkParams getPointer(long i2) {
        return (AVBufferSinkParams)new AVBufferSinkParams(this).offsetAddress(i2);
    }

    @Cast(value={"const AVPixelFormat*"})
    public native IntPointer pixel_fmts();

    public native AVBufferSinkParams pixel_fmts(IntPointer var1);

    static {
        Loader.load();
    }
}

