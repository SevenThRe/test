/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp;

import java.nio.FloatBuffer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit={javacpp.class})
public class FloatPointer
extends Pointer {
    private static final Logger logger;

    public FloatPointer(float ... array) {
        this(array.length);
        this.put(array);
    }

    public FloatPointer(FloatBuffer buffer) {
        super(buffer);
        if (buffer != null && !buffer.isDirect() && buffer.hasArray()) {
            float[] array = buffer.array();
            this.allocateArray(array.length - buffer.arrayOffset());
            this.put(array, buffer.arrayOffset(), array.length - buffer.arrayOffset());
            this.position(buffer.position());
            this.limit(buffer.limit());
        }
    }

    public FloatPointer(long size) {
        try {
            this.allocateArray(size);
            if (size > 0L && this.address == 0L) {
                throw new OutOfMemoryError("Native allocator returned address == 0");
            }
        }
        catch (UnsatisfiedLinkError e2) {
            throw new RuntimeException("No native JavaCPP library in memory. (Has Loader.load() been called?)", e2);
        }
        catch (OutOfMemoryError e3) {
            OutOfMemoryError e2 = new OutOfMemoryError("Cannot allocate new FloatPointer(" + size + "): totalBytes = " + FloatPointer.formatBytes(FloatPointer.totalBytes()) + ", physicalBytes = " + FloatPointer.formatBytes(FloatPointer.physicalBytes()));
            e2.initCause(e3);
            throw e2;
        }
    }

    public FloatPointer() {
    }

    public FloatPointer(Pointer p2) {
        super(p2);
    }

    private native void allocateArray(long var1);

    public FloatPointer position(long position) {
        return (FloatPointer)super.position(position);
    }

    public FloatPointer limit(long limit) {
        return (FloatPointer)super.limit(limit);
    }

    public FloatPointer capacity(long capacity) {
        return (FloatPointer)super.capacity(capacity);
    }

    @Override
    public int sizeof() {
        return this.getClass() == FloatPointer.class ? 4 : super.sizeof();
    }

    public FloatPointer getPointer(long i2) {
        return (FloatPointer)new FloatPointer(this).offsetAddress(i2);
    }

    public float get() {
        return this.get(0L);
    }

    public native float get(long var1);

    public FloatPointer put(float f2) {
        return this.put(0L, f2);
    }

    public native FloatPointer put(long var1, float var3);

    public FloatPointer get(float[] array) {
        return this.get(array, 0, array.length);
    }

    public FloatPointer put(float ... array) {
        return this.put(array, 0, array.length);
    }

    public native FloatPointer get(float[] var1, int var2, int var3);

    public native FloatPointer put(float[] var1, int var2, int var3);

    @Override
    public final FloatBuffer asBuffer() {
        return this.asByteBuffer().asFloatBuffer();
    }

    static {
        block2: {
            logger = Logger.create(FloatPointer.class);
            try {
                Loader.load();
            }
            catch (Throwable t2) {
                if (!logger.isDebugEnabled()) break block2;
                logger.debug("Could not load FloatPointer: " + t2);
            }
        }
    }
}

