/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.CharPointer;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.FloatPointer;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.LongPointer;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacpp.ShortPointer;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit={javacpp.class})
public class PointerPointer<P extends Pointer>
extends Pointer {
    private static final Logger logger;
    private P[] pointerArray;

    public PointerPointer(String ... array) {
        this(array.length);
        this.putString(array);
    }

    public PointerPointer(String[] array, String charsetName) throws UnsupportedEncodingException {
        this(array.length);
        this.putString(array, charsetName);
    }

    public PointerPointer(String[] array, Charset charset) {
        this(array.length);
        this.putString(array, charset);
    }

    public PointerPointer(P ... array) {
        this(array.length);
        this.put((Pointer[])array);
    }

    public PointerPointer(byte[] ... array) {
        this(array.length);
        this.put(array);
    }

    public PointerPointer(short[] ... array) {
        this(array.length);
        this.put(array);
    }

    public PointerPointer(int[] ... array) {
        this(array.length);
        this.put(array);
    }

    public PointerPointer(long[] ... array) {
        this(array.length);
        this.put(array);
    }

    public PointerPointer(float[] ... array) {
        this(array.length);
        this.put(array);
    }

    public PointerPointer(double[] ... array) {
        this(array.length);
        this.put(array);
    }

    public PointerPointer(char[] ... array) {
        this(array.length);
        this.put(array);
    }

    public PointerPointer(long size) {
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
            OutOfMemoryError e2 = new OutOfMemoryError("Cannot allocate new PointerPointer(" + size + "): totalBytes = " + PointerPointer.formatBytes(PointerPointer.totalBytes()) + ", physicalBytes = " + PointerPointer.formatBytes(PointerPointer.physicalBytes()));
            e2.initCause(e3);
            throw e2;
        }
    }

    public PointerPointer() {
    }

    public PointerPointer(Pointer p2) {
        super(p2);
    }

    private native void allocateArray(long var1);

    public PointerPointer<P> position(long position) {
        return (PointerPointer)super.position(position);
    }

    public PointerPointer<P> limit(long limit) {
        return (PointerPointer)super.limit(limit);
    }

    public PointerPointer<P> capacity(long capacity) {
        return (PointerPointer)super.capacity(capacity);
    }

    public PointerPointer<P> getPointer(long i2) {
        return (PointerPointer)new PointerPointer<P>(this).offsetAddress(i2);
    }

    public String getString(long i2) {
        BytePointer p2 = this.get(BytePointer.class, i2);
        return p2 != null ? p2.getString() : null;
    }

    public String getString(long i2, String charsetName) throws UnsupportedEncodingException {
        BytePointer p2 = this.get(BytePointer.class, i2);
        return p2 != null ? p2.getString(charsetName) : null;
    }

    public String getString(long i2, Charset charset) {
        BytePointer p2 = this.get(BytePointer.class, i2);
        return p2 != null ? p2.getString(charset) : null;
    }

    public PointerPointer<P> putString(String ... array) {
        this.pointerArray = new BytePointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new BytePointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> putString(String[] array, String charsetName) throws UnsupportedEncodingException {
        this.pointerArray = new BytePointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new BytePointer(array[i2], charsetName) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> putString(String[] array, Charset charset) {
        this.pointerArray = new BytePointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new BytePointer(array[i2], charset) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> put(P ... array) {
        this.pointerArray = array;
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.put(i2, (Pointer)array[i2]);
        }
        return this;
    }

    public PointerPointer<P> put(byte[] ... array) {
        this.pointerArray = new BytePointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new BytePointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> put(short[] ... array) {
        this.pointerArray = new ShortPointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new ShortPointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> put(int[] ... array) {
        this.pointerArray = new IntPointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new IntPointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> put(long[] ... array) {
        this.pointerArray = new LongPointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new LongPointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> put(float[] ... array) {
        this.pointerArray = new FloatPointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new FloatPointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> put(double[] ... array) {
        this.pointerArray = new DoublePointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new DoublePointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public PointerPointer<P> put(char[] ... array) {
        this.pointerArray = new CharPointer[array.length];
        for (int i2 = 0; i2 < array.length; ++i2) {
            this.pointerArray[i2] = array[i2] != null ? new CharPointer(array[i2]) : null;
        }
        return this.put((Pointer[])this.pointerArray);
    }

    public Pointer get() {
        return this.get(0L);
    }

    public P get(Class<P> cls) {
        return this.get(cls, 0L);
    }

    public Pointer get(long i2) {
        return this.get(Pointer.class, i2);
    }

    public native P get(Class<P> var1, long var2);

    public PointerPointer<P> put(Pointer p2) {
        return this.put(0L, p2);
    }

    public native PointerPointer<P> put(long var1, Pointer var3);

    static {
        block2: {
            logger = Logger.create(PointerPointer.class);
            try {
                Loader.load();
            }
            catch (Throwable t2) {
                if (!logger.isDebugEnabled()) break block2;
                logger.debug("Could not load PointerPointer: " + t2);
            }
        }
    }
}

