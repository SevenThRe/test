/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import org.bytedeco.ffmpeg.avfilter.AVABufferSinkParams;
import org.bytedeco.ffmpeg.avfilter.AVBufferSinkParams;
import org.bytedeco.ffmpeg.avfilter.AVBufferSrcParameters;
import org.bytedeco.ffmpeg.avfilter.AVFilter;
import org.bytedeco.ffmpeg.avfilter.AVFilterContext;
import org.bytedeco.ffmpeg.avfilter.AVFilterGraph;
import org.bytedeco.ffmpeg.avfilter.AVFilterInOut;
import org.bytedeco.ffmpeg.avfilter.AVFilterLink;
import org.bytedeco.ffmpeg.avfilter.AVFilterPad;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.NoException;

public class avfilter
extends org.bytedeco.ffmpeg.presets.avfilter {
    public static final int AVFILTER_FLAG_DYNAMIC_INPUTS = 1;
    public static final int AVFILTER_FLAG_DYNAMIC_OUTPUTS = 2;
    public static final int AVFILTER_FLAG_SLICE_THREADS = 4;
    public static final int AVFILTER_FLAG_SUPPORT_TIMELINE_GENERIC = 65536;
    public static final int AVFILTER_FLAG_SUPPORT_TIMELINE_INTERNAL = 131072;
    public static final int AVFILTER_FLAG_SUPPORT_TIMELINE = 196608;
    public static final int AVFILTER_THREAD_SLICE = 1;
    public static final int AVFILTER_CMD_FLAG_ONE = 1;
    public static final int AVFILTER_CMD_FLAG_FAST = 2;
    public static final int AVFILTER_AUTO_CONVERT_ALL = 0;
    public static final int AVFILTER_AUTO_CONVERT_NONE = -1;
    public static final int AV_BUFFERSINK_FLAG_PEEK = 1;
    public static final int AV_BUFFERSINK_FLAG_NO_REQUEST = 2;
    public static final int AV_BUFFERSRC_FLAG_NO_CHECK_FORMAT = 1;
    public static final int AV_BUFFERSRC_FLAG_PUSH = 4;
    public static final int AV_BUFFERSRC_FLAG_KEEP_REF = 8;

    @NoException
    @Cast(value={"unsigned"})
    public static native int avfilter_version();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avfilter_configuration();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avfilter_license();

    @NoException
    public static native int avfilter_pad_count(@Const AVFilterPad var0);

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avfilter_pad_get_name(@Const AVFilterPad var0, int var1);

    @NoException
    @Cast(value={"AVMediaType"})
    public static native int avfilter_pad_get_type(@Const AVFilterPad var0, int var1);

    @NoException
    public static native int avfilter_link(AVFilterContext var0, @Cast(value={"unsigned"}) int var1, AVFilterContext var2, @Cast(value={"unsigned"}) int var3);

    @NoException
    public static native void avfilter_link_free(@Cast(value={"AVFilterLink**"}) PointerPointer var0);

    @NoException
    public static native void avfilter_link_free(@ByPtrPtr AVFilterLink var0);

    @NoException
    @Deprecated
    public static native int avfilter_link_get_channels(AVFilterLink var0);

    @NoException
    @Deprecated
    public static native void avfilter_link_set_closed(AVFilterLink var0, int var1);

    @NoException
    public static native int avfilter_config_links(AVFilterContext var0);

    @NoException
    public static native int avfilter_process_command(AVFilterContext var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"char*"}) BytePointer var3, int var4, int var5);

    @NoException
    public static native int avfilter_process_command(AVFilterContext var0, String var1, String var2, @Cast(value={"char*"}) ByteBuffer var3, int var4, int var5);

    @NoException
    public static native int avfilter_process_command(AVFilterContext var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"char*"}) byte[] var3, int var4, int var5);

    @NoException
    public static native int avfilter_process_command(AVFilterContext var0, String var1, String var2, @Cast(value={"char*"}) BytePointer var3, int var4, int var5);

    @NoException
    public static native int avfilter_process_command(AVFilterContext var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"char*"}) ByteBuffer var3, int var4, int var5);

    @NoException
    public static native int avfilter_process_command(AVFilterContext var0, String var1, String var2, @Cast(value={"char*"}) byte[] var3, int var4, int var5);

    @NoException
    @Const
    public static native AVFilter av_filter_iterate(@Cast(value={"void**"}) PointerPointer var0);

    @NoException
    @Const
    public static native AVFilter av_filter_iterate(@Cast(value={"void**"}) @ByPtrPtr Pointer var0);

    @NoException
    @Deprecated
    public static native void avfilter_register_all();

    @NoException
    @Deprecated
    public static native int avfilter_register(AVFilter var0);

    @NoException
    @Const
    @Deprecated
    public static native AVFilter avfilter_next(@Const AVFilter var0);

    @NoException
    @Const
    public static native AVFilter avfilter_get_by_name(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    @Const
    public static native AVFilter avfilter_get_by_name(String var0);

    @NoException
    public static native int avfilter_init_str(AVFilterContext var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native int avfilter_init_str(AVFilterContext var0, String var1);

    @NoException
    public static native int avfilter_init_dict(AVFilterContext var0, @Cast(value={"AVDictionary**"}) PointerPointer var1);

    @NoException
    public static native int avfilter_init_dict(AVFilterContext var0, @ByPtrPtr AVDictionary var1);

    @NoException
    public static native void avfilter_free(AVFilterContext var0);

    @NoException
    public static native int avfilter_insert_filter(AVFilterLink var0, AVFilterContext var1, @Cast(value={"unsigned"}) int var2, @Cast(value={"unsigned"}) int var3);

    @NoException
    @Const
    public static native AVClass avfilter_get_class();

    @NoException
    public static native AVFilterGraph avfilter_graph_alloc();

    @NoException
    public static native AVFilterContext avfilter_graph_alloc_filter(AVFilterGraph var0, @Const AVFilter var1, @Cast(value={"const char*"}) BytePointer var2);

    @NoException
    public static native AVFilterContext avfilter_graph_alloc_filter(AVFilterGraph var0, @Const AVFilter var1, String var2);

    @NoException
    public static native AVFilterContext avfilter_graph_get_filter(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native AVFilterContext avfilter_graph_get_filter(AVFilterGraph var0, String var1);

    @NoException
    public static native int avfilter_graph_create_filter(@Cast(value={"AVFilterContext**"}) PointerPointer var0, @Const AVFilter var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, Pointer var4, AVFilterGraph var5);

    @NoException
    public static native int avfilter_graph_create_filter(@ByPtrPtr AVFilterContext var0, @Const AVFilter var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, Pointer var4, AVFilterGraph var5);

    @NoException
    public static native int avfilter_graph_create_filter(@ByPtrPtr AVFilterContext var0, @Const AVFilter var1, String var2, String var3, Pointer var4, AVFilterGraph var5);

    @NoException
    public static native void avfilter_graph_set_auto_convert(AVFilterGraph var0, @Cast(value={"unsigned"}) int var1);

    @NoException
    public static native int avfilter_graph_config(AVFilterGraph var0, Pointer var1);

    @NoException
    public static native void avfilter_graph_free(@Cast(value={"AVFilterGraph**"}) PointerPointer var0);

    @NoException
    public static native void avfilter_graph_free(@ByPtrPtr AVFilterGraph var0);

    @NoException
    public static native AVFilterInOut avfilter_inout_alloc();

    @NoException
    public static native void avfilter_inout_free(@Cast(value={"AVFilterInOut**"}) PointerPointer var0);

    @NoException
    public static native void avfilter_inout_free(@ByPtrPtr AVFilterInOut var0);

    @NoException
    public static native int avfilter_graph_parse(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, AVFilterInOut var2, AVFilterInOut var3, Pointer var4);

    @NoException
    public static native int avfilter_graph_parse(AVFilterGraph var0, String var1, AVFilterInOut var2, AVFilterInOut var3, Pointer var4);

    @NoException
    public static native int avfilter_graph_parse_ptr(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"AVFilterInOut**"}) PointerPointer var2, @Cast(value={"AVFilterInOut**"}) PointerPointer var3, Pointer var4);

    @NoException
    public static native int avfilter_graph_parse_ptr(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @ByPtrPtr AVFilterInOut var2, @ByPtrPtr AVFilterInOut var3, Pointer var4);

    @NoException
    public static native int avfilter_graph_parse_ptr(AVFilterGraph var0, String var1, @ByPtrPtr AVFilterInOut var2, @ByPtrPtr AVFilterInOut var3, Pointer var4);

    @NoException
    public static native int avfilter_graph_parse2(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"AVFilterInOut**"}) PointerPointer var2, @Cast(value={"AVFilterInOut**"}) PointerPointer var3);

    @NoException
    public static native int avfilter_graph_parse2(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @ByPtrPtr AVFilterInOut var2, @ByPtrPtr AVFilterInOut var3);

    @NoException
    public static native int avfilter_graph_parse2(AVFilterGraph var0, String var1, @ByPtrPtr AVFilterInOut var2, @ByPtrPtr AVFilterInOut var3);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, @Cast(value={"char*"}) BytePointer var4, int var5, int var6);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph var0, String var1, String var2, String var3, @Cast(value={"char*"}) ByteBuffer var4, int var5, int var6);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, @Cast(value={"char*"}) byte[] var4, int var5, int var6);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph var0, String var1, String var2, String var3, @Cast(value={"char*"}) BytePointer var4, int var5, int var6);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, @Cast(value={"char*"}) ByteBuffer var4, int var5, int var6);

    @NoException
    public static native int avfilter_graph_send_command(AVFilterGraph var0, String var1, String var2, String var3, @Cast(value={"char*"}) byte[] var4, int var5, int var6);

    @NoException
    public static native int avfilter_graph_queue_command(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, int var4, double var5);

    @NoException
    public static native int avfilter_graph_queue_command(AVFilterGraph var0, String var1, String var2, String var3, int var4, double var5);

    @NoException
    @Cast(value={"char*"})
    public static native BytePointer avfilter_graph_dump(AVFilterGraph var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    @Cast(value={"char*"})
    public static native ByteBuffer avfilter_graph_dump(AVFilterGraph var0, String var1);

    @NoException
    public static native int avfilter_graph_request_oldest(AVFilterGraph var0);

    @NoException
    public static native int av_buffersink_get_frame_flags(AVFilterContext var0, AVFrame var1, int var2);

    @NoException
    @Deprecated
    public static native AVBufferSinkParams av_buffersink_params_alloc();

    @NoException
    @Deprecated
    public static native AVABufferSinkParams av_abuffersink_params_alloc();

    @NoException
    public static native void av_buffersink_set_frame_size(AVFilterContext var0, @Cast(value={"unsigned"}) int var1);

    @NoException
    @Cast(value={"AVMediaType"})
    public static native int av_buffersink_get_type(@Const AVFilterContext var0);

    @NoException
    @ByVal
    public static native AVRational av_buffersink_get_time_base(@Const AVFilterContext var0);

    @NoException
    public static native int av_buffersink_get_format(@Const AVFilterContext var0);

    @NoException
    @ByVal
    public static native AVRational av_buffersink_get_frame_rate(@Const AVFilterContext var0);

    @NoException
    public static native int av_buffersink_get_w(@Const AVFilterContext var0);

    @NoException
    public static native int av_buffersink_get_h(@Const AVFilterContext var0);

    @NoException
    @ByVal
    public static native AVRational av_buffersink_get_sample_aspect_ratio(@Const AVFilterContext var0);

    @NoException
    public static native int av_buffersink_get_channels(@Const AVFilterContext var0);

    @NoException
    @Cast(value={"uint64_t"})
    public static native long av_buffersink_get_channel_layout(@Const AVFilterContext var0);

    @NoException
    public static native int av_buffersink_get_sample_rate(@Const AVFilterContext var0);

    @NoException
    public static native AVBufferRef av_buffersink_get_hw_frames_ctx(@Const AVFilterContext var0);

    @NoException
    public static native int av_buffersink_get_frame(AVFilterContext var0, AVFrame var1);

    @NoException
    public static native int av_buffersink_get_samples(AVFilterContext var0, AVFrame var1, int var2);

    @NoException
    @Cast(value={"unsigned"})
    public static native int av_buffersrc_get_nb_failed_requests(AVFilterContext var0);

    @NoException
    public static native AVBufferSrcParameters av_buffersrc_parameters_alloc();

    @NoException
    public static native int av_buffersrc_parameters_set(AVFilterContext var0, AVBufferSrcParameters var1);

    @NoException
    public static native int av_buffersrc_write_frame(AVFilterContext var0, @Const AVFrame var1);

    @NoException
    public static native int av_buffersrc_add_frame(AVFilterContext var0, AVFrame var1);

    @NoException
    public static native int av_buffersrc_add_frame_flags(AVFilterContext var0, AVFrame var1, int var2);

    @NoException
    public static native int av_buffersrc_close(AVFilterContext var0, @Cast(value={"int64_t"}) long var1, @Cast(value={"unsigned"}) int var3);

    static {
        Loader.load();
    }
}

