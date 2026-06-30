/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.presets;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(inherit={avutil.class}, target="org.bytedeco.ffmpeg.swscale", global="org.bytedeco.ffmpeg.global.swscale", value={@Platform(cinclude={"<libswscale/swscale.h>"}, link={"swscale@.5"}), @Platform(value={"windows"}, preload={"swscale-5"})})
public class swscale
implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
    }
}

