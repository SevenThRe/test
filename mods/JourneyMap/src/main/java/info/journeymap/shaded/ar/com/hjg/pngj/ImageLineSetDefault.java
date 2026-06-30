/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.IImageLine;
import info.journeymap.shaded.ar.com.hjg.pngj.IImageLineSet;
import info.journeymap.shaded.ar.com.hjg.pngj.IImageLineSetFactory;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageLineByte;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageLineInt;
import java.util.ArrayList;
import java.util.List;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public abstract class ImageLineSetDefault<T extends IImageLine>
implements IImageLineSet<T> {
    protected final ImageInfo imgInfo;
    private final boolean singleCursor;
    private final int nlines;
    private final int offset;
    private final int step;
    protected List<T> imageLines;
    protected T imageLine;
    protected int currentRow = -1;

    public ImageLineSetDefault(ImageInfo imgInfo, boolean singleCursor, int nlines, int noffset, int step) {
        this.imgInfo = imgInfo;
        this.singleCursor = singleCursor;
        if (singleCursor) {
            this.nlines = 1;
            this.offset = 0;
            this.step = 1;
        } else {
            this.nlines = imgInfo.rows;
            this.offset = 0;
            this.step = 1;
        }
        this.createImageLines();
    }

    private void createImageLines() {
        if (this.singleCursor) {
            this.imageLine = this.createImageLine();
        } else {
            this.imageLines = new ArrayList<T>();
            for (int i = 0; i < this.nlines; ++i) {
                this.imageLines.add(this.createImageLine());
            }
        }
    }

    protected abstract T createImageLine();

    @Override
    public T getImageLine(int n) {
        this.currentRow = n;
        if (this.singleCursor) {
            return this.imageLine;
        }
        return (T)((IImageLine)this.imageLines.get(this.imageRowToMatrixRowStrict(n)));
    }

    @Override
    public boolean hasImageLine(int n) {
        return this.singleCursor ? this.currentRow == n : this.imageRowToMatrixRowStrict(n) >= 0;
    }

    @Override
    public int size() {
        return this.nlines;
    }

    public int imageRowToMatrixRowStrict(int imrow) {
        int mrow = (imrow -= this.offset) >= 0 && imrow % this.step == 0 ? imrow / this.step : -1;
        return mrow < this.nlines ? mrow : -1;
    }

    public int matrixRowToImageRow(int mrow) {
        return mrow * this.step + this.offset;
    }

    public int imageRowToMatrixRow(int imrow) {
        int r = (imrow - this.offset) / this.step;
        return r < 0 ? 0 : (r < this.nlines ? r : this.nlines - 1);
    }

    public static IImageLineSetFactory<ImageLineInt> getFactoryInt() {
        return new IImageLineSetFactory<ImageLineInt>(){

            @Override
            public IImageLineSet<ImageLineInt> create(ImageInfo iminfo, boolean singleCursor, int nlines, int noffset, int step) {
                return new ImageLineSetDefault<ImageLineInt>(iminfo, singleCursor, nlines, noffset, step){

                    @Override
                    protected ImageLineInt createImageLine() {
                        return new ImageLineInt(this.imgInfo);
                    }
                };
            }
        };
    }

    public static IImageLineSetFactory<ImageLineByte> getFactoryByte() {
        return new IImageLineSetFactory<ImageLineByte>(){

            @Override
            public IImageLineSet<ImageLineByte> create(ImageInfo iminfo, boolean singleCursor, int nlines, int noffset, int step) {
                return new ImageLineSetDefault<ImageLineByte>(iminfo, singleCursor, nlines, noffset, step){

                    @Override
                    protected ImageLineByte createImageLine() {
                        return new ImageLineByte(this.imgInfo);
                    }
                };
            }
        };
    }
}

