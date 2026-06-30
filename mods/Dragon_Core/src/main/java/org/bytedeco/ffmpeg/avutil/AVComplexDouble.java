/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avutil.class})
public class AVComplexDouble
extends Pointer {
    public AVComplexDouble() {
        super((Pointer)null);
        this.allocate();
    }

    public AVComplexDouble(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVComplexDouble(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVComplexDouble position(long position) {
        return (AVComplexDouble)super.position(position);
    }

    public AVComplexDouble getPointer(long i2) {
        return (AVComplexDouble)new AVComplexDouble(this).offsetAddress(i2);
    }

    public native double re();

    public native AVComplexDouble re(double var1);

    public native double im();

    public native AVComplexDouble im(double var1);

    static {
        Loader.load();
    }
}

