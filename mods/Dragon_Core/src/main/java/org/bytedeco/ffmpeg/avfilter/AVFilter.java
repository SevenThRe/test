/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.ffmpeg.avfilter;

import org.bytedeco.ffmpeg.avfilter.AVFilterContext;
import org.bytedeco.ffmpeg.avfilter.AVFilterPad;
import org.bytedeco.ffmpeg.avutil.AVClass;
import org.bytedeco.ffmpeg.presets.avfilter;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.FunctionPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Const;
import org.bytedeco.javacpp.annotation.Properties;

@Properties(inherit={avfilter.class})
public class AVFilter
extends Pointer {
    public AVFilter() {
        super((Pointer)null);
        this.allocate();
    }

    public AVFilter(long size) {
        super((Pointer)null);
        this.allocateArray(size);
    }

    public AVFilter(Pointer p2) {
        super(p2);
    }

    private native void allocate();

    private native void allocateArray(long var1);

    public AVFilter position(long position) {
        return (AVFilter)super.position(position);
    }

    public AVFilter getPointer(long i2) {
        return (AVFilter)new AVFilter(this).offsetAddress(i2);
    }

    @Cast(value={"const char*"})
    public native BytePointer name();

    public native AVFilter name(BytePointer var1);

    @Cast(value={"const char*"})
    public native BytePointer description();

    public native AVFilter description(BytePointer var1);

    @Const
    public native AVFilterPad inputs();

    public native AVFilter inputs(AVFilterPad var1);

    @Const
    public native AVFilterPad outputs();

    public native AVFilter outputs(AVFilterPad var1);

    @Const
    public native AVClass priv_class();

    public native AVFilter priv_class(AVClass var1);

    public native int flags();

    public native AVFilter flags(int var1);

    public native Preinit_AVFilterContext preinit();

    public native AVFilter preinit(Preinit_AVFilterContext var1);

    public native Init_AVFilterContext init();

    public native AVFilter init(Init_AVFilterContext var1);

    public native Init_dict_AVFilterContext_PointerPointer init_dict();

    public native AVFilter init_dict(Init_dict_AVFilterContext_PointerPointer var1);

    public native Uninit_AVFilterContext uninit();

    public native AVFilter uninit(Uninit_AVFilterContext var1);

    public native Query_formats_AVFilterContext query_formats();

    public native AVFilter query_formats(Query_formats_AVFilterContext var1);

    public native int priv_size();

    public native AVFilter priv_size(int var1);

    public native int flags_internal();

    public native AVFilter flags_internal(int var1);

    public native AVFilter next();

    public native AVFilter next(AVFilter var1);

    public native Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int process_command();

    public native AVFilter process_command(Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int var1);

    public native Init_opaque_AVFilterContext_Pointer init_opaque();

    public native AVFilter init_opaque(Init_opaque_AVFilterContext_Pointer var1);

    public native Activate_AVFilterContext activate();

    public native AVFilter activate(Activate_AVFilterContext var1);

    static {
        Loader.load();
    }

    public static class Activate_AVFilterContext
    extends FunctionPointer {
        public Activate_AVFilterContext(Pointer p2) {
            super(p2);
        }

        protected Activate_AVFilterContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFilterContext var1);

        static {
            Loader.load();
        }
    }

    public static class Init_opaque_AVFilterContext_Pointer
    extends FunctionPointer {
        public Init_opaque_AVFilterContext_Pointer(Pointer p2) {
            super(p2);
        }

        protected Init_opaque_AVFilterContext_Pointer() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFilterContext var1, Pointer var2);

        static {
            Loader.load();
        }
    }

    public static class Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int
    extends FunctionPointer {
        public Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int(Pointer p2) {
            super(p2);
        }

        protected Process_command_AVFilterContext_BytePointer_BytePointer_BytePointer_int_int() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFilterContext var1, @Cast(value={"const char*"}) BytePointer var2, @Cast(value={"const char*"}) BytePointer var3, @Cast(value={"char*"}) BytePointer var4, int var5, int var6);

        static {
            Loader.load();
        }
    }

    public static class Query_formats_AVFilterContext
    extends FunctionPointer {
        public Query_formats_AVFilterContext(Pointer p2) {
            super(p2);
        }

        protected Query_formats_AVFilterContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFilterContext var1);

        static {
            Loader.load();
        }
    }

    public static class Uninit_AVFilterContext
    extends FunctionPointer {
        public Uninit_AVFilterContext(Pointer p2) {
            super(p2);
        }

        protected Uninit_AVFilterContext() {
            this.allocate();
        }

        private native void allocate();

        public native void call(AVFilterContext var1);

        static {
            Loader.load();
        }
    }

    public static class Init_dict_AVFilterContext_PointerPointer
    extends FunctionPointer {
        public Init_dict_AVFilterContext_PointerPointer(Pointer p2) {
            super(p2);
        }

        protected Init_dict_AVFilterContext_PointerPointer() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFilterContext var1, @Cast(value={"AVDictionary**"}) PointerPointer var2);

        static {
            Loader.load();
        }
    }

    public static class Init_AVFilterContext
    extends FunctionPointer {
        public Init_AVFilterContext(Pointer p2) {
            super(p2);
        }

        protected Init_AVFilterContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFilterContext var1);

        static {
            Loader.load();
        }
    }

    public static class Preinit_AVFilterContext
    extends FunctionPointer {
        public Preinit_AVFilterContext(Pointer p2) {
            super(p2);
        }

        protected Preinit_AVFilterContext() {
            this.allocate();
        }

        private native void allocate();

        public native int call(AVFilterContext var1);

        static {
            Loader.load();
        }
    }
}

