/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Opaque
@Properties(inherit={avfilter.class})
public class AVFilterInternal
extends Pointer {
    public AVFilterInternal() {
        super((Pointer)null);
    }

    public AVFilterInternal(Pointer p2) {
        super(p2);
    }
}

