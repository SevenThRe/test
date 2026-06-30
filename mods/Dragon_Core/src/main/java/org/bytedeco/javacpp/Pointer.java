/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacpp;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;
import org.bytedeco.javacpp.BytePointer;
import org.bytedeco.javacpp.Loader;
import org.bytedeco.javacpp.PointerScope;
import org.bytedeco.javacpp.annotation.Name;
import org.bytedeco.javacpp.annotation.Properties;
import org.bytedeco.javacpp.presets.javacpp;
import org.bytedeco.javacpp.tools.Logger;

@Properties(inherit={javacpp.class})
public class Pointer
implements AutoCloseable {
    private static final Logger logger;
    private static final ReferenceQueue<Pointer> referenceQueue;
    static final Thread deallocatorThread;
    static final long maxBytes;
    static final long maxPhysicalBytes;
    static final int maxRetries;
    protected long address = 0L;
    protected long position = 0L;
    protected long limit = 0L;
    protected long capacity = 0L;
    private Deallocator deallocator = null;

    public Pointer() {
    }

    public Pointer(Pointer p2) {
        if (p2 != null) {
            this.address = p2.address;
            this.position = p2.position;
            this.limit = p2.limit;
            this.capacity = p2.capacity;
            if (p2.deallocator != null) {
                this.deallocator = new ProxyDeallocator(this, p2);
            }
        }
    }

    public Pointer(Buffer b2) {
        if (b2 != null) {
            this.allocate(b2);
        }
        if (!this.isNull()) {
            this.address -= (long)(b2.position() * this.sizeof());
            this.position = b2.position();
            this.limit = b2.limit();
            this.capacity = b2.capacity();
            this.deallocator = new ProxyDeallocator(this, b2);
        }
    }

    private native void allocate(Buffer var1);

    void init(long allocatedAddress, long allocatedCapacity, long ownerAddress, long deallocatorAddress) {
        this.address = allocatedAddress;
        this.position = 0L;
        this.limit = allocatedCapacity;
        this.capacity = allocatedCapacity;
        if (ownerAddress != 0L && deallocatorAddress != 0L) {
            this.deallocator(new NativeDeallocator(this, ownerAddress, deallocatorAddress));
        }
    }

    protected <P extends Pointer> P offsetAddress(long i2) {
        this.address += i2 * (long)this.sizeof();
        this.limit = Math.max(0L, this.limit - i2);
        this.capacity = Math.max(0L, this.capacity - i2);
        return (P)this;
    }

    protected static <P extends Pointer> P withDeallocator(P p2) {
        return p2.deallocator(new CustomDeallocator(p2));
    }

    public static String formatBytes(long bytes) {
        if (bytes < 102400L) {
            return bytes + "";
        }
        if ((bytes /= 1024L) < 102400L) {
            return bytes + "K";
        }
        if ((bytes /= 1024L) < 102400L) {
            return bytes + "M";
        }
        if ((bytes /= 1024L) < 102400L) {
            return bytes + "G";
        }
        return bytes / 1024L + "T";
    }

    public static long parseBytes(String string, long relativeMultiple) throws NumberFormatException {
        int i2;
        for (i2 = 0; i2 < string.length() && Character.isDigit(string.charAt(i2)); ++i2) {
        }
        long size = Long.parseLong(string.substring(0, i2));
        switch (string.substring(i2).trim().toLowerCase()) {
            case "%": {
                size = size * relativeMultiple / 100L;
                break;
            }
            case "t": 
            case "tb": {
                size *= 1024L;
            }
            case "g": 
            case "gb": {
                size *= 1024L;
            }
            case "m": 
            case "mb": {
                size *= 1024L;
            }
            case "k": 
            case "kb": {
                size *= 1024L;
            }
            case "": {
                break;
            }
            default: {
                throw new NumberFormatException("Cannot parse into bytes: " + string);
            }
        }
        return size;
    }

    public static void deallocateReferences() {
        DeallocatorReference r2;
        while (referenceQueue != null && (r2 = (DeallocatorReference)referenceQueue.poll()) != null) {
            r2.clear();
            r2.remove();
        }
    }

    public static long maxBytes() {
        return maxBytes;
    }

    public static long totalBytes() {
        return DeallocatorReference.totalBytes;
    }

    public static long totalCount() {
        return DeallocatorReference.totalCount;
    }

    public static long maxPhysicalBytes() {
        return maxPhysicalBytes;
    }

    @Name(value={"JavaCPP_trimMemory"})
    private static native boolean trimMemory();

    @Name(value={"JavaCPP_physicalBytes"})
    public static native long physicalBytes();

    @Name(value={"JavaCPP_totalPhysicalBytes"})
    public static native long totalPhysicalBytes();

    @Name(value={"JavaCPP_availablePhysicalBytes"})
    public static native long availablePhysicalBytes();

    public static boolean isNull(Pointer p2) {
        return p2 == null || p2.address == 0L;
    }

    public boolean isNull() {
        return this.address == 0L;
    }

    public void setNull() {
        this.address = 0L;
    }

    public long address() {
        return this.address;
    }

    public long position() {
        return this.position;
    }

    public <P extends Pointer> P position(long position) {
        this.position = position;
        return (P)this;
    }

    public long limit() {
        return this.limit;
    }

    public <P extends Pointer> P limit(long limit) {
        this.limit = limit;
        return (P)this;
    }

    public long capacity() {
        return this.capacity;
    }

    public <P extends Pointer> P capacity(long capacity) {
        this.limit = capacity;
        this.capacity = capacity;
        return (P)this;
    }

    protected Deallocator deallocator() {
        return this.deallocator;
    }

    /*
     * WARNING - Removed try catching itself - possible behaviour change.
     * Enabled aggressive block sorting
     * Enabled unnecessary exception pruning
     * Enabled aggressive exception aggregation
     * Converted monitor instructions to comments
     * Lifted jumps to return sites
     */
    protected <P extends Pointer> P deallocator(Deallocator deallocator) {
        Iterator<PointerScope> it2;
        if (this.deallocator != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Predeallocating " + this);
            }
            this.deallocator.deallocate();
            this.deallocator = null;
        }
        if (deallocator == null) return (P)this;
        if (deallocator.equals(null)) return (P)this;
        DeallocatorReference r2 = deallocator instanceof DeallocatorReference ? (DeallocatorReference)deallocator : new DeallocatorReference(this, deallocator);
        this.deallocator = r2;
        if (referenceQueue != null) {
            long lastPhysicalBytes;
            block16: {
                Class<DeallocatorThread> clazz = DeallocatorThread.class;
                // MONITORENTER : org.bytedeco.javacpp.Pointer$DeallocatorThread.class
                int count = 0;
                lastPhysicalBytes = maxPhysicalBytes > 0L ? Pointer.physicalBytes() : 0L;
                try {
                    while (count++ < maxRetries && (maxBytes > 0L && DeallocatorReference.totalBytes + r2.bytes > maxBytes || maxPhysicalBytes > 0L && lastPhysicalBytes > maxPhysicalBytes)) {
                        if (logger.isDebugEnabled()) {
                            logger.debug("Calling System.gc() and Pointer.trimMemory() in " + this);
                        }
                        System.gc();
                        Thread.sleep(100L);
                        Pointer.trimMemory();
                        lastPhysicalBytes = maxPhysicalBytes > 0L ? Pointer.physicalBytes() : 0L;
                    }
                }
                catch (InterruptedException ex2) {
                    Thread.currentThread().interrupt();
                }
                catch (UnsatisfiedLinkError e2) {
                    if (!logger.isDebugEnabled()) break block16;
                    logger.debug(e2.getMessage());
                }
            }
            if (maxBytes > 0L && DeallocatorReference.totalBytes + r2.bytes > maxBytes) {
                this.deallocate();
                throw new OutOfMemoryError("Failed to allocate memory within limits: totalBytes (" + Pointer.formatBytes(DeallocatorReference.totalBytes) + " + " + Pointer.formatBytes(r2.bytes) + ") > maxBytes (" + Pointer.formatBytes(maxBytes) + ")");
            }
            if (maxPhysicalBytes > 0L && lastPhysicalBytes > maxPhysicalBytes) {
                this.deallocate();
                throw new OutOfMemoryError("Physical memory usage is too high: physicalBytes (" + Pointer.formatBytes(lastPhysicalBytes) + ") > maxPhysicalBytes (" + Pointer.formatBytes(maxPhysicalBytes) + ")");
            }
            if (logger.isDebugEnabled()) {
                logger.debug("Registering " + this);
            }
            r2.add();
            // MONITOREXIT : clazz
        }
        if ((it2 = PointerScope.getScopeIterator()) == null) return (P)this;
        while (it2.hasNext()) {
            try {
                it2.next().attach(this);
                return (P)this;
            }
            catch (IllegalArgumentException e3) {
            }
        }
        return (P)this;
    }

    @Override
    public void close() {
        this.releaseReference();
    }

    public void deallocate() {
        this.deallocate(true);
    }

    public void deallocate(boolean deallocate) {
        DeallocatorReference r2 = (DeallocatorReference)this.deallocator;
        if (deallocate && this.deallocator != null) {
            if (logger.isDebugEnabled()) {
                logger.debug("Deallocating " + this);
            }
            this.deallocator.deallocate();
            this.deallocator = null;
            this.address = 0L;
        }
        if (r2 != null) {
            r2.deallocator = null;
            r2.clear();
            r2.remove();
            r2.deallocator = this.deallocator;
        }
    }

    public <P extends Pointer> P retainReference() {
        ReferenceCounter r2 = (ReferenceCounter)((Object)this.deallocator);
        if (r2 != null) {
            r2.retain();
        }
        return (P)this;
    }

    public boolean releaseReference() {
        DeallocatorReference r2 = (DeallocatorReference)this.deallocator;
        if (r2 != null && r2.release()) {
            this.deallocator = null;
            this.address = 0L;
            r2.clear();
            r2.remove();
            return true;
        }
        return false;
    }

    public int referenceCount() {
        ReferenceCounter r2 = (ReferenceCounter)((Object)this.deallocator);
        return r2 != null ? r2.count() : -1;
    }

    public static int offsetof(Class<? extends Pointer> type, String member) {
        return Loader.offsetof(type, member);
    }

    public int offsetof(String member) {
        int offset = -1;
        try {
            Class<?> c2 = this.getClass();
            if (c2 != Pointer.class) {
                offset = Loader.offsetof(c2, member);
            }
        }
        catch (ClassCastException | NullPointerException e2) {
            return offset;
        }
        return offset;
    }

    public static int sizeof(Class<? extends Pointer> type) {
        return Loader.sizeof(type);
    }

    public int sizeof() {
        Class<?> c2 = this.getClass();
        if (c2 == Pointer.class || c2 == BytePointer.class) {
            return 1;
        }
        return this.offsetof("sizeof");
    }

    private native ByteBuffer asDirectBuffer();

    public ByteBuffer asByteBuffer() {
        if (this.isNull()) {
            return null;
        }
        if (this.limit > 0L && this.limit < this.position) {
            throw new IllegalArgumentException("limit < position: (" + this.limit + " < " + this.position + ")");
        }
        int size = this.sizeof();
        Pointer p2 = new Pointer();
        p2.address = this.address;
        return super.asDirectBuffer().order(ByteOrder.nativeOrder());
    }

    public Buffer asBuffer() {
        return this.asByteBuffer();
    }

    public static native Pointer malloc(long var0);

    public static native Pointer calloc(long var0, long var2);

    public static native Pointer realloc(Pointer var0, long var1);

    public static native void free(Pointer var0);

    public static native Pointer memchr(Pointer var0, int var1, long var2);

    public static native int memcmp(Pointer var0, Pointer var1, long var2);

    public static native Pointer memcpy(Pointer var0, Pointer var1, long var2);

    public static native Pointer memmove(Pointer var0, Pointer var1, long var2);

    public static native Pointer memset(Pointer var0, int var1, long var2);

    public <P extends Pointer> P getPointer() {
        return this.getPointer(0L);
    }

    public <P extends Pointer> P getPointer(long i2) {
        return (P)this.getPointer(this.getClass(), i2);
    }

    public <P extends Pointer> P getPointer(Class<P> type) {
        return this.getPointer(type, 0L);
    }

    public <P extends Pointer> P getPointer(Class<P> type, long i2) {
        try {
            Pointer p2 = (Pointer)type.getDeclaredConstructor(Pointer.class).newInstance(this);
            p2.position = this.position != 0L ? Math.max(0L, this.position * (long)this.sizeof() / (long)p2.sizeof()) : 0L;
            p2.limit = this.limit != 0L ? Math.max(0L, this.limit * (long)this.sizeof() / (long)p2.sizeof()) : 0L;
            p2.capacity = this.capacity != 0L ? Math.max(0L, this.capacity * (long)this.sizeof() / (long)p2.sizeof()) : 0L;
            return p2.offsetAddress(i2);
        }
        catch (ReflectiveOperationException e2) {
            throw new RuntimeException(e2);
        }
    }

    public <P extends Pointer> P put(Pointer p2) {
        if (p2.limit > 0L && p2.limit < p2.position) {
            throw new IllegalArgumentException("limit < position: (" + p2.limit + " < " + p2.position + ")");
        }
        int size = this.sizeof();
        int psize = p2.sizeof();
        long length = (long)psize * (p2.limit <= 0L ? 1L : p2.limit - p2.position);
        this.position *= (long)size;
        p2.position *= (long)psize;
        Pointer.memcpy(this, p2, length);
        this.position /= (long)size;
        p2.position /= (long)psize;
        return (P)this;
    }

    public <P extends Pointer> P fill(int b2) {
        if (this.limit > 0L && this.limit < this.position) {
            throw new IllegalArgumentException("limit < position: (" + this.limit + " < " + this.position + ")");
        }
        int size = this.sizeof();
        long length = (long)size * (this.limit <= 0L ? 1L : this.limit - this.position);
        this.position *= (long)size;
        Pointer.memset(this, b2, length);
        this.position /= (long)size;
        return (P)this;
    }

    public <P extends Pointer> P zero() {
        return this.fill(0);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null) {
            return this.isNull();
        }
        if (obj.getClass() != this.getClass() && obj.getClass() != Pointer.class && this.getClass() != Pointer.class) {
            return false;
        }
        Pointer other = (Pointer)obj;
        return this.address == other.address && this.position == other.position;
    }

    public int hashCode() {
        return (int)this.address;
    }

    public String toString() {
        return this.getClass().getName() + "[address=0x" + Long.toHexString(this.address) + ",position=" + this.position + ",limit=" + this.limit + ",capacity=" + this.capacity + ",deallocator=" + this.deallocator + "]";
    }

    static {
        block16: {
            long maxMemory;
            logger = Logger.create(Pointer.class);
            String s2 = System.getProperty("org.bytedeco.javacpp.nopointergc", "false").toLowerCase();
            if ((s2 = System.getProperty("org.bytedeco.javacpp.noPointerGC", s2).toLowerCase()).equals("true") || s2.equals("t") || s2.equals("")) {
                referenceQueue = null;
                deallocatorThread = null;
            } else {
                referenceQueue = new ReferenceQueue();
                deallocatorThread = new DeallocatorThread();
            }
            long m2 = maxMemory = Runtime.getRuntime().maxMemory();
            s2 = System.getProperty("org.bytedeco.javacpp.maxbytes");
            s2 = System.getProperty("org.bytedeco.javacpp.maxBytes", s2);
            if (s2 != null && s2.length() > 0) {
                try {
                    m2 = Pointer.parseBytes(s2, maxMemory);
                }
                catch (NumberFormatException e2) {
                    throw new RuntimeException(e2);
                }
            }
            m2 = (maxBytes = m2) > 0L ? maxBytes + 3L * maxMemory : 0L;
            s2 = System.getProperty("org.bytedeco.javacpp.maxphysicalbytes");
            if ((s2 = System.getProperty("org.bytedeco.javacpp.maxPhysicalBytes", s2)) != null && s2.length() > 0) {
                try {
                    m2 = Pointer.parseBytes(s2, maxMemory);
                }
                catch (NumberFormatException e3) {
                    throw new RuntimeException(e3);
                }
            }
            maxPhysicalBytes = m2;
            int n2 = 10;
            s2 = System.getProperty("org.bytedeco.javacpp.maxretries");
            if ((s2 = System.getProperty("org.bytedeco.javacpp.maxRetries", s2)) != null && s2.length() > 0) {
                try {
                    n2 = Integer.parseInt(s2);
                }
                catch (NumberFormatException e4) {
                    throw new RuntimeException(e4);
                }
            }
            maxRetries = n2;
            try {
                Loader.load();
            }
            catch (Throwable t2) {
                if (!logger.isDebugEnabled()) break block16;
                logger.debug("Could not load Pointer: " + t2);
            }
        }
        String mx2 = System.getProperty("org.bytedeco.javacpp.mxbean", "false").toLowerCase();
        if (mx2.equals("true") || mx2.equals("t") || mx2.equals("")) {
            try {
                Class<?> c2 = Class.forName("org.bytedeco.javacpp.tools.PointerBufferPoolMXBean");
                Method method = c2.getDeclaredMethod("register", new Class[0]);
                method.invoke(null, new Object[0]);
            }
            catch (ReflectiveOperationException e5) {
                logger.warn("Could not register PointerBufferPoolMXBean: " + e5);
            }
        }
    }

    static class DeallocatorThread
    extends Thread {
        DeallocatorThread() {
            super("JavaCPP Deallocator");
            this.setPriority(10);
            this.setDaemon(true);
            this.setContextClassLoader(null);
            this.start();
        }

        @Override
        public void run() {
            try {
                while (true) {
                    DeallocatorReference r2 = (DeallocatorReference)referenceQueue.remove();
                    r2.clear();
                    r2.remove();
                }
            }
            catch (InterruptedException ex2) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    static class DeallocatorReference
    extends PhantomReference<Pointer>
    implements Deallocator,
    ReferenceCounter {
        static volatile DeallocatorReference head = null;
        volatile DeallocatorReference prev = this;
        volatile DeallocatorReference next = this;
        Deallocator deallocator;
        static volatile long totalBytes = 0L;
        static volatile long totalCount = 0L;
        long bytes;
        AtomicInteger count;

        DeallocatorReference(Pointer p2, Deallocator deallocator) {
            super(p2, referenceQueue);
            this.deallocator = deallocator;
            this.bytes = p2.capacity != 0L && referenceQueue != null ? p2.capacity * (long)p2.sizeof() : 0L;
            this.count = new AtomicInteger(0);
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        final void add() {
            Class<DeallocatorReference> clazz = DeallocatorReference.class;
            synchronized (DeallocatorReference.class) {
                if (head == null) {
                    head = this;
                    this.next = null;
                    this.prev = null;
                } else {
                    this.prev = null;
                    this.next = head;
                    this.next.prev = head = this;
                }
                totalBytes += this.bytes;
                ++totalCount;
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return;
            }
        }

        /*
         * WARNING - Removed try catching itself - possible behaviour change.
         */
        final void remove() {
            if (this.prev == this && this.next == this) {
                return;
            }
            Class<DeallocatorReference> clazz = DeallocatorReference.class;
            synchronized (DeallocatorReference.class) {
                if (this.prev == null) {
                    head = this.next;
                } else {
                    this.prev.next = this.next;
                }
                if (this.next != null) {
                    this.next.prev = this.prev;
                }
                this.prev = this.next = this;
                totalBytes -= this.bytes;
                --totalCount;
                // ** MonitorExit[var1_1] (shouldn't be in output)
                return;
            }
        }

        @Override
        public void clear() {
            super.clear();
            if (this.deallocator != null) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Collecting " + this);
                }
                this.deallocate();
            }
        }

        @Override
        public void deallocate() {
            if (this.deallocator != null) {
                this.deallocator.deallocate();
                this.deallocator = null;
            }
        }

        @Override
        public void retain() {
            if (this.deallocator != null) {
                this.count.incrementAndGet();
            }
        }

        @Override
        public boolean release() {
            if (this.deallocator != null && this.count.decrementAndGet() <= 0) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Releasing " + this);
                }
                this.deallocate();
                return true;
            }
            return false;
        }

        @Override
        public int count() {
            return this.deallocator != null ? this.count.get() : -1;
        }

        public String toString() {
            return this.getClass().getName() + "[deallocator=" + this.deallocator + ",count=" + this.count + "]";
        }
    }

    static class ProxyDeallocator
    extends DeallocatorReference {
        Buffer buffer;
        Pointer pointer;

        public ProxyDeallocator(Pointer p2, Buffer b2) {
            super(p2, (Deallocator)null);
            this.deallocator = this;
            this.buffer = b2;
        }

        public ProxyDeallocator(Pointer p2, Pointer p22) {
            super(p2, (Deallocator)null);
            this.deallocator = this;
            this.pointer = p22;
        }

        @Override
        public void deallocate() {
            this.buffer = null;
            if (this.pointer != null) {
                this.pointer.deallocate();
            }
        }

        @Override
        public void retain() {
            if (this.pointer != null) {
                this.pointer.retainReference();
            }
        }

        @Override
        public boolean release() {
            return this.pointer != null ? this.pointer.releaseReference() : false;
        }

        @Override
        public int count() {
            return this.pointer != null ? this.pointer.referenceCount() : -1;
        }

        @Override
        public String toString() {
            return this.getClass().getName() + "[buffer=" + this.buffer + ",pointer=" + this.pointer + "]";
        }
    }

    protected static class NativeDeallocator
    extends DeallocatorReference {
        private long ownerAddress;
        private long deallocatorAddress;

        NativeDeallocator(Pointer p2, long ownerAddress, long deallocatorAddress) {
            super(p2, (Deallocator)null);
            this.deallocator = this;
            this.ownerAddress = ownerAddress;
            this.deallocatorAddress = deallocatorAddress;
        }

        @Override
        public void deallocate() {
            if (this.ownerAddress != 0L && this.deallocatorAddress != 0L) {
                this.deallocate(this.ownerAddress, this.deallocatorAddress);
                this.deallocatorAddress = 0L;
                this.ownerAddress = 0L;
            }
        }

        private native void deallocate(long var1, long var3);

        @Override
        public String toString() {
            return this.getClass().getName() + "[ownerAddress=0x" + Long.toHexString(this.ownerAddress) + ",deallocatorAddress=0x" + Long.toHexString(this.deallocatorAddress) + "]";
        }
    }

    protected static class CustomDeallocator
    extends DeallocatorReference {
        Pointer pointer = null;
        Method method = null;

        public CustomDeallocator(Pointer p2) {
            super(p2, (Deallocator)null);
            this.deallocator = this;
            Class<?> cls = p2.getClass();
            for (Method m2 : cls.getDeclaredMethods()) {
                Class<?>[] parameters = m2.getParameterTypes();
                if (!Modifier.isStatic(m2.getModifiers()) || !m2.getReturnType().equals(Void.TYPE) || !m2.getName().equals("deallocate") || parameters.length != 1 || !Pointer.class.isAssignableFrom(parameters[0])) continue;
                m2.setAccessible(true);
                this.method = m2;
                break;
            }
            if (this.method == null) {
                throw new RuntimeException(new NoSuchMethodException("static void " + cls.getCanonicalName() + ".deallocate(" + Pointer.class.getCanonicalName() + ")"));
            }
            try {
                Constructor<?> c2 = cls.getConstructor(Pointer.class);
                c2.setAccessible(true);
                this.pointer = (Pointer)c2.newInstance(p2);
            }
            catch (Exception ex2) {
                throw new RuntimeException(ex2);
            }
        }

        @Override
        public void deallocate() {
            try {
                this.method.invoke(null, this.pointer);
                this.pointer.setNull();
            }
            catch (Exception ex2) {
                throw new RuntimeException(ex2);
            }
        }

        @Override
        public String toString() {
            return this.getClass().getName() + "[pointer=" + this.pointer + ",method=" + this.method + "]";
        }
    }

    protected static interface ReferenceCounter {
        public void retain();

        public boolean release();

        public int count();
    }

    protected static interface Deallocator {
        public void deallocate();
    }
}

