/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.LongBuffer;
import org.bytedeco.ffmpeg.avcodec.AVBitStreamFilterContext;
import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVCodecParserContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avformat.AVCodecTag;
import org.bytedeco.ffmpeg.avformat.AVFormatContext;
import org.bytedeco.ffmpeg.avformat.AVIOContext;
import org.bytedeco.ffmpeg.avformat.AVIODirContext;
import org.bytedeco.ffmpeg.avformat.AVIODirEntry;
import org.bytedeco.ffmpeg.avformat.AVIOInterruptCB;
import org.bytedeco.ffmpeg.avformat.AVInputFormat;
import org.bytedeco.ffmpeg.avformat.AVOpenCallback;
import org.bytedeco.ffmpeg.avformat.AVOutputFormat;
import org.bytedeco.ffmpeg.avformat.AVProbeData;
import org.bytedeco.ffmpeg.avformat.AVProgram;
import org.bytedeco.ffmpeg.avformat.AVStream;
import org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_ByteBuffer_int;
import org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_BytePointer_int;
import org.bytedeco.ffmpeg.avformat.Read_packet_Pointer_byte___int;
import org.bytedeco.ffmpeg.avformat.Seek_Pointer_long_int;
import org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_ByteBuffer_int;
import org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_BytePointer_int;
import org.bytedeco.ffmpeg.avformat.Write_packet_Pointer_byte___int;
import org.bytedeco.ffmpeg.avformat.av_format_control_message;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.NoException;

public class avformat
extends org.bytedeco.ffmpeg.presets.avformat {
    public static final int AVIO_SEEKABLE_NORMAL = 1;
    public static final int AVIO_SEEKABLE_TIME = 2;
    public static final int AVIO_ENTRY_UNKNOWN = 0;
    public static final int AVIO_ENTRY_BLOCK_DEVICE = 1;
    public static final int AVIO_ENTRY_CHARACTER_DEVICE = 2;
    public static final int AVIO_ENTRY_DIRECTORY = 3;
    public static final int AVIO_ENTRY_NAMED_PIPE = 4;
    public static final int AVIO_ENTRY_SYMBOLIC_LINK = 5;
    public static final int AVIO_ENTRY_SOCKET = 6;
    public static final int AVIO_ENTRY_FILE = 7;
    public static final int AVIO_ENTRY_SERVER = 8;
    public static final int AVIO_ENTRY_SHARE = 9;
    public static final int AVIO_ENTRY_WORKGROUP = 10;
    public static final int AVIO_DATA_MARKER_HEADER = 0;
    public static final int AVIO_DATA_MARKER_SYNC_POINT = 1;
    public static final int AVIO_DATA_MARKER_BOUNDARY_POINT = 2;
    public static final int AVIO_DATA_MARKER_UNKNOWN = 3;
    public static final int AVIO_DATA_MARKER_TRAILER = 4;
    public static final int AVIO_DATA_MARKER_FLUSH_POINT = 5;
    public static final int AVSEEK_SIZE = 65536;
    public static final int AVSEEK_FORCE = 131072;
    public static final int AVIO_FLAG_READ = 1;
    public static final int AVIO_FLAG_WRITE = 2;
    public static final int AVIO_FLAG_READ_WRITE = 3;
    public static final int AVIO_FLAG_NONBLOCK = 8;
    public static final int AVIO_FLAG_DIRECT = 32768;
    public static final int AVPROBE_SCORE_RETRY;
    public static final int AVPROBE_SCORE_STREAM_RETRY;
    public static final int AVPROBE_SCORE_EXTENSION = 50;
    public static final int AVPROBE_SCORE_MIME = 75;
    public static final int AVPROBE_SCORE_MAX = 100;
    public static final int AVPROBE_PADDING_SIZE = 32;
    public static final int AVFMT_NOFILE = 1;
    public static final int AVFMT_NEEDNUMBER = 2;
    public static final int AVFMT_SHOW_IDS = 8;
    public static final int AVFMT_GLOBALHEADER = 64;
    public static final int AVFMT_NOTIMESTAMPS = 128;
    public static final int AVFMT_GENERIC_INDEX = 256;
    public static final int AVFMT_TS_DISCONT = 512;
    public static final int AVFMT_VARIABLE_FPS = 1024;
    public static final int AVFMT_NODIMENSIONS = 2048;
    public static final int AVFMT_NOSTREAMS = 4096;
    public static final int AVFMT_NOBINSEARCH = 8192;
    public static final int AVFMT_NOGENSEARCH = 16384;
    public static final int AVFMT_NO_BYTE_SEEK = 32768;
    public static final int AVFMT_ALLOW_FLUSH = 65536;
    public static final int AVFMT_TS_NONSTRICT = 131072;
    public static final int AVFMT_TS_NEGATIVE = 262144;
    public static final int AVFMT_SEEK_TO_PTS = 0x4000000;
    public static final int AVSTREAM_PARSE_NONE = 0;
    public static final int AVSTREAM_PARSE_FULL = 1;
    public static final int AVSTREAM_PARSE_HEADERS = 2;
    public static final int AVSTREAM_PARSE_TIMESTAMPS = 3;
    public static final int AVSTREAM_PARSE_FULL_ONCE = 4;
    public static final int AVSTREAM_PARSE_FULL_RAW = 5;
    public static final int AV_DISPOSITION_DEFAULT = 1;
    public static final int AV_DISPOSITION_DUB = 2;
    public static final int AV_DISPOSITION_ORIGINAL = 4;
    public static final int AV_DISPOSITION_COMMENT = 8;
    public static final int AV_DISPOSITION_LYRICS = 16;
    public static final int AV_DISPOSITION_KARAOKE = 32;
    public static final int AV_DISPOSITION_FORCED = 64;
    public static final int AV_DISPOSITION_HEARING_IMPAIRED = 128;
    public static final int AV_DISPOSITION_VISUAL_IMPAIRED = 256;
    public static final int AV_DISPOSITION_CLEAN_EFFECTS = 512;
    public static final int AV_DISPOSITION_ATTACHED_PIC = 1024;
    public static final int AV_DISPOSITION_TIMED_THUMBNAILS = 2048;
    public static final int AV_DISPOSITION_CAPTIONS = 65536;
    public static final int AV_DISPOSITION_DESCRIPTIONS = 131072;
    public static final int AV_DISPOSITION_METADATA = 262144;
    public static final int AV_DISPOSITION_DEPENDENT = 524288;
    public static final int AV_DISPOSITION_STILL_IMAGE = 0x100000;
    public static final int AV_PTS_WRAP_IGNORE = 0;
    public static final int AV_PTS_WRAP_ADD_OFFSET = 1;
    public static final int AV_PTS_WRAP_SUB_OFFSET = -1;
    public static final int AV_PROGRAM_RUNNING = 1;
    public static final int AVFMTCTX_NOHEADER = 1;
    public static final int AVFMTCTX_UNSEEKABLE = 2;
    public static final int AVFMT_DURATION_FROM_PTS = 0;
    public static final int AVFMT_DURATION_FROM_STREAM = 1;
    public static final int AVFMT_DURATION_FROM_BITRATE = 2;
    public static final int AVSEEK_FLAG_BACKWARD = 1;
    public static final int AVSEEK_FLAG_BYTE = 2;
    public static final int AVSEEK_FLAG_ANY = 4;
    public static final int AVSEEK_FLAG_FRAME = 8;
    public static final int AVSTREAM_INIT_IN_WRITE_HEADER = 0;
    public static final int AVSTREAM_INIT_IN_INIT_OUTPUT = 1;
    public static final int AV_FRAME_FILENAME_FLAGS_MULTIPLE = 1;
    public static final int AVFMT_TBCF_AUTO = -1;
    public static final int AVFMT_TBCF_DECODER = 0;
    public static final int AVFMT_TBCF_DEMUXER = 1;
    public static final int AVFMT_TBCF_R_FRAMERATE = 2;

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avio_find_protocol_name(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    public static native String avio_find_protocol_name(String var0);

    @NoException
    public static native int avio_check(@Cast(value={"const char*"}) BytePointer var0, int var1);

    @NoException
    public static native int avio_check(String var0, int var1);

    @NoException
    public static native int avpriv_io_move(@Cast(value={"const char*"}) BytePointer var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native int avpriv_io_move(String var0, String var1);

    @NoException
    public static native int avpriv_io_delete(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    public static native int avpriv_io_delete(String var0);

    @NoException
    public static native int avio_open_dir(@Cast(value={"AVIODirContext**"}) PointerPointer var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"AVDictionary**"}) PointerPointer var2);

    @NoException
    public static native int avio_open_dir(@ByPtrPtr AVIODirContext var0, @Cast(value={"const char*"}) BytePointer var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native int avio_open_dir(@ByPtrPtr AVIODirContext var0, String var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native int avio_read_dir(AVIODirContext var0, @Cast(value={"AVIODirEntry**"}) PointerPointer var1);

    @NoException
    public static native int avio_read_dir(AVIODirContext var0, @ByPtrPtr AVIODirEntry var1);

    @NoException
    public static native int avio_close_dir(@Cast(value={"AVIODirContext**"}) PointerPointer var0);

    @NoException
    public static native int avio_close_dir(@ByPtrPtr AVIODirContext var0);

    @NoException
    public static native void avio_free_directory_entry(@Cast(value={"AVIODirEntry**"}) PointerPointer var0);

    @NoException
    public static native void avio_free_directory_entry(@ByPtrPtr AVIODirEntry var0);

    @NoException
    public static native AVIOContext avio_alloc_context(@Cast(value={"unsigned char*"}) BytePointer var0, int var1, int var2, Pointer var3, Read_packet_Pointer_BytePointer_int var4, Write_packet_Pointer_BytePointer_int var5, Seek_Pointer_long_int var6);

    @NoException
    public static native AVIOContext avio_alloc_context(@Cast(value={"unsigned char*"}) ByteBuffer var0, int var1, int var2, Pointer var3, Read_packet_Pointer_ByteBuffer_int var4, Write_packet_Pointer_ByteBuffer_int var5, Seek_Pointer_long_int var6);

    @NoException
    public static native AVIOContext avio_alloc_context(@Cast(value={"unsigned char*"}) byte[] var0, int var1, int var2, Pointer var3, Read_packet_Pointer_byte___int var4, Write_packet_Pointer_byte___int var5, Seek_Pointer_long_int var6);

    @NoException
    public static native void avio_context_free(@Cast(value={"AVIOContext**"}) PointerPointer var0);

    @NoException
    public static native void avio_context_free(@ByPtrPtr AVIOContext var0);

    @NoException
    public static native void avio_w8(AVIOContext var0, int var1);

    @NoException
    public static native void avio_write(AVIOContext var0, @Cast(value={"const unsigned char*"}) BytePointer var1, int var2);

    @NoException
    public static native void avio_write(AVIOContext var0, @Cast(value={"const unsigned char*"}) ByteBuffer var1, int var2);

    @NoException
    public static native void avio_write(AVIOContext var0, @Cast(value={"const unsigned char*"}) byte[] var1, int var2);

    @NoException
    public static native void avio_wl64(AVIOContext var0, @Cast(value={"uint64_t"}) long var1);

    @NoException
    public static native void avio_wb64(AVIOContext var0, @Cast(value={"uint64_t"}) long var1);

    @NoException
    public static native void avio_wl32(AVIOContext var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    public static native void avio_wb32(AVIOContext var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    public static native void avio_wl24(AVIOContext var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    public static native void avio_wb24(AVIOContext var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    public static native void avio_wl16(AVIOContext var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    public static native void avio_wb16(AVIOContext var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    public static native int avio_put_str(AVIOContext var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native int avio_put_str(AVIOContext var0, String var1);

    @NoException
    public static native int avio_put_str16le(AVIOContext var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native int avio_put_str16le(AVIOContext var0, String var1);

    @NoException
    public static native int avio_put_str16be(AVIOContext var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native int avio_put_str16be(AVIOContext var0, String var1);

    @NoException
    public static native void avio_write_marker(AVIOContext var0, @Cast(value={"int64_t"}) long var1, @Cast(value={"AVIODataMarkerType"}) int var3);

    @NoException
    @Cast(value={"int64_t"})
    public static native long avio_seek(AVIOContext var0, @Cast(value={"int64_t"}) long var1, int var3);

    @NoException
    @Cast(value={"int64_t"})
    public static native long avio_skip(AVIOContext var0, @Cast(value={"int64_t"}) long var1);

    @NoException
    @Cast(value={"int64_t"})
    public static native long avio_tell(AVIOContext var0);

    @NoException
    @Cast(value={"int64_t"})
    public static native long avio_size(AVIOContext var0);

    @NoException
    public static native int avio_feof(AVIOContext var0);

    @NoException
    public static native int avio_printf(AVIOContext var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native int avio_printf(AVIOContext var0, String var1);

    @NoException
    public static native void avio_print_string_array(AVIOContext var0, @Cast(value={"const char**"}) PointerPointer var1);

    @NoException
    public static native void avio_print_string_array(AVIOContext var0, @Cast(value={"const char**"}) @ByPtrPtr BytePointer var1);

    @NoException
    public static native void avio_print_string_array(AVIOContext var0, @Cast(value={"const char**"}) @ByPtrPtr ByteBuffer var1);

    @NoException
    public static native void avio_print_string_array(AVIOContext var0, @Cast(value={"const char**"}) @ByPtrPtr byte[] var1);

    @NoException
    public static native void avio_flush(AVIOContext var0);

    @NoException
    public static native int avio_read(AVIOContext var0, @Cast(value={"unsigned char*"}) BytePointer var1, int var2);

    @NoException
    public static native int avio_read(AVIOContext var0, @Cast(value={"unsigned char*"}) ByteBuffer var1, int var2);

    @NoException
    public static native int avio_read(AVIOContext var0, @Cast(value={"unsigned char*"}) byte[] var1, int var2);

    @NoException
    public static native int avio_read_partial(AVIOContext var0, @Cast(value={"unsigned char*"}) BytePointer var1, int var2);

    @NoException
    public static native int avio_read_partial(AVIOContext var0, @Cast(value={"unsigned char*"}) ByteBuffer var1, int var2);

    @NoException
    public static native int avio_read_partial(AVIOContext var0, @Cast(value={"unsigned char*"}) byte[] var1, int var2);

    @NoException
    public static native int avio_r8(AVIOContext var0);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int avio_rl16(AVIOContext var0);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int avio_rl24(AVIOContext var0);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int avio_rl32(AVIOContext var0);

    @NoException
    @Cast(value={"uint64_t"})
    public static native long avio_rl64(AVIOContext var0);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int avio_rb16(AVIOContext var0);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int avio_rb24(AVIOContext var0);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int avio_rb32(AVIOContext var0);

    @NoException
    @Cast(value={"uint64_t"})
    public static native long avio_rb64(AVIOContext var0);

    @NoException
    public static native int avio_get_str(AVIOContext var0, int var1, @Cast(value={"char*"}) BytePointer var2, int var3);

    @NoException
    public static native int avio_get_str(AVIOContext var0, int var1, @Cast(value={"char*"}) ByteBuffer var2, int var3);

    @NoException
    public static native int avio_get_str(AVIOContext var0, int var1, @Cast(value={"char*"}) byte[] var2, int var3);

    @NoException
    public static native int avio_get_str16le(AVIOContext var0, int var1, @Cast(value={"char*"}) BytePointer var2, int var3);

    @NoException
    public static native int avio_get_str16le(AVIOContext var0, int var1, @Cast(value={"char*"}) ByteBuffer var2, int var3);

    @NoException
    public static native int avio_get_str16le(AVIOContext var0, int var1, @Cast(value={"char*"}) byte[] var2, int var3);

    @NoException
    public static native int avio_get_str16be(AVIOContext var0, int var1, @Cast(value={"char*"}) BytePointer var2, int var3);

    @NoException
    public static native int avio_get_str16be(AVIOContext var0, int var1, @Cast(value={"char*"}) ByteBuffer var2, int var3);

    @NoException
    public static native int avio_get_str16be(AVIOContext var0, int var1, @Cast(value={"char*"}) byte[] var2, int var3);

    @NoException
    public static native int avio_open(@Cast(value={"AVIOContext**"}) PointerPointer var0, @Cast(value={"const char*"}) BytePointer var1, int var2);

    @NoException
    public static native int avio_open(@ByPtrPtr AVIOContext var0, @Cast(value={"const char*"}) BytePointer var1, int var2);

    @NoException
    public static native int avio_open(@ByPtrPtr AVIOContext var0, String var1, int var2);

    @NoException
    public static native int avio_open2(@Cast(value={"AVIOContext**"}) PointerPointer var0, @Cast(value={"const char*"}) BytePointer var1, int var2, @Const AVIOInterruptCB var3, @Cast(value={"AVDictionary**"}) PointerPointer var4);

    @NoException
    public static native int avio_open2(@ByPtrPtr AVIOContext var0, @Cast(value={"const char*"}) BytePointer var1, int var2, @Const AVIOInterruptCB var3, @ByPtrPtr AVDictionary var4);

    @NoException
    public static native int avio_open2(@ByPtrPtr AVIOContext var0, String var1, int var2, @Const AVIOInterruptCB var3, @ByPtrPtr AVDictionary var4);

    @NoException
    public static native int avio_close(AVIOContext var0);

    @NoException
    public static native int avio_closep(@Cast(value={"AVIOContext**"}) PointerPointer var0);

    @NoException
    public static native int avio_closep(@ByPtrPtr AVIOContext var0);

    @NoException
    public static native int avio_open_dyn_buf(@Cast(value={"AVIOContext**"}) PointerPointer var0);

    @NoException
    public static native int avio_open_dyn_buf(@ByPtrPtr AVIOContext var0);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) PointerPointer var1);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var1);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var1);

    @NoException
    public static native int avio_get_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var1);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) PointerPointer var1);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var1);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var1);

    @NoException
    public static native int avio_close_dyn_buf(AVIOContext var0, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var1);

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avio_enum_protocols(@Cast(value={"void**"}) PointerPointer var0, int var1);

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avio_enum_protocols(@Cast(value={"void**"}) @ByPtrPtr Pointer var0, int var1);

    @NoException
    @Const
    public static native AVClass avio_protocol_get_class(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    @Const
    public static native AVClass avio_protocol_get_class(String var0);

    @NoException
    public static native int avio_pause(AVIOContext var0, int var1);

    @NoException
    @Cast(value={"int64_t"})
    public static native long avio_seek_time(AVIOContext var0, int var1, @Cast(value={"int64_t"}) long var2, int var4);

    @NoException
    public static native int avio_read_to_bprint(AVIOContext var0, @Cast(value={"AVBPrint*"}) Pointer var1, @Cast(value={"size_t"}) long var2);

    @NoException
    public static native int avio_accept(AVIOContext var0, @Cast(value={"AVIOContext**"}) PointerPointer var1);

    @NoException
    public static native int avio_accept(AVIOContext var0, @ByPtrPtr AVIOContext var1);

    @NoException
    public static native int avio_handshake(AVIOContext var0);

    @NoException
    public static native int av_get_packet(AVIOContext var0, AVPacket var1, int var2);

    @NoException
    public static native int av_append_packet(AVIOContext var0, AVPacket var1, int var2);

    @MemberGetter
    public static native int AVPROBE_SCORE_RETRY();

    @MemberGetter
    public static native int AVPROBE_SCORE_STREAM_RETRY();

    @NoException
    @Deprecated
    @ByVal
    public static native AVRational av_stream_get_r_frame_rate(@Const AVStream var0);

    @NoException
    @Deprecated
    public static native void av_stream_set_r_frame_rate(AVStream var0, @ByVal AVRational var1);

    @NoException
    @Cast(value={"char*"})
    @Deprecated
    public static native BytePointer av_stream_get_recommended_encoder_configuration(@Const AVStream var0);

    @NoException
    @Deprecated
    public static native void av_stream_set_recommended_encoder_configuration(AVStream var0, @Cast(value={"char*"}) BytePointer var1);

    @NoException
    @Deprecated
    public static native void av_stream_set_recommended_encoder_configuration(AVStream var0, @Cast(value={"char*"}) ByteBuffer var1);

    @NoException
    @Deprecated
    public static native void av_stream_set_recommended_encoder_configuration(AVStream var0, @Cast(value={"char*"}) byte[] var1);

    @NoException
    public static native AVCodecParserContext av_stream_get_parser(@Const AVStream var0);

    @NoException
    @Cast(value={"int64_t"})
    public static native long av_stream_get_end_pts(@Const AVStream var0);

    @NoException
    @Deprecated
    public static native int av_format_get_probe_score(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_video_codec(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_video_codec(AVFormatContext var0, AVCodec var1);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_audio_codec(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_audio_codec(AVFormatContext var0, AVCodec var1);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_subtitle_codec(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_subtitle_codec(AVFormatContext var0, AVCodec var1);

    @NoException
    @Deprecated
    public static native AVCodec av_format_get_data_codec(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_data_codec(AVFormatContext var0, AVCodec var1);

    @NoException
    @Deprecated
    public static native int av_format_get_metadata_header_padding(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_metadata_header_padding(AVFormatContext var0, int var1);

    @NoException
    @Deprecated
    public static native Pointer av_format_get_opaque(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_opaque(AVFormatContext var0, Pointer var1);

    @NoException
    @Deprecated
    public static native av_format_control_message av_format_get_control_message_cb(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_control_message_cb(AVFormatContext var0, av_format_control_message var1);

    @NoException
    @Deprecated
    public static native AVOpenCallback av_format_get_open_cb(@Const AVFormatContext var0);

    @NoException
    @Deprecated
    public static native void av_format_set_open_cb(AVFormatContext var0, AVOpenCallback var1);

    @NoException
    public static native void av_format_inject_global_side_data(AVFormatContext var0);

    @NoException
    @Cast(value={"AVDurationEstimationMethod"})
    public static native int av_fmt_ctx_get_duration_estimation_method(@Const AVFormatContext var0);

    @NoException
    @Cast(value={"unsigned"})
    public static native int avformat_version();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avformat_configuration();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avformat_license();

    @NoException
    @Deprecated
    public static native void av_register_all();

    @NoException
    @Deprecated
    public static native void av_register_input_format(AVInputFormat var0);

    @NoException
    @Deprecated
    public static native void av_register_output_format(AVOutputFormat var0);

    @NoException
    public static native int avformat_network_init();

    @NoException
    public static native int avformat_network_deinit();

    @NoException
    @Deprecated
    public static native AVInputFormat av_iformat_next(@Const AVInputFormat var0);

    @NoException
    @Deprecated
    public static native AVOutputFormat av_oformat_next(@Const AVOutputFormat var0);

    @NoException
    @Const
    public static native AVOutputFormat av_muxer_iterate(@Cast(value={"void**"}) PointerPointer var0);

    @NoException
    @Const
    public static native AVOutputFormat av_muxer_iterate(@Cast(value={"void**"}) @ByPtrPtr Pointer var0);

    @NoException
    @Const
    public static native AVInputFormat av_demuxer_iterate(@Cast(value={"void**"}) PointerPointer var0);

    @NoException
    @Const
    public static native AVInputFormat av_demuxer_iterate(@Cast(value={"void**"}) @ByPtrPtr Pointer var0);

    @NoException
    public static native AVFormatContext avformat_alloc_context();

    @NoException
    public static native void avformat_free_context(AVFormatContext var0);

    @NoException
    @Const
    public static native AVClass avformat_get_class();

    @NoException
    public static native AVStream avformat_new_stream(AVFormatContext var0, @Const AVCodec var1);

    @NoException
    public static native int av_stream_add_side_data(AVStream var0, @Cast(value={"AVPacketSideDataType"}) int var1, @Cast(value={"uint8_t*"}) BytePointer var2, @Cast(value={"size_t"}) long var3);

    @NoException
    public static native int av_stream_add_side_data(AVStream var0, @Cast(value={"AVPacketSideDataType"}) int var1, @Cast(value={"uint8_t*"}) ByteBuffer var2, @Cast(value={"size_t"}) long var3);

    @NoException
    public static native int av_stream_add_side_data(AVStream var0, @Cast(value={"AVPacketSideDataType"}) int var1, @Cast(value={"uint8_t*"}) byte[] var2, @Cast(value={"size_t"}) long var3);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native BytePointer av_stream_new_side_data(AVStream var0, @Cast(value={"AVPacketSideDataType"}) int var1, int var2);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native BytePointer av_stream_get_side_data(@Const AVStream var0, @Cast(value={"AVPacketSideDataType"}) int var1, IntPointer var2);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native ByteBuffer av_stream_get_side_data(@Const AVStream var0, @Cast(value={"AVPacketSideDataType"}) int var1, IntBuffer var2);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native byte[] av_stream_get_side_data(@Const AVStream var0, @Cast(value={"AVPacketSideDataType"}) int var1, int[] var2);

    @NoException
    public static native AVProgram av_new_program(AVFormatContext var0, int var1);

    @NoException
    public static native int avformat_alloc_output_context2(@Cast(value={"AVFormatContext**"}) PointerPointer var0, AVOutputFormat var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3);

    @NoException
    public static native int avformat_alloc_output_context2(@ByPtrPtr AVFormatContext var0, AVOutputFormat var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3);

    @NoException
    public static native int avformat_alloc_output_context2(@ByPtrPtr AVFormatContext var0, AVOutputFormat var1, String var2, String var3);

    @NoException
    public static native AVInputFormat av_find_input_format(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    public static native AVInputFormat av_find_input_format(String var0);

    @NoException
    public static native AVInputFormat av_probe_input_format(AVProbeData var0, int var1);

    @NoException
    public static native AVInputFormat av_probe_input_format2(AVProbeData var0, int var1, IntPointer var2);

    @NoException
    public static native AVInputFormat av_probe_input_format2(AVProbeData var0, int var1, IntBuffer var2);

    @NoException
    public static native AVInputFormat av_probe_input_format2(AVProbeData var0, int var1, int[] var2);

    @NoException
    public static native AVInputFormat av_probe_input_format3(AVProbeData var0, int var1, IntPointer var2);

    @NoException
    public static native AVInputFormat av_probe_input_format3(AVProbeData var0, int var1, IntBuffer var2);

    @NoException
    public static native AVInputFormat av_probe_input_format3(AVProbeData var0, int var1, int[] var2);

    @NoException
    public static native int av_probe_input_buffer2(AVIOContext var0, @Cast(value={"AVInputFormat**"}) PointerPointer var1, @Cast(value={"const char*"}) BytePointer var2, Pointer var3, @Cast(value={"unsigned int"}) int var4, @Cast(value={"unsigned int"}) int var5);

    @NoException
    public static native int av_probe_input_buffer2(AVIOContext var0, @ByPtrPtr AVInputFormat var1, @Cast(value={"const char*"}) BytePointer var2, Pointer var3, @Cast(value={"unsigned int"}) int var4, @Cast(value={"unsigned int"}) int var5);

    @NoException
    public static native int av_probe_input_buffer2(AVIOContext var0, @ByPtrPtr AVInputFormat var1, String var2, Pointer var3, @Cast(value={"unsigned int"}) int var4, @Cast(value={"unsigned int"}) int var5);

    @NoException
    public static native int av_probe_input_buffer(AVIOContext var0, @Cast(value={"AVInputFormat**"}) PointerPointer var1, @Cast(value={"const char*"}) BytePointer var2, Pointer var3, @Cast(value={"unsigned int"}) int var4, @Cast(value={"unsigned int"}) int var5);

    @NoException
    public static native int av_probe_input_buffer(AVIOContext var0, @ByPtrPtr AVInputFormat var1, @Cast(value={"const char*"}) BytePointer var2, Pointer var3, @Cast(value={"unsigned int"}) int var4, @Cast(value={"unsigned int"}) int var5);

    @NoException
    public static native int av_probe_input_buffer(AVIOContext var0, @ByPtrPtr AVInputFormat var1, String var2, Pointer var3, @Cast(value={"unsigned int"}) int var4, @Cast(value={"unsigned int"}) int var5);

    @NoException
    public static native int avformat_open_input(@Cast(value={"AVFormatContext**"}) PointerPointer var0, @Cast(value={"const char*"}) BytePointer var1, AVInputFormat var2, @Cast(value={"AVDictionary**"}) PointerPointer var3);

    @NoException
    public static native int avformat_open_input(@ByPtrPtr AVFormatContext var0, @Cast(value={"const char*"}) BytePointer var1, AVInputFormat var2, @ByPtrPtr AVDictionary var3);

    @NoException
    public static native int avformat_open_input(@ByPtrPtr AVFormatContext var0, String var1, AVInputFormat var2, @ByPtrPtr AVDictionary var3);

    @NoException
    @Deprecated
    public static native int av_demuxer_open(AVFormatContext var0);

    @NoException
    public static native int avformat_find_stream_info(AVFormatContext var0, @Cast(value={"AVDictionary**"}) PointerPointer var1);

    @NoException
    public static native int avformat_find_stream_info(AVFormatContext var0, @ByPtrPtr AVDictionary var1);

    @NoException
    public static native AVProgram av_find_program_from_stream(AVFormatContext var0, AVProgram var1, int var2);

    @NoException
    public static native void av_program_add_stream_index(AVFormatContext var0, int var1, @Cast(value={"unsigned int"}) int var2);

    @NoException
    public static native int av_find_best_stream(AVFormatContext var0, @Cast(value={"AVMediaType"}) int var1, int var2, int var3, @Cast(value={"AVCodec**"}) PointerPointer var4, int var5);

    @NoException
    public static native int av_find_best_stream(AVFormatContext var0, @Cast(value={"AVMediaType"}) int var1, int var2, int var3, @ByPtrPtr AVCodec var4, int var5);

    @NoException
    public static native int av_read_frame(AVFormatContext var0, AVPacket var1);

    @NoException
    public static native int av_seek_frame(AVFormatContext var0, int var1, @Cast(value={"int64_t"}) long var2, int var4);

    @NoException
    public static native int avformat_seek_file(AVFormatContext var0, int var1, @Cast(value={"int64_t"}) long var2, @Cast(value={"int64_t"}) long var4, @Cast(value={"int64_t"}) long var6, int var8);

    @NoException
    public static native int avformat_flush(AVFormatContext var0);

    @NoException
    public static native int av_read_play(AVFormatContext var0);

    @NoException
    public static native int av_read_pause(AVFormatContext var0);

    @NoException
    public static native void avformat_close_input(@Cast(value={"AVFormatContext**"}) PointerPointer var0);

    @NoException
    public static native void avformat_close_input(@ByPtrPtr AVFormatContext var0);

    @NoException
    public static native int avformat_write_header(AVFormatContext var0, @Cast(value={"AVDictionary**"}) PointerPointer var1);

    @NoException
    public static native int avformat_write_header(AVFormatContext var0, @ByPtrPtr AVDictionary var1);

    @NoException
    public static native int avformat_init_output(AVFormatContext var0, @Cast(value={"AVDictionary**"}) PointerPointer var1);

    @NoException
    public static native int avformat_init_output(AVFormatContext var0, @ByPtrPtr AVDictionary var1);

    @NoException
    public static native int av_write_frame(AVFormatContext var0, AVPacket var1);

    @NoException
    public static native int av_interleaved_write_frame(AVFormatContext var0, AVPacket var1);

    @NoException
    public static native int av_write_uncoded_frame(AVFormatContext var0, int var1, AVFrame var2);

    @NoException
    public static native int av_interleaved_write_uncoded_frame(AVFormatContext var0, int var1, AVFrame var2);

    @NoException
    public static native int av_write_uncoded_frame_query(AVFormatContext var0, int var1);

    @NoException
    public static native int av_write_trailer(AVFormatContext var0);

    @NoException
    public static native AVOutputFormat av_guess_format(@Cast(value={"const char*"}) BytePointer var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2);

    @NoException
    public static native AVOutputFormat av_guess_format(String var0, String var1, String var2);

    @NoException
    @Cast(value={"AVCodecID"})
    public static native int av_guess_codec(AVOutputFormat var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, @Cast(value={"AVMediaType"}) int var4);

    @NoException
    @Cast(value={"AVCodecID"})
    public static native int av_guess_codec(AVOutputFormat var0, String var1, String var2, String var3, @Cast(value={"AVMediaType"}) int var4);

    @NoException
    public static native int av_get_output_timestamp(AVFormatContext var0, int var1, @Cast(value={"int64_t*"}) LongPointer var2, @Cast(value={"int64_t*"}) LongPointer var3);

    @NoException
    public static native int av_get_output_timestamp(AVFormatContext var0, int var1, @Cast(value={"int64_t*"}) LongBuffer var2, @Cast(value={"int64_t*"}) LongBuffer var3);

    @NoException
    public static native int av_get_output_timestamp(AVFormatContext var0, int var1, @Cast(value={"int64_t*"}) long[] var2, @Cast(value={"int64_t*"}) long[] var3);

    @NoException
    public static native void av_hex_dump(@Cast(value={"FILE*"}) Pointer var0, @Cast(value={"const uint8_t*"}) BytePointer var1, int var2);

    @NoException
    public static native void av_hex_dump(@Cast(value={"FILE*"}) Pointer var0, @Cast(value={"const uint8_t*"}) ByteBuffer var1, int var2);

    @NoException
    public static native void av_hex_dump(@Cast(value={"FILE*"}) Pointer var0, @Cast(value={"const uint8_t*"}) byte[] var1, int var2);

    @NoException
    public static native void av_hex_dump_log(Pointer var0, int var1, @Cast(value={"const uint8_t*"}) BytePointer var2, int var3);

    @NoException
    public static native void av_hex_dump_log(Pointer var0, int var1, @Cast(value={"const uint8_t*"}) ByteBuffer var2, int var3);

    @NoException
    public static native void av_hex_dump_log(Pointer var0, int var1, @Cast(value={"const uint8_t*"}) byte[] var2, int var3);

    @NoException
    public static native void av_pkt_dump2(@Cast(value={"FILE*"}) Pointer var0, @Const AVPacket var1, int var2, @Const AVStream var3);

    @NoException
    public static native void av_pkt_dump_log2(Pointer var0, int var1, @Const AVPacket var2, int var3, @Const AVStream var4);

    @NoException
    @Cast(value={"AVCodecID"})
    public static native int av_codec_get_id(@Cast(value={"const AVCodecTag*const*"}) PointerPointer var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    @Cast(value={"AVCodecID"})
    public static native int av_codec_get_id(@Const @ByPtrPtr AVCodecTag var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int av_codec_get_tag(@Cast(value={"const AVCodecTag*const*"}) PointerPointer var0, @Cast(value={"AVCodecID"}) int var1);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int av_codec_get_tag(@Const @ByPtrPtr AVCodecTag var0, @Cast(value={"AVCodecID"}) int var1);

    @NoException
    public static native int av_codec_get_tag2(@Cast(value={"const AVCodecTag*const*"}) PointerPointer var0, @Cast(value={"AVCodecID"}) int var1, @Cast(value={"unsigned int*"}) IntPointer var2);

    @NoException
    public static native int av_codec_get_tag2(@Const @ByPtrPtr AVCodecTag var0, @Cast(value={"AVCodecID"}) int var1, @Cast(value={"unsigned int*"}) IntPointer var2);

    @NoException
    public static native int av_codec_get_tag2(@Const @ByPtrPtr AVCodecTag var0, @Cast(value={"AVCodecID"}) int var1, @Cast(value={"unsigned int*"}) IntBuffer var2);

    @NoException
    public static native int av_codec_get_tag2(@Const @ByPtrPtr AVCodecTag var0, @Cast(value={"AVCodecID"}) int var1, @Cast(value={"unsigned int*"}) int[] var2);

    @NoException
    public static native int av_find_default_stream_index(AVFormatContext var0);

    @NoException
    public static native int av_index_search_timestamp(AVStream var0, @Cast(value={"int64_t"}) long var1, int var3);

    @NoException
    public static native int av_add_index_entry(AVStream var0, @Cast(value={"int64_t"}) long var1, @Cast(value={"int64_t"}) long var3, int var5, int var6, int var7);

    @NoException
    public static native void av_url_split(@Cast(value={"char*"}) BytePointer var0, int var1, @Cast(value={"char*"}) BytePointer var2, int var3, @Cast(value={"char*"}) BytePointer var4, int var5, IntPointer var6, @Cast(value={"char*"}) BytePointer var7, int var8, @Cast(value={"const char*"}) BytePointer var9);

    @NoException
    public static native void av_url_split(@Cast(value={"char*"}) ByteBuffer var0, int var1, @Cast(value={"char*"}) ByteBuffer var2, int var3, @Cast(value={"char*"}) ByteBuffer var4, int var5, IntBuffer var6, @Cast(value={"char*"}) ByteBuffer var7, int var8, String var9);

    @NoException
    public static native void av_url_split(@Cast(value={"char*"}) byte[] var0, int var1, @Cast(value={"char*"}) byte[] var2, int var3, @Cast(value={"char*"}) byte[] var4, int var5, int[] var6, @Cast(value={"char*"}) byte[] var7, int var8, @Cast(value={"const char*"}) BytePointer var9);

    @NoException
    public static native void av_url_split(@Cast(value={"char*"}) BytePointer var0, int var1, @Cast(value={"char*"}) BytePointer var2, int var3, @Cast(value={"char*"}) BytePointer var4, int var5, IntPointer var6, @Cast(value={"char*"}) BytePointer var7, int var8, String var9);

    @NoException
    public static native void av_url_split(@Cast(value={"char*"}) ByteBuffer var0, int var1, @Cast(value={"char*"}) ByteBuffer var2, int var3, @Cast(value={"char*"}) ByteBuffer var4, int var5, IntBuffer var6, @Cast(value={"char*"}) ByteBuffer var7, int var8, @Cast(value={"const char*"}) BytePointer var9);

    @NoException
    public static native void av_url_split(@Cast(value={"char*"}) byte[] var0, int var1, @Cast(value={"char*"}) byte[] var2, int var3, @Cast(value={"char*"}) byte[] var4, int var5, int[] var6, @Cast(value={"char*"}) byte[] var7, int var8, String var9);

    @NoException
    public static native void av_dump_format(AVFormatContext var0, int var1, @Cast(value={"const char*"}) BytePointer var2, int var3);

    @NoException
    public static native void av_dump_format(AVFormatContext var0, int var1, String var2, int var3);

    @NoException
    public static native int av_get_frame_filename2(@Cast(value={"char*"}) BytePointer var0, int var1, @Cast(value={"const char*"}) BytePointer var2, int var3, int var4);

    @NoException
    public static native int av_get_frame_filename2(@Cast(value={"char*"}) ByteBuffer var0, int var1, String var2, int var3, int var4);

    @NoException
    public static native int av_get_frame_filename2(@Cast(value={"char*"}) byte[] var0, int var1, @Cast(value={"const char*"}) BytePointer var2, int var3, int var4);

    @NoException
    public static native int av_get_frame_filename2(@Cast(value={"char*"}) BytePointer var0, int var1, String var2, int var3, int var4);

    @NoException
    public static native int av_get_frame_filename2(@Cast(value={"char*"}) ByteBuffer var0, int var1, @Cast(value={"const char*"}) BytePointer var2, int var3, int var4);

    @NoException
    public static native int av_get_frame_filename2(@Cast(value={"char*"}) byte[] var0, int var1, String var2, int var3, int var4);

    @NoException
    public static native int av_get_frame_filename(@Cast(value={"char*"}) BytePointer var0, int var1, @Cast(value={"const char*"}) BytePointer var2, int var3);

    @NoException
    public static native int av_get_frame_filename(@Cast(value={"char*"}) ByteBuffer var0, int var1, String var2, int var3);

    @NoException
    public static native int av_get_frame_filename(@Cast(value={"char*"}) byte[] var0, int var1, @Cast(value={"const char*"}) BytePointer var2, int var3);

    @NoException
    public static native int av_get_frame_filename(@Cast(value={"char*"}) BytePointer var0, int var1, String var2, int var3);

    @NoException
    public static native int av_get_frame_filename(@Cast(value={"char*"}) ByteBuffer var0, int var1, @Cast(value={"const char*"}) BytePointer var2, int var3);

    @NoException
    public static native int av_get_frame_filename(@Cast(value={"char*"}) byte[] var0, int var1, String var2, int var3);

    @NoException
    public static native int av_filename_number_test(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    public static native int av_filename_number_test(String var0);

    @NoException
    public static native int av_sdp_create(@Cast(value={"AVFormatContext**"}) PointerPointer var0, int var1, @Cast(value={"char*"}) BytePointer var2, int var3);

    @NoException
    public static native int av_sdp_create(@ByPtrPtr AVFormatContext var0, int var1, @Cast(value={"char*"}) BytePointer var2, int var3);

    @NoException
    public static native int av_sdp_create(@ByPtrPtr AVFormatContext var0, int var1, @Cast(value={"char*"}) ByteBuffer var2, int var3);

    @NoException
    public static native int av_sdp_create(@ByPtrPtr AVFormatContext var0, int var1, @Cast(value={"char*"}) byte[] var2, int var3);

    @NoException
    public static native int av_match_ext(@Cast(value={"const char*"}) BytePointer var0, @Cast(value={"const char*"}) BytePointer var1);

    @NoException
    public static native int av_match_ext(String var0, String var1);

    @NoException
    public static native int avformat_query_codec(@Const AVOutputFormat var0, @Cast(value={"AVCodecID"}) int var1, int var2);

    @NoException
    @Const
    public static native AVCodecTag avformat_get_riff_video_tags();

    @NoException
    @Const
    public static native AVCodecTag avformat_get_riff_audio_tags();

    @NoException
    @Const
    public static native AVCodecTag avformat_get_mov_video_tags();

    @NoException
    @Const
    public static native AVCodecTag avformat_get_mov_audio_tags();

    @NoException
    @ByVal
    public static native AVRational av_guess_sample_aspect_ratio(AVFormatContext var0, AVStream var1, AVFrame var2);

    @NoException
    @ByVal
    public static native AVRational av_guess_frame_rate(AVFormatContext var0, AVStream var1, AVFrame var2);

    @NoException
    public static native int avformat_match_stream_specifier(AVFormatContext var0, AVStream var1, @Cast(value={"const char*"}) BytePointer var2);

    @NoException
    public static native int avformat_match_stream_specifier(AVFormatContext var0, AVStream var1, String var2);

    @NoException
    public static native int avformat_queue_attached_pictures(AVFormatContext var0);

    @NoException
    @Deprecated
    public static native int av_apply_bitstream_filters(AVCodecContext var0, AVPacket var1, AVBitStreamFilterContext var2);

    @NoException
    public static native int avformat_transfer_internal_stream_timing_info(@Const AVOutputFormat var0, AVStream var1, @Const AVStream var2, @Cast(value={"AVTimebaseSource"}) int var3);

    @NoException
    @ByVal
    public static native AVRational av_stream_get_codec_timebase(@Const AVStream var0);

    static {
        Loader.load();
        AVPROBE_SCORE_RETRY = avformat.AVPROBE_SCORE_RETRY();
        AVPROBE_SCORE_STREAM_RETRY = avformat.AVPROBE_SCORE_STREAM_RETRY();
    }
}

