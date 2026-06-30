/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.IImageLine;
import info.journeymap.shaded.ar.com.hjg.pngj.IImageLineSet;
import info.journeymap.shaded.ar.com.hjg.pngj.ImageInfo;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface IImageLineSetFactory<T extends IImageLine> {
    public IImageLineSet<T> create(ImageInfo var1, boolean var2, int var3, int var4, int var5);
}

