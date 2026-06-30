/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.swresample.SwrContext;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.NoException;

public class swresample
extends org.bytedeco.ffmpeg.presets.swresample {
    public static final int SWR_FLAG_RESAMPLE = 1;
    public static final int SWR_DITHER_NONE = 0;
    public static final int SWR_DITHER_RECTANGULAR = 1;
    public static final int SWR_DITHER_TRIANGULAR = 2;
    public static final int SWR_DITHER_TRIANGULAR_HIGHPASS = 3;
    public static final int SWR_DITHER_NS = 64;
    public static final int SWR_DITHER_NS_LIPSHITZ = 65;
    public static final int SWR_DITHER_NS_F_WEIGHTED = 66;
    public static final int SWR_DITHER_NS_MODIFIED_E_WEIGHTED = 67;
    public static final int SWR_DITHER_NS_IMPROVED_E_WEIGHTED = 68;
    public static final int SWR_DITHER_NS_SHIBATA = 69;
    public static final int SWR_DITHER_NS_LOW_SHIBATA = 70;
    public static final int SWR_DITHER_NS_HIGH_SHIBATA = 71;
    public static final int SWR_DITHER_NB = 72;
    public static final int SWR_ENGINE_SWR = 0;
    public static final int SWR_ENGINE_SOXR = 1;
    public static final int SWR_ENGINE_NB = 2;
    public static final int SWR_FILTER_TYPE_CUBIC = 0;
    public static final int SWR_FILTER_TYPE_BLACKMAN_NUTTALL = 1;
    public static final int SWR_FILTER_TYPE_KAISER = 2;

    @NoException
    @Const
    public static native AVClass swr_get_class();

    @NoException
    public static native SwrContext swr_alloc();

    @NoException
    public static native int swr_init(SwrContext var0);

    @NoException
    public static native int swr_is_initialized(SwrContext var0);

    @NoException
    public static native SwrContext swr_alloc_set_opts(SwrContext var0, @Cast(value={"int64_t"}) long var1, @Cast(value={"AVSampleFormat"}) int var3, int var4, @Cast(value={"int64_t"}) long var5, @Cast(value={"AVSampleFormat"}) int var7, int var8, int var9, Pointer var10);

    @NoException
    public static native void swr_free(@Cast(value={"SwrContext**"}) PointerPointer var0);

    @NoException
    public static native void swr_free(@ByPtrPtr SwrContext var0);

    @NoException
    public static native void swr_close(SwrContext var0);

    @NoException
    public static native int swr_convert(SwrContext var0, @Cast(value={"uint8_t**"}) PointerPointer var1, int var2, @Cast(value={"const uint8_t**"}) PointerPointer var3, int var4);

    @NoException
    public static native int swr_convert(SwrContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var1, int var2, @Cast(value={"const uint8_t**"}) @ByPtrPtr BytePointer var3, int var4);

    @NoException
    public static native int swr_convert(SwrContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var1, int var2, @Cast(value={"const uint8_t**"}) @ByPtrPtr ByteBuffer var3, int var4);

    @NoException
    public static native int swr_convert(SwrContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var1, int var2, @Cast(value={"const uint8_t**"}) @ByPtrPtr byte[] var3, int var4);

    @NoException
    @Cast(value={"int64_t"})
    public static native long swr_next_pts(SwrContext var0, @Cast(value={"int64_t"}) long var1);

    @NoException
    public static native int swr_set_compensation(SwrContext var0, int var1, int var2);

    @NoException
    public static native int swr_set_channel_mapping(SwrContext var0, @Const IntPointer var1);

    @NoException
    public static native int swr_set_channel_mapping(SwrContext var0, @Const IntBuffer var1);

    @NoException
    public static native int swr_set_channel_mapping(SwrContext var0, @Const int[] var1);

    @NoException
    public static native int swr_build_matrix(@Cast(value={"uint64_t"}) long var0, @Cast(value={"uint64_t"}) long var2, double var4, double var6, double var8, double var10, double var12, DoublePointer var14, int var15, @Cast(value={"AVMatrixEncoding"}) int var16, Pointer var17);

    @NoException
    public static native int swr_build_matrix(@Cast(value={"uint64_t"}) long var0, @Cast(value={"uint64_t"}) long var2, double var4, double var6, double var8, double var10, double var12, DoubleBuffer var14, int var15, @Cast(value={"AVMatrixEncoding"}) int var16, Pointer var17);

    @NoException
    public static native int swr_build_matrix(@Cast(value={"uint64_t"}) long var0, @Cast(value={"uint64_t"}) long var2, double var4, double var6, double var8, double var10, double var12, double[] var14, int var15, @Cast(value={"AVMatrixEncoding"}) int var16, Pointer var17);

    @NoException
    public static native int swr_set_matrix(SwrContext var0, @Const DoublePointer var1, int var2);

    @NoException
    public static native int swr_set_matrix(SwrContext var0, @Const DoubleBuffer var1, int var2);

    @NoException
    public static native int swr_set_matrix(SwrContext var0, @Const double[] var1, int var2);

    @NoException
    public static native int swr_drop_output(SwrContext var0, int var1);

    @NoException
    public static native int swr_inject_silence(SwrContext var0, int var1);

    @NoException
    @Cast(value={"int64_t"})
    public static native long swr_get_delay(SwrContext var0, @Cast(value={"int64_t"}) long var1);

    @NoException
    public static native int swr_get_out_samples(SwrContext var0, int var1);

    @NoException
    @Cast(value={"unsigned"})
    public static native int swresample_version();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer swresample_configuration();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer swresample_license();

    @NoException
    public static native int swr_convert_frame(SwrContext var0, AVFrame var1, @Const AVFrame var2);

    @NoException
    public static native int swr_config_frame(SwrContext var0, @Const AVFrame var1, @Const AVFrame var2);

    static {
        Loader.load();
    }
}

