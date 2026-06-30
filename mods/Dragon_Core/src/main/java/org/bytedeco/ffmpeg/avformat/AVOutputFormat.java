/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVCodecTag;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avformat.class})
public class AVOutputFormat
extends Pointer {
    public AVOutputFormat() {
        super((Pointer)null);
        this.allocate();
    }

    public AVOutputFormat(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVOutputFormat(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVOutputFormat position(long position) {
        return (AVOutputFormat)super.position(position);
    }

    public AVOutputFormat getPointer(long i2) {
        return (AVOutputFormat)new AVOutputFormat(this).offsetAddress(i2);
    }

    @Cast(value={"const char*"})
    public native BytePointer name();

    public native AVOutputFormat name(BytePointer var1);

    @Cast(value={"const char*"})
    public native BytePointer long_name();

    public native AVOutputFormat long_name(BytePointer var1);

    @Cast(value={"const char*"})
    public native BytePointer mime_type();

    public native AVOutputFormat mime_type(BytePointer var1);

    @Cast(value={"const char*"})
    public native BytePointer extensions();

    public native AVOutputFormat extensions(BytePointer var1);

    @Cast(value={"AVCodecID"})
    public native int audio_codec();

    public native AVOutputFormat audio_codec(int var1);

    @Cast(value={"AVCodecID"})
    public native int video_codec();

    public native AVOutputFormat video_codec(int var1);

    @Cast(value={"AVCodecID"})
    public native int subtitle_codec();

    public native AVOutputFormat subtitle_codec(int var1);

    public native int flags();

    public native AVOutputFormat flags(int var1);

    @MemberGetter
    @Const
    public native AVCodecTag codec_tag(int var1);

    @MemberGetter
    @Cast(value={"const AVCodecTag*const*"})
    public native PointerPointer codec_tag();

    @Const
    public native AVClass priv_class();

    public native AVOutputFormat priv_class(AVClass var1);

    public native AVOutputFormat next();

    public native AVOutputFormat next(AVOutputFormat var1);

    public native int priv_data_size();

    public native AVOutputFormat priv_data_size(int var1);

    public native Write_header_AVFormatContext write_header();

    public native AVOutputFormat write_header(Write_header_AVFormatContext var1);

    public native Write_packet_AVFormatContext_AVPacket write_packet();

    public native AVOutputFormat write_packet(Write_packet_AVFormatContext_AVPacket var1);

    public native Write_trailer_AVFormatContext write_trailer();

    public native AVOutputFormat write_trailer(Write_trailer_AVFormatContext var1);

    public native Interleave_packet_AVFormatContext_AVPacket_AVPacket_int interleave_packet();

    public native AVOutputFormat interleave_packet(Interleave_packet_AVFormatContext_AVPacket_AVPacket_int var1);

    public native Query_codec_int_int query_codec();

    public native AVOutputFormat query_codec(Query_codec_int_int var1);

    public native Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer get_output_timestamp();

    public native AVOutputFormat get_output_timestamp(Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer var1);

    public native Control_message_AVFormatContext_int_Pointer_long control_message();

    public native AVOutputFormat control_message(Control_message_AVFormatContext_int_Pointer_long var1);

    public native Write_uncoded_frame_AVFormatContext_int_PointerPointer_int write_uncoded_frame();

    public native AVOutputFormat write_uncoded_frame(Write_uncoded_frame_AVFormatContext_int_PointerPointer_int var1);

    public native Get_device_list_AVFormatContext_Pointer get_device_list();

    public native AVOutputFormat get_device_list(Get_device_list_AVFormatContext_Pointer var1);

    public native Create_device_capabilities_AVFormatContext_Pointer create_device_capabilities();

    public native AVOutputFormat create_device_capabilities(Create_device_capabilities_AVFormatContext_Pointer var1);

    public native Free_device_capabilities_AVFormatContext_Pointer free_device_capabilities();

    public native AVOutputFormat free_device_capabilities(Free_device_capabilities_AVFormatContext_Pointer var1);

    @Cast(value={"AVCodecID"})
    public native int data_codec();

    public native AVOutputFormat data_codec(int var1);

    public native Init_AVFormatContext init();

    public native AVOutputFormat init(Init_AVFormatContext var1);

    public native Deinit_AVFormatContext deinit();

    public native AVOutputFormat deinit(Deinit_AVFormatContext var1);

    public native Check_bitstream_AVFormatContext_AVPacket check_bitstream();

    public native AVOutputFormat check_bitstream(Check_bitstream_AVFormatContext_AVPacket var1);

    static {
        Loader.load();
    }

    public static class Check_bitstream_AVFormatContext_AVPacket
    extends FunctionPointer {
        public Check_bitstream_AVFormatContext_AVPacket(Pointer p2) {
            super(p2);
        }

        protected Check_bitstream_AVFormatContext_AVPacket() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, @Const AVPacket var2);

        static {
            Loader.load();
        }
    }

    public static class Deinit_AVFormatContext
    extends FunctionPointer {
        public Deinit_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Deinit_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }

    public static class Init_AVFormatContext
    extends FunctionPointer {
        public Init_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Init_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }

    public static class Free_device_capabilities_AVFormatContext_Pointer
    extends FunctionPointer {
        public Free_device_capabilities_AVFormatContext_Pointer(Pointer p2) {
            super(p2);
        }

        protected Free_device_capabilities_AVFormatContext_Pointer() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, @Cast(value={"AVDeviceCapabilitiesQuery*"}) Pointer var2);

        static {
            Loader.load();
        }
    }

    public static class Create_device_capabilities_AVFormatContext_Pointer
    extends FunctionPointer {
        public Create_device_capabilities_AVFormatContext_Pointer(Pointer p2) {
            super(p2);
        }

        protected Create_device_capabilities_AVFormatContext_Pointer() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, @Cast(value={"AVDeviceCapabilitiesQuery*"}) Pointer var2);

        static {
            Loader.load();
        }
    }

    public static class Get_device_list_AVFormatContext_Pointer
    extends FunctionPointer {
        public Get_device_list_AVFormatContext_Pointer(Pointer p2) {
            super(p2);
        }

        protected Get_device_list_AVFormatContext_Pointer() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, @Cast(value={"AVDeviceInfoList*"}) Pointer var2);

        static {
            Loader.load();
        }
    }

    public static class Write_uncoded_frame_AVFormatContext_int_PointerPointer_int
    extends FunctionPointer {
        public Write_uncoded_frame_AVFormatContext_int_PointerPointer_int(Pointer p2) {
            super(p2);
        }

        protected Write_uncoded_frame_AVFormatContext_int_PointerPointer_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, int var2, @Cast(value={"AVFrame**"}) PointerPointer var3, @Cast(value={"unsigned"}) int var4);

        static {
            Loader.load();
        }
    }

    public static class Control_message_AVFormatContext_int_Pointer_long
    extends FunctionPointer {
        public Control_message_AVFormatContext_int_Pointer_long(Pointer p2) {
            super(p2);
        }

        protected Control_message_AVFormatContext_int_Pointer_long() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, int var2, Pointer var3, @Cast(value={"size_t"}) long var4);

        static {
            Loader.load();
        }
    }

    public static class Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer
    extends FunctionPointer {
        public Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer(Pointer p2) {
            super(p2);
        }

        protected Get_output_timestamp_AVFormatContext_int_LongPointer_LongPointer() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVFormatContext var1, int var2, @Cast(value={"int64_t*"}) LongPointer var3, @Cast(value={"int64_t*"}) LongPointer var4);

        static {
            Loader.load();
        }
    }

    public static class Query_codec_int_int
    extends FunctionPointer {
        public Query_codec_int_int(Pointer p2) {
            super(p2);
        }

        protected Query_codec_int_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(@Cast(value={"AVCodecID"}) int var1, int var2);

        static {
            Loader.load();
        }
    }

    public static class Interleave_packet_AVFormatContext_AVPacket_AVPacket_int
    extends FunctionPointer {
        public Interleave_packet_AVFormatContext_AVPacket_AVPacket_int(Pointer p2) {
            super(p2);
        }

        protected Interleave_packet_AVFormatContext_AVPacket_AVPacket_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, AVPacket var2, AVPacket var3, int var4);

        static {
            Loader.load();
        }
    }

    public static class Write_trailer_AVFormatContext
    extends FunctionPointer {
        public Write_trailer_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Write_trailer_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }

    public static class Write_packet_AVFormatContext_AVPacket
    extends FunctionPointer {
        public Write_packet_AVFormatContext_AVPacket(Pointer p2) {
            super(p2);
        }

        protected Write_packet_AVFormatContext_AVPacket() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, AVPacket var2);

        static {
            Loader.load();
        }
    }

    public static class Write_header_AVFormatContext
    extends FunctionPointer {
        public Write_header_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Write_header_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }
}

