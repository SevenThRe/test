/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.bytedeco.ffmpeg.avcodec.Cb_PointerPointer_int;
import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacpp.IntPointer;
import org.bytedeco.javacpp.PointerPointer;
import org.bytedeco.javacpp.annotation.Cast;

public class FFmpegLockCallback {
    private static boolean initialized = false;
    private static AtomicInteger lockCounter = new AtomicInteger(0);
    private static HashMap<Integer, Lock> lockArray = new HashMap();
    private static Cb_PointerPointer_int lockCallback = (Cb_PointerPointer_int)new Cb_PointerPointer_int(){

        @Override
        public int call(@Cast(value={"void**"}) PointerPointer mutex, @Cast(value={"AVLockOp"}) int op2) {
            switch (op2) {
                case 0: {
                    int number = lockCounter.incrementAndGet();
                    new IntPointer(mutex).put(0L, number);
                    lockArray.put(number, new ReentrantLock());
                    return 0;
                }
                case 1: {
                    int number = new IntPointer(mutex).get(0L);
                    Lock l2 = (Lock)lockArray.get(number);
                    if (l2 == null) {
                        System.err.println("Lock not found!");
                        return -1;
                    }
                    l2.lock();
                    return 0;
                }
                case 2: {
                    int number = new IntPointer(mutex).get(0L);
                    Lock l3 = (Lock)lockArray.get(number);
                    if (l3 == null) {
                        System.err.println("Lock not found!");
                        return -1;
                    }
                    l3.unlock();
                    return 0;
                }
                case 3: {
                    int number = new IntPointer(mutex).get(0L);
                    lockArray.remove(number);
                    mutex.put(0L, null);
                    return 0;
                }
            }
            return -1;
        }
    }.retainReference();

    public static synchronized void init() {
        if (!initialized) {
            initialized = true;
            avcodec.av_lockmgr_register(lockCallback);
        }
    }
}

