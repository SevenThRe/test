/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj.pixels;

import info.journeymap.shaded.ar.com.hjg.pngj.FilterType;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.PngHelperInternal;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjExceptionInternal;
import java.util.Arrays;

public class FiltersPerformance {
    private final ImageInfo iminfo;
    private double memoryA = 0.7;
    private int lastrow = -1;
    private double[] absum = new double[5];
    private double[] entropy = new double[5];
    private double[] cost = new double[5];
    private int[] histog = new int[256];
    private int lastprefered = -1;
    private boolean initdone = false;
    private double preferenceForNone = 1.0;
    public static final double[] FILTER_WEIGHTS_DEFAULT = new double[]{0.73, 1.03, 0.97, 1.11, 1.22};
    private double[] filter_weights = new double[]{-1.0, -1.0, -1.0, -1.0, -1.0};
    private static final double LOG2NI = -1.0 / Math.log(2.0);

    public FiltersPerformance(ImageInfo imgInfo) {
        this.iminfo = imgInfo;
    }

    private void init() {
        if (this.filter_weights[0] < 0.0) {
            System.arraycopy(FILTER_WEIGHTS_DEFAULT, 0, this.filter_weights, 0, 5);
            double wNone = this.filter_weights[0];
            if (this.iminfo.bitDepth == 16) {
                wNone = 1.2;
            } else if (this.iminfo.alpha) {
                wNone = 0.8;
            } else if (this.iminfo.indexed || this.iminfo.bitDepth < 8) {
                wNone = 0.4;
            }
            this.filter_weights[0] = wNone /= this.preferenceForNone;
        }
        Arrays.fill(this.cost, 1.0);
        this.initdone = true;
    }

    public void updateFromFiltered(FilterType ftype, byte[] rowff, int rown) {
        this.updateFromRawOrFiltered(ftype, rowff, null, null, rown);
    }

    public void updateFromRaw(FilterType ftype, byte[] rowb, byte[] rowbprev, int rown) {
        this.updateFromRawOrFiltered(ftype, null, rowb, rowbprev, rown);
    }

    private void updateFromRawOrFiltered(FilterType ftype, byte[] rowff, byte[] rowb, byte[] rowbprev, int rown) {
        if (!this.initdone) {
            this.init();
        }
        if (rown != this.lastrow) {
            Arrays.fill(this.absum, Double.NaN);
            Arrays.fill(this.entropy, Double.NaN);
        }
        this.lastrow = rown;
        if (rowff != null) {
            this.computeHistogram(rowff);
        } else {
            this.computeHistogramForFilter(ftype, rowb, rowbprev);
        }
        if (ftype == FilterType.FILTER_NONE) {
            this.entropy[ftype.val] = this.computeEntropyFromHistogram();
        } else {
            this.absum[ftype.val] = this.computeAbsFromHistogram();
        }
    }

    public FilterType getPreferred() {
        int fi = 0;
        double vali = Double.MAX_VALUE;
        double val = 0.0;
        for (int i = 0; i < 5; ++i) {
            if (!Double.isNaN(this.absum[i])) {
                val = this.absum[i];
            } else {
                if (Double.isNaN(this.entropy[i])) continue;
                val = (Math.pow(2.0, this.entropy[i]) - 1.0) * 0.5;
            }
            val *= this.filter_weights[i];
            this.cost[i] = val = this.cost[i] * this.memoryA + (1.0 - this.memoryA) * val;
            if (!(val < vali)) continue;
            vali = val;
            fi = i;
        }
        this.lastprefered = fi;
        return FilterType.getByVal(this.lastprefered);
    }

    public final void computeHistogramForFilter(FilterType filterType, byte[] rowb, byte[] rowbprev) {
        Arrays.fill(this.histog, 0);
        int imax = this.iminfo.bytesPerRow;
        switch (filterType) {
            case FILTER_NONE: {
                for (int i = 1; i <= imax; ++i) {
                    int n = rowb[i] & 0xFF;
                    this.histog[n] = this.histog[n] + 1;
                }
                break;
            }
            case FILTER_PAETH: {
                int i;
                for (i = 1; i <= imax; ++i) {
                    int n = PngHelperInternal.filterRowPaeth(rowb[i], 0, rowbprev[i] & 0xFF, 0);
                    this.histog[n] = this.histog[n] + 1;
                }
                int j = 1;
                i = this.iminfo.bytesPixel + 1;
                while (i <= imax) {
                    int n = PngHelperInternal.filterRowPaeth(rowb[i], rowb[j] & 0xFF, rowbprev[i] & 0xFF, rowbprev[j] & 0xFF);
                    this.histog[n] = this.histog[n] + 1;
                    ++i;
                    ++j;
                }
                break;
            }
            case FILTER_SUB: {
                int i;
                for (i = 1; i <= this.iminfo.bytesPixel; ++i) {
                    int n = rowb[i] & 0xFF;
                    this.histog[n] = this.histog[n] + 1;
                }
                int j = 1;
                i = this.iminfo.bytesPixel + 1;
                while (i <= imax) {
                    int n = rowb[i] - rowb[j] & 0xFF;
                    this.histog[n] = this.histog[n] + 1;
                    ++i;
                    ++j;
                }
                break;
            }
            case FILTER_UP: {
                for (int i = 1; i <= this.iminfo.bytesPerRow; ++i) {
                    int n = rowb[i] - rowbprev[i] & 0xFF;
                    this.histog[n] = this.histog[n] + 1;
                }
                break;
            }
            case FILTER_AVERAGE: {
                int i;
                for (i = 1; i <= this.iminfo.bytesPixel; ++i) {
                    int n = (rowb[i] & 0xFF) - (rowbprev[i] & 0xFF) / 2 & 0xFF;
                    this.histog[n] = this.histog[n] + 1;
                }
                int j = 1;
                i = this.iminfo.bytesPixel + 1;
                while (i <= imax) {
                    int n = (rowb[i] & 0xFF) - ((rowbprev[i] & 0xFF) + (rowb[j] & 0xFF)) / 2 & 0xFF;
                    this.histog[n] = this.histog[n] + 1;
                    ++i;
                    ++j;
                }
                break;
            }
            default: {
                throw new PngjExceptionInternal("Bad filter:" + (Object)((Object)filterType));
            }
        }
    }

    public void computeHistogram(byte[] rowff) {
        Arrays.fill(this.histog, 0);
        for (int i = 1; i < this.iminfo.bytesPerRow; ++i) {
            int n = rowff[i] & 0xFF;
            this.histog[n] = this.histog[n] + 1;
        }
    }

    public double computeAbsFromHistogram() {
        int i;
        int s = 0;
        for (i = 1; i < 128; ++i) {
            s += this.histog[i] * i;
        }
        i = 128;
        for (int j = 128; j > 0; --j) {
            s += this.histog[i] * j;
            ++i;
        }
        return (double)s / (double)this.iminfo.bytesPerRow;
    }

    public final double computeEntropyFromHistogram() {
        double s = 1.0 / (double)this.iminfo.bytesPerRow;
        double ls = Math.log(s);
        double h = 0.0;
        for (int x : this.histog) {
            if (x <= 0) continue;
            h += (Math.log(x) + ls) * (double)x;
        }
        if ((h *= s * LOG2NI) < 0.0) {
            h = 0.0;
        }
        return h;
    }

    public void setPreferenceForNone(double preferenceForNone) {
        this.preferenceForNone = preferenceForNone;
    }

    public void tuneMemory(double m) {
        this.memoryA = m == 0.0 ? 0.0 : Math.pow(this.memoryA, 1.0 / m);
    }

    public void setFilterWeights(double[] weights) {
        System.arraycopy(weights, 0, this.filter_weights, 0, 5);
    }
}

