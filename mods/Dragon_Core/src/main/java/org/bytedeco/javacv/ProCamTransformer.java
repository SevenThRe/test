/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.cvkernels$KernelData
 *  org.bytedeco.opencv.global.opencv_calib3d
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_imgproc
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.CvRect
 *  org.bytedeco.opencv.opencv_core.CvScalar
 *  org.bytedeco.opencv.opencv_core.IplImage
 *  org.bytedeco.opencv.opencv_core.IplROI
 *  org.bytedeco.opencv.opencv_core.Mat
 */
package org.bytedeco.javacv;

import org.bytedeco.javacv.CameraDevice;
import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.JavaCV;
import org.bytedeco.javacv.ProjectiveColorTransformer;
import org.bytedeco.javacv.ProjectorDevice;
import org.bytedeco.javacv.cvkernels;
import org.bytedeco.opencv.cvkernels;
import org.bytedeco.opencv.global.opencv_calib3d;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.IplROI;
import org.bytedeco.opencv.opencv_core.Mat;

public class ProCamTransformer
implements ImageTransformer {
    protected CameraDevice camera = null;
    protected ProjectorDevice projector = null;
    protected ProjectiveColorTransformer surfaceTransformer = null;
    protected ProjectiveColorTransformer projectorTransformer = null;
    protected IplImage[] projectorImage = null;
    protected IplImage[] surfaceImage = null;
    protected CvScalar fillColor = opencv_core.cvScalar((double)0.0, (double)0.0, (double)0.0, (double)1.0);
    protected CvRect roi = new CvRect();
    protected CvMat frontoParallelH = null;
    protected CvMat invFrontoParallelH = null;
    protected CvMat invCameraMatrix = null;
    protected cvkernels.KernelData kernelData = null;
    protected CvMat[] H1 = null;
    protected CvMat[] H2 = null;
    protected CvMat[] X = null;

    public ProCamTransformer(double[] referencePoints, CameraDevice camera, ProjectorDevice projector) {
        this(referencePoints, camera, projector, null);
    }

    public ProCamTransformer(double[] referencePoints, CameraDevice camera, ProjectorDevice projector, CvMat n2) {
        this.camera = camera;
        this.projector = projector;
        if (referencePoints != null) {
            this.surfaceTransformer = new ProjectiveColorTransformer(camera.cameraMatrix, camera.cameraMatrix, null, null, n2, referencePoints, null, null, 3, 0);
        }
        double[] referencePoints1 = new double[]{0.0, 0.0, camera.imageWidth / 2, camera.imageHeight, camera.imageWidth, 0.0};
        double[] referencePoints2 = new double[]{0.0, 0.0, projector.imageWidth / 2, projector.imageHeight, projector.imageWidth, 0.0};
        if (n2 != null) {
            this.invCameraMatrix = CvMat.create((int)3, (int)3);
            opencv_core.cvInvert((CvArr)camera.cameraMatrix, (CvArr)this.invCameraMatrix);
            JavaCV.perspectiveTransform(referencePoints2, referencePoints1, this.invCameraMatrix, projector.cameraMatrix, projector.R, projector.T, n2, true);
        }
        this.projectorTransformer = new ProjectiveColorTransformer(camera.cameraMatrix, projector.cameraMatrix, projector.R, projector.T, null, referencePoints1, referencePoints2, projector.colorMixingMatrix, 1, 3);
        if (referencePoints != null && n2 != null) {
            this.frontoParallelH = camera.getFrontoParallelH(referencePoints, n2, CvMat.create((int)3, (int)3));
            this.invFrontoParallelH = this.frontoParallelH.clone();
            opencv_core.cvInvert((CvArr)this.frontoParallelH, (CvArr)this.invFrontoParallelH);
        }
    }

    public int getNumGains() {
        return this.projectorTransformer.getNumGains();
    }

    public int getNumBiases() {
        return this.projectorTransformer.getNumBiases();
    }

    public CvScalar getFillColor() {
        return this.fillColor;
    }

    public void setFillColor(CvScalar fillColor) {
        this.fillColor = fillColor;
    }

    public ProjectiveColorTransformer getSurfaceTransformer() {
        return this.surfaceTransformer;
    }

    public ProjectiveColorTransformer getProjectorTransformer() {
        return this.projectorTransformer;
    }

    public IplImage getProjectorImage(int pyramidLevel) {
        return this.projectorImage[pyramidLevel];
    }

    public void setProjectorImage(IplImage projectorImage0, int minLevel, int maxLevel) {
        this.setProjectorImage(projectorImage0, minLevel, maxLevel, true);
    }

    public void setProjectorImage(IplImage projectorImage0, int minLevel, int maxLevel, boolean convertToFloat) {
        if (this.projectorImage == null || this.projectorImage.length != maxLevel + 1) {
            this.projectorImage = new IplImage[maxLevel + 1];
        }
        if (projectorImage0.depth() == 32 || !convertToFloat) {
            this.projectorImage[minLevel] = projectorImage0;
        } else {
            IplROI ir2;
            if (this.projectorImage[minLevel] == null) {
                this.projectorImage[minLevel] = IplImage.create((int)projectorImage0.width(), (int)projectorImage0.height(), (int)32, (int)projectorImage0.nChannels(), (int)projectorImage0.origin());
            }
            if ((ir2 = projectorImage0.roi()) != null) {
                int align = 1 << maxLevel + 1;
                this.roi.x(Math.max(0, (int)Math.floor((double)ir2.xOffset() / (double)align) * align));
                this.roi.y(Math.max(0, (int)Math.floor((double)ir2.yOffset() / (double)align) * align));
                this.roi.width(Math.min(projectorImage0.width(), (int)Math.ceil((double)ir2.width() / (double)align) * align));
                this.roi.height(Math.min(projectorImage0.height(), (int)Math.ceil((double)ir2.height() / (double)align) * align));
                opencv_core.cvSetImageROI((IplImage)projectorImage0, (CvRect)this.roi);
                opencv_core.cvSetImageROI((IplImage)this.projectorImage[minLevel], (CvRect)this.roi);
            } else {
                opencv_core.cvResetImageROI((IplImage)projectorImage0);
                opencv_core.cvResetImageROI((IplImage)this.projectorImage[minLevel]);
            }
            opencv_core.cvConvertScale((CvArr)projectorImage0, (CvArr)this.projectorImage[minLevel], (double)0.00392156862745098, (double)0.0);
        }
        for (int i2 = minLevel + 1; i2 <= maxLevel; ++i2) {
            IplROI ir3;
            int w2 = this.projectorImage[i2 - 1].width() / 2;
            int h2 = this.projectorImage[i2 - 1].height() / 2;
            int d2 = this.projectorImage[i2 - 1].depth();
            int c2 = this.projectorImage[i2 - 1].nChannels();
            int o2 = this.projectorImage[i2 - 1].origin();
            if (this.projectorImage[i2] == null) {
                this.projectorImage[i2] = IplImage.create((int)w2, (int)h2, (int)d2, (int)c2, (int)o2);
            }
            if ((ir3 = this.projectorImage[i2 - 1].roi()) != null) {
                this.roi.x(ir3.xOffset() / 2);
                this.roi.width(ir3.width() / 2);
                this.roi.y(ir3.yOffset() / 2);
                this.roi.height(ir3.height() / 2);
                opencv_core.cvSetImageROI((IplImage)this.projectorImage[i2], (CvRect)this.roi);
            } else {
                opencv_core.cvResetImageROI((IplImage)this.projectorImage[i2]);
            }
            opencv_imgproc.cvPyrDown((CvArr)this.projectorImage[i2 - 1], (CvArr)this.projectorImage[i2], (int)7);
            opencv_core.cvResetImageROI((IplImage)this.projectorImage[i2 - 1]);
        }
    }

    public IplImage getSurfaceImage(int pyramidLevel) {
        return this.surfaceImage[pyramidLevel];
    }

    public void setSurfaceImage(IplImage surfaceImage0, int pyramidLevels) {
        if (this.surfaceImage == null || this.surfaceImage.length != pyramidLevels) {
            this.surfaceImage = new IplImage[pyramidLevels];
        }
        this.surfaceImage[0] = surfaceImage0;
        opencv_core.cvResetImageROI((IplImage)surfaceImage0);
        for (int i2 = 1; i2 < pyramidLevels; ++i2) {
            int w2 = this.surfaceImage[i2 - 1].width() / 2;
            int h2 = this.surfaceImage[i2 - 1].height() / 2;
            int d2 = this.surfaceImage[i2 - 1].depth();
            int c2 = this.surfaceImage[i2 - 1].nChannels();
            int o2 = this.surfaceImage[i2 - 1].origin();
            if (this.surfaceImage[i2] == null) {
                this.surfaceImage[i2] = IplImage.create((int)w2, (int)h2, (int)d2, (int)c2, (int)o2);
            } else {
                opencv_core.cvResetImageROI((IplImage)this.surfaceImage[i2]);
            }
            opencv_imgproc.cvPyrDown((CvArr)this.surfaceImage[i2 - 1], (CvArr)this.surfaceImage[i2], (int)7);
        }
    }

    protected void prepareTransforms(CvMat H1, CvMat H2, CvMat X, int pyramidLevel, Parameters p2) {
        ProjectiveColorTransformer.Parameters cameraParameters = p2.getSurfaceParameters();
        ProjectiveColorTransformer.Parameters projectorParameters = p2.getProjectorParameters();
        if (this.surfaceTransformer != null) {
            opencv_core.cvInvert((CvArr)cameraParameters.getH(), (CvArr)H1);
        }
        opencv_core.cvInvert((CvArr)projectorParameters.getH(), (CvArr)H2);
        if (pyramidLevel > 0) {
            int scale = 1 << pyramidLevel;
            if (this.surfaceTransformer != null) {
                H1.put(2, H1.get(2) / (double)scale);
                H1.put(5, H1.get(5) / (double)scale);
                H1.put(6, H1.get(6) * (double)scale);
                H1.put(7, H1.get(7) * (double)scale);
            }
            H2.put(2, H2.get(2) / (double)scale);
            H2.put(5, H2.get(5) / (double)scale);
            H2.put(6, H2.get(6) * (double)scale);
            H2.put(7, H2.get(7) * (double)scale);
        }
        double[] x2 = this.projector.colorMixingMatrix.get();
        double[] a2 = projectorParameters.getColorParameters();
        double a22 = a2[0];
        X.put(new double[]{a22 * x2[0], a22 * x2[1], a22 * x2[2], a2[1], a22 * x2[3], a22 * x2[4], a22 * x2[5], a2[2], a22 * x2[6], a22 * x2[7], a22 * x2[8], a2[3], 0.0, 0.0, 0.0, 1.0});
    }

    public void transform(IplImage srcImage, IplImage dstImage, CvRect roi, int pyramidLevel, ImageTransformer.Parameters parameters, boolean inverse) {
        if (inverse) {
            throw new UnsupportedOperationException("Inverse transform not supported.");
        }
        Parameters p2 = (Parameters)parameters;
        ProjectiveColorTransformer.Parameters cameraParameters = p2.getSurfaceParameters();
        ProjectiveColorTransformer.Parameters projectorParameters = p2.getProjectorParameters();
        if (p2.tempImage == null || p2.tempImage.length <= pyramidLevel) {
            Parameters.access$002(p2, new IplImage[pyramidLevel + 1]);
        }
        ((Parameters)p2).tempImage[pyramidLevel] = IplImage.createIfNotCompatible((IplImage)p2.tempImage[pyramidLevel], (IplImage)dstImage);
        if (roi == null) {
            opencv_core.cvResetImageROI((IplImage)p2.tempImage[pyramidLevel]);
        } else {
            opencv_core.cvSetImageROI((IplImage)p2.tempImage[pyramidLevel], (CvRect)roi);
        }
        if (this.surfaceTransformer != null) {
            this.surfaceTransformer.transform(srcImage, p2.tempImage[pyramidLevel], roi, pyramidLevel, cameraParameters, false);
        }
        this.projectorTransformer.transform(this.projectorImage[pyramidLevel], dstImage, roi, pyramidLevel, projectorParameters, false);
        if (this.surfaceTransformer != null) {
            opencv_core.cvMul((CvArr)dstImage, (CvArr)p2.tempImage[pyramidLevel], (CvArr)dstImage, (double)(1.0 / dstImage.highValue()));
        } else {
            opencv_core.cvCopy((CvArr)p2.tempImage[pyramidLevel], (CvArr)dstImage);
        }
    }

    @Override
    public void transform(CvMat srcPts, CvMat dstPts, ImageTransformer.Parameters parameters, boolean inverse) {
        if (this.surfaceTransformer != null) {
            this.surfaceTransformer.transform(srcPts, dstPts, ((Parameters)parameters).surfaceParameters, inverse);
        } else if (dstPts != srcPts) {
            dstPts.put(srcPts);
        }
    }

    @Override
    public void transform(ImageTransformer.Data[] data, CvRect roi, ImageTransformer.Parameters[] parameters, boolean[] inverses) {
        int i2;
        assert (data.length == parameters.length);
        if (this.kernelData == null || this.kernelData.capacity() < (long)data.length) {
            this.kernelData = new cvkernels.KernelData((long)data.length);
        }
        if ((this.H1 == null || this.H1.length < data.length) && this.surfaceTransformer != null) {
            this.H1 = new CvMat[data.length];
            for (i2 = 0; i2 < this.H1.length; ++i2) {
                this.H1[i2] = CvMat.create((int)3, (int)3);
            }
        }
        if (this.H2 == null || this.H2.length < data.length) {
            this.H2 = new CvMat[data.length];
            for (i2 = 0; i2 < this.H2.length; ++i2) {
                this.H2[i2] = CvMat.create((int)3, (int)3);
            }
        }
        if (this.X == null || this.X.length < data.length) {
            this.X = new CvMat[data.length];
            for (i2 = 0; i2 < this.X.length; ++i2) {
                this.X[i2] = CvMat.create((int)4, (int)4);
            }
        }
        for (i2 = 0; i2 < data.length; ++i2) {
            this.kernelData.position((long)i2);
            this.kernelData.srcImg(this.projectorImage[data[i2].pyramidLevel]);
            this.kernelData.srcImg2(this.surfaceTransformer == null ? null : data[i2].srcImg);
            this.kernelData.subImg(data[i2].subImg);
            this.kernelData.srcDotImg(data[i2].srcDotImg);
            this.kernelData.mask(data[i2].mask);
            this.kernelData.zeroThreshold(data[i2].zeroThreshold);
            this.kernelData.outlierThreshold(data[i2].outlierThreshold);
            if (inverses != null && inverses[i2]) {
                throw new UnsupportedOperationException("Inverse transform not supported.");
            }
            this.prepareTransforms(this.surfaceTransformer == null ? null : this.H1[i2], this.H2[i2], this.X[i2], data[i2].pyramidLevel, (Parameters)parameters[i2]);
            this.kernelData.H1(this.H2[i2]);
            this.kernelData.H2(this.surfaceTransformer == null ? null : this.H1[i2]);
            this.kernelData.X(this.X[i2]);
            this.kernelData.transImg(data[i2].transImg);
            this.kernelData.dstImg(data[i2].dstImg);
            this.kernelData.dstDstDot(data[i2].dstDstDot);
        }
        long fullCapacity = this.kernelData.capacity();
        this.kernelData.capacity((long)data.length);
        cvkernels.multiWarpColorTransform(this.kernelData, roi, this.getFillColor());
        this.kernelData.capacity(fullCapacity);
        for (int i3 = 0; i3 < data.length; ++i3) {
            this.kernelData.position((long)i3);
            data[i3].dstCount = this.kernelData.dstCount();
            data[i3].dstCountZero = this.kernelData.dstCountZero();
            data[i3].dstCountOutlier = this.kernelData.dstCountOutlier();
            data[i3].srcDstDot = this.kernelData.srcDstDot();
        }
    }

    @Override
    public Parameters createParameters() {
        return new Parameters();
    }

    public class Parameters
    implements ImageTransformer.Parameters {
        private ProjectiveColorTransformer.Parameters surfaceParameters = null;
        private ProjectiveColorTransformer.Parameters projectorParameters = null;
        private IplImage[] tempImage = null;
        private CvMat H = CvMat.create((int)3, (int)3);
        private CvMat R = CvMat.create((int)3, (int)3);
        private CvMat n = CvMat.create((int)3, (int)1);
        private CvMat t = CvMat.create((int)3, (int)1);

        protected Parameters() {
            this.reset(false);
        }

        protected Parameters(ProjectiveColorTransformer.Parameters surfaceParameters, ProjectiveColorTransformer.Parameters projectorParameters) {
            this.reset(surfaceParameters, projectorParameters);
        }

        public ProjectiveColorTransformer.Parameters getSurfaceParameters() {
            return this.surfaceParameters;
        }

        public ProjectiveColorTransformer.Parameters getProjectorParameters() {
            return this.projectorParameters;
        }

        private int getSizeForSurface() {
            return ProCamTransformer.this.surfaceTransformer == null ? 0 : this.surfaceParameters.size() - ProCamTransformer.this.surfaceTransformer.getNumGains() - ProCamTransformer.this.surfaceTransformer.getNumBiases();
        }

        private int getSizeForProjector() {
            return this.projectorParameters.size();
        }

        @Override
        public int size() {
            return this.getSizeForSurface() + this.getSizeForProjector();
        }

        @Override
        public double[] get() {
            double[] p2 = new double[this.size()];
            for (int i2 = 0; i2 < p2.length; ++i2) {
                p2[i2] = this.get(i2);
            }
            return p2;
        }

        @Override
        public double get(int i2) {
            if (i2 < this.getSizeForSurface()) {
                return this.surfaceParameters.get(i2);
            }
            return this.projectorParameters.get(i2 - this.getSizeForSurface());
        }

        @Override
        public void set(double ... p2) {
            for (int i2 = 0; i2 < p2.length; ++i2) {
                this.set(i2, p2[i2]);
            }
        }

        @Override
        public void set(int i2, double p2) {
            if (i2 < this.getSizeForSurface()) {
                this.surfaceParameters.set(i2, p2);
            } else {
                this.projectorParameters.set(i2 - this.getSizeForSurface(), p2);
            }
        }

        @Override
        public void set(ImageTransformer.Parameters p2) {
            Parameters pcp = (Parameters)p2;
            if (ProCamTransformer.this.surfaceTransformer != null) {
                this.surfaceParameters.set(pcp.getSurfaceParameters());
                this.surfaceParameters.resetColor(false);
            }
            this.projectorParameters.set(pcp.getProjectorParameters());
        }

        @Override
        public void reset(boolean asIdentity) {
            this.reset(null, null);
        }

        public void reset(ProjectiveColorTransformer.Parameters surfaceParameters, ProjectiveColorTransformer.Parameters projectorParameters) {
            if (surfaceParameters == null && ProCamTransformer.this.surfaceTransformer != null) {
                surfaceParameters = ProCamTransformer.this.surfaceTransformer.createParameters();
            }
            if (projectorParameters == null) {
                projectorParameters = ProCamTransformer.this.projectorTransformer.createParameters();
            }
            this.surfaceParameters = surfaceParameters;
            this.projectorParameters = projectorParameters;
            this.setSubspace(this.getSubspace());
        }

        @Override
        public double getConstraintError() {
            double error = ProCamTransformer.this.surfaceTransformer == null ? 0.0 : this.surfaceParameters.getConstraintError();
            this.projectorParameters.update();
            return error;
        }

        @Override
        public void compose(ImageTransformer.Parameters p1, boolean inverse1, ImageTransformer.Parameters p2, boolean inverse2) {
            throw new UnsupportedOperationException("Compose operation not supported.");
        }

        @Override
        public boolean preoptimize() {
            double[] p2 = this.setSubspaceInternal(this.getSubspaceInternal());
            if (p2 != null) {
                this.set(8, p2[8]);
                this.set(9, p2[9]);
                this.set(10, p2[10]);
                return true;
            }
            return false;
        }

        @Override
        public void setSubspace(double ... p2) {
            double[] dst = this.setSubspaceInternal(p2);
            if (dst != null) {
                this.set(dst);
            }
        }

        @Override
        public double[] getSubspace() {
            return this.getSubspaceInternal();
        }

        private double[] setSubspaceInternal(double ... p2) {
            if (ProCamTransformer.this.invFrontoParallelH == null) {
                return null;
            }
            double[] dst = new double[11];
            this.t.put(new double[]{p2[0], p2[1], p2[2]});
            opencv_calib3d.Rodrigues((Mat)opencv_core.cvarrToMat((CvArr)this.t), (Mat)opencv_core.cvarrToMat((CvArr)this.R), null);
            this.t.put(new double[]{p2[3], p2[4], p2[5]});
            this.H.put(new double[]{this.R.get(0), this.R.get(1), this.t.get(0), this.R.get(3), this.R.get(4), this.t.get(1), this.R.get(6), this.R.get(7), this.t.get(2)});
            opencv_core.cvMatMul((CvArr)this.H, (CvArr)ProCamTransformer.this.invFrontoParallelH, (CvArr)this.H);
            opencv_core.cvMatMul((CvArr)ProCamTransformer.this.surfaceTransformer.getK2(), (CvArr)this.H, (CvArr)this.H);
            opencv_core.cvMatMul((CvArr)this.H, (CvArr)ProCamTransformer.this.surfaceTransformer.getInvK1(), (CvArr)this.H);
            opencv_core.cvGEMM((CvArr)this.R, (CvArr)this.t, (double)1.0, null, (double)0.0, (CvArr)this.t, (int)1);
            double scale = 1.0 / this.t.get(2);
            this.n.put(new double[]{0.0, 0.0, 1.0});
            opencv_core.cvGEMM((CvArr)this.R, (CvArr)this.n, (double)scale, null, (double)0.0, (CvArr)this.n, (int)0);
            double[] src = ProCamTransformer.this.projectorTransformer.getReferencePoints2();
            JavaCV.perspectiveTransform(src, dst, ProCamTransformer.this.projectorTransformer.getInvK1(), ProCamTransformer.this.projectorTransformer.getK2(), ProCamTransformer.this.projectorTransformer.getR(), ProCamTransformer.this.projectorTransformer.getT(), this.n, true);
            dst[8] = dst[0];
            dst[9] = dst[2];
            dst[10] = dst[4];
            JavaCV.perspectiveTransform(ProCamTransformer.this.surfaceTransformer.getReferencePoints1(), dst, this.H);
            return dst;
        }

        private double[] getSubspaceInternal() {
            if (ProCamTransformer.this.frontoParallelH == null) {
                return null;
            }
            opencv_core.cvMatMul((CvArr)ProCamTransformer.this.surfaceTransformer.getK1(), (CvArr)ProCamTransformer.this.frontoParallelH, (CvArr)this.H);
            opencv_core.cvMatMul((CvArr)this.surfaceParameters.getH(), (CvArr)this.H, (CvArr)this.H);
            opencv_core.cvMatMul((CvArr)ProCamTransformer.this.surfaceTransformer.getInvK2(), (CvArr)this.H, (CvArr)this.H);
            JavaCV.HtoRt(this.H, this.R, this.t);
            opencv_calib3d.Rodrigues((Mat)opencv_core.cvarrToMat((CvArr)this.R), (Mat)opencv_core.cvarrToMat((CvArr)this.n), null);
            double[] p2 = new double[]{this.n.get(0), this.n.get(1), this.n.get(2), this.t.get(0), this.t.get(1), this.t.get(2)};
            return p2;
        }

        public CvMat getN() {
            double[] src = ProCamTransformer.this.projectorTransformer.getReferencePoints2();
            double[] dst = (double[])ProCamTransformer.this.projectorTransformer.getReferencePoints1().clone();
            dst[0] = this.projectorParameters.get(0);
            dst[2] = this.projectorParameters.get(1);
            dst[4] = this.projectorParameters.get(2);
            opencv_core.cvTranspose((CvArr)ProCamTransformer.this.projectorTransformer.getR(), (CvArr)this.R);
            opencv_core.cvGEMM((CvArr)this.R, (CvArr)ProCamTransformer.this.projectorTransformer.getT(), (double)-1.0, null, (double)0.0, (CvArr)this.t, (int)0);
            JavaCV.getPlaneParameters(src, dst, ProCamTransformer.this.projectorTransformer.getInvK2(), ProCamTransformer.this.projectorTransformer.getK1(), this.R, this.t, this.n);
            double d2 = 1.0 + opencv_core.cvDotProduct((CvArr)this.n, (CvArr)ProCamTransformer.this.projectorTransformer.getT());
            opencv_core.cvGEMM((CvArr)this.R, (CvArr)this.n, (double)(1.0 / d2), null, (double)0.0, (CvArr)this.n, (int)0);
            return this.n;
        }

        public CvMat getN0() {
            this.n = this.getN();
            if (ProCamTransformer.this.surfaceTransformer == null) {
                return this.n;
            }
            ProCamTransformer.this.camera.getFrontoParallelH(this.surfaceParameters.get(), this.n, this.R);
            opencv_core.cvInvert((CvArr)this.surfaceParameters.getH(), (CvArr)this.H);
            opencv_core.cvMatMul((CvArr)this.H, (CvArr)ProCamTransformer.this.surfaceTransformer.getK2(), (CvArr)this.H);
            opencv_core.cvMatMul((CvArr)this.H, (CvArr)this.R, (CvArr)this.H);
            opencv_core.cvMatMul((CvArr)ProCamTransformer.this.surfaceTransformer.getInvK1(), (CvArr)this.H, (CvArr)this.H);
            JavaCV.HtoRt(this.H, this.R, this.t);
            opencv_core.cvGEMM((CvArr)this.R, (CvArr)this.t, (double)1.0, null, (double)0.0, (CvArr)this.t, (int)1);
            double scale = 1.0 / this.t.get(2);
            this.n.put(new double[]{0.0, 0.0, 1.0});
            opencv_core.cvGEMM((CvArr)this.R, (CvArr)this.n, (double)scale, null, (double)0.0, (CvArr)this.n, (int)0);
            return this.n;
        }

        @Override
        public Parameters clone() {
            Parameters p2 = new Parameters();
            p2.surfaceParameters = this.surfaceParameters == null ? null : this.surfaceParameters.clone();
            p2.projectorParameters = this.projectorParameters.clone();
            return p2;
        }

        public String toString() {
            if (this.surfaceParameters != null) {
                return this.surfaceParameters.toString() + this.projectorParameters.toString();
            }
            return this.projectorParameters.toString();
        }

        static /* synthetic */ IplImage[] access$002(Parameters x0, IplImage[] x1) {
            x0.tempImage = x1;
            return x1;
        }
    }
}

