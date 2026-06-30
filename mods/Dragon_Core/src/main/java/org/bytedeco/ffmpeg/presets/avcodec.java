/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.presets;

import org.bytedeco.ffmpeg.presets.swresample;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(inherit={swresample.class}, target="org.bytedeco.ffmpeg.avcodec", global="org.bytedeco.ffmpeg.global.avcodec", value={@Platform(cinclude={"<libavcodec/codec_id.h>", "<libavcodec/codec_desc.h>", "<libavcodec/codec_par.h>", "<libavcodec/packet.h>", "<libavcodec/bsf.h>", "<libavcodec/codec.h>", "<libavcodec/avcodec.h>", "<libavcodec/jni.h>", "<libavcodec/avfft.h>"}, link={"avcodec@.58"}), @Platform(value={"linux-arm"}, preload={"asound@.2", "vchiq_arm", "vcos", "vcsm", "bcm_host", "mmal_core", "mmal_util", "mmal_vc_client"}), @Platform(value={"windows"}, preload={"avcodec-58"})})
public class avcodec
implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("!FF_API_LOWRES", "!FF_API_DEBUG_MV").define(false)).put(new Info("CODEC_FLAG_CLOSED_GOP").translate().cppTypes("long")).put(new Info("AVCodecHWConfigInternal").cast().pointerTypes("Pointer")).put(new Info("AVCodec::hw_configs").skip()).putFirst(new Info("AVPanScan").pointerTypes("AVPanScan")).putFirst(new Info("AVCodecContext").pointerTypes("AVCodecContext"));
    }
}

