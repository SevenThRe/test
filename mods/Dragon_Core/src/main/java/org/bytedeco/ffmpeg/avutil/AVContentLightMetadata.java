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
public class AVContentLightMetadata
extends Pointer {
    public AVContentLightMetadata() {
        super((Pointer)null);
        this.allocate();
    }

    public AVContentLightMetadata(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVContentLightMetadata(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVContentLightMetadata position(long position) {
        return (AVContentLightMetadata)super.position(position);
    }

    public AVContentLightMetadata getPointer(long i2) {
        return (AVContentLightMetadata)new AVContentLightMetadata(this).offsetAddress(i2);
    }

    @Cast(value={"unsigned"})
    public native int MaxCLL();

    public native AVContentLightMetadata MaxCLL(int var1);

    @Cast(value={"unsigned"})
    public native int MaxFALL();

    public native AVContentLightMetadata MaxFALL(int var1);

    static {
        Loader.load();
    }
}

