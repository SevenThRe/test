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

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.logging.Logger;
import org.bytedeco.javacv.CameraDevice;
import org.bytedeco.javacv.GNImageAligner;
import org.bytedeco.javacv.JavaCV;
import org.bytedeco.javacv.ProCamTransformer;
import org.bytedeco.javacv.ProjectorDevice;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;

public class ReflectanceInitializer {
    private static ThreadLocal<CvMat> mat3x1 = CvMat.createThreadLocal((int)3, (int)1);
    private static ThreadLocal<CvMat> mat3x3 = CvMat.createThreadLocal((int)3, (int)3);
    private static ThreadLocal<CvMat> mat4x4 = CvMat.createThreadLocal((int)4, (int)4);
    private GNImageAligner.Settings alignerSettings;
    private int smoothingSize;
    private double reflectanceMin;
    private CameraDevice cameraDevice;
    private ProjectorDevice projectorDevice;
    private IplImage[] projectorImages;

    public ReflectanceInitializer(CameraDevice cameraDevice, ProjectorDevice projectorDevice, int channels, GNImageAligner.Settings alignerSettings) {
        this(cameraDevice, projectorDevice, channels, alignerSettings, 51, 0.01);
    }

    public ReflectanceInitializer(CameraDevice cameraDevice, ProjectorDevice projectorDevice, int channels, GNImageAligner.Settings alignerSettings, int smoothingSize, double reflectanceMin) {
        this.alignerSettings = alignerSettings;
        this.smoothingSize = smoothingSize;
        this.reflectanceMin = reflectanceMin;
        this.cameraDevice = cameraDevice;
        this.projectorDevice = projectorDevice;
        this.projectorImages = new IplImage[3];
        for (int i2 = 0; i2 < this.projectorImages.length; ++i2) {
            this.projectorImages[i2] = IplImage.create((int)projectorDevice.imageWidth, (int)projectorDevice.imageHeight, (int)32, (int)channels);
        }
        opencv_core.cvSetZero((CvArr)this.projectorImages[0]);
        opencv_core.cvSet((CvArr)this.projectorImages[1], (CvScalar)CvScalar.ONE);
        CvMat H = mat3x3.get();
        projectorDevice.getRectifyingHomography(cameraDevice, H);
        JavaCV.fractalTriangleWave(this.projectorImages[2], H);
    }

    public IplImage[] getProjectorImages() {
        return this.projectorImages;
    }

    public IplImage initializeReflectance(IplImage[] cameraImages, IplImage reflectance, double[] roiPts, double[] gainAmbientLight) {
        CvMat invp;
        int w2 = cameraImages[0].width();
        int h2 = cameraImages[0].height();
        int channels = cameraImages[0].nChannels();
        IplImage mask = IplImage.create((int)w2, (int)h2, (int)8, (int)1);
        opencv_core.cvSetZero((CvArr)mask);
        opencv_imgproc.cvFillConvexPoly((CvArr)mask, (CvPoint)new CvPoint((long)(roiPts.length / 2)).put((byte)(16 - this.cameraDevice.getMapsPyramidLevel()), roiPts), (int)4, (CvScalar)CvScalar.WHITE, (int)8, (int)16);
        IplImage float1 = cameraImages[0];
        IplImage float2 = cameraImages[1];
        opencv_core.cvCopy((CvArr)float2, (CvArr)reflectance);
        opencv_imgproc.cvSmooth((CvArr)float1, (CvArr)float1, (int)2, (int)this.smoothingSize, (int)0, (double)0.0, (double)0.0);
        opencv_imgproc.cvSmooth((CvArr)float2, (CvArr)float2, (int)2, (int)this.smoothingSize, (int)0, (double)0.0, (double)0.0);
        opencv_core.cvSub((CvArr)float2, (CvArr)float1, (CvArr)float2, null);
        CvMat p2 = mat3x1.get();
        p2.put(new double[]{1.0, 1.0, 1.0});
        opencv_core.cvMatMul((CvArr)this.projectorDevice.colorMixingMatrix, (CvArr)p2, (CvArr)p2);
        if (float2.nChannels() == 4) {
            invp = mat4x4.get();
            invp.put(new double[]{1.0 / p2.get(0), 0.0, 0.0, 0.0, 0.0, 1.0 / p2.get(1), 0.0, 0.0, 0.0, 0.0, 1.0 / p2.get(2), 0.0, 0.0, 0.0, 0.0, 1.0});
        } else {
            invp = mat3x3.get();
            invp.put(new double[]{1.0 / p2.get(0), 0.0, 0.0, 0.0, 1.0 / p2.get(1), 0.0, 0.0, 0.0, 1.0 / p2.get(2)});
        }
        opencv_core.cvTransform((CvArr)float2, (CvArr)float2, (CvMat)invp, null);
        FloatBuffer fb1 = float1.getFloatBuffer();
        FloatBuffer fb2 = float2.getFloatBuffer();
        ByteBuffer mb2 = mask.getByteBuffer();
        assert (fb1.capacity() == fb2.capacity() / 3);
        assert (fb1.capacity() == mb2.capacity() / 3);
        int[] nPixels = new int[channels];
        int i2 = 0;
        for (int j2 = 0; j2 < fb1.capacity(); j2 += channels) {
            for (int z2 = 0; z2 < channels; ++z2) {
                float ra2 = fb1.get(j2 + z2);
                float r2 = fb2.get(j2 + z2);
                float a2 = r2 == 0.0f ? 0.0f : ra2 / r2;
                fb1.put(j2 + z2, a2);
                if (mb2.get(i2) == 0 || !((double)r2 > this.reflectanceMin)) continue;
                int n2 = z2;
                nPixels[n2] = nPixels[n2] + 1;
                int n3 = z2 + 1;
                gainAmbientLight[n3] = gainAmbientLight[n3] + (double)a2;
            }
            ++i2;
        }
        gainAmbientLight[0] = 1.0;
        for (int z3 = 0; z3 < gainAmbientLight.length - 1; ++z3) {
            gainAmbientLight[z3 + 1] = nPixels[z3] == 0 ? 0.0 : gainAmbientLight[z3 + 1] / (double)nPixels[z3];
        }
        opencv_core.cvAddS((CvArr)float1, (CvScalar)opencv_core.cvScalar((double)p2.get(0), (double)p2.get(1), (double)p2.get(2), (double)0.0), (CvArr)float1, null);
        opencv_core.cvDiv((CvArr)reflectance, (CvArr)float1, (CvArr)reflectance, (double)1.0);
        opencv_core.cvNot((CvArr)mask, (CvArr)mask);
        opencv_imgproc.cvErode((CvArr)mask, (CvArr)mask, null, (int)15);
        opencv_core.cvSet((CvArr)reflectance, (CvScalar)CvScalar.ZERO, (CvArr)mask);
        return reflectance;
    }

    public CvMat initializePlaneParameters(IplImage reflectance, IplImage cameraImage, double[] referencePoints, double[] roiPts, double[] gainAmbientLight) {
        int iterations;
        ProCamTransformer transformer = new ProCamTransformer(referencePoints, this.cameraDevice, this.projectorDevice, null);
        transformer.setProjectorImage(this.projectorImages[2], 0, this.alignerSettings.pyramidLevelMax);
        ProCamTransformer.Parameters parameters = transformer.createParameters();
        int gainAmbientLightStart = parameters.size() - gainAmbientLight.length;
        int gainAmbientLightEnd = parameters.size();
        for (int i2 = gainAmbientLightStart; i2 < gainAmbientLightEnd; ++i2) {
            parameters.set(i2, gainAmbientLight[i2 - gainAmbientLightStart]);
        }
        GNImageAligner aligner = new GNImageAligner(transformer, parameters, reflectance, roiPts, cameraImage, this.alignerSettings);
        double[] delta = new double[parameters.size() + 1];
        boolean converged = false;
        long iterationsStartTime = System.currentTimeMillis();
        for (iterations = 0; !converged && iterations < 100; ++iterations) {
            converged = aligner.iterate(delta);
        }
        parameters = (ProCamTransformer.Parameters)aligner.getParameters();
        Logger.getLogger(ReflectanceInitializer.class.getName()).info("iteratingTime = " + (System.currentTimeMillis() - iterationsStartTime) + "  iterations = " + iterations + "  objectiveRMSE = " + (float)aligner.getRMSE());
        return parameters.getN0();
    }
}

