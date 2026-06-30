/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.IntBuffer;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.swscale.SwsContext;
import org.bytedeco.ffmpeg.swscale.SwsFilter;
import org.bytedeco.ffmpeg.swscale.SwsVector;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.NoException;

public class swscale
extends org.bytedeco.ffmpeg.presets.swscale {
    public static final int SWS_FAST_BILINEAR = 1;
    public static final int SWS_BILINEAR = 2;
    public static final int SWS_BICUBIC = 4;
    public static final int SWS_X = 8;
    public static final int SWS_POINT = 16;
    public static final int SWS_AREA = 32;
    public static final int SWS_BICUBLIN = 64;
    public static final int SWS_GAUSS = 128;
    public static final int SWS_SINC = 256;
    public static final int SWS_LANCZOS = 512;
    public static final int SWS_SPLINE = 1024;
    public static final int SWS_SRC_V_CHR_DROP_MASK = 196608;
    public static final int SWS_SRC_V_CHR_DROP_SHIFT = 16;
    public static final int SWS_PARAM_DEFAULT = 123456;
    public static final int SWS_PRINT_INFO = 4096;
    public static final int SWS_FULL_CHR_H_INT = 8192;
    public static final int SWS_FULL_CHR_H_INP = 16384;
    public static final int SWS_DIRECT_BGR = 32768;
    public static final int SWS_ACCURATE_RND = 262144;
    public static final int SWS_BITEXACT = 524288;
    public static final int SWS_ERROR_DIFFUSION = 0x800000;
    public static final double SWS_MAX_REDUCE_CUTOFF = 0.002;
    public static final int SWS_CS_ITU709 = 1;
    public static final int SWS_CS_FCC = 4;
    public static final int SWS_CS_ITU601 = 5;
    public static final int SWS_CS_ITU624 = 5;
    public static final int SWS_CS_SMPTE170M = 5;
    public static final int SWS_CS_SMPTE240M = 7;
    public static final int SWS_CS_DEFAULT = 5;
    public static final int SWS_CS_BT2020 = 9;

    @NoException
    @Cast(value={"unsigned"})
    public static native int swscale_version();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer swscale_configuration();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer swscale_license();

    @NoException
    @Const
    public static native IntPointer sws_getCoefficients(int var0);

    @NoException
    public static native int sws_isSupportedInput(@Cast(value={"AVPixelFormat"}) int var0);

    @NoException
    public static native int sws_isSupportedOutput(@Cast(value={"AVPixelFormat"}) int var0);

    @NoException
    public static native int sws_isSupportedEndiannessConversion(@Cast(value={"AVPixelFormat"}) int var0);

    @NoException
    public static native SwsContext sws_alloc_context();

    @NoException
    public static native int sws_init_context(SwsContext var0, SwsFilter var1, SwsFilter var2);

    @NoException
    public static native void sws_freeContext(SwsContext var0);

    @NoException
    public static native SwsContext sws_getContext(int var0, int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4, @Cast(value={"AVPixelFormat"}) int var5, int var6, SwsFilter var7, SwsFilter var8, @Const DoublePointer var9);

    @NoException
    public static native SwsContext sws_getContext(int var0, int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4, @Cast(value={"AVPixelFormat"}) int var5, int var6, SwsFilter var7, SwsFilter var8, @Const DoubleBuffer var9);

    @NoException
    public static native SwsContext sws_getContext(int var0, int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4, @Cast(value={"AVPixelFormat"}) int var5, int var6, SwsFilter var7, SwsFilter var8, @Const double[] var9);

    @NoException
    public static native int sws_scale(SwsContext var0, @Cast(value={"const uint8_t*const*"}) PointerPointer var1, @Const IntPointer var2, int var3, int var4, @Cast(value={"uint8_t*const*"}) PointerPointer var5, @Const IntPointer var6);

    @NoException
    public static native int sws_scale(SwsContext var0, @Cast(value={"const uint8_t*const*"}) @ByPtrPtr BytePointer var1, @Const IntPointer var2, int var3, int var4, @Cast(value={"uint8_t*const*"}) @ByPtrPtr BytePointer var5, @Const IntPointer var6);

    @NoException
    public static native int sws_scale(SwsContext var0, @Cast(value={"const uint8_t*const*"}) @ByPtrPtr ByteBuffer var1, @Const IntBuffer var2, int var3, int var4, @Cast(value={"uint8_t*const*"}) @ByPtrPtr ByteBuffer var5, @Const IntBuffer var6);

    @NoException
    public static native int sws_scale(SwsContext var0, @Cast(value={"const uint8_t*const*"}) @ByPtrPtr byte[] var1, @Const int[] var2, int var3, int var4, @Cast(value={"uint8_t*const*"}) @ByPtrPtr byte[] var5, @Const int[] var6);

    @NoException
    public static native int sws_setColorspaceDetails(SwsContext var0, @Const IntPointer var1, int var2, @Const IntPointer var3, int var4, int var5, int var6, int var7);

    @NoException
    public static native int sws_setColorspaceDetails(SwsContext var0, @Const IntBuffer var1, int var2, @Const IntBuffer var3, int var4, int var5, int var6, int var7);

    @NoException
    public static native int sws_setColorspaceDetails(SwsContext var0, @Const int[] var1, int var2, @Const int[] var3, int var4, int var5, int var6, int var7);

    @NoException
    public static native int sws_getColorspaceDetails(SwsContext var0, @Cast(value={"int**"}) PointerPointer var1, IntPointer var2, @Cast(value={"int**"}) PointerPointer var3, IntPointer var4, IntPointer var5, IntPointer var6, IntPointer var7);

    @NoException
    public static native int sws_getColorspaceDetails(SwsContext var0, @ByPtrPtr IntPointer var1, IntPointer var2, @ByPtrPtr IntPointer var3, IntPointer var4, IntPointer var5, IntPointer var6, IntPointer var7);

    @NoException
    public static native int sws_getColorspaceDetails(SwsContext var0, @ByPtrPtr IntBuffer var1, IntBuffer var2, @ByPtrPtr IntBuffer var3, IntBuffer var4, IntBuffer var5, IntBuffer var6, IntBuffer var7);

    @NoException
    public static native int sws_getColorspaceDetails(SwsContext var0, @ByPtrPtr int[] var1, int[] var2, @ByPtrPtr int[] var3, int[] var4, int[] var5, int[] var6, int[] var7);

    @NoException
    public static native SwsVector sws_allocVec(int var0);

    @NoException
    public static native SwsVector sws_getGaussianVec(double var0, double var2);

    @NoException
    public static native void sws_scaleVec(SwsVector var0, double var1);

    @NoException
    public static native void sws_normalizeVec(SwsVector var0, double var1);

    @NoException
    @Deprecated
    public static native SwsVector sws_getConstVec(double var0, int var2);

    @NoException
    @Deprecated
    public static native SwsVector sws_getIdentityVec();

    @NoException
    @Deprecated
    public static native void sws_convVec(SwsVector var0, SwsVector var1);

    @NoException
    @Deprecated
    public static native void sws_addVec(SwsVector var0, SwsVector var1);

    @NoException
    @Deprecated
    public static native void sws_subVec(SwsVector var0, SwsVector var1);

    @NoException
    @Deprecated
    public static native void sws_shiftVec(SwsVector var0, int var1);

    @NoException
    @Deprecated
    public static native SwsVector sws_cloneVec(SwsVector var0);

    @NoException
    @Deprecated
    public static native void sws_printVec2(SwsVector var0, AVClass var1, int var2);

    @NoException
    public static native void sws_freeVec(SwsVector var0);

    @NoException
    public static native SwsFilter sws_getDefaultFilter(float var0, float var1, float var2, float var3, float var4, float var5, int var6);

    @NoException
    public static native void sws_freeFilter(SwsFilter var0);

    @NoException
    public static native SwsContext sws_getCachedContext(SwsContext var0, int var1, int var2, @Cast(value={"AVPixelFormat"}) int var3, int var4, int var5, @Cast(value={"AVPixelFormat"}) int var6, int var7, SwsFilter var8, SwsFilter var9, @Const DoublePointer var10);

    @NoException
    public static native SwsContext sws_getCachedContext(SwsContext var0, int var1, int var2, @Cast(value={"AVPixelFormat"}) int var3, int var4, int var5, @Cast(value={"AVPixelFormat"}) int var6, int var7, SwsFilter var8, SwsFilter var9, @Const DoubleBuffer var10);

    @NoException
    public static native SwsContext sws_getCachedContext(SwsContext var0, int var1, int var2, @Cast(value={"AVPixelFormat"}) int var3, int var4, int var5, @Cast(value={"AVPixelFormat"}) int var6, int var7, SwsFilter var8, SwsFilter var9, @Const double[] var10);

    @NoException
    public static native void sws_convertPalette8ToPacked32(@Cast(value={"const uint8_t*"}) BytePointer var0, @Cast(value={"uint8_t*"}) BytePointer var1, int var2, @Cast(value={"const uint8_t*"}) BytePointer var3);

    @NoException
    public static native void sws_convertPalette8ToPacked32(@Cast(value={"const uint8_t*"}) ByteBuffer var0, @Cast(value={"uint8_t*"}) ByteBuffer var1, int var2, @Cast(value={"const uint8_t*"}) ByteBuffer var3);

    @NoException
    public static native void sws_convertPalette8ToPacked32(@Cast(value={"const uint8_t*"}) byte[] var0, @Cast(value={"uint8_t*"}) byte[] var1, int var2, @Cast(value={"const uint8_t*"}) byte[] var3);

    @NoException
    public static native void sws_convertPalette8ToPacked24(@Cast(value={"const uint8_t*"}) BytePointer var0, @Cast(value={"uint8_t*"}) BytePointer var1, int var2, @Cast(value={"const uint8_t*"}) BytePointer var3);

    @NoException
    public static native void sws_convertPalette8ToPacked24(@Cast(value={"const uint8_t*"}) ByteBuffer var0, @Cast(value={"uint8_t*"}) ByteBuffer var1, int var2, @Cast(value={"const uint8_t*"}) ByteBuffer var3);

    @NoException
    public static native void sws_convertPalette8ToPacked24(@Cast(value={"const uint8_t*"}) byte[] var0, @Cast(value={"uint8_t*"}) byte[] var1, int var2, @Cast(value={"const uint8_t*"}) byte[] var3);

    @NoException
    @Const
    public static native AVClass sws_get_class();

    static {
        Loader.load();
    }
}

