/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.presets;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(inherit={avutil.class}, target="org.bytedeco.ffmpeg.swresample", global="org.bytedeco.ffmpeg.global.swresample", value={@Platform(cinclude={"<libswresample/swresample.h>"}, link={"swresample@.3"}), @Platform(value={"windows"}, preload={"swresample-3"})})
public class swresample
implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
    }
}

