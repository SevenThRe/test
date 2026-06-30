/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avutil;

import org.bytedeco.ffmpeg.presets.avutil;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avutil.class})
public class Int_func_Pointer_Pointer_int
extends FunctionPointer {
    public Int_func_Pointer_Pointer_int(Pointer p2) {
        super(p2);
    }

    protected Int_func_Pointer_Pointer_int() {
        this.allocate();
    }

    private native void allocate();

    public native int call(Pointer var1, Pointer var2, int var3);

    static {
        Loader.load();
    }
}

