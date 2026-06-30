/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.artoolkitplus.global.ARToolKitPlus
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_imgproc
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.CvPoint
 *  org.bytedeco.opencv.opencv_core.CvScalar
 *  org.bytedeco.opencv.opencv_core.IplImage
 */
package org.bytedeco.javacv;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.bytedeco.artoolkitplus.global.ARToolKitPlus;
import org.bytedeco.javacv.BaseChildSettings;
import org.bytedeco.javacv.JavaCV;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

public class Marker
implements Cloneable {
    public int id;
    public double[] corners;
    public double confidence;
    private static IplImage[] imageCache = new IplImage[4096];
    private static final double[] src = new double[]{0.0, 0.0, 8.0, 0.0, 8.0, 8.0, 0.0, 8.0};
    private static ThreadLocal<CvMat> H3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> srcPts4x1 = CvMat.createThreadLocal((int)4, (int)1, (int)6, (int)2);
    private static ThreadLocal<CvMat> dstPts4x1 = CvMat.createThreadLocal((int)4, (int)1, (int)6, (int)2);

    public Marker(int id2, double[] corners, double confidence) {
        this.id = id2;
        this.corners = corners;
        this.confidence = confidence;
    }

    public Marker(int id2, double ... corners) {
        this(id2, corners, 1.0);
    }

    public Marker clone() {
        return new Marker(this.id, (double[])this.corners.clone(), this.confidence);
    }

    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + (this.corners != null ? this.corners.hashCode() : 0);
        return hash;
    }

    public boolean equals(Object o2) {
        if (o2 instanceof Marker) {
            Marker m2 = (Marker)o2;
            return m2.id == this.id && Arrays.equals(m2.corners, this.corners);
        }
        return false;
    }

    public double[] getCenter() {
        double x2 = 0.0;
        double y2 = 0.0;
        for (int i2 = 0; i2 < 4; ++i2) {
            x2 += this.corners[2 * i2];
            y2 += this.corners[2 * i2 + 1];
        }
        return new double[]{x2 /= 4.0, y2 /= 4.0};
    }

    public IplImage getImage() {
        return Marker.getImage(this.id);
    }

    public static IplImage getImage(int id2) {
        if (imageCache[id2] == null) {
            Marker.imageCache[id2] = IplImage.create((int)8, (int)8, (int)8, (int)1);
            ARToolKitPlus.createImagePatternBCH((int)id2, (ByteBuffer)imageCache[id2].getByteBuffer());
        }
        return imageCache[id2];
    }

    public void draw(IplImage image) {
        this.draw(image, CvScalar.BLACK, 1.0, null);
    }

    public void draw(IplImage image, CvScalar color, double scale, CvMat prewarp) {
        this.draw(image, color, scale, scale, prewarp);
    }

    public void draw(IplImage image, CvScalar color, double scaleX, double scaleY, CvMat prewarp) {
        CvMat H = H3x3.get();
        JavaCV.getPerspectiveTransform(src, this.corners, H);
        if (prewarp != null) {
            opencv_core.cvGEMM((CvArr)prewarp, (CvArr)H, (double)1.0, null, (double)0.0, (CvArr)H, (int)0);
        }
        IplImage marker = this.getImage();
        ByteBuffer mbuf = marker.getByteBuffer();
        CvMat srcPts = srcPts4x1.get();
        CvMat dstPts = dstPts4x1.get();
        CvPoint tempPts = new CvPoint(4L);
        int h2 = marker.height();
        int w2 = marker.width();
        for (int y2 = 0; y2 < h2; ++y2) {
            for (int x2 = 0; x2 < w2; ++x2) {
                int i2;
                if (mbuf.get(y2 * w2 + x2) != 0) continue;
                srcPts.put(new double[]{x2, y2, x2 + 1, y2, x2 + 1, y2 + 1, x2, y2 + 1});
                opencv_core.cvPerspectiveTransform((CvArr)srcPts, (CvArr)dstPts, (CvMat)H);
                double centerx = 0.0;
                double centery = 0.0;
                for (i2 = 0; i2 < 4; ++i2) {
                    centerx += dstPts.get(i2 * 2);
                    centery += dstPts.get(i2 * 2 + 1);
                }
                centerx /= 4.0;
                centery /= 4.0;
                for (i2 = 0; i2 < 4; ++i2) {
                    double a2 = dstPts.get(i2 * 2);
                    double b2 = dstPts.get(i2 * 2 + 1);
                    double dx2 = centerx - a2;
                    double dy2 = centery - b2;
                    dx2 = dx2 < 0.0 ? -1.0 : 0.0;
                    dy2 = dy2 < 0.0 ? -1.0 : 0.0;
                    tempPts.position((long)i2).x((int)Math.round((a2 * scaleX + dx2) * 65536.0));
                    tempPts.position((long)i2).y((int)Math.round((b2 * scaleY + dy2) * 65536.0));
                }
                opencv_imgproc.cvFillConvexPoly((CvArr)image, (CvPoint)tempPts.position(0L), (int)4, (CvScalar)color, (int)8, (int)16);
            }
        }
    }

    public static Marker[][] createArray(ArraySettings settings) {
        return Marker.createArray(settings, 0.0, 0.0);
    }

    public static Marker[][] createArray(ArraySettings settings, double marginx, double marginy) {
        Marker[] markers = new Marker[settings.rows * settings.columns];
        int id2 = 0;
        for (int y2 = 0; y2 < settings.rows; ++y2) {
            for (int x2 = 0; x2 < settings.columns; ++x2) {
                double sx2 = settings.sizeX / 2.0;
                double sy2 = settings.sizeY / 2.0;
                double cx2 = (double)x2 * settings.spacingX + sx2 + marginx;
                double cy2 = (double)y2 * settings.spacingY + sy2 + marginy;
                markers[id2] = new Marker(id2, new double[]{cx2 - sx2, cy2 - sy2, cx2 + sx2, cy2 - sy2, cx2 + sx2, cy2 + sy2, cx2 - sx2, cy2 + sy2}, 1.0);
                ++id2;
            }
        }
        if (!settings.checkered) {
            return new Marker[][]{markers};
        }
        Marker[] markers1 = new Marker[markers.length / 2];
        Marker[] markers2 = new Marker[markers.length / 2];
        for (int i2 = 0; i2 < markers.length; ++i2) {
            int y3;
            int x3 = i2 % settings.columns;
            if (x3 % 2 == 0 ^ (y3 = i2 / settings.columns) % 2 == 0) {
                markers2[i2 / 2] = markers[i2];
                continue;
            }
            markers1[i2 / 2] = markers[i2];
        }
        return new Marker[][]{markers2, markers1};
    }

    public static Marker[][] createArray(int rows, int columns, double sizeX, double sizeY, double spacingX, double spacingY, boolean checkered, double marginx, double marginy) {
        ArraySettings s2 = new ArraySettings();
        s2.rows = rows;
        s2.columns = columns;
        s2.sizeX = sizeX;
        s2.sizeY = sizeY;
        s2.spacingX = spacingX;
        s2.spacingY = spacingY;
        s2.checkered = checkered;
        return Marker.createArray(s2, marginx, marginy);
    }

    public static void applyWarp(Marker[] markers, CvMat warp) {
        CvMat pts = srcPts4x1.get();
        for (Marker m2 : markers) {
            opencv_core.cvPerspectiveTransform((CvArr)pts.put(m2.corners), (CvArr)pts, (CvMat)warp);
            pts.get(m2.corners);
        }
    }

    public String toString() {
        String s2 = "[" + this.id + ": (" + this.corners[0] + ", " + this.corners[1] + ") (" + this.corners[2] + ", " + this.corners[3] + ") (" + this.corners[4] + ", " + this.corners[5] + ") (" + this.corners[6] + ", " + this.corners[7] + ")]";
        return s2;
    }

    public static class ArraySettings
    extends BaseChildSettings {
        int rows = 8;
        int columns = 12;
        double sizeX = 200.0;
        double sizeY = 200.0;
        double spacingX = 300.0;
        double spacingY = 300.0;
        boolean checkered = true;

        public int getRows() {
            return this.rows;
        }

        public void setRows(int rows) {
            this.rows = rows;
            this.firePropertyChange("rows", this.rows, this.rows);
        }

        public int getColumns() {
            return this.columns;
        }

        public void setColumns(int columns) {
            this.columns = columns;
            this.firePropertyChange("columns", this.columns, this.columns);
        }

        public double getSizeX() {
            return this.sizeX;
        }

        public void setSizeX(double sizeX) {
            this.sizeX = sizeX;
            this.firePropertyChange("sizeX", this.sizeX, this.sizeX);
        }

        public double getSizeY() {
            return this.sizeY;
        }

        public void setSizeY(double sizeY) {
            this.sizeY = sizeY;
            this.firePropertyChange("sizeY", this.sizeY, this.sizeY);
        }

        public double getSpacingX() {
            return this.spacingX;
        }

        public void setSpacingX(double spacingX) {
            this.spacingX = spacingX;
            this.firePropertyChange("spacingX", this.spacingX, this.spacingX);
        }

        public double getSpacingY() {
            return this.spacingY;
        }

        public void setSpacingY(double spacingY) {
            this.spacingY = spacingY;
            this.firePropertyChange("spacingY", this.spacingY, this.spacingY);
        }

        public boolean isCheckered() {
            return this.checkered;
        }

        public void setCheckered(boolean checkered) {
            this.checkered = checkered;
            this.firePropertyChange("checkered", this.checkered, this.checkered);
        }
    }
}

