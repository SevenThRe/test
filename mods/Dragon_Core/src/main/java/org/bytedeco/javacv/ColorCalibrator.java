/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.CvScalar
 */
package org.bytedeco.javacv;

import java.awt.Color;
import org.bytedeco.javacv.ProjectiveDevice;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvScalar;

public class ColorCalibrator {
    private ProjectiveDevice device;

    public ColorCalibrator(ProjectiveDevice device) {
        this.device = device;
    }

    public double calibrate(Color[] referenceColors, Color[] deviceColors) {
        assert (referenceColors.length == deviceColors.length);
        int[] order = this.device.getRGBColorOrder();
        CvMat A = CvMat.create((int)(referenceColors.length * 3), (int)12);
        CvMat b2 = CvMat.create((int)(referenceColors.length * 3), (int)1);
        CvMat x2 = CvMat.create((int)12, (int)1);
        double gamma = this.device.getSettings().getResponseGamma();
        for (int i2 = 0; i2 < referenceColors.length; ++i2) {
            float[] dc2 = deviceColors[i2].getRGBColorComponents(null);
            float[] rc2 = referenceColors[i2].getRGBColorComponents(null);
            double dc1 = Math.pow(dc2[order[0]], gamma);
            double dc22 = Math.pow(dc2[order[1]], gamma);
            double dc3 = Math.pow(dc2[order[2]], gamma);
            for (int j2 = 0; j2 < 3; ++j2) {
                int k2 = i2 * 36 + j2 * 16;
                A.put(k2, dc1);
                A.put(k2 + 1, dc22);
                A.put(k2 + 2, dc3);
                A.put(k2 + 3, 1.0);
                if (j2 >= 2) continue;
                for (int m2 = 0; m2 < 12; ++m2) {
                    A.put(k2 + 4 + m2, 0.0);
                }
            }
            b2.put(i2 * 3, (double)rc2[order[0]]);
            b2.put(i2 * 3 + 1, (double)rc2[order[1]]);
            b2.put(i2 * 3 + 2, (double)rc2[order[2]]);
        }
        if ((double)opencv_core.cvSolve((CvArr)A, (CvArr)b2, (CvArr)x2, (int)1) != 1.0) {
            System.out.println("Error solving.");
        }
        CvMat b22 = CvMat.create((int)b2.rows(), (int)1);
        opencv_core.cvMatMul((CvArr)A, (CvArr)x2, (CvArr)b22);
        double MSE = opencv_core.cvNorm((CvArr)b2, (CvArr)b22) * opencv_core.cvNorm((CvArr)b2, (CvArr)b22) / (double)b2.rows();
        double RMSE = Math.sqrt(MSE);
        CvScalar mean = new CvScalar();
        CvScalar stddev = new CvScalar();
        opencv_core.cvAvgSdv((CvArr)b2, (CvScalar)mean, (CvScalar)stddev, null);
        double R2 = 1.0 - MSE / (stddev.val(0) * stddev.val(0));
        this.device.colorMixingMatrix = CvMat.create((int)3, (int)3);
        this.device.additiveLight = CvMat.create((int)3, (int)1);
        for (int i3 = 0; i3 < 3; ++i3) {
            double x0 = x2.get(i3 * 4);
            double x1 = x2.get(i3 * 4 + 1);
            double x22 = x2.get(i3 * 4 + 2);
            double x3 = x2.get(i3 * 4 + 3);
            this.device.colorMixingMatrix.put(i3 * 3, x0);
            this.device.colorMixingMatrix.put(i3 * 3 + 1, x1);
            this.device.colorMixingMatrix.put(i3 * 3 + 2, x22);
            this.device.additiveLight.put(i3, x3);
        }
        this.device.colorR2 = R2;
        this.device.avgColorErr = RMSE;
        return this.device.avgColorErr;
    }
}

