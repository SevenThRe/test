/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avfilter.AVFilterFormats;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avfilter.class})
public class AVFilterFormatsConfig
extends Pointer {
    public AVFilterFormatsConfig() {
        super((Pointer)null);
        this.allocate();
    }

    public AVFilterFormatsConfig(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVFilterFormatsConfig(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVFilterFormatsConfig position(long position) {
        return (AVFilterFormatsConfig)super.position(position);
    }

    public AVFilterFormatsConfig getPointer(long i2) {
        return (AVFilterFormatsConfig)new AVFilterFormatsConfig(this).offsetAddress(i2);
    }

    public native AVFilterFormats formats();

    public native AVFilterFormatsConfig formats(AVFilterFormats var1);

    public native AVFilterFormats samplerates();

    public native AVFilterFormatsConfig samplerates(AVFilterFormats var1);

    @Cast(value={"AVFilterChannelLayouts*"})
    public native Pointer channel_layouts();

    public native AVFilterFormatsConfig channel_layouts(Pointer var1);

    static {
        Loader.load();
    }
}

