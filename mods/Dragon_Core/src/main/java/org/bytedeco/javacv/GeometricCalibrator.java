/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_calib3d
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.IplImage
 *  org.bytedeco.opencv.opencv_core.Mat
 *  org.bytedeco.opencv.opencv_core.MatVector
 *  org.bytedeco.opencv.opencv_core.Point2f
 *  org.bytedeco.opencv.opencv_core.Point2fVector
 *  org.bytedeco.opencv.opencv_core.Point2fVectorVector
 *  org.bytedeco.opencv.opencv_core.Point3f
 *  org.bytedeco.opencv.opencv_core.Point3fVector
 *  org.bytedeco.opencv.opencv_core.Point3fVectorVector
 *  org.bytedeco.opencv.opencv_core.Size
 *  org.bytedeco.opencv.opencv_core.TermCriteria
 */
package org.bytedeco.javacv;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.MarkedPlane;
import org.bytedeco.javacv.Marker;
import org.bytedeco.javacv.MarkerDetector;
import org.bytedeco.javacv.ProjectiveDevice;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.MatVector;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Point2fVector;
import org.bytedeco.opencv.opencv_core.Point2fVectorVector;
import org.bytedeco.opencv.opencv_core.Point3f;
import org.bytedeco.opencv.opencv_core.Point3fVector;
import org.bytedeco.opencv.opencv_core.Point3fVectorVector;
import org.bytedeco.opencv.opencv_core.Size;
import org.bytedeco.opencv.opencv_core.TermCriteria;

public class GeometricCalibrator {
    private Settings settings;
    MarkerDetector markerDetector;
    private MarkedPlane markedPlane;
    private ProjectiveDevice projectiveDevice;
    private LinkedList<Marker[]> allObjectMarkers = new LinkedList();
    private LinkedList<Marker[]> allImageMarkers = new LinkedList();
    private IplImage tempImage = null;
    private Marker[] lastDetectedMarkers = null;
    private CvMat warp = CvMat.create((int)3, (int)3);
    private CvMat prevWarp = CvMat.create((int)3, (int)3);
    private CvMat lastWarp = CvMat.create((int)3, (int)3);
    private CvMat warpSrcPts = CvMat.create((int)1, (int)4, (int)6, (int)2);
    private CvMat warpDstPts = CvMat.create((int)1, (int)4, (int)6, (int)2);
    private CvMat tempPts = CvMat.create((int)1, (int)4, (int)6, (int)2);

    public GeometricCalibrator(Settings settings, MarkerDetector.Settings detectorSettings, MarkedPlane markedPlane, ProjectiveDevice projectiveDevice) {
        this.settings = settings;
        this.markerDetector = new MarkerDetector(detectorSettings);
        this.markedPlane = markedPlane;
        this.projectiveDevice = projectiveDevice;
        opencv_core.cvSetIdentity((CvArr)this.prevWarp);
        opencv_core.cvSetIdentity((CvArr)this.lastWarp);
        if (markedPlane != null) {
            int w2 = markedPlane.getImage().width();
            int h2 = markedPlane.getImage().height();
            this.warpSrcPts.put(new double[]{0.0, 0.0, w2, 0.0, w2, h2, 0.0, h2});
        }
    }

    public MarkerDetector getMarkerDetector() {
        return this.markerDetector;
    }

    public MarkedPlane getMarkedPlane() {
        return this.markedPlane;
    }

    public ProjectiveDevice getProjectiveDevice() {
        return this.projectiveDevice;
    }

    public LinkedList<Marker[]> getAllObjectMarkers() {
        return this.allObjectMarkers;
    }

    public void setAllObjectMarkers(LinkedList<Marker[]> allObjectMarkers) {
        this.allObjectMarkers = allObjectMarkers;
    }

    public LinkedList<Marker[]> getAllImageMarkers() {
        return this.allImageMarkers;
    }

    public void setAllImageMarkers(LinkedList<Marker[]> allImageMarkers) {
        this.allImageMarkers = allImageMarkers;
    }

    public Marker[] processImage(IplImage image) {
        boolean whiteMarkers;
        this.projectiveDevice.imageWidth = image.width();
        this.projectiveDevice.imageHeight = image.height();
        boolean bl2 = whiteMarkers = this.markedPlane.getForegroundColor().magnitude() > this.markedPlane.getBackgroundColor().magnitude();
        if (image.depth() > 8) {
            if (this.tempImage == null || this.tempImage.width() != image.width() || this.tempImage.height() != image.height()) {
                this.tempImage = IplImage.create((int)image.width(), (int)image.height(), (int)8, (int)1, (int)image.origin());
            }
            opencv_core.cvConvertScale((CvArr)image, (CvArr)this.tempImage, (double)0.00390625, (double)0.0);
            this.lastDetectedMarkers = this.markerDetector.detect(this.tempImage, whiteMarkers);
        } else {
            this.lastDetectedMarkers = this.markerDetector.detect(image, whiteMarkers);
        }
        if ((double)this.lastDetectedMarkers.length < (double)this.markedPlane.getMarkers().length * this.settings.detectedBoardMin) {
            return null;
        }
        this.markedPlane.getTotalWarp(this.lastDetectedMarkers, this.warp);
        opencv_core.cvPerspectiveTransform((CvArr)this.warpSrcPts, (CvArr)this.warpDstPts, (CvMat)this.warp);
        opencv_core.cvPerspectiveTransform((CvArr)this.warpSrcPts, (CvArr)this.tempPts, (CvMat)this.prevWarp);
        double rmsePrev = opencv_core.cvNorm((CvArr)this.warpDstPts, (CvArr)this.tempPts);
        opencv_core.cvPerspectiveTransform((CvArr)this.warpSrcPts, (CvArr)this.tempPts, (CvMat)this.lastWarp);
        double rmseLast = opencv_core.cvNorm((CvArr)this.warpDstPts, (CvArr)this.tempPts);
        opencv_core.cvCopy((CvArr)this.warp, (CvArr)this.prevWarp);
        int imageSize = (image.width() + image.height()) / 2;
        if (rmsePrev < this.settings.patternSteadySize * (double)imageSize && rmseLast > this.settings.patternMovedSize * (double)imageSize) {
            return this.lastDetectedMarkers;
        }
        return null;
    }

    public void drawMarkers(IplImage image) {
        this.markerDetector.draw(image, this.lastDetectedMarkers);
    }

    public void addMarkers() {
        this.addMarkers(this.markedPlane.getMarkers(), this.lastDetectedMarkers);
    }

    public void addMarkers(Marker[] imageMarkers) {
        this.addMarkers(this.markedPlane.getMarkers(), imageMarkers);
    }

    public void addMarkers(Marker[] objectMarkers, Marker[] imageMarkers) {
        int maxLength = Math.min(objectMarkers.length, imageMarkers.length);
        Marker[] om2 = new Marker[maxLength];
        Marker[] im2 = new Marker[maxLength];
        int i2 = 0;
        block0: for (Marker m1 : objectMarkers) {
            for (Marker m2 : imageMarkers) {
                if (m1.id != m2.id) continue;
                om2[i2] = m1;
                im2[i2] = m2;
                ++i2;
                continue block0;
            }
        }
        if (i2 < maxLength) {
            om2 = Arrays.copyOf(om2, i2);
            im2 = Arrays.copyOf(im2, i2);
        }
        this.allObjectMarkers.add(om2);
        this.allImageMarkers.add(im2);
        opencv_core.cvCopy((CvArr)this.prevWarp, (CvArr)this.lastWarp);
    }

    public int getImageCount() {
        assert (this.allObjectMarkers.size() == this.allImageMarkers.size());
        return this.allObjectMarkers.size();
    }

    private Point3fVectorVector getObjectPoints(CvMat points, CvMat counts) {
        FloatBuffer pointsBuf = points.getFloatBuffer();
        IntBuffer countsBuf = counts.getIntBuffer();
        int n2 = counts.length();
        Point3fVectorVector vectors = new Point3fVectorVector((long)n2);
        for (int i2 = 0; i2 < n2; ++i2) {
            int m2 = countsBuf.get();
            Point3fVector vector = new Point3fVector((long)m2);
            for (int j2 = 0; j2 < m2; ++j2) {
                vector.put((long)j2, new Point3f(pointsBuf.get(), pointsBuf.get(), pointsBuf.get()));
            }
            vectors.put((long)i2, vector);
        }
        return vectors;
    }

    private Point2fVectorVector getImagePoints(CvMat points, CvMat counts) {
        FloatBuffer pointsBuf = points.getFloatBuffer();
        IntBuffer countsBuf = counts.getIntBuffer();
        int n2 = counts.length();
        Point2fVectorVector vectors = new Point2fVectorVector((long)n2);
        for (int i2 = 0; i2 < n2; ++i2) {
            int m2 = countsBuf.get();
            Point2fVector vector = new Point2fVector((long)m2);
            for (int j2 = 0; j2 < m2; ++j2) {
                vector.put((long)j2, new Point2f(pointsBuf.get(), pointsBuf.get()));
            }
            vectors.put((long)i2, vector);
        }
        return vectors;
    }

    private CvMat[] getPoints(boolean useCenters) {
        assert (this.allObjectMarkers.size() == this.allImageMarkers.size());
        Iterator i1 = this.allObjectMarkers.iterator();
        Iterator i2 = this.allImageMarkers.iterator();
        CvMat pointCounts = CvMat.create((int)1, (int)this.allImageMarkers.size(), (int)4, (int)1);
        IntBuffer pointCountsBuf = pointCounts.getIntBuffer();
        int totalPointCount = 0;
        while (i1.hasNext() && i2.hasNext()) {
            Marker[] m1 = (Marker[])i1.next();
            Marker[] m2 = (Marker[])i2.next();
            assert (m1.length == m2.length);
            int n2 = m1.length * (useCenters ? 1 : 4);
            pointCountsBuf.put(n2);
            totalPointCount += n2;
        }
        i1 = this.allObjectMarkers.iterator();
        i2 = this.allImageMarkers.iterator();
        CvMat objectPoints = CvMat.create((int)1, (int)totalPointCount, (int)5, (int)3);
        CvMat imagePoints = CvMat.create((int)1, (int)totalPointCount, (int)5, (int)2);
        FloatBuffer objectPointsBuf = objectPoints.getFloatBuffer();
        FloatBuffer imagePointsBuf = imagePoints.getFloatBuffer();
        while (i1.hasNext() && i2.hasNext()) {
            Marker[] m1 = (Marker[])i1.next();
            Marker[] m2 = (Marker[])i2.next();
            for (int j2 = 0; j2 < m1.length; ++j2) {
                if (useCenters) {
                    double[] c1 = m1[j2].getCenter();
                    objectPointsBuf.put((float)c1[0]);
                    objectPointsBuf.put((float)c1[1]);
                    objectPointsBuf.put(0.0f);
                    double[] c2 = m2[j2].getCenter();
                    imagePointsBuf.put((float)c2[0]);
                    imagePointsBuf.put((float)c2[1]);
                    continue;
                }
                for (int k2 = 0; k2 < 4; ++k2) {
                    objectPointsBuf.put((float)m1[j2].corners[2 * k2]);
                    objectPointsBuf.put((float)m1[j2].corners[2 * k2 + 1]);
                    objectPointsBuf.put(0.0f);
                    imagePointsBuf.put((float)m2[j2].corners[2 * k2]);
                    imagePointsBuf.put((float)m2[j2].corners[2 * k2 + 1]);
                }
            }
        }
        return new CvMat[]{objectPoints, imagePoints, pointCounts};
    }

    public static double[] computeReprojectionError(CvMat object_points, CvMat image_points, CvMat point_counts, CvMat camera_matrix, CvMat dist_coeffs, CvMat rot_vects, CvMat trans_vects, CvMat per_view_errors) {
        CvMat image_points2 = CvMat.create((int)image_points.rows(), (int)image_points.cols(), (int)image_points.type());
        int image_count = rot_vects.rows();
        int points_so_far = 0;
        double total_err = 0.0;
        double max_err = 0.0;
        CvMat object_points_i = new CvMat();
        CvMat image_points_i = new CvMat();
        CvMat image_points2_i = new CvMat();
        IntBuffer point_counts_buf = point_counts.getIntBuffer();
        CvMat rot_vect = new CvMat();
        CvMat trans_vect = new CvMat();
        for (int i2 = 0; i2 < image_count; ++i2) {
            object_points_i.reset();
            image_points_i.reset();
            image_points2_i.reset();
            int point_count = point_counts_buf.get(i2);
            opencv_core.cvGetCols((CvArr)object_points, (CvMat)object_points_i, (int)points_so_far, (int)(points_so_far + point_count));
            opencv_core.cvGetCols((CvArr)image_points, (CvMat)image_points_i, (int)points_so_far, (int)(points_so_far + point_count));
            opencv_core.cvGetCols((CvArr)image_points2, (CvMat)image_points2_i, (int)points_so_far, (int)(points_so_far + point_count));
            points_so_far += point_count;
            opencv_core.cvGetRows((CvArr)rot_vects, (CvMat)rot_vect, (int)i2, (int)(i2 + 1), (int)1);
            opencv_core.cvGetRows((CvArr)trans_vects, (CvMat)trans_vect, (int)i2, (int)(i2 + 1), (int)1);
            opencv_calib3d.projectPoints((Mat)opencv_core.cvarrToMat((CvArr)object_points_i), (Mat)opencv_core.cvarrToMat((CvArr)rot_vect), (Mat)opencv_core.cvarrToMat((CvArr)trans_vect), (Mat)opencv_core.cvarrToMat((CvArr)camera_matrix), (Mat)opencv_core.cvarrToMat((CvArr)dist_coeffs), (Mat)opencv_core.cvarrToMat((CvArr)image_points2_i));
            double err = opencv_core.cvNorm((CvArr)image_points_i, (CvArr)image_points2_i);
            err *= err;
            if (per_view_errors != null) {
                per_view_errors.put(i2, Math.sqrt(err / (double)point_count));
            }
            total_err += err;
            for (int j2 = 0; j2 < point_count; ++j2) {
                double y2;
                double dy2;
                double x1 = image_points_i.get(0, j2, 0);
                double y1 = image_points_i.get(0, j2, 1);
                double x2 = image_points2_i.get(0, j2, 0);
                double dx2 = x1 - x2;
                err = Math.sqrt(dx2 * dx2 + (dy2 = y1 - (y2 = image_points2_i.get(0, j2, 1))) * dy2);
                if (!(err > max_err)) continue;
                max_err = err;
            }
        }
        return new double[]{Math.sqrt(total_err / (double)points_so_far), max_err};
    }

    public double[] calibrate(boolean useCenters) {
        int kn2;
        ProjectiveDevice d2 = this.projectiveDevice;
        ProjectiveDevice.CalibrationSettings dsettings = (ProjectiveDevice.CalibrationSettings)d2.getSettings();
        if (d2.cameraMatrix == null) {
            d2.cameraMatrix = CvMat.create((int)3, (int)3);
            opencv_core.cvSetZero((CvArr)d2.cameraMatrix);
            if ((dsettings.flags & 2) != 0) {
                d2.cameraMatrix.put(0, dsettings.initAspectRatio);
                d2.cameraMatrix.put(4, 1.0);
            }
        }
        int n2 = kn2 = dsettings.isFixK3() ? 4 : 5;
        if (dsettings.isRationalModel() && !dsettings.isFixK4() && !dsettings.isFixK4() && !dsettings.isFixK5()) {
            kn2 = 8;
        }
        if (d2.distortionCoeffs == null || d2.distortionCoeffs.cols() != kn2) {
            d2.distortionCoeffs = CvMat.create((int)1, (int)kn2);
            opencv_core.cvSetZero((CvArr)d2.distortionCoeffs);
        }
        CvMat rotVects = new CvMat();
        CvMat transVects = new CvMat();
        d2.extrParams = CvMat.create((int)this.allImageMarkers.size(), (int)6);
        opencv_core.cvGetCols((CvArr)d2.extrParams, (CvMat)rotVects, (int)0, (int)3);
        opencv_core.cvGetCols((CvArr)d2.extrParams, (CvMat)transVects, (int)3, (int)6);
        CvMat[] points = this.getPoints(useCenters);
        MatVector rvecs = new MatVector();
        MatVector tvecs = new MatVector();
        Mat distortionCoeffs = new Mat();
        opencv_calib3d.calibrateCamera((Point3fVectorVector)this.getObjectPoints(points[0], points[2]), (Point2fVectorVector)this.getImagePoints(points[1], points[2]), (Size)new Size(d2.imageWidth, d2.imageHeight), (Mat)opencv_core.cvarrToMat((CvArr)d2.cameraMatrix), (Mat)distortionCoeffs, (MatVector)rvecs, (MatVector)tvecs, (int)dsettings.flags, (TermCriteria)new TermCriteria(3, 30, 2.220446049250313E-16));
        int n3 = (int)rvecs.size();
        CvMat row = new CvMat();
        for (int i2 = 0; i2 < n3; ++i2) {
            opencv_core.cvTranspose((CvArr)opencv_core.cvMat((Mat)rvecs.get((long)i2)), (CvArr)opencv_core.cvGetRow((CvArr)rotVects, (CvMat)row, (int)i2));
            opencv_core.cvTranspose((CvArr)opencv_core.cvMat((Mat)tvecs.get((long)i2)), (CvArr)opencv_core.cvGetRow((CvArr)transVects, (CvMat)row, (int)i2));
        }
        d2.distortionCoeffs = opencv_core.cvMat((Mat)distortionCoeffs).clone();
        if (opencv_core.cvCheckArr((CvArr)d2.cameraMatrix, (int)2, (double)0.0, (double)0.0) != 0 && opencv_core.cvCheckArr((CvArr)d2.distortionCoeffs, (int)2, (double)0.0, (double)0.0) != 0 && opencv_core.cvCheckArr((CvArr)d2.extrParams, (int)2, (double)0.0, (double)0.0) != 0) {
            d2.reprojErrs = CvMat.create((int)1, (int)this.allImageMarkers.size());
            double[] err = GeometricCalibrator.computeReprojectionError(points[0], points[1], points[2], d2.cameraMatrix, d2.distortionCoeffs, rotVects, transVects, d2.reprojErrs);
            d2.avgReprojErr = err[0];
            d2.maxReprojErr = err[1];
            return err;
        }
        d2.cameraMatrix = null;
        d2.avgReprojErr = -1.0;
        d2.maxReprojErr = -1.0;
        return null;
    }

    public static double[] computeStereoError(CvMat imagePoints1, CvMat imagePoints2, CvMat M1, CvMat D1, CvMat M2, CvMat D2, CvMat F) {
        int N = imagePoints1.cols();
        CvMat L1 = CvMat.create((int)1, (int)N, (int)5, (int)3);
        CvMat L2 = CvMat.create((int)1, (int)N, (int)5, (int)3);
        opencv_calib3d.undistortPoints((Mat)opencv_core.cvarrToMat((CvArr)imagePoints1), (Mat)opencv_core.cvarrToMat((CvArr)imagePoints1), (Mat)opencv_core.cvarrToMat((CvArr)M1), (Mat)opencv_core.cvarrToMat((CvArr)D1), null, (Mat)opencv_core.cvarrToMat((CvArr)M1));
        opencv_calib3d.undistortPoints((Mat)opencv_core.cvarrToMat((CvArr)imagePoints2), (Mat)opencv_core.cvarrToMat((CvArr)imagePoints2), (Mat)opencv_core.cvarrToMat((CvArr)M2), (Mat)opencv_core.cvarrToMat((CvArr)D2), null, (Mat)opencv_core.cvarrToMat((CvArr)M2));
        opencv_calib3d.computeCorrespondEpilines((Mat)opencv_core.cvarrToMat((CvArr)imagePoints1), (int)1, (Mat)opencv_core.cvarrToMat((CvArr)F), (Mat)opencv_core.cvarrToMat((CvArr)L1));
        opencv_calib3d.computeCorrespondEpilines((Mat)opencv_core.cvarrToMat((CvArr)imagePoints2), (int)2, (Mat)opencv_core.cvarrToMat((CvArr)F), (Mat)opencv_core.cvarrToMat((CvArr)L2));
        double avgErr = 0.0;
        double maxErr = 0.0;
        CvMat p1 = imagePoints1;
        CvMat p2 = imagePoints2;
        for (int i2 = 0; i2 < N; ++i2) {
            double e1 = p1.get(0, i2, 0) * L2.get(0, i2, 0) + p1.get(0, i2, 1) * L2.get(0, i2, 1) + L2.get(0, i2, 2);
            double e2 = p2.get(0, i2, 0) * L1.get(0, i2, 0) + p2.get(0, i2, 1) * L1.get(0, i2, 1) + L1.get(0, i2, 2);
            double err = e1 * e1 + e2 * e2;
            avgErr += err;
            if (!((err = Math.sqrt(err)) > maxErr)) continue;
            maxErr = err;
        }
        return new double[]{Math.sqrt(avgErr / (double)N), maxErr};
    }

    public double[] calibrateStereo(boolean useCenters, GeometricCalibrator peer) {
        ProjectiveDevice d2 = this.projectiveDevice;
        ProjectiveDevice dp2 = peer.projectiveDevice;
        ProjectiveDevice.CalibrationSettings dsettings = (ProjectiveDevice.CalibrationSettings)d2.getSettings();
        ProjectiveDevice.CalibrationSettings dpsettings = (ProjectiveDevice.CalibrationSettings)dp2.getSettings();
        CvMat[] points1 = this.getPoints(useCenters);
        CvMat[] points2 = peer.getPoints(useCenters);
        FloatBuffer objPts1 = points1[0].getFloatBuffer();
        FloatBuffer imgPts1 = points1[1].getFloatBuffer();
        IntBuffer imgCount1 = points1[2].getIntBuffer();
        FloatBuffer objPts2 = points2[0].getFloatBuffer();
        FloatBuffer imgPts2 = points2[1].getFloatBuffer();
        IntBuffer imgCount2 = points2[2].getIntBuffer();
        assert (imgCount1.capacity() == imgCount2.capacity());
        CvMat objectPointsMat = CvMat.create((int)1, (int)Math.min(objPts1.capacity(), objPts2.capacity()), (int)5, (int)3);
        CvMat imagePoints1Mat = CvMat.create((int)1, (int)Math.min(imgPts1.capacity(), imgPts2.capacity()), (int)5, (int)2);
        CvMat imagePoints2Mat = CvMat.create((int)1, (int)Math.min(imgPts1.capacity(), imgPts2.capacity()), (int)5, (int)2);
        CvMat pointCountsMat = CvMat.create((int)1, (int)imgCount1.capacity(), (int)4, (int)1);
        FloatBuffer objectPoints = objectPointsMat.getFloatBuffer();
        FloatBuffer imagePoints1 = imagePoints1Mat.getFloatBuffer();
        FloatBuffer imagePoints2 = imagePoints2Mat.getFloatBuffer();
        IntBuffer pointCounts = pointCountsMat.getIntBuffer();
        int end1 = 0;
        int end2 = 0;
        for (int i2 = 0; i2 < imgCount1.capacity(); ++i2) {
            int start1 = end1;
            int start2 = end2;
            end1 = start1 + imgCount1.get(i2);
            end2 = start2 + imgCount2.get(i2);
            int count = 0;
            block1: for (int j2 = start1; j2 < end1; ++j2) {
                float x1 = objPts1.get(j2 * 3);
                float y1 = objPts1.get(j2 * 3 + 1);
                float z1 = objPts1.get(j2 * 3 + 2);
                for (int k2 = start2; k2 < end2; ++k2) {
                    float x2 = objPts2.get(k2 * 3);
                    float y2 = objPts2.get(k2 * 3 + 1);
                    float z2 = objPts2.get(k2 * 3 + 2);
                    if (x1 != x2 || y1 != y2 || z1 != z2) continue;
                    objectPoints.put(x1);
                    objectPoints.put(y1);
                    objectPoints.put(z1);
                    imagePoints1.put(imgPts1.get(j2 * 2));
                    imagePoints1.put(imgPts1.get(j2 * 2 + 1));
                    imagePoints2.put(imgPts2.get(k2 * 2));
                    imagePoints2.put(imgPts2.get(k2 * 2 + 1));
                    ++count;
                    continue block1;
                }
            }
            if (count <= 0) continue;
            pointCounts.put(count);
        }
        objectPointsMat.cols(objectPoints.position() / 3);
        imagePoints1Mat.cols(imagePoints1.position() / 2);
        imagePoints2Mat.cols(imagePoints2.position() / 2);
        pointCountsMat.cols(pointCounts.position());
        d2.R = CvMat.create((int)3, (int)3);
        d2.R.put(new double[]{1.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0});
        d2.T = CvMat.create((int)3, (int)1);
        d2.T.put(new double[]{0.0, 0.0, 0.0});
        d2.E = CvMat.create((int)3, (int)3);
        opencv_core.cvSetZero((CvArr)d2.E);
        d2.F = CvMat.create((int)3, (int)3);
        opencv_core.cvSetZero((CvArr)d2.F);
        dp2.R = CvMat.create((int)3, (int)3);
        dp2.T = CvMat.create((int)3, (int)1);
        dp2.E = CvMat.create((int)3, (int)3);
        dp2.F = CvMat.create((int)3, (int)3);
        opencv_calib3d.stereoCalibrate((Point3fVectorVector)this.getObjectPoints(objectPointsMat, pointCountsMat), (Point2fVectorVector)this.getImagePoints(imagePoints1Mat, pointCountsMat), (Point2fVectorVector)this.getImagePoints(imagePoints2Mat, pointCountsMat), (Mat)opencv_core.cvarrToMat((CvArr)d2.cameraMatrix), (Mat)opencv_core.cvarrToMat((CvArr)d2.distortionCoeffs), (Mat)opencv_core.cvarrToMat((CvArr)dp2.cameraMatrix), (Mat)opencv_core.cvarrToMat((CvArr)dp2.distortionCoeffs), (Size)new Size(d2.imageWidth, d2.imageHeight), (Mat)opencv_core.cvarrToMat((CvArr)dp2.R), (Mat)opencv_core.cvarrToMat((CvArr)dp2.T), (Mat)opencv_core.cvarrToMat((CvArr)dp2.E), (Mat)opencv_core.cvarrToMat((CvArr)dp2.F), (int)dpsettings.flags, (TermCriteria)new TermCriteria(3, 100, 1.0E-6));
        d2.avgEpipolarErr = 0.0;
        d2.maxEpipolarErr = 0.0;
        double[] err = GeometricCalibrator.computeStereoError(imagePoints1Mat, imagePoints2Mat, d2.cameraMatrix, d2.distortionCoeffs, dp2.cameraMatrix, dp2.distortionCoeffs, dp2.F);
        dp2.avgEpipolarErr = err[0];
        dp2.maxEpipolarErr = err[1];
        return err;
    }

    public static class Settings
    extends BaseChildSettings {
        double detectedBoardMin = 0.5;
        double patternSteadySize = 0.005;
        double patternMovedSize = 0.05;

        public double getDetectedBoardMin() {
            return this.detectedBoardMin;
        }

        public void setDetectedBoardMin(double detectedBoardMin) {
            this.detectedBoardMin = detectedBoardMin;
        }

        public double getPatternSteadySize() {
            return this.patternSteadySize;
        }

        public void setPatternSteadySize(double patternSteadySize) {
            this.patternSteadySize = patternSteadySize;
        }

        public double getPatternMovedSize() {
            return this.patternMovedSize;
        }

        public void setPatternMovedSize(double patternMovedSize) {
            this.patternMovedSize = patternMovedSize;
        }
    }
}

