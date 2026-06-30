/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import org.bytedeco.ffmpeg.postproc.pp_context;
import org.bytedeco.ffmpeg.postproc.pp_mode;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.NoException;

public class postproc
extends org.bytedeco.ffmpeg.presets.postproc {
    public static final int PP_QUALITY_MAX = 6;
    public static final int PP_CPU_CAPS_MMX = Integer.MIN_VALUE;
    public static final int PP_CPU_CAPS_MMX2 = 0x20000000;
    public static final int PP_CPU_CAPS_3DNOW = 0x40000000;
    public static final int PP_CPU_CAPS_ALTIVEC = 0x10000000;
    public static final int PP_CPU_CAPS_AUTO = 524288;
    public static final int PP_FORMAT = 8;
    public static final int PP_FORMAT_420 = 25;
    public static final int PP_FORMAT_422 = 9;
    public static final int PP_FORMAT_411 = 10;
    public static final int PP_FORMAT_444 = 8;
    public static final int PP_FORMAT_440 = 24;
    public static final int PP_PICT_TYPE_QP2 = 16;

    @NoException
    @Cast(value={"unsigned"})
    public static native int postproc_version();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer postproc_configuration();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer postproc_license();

    @MemberGetter
    @Cast(value={"const char"})
    public static native byte pp_help(int var0);

    @MemberGetter
    @Cast(value={"const char*"})
    public static native BytePointer pp_help();

    @NoException
    public static native void pp_postprocess(@Cast(value={"const uint8_t**"}) PointerPointer var0, @Const IntPointer var1, @Cast(value={"uint8_t**"}) PointerPointer var2, @Const IntPointer var3, int var4, int var5, @Const BytePointer var6, int var7, pp_mode var8, pp_context var9, int var10);

    @NoException
    public static native void pp_postprocess(@Cast(value={"const uint8_t**"}) @ByPtrPtr BytePointer var0, @Const IntPointer var1, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var2, @Const IntPointer var3, int var4, int var5, @Const BytePointer var6, int var7, pp_mode var8, pp_context var9, int var10);

    @NoException
    public static native void pp_postprocess(@Cast(value={"const uint8_t**"}) @ByPtrPtr ByteBuffer var0, @Const IntBuffer var1, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var2, @Const IntBuffer var3, int var4, int var5, @Const ByteBuffer var6, int var7, pp_mode var8, pp_context var9, int var10);

    @NoException
    public static native void pp_postprocess(@Cast(value={"const uint8_t**"}) @ByPtrPtr byte[] var0, @Const int[] var1, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var2, @Const int[] var3, int var4, int var5, @Const byte[] var6, int var7, pp_mode var8, pp_context var9, int var10);

    @NoException
    public static native pp_mode pp_get_mode_by_name_and_quality(@Cast(value={"const char*"}) BytePointer var0, int var1);

    @NoException
    public static native pp_mode pp_get_mode_by_name_and_quality(String var0, int var1);

    @NoException
    public static native void pp_free_mode(pp_mode var0);

    @NoException
    public static native pp_context pp_get_context(int var0, int var1, int var2);

    @NoException
    public static native void pp_free_context(pp_context var0);

    static {
        Loader.load();
    }
}

