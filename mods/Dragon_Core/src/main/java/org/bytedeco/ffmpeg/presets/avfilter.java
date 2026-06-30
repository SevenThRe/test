/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.presets;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.ffmpeg.presets.postproc;
import org.bytedeco.ffmpeg.presets.swresample;
import org.bytedeco.ffmpeg.presets.swscale;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(inherit={avformat.class, postproc.class, swresample.class, swscale.class}, target="org.bytedeco.ffmpeg.avfilter", global="org.bytedeco.ffmpeg.global.avfilter", value={@Platform(cinclude={"<libavfilter/avfilter.h>", "<libavfilter/buffersink.h>", "<libavfilter/buffersrc.h>"}, link={"avfilter@.7"}), @Platform(value={"windows"}, preload={"avfilter-7"})})
public class avfilter
implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("AVFilterPool", "AVFilterCommand", "AVFilterChannelLayouts", "FFFrameQueue").cast().pointerTypes("Pointer")).put(new Info("AV_HAVE_INCOMPATIBLE_LIBAV_ABI || !FF_API_OLD_GRAPH_PARSE").define(true)).put(new Info("!FF_API_FOO_COUNT", "FF_INTERNAL_FIELDS").define(false));
    }
}

