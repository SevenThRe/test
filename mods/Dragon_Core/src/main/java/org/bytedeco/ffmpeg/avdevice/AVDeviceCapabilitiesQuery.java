/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avdevice;

import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avdevice;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avdevice.class})
public class AVDeviceCapabilitiesQuery
extends Pointer {
    public AVDeviceCapabilitiesQuery() {
        super((Pointer)null);
        this.allocate();
    }

    public AVDeviceCapabilitiesQuery(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVDeviceCapabilitiesQuery(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVDeviceCapabilitiesQuery position(long position) {
        return (AVDeviceCapabilitiesQuery)super.position(position);
    }

    public AVDeviceCapabilitiesQuery getPointer(long i2) {
        return (AVDeviceCapabilitiesQuery)new AVDeviceCapabilitiesQuery(this).offsetAddress(i2);
    }

    @Const
    public native AVClass av_class();

    public native AVDeviceCapabilitiesQuery av_class(AVClass var1);

    public native AVFormatContext device_context();

    public native AVDeviceCapabilitiesQuery device_context(AVFormatContext var1);

    @Cast(value={"AVCodecID"})
    public native int codec();

    public native AVDeviceCapabilitiesQuery codec(int var1);

    @Cast(value={"AVSampleFormat"})
    public native int sample_format();

    public native AVDeviceCapabilitiesQuery sample_format(int var1);

    @Cast(value={"AVPixelFormat"})
    public native int pixel_format();

    public native AVDeviceCapabilitiesQuery pixel_format(int var1);

    public native int sample_rate();

    public native AVDeviceCapabilitiesQuery sample_rate(int var1);

    public native int channels();

    public native AVDeviceCapabilitiesQuery channels(int var1);

    @Cast(value={"int64_t"})
    public native long channel_layout();

    public native AVDeviceCapabilitiesQuery channel_layout(long var1);

    public native int window_width();

    public native AVDeviceCapabilitiesQuery window_width(int var1);

    public native int window_height();

    public native AVDeviceCapabilitiesQuery window_height(int var1);

    public native int frame_width();

    public native AVDeviceCapabilitiesQuery frame_width(int var1);

    public native int frame_height();

    public native AVDeviceCapabilitiesQuery frame_height(int var1);

    @ByRef
    public native AVRational fps();

    public native AVDeviceCapabilitiesQuery fps(AVRational var1);

    static {
        Loader.load();
    }
}

