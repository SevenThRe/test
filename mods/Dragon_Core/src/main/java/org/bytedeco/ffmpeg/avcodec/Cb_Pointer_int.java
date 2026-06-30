/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.ByPtrPtr;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class Cb_Pointer_int
extends FunctionPointer {
    public Cb_Pointer_int(Pointer p2) {
        super(p2);
    }

    protected Cb_Pointer_int() {
        this.allocate();
    }

    private native void allocate();

    public native int call(@Cast(value={"void**"}) @ByPtrPtr Pointer var1, @Cast(value={"AVLockOp"}) int var2);

    static {
        Loader.load();
    }
}

