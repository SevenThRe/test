/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVCodecDefault;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avcodec.AVProfile;
import org.bytedeco.ffmpeg.avcodec.AVSubtitle;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class AVCodec
extends Pointer {
    public AVCodec() {
        super((Pointer)null);
        this.allocate();
    }

    public AVCodec(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVCodec(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVCodec position(long position) {
        return (AVCodec)super.position(position);
    }

    public AVCodec getPointer(long i2) {
        return (AVCodec)new AVCodec(this).offsetAddress(i2);
    }

    @Cast(value={"const char*"})
    public native BytePointer name();

    public native AVCodec name(BytePointer var1);

    @Cast(value={"const char*"})
    public native BytePointer long_name();

    public native AVCodec long_name(BytePointer var1);

    @Cast(value={"AVMediaType"})
    public native int type();

    public native AVCodec type(int var1);

    @Cast(value={"AVCodecID"})
    public native int id();

    public native AVCodec id(int var1);

    public native int capabilities();

    public native AVCodec capabilities(int var1);

    @Const
    public native AVRational supported_framerates();

    public native AVCodec supported_framerates(AVRational var1);

    @Cast(value={"const AVPixelFormat*"})
    public native IntPointer pix_fmts();

    public native AVCodec pix_fmts(IntPointer var1);

    @Const
    public native IntPointer supported_samplerates();

    public native AVCodec supported_samplerates(IntPointer var1);

    @Cast(value={"const AVSampleFormat*"})
    public native IntPointer sample_fmts();

    public native AVCodec sample_fmts(IntPointer var1);

    @Cast(value={"const uint64_t*"})
    public native LongPointer channel_layouts();

    public native AVCodec channel_layouts(LongPointer var1);

    @Cast(value={"uint8_t"})
    public native byte max_lowres();

    public native AVCodec max_lowres(byte var1);

    @Const
    public native AVClass priv_class();

    public native AVCodec priv_class(AVClass var1);

    @Const
    public native AVProfile profiles();

    public native AVCodec profiles(AVProfile var1);

    @Cast(value={"const char*"})
    public native BytePointer wrapper_name();

    public native AVCodec wrapper_name(BytePointer var1);

    public native int priv_data_size();

    public native AVCodec priv_data_size(int var1);

    public native AVCodec next();

    public native AVCodec next(AVCodec var1);

    public native Update_thread_context_AVCodecContext_AVCodecContext update_thread_context();

    public native AVCodec update_thread_context(Update_thread_context_AVCodecContext_AVCodecContext var1);

    @Const
    public native AVCodecDefault defaults();

    public native AVCodec defaults(AVCodecDefault var1);

    public native Init_static_data_AVCodec init_static_data();

    public native AVCodec init_static_data(Init_static_data_AVCodec var1);

    public native Init_AVCodecContext init();

    public native AVCodec init(Init_AVCodecContext var1);

    public native Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle encode_sub();

    public native AVCodec encode_sub(Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle var1);

    public native Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer encode2();

    public native AVCodec encode2(Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer var1);

    public native Decode_AVCodecContext_Pointer_IntPointer_AVPacket decode();

    public native AVCodec decode(Decode_AVCodecContext_Pointer_IntPointer_AVPacket var1);

    @Name(value={"close"})
    public native Close_AVCodecContext _close();

    public native AVCodec _close(Close_AVCodecContext var1);

    public native Receive_packet_AVCodecContext_AVPacket receive_packet();

    public native AVCodec receive_packet(Receive_packet_AVCodecContext_AVPacket var1);

    public native Receive_frame_AVCodecContext_AVFrame receive_frame();

    public native AVCodec receive_frame(Receive_frame_AVCodecContext_AVFrame var1);

    public native Flush_AVCodecContext flush();

    public native AVCodec flush(Flush_AVCodecContext var1);

    public native int caps_internal();

    public native AVCodec caps_internal(int var1);

    @Cast(value={"const char*"})
    public native BytePointer bsfs();

    public native AVCodec bsfs(BytePointer var1);

    @Cast(value={"const uint32_t*"})
    public native IntPointer codec_tags();

    public native AVCodec codec_tags(IntPointer var1);

    static {
        Loader.load();
    }

    public static class Flush_AVCodecContext
    extends FunctionPointer {
        public Flush_AVCodecContext(Pointer p2) {
            super(p2);
        }

        protected Flush_AVCodecContext() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVCodecContext var1);

        static {
            Loader.load();
        }
    }

    public static class Receive_frame_AVCodecContext_AVFrame
    extends FunctionPointer {
        public Receive_frame_AVCodecContext_AVFrame(Pointer p2) {
            super(p2);
        }

        protected Receive_frame_AVCodecContext_AVFrame() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, AVFrame var2);

        static {
            Loader.load();
        }
    }

    public static class Receive_packet_AVCodecContext_AVPacket
    extends FunctionPointer {
        public Receive_packet_AVCodecContext_AVPacket(Pointer p2) {
            super(p2);
        }

        protected Receive_packet_AVCodecContext_AVPacket() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, AVPacket var2);

        static {
            Loader.load();
        }
    }

    public static class Close_AVCodecContext
    extends FunctionPointer {
        public Close_AVCodecContext(Pointer p2) {
            super(p2);
        }

        protected Close_AVCodecContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1);

        static {
            Loader.load();
        }
    }

    public static class Decode_AVCodecContext_Pointer_IntPointer_AVPacket
    extends FunctionPointer {
        public Decode_AVCodecContext_Pointer_IntPointer_AVPacket(Pointer p2) {
            super(p2);
        }

        protected Decode_AVCodecContext_Pointer_IntPointer_AVPacket() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, Pointer var2, IntPointer var3, AVPacket var4);

        static {
            Loader.load();
        }
    }

    public static class Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer
    extends FunctionPointer {
        public Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer(Pointer p2) {
            super(p2);
        }

        protected Encode2_AVCodecContext_AVPacket_AVFrame_IntPointer() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, AVPacket var2, @Const AVFrame var3, IntPointer var4);

        static {
            Loader.load();
        }
    }

    public static class Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle
    extends FunctionPointer {
        public Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle(Pointer p2) {
            super(p2);
        }

        protected Encode_sub_AVCodecContext_BytePointer_int_AVSubtitle() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, @Cast(value={"uint8_t*"}) BytePointer var2, int var3, @Const AVSubtitle var4);

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

    public static class Init_static_data_AVCodec
    extends FunctionPointer {
        public Init_static_data_AVCodec(Pointer p2) {
            super(p2);
        }

        protected Init_static_data_AVCodec() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVCodec var1);

        static {
            Loader.load();
        }
    }

    public static class Update_thread_context_AVCodecContext_AVCodecContext
    extends FunctionPointer {
        public Update_thread_context_AVCodecContext_AVCodecContext(Pointer p2) {
            super(p2);
        }

        protected Update_thread_context_AVCodecContext_AVCodecContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, @Const AVCodecContext var2);

        static {
            Loader.load();
        }
    }
}

