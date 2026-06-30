/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.global;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import org.bytedeco.ffmpeg.avcodec.AVBSFContext;
import org.bytedeco.ffmpeg.avcodec.AVBSFList;
import org.bytedeco.ffmpeg.avcodec.AVBitStreamFilter;
import org.bytedeco.ffmpeg.avcodec.AVBitStreamFilterContext;
import org.bytedeco.ffmpeg.avcodec.AVCPBProperties;
import org.bytedeco.ffmpeg.avcodec.AVCodec;
import org.bytedeco.ffmpeg.avcodec.AVCodecContext;
import org.bytedeco.ffmpeg.avcodec.AVCodecDescriptor;
import org.bytedeco.ffmpeg.avcodec.AVCodecHWConfig;
import org.bytedeco.ffmpeg.avcodec.AVCodecParameters;
import org.bytedeco.ffmpeg.avcodec.AVCodecParser;
import org.bytedeco.ffmpeg.avcodec.AVCodecParserContext;
import org.bytedeco.ffmpeg.avcodec.AVHWAccel;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avcodec.AVPicture;
import org.bytedeco.ffmpeg.avcodec.AVSubtitle;
import org.bytedeco.ffmpeg.avcodec.Cb_PointerPointer_int;
import org.bytedeco.ffmpeg.avcodec.Cb_Pointer_int;
import org.bytedeco.ffmpeg.avcodec.DCTContext;
import org.bytedeco.ffmpeg.avcodec.FFTComplex;
import org.bytedeco.ffmpeg.avcodec.FFTContext;
import org.bytedeco.ffmpeg.avcodec.Func_AVCodecContext_Pointer;
import org.bytedeco.ffmpeg.avcodec.Func_AVCodecContext_Pointer_int_int;
import org.bytedeco.ffmpeg.avcodec.RDFTContext;
import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.avutil.AVDictionary;
import org.bytedeco.ffmpeg.avutil.AVFrame;
import org.bytedeco.ffmpeg.avutil.AVRational;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.SizeTPointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.ByVal;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.NoException;

public class avcodec
extends org.bytedeco.ffmpeg.presets.avcodec {
    public static final int AV_CODEC_ID_NONE = 0;
    public static final int AV_CODEC_ID_MPEG1VIDEO = 1;
    public static final int AV_CODEC_ID_MPEG2VIDEO = 2;
    public static final int AV_CODEC_ID_H261 = 3;
    public static final int AV_CODEC_ID_H263 = 4;
    public static final int AV_CODEC_ID_RV10 = 5;
    public static final int AV_CODEC_ID_RV20 = 6;
    public static final int AV_CODEC_ID_MJPEG = 7;
    public static final int AV_CODEC_ID_MJPEGB = 8;
    public static final int AV_CODEC_ID_LJPEG = 9;
    public static final int AV_CODEC_ID_SP5X = 10;
    public static final int AV_CODEC_ID_JPEGLS = 11;
    public static final int AV_CODEC_ID_MPEG4 = 12;
    public static final int AV_CODEC_ID_RAWVIDEO = 13;
    public static final int AV_CODEC_ID_MSMPEG4V1 = 14;
    public static final int AV_CODEC_ID_MSMPEG4V2 = 15;
    public static final int AV_CODEC_ID_MSMPEG4V3 = 16;
    public static final int AV_CODEC_ID_WMV1 = 17;
    public static final int AV_CODEC_ID_WMV2 = 18;
    public static final int AV_CODEC_ID_H263P = 19;
    public static final int AV_CODEC_ID_H263I = 20;
    public static final int AV_CODEC_ID_FLV1 = 21;
    public static final int AV_CODEC_ID_SVQ1 = 22;
    public static final int AV_CODEC_ID_SVQ3 = 23;
    public static final int AV_CODEC_ID_DVVIDEO = 24;
    public static final int AV_CODEC_ID_HUFFYUV = 25;
    public static final int AV_CODEC_ID_CYUV = 26;
    public static final int AV_CODEC_ID_H264 = 27;
    public static final int AV_CODEC_ID_INDEO3 = 28;
    public static final int AV_CODEC_ID_VP3 = 29;
    public static final int AV_CODEC_ID_THEORA = 30;
    public static final int AV_CODEC_ID_ASV1 = 31;
    public static final int AV_CODEC_ID_ASV2 = 32;
    public static final int AV_CODEC_ID_FFV1 = 33;
    public static final int AV_CODEC_ID_4XM = 34;
    public static final int AV_CODEC_ID_VCR1 = 35;
    public static final int AV_CODEC_ID_CLJR = 36;
    public static final int AV_CODEC_ID_MDEC = 37;
    public static final int AV_CODEC_ID_ROQ = 38;
    public static final int AV_CODEC_ID_INTERPLAY_VIDEO = 39;
    public static final int AV_CODEC_ID_XAN_WC3 = 40;
    public static final int AV_CODEC_ID_XAN_WC4 = 41;
    public static final int AV_CODEC_ID_RPZA = 42;
    public static final int AV_CODEC_ID_CINEPAK = 43;
    public static final int AV_CODEC_ID_WS_VQA = 44;
    public static final int AV_CODEC_ID_MSRLE = 45;
    public static final int AV_CODEC_ID_MSVIDEO1 = 46;
    public static final int AV_CODEC_ID_IDCIN = 47;
    public static final int AV_CODEC_ID_8BPS = 48;
    public static final int AV_CODEC_ID_SMC = 49;
    public static final int AV_CODEC_ID_FLIC = 50;
    public static final int AV_CODEC_ID_TRUEMOTION1 = 51;
    public static final int AV_CODEC_ID_VMDVIDEO = 52;
    public static final int AV_CODEC_ID_MSZH = 53;
    public static final int AV_CODEC_ID_ZLIB = 54;
    public static final int AV_CODEC_ID_QTRLE = 55;
    public static final int AV_CODEC_ID_TSCC = 56;
    public static final int AV_CODEC_ID_ULTI = 57;
    public static final int AV_CODEC_ID_QDRAW = 58;
    public static final int AV_CODEC_ID_VIXL = 59;
    public static final int AV_CODEC_ID_QPEG = 60;
    public static final int AV_CODEC_ID_PNG = 61;
    public static final int AV_CODEC_ID_PPM = 62;
    public static final int AV_CODEC_ID_PBM = 63;
    public static final int AV_CODEC_ID_PGM = 64;
    public static final int AV_CODEC_ID_PGMYUV = 65;
    public static final int AV_CODEC_ID_PAM = 66;
    public static final int AV_CODEC_ID_FFVHUFF = 67;
    public static final int AV_CODEC_ID_RV30 = 68;
    public static final int AV_CODEC_ID_RV40 = 69;
    public static final int AV_CODEC_ID_VC1 = 70;
    public static final int AV_CODEC_ID_WMV3 = 71;
    public static final int AV_CODEC_ID_LOCO = 72;
    public static final int AV_CODEC_ID_WNV1 = 73;
    public static final int AV_CODEC_ID_AASC = 74;
    public static final int AV_CODEC_ID_INDEO2 = 75;
    public static final int AV_CODEC_ID_FRAPS = 76;
    public static final int AV_CODEC_ID_TRUEMOTION2 = 77;
    public static final int AV_CODEC_ID_BMP = 78;
    public static final int AV_CODEC_ID_CSCD = 79;
    public static final int AV_CODEC_ID_MMVIDEO = 80;
    public static final int AV_CODEC_ID_ZMBV = 81;
    public static final int AV_CODEC_ID_AVS = 82;
    public static final int AV_CODEC_ID_SMACKVIDEO = 83;
    public static final int AV_CODEC_ID_NUV = 84;
    public static final int AV_CODEC_ID_KMVC = 85;
    public static final int AV_CODEC_ID_FLASHSV = 86;
    public static final int AV_CODEC_ID_CAVS = 87;
    public static final int AV_CODEC_ID_JPEG2000 = 88;
    public static final int AV_CODEC_ID_VMNC = 89;
    public static final int AV_CODEC_ID_VP5 = 90;
    public static final int AV_CODEC_ID_VP6 = 91;
    public static final int AV_CODEC_ID_VP6F = 92;
    public static final int AV_CODEC_ID_TARGA = 93;
    public static final int AV_CODEC_ID_DSICINVIDEO = 94;
    public static final int AV_CODEC_ID_TIERTEXSEQVIDEO = 95;
    public static final int AV_CODEC_ID_TIFF = 96;
    public static final int AV_CODEC_ID_GIF = 97;
    public static final int AV_CODEC_ID_DXA = 98;
    public static final int AV_CODEC_ID_DNXHD = 99;
    public static final int AV_CODEC_ID_THP = 100;
    public static final int AV_CODEC_ID_SGI = 101;
    public static final int AV_CODEC_ID_C93 = 102;
    public static final int AV_CODEC_ID_BETHSOFTVID = 103;
    public static final int AV_CODEC_ID_PTX = 104;
    public static final int AV_CODEC_ID_TXD = 105;
    public static final int AV_CODEC_ID_VP6A = 106;
    public static final int AV_CODEC_ID_AMV = 107;
    public static final int AV_CODEC_ID_VB = 108;
    public static final int AV_CODEC_ID_PCX = 109;
    public static final int AV_CODEC_ID_SUNRAST = 110;
    public static final int AV_CODEC_ID_INDEO4 = 111;
    public static final int AV_CODEC_ID_INDEO5 = 112;
    public static final int AV_CODEC_ID_MIMIC = 113;
    public static final int AV_CODEC_ID_RL2 = 114;
    public static final int AV_CODEC_ID_ESCAPE124 = 115;
    public static final int AV_CODEC_ID_DIRAC = 116;
    public static final int AV_CODEC_ID_BFI = 117;
    public static final int AV_CODEC_ID_CMV = 118;
    public static final int AV_CODEC_ID_MOTIONPIXELS = 119;
    public static final int AV_CODEC_ID_TGV = 120;
    public static final int AV_CODEC_ID_TGQ = 121;
    public static final int AV_CODEC_ID_TQI = 122;
    public static final int AV_CODEC_ID_AURA = 123;
    public static final int AV_CODEC_ID_AURA2 = 124;
    public static final int AV_CODEC_ID_V210X = 125;
    public static final int AV_CODEC_ID_TMV = 126;
    public static final int AV_CODEC_ID_V210 = 127;
    public static final int AV_CODEC_ID_DPX = 128;
    public static final int AV_CODEC_ID_MAD = 129;
    public static final int AV_CODEC_ID_FRWU = 130;
    public static final int AV_CODEC_ID_FLASHSV2 = 131;
    public static final int AV_CODEC_ID_CDGRAPHICS = 132;
    public static final int AV_CODEC_ID_R210 = 133;
    public static final int AV_CODEC_ID_ANM = 134;
    public static final int AV_CODEC_ID_BINKVIDEO = 135;
    public static final int AV_CODEC_ID_IFF_ILBM = 136;
    public static final int AV_CODEC_ID_IFF_BYTERUN1 = 136;
    public static final int AV_CODEC_ID_KGV1 = 137;
    public static final int AV_CODEC_ID_YOP = 138;
    public static final int AV_CODEC_ID_VP8 = 139;
    public static final int AV_CODEC_ID_PICTOR = 140;
    public static final int AV_CODEC_ID_ANSI = 141;
    public static final int AV_CODEC_ID_A64_MULTI = 142;
    public static final int AV_CODEC_ID_A64_MULTI5 = 143;
    public static final int AV_CODEC_ID_R10K = 144;
    public static final int AV_CODEC_ID_MXPEG = 145;
    public static final int AV_CODEC_ID_LAGARITH = 146;
    public static final int AV_CODEC_ID_PRORES = 147;
    public static final int AV_CODEC_ID_JV = 148;
    public static final int AV_CODEC_ID_DFA = 149;
    public static final int AV_CODEC_ID_WMV3IMAGE = 150;
    public static final int AV_CODEC_ID_VC1IMAGE = 151;
    public static final int AV_CODEC_ID_UTVIDEO = 152;
    public static final int AV_CODEC_ID_BMV_VIDEO = 153;
    public static final int AV_CODEC_ID_VBLE = 154;
    public static final int AV_CODEC_ID_DXTORY = 155;
    public static final int AV_CODEC_ID_V410 = 156;
    public static final int AV_CODEC_ID_XWD = 157;
    public static final int AV_CODEC_ID_CDXL = 158;
    public static final int AV_CODEC_ID_XBM = 159;
    public static final int AV_CODEC_ID_ZEROCODEC = 160;
    public static final int AV_CODEC_ID_MSS1 = 161;
    public static final int AV_CODEC_ID_MSA1 = 162;
    public static final int AV_CODEC_ID_TSCC2 = 163;
    public static final int AV_CODEC_ID_MTS2 = 164;
    public static final int AV_CODEC_ID_CLLC = 165;
    public static final int AV_CODEC_ID_MSS2 = 166;
    public static final int AV_CODEC_ID_VP9 = 167;
    public static final int AV_CODEC_ID_AIC = 168;
    public static final int AV_CODEC_ID_ESCAPE130 = 169;
    public static final int AV_CODEC_ID_G2M = 170;
    public static final int AV_CODEC_ID_WEBP = 171;
    public static final int AV_CODEC_ID_HNM4_VIDEO = 172;
    public static final int AV_CODEC_ID_HEVC = 173;
    public static final int AV_CODEC_ID_H265 = 173;
    public static final int AV_CODEC_ID_FIC = 174;
    public static final int AV_CODEC_ID_ALIAS_PIX = 175;
    public static final int AV_CODEC_ID_BRENDER_PIX = 176;
    public static final int AV_CODEC_ID_PAF_VIDEO = 177;
    public static final int AV_CODEC_ID_EXR = 178;
    public static final int AV_CODEC_ID_VP7 = 179;
    public static final int AV_CODEC_ID_SANM = 180;
    public static final int AV_CODEC_ID_SGIRLE = 181;
    public static final int AV_CODEC_ID_MVC1 = 182;
    public static final int AV_CODEC_ID_MVC2 = 183;
    public static final int AV_CODEC_ID_HQX = 184;
    public static final int AV_CODEC_ID_TDSC = 185;
    public static final int AV_CODEC_ID_HQ_HQA = 186;
    public static final int AV_CODEC_ID_HAP = 187;
    public static final int AV_CODEC_ID_DDS = 188;
    public static final int AV_CODEC_ID_DXV = 189;
    public static final int AV_CODEC_ID_SCREENPRESSO = 190;
    public static final int AV_CODEC_ID_RSCC = 191;
    public static final int AV_CODEC_ID_AVS2 = 192;
    public static final int AV_CODEC_ID_PGX = 193;
    public static final int AV_CODEC_ID_AVS3 = 194;
    public static final int AV_CODEC_ID_MSP2 = 195;
    public static final int AV_CODEC_ID_VVC = 196;
    public static final int AV_CODEC_ID_H266 = 196;
    public static final int AV_CODEC_ID_Y41P = 32768;
    public static final int AV_CODEC_ID_AVRP = 32769;
    public static final int AV_CODEC_ID_012V = 32770;
    public static final int AV_CODEC_ID_AVUI = 32771;
    public static final int AV_CODEC_ID_AYUV = 32772;
    public static final int AV_CODEC_ID_TARGA_Y216 = 32773;
    public static final int AV_CODEC_ID_V308 = 32774;
    public static final int AV_CODEC_ID_V408 = 32775;
    public static final int AV_CODEC_ID_YUV4 = 32776;
    public static final int AV_CODEC_ID_AVRN = 32777;
    public static final int AV_CODEC_ID_CPIA = 32778;
    public static final int AV_CODEC_ID_XFACE = 32779;
    public static final int AV_CODEC_ID_SNOW = 32780;
    public static final int AV_CODEC_ID_SMVJPEG = 32781;
    public static final int AV_CODEC_ID_APNG = 32782;
    public static final int AV_CODEC_ID_DAALA = 32783;
    public static final int AV_CODEC_ID_CFHD = 32784;
    public static final int AV_CODEC_ID_TRUEMOTION2RT = 32785;
    public static final int AV_CODEC_ID_M101 = 32786;
    public static final int AV_CODEC_ID_MAGICYUV = 32787;
    public static final int AV_CODEC_ID_SHEERVIDEO = 32788;
    public static final int AV_CODEC_ID_YLC = 32789;
    public static final int AV_CODEC_ID_PSD = 32790;
    public static final int AV_CODEC_ID_PIXLET = 32791;
    public static final int AV_CODEC_ID_SPEEDHQ = 32792;
    public static final int AV_CODEC_ID_FMVC = 32793;
    public static final int AV_CODEC_ID_SCPR = 32794;
    public static final int AV_CODEC_ID_CLEARVIDEO = 32795;
    public static final int AV_CODEC_ID_XPM = 32796;
    public static final int AV_CODEC_ID_AV1 = 32797;
    public static final int AV_CODEC_ID_BITPACKED = 32798;
    public static final int AV_CODEC_ID_MSCC = 32799;
    public static final int AV_CODEC_ID_SRGC = 32800;
    public static final int AV_CODEC_ID_SVG = 32801;
    public static final int AV_CODEC_ID_GDV = 32802;
    public static final int AV_CODEC_ID_FITS = 32803;
    public static final int AV_CODEC_ID_IMM4 = 32804;
    public static final int AV_CODEC_ID_PROSUMER = 32805;
    public static final int AV_CODEC_ID_MWSC = 32806;
    public static final int AV_CODEC_ID_WCMV = 32807;
    public static final int AV_CODEC_ID_RASC = 32808;
    public static final int AV_CODEC_ID_HYMT = 32809;
    public static final int AV_CODEC_ID_ARBC = 32810;
    public static final int AV_CODEC_ID_AGM = 32811;
    public static final int AV_CODEC_ID_LSCR = 32812;
    public static final int AV_CODEC_ID_VP4 = 32813;
    public static final int AV_CODEC_ID_IMM5 = 32814;
    public static final int AV_CODEC_ID_MVDV = 32815;
    public static final int AV_CODEC_ID_MVHA = 32816;
    public static final int AV_CODEC_ID_CDTOONS = 32817;
    public static final int AV_CODEC_ID_MV30 = 32818;
    public static final int AV_CODEC_ID_NOTCHLC = 32819;
    public static final int AV_CODEC_ID_PFM = 32820;
    public static final int AV_CODEC_ID_MOBICLIP = 32821;
    public static final int AV_CODEC_ID_PHOTOCD = 32822;
    public static final int AV_CODEC_ID_IPU = 32823;
    public static final int AV_CODEC_ID_ARGO = 32824;
    public static final int AV_CODEC_ID_CRI = 32825;
    public static final int AV_CODEC_ID_SIMBIOSIS_IMX = 32826;
    public static final int AV_CODEC_ID_SGA_VIDEO = 32827;
    public static final int AV_CODEC_ID_FIRST_AUDIO = 65536;
    public static final int AV_CODEC_ID_PCM_S16LE = 65536;
    public static final int AV_CODEC_ID_PCM_S16BE = 65537;
    public static final int AV_CODEC_ID_PCM_U16LE = 65538;
    public static final int AV_CODEC_ID_PCM_U16BE = 65539;
    public static final int AV_CODEC_ID_PCM_S8 = 65540;
    public static final int AV_CODEC_ID_PCM_U8 = 65541;
    public static final int AV_CODEC_ID_PCM_MULAW = 65542;
    public static final int AV_CODEC_ID_PCM_ALAW = 65543;
    public static final int AV_CODEC_ID_PCM_S32LE = 65544;
    public static final int AV_CODEC_ID_PCM_S32BE = 65545;
    public static final int AV_CODEC_ID_PCM_U32LE = 65546;
    public static final int AV_CODEC_ID_PCM_U32BE = 65547;
    public static final int AV_CODEC_ID_PCM_S24LE = 65548;
    public static final int AV_CODEC_ID_PCM_S24BE = 65549;
    public static final int AV_CODEC_ID_PCM_U24LE = 65550;
    public static final int AV_CODEC_ID_PCM_U24BE = 65551;
    public static final int AV_CODEC_ID_PCM_S24DAUD = 65552;
    public static final int AV_CODEC_ID_PCM_ZORK = 65553;
    public static final int AV_CODEC_ID_PCM_S16LE_PLANAR = 65554;
    public static final int AV_CODEC_ID_PCM_DVD = 65555;
    public static final int AV_CODEC_ID_PCM_F32BE = 65556;
    public static final int AV_CODEC_ID_PCM_F32LE = 65557;
    public static final int AV_CODEC_ID_PCM_F64BE = 65558;
    public static final int AV_CODEC_ID_PCM_F64LE = 65559;
    public static final int AV_CODEC_ID_PCM_BLURAY = 65560;
    public static final int AV_CODEC_ID_PCM_LXF = 65561;
    public static final int AV_CODEC_ID_S302M = 65562;
    public static final int AV_CODEC_ID_PCM_S8_PLANAR = 65563;
    public static final int AV_CODEC_ID_PCM_S24LE_PLANAR = 65564;
    public static final int AV_CODEC_ID_PCM_S32LE_PLANAR = 65565;
    public static final int AV_CODEC_ID_PCM_S16BE_PLANAR = 65566;
    public static final int AV_CODEC_ID_PCM_S64LE = 67584;
    public static final int AV_CODEC_ID_PCM_S64BE = 67585;
    public static final int AV_CODEC_ID_PCM_F16LE = 67586;
    public static final int AV_CODEC_ID_PCM_F24LE = 67587;
    public static final int AV_CODEC_ID_PCM_VIDC = 67588;
    public static final int AV_CODEC_ID_PCM_SGA = 67589;
    public static final int AV_CODEC_ID_ADPCM_IMA_QT = 69632;
    public static final int AV_CODEC_ID_ADPCM_IMA_WAV = 69633;
    public static final int AV_CODEC_ID_ADPCM_IMA_DK3 = 69634;
    public static final int AV_CODEC_ID_ADPCM_IMA_DK4 = 69635;
    public static final int AV_CODEC_ID_ADPCM_IMA_WS = 69636;
    public static final int AV_CODEC_ID_ADPCM_IMA_SMJPEG = 69637;
    public static final int AV_CODEC_ID_ADPCM_MS = 69638;
    public static final int AV_CODEC_ID_ADPCM_4XM = 69639;
    public static final int AV_CODEC_ID_ADPCM_XA = 69640;
    public static final int AV_CODEC_ID_ADPCM_ADX = 69641;
    public static final int AV_CODEC_ID_ADPCM_EA = 69642;
    public static final int AV_CODEC_ID_ADPCM_G726 = 69643;
    public static final int AV_CODEC_ID_ADPCM_CT = 69644;
    public static final int AV_CODEC_ID_ADPCM_SWF = 69645;
    public static final int AV_CODEC_ID_ADPCM_YAMAHA = 69646;
    public static final int AV_CODEC_ID_ADPCM_SBPRO_4 = 69647;
    public static final int AV_CODEC_ID_ADPCM_SBPRO_3 = 69648;
    public static final int AV_CODEC_ID_ADPCM_SBPRO_2 = 69649;
    public static final int AV_CODEC_ID_ADPCM_THP = 69650;
    public static final int AV_CODEC_ID_ADPCM_IMA_AMV = 69651;
    public static final int AV_CODEC_ID_ADPCM_EA_R1 = 69652;
    public static final int AV_CODEC_ID_ADPCM_EA_R3 = 69653;
    public static final int AV_CODEC_ID_ADPCM_EA_R2 = 69654;
    public static final int AV_CODEC_ID_ADPCM_IMA_EA_SEAD = 69655;
    public static final int AV_CODEC_ID_ADPCM_IMA_EA_EACS = 69656;
    public static final int AV_CODEC_ID_ADPCM_EA_XAS = 69657;
    public static final int AV_CODEC_ID_ADPCM_EA_MAXIS_XA = 69658;
    public static final int AV_CODEC_ID_ADPCM_IMA_ISS = 69659;
    public static final int AV_CODEC_ID_ADPCM_G722 = 69660;
    public static final int AV_CODEC_ID_ADPCM_IMA_APC = 69661;
    public static final int AV_CODEC_ID_ADPCM_VIMA = 69662;
    public static final int AV_CODEC_ID_ADPCM_AFC = 71680;
    public static final int AV_CODEC_ID_ADPCM_IMA_OKI = 71681;
    public static final int AV_CODEC_ID_ADPCM_DTK = 71682;
    public static final int AV_CODEC_ID_ADPCM_IMA_RAD = 71683;
    public static final int AV_CODEC_ID_ADPCM_G726LE = 71684;
    public static final int AV_CODEC_ID_ADPCM_THP_LE = 71685;
    public static final int AV_CODEC_ID_ADPCM_PSX = 71686;
    public static final int AV_CODEC_ID_ADPCM_AICA = 71687;
    public static final int AV_CODEC_ID_ADPCM_IMA_DAT4 = 71688;
    public static final int AV_CODEC_ID_ADPCM_MTAF = 71689;
    public static final int AV_CODEC_ID_ADPCM_AGM = 71690;
    public static final int AV_CODEC_ID_ADPCM_ARGO = 71691;
    public static final int AV_CODEC_ID_ADPCM_IMA_SSI = 71692;
    public static final int AV_CODEC_ID_ADPCM_ZORK = 71693;
    public static final int AV_CODEC_ID_ADPCM_IMA_APM = 71694;
    public static final int AV_CODEC_ID_ADPCM_IMA_ALP = 71695;
    public static final int AV_CODEC_ID_ADPCM_IMA_MTF = 71696;
    public static final int AV_CODEC_ID_ADPCM_IMA_CUNNING = 71697;
    public static final int AV_CODEC_ID_ADPCM_IMA_MOFLEX = 71698;
    public static final int AV_CODEC_ID_AMR_NB = 73728;
    public static final int AV_CODEC_ID_AMR_WB = 73729;
    public static final int AV_CODEC_ID_RA_144 = 77824;
    public static final int AV_CODEC_ID_RA_288 = 77825;
    public static final int AV_CODEC_ID_ROQ_DPCM = 81920;
    public static final int AV_CODEC_ID_INTERPLAY_DPCM = 81921;
    public static final int AV_CODEC_ID_XAN_DPCM = 81922;
    public static final int AV_CODEC_ID_SOL_DPCM = 81923;
    public static final int AV_CODEC_ID_SDX2_DPCM = 83968;
    public static final int AV_CODEC_ID_GREMLIN_DPCM = 83969;
    public static final int AV_CODEC_ID_DERF_DPCM = 83970;
    public static final int AV_CODEC_ID_MP2 = 86016;
    public static final int AV_CODEC_ID_MP3 = 86017;
    public static final int AV_CODEC_ID_AAC = 86018;
    public static final int AV_CODEC_ID_AC3 = 86019;
    public static final int AV_CODEC_ID_DTS = 86020;
    public static final int AV_CODEC_ID_VORBIS = 86021;
    public static final int AV_CODEC_ID_DVAUDIO = 86022;
    public static final int AV_CODEC_ID_WMAV1 = 86023;
    public static final int AV_CODEC_ID_WMAV2 = 86024;
    public static final int AV_CODEC_ID_MACE3 = 86025;
    public static final int AV_CODEC_ID_MACE6 = 86026;
    public static final int AV_CODEC_ID_VMDAUDIO = 86027;
    public static final int AV_CODEC_ID_FLAC = 86028;
    public static final int AV_CODEC_ID_MP3ADU = 86029;
    public static final int AV_CODEC_ID_MP3ON4 = 86030;
    public static final int AV_CODEC_ID_SHORTEN = 86031;
    public static final int AV_CODEC_ID_ALAC = 86032;
    public static final int AV_CODEC_ID_WESTWOOD_SND1 = 86033;
    public static final int AV_CODEC_ID_GSM = 86034;
    public static final int AV_CODEC_ID_QDM2 = 86035;
    public static final int AV_CODEC_ID_COOK = 86036;
    public static final int AV_CODEC_ID_TRUESPEECH = 86037;
    public static final int AV_CODEC_ID_TTA = 86038;
    public static final int AV_CODEC_ID_SMACKAUDIO = 86039;
    public static final int AV_CODEC_ID_QCELP = 86040;
    public static final int AV_CODEC_ID_WAVPACK = 86041;
    public static final int AV_CODEC_ID_DSICINAUDIO = 86042;
    public static final int AV_CODEC_ID_IMC = 86043;
    public static final int AV_CODEC_ID_MUSEPACK7 = 86044;
    public static final int AV_CODEC_ID_MLP = 86045;
    public static final int AV_CODEC_ID_GSM_MS = 86046;
    public static final int AV_CODEC_ID_ATRAC3 = 86047;
    public static final int AV_CODEC_ID_APE = 86048;
    public static final int AV_CODEC_ID_NELLYMOSER = 86049;
    public static final int AV_CODEC_ID_MUSEPACK8 = 86050;
    public static final int AV_CODEC_ID_SPEEX = 86051;
    public static final int AV_CODEC_ID_WMAVOICE = 86052;
    public static final int AV_CODEC_ID_WMAPRO = 86053;
    public static final int AV_CODEC_ID_WMALOSSLESS = 86054;
    public static final int AV_CODEC_ID_ATRAC3P = 86055;
    public static final int AV_CODEC_ID_EAC3 = 86056;
    public static final int AV_CODEC_ID_SIPR = 86057;
    public static final int AV_CODEC_ID_MP1 = 86058;
    public static final int AV_CODEC_ID_TWINVQ = 86059;
    public static final int AV_CODEC_ID_TRUEHD = 86060;
    public static final int AV_CODEC_ID_MP4ALS = 86061;
    public static final int AV_CODEC_ID_ATRAC1 = 86062;
    public static final int AV_CODEC_ID_BINKAUDIO_RDFT = 86063;
    public static final int AV_CODEC_ID_BINKAUDIO_DCT = 86064;
    public static final int AV_CODEC_ID_AAC_LATM = 86065;
    public static final int AV_CODEC_ID_QDMC = 86066;
    public static final int AV_CODEC_ID_CELT = 86067;
    public static final int AV_CODEC_ID_G723_1 = 86068;
    public static final int AV_CODEC_ID_G729 = 86069;
    public static final int AV_CODEC_ID_8SVX_EXP = 86070;
    public static final int AV_CODEC_ID_8SVX_FIB = 86071;
    public static final int AV_CODEC_ID_BMV_AUDIO = 86072;
    public static final int AV_CODEC_ID_RALF = 86073;
    public static final int AV_CODEC_ID_IAC = 86074;
    public static final int AV_CODEC_ID_ILBC = 86075;
    public static final int AV_CODEC_ID_OPUS = 86076;
    public static final int AV_CODEC_ID_COMFORT_NOISE = 86077;
    public static final int AV_CODEC_ID_TAK = 86078;
    public static final int AV_CODEC_ID_METASOUND = 86079;
    public static final int AV_CODEC_ID_PAF_AUDIO = 86080;
    public static final int AV_CODEC_ID_ON2AVC = 86081;
    public static final int AV_CODEC_ID_DSS_SP = 86082;
    public static final int AV_CODEC_ID_CODEC2 = 86083;
    public static final int AV_CODEC_ID_FFWAVESYNTH = 88064;
    public static final int AV_CODEC_ID_SONIC = 88065;
    public static final int AV_CODEC_ID_SONIC_LS = 88066;
    public static final int AV_CODEC_ID_EVRC = 88067;
    public static final int AV_CODEC_ID_SMV = 88068;
    public static final int AV_CODEC_ID_DSD_LSBF = 88069;
    public static final int AV_CODEC_ID_DSD_MSBF = 88070;
    public static final int AV_CODEC_ID_DSD_LSBF_PLANAR = 88071;
    public static final int AV_CODEC_ID_DSD_MSBF_PLANAR = 88072;
    public static final int AV_CODEC_ID_4GV = 88073;
    public static final int AV_CODEC_ID_INTERPLAY_ACM = 88074;
    public static final int AV_CODEC_ID_XMA1 = 88075;
    public static final int AV_CODEC_ID_XMA2 = 88076;
    public static final int AV_CODEC_ID_DST = 88077;
    public static final int AV_CODEC_ID_ATRAC3AL = 88078;
    public static final int AV_CODEC_ID_ATRAC3PAL = 88079;
    public static final int AV_CODEC_ID_DOLBY_E = 88080;
    public static final int AV_CODEC_ID_APTX = 88081;
    public static final int AV_CODEC_ID_APTX_HD = 88082;
    public static final int AV_CODEC_ID_SBC = 88083;
    public static final int AV_CODEC_ID_ATRAC9 = 88084;
    public static final int AV_CODEC_ID_HCOM = 88085;
    public static final int AV_CODEC_ID_ACELP_KELVIN = 88086;
    public static final int AV_CODEC_ID_MPEGH_3D_AUDIO = 88087;
    public static final int AV_CODEC_ID_SIREN = 88088;
    public static final int AV_CODEC_ID_HCA = 88089;
    public static final int AV_CODEC_ID_FASTAUDIO = 88090;
    public static final int AV_CODEC_ID_FIRST_SUBTITLE = 94208;
    public static final int AV_CODEC_ID_DVD_SUBTITLE = 94208;
    public static final int AV_CODEC_ID_DVB_SUBTITLE = 94209;
    public static final int AV_CODEC_ID_TEXT = 94210;
    public static final int AV_CODEC_ID_XSUB = 94211;
    public static final int AV_CODEC_ID_SSA = 94212;
    public static final int AV_CODEC_ID_MOV_TEXT = 94213;
    public static final int AV_CODEC_ID_HDMV_PGS_SUBTITLE = 94214;
    public static final int AV_CODEC_ID_DVB_TELETEXT = 94215;
    public static final int AV_CODEC_ID_SRT = 94216;
    public static final int AV_CODEC_ID_MICRODVD = 96256;
    public static final int AV_CODEC_ID_EIA_608 = 96257;
    public static final int AV_CODEC_ID_JACOSUB = 96258;
    public static final int AV_CODEC_ID_SAMI = 96259;
    public static final int AV_CODEC_ID_REALTEXT = 96260;
    public static final int AV_CODEC_ID_STL = 96261;
    public static final int AV_CODEC_ID_SUBVIEWER1 = 96262;
    public static final int AV_CODEC_ID_SUBVIEWER = 96263;
    public static final int AV_CODEC_ID_SUBRIP = 96264;
    public static final int AV_CODEC_ID_WEBVTT = 96265;
    public static final int AV_CODEC_ID_MPL2 = 96266;
    public static final int AV_CODEC_ID_VPLAYER = 96267;
    public static final int AV_CODEC_ID_PJS = 96268;
    public static final int AV_CODEC_ID_ASS = 96269;
    public static final int AV_CODEC_ID_HDMV_TEXT_SUBTITLE = 96270;
    public static final int AV_CODEC_ID_TTML = 96271;
    public static final int AV_CODEC_ID_ARIB_CAPTION = 96272;
    public static final int AV_CODEC_ID_FIRST_UNKNOWN = 98304;
    public static final int AV_CODEC_ID_TTF = 98304;
    public static final int AV_CODEC_ID_SCTE_35 = 98305;
    public static final int AV_CODEC_ID_EPG = 98306;
    public static final int AV_CODEC_ID_BINTEXT = 100352;
    public static final int AV_CODEC_ID_XBIN = 100353;
    public static final int AV_CODEC_ID_IDF = 100354;
    public static final int AV_CODEC_ID_OTF = 100355;
    public static final int AV_CODEC_ID_SMPTE_KLV = 100356;
    public static final int AV_CODEC_ID_DVD_NAV = 100357;
    public static final int AV_CODEC_ID_TIMED_ID3 = 100358;
    public static final int AV_CODEC_ID_BIN_DATA = 100359;
    public static final int AV_CODEC_ID_PROBE = 102400;
    public static final int AV_CODEC_ID_MPEG2TS = 131072;
    public static final int AV_CODEC_ID_MPEG4SYSTEMS = 131073;
    public static final int AV_CODEC_ID_FFMETADATA = 135168;
    public static final int AV_CODEC_ID_WRAPPED_AVFRAME = 135169;
    public static final int AV_CODEC_PROP_INTRA_ONLY = 1;
    public static final int AV_CODEC_PROP_LOSSY = 2;
    public static final int AV_CODEC_PROP_LOSSLESS = 4;
    public static final int AV_CODEC_PROP_REORDER = 8;
    public static final int AV_CODEC_PROP_BITMAP_SUB = 65536;
    public static final int AV_CODEC_PROP_TEXT_SUB = 131072;
    public static final int AV_FIELD_UNKNOWN = 0;
    public static final int AV_FIELD_PROGRESSIVE = 1;
    public static final int AV_FIELD_TT = 2;
    public static final int AV_FIELD_BB = 3;
    public static final int AV_FIELD_TB = 4;
    public static final int AV_FIELD_BT = 5;
    public static final int AV_PKT_DATA_PALETTE = 0;
    public static final int AV_PKT_DATA_NEW_EXTRADATA = 1;
    public static final int AV_PKT_DATA_PARAM_CHANGE = 2;
    public static final int AV_PKT_DATA_H263_MB_INFO = 3;
    public static final int AV_PKT_DATA_REPLAYGAIN = 4;
    public static final int AV_PKT_DATA_DISPLAYMATRIX = 5;
    public static final int AV_PKT_DATA_STEREO3D = 6;
    public static final int AV_PKT_DATA_AUDIO_SERVICE_TYPE = 7;
    public static final int AV_PKT_DATA_QUALITY_STATS = 8;
    public static final int AV_PKT_DATA_FALLBACK_TRACK = 9;
    public static final int AV_PKT_DATA_CPB_PROPERTIES = 10;
    public static final int AV_PKT_DATA_SKIP_SAMPLES = 11;
    public static final int AV_PKT_DATA_JP_DUALMONO = 12;
    public static final int AV_PKT_DATA_STRINGS_METADATA = 13;
    public static final int AV_PKT_DATA_SUBTITLE_POSITION = 14;
    public static final int AV_PKT_DATA_MATROSKA_BLOCKADDITIONAL = 15;
    public static final int AV_PKT_DATA_WEBVTT_IDENTIFIER = 16;
    public static final int AV_PKT_DATA_WEBVTT_SETTINGS = 17;
    public static final int AV_PKT_DATA_METADATA_UPDATE = 18;
    public static final int AV_PKT_DATA_MPEGTS_STREAM_ID = 19;
    public static final int AV_PKT_DATA_MASTERING_DISPLAY_METADATA = 20;
    public static final int AV_PKT_DATA_SPHERICAL = 21;
    public static final int AV_PKT_DATA_CONTENT_LIGHT_LEVEL = 22;
    public static final int AV_PKT_DATA_A53_CC = 23;
    public static final int AV_PKT_DATA_ENCRYPTION_INIT_INFO = 24;
    public static final int AV_PKT_DATA_ENCRYPTION_INFO = 25;
    public static final int AV_PKT_DATA_AFD = 26;
    public static final int AV_PKT_DATA_PRFT = 27;
    public static final int AV_PKT_DATA_ICC_PROFILE = 28;
    public static final int AV_PKT_DATA_DOVI_CONF = 29;
    public static final int AV_PKT_DATA_S12M_TIMECODE = 30;
    public static final int AV_PKT_DATA_NB = 31;
    public static final int AV_PKT_DATA_QUALITY_FACTOR = 8;
    public static final int AV_PKT_FLAG_KEY = 1;
    public static final int AV_PKT_FLAG_CORRUPT = 2;
    public static final int AV_PKT_FLAG_DISCARD = 4;
    public static final int AV_PKT_FLAG_TRUSTED = 8;
    public static final int AV_PKT_FLAG_DISPOSABLE = 16;
    public static final int AV_SIDE_DATA_PARAM_CHANGE_CHANNEL_COUNT = 1;
    public static final int AV_SIDE_DATA_PARAM_CHANGE_CHANNEL_LAYOUT = 2;
    public static final int AV_SIDE_DATA_PARAM_CHANGE_SAMPLE_RATE = 4;
    public static final int AV_SIDE_DATA_PARAM_CHANGE_DIMENSIONS = 8;
    public static final int AV_CODEC_CAP_DRAW_HORIZ_BAND = 1;
    public static final int AV_CODEC_CAP_DR1 = 2;
    public static final int AV_CODEC_CAP_TRUNCATED = 8;
    public static final int AV_CODEC_CAP_DELAY = 32;
    public static final int AV_CODEC_CAP_SMALL_LAST_FRAME = 64;
    public static final int AV_CODEC_CAP_SUBFRAMES = 256;
    public static final int AV_CODEC_CAP_EXPERIMENTAL = 512;
    public static final int AV_CODEC_CAP_CHANNEL_CONF = 1024;
    public static final int AV_CODEC_CAP_FRAME_THREADS = 4096;
    public static final int AV_CODEC_CAP_SLICE_THREADS = 8192;
    public static final int AV_CODEC_CAP_PARAM_CHANGE = 16384;
    public static final int AV_CODEC_CAP_OTHER_THREADS = 32768;
    public static final int AV_CODEC_CAP_AUTO_THREADS = 32768;
    public static final int AV_CODEC_CAP_VARIABLE_FRAME_SIZE = 65536;
    public static final int AV_CODEC_CAP_AVOID_PROBING = 131072;
    public static final int AV_CODEC_CAP_INTRA_ONLY = 0x40000000;
    public static final int AV_CODEC_CAP_LOSSLESS = Integer.MIN_VALUE;
    public static final int AV_CODEC_CAP_HARDWARE = 262144;
    public static final int AV_CODEC_CAP_HYBRID = 524288;
    public static final int AV_CODEC_CAP_ENCODER_REORDERED_OPAQUE = 0x100000;
    public static final int AV_CODEC_CAP_ENCODER_FLUSH = 0x200000;
    public static final int AV_CODEC_HW_CONFIG_METHOD_HW_DEVICE_CTX = 1;
    public static final int AV_CODEC_HW_CONFIG_METHOD_HW_FRAMES_CTX = 2;
    public static final int AV_CODEC_HW_CONFIG_METHOD_INTERNAL = 4;
    public static final int AV_CODEC_HW_CONFIG_METHOD_AD_HOC = 8;
    public static final int AV_INPUT_BUFFER_PADDING_SIZE = 64;
    public static final int AV_INPUT_BUFFER_MIN_SIZE = 16384;
    public static final int AVDISCARD_NONE = -16;
    public static final int AVDISCARD_DEFAULT = 0;
    public static final int AVDISCARD_NONREF = 8;
    public static final int AVDISCARD_BIDIR = 16;
    public static final int AVDISCARD_NONINTRA = 24;
    public static final int AVDISCARD_NONKEY = 32;
    public static final int AVDISCARD_ALL = 48;
    public static final int AV_AUDIO_SERVICE_TYPE_MAIN = 0;
    public static final int AV_AUDIO_SERVICE_TYPE_EFFECTS = 1;
    public static final int AV_AUDIO_SERVICE_TYPE_VISUALLY_IMPAIRED = 2;
    public static final int AV_AUDIO_SERVICE_TYPE_HEARING_IMPAIRED = 3;
    public static final int AV_AUDIO_SERVICE_TYPE_DIALOGUE = 4;
    public static final int AV_AUDIO_SERVICE_TYPE_COMMENTARY = 5;
    public static final int AV_AUDIO_SERVICE_TYPE_EMERGENCY = 6;
    public static final int AV_AUDIO_SERVICE_TYPE_VOICE_OVER = 7;
    public static final int AV_AUDIO_SERVICE_TYPE_KARAOKE = 8;
    public static final int AV_AUDIO_SERVICE_TYPE_NB = 9;
    public static final int AV_CODEC_FLAG_UNALIGNED = 1;
    public static final int AV_CODEC_FLAG_QSCALE = 2;
    public static final int AV_CODEC_FLAG_4MV = 4;
    public static final int AV_CODEC_FLAG_OUTPUT_CORRUPT = 8;
    public static final int AV_CODEC_FLAG_QPEL = 16;
    public static final int AV_CODEC_FLAG_DROPCHANGED = 32;
    public static final int AV_CODEC_FLAG_PASS1 = 512;
    public static final int AV_CODEC_FLAG_PASS2 = 1024;
    public static final int AV_CODEC_FLAG_LOOP_FILTER = 2048;
    public static final int AV_CODEC_FLAG_GRAY = 8192;
    public static final int AV_CODEC_FLAG_PSNR = 32768;
    public static final int AV_CODEC_FLAG_TRUNCATED = 65536;
    public static final int AV_CODEC_FLAG_INTERLACED_DCT = 262144;
    public static final int AV_CODEC_FLAG_LOW_DELAY = 524288;
    public static final int AV_CODEC_FLAG_GLOBAL_HEADER = 0x400000;
    public static final int AV_CODEC_FLAG_BITEXACT = 0x800000;
    public static final int AV_CODEC_FLAG_AC_PRED = 0x1000000;
    public static final int AV_CODEC_FLAG_INTERLACED_ME = 0x20000000;
    public static final int AV_CODEC_FLAG_CLOSED_GOP = Integer.MIN_VALUE;
    public static final int AV_CODEC_FLAG2_FAST = 1;
    public static final int AV_CODEC_FLAG2_NO_OUTPUT = 4;
    public static final int AV_CODEC_FLAG2_LOCAL_HEADER = 8;
    public static final int AV_CODEC_FLAG2_DROP_FRAME_TIMECODE = 8192;
    public static final int AV_CODEC_FLAG2_CHUNKS = 32768;
    public static final int AV_CODEC_FLAG2_IGNORE_CROP = 65536;
    public static final int AV_CODEC_FLAG2_SHOW_ALL = 0x400000;
    public static final int AV_CODEC_FLAG2_EXPORT_MVS = 0x10000000;
    public static final int AV_CODEC_FLAG2_SKIP_MANUAL = 0x20000000;
    public static final int AV_CODEC_FLAG2_RO_FLUSH_NOOP = 0x40000000;
    public static final int AV_CODEC_EXPORT_DATA_MVS = 1;
    public static final int AV_CODEC_EXPORT_DATA_PRFT = 2;
    public static final int AV_CODEC_EXPORT_DATA_VIDEO_ENC_PARAMS = 4;
    public static final int AV_CODEC_EXPORT_DATA_FILM_GRAIN = 8;
    public static final int AV_GET_BUFFER_FLAG_REF = 1;
    public static final int AV_GET_ENCODE_BUFFER_FLAG_REF = 1;
    public static final int AV_HWACCEL_CODEC_CAP_EXPERIMENTAL = 512;
    public static final int AV_HWACCEL_FLAG_IGNORE_LEVEL = 1;
    public static final int AV_HWACCEL_FLAG_ALLOW_HIGH_DEPTH = 2;
    public static final int AV_HWACCEL_FLAG_ALLOW_PROFILE_MISMATCH = 4;
    public static final int SUBTITLE_NONE = 0;
    public static final int SUBTITLE_BITMAP = 1;
    public static final int SUBTITLE_TEXT = 2;
    public static final int SUBTITLE_ASS = 3;
    public static final int AV_SUBTITLE_FLAG_FORCED = 1;
    public static final int AV_PICTURE_STRUCTURE_UNKNOWN = 0;
    public static final int AV_PICTURE_STRUCTURE_TOP_FIELD = 1;
    public static final int AV_PICTURE_STRUCTURE_BOTTOM_FIELD = 2;
    public static final int AV_PICTURE_STRUCTURE_FRAME = 3;
    public static final int AV_LOCK_CREATE = 0;
    public static final int AV_LOCK_OBTAIN = 1;
    public static final int AV_LOCK_RELEASE = 2;
    public static final int AV_LOCK_DESTROY = 3;
    public static final int DFT_R2C = 0;
    public static final int IDFT_C2R = 1;
    public static final int IDFT_R2C = 2;
    public static final int DFT_C2R = 3;
    public static final int DCT_II = 0;
    public static final int DCT_III = 1;
    public static final int DCT_I = 2;
    public static final int DST_I = 3;

    @NoException
    @Cast(value={"AVMediaType"})
    public static native int avcodec_get_type(@Cast(value={"AVCodecID"}) int var0);

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avcodec_get_name(@Cast(value={"AVCodecID"}) int var0);

    @NoException
    @Const
    public static native AVCodecDescriptor avcodec_descriptor_get(@Cast(value={"AVCodecID"}) int var0);

    @NoException
    @Const
    public static native AVCodecDescriptor avcodec_descriptor_next(@Const AVCodecDescriptor var0);

    @NoException
    @Const
    public static native AVCodecDescriptor avcodec_descriptor_get_by_name(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    @Const
    public static native AVCodecDescriptor avcodec_descriptor_get_by_name(String var0);

    @NoException
    public static native AVCodecParameters avcodec_parameters_alloc();

    @NoException
    public static native void avcodec_parameters_free(@Cast(value={"AVCodecParameters**"}) PointerPointer var0);

    @NoException
    public static native void avcodec_parameters_free(@ByPtrPtr AVCodecParameters var0);

    @NoException
    public static native int avcodec_parameters_copy(AVCodecParameters var0, @Const AVCodecParameters var1);

    @NoException
    public static native AVPacket av_packet_alloc();

    @NoException
    public static native AVPacket av_packet_clone(@Const AVPacket var0);

    @NoException
    public static native void av_packet_free(@Cast(value={"AVPacket**"}) PointerPointer var0);

    @NoException
    public static native void av_packet_free(@ByPtrPtr AVPacket var0);

    @NoException
    @Deprecated
    public static native void av_init_packet(AVPacket var0);

    @NoException
    public static native int av_new_packet(AVPacket var0, int var1);

    @NoException
    public static native void av_shrink_packet(AVPacket var0, int var1);

    @NoException
    public static native int av_grow_packet(AVPacket var0, int var1);

    @NoException
    public static native int av_packet_from_data(AVPacket var0, @Cast(value={"uint8_t*"}) BytePointer var1, int var2);

    @NoException
    public static native int av_packet_from_data(AVPacket var0, @Cast(value={"uint8_t*"}) ByteBuffer var1, int var2);

    @NoException
    public static native int av_packet_from_data(AVPacket var0, @Cast(value={"uint8_t*"}) byte[] var1, int var2);

    @NoException
    @Deprecated
    public static native int av_dup_packet(AVPacket var0);

    @NoException
    @Deprecated
    public static native int av_copy_packet(AVPacket var0, @Const AVPacket var1);

    @NoException
    @Deprecated
    public static native int av_copy_packet_side_data(AVPacket var0, @Const AVPacket var1);

    @NoException
    @Deprecated
    public static native void av_free_packet(AVPacket var0);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native BytePointer av_packet_new_side_data(AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, int var2);

    @NoException
    public static native int av_packet_add_side_data(AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, @Cast(value={"uint8_t*"}) BytePointer var2, @Cast(value={"size_t"}) long var3);

    @NoException
    public static native int av_packet_add_side_data(AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, @Cast(value={"uint8_t*"}) ByteBuffer var2, @Cast(value={"size_t"}) long var3);

    @NoException
    public static native int av_packet_add_side_data(AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, @Cast(value={"uint8_t*"}) byte[] var2, @Cast(value={"size_t"}) long var3);

    @NoException
    public static native int av_packet_shrink_side_data(AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, int var2);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native BytePointer av_packet_get_side_data(@Const AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, IntPointer var2);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native ByteBuffer av_packet_get_side_data(@Const AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, IntBuffer var2);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native byte[] av_packet_get_side_data(@Const AVPacket var0, @Cast(value={"AVPacketSideDataType"}) int var1, int[] var2);

    @NoException
    @Deprecated
    public static native int av_packet_merge_side_data(AVPacket var0);

    @NoException
    @Deprecated
    public static native int av_packet_split_side_data(AVPacket var0);

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer av_packet_side_data_name(@Cast(value={"AVPacketSideDataType"}) int var0);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native BytePointer av_packet_pack_dictionary(AVDictionary var0, IntPointer var1);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native ByteBuffer av_packet_pack_dictionary(AVDictionary var0, IntBuffer var1);

    @NoException
    @Cast(value={"uint8_t*"})
    public static native byte[] av_packet_pack_dictionary(AVDictionary var0, int[] var1);

    @NoException
    public static native int av_packet_unpack_dictionary(@Cast(value={"const uint8_t*"}) BytePointer var0, int var1, @Cast(value={"AVDictionary**"}) PointerPointer var2);

    @NoException
    public static native int av_packet_unpack_dictionary(@Cast(value={"const uint8_t*"}) BytePointer var0, int var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native int av_packet_unpack_dictionary(@Cast(value={"const uint8_t*"}) ByteBuffer var0, int var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native int av_packet_unpack_dictionary(@Cast(value={"const uint8_t*"}) byte[] var0, int var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native void av_packet_free_side_data(AVPacket var0);

    @NoException
    public static native int av_packet_ref(AVPacket var0, @Const AVPacket var1);

    @NoException
    public static native void av_packet_unref(AVPacket var0);

    @NoException
    public static native void av_packet_move_ref(AVPacket var0, AVPacket var1);

    @NoException
    public static native int av_packet_copy_props(AVPacket var0, @Const AVPacket var1);

    @NoException
    public static native int av_packet_make_refcounted(AVPacket var0);

    @NoException
    public static native int av_packet_make_writable(AVPacket var0);

    @NoException
    public static native void av_packet_rescale_ts(AVPacket var0, @ByVal AVRational var1, @ByVal AVRational var2);

    @NoException
    @Const
    public static native AVBitStreamFilter av_bsf_get_by_name(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    @Const
    public static native AVBitStreamFilter av_bsf_get_by_name(String var0);

    @NoException
    @Const
    public static native AVBitStreamFilter av_bsf_iterate(@Cast(value={"void**"}) PointerPointer var0);

    @NoException
    @Const
    public static native AVBitStreamFilter av_bsf_iterate(@Cast(value={"void**"}) @ByPtrPtr Pointer var0);

    @NoException
    public static native int av_bsf_alloc(@Const AVBitStreamFilter var0, @Cast(value={"AVBSFContext**"}) PointerPointer var1);

    @NoException
    public static native int av_bsf_alloc(@Const AVBitStreamFilter var0, @ByPtrPtr AVBSFContext var1);

    @NoException
    public static native int av_bsf_init(AVBSFContext var0);

    @NoException
    public static native int av_bsf_send_packet(AVBSFContext var0, AVPacket var1);

    @NoException
    public static native int av_bsf_receive_packet(AVBSFContext var0, AVPacket var1);

    @NoException
    public static native void av_bsf_flush(AVBSFContext var0);

    @NoException
    public static native void av_bsf_free(@Cast(value={"AVBSFContext**"}) PointerPointer var0);

    @NoException
    public static native void av_bsf_free(@ByPtrPtr AVBSFContext var0);

    @NoException
    @Const
    public static native AVClass av_bsf_get_class();

    @NoException
    public static native AVBSFList av_bsf_list_alloc();

    @NoException
    public static native void av_bsf_list_free(@Cast(value={"AVBSFList**"}) PointerPointer var0);

    @NoException
    public static native void av_bsf_list_free(@ByPtrPtr AVBSFList var0);

    @NoException
    public static native int av_bsf_list_append(AVBSFList var0, AVBSFContext var1);

    @NoException
    public static native int av_bsf_list_append2(AVBSFList var0, @Cast(value={"const char*"}) BytePointer var1, @Cast(value={"AVDictionary**"}) PointerPointer var2);

    @NoException
    public static native int av_bsf_list_append2(AVBSFList var0, @Cast(value={"const char*"}) BytePointer var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native int av_bsf_list_append2(AVBSFList var0, String var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native int av_bsf_list_finalize(@Cast(value={"AVBSFList**"}) PointerPointer var0, @Cast(value={"AVBSFContext**"}) PointerPointer var1);

    @NoException
    public static native int av_bsf_list_finalize(@ByPtrPtr AVBSFList var0, @ByPtrPtr AVBSFContext var1);

    @NoException
    public static native int av_bsf_list_parse_str(@Cast(value={"const char*"}) BytePointer var0, @Cast(value={"AVBSFContext**"}) PointerPointer var1);

    @NoException
    public static native int av_bsf_list_parse_str(@Cast(value={"const char*"}) BytePointer var0, @ByPtrPtr AVBSFContext var1);

    @NoException
    public static native int av_bsf_list_parse_str(String var0, @ByPtrPtr AVBSFContext var1);

    @NoException
    public static native int av_bsf_get_null_filter(@Cast(value={"AVBSFContext**"}) PointerPointer var0);

    @NoException
    public static native int av_bsf_get_null_filter(@ByPtrPtr AVBSFContext var0);

    @NoException
    @Const
    public static native AVCodec av_codec_iterate(@Cast(value={"void**"}) PointerPointer var0);

    @NoException
    @Const
    public static native AVCodec av_codec_iterate(@Cast(value={"void**"}) @ByPtrPtr Pointer var0);

    @NoException
    public static native AVCodec avcodec_find_decoder(@Cast(value={"AVCodecID"}) int var0);

    @NoException
    public static native AVCodec avcodec_find_decoder_by_name(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    public static native AVCodec avcodec_find_decoder_by_name(String var0);

    @NoException
    public static native AVCodec avcodec_find_encoder(@Cast(value={"AVCodecID"}) int var0);

    @NoException
    public static native AVCodec avcodec_find_encoder_by_name(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    public static native AVCodec avcodec_find_encoder_by_name(String var0);

    @NoException
    public static native int av_codec_is_encoder(@Const AVCodec var0);

    @NoException
    public static native int av_codec_is_decoder(@Const AVCodec var0);

    @NoException
    @Const
    public static native AVCodecHWConfig avcodec_get_hw_config(@Const AVCodec var0, int var1);

    @NoException
    @Deprecated
    @ByVal
    public static native AVRational av_codec_get_pkt_timebase(@Const AVCodecContext var0);

    @NoException
    @Deprecated
    public static native void av_codec_set_pkt_timebase(AVCodecContext var0, @ByVal AVRational var1);

    @NoException
    @Const
    @Deprecated
    public static native AVCodecDescriptor av_codec_get_codec_descriptor(@Const AVCodecContext var0);

    @NoException
    @Deprecated
    public static native void av_codec_set_codec_descriptor(AVCodecContext var0, @Const AVCodecDescriptor var1);

    @NoException
    @Cast(value={"unsigned"})
    @Deprecated
    public static native int av_codec_get_codec_properties(@Const AVCodecContext var0);

    @NoException
    @Deprecated
    public static native int av_codec_get_lowres(@Const AVCodecContext var0);

    @NoException
    @Deprecated
    public static native void av_codec_set_lowres(AVCodecContext var0, int var1);

    @NoException
    @Deprecated
    public static native int av_codec_get_seek_preroll(@Const AVCodecContext var0);

    @NoException
    @Deprecated
    public static native void av_codec_set_seek_preroll(AVCodecContext var0, int var1);

    @NoException
    @Cast(value={"uint16_t*"})
    @Deprecated
    public static native ShortPointer av_codec_get_chroma_intra_matrix(@Const AVCodecContext var0);

    @NoException
    @Deprecated
    public static native void av_codec_set_chroma_intra_matrix(AVCodecContext var0, @Cast(value={"uint16_t*"}) ShortPointer var1);

    @NoException
    @Deprecated
    public static native void av_codec_set_chroma_intra_matrix(AVCodecContext var0, @Cast(value={"uint16_t*"}) ShortBuffer var1);

    @NoException
    @Deprecated
    public static native void av_codec_set_chroma_intra_matrix(AVCodecContext var0, @Cast(value={"uint16_t*"}) short[] var1);

    @NoException
    @Deprecated
    public static native int av_codec_get_max_lowres(@Const AVCodec var0);

    @NoException
    @Deprecated
    public static native AVCodec av_codec_next(@Const AVCodec var0);

    @NoException
    @Cast(value={"unsigned"})
    public static native int avcodec_version();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avcodec_configuration();

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avcodec_license();

    @NoException
    @Deprecated
    public static native void avcodec_register(AVCodec var0);

    @NoException
    @Deprecated
    public static native void avcodec_register_all();

    @NoException
    public static native AVCodecContext avcodec_alloc_context3(@Const AVCodec var0);

    @NoException
    public static native void avcodec_free_context(@Cast(value={"AVCodecContext**"}) PointerPointer var0);

    @NoException
    public static native void avcodec_free_context(@ByPtrPtr AVCodecContext var0);

    @NoException
    public static native int avcodec_get_context_defaults3(AVCodecContext var0, @Const AVCodec var1);

    @NoException
    @Const
    public static native AVClass avcodec_get_class();

    @NoException
    @Const
    @Deprecated
    public static native AVClass avcodec_get_frame_class();

    @NoException
    @Const
    public static native AVClass avcodec_get_subtitle_rect_class();

    @NoException
    @Deprecated
    public static native int avcodec_copy_context(AVCodecContext var0, @Const AVCodecContext var1);

    @NoException
    public static native int avcodec_parameters_from_context(AVCodecParameters var0, @Const AVCodecContext var1);

    @NoException
    public static native int avcodec_parameters_to_context(AVCodecContext var0, @Const AVCodecParameters var1);

    @NoException
    public static native int avcodec_open2(AVCodecContext var0, @Const AVCodec var1, @Cast(value={"AVDictionary**"}) PointerPointer var2);

    @NoException
    public static native int avcodec_open2(AVCodecContext var0, @Const AVCodec var1, @ByPtrPtr AVDictionary var2);

    @NoException
    public static native int avcodec_close(AVCodecContext var0);

    @NoException
    public static native void avsubtitle_free(AVSubtitle var0);

    @NoException
    public static native int avcodec_default_get_buffer2(AVCodecContext var0, AVFrame var1, int var2);

    @NoException
    public static native int avcodec_default_get_encode_buffer(AVCodecContext var0, AVPacket var1, int var2);

    @NoException
    public static native void avcodec_align_dimensions(AVCodecContext var0, IntPointer var1, IntPointer var2);

    @NoException
    public static native void avcodec_align_dimensions(AVCodecContext var0, IntBuffer var1, IntBuffer var2);

    @NoException
    public static native void avcodec_align_dimensions(AVCodecContext var0, int[] var1, int[] var2);

    @NoException
    public static native void avcodec_align_dimensions2(AVCodecContext var0, IntPointer var1, IntPointer var2, IntPointer var3);

    @NoException
    public static native void avcodec_align_dimensions2(AVCodecContext var0, IntBuffer var1, IntBuffer var2, IntBuffer var3);

    @NoException
    public static native void avcodec_align_dimensions2(AVCodecContext var0, int[] var1, int[] var2, int[] var3);

    @NoException
    public static native int avcodec_enum_to_chroma_pos(IntPointer var0, IntPointer var1, @Cast(value={"AVChromaLocation"}) int var2);

    @NoException
    public static native int avcodec_enum_to_chroma_pos(IntBuffer var0, IntBuffer var1, @Cast(value={"AVChromaLocation"}) int var2);

    @NoException
    public static native int avcodec_enum_to_chroma_pos(int[] var0, int[] var1, @Cast(value={"AVChromaLocation"}) int var2);

    @NoException
    @Cast(value={"AVChromaLocation"})
    public static native int avcodec_chroma_pos_to_enum(int var0, int var1);

    @NoException
    @Deprecated
    public static native int avcodec_decode_audio4(AVCodecContext var0, AVFrame var1, IntPointer var2, @Const AVPacket var3);

    @NoException
    @Deprecated
    public static native int avcodec_decode_audio4(AVCodecContext var0, AVFrame var1, IntBuffer var2, @Const AVPacket var3);

    @NoException
    @Deprecated
    public static native int avcodec_decode_audio4(AVCodecContext var0, AVFrame var1, int[] var2, @Const AVPacket var3);

    @NoException
    @Deprecated
    public static native int avcodec_decode_video2(AVCodecContext var0, AVFrame var1, IntPointer var2, @Const AVPacket var3);

    @NoException
    @Deprecated
    public static native int avcodec_decode_video2(AVCodecContext var0, AVFrame var1, IntBuffer var2, @Const AVPacket var3);

    @NoException
    @Deprecated
    public static native int avcodec_decode_video2(AVCodecContext var0, AVFrame var1, int[] var2, @Const AVPacket var3);

    @NoException
    public static native int avcodec_decode_subtitle2(AVCodecContext var0, AVSubtitle var1, IntPointer var2, AVPacket var3);

    @NoException
    public static native int avcodec_decode_subtitle2(AVCodecContext var0, AVSubtitle var1, IntBuffer var2, AVPacket var3);

    @NoException
    public static native int avcodec_decode_subtitle2(AVCodecContext var0, AVSubtitle var1, int[] var2, AVPacket var3);

    @NoException
    public static native int avcodec_send_packet(AVCodecContext var0, @Const AVPacket var1);

    @NoException
    public static native int avcodec_receive_frame(AVCodecContext var0, AVFrame var1);

    @NoException
    public static native int avcodec_send_frame(AVCodecContext var0, @Const AVFrame var1);

    @NoException
    public static native int avcodec_receive_packet(AVCodecContext var0, AVPacket var1);

    @NoException
    public static native int avcodec_get_hw_frames_parameters(AVCodecContext var0, AVBufferRef var1, @Cast(value={"AVPixelFormat"}) int var2, @Cast(value={"AVBufferRef**"}) PointerPointer var3);

    @NoException
    public static native int avcodec_get_hw_frames_parameters(AVCodecContext var0, AVBufferRef var1, @Cast(value={"AVPixelFormat"}) int var2, @ByPtrPtr AVBufferRef var3);

    @NoException
    @Const
    public static native AVCodecParser av_parser_iterate(@Cast(value={"void**"}) PointerPointer var0);

    @NoException
    @Const
    public static native AVCodecParser av_parser_iterate(@Cast(value={"void**"}) @ByPtrPtr Pointer var0);

    @NoException
    @Deprecated
    public static native AVCodecParser av_parser_next(@Const AVCodecParser var0);

    @NoException
    @Deprecated
    public static native void av_register_codec_parser(AVCodecParser var0);

    @NoException
    public static native AVCodecParserContext av_parser_init(int var0);

    @NoException
    public static native int av_parser_parse2(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) PointerPointer var2, IntPointer var3, @Cast(value={"const uint8_t*"}) BytePointer var4, int var5, @Cast(value={"int64_t"}) long var6, @Cast(value={"int64_t"}) long var8, @Cast(value={"int64_t"}) long var10);

    @NoException
    public static native int av_parser_parse2(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var2, IntPointer var3, @Cast(value={"const uint8_t*"}) BytePointer var4, int var5, @Cast(value={"int64_t"}) long var6, @Cast(value={"int64_t"}) long var8, @Cast(value={"int64_t"}) long var10);

    @NoException
    public static native int av_parser_parse2(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var2, IntBuffer var3, @Cast(value={"const uint8_t*"}) ByteBuffer var4, int var5, @Cast(value={"int64_t"}) long var6, @Cast(value={"int64_t"}) long var8, @Cast(value={"int64_t"}) long var10);

    @NoException
    public static native int av_parser_parse2(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var2, int[] var3, @Cast(value={"const uint8_t*"}) byte[] var4, int var5, @Cast(value={"int64_t"}) long var6, @Cast(value={"int64_t"}) long var8, @Cast(value={"int64_t"}) long var10);

    @NoException
    @Deprecated
    public static native int av_parser_change(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) PointerPointer var2, IntPointer var3, @Cast(value={"const uint8_t*"}) BytePointer var4, int var5, int var6);

    @NoException
    @Deprecated
    public static native int av_parser_change(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var2, IntPointer var3, @Cast(value={"const uint8_t*"}) BytePointer var4, int var5, int var6);

    @NoException
    @Deprecated
    public static native int av_parser_change(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var2, IntBuffer var3, @Cast(value={"const uint8_t*"}) ByteBuffer var4, int var5, int var6);

    @NoException
    @Deprecated
    public static native int av_parser_change(AVCodecParserContext var0, AVCodecContext var1, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var2, int[] var3, @Cast(value={"const uint8_t*"}) byte[] var4, int var5, int var6);

    @NoException
    public static native void av_parser_close(AVCodecParserContext var0);

    @NoException
    @Deprecated
    public static native int avcodec_encode_audio2(AVCodecContext var0, AVPacket var1, @Const AVFrame var2, IntPointer var3);

    @NoException
    @Deprecated
    public static native int avcodec_encode_audio2(AVCodecContext var0, AVPacket var1, @Const AVFrame var2, IntBuffer var3);

    @NoException
    @Deprecated
    public static native int avcodec_encode_audio2(AVCodecContext var0, AVPacket var1, @Const AVFrame var2, int[] var3);

    @NoException
    @Deprecated
    public static native int avcodec_encode_video2(AVCodecContext var0, AVPacket var1, @Const AVFrame var2, IntPointer var3);

    @NoException
    @Deprecated
    public static native int avcodec_encode_video2(AVCodecContext var0, AVPacket var1, @Const AVFrame var2, IntBuffer var3);

    @NoException
    @Deprecated
    public static native int avcodec_encode_video2(AVCodecContext var0, AVPacket var1, @Const AVFrame var2, int[] var3);

    @NoException
    public static native int avcodec_encode_subtitle(AVCodecContext var0, @Cast(value={"uint8_t*"}) BytePointer var1, int var2, @Const AVSubtitle var3);

    @NoException
    public static native int avcodec_encode_subtitle(AVCodecContext var0, @Cast(value={"uint8_t*"}) ByteBuffer var1, int var2, @Const AVSubtitle var3);

    @NoException
    public static native int avcodec_encode_subtitle(AVCodecContext var0, @Cast(value={"uint8_t*"}) byte[] var1, int var2, @Const AVSubtitle var3);

    @NoException
    @Deprecated
    public static native int avpicture_alloc(AVPicture var0, @Cast(value={"AVPixelFormat"}) int var1, int var2, int var3);

    @NoException
    @Deprecated
    public static native void avpicture_free(AVPicture var0);

    @NoException
    @Deprecated
    public static native int avpicture_fill(AVPicture var0, @Cast(value={"const uint8_t*"}) BytePointer var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4);

    @NoException
    @Deprecated
    public static native int avpicture_fill(AVPicture var0, @Cast(value={"const uint8_t*"}) ByteBuffer var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4);

    @NoException
    @Deprecated
    public static native int avpicture_fill(AVPicture var0, @Cast(value={"const uint8_t*"}) byte[] var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4);

    @NoException
    @Deprecated
    public static native int avpicture_layout(@Const AVPicture var0, @Cast(value={"AVPixelFormat"}) int var1, int var2, int var3, @Cast(value={"unsigned char*"}) BytePointer var4, int var5);

    @NoException
    @Deprecated
    public static native int avpicture_layout(@Const AVPicture var0, @Cast(value={"AVPixelFormat"}) int var1, int var2, int var3, @Cast(value={"unsigned char*"}) ByteBuffer var4, int var5);

    @NoException
    @Deprecated
    public static native int avpicture_layout(@Const AVPicture var0, @Cast(value={"AVPixelFormat"}) int var1, int var2, int var3, @Cast(value={"unsigned char*"}) byte[] var4, int var5);

    @NoException
    @Deprecated
    public static native int avpicture_get_size(@Cast(value={"AVPixelFormat"}) int var0, int var1, int var2);

    @NoException
    @Deprecated
    public static native void av_picture_copy(AVPicture var0, @Const AVPicture var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4);

    @NoException
    @Deprecated
    public static native int av_picture_crop(AVPicture var0, @Const AVPicture var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int var4);

    @NoException
    @Deprecated
    public static native int av_picture_pad(AVPicture var0, @Const AVPicture var1, int var2, int var3, @Cast(value={"AVPixelFormat"}) int var4, int var5, int var6, int var7, int var8, IntPointer var9);

    @NoException
    @Deprecated
    public static native int av_picture_pad(AVPicture var0, @Const AVPicture var1, int var2, int var3, @Cast(value={"AVPixelFormat"}) int var4, int var5, int var6, int var7, int var8, IntBuffer var9);

    @NoException
    @Deprecated
    public static native int av_picture_pad(AVPicture var0, @Const AVPicture var1, int var2, int var3, @Cast(value={"AVPixelFormat"}) int var4, int var5, int var6, int var7, int var8, int[] var9);

    @NoException
    @Deprecated
    public static native void avcodec_get_chroma_sub_sample(@Cast(value={"AVPixelFormat"}) int var0, IntPointer var1, IntPointer var2);

    @NoException
    @Deprecated
    public static native void avcodec_get_chroma_sub_sample(@Cast(value={"AVPixelFormat"}) int var0, IntBuffer var1, IntBuffer var2);

    @NoException
    @Deprecated
    public static native void avcodec_get_chroma_sub_sample(@Cast(value={"AVPixelFormat"}) int var0, int[] var1, int[] var2);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int avcodec_pix_fmt_to_codec_tag(@Cast(value={"AVPixelFormat"}) int var0);

    @NoException
    @Cast(value={"AVPixelFormat"})
    public static native int avcodec_find_best_pix_fmt_of_list(@Cast(value={"const AVPixelFormat*"}) IntPointer var0, @Cast(value={"AVPixelFormat"}) int var1, int var2, IntPointer var3);

    @NoException
    @Cast(value={"AVPixelFormat"})
    public static native int avcodec_find_best_pix_fmt_of_list(@Cast(value={"const AVPixelFormat*"}) IntBuffer var0, @Cast(value={"AVPixelFormat"}) int var1, int var2, IntBuffer var3);

    @NoException
    @Cast(value={"AVPixelFormat"})
    public static native int avcodec_find_best_pix_fmt_of_list(@Cast(value={"const AVPixelFormat*"}) int[] var0, @Cast(value={"AVPixelFormat"}) int var1, int var2, int[] var3);

    @NoException
    @Deprecated
    public static native int avcodec_get_pix_fmt_loss(@Cast(value={"AVPixelFormat"}) int var0, @Cast(value={"AVPixelFormat"}) int var1, int var2);

    @NoException
    @Cast(value={"AVPixelFormat"})
    @Deprecated
    public static native int avcodec_find_best_pix_fmt_of_2(@Cast(value={"AVPixelFormat"}) int var0, @Cast(value={"AVPixelFormat"}) int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, IntPointer var4);

    @NoException
    @Cast(value={"AVPixelFormat"})
    @Deprecated
    public static native int avcodec_find_best_pix_fmt_of_2(@Cast(value={"AVPixelFormat"}) int var0, @Cast(value={"AVPixelFormat"}) int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, IntBuffer var4);

    @NoException
    @Cast(value={"AVPixelFormat"})
    @Deprecated
    public static native int avcodec_find_best_pix_fmt_of_2(@Cast(value={"AVPixelFormat"}) int var0, @Cast(value={"AVPixelFormat"}) int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int[] var4);

    @NoException
    @Cast(value={"AVPixelFormat"})
    @Deprecated
    public static native int avcodec_find_best_pix_fmt2(@Cast(value={"AVPixelFormat"}) int var0, @Cast(value={"AVPixelFormat"}) int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, IntPointer var4);

    @NoException
    @Cast(value={"AVPixelFormat"})
    @Deprecated
    public static native int avcodec_find_best_pix_fmt2(@Cast(value={"AVPixelFormat"}) int var0, @Cast(value={"AVPixelFormat"}) int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, IntBuffer var4);

    @NoException
    @Cast(value={"AVPixelFormat"})
    @Deprecated
    public static native int avcodec_find_best_pix_fmt2(@Cast(value={"AVPixelFormat"}) int var0, @Cast(value={"AVPixelFormat"}) int var1, @Cast(value={"AVPixelFormat"}) int var2, int var3, int[] var4);

    @NoException
    @Cast(value={"AVPixelFormat"})
    public static native int avcodec_default_get_format(AVCodecContext var0, @Cast(value={"const AVPixelFormat*"}) IntPointer var1);

    @NoException
    @Cast(value={"AVPixelFormat"})
    public static native int avcodec_default_get_format(AVCodecContext var0, @Cast(value={"const AVPixelFormat*"}) IntBuffer var1);

    @NoException
    @Cast(value={"AVPixelFormat"})
    public static native int avcodec_default_get_format(AVCodecContext var0, @Cast(value={"const AVPixelFormat*"}) int[] var1);

    @NoException
    @Cast(value={"size_t"})
    @Deprecated
    public static native long av_get_codec_tag_string(@Cast(value={"char*"}) BytePointer var0, @Cast(value={"size_t"}) long var1, @Cast(value={"unsigned int"}) int var3);

    @NoException
    @Cast(value={"size_t"})
    @Deprecated
    public static native long av_get_codec_tag_string(@Cast(value={"char*"}) ByteBuffer var0, @Cast(value={"size_t"}) long var1, @Cast(value={"unsigned int"}) int var3);

    @NoException
    @Cast(value={"size_t"})
    @Deprecated
    public static native long av_get_codec_tag_string(@Cast(value={"char*"}) byte[] var0, @Cast(value={"size_t"}) long var1, @Cast(value={"unsigned int"}) int var3);

    @NoException
    public static native void avcodec_string(@Cast(value={"char*"}) BytePointer var0, int var1, AVCodecContext var2, int var3);

    @NoException
    public static native void avcodec_string(@Cast(value={"char*"}) ByteBuffer var0, int var1, AVCodecContext var2, int var3);

    @NoException
    public static native void avcodec_string(@Cast(value={"char*"}) byte[] var0, int var1, AVCodecContext var2, int var3);

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer av_get_profile_name(@Const AVCodec var0, int var1);

    @NoException
    @Cast(value={"const char*"})
    public static native BytePointer avcodec_profile_name(@Cast(value={"AVCodecID"}) int var0, int var1);

    @NoException
    public static native int avcodec_default_execute(AVCodecContext var0, Func_AVCodecContext_Pointer var1, Pointer var2, IntPointer var3, int var4, int var5);

    @NoException
    public static native int avcodec_default_execute(AVCodecContext var0, Func_AVCodecContext_Pointer var1, Pointer var2, IntBuffer var3, int var4, int var5);

    @NoException
    public static native int avcodec_default_execute(AVCodecContext var0, Func_AVCodecContext_Pointer var1, Pointer var2, int[] var3, int var4, int var5);

    @NoException
    public static native int avcodec_default_execute2(AVCodecContext var0, Func_AVCodecContext_Pointer_int_int var1, Pointer var2, IntPointer var3, int var4);

    @NoException
    public static native int avcodec_default_execute2(AVCodecContext var0, Func_AVCodecContext_Pointer_int_int var1, Pointer var2, IntBuffer var3, int var4);

    @NoException
    public static native int avcodec_default_execute2(AVCodecContext var0, Func_AVCodecContext_Pointer_int_int var1, Pointer var2, int[] var3, int var4);

    @NoException
    public static native int avcodec_fill_audio_frame(AVFrame var0, int var1, @Cast(value={"AVSampleFormat"}) int var2, @Cast(value={"const uint8_t*"}) BytePointer var3, int var4, int var5);

    @NoException
    public static native int avcodec_fill_audio_frame(AVFrame var0, int var1, @Cast(value={"AVSampleFormat"}) int var2, @Cast(value={"const uint8_t*"}) ByteBuffer var3, int var4, int var5);

    @NoException
    public static native int avcodec_fill_audio_frame(AVFrame var0, int var1, @Cast(value={"AVSampleFormat"}) int var2, @Cast(value={"const uint8_t*"}) byte[] var3, int var4, int var5);

    @NoException
    public static native void avcodec_flush_buffers(AVCodecContext var0);

    @NoException
    public static native int av_get_bits_per_sample(@Cast(value={"AVCodecID"}) int var0);

    @NoException
    @Cast(value={"AVCodecID"})
    public static native int av_get_pcm_codec(@Cast(value={"AVSampleFormat"}) int var0, int var1);

    @NoException
    public static native int av_get_exact_bits_per_sample(@Cast(value={"AVCodecID"}) int var0);

    @NoException
    public static native int av_get_audio_frame_duration(AVCodecContext var0, int var1);

    @NoException
    public static native int av_get_audio_frame_duration2(AVCodecParameters var0, int var1);

    @NoException
    @Deprecated
    public static native void av_register_bitstream_filter(AVBitStreamFilter var0);

    @NoException
    @Deprecated
    public static native AVBitStreamFilterContext av_bitstream_filter_init(@Cast(value={"const char*"}) BytePointer var0);

    @NoException
    @Deprecated
    public static native AVBitStreamFilterContext av_bitstream_filter_init(String var0);

    @NoException
    @Deprecated
    public static native int av_bitstream_filter_filter(AVBitStreamFilterContext var0, AVCodecContext var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"uint8_t**"}) PointerPointer var3, IntPointer var4, @Cast(value={"const uint8_t*"}) BytePointer var5, int var6, int var7);

    @NoException
    @Deprecated
    public static native int av_bitstream_filter_filter(AVBitStreamFilterContext var0, AVCodecContext var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var3, IntPointer var4, @Cast(value={"const uint8_t*"}) BytePointer var5, int var6, int var7);

    @NoException
    @Deprecated
    public static native int av_bitstream_filter_filter(AVBitStreamFilterContext var0, AVCodecContext var1, String var2, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var3, IntBuffer var4, @Cast(value={"const uint8_t*"}) ByteBuffer var5, int var6, int var7);

    @NoException
    @Deprecated
    public static native int av_bitstream_filter_filter(AVBitStreamFilterContext var0, AVCodecContext var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var3, int[] var4, @Cast(value={"const uint8_t*"}) byte[] var5, int var6, int var7);

    @NoException
    @Deprecated
    public static native int av_bitstream_filter_filter(AVBitStreamFilterContext var0, AVCodecContext var1, String var2, @Cast(value={"uint8_t**"}) @ByPtrPtr BytePointer var3, IntPointer var4, @Cast(value={"const uint8_t*"}) BytePointer var5, int var6, int var7);

    @NoException
    @Deprecated
    public static native int av_bitstream_filter_filter(AVBitStreamFilterContext var0, AVCodecContext var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"uint8_t**"}) @ByPtrPtr ByteBuffer var3, IntBuffer var4, @Cast(value={"const uint8_t*"}) ByteBuffer var5, int var6, int var7);

    @NoException
    @Deprecated
    public static native int av_bitstream_filter_filter(AVBitStreamFilterContext var0, AVCodecContext var1, String var2, @Cast(value={"uint8_t**"}) @ByPtrPtr byte[] var3, int[] var4, @Cast(value={"const uint8_t*"}) byte[] var5, int var6, int var7);

    @NoException
    @Deprecated
    public static native void av_bitstream_filter_close(AVBitStreamFilterContext var0);

    @NoException
    @Const
    @Deprecated
    public static native AVBitStreamFilter av_bitstream_filter_next(@Const AVBitStreamFilter var0);

    @NoException
    @Const
    @Deprecated
    public static native AVBitStreamFilter av_bsf_next(@Cast(value={"void**"}) PointerPointer var0);

    @NoException
    @Const
    @Deprecated
    public static native AVBitStreamFilter av_bsf_next(@Cast(value={"void**"}) @ByPtrPtr Pointer var0);

    @NoException
    public static native void av_fast_padded_malloc(Pointer var0, @Cast(value={"unsigned int*"}) IntPointer var1, @Cast(value={"size_t"}) long var2);

    @NoException
    public static native void av_fast_padded_malloc(Pointer var0, @Cast(value={"unsigned int*"}) IntBuffer var1, @Cast(value={"size_t"}) long var2);

    @NoException
    public static native void av_fast_padded_malloc(Pointer var0, @Cast(value={"unsigned int*"}) int[] var1, @Cast(value={"size_t"}) long var2);

    @NoException
    public static native void av_fast_padded_mallocz(Pointer var0, @Cast(value={"unsigned int*"}) IntPointer var1, @Cast(value={"size_t"}) long var2);

    @NoException
    public static native void av_fast_padded_mallocz(Pointer var0, @Cast(value={"unsigned int*"}) IntBuffer var1, @Cast(value={"size_t"}) long var2);

    @NoException
    public static native void av_fast_padded_mallocz(Pointer var0, @Cast(value={"unsigned int*"}) int[] var1, @Cast(value={"size_t"}) long var2);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int av_xiphlacing(@Cast(value={"unsigned char*"}) BytePointer var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int av_xiphlacing(@Cast(value={"unsigned char*"}) ByteBuffer var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    @Cast(value={"unsigned int"})
    public static native int av_xiphlacing(@Cast(value={"unsigned char*"}) byte[] var0, @Cast(value={"unsigned int"}) int var1);

    @NoException
    @Deprecated
    public static native void av_register_hwaccel(AVHWAccel var0);

    @NoException
    @Deprecated
    public static native AVHWAccel av_hwaccel_next(@Const AVHWAccel var0);

    @NoException
    @Deprecated
    public static native int av_lockmgr_register(Cb_PointerPointer_int var0);

    @NoException
    @Deprecated
    public static native int av_lockmgr_register(Cb_Pointer_int var0);

    @NoException
    public static native int avcodec_is_open(AVCodecContext var0);

    @NoException
    public static native AVCPBProperties av_cpb_properties_alloc(@Cast(value={"size_t*"}) SizeTPointer var0);

    @NoException
    public static native int av_jni_set_java_vm(Pointer var0, Pointer var1);

    @NoException
    public static native Pointer av_jni_get_java_vm(Pointer var0);

    @NoException
    public static native FFTContext av_fft_init(int var0, int var1);

    @NoException
    public static native void av_fft_permute(FFTContext var0, FFTComplex var1);

    @NoException
    public static native void av_fft_calc(FFTContext var0, FFTComplex var1);

    @NoException
    public static native void av_fft_end(FFTContext var0);

    @NoException
    public static native FFTContext av_mdct_init(int var0, int var1, double var2);

    @NoException
    public static native void av_imdct_calc(FFTContext var0, @Cast(value={"FFTSample*"}) FloatPointer var1, @Cast(value={"const FFTSample*"}) FloatPointer var2);

    @NoException
    public static native void av_imdct_calc(FFTContext var0, @Cast(value={"FFTSample*"}) FloatBuffer var1, @Cast(value={"const FFTSample*"}) FloatBuffer var2);

    @NoException
    public static native void av_imdct_calc(FFTContext var0, @Cast(value={"FFTSample*"}) float[] var1, @Cast(value={"const FFTSample*"}) float[] var2);

    @NoException
    public static native void av_imdct_half(FFTContext var0, @Cast(value={"FFTSample*"}) FloatPointer var1, @Cast(value={"const FFTSample*"}) FloatPointer var2);

    @NoException
    public static native void av_imdct_half(FFTContext var0, @Cast(value={"FFTSample*"}) FloatBuffer var1, @Cast(value={"const FFTSample*"}) FloatBuffer var2);

    @NoException
    public static native void av_imdct_half(FFTContext var0, @Cast(value={"FFTSample*"}) float[] var1, @Cast(value={"const FFTSample*"}) float[] var2);

    @NoException
    public static native void av_mdct_calc(FFTContext var0, @Cast(value={"FFTSample*"}) FloatPointer var1, @Cast(value={"const FFTSample*"}) FloatPointer var2);

    @NoException
    public static native void av_mdct_calc(FFTContext var0, @Cast(value={"FFTSample*"}) FloatBuffer var1, @Cast(value={"const FFTSample*"}) FloatBuffer var2);

    @NoException
    public static native void av_mdct_calc(FFTContext var0, @Cast(value={"FFTSample*"}) float[] var1, @Cast(value={"const FFTSample*"}) float[] var2);

    @NoException
    public static native void av_mdct_end(FFTContext var0);

    @NoException
    public static native RDFTContext av_rdft_init(int var0, @Cast(value={"RDFTransformType"}) int var1);

    @NoException
    public static native void av_rdft_calc(RDFTContext var0, @Cast(value={"FFTSample*"}) FloatPointer var1);

    @NoException
    public static native void av_rdft_calc(RDFTContext var0, @Cast(value={"FFTSample*"}) FloatBuffer var1);

    @NoException
    public static native void av_rdft_calc(RDFTContext var0, @Cast(value={"FFTSample*"}) float[] var1);

    @NoException
    public static native void av_rdft_end(RDFTContext var0);

    @NoException
    public static native DCTContext av_dct_init(int var0, @Cast(value={"DCTTransformType"}) int var1);

    @NoException
    public static native void av_dct_calc(DCTContext var0, @Cast(value={"FFTSample*"}) FloatPointer var1);

    @NoException
    public static native void av_dct_calc(DCTContext var0, @Cast(value={"FFTSample*"}) FloatBuffer var1);

    @NoException
    public static native void av_dct_calc(DCTContext var0, @Cast(value={"FFTSample*"}) float[] var1);

    @NoException
    public static native void av_dct_end(DCTContext var0);

    static {
        Loader.load();
    }
}

