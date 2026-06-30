/*
 * Decompiled with CFR 0.152.
 */
package org.bytedeco.javacv;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Parallel {
    private static final ExecutorService threadPool = Executors.newCachedThreadPool();
    public static final String NUM_THREADS = "org.bytedeco.javacv.numthreads";

    public static int getNumThreads() {
        try {
            String s2 = System.getProperty(NUM_THREADS);
            if (s2 != null) {
                return Integer.valueOf(s2);
            }
        }
        catch (NumberFormatException e2) {
            throw new RuntimeException(e2);
        }
        return Parallel.getNumCores();
    }

    public static void setNumThreads(int numThreads) {
        System.setProperty(NUM_THREADS, Integer.toString(numThreads));
    }

    public static int getNumCores() {
        return Runtime.getRuntime().availableProcessors();
    }

    public static void run(Runnable ... runnables) {
        if (runnables.length == 1) {
            runnables[0].run();
            return;
        }
        Future[] futures = new Future[runnables.length];
        for (int i2 = 0; i2 < runnables.length; ++i2) {
            futures[i2] = threadPool.submit(runnables[i2]);
        }
        Throwable error = null;
        try {
            for (Future f2 : futures) {
                if (f2.isDone()) continue;
                f2.get();
            }
        }
        catch (Throwable t2) {
            error = t2;
        }
        if (error != null) {
            for (Future f2 : futures) {
                f2.cancel(true);
            }
            throw new RuntimeException(error);
        }
    }

    public static void loop(int from, int to2, Looper looper) {
        Parallel.loop(from, to2, Parallel.getNumThreads(), looper);
    }

    public static void loop(int from, int to2, int numThreads, final Looper looper) {
        int numLoopers = Math.min(to2 - from, numThreads > 0 ? numThreads : Parallel.getNumCores());
        Runnable[] runnables = new Runnable[numLoopers];
        for (int i2 = 0; i2 < numLoopers; ++i2) {
            final int subFrom = (to2 - from) * i2 / numLoopers + from;
            final int subTo = (to2 - from) * (i2 + 1) / numLoopers + from;
            final int looperID = i2;
            runnables[i2] = new Runnable(){

                @Override
                public void run() {
                    looper.loop(subFrom, subTo, looperID);
                }
            };
        }
        Parallel.run(runnables);
    }

    public static interface Looper {
        public void loop(int var1, int var2, int var3);
    }
}

