/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avcodec.AVCodecDescriptor;
import org.bytedeco.ffmpeg.avcodec.AVCodecInternal;
import org.bytedeco.ffmpeg.avcodec.AVHWAccel;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avcodec.AVPacketSideData;
import org.bytedeco.ffmpeg.avcodec.RcOverride;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
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
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.ByRef;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.MemberGetter;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class AVCodecContext
extends Pointer {
    public static final int FF_COMPRESSION_DEFAULT = -1;
    public static final int FF_PRED_LEFT = 0;
    public static final int FF_PRED_PLANE = 1;
    public static final int FF_PRED_MEDIAN = 2;
    public static final int FF_CMP_SAD = 0;
    public static final int FF_CMP_SSE = 1;
    public static final int FF_CMP_SATD = 2;
    public static final int FF_CMP_DCT = 3;
    public static final int FF_CMP_PSNR = 4;
    public static final int FF_CMP_BIT = 5;
    public static final int FF_CMP_RD = 6;
    public static final int FF_CMP_ZERO = 7;
    public static final int FF_CMP_VSAD = 8;
    public static final int FF_CMP_VSSE = 9;
    public static final int FF_CMP_NSSE = 10;
    public static final int FF_CMP_W53 = 11;
    public static final int FF_CMP_W97 = 12;
    public static final int FF_CMP_DCTMAX = 13;
    public static final int FF_CMP_DCT264 = 14;
    public static final int FF_CMP_MEDIAN_SAD = 15;
    public static final int FF_CMP_CHROMA = 256;
    public static final int SLICE_FLAG_CODED_ORDER = 1;
    public static final int SLICE_FLAG_ALLOW_FIELD = 2;
    public static final int SLICE_FLAG_ALLOW_PLANE = 4;
    public static final int FF_MB_DECISION_SIMPLE = 0;
    public static final int FF_MB_DECISION_BITS = 1;
    public static final int FF_MB_DECISION_RD = 2;
    public static final int FF_CODER_TYPE_VLC = 0;
    public static final int FF_CODER_TYPE_AC = 1;
    public static final int FF_CODER_TYPE_RAW = 2;
    public static final int FF_CODER_TYPE_RLE = 3;
    public static final int FF_BUG_AUTODETECT = 1;
    public static final int FF_BUG_XVID_ILACE = 4;
    public static final int FF_BUG_UMP4 = 8;
    public static final int FF_BUG_NO_PADDING = 16;
    public static final int FF_BUG_AMV = 32;
    public static final int FF_BUG_QPEL_CHROMA = 64;
    public static final int FF_BUG_STD_QPEL = 128;
    public static final int FF_BUG_QPEL_CHROMA2 = 256;
    public static final int FF_BUG_DIRECT_BLOCKSIZE = 512;
    public static final int FF_BUG_EDGE = 1024;
    public static final int FF_BUG_HPEL_CHROMA = 2048;
    public static final int FF_BUG_DC_CLIP = 4096;
    public static final int FF_BUG_MS = 8192;
    public static final int FF_BUG_TRUNCATED = 16384;
    public static final int FF_BUG_IEDGE = 32768;
    public static final int FF_COMPLIANCE_VERY_STRICT = 2;
    public static final int FF_COMPLIANCE_STRICT = 1;
    public static final int FF_COMPLIANCE_NORMAL = 0;
    public static final int FF_COMPLIANCE_UNOFFICIAL = -1;
    public static final int FF_COMPLIANCE_EXPERIMENTAL = -2;
    public static final int FF_EC_GUESS_MVS = 1;
    public static final int FF_EC_DEBLOCK = 2;
    public static final int FF_EC_FAVOR_INTER = 256;
    public static final int FF_DEBUG_PICT_INFO = 1;
    public static final int FF_DEBUG_RC = 2;
    public static final int FF_DEBUG_BITSTREAM = 4;
    public static final int FF_DEBUG_MB_TYPE = 8;
    public static final int FF_DEBUG_QP = 16;
    public static final int FF_DEBUG_DCT_COEFF = 64;
    public static final int FF_DEBUG_SKIP = 128;
    public static final int FF_DEBUG_STARTCODE = 256;
    public static final int FF_DEBUG_ER = 1024;
    public static final int FF_DEBUG_MMCO = 2048;
    public static final int FF_DEBUG_BUGS = 4096;
    public static final int FF_DEBUG_BUFFERS = 32768;
    public static final int FF_DEBUG_THREADS = 65536;
    public static final int FF_DEBUG_GREEN_MD = 0x800000;
    public static final int FF_DEBUG_NOMC = 0x1000000;
    public static final int AV_EF_CRCCHECK = 1;
    public static final int AV_EF_BITSTREAM = 2;
    public static final int AV_EF_BUFFER = 4;
    public static final int AV_EF_EXPLODE = 8;
    public static final int AV_EF_IGNORE_ERR = 32768;
    public static final int AV_EF_CAREFUL = 65536;
    public static final int AV_EF_COMPLIANT = 131072;
    public static final int AV_EF_AGGRESSIVE = 262144;
    public static final int FF_DCT_AUTO = 0;
    public static final int FF_DCT_FASTINT = 1;
    public static final int FF_DCT_INT = 2;
    public static final int FF_DCT_MMX = 3;
    public static final int FF_DCT_ALTIVEC = 5;
    public static final int FF_DCT_FAAN = 6;
    public static final int FF_IDCT_AUTO = 0;
    public static final int FF_IDCT_INT = 1;
    public static final int FF_IDCT_SIMPLE = 2;
    public static final int FF_IDCT_SIMPLEMMX = 3;
    public static final int FF_IDCT_ARM = 7;
    public static final int FF_IDCT_ALTIVEC = 8;
    public static final int FF_IDCT_SIMPLEARM = 10;
    public static final int FF_IDCT_XVID = 14;
    public static final int FF_IDCT_SIMPLEARMV5TE = 16;
    public static final int FF_IDCT_SIMPLEARMV6 = 17;
    public static final int FF_IDCT_FAAN = 20;
    public static final int FF_IDCT_SIMPLENEON = 22;
    public static final int FF_IDCT_NONE = 24;
    public static final int FF_IDCT_SIMPLEAUTO = 128;
    public static final int FF_THREAD_FRAME = 1;
    public static final int FF_THREAD_SLICE = 2;
    public static final int FF_PROFILE_UNKNOWN = -99;
    public static final int FF_PROFILE_RESERVED = -100;
    public static final int FF_PROFILE_AAC_MAIN = 0;
    public static final int FF_PROFILE_AAC_LOW = 1;
    public static final int FF_PROFILE_AAC_SSR = 2;
    public static final int FF_PROFILE_AAC_LTP = 3;
    public static final int FF_PROFILE_AAC_HE = 4;
    public static final int FF_PROFILE_AAC_HE_V2 = 28;
    public static final int FF_PROFILE_AAC_LD = 22;
    public static final int FF_PROFILE_AAC_ELD = 38;
    public static final int FF_PROFILE_MPEG2_AAC_LOW = 128;
    public static final int FF_PROFILE_MPEG2_AAC_HE = 131;
    public static final int FF_PROFILE_DNXHD = 0;
    public static final int FF_PROFILE_DNXHR_LB = 1;
    public static final int FF_PROFILE_DNXHR_SQ = 2;
    public static final int FF_PROFILE_DNXHR_HQ = 3;
    public static final int FF_PROFILE_DNXHR_HQX = 4;
    public static final int FF_PROFILE_DNXHR_444 = 5;
    public static final int FF_PROFILE_DTS = 20;
    public static final int FF_PROFILE_DTS_ES = 30;
    public static final int FF_PROFILE_DTS_96_24 = 40;
    public static final int FF_PROFILE_DTS_HD_HRA = 50;
    public static final int FF_PROFILE_DTS_HD_MA = 60;
    public static final int FF_PROFILE_DTS_EXPRESS = 70;
    public static final int FF_PROFILE_MPEG2_422 = 0;
    public static final int FF_PROFILE_MPEG2_HIGH = 1;
    public static final int FF_PROFILE_MPEG2_SS = 2;
    public static final int FF_PROFILE_MPEG2_SNR_SCALABLE = 3;
    public static final int FF_PROFILE_MPEG2_MAIN = 4;
    public static final int FF_PROFILE_MPEG2_SIMPLE = 5;
    public static final int FF_PROFILE_H264_CONSTRAINED = 512;
    public static final int FF_PROFILE_H264_INTRA = 2048;
    public static final int FF_PROFILE_H264_BASELINE = 66;
    public static final int FF_PROFILE_H264_CONSTRAINED_BASELINE = 578;
    public static final int FF_PROFILE_H264_MAIN = 77;
    public static final int FF_PROFILE_H264_EXTENDED = 88;
    public static final int FF_PROFILE_H264_HIGH = 100;
    public static final int FF_PROFILE_H264_HIGH_10 = 110;
    public static final int FF_PROFILE_H264_HIGH_10_INTRA = 2158;
    public static final int FF_PROFILE_H264_MULTIVIEW_HIGH = 118;
    public static final int FF_PROFILE_H264_HIGH_422 = 122;
    public static final int FF_PROFILE_H264_HIGH_422_INTRA = 2170;
    public static final int FF_PROFILE_H264_STEREO_HIGH = 128;
    public static final int FF_PROFILE_H264_HIGH_444 = 144;
    public static final int FF_PROFILE_H264_HIGH_444_PREDICTIVE = 244;
    public static final int FF_PROFILE_H264_HIGH_444_INTRA = 2292;
    public static final int FF_PROFILE_H264_CAVLC_444 = 44;
    public static final int FF_PROFILE_VC1_SIMPLE = 0;
    public static final int FF_PROFILE_VC1_MAIN = 1;
    public static final int FF_PROFILE_VC1_COMPLEX = 2;
    public static final int FF_PROFILE_VC1_ADVANCED = 3;
    public static final int FF_PROFILE_MPEG4_SIMPLE = 0;
    public static final int FF_PROFILE_MPEG4_SIMPLE_SCALABLE = 1;
    public static final int FF_PROFILE_MPEG4_CORE = 2;
    public static final int FF_PROFILE_MPEG4_MAIN = 3;
    public static final int FF_PROFILE_MPEG4_N_BIT = 4;
    public static final int FF_PROFILE_MPEG4_SCALABLE_TEXTURE = 5;
    public static final int FF_PROFILE_MPEG4_SIMPLE_FACE_ANIMATION = 6;
    public static final int FF_PROFILE_MPEG4_BASIC_ANIMATED_TEXTURE = 7;
    public static final int FF_PROFILE_MPEG4_HYBRID = 8;
    public static final int FF_PROFILE_MPEG4_ADVANCED_REAL_TIME = 9;
    public static final int FF_PROFILE_MPEG4_CORE_SCALABLE = 10;
    public static final int FF_PROFILE_MPEG4_ADVANCED_CODING = 11;
    public static final int FF_PROFILE_MPEG4_ADVANCED_CORE = 12;
    public static final int FF_PROFILE_MPEG4_ADVANCED_SCALABLE_TEXTURE = 13;
    public static final int FF_PROFILE_MPEG4_SIMPLE_STUDIO = 14;
    public static final int FF_PROFILE_MPEG4_ADVANCED_SIMPLE = 15;
    public static final int FF_PROFILE_JPEG2000_CSTREAM_RESTRICTION_0 = 1;
    public static final int FF_PROFILE_JPEG2000_CSTREAM_RESTRICTION_1 = 2;
    public static final int FF_PROFILE_JPEG2000_CSTREAM_NO_RESTRICTION = 32768;
    public static final int FF_PROFILE_JPEG2000_DCINEMA_2K = 3;
    public static final int FF_PROFILE_JPEG2000_DCINEMA_4K = 4;
    public static final int FF_PROFILE_VP9_0 = 0;
    public static final int FF_PROFILE_VP9_1 = 1;
    public static final int FF_PROFILE_VP9_2 = 2;
    public static final int FF_PROFILE_VP9_3 = 3;
    public static final int FF_PROFILE_HEVC_MAIN = 1;
    public static final int FF_PROFILE_HEVC_MAIN_10 = 2;
    public static final int FF_PROFILE_HEVC_MAIN_STILL_PICTURE = 3;
    public static final int FF_PROFILE_HEVC_REXT = 4;
    public static final int FF_PROFILE_VVC_MAIN_10 = 1;
    public static final int FF_PROFILE_VVC_MAIN_10_444 = 33;
    public static final int FF_PROFILE_AV1_MAIN = 0;
    public static final int FF_PROFILE_AV1_HIGH = 1;
    public static final int FF_PROFILE_AV1_PROFESSIONAL = 2;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_BASELINE_DCT = 192;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_EXTENDED_SEQUENTIAL_DCT = 193;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_PROGRESSIVE_DCT = 194;
    public static final int FF_PROFILE_MJPEG_HUFFMAN_LOSSLESS = 195;
    public static final int FF_PROFILE_MJPEG_JPEG_LS = 247;
    public static final int FF_PROFILE_SBC_MSBC = 1;
    public static final int FF_PROFILE_PRORES_PROXY = 0;
    public static final int FF_PROFILE_PRORES_LT = 1;
    public static final int FF_PROFILE_PRORES_STANDARD = 2;
    public static final int FF_PROFILE_PRORES_HQ = 3;
    public static final int FF_PROFILE_PRORES_4444 = 4;
    public static final int FF_PROFILE_PRORES_XQ = 5;
    public static final int FF_PROFILE_ARIB_PROFILE_A = 0;
    public static final int FF_PROFILE_ARIB_PROFILE_C = 1;
    public static final int FF_PROFILE_KLVA_SYNC = 0;
    public static final int FF_PROFILE_KLVA_ASYNC = 1;
    public static final int FF_LEVEL_UNKNOWN = -99;
    public static final int FF_SUB_CHARENC_MODE_DO_NOTHING = -1;
    public static final int FF_SUB_CHARENC_MODE_AUTOMATIC = 0;
    public static final int FF_SUB_CHARENC_MODE_PRE_DECODER = 1;
    public static final int FF_SUB_CHARENC_MODE_IGNORE = 2;
    public static final int FF_DEBUG_VIS_MV_P_FOR = 1;
    public static final int FF_DEBUG_VIS_MV_B_FOR = 2;
    public static final int FF_DEBUG_VIS_MV_B_BACK = 4;
    public static final int FF_CODEC_PROPERTY_LOSSLESS = 1;
    public static final int FF_CODEC_PROPERTY_CLOSED_CAPTIONS = 2;
    public static final int FF_SUB_TEXT_FMT_ASS = 0;
    public static final int FF_SUB_TEXT_FMT_ASS_WITH_TIMINGS = 1;

    public AVCodecContext() {
        super((Pointer)null);
        this.allocate();
    }

    public AVCodecContext(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVCodecContext(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVCodecContext position(long position) {
        return (AVCodecContext)super.position(position);
    }

    public AVCodecContext getPointer(long i2) {
        return (AVCodecContext)new AVCodecContext(this).offsetAddress(i2);
    }

    @Const
    public native AVClass av_class();

    public native AVCodecContext av_class(AVClass var1);

    public native int log_level_offset();

    public native AVCodecContext log_level_offset(int var1);

    @Cast(value={"AVMediaType"})
    public native int codec_type();

    public native AVCodecContext codec_type(int var1);

    @Const
    public native AVCodec codec();

    public native AVCodecContext codec(AVCodec var1);

    @Cast(value={"AVCodecID"})
    public native int codec_id();

    public native AVCodecContext codec_id(int var1);

    @Cast(value={"unsigned int"})
    public native int codec_tag();

    public native AVCodecContext codec_tag(int var1);

    public native Pointer priv_data();

    public native AVCodecContext priv_data(Pointer var1);

    public native AVCodecInternal internal();

    public native AVCodecContext internal(AVCodecInternal var1);

    public native Pointer opaque();

    public native AVCodecContext opaque(Pointer var1);

    @Cast(value={"int64_t"})
    public native long bit_rate();

    public native AVCodecContext bit_rate(long var1);

    public native int bit_rate_tolerance();

    public native AVCodecContext bit_rate_tolerance(int var1);

    public native int global_quality();

    public native AVCodecContext global_quality(int var1);

    public native int compression_level();

    public native AVCodecContext compression_level(int var1);

    public native int flags();

    public native AVCodecContext flags(int var1);

    public native int flags2();

    public native AVCodecContext flags2(int var1);

    @Cast(value={"uint8_t*"})
    public native BytePointer extradata();

    public native AVCodecContext extradata(BytePointer var1);

    public native int extradata_size();

    public native AVCodecContext extradata_size(int var1);

    @ByRef
    public native AVRational time_base();

    public native AVCodecContext time_base(AVRational var1);

    public native int ticks_per_frame();

    public native AVCodecContext ticks_per_frame(int var1);

    public native int delay();

    public native AVCodecContext delay(int var1);

    public native int width();

    public native AVCodecContext width(int var1);

    public native int height();

    public native AVCodecContext height(int var1);

    public native int coded_width();

    public native AVCodecContext coded_width(int var1);

    public native int coded_height();

    public native AVCodecContext coded_height(int var1);

    public native int gop_size();

    public native AVCodecContext gop_size(int var1);

    @Cast(value={"AVPixelFormat"})
    public native int pix_fmt();

    public native AVCodecContext pix_fmt(int var1);

    public native Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int draw_horiz_band();

    public native AVCodecContext draw_horiz_band(Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int var1);

    public native Get_format_AVCodecContext_IntPointer get_format();

    public native AVCodecContext get_format(Get_format_AVCodecContext_IntPointer var1);

    public native int max_b_frames();

    public native AVCodecContext max_b_frames(int var1);

    public native float b_quant_factor();

    public native AVCodecContext b_quant_factor(float var1);

    @Deprecated
    public native int b_frame_strategy();

    public native AVCodecContext b_frame_strategy(int var1);

    public native float b_quant_offset();

    public native AVCodecContext b_quant_offset(float var1);

    public native int has_b_frames();

    public native AVCodecContext has_b_frames(int var1);

    @Deprecated
    public native int mpeg_quant();

    public native AVCodecContext mpeg_quant(int var1);

    public native float i_quant_factor();

    public native AVCodecContext i_quant_factor(float var1);

    public native float i_quant_offset();

    public native AVCodecContext i_quant_offset(float var1);

    public native float lumi_masking();

    public native AVCodecContext lumi_masking(float var1);

    public native float temporal_cplx_masking();

    public native AVCodecContext temporal_cplx_masking(float var1);

    public native float spatial_cplx_masking();

    public native AVCodecContext spatial_cplx_masking(float var1);

    public native float p_masking();

    public native AVCodecContext p_masking(float var1);

    public native float dark_masking();

    public native AVCodecContext dark_masking(float var1);

    public native int slice_count();

    public native AVCodecContext slice_count(int var1);

    @Deprecated
    public native int prediction_method();

    public native AVCodecContext prediction_method(int var1);

    public native IntPointer slice_offset();

    public native AVCodecContext slice_offset(IntPointer var1);

    @ByRef
    public native AVRational sample_aspect_ratio();

    public native AVCodecContext sample_aspect_ratio(AVRational var1);

    public native int me_cmp();

    public native AVCodecContext me_cmp(int var1);

    public native int me_sub_cmp();

    public native AVCodecContext me_sub_cmp(int var1);

    public native int mb_cmp();

    public native AVCodecContext mb_cmp(int var1);

    public native int ildct_cmp();

    public native AVCodecContext ildct_cmp(int var1);

    public native int dia_size();

    public native AVCodecContext dia_size(int var1);

    public native int last_predictor_count();

    public native AVCodecContext last_predictor_count(int var1);

    @Deprecated
    public native int pre_me();

    public native AVCodecContext pre_me(int var1);

    public native int me_pre_cmp();

    public native AVCodecContext me_pre_cmp(int var1);

    public native int pre_dia_size();

    public native AVCodecContext pre_dia_size(int var1);

    public native int me_subpel_quality();

    public native AVCodecContext me_subpel_quality(int var1);

    public native int me_range();

    public native AVCodecContext me_range(int var1);

    public native int slice_flags();

    public native AVCodecContext slice_flags(int var1);

    public native int mb_decision();

    public native AVCodecContext mb_decision(int var1);

    @Cast(value={"uint16_t*"})
    public native ShortPointer intra_matrix();

    public native AVCodecContext intra_matrix(ShortPointer var1);

    @Cast(value={"uint16_t*"})
    public native ShortPointer inter_matrix();

    public native AVCodecContext inter_matrix(ShortPointer var1);

    @Deprecated
    public native int scenechange_threshold();

    public native AVCodecContext scenechange_threshold(int var1);

    @Deprecated
    public native int noise_reduction();

    public native AVCodecContext noise_reduction(int var1);

    public native int intra_dc_precision();

    public native AVCodecContext intra_dc_precision(int var1);

    public native int skip_top();

    public native AVCodecContext skip_top(int var1);

    public native int skip_bottom();

    public native AVCodecContext skip_bottom(int var1);

    public native int mb_lmin();

    public native AVCodecContext mb_lmin(int var1);

    public native int mb_lmax();

    public native AVCodecContext mb_lmax(int var1);

    @Deprecated
    public native int me_penalty_compensation();

    public native AVCodecContext me_penalty_compensation(int var1);

    public native int bidir_refine();

    public native AVCodecContext bidir_refine(int var1);

    @Deprecated
    public native int brd_scale();

    public native AVCodecContext brd_scale(int var1);

    public native int keyint_min();

    public native AVCodecContext keyint_min(int var1);

    public native int refs();

    public native AVCodecContext refs(int var1);

    @Deprecated
    public native int chromaoffset();

    public native AVCodecContext chromaoffset(int var1);

    public native int mv0_threshold();

    public native AVCodecContext mv0_threshold(int var1);

    @Deprecated
    public native int b_sensitivity();

    public native AVCodecContext b_sensitivity(int var1);

    @Cast(value={"AVColorPrimaries"})
    public native int color_primaries();

    public native AVCodecContext color_primaries(int var1);

    @Cast(value={"AVColorTransferCharacteristic"})
    public native int color_trc();

    public native AVCodecContext color_trc(int var1);

    @Cast(value={"AVColorSpace"})
    public native int colorspace();

    public native AVCodecContext colorspace(int var1);

    @Cast(value={"AVColorRange"})
    public native int color_range();

    public native AVCodecContext color_range(int var1);

    @Cast(value={"AVChromaLocation"})
    public native int chroma_sample_location();

    public native AVCodecContext chroma_sample_location(int var1);

    public native int slices();

    public native AVCodecContext slices(int var1);

    @Cast(value={"AVFieldOrder"})
    public native int field_order();

    public native AVCodecContext field_order(int var1);

    public native int sample_rate();

    public native AVCodecContext sample_rate(int var1);

    public native int channels();

    public native AVCodecContext channels(int var1);

    @Cast(value={"AVSampleFormat"})
    public native int sample_fmt();

    public native AVCodecContext sample_fmt(int var1);

    public native int frame_size();

    public native AVCodecContext frame_size(int var1);

    public native int frame_number();

    public native AVCodecContext frame_number(int var1);

    public native int block_align();

    public native AVCodecContext block_align(int var1);

    public native int cutoff();

    public native AVCodecContext cutoff(int var1);

    @Cast(value={"uint64_t"})
    public native long channel_layout();

    public native AVCodecContext channel_layout(long var1);

    @Cast(value={"uint64_t"})
    public native long request_channel_layout();

    public native AVCodecContext request_channel_layout(long var1);

    @Cast(value={"AVAudioServiceType"})
    public native int audio_service_type();

    public native AVCodecContext audio_service_type(int var1);

    @Cast(value={"AVSampleFormat"})
    public native int request_sample_fmt();

    public native AVCodecContext request_sample_fmt(int var1);

    public native Get_buffer2_AVCodecContext_AVFrame_int get_buffer2();

    public native AVCodecContext get_buffer2(Get_buffer2_AVCodecContext_AVFrame_int var1);

    @Deprecated
    public native int refcounted_frames();

    public native AVCodecContext refcounted_frames(int var1);

    public native float qcompress();

    public native AVCodecContext qcompress(float var1);

    public native float qblur();

    public native AVCodecContext qblur(float var1);

    public native int qmin();

    public native AVCodecContext qmin(int var1);

    public native int qmax();

    public native AVCodecContext qmax(int var1);

    public native int max_qdiff();

    public native AVCodecContext max_qdiff(int var1);

    public native int rc_buffer_size();

    public native AVCodecContext rc_buffer_size(int var1);

    public native int rc_override_count();

    public native AVCodecContext rc_override_count(int var1);

    public native RcOverride rc_override();

    public native AVCodecContext rc_override(RcOverride var1);

    @Cast(value={"int64_t"})
    public native long rc_max_rate();

    public native AVCodecContext rc_max_rate(long var1);

    @Cast(value={"int64_t"})
    public native long rc_min_rate();

    public native AVCodecContext rc_min_rate(long var1);

    public native float rc_max_available_vbv_use();

    public native AVCodecContext rc_max_available_vbv_use(float var1);

    public native float rc_min_vbv_overflow_use();

    public native AVCodecContext rc_min_vbv_overflow_use(float var1);

    public native int rc_initial_buffer_occupancy();

    public native AVCodecContext rc_initial_buffer_occupancy(int var1);

    @Deprecated
    public native int coder_type();

    public native AVCodecContext coder_type(int var1);

    @Deprecated
    public native int context_model();

    public native AVCodecContext context_model(int var1);

    @Deprecated
    public native int frame_skip_threshold();

    public native AVCodecContext frame_skip_threshold(int var1);

    @Deprecated
    public native int frame_skip_factor();

    public native AVCodecContext frame_skip_factor(int var1);

    @Deprecated
    public native int frame_skip_exp();

    public native AVCodecContext frame_skip_exp(int var1);

    @Deprecated
    public native int frame_skip_cmp();

    public native AVCodecContext frame_skip_cmp(int var1);

    public native int trellis();

    public native AVCodecContext trellis(int var1);

    @Deprecated
    public native int min_prediction_order();

    public native AVCodecContext min_prediction_order(int var1);

    @Deprecated
    public native int max_prediction_order();

    public native AVCodecContext max_prediction_order(int var1);

    @Cast(value={"int64_t"})
    @Deprecated
    public native long timecode_frame_start();

    public native AVCodecContext timecode_frame_start(long var1);

    public native Rtp_callback_AVCodecContext_Pointer_int_int rtp_callback();

    public native AVCodecContext rtp_callback(Rtp_callback_AVCodecContext_Pointer_int_int var1);

    @Deprecated
    public native int rtp_payload_size();

    public native AVCodecContext rtp_payload_size(int var1);

    @Deprecated
    public native int mv_bits();

    public native AVCodecContext mv_bits(int var1);

    @Deprecated
    public native int header_bits();

    public native AVCodecContext header_bits(int var1);

    @Deprecated
    public native int i_tex_bits();

    public native AVCodecContext i_tex_bits(int var1);

    @Deprecated
    public native int p_tex_bits();

    public native AVCodecContext p_tex_bits(int var1);

    @Deprecated
    public native int i_count();

    public native AVCodecContext i_count(int var1);

    @Deprecated
    public native int p_count();

    public native AVCodecContext p_count(int var1);

    @Deprecated
    public native int skip_count();

    public native AVCodecContext skip_count(int var1);

    @Deprecated
    public native int misc_bits();

    public native AVCodecContext misc_bits(int var1);

    @Deprecated
    public native int frame_bits();

    public native AVCodecContext frame_bits(int var1);

    @Cast(value={"char*"})
    public native BytePointer stats_out();

    public native AVCodecContext stats_out(BytePointer var1);

    @Cast(value={"char*"})
    public native BytePointer stats_in();

    public native AVCodecContext stats_in(BytePointer var1);

    public native int workaround_bugs();

    public native AVCodecContext workaround_bugs(int var1);

    public native int strict_std_compliance();

    public native AVCodecContext strict_std_compliance(int var1);

    public native int error_concealment();

    public native AVCodecContext error_concealment(int var1);

    public native int debug();

    public native AVCodecContext debug(int var1);

    public native int err_recognition();

    public native AVCodecContext err_recognition(int var1);

    @Cast(value={"int64_t"})
    public native long reordered_opaque();

    public native AVCodecContext reordered_opaque(long var1);

    @Const
    public native AVHWAccel hwaccel();

    public native AVCodecContext hwaccel(AVHWAccel var1);

    public native Pointer hwaccel_context();

    public native AVCodecContext hwaccel_context(Pointer var1);

    @Cast(value={"uint64_t"})
    public native long error(int var1);

    public native AVCodecContext error(int var1, long var2);

    @MemberGetter
    @Cast(value={"uint64_t*"})
    public native LongPointer error();

    public native int dct_algo();

    public native AVCodecContext dct_algo(int var1);

    public native int idct_algo();

    public native AVCodecContext idct_algo(int var1);

    public native int bits_per_coded_sample();

    public native AVCodecContext bits_per_coded_sample(int var1);

    public native int bits_per_raw_sample();

    public native AVCodecContext bits_per_raw_sample(int var1);

    public native int lowres();

    public native AVCodecContext lowres(int var1);

    @Deprecated
    public native AVFrame coded_frame();

    public native AVCodecContext coded_frame(AVFrame var1);

    public native int thread_count();

    public native AVCodecContext thread_count(int var1);

    public native int thread_type();

    public native AVCodecContext thread_type(int var1);

    public native int active_thread_type();

    public native AVCodecContext active_thread_type(int var1);

    @Deprecated
    public native int thread_safe_callbacks();

    public native AVCodecContext thread_safe_callbacks(int var1);

    public native Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int execute();

    public native AVCodecContext execute(Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int var1);

    public native Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int execute2();

    public native AVCodecContext execute2(Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int var1);

    public native int nsse_weight();

    public native AVCodecContext nsse_weight(int var1);

    public native int profile();

    public native AVCodecContext profile(int var1);

    public native int level();

    public native AVCodecContext level(int var1);

    @Cast(value={"AVDiscard"})
    public native int skip_loop_filter();

    public native AVCodecContext skip_loop_filter(int var1);

    @Cast(value={"AVDiscard"})
    public native int skip_idct();

    public native AVCodecContext skip_idct(int var1);

    @Cast(value={"AVDiscard"})
    public native int skip_frame();

    public native AVCodecContext skip_frame(int var1);

    @Cast(value={"uint8_t*"})
    public native BytePointer subtitle_header();

    public native AVCodecContext subtitle_header(BytePointer var1);

    public native int subtitle_header_size();

    public native AVCodecContext subtitle_header_size(int var1);

    @Cast(value={"uint64_t"})
    @Deprecated
    public native long vbv_delay();

    public native AVCodecContext vbv_delay(long var1);

    @Deprecated
    public native int side_data_only_packets();

    public native AVCodecContext side_data_only_packets(int var1);

    public native int initial_padding();

    public native AVCodecContext initial_padding(int var1);

    @ByRef
    public native AVRational framerate();

    public native AVCodecContext framerate(AVRational var1);

    @Cast(value={"AVPixelFormat"})
    public native int sw_pix_fmt();

    public native AVCodecContext sw_pix_fmt(int var1);

    @ByRef
    public native AVRational pkt_timebase();

    public native AVCodecContext pkt_timebase(AVRational var1);

    @Const
    public native AVCodecDescriptor codec_descriptor();

    public native AVCodecContext codec_descriptor(AVCodecDescriptor var1);

    @Cast(value={"int64_t"})
    public native long pts_correction_num_faulty_pts();

    public native AVCodecContext pts_correction_num_faulty_pts(long var1);

    @Cast(value={"int64_t"})
    public native long pts_correction_num_faulty_dts();

    public native AVCodecContext pts_correction_num_faulty_dts(long var1);

    @Cast(value={"int64_t"})
    public native long pts_correction_last_pts();

    public native AVCodecContext pts_correction_last_pts(long var1);

    @Cast(value={"int64_t"})
    public native long pts_correction_last_dts();

    public native AVCodecContext pts_correction_last_dts(long var1);

    @Cast(value={"char*"})
    public native BytePointer sub_charenc();

    public native AVCodecContext sub_charenc(BytePointer var1);

    public native int sub_charenc_mode();

    public native AVCodecContext sub_charenc_mode(int var1);

    public native int skip_alpha();

    public native AVCodecContext skip_alpha(int var1);

    public native int seek_preroll();

    public native AVCodecContext seek_preroll(int var1);

    @Deprecated
    public native int debug_mv();

    public native AVCodecContext debug_mv(int var1);

    @Cast(value={"uint16_t*"})
    public native ShortPointer chroma_intra_matrix();

    public native AVCodecContext chroma_intra_matrix(ShortPointer var1);

    @Cast(value={"uint8_t*"})
    public native BytePointer dump_separator();

    public native AVCodecContext dump_separator(BytePointer var1);

    @Cast(value={"char*"})
    public native BytePointer codec_whitelist();

    public native AVCodecContext codec_whitelist(BytePointer var1);

    @Cast(value={"unsigned"})
    public native int properties();

    public native AVCodecContext properties(int var1);

    public native AVPacketSideData coded_side_data();

    public native AVCodecContext coded_side_data(AVPacketSideData var1);

    public native int nb_coded_side_data();

    public native AVCodecContext nb_coded_side_data(int var1);

    public native AVBufferRef hw_frames_ctx();

    public native AVCodecContext hw_frames_ctx(AVBufferRef var1);

    public native int sub_text_format();

    public native AVCodecContext sub_text_format(int var1);

    public native int trailing_padding();

    public native AVCodecContext trailing_padding(int var1);

    @Cast(value={"int64_t"})
    public native long max_pixels();

    public native AVCodecContext max_pixels(long var1);

    public native AVBufferRef hw_device_ctx();

    public native AVCodecContext hw_device_ctx(AVBufferRef var1);

    public native int hwaccel_flags();

    public native AVCodecContext hwaccel_flags(int var1);

    public native int apply_cropping();

    public native AVCodecContext apply_cropping(int var1);

    public native int extra_hw_frames();

    public native AVCodecContext extra_hw_frames(int var1);

    public native int discard_damaged_percentage();

    public native AVCodecContext discard_damaged_percentage(int var1);

    @Cast(value={"int64_t"})
    public native long max_samples();

    public native AVCodecContext max_samples(long var1);

    public native int export_side_data();

    public native AVCodecContext export_side_data(int var1);

    public native Get_encode_buffer_AVCodecContext_AVPacket_int get_encode_buffer();

    public native AVCodecContext get_encode_buffer(Get_encode_buffer_AVCodecContext_AVPacket_int var1);

    static {
        Loader.load();
    }

    public static class Get_encode_buffer_AVCodecContext_AVPacket_int
    extends FunctionPointer {
        public Get_encode_buffer_AVCodecContext_AVPacket_int(Pointer p2) {
            super(p2);
        }

        protected Get_encode_buffer_AVCodecContext_AVPacket_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, AVPacket var2, int var3);

        static {
            Loader.load();
        }
    }

    public static class Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int
    extends FunctionPointer {
        public Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int(Pointer p2) {
            super(p2);
        }

        protected Execute2_AVCodecContext_Func_AVCodecContext_Pointer_int_int_Pointer_IntPointer_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, Func_AVCodecContext_Pointer_int_int var2, Pointer var3, IntPointer var4, int var5);

        static {
            Loader.load();
        }
    }

    public static class Func_AVCodecContext_Pointer_int_int
    extends FunctionPointer {
        public Func_AVCodecContext_Pointer_int_int(Pointer p2) {
            super(p2);
        }

        protected Func_AVCodecContext_Pointer_int_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, Pointer var2, int var3, int var4);

        static {
            Loader.load();
        }
    }

    public static class Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int
    extends FunctionPointer {
        public Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int(Pointer p2) {
            super(p2);
        }

        protected Execute_AVCodecContext_Func_AVCodecContext_Pointer_Pointer_IntPointer_int_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, Func_AVCodecContext_Pointer var2, Pointer var3, IntPointer var4, int var5, int var6);

        static {
            Loader.load();
        }
    }

    public static class Func_AVCodecContext_Pointer
    extends FunctionPointer {
        public Func_AVCodecContext_Pointer(Pointer p2) {
            super(p2);
        }

        protected Func_AVCodecContext_Pointer() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, Pointer var2);

        static {
            Loader.load();
        }
    }

    public static class Rtp_callback_AVCodecContext_Pointer_int_int
    extends FunctionPointer {
        public Rtp_callback_AVCodecContext_Pointer_int_int(Pointer p2) {
            super(p2);
        }

        protected Rtp_callback_AVCodecContext_Pointer_int_int() {
            this.allocate();
        }

        private native void allocate();

        @Deprecated
        public native void call(AVCodecContext var1, Pointer var2, int var3, int var4);

        static {
            Loader.load();
        }
    }

    public static class Get_buffer2_AVCodecContext_AVFrame_int
    extends FunctionPointer {
        public Get_buffer2_AVCodecContext_AVFrame_int(Pointer p2) {
            super(p2);
        }

        protected Get_buffer2_AVCodecContext_AVFrame_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVCodecContext var1, AVFrame var2, int var3);

        static {
            Loader.load();
        }
    }

    public static class Get_format_AVCodecContext_IntPointer
    extends FunctionPointer {
        public Get_format_AVCodecContext_IntPointer(Pointer p2) {
            super(p2);
        }

        protected Get_format_AVCodecContext_IntPointer() {
            this.allocate();
        }

        private native void allocate();

        @Cast(value={"AVPixelFormat"})
        public native int call(AVCodecContext var1, @Cast(value={"const AVPixelFormat*"}) IntPointer var2);

        static {
            Loader.load();
        }
    }

    public static class Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int
    extends FunctionPointer {
        public Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int(Pointer p2) {
            super(p2);
        }

        protected Draw_horiz_band_AVCodecContext_AVFrame_IntPointer_int_int_int() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVCodecContext var1, @Const AVFrame var2, IntPointer var3, int var4, int var5, int var6);

        static {
            Loader.load();
        }
    }
}

