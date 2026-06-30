/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_imgproc
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.CvPoint
 *  org.bytedeco.opencv.opencv_core.CvScalar
 *  org.bytedeco.opencv.opencv_core.IplImage
 */
package org.bytedeco.javacv;

import java.awt.Color;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.CameraDevice;
import org.bytedeco.javacv.ColorCalibrator;
import org.bytedeco.javacv.JavaCV;
import org.bytedeco.javacv.MarkedPlane;
import org.bytedeco.javacv.Marker;
import org.bytedeco.javacv.MarkerDetector;
import org.bytedeco.javacv.ProjectorDevice;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

public class ProCamColorCalibrator {
    private Settings settings;
    private MarkerDetector markerDetector = null;
    private MarkedPlane boardPlane = null;
    private CameraDevice camera = null;
    private ProjectorDevice projector = null;
    private Color[] projectorColors = null;
    private Color[] cameraColors = null;
    private int counter = 0;
    private CvMat boardSrcPts;
    private CvMat boardDstPts;
    private CvMat projSrcPts;
    private CvMat projDstPts;
    private CvMat camKinv;
    private IplImage mask;
    private IplImage mask2;
    private IplImage undistImage;
    private static ThreadLocal<CvMat> H3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> R3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> t3x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> n3x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> z3x1 = CvMat.createThreadLocal((int)3, (int)1);

    public ProCamColorCalibrator(Settings settings, MarkerDetector.Settings detectorSettings, MarkedPlane boardPlane, CameraDevice camera, ProjectorDevice projector) {
        this.settings = settings;
        this.markerDetector = new MarkerDetector(detectorSettings);
        this.boardPlane = boardPlane;
        this.camera = camera;
        this.projector = projector;
        Marker[] boardMarkers = boardPlane.getMarkers();
        this.boardSrcPts = CvMat.create((int)(4 + boardMarkers.length * 4), (int)1, (int)6, (int)2);
        this.boardDstPts = CvMat.create((int)(4 + boardMarkers.length * 4), (int)1, (int)6, (int)2);
        this.boardSrcPts.put(new double[]{0.0, 0.0, boardPlane.getWidth(), 0.0, boardPlane.getWidth(), boardPlane.getHeight(), 0.0, boardPlane.getHeight()});
        for (int i2 = 0; i2 < boardMarkers.length; ++i2) {
            this.boardSrcPts.put(8 + i2 * 8, boardMarkers[i2].corners);
        }
        this.projSrcPts = CvMat.create((int)4, (int)1, (int)6, (int)2);
        this.projDstPts = CvMat.create((int)4, (int)1, (int)6, (int)2);
        this.projSrcPts.put(new double[]{0.0, 0.0, projector.imageWidth - 1, 0.0, projector.imageWidth - 1, projector.imageHeight - 1, 0.0, projector.imageHeight - 1});
        this.camKinv = CvMat.create((int)3, (int)3);
        opencv_core.cvInvert((CvArr)camera.cameraMatrix, (CvArr)this.camKinv);
    }

    public int getColorCount() {
        return this.counter;
    }

    public Color[] getProjectorColors() {
        double invgamma = 1.0 / this.projector.getSettings().getResponseGamma();
        int s2 = this.settings.samplesPerChannel;
        if (this.projectorColors == null) {
            this.projectorColors = new Color[s2 * s2 * s2];
            this.cameraColors = new Color[s2 * s2 * s2];
            for (int i2 = 0; i2 < this.projectorColors.length; ++i2) {
                int j2 = i2 / s2;
                int k2 = j2 / s2;
                double r2 = Math.pow((double)(i2 % s2) / (double)(s2 - 1), invgamma);
                double g2 = Math.pow((double)(j2 % s2) / (double)(s2 - 1), invgamma);
                double b2 = Math.pow((double)(k2 % s2) / (double)(s2 - 1), invgamma);
                this.projectorColors[i2] = new Color((float)r2, (float)g2, (float)b2);
            }
        }
        return this.projectorColors;
    }

    public Color getProjectorColor() {
        return this.getProjectorColors()[this.counter];
    }

    public Color[] getCameraColors() {
        return this.cameraColors;
    }

    public Color getCameraColor() {
        return this.getCameraColors()[this.counter];
    }

    public void addCameraColor() {
        ++this.counter;
    }

    public void addCameraColor(Color color) {
        this.cameraColors[this.counter++] = color;
    }

    public IplImage getMaskImage() {
        return this.mask;
    }

    public IplImage getUndistortedCameraImage() {
        return this.undistImage;
    }

    public boolean processCameraImage(IplImage cameraImage) {
        if (this.undistImage == null || this.undistImage.width() != cameraImage.width() || this.undistImage.height() != cameraImage.height() || this.undistImage.depth() != cameraImage.depth()) {
            this.undistImage = cameraImage.clone();
        }
        if (this.mask == null || this.mask2 == null || this.mask.width() != cameraImage.width() || this.mask2.width() != cameraImage.width() || this.mask.height() != cameraImage.height() || this.mask2.height() != cameraImage.width()) {
            this.mask = IplImage.create((int)cameraImage.width(), (int)cameraImage.height(), (int)8, (int)1, (int)cameraImage.origin());
            this.mask2 = IplImage.create((int)cameraImage.width(), (int)cameraImage.height(), (int)8, (int)1, (int)cameraImage.origin());
        }
        CvMat H = H3x3.get();
        CvMat R = R3x3.get();
        CvMat t2 = t3x1.get();
        CvMat n2 = n3x1.get();
        CvMat z2 = z3x1.get();
        z2.put(new double[]{0.0, 0.0, 1.0});
        this.camera.undistort(cameraImage, this.undistImage);
        Marker[] detectedBoardMarkers = this.markerDetector.detect(this.undistImage, false);
        if ((double)detectedBoardMarkers.length >= (double)this.boardPlane.getMarkers().length * this.settings.detectedBoardMin) {
            int j2;
            this.boardPlane.getTotalWarp(detectedBoardMarkers, H);
            opencv_core.cvPerspectiveTransform((CvArr)this.boardSrcPts, (CvArr)this.boardDstPts, (CvMat)H);
            double[] boardPts = this.boardDstPts.get();
            opencv_core.cvMatMul((CvArr)this.camKinv, (CvArr)H, (CvArr)R);
            double error = JavaCV.HnToRt(R, z2, R, t2);
            opencv_core.cvMatMul((CvArr)R, (CvArr)z2, (CvArr)n2);
            double d2 = opencv_core.cvDotProduct((CvArr)t2, (CvArr)z2);
            opencv_core.cvGEMM((CvArr)this.projector.T, (CvArr)n2, (double)(-1.0 / d2), (CvArr)this.projector.R, (double)1.0, (CvArr)H, (int)2);
            opencv_core.cvMatMul((CvArr)this.projector.cameraMatrix, (CvArr)H, (CvArr)H);
            opencv_core.cvMatMul((CvArr)H, (CvArr)this.camKinv, (CvArr)H);
            opencv_core.cvConvertScale((CvArr)H, (CvArr)H, (double)(1.0 / H.get(8)), (double)0.0);
            opencv_core.cvInvert((CvArr)H, (CvArr)H);
            opencv_core.cvConvertScale((CvArr)H, (CvArr)H, (double)(1.0 / H.get(8)), (double)0.0);
            opencv_core.cvPerspectiveTransform((CvArr)this.projSrcPts, (CvArr)this.projDstPts, (CvMat)H);
            double[] projPts = this.projDstPts.get();
            opencv_core.cvSetZero((CvArr)this.mask);
            double cx2 = 0.0;
            double cy2 = 0.0;
            for (j2 = 0; j2 < 4; ++j2) {
                cx2 += boardPts[j2 * 2];
                cy2 += boardPts[j2 * 2 + 1];
            }
            cx2 /= 4.0;
            cy2 /= 4.0;
            for (j2 = 0; j2 < 4; ++j2) {
                int n3 = j2 * 2;
                boardPts[n3] = boardPts[n3] - (boardPts[j2 * 2] - cx2) * this.settings.trimmingFraction;
                int n4 = j2 * 2 + 1;
                boardPts[n4] = boardPts[n4] - (boardPts[j2 * 2 + 1] - cy2) * this.settings.trimmingFraction;
            }
            opencv_imgproc.cvFillConvexPoly((CvArr)this.mask, (CvPoint)new CvPoint(4L).put((byte)16, boardPts, 0, 8), (int)4, (CvScalar)CvScalar.WHITE, (int)8, (int)16);
            for (j2 = 0; j2 < (boardPts.length - 8) / 8; ++j2) {
                opencv_imgproc.cvFillConvexPoly((CvArr)this.mask, (CvPoint)new CvPoint(4L).put((byte)16, boardPts, 8 + j2 * 8, 8), (int)4, (CvScalar)CvScalar.BLACK, (int)8, (int)16);
            }
            opencv_core.cvSetZero((CvArr)this.mask2);
            cx2 = 0.0;
            cy2 = 0.0;
            for (j2 = 0; j2 < 4; ++j2) {
                cx2 += projPts[j2 * 2];
                cy2 += projPts[j2 * 2 + 1];
            }
            cx2 /= 4.0;
            cy2 /= 4.0;
            for (j2 = 0; j2 < 4; ++j2) {
                int n5 = j2 * 2;
                projPts[n5] = projPts[n5] - (projPts[j2 * 2] - cx2) * this.settings.trimmingFraction;
                int n6 = j2 * 2 + 1;
                projPts[n6] = projPts[n6] - (projPts[j2 * 2 + 1] - cy2) * this.settings.trimmingFraction;
            }
            opencv_imgproc.cvFillConvexPoly((CvArr)this.mask2, (CvPoint)new CvPoint(4L).put((byte)16, projPts, 0, 8), (int)4, (CvScalar)CvScalar.WHITE, (int)8, (int)16);
            opencv_core.cvAnd((CvArr)this.mask, (CvArr)this.mask2, (CvArr)this.mask, null);
            opencv_imgproc.cvErode((CvArr)this.mask, (CvArr)this.mask, null, (int)1);
            CvScalar c2 = opencv_core.cvAvg((CvArr)this.undistImage, (CvArr)this.mask);
            int[] o2 = this.camera.getRGBColorOrder();
            double s2 = cameraImage.highValue();
            this.cameraColors[this.counter] = new Color((float)(c2.val(o2[0]) / s2), (float)(c2.val(o2[1]) / s2), (float)(c2.val(o2[2]) / s2));
            return true;
        }
        return false;
    }

    public double calibrate() {
        Color[] cc2 = this.getCameraColors();
        Color[] pc2 = this.getProjectorColors();
        assert (this.counter == pc2.length);
        ColorCalibrator calibrator = new ColorCalibrator(this.projector);
        this.projector.avgColorErr = calibrator.calibrate(cc2, pc2);
        this.camera.colorMixingMatrix = CvMat.create((int)3, (int)3);
        this.camera.additiveLight = CvMat.create((int)3, (int)1);
        opencv_core.cvSetIdentity((CvArr)this.camera.colorMixingMatrix);
        opencv_core.cvSetZero((CvArr)this.camera.additiveLight);
        this.counter = 0;
        return this.projector.avgColorErr;
    }

    public static class Settings
    extends BaseChildSettings {
        int samplesPerChannel = 4;
        double trimmingFraction = 0.01;
        double detectedBoardMin = 0.5;

        public int getSamplesPerChannel() {
            return this.samplesPerChannel;
        }

        public void setSamplesPerChannel(int samplesPerChannel) {
            this.samplesPerChannel = samplesPerChannel;
        }

        public double getDetectedBoardMin() {
            return this.detectedBoardMin;
        }

        public void setDetectedBoardMin(double detectedBoardMin) {
            this.detectedBoardMin = detectedBoardMin;
        }
    }
}

