/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class FFTComplex
extends Pointer {
    public FFTComplex() {
        super((Pointer)null);
        this.allocate();
    }

    public FFTComplex(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public FFTComplex(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public FFTComplex position(long position) {
        return (FFTComplex)super.position(position);
    }

    public FFTComplex getPointer(long i2) {
        return (FFTComplex)new FFTComplex(this).offsetAddress(i2);
    }

    @Cast(value={"FFTSample"})
    public native float re();

    public native FFTComplex re(float var1);

    @Cast(value={"FFTSample"})
    public native float im();

    public native FFTComplex im(float var1);

    static {
        Loader.load();
    }
}

