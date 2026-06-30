/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.presets;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(inherit={avfilter.class}, target="org.bytedeco.ffmpeg.avdevice", global="org.bytedeco.ffmpeg.global.avdevice", value={@Platform(cinclude={"<libavdevice/avdevice.h>"}, link={"avdevice@.58"}), @Platform(value={"windows"}, preload={"avdevice-58"})})
public class avdevice
implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap.putFirst(new Info("AVDeviceInfoList").pointerTypes("AVDeviceInfoList")).putFirst(new Info("AVDeviceCapabilitiesQuery").pointerTypes("AVDeviceCapabilitiesQuery")).put(new Info("av_device_capabilities").skip());
    }
}

