/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avformat;

import org.bytedeco.ffmpeg.presets.avformat;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Opaque
@Properties(inherit={avformat.class})
public class AVFormatInternal
extends Pointer {
    public AVFormatInternal() {
        super((Pointer)null);
    }

    public AVFormatInternal(Pointer p2) {
        super(p2);
    }
}

