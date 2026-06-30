/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.DeflatedChunksSet;
import info.journeymap.shaded.ar.com.hjg.pngj.Deinterlacer;
import info.journeymap.shaded.ar.com.hjg.pngj.FilterType;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;
import info.journeymap.shaded.ar.com.hjg.pngj.PngHelperInternal;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjInputException;
import info.journeymap.shaded.ar.com.hjg.pngj.RowInfo;
import java.util.Arrays;
import java.util.zip.Checksum;
import java.util.zip.Inflater;

public class IdatSet
extends DeflatedChunksSet {
    protected byte[] rowUnfiltered;
    protected byte[] rowUnfilteredPrev;
    protected final ImageInfo imgInfo;
    protected final Deinterlacer deinterlacer;
    final RowInfo rowinfo;
    protected int[] filterUseStat = new int[5];

    public IdatSet(String id, ImageInfo iminfo, Deinterlacer deinterlacer) {
        this(id, iminfo, deinterlacer, null, null);
    }

    public IdatSet(String id, ImageInfo iminfo, Deinterlacer deinterlacer, Inflater inf, byte[] buffer) {
        super(id, deinterlacer != null ? deinterlacer.getBytesToRead() + 1 : iminfo.bytesPerRow + 1, iminfo.bytesPerRow + 1, inf, buffer);
        this.imgInfo = iminfo;
        this.deinterlacer = deinterlacer;
        this.rowinfo = new RowInfo(iminfo, deinterlacer);
    }

    public void unfilterRow() {
        this.unfilterRow(this.rowinfo.bytesRow);
    }

    protected void unfilterRow(int nbytes) {
        if (this.rowUnfiltered == null || this.rowUnfiltered.length < this.row.length) {
            this.rowUnfiltered = new byte[this.row.length];
            this.rowUnfilteredPrev = new byte[this.row.length];
        }
        if (this.rowinfo.rowNsubImg == 0) {
            Arrays.fill(this.rowUnfiltered, (byte)0);
        }
        byte[] tmp = this.rowUnfiltered;
        this.rowUnfiltered = this.rowUnfilteredPrev;
        this.rowUnfilteredPrev = tmp;
        byte ftn = this.row[0];
        FilterType ft = FilterType.getByVal(ftn);
        if (ft == null) {
            throw new PngjInputException("Filter type " + ftn + " invalid");
        }
        byte by = ftn;
        this.filterUseStat[by] = this.filterUseStat[by] + 1;
        this.rowUnfiltered[0] = this.row[0];
        switch (ft) {
            case FILTER_NONE: {
                this.unfilterRowNone(nbytes);
                break;
            }
            case FILTER_SUB: {
                this.unfilterRowSub(nbytes);
                break;
            }
            case FILTER_UP: {
                this.unfilterRowUp(nbytes);
                break;
            }
            case FILTER_AVERAGE: {
                this.unfilterRowAverage(nbytes);
                break;
            }
            case FILTER_PAETH: {
                this.unfilterRowPaeth(nbytes);
                break;
            }
            default: {
                throw new PngjInputException("Filter type " + ftn + " not implemented");
            }
        }
    }

    private void unfilterRowAverage(int nbytes) {
        int j = 1 - this.imgInfo.bytesPixel;
        int i = 1;
        while (i <= nbytes) {
            int x = j > 0 ? this.rowUnfiltered[j] & 0xFF : 0;
            this.rowUnfiltered[i] = (byte)(this.row[i] + (x + (this.rowUnfilteredPrev[i] & 0xFF)) / 2);
            ++i;
            ++j;
        }
    }

    private void unfilterRowNone(int nbytes) {
        for (int i = 1; i <= nbytes; ++i) {
            this.rowUnfiltered[i] = this.row[i];
        }
    }

    private void unfilterRowPaeth(int nbytes) {
        int j = 1 - this.imgInfo.bytesPixel;
        int i = 1;
        while (i <= nbytes) {
            int x = j > 0 ? this.rowUnfiltered[j] & 0xFF : 0;
            int y = j > 0 ? this.rowUnfilteredPrev[j] & 0xFF : 0;
            this.rowUnfiltered[i] = (byte)(this.row[i] + PngHelperInternal.filterPaethPredictor(x, this.rowUnfilteredPrev[i] & 0xFF, y));
            ++i;
            ++j;
        }
    }

    private void unfilterRowSub(int nbytes) {
        int i;
        for (i = 1; i <= this.imgInfo.bytesPixel; ++i) {
            this.rowUnfiltered[i] = this.row[i];
        }
        int j = 1;
        i = this.imgInfo.bytesPixel + 1;
        while (i <= nbytes) {
            this.rowUnfiltered[i] = (byte)(this.row[i] + this.rowUnfiltered[j]);
            ++i;
            ++j;
        }
    }

    private void unfilterRowUp(int nbytes) {
        for (int i = 1; i <= nbytes; ++i) {
            this.rowUnfiltered[i] = (byte)(this.row[i] + this.rowUnfilteredPrev[i]);
        }
    }

    protected void preProcessRow() {
        super.preProcessRow();
        this.rowinfo.update(this.getRown());
        this.unfilterRow();
        this.rowinfo.updateBuf(this.rowUnfiltered, this.rowinfo.bytesRow + 1);
    }

    protected int processRowCallback() {
        int bytesNextRow = this.advanceToNextRow();
        return bytesNextRow;
    }

    protected void processDoneCallback() {
    }

    public int advanceToNextRow() {
        int bytesNextRow;
        if (this.deinterlacer == null) {
            bytesNextRow = this.getRown() >= this.imgInfo.rows - 1 ? 0 : this.imgInfo.bytesPerRow + 1;
        } else {
            boolean more = this.deinterlacer.nextRow();
            int n = bytesNextRow = more ? this.deinterlacer.getBytesToRead() + 1 : 0;
        }
        if (!this.isCallbackMode()) {
            this.prepareForNextRow(bytesNextRow);
        }
        return bytesNextRow;
    }

    public boolean isRowReady() {
        return !this.isWaitingForMoreInput();
    }

    public byte[] getUnfilteredRow() {
        return this.rowUnfiltered;
    }

    public Deinterlacer getDeinterlacer() {
        return this.deinterlacer;
    }

    void updateCrcs(Checksum ... idatCrcs) {
        for (Checksum idatCrca : idatCrcs) {
            if (idatCrca == null) continue;
            idatCrca.update(this.getUnfilteredRow(), 1, this.getRowFilled() - 1);
        }
    }

    public void close() {
        super.close();
        this.rowUnfiltered = null;
        this.rowUnfilteredPrev = null;
    }

    public int[] getFilterUseStat() {
        return this.filterUseStat;
    }
}

