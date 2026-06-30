/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Opaque
@Properties(inherit={avcodec.class})
public class AVCodecInternal
extends Pointer {
    public AVCodecInternal() {
        super((Pointer)null);
    }

    public AVCodecInternal(Pointer p2) {
        super(p2);
    }
}

