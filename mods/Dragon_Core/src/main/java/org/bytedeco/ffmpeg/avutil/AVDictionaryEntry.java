/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avutil.class})
public class AVDictionaryEntry
extends Pointer {
    public AVDictionaryEntry() {
        super((Pointer)null);
        this.allocate();
    }

    public AVDictionaryEntry(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVDictionaryEntry(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVDictionaryEntry position(long position) {
        return (AVDictionaryEntry)super.position(position);
    }

    public AVDictionaryEntry getPointer(long i2) {
        return (AVDictionaryEntry)new AVDictionaryEntry(this).offsetAddress(i2);
    }

    @Cast(value={"char*"})
    public native BytePointer key();

    public native AVDictionaryEntry key(BytePointer var1);

    @Cast(value={"char*"})
    public native BytePointer value();

    public native AVDictionaryEntry value(BytePointer var1);

    static {
        Loader.load();
    }
}

