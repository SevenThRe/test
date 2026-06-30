/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avutil.class})
public class AVStereo3D
extends Pointer {
    public AVStereo3D() {
        super((Pointer)null);
        this.allocate();
    }

    public AVStereo3D(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVStereo3D(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVStereo3D position(long position) {
        return (AVStereo3D)super.position(position);
    }

    public AVStereo3D getPointer(long i2) {
        return (AVStereo3D)new AVStereo3D(this).offsetAddress(i2);
    }

    @Cast(value={"AVStereo3DType"})
    public native int type();

    public native AVStereo3D type(int var1);

    public native int flags();

    public native AVStereo3D flags(int var1);

    @Cast(value={"AVStereo3DView"})
    public native int view();

    public native AVStereo3D view(int var1);

    static {
        Loader.load();
    }
}

