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
public class AVCodecDefault
extends Pointer {
    public AVCodecDefault() {
        super((Pointer)null);
    }

    public AVCodecDefault(Pointer p2) {
        super(p2);
    }
}

