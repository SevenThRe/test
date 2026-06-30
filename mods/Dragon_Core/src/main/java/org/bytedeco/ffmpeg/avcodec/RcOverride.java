/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class RcOverride
extends Pointer {
    public RcOverride() {
        super((Pointer)null);
        this.allocate();
    }

    public RcOverride(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public RcOverride(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public RcOverride position(long position) {
        return (RcOverride)super.position(position);
    }

    public RcOverride getPointer(long i2) {
        return (RcOverride)new RcOverride(this).offsetAddress(i2);
    }

    public native int start_frame();

    public native RcOverride start_frame(int var1);

    public native int end_frame();

    public native RcOverride end_frame(int var1);

    public native int qscale();

    public native RcOverride qscale(int var1);

    public native float quality_factor();

    public native RcOverride quality_factor(float var1);

    static {
        Loader.load();
    }
}

