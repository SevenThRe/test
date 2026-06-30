/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avformat.class})
public class AVIODirContext
extends Pointer {
    public AVIODirContext() {
        super((Pointer)null);
        this.allocate();
    }

    public AVIODirContext(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVIODirContext(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVIODirContext position(long position) {
        return (AVIODirContext)super.position(position);
    }

    public AVIODirContext getPointer(long i2) {
        return (AVIODirContext)new AVIODirContext(this).offsetAddress(i2);
    }

    @Cast(value={"URLContext*"})
    public native Pointer url_context();

    public native AVIODirContext url_context(Pointer var1);

    static {
        Loader.load();
    }
}

