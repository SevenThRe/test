/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avcodec.AVBitStreamFilter;
import org.bytedeco.ffmpeg.avcodec.AVCodecParserContext;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class AVBitStreamFilterContext
extends Pointer {
    public AVBitStreamFilterContext() {
        super((Pointer)null);
        this.allocate();
    }

    public AVBitStreamFilterContext(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVBitStreamFilterContext(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVBitStreamFilterContext position(long position) {
        return (AVBitStreamFilterContext)super.position(position);
    }

    public AVBitStreamFilterContext getPointer(long i2) {
        return (AVBitStreamFilterContext)new AVBitStreamFilterContext(this).offsetAddress(i2);
    }

    public native Pointer priv_data();

    public native AVBitStreamFilterContext priv_data(Pointer var1);

    @Const
    public native AVBitStreamFilter filter();

    public native AVBitStreamFilterContext filter(AVBitStreamFilter var1);

    public native AVCodecParserContext parser();

    public native AVBitStreamFilterContext parser(AVCodecParserContext var1);

    public native AVBitStreamFilterContext next();

    public native AVBitStreamFilterContext next(AVBitStreamFilterContext var1);

    @Cast(value={"char*"})
    public native BytePointer args();

    public native AVBitStreamFilterContext args(BytePointer var1);

    static {
        Loader.load();
    }
}

