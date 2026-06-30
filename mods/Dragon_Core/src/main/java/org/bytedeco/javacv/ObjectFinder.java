/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_calib3d
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_imgcodecs
 *  org.bytedeco.opencv.global.opencv_imgproc
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvRect
 *  org.bytedeco.opencv.opencv_core.IplImage
 *  org.bytedeco.opencv.opencv_core.KeyPoint
 *  org.bytedeco.opencv.opencv_core.KeyPointVector
 *  org.bytedeco.opencv.opencv_core.Mat
 *  org.bytedeco.opencv.opencv_core.Point
 *  org.bytedeco.opencv.opencv_core.Point2f
 *  org.bytedeco.opencv.opencv_core.Scalar
 *  org.bytedeco.opencv.opencv_features2d.AKAZE
 *  org.bytedeco.opencv.opencv_flann.Index
 *  org.bytedeco.opencv.opencv_flann.IndexParams
 *  org.bytedeco.opencv.opencv_flann.LshIndexParams
 *  org.bytedeco.opencv.opencv_flann.SearchParams
 */
package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgcodecs;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.KeyPoint;
import org.bytedeco.opencv.opencv_core.KeyPointVector;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.Point;
import org.bytedeco.opencv.opencv_core.Point2f;
import org.bytedeco.opencv.opencv_core.Scalar;
import org.bytedeco.opencv.opencv_features2d.AKAZE;
import org.bytedeco.opencv.opencv_flann.Index;
import org.bytedeco.opencv.opencv_flann.IndexParams;
import org.bytedeco.opencv.opencv_flann.LshIndexParams;
import org.bytedeco.opencv.opencv_flann.SearchParams;

public class ObjectFinder {
    Settings settings;
    static final Logger logger = Logger.getLogger(ObjectFinder.class.getName());
    KeyPointVector objectKeypoints = null;
    KeyPointVector imageKeypoints = null;
    Mat objectDescriptors = null;
    Mat imageDescriptors = null;
    Mat indicesMat;
    Mat distsMat;
    Index flannIndex = null;
    IndexParams indexParams = null;
    SearchParams searchParams = null;
    Mat pt1 = null;
    Mat pt2 = null;
    Mat mask = null;
    Mat H = null;
    ArrayList<Integer> ptpairs = null;
    static final int[] bits = new int[256];

    public ObjectFinder(IplImage objectImage) {
        this.settings = new Settings();
        this.settings.objectImage = objectImage;
        this.setSettings(this.settings);
    }

    public ObjectFinder(Settings settings) {
        this.setSettings(settings);
    }

    public Settings getSettings() {
        return this.settings;
    }

    public void setSettings(Settings settings) {
        this.settings = settings;
        this.objectKeypoints = new KeyPointVector();
        this.objectDescriptors = new Mat();
        settings.detector.detectAndCompute(opencv_core.cvarrToMat((CvArr)settings.objectImage), new Mat(), this.objectKeypoints, this.objectDescriptors, false);
        int total = (int)this.objectKeypoints.size();
        if (settings.useFLANN) {
            this.indicesMat = new Mat(total, 2, opencv_core.CV_32SC1);
            this.distsMat = new Mat(total, 2, opencv_core.CV_32FC1);
            this.flannIndex = new Index();
            this.indexParams = new LshIndexParams(12, 20, 2);
            this.searchParams = new SearchParams(64, 0.0f, true);
            this.searchParams.deallocate(false);
        }
        this.pt1 = new Mat(total, 1, opencv_core.CV_32FC2);
        this.pt2 = new Mat(total, 1, opencv_core.CV_32FC2);
        this.mask = new Mat(total, 1, opencv_core.CV_8UC1);
        this.H = new Mat(3, 3, opencv_core.CV_64FC1);
        this.ptpairs = new ArrayList(2 * this.objectDescriptors.rows());
        logger.info(total + " object descriptors");
    }

    public double[] find(IplImage image) {
        if (this.objectDescriptors.rows() < this.settings.getMatchesMin()) {
            return null;
        }
        this.imageKeypoints = new KeyPointVector();
        this.imageDescriptors = new Mat();
        this.settings.detector.detectAndCompute(opencv_core.cvarrToMat((CvArr)image), new Mat(), this.imageKeypoints, this.imageDescriptors, false);
        if (this.imageDescriptors.rows() < this.settings.getMatchesMin()) {
            return null;
        }
        int total = (int)this.imageKeypoints.size();
        logger.info(total + " image descriptors");
        int w2 = this.settings.objectImage.width();
        int h2 = this.settings.objectImage.height();
        double[] srcCorners = new double[]{0.0, 0.0, w2, 0.0, w2, h2, 0.0, h2};
        double[] dstCorners = this.locatePlanarObject(this.objectKeypoints, this.objectDescriptors, this.imageKeypoints, this.imageDescriptors, srcCorners);
        return dstCorners;
    }

    int compareDescriptors(ByteBuffer d1, ByteBuffer d2, int best) {
        int totalCost = 0;
        assert (d1.limit() - d1.position() == d2.limit() - d2.position());
        while (d1.position() < d1.limit() && (totalCost += bits[(d1.get() ^ d2.get()) & 0xFF]) <= best) {
        }
        return totalCost;
    }

    int naiveNearestNeighbor(ByteBuffer vec, ByteBuffer modelDescriptors) {
        int neighbor = -1;
        int dist1 = Integer.MAX_VALUE;
        int dist2 = Integer.MAX_VALUE;
        int size = vec.limit() - vec.position();
        int i2 = 0;
        while (i2 * size < modelDescriptors.capacity()) {
            ByteBuffer mvec = (ByteBuffer)modelDescriptors.position(i2 * size).limit((i2 + 1) * size);
            int d2 = this.compareDescriptors((ByteBuffer)vec.reset(), mvec, dist2);
            if (d2 < dist1) {
                dist2 = dist1;
                dist1 = d2;
                neighbor = i2;
            } else if (d2 < dist2) {
                dist2 = d2;
            }
            ++i2;
        }
        if ((double)dist1 < this.settings.distanceThreshold * (double)dist2) {
            return neighbor;
        }
        return -1;
    }

    void findPairs(Mat objectDescriptors, Mat imageDescriptors) {
        int size = imageDescriptors.cols();
        ByteBuffer objectBuf = (ByteBuffer)objectDescriptors.createBuffer();
        ByteBuffer imageBuf = (ByteBuffer)imageDescriptors.createBuffer();
        int i2 = 0;
        while (i2 * size < objectBuf.capacity()) {
            ByteBuffer descriptor = (ByteBuffer)objectBuf.position(i2 * size).limit((i2 + 1) * size).mark();
            int nearestNeighbor = this.naiveNearestNeighbor(descriptor, imageBuf);
            if (nearestNeighbor >= 0) {
                this.ptpairs.add(i2);
                this.ptpairs.add(nearestNeighbor);
            }
            ++i2;
        }
    }

    void flannFindPairs(Mat objectDescriptors, Mat imageDescriptors) {
        int length = objectDescriptors.rows();
        this.flannIndex.build(imageDescriptors, this.indexParams, 9);
        this.flannIndex.knnSearch(objectDescriptors, this.indicesMat, this.distsMat, 2, this.searchParams);
        IntBuffer indicesBuf = (IntBuffer)this.indicesMat.createBuffer();
        IntBuffer distsBuf = (IntBuffer)this.distsMat.createBuffer();
        for (int i2 = 0; i2 < length; ++i2) {
            if (!((double)distsBuf.get(2 * i2) < this.settings.distanceThreshold * (double)distsBuf.get(2 * i2 + 1))) continue;
            this.ptpairs.add(i2);
            this.ptpairs.add(indicesBuf.get(2 * i2));
        }
    }

    double[] locatePlanarObject(KeyPointVector objectKeypoints, Mat objectDescriptors, KeyPointVector imageKeypoints, Mat imageDescriptors, double[] srcCorners) {
        this.ptpairs.clear();
        if (this.settings.useFLANN) {
            this.flannFindPairs(objectDescriptors, imageDescriptors);
        } else {
            this.findPairs(objectDescriptors, imageDescriptors);
        }
        int n2 = this.ptpairs.size() / 2;
        logger.info(n2 + " matching pairs found");
        if (n2 < this.settings.matchesMin) {
            return null;
        }
        this.pt1.resize((long)n2);
        this.pt2.resize((long)n2);
        this.mask.resize((long)n2);
        FloatBuffer pt1Idx = (FloatBuffer)this.pt1.createBuffer();
        FloatBuffer pt2Idx = (FloatBuffer)this.pt2.createBuffer();
        for (int i2 = 0; i2 < n2; ++i2) {
            Point2f p1 = objectKeypoints.get((long)this.ptpairs.get(2 * i2).intValue()).pt();
            pt1Idx.put(2 * i2, p1.x());
            pt1Idx.put(2 * i2 + 1, p1.y());
            Point2f p2 = imageKeypoints.get((long)this.ptpairs.get(2 * i2 + 1).intValue()).pt();
            pt2Idx.put(2 * i2, p2.x());
            pt2Idx.put(2 * i2 + 1, p2.y());
        }
        this.H = opencv_calib3d.findHomography((Mat)this.pt1, (Mat)this.pt2, (int)8, (double)this.settings.ransacReprojThreshold, (Mat)this.mask, (int)2000, (double)0.995);
        if (this.H.empty() || opencv_core.countNonZero((Mat)this.mask) < this.settings.matchesMin) {
            return null;
        }
        double[] h2 = (double[])this.H.createIndexer(false).array();
        double[] dstCorners = new double[srcCorners.length];
        for (int i3 = 0; i3 < srcCorners.length / 2; ++i3) {
            double x2 = srcCorners[2 * i3];
            double y2 = srcCorners[2 * i3 + 1];
            double Z = 1.0 / (h2[6] * x2 + h2[7] * y2 + h2[8]);
            double X = (h2[0] * x2 + h2[1] * y2 + h2[2]) * Z;
            double Y = (h2[3] * x2 + h2[4] * y2 + h2[5]) * Z;
            dstCorners[2 * i3] = X;
            dstCorners[2 * i3 + 1] = Y;
        }
        return dstCorners;
    }

    public static void main(String[] args) throws Exception {
        int i2;
        String objectFilename = args.length == 2 ? args[0] : "/usr/local/share/OpenCV/samples/c/box.png";
        String sceneFilename = args.length == 2 ? args[1] : "/usr/local/share/OpenCV/samples/c/box_in_scene.png";
        IplImage object = opencv_imgcodecs.cvLoadImage((String)objectFilename, (int)0);
        IplImage image = opencv_imgcodecs.cvLoadImage((String)sceneFilename, (int)0);
        if (object == null || image == null) {
            System.err.println("Can not load " + objectFilename + " and/or " + sceneFilename);
            System.exit(-1);
        }
        IplImage objectColor = IplImage.create((int)object.width(), (int)object.height(), (int)8, (int)3);
        opencv_imgproc.cvCvtColor((CvArr)object, (CvArr)objectColor, (int)8);
        IplImage correspond = IplImage.create((int)image.width(), (int)(object.height() + image.height()), (int)8, (int)1);
        opencv_core.cvSetImageROI((IplImage)correspond, (CvRect)opencv_core.cvRect((int)0, (int)0, (int)object.width(), (int)object.height()));
        opencv_core.cvCopy((CvArr)object, (CvArr)correspond);
        opencv_core.cvSetImageROI((IplImage)correspond, (CvRect)opencv_core.cvRect((int)0, (int)object.height(), (int)correspond.width(), (int)correspond.height()));
        opencv_core.cvCopy((CvArr)image, (CvArr)correspond);
        opencv_core.cvResetImageROI((IplImage)correspond);
        Settings settings = new Settings();
        settings.objectImage = object;
        settings.useFLANN = true;
        settings.ransacReprojThreshold = 5.0;
        ObjectFinder finder = new ObjectFinder(settings);
        long start = System.currentTimeMillis();
        double[] dst_corners = finder.find(image);
        System.out.println("Finding time = " + (System.currentTimeMillis() - start) + " ms");
        if (dst_corners != null) {
            for (i2 = 0; i2 < 4; ++i2) {
                int j2 = (i2 + 1) % 4;
                int x1 = (int)Math.round(dst_corners[2 * i2]);
                int y1 = (int)Math.round(dst_corners[2 * i2 + 1]);
                int x2 = (int)Math.round(dst_corners[2 * j2]);
                int y2 = (int)Math.round(dst_corners[2 * j2 + 1]);
                opencv_imgproc.line((Mat)opencv_core.cvarrToMat((CvArr)correspond), (Point)new Point(x1, y1 + object.height()), (Point)new Point(x2, y2 + object.height()), (Scalar)Scalar.WHITE, (int)1, (int)8, (int)0);
            }
        }
        for (i2 = 0; i2 < finder.ptpairs.size(); i2 += 2) {
            Point2f pt1 = finder.objectKeypoints.get((long)finder.ptpairs.get(i2).intValue()).pt();
            Point2f pt2 = finder.imageKeypoints.get((long)finder.ptpairs.get(i2 + 1).intValue()).pt();
            opencv_imgproc.line((Mat)opencv_core.cvarrToMat((CvArr)correspond), (Point)new Point(Math.round(pt1.x()), Math.round(pt1.y())), (Point)new Point(Math.round(pt2.x()), Math.round(pt2.y() + (float)object.height())), (Scalar)Scalar.WHITE, (int)1, (int)8, (int)0);
        }
        CanvasFrame objectFrame = new CanvasFrame("Object");
        CanvasFrame correspondFrame = new CanvasFrame("Object Correspond");
        OpenCVFrameConverter.ToIplImage converter = new OpenCVFrameConverter.ToIplImage();
        correspondFrame.showImage(((OpenCVFrameConverter)converter).convert(correspond));
        int i3 = 0;
        while ((long)i3 < finder.objectKeypoints.size()) {
            KeyPoint r2 = finder.objectKeypoints.get((long)i3);
            Point center = new Point(Math.round(r2.pt().x()), Math.round(r2.pt().y()));
            int radius = Math.round(r2.size() / 2.0f);
            opencv_imgproc.circle((Mat)opencv_core.cvarrToMat((CvArr)objectColor), (Point)center, (int)radius, (Scalar)Scalar.RED, (int)1, (int)8, (int)0);
            ++i3;
        }
        objectFrame.showImage(((OpenCVFrameConverter)converter).convert(objectColor));
        objectFrame.waitKey();
        objectFrame.dispose();
        correspondFrame.dispose();
    }

    static {
        for (int i2 = 0; i2 < bits.length; ++i2) {
            for (int j2 = i2; j2 != 0; j2 >>= 1) {
                int n2 = i2;
                bits[n2] = bits[n2] + (j2 & 1);
            }
        }
    }

    public static class Settings
    extends BaseChildSettings {
        IplImage objectImage = null;
        AKAZE detector = AKAZE.create();
        double distanceThreshold = 0.75;
        int matchesMin = 4;
        double ransacReprojThreshold = 1.0;
        boolean useFLANN = false;

        public IplImage getObjectImage() {
            return this.objectImage;
        }

        public void setObjectImage(IplImage objectImage) {
            this.objectImage = objectImage;
        }

        public int getDescriptorType() {
            return this.detector.getDescriptorType();
        }

        public void setDescriptorType(int dtype) {
            this.detector.setDescriptorType(dtype);
        }

        public int getDescriptorSize() {
            return this.detector.getDescriptorSize();
        }

        public void setDescriptorSize(int dsize) {
            this.detector.setDescriptorSize(dsize);
        }

        public int getDescriptorChannels() {
            return this.detector.getDescriptorChannels();
        }

        public void setDescriptorChannels(int dch) {
            this.detector.setDescriptorChannels(dch);
        }

        public double getThreshold() {
            return this.detector.getThreshold();
        }

        public void setThreshold(double threshold) {
            this.detector.setThreshold(threshold);
        }

        public int getNOctaves() {
            return this.detector.getNOctaves();
        }

        public void setNOctaves(int nOctaves) {
            this.detector.setNOctaves(nOctaves);
        }

        public int getNOctaveLayers() {
            return this.detector.getNOctaveLayers();
        }

        public void setNOctaveLayers(int nOctaveLayers) {
            this.detector.setNOctaveLayers(nOctaveLayers);
        }

        public double getDistanceThreshold() {
            return this.distanceThreshold;
        }

        public void setDistanceThreshold(double distanceThreshold) {
            this.distanceThreshold = distanceThreshold;
        }

        public int getMatchesMin() {
            return this.matchesMin;
        }

        public void setMatchesMin(int matchesMin) {
            this.matchesMin = matchesMin;
        }

        public double getRansacReprojThreshold() {
            return this.ransacReprojThreshold;
        }

        public void setRansacReprojThreshold(double ransacReprojThreshold) {
            this.ransacReprojThreshold = ransacReprojThreshold;
        }

        public boolean isUseFLANN() {
            return this.useFLANN;
        }

        public void setUseFLANN(boolean useFLANN) {
            this.useFLANN = useFLANN;
        }
    }
}

