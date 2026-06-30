/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.FilterType;
import info.journeymap.shaded.ar.com.hjg.pngj.IImageLine;
import info.journeymap.shaded.ar.com.hjg.pngj.IImageLineArray;
import info.journeymap.shaded.ar.com.hjg.pngj.IImageLineFactory;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageLineHelper;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public class ImageLineInt
implements IImageLine,
IImageLineArray {
    public final ImageInfo imgInfo;
    protected final int[] scanline;
    protected final int size;
    protected FilterType filterType = FilterType.FILTER_UNKNOWN;

    public ImageLineInt(ImageInfo imgInfo) {
        this(imgInfo, null);
    }

    public ImageLineInt(ImageInfo imgInfo, int[] sci) {
        this.imgInfo = imgInfo;
        this.filterType = FilterType.FILTER_UNKNOWN;
        this.size = imgInfo.samplesPerRow;
        this.scanline = sci != null && sci.length >= this.size ? sci : new int[this.size];
    }

    @Override
    public FilterType getFilterType() {
        return this.filterType;
    }

    protected void setFilterType(FilterType ft) {
        this.filterType = ft;
    }

    public String toString() {
        return " cols=" + this.imgInfo.cols + " bpc=" + this.imgInfo.bitDepth + " size=" + this.scanline.length;
    }

    @Override
    public void readFromPngRaw(byte[] raw, int len, int offset, int step) {
        this.setFilterType(FilterType.getByVal(raw[0]));
        int len1 = len - 1;
        int step1 = (step - 1) * this.imgInfo.channels;
        if (this.imgInfo.bitDepth == 8) {
            if (step == 1) {
                for (int i = 0; i < this.size; ++i) {
                    this.scanline[i] = raw[i + 1] & 0xFF;
                }
            } else {
                int s = 1;
                int c = 0;
                int i = offset * this.imgInfo.channels;
                while (s <= len1) {
                    this.scanline[i] = raw[s] & 0xFF;
                    if (++c == this.imgInfo.channels) {
                        c = 0;
                        i += step1;
                    }
                    ++s;
                    ++i;
                }
            }
        } else if (this.imgInfo.bitDepth == 16) {
            if (step == 1) {
                int s = 1;
                for (int i = 0; i < this.size; ++i) {
                    this.scanline[i] = (raw[s++] & 0xFF) << 8 | raw[s++] & 0xFF;
                }
            } else {
                int i;
                int s = 1;
                int c = 0;
                int n = i = offset != 0 ? offset * this.imgInfo.channels : 0;
                while (s <= len1) {
                    this.scanline[i] = (raw[s++] & 0xFF) << 8 | raw[s] & 0xFF;
                    if (++c == this.imgInfo.channels) {
                        c = 0;
                        i += step1;
                    }
                    ++s;
                    ++i;
                }
            }
        } else {
            int bd = this.imgInfo.bitDepth;
            int mask0 = ImageLineHelper.getMaskForPackedFormats(bd);
            int i = offset * this.imgInfo.channels;
            int c = 0;
            for (int r = 1; r < len; ++r) {
                int mask = mask0;
                int shi = 8 - bd;
                do {
                    this.scanline[i] = (raw[r] & mask) >> shi;
                    mask >>= bd;
                    shi -= bd;
                    ++i;
                    if (++c != this.imgInfo.channels) continue;
                    c = 0;
                    i += step1;
                } while (mask != 0 && i < this.size);
            }
        }
    }

    @Override
    public void writeToPngRaw(byte[] raw) {
        raw[0] = (byte)this.filterType.val;
        if (this.imgInfo.bitDepth == 8) {
            for (int i = 0; i < this.size; ++i) {
                raw[i + 1] = (byte)this.scanline[i];
            }
        } else if (this.imgInfo.bitDepth == 16) {
            int s = 1;
            for (int i = 0; i < this.size; ++i) {
                raw[s++] = (byte)(this.scanline[i] >> 8);
                raw[s++] = (byte)(this.scanline[i] & 0xFF);
            }
        } else {
            int bd = this.imgInfo.bitDepth;
            int shi = 8 - bd;
            int v = 0;
            int r = 1;
            for (int i = 0; i < this.size; ++i) {
                v |= this.scanline[i] << shi;
                if ((shi -= bd) >= 0 && i != this.size - 1) continue;
                raw[r++] = (byte)v;
                shi = 8 - bd;
                v = 0;
            }
        }
    }

    @Override
    public void endReadFromPngRaw() {
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public int getElem(int i) {
        return this.scanline[i];
    }

    public int[] getScanline() {
        return this.scanline;
    }

    @Override
    public ImageInfo getImageInfo() {
        return this.imgInfo;
    }

    public static IImageLineFactory<ImageLineInt> getFactory(ImageInfo iminfo) {
        return new IImageLineFactory<ImageLineInt>(){

            @Override
            public ImageLineInt createImageLine(ImageInfo iminfo) {
                return new ImageLineInt(iminfo);
            }
        };
    }
}

