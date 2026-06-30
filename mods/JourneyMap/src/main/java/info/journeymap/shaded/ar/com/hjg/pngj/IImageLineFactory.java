/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.IImageLine;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface IImageLineFactory<T extends IImageLine> {
    public T createImageLine(ImageInfo var1);
}

