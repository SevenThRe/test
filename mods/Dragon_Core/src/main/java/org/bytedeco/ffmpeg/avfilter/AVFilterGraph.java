/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avfilter.AVFilterContext;
import org.bytedeco.ffmpeg.avfilter.AVFilterGraphInternal;
import org.bytedeco.ffmpeg.avfilter.AVFilterLink;
import org.bytedeco.ffmpeg.avfilter.avfilter_execute_func;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avfilter.class})
public class AVFilterGraph
extends Pointer {
    public AVFilterGraph() {
        super((Pointer)null);
        this.allocate();
    }

    public AVFilterGraph(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVFilterGraph(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVFilterGraph position(long position) {
        return (AVFilterGraph)super.position(position);
    }

    public AVFilterGraph getPointer(long i2) {
        return (AVFilterGraph)new AVFilterGraph(this).offsetAddress(i2);
    }

    @Const
    public native AVClass av_class();

    public native AVFilterGraph av_class(AVClass var1);

    public native AVFilterContext filters(int var1);

    public native AVFilterGraph filters(int var1, AVFilterContext var2);

    @Cast(value={"AVFilterContext**"})
    public native PointerPointer filters();

    public native AVFilterGraph filters(PointerPointer var1);

    @Cast(value={"unsigned"})
    public native int nb_filters();

    public native AVFilterGraph nb_filters(int var1);

    @Cast(value={"char*"})
    public native BytePointer scale_sws_opts();

    public native AVFilterGraph scale_sws_opts(BytePointer var1);

    @Cast(value={"char*"})
    @Deprecated
    public native BytePointer resample_lavr_opts();

    public native AVFilterGraph resample_lavr_opts(BytePointer var1);

    public native int thread_type();

    public native AVFilterGraph thread_type(int var1);

    public native int nb_threads();

    public native AVFilterGraph nb_threads(int var1);

    public native AVFilterGraphInternal internal();

    public native AVFilterGraph internal(AVFilterGraphInternal var1);

    public native Pointer opaque();

    public native AVFilterGraph opaque(Pointer var1);

    public native avfilter_execute_func execute();

    public native AVFilterGraph execute(avfilter_execute_func var1);

    @Cast(value={"char*"})
    public native BytePointer aresample_swr_opts();

    public native AVFilterGraph aresample_swr_opts(BytePointer var1);

    public native AVFilterLink sink_links(int var1);

    public native AVFilterGraph sink_links(int var1, AVFilterLink var2);

    @Cast(value={"AVFilterLink**"})
    public native PointerPointer sink_links();

    public native AVFilterGraph sink_links(PointerPointer var1);

    public native int sink_links_count();

    public native AVFilterGraph sink_links_count(int var1);

    @Cast(value={"unsigned"})
    public native int disable_auto_convert();

    public native AVFilterGraph disable_auto_convert(int var1);

    static {
        Loader.load();
    }
}

