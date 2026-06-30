/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_calib3d
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_imgproc
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.CvScalar
 *  org.bytedeco.opencv.opencv_core.FileNode
 *  org.bytedeco.opencv.opencv_core.FileStorage
 *  org.bytedeco.opencv.opencv_core.IplImage
 *  org.bytedeco.opencv.opencv_core.Mat
 *  org.bytedeco.opencv.opencv_core.Size
 */
package org.bytedeco.javacv;

import java.io.File;
import java.nio.FloatBuffer;
import java.util.Arrays;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.CameraDevice;
import org.bytedeco.javacv.JavaCV;
import org.bytedeco.javacv.ProjectorDevice;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.FileNode;
import org.bytedeco.opencv.opencv_core.FileStorage;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Size;

public class ProjectiveDevice {
    private Settings settings;
    public int imageWidth = 0;
    public int imageHeight = 0;
    public CvMat cameraMatrix = null;
    public CvMat distortionCoeffs = null;
    public CvMat extrParams = null;
    public CvMat reprojErrs = null;
    public double avgReprojErr;
    public double maxReprojErr;
    public CvMat R = null;
    public CvMat T = null;
    public CvMat E = null;
    public CvMat F = null;
    public double avgEpipolarErr;
    public double maxEpipolarErr;
    public String colorOrder = "BGR";
    public CvMat colorMixingMatrix = null;
    public CvMat additiveLight = null;
    public double avgColorErr;
    public double colorR2 = 1.0;
    private boolean fixedPointMaps = false;
    private int mapsPyramidLevel = 0;
    private IplImage[] undistortMaps1 = new IplImage[]{null};
    private IplImage[] undistortMaps2 = new IplImage[]{null};
    private IplImage[] distortMaps1 = new IplImage[]{null};
    private IplImage[] distortMaps2 = new IplImage[]{null};
    private IplImage tempImage = null;
    private static ThreadLocal<CvMat> temp3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> B4x3 = CvMat.createThreadLocal((int)4, (int)3);
    private static ThreadLocal<CvMat> a4x1 = CvMat.createThreadLocal((int)4, (int)1);
    private static ThreadLocal<CvMat> t3x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> relativeR3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> relativeT3x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> R13x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> P13x4 = CvMat.createThreadLocal((int)3, (int)4);
    private static ThreadLocal<CvMat> R23x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> P23x4 = CvMat.createThreadLocal((int)3, (int)4);

    public ProjectiveDevice(String name) {
        Settings s2 = new Settings();
        s2.name = name;
        this.setSettings(s2);
    }

    public ProjectiveDevice(String name, File file) throws Exception {
        this(name);
        this.readParameters(file);
    }

    public ProjectiveDevice(String name, String filename) throws Exception {
        this(name);
        this.readParameters(filename);
    }

    public ProjectiveDevice(String name, FileStorage fs2) throws Exception {
        this(name);
        this.readParameters(fs2);
    }

    public ProjectiveDevice(Settings settings) throws Exception {
        this.setSettings(settings);
        if (settings instanceof CalibratedSettings) {
            this.readParameters(((CalibratedSettings)settings).parametersFile);
        }
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
    }

    public void rescale(int imageWidth, int imageHeight) {
        if ((imageWidth != this.imageWidth || imageHeight != this.imageHeight) && this.cameraMatrix != null) {
            double sx2 = (double)imageWidth / (double)this.imageWidth;
            double sy2 = (double)imageHeight / (double)this.imageHeight;
            this.cameraMatrix.put(0, sx2 * this.cameraMatrix.get(0));
            this.cameraMatrix.put(1, sx2 * this.cameraMatrix.get(1));
            this.cameraMatrix.put(2, sx2 * this.cameraMatrix.get(2));
            this.cameraMatrix.put(3, sy2 * this.cameraMatrix.get(3));
            this.cameraMatrix.put(4, sy2 * this.cameraMatrix.get(4));
            this.cameraMatrix.put(5, sy2 * this.cameraMatrix.get(5));
            this.imageWidth = imageWidth;
            this.imageHeight = imageHeight;
            int p2 = this.mapsPyramidLevel;
            this.distortMaps2[p2] = null;
            this.distortMaps1[p2] = null;
            this.undistortMaps2[p2] = null;
            this.undistortMaps1[p2] = null;
        }
    }

    public int[] getRGBColorOrder() {
        int[] order = new int[3];
        block5: for (int i2 = 0; i2 < 3; ++i2) {
            switch (Character.toUpperCase(this.colorOrder.charAt(i2))) {
                case 'B': {
                    order[i2] = 2;
                    continue block5;
                }
                case 'G': {
                    order[i2] = 1;
                    continue block5;
                }
                case 'R': {
                    order[i2] = 0;
                    continue block5;
                }
                default: {
                    assert (false);
                    continue block5;
                }
            }
        }
        return order;
    }

    public static double[] undistort(double[] xd2, double[] k2) {
        double k1 = k2[0];
        double k22 = k2[1];
        double k3 = k2.length > 4 ? k2[4] : 0.0;
        double k4 = k2.length > 5 ? k2[5] : 0.0;
        double k5 = k2.length > 6 ? k2[6] : 0.0;
        double k6 = k2.length > 7 ? k2[7] : 0.0;
        double p1 = k2[2];
        double p2 = k2[3];
        double[] xu2 = (double[])xd2.clone();
        for (int i2 = 0; i2 < xd2.length / 2; ++i2) {
            double x2 = xu2[i2 * 2];
            double y2 = xu2[i2 * 2 + 1];
            double xo2 = xd2[i2 * 2];
            double yo2 = xd2[i2 * 2 + 1];
            for (int j2 = 0; j2 < 20; ++j2) {
                double r_2 = x2 * x2 + y2 * y2;
                double k_radial = 1.0 + k1 * r_2 + k22 * r_2 * r_2 + k3 * r_2 * r_2 * r_2;
                double delta_x = 2.0 * p1 * x2 * y2 + p2 * (r_2 + 2.0 * x2 * x2);
                double delta_y = p1 * (r_2 + 2.0 * y2 * y2) + 2.0 * p2 * x2 * y2;
                x2 = (xo2 - delta_x) / k_radial;
                y2 = (yo2 - delta_y) / k_radial;
            }
            xu2[i2 * 2] = x2;
            xu2[i2 * 2 + 1] = y2;
        }
        return xu2;
    }

    public double[] undistort(double ... x2) {
        double[] xn2 = ProjectiveDevice.normalize(x2, this.cameraMatrix);
        double[] xu2 = ProjectiveDevice.undistort(xn2, this.distortionCoeffs.get());
        return ProjectiveDevice.unnormalize(xu2, this.cameraMatrix);
    }

    public static double[] distort(double[] xu2, double[] k2) {
        double k1 = k2[0];
        double k22 = k2[1];
        double k3 = k2.length > 4 ? k2[4] : 0.0;
        double k4 = k2.length > 5 ? k2[5] : 0.0;
        double k5 = k2.length > 6 ? k2[6] : 0.0;
        double k6 = k2.length > 7 ? k2[7] : 0.0;
        double p1 = k2[2];
        double p2 = k2[3];
        double[] xd2 = (double[])xu2.clone();
        for (int i2 = 0; i2 < xu2.length / 2; ++i2) {
            double x2 = xu2[i2 * 2];
            double y2 = xu2[i2 * 2 + 1];
            double r_2 = x2 * x2 + y2 * y2;
            double k_radial = 1.0 + k1 * r_2 + k22 * r_2 * r_2 + k3 * r_2 * r_2 * r_2;
            double delta_x = 2.0 * p1 * x2 * y2 + p2 * (r_2 + 2.0 * x2 * x2);
            double delta_y = p1 * (r_2 + 2.0 * y2 * y2) + 2.0 * p2 * x2 * y2;
            xd2[i2 * 2] = x2 * k_radial + delta_x;
            xd2[i2 * 2 + 1] = y2 * k_radial + delta_y;
        }
        return xd2;
    }

    public double[] distort(double ... x2) {
        double[] xn2 = ProjectiveDevice.normalize(x2, this.cameraMatrix);
        double[] xd2 = ProjectiveDevice.distort(xn2, this.distortionCoeffs.get());
        return ProjectiveDevice.unnormalize(xd2, this.cameraMatrix);
    }

    public static double[] normalize(double[] xu2, CvMat K) {
        double[] xn2 = (double[])xu2.clone();
        double fx2 = K.get(0) / K.get(8);
        double fy2 = K.get(4) / K.get(8);
        double dx2 = K.get(2) / K.get(8);
        double dy2 = K.get(5) / K.get(8);
        double s2 = K.get(1) / K.get(8);
        for (int i2 = 0; i2 < xu2.length / 2; ++i2) {
            xn2[i2 * 2] = (xu2[i2 * 2] - dx2) / fx2 - s2 * (xu2[i2 * 2 + 1] + dy2) / (fx2 * fy2);
            xn2[i2 * 2 + 1] = (xu2[i2 * 2 + 1] - dy2) / fy2;
        }
        return xn2;
    }

    public static double[] unnormalize(double[] xn2, CvMat K) {
        double[] xu2 = (double[])xn2.clone();
        double fx2 = K.get(0) / K.get(8);
        double fy2 = K.get(4) / K.get(8);
        double dx2 = K.get(2) / K.get(8);
        double dy2 = K.get(5) / K.get(8);
        double s2 = K.get(1) / K.get(8);
        for (int i2 = 0; i2 < xn2.length / 2; ++i2) {
            xu2[i2 * 2] = fx2 * xn2[i2 * 2] + dx2 + s2 * xn2[i2 * 2 + 1];
            xu2[i2 * 2 + 1] = fy2 * xn2[i2 * 2 + 1] + dy2;
        }
        return xu2;
    }

    public boolean isFixedPointMaps() {
        return this.fixedPointMaps;
    }

    public void setFixedPointMaps(boolean fixedPointMaps) {
        if (this.fixedPointMaps != fixedPointMaps) {
            this.fixedPointMaps = fixedPointMaps;
            int p2 = this.mapsPyramidLevel;
            this.distortMaps2[p2] = null;
            this.distortMaps1[p2] = null;
            this.undistortMaps2[p2] = null;
            this.undistortMaps1[p2] = null;
        }
    }

    public int getMapsPyramidLevel() {
        return this.mapsPyramidLevel;
    }

    public void setMapsPyramidLevel(int mapsPyramidLevel) {
        if (this.mapsPyramidLevel != mapsPyramidLevel) {
            this.mapsPyramidLevel = mapsPyramidLevel;
            int p2 = mapsPyramidLevel;
            if (p2 >= this.undistortMaps1.length || p2 >= this.undistortMaps2.length || p2 >= this.distortMaps1.length || p2 >= this.distortMaps2.length) {
                this.undistortMaps1 = Arrays.copyOf(this.undistortMaps1, p2 + 1);
                this.undistortMaps2 = Arrays.copyOf(this.undistortMaps2, p2 + 1);
                this.distortMaps1 = Arrays.copyOf(this.distortMaps1, p2 + 1);
                this.distortMaps2 = Arrays.copyOf(this.distortMaps2, p2 + 1);
            }
        }
    }

    private void initUndistortMaps() {
        int p2 = this.mapsPyramidLevel;
        if (this.undistortMaps1[p2] == null || this.undistortMaps2[p2] == null) {
            if (this.fixedPointMaps) {
                this.undistortMaps1[p2] = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)-2147483632, (int)2);
                this.undistortMaps2[p2] = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)16, (int)1);
            } else {
                this.undistortMaps1[p2] = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)32, (int)1);
                this.undistortMaps2[p2] = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)32, (int)1);
            }
            Mat A = opencv_core.cvarrToMat((CvArr)this.cameraMatrix);
            Mat m1 = opencv_core.cvarrToMat((CvArr)this.undistortMaps1[p2]);
            Mat m2 = opencv_core.cvarrToMat((CvArr)this.undistortMaps2[p2]);
            opencv_calib3d.initUndistortRectifyMap((Mat)A, (Mat)opencv_core.cvarrToMat((CvArr)this.distortionCoeffs), (Mat)new Mat(), (Mat)A, (Size)m1.size(), (int)m1.type(), (Mat)m1, (Mat)m2);
            if (this.mapsPyramidLevel > 0) {
                IplImage map1 = this.undistortMaps1[p2];
                IplImage map2 = this.undistortMaps2[p2];
                int w2 = this.imageWidth >> p2;
                int h2 = this.imageHeight >> p2;
                this.undistortMaps1[p2] = IplImage.create((int)w2, (int)h2, (int)map1.depth(), (int)map1.nChannels());
                this.undistortMaps2[p2] = IplImage.create((int)w2, (int)h2, (int)map2.depth(), (int)map2.nChannels());
                opencv_imgproc.cvResize((CvArr)map1, (CvArr)this.undistortMaps1[p2], (int)0);
                opencv_imgproc.cvResize((CvArr)map2, (CvArr)this.undistortMaps2[p2], (int)0);
            }
        }
    }

    public IplImage getUndistortMap1() {
        this.initUndistortMaps();
        return this.undistortMaps1[this.mapsPyramidLevel];
    }

    public IplImage getUndistortMap2() {
        this.initUndistortMaps();
        return this.undistortMaps2[this.mapsPyramidLevel];
    }

    public void undistort(IplImage src, IplImage dst) {
        if (src != null && dst != null) {
            this.initUndistortMaps();
            opencv_imgproc.cvRemap((CvArr)src, (CvArr)dst, (CvArr)this.undistortMaps1[this.mapsPyramidLevel], (CvArr)this.undistortMaps2[this.mapsPyramidLevel], (int)9, (CvScalar)CvScalar.ZERO);
        }
    }

    public IplImage undistort(IplImage image) {
        if (image != null) {
            this.initUndistortMaps();
            this.tempImage = IplImage.createIfNotCompatible((IplImage)this.tempImage, (IplImage)image);
            opencv_core.cvResetImageROI((IplImage)this.tempImage);
            opencv_imgproc.cvRemap((CvArr)image, (CvArr)this.tempImage, (CvArr)this.undistortMaps1[this.mapsPyramidLevel], (CvArr)this.undistortMaps2[this.mapsPyramidLevel], (int)9, (CvScalar)CvScalar.ZERO);
            return this.tempImage;
        }
        return null;
    }

    private void initDistortMaps() {
        int p2 = this.mapsPyramidLevel;
        if (this.distortMaps1[p2] == null || this.distortMaps2[p2] == null) {
            IplImage mapx = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)32, (int)1);
            IplImage mapy = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)32, (int)1);
            FloatBuffer bufx = mapx.getFloatBuffer();
            FloatBuffer bufy = mapy.getFloatBuffer();
            int width = mapx.width();
            int height = mapx.height();
            for (int y2 = 0; y2 < height; ++y2) {
                for (int x2 = 0; x2 < width; ++x2) {
                    double[] distxy = this.undistort(x2, y2);
                    bufx.put((float)distxy[0]);
                    bufy.put((float)distxy[1]);
                }
            }
            if (this.fixedPointMaps) {
                this.distortMaps1[p2] = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)-2147483632, (int)2);
                this.distortMaps2[p2] = IplImage.create((int)this.imageWidth, (int)this.imageHeight, (int)16, (int)1);
                opencv_imgproc.cvConvertMaps((CvArr)mapx, (CvArr)mapy, (CvArr)this.distortMaps1[p2], (CvArr)this.distortMaps2[p2]);
                mapx.release();
                mapy.release();
            } else {
                this.distortMaps1[p2] = mapx;
                this.distortMaps2[p2] = mapy;
            }
            if (this.mapsPyramidLevel > 0) {
                IplImage map1 = this.distortMaps1[p2];
                IplImage map2 = this.distortMaps2[p2];
                int w2 = this.imageWidth >> p2;
                int h2 = this.imageHeight >> p2;
                this.distortMaps1[p2] = IplImage.create((int)w2, (int)h2, (int)map1.depth(), (int)map1.nChannels());
                this.distortMaps2[p2] = IplImage.create((int)w2, (int)h2, (int)map2.depth(), (int)map2.nChannels());
                opencv_imgproc.cvResize((CvArr)map1, (CvArr)this.distortMaps1[p2], (int)0);
                opencv_imgproc.cvResize((CvArr)map2, (CvArr)this.distortMaps2[p2], (int)0);
            }
        }
    }

    public IplImage getDistortMap1() {
        this.initDistortMaps();
        return this.distortMaps1[this.mapsPyramidLevel];
    }

    public IplImage getDistortMap2() {
        this.initDistortMaps();
        return this.distortMaps2[this.mapsPyramidLevel];
    }

    public void distort(IplImage src, IplImage dst) {
        if (src != null && dst != null) {
            this.initDistortMaps();
            opencv_imgproc.cvRemap((CvArr)src, (CvArr)dst, (CvArr)this.distortMaps1[this.mapsPyramidLevel], (CvArr)this.distortMaps2[this.mapsPyramidLevel], (int)9, (CvScalar)CvScalar.ZERO);
        }
    }

    public IplImage distort(IplImage image) {
        if (image != null) {
            this.initDistortMaps();
            this.tempImage = IplImage.createIfNotCompatible((IplImage)this.tempImage, (IplImage)image);
            opencv_imgproc.cvRemap((CvArr)image, (CvArr)this.tempImage, (CvArr)this.distortMaps1[this.mapsPyramidLevel], (CvArr)this.distortMaps2[this.mapsPyramidLevel], (int)9, (CvScalar)CvScalar.ZERO);
            return this.tempImage;
        }
        return null;
    }

    public CvMat getBackProjectionMatrix(CvMat n2, double d2, CvMat B) {
        CvMat temp = temp3x3.get();
        temp.cols(1);
        temp.step(temp.step() / 3);
        B.rows(3);
        opencv_core.cvGEMM((CvArr)this.R, (CvArr)this.T, (double)-1.0, null, (double)0.0, (CvArr)temp, (int)1);
        opencv_core.cvGEMM((CvArr)temp, (CvArr)n2, (double)1.0, null, (double)0.0, (CvArr)B, (int)2);
        double a2 = opencv_core.cvDotProduct((CvArr)n2, (CvArr)temp) + d2;
        B.put(0, B.get(0) - a2);
        B.put(4, B.get(4) - a2);
        B.put(8, B.get(8) - a2);
        B.rows(4);
        temp.cols(3);
        temp.step(temp.step() * 3);
        B.put(9, n2.get());
        opencv_core.cvMatMul((CvArr)this.cameraMatrix, (CvArr)this.R, (CvArr)temp);
        opencv_core.cvInvert((CvArr)temp, (CvArr)temp, (int)0);
        opencv_core.cvMatMul((CvArr)B, (CvArr)temp, (CvArr)B);
        opencv_core.cvConvertScale((CvArr)B, (CvArr)B, (double)(1.0 / B.get(11)), (double)0.0);
        return B;
    }

    public CvMat getFrontoParallelH(double[] roipts, CvMat n2, CvMat H) {
        CvMat B = B4x3.get();
        CvMat a2 = a4x1.get();
        CvMat t2 = t3x1.get();
        double s2 = Math.signum(n2.get(2));
        double[] dir = JavaCV.unitize(-s2 * n2.get(1), s2 * n2.get(0));
        double theta = Math.acos(s2 * n2.get(2) / JavaCV.norm(n2.get()));
        t2.put(new double[]{theta * dir[0], theta * dir[1], 0.0});
        opencv_calib3d.Rodrigues((Mat)opencv_core.cvarrToMat((CvArr)t2), (Mat)opencv_core.cvarrToMat((CvArr)H), null);
        opencv_core.cvMatMul((CvArr)this.R, (CvArr)H, (CvArr)H);
        double x2 = 0.0;
        double y2 = 0.0;
        if (roipts != null) {
            double x1 = roipts[0];
            double y1 = roipts[1];
            double x22 = roipts[4];
            double y22 = roipts[5];
            double x3 = roipts[2];
            double y3 = roipts[3];
            double x4 = roipts[6];
            double y4 = roipts[7];
            double u2 = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / ((y4 - y3) * (x22 - x1) - (x4 - x3) * (y22 - y1));
            x2 = x1 + u2 * (x22 - x1);
            y2 = y1 + u2 * (y22 - y1);
        }
        this.getBackProjectionMatrix(n2, -1.0, B);
        t2.put(new double[]{x2, y2, 1.0});
        opencv_core.cvMatMul((CvArr)B, (CvArr)t2, (CvArr)a2);
        H.put(2, a2.get(0) / a2.get(3));
        H.put(5, a2.get(1) / a2.get(3));
        H.put(8, a2.get(2) / a2.get(3));
        return H;
    }

    public CvMat getRectifyingHomography(ProjectiveDevice peer, CvMat H) {
        CvMat relativeR = relativeR3x3.get();
        CvMat relativeT = relativeT3x1.get();
        opencv_core.cvGEMM((CvArr)this.R, (CvArr)peer.R, (double)1.0, null, (double)0.0, (CvArr)relativeR, (int)2);
        opencv_core.cvGEMM((CvArr)relativeR, (CvArr)peer.T, (double)-1.0, (CvArr)this.T, (double)1.0, (CvArr)relativeT, (int)0);
        CvMat R1 = R13x3.get();
        CvMat P1 = P13x4.get();
        CvMat R2 = R23x3.get();
        CvMat P2 = P23x4.get();
        Size imageSize = new Size((peer.imageWidth + this.imageWidth) / 2, (peer.imageHeight + this.imageHeight) / 2);
        opencv_calib3d.stereoRectify((Mat)opencv_core.cvarrToMat((CvArr)peer.cameraMatrix), (Mat)opencv_core.cvarrToMat((CvArr)this.cameraMatrix), (Mat)opencv_core.cvarrToMat((CvArr)peer.distortionCoeffs), (Mat)opencv_core.cvarrToMat((CvArr)this.distortionCoeffs), (Size)imageSize, (Mat)opencv_core.cvarrToMat((CvArr)relativeR), (Mat)opencv_core.cvarrToMat((CvArr)relativeT), (Mat)opencv_core.cvarrToMat((CvArr)R1), (Mat)opencv_core.cvarrToMat((CvArr)R2), (Mat)opencv_core.cvarrToMat((CvArr)P1), (Mat)opencv_core.cvarrToMat((CvArr)P2), (Mat)new Mat(), (int)0, (double)-1.0, (Size)new Size(), null, null);
        opencv_core.cvMatMul((CvArr)this.cameraMatrix, (CvArr)R2, (CvArr)R2);
        opencv_core.cvInvert((CvArr)this.cameraMatrix, (CvArr)R1);
        opencv_core.cvMatMul((CvArr)R2, (CvArr)R1, (CvArr)H);
        return H;
    }

    public static ProjectiveDevice[] read(String filename) throws Exception {
        FileStorage fs2 = new FileStorage(filename, 0);
        CameraDevice[] cameraDevices = CameraDevice.read(fs2);
        ProjectorDevice[] projectorDevices = ProjectorDevice.read(fs2);
        ProjectiveDevice[] devices = new ProjectiveDevice[cameraDevices.length + projectorDevices.length];
        int i2 = 0;
        for (CameraDevice cameraDevice : cameraDevices) {
            devices[i2++] = cameraDevice;
        }
        for (ProjectiveDevice projectiveDevice : projectorDevices) {
            devices[i2++] = projectiveDevice;
        }
        fs2.release();
        return devices;
    }

    public static void write(String filename, ProjectiveDevice[] ... devices) {
        int totalLength = 0;
        for (ProjectiveDevice[] ds2 : devices) {
            totalLength += ds2.length;
        }
        ProjectiveDevice[] allDevices = new ProjectiveDevice[totalLength];
        int i2 = 0;
        ProjectiveDevice[][] projectiveDeviceArray = devices;
        int n2 = projectiveDeviceArray.length;
        for (int i3 = 0; i3 < n2; ++i3) {
            ProjectiveDevice[] ds3;
            for (ProjectiveDevice d2 : ds3 = projectiveDeviceArray[i3]) {
                allDevices[i2++] = d2;
            }
        }
        ProjectiveDevice.write(filename, allDevices);
    }

    public static void write(String filename, ProjectiveDevice ... devices) {
        FileStorage fs2 = new FileStorage(filename, 1);
        opencv_core.shiftLeft((FileStorage)opencv_core.shiftLeft((FileStorage)fs2, (String)"Cameras"), (String)"[");
        for (ProjectiveDevice d2 : devices) {
            if (!(d2 instanceof CameraDevice)) continue;
            opencv_core.write((FileStorage)fs2, (String)d2.getSettings().getName());
        }
        opencv_core.shiftLeft((FileStorage)fs2, (String)"]");
        opencv_core.shiftLeft((FileStorage)opencv_core.shiftLeft((FileStorage)fs2, (String)"Projectors"), (String)"[");
        for (ProjectiveDevice d2 : devices) {
            if (!(d2 instanceof ProjectorDevice)) continue;
            opencv_core.write((FileStorage)fs2, (String)d2.getSettings().getName());
        }
        opencv_core.shiftLeft((FileStorage)fs2, (String)"]");
        for (ProjectiveDevice d2 : devices) {
            d2.writeParameters(fs2);
        }
        fs2.release();
    }

    public void writeParameters(File file) {
        this.writeParameters(file.getAbsolutePath());
    }

    public void writeParameters(String filename) {
        FileStorage fs2 = new FileStorage(filename, 1);
        this.writeParameters(fs2);
        fs2.release();
    }

    public void writeParameters(FileStorage fs2) {
        opencv_core.shiftLeft((FileStorage)opencv_core.shiftLeft((FileStorage)fs2, (String)this.getSettings().getName()), (String)"{");
        opencv_core.write((FileStorage)fs2, (String)"imageWidth", (int)this.imageWidth);
        opencv_core.write((FileStorage)fs2, (String)"imageHeight", (int)this.imageHeight);
        opencv_core.write((FileStorage)fs2, (String)"responseGamma", (double)this.getSettings().getResponseGamma());
        if (this.cameraMatrix != null) {
            opencv_core.write((FileStorage)fs2, (String)"cameraMatrix", (Mat)opencv_core.cvarrToMat((CvArr)this.cameraMatrix));
        }
        if (this.distortionCoeffs != null) {
            opencv_core.write((FileStorage)fs2, (String)"distortionCoeffs", (Mat)opencv_core.cvarrToMat((CvArr)this.distortionCoeffs));
        }
        if (this.extrParams != null) {
            opencv_core.write((FileStorage)fs2, (String)"extrParams", (Mat)opencv_core.cvarrToMat((CvArr)this.extrParams));
        }
        if (this.reprojErrs != null) {
            opencv_core.write((FileStorage)fs2, (String)"reprojErrs", (Mat)opencv_core.cvarrToMat((CvArr)this.reprojErrs));
        }
        opencv_core.write((FileStorage)fs2, (String)"avgReprojErr", (double)this.avgReprojErr);
        opencv_core.write((FileStorage)fs2, (String)"maxReprojErr", (double)this.maxReprojErr);
        if (this.R != null) {
            opencv_core.write((FileStorage)fs2, (String)"R", (Mat)opencv_core.cvarrToMat((CvArr)this.R));
        }
        if (this.T != null) {
            opencv_core.write((FileStorage)fs2, (String)"T", (Mat)opencv_core.cvarrToMat((CvArr)this.T));
        }
        if (this.E != null) {
            opencv_core.write((FileStorage)fs2, (String)"E", (Mat)opencv_core.cvarrToMat((CvArr)this.E));
        }
        if (this.F != null) {
            opencv_core.write((FileStorage)fs2, (String)"F", (Mat)opencv_core.cvarrToMat((CvArr)this.F));
        }
        opencv_core.write((FileStorage)fs2, (String)"avgEpipolarErr", (double)this.avgEpipolarErr);
        opencv_core.write((FileStorage)fs2, (String)"maxEpipolarErr", (double)this.maxEpipolarErr);
        opencv_core.write((FileStorage)fs2, (String)"colorOrder", (String)this.colorOrder);
        if (this.colorMixingMatrix != null) {
            opencv_core.write((FileStorage)fs2, (String)"colorMixingMatrix", (Mat)opencv_core.cvarrToMat((CvArr)this.colorMixingMatrix));
        }
        if (this.additiveLight != null) {
            opencv_core.write((FileStorage)fs2, (String)"additiveLight", (Mat)opencv_core.cvarrToMat((CvArr)this.additiveLight));
        }
        opencv_core.write((FileStorage)fs2, (String)"avgColorErr", (double)this.avgColorErr);
        opencv_core.write((FileStorage)fs2, (String)"colorR2", (double)this.colorR2);
        opencv_core.shiftLeft((FileStorage)fs2, (String)"}");
    }

    public void readParameters(File file) throws Exception {
        this.readParameters(file.getAbsolutePath());
    }

    public void readParameters(String filename) throws Exception {
        FileStorage fs2 = new FileStorage(filename, 0);
        this.readParameters(fs2);
        fs2.release();
    }

    public void readParameters(FileStorage fs2) throws Exception {
        if (fs2 == null) {
            throw new Exception("Error: FileStorage is null, cannot read parameters for device " + this.getSettings().getName() + ". Is the parametersFile correct?");
        }
        FileNode fn2 = fs2.get(this.getSettings().getName());
        if (fn2 == null) {
            throw new Exception("Error: FileNode is null, cannot read parameters for device " + this.getSettings().getName() + ". Is the name correct?");
        }
        FileNode n2 = fn2.get("imageWidth");
        if (n2.isInt()) {
            this.imageWidth = n2.asInt();
        }
        if ((n2 = fn2.get("imageHeight")).isInt()) {
            this.imageHeight = n2.asInt();
        }
        if ((n2 = fn2.get("gamma")).isReal()) {
            this.getSettings().setResponseGamma(n2.asDouble());
        }
        Mat m2 = new Mat();
        opencv_core.read((FileNode)fn2.get("cameraMatrix"), (Mat)m2);
        this.cameraMatrix = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        opencv_core.read((FileNode)fn2.get("distortionCoeffs"), (Mat)m2);
        this.distortionCoeffs = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        opencv_core.read((FileNode)fn2.get("extrParams"), (Mat)m2);
        this.extrParams = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        opencv_core.read((FileNode)fn2.get("reprojErrs"), (Mat)m2);
        this.reprojErrs = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        n2 = fn2.get("avgReprojErr");
        if (n2.isReal()) {
            this.avgReprojErr = n2.asDouble();
        }
        if ((n2 = fn2.get("maxReprojErr")).isReal()) {
            this.maxReprojErr = n2.asDouble();
        }
        opencv_core.read((FileNode)fn2.get("R"), (Mat)m2);
        this.R = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        opencv_core.read((FileNode)fn2.get("T"), (Mat)m2);
        this.T = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        opencv_core.read((FileNode)fn2.get("E"), (Mat)m2);
        this.E = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        opencv_core.read((FileNode)fn2.get("F"), (Mat)m2);
        this.F = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        n2 = fn2.get("avgEpipolarErr");
        if (n2.isReal()) {
            this.avgEpipolarErr = n2.asDouble();
        }
        if ((n2 = fn2.get("maxEpipolarErr")).isReal()) {
            this.maxEpipolarErr = n2.asDouble();
        }
        if ((n2 = fn2.get("colorOrder")).isString()) {
            this.colorOrder = n2.asBytePointer().getString();
        }
        opencv_core.read((FileNode)fn2.get("colorMixingMatrix"), (Mat)m2);
        this.colorMixingMatrix = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        opencv_core.read((FileNode)fn2.get("additiveLight"), (Mat)m2);
        this.additiveLight = m2.empty() ? null : opencv_core.cvMat((Mat)m2).clone();
        n2 = fn2.get("avgColorErr");
        if (n2.isReal()) {
            this.avgColorErr = n2.asDouble();
        }
        if ((n2 = fn2.get("colorR2")).isReal()) {
            this.colorR2 = n2.asDouble();
        }
    }

    public String toString() {
        String s2 = this.getSettings().getName() + " (" + this.imageWidth + " x " + this.imageHeight + ")\n";
        for (int i2 = 0; i2 < this.getSettings().getName().length(); ++i2) {
            s2 = s2 + "=";
        }
        s2 = s2 + "\nIntrinsics\n----------\ncamera matrix = " + (this.cameraMatrix == null ? "null" : this.cameraMatrix.toString(16)) + "\ndistortion coefficients = " + (this.distortionCoeffs == null ? "null" : this.distortionCoeffs) + "\nreprojection RMS/max error (pixels) = " + (float)this.avgReprojErr + " / " + (float)this.maxReprojErr + "\n\nExtrinsics\n----------\nrotation = " + (this.R == null ? "null" : this.R.toString(11)) + "\ntranslation = " + (this.T == null ? "null" : this.T.toString(14)) + "\nepipolar RMS/max error (pixels) = " + (float)this.avgEpipolarErr + " / " + (float)this.maxEpipolarErr + "\n\nColor\n-----\norder = " + this.colorOrder + "\nmixing matrix = " + (this.colorMixingMatrix == null ? "null" : this.colorMixingMatrix.toString(16)) + "\nadditive light = " + (this.additiveLight == null ? "null" : this.additiveLight.toString(17)) + "\nnormalized RMSE (intensity) = " + (float)this.avgColorErr + "\nR^2 (intensity) = " + (float)this.colorR2;
        return s2;
    }

    public static class Exception
    extends java.lang.Exception {
        public Exception(String message) {
            super(message);
        }

        public Exception(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static class CalibratedSettings
    extends Settings {
        File parametersFile = new File("calibration.yaml");

        public CalibratedSettings() {
        }

        public CalibratedSettings(CalibratedSettings settings) {
            super(settings);
            this.parametersFile = settings.parametersFile;
        }

        public File getParametersFile() {
            return this.parametersFile;
        }

        public void setParametersFile(File parametersFile) {
            this.parametersFile = parametersFile;
        }

        public String getParametersFilename() {
            return this.parametersFile == null ? "" : this.parametersFile.getPath();
        }

        public void setParametersFilename(String parametersFilename) {
            this.parametersFile = parametersFilename == null || parametersFilename.length() == 0 ? null : new File(parametersFilename);
        }
    }

    public static class CalibrationSettings
    extends Settings {
        double initAspectRatio = 1.0;
        int flags = 14720;

        public CalibrationSettings() {
        }

        public CalibrationSettings(CalibrationSettings settings) {
            super(settings);
            this.initAspectRatio = settings.initAspectRatio;
            this.flags = settings.flags;
        }

        public double getInitAspectRatio() {
            return this.initAspectRatio;
        }

        public void setInitAspectRatio(double initAspectRatio) {
            this.initAspectRatio = initAspectRatio;
        }

        public boolean isUseIntrinsicGuess() {
            return (this.flags & 1) != 0;
        }

        public void setUseIntrinsicGuess(boolean useIntrinsicGuess) {
            this.flags = useIntrinsicGuess ? (this.flags |= 1) : (this.flags &= 0xFFFFFFFE);
        }

        public boolean isFixAspectRatio() {
            return (this.flags & 2) != 0;
        }

        public void setFixAspectRatio(boolean fixAspectRatio) {
            this.flags = fixAspectRatio ? (this.flags |= 2) : (this.flags &= 0xFFFFFFFD);
        }

        public boolean isFixPrincipalPoint() {
            return (this.flags & 4) != 0;
        }

        public void setFixPrincipalPoint(boolean fixPrincipalPoint) {
            this.flags = fixPrincipalPoint ? (this.flags |= 4) : (this.flags &= 0xFFFFFFFB);
        }

        public boolean isZeroTangentDist() {
            return (this.flags & 8) != 0;
        }

        public void setZeroTangentDist(boolean zeroTangentDist) {
            this.flags = zeroTangentDist ? (this.flags |= 8) : (this.flags &= 0xFFFFFFF7);
        }

        public boolean isFixFocalLength() {
            return (this.flags & 0x10) != 0;
        }

        public void setFixFocalLength(boolean fixFocalLength) {
            this.flags = fixFocalLength ? (this.flags |= 0x10) : (this.flags &= 0xFFFFFFEF);
        }

        public boolean isFixK1() {
            return (this.flags & 0x20) != 0;
        }

        public void setFixK1(boolean fixK1) {
            this.flags = fixK1 ? (this.flags |= 0x20) : (this.flags &= 0xFFFFFFDF);
        }

        public boolean isFixK2() {
            return (this.flags & 0x40) != 0;
        }

        public void setFixK2(boolean fixK2) {
            this.flags = fixK2 ? (this.flags |= 0x40) : (this.flags &= 0xFFFFFFBF);
        }

        public boolean isFixK3() {
            return (this.flags & 0x80) != 0;
        }

        public void setFixK3(boolean fixK3) {
            this.flags = fixK3 ? (this.flags |= 0x80) : (this.flags &= 0xFFFFFF7F);
        }

        public boolean isFixK4() {
            return (this.flags & 0x800) != 0;
        }

        public void setFixK4(boolean fixK4) {
            this.flags = fixK4 ? (this.flags |= 0x800) : (this.flags &= 0xFFFFF7FF);
        }

        public boolean isFixK5() {
            return (this.flags & 0x1000) != 0;
        }

        public void setFixK5(boolean fixK5) {
            this.flags = fixK5 ? (this.flags |= 0x1000) : (this.flags &= 0xFFFFEFFF);
        }

        public boolean isFixK6() {
            return (this.flags & 0x2000) != 0;
        }

        public void setFixK6(boolean fixK6) {
            this.flags = fixK6 ? (this.flags |= 0x2000) : (this.flags &= 0xFFFFDFFF);
        }

        public boolean isRationalModel() {
            return (this.flags & 0x4000) != 0;
        }

        public void setRationalModel(boolean rationalModel) {
            this.flags = rationalModel ? (this.flags |= 0x4000) : (this.flags &= 0xFFFFBFFF);
        }

        public boolean isStereoFixIntrinsic() {
            return (this.flags & 0x100) != 0;
        }

        public void setStereoFixIntrinsic(boolean stereoFixIntrinsic) {
            this.flags = stereoFixIntrinsic ? (this.flags |= 0x100) : (this.flags &= 0xFFFFFEFF);
        }

        public boolean isStereoSameFocalLength() {
            return (this.flags & 0x200) != 0;
        }

        public void setStereoSameFocalLength(boolean stereoSameFocalLength) {
            this.flags = stereoSameFocalLength ? (this.flags |= 0x200) : (this.flags &= 0xFFFFFDFF);
        }
    }

    public static class Settings
    extends BaseChildSettings {
        String name = "";
        double responseGamma = 0.0;

        public Settings() {
        }

        public Settings(Settings settings) {
            this.name = settings.name;
            this.responseGamma = settings.responseGamma;
        }

        @Override
        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            this.name = name;
            this.firePropertyChange("name", this.name, this.name);
        }

        public double getResponseGamma() {
            return this.responseGamma;
        }

        public void setResponseGamma(double responseGamma) {
            this.responseGamma = responseGamma;
        }
    }
}

