/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avutil.class})
public class AVComplexFloat
extends Pointer {
    public AVComplexFloat() {
        super((Pointer)null);
        this.allocate();
    }

    public AVComplexFloat(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVComplexFloat(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVComplexFloat position(long position) {
        return (AVComplexFloat)super.position(position);
    }

    public AVComplexFloat getPointer(long i2) {
        return (AVComplexFloat)new AVComplexFloat(this).offsetAddress(i2);
    }

    public native float re();

    public native AVComplexFloat re(float var1);

    public native float im();

    public native AVComplexFloat im(float var1);

    static {
        Loader.load();
    }
}

