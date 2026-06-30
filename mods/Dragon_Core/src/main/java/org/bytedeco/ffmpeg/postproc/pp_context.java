/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.postproc;

import org.bytedeco.ffmpeg.presets.postproc;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Opaque;
import org.bytedeco.javacpp.annotation.Properties;

@Opaque
@Properties(inherit={postproc.class})
public class pp_context
extends Pointer {
    public pp_context() {
        super((Pointer)null);
    }

    public pp_context(Pointer p2) {
        super(p2);
    }
}

