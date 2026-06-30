/*
 * Decompiled with CFR 0.152.
 */
package goblinbob.mobends.core.util;

public class BenchmarkTopic {
    private final String name;
    private final int maxIterations;
    private long start;
    private long accumulative = 0L;
    private int iterations;

    public BenchmarkTopic(String name, int iterations) {
        this.name = name;
        this.maxIterations = iterations;
    }

    public void start() {
        this.start = System.nanoTime();
    }

    public void stop() {
        long stop = System.nanoTime();
        this.accumulative += stop - this.start;
        ++this.iterations;
        if (this.iterations >= this.maxIterations) {
            System.out.println(String.format("[Benchmark: %s] Average %fns", this.name, (double)this.accumulative / (double)this.iterations));
            this.iterations = 0;
            this.accumulative = 0L;
        }
    }
}

