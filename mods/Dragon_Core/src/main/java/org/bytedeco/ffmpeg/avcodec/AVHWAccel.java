/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.MpegEncContext;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class AVHWAccel
extends Pointer {
    public AVHWAccel() {
        super((Pointer)null);
        this.allocate();
    }

    public AVHWAccel(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVHWAccel(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVHWAccel position(long position) {
        return (AVHWAccel)super.position(position);
    }

    public AVHWAccel getPointer(long i2) {
        return (AVHWAccel)new AVHWAccel(this).offsetAddress(i2);
    }

    @Cast(value={"const char*"})
    public native BytePointer name();

    public native AVHWAccel name(BytePointer var1);

    @Cast(value={"AVMediaType"})
    public native int type();

    public native AVHWAccel type(int var1);

    @Cast(value={"AVCodecID"})
    public native int id();

    public native AVHWAccel id(int var1);

    @Cast(value={"AVPixelFormat"})
    public native int pix_fmt();

    public native AVHWAccel pix_fmt(int var1);

    public native int capabilities();

    public native AVHWAccel capabilities(int var1);

    public native Alloc_frame_AVCodecContext_AVFrame alloc_frame();

    public native AVHWAccel alloc_frame(Alloc_frame_AVCodecContext_AVFrame var1);

    public native Start_frame_AVCodecContext_BytePointer_int start_frame();

    public native AVHWAccel start_frame(Start_frame_AVCodecContext_BytePointer_int var1);

    public native Decode_params_AVCodecContext_int_BytePointer_int decode_params();

    public native AVHWAccel decode_params(Decode_params_AVCodecContext_int_BytePointer_int var1);

    public native Decode_slice_AVCodecContext_BytePointer_int decode_slice();

    public native AVHWAccel decode_slice(Decode_slice_AVCodecContext_BytePointer_int var1);

    public native End_frame_AVCodecContext end_frame();

    public native AVHWAccel end_frame(End_frame_AVCodecContext var1);

    public native int frame_priv_data_size();

    public native AVHWAccel frame_priv_data_size(int var1);

    public native Decode_mb_MpegEncContext decode_mb();

    public native AVHWAccel decode_mb(Decode_mb_MpegEncContext var1);

    public native Init_AVCodecContext init();

    public native AVHWAccel init(Init_AVCodecContext var1);

    public native Uninit_AVCodecContext uninit();

    public native AVHWAccel uninit(Uninit_AVCodecContext var1);

    public native int priv_data_size();

    public native AVHWAccel priv_data_size(int var1);

    public native int caps_internal();

    public native AVHWAccel caps_internal(int var1);

    public native Frame_params_AVCodecContext_AVBufferRef frame_params();

    public native AVHWAccel frame_params(Frame_params_AVCodecContext_AVBufferRef var1);

    static {
        Loader.load();
    }

    public static class Frame_params_AVCodecContext_AVBufferRef
    extends FunctionPointer {
        public Frame_params_AVCodecContext_AVBufferRef(Pointer p2) {
            super(p2);
        }

        protected Frame_params_AVCodecContext_AVBufferRef() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, AVBufferRef var2);

        static {
            Loader.load();
        }
    }

    public static class Uninit_AVCodecContext
    extends FunctionPointer {
        public Uninit_AVCodecContext(Pointer p2) {
            super(p2);
        }

        protected Uninit_AVCodecContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1);

        static {
            Loader.load();
        }
    }

    public static class Init_AVCodecContext
    extends FunctionPointer {
        public Init_AVCodecContext(Pointer p2) {
            super(p2);
        }

        protected Init_AVCodecContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1);

        static {
            Loader.load();
        }
    }

    public static class Decode_mb_MpegEncContext
    extends FunctionPointer {
        public Decode_mb_MpegEncContext(Pointer p2) {
            super(p2);
        }

        protected Decode_mb_MpegEncContext() {
            this.allocate();
        }

        private native void allocate();

        public native void call(MpegEncContext var1);

        static {
            Loader.load();
        }
    }

    public static class End_frame_AVCodecContext
    extends FunctionPointer {
        public End_frame_AVCodecContext(Pointer p2) {
            super(p2);
        }

        protected End_frame_AVCodecContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1);

        static {
            Loader.load();
        }
    }

    public static class Decode_slice_AVCodecContext_BytePointer_int
    extends FunctionPointer {
        public Decode_slice_AVCodecContext_BytePointer_int(Pointer p2) {
            super(p2);
        }

        protected Decode_slice_AVCodecContext_BytePointer_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, @Cast(value={"const uint8_t*"}) BytePointer var2, @Cast(value={"uint32_t"}) int var3);

        static {
            Loader.load();
        }
    }

    public static class Decode_params_AVCodecContext_int_BytePointer_int
    extends FunctionPointer {
        public Decode_params_AVCodecContext_int_BytePointer_int(Pointer p2) {
            super(p2);
        }

        protected Decode_params_AVCodecContext_int_BytePointer_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, int var2, @Cast(value={"const uint8_t*"}) BytePointer var3, @Cast(value={"uint32_t"}) int var4);

        static {
            Loader.load();
        }
    }

    public static class Start_frame_AVCodecContext_BytePointer_int
    extends FunctionPointer {
        public Start_frame_AVCodecContext_BytePointer_int(Pointer p2) {
            super(p2);
        }

        protected Start_frame_AVCodecContext_BytePointer_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, @Cast(value={"const uint8_t*"}) BytePointer var2, @Cast(value={"uint32_t"}) int var3);

        static {
            Loader.load();
        }
    }

    public static class Alloc_frame_AVCodecContext_AVFrame
    extends FunctionPointer {
        public Alloc_frame_AVCodecContext_AVFrame(Pointer p2) {
            super(p2);
        }

        protected Alloc_frame_AVCodecContext_AVFrame() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, AVFrame var2);

        static {
            Loader.load();
        }
    }
}

