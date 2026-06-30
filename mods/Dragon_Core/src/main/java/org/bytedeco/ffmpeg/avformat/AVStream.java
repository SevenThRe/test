/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avcodec.AVCodecParserContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avcodec.AVPacketSideData;
import org.bytedeco.ffmpeg.avformat.AVIndexEntry;
import org.bytedeco.ffmpeg.avformat.AVProbeData;
import org.bytedeco.ffmpeg.avformat.AVStreamInternal;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avformat.class})
public class AVStream
extends Pointer {
    public static final int AVSTREAM_EVENT_FLAG_METADATA_UPDATED = 1;
    public static final int AVSTREAM_EVENT_FLAG_NEW_PACKETS = 2;

    public AVStream() {
        super((Pointer)null);
        this.allocate();
    }

    public AVStream(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVStream(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVStream position(long position) {
        return (AVStream)super.position(position);
    }

    public AVStream getPointer(long i2) {
        return (AVStream)new AVStream(this).offsetAddress(i2);
    }

    public native int index();

    public native AVStream index(int var1);

    public native int id();

    public native AVStream id(int var1);

    @Deprecated
    public native AVCodecContext codec();

    public native AVStream codec(AVCodecContext var1);

    public native Pointer priv_data();

    public native AVStream priv_data(Pointer var1);

    @ByRef
    public native AVRational time_base();

    public native AVStream time_base(AVRational var1);

    @Cast(value={"int64_t"})
    public native long start_time();

    public native AVStream start_time(long var1);

    @Cast(value={"int64_t"})
    public native long duration();

    public native AVStream duration(long var1);

    @Cast(value={"int64_t"})
    public native long nb_frames();

    public native AVStream nb_frames(long var1);

    public native int disposition();

    public native AVStream disposition(int var1);

    @Cast(value={"AVDiscard"})
    public native int discard();

    public native AVStream discard(int var1);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native AVStream sample_aspect_ratio(AVRational var1);

    public native AVDictionary metadata();

    public native AVStream metadata(AVDictionary var1);

    @ByRef
    public native AVRational avg_frame_rate();

    public native AVStream avg_frame_rate(AVRational var1);

    @ByRef
    public native AVPacket attached_pic();

    public native AVStream attached_pic(AVPacket var1);

    public native AVPacketSideData side_data();

    public native AVStream side_data(AVPacketSideData var1);

    public native int nb_side_data();

    public native AVStream nb_side_data(int var1);

    public native int event_flags();

    public native AVStream event_flags(int var1);

    @ByRef
    public native AVRational r_frame_rate();

    public native AVStream r_frame_rate(AVRational var1);

    @Cast(value={"char*"})
    @Deprecated
    public native BytePointer recommended_encoder_configuration();

    public native AVStream recommended_encoder_configuration(BytePointer var1);

    public native AVCodecParameters codecpar();

    public native AVStream codecpar(AVCodecParameters var1);

    public native Pointer unused();

    public native AVStream unused(Pointer var1);

    public native int pts_wrap_bits();

    public native AVStream pts_wrap_bits(int var1);

    @Cast(value={"int64_t"})
    public native long first_dts();

    public native AVStream first_dts(long var1);

    @Cast(value={"int64_t"})
    public native long cur_dts();

    public native AVStream cur_dts(long var1);

    @Cast(value={"int64_t"})
    public native long last_IP_pts();

    public native AVStream last_IP_pts(long var1);

    public native int last_IP_duration();

    public native AVStream last_IP_duration(int var1);

    public native int probe_packets();

    public native AVStream probe_packets(int var1);

    public native int codec_info_nb_frames();

    public native AVStream codec_info_nb_frames(int var1);

    @Cast(value={"AVStreamParseType"})
    public native int need_parsing();

    public native AVStream need_parsing(int var1);

    public native AVCodecParserContext parser();

    public native AVStream parser(AVCodecParserContext var1);

    public native Pointer unused7();

    public native AVStream unused7(Pointer var1);

    @ByRef
    public native AVProbeData unused6();

    public native AVStream unused6(AVProbeData var1);

    @Cast(value={"int64_t"})
    public native long unused5(int var1);

    public native AVStream unused5(int var1, long var2);

    @MemberGetter
    @Cast(value={"int64_t*"})
    public native LongPointer unused5();

    public native AVIndexEntry index_entries();

    public native AVStream index_entries(AVIndexEntry var1);

    public native int nb_index_entries();

    public native AVStream nb_index_entries(int var1);

    @Cast(value={"unsigned int"})
    public native int index_entries_allocated_size();

    public native AVStream index_entries_allocated_size(int var1);

    public native int stream_identifier();

    public native AVStream stream_identifier(int var1);

    public native int unused8();

    public native AVStream unused8(int var1);

    public native int unused9();

    public native AVStream unused9(int var1);

    public native int unused10();

    public native AVStream unused10(int var1);

    public native AVStreamInternal internal();

    public native AVStream internal(AVStreamInternal var1);

    static {
        Loader.load();
    }
}

