/*
 * Decompiled with CFR 0.152.
 */
package info.journeymap.shaded.ar.com.hjg.pngj;

import info.journeymap.shaded.ar.com.hjg.pngj.PngjInputException;
import info.journeymap.shaded.ar.com.hjg.pngj.PngjOutputException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

final class PngHelperInternal2 {
    PngHelperInternal2() {
    }

    static OutputStream ostreamFromFile(File f, boolean allowoverwrite) {
        FileOutputStream os = null;
        if (f.exists() && !allowoverwrite) {
            throw new PngjOutputException("File already exists: " + f);
        }
        try {
            os = new FileOutputStream(f);
        }
        catch (Exception e) {
            throw new PngjInputException("Could not open for write" + f, e);
        }
        return os;
    }
}

