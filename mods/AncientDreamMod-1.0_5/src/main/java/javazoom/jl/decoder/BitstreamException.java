/*
 * Decompiled with CFR 0.152.
 */
package javazoom.jl.decoder;

import javazoom.jl.decoder.BitstreamErrors;
import javazoom.jl.decoder.JavaLayerException;

public class BitstreamException
extends JavaLayerException
implements BitstreamErrors {
    private int errorcode = 256;

    public BitstreamException(String string, Throwable throwable) {
        super(string, throwable);
    }

    public BitstreamException(int n, Throwable throwable) {
        this(BitstreamException.getErrorString(n), throwable);
        this.errorcode = n;
    }

    public int getErrorCode() {
        return this.errorcode;
    }

    public static String getErrorString(int n) {
        return "Bitstream errorcode " + Integer.toHexString(n);
    }
}

