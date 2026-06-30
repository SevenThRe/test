/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.IImageLine;

/*
 * This class specifies class file version 49.0 but uses Java 6 signatures.  Assumed Java 6.
 */
public interface IImageLineSet<T extends IImageLine> {
    public T getImageLine(int var1);

    public boolean hasImageLine(int var1);

    public int size();
}

