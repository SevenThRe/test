/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_imgproc
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvBox2D
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.CvPoint2D32f
 *  org.bytedeco.opencv.opencv_core.CvRect
 *  org.bytedeco.opencv.opencv_core.CvSize2D32f
 *  org.bytedeco.opencv.opencv_core.IplImage
 *  org.bytedeco.opencv.opencv_imgproc.CvMoments
 */
package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.ShortBuffer;
import java.util.Arrays;
import org.bytedeco.javacv.Parallel;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvBox2D;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint2D32f;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvSize2D32f;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_imgproc.CvMoments;

public class JavaCV {
    public static final double SQRT2 = 1.4142135623730951;
    public static final double FLT_EPSILON = 1.1920928955078125E-7;
    public static final double DBL_EPSILON = 2.220446049250313E-16;
    private static ThreadLocal<CvMoments> moments = CvMoments.createThreadLocal();
    private static ThreadLocal<CvMat> A8x8 = CvMat.createThreadLocal((int)8, (int)8);
    private static ThreadLocal<CvMat> b8x1 = CvMat.createThreadLocal((int)8, (int)1);
    private static ThreadLocal<CvMat> x8x1 = CvMat.createThreadLocal((int)8, (int)1);
    private static ThreadLocal<CvMat> A3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> b3x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> n3x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> H3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> M3x2 = CvMat.createThreadLocal((int)3, (int)2);
    private static ThreadLocal<CvMat> S2x2 = CvMat.createThreadLocal((int)2, (int)2);
    private static ThreadLocal<CvMat> U3x2 = CvMat.createThreadLocal((int)3, (int)2);
    private static ThreadLocal<CvMat> V2x2 = CvMat.createThreadLocal((int)2, (int)2);
    private static ThreadLocal<CvMat> R13x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> R23x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> t13x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> t23x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> n13x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> n23x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> H13x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> H23x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> S3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> U3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> V3x3 = CvMat.createThreadLocal((int)3, (int)3);

    public static double distanceToLine(double x1, double y1, double x2, double y2, double x3, double y3) {
        double dx2 = x2 - x1;
        double dy2 = y2 - y1;
        double d2 = dx2 * dx2 + dy2 * dy2;
        double u2 = ((x3 - x1) * dx2 + (y3 - y1) * dy2) / d2;
        double x4 = x1 + u2 * dx2;
        double y4 = y1 + u2 * dy2;
        dx2 = x4 - x3;
        dy2 = y4 - y3;
        return dx2 * dx2 + dy2 * dy2;
    }

    public static CvBox2D boundedRect(CvMat contour, CvBox2D box) {
        int contourLength = contour.length();
        CvMoments m2 = moments.get();
        opencv_imgproc.cvMoments((CvArr)contour, (CvMoments)m2, (int)0);
        double inv_m00 = 1.0 / m2.m00();
        double centerX = m2.m10() * inv_m00;
        double centerY = m2.m01() * inv_m00;
        float[] pts = new float[8];
        CvPoint2D32f center = box.center();
        CvSize2D32f size = box.size();
        center.put(centerX, centerY);
        opencv_imgproc.cvBoxPoints((CvBox2D)box, (float[])pts);
        float scale = Float.POSITIVE_INFINITY;
        for (int i2 = 0; i2 < 4; ++i2) {
            double x1 = centerX;
            double y1 = centerY;
            double x2 = pts[2 * i2];
            double y2 = pts[2 * i2 + 1];
            for (int j2 = 0; j2 < contourLength; ++j2) {
                int k2 = (j2 + 1) % contourLength;
                double x3 = contour.get(2 * j2);
                double y3 = contour.get(2 * j2 + 1);
                double x4 = contour.get(2 * k2);
                double y4 = contour.get(2 * k2 + 1);
                double d2 = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
                double ua2 = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / d2;
                double ub2 = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / d2;
                if (!(ub2 >= 0.0) || !(ub2 <= 1.0) || !(ua2 >= 0.0) || !(ua2 < (double)scale)) continue;
                scale = (float)ua2;
            }
        }
        size.width(scale * size.width()).height(scale * size.height());
        return box;
    }

    public static CvRect boundingRect(double[] contour, CvRect rect, int padX, int padY, int alignX, int alignY) {
        double minX = contour[0];
        double minY = contour[1];
        double maxX = contour[0];
        double maxY = contour[1];
        for (int i2 = 1; i2 < contour.length / 2; ++i2) {
            double x2 = contour[2 * i2];
            double y2 = contour[2 * i2 + 1];
            minX = Math.min(minX, x2);
            minY = Math.min(minY, y2);
            maxX = Math.max(maxX, x2);
            maxY = Math.max(maxY, y2);
        }
        int x3 = (int)Math.floor(Math.max((double)rect.x(), minX - (double)padX) / (double)alignX) * alignX;
        int y3 = (int)Math.floor(Math.max((double)rect.y(), minY - (double)padY) / (double)alignY) * alignY;
        int width = (int)Math.ceil(Math.min((double)rect.width(), maxX + (double)padX) / (double)alignX) * alignX - x3;
        int height = (int)Math.ceil(Math.min((double)rect.height(), maxY + (double)padY) / (double)alignY) * alignY - y3;
        return rect.x(x3).y(y3).width(Math.max(0, width)).height(Math.max(0, height));
    }

    public static CvMat getPerspectiveTransform(double[] src, double[] dst, CvMat map_matrix) {
        CvMat A = A8x8.get();
        CvMat b2 = b8x1.get();
        CvMat x2 = x8x1.get();
        for (int i2 = 0; i2 < 4; ++i2) {
            A.put(i2 * 8 + 0, src[i2 * 2]);
            A.put((i2 + 4) * 8 + 3, src[i2 * 2]);
            A.put(i2 * 8 + 1, src[i2 * 2 + 1]);
            A.put((i2 + 4) * 8 + 4, src[i2 * 2 + 1]);
            A.put(i2 * 8 + 2, 1.0);
            A.put((i2 + 4) * 8 + 5, 1.0);
            A.put(i2 * 8 + 3, 0.0);
            A.put(i2 * 8 + 4, 0.0);
            A.put(i2 * 8 + 5, 0.0);
            A.put((i2 + 4) * 8 + 0, 0.0);
            A.put((i2 + 4) * 8 + 1, 0.0);
            A.put((i2 + 4) * 8 + 2, 0.0);
            A.put(i2 * 8 + 6, -src[i2 * 2] * dst[i2 * 2]);
            A.put(i2 * 8 + 7, -src[i2 * 2 + 1] * dst[i2 * 2]);
            A.put((i2 + 4) * 8 + 6, -src[i2 * 2] * dst[i2 * 2 + 1]);
            A.put((i2 + 4) * 8 + 7, -src[i2 * 2 + 1] * dst[i2 * 2 + 1]);
            b2.put(i2, dst[i2 * 2]);
            b2.put(i2 + 4, dst[i2 * 2 + 1]);
        }
        opencv_core.cvSolve((CvArr)A, (CvArr)b2, (CvArr)x2, (int)0);
        map_matrix.put(x2.get());
        map_matrix.put(8, 1.0);
        return map_matrix;
    }

    public static void perspectiveTransform(double[] src, double[] dst, CvMat map_matrix) {
        double[] mat = map_matrix.get();
        for (int j2 = 0; j2 < src.length; j2 += 2) {
            double x2 = src[j2];
            double y2 = src[j2 + 1];
            double w2 = x2 * mat[6] + y2 * mat[7] + mat[8];
            if (Math.abs(w2) > 1.1920928955078125E-7) {
                w2 = 1.0 / w2;
                dst[j2] = (x2 * mat[0] + y2 * mat[1] + mat[2]) * w2;
                dst[j2 + 1] = (x2 * mat[3] + y2 * mat[4] + mat[5]) * w2;
                continue;
            }
            dst[j2 + 1] = 0.0;
            dst[j2] = 0.0;
        }
    }

    public static CvMat getPlaneParameters(double[] src, double[] dst, CvMat invSrcK, CvMat dstK, CvMat R, CvMat t2, CvMat n2) {
        CvMat A = A3x3.get();
        CvMat b2 = b3x1.get();
        double[] x2 = new double[6];
        double[] y2 = new double[6];
        JavaCV.perspectiveTransform(src, x2, invSrcK);
        opencv_core.cvInvert((CvArr)dstK, (CvArr)A);
        JavaCV.perspectiveTransform(dst, y2, A);
        for (int i2 = 0; i2 < 3; ++i2) {
            A.put(i2, 0, (t2.get(2) * y2[i2 * 2] - t2.get(0)) * x2[i2 * 2]);
            A.put(i2, 1, (t2.get(2) * y2[i2 * 2] - t2.get(0)) * x2[i2 * 2 + 1]);
            A.put(i2, 2, t2.get(2) * y2[i2 * 2] - t2.get(0));
            b2.put(i2, (R.get(2, 0) * x2[i2 * 2] + R.get(2, 1) * x2[i2 * 2 + 1] + R.get(2, 2)) * y2[i2 * 2] - (R.get(0, 0) * x2[i2 * 2] + R.get(0, 1) * x2[i2 * 2 + 1] + R.get(0, 2)));
        }
        opencv_core.cvSolve((CvArr)A, (CvArr)b2, (CvArr)n2, (int)0);
        return n2;
    }

    public static CvMat getPerspectiveTransform(double[] src, double[] dst, CvMat invSrcK, CvMat dstK, CvMat R, CvMat t2, CvMat H) {
        CvMat n2 = n3x1.get();
        JavaCV.getPlaneParameters(src, dst, invSrcK, dstK, R, t2, n2);
        opencv_core.cvGEMM((CvArr)t2, (CvArr)n2, (double)-1.0, (CvArr)R, (double)1.0, (CvArr)H, (int)2);
        opencv_core.cvMatMul((CvArr)dstK, (CvArr)H, (CvArr)H);
        opencv_core.cvMatMul((CvArr)H, (CvArr)invSrcK, (CvArr)H);
        return H;
    }

    public static void perspectiveTransform(double[] src, double[] dst, CvMat invSrcK, CvMat dstK, CvMat R, CvMat t2, CvMat n2, boolean invert) {
        CvMat H = H3x3.get();
        opencv_core.cvGEMM((CvArr)t2, (CvArr)n2, (double)-1.0, (CvArr)R, (double)1.0, (CvArr)H, (int)2);
        opencv_core.cvMatMul((CvArr)dstK, (CvArr)H, (CvArr)H);
        opencv_core.cvMatMul((CvArr)H, (CvArr)invSrcK, (CvArr)H);
        if (invert) {
            opencv_core.cvInvert((CvArr)H, (CvArr)H);
        }
        JavaCV.perspectiveTransform(src, dst, H);
    }

    public static void HtoRt(CvMat H, CvMat R, CvMat t2) {
        CvMat M = M3x2.get();
        CvMat S = S2x2.get();
        CvMat U = U3x2.get();
        CvMat V = V2x2.get();
        M.put(new double[]{H.get(0), H.get(1), H.get(3), H.get(4), H.get(6), H.get(7)});
        opencv_core.cvSVD((CvArr)M, (CvArr)S, (CvArr)U, (CvArr)V, (int)4);
        double lambda = S.get(3);
        t2.put(new double[]{H.get(2) / lambda, H.get(5) / lambda, H.get(8) / lambda});
        opencv_core.cvMatMul((CvArr)U, (CvArr)V, (CvArr)M);
        R.put(new double[]{M.get(0), M.get(1), M.get(2) * M.get(5) - M.get(3) * M.get(4), M.get(2), M.get(3), M.get(1) * M.get(4) - M.get(0) * M.get(5), M.get(4), M.get(5), M.get(0) * M.get(3) - M.get(1) * M.get(2)});
    }

    public static double HnToRt(CvMat H, CvMat n2, CvMat R, CvMat t2) {
        double err;
        CvMat S = S3x3.get();
        CvMat U = U3x3.get();
        CvMat V = V3x3.get();
        opencv_core.cvSVD((CvArr)H, (CvArr)S, (CvArr)U, (CvArr)V, (int)0);
        CvMat R1 = R13x3.get();
        CvMat R2 = R23x3.get();
        CvMat t1 = t13x1.get();
        CvMat t22 = t23x1.get();
        CvMat n1 = n13x1.get();
        CvMat n22 = n23x1.get();
        CvMat H1 = H13x3.get();
        CvMat H2 = H23x3.get();
        double zeta = JavaCV.homogToRt(S, U, V, R1, t1, n1, R2, t22, n22);
        opencv_core.cvGEMM((CvArr)R1, (CvArr)H, (double)(1.0 / S.get(4)), null, (double)0.0, (CvArr)H1, (int)1);
        opencv_core.cvGEMM((CvArr)R2, (CvArr)H, (double)(1.0 / S.get(4)), null, (double)0.0, (CvArr)H2, (int)1);
        H1.put(0, H1.get(0) - 1.0);
        H1.put(4, H1.get(4) - 1.0);
        H1.put(8, H1.get(8) - 1.0);
        H2.put(0, H2.get(0) - 1.0);
        H2.put(4, H2.get(4) - 1.0);
        H2.put(8, H2.get(8) - 1.0);
        double d2 = Math.abs(n2.get(0)) + Math.abs(n2.get(1)) + Math.abs(n2.get(2));
        double[] s2 = new double[]{-Math.signum(n2.get(0)), -Math.signum(n2.get(1)), -Math.signum(n2.get(2))};
        t1.put(new double[]{0.0, 0.0, 0.0});
        t22.put(new double[]{0.0, 0.0, 0.0});
        for (int i2 = 0; i2 < 3; ++i2) {
            t1.put(0, t1.get(0) + s2[i2] * H1.get(i2) / d2);
            t1.put(1, t1.get(1) + s2[i2] * H1.get(i2 + 3) / d2);
            t1.put(2, t1.get(2) + s2[i2] * H1.get(i2 + 6) / d2);
            t22.put(0, t22.get(0) + s2[i2] * H2.get(i2) / d2);
            t22.put(1, t22.get(1) + s2[i2] * H2.get(i2 + 3) / d2);
            t22.put(2, t22.get(2) + s2[i2] * H2.get(i2 + 6) / d2);
        }
        opencv_core.cvGEMM((CvArr)t1, (CvArr)n2, (double)1.0, (CvArr)H1, (double)1.0, (CvArr)H1, (int)2);
        opencv_core.cvGEMM((CvArr)t22, (CvArr)n2, (double)1.0, (CvArr)H2, (double)1.0, (CvArr)H2, (int)2);
        double err1 = opencv_core.cvNorm((CvArr)H1);
        double err2 = opencv_core.cvNorm((CvArr)H2);
        if (err1 < err2) {
            if (R != null) {
                R.put(R1);
            }
            if (t2 != null) {
                t2.put(t1);
            }
            err = err1;
        } else {
            if (R != null) {
                R.put(R2);
            }
            if (t2 != null) {
                t2.put(t22);
            }
            err = err2;
        }
        return err;
    }

    public static double homogToRt(CvMat H, CvMat R1, CvMat t1, CvMat n1, CvMat R2, CvMat t2, CvMat n2) {
        CvMat S = S3x3.get();
        CvMat U = U3x3.get();
        CvMat V = V3x3.get();
        opencv_core.cvSVD((CvArr)H, (CvArr)S, (CvArr)U, (CvArr)V, (int)0);
        double zeta = JavaCV.homogToRt(S, U, V, R1, t1, n1, R2, t2, n2);
        return zeta;
    }

    public static double homogToRt(CvMat S, CvMat U, CvMat V, CvMat R1, CvMat t1, CvMat n1, CvMat R2, CvMat t2, CvMat n2) {
        double s1 = S.get(0) / S.get(4);
        double s3 = S.get(8) / S.get(4);
        double zeta = s1 - s3;
        double a1 = Math.sqrt(1.0 - s3 * s3);
        double b1 = Math.sqrt(s1 * s1 - 1.0);
        double[] ab2 = JavaCV.unitize(a1, b1);
        double[] cd2 = JavaCV.unitize(1.0 + s1 * s3, a1 * b1);
        double[] ef2 = JavaCV.unitize(-ab2[1] / s1, -ab2[0] / s3);
        R1.put(new double[]{cd2[0], 0.0, cd2[1], 0.0, 1.0, 0.0, -cd2[1], 0.0, cd2[0]});
        opencv_core.cvGEMM((CvArr)U, (CvArr)R1, (double)1.0, null, (double)0.0, (CvArr)R1, (int)0);
        opencv_core.cvGEMM((CvArr)R1, (CvArr)V, (double)1.0, null, (double)0.0, (CvArr)R1, (int)2);
        R2.put(new double[]{cd2[0], 0.0, -cd2[1], 0.0, 1.0, 0.0, cd2[1], 0.0, cd2[0]});
        opencv_core.cvGEMM((CvArr)U, (CvArr)R2, (double)1.0, null, (double)0.0, (CvArr)R2, (int)0);
        opencv_core.cvGEMM((CvArr)R2, (CvArr)V, (double)1.0, null, (double)0.0, (CvArr)R2, (int)2);
        double[] v1 = new double[]{V.get(0), V.get(3), V.get(6)};
        double[] v3 = new double[]{V.get(2), V.get(5), V.get(8)};
        double sign1 = 1.0;
        double sign2 = 1.0;
        for (int i2 = 2; i2 >= 0; --i2) {
            n1.put(i2, sign1 * (ab2[1] * v1[i2] - ab2[0] * v3[i2]));
            n2.put(i2, sign2 * (ab2[1] * v1[i2] + ab2[0] * v3[i2]));
            t1.put(i2, sign1 * (ef2[0] * v1[i2] + ef2[1] * v3[i2]));
            t2.put(i2, sign2 * (ef2[0] * v1[i2] - ef2[1] * v3[i2]));
            if (i2 != 2) continue;
            if (n1.get(2) < 0.0) {
                n1.put(2, -n1.get(2));
                t1.put(2, -t1.get(2));
                sign1 = -1.0;
            }
            if (!(n2.get(2) < 0.0)) continue;
            n2.put(2, -n2.get(2));
            t2.put(2, -t2.get(2));
            sign2 = -1.0;
        }
        return zeta;
    }

    public static double[] unitize(double a2, double b2) {
        double norm = Math.sqrt(a2 * a2 + b2 * b2);
        if (norm > 1.1920928955078125E-7) {
            a2 /= norm;
            b2 /= norm;
        }
        return new double[]{a2, b2};
    }

    public static void adaptiveThreshold(IplImage srcImage, IplImage sumImage, IplImage sqSumImage, IplImage dstImage, final boolean invert, final int windowMax, final int windowMin, double varMultiplier, final double k2) {
        final int w2 = srcImage.width();
        final int h2 = srcImage.height();
        int srcChannels = srcImage.nChannels();
        final int srcDepth = srcImage.depth();
        int dstDepth = dstImage.depth();
        if (srcChannels > 1 && dstDepth == 8) {
            opencv_imgproc.cvCvtColor((CvArr)srcImage, (CvArr)dstImage, (int)(srcChannels == 4 ? 11 : 6));
            srcImage = dstImage;
        }
        final ByteBuffer srcBuf = srcImage.getByteBuffer();
        final ByteBuffer dstBuf = dstImage.getByteBuffer();
        final DoubleBuffer sumBuf = sumImage.getDoubleBuffer();
        final DoubleBuffer sqSumBuf = sqSumImage.getDoubleBuffer();
        final int srcStep = srcImage.widthStep();
        final int dstStep = dstImage.widthStep();
        final int sumStep = sumImage.widthStep();
        final int sqSumStep = sqSumImage.widthStep();
        opencv_imgproc.cvIntegral((CvArr)srcImage, (CvArr)sumImage, (CvArr)sqSumImage, null);
        double totalMean = sumBuf.get((h2 - 1) * sumStep / 8 + (w2 - 1)) - sumBuf.get((h2 - 1) * sumStep / 8) - sumBuf.get(w2 - 1) + sumBuf.get(0);
        double totalSqMean = sqSumBuf.get((h2 - 1) * sqSumStep / 8 + (w2 - 1)) - sqSumBuf.get((h2 - 1) * sqSumStep / 8) - sqSumBuf.get(w2 - 1) + sqSumBuf.get(0);
        double totalVar = (totalSqMean /= (double)(w2 * h2)) - (totalMean /= (double)(w2 * h2)) * totalMean;
        final double targetVar = totalVar * varMultiplier;
        Parallel.loop(0, h2, new Parallel.Looper(){

            @Override
            public void loop(int from, int to2, int looperID) {
                for (int y2 = from; y2 < to2; ++y2) {
                    for (int x2 = 0; x2 < w2; ++x2) {
                        double threshold;
                        double var = 0.0;
                        double mean = 0.0;
                        double sqMean = 0.0;
                        int upperLimit = windowMax;
                        int lowerLimit = windowMin;
                        int window = upperLimit;
                        while (upperLimit - lowerLimit > 2) {
                            int x1 = Math.max(x2 - window / 2, 0);
                            int x22 = Math.min(x2 + window / 2 + 1, w2);
                            int y1 = Math.max(y2 - window / 2, 0);
                            int y22 = Math.min(y2 + window / 2 + 1, h2);
                            mean = sumBuf.get(y22 * sumStep / 8 + x22) - sumBuf.get(y22 * sumStep / 8 + x1) - sumBuf.get(y1 * sumStep / 8 + x22) + sumBuf.get(y1 * sumStep / 8 + x1);
                            sqMean = sqSumBuf.get(y22 * sqSumStep / 8 + x22) - sqSumBuf.get(y22 * sqSumStep / 8 + x1) - sqSumBuf.get(y1 * sqSumStep / 8 + x22) + sqSumBuf.get(y1 * sqSumStep / 8 + x1);
                            var = (sqMean /= (double)(window * window)) - (mean /= (double)(window * window)) * mean;
                            if (window == upperLimit && var < targetVar) break;
                            if (var > targetVar) {
                                upperLimit = window;
                            } else {
                                lowerLimit = window;
                            }
                            window = lowerLimit + (upperLimit - lowerLimit) / 2;
                            window = window / 2 * 2 + 1;
                        }
                        double value = 0.0;
                        if (srcDepth == 8) {
                            value = srcBuf.get(y2 * srcStep + x2) & 0xFF;
                        } else if (srcDepth == 32) {
                            value = srcBuf.getFloat(y2 * srcStep + 4 * x2);
                        } else if (srcDepth == 64) {
                            value = srcBuf.getDouble(y2 * srcStep + 8 * x2);
                        } else assert (false);
                        if (invert) {
                            threshold = 255.0 - (255.0 - mean) * k2;
                            dstBuf.put(y2 * dstStep + x2, value < threshold ? (byte)-1 : 0);
                            continue;
                        }
                        threshold = mean * k2;
                        dstBuf.put(y2 * dstStep + x2, value > threshold ? (byte)-1 : 0);
                    }
                }
            }
        });
    }

    public static void hysteresisThreshold(IplImage srcImage, IplImage dstImage, double highThresh, double lowThresh, double maxValue) {
        byte prev;
        int highThreshold = (int)Math.round(highThresh);
        int lowThreshold = (int)Math.round(lowThresh);
        byte lowValue = 0;
        byte medValue = (byte)Math.round(maxValue / 2.0);
        byte highValue = (byte)Math.round(maxValue);
        int height = srcImage.height();
        int width = srcImage.width();
        ByteBuffer srcData = srcImage.getByteBuffer();
        ByteBuffer dstData = dstImage.getByteBuffer();
        int srcStep = srcImage.widthStep();
        int dstStep = dstImage.widthStep();
        int srcIndex = 0;
        int dstIndex = 0;
        int i2 = 0;
        int in2 = srcData.get(srcIndex + i2) & 0xFF;
        if (in2 >= highThreshold) {
            dstData.put(dstIndex + i2, highValue);
        } else if (in2 < lowThreshold) {
            dstData.put(dstIndex + i2, lowValue);
        } else {
            dstData.put(dstIndex + i2, medValue);
        }
        for (i2 = 1; i2 < width - 1; ++i2) {
            in2 = srcData.get(srcIndex + i2) & 0xFF;
            if (in2 >= highThreshold) {
                dstData.put(dstIndex + i2, highValue);
                continue;
            }
            if (in2 < lowThreshold) {
                dstData.put(dstIndex + i2, lowValue);
                continue;
            }
            prev = dstData.get(dstIndex + i2 - 1);
            if (prev == highValue) {
                dstData.put(dstIndex + i2, highValue);
                continue;
            }
            dstData.put(dstIndex + i2, medValue);
        }
        i2 = width - 1;
        in2 = srcData.get(srcIndex + i2) & 0xFF;
        if (in2 >= highThreshold) {
            dstData.put(dstIndex + i2, highValue);
        } else if (in2 < lowThreshold) {
            dstData.put(dstIndex + i2, lowValue);
        } else {
            prev = dstData.get(dstIndex + i2 - 1);
            if (prev == highValue) {
                dstData.put(dstIndex + i2, highValue);
            } else {
                dstData.put(dstIndex + i2, medValue);
            }
        }
        while (true) {
            byte prev3;
            byte prev2;
            byte prev1;
            int n2 = --height;
            --height;
            if (n2 <= 0) break;
            dstIndex += dstStep;
            i2 = 0;
            in2 = srcData.get((srcIndex += srcStep) + i2) & 0xFF;
            if (in2 >= highThreshold) {
                dstData.put(dstIndex + i2, highValue);
            } else if (in2 < lowThreshold) {
                dstData.put(dstIndex + i2, lowValue);
            } else {
                prev1 = dstData.get(dstIndex + i2 - dstStep);
                prev2 = dstData.get(dstIndex + i2 - dstStep + 1);
                if (prev1 == highValue || prev2 == highValue) {
                    dstData.put(dstIndex + i2, highValue);
                } else {
                    dstData.put(dstIndex + i2, medValue);
                }
            }
            for (i2 = 1; i2 < width - 1; ++i2) {
                in2 = srcData.get(srcIndex + i2) & 0xFF;
                if (in2 >= highThreshold) {
                    dstData.put(dstIndex + i2, highValue);
                    continue;
                }
                if (in2 < lowThreshold) {
                    dstData.put(dstIndex + i2, lowValue);
                    continue;
                }
                prev1 = dstData.get(dstIndex + i2 - 1);
                prev2 = dstData.get(dstIndex + i2 - dstStep - 1);
                prev3 = dstData.get(dstIndex + i2 - dstStep);
                byte prev4 = dstData.get(dstIndex + i2 - dstStep + 1);
                if (prev1 == highValue || prev2 == highValue || prev3 == highValue || prev4 == highValue) {
                    dstData.put(dstIndex + i2, highValue);
                    continue;
                }
                dstData.put(dstIndex + i2, medValue);
            }
            i2 = width - 1;
            in2 = srcData.get(srcIndex + i2) & 0xFF;
            if (in2 >= highThreshold) {
                dstData.put(dstIndex + i2, highValue);
                continue;
            }
            if (in2 < lowThreshold) {
                dstData.put(dstIndex + i2, lowValue);
                continue;
            }
            prev1 = dstData.get(dstIndex + i2 - 1);
            prev2 = dstData.get(dstIndex + i2 - dstStep - 1);
            prev3 = dstData.get(dstIndex + i2 - dstStep);
            if (prev1 == highValue || prev2 == highValue || prev3 == highValue) {
                dstData.put(dstIndex + i2, highValue);
                continue;
            }
            dstData.put(dstIndex + i2, medValue);
        }
        height = srcImage.height();
        dstIndex = (height - 1) * dstStep;
        if (dstData.get(dstIndex + (i2 = (width = srcImage.width()) - 1)) == medValue) {
            dstData.put(dstIndex + i2, lowValue);
        }
        for (i2 = width - 2; i2 > 0; --i2) {
            if (dstData.get(dstIndex + i2) != medValue) continue;
            if (dstData.get(dstIndex + i2 + 1) == highValue) {
                dstData.put(dstIndex + i2, highValue);
                continue;
            }
            dstData.put(dstIndex + i2, lowValue);
        }
        i2 = 0;
        if (dstData.get(dstIndex + i2) == medValue) {
            if (dstData.get(dstIndex + i2 + 1) == highValue) {
                dstData.put(dstIndex + i2, highValue);
            } else {
                dstData.put(dstIndex + i2, lowValue);
            }
        }
        while (true) {
            int n3 = --height;
            --height;
            if (n3 <= 0) break;
            i2 = width - 1;
            if (dstData.get((dstIndex -= dstStep) + i2) == medValue) {
                if (dstData.get(dstIndex + i2 + dstStep) == highValue || dstData.get(dstIndex + i2 + dstStep - 1) == highValue) {
                    dstData.put(dstIndex + i2, highValue);
                } else {
                    dstData.put(dstIndex + i2, lowValue);
                }
            }
            for (i2 = width - 2; i2 > 0; --i2) {
                if (dstData.get(dstIndex + i2) != medValue) continue;
                if (dstData.get(dstIndex + i2 + 1) == highValue || dstData.get(dstIndex + i2 + dstStep + 1) == highValue || dstData.get(dstIndex + i2 + dstStep) == highValue || dstData.get(dstIndex + i2 + dstStep - 1) == highValue) {
                    dstData.put(dstIndex + i2, highValue);
                    continue;
                }
                dstData.put(dstIndex + i2, lowValue);
            }
            i2 = 0;
            if (dstData.get(dstIndex + i2) != medValue) continue;
            if (dstData.get(dstIndex + i2 + 1) == highValue || dstData.get(dstIndex + i2 + dstStep + 1) == highValue || dstData.get(dstIndex + i2 + dstStep) == highValue) {
                dstData.put(dstIndex + i2, highValue);
                continue;
            }
            dstData.put(dstIndex + i2, lowValue);
        }
    }

    public static void clamp(IplImage src, IplImage dst, double min, double max) {
        switch (src.depth()) {
            case 8: {
                ByteBuffer sb2 = src.getByteBuffer();
                ByteBuffer db2 = dst.getByteBuffer();
                for (int i2 = 0; i2 < sb2.capacity(); ++i2) {
                    db2.put(i2, (byte)Math.max(Math.min((double)(sb2.get(i2) & 0xFF), max), min));
                }
                break;
            }
            case 16: {
                ShortBuffer sb3 = src.getShortBuffer();
                ShortBuffer db3 = dst.getShortBuffer();
                for (int i3 = 0; i3 < sb3.capacity(); ++i3) {
                    db3.put(i3, (short)Math.max(Math.min((double)(sb3.get(i3) & 0xFFFF), max), min));
                }
                break;
            }
            case 32: {
                FloatBuffer sb4 = src.getFloatBuffer();
                FloatBuffer db4 = dst.getFloatBuffer();
                for (int i4 = 0; i4 < sb4.capacity(); ++i4) {
                    db4.put(i4, (float)Math.max(Math.min((double)sb4.get(i4), max), min));
                }
                break;
            }
            case -2147483640: {
                ByteBuffer sb5 = src.getByteBuffer();
                ByteBuffer db5 = dst.getByteBuffer();
                for (int i5 = 0; i5 < sb5.capacity(); ++i5) {
                    db5.put(i5, (byte)Math.max(Math.min((double)sb5.get(i5), max), min));
                }
                break;
            }
            case -2147483632: {
                ShortBuffer sb6 = src.getShortBuffer();
                ShortBuffer db6 = dst.getShortBuffer();
                for (int i6 = 0; i6 < sb6.capacity(); ++i6) {
                    db6.put(i6, (short)Math.max(Math.min((double)sb6.get(i6), max), min));
                }
                break;
            }
            case -2147483616: {
                IntBuffer sb7 = src.getIntBuffer();
                IntBuffer db7 = dst.getIntBuffer();
                for (int i7 = 0; i7 < sb7.capacity(); ++i7) {
                    db7.put(i7, (int)Math.max(Math.min((double)sb7.get(i7), max), min));
                }
                break;
            }
            case 64: {
                DoubleBuffer sb8 = src.getDoubleBuffer();
                DoubleBuffer db8 = dst.getDoubleBuffer();
                for (int i8 = 0; i8 < sb8.capacity(); ++i8) {
                    db8.put(i8, Math.max(Math.min(sb8.get(i8), max), min));
                }
                break;
            }
            default: {
                assert (false);
                break;
            }
        }
    }

    public static double norm(double[] v2) {
        return JavaCV.norm(v2, 2.0);
    }

    public static double norm(double[] v2, double p2) {
        double norm = 0.0;
        if (p2 == 1.0) {
            for (double e2 : v2) {
                norm += Math.abs(e2);
            }
        } else if (p2 == 2.0) {
            for (double e3 : v2) {
                norm += e3 * e3;
            }
            norm = Math.sqrt(norm);
        } else if (p2 == Double.POSITIVE_INFINITY) {
            for (double e4 : v2) {
                if (!((e4 = Math.abs(e4)) > norm)) continue;
                norm = e4;
            }
        } else if (p2 == Double.NEGATIVE_INFINITY) {
            norm = Double.MAX_VALUE;
            for (double e5 : v2) {
                if (!((e5 = Math.abs(e5)) < norm)) continue;
                norm = e5;
            }
        } else {
            for (double e6 : v2) {
                norm += Math.pow(Math.abs(e6), p2);
            }
            norm = Math.pow(norm, 1.0 / p2);
        }
        return norm;
    }

    public static double norm(CvMat A) {
        return JavaCV.norm(A, 2.0);
    }

    public static double norm(CvMat A, double p2) {
        return JavaCV.norm(A, p2, null);
    }

    public static double norm(CvMat A, double p2, CvMat W) {
        double norm = -1.0;
        if (p2 == 1.0) {
            int cols = A.cols();
            int rows = A.rows();
            for (int j2 = 0; j2 < cols; ++j2) {
                double n2 = 0.0;
                for (int i2 = 0; i2 < rows; ++i2) {
                    n2 += Math.abs(A.get(i2, j2));
                }
                norm = Math.max(n2, norm);
            }
        } else if (p2 == 2.0) {
            int size = Math.min(A.rows(), A.cols());
            if (W == null || W.rows() != size || W.cols() != 1) {
                W = CvMat.create((int)size, (int)1);
            }
            opencv_core.cvSVD((CvArr)A, (CvArr)W, null, null, (int)0);
            norm = W.get(0);
        } else if (p2 == Double.POSITIVE_INFINITY) {
            int rows = A.rows();
            int cols = A.cols();
            for (int i3 = 0; i3 < rows; ++i3) {
                double n3 = 0.0;
                for (int j3 = 0; j3 < cols; ++j3) {
                    n3 += Math.abs(A.get(i3, j3));
                }
                norm = Math.max(n3, norm);
            }
        } else assert (false);
        return norm;
    }

    public static double cond(CvMat A) {
        return JavaCV.cond(A, 2.0);
    }

    public static double cond(CvMat A, double p2) {
        return JavaCV.cond(A, p2, null);
    }

    public static double cond(CvMat A, double p2, CvMat W) {
        double cond = -1.0;
        if (p2 == 2.0) {
            int size = Math.min(A.rows(), A.cols());
            if (W == null || W.rows() != size || W.cols() != 1) {
                W = CvMat.create((int)size, (int)1);
            }
            opencv_core.cvSVD((CvArr)A, (CvArr)W, null, null, (int)0);
            cond = W.get(0) / W.get(W.length() - 1);
        } else {
            int rows = A.rows();
            int cols = A.cols();
            if (W == null || W.rows() != rows || W.cols() != cols) {
                W = CvMat.create((int)rows, (int)cols);
            }
            CvMat Ainv = W;
            opencv_core.cvInvert((CvArr)A, (CvArr)Ainv);
            cond = JavaCV.norm(A, p2) * JavaCV.norm(Ainv, p2);
        }
        return cond;
    }

    public static double median(double[] doubles) {
        double[] sorted = (double[])doubles.clone();
        Arrays.sort(sorted);
        if (doubles.length % 2 == 0) {
            return (sorted[doubles.length / 2 - 1] + sorted[doubles.length / 2]) / 2.0;
        }
        return sorted[doubles.length / 2];
    }

    public static <T> T median(T[] objects) {
        Object[] sorted = (Object[])objects.clone();
        Arrays.sort(sorted);
        return (T)sorted[sorted.length / 2];
    }

    public static void fractalTriangleWave(double[] line, int i2, int j2, double a2) {
        JavaCV.fractalTriangleWave(line, i2, j2, a2, -1);
    }

    public static void fractalTriangleWave(double[] line, int i2, int j2, double a2, int roughness) {
        int m2 = (j2 - i2) / 2 + i2;
        if (i2 == j2 || i2 == m2) {
            return;
        }
        line[m2] = (line[i2] + line[j2]) / 2.0 + a2;
        if (roughness > 0 && line.length > roughness * (j2 - i2)) {
            JavaCV.fractalTriangleWave(line, i2, m2, 0.0, roughness);
            JavaCV.fractalTriangleWave(line, m2, j2, 0.0, roughness);
        } else {
            JavaCV.fractalTriangleWave(line, i2, m2, a2 / 1.4142135623730951, roughness);
            JavaCV.fractalTriangleWave(line, m2, j2, -a2 / 1.4142135623730951, roughness);
        }
    }

    public static void fractalTriangleWave(IplImage image, CvMat H) {
        JavaCV.fractalTriangleWave(image, H, -1);
    }

    public static void fractalTriangleWave(IplImage image, CvMat H, int roughness) {
        assert (image.depth() == 32);
        double[] line = new double[image.width()];
        JavaCV.fractalTriangleWave(line, 0, line.length / 2, 1.0, roughness);
        JavaCV.fractalTriangleWave(line, line.length / 2, line.length - 1, -1.0, roughness);
        double[] minMax = new double[]{Double.MAX_VALUE, Double.MIN_VALUE};
        int height = image.height();
        int width = image.width();
        int channels = image.nChannels();
        int step = image.widthStep();
        int start = 0;
        if (image.roi() != null) {
            height = image.roi().height();
            width = image.roi().width();
            start = image.roi().yOffset() * step / 4 + image.roi().xOffset() * channels;
        }
        FloatBuffer fb2 = image.getFloatBuffer(start);
        double[] h2 = H == null ? null : H.get();
        for (int y2 = 0; y2 < height; ++y2) {
            for (int x2 = 0; x2 < width; ++x2) {
                for (int z2 = 0; z2 < channels; ++z2) {
                    double sum = 0.0;
                    if (h2 == null) {
                        sum += line[x2];
                    } else {
                        double x22;
                        for (x22 = (h2[0] * (double)x2 + h2[1] * (double)y2 + h2[2]) / (h2[6] * (double)x2 + h2[7] * (double)y2 + h2[8]); x22 < 0.0; x22 += (double)line.length) {
                        }
                        int xi2 = (int)x22;
                        double xn2 = x22 - (double)xi2;
                        sum += line[xi2 % line.length] * (1.0 - xn2) + line[(xi2 + 1) % line.length] * xn2;
                    }
                    minMax[0] = Math.min(minMax[0], sum);
                    minMax[1] = Math.max(minMax[1], sum);
                    fb2.put(y2 * step / 4 + x2 * channels + z2, (float)sum);
                }
            }
        }
        opencv_core.cvConvertScale((CvArr)image, (CvArr)image, (double)(1.0 / (minMax[1] - minMax[0])), (double)(-minMax[0] / (minMax[1] - minMax[0])));
    }

    public static void main(String[] args) {
        String version = JavaCV.class.getPackage().getImplementationVersion();
        if (version == null) {
            version = "unknown";
        }
        System.out.println("JavaCV version " + version + "\nCopyright (C) 2009-2018 Samuel Audet <samuel.audet@gmail.com>\nProject site: https://github.com/bytedeco/javacv");
        System.exit(0);
    }
}

