/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.swscale;

import org.bytedeco.ffmpeg.presets.swscale;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Opaque
@Properties(inherit={swscale.class})
public class SwsContext
extends Pointer {
    public SwsContext() {
        super((Pointer)null);
    }

    public SwsContext(Pointer p2) {
        super(p2);
    }
}

