/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVCodecTag;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVProbeData;
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
public class AVInputFormat
extends Pointer {
    public AVInputFormat() {
        super((Pointer)null);
        this.allocate();
    }

    public AVInputFormat(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVInputFormat(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVInputFormat position(long position) {
        return (AVInputFormat)super.position(position);
    }

    public AVInputFormat getPointer(long i2) {
        return (AVInputFormat)new AVInputFormat(this).offsetAddress(i2);
    }

    @Cast(value={"const char*"})
    public native BytePointer name();

    public native AVInputFormat name(BytePointer var1);

    @Cast(value={"const char*"})
    public native BytePointer long_name();

    public native AVInputFormat long_name(BytePointer var1);

    public native int flags();

    public native AVInputFormat flags(int var1);

    @Cast(value={"const char*"})
    public native BytePointer extensions();

    public native AVInputFormat extensions(BytePointer var1);

    @MemberGetter
    @Const
    public native AVCodecTag codec_tag(int var1);

    @MemberGetter
    @Cast(value={"const AVCodecTag*const*"})
    public native PointerPointer codec_tag();

    @Const
    public native AVClass priv_class();

    public native AVInputFormat priv_class(AVClass var1);

    @Cast(value={"const char*"})
    public native BytePointer mime_type();

    public native AVInputFormat mime_type(BytePointer var1);

    public native AVInputFormat next();

    public native AVInputFormat next(AVInputFormat var1);

    public native int raw_codec_id();

    public native AVInputFormat raw_codec_id(int var1);

    public native int priv_data_size();

    public native AVInputFormat priv_data_size(int var1);

    public native Read_probe_AVProbeData read_probe();

    public native AVInputFormat read_probe(Read_probe_AVProbeData var1);

    public native Read_header_AVFormatContext read_header();

    public native AVInputFormat read_header(Read_header_AVFormatContext var1);

    public native Read_packet_AVFormatContext_AVPacket read_packet();

    public native AVInputFormat read_packet(Read_packet_AVFormatContext_AVPacket var1);

    public native Read_close_AVFormatContext read_close();

    public native AVInputFormat read_close(Read_close_AVFormatContext var1);

    public native Read_seek_AVFormatContext_int_long_int read_seek();

    public native AVInputFormat read_seek(Read_seek_AVFormatContext_int_long_int var1);

    public native Read_timestamp_AVFormatContext_int_LongPointer_long read_timestamp();

    public native AVInputFormat read_timestamp(Read_timestamp_AVFormatContext_int_LongPointer_long var1);

    public native Read_play_AVFormatContext read_play();

    public native AVInputFormat read_play(Read_play_AVFormatContext var1);

    public native Read_pause_AVFormatContext read_pause();

    public native AVInputFormat read_pause(Read_pause_AVFormatContext var1);

    public native Read_seek2_AVFormatContext_int_long_long_long_int read_seek2();

    public native AVInputFormat read_seek2(Read_seek2_AVFormatContext_int_long_long_long_int var1);

    public native Get_device_list_AVFormatContext_Pointer get_device_list();

    public native AVInputFormat get_device_list(Get_device_list_AVFormatContext_Pointer var1);

    public native Create_device_capabilities_AVFormatContext_Pointer create_device_capabilities();

    public native AVInputFormat create_device_capabilities(Create_device_capabilities_AVFormatContext_Pointer var1);

    public native Free_device_capabilities_AVFormatContext_Pointer free_device_capabilities();

    public native AVInputFormat free_device_capabilities(Free_device_capabilities_AVFormatContext_Pointer var1);

    static {
        Loader.load();
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

    public static class Read_seek2_AVFormatContext_int_long_long_long_int
    extends FunctionPointer {
        public Read_seek2_AVFormatContext_int_long_long_long_int(Pointer p2) {
            super(p2);
        }

        protected Read_seek2_AVFormatContext_int_long_long_long_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, int var2, @Cast(value={"int64_t"}) long var3, @Cast(value={"int64_t"}) long var5, @Cast(value={"int64_t"}) long var7, int var9);

        static {
            Loader.load();
        }
    }

    public static class Read_pause_AVFormatContext
    extends FunctionPointer {
        public Read_pause_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Read_pause_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }

    public static class Read_play_AVFormatContext
    extends FunctionPointer {
        public Read_play_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Read_play_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }

    public static class Read_timestamp_AVFormatContext_int_LongPointer_long
    extends FunctionPointer {
        public Read_timestamp_AVFormatContext_int_LongPointer_long(Pointer p2) {
            super(p2);
        }

        protected Read_timestamp_AVFormatContext_int_LongPointer_long() {
            this.allocate();
        }

        private native void allocate();

        @Cast(value={"int64_t"})
        public native long call(AVFormatContext var1, int var2, @Cast(value={"int64_t*"}) LongPointer var3, @Cast(value={"int64_t"}) long var4);

        static {
            Loader.load();
        }
    }

    public static class Read_seek_AVFormatContext_int_long_int
    extends FunctionPointer {
        public Read_seek_AVFormatContext_int_long_int(Pointer p2) {
            super(p2);
        }

        protected Read_seek_AVFormatContext_int_long_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, int var2, @Cast(value={"int64_t"}) long var3, int var5);

        static {
            Loader.load();
        }
    }

    public static class Read_close_AVFormatContext
    extends FunctionPointer {
        public Read_close_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Read_close_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }

    public static class Read_packet_AVFormatContext_AVPacket
    extends FunctionPointer {
        public Read_packet_AVFormatContext_AVPacket(Pointer p2) {
            super(p2);
        }

        protected Read_packet_AVFormatContext_AVPacket() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1, AVPacket var2);

        static {
            Loader.load();
        }
    }

    public static class Read_header_AVFormatContext
    extends FunctionPointer {
        public Read_header_AVFormatContext(Pointer p2) {
            super(p2);
        }

        protected Read_header_AVFormatContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFormatContext var1);

        static {
            Loader.load();
        }
    }

    public static class Read_probe_AVProbeData
    extends FunctionPointer {
        public Read_probe_AVProbeData(Pointer p2) {
            super(p2);
        }

        protected Read_probe_AVProbeData() {
            this.allocate();
        }

        private native void allocate();

        public native int call(@Const AVProbeData var1);

        static {
            Loader.load();
        }
    }
}

