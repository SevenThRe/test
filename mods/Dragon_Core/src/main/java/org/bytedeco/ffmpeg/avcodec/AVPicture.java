/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class AVPicture
extends Pointer {
    public AVPicture() {
        super((Pointer)null);
        this.allocate();
    }

    public AVPicture(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVPicture(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVPicture position(long position) {
        return (AVPicture)super.position(position);
    }

    public AVPicture getPointer(long i2) {
        return (AVPicture)new AVPicture(this).offsetAddress(i2);
    }

    @Cast(value={"uint8_t*"})
    @Deprecated
    public native BytePointer data(int var1);

    public native AVPicture data(int var1, BytePointer var2);

    @MemberGetter
    @Cast(value={"uint8_t**"})
    @Deprecated
    public native PointerPointer data();

    @Deprecated
    public native int linesize(int var1);

    public native AVPicture linesize(int var1, int var2);

    @MemberGetter
    @Deprecated
    public native IntPointer linesize();

    static {
        Loader.load();
    }
}

