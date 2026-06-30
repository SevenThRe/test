/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.avutil.AVBufferRef;
import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avutil.class})
public class Alloc_int
extends FunctionPointer {
    public Alloc_int(Pointer p2) {
        super(p2);
    }

    protected Alloc_int() {
        this.allocate();
    }

    private native void allocate();

    public native AVBufferRef call(int var1);

    static {
        Loader.load();
    }
}

