/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.presets;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.annotation.Platform;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.tools.Info;
import org.bytedeco.javacpp.tools.InfoMap;
import org.bytedeco.javacpp.tools.InfoMapper;

@Properties(inherit={avutil.class}, target="org.bytedeco.ffmpeg.postproc", global="org.bytedeco.ffmpeg.global.postproc", value={@Platform(cinclude={"<libpostproc/postprocess.h>"}, link={"postproc@.55"}, extension={"-gpl"}), @Platform(value={"windows"}, preload={"postproc-55"}, extension={"-gpl"})})
public class postproc
implements InfoMapper {
    @Override
    public void map(InfoMap infoMap) {
        infoMap.put(new Info("QP_STORE_T").cppTypes(new String[0]).valueTypes("byte").pointerTypes("BytePointer")).put(new Info("LIBPOSTPROC_VERSION_INT < (52<<16)").define(false));
    }
}

