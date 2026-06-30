/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avfilter.AVFilterContext;
import org.bytedeco.ffmpeg.avfilter.AVFilterFormatsConfig;
import org.bytedeco.ffmpeg.avfilter.AVFilterGraph;
import org.bytedeco.ffmpeg.avfilter.AVFilterPad;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avfilter.class})
public class AVFilterLink
extends Pointer {
    public static final int AVLINK_UNINIT = 0;
    public static final int AVLINK_STARTINIT = 1;
    public static final int AVLINK_INIT = 2;

    public AVFilterLink() {
        super((Pointer)null);
        this.allocate();
    }

    public AVFilterLink(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVFilterLink(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVFilterLink position(long position) {
        return (AVFilterLink)super.position(position);
    }

    public AVFilterLink getPointer(long i2) {
        return (AVFilterLink)new AVFilterLink(this).offsetAddress(i2);
    }

    public native AVFilterContext src();

    public native AVFilterLink src(AVFilterContext var1);

    public native AVFilterPad srcpad();

    public native AVFilterLink srcpad(AVFilterPad var1);

    public native AVFilterContext dst();

    public native AVFilterLink dst(AVFilterContext var1);

    public native AVFilterPad dstpad();

    public native AVFilterLink dstpad(AVFilterPad var1);

    @Cast(value={"AVMediaType"})
    public native int type();

    public native AVFilterLink type(int var1);

    public native int w();

    public native AVFilterLink w(int var1);

    public native int h();

    public native AVFilterLink h(int var1);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native AVFilterLink sample_aspect_ratio(AVRational var1);

    @Cast(value={"uint64_t"})
    public native long channel_layout();

    public native AVFilterLink channel_layout(long var1);

    public native int sample_rate();

    public native AVFilterLink sample_rate(int var1);

    public native int format();

    public native AVFilterLink format(int var1);

    @ByRef
    public native AVRational time_base();

    public native AVFilterLink time_base(AVRational var1);

    @ByRef
    public native AVFilterFormatsConfig incfg();

    public native AVFilterLink incfg(AVFilterFormatsConfig var1);

    @ByRef
    public native AVFilterFormatsConfig outcfg();

    public native AVFilterLink outcfg(AVFilterFormatsConfig var1);

    public native AVFilterGraph graph();

    public native AVFilterLink graph(AVFilterGraph var1);

    @Cast(value={"int64_t"})
    public native long current_pts();

    public native AVFilterLink current_pts(long var1);

    @Cast(value={"int64_t"})
    public native long current_pts_us();

    public native AVFilterLink current_pts_us(long var1);

    public native int age_index();

    public native AVFilterLink age_index(int var1);

    @ByRef
    public native AVRational frame_rate();

    public native AVFilterLink frame_rate(AVRational var1);

    public native AVFrame partial_buf();

    public native AVFilterLink partial_buf(AVFrame var1);

    public native int partial_buf_size();

    public native AVFilterLink partial_buf_size(int var1);

    public native int min_samples();

    public native AVFilterLink min_samples(int var1);

    public native int max_samples();

    public native AVFilterLink max_samples(int var1);

    public native int channels();

    public native AVFilterLink channels(int var1);

    @Cast(value={"int64_t"})
    public native long frame_count_in();

    public native AVFilterLink frame_count_in(long var1);

    @Cast(value={"int64_t"})
    public native long frame_count_out();

    public native AVFilterLink frame_count_out(long var1);

    public native Pointer frame_pool();

    public native AVFilterLink frame_pool(Pointer var1);

    public native int frame_wanted_out();

    public native AVFilterLink frame_wanted_out(int var1);

    public native AVBufferRef hw_frames_ctx();

    public native AVFilterLink hw_frames_ctx(AVBufferRef var1);

    @Cast(value={"char"})
    public native byte reserved(int var1);

    public native AVFilterLink reserved(int var1, byte var2);

    @MemberGetter
    @Cast(value={"char*"})
    public native BytePointer reserved();

    static {
        Loader.load();
    }
}

