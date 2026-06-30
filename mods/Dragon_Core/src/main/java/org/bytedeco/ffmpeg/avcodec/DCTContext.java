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
public class DCTContext
extends Pointer {
    public DCTContext() {
        super((Pointer)null);
    }

    public DCTContext(Pointer p2) {
        super(p2);
    }
}

