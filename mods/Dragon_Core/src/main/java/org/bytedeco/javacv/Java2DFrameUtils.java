/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.opencv_core.IplImage
 *  org.bytedeco.opencv.opencv_core.Mat
 */
package org.bytedeco.javacv;

import java.awt.image.BufferedImage;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.Mat;

public class Java2DFrameUtils {
    private static OpenCVFrameConverter.ToIplImage iplConv = new OpenCVFrameConverter.ToIplImage();
    private static OpenCVFrameConverter.ToMat matConv = new OpenCVFrameConverter.ToMat();
    private static Java2DFrameConverter biConv = new Java2DFrameConverter();

    public static BufferedImage deepCopy(BufferedImage source) {
        return Java2DFrameConverter.cloneBufferedImage(source);
    }

    public static synchronized BufferedImage toBufferedImage(IplImage src) {
        return Java2DFrameUtils.deepCopy(biConv.getBufferedImage(iplConv.convert(src).clone()));
    }

    public static synchronized BufferedImage toBufferedImage(Mat src) {
        return Java2DFrameUtils.deepCopy(biConv.getBufferedImage(matConv.convert(src).clone()));
    }

    public static synchronized BufferedImage toBufferedImage(Frame src) {
        return Java2DFrameUtils.deepCopy(biConv.getBufferedImage(src.clone()));
    }

    public static synchronized IplImage toIplImage(Mat src) {
        return iplConv.convertToIplImage(matConv.convert(src)).clone();
    }

    public static synchronized IplImage toIplImage(Frame src) {
        return iplConv.convertToIplImage(src).clone();
    }

    public static synchronized IplImage toIplImage(BufferedImage src) {
        return iplConv.convertToIplImage(biConv.convert(src)).clone();
    }

    public static synchronized Mat toMat(IplImage src) {
        return matConv.convertToMat(iplConv.convert(src).clone());
    }

    public static synchronized Mat toMat(Frame src) {
        return matConv.convertToMat(src).clone();
    }

    public static synchronized Mat toMat(BufferedImage src) {
        return matConv.convertToMat(biConv.convert(src)).clone();
    }

    public static synchronized Frame toFrame(IplImage src) {
        return iplConv.convert(src).clone();
    }

    public static synchronized Frame toFrame(Mat src) {
        return matConv.convert(src).clone();
    }

    public static synchronized Frame toFrame(BufferedImage src) {
        return biConv.convert(src).clone();
    }
}

