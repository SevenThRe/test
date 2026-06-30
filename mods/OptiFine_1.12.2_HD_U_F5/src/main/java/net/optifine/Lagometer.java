/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  bib
 *  bid
 *  biq
 *  bit
 *  buk
 *  bus
 *  bve
 *  cdy
 *  org.lwjgl.opengl.GL11
 *  rl
 */
package net.optifine;

import org.lwjgl.opengl.GL11;

public class Lagometer {
    private static bib mc;
    private static bid gameSettings;
    private static rl profiler;
    public static boolean active;
    public static TimerNano timerTick;
    public static TimerNano timerScheduledExecutables;
    public static TimerNano timerChunkUpload;
    public static TimerNano timerChunkUpdate;
    public static TimerNano timerVisibility;
    public static TimerNano timerTerrain;
    public static TimerNano timerServer;
    private static long[] timesFrame;
    private static long[] timesTick;
    private static long[] timesScheduledExecutables;
    private static long[] timesChunkUpload;
    private static long[] timesChunkUpdate;
    private static long[] timesVisibility;
    private static long[] timesTerrain;
    private static long[] timesServer;
    private static boolean[] gcs;
    private static int numRecordedFrameTimes;
    private static long prevFrameTimeNano;
    private static long renderTimeNano;
    private static long memTimeStartMs;
    private static long memStart;
    private static long memTimeLast;
    private static long memLast;
    private static long memTimeDiffMs;
    private static long memDiff;
    private static int memMbSec;

    public static boolean updateMemoryAllocation() {
        long timeNowMs = System.currentTimeMillis();
        long memNow = Lagometer.getMemoryUsed();
        boolean gc = false;
        if (memNow < memLast) {
            double memDiffMb = (double)memDiff / 1000000.0;
            double timeDiffSec = (double)memTimeDiffMs / 1000.0;
            int mbSec = (int)(memDiffMb / timeDiffSec);
            if (mbSec > 0) {
                memMbSec = mbSec;
            }
            memTimeStartMs = timeNowMs;
            memStart = memNow;
            memTimeDiffMs = 0L;
            memDiff = 0L;
            gc = true;
        } else {
            memTimeDiffMs = timeNowMs - memTimeStartMs;
            memDiff = memNow - memStart;
        }
        memTimeLast = timeNowMs;
        memLast = memNow;
        return gc;
    }

    private static long getMemoryUsed() {
        Runtime r = Runtime.getRuntime();
        return r.totalMemory() - r.freeMemory();
    }

    public static void updateLagometer() {
        if (mc == null) {
            mc = bib.z();
            gameSettings = Lagometer.mc.t;
            profiler = Lagometer.mc.B;
        }
        if (!Lagometer.gameSettings.ax || !Lagometer.gameSettings.ofLagometer && !Lagometer.gameSettings.az) {
            active = false;
            prevFrameTimeNano = -1L;
            return;
        }
        active = true;
        long timeNowNano = System.nanoTime();
        if (prevFrameTimeNano == -1L) {
            prevFrameTimeNano = timeNowNano;
            return;
        }
        int frameIndex = numRecordedFrameTimes & timesFrame.length - 1;
        ++numRecordedFrameTimes;
        boolean gc = Lagometer.updateMemoryAllocation();
        Lagometer.timesFrame[frameIndex] = timeNowNano - prevFrameTimeNano - renderTimeNano;
        Lagometer.timesTick[frameIndex] = Lagometer.timerTick.timeNano;
        Lagometer.timesScheduledExecutables[frameIndex] = Lagometer.timerScheduledExecutables.timeNano;
        Lagometer.timesChunkUpload[frameIndex] = Lagometer.timerChunkUpload.timeNano;
        Lagometer.timesChunkUpdate[frameIndex] = Lagometer.timerChunkUpdate.timeNano;
        Lagometer.timesVisibility[frameIndex] = Lagometer.timerVisibility.timeNano;
        Lagometer.timesTerrain[frameIndex] = Lagometer.timerTerrain.timeNano;
        Lagometer.timesServer[frameIndex] = Lagometer.timerServer.timeNano;
        Lagometer.gcs[frameIndex] = gc;
        Lagometer.timerTick.reset();
        Lagometer.timerScheduledExecutables.reset();
        Lagometer.timerVisibility.reset();
        Lagometer.timerChunkUpdate.reset();
        Lagometer.timerChunkUpload.reset();
        Lagometer.timerTerrain.reset();
        Lagometer.timerServer.reset();
        prevFrameTimeNano = System.nanoTime();
    }

    public static void showLagometer(bit scaledResolution) {
        if (gameSettings == null) {
            return;
        }
        if (!Lagometer.gameSettings.ofLagometer && !Lagometer.gameSettings.az) {
            return;
        }
        long timeRenderStartNano = System.nanoTime();
        bus.m((int)256);
        bus.n((int)5889);
        bus.G();
        bus.h();
        bus.F();
        bus.a((double)0.0, (double)Lagometer.mc.d, (double)Lagometer.mc.e, (double)0.0, (double)1000.0, (double)3000.0);
        bus.n((int)5888);
        bus.G();
        bus.F();
        bus.c((float)0.0f, (float)0.0f, (float)-2000.0f);
        GL11.glLineWidth((float)1.0f);
        bus.z();
        bve tess = bve.a();
        buk tessellator = tess.c();
        tessellator.a(1, cdy.f);
        for (int frameNum = 0; frameNum < timesFrame.length; ++frameNum) {
            int lum = (frameNum - numRecordedFrameTimes & timesFrame.length - 1) * 100 / timesFrame.length;
            lum += 155;
            float baseHeight = Lagometer.mc.e;
            long heightFrame = 0L;
            if (gcs[frameNum]) {
                heightFrame = Lagometer.renderTime(frameNum, timesFrame[frameNum], lum, lum / 2, 0, baseHeight, tessellator);
                continue;
            }
            heightFrame = Lagometer.renderTime(frameNum, timesFrame[frameNum], lum, lum, lum, baseHeight, tessellator);
            baseHeight -= (float)Lagometer.renderTime(frameNum, timesServer[frameNum], lum / 2, lum / 2, lum / 2, baseHeight, tessellator);
            baseHeight -= (float)Lagometer.renderTime(frameNum, timesTerrain[frameNum], 0, lum, 0, baseHeight, tessellator);
            baseHeight -= (float)Lagometer.renderTime(frameNum, timesVisibility[frameNum], lum, lum, 0, baseHeight, tessellator);
            baseHeight -= (float)Lagometer.renderTime(frameNum, timesChunkUpdate[frameNum], lum, 0, 0, baseHeight, tessellator);
            baseHeight -= (float)Lagometer.renderTime(frameNum, timesChunkUpload[frameNum], lum, 0, lum, baseHeight, tessellator);
            baseHeight -= (float)Lagometer.renderTime(frameNum, timesScheduledExecutables[frameNum], 0, 0, lum, baseHeight, tessellator);
            baseHeight -= (float)Lagometer.renderTime(frameNum, timesTick[frameNum], 0, lum, lum, baseHeight, tessellator);
        }
        Lagometer.renderTimeDivider(0, timesFrame.length, 33333333L, 196, 196, 196, Lagometer.mc.e, tessellator);
        Lagometer.renderTimeDivider(0, timesFrame.length, 16666666L, 196, 196, 196, Lagometer.mc.e, tessellator);
        tess.b();
        bus.y();
        int y60 = Lagometer.mc.e - 80;
        int y30 = Lagometer.mc.e - 160;
        Lagometer.mc.k.a("30", 2, y30 + 1, -8947849);
        Lagometer.mc.k.a("30", 1, y30, -3881788);
        Lagometer.mc.k.a("60", 2, y60 + 1, -8947849);
        Lagometer.mc.k.a("60", 1, y60, -3881788);
        bus.n((int)5889);
        bus.H();
        bus.n((int)5888);
        bus.H();
        bus.y();
        float lumMem = 1.0f - (float)((double)(System.currentTimeMillis() - memTimeStartMs) / 1000.0);
        lumMem = Config.limit(lumMem, 0.0f, 1.0f);
        int memColR = (int)(170.0f + lumMem * 85.0f);
        int memColG = (int)(100.0f + lumMem * 55.0f);
        int memColB = (int)(10.0f + lumMem * 10.0f);
        int colMem = memColR << 16 | memColG << 8 | memColB;
        int posX = 512 / scaledResolution.e() + 2;
        int posY = Lagometer.mc.e / scaledResolution.e() - 8;
        biq cfr_ignored_0 = Lagometer.mc.q;
        biq.a((int)(posX - 1), (int)(posY - 1), (int)(posX + 50), (int)(posY + 10), (int)-1605349296);
        Lagometer.mc.k.a(" " + memMbSec + " MB/s", posX, posY, colMem);
        renderTimeNano = System.nanoTime() - timeRenderStartNano;
    }

    private static long renderTime(int frameNum, long time, int r, int g, int b2, float baseHeight, buk tessellator) {
        long heightTime = time / 200000L;
        if (heightTime < 3L) {
            return 0L;
        }
        tessellator.b((double)((float)frameNum + 0.5f), (double)(baseHeight - (float)heightTime + 0.5f), 0.0).b(r, g, b2, 255).d();
        tessellator.b((double)((float)frameNum + 0.5f), (double)(baseHeight + 0.5f), 0.0).b(r, g, b2, 255).d();
        return heightTime;
    }

    private static long renderTimeDivider(int frameStart, int frameEnd, long time, int r, int g, int b2, float baseHeight, buk tessellator) {
        long heightTime = time / 200000L;
        if (heightTime < 3L) {
            return 0L;
        }
        tessellator.b((double)((float)frameStart + 0.5f), (double)(baseHeight - (float)heightTime + 0.5f), 0.0).b(r, g, b2, 255).d();
        tessellator.b((double)((float)frameEnd + 0.5f), (double)(baseHeight - (float)heightTime + 0.5f), 0.0).b(r, g, b2, 255).d();
        return heightTime;
    }

    public static boolean isActive() {
        return active;
    }

    static {
        active = false;
        timerTick = new TimerNano();
        timerScheduledExecutables = new TimerNano();
        timerChunkUpload = new TimerNano();
        timerChunkUpdate = new TimerNano();
        timerVisibility = new TimerNano();
        timerTerrain = new TimerNano();
        timerServer = new TimerNano();
        timesFrame = new long[512];
        timesTick = new long[512];
        timesScheduledExecutables = new long[512];
        timesChunkUpload = new long[512];
        timesChunkUpdate = new long[512];
        timesVisibility = new long[512];
        timesTerrain = new long[512];
        timesServer = new long[512];
        gcs = new boolean[512];
        numRecordedFrameTimes = 0;
        prevFrameTimeNano = -1L;
        renderTimeNano = 0L;
        memTimeStartMs = System.currentTimeMillis();
        memStart = Lagometer.getMemoryUsed();
        memTimeLast = memTimeStartMs;
        memLast = memStart;
        memTimeDiffMs = 1L;
        memDiff = 0L;
        memMbSec = 0;
    }

    public static class TimerNano {
        public long timeStartNano = 0L;
        public long timeNano = 0L;

        public void start() {
            if (!active) {
                return;
            }
            if (this.timeStartNano == 0L) {
                this.timeStartNano = System.nanoTime();
            }
        }

        public void end() {
            if (!active) {
                return;
            }
            if (this.timeStartNano != 0L) {
                this.timeNano += System.nanoTime() - this.timeStartNano;
                this.timeStartNano = 0L;
            }
        }

        private void reset() {
            this.timeNano = 0L;
            this.timeStartNano = 0L;
        }
    }
}

