/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp;

import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.annotation.Cast;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Name(value={"long"})
@Properties(inherit={javacpp.class})
public class CLongPointer
extends Pointer {
    private static final Logger logger;

    public CLongPointer(long ... array) {
        this((long)array.length);
        this.put(array);
    }

    public CLongPointer(long size) {
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
            OutOfMemoryError e2 = new OutOfMemoryError("Cannot allocate new CLongPointer(" + size + "): totalBytes = " + CLongPointer.formatBytes(CLongPointer.totalBytes()) + ", physicalBytes = " + CLongPointer.formatBytes(CLongPointer.physicalBytes()));
            e2.initCause(e3);
            throw e2;
        }
    }

    public CLongPointer() {
    }

    public CLongPointer(Pointer p2) {
        super(p2);
    }

    private native void allocateArray(long var1);

    public CLongPointer position(long position) {
        return (CLongPointer)super.position(position);
    }

    public CLongPointer limit(long limit) {
        return (CLongPointer)super.limit(limit);
    }

    public CLongPointer capacity(long capacity) {
        return (CLongPointer)super.capacity(capacity);
    }

    public CLongPointer getPointer(long i2) {
        return (CLongPointer)new CLongPointer(this).offsetAddress(i2);
    }

    public long get() {
        return this.get(0L);
    }

    @Cast(value={"long"})
    public native long get(long var1);

    public CLongPointer put(long l2) {
        return this.put(0L, l2);
    }

    public native CLongPointer put(long var1, long var3);

    public CLongPointer get(long[] array) {
        return this.get(array, 0, array.length);
    }

    public CLongPointer put(long ... array) {
        return this.put(array, 0, array.length);
    }

    public CLongPointer get(long[] array, int offset, int length) {
        for (int i2 = offset; i2 < offset + length; ++i2) {
            array[i2] = this.get(i2);
        }
        return this;
    }

    public CLongPointer put(long[] array, int offset, int length) {
        for (int i2 = offset; i2 < offset + length; ++i2) {
            this.put((long)i2, array[i2]);
        }
        return this;
    }

    static {
        block2: {
            logger = Logger.create(CLongPointer.class);
            try {
                Loader.load();
            }
            catch (Throwable t2) {
                if (!logger.isDebugEnabled()) break block2;
                logger.debug("Could not load CLongPointer: " + t2);
            }
        }
    }
}

