/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.bytedeco.opencv.global.opencv_core
 *  org.bytedeco.opencv.global.opencv_imgproc
 *  org.bytedeco.opencv.opencv_core.CvArr
 *  org.bytedeco.opencv.opencv_core.CvMat
 *  org.bytedeco.opencv.opencv_core.CvPoint
 *  org.bytedeco.opencv.opencv_core.CvRect
 *  org.bytedeco.opencv.opencv_core.CvScalar
 *  org.bytedeco.opencv.opencv_core.IplImage
 *  org.bytedeco.opencv.opencv_core.IplROI
 */
package org.bytedeco.javacv;

import java.util.Arrays;
import org.bytedeco.javacv.ImageAligner;
import org.bytedeco.javacv.ImageTransformer;
import org.bytedeco.javacv.JavaCV;
import org.bytedeco.javacv.Parallel;
import org.bytedeco.opencv.global.opencv_core;
import org.bytedeco.opencv.global.opencv_imgproc;
import org.bytedeco.opencv.opencv_core.CvArr;
import org.bytedeco.opencv.opencv_core.CvMat;
import org.bytedeco.opencv.opencv_core.CvPoint;
import org.bytedeco.opencv.opencv_core.CvRect;
import org.bytedeco.opencv.opencv_core.CvScalar;
import org.bytedeco.opencv.opencv_core.IplImage;
import org.bytedeco.opencv.opencv_core.IplROI;

public class GNImageAligner
implements ImageAligner {
    protected Settings settings;
    protected final int n;
    protected IplImage[] template;
    protected IplImage[] target;
    protected IplImage[] transformed;
    protected IplImage[] residual;
    protected IplImage[] mask;
    protected IplImage[] images = new IplImage[5];
    protected CvMat srcRoiPts;
    protected CvMat dstRoiPts;
    protected CvPoint dstRoiPtsArray;
    protected CvRect roi;
    protected CvRect temproi;
    protected ImageTransformer transformer;
    protected ImageTransformer.Data[] hessianGradientTransformerData;
    protected ImageTransformer.Data[] residualTransformerData;
    protected ImageTransformer.Parameters parameters;
    protected ImageTransformer.Parameters[] parametersArray;
    protected ImageTransformer.Parameters[] tempParameters;
    protected ImageTransformer.Parameters priorParameters;
    protected CvMat hessian;
    protected CvMat gradient;
    protected CvMat update;
    protected CvMat prior;
    protected double[] constraintGrad;
    protected double[] subspaceResidual;
    protected double[][] subspaceJacobian;
    protected double[] updateScale;
    protected boolean[] subspaceCorrelated;
    protected int pyramidLevel;
    protected double RMSE;
    protected boolean residualUpdateNeeded = true;
    protected int lastLinePosition = 0;
    protected int trials = 0;
    protected double[] subspaceParameters;
    protected double[][] tempSubspaceParameters;

    public GNImageAligner(ImageTransformer transformer, ImageTransformer.Parameters initialParameters, IplImage template0, double[] roiPts, IplImage target0) {
        this(transformer, initialParameters, template0, roiPts, target0, new Settings());
    }

    public GNImageAligner(ImageTransformer transformer, ImageTransformer.Parameters initialParameters, IplImage template0, double[] roiPts, IplImage target0, Settings settings) {
        this(transformer, initialParameters);
        int i2;
        this.setSettings(settings);
        int minLevel = settings.pyramidLevelMin;
        int maxLevel = settings.pyramidLevelMax;
        this.template = new IplImage[maxLevel + 1];
        this.target = new IplImage[maxLevel + 1];
        this.transformed = new IplImage[maxLevel + 1];
        this.residual = new IplImage[maxLevel + 1];
        this.mask = new IplImage[maxLevel + 1];
        int w2 = template0 != null ? template0.width() : target0.width();
        int h2 = template0 != null ? template0.height() : target0.height();
        int c2 = template0 != null ? template0.nChannels() : target0.nChannels();
        int o2 = template0 != null ? template0.origin() : target0.origin();
        for (i2 = minLevel; i2 <= maxLevel; ++i2) {
            this.template[i2] = i2 == minLevel && template0 != null && template0.depth() == 32 ? template0 : IplImage.create((int)w2, (int)h2, (int)32, (int)c2, (int)o2);
            this.target[i2] = i2 == minLevel && target0 != null && target0.depth() == 32 ? target0 : IplImage.create((int)w2, (int)h2, (int)32, (int)c2, (int)o2);
            this.transformed[i2] = IplImage.create((int)w2, (int)h2, (int)32, (int)c2, (int)o2);
            this.residual[i2] = IplImage.create((int)w2, (int)h2, (int)32, (int)c2, (int)o2);
            this.mask[i2] = IplImage.create((int)w2, (int)h2, (int)8, (int)1, (int)o2);
            w2 /= 2;
            h2 /= 2;
        }
        this.hessianGradientTransformerData = new ImageTransformer.Data[this.n];
        for (i2 = 0; i2 < this.n; ++i2) {
            this.hessianGradientTransformerData[i2] = new ImageTransformer.Data(this.template[this.pyramidLevel], this.transformed[this.pyramidLevel], this.residual[this.pyramidLevel], this.mask[this.pyramidLevel], 0.0, 0.0, this.pyramidLevel, null, null, this.n);
        }
        this.residualTransformerData = new ImageTransformer.Data[]{new ImageTransformer.Data(this.template[this.pyramidLevel], this.target[this.pyramidLevel], null, this.mask[this.pyramidLevel], 0.0, 0.0, this.pyramidLevel, this.transformed[this.pyramidLevel], this.residual[this.pyramidLevel], 1)};
        this.setConstrained(settings.constrained);
        this.setTemplateImage(template0, roiPts);
        this.setTargetImage(target0);
    }

    protected GNImageAligner(ImageTransformer transformer, ImageTransformer.Parameters initialParameters) {
        int i2;
        this.n = initialParameters.size();
        this.srcRoiPts = CvMat.create((int)4, (int)1, (int)6, (int)2);
        this.dstRoiPts = CvMat.create((int)4, (int)1, (int)6, (int)2);
        this.dstRoiPtsArray = new CvPoint(4L);
        this.roi = new CvRect();
        this.temproi = new CvRect();
        this.transformer = transformer;
        this.parameters = initialParameters.clone();
        this.parametersArray = new ImageTransformer.Parameters[]{this.parameters};
        this.tempParameters = new ImageTransformer.Parameters[this.n];
        for (i2 = 0; i2 < this.tempParameters.length; ++i2) {
            this.tempParameters[i2] = initialParameters.clone();
        }
        this.subspaceParameters = this.parameters.getSubspace();
        if (this.subspaceParameters != null) {
            this.tempSubspaceParameters = new double[Parallel.getNumThreads()][];
            for (i2 = 0; i2 < this.tempSubspaceParameters.length; ++i2) {
                this.tempSubspaceParameters[i2] = (double[])this.subspaceParameters.clone();
            }
        }
    }

    @Override
    public Settings getSettings() {
        return this.settings;
    }

    @Override
    public void setSettings(ImageAligner.Settings settings) {
        this.settings = (Settings)settings;
    }

    @Override
    public IplImage getTemplateImage() {
        return this.template[this.pyramidLevel];
    }

    @Override
    public void setTemplateImage(IplImage template0, double[] roiPts) {
        int minLevel = this.settings.pyramidLevelMin;
        int maxLevel = this.settings.pyramidLevelMax;
        if (roiPts == null && template0 != null) {
            int w2 = template0.width() << minLevel;
            int h2 = template0.height() << minLevel;
            this.srcRoiPts.put(new double[]{0.0, 0.0, w2, 0.0, w2, h2, 0.0, h2});
        } else if (roiPts != null) {
            this.srcRoiPts.put(roiPts);
        }
        if (template0 == null) {
            return;
        }
        if (template0.depth() == 32) {
            this.template[minLevel] = template0;
        } else {
            opencv_core.cvConvertScale((CvArr)template0, (CvArr)this.template[minLevel], (double)(1.0 / template0.highValue()), (double)0.0);
        }
        for (int i2 = minLevel + 1; i2 <= maxLevel; ++i2) {
            opencv_imgproc.cvPyrDown((CvArr)this.template[i2 - 1], (CvArr)this.template[i2], (int)7);
        }
        this.setPyramidLevel(maxLevel);
    }

    @Override
    public IplImage getTargetImage() {
        return this.target[this.pyramidLevel];
    }

    @Override
    public void setTargetImage(IplImage target0) {
        int minLevel = this.settings.pyramidLevelMin;
        int maxLevel = this.settings.pyramidLevelMax;
        if (target0 == null) {
            return;
        }
        if (target0.depth() == 32) {
            this.target[minLevel] = target0;
        }
        if (this.settings.displacementMax > 0.0) {
            this.transformer.transform(this.srcRoiPts, this.dstRoiPts, this.parameters, false);
            double[] pts = this.dstRoiPts.get();
            int i2 = 0;
            while (i2 < pts.length) {
                int n2 = i2++;
                pts[n2] = pts[n2] / (double)(1 << minLevel);
            }
            int width = this.target[minLevel].width();
            int height = this.target[minLevel].height();
            this.temproi.x(0).y(0).width(width).height(height);
            int padX = (int)Math.round(this.settings.displacementMax * (double)width);
            int padY = (int)Math.round(this.settings.displacementMax * (double)height);
            int align = 1 << maxLevel + 1;
            JavaCV.boundingRect(pts, this.temproi, padX + 3, padY + 3, align, align);
            opencv_core.cvSetImageROI((IplImage)target0, (CvRect)this.temproi);
            opencv_core.cvSetImageROI((IplImage)this.target[minLevel], (CvRect)this.temproi);
        } else {
            opencv_core.cvResetImageROI((IplImage)target0);
            opencv_core.cvResetImageROI((IplImage)this.target[minLevel]);
        }
        if (target0.depth() != 32) {
            opencv_core.cvConvertScale((CvArr)target0, (CvArr)this.target[minLevel], (double)(1.0 / target0.highValue()), (double)0.0);
            opencv_core.cvResetImageROI((IplImage)target0);
        }
        for (int i3 = minLevel + 1; i3 <= maxLevel; ++i3) {
            IplROI ir2 = this.target[i3 - 1].roi();
            if (ir2 != null) {
                this.temproi.x(ir2.xOffset() / 2);
                this.temproi.width(ir2.width() / 2);
                this.temproi.y(ir2.yOffset() / 2);
                this.temproi.height(ir2.height() / 2);
                opencv_core.cvSetImageROI((IplImage)this.target[i3], (CvRect)this.temproi);
            } else {
                opencv_core.cvResetImageROI((IplImage)this.target[i3]);
            }
            opencv_imgproc.cvPyrDown((CvArr)this.target[i3 - 1], (CvArr)this.target[i3], (int)7);
        }
        this.setPyramidLevel(maxLevel);
    }

    @Override
    public int getPyramidLevel() {
        return this.pyramidLevel;
    }

    @Override
    public void setPyramidLevel(int pyramidLevel) {
        this.pyramidLevel = pyramidLevel;
        this.residualUpdateNeeded = true;
        this.trials = 0;
    }

    public boolean isConstrained() {
        return this.settings.constrained;
    }

    public void setConstrained(boolean constrained) {
        int m2;
        if (this.settings.constrained == constrained && this.hessian != null && this.gradient != null && this.update != null) {
            return;
        }
        this.settings.constrained = constrained;
        int n2 = m2 = constrained ? this.n + 1 : this.n;
        if (this.subspaceParameters != null && this.settings.alphaSubspace != 0.0) {
            m2 += this.subspaceParameters.length;
        }
        this.hessian = CvMat.create((int)m2, (int)m2);
        this.gradient = CvMat.create((int)m2, (int)1);
        this.update = CvMat.create((int)m2, (int)1);
        this.updateScale = new double[m2];
        this.prior = CvMat.create((int)this.n, (int)1);
        this.constraintGrad = new double[this.n];
        this.subspaceResidual = new double[this.n];
        this.subspaceJacobian = new double[m2][this.n];
        this.subspaceCorrelated = new boolean[this.n];
    }

    @Override
    public ImageTransformer.Parameters getParameters() {
        return this.parameters;
    }

    @Override
    public void setParameters(ImageTransformer.Parameters parameters) {
        this.parameters.set(parameters);
        this.subspaceParameters = parameters.getSubspace();
        if (this.subspaceParameters != null && this.settings.alphaSubspace != 0.0) {
            for (int i2 = 0; i2 < this.tempSubspaceParameters.length; ++i2) {
                this.tempSubspaceParameters[i2] = (double[])this.subspaceParameters.clone();
            }
        }
        this.residualUpdateNeeded = true;
    }

    public ImageTransformer.Parameters getPriorParameters() {
        return this.priorParameters;
    }

    public void setPriorParameters(ImageTransformer.Parameters priorParameters) {
        this.priorParameters.set(priorParameters);
    }

    @Override
    public double[] getTransformedRoiPts() {
        if (this.residualUpdateNeeded) {
            this.doRoi();
            this.doResidual();
        }
        return this.dstRoiPts.get();
    }

    @Override
    public IplImage getTransformedImage() {
        if (this.residualUpdateNeeded) {
            this.doRoi();
            this.doResidual();
        }
        return this.transformed[this.pyramidLevel];
    }

    @Override
    public IplImage getResidualImage() {
        if (this.residualUpdateNeeded) {
            this.doRoi();
            this.doResidual();
        }
        return this.residual[this.pyramidLevel];
    }

    @Override
    public IplImage getMaskImage() {
        return this.mask[this.pyramidLevel];
    }

    @Override
    public double getRMSE() {
        if (this.residualUpdateNeeded) {
            this.doRoi();
            this.doResidual();
        }
        return this.RMSE;
    }

    public int getPixelCount() {
        if (this.residualUpdateNeeded) {
            this.doRoi();
            this.doResidual();
        }
        return this.residualTransformerData[0].dstCount;
    }

    public int getOutlierCount() {
        return this.hessianGradientTransformerData[0].dstCountOutlier;
    }

    @Override
    public CvRect getRoi() {
        if (this.residualUpdateNeeded) {
            this.doRoi();
        }
        return this.roi;
    }

    public int getLastLinePosition() {
        return this.lastLinePosition;
    }

    @Override
    public IplImage[] getImages() {
        this.images[0] = this.getTemplateImage();
        this.images[1] = this.getTargetImage();
        this.images[2] = this.getTransformedImage();
        this.images[3] = this.getResidualImage();
        this.images[4] = this.getMaskImage();
        return this.images;
    }

    @Override
    public boolean iterate(double[] delta) {
        boolean invalid;
        int i2;
        double[] prevSubspaceParameters;
        boolean converged = false;
        double prevRMSE = this.getRMSE();
        double[] prevParameters = this.parameters.get();
        double[] dArray = prevSubspaceParameters = this.subspaceParameters == null ? null : (double[])this.subspaceParameters.clone();
        if (this.trials == 0 && this.parameters.preoptimize()) {
            this.setParameters(this.parameters);
            this.doResidual();
        }
        double[] resetParameters = this.parameters.get();
        double[] resetSubspaceParameters = this.subspaceParameters == null ? null : (double[])this.subspaceParameters.clone();
        this.doHessianGradient(this.updateScale);
        this.lastLinePosition = 0;
        opencv_core.cvSolve((CvArr)this.hessian, (CvArr)this.gradient, (CvArr)this.update, (int)1);
        for (i2 = 0; i2 < this.n; ++i2) {
            this.parameters.set(i2, this.parameters.get(i2) + this.settings.lineSearch[0] * this.update.get(i2) * this.updateScale[i2]);
        }
        for (i2 = this.n; i2 < this.update.length(); ++i2) {
            int n2 = i2 - this.n;
            this.subspaceParameters[n2] = this.subspaceParameters[n2] + this.settings.lineSearch[0] * this.update.get(i2) * this.updateScale[i2];
        }
        this.residualUpdateNeeded = true;
        for (int j2 = 1; j2 < this.settings.lineSearch.length && this.getRMSE() > prevRMSE; ++j2) {
            int i3;
            this.RMSE = prevRMSE;
            this.parameters.set(resetParameters);
            if (this.subspaceParameters != null) {
                System.arraycopy(resetSubspaceParameters, 0, this.subspaceParameters, 0, this.subspaceParameters.length);
            }
            this.lastLinePosition = j2;
            for (i3 = 0; i3 < this.n; ++i3) {
                this.parameters.set(i3, this.parameters.get(i3) + this.settings.lineSearch[j2] * this.update.get(i3) * this.updateScale[i3]);
            }
            for (i3 = this.n; i3 < this.update.length(); ++i3) {
                int n3 = i3 - this.n;
                this.subspaceParameters[n3] = this.subspaceParameters[n3] + this.settings.lineSearch[j2] * this.update.get(i3) * this.updateScale[i3];
            }
            this.residualUpdateNeeded = true;
        }
        double deltaNorm = 0.0;
        if (delta != null) {
            for (int i4 = 0; i4 < delta.length && i4 < this.updateScale.length; ++i4) {
                delta[i4] = this.settings.lineSearch[this.lastLinePosition] * this.update.get(i4) * this.updateScale[i4];
            }
            deltaNorm = JavaCV.norm(Arrays.copyOf(delta, this.n));
        }
        boolean bl2 = invalid = this.getRMSE() > prevRMSE || deltaNorm > this.settings.deltaMax || Double.isNaN(this.RMSE) || Double.isInfinite(this.RMSE);
        if (invalid) {
            this.RMSE = prevRMSE;
            this.parameters.set(prevParameters);
            if (this.subspaceParameters != null) {
                System.arraycopy(prevSubspaceParameters, 0, this.subspaceParameters, 0, this.subspaceParameters.length);
            }
            this.residualUpdateNeeded = true;
        }
        if (invalid && deltaNorm > this.settings.deltaMin && ++this.trials < 2) {
            return false;
        }
        if (invalid || deltaNorm < this.settings.deltaMin) {
            this.trials = 0;
            if (this.pyramidLevel > this.settings.pyramidLevelMin) {
                this.setPyramidLevel(this.pyramidLevel - 1);
            } else {
                converged = true;
            }
        } else {
            this.trials = 0;
        }
        return converged;
    }

    protected void doHessianGradient(final double[] scale) {
        ImageTransformer.Data d2;
        int i2;
        final double constraintError = this.parameters.getConstraintError();
        final double stepSize = this.settings.stepSize;
        opencv_core.cvSetZero((CvArr)this.gradient);
        opencv_core.cvSetZero((CvArr)this.hessian);
        Parallel.loop(0, this.n, new Parallel.Looper(){

            @Override
            public void loop(int from, int to2, int looperID) {
                for (int i2 = from; i2 < to2; ++i2) {
                    GNImageAligner.this.tempParameters[i2].set(GNImageAligner.this.parameters);
                    GNImageAligner.this.tempParameters[i2].set(i2, GNImageAligner.this.tempParameters[i2].get(i2) + stepSize);
                    scale[i2] = GNImageAligner.this.tempParameters[i2].get(i2) - GNImageAligner.this.parameters.get(i2);
                    GNImageAligner.this.constraintGrad[i2] = GNImageAligner.this.tempParameters[i2].getConstraintError() - constraintError;
                }
            }
        });
        for (i2 = 0; i2 < this.n; ++i2) {
            d2 = this.hessianGradientTransformerData[i2];
            d2.srcImg = this.template[this.pyramidLevel];
            d2.subImg = this.transformed[this.pyramidLevel];
            d2.srcDotImg = this.residual[this.pyramidLevel];
            d2.transImg = null;
            d2.dstImg = null;
            d2.mask = this.mask[this.pyramidLevel];
            d2.zeroThreshold = this.settings.thresholdsZero[Math.min(this.settings.thresholdsZero.length - 1, this.pyramidLevel)];
            d2.outlierThreshold = this.settings.thresholdsOutlier[Math.min(this.settings.thresholdsOutlier.length - 1, this.pyramidLevel)];
            if (this.settings.thresholdsMulRMSE) {
                d2.zeroThreshold *= this.RMSE;
                d2.outlierThreshold *= this.RMSE;
            }
            d2.pyramidLevel = this.pyramidLevel;
        }
        this.transformer.transform(this.hessianGradientTransformerData, this.roi, this.tempParameters, null);
        for (i2 = 0; i2 < this.n; ++i2) {
            d2 = this.hessianGradientTransformerData[i2];
            this.gradient.put(i2, this.gradient.get(i2) - d2.srcDstDot);
            for (int j2 = 0; j2 < this.n; ++j2) {
                this.hessian.put(i2, j2, this.hessian.get(i2, j2) + d2.dstDstDot.get(j2));
            }
        }
        this.doRegularization(this.updateScale);
    }

    protected void doRegularization(final double[] scale) {
        int i2;
        double constraintError = this.parameters.getConstraintError();
        final double stepSize = this.settings.stepSize;
        if ((this.settings.gammaTgamma != null || this.settings.alphaTikhonov != 0.0) && this.prior != null && this.priorParameters != null) {
            int i3;
            for (i3 = 0; i3 < this.n; ++i3) {
                this.prior.put(i3, this.parameters.get(i3) - this.priorParameters.get(i3));
            }
            opencv_core.cvMatMul((CvArr)this.hessian, (CvArr)this.prior, (CvArr)this.prior);
            for (i3 = 0; i3 < this.n; ++i3) {
                this.gradient.put(i3, this.gradient.get(i3) + this.prior.get(i3));
            }
        }
        if (this.settings.constrained) {
            double constraintGradSum = 0.0;
            for (double d2 : this.constraintGrad) {
                constraintGradSum += d2;
            }
            scale[this.n] = (double)this.n * constraintGradSum;
            for (i2 = 0; i2 < this.n; ++i2) {
                double c2 = this.constraintGrad[i2] * scale[this.n];
                this.hessian.put(i2, this.n, c2);
                this.hessian.put(this.n, i2, c2);
            }
            this.gradient.put(this.n, -constraintError * scale[this.n]);
        }
        if (this.subspaceParameters != null && this.subspaceParameters.length > 0 && this.settings.alphaSubspace != 0.0) {
            final int m2 = this.subspaceParameters.length;
            Arrays.fill(this.subspaceCorrelated, false);
            this.tempParameters[0].set(this.parameters);
            this.tempParameters[0].setSubspace(this.subspaceParameters);
            Parallel.loop(0, this.n + m2, this.tempSubspaceParameters.length, new Parallel.Looper(){

                @Override
                public void loop(int from, int to2, int looperID) {
                    for (int i2 = from; i2 < to2; ++i2) {
                        if (i2 < GNImageAligner.this.n) {
                            Arrays.fill(GNImageAligner.this.subspaceJacobian[i2], 0.0);
                            GNImageAligner.this.subspaceJacobian[i2][i2] = scale[i2];
                            continue;
                        }
                        System.arraycopy(GNImageAligner.this.subspaceParameters, 0, GNImageAligner.this.tempSubspaceParameters[looperID], 0, m2);
                        double[] dArray = GNImageAligner.this.tempSubspaceParameters[looperID];
                        int n2 = i2 - GNImageAligner.this.n;
                        dArray[n2] = dArray[n2] + stepSize;
                        GNImageAligner.this.tempParameters[i2 - GNImageAligner.this.n + 1].set(GNImageAligner.this.parameters);
                        GNImageAligner.this.tempParameters[i2 - GNImageAligner.this.n + 1].setSubspace(GNImageAligner.this.tempSubspaceParameters[looperID]);
                        scale[i2] = GNImageAligner.this.tempSubspaceParameters[looperID][i2 - GNImageAligner.this.n] - GNImageAligner.this.subspaceParameters[i2 - GNImageAligner.this.n];
                        for (int j2 = 0; j2 < GNImageAligner.this.n; ++j2) {
                            GNImageAligner.this.subspaceJacobian[i2][j2] = GNImageAligner.this.tempParameters[0].get(j2) - GNImageAligner.this.tempParameters[i2 - GNImageAligner.this.n + 1].get(j2);
                            int n3 = j2;
                            GNImageAligner.this.subspaceCorrelated[n3] = GNImageAligner.this.subspaceCorrelated[n3] | GNImageAligner.this.subspaceJacobian[i2][j2] != 0.0;
                        }
                    }
                }
            });
            int subspaceCorrelatedCount = 0;
            for (i2 = 0; i2 < this.n; ++i2) {
                this.subspaceResidual[i2] = this.parameters.get(i2) - this.tempParameters[0].get(i2);
                if (!this.subspaceCorrelated[i2]) continue;
                ++subspaceCorrelatedCount;
            }
            final double K = this.settings.alphaSubspace * this.settings.alphaSubspace * this.RMSE * this.RMSE / (double)subspaceCorrelatedCount;
            Parallel.loop(0, this.n + m2, new Parallel.Looper(){

                @Override
                public void loop(int from, int to2, int looperID) {
                    for (int i2 = from; i2 < to2; ++i2) {
                        if (i2 < GNImageAligner.this.n && !GNImageAligner.this.subspaceCorrelated[i2]) continue;
                        for (int j2 = i2; j2 < GNImageAligner.this.n + m2; ++j2) {
                            if (j2 < GNImageAligner.this.n && !GNImageAligner.this.subspaceCorrelated[j2]) continue;
                            double h2 = 0.0;
                            for (int k2 = 0; k2 < GNImageAligner.this.n; ++k2) {
                                h2 += GNImageAligner.this.subspaceJacobian[i2][k2] * GNImageAligner.this.subspaceJacobian[j2][k2];
                            }
                            h2 = GNImageAligner.this.hessian.get(i2, j2) + K * h2;
                            GNImageAligner.this.hessian.put(i2, j2, h2);
                            GNImageAligner.this.hessian.put(j2, i2, h2);
                        }
                        double g2 = 0.0;
                        for (int k3 = 0; k3 < GNImageAligner.this.n; ++k3) {
                            g2 -= GNImageAligner.this.subspaceJacobian[i2][k3] * GNImageAligner.this.subspaceResidual[k3];
                        }
                        g2 = GNImageAligner.this.gradient.get(i2) + K * g2;
                        GNImageAligner.this.gradient.put(i2, g2);
                    }
                }
            });
        }
        int rows = this.hessian.rows();
        int cols = this.hessian.cols();
        for (int i4 = 0; i4 < rows; ++i4) {
            for (int j2 = 0; j2 < cols; ++j2) {
                double h2 = this.hessian.get(i4, j2);
                double g2 = 0.0;
                if (this.settings.gammaTgamma != null && i4 < this.settings.gammaTgamma.rows() && j2 < this.settings.gammaTgamma.cols()) {
                    g2 = this.settings.gammaTgamma.get(i4, j2);
                }
                double a2 = 0.0;
                if (i4 == j2 && i4 < this.n) {
                    a2 = this.settings.alphaTikhonov * this.settings.alphaTikhonov;
                }
                this.hessian.put(i4, j2, h2 + g2 + a2);
            }
        }
    }

    protected void doRoi() {
        this.transformer.transform(this.srcRoiPts, this.dstRoiPts, this.parameters, false);
        double[] pts = this.dstRoiPts.get();
        int i2 = 0;
        while (i2 < pts.length) {
            int n2 = i2++;
            pts[n2] = pts[n2] / (double)(1 << this.pyramidLevel);
        }
        this.roi.x(0).y(0).width(this.mask[this.pyramidLevel].width()).height(this.mask[this.pyramidLevel].height());
        JavaCV.boundingRect(pts, this.roi, 3, 3, 16, 1);
        opencv_core.cvSetZero((CvArr)this.mask[this.pyramidLevel]);
        this.dstRoiPtsArray.put((byte)16, pts);
        opencv_imgproc.cvFillConvexPoly((CvArr)this.mask[this.pyramidLevel], (CvPoint)this.dstRoiPtsArray, (int)4, (CvScalar)CvScalar.WHITE, (int)8, (int)16);
    }

    protected void doResidual() {
        this.parameters.getConstraintError();
        ImageTransformer.Data d2 = this.residualTransformerData[0];
        d2.srcImg = this.template[this.pyramidLevel];
        d2.subImg = this.target[this.pyramidLevel];
        d2.srcDotImg = null;
        d2.transImg = this.transformed[this.pyramidLevel];
        d2.dstImg = this.residual[this.pyramidLevel];
        d2.mask = this.mask[this.pyramidLevel];
        d2.zeroThreshold = this.settings.thresholdsZero[Math.min(this.settings.thresholdsZero.length - 1, this.pyramidLevel)];
        d2.outlierThreshold = this.settings.thresholdsOutlier[Math.min(this.settings.thresholdsOutlier.length - 1, this.pyramidLevel)];
        if (this.settings.thresholdsMulRMSE) {
            d2.zeroThreshold *= this.RMSE;
            d2.outlierThreshold *= this.RMSE;
        }
        d2.pyramidLevel = this.pyramidLevel;
        this.transformer.transform(this.residualTransformerData, this.roi, this.parametersArray, null);
        double dstDstDot = this.residualTransformerData[0].dstDstDot.get(0);
        int dstCount = this.residualTransformerData[0].dstCount;
        this.RMSE = dstCount < this.n ? Double.NaN : Math.sqrt(dstDstDot / (double)dstCount);
        this.residualUpdateNeeded = false;
    }

    public static class Settings
    extends ImageAligner.Settings
    implements Cloneable {
        double stepSize = 0.1;
        double[] lineSearch = new double[]{1.0, 0.25};
        double deltaMin = 10.0;
        double deltaMax = 300.0;
        double displacementMax = 0.2;
        double alphaSubspace = 0.1;
        double alphaTikhonov = 0.0;
        CvMat gammaTgamma = null;
        boolean constrained = false;

        public Settings() {
        }

        public Settings(Settings s2) {
            super(s2);
            this.stepSize = s2.stepSize;
            this.lineSearch = s2.lineSearch;
            this.deltaMin = s2.deltaMin;
            this.deltaMax = s2.deltaMax;
            this.displacementMax = s2.displacementMax;
            this.alphaSubspace = s2.alphaSubspace;
            this.alphaTikhonov = s2.alphaTikhonov;
            this.gammaTgamma = s2.gammaTgamma;
            this.constrained = s2.constrained;
        }

        public double getStepSize() {
            return this.stepSize;
        }

        public void setStepSize(double stepSize) {
            this.stepSize = stepSize;
        }

        public double[] getLineSearch() {
            return this.lineSearch;
        }

        public void setLineSearch(double[] lineSearch) {
            this.lineSearch = lineSearch;
        }

        public double getDeltaMin() {
            return this.deltaMin;
        }

        public void setDeltaMin(double deltaMin) {
            this.deltaMin = deltaMin;
        }

        public double getDeltaMax() {
            return this.deltaMax;
        }

        public void setDeltaMax(double deltaMax) {
            this.deltaMax = deltaMax;
        }

        public double getDisplacementMax() {
            return this.displacementMax;
        }

        public void setDisplacementMax(double displacementMax) {
            this.displacementMax = displacementMax;
        }

        public double getAlphaSubspace() {
            return this.alphaSubspace;
        }

        public void setAlphaSubspace(double alphaSubspace) {
            this.alphaSubspace = alphaSubspace;
        }

        public double getAlphaTikhonov() {
            return this.alphaTikhonov;
        }

        public void setAlphaTikhonov(double alphaTikhonov) {
            this.alphaTikhonov = alphaTikhonov;
        }

        public CvMat getGammaTgamma() {
            return this.gammaTgamma;
        }

        public void setGammaTgamma(CvMat gammaTgamma) {
            this.gammaTgamma = gammaTgamma;
        }

        @Override
        public Settings clone() {
            return new Settings(this);
        }
    }
}

