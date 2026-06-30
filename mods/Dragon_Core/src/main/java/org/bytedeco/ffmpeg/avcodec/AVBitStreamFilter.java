/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avcodec;

import org.bytedeco.ffmpeg.avcodec.AVBSFContext;
import org.bytedeco.ffmpeg.avcodec.AVPacket;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avcodec;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avcodec.class})
public class AVBitStreamFilter
extends Pointer {
    public AVBitStreamFilter() {
        super((Pointer)null);
        this.allocate();
    }

    public AVBitStreamFilter(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVBitStreamFilter(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVBitStreamFilter position(long position) {
        return (AVBitStreamFilter)super.position(position);
    }

    public AVBitStreamFilter getPointer(long i2) {
        return (AVBitStreamFilter)new AVBitStreamFilter(this).offsetAddress(i2);
    }

    @Cast(value={"const char*"})
    public native BytePointer name();

    public native AVBitStreamFilter name(BytePointer var1);

    @Cast(value={"const AVCodecID*"})
    public native IntPointer codec_ids();

    public native AVBitStreamFilter codec_ids(IntPointer var1);

    @Const
    public native AVClass priv_class();

    public native AVBitStreamFilter priv_class(AVClass var1);

    public native int priv_data_size();

    public native AVBitStreamFilter priv_data_size(int var1);

    public native Init_AVBSFContext init();

    public native AVBitStreamFilter init(Init_AVBSFContext var1);

    public native Filter_AVBSFContext_AVPacket filter();

    public native AVBitStreamFilter filter(Filter_AVBSFContext_AVPacket var1);

    @Name(value={"close"})
    public native Close_AVBSFContext _close();

    public native AVBitStreamFilter _close(Close_AVBSFContext var1);

    public native Flush_AVBSFContext flush();

    public native AVBitStreamFilter flush(Flush_AVBSFContext var1);

    static {
        Loader.load();
    }

    public static class Flush_AVBSFContext
    extends FunctionPointer {
        public Flush_AVBSFContext(Pointer p2) {
            super(p2);
        }

        protected Flush_AVBSFContext() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVBSFContext var1);

        static {
            Loader.load();
        }
    }

    public static class Close_AVBSFContext
    extends FunctionPointer {
        public Close_AVBSFContext(Pointer p2) {
            super(p2);
        }

        protected Close_AVBSFContext() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVBSFContext var1);

        static {
            Loader.load();
        }
    }

    public static class Filter_AVBSFContext_AVPacket
    extends FunctionPointer {
        public Filter_AVBSFContext_AVPacket(Pointer p2) {
            super(p2);
        }

        protected Filter_AVBSFContext_AVPacket() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVBSFContext var1, AVPacket var2);

        static {
            Loader.load();
        }
    }

    public static class Init_AVBSFContext
    extends FunctionPointer {
        public Init_AVBSFContext(Pointer p2) {
            super(p2);
        }

        protected Init_AVBSFContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVBSFContext var1);

        static {
            Loader.load();
        }
    }
}

