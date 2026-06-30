/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.cvkernels
 *  org.bytedeco.opencv.cvkernels$KernelData
 *  org.bytedeco.opencv.opencv_core.CvRect
 *  org.bytedeco.opencv.opencv_core.CvScalar
 *  org.bytedeco.opencv.opencv_core.IplImage
 */
package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import org.bytedeco.javacpp.Pointer;
import org.bytedeco.javacv.Parallel;
import org.bytedeco.opencv.cvkernels;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

public class cvkernels
extends org.bytedeco.opencv.cvkernels {
    private static ThreadLocal<ParallelData[]> parallelData = new ThreadLocal<ParallelData[]>(){

        @Override
        protected ParallelData[] initialValue() {
            ParallelData[] pd2 = new ParallelData[Parallel.getNumThreads()];
            for (int i2 = 0; i2 < pd2.length; ++i2) {
                pd2[i2] = new ParallelData();
            }
            return pd2;
        }
    };

    public static void multiWarpColorTransform(cvkernels.KernelData data, CvRect roi, final CvScalar fillColor) {
        int h2;
        int w2;
        int y2;
        int x2;
        final int size = (int)data.capacity();
        final ParallelData[] pd2 = parallelData.get();
        for (int i2 = 0; i2 < pd2.length; ++i2) {
            cvkernels.KernelData d2;
            int j2;
            if (pd2[i2].data == null || pd2[i2].data.capacity() < (long)size) {
                pd2[i2].data = new cvkernels.KernelData((long)size);
                for (j2 = 0; j2 < size; ++j2) {
                    d2 = pd2[i2].data.position((long)j2);
                    data.position((long)j2);
                    if (data.dstDstDot() == null) continue;
                    d2.dstDstDot(ByteBuffer.allocateDirect(data.dstDstDot().capacity() * 8).order(ByteOrder.nativeOrder()).asDoubleBuffer());
                }
            }
            for (j2 = 0; j2 < size; ++j2) {
                d2 = pd2[i2].data.position((long)j2);
                d2.put((Pointer)data.position((long)j2));
                d2.dstDstDot(d2.dstDstDot());
            }
        }
        IplImage img = data.position(0L).srcImg();
        final int depth = img.depth();
        if (roi != null) {
            x2 = roi.x();
            y2 = roi.y();
            w2 = roi.width();
            h2 = roi.height();
        } else {
            x2 = 0;
            y2 = 0;
            w2 = img.width();
            h2 = img.height();
        }
        Parallel.loop(y2, y2 + h2, pd2.length, new Parallel.Looper(){

            @Override
            public void loop(int from, int to2, int looperID) {
                CvRect r2 = pd2[looperID].roi.x(x2).y(from).width(w2).height(to2 - from);
                if (depth == 32) {
                    org.bytedeco.opencv.cvkernels.multiWarpColorTransform32F((cvkernels.KernelData)pd2[looperID].data.position(0L), (int)size, (CvRect)r2, (CvScalar)fillColor);
                } else if (depth == 8) {
                    org.bytedeco.opencv.cvkernels.multiWarpColorTransform8U((cvkernels.KernelData)pd2[looperID].data.position(0L), (int)size, (CvRect)r2, (CvScalar)fillColor);
                } else assert (false);
            }
        });
        for (int i3 = 0; i3 < size; ++i3) {
            int dstCount = 0;
            int dstCountZero = 0;
            int dstCountOutlier = 0;
            double srcDstDot = 0.0;
            double[] dstDstDot = null;
            if (data.dstDstDot() != null) {
                dstDstDot = new double[data.dstDstDot().capacity()];
            }
            for (int j3 = 0; j3 < pd2.length; ++j3) {
                cvkernels.KernelData d3 = pd2[j3].data.position((long)i3);
                dstCount += d3.dstCount();
                dstCountZero += d3.dstCountZero();
                dstCountOutlier += d3.dstCountOutlier();
                srcDstDot += d3.srcDstDot();
                if (dstDstDot == null || d3.dstDstDot() == null) continue;
                for (int k2 = 0; k2 < dstDstDot.length; ++k2) {
                    int n2 = k2;
                    dstDstDot[n2] = dstDstDot[n2] + d3.dstDstDot().get(k2);
                }
            }
            data.position((long)i3);
            data.dstCount(dstCount);
            data.dstCountZero(dstCountZero);
            data.dstCountOutlier(dstCountOutlier);
            data.srcDstDot(srcDstDot);
            if (dstDstDot == null || data.dstDstDot() == null) continue;
            data.dstDstDot().position(0);
            data.dstDstDot().put(dstDstDot);
        }
    }

    private static class ParallelData {
        cvkernels.KernelData data = null;
        CvRect roi = new CvRect();

        private ParallelData() {
        }
    }
}

