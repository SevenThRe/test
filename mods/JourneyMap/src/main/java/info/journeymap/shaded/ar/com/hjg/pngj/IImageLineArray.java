/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.FilterType;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;

public interface IImageLineArray {
    public ImageInfo getImageInfo();

    public FilterType getFilterType();

    public int getSize();

    public int getElem(int var1);
}

